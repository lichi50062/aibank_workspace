package com.tfb.aibank.chl.creditcard.tx005.task;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.resource.dto.CreditLimitsApplyData;
import com.tfb.aibank.chl.creditcard.resource.dto.EBCC002Request;
import com.tfb.aibank.chl.creditcard.tx005.model.AdjustItemType;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005050Rq;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005050Rs;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CacheData;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005Output;
import com.tfb.aibank.chl.creditcard.tx005.service.NCCTX005Service;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.model.TxnResult;
import com.tfb.aibank.common.type.MailParamType;
import com.tfb.aibank.common.type.TxSourceType;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)NCCTX005050Task.java
 * 
 * <p>Description:額度調整 050 結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCTX005050Task extends AbstractAIBankBaseTask<NCCTX005050Rq, NCCTX005050Rs> {

    @Autowired
    private NCCTX005Service service;
    @Autowired
    private UserDataCacheService userDataCacheService;

    private NCCTX005Output dataOutput = new NCCTX005Output();

    @Override
    public void validate(NCCTX005050Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCTX005050Rq rqData, NCCTX005050Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        NCCTX005CacheData cache = super.getCache(NCCTX005Service.CACHE_KEY, NCCTX005CacheData.class);

        // 若有財力證明檔案，將檔案儲存在指定位置，以利BIZ service取用
        service.saveProofFiles(loginUser, cache.getProofFiles());

        // 執行交易
        CreditLimitsApplyData data = buildCreditLimitsApplyData(loginUser, cache);
        EBCC002Request request = buildEBCC002Request(loginUser, cache);
        service.doTransaction(loginUser, request, data, cache, dataOutput, getAccessLog());

        // 組成交易結果物件
        data = dataOutput.getCreditLimitsApplyData();
        String txStatus = data.getTxStatus();
        Date hostTxTime = data.getHostTxTime();
        String systemId = data.getTxErrorSystemId();
        String errorCode = data.getTxErrorCode();
        String errorDesc = data.getTxErrorDesc();
        TxnResult txnResult = createTxnResult(txStatus, hostTxTime, systemId, errorCode, errorDesc);
        rsData.setTxnResult(txnResult);

        AdjustItemType adjustItem = cache.getAdjustItem();
        // 調整項目
        rsData.setAdjustItem(adjustItem);
        // 預計調整金額
        rsData.setAdjustAmt(cache.getAdjustAmt());
        // 交易時間
        rsData.setHostTxTime(dataOutput.getCreditLimitsApplyData().getHostTxTime());
        // 目前額度
        rsData.setCurrentQuota(cache.getCurrentQuota());
        if (adjustItem.isTurnUp()) {
            // 額度用途
            rsData.setQuotaUsage(cache.getQuotaUsage());
            // Email
            rsData.setEmail(DataMaskUtil.maskEmail(cache.getWorkInfo().getEmail()));
            // 通知作業
            sendTxnResultMail(rsData, data, cache);
        }
        else if (adjustItem.isAdditionalCardLimit()) {
            // 附卡持卡人中文姓名(隱碼)
            rsData.setMaskCnam(cache.getSupplementaryCard().getMaskCnam());
            // 附卡持卡人ID(隱碼)
            rsData.setMaskHdid(cache.getSupplementaryCard().getMaskHdid());
        }
    }

    private CreditLimitsApplyData buildCreditLimitsApplyData(AIBankUser aibankUser, NCCTX005CacheData cache) {
        CreditLimitsApplyData model = new CreditLimitsApplyData();
        model.setApplyNo(null);
        model.setCompanyKey(null);
        model.setNameCode(aibankUser.getNameCode());
        model.setUserKey(null);
        model.setUserId(aibankUser.getUserId());
        model.setClientIp(getClientIp());
        model.setTxSource(TxSourceType.AI_BANK.getCode());
        model.setTxType(cache.getAdjustItem().getCode());
        model.setTxDate(new Date());
        model.setTxStatus(TxStatusType.PROCESS.getCode());
        model.setCreditLimits(ConvertUtils.bigDecimal2Str(cache.getCreditLimit()));
        if (cache.getCustomerCardApply() == null || StringUtils.isBlank(cache.getCustomerCardApply().getAnnualIncome())) {
            model.setAnnualIncome(StringUtils.EMPTY);
        }
        else {
            model.setAnnualIncome(cache.getCustomerCardApply().getAnnualIncome());
        }
        model.setEmail(cache.getWorkInfo().getEmail());
        model.setTraceId(MDC.get(MDCKey.traceId.name()));

        switch (cache.getAdjustItem()) {
        case TURN_UP:
            model.setNewCreditLimits(ConvertUtils.bigDecimal2Str(cache.getAdjustAmt()));
            model.setLimitsPurpose(cache.getQuotaUsage().getCode());
            if (cache.getQuotaUsage().isOther()) {
                model.setLimitsPurposeDesc(cache.getOtherUsage());
            }
            else if (cache.getQuotaUsage().isLifeInsuranceFeeInstallment()) {
                model.setInsurance(ConvertUtils.bigDecimal2Str(cache.getFullInsurAmt()));
            }
            model.setCompanyName(cache.getCompany());
            model.setTitle(cache.getJobTitle());
            model.setWorkYear(cache.getSeniorityY());
            model.setWorkMonth(cache.getSeniorityM());
            model.setOfficeTelArea(cache.getOfficeTelArea());
            model.setOfficeTel(cache.getOfficeTel());
            model.setOfficeTelExt(cache.getOfficeTelExt());
            model.setUploadCount(CollectionUtils.isEmpty(cache.getProofFiles()) ? 0 : cache.getProofFiles().size());
            model.setUploadStatus(null);
            model.setUploadDesc(null);
            model.setOfficeAddrName(cache.getOfficeAddress());
            model.setOfficeAddrCityCode(cache.getCityCode1());
            model.setOfficeAddrCityName(cache.getCityName());
            model.setOfficeAddrTownCode(cache.getZipcode());
            model.setOfficeAddrTownName(cache.getAreaName());
            if (cache.getCustomerCardApply() != null) {
                model.setAiIncomeCode(cache.getCustomerCardApply().getIncomeFlag());
                model.setAiAnnualIncome(cache.getCustomerCardApply().getAnnualIncome());
                model.setAiAdjAmt(cache.getCustomerCardApply().getAdjustAmt());
                model.setAiUpdDatetime(cache.getCustomerCardApply().getSnapDate());
            }
            break;
        case DOWNGRADE:
            model.setNewCreditLimits(ConvertUtils.bigDecimal2Str(cache.getAdjustAmt()));
            break;
        case ADDITIONAL_CARD_LIMIT:
            model.setSecondaryUid(cache.getSupplementaryCard().getVnHdid());
            model.setSecondaryName(cache.getSupplementaryCard().getVnCnam());
            model.setSecondaryCreditLimits(ConvertUtils.bigDecimal2Str(cache.getSupplementaryCard().getVnCpma()));
            model.setNewSecondaryCreditLimits(ConvertUtils.bigDecimal2Str(cache.getAdjustAmt()));
            break;
        }
        return model;
    }

    private EBCC002Request buildEBCC002Request(AIBankUser aibankUser, NCCTX005CacheData cache) {

        Date datetime = new Date();

        EBCC002Request request = new EBCC002Request();

        request.setHTLID("2004012");

        request.setAdjType(cache.getAdjustItem().getCode());
        request.setAdjAmt(cache.getAdjustAmt());
        request.setApplySrc("7"); // 固定為 7
        request.setInsurYear("0");
        request.setSmsFlag("N");
        request.setSmsPhone(StringUtils.EMPTY);
        request.setEmailFlag("Y");
        request.setEmail(cache.getWorkInfo().getEmail());
        request.setIp(getClientIp());
        request.setApplyDate(datetime);
        request.setApplyTime(datetime);
        request.setIdAuthDate(aibankUser.getCreateTime());
        request.setIdAuthTime(aibankUser.getCreateTime());
        if (aibankUser.getCustomerType().isGeneral()) {
            request.setIdAuthType("1");
        }
        else if (aibankUser.getCustomerType().isCardMember()) {
            request.setIdAuthType("2");
        }
        request.setHaveCustomerCardApply(cache.getCustomerCardApply() != null);

        switch (cache.getAdjustItem()) {
        case TURN_UP:
            request.setCustId(aibankUser.getCustId());
            request.setBefAdjAmt(cache.getCreditLimit());
            request.setAdjUsage(cache.getQuotaUsage().getCode());
            if (cache.getQuotaUsage().isOther()) {
                request.setAdjMemo(cache.getOtherUsage());
            }
            else if (cache.getQuotaUsage().isLifeInsuranceFeeInstallment()) {
                request.setInsurAmt(cache.getFullInsurAmt());
            }
            request.setCompNm(cache.getCompany());
            request.setJobTitle(cache.getJobTitle());
            request.setCurSeniorityYear(cache.getSeniorityY());
            request.setCurSeniorityMonth(cache.getSeniorityM());
            request.setoTelArea(cache.getOfficeTelArea());
            request.setoTelNumber(cache.getOfficeTel());
            request.setoExtension(cache.getOfficeTelExt());
            request.setFilecnt(0);
            if (CollectionUtils.isNotEmpty(cache.getProofFiles())) {
                int size = cache.getProofFiles().size();
                request.setFilecnt(size);
                request.setFilename1(cache.getProofFiles().get(0).getName());
                if (size > 1) {
                    request.setFilename2(cache.getProofFiles().get(1).getName());
                }
                if (size > 2) {
                    request.setFilename3(cache.getProofFiles().get(2).getName());
                }
                if (size > 3) {
                    request.setFilename4(cache.getProofFiles().get(3).getName());
                }
                if (size > 4) {
                    request.setFilename5(cache.getProofFiles().get(4).getName());
                }
                if (size > 5) {
                    request.setFilename6(cache.getProofFiles().get(5).getName());
                }
                if (size > 6) {
                    request.setFilename7(cache.getProofFiles().get(6).getName());
                }
                if (size > 7) {
                    request.setFilename8(cache.getProofFiles().get(7).getName());
                }
                if (size > 8) {
                    request.setFilename9(cache.getProofFiles().get(8).getName());
                }
                if (size > 9) {
                    request.setFilename10(cache.getProofFiles().get(9).getName());
                }
            }
            if (cache.getCustomerCardApply() == null) {
                request.setAiIncomeCode(null);
                request.setAiAnnualIncome(null);
                request.setAiAdjAmt(null);
                request.setAiUpdDatetime(null);
            }
            else {
                request.setoZip(cache.getOfficeZip());
                request.setoAddr(cache.getOfficeAddress());
                request.setAiIncomeCode(cache.getCustomerCardApply().getIncomeFlag());
                request.setAiAnnualIncome(cache.getCustomerCardApply().getAnnualIncome());
                request.setAiAdjAmt(cache.getCustomerCardApply().getAdjustAmt());
                request.setAiUpdDatetime(cache.getCustomerCardApply().getSnapDate());
            }
            break;
        case DOWNGRADE:
            request.setCustId(aibankUser.getCustId());
            request.setBefAdjAmt(cache.getCreditLimit());
            break;
        case ADDITIONAL_CARD_LIMIT:
            request.setCustId(cache.getSupplementaryCard().getVnHdid());
            request.setmCustId(cache.getWorkInfo().getAcid());
            request.setBefAdjAmt(cache.getSupplementaryCard().getVnCpma());
            break;
        }
        return request;
    }

    private void sendTxnResultMail(NCCTX005050Rs rsData, CreditLimitsApplyData data, NCCTX005CacheData cache) throws ActionException {

        // 產生Email通知的內容
        Map<String, String> params = getMailParamsByRsData(rsData);
        params.put(MailParamType.TXN_NAME.getCode(), I18NUtils.getMessage("ncctx005.mail.txnName")); // 固定信用額度調高
        params.put(MailParamType.TX_STATUS.getCode(), rsData.getTxnResult().getTxStatus());
        params.put(MailParamType.TX_STATUS_DESC.getCode(), rsData.getTxnResult().getTxStatusDesc());
        params.put(MailParamType.TX_ERROR_DESC.getCode(), rsData.getTxnResult().getTxErrorDesc());
        params.put(MailParamType.TX_ERROR_SYSTEM_ID.getCode(), data.getTxErrorSystemId());
        params.put(MailParamType.TX_ERROR_CODE.getCode(), data.getTxErrorCode());
        params.put(MailParamType.HOST_TX_TIME.getCode(), DateFormatUtils.CE_DATETIME_FORMAT.format(rsData.getTxnResult().getHostTxTime()));

        // 目前信用額度
        params.put("currentQuota", IBUtils.formatMoney(rsData.getCurrentQuota()));
        // 調高後額度
        params.put("adjustAmt", IBUtils.formatMoney(rsData.getAdjustAmt()));
        // 額度用途
        params.put("quotaUsage", rsData.getQuotaUsage().getI18NMemo());

        // 台北富邦行動銀行固定信用額度調高送件通知
        String subject = I18NUtils.getMessage("ncctx005.mail.subject");
        String emailTo = cache.getWorkInfo().getEmail();
        if (StringUtils.isNotBlank(emailTo)) {
            this.sendTxnResultMail(subject, emailTo, params);
        }
        else {
            // 若為一般會員登入，發查 CEW013R 取得信用卡會員登記的行動電話
            String mobileNo = StringUtils.EMPTY;
            if (getLoginUser().getCustomerType().isGeneral()) {
                mobileNo = userDataCacheService.getOtpMobileFromCEW013R(getLoginUser());
            }
            else {
                mobileNo = getLoginUser().getMobileNo();
            }
            if (StringUtils.isNotBlank(mobileNo)) {
                String txStatus = data.getTxStatus();
                String hostTxTime = DateFormatUtils.CE_DATETIME_FORMAT.format(data.getHostTxTime());
                // 【台北富邦銀行】親愛的客戶，您 {交易時間} 於行動銀行調整固定信用額度送件{0, choice, 0#成功|1#失敗|2#預約成功|3#預約失敗|4#結果未明}，如有疑問請洽本行客服。
                String message = I18NUtils.getMessage("ncctx005.sms.message", ConvertUtils.str2Int(txStatus), hostTxTime);
                this.sendResultMsg(message, data.getApplyKey(), null, mobileNo);
            }
        }
    }

}