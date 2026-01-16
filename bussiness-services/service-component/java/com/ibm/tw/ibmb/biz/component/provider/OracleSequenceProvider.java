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
package com.ibm.tw.ibmb.biz.component.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.tw.ibmb.util.ValidateParamUtils;

// @formatter:off
/**
 * @(#)OracleSequenceProvider.java
 * 
 * <p>Description:Oracle Sequence provider</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/01, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class OracleSequenceProvider extends DBSequenceProvider {

    private static Map<String, String> ALLOWED_SEQ_MAP = new HashMap<>();

    private Map<String, String> getAllowedSeqMap() {
        if (ALLOWED_SEQ_MAP == null || ALLOWED_SEQ_MAP.isEmpty()) {
            Map<String, String> map = new HashMap<>();
            map.put("CASH_ADVANCE_SEQ", "SELECT CASH_ADVANCE_SEQ.NEXTVAL FROM DUAL");
            map.put("EAI_AI_SEQ", "SELECT EAI_AI_SEQ.NEXTVAL FROM DUAL");
            map.put("TWID_FIDO_SEQ", "SELECT TWID_FIDO_SEQ.NEXTVAL FROM DUAL");
            map.put("FX_TRANS_SEQ", "SELECT FX_TRANS_SEQ.NEXTVAL FROM DUAL");
            map.put("PREREQ_SRL_MB_SEQ", "SELECT PREREQ_SRL_MB_SEQ.NEXTVAL FROM DUAL");
            map.put("PREREQ_SRL_SEQ", "SELECT PREREQ_SRL_SEQ.NEXTVAL FROM DUAL");
            map.put("TRUST_APPLY_UPLOAD_FILE_SEQ", "SELECT TRUST_APPLY_UPLOAD_FILE_SEQ.NEXTVAL FROM DUAL");
            map.put("TRACE_NUM_SEQ", "SELECT TRACE_NUM_SEQ.NEXTVAL FROM DUAL");
            map.put("TSTACD_SEQ", "SELECT TSTACD_SEQ.NEXTVAL FROM DUAL");
            map.put("PAYMENT_AUTO_BAT_SEQ", "SELECT PAYMENT_AUTO_BAT_SEQ.NEXTVAL FROM DUAL");
            map.put("PAYMENT_AUTO_TXN_SEQ", "SELECT PAYMENT_AUTO_TXN_SEQ.NEXTVAL FROM DUAL");
            ALLOWED_SEQ_MAP = map;
        }
        return ALLOWED_SEQ_MAP;
    }

    @Override
    protected String getNextValSQL() {

        // #4504 fortify SQL Injection
        String seqName = getSeqName();
        if (!getAllowedSeqMap().containsKey(seqName)) {
            throw new IllegalArgumentException("invalid sequence name");
        }
        else {
            return getAllowedSeqMap().get(seqName);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public final Long doSelectNextSeq() {
        String nextValSQL = ValidateParamUtils.validParam(getNextValSQL());
        if (!getAllowedSeqMap().containsValue(nextValSQL)) {
            throw new IllegalArgumentException("invalid val sql");
        }
        else {
            return getJdbcTemplate().queryForObject(nextValSQL, Long.class);
        }
    }

}
