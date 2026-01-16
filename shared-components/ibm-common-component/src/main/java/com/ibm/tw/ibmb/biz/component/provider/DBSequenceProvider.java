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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.jdbc.core.JdbcTemplate;

// @formatter:off
/**
 * @(#)DBSequenceProvider.java
 * 
 * <p>Description:DB Sequence Provider abstract class</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/01, Horance Chou	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class DBSequenceProvider implements SequenceProvider, BeanFactoryAware {

    private BeanFactory beanFactory;

    /**
     * 取 Oracle jdbcTemplate
     * 
     * @return
     */
    protected JdbcTemplate getJdbcTemplate() {
        return this.beanFactory.getBean("pibJdbcTemplate", JdbcTemplate.class);
    }

    protected abstract String getNextValSQL();

    protected abstract String getSeqName();

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
