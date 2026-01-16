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
package com.tfb.aibank.chl.component.security.motp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.component.motplog.MotpLogActionType;

// @formatter:off
/**
 * @(#)MotpLogHelper.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/03, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class MotpLogHelper {

    @Autowired
    private MotpSecurityResource MotpSecurityResource;

    public void saveLog(MotpLogActionType type, AIBankUser user, String serviceName, String serviceMethod, String Status, String rqData, String rsData) {

        SaveMotpLogRequest request = new SaveMotpLogRequest();
        request.setType(type);
        request.setCustId(user.getCustId());
        request.setUserId(user.getUserId());
        request.setCompanyKind(user.getCompanyKind());
        request.setUidDup(user.getUidDup());
        request.setServiceName(serviceName);

        request.setServiceMethod(serviceMethod);
        request.setStatus(Status);
        request.setRqData(rqData);
        request.setRsData(rsData);

        MotpSecurityResource.saveMotpLog(request);

    }

}
