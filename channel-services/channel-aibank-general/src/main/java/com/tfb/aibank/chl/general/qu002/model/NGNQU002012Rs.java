package com.tfb.aibank.chl.general.qu002.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.common.model.ServiceBase;

// @formatter:off
/**
 * @(#)NGNQU002012Rs.java
 * 
 * <p>Description:服務據點 012 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU002012Rs implements RsData {
    /** 鄰近atm */
    private List<ServiceBase> atms;

    /**
     * @return the atms
     */
    public List<ServiceBase> getAtms() {
        return atms;
    }

    /**
     * @param atms
     *            the atms to set
     */
    public void setAtms(List<ServiceBase> atms) {
        this.atms = atms;
    }
}
