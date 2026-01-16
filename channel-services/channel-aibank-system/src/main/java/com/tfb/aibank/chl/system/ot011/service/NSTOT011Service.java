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
package com.tfb.aibank.chl.system.ot011.service;

import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.system.ot011.model.NSTOT011Output;
import com.tfb.aibank.chl.system.service.NSTService;

// @formatter:off
/**
 * @(#)NSTOT01Service.java
 * 
 * <p>Description:日終掛牌 買匯匯率popup區塊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/29, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NSTOT011Service extends NSTService {

    public void getInvestCurrencySortNum(String currencyCode, NSTOT011Output output) {
        output.setSort(super.getInvestCurrencySortNum(currencyCode));
    }

}
