package com.tfb.aibank.biz.user.services.knowledgecheck;

import java.util.Date;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.KnowledgeCheckLogRepository;
import com.tfb.aibank.biz.user.repository.entities.KnowledgeCheckLogEntity;
import com.tfb.aibank.biz.user.services.knowledgecheck.model.KnowledgeCheckRequest;

/**
 // @formatter:off
 * @(#)KnowledgeCheckService.java
 * 
 * <p>Description:交易高齡認知 Service</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2024/02/20, Alex PY Li
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KnowledgeCheckService {
    private static final IBLog logger = IBLog.getLog(KnowledgeCheckService.class);
    private KnowledgeCheckLogRepository knowledgeCheckLogRepository;
    private IdentityService identityService;

    public KnowledgeCheckService(KnowledgeCheckLogRepository knowledgeCheckLogRepository, IdentityService identityService) {
        this.knowledgeCheckLogRepository = knowledgeCheckLogRepository;
        this.identityService = identityService;
    }

    /**
     * 新增交易高齡認知檢核
     * 
     * @param request
     * @return
     * @throws CryptException
     */
    public Boolean addKnowledgeCheck(KnowledgeCheckRequest request) throws CryptException {
        IdentityData identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());
        KnowledgeCheckLogEntity entity = new KnowledgeCheckLogEntity();
        entity.setCompanyKey(identityData.getCompanyKey());
        entity.setUserKey(identityData.getUserKey());
        entity.setTaskId(request.getTaskId());
        entity.setQuestion1(request.getQuestion1());
        entity.setAnswer1(request.getAnswer1());
        entity.setQuestion2(request.getQuestion2());
        entity.setAnswer2(request.getAnswer2());
        entity.setQuestion3(request.getQuestion3());
        entity.setAnswer3(request.getAnswer3());
        entity.setClientIp(request.getClientIp());
        entity.setClientPort(request.getClientPort());
        entity.setTraceId(request.getTraceId());
        entity.setCreateTime(new Date());
        this.knowledgeCheckLogRepository.save(entity);
        return true;
    }

}
