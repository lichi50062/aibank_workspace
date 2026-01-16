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
package com.tfb.aibank.biz.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.security.repository.entities.CertificateActLogDataEntity;

// @formatter:off
/**
 * @(#)CertificateActLogDataRepo.java
 * 
 * <p>Description:CERTIFICATE LOG DATA</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/12/02, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface CertificateActLogDataRepository extends JpaRepository<CertificateActLogDataEntity, Integer> {

}
