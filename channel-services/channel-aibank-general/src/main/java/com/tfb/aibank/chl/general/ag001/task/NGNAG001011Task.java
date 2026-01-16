/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.ag001.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ag001.model.NGNAG001011Rq;
import com.tfb.aibank.chl.general.ag001.model.NGNAG001011Rs;
import com.tfb.aibank.chl.general.ag001.service.NGNAG001Service;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.UpdateUserDeviceBindingRequest;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NGNAG001011Task.java
* 
* <p>Description:免登速查 - 更新設定 - Task</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NGNAG001011Task extends AbstractAIBankBaseTask<NGNAG001011Rq, NGNAG001011Rs> {

    @Autowired
    private UserResource userResource;

    @Autowired
    private NGNAG001Service service;

    @Override
    public void validate(NGNAG001011Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNAG001011Rq rqData, NGNAG001011Rs rsData) throws ActionException {

        // 啟用時，新增一筆同意條款紀錄至DB
        if (rqData.isQsearchFlag()) {
            saveContractLog(NGNAG001Service.REMARK_KEY, "020");
        }

        // 更新使用者裝置綁定資料
        updateUserDeviceBinding(rqData, rsData);
    }

    /**
     * 更新使用者裝置綁定資料
     * 
     * @param rqData
     * @param rsData
     */
    private void updateUserDeviceBinding(NGNAG001011Rq rqData, NGNAG001011Rs rsData) {

        ActionException error = null;
        try {

            UpdateUserDeviceBindingRequest request = new UpdateUserDeviceBindingRequest();
            request.setDeviceUuid(getDeviceIxd());
            request.setUpdateQsearchFlag(true);
            request.setQsearchFlag(rqData.isQsearchFlag() ? 1 : 0);

            AIBankUser loginUser = getLoginUser();
            userResource.updateUserDeviceBinding(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), request);
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            error = service.handleException(getAccessLog(), ExceptionUtils.getActionException(e));
        }
        rsData.setSuccess(null == error);

        // 發送Email通知
        sendEmailNotify(error);
    }

    /**
     * 發送Email通知
     * 
     * @param error
     * @throws
     */
    private void sendEmailNotify(ActionException error) {

        boolean isSuccess = (error == null);

        // 產生Email通知的內容
        Map<String, String> params = new HashMap<>();
        // 結果類型
        params.put("statusType", isSuccess ? "1" : "2");
        // 結果說明
        String statusTypeDesc = isSuccess ? I18NUtils.getMessage("ngnag001.notification.email.success") : I18NUtils.getMessage("ngnag001.notification.email.fail");
        params.put("statusTypeDesc", statusTypeDesc);
        // 有錯誤
        if (!isSuccess) {
            // 錯誤顯示
            params.put("errorDisplay", new StringBuilder("（").append(error.getErrorCode()).append("）").append(error.getErrorDesc()).toString());
        }
        // 交易名稱
        params.put("txnName", I18NUtils.getMessage("ngnag001.notification.email.txn-name"));
        // 交易時間
        params.put("txTime", DateUtils.getDateTimeStr(new Date()));
        // 台北富邦行動銀行「免登速查」{0}通知
        String subject = I18NUtils.getMessage("ngnag001.notification.email.subject", statusTypeDesc);

        this.sendTxnResultMail(subject, getLoginUser().getUserData().getEmails(), params);

    }

}
