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
package com.tfb.aibank.biz.user.services.identitytype;

import java.util.List;

import org.apache.xmlbeans.XmlException;

import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.services.identitytype.model.FinancialIndustryModel;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB032179RS;

import tw.com.ibm.mf.eb.EB032179D001SvcRsType;

// @formatter:off
/**
 * @(#)IdentityTypeService.java
 * 
 * <p>Description:身份別 服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/29, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class IdentityTypeService {

    private EsbChannelGateway esbGateway;

    public IdentityTypeService(EsbChannelGateway esbGateway) {
        this.esbGateway = esbGateway;
    }

    /**
     * 是否 金融同業身分業
     * 
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public FinancialIndustryModel isFinancialInstitutionIdentity(String custId) throws XmlException, EAIException, EAIResponseException {
        FinancialIndustryModel model = new FinancialIndustryModel();

        EB032179RS eb032179Rs = this.esbGateway.sendEB032179("0", custId, "999");
        EB032179D001SvcRsType eb032179D001Rs = (EB032179D001SvcRsType) eb032179Rs.getBody().changeType(EB032179D001SvcRsType.type);
        String[] financialIndustryCodes = { "010900", "010901", "010902", "010903", "020900", "020901", "020902", "020903", "040000", "070000", "070200", "070400", "070500", "070800", "070900", "080000", "090000", "100000", "110000", "120000", "130100", "130300", "130400" };
        List<String> financialIndustryCodeList = List.of(financialIndustryCodes);

        boolean isfinancialIndustry = financialIndustryCodeList.contains(eb032179D001Rs.getIDUCOD());
        model.setIsFinancialIndustry(isfinancialIndustry);
        model.setFinancialIndustryCode(eb032179D001Rs.getIDUCOD());
        return model;
    }
}
