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

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// @formatter:off
/**
 * @(#)TableSequenceProvider.java
 * 
 * <p>Description:SQL Table based implements of DDBSequenceProvider</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/01, Horance Chou	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class TableSequenceProvider extends DBSequenceProvider {

    private static final String SEQ_SQL = "SELECT NEXT_VAL FROM SEQ_TABLE WHERE SEQ_NAME = ?";

    private static final String INSERT_SEQ_SQL = "INSERT INTO SEQ_TABLE (SEQ_NAME, NEXT_VAL) values (?, ?)";

    private static final String UPDATE_SEQ_SQL = "UPDATE SEQ_TABLE set NEXT_VAL = ? WHERE SEQ_NAME = ?";

    private Long max = 999999999L;

    private Long start = 1000L;

    private Long interval = 1L;

    protected String getUpdateSQL() {
        return UPDATE_SEQ_SQL;
    }

    protected String getInsertSQL() {
        return INSERT_SEQ_SQL;
    }

    @Override
    protected String getNextValSQL() {
        return SEQ_SQL;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public final Long doSelectNextSeq() {
        Long seq;
        try {
            seq = getJdbcTemplate().queryForObject(getNextValSQL(), Long.class, getSeqName());
        }
        catch (EmptyResultDataAccessException e) {
            seq = startValue();
            getJdbcTemplate().update(getInsertSQL(), getSeqName(), startValue() + getInterval());
        }
        Long nextVal = seq + getInterval();
        if (nextVal.compareTo(maxValue()) >= 0) {
            nextVal = startValue();
        }
        getJdbcTemplate().update(getUpdateSQL(), nextVal, getSeqName());
        return seq;
    }

    public boolean isCycle() {
        return true;
    }

    public Long maxValue() {
        return max;
    }

    public Long startValue() {
        return start;
    }

    public Long getInterval() {
        return interval;
    }

}
