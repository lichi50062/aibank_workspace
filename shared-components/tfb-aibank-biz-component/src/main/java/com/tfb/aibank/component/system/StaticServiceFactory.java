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
package com.tfb.aibank.component.system;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.ArrayUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.SecurityResource;
import com.ibm.tw.ibmb.component.systemparam.SystemParam;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.component.systemparam.SystemParamResource;
import com.tfb.aibank.component.crypto.HSMTxCodeResource;

// @formatter:off
/**
 * @(#)StaticServiceFactory.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class StaticServiceFactory implements InitializingBean {

    private static StaticServiceFactory instance;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private SystemParamResource systemParamResource;

    @Autowired
    private HSMTxCodeResource hsmTxCodeResource;

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private Environment env;

    public static StaticServiceFactory getInstance() {
        if (instance == null) {
            throw new IllegalStateException("application not initialized");
        }
        return instance;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StaticServiceFactory.instance = this;
    }

    public SystemParamCacheManager getSystemParamCacheManager() {
        return systemParamCacheManager;
    }

    public SecurityResource getSecurityResource() {
        return securityResource;
    }

    public boolean isLocalEnvironment() {
        String[] activeProfiles = env.getActiveProfiles();
        if (ArrayUtils.contains(activeProfiles, "default") || ArrayUtils.contains(activeProfiles, "dev")) {
            return true;
        }
        return false;
    }

    public void updateSystemParam(SystemParam paramEntity) {
        systemParamResource.updateSystemParam(paramEntity.getCategory(), paramEntity.getParamKey(), paramEntity);
    }

    public String getHSMTxCode() {
        return this.hsmTxCodeResource.getHSMTxCode();
    }

    public String getKeyCipherHex() {
        SystemParam systemParam = systemParamResource.getSystemParam("CRYPT", "CRYPT_KEY");
        if (systemParam != null) {
            return systemParam.getParamValue();
        }
        return StringUtils.EMPTY;
    }

}
