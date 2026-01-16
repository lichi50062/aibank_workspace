package com.tfb.aibank.biz.security.gateway;

import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tfb.aibank.common.util.BaNCSUtil;
import com.tfb.aibank.common.util.UserUtils;
import com.tfb.aibank.integration.eai.EAI;
import com.tfb.aibank.integration.eai.EAIChannel;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIFactory;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB552170RQ;
import com.tfb.aibank.integration.eai.msg.EB552170RS;

import tw.com.ibm.mf.eb.EB552170SvcRqType;

//@formatter:off
/**
 * @(#)EsbChannelGateway.java
 * 
 * <p>Description:提供「與電文主機溝通」的類別</p>
 * <p>EsbChannelGateway 作為與電文主機溝通的閘道，上送資訊(Input)和下行資訊(Output)應簡潔單純。應避免在此對資料本身進行任何加工動作。</p>
 * <p>禁止@Autowired其他資源。</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/12/18, Edward Tien    
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Component
public class EsbChannelGateway {

    @Autowired
    private EAIFactory eaiFactory;

    /**
     * 發送EB552170，申請OTP
     * 
     * @param custId
     * @param uidDup
     * @param userId
     * @param nameCode
     * @param mobileNo
     * @param itemNo
     * @return
     * @throws Exception
     */
    public EB552170RS sendEB552170ForApplyOTP(String custId, String uidDup, String userId, String nameCode, String mobileNo) throws XmlException, EAIException, EAIResponseException {

        EAI<EB552170RQ, EB552170RS> eb552170Adaptor = eaiFactory.newInstance(EAIChannel.CBS, EB552170RQ.class, EB552170RS.class);
        EB552170RQ eb552170rq = eb552170Adaptor.getRequest();
        eb552170rq.getHeader().setHTLID("2004111");

        EB552170SvcRqType eb552170rqBody = eb552170rq.getBody();
        eb552170rqBody.setITEMNO("E");
        eb552170rqBody.setIDNO(UserUtils.getTrimmedCompanyUidDup(custId, uidDup));
        eb552170rqBody.setNAMECOD(nameCode);
        eb552170rqBody.setUSERID(userId);
        eb552170rqBody.setUSERIDLEVEL("1");
        eb552170rqBody.setBUSEB5("Y");
        eb552170rqBody.setNEWUSERID(mobileNo);
        eb552170rqBody.setCFPBrhCode("00200");
        eb552170rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));

        return eb552170Adaptor.sendAndReceive("AIBank申請OTP");
    }

    /**
     * 發送EB552170，停用OTP
     * 
     * @param custId
     * @param uidDup
     * @param userId
     * @param nameCode
     * @param mobileNo
     * @param itemNo
     * @return
     * @throws Exception
     */
    public EB552170RS sendEB552170ForDeleteOTP(String custId, String uidDup, String userId, String nameCode, String mobileNo) throws XmlException, EAIException, EAIResponseException {

        EAI<EB552170RQ, EB552170RS> eb552170Adaptor = eaiFactory.newInstance(EAIChannel.CBS, EB552170RQ.class, EB552170RS.class);
        EB552170RQ eb552170rq = eb552170Adaptor.getRequest();
        eb552170rq.getHeader().setHTLID("2004111");

        EB552170SvcRqType eb552170rqBody = eb552170rq.getBody();
        eb552170rqBody.setITEMNO("G");
        eb552170rqBody.setIDNO(UserUtils.getTrimmedCompanyUidDup(custId, uidDup));
        eb552170rqBody.setNAMECOD(nameCode);
        eb552170rqBody.setUSERID(userId);
        eb552170rqBody.setUSERIDLEVEL("1");
        eb552170rqBody.setBUSEB5("Y");
        eb552170rqBody.setNEWUSERID(mobileNo);
        eb552170rqBody.setCFPBrhCode("00200");
        eb552170rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));

        return eb552170Adaptor.sendAndReceive("AIBank停用OTP");
    }

}
