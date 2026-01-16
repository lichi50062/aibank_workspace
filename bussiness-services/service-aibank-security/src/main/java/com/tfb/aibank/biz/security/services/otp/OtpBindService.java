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
package com.tfb.aibank.biz.security.services.otp;

import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.biz.security.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.security.services.otp.model.ApplyOtpRequest;
import com.tfb.aibank.biz.security.services.otp.model.ApplyOtpResponse;
import com.tfb.aibank.biz.security.services.otp.model.DeleteOtpRequest;
import com.tfb.aibank.biz.security.services.otp.model.DeleteOtpResponse;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;

// @formatter:off
/**
 * @(#)OtpBindService.java
 * 
 * <p>Description:OTP綁定服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/18, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OtpBindService {

    private static final IBLog logger = IBLog.getLog(OtpBindService.class);

    private EsbChannelGateway esbChannelGateway;

    public OtpBindService(EsbChannelGateway esbChannelGateway) {
        super();
        this.esbChannelGateway = esbChannelGateway;
    }

    /**
     * 申請OTP
     * 
     * @param request
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public ApplyOtpResponse applyOtp(String custId, String uidDup, String userId, ApplyOtpRequest request) throws XmlException, EAIException, EAIResponseException {

        ApplyOtpResponse response = new ApplyOtpResponse();

        esbChannelGateway.sendEB552170ForApplyOTP(custId, uidDup, userId, request.getNameCode(), request.getMobileNo());

        // 成功
        response.setSuccess(true);

        return response;
    }

    /**
     * 停用OTP
     * 
     * @param request
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public DeleteOtpResponse deleteOtp(String custId, String uidDup, String userId, DeleteOtpRequest request) throws XmlException, EAIException, EAIResponseException {

        DeleteOtpResponse response = new DeleteOtpResponse();

        esbChannelGateway.sendEB552170ForDeleteOTP(custId, uidDup, userId, request.getNameCode(), request.getMobileNo());

        // 成功
        response.setSuccess(true);

        return response;
    }

}