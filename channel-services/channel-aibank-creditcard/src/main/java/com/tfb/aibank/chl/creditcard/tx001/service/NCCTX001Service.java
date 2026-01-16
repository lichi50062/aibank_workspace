package com.tfb.aibank.chl.creditcard.tx001.service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.tfb.aibank.chl.component.bank.BankCacheManager;
import com.tfb.aibank.chl.component.branch.BankBranchCacheManager;
import com.tfb.aibank.chl.component.tasktime.TaskTime;
import com.tfb.aibank.chl.component.tasktime.TaskTimeCacheManager;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001030Rq;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001Output;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCTX001Service.java
*
* <p>Description:預借現金</p>
*
* <p>Modify History:</p>
* v1.0, 2023/08/10 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NCCTX001Service extends NCCService {

    @Autowired
    private BankCacheManager bankCacheManager;

    @Autowired
    private BankBranchCacheManager bankBranchCacheManager;

    @Autowired
    private TaskTimeCacheManager taskTimeCacheManager;

    public final static String NCCTX001_CACHE_KEY = "NCCTX001_CACHE_KEY";

    public final static String NCCTX001_CACHE_TXN_KEY = "NCCTX001_CACHE_TXN_KEY";

    public boolean isBusinessTime() {
        // 是否為營業日
        if (!isBusinessDay(new Date())) {
            return false;
        }

        TaskTime taskTime = taskTimeCacheManager.getTaskTimeById("NCCTX001");

        if (taskTime == null) {
            taskTime = new TaskTime();
            taskTime.setGeneralStartTime("09:00");
            taskTime.setGeneralEndTime("15:30");
        }

        return isBusinessTime(taskTime.getGeneralStartTime(), taskTime.getGeneralEndTime());
    }

    //@formatter:off
    /**
     * 檢核是否為營業時間內
     * 
     * @param startStr [09:00]
     * @param endStr [21:00]
     * @return
     */
    //@formatter:on
    public static boolean isBusinessTime(String startStr, String endStr) {
        Calendar c = Calendar.getInstance();
        String hh = StringUtils.leftPadZero(String.valueOf(c.get(Calendar.HOUR_OF_DAY)), 2);
        String mm = StringUtils.leftPadZero(String.valueOf(c.get(Calendar.MINUTE)), 2);
        String ss = StringUtils.leftPadZero(String.valueOf(c.get(Calendar.SECOND)), 2);
        String nowTime = String.format("%s:%s:%s", hh, mm, ss);
        LocalTime start = LocalTime.parse(startStr + ":00");
        LocalTime end = LocalTime.parse(endStr + ":00");
        LocalTime now = LocalTime.parse(nowTime);
        return !now.isAfter(end) && !now.isBefore(start);
    }

    public String getAccountName(String accountKey, NCCTX001CacheVo cache) {

        for (TransOutAccount acct : cache.getAccountList()) {
            if (acct.getAccountKey().equals(accountKey)) {
                cache.setAccountNo(acct.getAccountId());
                return acct.getDisplayAccountName() + acct.getAccountId();
            }
        }
        return "";
    }

    public String getOtherBankAccountName(String bankId, String branchId, String accountNo, Locale locale) {

        String bankName = bankCacheManager.getBankName(bankId, locale);

        String branchName = bankBranchCacheManager.getBranchName(bankId, branchId, locale);

        return bankId + bankName + branchId + StringUtils.trimAllBigSpace(branchName) + "<BR>" + accountNo;
    }

    public boolean isOtherBank(NCCTX001030Rq rqData) {

        if ("0".equals(rqData.getAccountKey()) && StringUtils.isNotBlank(rqData.getBankId()) && StringUtils.isNotBlank(rqData.getBranchId()) && StringUtils.isNotBlank(rqData.getAccountNo())) {
            return true;
        }

        return false;
    }

    public BigDecimal getFee(int amount) {
        return new BigDecimal(amount * 0.03 + 100);
    }

    /**
     * 發查電文 CEW303R
     * 
     * @param aibankUser
     * @param dataOutput
     */
    public void sendCEW303R(AIBankUser aibankUser, NCCTX001Output dataOutput) {
        try {
            BaseServiceResponse<CEW303RResponse> cew303RResponse;
            if (aibankUser.getCompanyKind() == 4) {
                // 附卡信用卡會員
                cew303RResponse = creditCardResource.sendCEW303R("", aibankUser.getCardUserProfileVo().getCardNo());
            }
            else {
                cew303RResponse = creditCardResource.sendCEW303R(aibankUser.getCustId(), "");
            }
            dataOutput.setCew303RRes(cew303RResponse.getData());
        }
        catch (ServiceException sex) {
            logger.warn("CEW303R 發查失敗，不影響程序。");
        }
    }
}
