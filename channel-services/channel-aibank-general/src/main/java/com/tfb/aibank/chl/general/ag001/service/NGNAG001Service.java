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
package com.tfb.aibank.chl.general.ag001.service;

import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.general.service.NGNService;

//@formatter:off
/**
* @(#)NGNAG001Service.java
* 
* <p>Description:免登速查 - Service</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NGNAG001Service extends NGNService {

    /** 免登速查約定條款 RemarkKey */
    public static final String REMARK_KEY = "NGNAG001_020_LOGIN_QUERY";

}
