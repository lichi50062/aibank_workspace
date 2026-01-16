package com.tfb.aibank.chl.creditcard.ag003.service;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.UserResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CE6210RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.ChangeCustDataRecordModel;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCAG003Service.java
 * 
 * <p>Description:信用卡Email Service</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

@Service
public class NCCAG003Service extends NCCService {
    private static final IBLog logger = IBLog.getLog(NCCAG003Service.class);

    public static final String NCCAG003_CACHE_KEY = "NCCAG003CacheKey";

    public static final int DEFAULT_RESEND_COUNTDOWN_SECONDS = 30;

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private UserResource userResource;

    /**
     * 取得卡戶email
     * 
     * @param user
     * @param output
     */
    public void getCardUserEmail(AIBankUser user, CE6210RResponse output) {
        CE6210RResponse res = creditCardResource.getCardUserBaseInfo(user.getCustId());
        output.seteMail(res.geteMail());
    }

    /**
     * 修改卡戶email
     * 
     * @param user
     * @param newEmail
     */
    public void settingCardEmail(AIBankUser user, String newEmail) {
        creditCardResource.setCardUserEmail(user.getCustId(), newEmail);
    }

    /**
     * 創建個人資料紀錄
     * 
     * @param user
     * @throws Exception
     */
    public void createCustDataRecord(AIBankUser user, String newEmail, ChangeCustDataRecordModel model) {
        model.setNameCode(user.getNameCode());
        model.setChangeItem("02");
        model.setCustEmail(newEmail);
        model.setTxDate(new Date());
        model.setTxStatus("4");
        model.setCreateTime(new Date());
        // updateTime 不需寫入 ＠see 08/11 規格
        model.setTxSource("3");
        ChangeCustDataRecordModel newModel = userResource.createChangeCustDataRecord(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), model);
        model.setRecordKey(newModel.getRecordKey());
    }

    /**
     * 修改個人紀錄
     * 
     * @param user
     * @param model
     * @param aex
     */
    public void updateCustDataRecord(AIBankUser user, ChangeCustDataRecordModel model, ActionException aex) {
        if (Objects.isNull(aex.getStatus())) {
            model.setHostTxTime(new Date());
            model.setTxStatus("0");
            model.setUpdateTime(new Date());
        }
        else {
            model.setTxErrorCode(aex.getErrorCode());
            model.setTxErrorDesc(aex.getErrorDesc());
            model.setTxErrorSystemId(aex.getSystemId());
        }
        userResource.updateChangeCustDataRecord(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), model);

    }

}
