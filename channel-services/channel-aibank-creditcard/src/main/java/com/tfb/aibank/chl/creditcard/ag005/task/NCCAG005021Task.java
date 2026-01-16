package com.tfb.aibank.chl.creditcard.ag005.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag005.model.InsurFeeBenefitsType;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005021Rq;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005021Rs;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005CacheData;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005CardInfo;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005Output;
import com.tfb.aibank.chl.creditcard.ag005.service.NCCAG005Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW013RRes;
import com.tfb.aibank.chl.creditcard.utils.NCCUtils;
import com.tfb.aibank.common.model.TxnResult;
import com.tfb.aibank.common.type.MailParamType;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)NCCAG005021Task.java
 * 
 * <p>Description:保費權益設定 021 設定保費權益</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG005021Task extends AbstractAIBankBaseTask<NCCAG005021Rq, NCCAG005021Rs> {

    @Autowired
    private NCCAG005Service service;
    @Autowired
    private NCCUtils utils;
    @Autowired
    private UserDataCacheService userDataCacheService;

    private NCCAG005Output output = new NCCAG005Output();

    @Override
    public void validate(NCCAG005021Rq rqData) throws ActionException {
        InsurFeeBenefitsType insurFeeBenefitsType = InsurFeeBenefitsType.findByCode(rqData.getInsurFeeBenefits());
        if (insurFeeBenefitsType == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCAG005021Rq rqData, NCCAG005021Rs rsData) throws ActionException {

        // 發送電文 CEW346R，進行保費權益設定變更
        NCCAG005CacheData cache = this.getCache(NCCAG005Service.CACHE_KEY, NCCAG005CacheData.class);
        NCCAG005CardInfo cardInfo = cache.getCardInfo();
        cardInfo.setInsurFeeBenefits(InsurFeeBenefitsType.findByCode(rqData.getInsurFeeBenefits())); // 將頁面選擇的值放入-五萬元以上保費權益
        cardInfo.setInsurFeeBenefitsUnderFive(InsurFeeBenefitsType.findByCode(rqData.getInsurFeeBenefitsUnderFive()));// 將頁面選擇的值放入-未達五萬元保費權益
        String errorSystemId = StringUtils.EMPTY;
        String errorCode = StringUtils.EMPTY;
        String errorDesc = StringUtils.EMPTY;
        TxStatusType txStatus = TxStatusType.SUCCESS;
        service.sendCEW013R(getLoginUser().getCustId(), output);
        String emailAddr = Optional.ofNullable(output.getCew013rRes()).map(CEW013RRes::getEmailAddr).orElse(StringUtils.EMPTY);
        try {
			// 保費權益設定新增單筆金額五萬以下設定生效後走CEW346R電文，未生效走原來CEW328R
			if (service.getCEW345Valid()) {
				service.sendCEW346R(cardInfo.getCardNo(), cardInfo.getInsurFeeBenefits().getCode(),
						cardInfo.getInsurFeeBenefitsUnderFive() != null
								? cardInfo.getInsurFeeBenefitsUnderFive().getCode()
								: StringUtils.EMPTY);
			} else {
				service.sendCEW328R(cardInfo.getCardNo(), cardInfo.getInsurFeeBenefits().getCode());
			}
			
			if (StringUtils.isNotBlank(emailAddr)) {
                sendMail(cardInfo, errorSystemId, errorCode, errorDesc, txStatus, emailAddr);
            }
            else {
                // 若為一般會員登入，發查 CEW013R 取得信用卡會員登記的行動電話
                String mobileNo = StringUtils.EMPTY;
                String smsTxt = I18NUtils.getMessage("nccag005.sms", DateUtils.getDateTimeStr(new Date()), I18NUtils.getMessage("nccag005.mail.success"));

                if (getLoginUser().getCustomerType().isGeneral()) {
                    mobileNo = userDataCacheService.getOtpMobileFromCEW013R(getLoginUser());
                }
                else {
                    mobileNo = getLoginUser().getMobileNo();
                }
                sendResultMsg(smsTxt, null, null, mobileNo);
            }
        }
        catch (ServiceException sex) {
            logger.error(sex.getMessage(), sex);
            ActionException aex = ExceptionUtils.getActionException(sex);
            errorSystemId = aex.getSystemId();
            errorCode = aex.getErrorCode();
            errorDesc = IBUtils.getErrorDesc(errorCodeCacheManager, errorSystemId, errorCode, getLocale(), getFromPage());
            txStatus = TxStatusType.FAIL;

            if (StringUtils.isNotBlank(emailAddr)) {
                sendMail(cardInfo, errorSystemId, errorCode, errorDesc, txStatus, emailAddr);
            }
            else {
                // 若為一般會員登入，發查 CEW013R 取得信用卡會員登記的行動電話
                String mobileNo = StringUtils.EMPTY;

                String smsTxt = I18NUtils.getMessage("nccag005.sms", DateUtils.getDateTimeStr(new Date()), I18NUtils.getMessage("nccag005.mail.fail"));
                if (service.checkEsbIsUnknown(sex)) {
                    smsTxt = I18NUtils.getMessage("nccag005.sms.process", DateUtils.getDateTimeStr(new Date()));
                }
                if (getLoginUser().getCustomerType().isGeneral()) {
                    mobileNo = userDataCacheService.getOtpMobileFromCEW013R(getLoginUser());
                }
                else {
                    mobileNo = getLoginUser().getMobileNo();
                }
                sendResultMsg(smsTxt, null, null, mobileNo);
            }
            throw aex;
        }
    }

    private void sendMail(NCCAG005CardInfo cardInfo, String errorSystemId, String errorCode, String errorDesc, TxStatusType txStatus, String emailAddr) {
    	// 通知作業
        TxnResult txnResult = this.createTxnResult(txStatus.getCode(), new Date(), errorSystemId, errorCode, errorDesc);
        // 產生Email通知的內容
        Map<String, String> params = new HashMap<>();
        params.put(MailParamType.TXN_NAME.getCode(), I18NUtils.getMessage("nccag005.mail.txnName")); // 指定卡保費權益設定
        params.put(MailParamType.TX_STATUS.getCode(), txStatus.getCode());
        params.put(MailParamType.TX_STATUS_DESC.getCode(), txnResult.getTxStatusDesc());
        params.put(MailParamType.TX_ERROR_DESC.getCode(), txnResult.getTxErrorDesc());
        params.put(MailParamType.TX_ERROR_SYSTEM_ID.getCode(), errorSystemId);
        params.put(MailParamType.TX_ERROR_CODE.getCode(), errorCode);
        params.put(MailParamType.HOST_TX_TIME.getCode(), DateFormatUtils.CE_DATETIME_FORMAT.format(txnResult.getHostTxTime()));

        // 卡別
        params.put("CARD_NAME", cardInfo.getCardName());
        // 卡種
        params.put("CARD_LEVEL", utils.getCardLevelDesc(cardInfo.getCardType(), getLocale()));
        // 卡號
        params.put("CARD_IDENTIFIER", DataMaskUtil.maskCreditCard(cardInfo.getCardNo()));
		// 保費權益設定
		if (cardInfo.getInsurFeeBenefitsUnderFive() != null) {
			// 組成「保費權益設定」
			// 5萬(含)以上：OO期0利率
			// 5萬以下：OO期0利率
			StringBuilder insuType = new StringBuilder(0);
			insuType.append(I18NUtils.getMessage("nccag005.insur_fee_benefits.insu_type_a"));
			insuType.append(cardInfo.getInsurFeeBenefits().getI18NMemo());
			insuType.append("<br/>");
			insuType.append(I18NUtils.getMessage("nccag005.insur_fee_benefits.insu_type_b"));
			insuType.append(cardInfo.getInsurFeeBenefitsUnderFive().getI18NMemo());
			params.put("INSUR_FEE_BENEFITS", insuType.toString());
		} else {
			params.put("INSUR_FEE_BENEFITS", cardInfo.getInsurFeeBenefits().getI18NMemo());
		}

        String subject = I18NUtils.getMessage("nccag005.mail.subject", new Object[] { Integer.parseInt(txnResult.getTxStatus()) });

        this.sendTxnResultMail(subject, emailAddr, params);
    }
}
