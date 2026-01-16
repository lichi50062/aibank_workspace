/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.costco;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.piblog.CardCostcoDuesRepository;
import com.tfb.aibank.biz.user.repository.piblog.entities.CardCostcoDuesEntity;
import com.tfb.aibank.biz.user.services.costco.model.CEW466RRes;
import com.tfb.aibank.biz.user.services.costco.model.CardCostcoDuesModel;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.CEW4661RRS;
import com.tfb.aibank.integration.eai.msg.CEW466RRS;

import tw.com.ibm.mf.eb.CEW466RSvcRsType;

// @formatter:off
/**
 * @(#)CostcoCardService.java
 * 
 * <p>Description:[好市多-相關服務]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CostcoCardService {

    private static final IBLog logger = IBLog.getLog(CostcoCardService.class);

    private EsbChannelGateway esbGateway;
    private IdentityService identityService;
    private CardCostcoDuesRepository cardCostcoDuesRepository;

    public CostcoCardService(IdentityService identityService, EsbChannelGateway esbGateway, CardCostcoDuesRepository cardCostcoDuesRepository) {
        this.identityService = identityService;
        this.esbGateway = esbGateway;
        this.cardCostcoDuesRepository = cardCostcoDuesRepository;
    }

    public CEW466RRes sendCEW466R(String custIxd, String requestCode, String autoRenew) throws XmlException, EAIException, EAIResponseException {

        CEW466RRes response = new CEW466RRes();
        CEW466RRS rs = this.esbGateway.sendCEW466R(custIxd, requestCode, autoRenew);
        CEW466RSvcRsType rsBody = rs.getBody();
        response.setResponseCode(rsBody.getResponseCode());
        response.setAutoRenew(rsBody.getAutoRenew());
        response.setAbn(rsBody.getVOABND());
        response.setMembercardType(rsBody.getMembercardType());
        response.setMemberNumber(rsBody.getMemberNumber());

        return response;
    }

    public CEW466RRes sendCEW4661R(String custIxd, String responseCode, String voabnd) throws XmlException, EAIException, EAIResponseException {

        CEW466RRes response = new CEW466RRes();
        CEW4661RRS rs = this.esbGateway.sendCEW4661R(custIxd, responseCode, voabnd);
        rs.getBody();

        // 0000, V848 treat as success
        String herrid = rs.getHeader().getHERRID();
        response.setResponseCode(herrid);

        return response;
    }

    /**
     * save CardCostcoDues
     * 
     * @return
     */
    public CardCostcoDuesModel saveCardCostcoDues(CardCostcoDuesModel model) throws CryptException {
        IdentityData identityData = identityService.query(model.getCustId(), model.getUidDup(), model.getUserId(), model.getCompanyKind());

        CardCostcoDuesEntity entity = new CardCostcoDuesEntity();

        entity.setCompanyKey(identityData.getCompanyKey());
        entity.setNameCode(model.getNameCode());
        entity.setUserKey(identityData.getUserKey());
        entity.setUserId(model.getUserId());
        entity.setClientIp(model.getClientIp());
        entity.setMemberNo(model.getMemberNo());
        entity.setMemberCardType(model.getMemberCardType());
        entity.setTxSource("3");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setTraceId(model.getTraceId());

        entity.setCew466rTxStatus(StringUtils.isNoneBlank(model.getErrorCode466r()) ? "1" : "0");
        entity.setCew466rTxErrorCode(model.getErrorCode466r());
        entity.setCew466rTxErrorDesc(model.getErrorDesc466r());
        entity.setCew4661rTxStatus(StringUtils.isNoneBlank(model.getErrorCode4661r()) ? "1" : "0");
        entity.setCew4661rTxErrorCode(model.getErrorCode4661r());
        entity.setCew4661rTxErrorDesc(model.getErrorDesc4661r());
        entity.setCew4661rTxDate(new Date());
        entity.setCew4661rTxTime(new Date());

        entity = cardCostcoDuesRepository.save(entity);

        model.setCardCostcoDuesKey(entity.getCostcoDuesKey());

        return model;
    }

}
