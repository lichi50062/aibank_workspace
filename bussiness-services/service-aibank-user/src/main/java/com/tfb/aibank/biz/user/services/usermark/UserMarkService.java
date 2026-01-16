package com.tfb.aibank.biz.user.services.usermark;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlException;

import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.services.usermark.model.EB552170Response;
import com.tfb.aibank.biz.user.services.usermark.model.FC032155Response;
import com.tfb.aibank.biz.user.services.usermark.model.FC032155RsBody;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB552170RS;
import com.tfb.aibank.integration.eai.msg.FC032155RS;

import tw.com.ibm.mf.eb.EB552170SvcRsType;
import tw.com.ibm.mf.eb.FC032155SvcRsType;

// @formatter:off
/**
 * @(#)UserService.java
 * 
 * <p>Description:客戶註記</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/16, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UserMarkService {

    private EsbChannelGateway esbGateway;

    public UserMarkService(EsbChannelGateway esbChannelGateway) {
        this.esbGateway = esbChannelGateway;
    }

    /**
     * 取得本行客戶註記(FC032155)
     * 
     * @param custId
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public FC032155Response sendFC032155(String custId) throws XmlException, EAIException, EAIResponseException {
        FC032155Response response = new FC032155Response();
        List<FC032155RsBody> bodys = new ArrayList<>();
        List<FC032155RS> rsList = this.esbGateway.sendFC032155(custId);
        for (FC032155RS rs : rsList) {
            FC032155SvcRsType rsBody = rs.getBody();
            FC032155RsBody body = new FC032155RsBody();
            body.setIdnoLoan(rsBody.getIDNOLOAN());
            body.setTelCod1(rsBody.getTELCOD1());
            body.setReason1(rsBody.getReason1());
            body.setAprovDate1(rsBody.getAPROVDATE1());
            body.setBusAddr1(rsBody.getBUSADDR1());
            body.setFrCmpid1(rsBody.getFRCMPID1());
            body.setAdvSts1(rsBody.getADVSTS1());
            body.setBusDate1(rsBody.getBUSDATE1());
            body.setCttAddr1(rsBody.getCTTADDR1());
            body.setAddr0111(rsBody.getADDR0111());
            body.setAddr0121(rsBody.getADDR0121());
            body.setAddr0211(rsBody.getADDR0211());
            body.setAddr0221(rsBody.getADDR0221());
            body.setAddr0311(rsBody.getADDR0311());
            body.setSt1(rsBody.getST1());
            body.setMemo2901(rsBody.getMemo2901());
            body.setResetDate1(rsBody.getResetDate1());
            // body.setCanRes1(rsBody.getCANRES1());
            // body.setMemo24531(rsBody.getMemo24531());
            body.setSrnNo1(rsBody.getSRNNO1());
            body.setMemo24691(rsBody.getMemo24691());
            body.setMemo24741(rsBody.getMemo24741());
            body.setTxEmpId1(rsBody.getTXEMPID1());
            body.setAutEmpId1(rsBody.getAUTEMPID1());
            body.setOrgEmpId1(rsBody.getORGEMPID1());
            body.setCreateDate1(rsBody.getCREATEDATE1());
            body.setLstMtnDate1(rsBody.getLSTMTNDATE1());
            body.setLstMtnTime1(rsBody.getLSTMTNTIME1());
            body.setTelCod2(rsBody.getTELCOD2());
            body.setReason2(rsBody.getReason2());
            body.setAprovDate2(rsBody.getAPROVDATE2());
            body.setBusAddr2(rsBody.getBUSADDR2());
            body.setFrCmpid2(rsBody.getFRCMPID2());
            body.setAdvSts2(rsBody.getADVSTS2());
            body.setBusDate2(rsBody.getBUSDATE2());
            body.setCttAddr2(rsBody.getCTTADDR2());
            body.setAddr0112(rsBody.getADDR0112());
            body.setAddr0122(rsBody.getADDR0122());
            body.setAddr0212(rsBody.getADDR0212());
            body.setAddr0222(rsBody.getADDR0222());
            body.setAddr0312(rsBody.getADDR0312());
            body.setSt2(rsBody.getST2());
            body.setMemo2902(rsBody.getMemo2902());
            body.setResetDate2(rsBody.getResetDate2());
            body.setCanRes2(rsBody.getCANRES2());
            body.setMemo24532(rsBody.getMemo24532());
            body.setSrnNo2(rsBody.getSRNNO2());
            body.setMemo24692(rsBody.getMemo24692());
            body.setMemo24742(rsBody.getMemo24742());
            body.setTxEmpId2(rsBody.getTXEMPID2());
            body.setAutEmpId2(rsBody.getAUTEMPID2());
            body.setOrgEmpId2(rsBody.getORGEMPID2());
            body.setCreateDate2(rsBody.getCREATEDATE2());
            body.setLstMtnDate2(rsBody.getLSTMTNDATE2());
            body.setLstMtnTime2(rsBody.getLSTMTNTIME2());
            body.setMemo1(rsBody.getMEMO1());
            body.setMemo2(rsBody.getMEMO2());
            body.setReasonCode1(rsBody.getReasonCode1());
            body.setReasonCode2(rsBody.getReasonCode2());
            bodys.add(body);
        }
        response.setRsList(bodys);
        return response;
    }

    /**
     * 取得多使用者代碼註記
     * 
     * @param custId
     * @param dup
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public EB552170Response sendEB552170ForSingleUser(String custId, String dup) throws XmlException, EAIException, EAIResponseException {
        EB552170Response response = new EB552170Response();
        EB552170RS rs = esbGateway.sendEB552170ForSingleUser(custId, dup);
        EB552170SvcRsType rsType = rs.getBodyAsType(EB552170SvcRsType.class);
        response.setNtopnCnt(rsType.getNTOPNCNT());
        response.setOpnCnt(new BigDecimal(rsType.getOPNCNT()));
        return response;
    }

}