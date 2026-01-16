package com.tfb.aibank.chl.creditcard.qu001.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.cardpaytype.CardPaytype;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag005.model.InsurFeeBenefitsType;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001PayType;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001020Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001020Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001CardLockStatus;
import com.tfb.aibank.chl.creditcard.qu001.service.NCCQU001Service;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW321RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW327RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW327RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW329RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW329RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW345RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW345RResponse;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001020Task.java
* 
* <p>Description:信用卡總覽 卡片管理頁</p>
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
public class NCCQU001020Task extends AbstractAIBankBaseTask<NCCQU001020Rq, NCCQU001020Rs> {

    @Autowired
    private NCCQU001Utils utils;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private NCCQU001Service service;

    @Override
    public void validate(NCCQU001020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU001020Rq rqData, NCCQU001020Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        String cardKey = rqData.getCardKey();
        NCCQQU001CardData cardInfo = cache.getCardInfos().stream().filter(card -> StringUtils.equals(card.getCardKey(), cardKey)).findAny().orElse(null);
        if (cardInfo == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
        CreditCard creditCard = userDataCacheService.getAllCreditCards(aibankUser, getLocale()).stream().filter(card -> StringUtils.equals(card.getCardKey(), cardKey)).findFirst().orElse(new CreditCard());
		if (creditCard == null) {
			throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
		}
        String custId = getLoginUser().getCustId();

        // 1.電文取得年度累積消費資料
        try {
            CEW321RResponse cew321Rs = utils.sendCEW321R(creditCard.getCardNo());
            rsData.setStrday(cew321Rs.getStrday());
            rsData.setEndday(cew321Rs.getEndday());
            rsData.setOutamt1(cew321Rs.getOutamt1());
            rsData.setOutamt2(cew321Rs.getOutamt2());
            rsData.setOutcnt1(cew321Rs.getOutcnt1());
            rsData.setOutcnt2(cew321Rs.getOutcnt2());
        }
        catch (ServiceException e) {
            logger.warn("取得年度累積消費資料 sendCEW321R 查詢失敗:", e);
            rsData.setCew321rError(true);
        }

        // 2.電文取得保費權益資訊
        // 可設定保費權益判斷方式：[CE6220R_Rs.INSU_FLAG] = Y
        if (cardInfo.isInsuFlag()) {
            try {
                // 保費權益設定新增單筆金額五萬以下設定生效後走CEW345R電文，未生效走原來CEW327R
                if (utils.getCEW345Valid()) {
                    CEW345RResponse response = utils.sendCEW345R(custId);
                    Optional<CEW345RRepeat> opt = response.getTxRepeats().stream().filter(repeat -> StringUtils.equals(creditCard.getCardNo(), repeat.getCrdNo())).findAny();
                    if (opt.isPresent()) {
                        // 組成「保費權益設定」
                        if (StringUtils.isNotBlank(opt.get().getInsuTypeB())) {
                            // 5萬(含)以上：OO期0利率
                            // 5萬以下：OO期0利率
                            StringBuilder insuType = new StringBuilder(0);
                            insuType.append(InsurFeeBenefitsType.findByCode(opt.get().getInsuTypeA()).getI18NMemo());
                            rsData.setInsuTypeShow(insuType.toString());
                            // 顯示保費權益
                            // 5萬以下：OO期0利率
                            StringBuilder insuTypeUnderFive = new StringBuilder(0);
                            insuTypeUnderFive.append(InsurFeeBenefitsType.findByCode(opt.get().getInsuTypeB()).getI18NMemo());
                            // 顯示保費權益
                            rsData.setInsuTypeUnderFiveShow(insuTypeUnderFive.toString());
                            rsData.setInsuType(opt.get().getInsuTypeA());
                        }
                        else {
                            // OO期0利率
                            StringBuilder insuType = new StringBuilder(0);
                            insuType.append(InsurFeeBenefitsType.findByCode(opt.get().getInsuTypeA()).getI18NMemo());
                            // 顯示保費權益
                            rsData.setInsuTypeShow(insuType.toString());
                            rsData.setInsuType(opt.get().getInsuTypeA());
                        }
                    }
                }
                else {
                    CEW327RResponse response = utils.sendCEW327R(custId);
                    Optional<CEW327RRepeat> opt = response.getTxRepeats().stream().filter(repeat -> StringUtils.equals(creditCard.getCardNo(), repeat.getCdno())).findAny();
                    if (opt.isPresent()) {
                        rsData.setInsuTypeShow(InsurFeeBenefitsType.findByCode(opt.get().getInsutype()).getI18NMemo());
                        rsData.setInsuType(opt.get().getInsutype());
                    }
                }

            }
            catch (ServiceException e) {
                logger.warn("取得保費權益資訊 sendCEW327R sendCEW345R 查詢失敗:", e);
                rsData.setCew327rError(true);
            }
        }

        // 3.電文取得信用卡綁定行動支付類型
        // 已綁定行動支付判斷方式：[CE6220R_Rs.HCE_CARD] = Y
        if (cardInfo.isHceCard()) {
            try {
                CEW329RResponse response = utils.sendCEW329R(custId);
                List<CEW329RRepeat> cew329rRepeats = response.getTxRepeats();
                List<NCCQQU001PayType> payTypes = new ArrayList<>();
                for (CEW329RRepeat cew329rRepeat : cew329rRepeats) {
                    if (StringUtils.equals(creditCard.getCardNo(), cew329rRepeat.getHcphyn())) {
                        NCCQQU001PayType payType = new NCCQQU001PayType();
                        String cardType = cew329rRepeat.getHcttyp();
                        if (StringUtils.isNotBlank(cew329rRepeat.getHcttyp())) {
                            CardPaytype cardPayType = userDataCacheService.getCardPaytype(cew329rRepeat.getHcttyp(), getLocale());
                            cardType = cardPayType.getPayName();
                            payType.setOrderNo(cardPayType.getOrderNo());
                        }
                        payType.setHcttyp(cardType);
                        payType.setHchcen(cew329rRepeat.getHchcen());
                        payTypes.add(payType);
                    }
                }
                Comparator<NCCQQU001PayType> comparator = Comparator.comparing(NCCQQU001PayType::getOrderNo);
                payTypes = payTypes.stream().sorted(comparator).collect(Collectors.toList());
                rsData.setPayTypes(payTypes);
            }
            catch (ServiceException e) {
                logger.warn("取得信用卡綁定行動支付類型 sendCEW329R 查詢失敗:", e);
                rsData.setCew329rError(true);
            }
        }
        // 附卡資訊
        List<NCCQQU001CardData> additionalCards = cache.getUnderCardInfos().stream().filter(card -> StringUtils.equals(card.getMCardNo(), cardInfo.getCardNo()) && !StringUtils.equals(card.getCardNo(), cardInfo.getCardNo())).collect(Collectors.toList());
        rsData.setCardNickName(cardInfo.getCardNickName());
        rsData.setAdditionalCards(additionalCards);
        rsData.setCardNo(cardInfo.getCardNo());
        rsData.setCardType(cardInfo.getCardType());
        rsData.setCardName(cardInfo.getCardName());
        rsData.setCardCategory(cardInfo.getCardCategory());
        rsData.setCardFace(cardInfo.getCardFace());
        rsData.setTknFlag(cardInfo.isTknFlag());
        rsData.setCardKey(cardInfo.getCardKey());
        rsData.setIsCostcoMember(cache.isCostcoMerber());

        if (cache.isCostcoMerber()) {
            rsData.setIsApplyCostco(utils.getCostcoMemberInfo(getLoginUser().getCustId()));
        }
        // #7982 信用卡總覽apple pay按鈕新增開關控制(For JCB卡)
        boolean isJcbCard = StringUtils.startsWith(creditCard.getCardType2(), "J");
        String dbPayDbVer = "";
        if (isJcbCard) {
            dbPayDbVer = systemParamCacheManager.getValue("AIBANK", "APPLEPAY_JCB_PILOT");
        }
        else {
            dbPayDbVer = systemParamCacheManager.getValue("AIBANK", "APPLEPAY_PILOT");
        }

        // 檢查APP版本
        String currentAppVer = StringUtils.defaultString(getRequest().getAppVer()); // 當前裝置上使用的APP版本
        // #5146 是否APP版本號>=DB版本號
        rsData.setIsVersionCanApplePay(IBUtils.compareAppVersion(currentAppVer, dbPayDbVer) >= 0);

        String dbLineDbVer = systemParamCacheManager.getValue("AIBANK", "LINEPAY_PILOT");
        // #5146 是否APP版本號>=DB版本號
        rsData.setIsVersionCanLinePay(IBUtils.compareAppVersion(currentAppVer, dbLineDbVer) >= 0);

        // #8259 apple pay判定特定版次測試
        String dbApplePaySpecialVer = systemParamCacheManager.getValue("AIBANK", "APPLEPAY_BACKEND_PILOT");
        rsData.setIsNeedCheckTknFlagForApplePay(IBUtils.compareAppVersion(currentAppVer, dbApplePaySpecialVer) >= 0);
        
        NCCQU001CardLockStatus output = new NCCQU001CardLockStatus();
        service.queryCardControlData(creditCard, output);
        rsData.setCardLockStatus(output);
        rsData.setAdditional(utils.isAdditionalCard(creditCard));
        rsData.setVpsCard(StringUtils.isNotBlank(creditCard.getVpsF16()));
    }

}