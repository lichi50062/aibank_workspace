package com.tfb.aibank.chl.general.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;
import com.tfb.aibank.chl.general.resource.dto.NavigateSetting;

// @formatter:off
/**
 * @(#)NGNQU001010Rq.java
 *
 * <p>Description: NGNQU001010Rq</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[NGNQU001010Rq]</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001010Rq implements RqData {
    
    /**導頁設定值*/
    private NavigateSetting navigateSetting;

    public NavigateSetting getNavigateSetting() {
        return navigateSetting;
    }

    public void setNavigateSetting(NavigateSetting navigateSetting) {
        this.navigateSetting = navigateSetting;
    }
    
    
}
