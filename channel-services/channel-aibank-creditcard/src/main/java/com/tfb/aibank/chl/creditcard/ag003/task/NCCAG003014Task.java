package com.tfb.aibank.chl.creditcard.ag003.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag003.cache.NCCAG003CacheData;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003014Rq;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003014Rs;
import com.tfb.aibank.chl.creditcard.ag003.service.NCCAG003Service;
import com.tfb.aibank.chl.creditcard.resource.UserResource;
import com.tfb.aibank.chl.creditcard.resource.dto.ChangeCustDataRecordModel;
import com.tfb.aibank.common.model.TxnResult;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)NCCAG003013Task.java
 * 
 * <p>Description:信用卡email 設定 013 change email</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG003014Task extends AbstractAIBankBaseTask<NCCAG003014Rq, NCCAG003014Rs> {

    @Autowired
    private NCCAG003Service service;

    @Autowired
    private UserResource userResource;

    @Override
    public void validate(NCCAG003014Rq rqData) throws ActionException {
        // 交易前確認安控驗證狀態
        super.doTxConfirmCheckEmail();

        // 交易前確認安控驗證狀態
        super.doTxConfirmCheck();

        String newEmail = rqData.getNewEmail();
        if (StringUtils.isNotBlank(newEmail)) {
            if (!ValidatorUtility.checkEMail(newEmail) || !ValidatorUtility.checkLength(newEmail, 1, 40)) {
                throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
            }
        }
        else {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCAG003014Rq rqData, NCCAG003014Rs rsData) throws ActionException {
        NCCAG003CacheData cache = getCache(NCCAG003Service.NCCAG003_CACHE_KEY, NCCAG003CacheData.class);

        cache.setNewEmail(rqData.getNewEmail());
        ActionException aex = new ActionException();
        ErrorDescription errDesObj = null;
        // (4) 寫入一筆變更個人資料紀錄
        ChangeCustDataRecordModel recordModel = new ChangeCustDataRecordModel();
        recordModel.setClientIp(this.getClientIp());

        TxStatusType txnStatusType = TxStatusType.SUCCESS;
        service.createCustDataRecord(getLoginUser(), rqData.getNewEmail(), recordModel);
        try {
            // (5) 發送電文變更信用卡Email
            service.settingCardEmail(getLoginUser(), rqData.getNewEmail());
        }
        catch (ServiceException sex) {
            aex = service.handleException(getAccessLog(), sex);
            txnStatusType = service.getTxStatusType(sex);
            errDesObj = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex, getLocale(), getFromPage());
        }
        service.updateCustDataRecord(getLoginUser(), recordModel, aex);

        if (StringUtils.equals(txnStatusType.getCode(), TxStatusType.SUCCESS.getCode())) {
            // C. 若發送成功， 更新客戶基本資料。
            userResource.updateCompanyEmail(getLoginUser().getCustId(), getLoginUser().getUidDup(), getLoginUser().getUserId(), getLoginUser().getCompanyKind(), rqData.getNewEmail());
        }

        // @formatter:off
        // fortify: null deference
        TxnResult txnResult = Objects.isNull(aex.getStatus())
                ? createTxnResult(txnStatusType.getCode(), new Date(), "", "", "")
                :  errDesObj == null ? 
                        createTxnResult(txnStatusType.getCode(), new Date(), "", "", "")
                        : createTxnResult(txnStatusType.getCode(), new Date(), errDesObj.getSys(), errDesObj.getCode(), errDesObj.getDesc());
        // @formatter:on
        String txStatusDesc = I18NUtils.getMessage(cache.getIsHasOriginEmail() ? "nccag003.txn_result.hasemail.tx_status.desc" : "nccag003.txn_result.noemail.tx_status.desc", ConvertUtils.str2Int(txnStatusType.getCode()));

        txnResult.setTxStatusDesc(txStatusDesc);

        // 交易結果
        rsData.setTxnResult(txnResult);
        cache.setTxnResult(txnResult);

        if (StringUtils.isNotBlank(cache.getOriginEmail())) {
            // fortify: null deference
            sendEmail(txnResult, errDesObj == null ? "" : errDesObj.getCode(), errDesObj == null ? "" : errDesObj.getSys(), cache.getOriginEmail(), cache.getNewEmail());
        }
        // fortify: null deference
        sendEmail(txnResult, errDesObj == null  ? "" : errDesObj.getCode(), errDesObj == null ? "" : errDesObj.getSys(), cache.getNewEmail(), cache.getNewEmail());
        // #1898 email 更新至 AiBankUser
        getLoginUser().setEmail(cache.getNewEmail());
        getLoginUser().getUserData().setEmails(cache.getNewEmail());
        setCache(NCCAG003Service.NCCAG003_CACHE_KEY, cache);
    }

    private void sendEmail(TxnResult txnResult, String errorCode, String systemId, String email, String newEmail) throws ActionException {
        Map<String, String> params = new HashMap<>();
        params.put("hostTime", DateUtils.getDateTimeStr(new Date()));
        String txnResultDes = StringUtils.equals(txnResult.getTxStatus(), "0") ? I18NUtils.getMessage("nccag003.txn_result.email.success.desc") + "!" : I18NUtils.getMessage("nccag003.txn_result.email.fail.desc", systemId + "_" + errorCode);
        params.put("txnResult", txnResultDes);
        // #1699 異動後發送的Email通知內容有誤
        params.put("email", DataMaskUtil.maskEmailFirst4Chars(newEmail));
        params.put("status", txnResult.getTxStatus());
        params.put("title", getTaskName() + txnResultDes);
        params.put("txnName", getTaskName());
        String subject = I18NUtils.getMessage("email.subject.desc", getTaskName() + I18NUtils.getMessage("nccag003.txn_result.email.success.desc"));

        sendTxnResultMail(subject, email, params);
    }

}
