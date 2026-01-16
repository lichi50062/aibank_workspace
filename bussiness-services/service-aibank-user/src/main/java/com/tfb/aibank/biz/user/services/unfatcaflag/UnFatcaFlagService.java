package com.tfb.aibank.biz.user.services.unfatcaflag;


import org.apache.xmlbeans.XmlException;

import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.chl.component.userdatacache.model.EB67115Res;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB67115RS;


public class UnFatcaFlagService {
	private EsbChannelGateway esbGateway;

    public UnFatcaFlagService(EsbChannelGateway esbGateway) {
        this.esbGateway = esbGateway;
    }
    
    /**
     * 電文(EB67115)，取得客戶是否具備FATCA排外註記
     * 
     * @param custId
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public EB67115Res sendEB67115(String custId) throws XmlException, EAIException, EAIResponseException {

        EB67115RS rs = this.esbGateway.sendEB67115(custId);


        EB67115Res response = new EB67115Res();

        response.setUnfatcaflag(rs.getBody().getUnfatcaflag());

        return response;
    }
}
