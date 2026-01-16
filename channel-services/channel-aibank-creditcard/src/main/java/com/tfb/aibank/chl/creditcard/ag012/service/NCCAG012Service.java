package com.tfb.aibank.chl.creditcard.ag012.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012CardInfo;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.resource.dto.CE5552RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE5552RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CardControlSettingModel;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.model.TxnResult;

// @formatter:off
/**
 * @(#)NCCAG012Service.java
 * 
 * <p>Description:CHL NCCAG012 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCAG012Service extends NCCService {

    public static final String NCCAG012_CACHE_KEY = "NCCAG012CacheKey";

    @Autowired
    private UserDataCacheService creditcardService;

    /**
     * 查 CE5552R 信用卡資料
     * 
     * @param cardData
     * @param output
     */
    public void queryCardControlData(CreditCard cardData, NCCAG012CardInfo output) {
        CE5552RRequest request = new CE5552RRequest();
        request.setPiFunc("Q");
        request.setPiCdNo(cardData.getCardNo());
        output.setCardKey(cardData.getCardKey());
        // /** 暱稱 */
        output.setNickName(cardData.getCardNickname());
        // /** 卡號 */
        output.setCardNoForDisplay(DataMaskUtil.maskCreditCard(cardData.getCardNo()));
        // /** 是否啟用 CE6220R_Rs.CARD_ACTIVE_FLAG = Y。 */
        output.setCardActiveFlag(StringUtils.equals(cardData.getCardActiveCode(), "Y"));
        try {
            CE5552RResponse cardControlRes = creditCardResource.sendCE5552R(request);
            // /** 最近變更日期 YYYY/MM/DD */
            output.setPichDy(DateUtils.getCEDateStr(cardControlRes.getPiChDy()));

            // /** 國內實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPilPStatus(controlTag(cardControlRes.getPiLPhy(), cardControlRes.getPiLPam()));
            // /** 國內實體卡交易金額限制 */
            output.setPilPMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiLPam()));
            // /** 國外實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPifPStatus(controlTag(cardControlRes.getPiFPhy(), cardControlRes.getPiFPam()));
            // /** 國外實體卡交易金額限制 */
            output.setPifPMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiFPam()));
            // /** 國內非實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPilCStatus(controlTag(cardControlRes.getPiLCnp(), cardControlRes.getPiLCam()));
            // /** 國內非實體卡交易金額限制 */
            output.setPilCMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiLCam()));
            // /** 國外非實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPifCStatus(controlTag(cardControlRes.getPiFCnp(), cardControlRes.getPiFCam()));
            // /** 國外非實體卡交易金額限制 */
            output.setPifCMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiFCam()));
            // /** 國內行動支付交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPilTStatus(controlTag(cardControlRes.getPiLTkn(), cardControlRes.getPiLTam()));
            // /** 國內行動支付交易金額限制 */
            output.setPilTCMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiLTam()));
            // /** 國外行動支付交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPifTStatus(controlTag(cardControlRes.getPiFTkn(), cardControlRes.getPiFTam()));
            // /** 國外行動支付交易金額限制 */
            output.setPifTMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiFTam()));
            // @formatter:off
            Boolean isAllLock = output.getPilPStatus() == 0 && output.getPifPStatus() == 0 
                    && output.getPilCStatus() == 0 && output.getPifCStatus() == 0 
                    && output.getPilTStatus() == 0 && output.getPifTStatus() == 0;
            Boolean isAllNoLock = output.getPilPStatus() == 2 && output.getPifPStatus() == 2 
                    && output.getPilCStatus() == 2 && output.getPifCStatus() == 2
                    && output.getPilTStatus() == 2 && output.getPifTStatus() == 2;
            // @formatter:on
            // /** 所有狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            if (Boolean.TRUE.equals(isAllLock)) {
                output.setAllStatus(0);
            }
            else if (Boolean.TRUE.equals(isAllNoLock)) {
                output.setAllStatus(2);
            }
            else {
                output.setAllStatus(1);
            }
        }
        catch (ServiceException ex) {
            logger.error(ex.getMessage(), ex);
            output.setIsCe5552rError(true);
        }

    }

    /** 0: 全鎖定 1:部分鎖定 2:未鎖定 */
    private Integer controlTag(String txnStatus, String amt) {
        if (StringUtils.equals(txnStatus, "D")) {
            BigDecimal amtBd = ConvertUtils.str2BigDecimal(amt);
            return amtBd.compareTo(BigDecimal.ZERO) == 0 ? 0 : 1;
        }
        else {
            return 2;
        }
    }

    /**
     * 建立 CE5552R
     * 
     * @param cardInfoForTx
     * @param aibankUser
     * @param locale
     * @param request
     * @throws ActionException
     */
    public void buildTxCE5552RRequest(NCCAG012CardInfo cardInfoForTx, AIBankUser aibankUser, Locale locale, CE5552RRequest request) throws ActionException {
        // 功能碼 X(1)
        request.setPiFunc("U");
        // 卡號 X(16)
        CreditCard card = creditcardService.getCreditCardByCardKey(aibankUser, cardInfoForTx.getCardKey(), locale);
        request.setPiCdNo(card.getCardNo());
        // 國內實體卡交易控管碼 X(?)
        request.setPiLPhy(ConvertUtils.integer2Int(cardInfoForTx.getPilPStatus()) != 2 ? "D" : "");
        // 國外實體卡交易控管碼 X(?)
        request.setPiFPhy(ConvertUtils.integer2Int(cardInfoForTx.getPifPStatus()) != 2 ? "D" : "");
        // 國內非實體卡交易控管碼 X(?)
        request.setPiLCnp(ConvertUtils.integer2Int(cardInfoForTx.getPilCStatus()) != 2 ? "D" : "");
        // 國外非實體卡交易控管碼 X(?)
        request.setPiFCnp(ConvertUtils.integer2Int(cardInfoForTx.getPifCStatus()) != 2 ? "D" : "");
        // 國內行動支付卡交易控管碼 X(?)
        request.setPiLTkn(ConvertUtils.integer2Int(cardInfoForTx.getPilTStatus()) != 2 ? "D" : "");
        // 國外行動支付卡交易控管碼 X(?)
        request.setPiFTkn(ConvertUtils.integer2Int(cardInfoForTx.getPifTStatus()) != 2 ? "D" : "");
        // 國內實體卡交易刷卡金額上限 X(?)
        request.setPiLPam(ConvertUtils.bigDecimal2Str(cardInfoForTx.getPilPMoney()));
        // 國外實體卡交易易刷卡金額上限 X(?)
        request.setPiFPam(ConvertUtils.bigDecimal2Str(cardInfoForTx.getPifPMoney()));
        // 國內非實體卡交易易刷卡金額上限 X(?)
        request.setPiLCam(ConvertUtils.bigDecimal2Str(cardInfoForTx.getPilCMoney()));
        // 國外非實體卡交易易刷卡金額上限 X(?)
        request.setPiFCam(ConvertUtils.bigDecimal2Str(cardInfoForTx.getPifCMoney()));
        // 國內行動支付卡交易易刷卡金額上限 X(?)
        request.setPiLTam(ConvertUtils.bigDecimal2Str(cardInfoForTx.getPilTCMoney()));
        // 國外行動支付卡交易易刷卡金額上限 X(?)
        request.setPiFTam(ConvertUtils.bigDecimal2Str(cardInfoForTx.getPifTMoney()));
    }

    /**
     * 新增 CardControlSetting
     * 
     * @param aibankUser
     * @param request
     * @param output
     */
    public void insertCardControlSetting(AIBankUser aibankUser, CE5552RRequest request, CardControlSettingModel output) {
        output.setCompanyKey(aibankUser.getCompanyKey());
        output.setNameCode(aibankUser.getNameCode());
        output.setUserKey(aibankUser.getUserKey());
        output.setUserId(aibankUser.getUserId());
        output.setCardNo(request.getPiCdNo());
        output.setDomPhy(request.getPiLPhy());
        output.setDomPhyAmt(ConvertUtils.str2Int(request.getPiLPam()));
        output.setForPhy(request.getPiFPhy());
        output.setForPhyAmt(ConvertUtils.str2Int(request.getPiFPam()));
        output.setDomNphy(request.getPiLCnp());
        output.setDomNphyAmt(ConvertUtils.str2Int(request.getPiLCam()));
        output.setForNphy(request.getPiFCnp());
        output.setForNphyAmt(ConvertUtils.str2Int(request.getPiFCam()));
        output.setDomMob(request.getPiLTkn());
        output.setDomMobAmt(ConvertUtils.str2Int(request.getPiLTam()));
        output.setForMob(request.getPiFTkn());
        output.setForMobAmt(ConvertUtils.str2Int(request.getPiFTam()));
        output.setTxDate(new Date());
        output.setCreateTime(new Date());
        output.setTxSource("3");
        output.setTxStatus("4");
        CardControlSettingModel model = creditCardResource.sendCardControlSetting(output);
        output.setControlKey(model.getControlKey());
    }

    /**
     * update CardControlSetting
     * 
     * @param aibankUser
     * @param request
     * @param output
     */
    public void updateCardControlSetting(CardControlSettingModel model) {

        creditCardResource.sendCardControlSetting(model);
    }

    /**
     * CE5552R 交易
     * 
     * @param request
     * @param output
     */
    public void sendCE5552RForTx(CE5552RRequest request, TxnResult txnResult, CardControlSettingModel output) {
        try {
            creditCardResource.sendCE5552R(request);
            output.setTxStatus("0");
            output.setUpdateTime(new Date());
            txnResult.setTxStatus("0");
            txnResult.setTxStatusDesc(I18NUtils.getMessage("nccag012.settingsuccess"));
        }
        catch (ServiceException ex) {

            ActionException aex = new ActionException(ErrorCode.SETTING_CARD_ERROR, new HashMap<>(), ex.getErrorCode());
            String errDesc = MessageFormat.format(aex.getErrorDesc(), ex.getErrorCode()) + "(" + I18NUtils.getMessage("nccag012.errordesc01") + " " + ex.getSystemId() + ex.getErrorCode() + ")";
            output.setTxStatus("1");
            output.setTxErrorCode(aex.getErrorCode());
            output.setTxErrorDesc(errDesc);
            output.setTxErrorSystemId(aex.getSystemId());
            txnResult.setTxStatus("1");
            txnResult.setTxErrorDesc(errDesc);
            txnResult.setTxStatusDesc(I18NUtils.getMessage("nccag012.settingerror"));
        }
        txnResult.setHostTxTime(new Date());
        output.setHostTxTime(new Date());
        output.setTraceId(MDC.get(MDCKey.traceId.name()));
    }

}
