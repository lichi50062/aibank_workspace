package com.tfb.aibank.chl.general.qu001.task;

import java.util.Arrays;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.availabletask.UidDupAvailableTaskEntityVo;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001020Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001020Rs;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001Cache;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001Constants;
import com.tfb.aibank.chl.general.qu001.model.ProductType;
import com.tfb.aibank.chl.general.qu001.service.NGNQU001Service;
import com.tfb.aibank.chl.general.resource.dto.QuickSearchResponse;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.UserVo;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)NGNQU001020Task.java
 *
 * <p>Description: 首頁大卡</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[存款]</li>
 * </ol>
 */
//@formatter:on

@Component
@Scope("prototype")
public class NGNQU001020Task extends AbstractAIBankBaseTask<NGNQU001020Rq, NGNQU001020Rs> {

    @Autowired
    @Qualifier("NGNQU001Service")
    private NGNQU001Service service;

    DataInput input = new DataInput();
    DataOutput output = new DataOutput();

    @Override
    public void validate(NGNQU001020Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNQU001020Rq rqData, NGNQU001020Rs rsData) throws ActionException {

        NGNQU001Cache cache = getCache();

        input.setLocale(getLocale());
        output.setQuickSearchData(cache.getQsearchData());
        ProductType productType = ProductType.findByName(rqData.getProduct());
        input.setProductType(productType);
        input.setSession(getSessionId());
        input.setAiBankUser(getLoginUser());

        logger.debug("== NGNQU001020 handle ==rqData.getProduct(): {}, DbuObu: {}", rqData.getProduct(), cache.getUserBUType());

        switch (productType) {
        case DEPOSIT -> getHomeCardDataDeposit();
        case CREDITCARD -> {
            checkDbuObu(cache, output);
            if (haveLoginUser() && getLoginUser().getCustomerType().isGeneral() && StringUtils.isEmpty(cache.getUserBUType())) {
                throwActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            input.setDbu("DBU".equals(cache.getUserBUType()));
            try {
                getHomeCardDataCreditCard();
            }
            catch (ServiceException e) {
                boolean nightModeErrCode = Arrays.asList(NGNQU001Service.NIGHTMODE_EAI_ERR_CD).contains(e.getErrorCode());
                boolean nightModeCEW301r = AIBankErrorCode.CREDIT_CARD_DATA_UPDATING.errorCodeEquals(e.getErrorCode());
                if (nightModeErrCode || nightModeCEW301r) {
                    throw ExceptionUtils.getActionException(ErrorCode.NIGHT_MODE_QUERY);
                }
                else {
                    throw e;
                }
            }
            catch (Exception e) {
                ActionException aex = ExceptionUtils.getActionException(e);
                boolean nightModeErrCode = Arrays.asList(NGNQU001Service.NIGHTMODE_EAI_ERR_CD).contains(aex.getErrorCode());
                boolean nightModeCEW301r = AIBankErrorCode.CREDIT_CARD_DATA_UPDATING.errorCodeEquals(aex.getErrorCode());

                if (nightModeErrCode || nightModeCEW301r) {
                    throw ExceptionUtils.getActionException(ErrorCode.NIGHT_MODE_QUERY);
                }
                else {
                    throw e;
                }
            }
        }
        case INVESTMENT -> {
            checkDbuObu(cache, output);
            if (haveLoginUser() && getLoginUser().getCustomerType().isGeneral() && StringUtils.isEmpty(cache.getUserBUType())) {
                throwActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            input.setDbu("DBU".equals(cache.getUserBUType()));
            getHomeCardDataInvestment();
            setCache(NGNQU001021Task.class.getCanonicalName(), cache);
        }
        case LOAN -> getHomeCardDataLoan();
        case INSURANCE -> getHomeCardDataInsurance();
        case STOCK -> {
            getHomeCardDataStock();
            cache.setQsearchData(output.getQuickSearchData());
            setCache(NGNQU001022Task.class.getCanonicalName(), cache);
        }
        }
        setRsData(productType, output, rsData);
        setTaskCache(cache);
    }

    /**
     * 已登入或有免登速查資料
     * 
     * @param output
     * @return
     */
    boolean haveUserDataCanDoQuery(DataOutput output) {
        return (isLoggedIn() && haveLoginUser()) || (null != output.getQuickSearchData() && output.getQuickSearchData().haveQuickSearchOn());
    }

    public void checkDbuObu(NGNQU001Cache cacheData, DataOutput output) throws ActionException {
        if (StringUtils.isEmpty(cacheData.getUserBUType())) {
            this.service.getDbuObu(getLoginUser(), cacheData.getQsearchData(), output);
            logger.debug("== checkDbuObu: {} ==", output.getDbuObu());
            cacheData.setUserBUType(output.getDbuObu());
        }
    }

    /**
     * 取得貸款卡片資料
     * 
     * @throws ActionException
     */
    private void getHomeCardDataLoan() throws ActionException {
        UidDupAvailableTaskEntityVo uidDupVo = availableTaskCacheManager.getUidDupAvailableTask(NGNQU001Constants.NBOQU013);
        if (uidDupVo != null && uidDupVo.getAvalibleFlag() == 0) {
            input.setCardDisableForUidDup(true);
        }

        if (haveUserDataCanDoQuery(output)) {
            if (haveLoginUser()) {
                boolean queryUnavailable = input.isCardDisableForUidDup() && getLoginUser().uidDuplicatedUser();

                if (getLoginUser().getCustomerType().isCardMember() || queryUnavailable) {
                    // HomePageCard 廣告資料
                    boolean bizUser = StringUtils.length(getLoginUser().getCustId()) == 8; // 企業客群
                    input.setTaGroup(bizUser ? "5" : "4");
                    service.getHomePageCardV2(ProductType.LOAN.getCardId(), input, output);
                }
                else {
                    input.setAiBankUser(getLoginUser());
                    service.getHomeCardDataLoan(input, output);
                }
            }
            else {
                // 如果免登速查時，沒有Namecode，此資料應屬信用卡會員，不查貸款總覽
                if (StringUtils.isEmpty(output.getQuickSearchData().getNameCode())) {
                    // HomePageCard 廣告資料
                    boolean bizUser = StringUtils.length(output.getQuickSearchData().getCustId()) == 8; // 企業客群
                    input.setTaGroup(bizUser ? "5" : "4");
                    service.getHomePageCardV2(ProductType.LOAN.getCardId(), input, output);
                }
                else {

                    boolean queryUnavailable = input.isCardDisableForUidDup() && !"0".equals(output.getQuickSearchData().getUidDup());

                    if (queryUnavailable) {
                        // HomePageCard 廣告資料
                        boolean bizUser = StringUtils.length(output.getQuickSearchData().getCustId()) == 8; // 企業客群
                        input.setTaGroup(bizUser ? "5" : "4");
                        service.getHomePageCardV2(ProductType.LOAN.getCardId(), input, output);
                    }
                    else {
                        QuickSearchResponse qData = output.getQuickSearchData();
                        input.setCustId(qData.getCustId());
                        input.setUserId(output.getQuickSearchData().getUserId());
                        input.setNameCode(output.getQuickSearchData().getNameCode());
                        input.setUidDup(output.getQuickSearchData().getUidDup());
                        input.setCompanyKind(output.getQuickSearchData().getCompanyKind());
                        service.getHomeCardDataLoan(input, output);
                    }
                }
            }
        }
        else {
            // HomePageCard 廣告資料
            input.setTaGroup("4");
            service.getHomePageCardV2(ProductType.LOAN.getCardId(), input, output);
        }
    }

    private void getHomeCardDataInsurance() {
        UidDupAvailableTaskEntityVo uidDupVo = availableTaskCacheManager.getUidDupAvailableTask(NGNQU001Constants.NBOQU010);
        if (uidDupVo != null && uidDupVo.getAvalibleFlag() == 0) {
            input.setCardDisableForUidDup(true);
        }

        // 查詢免登速查狀態
        if (haveUserDataCanDoQuery(output)) {
            // 登入狀態
            if (haveLoginUser()) {

                boolean queryUnavailable = input.isCardDisableForUidDup() && getLoginUser().uidDuplicatedUser();

                if (!queryUnavailable) {
                    input.setCustId(getLoginUser().getCustId());
                    input.setBirthday(getLoginUser().getBirthDay());
                    input.setLoginIp(getClientIp());
                    service.getHomeCardDataInsurance(input, output);
                    if (null != output.getCardDataInsurance()) {
                        if (CollectionUtils.isNotEmpty(output.getCardDataInsurance().getAiOtherInsurDataResponses()) || CollectionUtils.isNotEmpty(output.getCardDataInsurance().getAiFubonInsurDataResponses())) {
                            return;
                        }
                    }
                }
            }
            // 未登入 有開啟免登速查
            else {
                boolean queryUnavailable = input.isCardDisableForUidDup() && !"0".equals(output.getQuickSearchData().getUidDup());

                if (!queryUnavailable) {
                    input.setCustId(output.getQuickSearchData().getCustId());
                    input.setBirthday(output.getQuickSearchData().getBirthday());
                    input.setLoginIp(getClientIp());
                    service.getHomeCardDataInsurance(input, output);
                    if (null != output.getCardDataInsurance()) {
                        if (CollectionUtils.isNotEmpty(output.getCardDataInsurance().getAiOtherInsurDataResponses()) || CollectionUtils.isNotEmpty(output.getCardDataInsurance().getAiFubonInsurDataResponses())) {
                            return;
                        }
                    }
                }
            }
        }

        output.setCardDataInsurance(null);
        // HomePageCard 廣告資料
        service.getHomePageCardV2(ProductType.INSURANCE.getCardId(), input, output);
    }

    private void getHomeCardDataDeposit() {

        UidDupAvailableTaskEntityVo uidDupVo = availableTaskCacheManager.getUidDupAvailableTask(NGNQU001Constants.NDSQU001);
        if (uidDupVo != null && uidDupVo.getAvalibleFlag() == 0) {
            input.setCardDisableForUidDup(true);
        }

        if (haveUserDataCanDoQuery(output)) {
            if (haveLoginUser()) {
                input.setCustId(getLoginUser().getCustId());
                boolean queryUnavailable = input.isCardDisableForUidDup() && getLoginUser().uidDuplicatedUser();
                if (getLoginUser().getCustomerType().isCardMember() || queryUnavailable) {
                    // HomePageCard 廣告資料
                    service.getHomePageCardV2(ProductType.DEPOSIT.getCardId(), input, output);
                }
                else {
                    input.setUserId(getLoginUser().getUserData().getUserUuid());
                    input.setNameCode(getLoginUser().getNameCode());
                    input.setUidDup(getLoginUser().getUidDup());
                    service.getHomeCardDataDeposit(input, output);
                }
            }
            else {
                QuickSearchResponse qData = output.getQuickSearchData();
                input.setCustId(qData.getCustId());
                // 如果免登速查時，沒有Namecode，此資料應屬信用卡會員，不查存款總覽
                if (StringUtils.isEmpty(output.getQuickSearchData().getNameCode())) {
                    // HomePageCard 廣告資料
                    service.getHomePageCardV2(ProductType.DEPOSIT.getCardId(), input, output);
                }
                else {

                    boolean queryUnavailable = input.isCardDisableForUidDup() && !"0".equals(output.getQuickSearchData().getUidDup());

                    if (queryUnavailable) {
                        // HomePageCard 廣告資料
                        service.getHomePageCardV2(ProductType.DEPOSIT.getCardId(), input, output);
                    }
                    else {
                        input.setUserId(output.getQuickSearchData().getUserId());
                        input.setNameCode(output.getQuickSearchData().getNameCode());
                        input.setUidDup(output.getQuickSearchData().getUidDup());
                        service.getHomeCardDataDeposit(input, output);
                    }

                }
            }
        }
        else {
            // HomePageCard 廣告資料
            service.getHomePageCardV2(ProductType.DEPOSIT.getCardId(), input, output);
        }
    }

    private void getHomeCardDataInvestment() throws ActionException {
        UidDupAvailableTaskEntityVo uidDupVo = availableTaskCacheManager.getUidDupAvailableTask(NGNQU001Constants.NBOQU004);
        if (uidDupVo != null && uidDupVo.getAvalibleFlag() == 0) {
            input.setCardDisableForUidDup(true);
        }

        if (haveUserDataCanDoQuery(output)) {
            if (haveLoginUser()) {
                input.setCustId(getLoginUser().getCustId());
                boolean queryUnavailable = input.isCardDisableForUidDup() && getLoginUser().uidDuplicatedUser();
                if (getLoginUser().getCustomerType().isCardMember() || queryUnavailable) {
                    // HomePageCard 廣告資料
                    service.getHomePageCardV2(ProductType.INVESTMENT.getCardId(), input, output);
                }
                else {
                    input.setNameCode(getLoginUser().getNameCode());
                    input.setHaveCreditCard(!service.userIsSpecialOrNoCreditCard(getLoginUser(), getLocale()));
                    input.setUserId(getLoginUser().getUserId());
                    input.setAiBankUser(getLoginUser());
                    service.getHomeCardDataInvestment(input, output);
                }
            }
            else {
                input.setCustId(output.getQuickSearchData().getCustId());
                // 如果免登速查時，沒有Namecode，此資料應屬信用卡會員，不查投資
                if (StringUtils.isEmpty(output.getQuickSearchData().getNameCode())) {
                    // HomePageCard 廣告資料
                    service.getHomePageCardV2(ProductType.INVESTMENT.getCardId(), input, output);
                }
                else {
                    boolean queryUnavailable = input.isCardDisableForUidDup() && !"0".equals(output.getQuickSearchData().getUidDup());

                    if (queryUnavailable) {
                        // HomePageCard 廣告資料
                        service.getHomePageCardV2(ProductType.INVESTMENT.getCardId(), input, output);
                    }
                    else {
                        input.setNameCode(output.getQuickSearchData().getNameCode());
                        input.setHaveCreditCard(!output.getQuickSearchData().isSpecialUserOrNoCreditCard());
                        input.setUserId(StringUtils.EMPTY);
                        service.getHomeCardDataInvestment(input, output);
                    }
                }
            }
        }
        else {
            // HomePageCard 廣告資料
            service.getHomePageCardV2(ProductType.INVESTMENT.getCardId(), input, output);
        }
    }

    private void getHomeCardDataCreditCard() throws ActionException {

        UidDupAvailableTaskEntityVo uidDupVo = availableTaskCacheManager.getUidDupAvailableTask(NGNQU001Constants.NCCQU001);
        if (uidDupVo != null && uidDupVo.getAvalibleFlag() == 0) {
            input.setCardDisableForUidDup(true);
        }

        if (haveLoginUser() || (null != output.getQuickSearchData() && output.getQuickSearchData().haveQuickSearchOn())) {

            boolean haveCC = false;
            if (haveLoginUser()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("AiBankUser: {}", IBUtils.attribute2Str(getLoginUser()));
                }
                input.setCustId(getLoginUser().getCustId());
                haveCC = !service.userIsSpecialOrNoCreditCard(getLoginUser(), getLocale());
            }
            else {
                input.setCustId(output.getQuickSearchData().getCustId());
                haveCC = !output.getQuickSearchData().isSpecialUserOrNoCreditCard();
            }
            if (haveCC) {
                boolean queryUnavailable = false;

                input.setLocale(getLocale());
                if (haveLoginUser()) {
                    queryUnavailable = input.isCardDisableForUidDup() && getLoginUser().uidDuplicatedUser();
                    input.setNameCode(getLoginUser().getNameCode());
                }
                else {
                    queryUnavailable = input.isCardDisableForUidDup() && !"0".equals(output.getQuickSearchData().getUidDup());
                    input.setNameCode(output.getQuickSearchData().getNameCode());
                    input.setInAccountCreditcardCheck(output.getQuickSearchData().isInAccountCreditcardCheck());
                    input.setSameBirthday(output.getQuickSearchData().isSameBirthday());
                }

                if (queryUnavailable) {
                    service.getHomePageCardV2(ProductType.CREDITCARD.getCardId(), input, output);
                }
                else {
                    try {
                        this.service.getHomeCardDataCreditCard(input, output, getLoginUser());
                    }
                    catch (ServiceException e) {
                        logger.error("== getHomeCardDataCreditCard err ==> ", e);
                        // 查信用卡有錯，出廣告
                        if (AIBankErrorCode.CREDIT_CARD_NOT_FOUND.getErrorCode().equals(e.getErrorCode())) {
                            // 查信用卡有錯時「AIBankErrorCode.CREDIT_CARD_NOT_FOUND」在前端會去共通錯誤頁，所以改成出廣告
                            service.getHomePageCardV2(ProductType.CREDITCARD.getCardId(), input, output);
                        }
                        else {
                            throw e;
                        }
                    }
                }
            }
            else {
                // 沒信用卡，出廣告
                service.getHomePageCardV2(ProductType.CREDITCARD.getCardId(), input, output);
            }
        }
        else {
            // HomePageCard 廣告資料
            service.getHomePageCardV2(ProductType.CREDITCARD.getCardId(), input, output);
        }
    }

    private void getHomeCardDataStock() {
        UidDupAvailableTaskEntityVo uidDupVo = availableTaskCacheManager.getUidDupAvailableTask(NGNQU001Constants.NBOQU011);
        if (uidDupVo != null && uidDupVo.getAvalibleFlag() == 0) {
            input.setCardDisableForUidDup(true);
        }

        boolean queryUnavailable = false;
        if (haveUserDataCanDoQuery(output)) {
            if (haveLoginUser()) {

                queryUnavailable = input.isCardDisableForUidDup() && getLoginUser().uidDuplicatedUser();
                input.setAiBankUser(getLoginUser());
                input.setLoggedIn(true);
            }
            else {
                // 免登速查
                queryUnavailable = input.isCardDisableForUidDup() && !"0".equals(output.getQuickSearchData().getUidDup());

                UserVo vo = new UserVo();
                vo.setUserUuid(output.getQuickSearchData().getUserId());
                vo.setNameCode(output.getQuickSearchData().getNameCode());
                vo.setUserKey(output.getQuickSearchData().getUserKey());
                vo.setCompanyKey(output.getQuickSearchData().getCompanyKey());
                AIBankUser aiBankUser = new AIBankUser(vo);
                CompanyVo cvo = new CompanyVo();
                cvo.setUidDup(output.getQuickSearchData().getUidDup());
                cvo.setCompanyKey(output.getQuickSearchData().getCompanyKey());
                cvo.setCompanyKind(output.getQuickSearchData().getCompanyKind());
                aiBankUser.setCustId(output.getQuickSearchData().getCustId());
                aiBankUser.setCompanyVo(cvo);
                aiBankUser.setBirthDay(output.getQuickSearchData().getBirthday());

                input.setAiBankUser(aiBankUser);
                input.setLoggedIn(false);
            }

            if (queryUnavailable) {
                // HomePageCard 廣告資料
                service.getHomePageCardV2(ProductType.STOCK.getCardId(), input, output);
            }
            else {
                service.getHomeCardDataStockV2(input, output);
                if (!haveLoginUser()) {
                    output.getQuickSearchData().setUserKey(input.getAiBankUser().getUserKey());
                    output.getQuickSearchData().setCompanyKey(input.getAiBankUser().getCompanyKey());
                    output.getQuickSearchData().setBirthday(input.getAiBankUser().getBirthDay());
                    output.getCardDataStock().setqSearchFlag(StringUtils.equals("1", output.getQuickSearchData().getQsearchFlag()));
                }
            }

        }
        else {
            // HomePageCard 廣告資料
            service.getHomePageCardV2(ProductType.STOCK.getCardId(), input, output);
        }
    }

    /**
     * 放置rsData資料
     */
    private void setRsData(ProductType productType, DataOutput output, NGNQU001020Rs rsData) {
        switch (productType) {
        case DEPOSIT -> {
            rsData.setDeposit(output.getCardDataDeposit());
        }
        case CREDITCARD -> {
            rsData.setCreditCard(output.getCardDataCreditCard());
        }
        case INVESTMENT -> {
            rsData.setInvestment(output.getCardDataInvestment());
        }
        case LOAN -> {
            rsData.setLoan(output.getCardDataLoan());
        }
        case INSURANCE -> {
            rsData.setInsurance(output.getCardDataInsurance());
        }
        case STOCK -> {
            rsData.setStock(output.getCardDataStock());
        }
        }
    }

    private boolean haveLoginUser() {
        return null != getLoginUser();
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    public NGNQU001Cache getCache() {
        NGNQU001Cache cache = getCache(NGNQU001Cache.class.getCanonicalName(), NGNQU001Cache.class);
        if (cache == null) {
            cache = new NGNQU001Cache();
        }
        return cache;
    }

    private void setTaskCache(NGNQU001Cache cacheData) {
        setCache(NGNQU001Cache.class.getCanonicalName(), cacheData);
    }
}
