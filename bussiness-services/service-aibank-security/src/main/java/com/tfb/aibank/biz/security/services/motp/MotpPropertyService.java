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
package com.tfb.aibank.biz.security.services.motp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.security.services.motp.type.MotpParam;
import com.tfb.aibank.common.type.BooleanType;

// @formatter:off
/**
 * @(#)MotpPropertyService.java
 * 
 * <p>Description:取得MOTP相關系統參數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class MotpPropertyService {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    /**
     * MOTP系統是否可以使用
     * 
     * @return
     */
    public boolean isMotpAvaliable() {

        return BooleanType.find(systemParamCacheManager.getValue(MotpParam.MOTP_AIBANK_AVALIABLE_FLAG)).isTrue();
    }

    /**
     * 取得驗證截止時效(單位:秒)
     * 
     * @return
     */
    public Integer getMotpExpireTimes() {

        return Integer.parseInt(systemParamCacheManager.getValue(MotpParam.MOTP_AUTH_EXPIRE_TIMES));
    }

}
