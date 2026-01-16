package com.tfb.aibank.chl.creditcard.qu008.task;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.creditcard.qu008.cache.NCCQU008CacheData;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008021Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008021Rs;
import com.tfb.aibank.chl.creditcard.qu008.service.NCCQU008Service;
import com.tfb.aibank.chl.type.RemarkContentType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.component.dic.DicCacheManager;

// @formatter:off
/**
 * @(#)NCCQU008021Task.java
 * 
 * <p>Description:信用卡分期管理 021 條款頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU008021Task extends AbstractAIBankBaseTask<NCCQU008021Rq, NCCQU008021Rs> {

    private static final String REMARK_KEY = "NCCQU008_021";

    @Autowired
    protected DicCacheManager dicCacheManager;

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Autowired
    private NCCQU008Service service;

    @Override
    public void validate(NCCQU008021Rq rqData) throws ActionException {
        // 防呆
        if (Objects.isNull(rqData.getIsBillProcess())) {
            throw ExceptionUtils.getActionException(new IllegalAccessException("need bill params"));
        }
    }

    @Override
    public void handle(NCCQU008021Rq rqData, NCCQU008021Rs rsData) throws ActionException {
        NCCQU008CacheData cache = getCache(NCCQU008Service.CACHE_KEY, NCCQU008CacheData.class);
        saveContractLog(NCCQU008021Task.REMARK_KEY, "021");
        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.SESSION_EXPIRED);
        }
        // #1749 簽訂約定書後回寫簽訂狀態
        service.getAgreementFlag(getLoginUser(), cache, "1", dicCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CEW227R_TXBSEQU"));
        // 查詢條款
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS.getType(), NCCQU008021Task.REMARK_KEY, getLocale());
        rsData.setTermsTitle(remarkContent.getTitle());
        rsData.setTermsContent(remarkContent.getContent());
        if (Boolean.TRUE.equals(rqData.getIsBillProcess())) {
            rsData.setTargetPagePath("040");
        }
        else if (StringUtils.equals(cache.getInstallmentType(), NCCQU008Service.UNBILL_TYPE) && !service.isRqTxnDataAnyEmpty(cache.getBillDates())) {
            rsData.setIsCleanHistory(true);
            rsData.setTargetPagePath("040");
        }
        else {
            rsData.setTargetPagePath("030");
        }
        rsData.setIsBillProcess(rqData.getIsBillProcess());
        setCache(NCCQU008Service.CACHE_KEY, cache);
    }

}
