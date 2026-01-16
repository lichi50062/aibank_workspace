package com.tfb.aibank.biz.user.services.feediscount;

import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.util.ConvertUtils;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.services.feediscount.model.FEP512161Response;
import com.tfb.aibank.biz.user.services.feediscount.model.FEP512166Response;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.FEP512161RS;
import com.tfb.aibank.integration.eai.msg.FEP512166RS;

import tw.com.ibm.mf.eb.FEP512161SvcRsType;
import tw.com.ibm.mf.eb.FEP512166SvcRsType;

// @formatter:off
/**
 * @(#)FeeDiscountService.java
 * 
 * <p>Description:提供「手續費優惠」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/19, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FeeDiscountService {

    private EsbChannelGateway esbGateway;

    public FeeDiscountService(EsbChannelGateway esbGateway) {
        this.esbGateway = esbGateway;
    }

    /**
     * 客戶分群跨行手續費優惠查詢
     * 
     * @param custId
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public FEP512166Response sendFEP512166(String custId) throws XmlException, EAIException, EAIResponseException {

        FEP512166RS rs = esbGateway.sendFEP512166(custId);

        FEP512166SvcRsType rsBody = rs.getBodyAsType(FEP512166SvcRsType.class);

        FEP512166Response response = new FEP512166Response();
        response.setSpecCsCnt(ConvertUtils.bigInteger2Integer(rsBody.getSpecCsCnt()));
        response.setRemainCsCnt(ConvertUtils.bigInteger2Integer(rsBody.getRemainCsCnt()));
        response.setSpecTrCnt(ConvertUtils.bigInteger2Integer(rsBody.getSpecTrCnt()));
        response.setRemainTrCnt(ConvertUtils.bigInteger2Integer(rsBody.getRemainTrCnt()));
        response.setSpecTrCnt2(ConvertUtils.bigInteger2Integer(rsBody.getSpecTrCnt2()));
        response.setRemainTrCnt2(ConvertUtils.bigInteger2Integer(rsBody.getRemainTrCnt2()));
        response.setVipFlag(rsBody.getVipFlag());
        response.setVipFlagName(rsBody.getVipFlagName());
        response.setSrcFlg1(rsBody.getSRCFLG1());
        response.setSrcFlg2(rsBody.getSRCFLG2());
        response.setSrcFlg3(rsBody.getSRCFLG3());
        response.setSrcFlg4(rsBody.getSRCFLG4());
        response.setSrcFlg5(rsBody.getSRCFLG5());
        response.setSrcFlg6(rsBody.getSRCFLG6());
        return response;
    }

    /**
     * 客戶分群跨行手續費優惠查詢
     * 
     * @param acctId
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public FEP512161Response sendFEP512161(String acctId) throws XmlException, EAIException, EAIResponseException {

        FEP512161RS rs = esbGateway.sendFEP512161(acctId);

        FEP512161SvcRsType rsBody = rs.getBodyAsType(FEP512161SvcRsType.class);

        FEP512161Response response = new FEP512161Response();
        response.setCsCnt(ConvertUtils.bigInteger2Integer(rsBody.getCsCnt()));
        response.setStaDateSA(ConvertUtils.calendar2Date(rsBody.getStaDateSA()));
        response.setStaDateVB(ConvertUtils.calendar2Date(rsBody.getStaDateVB()));
        response.setCsCntR(ConvertUtils.bigInteger2Integer(rsBody.getCsCntR()));
        response.setEndDateSA(ConvertUtils.calendar2Date(rsBody.getEndDateSA()));
        response.setEndDateVB(ConvertUtils.calendar2Date(rsBody.getEndDateVB()));
        response.setTrCnt(ConvertUtils.bigInteger2Integer(rsBody.getTrCnt()));
        response.setStaDateOT(ConvertUtils.calendar2Date(rsBody.getStaDateOT()));
        response.setStaDateVP(ConvertUtils.calendar2Date(rsBody.getStaDateVP()));
        response.setTrCntR(ConvertUtils.bigInteger2Integer(rsBody.getTrCntR()));
        response.setEndDateOT(ConvertUtils.calendar2Date(rsBody.getEndDateOT()));
        response.setEndDateVP(ConvertUtils.calendar2Date(rsBody.getEndDateVP()));
        response.setTrCnt2(ConvertUtils.bigInteger2Integer(rsBody.getTrCnt2()));
        response.setStaDateEB(ConvertUtils.calendar2Date(rsBody.getStaDateEB()));
        response.setTrCntR2(ConvertUtils.bigInteger2Integer(rsBody.getTrCntR2()));
        response.setEndDateEB(ConvertUtils.calendar2Date(rsBody.getEndDateEB()));
        response.setSrcMemo1(rsBody.getSrcMemo1());
        response.setSrcMemo2(rsBody.getSrcMemo2());
        response.setSrcMemo3(rsBody.getSrcMemo3());
        response.setSrcMemo4(rsBody.getSrcMemo4());
        response.setSrcMemo5(rsBody.getSrcMemo5());
        response.setSrcMemo6(rsBody.getSrcMemo6());
        response.setSrcId4(rsBody.getSrcID4());
        response.setFlg4Name(rsBody.getFLG4NAME());
        return response;
    }
}