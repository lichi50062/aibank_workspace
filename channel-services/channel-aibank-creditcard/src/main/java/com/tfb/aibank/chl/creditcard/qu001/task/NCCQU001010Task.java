package com.tfb.aibank.chl.creditcard.qu001.task;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001Bill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001BonusRewards;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001InstallmentGroup;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001010Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001010Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001PaymentInfo;
import com.tfb.aibank.chl.creditcard.qu001.service.NCCQU001Service;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RA021Repeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RB100Repeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW315RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0051Response;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.CreditCardIdType;

//@formatter:off
/**
* @(#)NCCQU001010Task.java
* 
* <p>Description:信用卡總覽 功能首頁</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU001010Task extends AbstractAIBankBaseTask<NCCQU001010Rq, NCCQU001010Rs> {
    @Autowired
    protected UserDataCacheService cardService;

    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private NCCQU001Utils utils;

    @Autowired
    private NCCQU001Service service;

    @Autowired
    private UserDataCacheService userCreditCardService;

    @Autowired
    private CurrencyCodeCacheManager currencyCodeCacheManager;

    @Autowired
    private CreditCardResource creditCardResource;

    @Override
    public void validate(NCCQU001010Rq rqData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();

        // 若為信用卡特殊戶，引導至「共通錯誤頁」顯示錯誤訊息：本行目前無法查詢您的信用卡資料，如有疑問，請洽本行信用卡客服中心02-8751-1313。
        if (userCreditCardService.isSpecialCreditCard(aibankUser))
            throwActionException(ErrorCode.SPECIAL_CARD_USER);
    }

    @Override
    public void handle(NCCQU001010Rq rqData, NCCQU001010Rs rsData) throws ActionException {
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        if (null == cache) {
            cache = new NCCQU001Cache();
        }
        AIBankUser aiBankUser = getLoginUser();
        Locale userLocale = getLocale();

        List<CreditCard> creditCards = aiBankUser.getAllCreditCards();
        if (CollectionUtils.isEmpty(creditCards)) {
            creditCards = cardService.getAllCreditCards(aiBankUser, getLocale());
        }

        if (CollectionUtils.isEmpty(creditCards)) {
            rsData.setNoCreditCard(true);
            return;
        }

        //  是否持有好市多正卡
        cache.setCostcoMerber(utils.isCostcoMerber(creditCards));

        logger.debug("信用卡暫存 有{}資料", CollectionUtils.size(creditCards));

        String actionType = rqData.getActionType();
        String cardKey = rqData.getCardKey();
        rsData.setCardKey(cardKey);
        String custId = aiBankUser.getCustId();

        // 是否有好多金資料
        // 帳單資訊默認有 不需要檢核
        boolean hasCostcoCard = true;

        // 持有正卡
        boolean hasPrimaryCard = utils.hasPrimaryCardForSessionData(creditCards);

        // 傳入參數{導頁參數}判斷：[GOTO] = 050，為“繳款紀錄頁”
        if (StringUtils.equals("050", rqData.getGOTO()) && hasPrimaryCard) {
            logger.debug("頁面前往繳款記錄頁");
            // 繳款紀錄頁
            rsData.setGOTO("050");
            return;
        }
        logger.debug("頁面前往交易首頁");

        // 信用卡身分別
        CreditCardIdType creditCardIdType = userDataCacheService.getCreditCardIdType(aiBankUser, userLocale);
        // COMPANY_KIND = 4，且為附卡人身分
        boolean isAdditionalCardholder = aiBankUser.getCompanyKindType().isSecondary() && creditCardIdType.isSupplementaryCard();
        cache.setAdditionalCardholder(isAdditionalCardholder);
        rsData.setAdditionalCardholder(isAdditionalCardholder);

        List<NCCQQU001CardData> cardInfos = cache.getCardInfos();
        List<NCCQQU001CardData> underCardInfos = cache.getUnderCardInfos();

        try {
            // 取得的歸戶信用卡清單中，無任一張信用卡已啟用
            if (!utils.hasActivatedCreditCard(creditCards)) {
                // 進入「功能首頁」，「各卡片資訊」顯示尚未開卡樣式
                rsData.setCardUnopened(true);
            }
            if (CollectionUtils.isEmpty(cardInfos)) {
                // 虛擬卡片cardType
                String virtualCard = systemParamCacheManager.getValue("PIB", "J_VIRTUALCARD_CARDTYPE");
                logger.debug("J_VIRTUALCARD_CARDTYPE:{}", virtualCard);
                cardInfos = new ArrayList<>();
                underCardInfos = new ArrayList<>();
                service.saveBaseCardCache(creditCards, virtualCard, cardInfos, underCardInfos);
                cache.setCardInfos(cardInfos);
                List<NCCQQU001CardData> cardInfoTab2 = new ArrayList<>();
                for (NCCQQU001CardData cardData : cardInfos) {
                    NCCQQU001CardData cardDataCopy = new NCCQQU001CardData();
                    BeanUtils.copyProperties(cardDataCopy, cardData);
                    cardInfoTab2.add(cardDataCopy);
                }
                cache.setCardInfosTab2(cardInfoTab2);
                cache.setUnderCardInfos(underCardInfos);
            }
        }
        catch (ServiceException | IllegalAccessException | InvocationTargetException e) {
            // 取得歸戶信用卡清單 噴錯將於[最新消費TAB]下顯示錯誤樣式：最新消費載入失敗(待確認)<換行>再試一次，並接續下一步驟
            logger.warn("取得歸戶信用卡清單發生錯誤:", e);
            rsData.setCardError(true);
            return;
        }

        if (StringUtils.equals("2", actionType) && hasPrimaryCard) {
            // 帳單資訊
            tab2(rsData, cache, custId);
        }
        else {
            // 如果是信用卡會員登入 且這個客戶是附卡人 則要去DB CARD_USER_PROFILE 取他是用哪一張卡片登入的 跟歸戶清單比對 有對到的才是真正的歸戶清單
            if (aiBankUser.getCustomerType().isCardMember() && aiBankUser.getCompanyKind() == 4) {
                cache.setCardInfos(this.reloadAdditionalCard(aiBankUser.getCardUserProfileVo().getCardNo(), cardInfos));
            }
            // 最新消費
            tab1(rsData, cache, aiBankUser, custId, hasPrimaryCard);
        }

        if (hasPrimaryCard) {
            // 好多金資料
            hasCostcoCard = utils.hasCostcoCard(creditCards);
            if (hasCostcoCard) {
                if (StringUtils.equals("2", actionType)) {
                    // tab2 發查上一期
                    if (null == cache.getLastVb0051()) {
                        try {
                            VB0051Response response = utils.sendVB0051(custId, "1");
                            cache.setLastVb0051(response);
                        }
                        catch (ServiceException e) {
                            logger.warn("取得好多金資料 sendVB0051 查詢失敗:", e);
                            rsData.setCostcoError(true);
                        }
                    }
                    if (null != cache.getLastVb0051()) {
                        rsData.setSignFlg3(cache.getLastVb0051().getSignFlg3());
                        rsData.setAddamt(cache.getLastVb0051().getAddamt() == null ? BigDecimal.ZERO : cache.getLastVb0051().getAddamt());
                    }
                }
                else {
                    // tab1 發查本期
                    if (null == cache.getVb0051()) {
                        try {
                            VB0051Response response = utils.sendVB0051(custId, "0");
                            cache.setVb0051(response);
                        }
                        catch (ServiceException e) {
                            logger.warn("取得好多金資料 sendVB0051 查詢失敗:", e);
                        }
                    }
                    if (null != cache.getVb0051()) {
                        rsData.setSignFlg3(cache.getVb0051().getSignFlg3());
                        rsData.setAddamt(cache.getVb0051().getAddamt());
                    }
                }
            }
        }
        rsData.setHasPrimaryCard(hasPrimaryCard);
        rsData.setTxnSource(rqData.getTxnSource());

        setCache(NCCQU001Utils.NCCQU001_CACHE_KEY, cache);
    }

    /*
     * 附卡重整歸戶清單
     * 
     * @param cardNo
     * 
     * @param cardInfos
     * 
     * @return
     */
    private List<NCCQQU001CardData> reloadAdditionalCard(String cardNo, List<NCCQQU001CardData> cardInfos) {
        String maskNo = DataMaskUtil.maskCreditCard(cardNo);
        return cardInfos.stream().filter(card -> StringUtils.equals(maskNo, card.getCardNo())).toList();
    }

    /**
     * 最新消費頁籤
     *
     * @param rsData
     * @param cache
     * @param aiBankUser
     * @param custId
     * @param hasPrimaryCard
     */
    private void tab1(NCCQU001010Rs rsData, NCCQU001Cache cache, AIBankUser aiBankUser, String custId, boolean hasPrimaryCard) {
        boolean noCredit = !userCreditCardService.haveCreditCard(aiBankUser);

        // 未持有信用卡
        rsData.setNoCreditCard(noCredit);

        if (rsData.isCardUnopened() || noCredit) {
            rsData.setCardInfos(cache.getCardInfos());
            rsData.setUnderCardInfos(cache.getUnderCardInfos());
            return;
        }
        // 持有信用卡 任一張信用卡已啟用
        // 取得本期未出帳消費明細
        try {
            List<CEW205RRepeat> cew205RRepeats = cache.getCew205RRepeats();
            if (CollectionUtils.isEmpty(cew205RRepeats)) {
                cew205RRepeats = new ArrayList<>();
                List<CreditCard> creditCards = aiBankUser.getAllCreditCards();
                service.getConsumerDetails(custId, cache.getCardInfos(), creditCards, cew205RRepeats);
                cache.setCew205RRepeats(cew205RRepeats);
            }

            if (CollectionUtils.isNotEmpty(cew205RRepeats)) {

                BigDecimal totalAmount = utils.getTotalAmountFromCEW205R(cew205RRepeats);
                rsData.setTotalAmount(totalAmount);

                List<NCCQQU001Bill> bills = new ArrayList<>();
                // 整理一整包消費明細
                service.transCew205R(cew205RRepeats, bills, userCreditCardService, currencyCodeCacheManager, getLocale(), cache.getCardInfos(), cache.getUnderCardInfos());

                // 下次結帳日
                rsData.setNextCheckoutDate(cew205RRepeats.get(0).getNcCday());

                if (aiBankUser.getCustomerType().isCardMember() && aiBankUser.getCompanyKind() == 4) {
                    logger.debug("信用卡附卡使用者不需要無中生有已停用卡片與排序");
                }
                else {
                    // 無中生有已停用卡片
                    service.createSomethingNothing(cew205RRepeats, cache.getCardInfos(), cache.getUnderCardInfos());
                    // 有消費明細後 將卡片資料排序
                    service.checkCew205rHasData(cew205RRepeats, cache.getCardInfos(), cache.getUnderCardInfos());
                }

                rsData.setBills(bills);

            }

        }
        catch (ServiceException e) {
            logger.warn("取得本期未出帳消費明細 sendCEW205R 查詢失敗:", e);
            rsData.setCew205rError(true);
            rsData.setCew205rErrorCode(e.getErrorCode());
        }

        // 進度條 剩餘額度
        if (cache.getCew303r() == null) {
            try {
                if (hasPrimaryCard) {
                    CEW303RResponse response = utils.sendCEW303R(custId, StringUtils.EMPTY);
                    cache.setCew303r(response);
                }
                else {
                    // 無持有正卡、以信用卡會員登入且為附卡人身分
                    if (cache.isAdditionalCardholder()) {
                        CEW303RResponse response = utils.sendCEW303R(StringUtils.EMPTY, aiBankUser.getCardUserProfileVo().getCardNo());
                        cache.setCew303r(response);
                    }
                }
            }
            catch (ServiceException e) {
                logger.warn("取得信用卡帳務資料 sendCEW303R 查詢失敗:", e);
                rsData.setCew303rError(true);
                rsData.setCew303rErrorCode(e.getErrorCode());
            }
        }

        if (cache.getCew303r() != null) {
            rsData.setQuota(cache.getCew303r().getAcctIdCram());
            rsData.setBalQuota(cache.getCew303r().getAcctIdPcb1S());
        }

        rsData.setCardInfos(cache.getCardInfos());
        rsData.setUnderCardInfos(cache.getUnderCardInfos());
    }

    /**
     * 帳單資訊頁籤
     * 
     * @param rsData
     * @param cache
     * @param custId
     * @throws ActionException
     */
    private void tab2(NCCQU001010Rs rsData, NCCQU001Cache cache, String custId) throws ActionException {
        // 帳單明細
        if (null == cache.getCew314r()) {
            try {
                CEW314RResponse cew314r = utils.sendCEW314R(custId, "1");
                logger.debug("CEW314RResponse A021 有{}筆資料", cew314r == null ? 0 : CollectionUtils.size(cew314r.getA021Repeats()));
                logger.debug("CEW314RResponse B100 有{}筆資料", cew314r == null ? 0 : CollectionUtils.size(cew314r.getB100Repeats()));
                logger.debug("CEW314RResponse B500 有{}筆資料", cew314r == null ? 0 : CollectionUtils.size(cew314r.getB500Repeats()));
                cache.setCew314r(cew314r);
            }
            catch (ServiceException e) {
                logger.warn("取得帳單明細 sendCEW314R 查詢失敗:", e);
                rsData.setCew314Error(true);
                rsData.setCew314ErrorCode(e.getErrorCode());
                setCache(NCCQU001Utils.NCCQU001_CACHE_KEY, cache);
            }
        }
        if (null != cache.getCew314r() && CollectionUtils.isNotEmpty(cache.getCew314r().getA021Repeats())) {
            CEW314RA021Repeat lastMonthData = cache.getCew314r().getA021Repeats().get(0);
            // 正負號
            rsData.setAatpin(lastMonthData.getAatpin());
            // 帳單月份
            rsData.setAacldy(lastMonthData.getAacldy());
            // 帳單金額
            rsData.setAatpay(lastMonthData.getAatpay());
            // 繳款期限
            rsData.setAalmdy(lastMonthData.getAalmdy());
            // 繳款期限是否大於等於系統日
            Date currentDate = new Date();
            DateUtils.clearTime(currentDate);
            rsData.setMoreThenSysdate(!DateUtils.isBefore(lastMonthData.getAalmdy(), currentDate));

            NCCQU001PaymentInfo paymentInfo = new NCCQU001PaymentInfo(lastMonthData, cache.getCew314r().getB060Repeats());
            rsData.setPaymentInfo(paymentInfo);
        }
        else {
            // 繳款期限
            rsData.setAalmdy(DateUtils.getToday());
        }

        rsData.setCardInfos(cache.getCardInfosTab2());

        // 帳單月份沒值, give default
        if (rsData.getAacldy() == null)
            rsData.setAacldy(new Date());

        if (null != cache.getCew314r() && CollectionUtils.isNotEmpty(cache.getCew314r().getB100Repeats())) {
            List<CEW314RB100Repeat> b100Repeat = cache.getCew314r().getB100Repeats();
            // 明細資料
            List<NCCQQU001Bill> bills = new ArrayList<>();
            // 分期Bill資料
            List<NCCQQU001Bill> installmentBills = new ArrayList<>();
            // 分期資料
            List<NCCQQU001InstallmentGroup> installments = new ArrayList<>();
            utils.transHistoricalBill(b100Repeat, bills, installments, installmentBills, currencyCodeCacheManager, getLocale(), cache.getCardInfos(), cache.getUnderCardInfos(), true);
            // 消費明細找回卡片資料
            List<NCCQQU001CardData> cardInfos = utils.createCardInfoByCEW314R(bills, installmentBills, cache.getCardInfosTab2(), cache.getUnderCardInfos());
            rsData.setCardInfos(cardInfos);
            rsData.setBills(bills);
            rsData.setInstallments(installments);
        }

        if (null == cache.getCew303r()) {
            try {
                CEW303RResponse response = utils.sendCEW303R(custId, StringUtils.EMPTY);
                // (5) {帳單金額}資料來源
                rsData.setAcctIdSbal(response.getAcctIdSbal());
                // (6) {最低應繳金額}資料來源
                rsData.setAcctIdMinp(response.getAcctIdMinp());
                // (7) {已繳金額}資料來源
                rsData.setAcctIdPayd(response.getAcctIdPayd());
                // (8) {剩餘應繳金額}資料來源
                rsData.setAcctIdDpayd(response.getAcctIdDpayd());
                // (9) {遲繳註記}為“有遲繳天數” == 1
                rsData.setAcctIdDlusts(response.getAcctIdDlusts());
                cache.setCew303r(response);
            }
            catch (ServiceException e) {
                logger.warn("取得信用卡帳務資料 sendCEW303R 查詢失敗:", e);
                rsData.setCew303rError(true);
                rsData.setCew303rErrorCode(e.getErrorCode());
            }
        }
        else {
            // (5) {帳單金額}資料來源
            rsData.setAcctIdSbal(cache.getCew303r().getAcctIdSbal());
            // (6) {最低應繳金額}資料來源
            rsData.setAcctIdMinp(cache.getCew303r().getAcctIdMinp());
            // (7) {已繳金額}資料來源
            rsData.setAcctIdPayd(cache.getCew303r().getAcctIdPayd());
            // (8) {剩餘應繳金額}資料來源
            rsData.setAcctIdDpayd(cache.getCew303r().getAcctIdDpayd());
            // (9) {遲繳註記}為“有遲繳天數” == 1
            rsData.setAcctIdDlusts(cache.getCew303r().getAcctIdDlusts());
        }

        // 紅利、哩程回饋 改用CEW314R B500資料
        if (null != cache.getCew314r() && CollectionUtils.isNotEmpty(cache.getCew314r().getB500Repeats())) {
            NCCQQU001BonusRewards bonusRewards = new NCCQQU001BonusRewards();
            service.getBouns(cache.getCew314r().getB500Repeats(), bonusRewards);
            rsData.setBonusRewards(bonusRewards);
        }

        Boolean automatic = cache.getAutomatic();
        // 自動扣繳設定資料
        if (null == automatic) {
            try {
                automatic = utils.sendCEW309R(custId, "Q");
                cache.setAutomatic(automatic);
            }
            catch (ServiceException e) {
                logger.warn("自動扣繳設定資料 sendCEW309R 查詢失敗:", e);
            }
        }
        rsData.setAutomatic(null != automatic && automatic);
        rsData.setTabII(true);
        try {
            service.cacheCEW315RResponse(cache, getLoginUser().getCustId());
            CEW315RResponse response = cache.getCew315Rresponse();
            if (response != null && StringUtils.isBlank(response.getWording()) && StringUtils.equals(response.getStatus(), "N")) {
                rsData.setBillInstallmentApply(false);
            }
        }
        catch (ServiceException e) {
            logger.warn("帳單資訊 CEW315R 查詢失敗:", e);
        }

    }

}
