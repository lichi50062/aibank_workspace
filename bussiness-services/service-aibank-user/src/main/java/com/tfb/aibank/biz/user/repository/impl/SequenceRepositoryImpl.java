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
package com.tfb.aibank.biz.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.tfb.aibank.biz.user.repository.SequenceRepositoryCustom;

// @formatter:off
/**
 * @(#)CompanyRepository.java
 * 
 * <p>Description:流水號 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/11, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class SequenceRepositoryImpl implements SequenceRepositoryCustom {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer getSequenceForDataSyncLog() {
        // fortify: fix SQL injection

        String sql = """
                SELECT AI_DATA_SYNC_LOG_SEQ.NEXTVAL
                FROM DUAL
                """;

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
