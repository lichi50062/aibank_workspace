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
package com.tfb.aibank.biz.user.services.otp;

import org.apache.xmlbeans.XmlException;

import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.services.otp.model.RetrieveUserOTPStatusResponse;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB552170RS;

import tw.com.ibm.mf.eb.EB552170SvcRsType;

//@formatter:off
/**
* @(#)OtpService.java
* 
* <p>Description:OTP Service</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class OtpService {

    private EsbChannelGateway esbChannelGateway;

    public OtpService(EsbChannelGateway esbChannelGateway) {
        this.esbChannelGateway = esbChannelGateway;
    }

    /**
     * 取得使用者OTP狀態
     * 
     * @param custId
     * @param dup
     * @param userId
     * @param nameCode
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public RetrieveUserOTPStatusResponse retrieveUserOTPStatus(String custId, String dup, String userId, String nameCode) throws XmlException, EAIException, EAIResponseException {

        RetrieveUserOTPStatusResponse response = new RetrieveUserOTPStatusResponse();

        EB552170RS rs = esbChannelGateway.sendEB552170ForRetrieveUserOTPStatus(custId, dup, userId, nameCode);

        EB552170SvcRsType rsType = rs.getBodyAsType(EB552170SvcRsType.class);

        response.setOtpFlag(rsType.getOTPFLG());

        response.setOtpMobile(rsType.getOTPMOBILE());

        response.setOtpMobileEmp(rsType.getOtpMobileEmp());

        return response;
    }

}
