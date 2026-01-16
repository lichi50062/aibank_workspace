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
package com.tfb.aibank.biz.user.repository;

// @formatter:off
/**
 * @(#)SequenceRepositoryCustom.java
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
public interface SequenceRepositoryCustom {

    Integer getSequenceForDataSyncLog();

}
