package com.tfb.aibank.chl.creditcard.ag010.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.creditcard.ag010.model.NCCAG010DataOutput;
import com.tfb.aibank.chl.creditcard.resource.UserResource;
import com.tfb.aibank.chl.creditcard.resource.dto.ExecuteChangeCcUserPinRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.ExecuteChangeCcUserPinResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.ExecuteUserLogoutRequest;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCAG010Service.java
* 
* <p>Description: 變更密碼(信用卡) service</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, Aaron
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NCCAG010Service {

    private static final IBLog logger = IBLog.getLog(NCCAG010Service.class);

    public static final String CACHE_KEY = "NCCAG010_CACHE_KEY";

    @Autowired
    private UserResource userResource;

    /**
     * 發送變更密碼電文
     * 
     * @param eoldpxss
     * @param enewPxss
     * @param aibankUser
     * @param dataOutput
     */
    public void sendChangePinBlock(String eoldpxss, String enewPxss, AIBankUser aibankUser, NCCAG010DataOutput dataOutput) {

        ExecuteChangeCcUserPinRequest request = new ExecuteChangeCcUserPinRequest();

        request.setUid(aibankUser.getCustId());
        request.setUuid(aibankUser.getUserId());
        request.setSecrxt(eoldpxss);
        request.setNewSecrxt(enewPxss);
        request.setNameCode(aibankUser.getNameCode());

        ExecuteChangeCcUserPinResponse response = userResource.executeChangeCcUserPin(request);
        dataOutput.setResponse(response);
    }

    /**
     * 登出
     * 
     * @param aibankUser
     */
    public void executeUserLogout(AIBankUser aibankUser) {
        ExecuteUserLogoutRequest request = new ExecuteUserLogoutRequest();
        request.setCustId(aibankUser.getCustIdWithDup());
        request.setUserId(aibankUser.getUserId());
        request.setCompanyKind(aibankUser.getCompanyKind());
        request.setLoginLogPk(aibankUser.getLoginLogPk());
        userResource.executeUserLogout(request);
    }

}