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
package com.tfb.aibank.biz.security.services.motp.helper.model;

import com.tfb.aibank.biz.security.repository.entities.MotpTransDataEntity;
import com.tfb.aibank.biz.security.repository.entities.MotpVerifyCarrierEntity;

// @formatter:off
/**
 * @(#)FindMotpTransDataResult.java
 * 
 * <p>Description:取得交易認證資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/16, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FindMotpTransDataResult {

    /** MOTP交易認證資訊 */
    private MotpTransDataEntity motpTransData;

    /** MOTP交易驗證之載具資訊 */
    private MotpVerifyCarrierEntity carrierData;

    /**
     * @return the motpTransData
     */
    public MotpTransDataEntity getMotpTransData() {
        return motpTransData;
    }

    /**
     * @param motpTransData
     *            the motpTransData to set
     */
    public void setMotpTransData(MotpTransDataEntity motpTransData) {
        this.motpTransData = motpTransData;
    }

    /**
     * @return the carrierData
     */
    public MotpVerifyCarrierEntity getCarrierData() {
        return carrierData;
    }

    /**
     * @param carrierData
     *            the carrierData to set
     */
    public void setCarrierData(MotpVerifyCarrierEntity carrierData) {
        this.carrierData = carrierData;
    }

}
