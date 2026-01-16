package com.tfb.aibank.chl.general.qu002.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.common.model.ServiceBase;

// @formatter:off
/**
 * @(#)NGNQU002011Rs.java
 * 
 * <p>Description:服務據點 011 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU002011Rs implements RsData {
    /** 鄰近分行 */
    private List<ServiceBase> branchs;

    /**
     * @return the branchs
     */
    public List<ServiceBase> getBranchs() {
        return branchs;
    }

    /**
     * @param branchs
     *            the branchs to set
     */
    public void setBranchs(List<ServiceBase> branchs) {
        this.branchs = branchs;
    }
}
