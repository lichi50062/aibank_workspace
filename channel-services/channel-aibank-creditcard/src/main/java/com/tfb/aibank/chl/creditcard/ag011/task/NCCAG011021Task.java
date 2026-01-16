package com.tfb.aibank.chl.creditcard.ag011.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011021Rq;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011021Rs;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011Cache;
import com.tfb.aibank.chl.creditcard.ag011.service.NCCAG011Service;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCAG011020Task.java
 * 
 * <p>Description:好市多會費代扣繳申請 020 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG011021Task extends AbstractAIBankBaseTask<NCCAG011021Rq, NCCAG011021Rs> {

    @Autowired
    private NCCAG011Service service;

    /** 交易暫存 */
    private NCCAG011Cache cacheData;

    @Override
    public void validate(NCCAG011021Rq rqData) throws ActionException {
        // nothing...
        doTxConfirmCheck();
    }

    @Override
    public void handle(NCCAG011021Rq rqData, NCCAG011021Rs rsData) throws ActionException {

        cacheData = getCache(NCCAG011Service.CACHE_KEY, NCCAG011Cache.class);

        AIBankUser user = getLoginUser();

        // (1) 新增一筆同意條款紀錄至DB
        saveContractLog(NCCAG011Service.REMARK_KEY, "010");

        try {
            service.doTransaction(getLoginUser(), cacheData, rsData, getTraceId());
        }
        catch (ActionException e) {
            throw e;
        }
        finally {
            // 發送交易結果Email通知，若客戶Email為空值需改發簡訊
            // 發送Email通知信
            String email = user.getUserData().getEmails();
            if (StringUtils.isNotBlank(email)) {
                // 發送交易結果Email通知
                sendTxResultEmail(rsData);
            }
            else {
                // 發送交易結果SMS通知
                String subject = I18NUtils.getMessage("nccag011.sms.subject", new Object[] { DateUtils.getDateTimeStr(new Date()), ConvertUtils.str2Int(rsData.getTxStatus()) });
                service.getCardMemberMobile(user);
                String phone = switch (user.getCustomerType()) {
                case CARDMEMBER -> user.getOtpMobileFromCEW013R();
                default -> user.getMobileNo();
                };
                this.sendResultMsg(subject, phone);
            }

        }

    }

    /**
     * 發送交易結果Email通知
     * 
     * @param rsData
     * @param response
     */
    private void sendTxResultEmail(NCCAG011021Rs rsData) {

        // 產生Email通知的內容
        Map<String, String> params = new HashMap<>();

        int status = ConvertUtils.str2Int(rsData.getTxStatus());
        params.put("txStatus", rsData.getTxStatus());
        // 錯誤資訊
        params.put("errorSystemId", rsData.getSystemId());
        params.put("errorCode", rsData.getErrorCode());
        params.put("errorDesc", StringUtils.defaultIfBlank(getErrorDesc(rsData.getSystemId(), rsData.getErrorCode()), rsData.getErrorDesc()));
        // 交易名稱
        String taskName = getTaskName();
        params.put("txnName", taskName);
        // {0}{1, choice, 0#申請成功|1#申請失敗|4#結果未明}通知
        params.put("txnTitle", I18NUtils.getMessage("nccag011.mail.txnTitle", new Object[] { taskName, status }));
        // 交易時間
        params.put("txTime", DateUtils.getDateTimeStr(new Date()));

        String subject = I18NUtils.getMessage("nccag011.mail.subject", new Object[] { taskName, status });

        try {
            this.sendTxnResultMail(subject, getLoginUser().getUserData().getEmails(), params);
        }
        catch (Exception e) {
            // 寄送交易結果郵件，失敗不中斷程序。
            logger.warn("send email failed,{}", e.getMessage(), e);
        }
    }

}
