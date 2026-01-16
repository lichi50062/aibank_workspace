package com.tfb.aibank.chl.system.ot012.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot012.model.NSTOT012010Rq;
import com.tfb.aibank.chl.system.ot012.model.NSTOT012010Rs;
import com.tfb.aibank.chl.system.ot012.service.NSTOT012Service;
import com.tfb.aibank.chl.system.resource.dto.KnowledgeCheckRequest;

// @formatter:off
/**
 * @(#)NSTOT012010Task.java
 * 
 * <p>Description:高齡認知檢核</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT012010Task extends AbstractAIBankBaseTask<NSTOT012010Rq, NSTOT012010Rs> {

    @Autowired
    private NSTOT012Service service;

    @Override
    public void validate(NSTOT012010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT012010Rq rqData, NSTOT012010Rs rsData) throws ActionException {
        KnowledgeCheckRequest knowledgeCheckRequest = genKnowledgeCheckRequest(rqData);
        service.addKnowledgeCheck(knowledgeCheckRequest);
    }

    /**
     * 建立 交易高齡認知檢核 Request
     * 
     * @param rqData
     * @return
     */
    private KnowledgeCheckRequest genKnowledgeCheckRequest(NSTOT012010Rq rqData) {
        AIBankUser loginUser = getLoginUser();
        loginUser.getCompanyKey();
        loginUser.getUserKey();
        KnowledgeCheckRequest rq = new KnowledgeCheckRequest();
        rq.setCustId(loginUser.getCustId());
        rq.setUidDup(loginUser.getUidDup());
        rq.setUserId(loginUser.getUserId());
        rq.setCompanyKind(loginUser.getCompanyKind());
        rq.setTaskId(rqData.getTaskNo());
        rq.setQuestion1(rqData.getQuestion1());
        rq.setAnswer1(rqData.getAnswer1());
        rq.setQuestion2(rqData.getQuestion2());
        rq.setAnswer2(rqData.getAnswer2());
        rq.setQuestion3(rqData.getQuestion3());
        rq.setAnswer3(rqData.getAnswer3());
        rq.setClientIp(getClientIp());
        rq.setClientPort(getSourcePort());
        rq.setTraceId(getTraceId());
        return rq;
    }

}
