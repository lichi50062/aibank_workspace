/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.mfdarea;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)MfdAreaDesc.java
 * 
 * <p>Description:基金地區說明</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/10, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MfdAreaDesc implements Serializable {

    public MfdAreaDesc() {
        // default constructor
    }

    public MfdAreaDesc(String areaCode, String areaName) {
        this.areaCode = areaCode;
        this.areaName = areaName;
    }

    private static final long serialVersionUID = 8297569804676975717L;

    /** 地區代碼 */
    private String areaCode;
    /** 地區名稱 */
    private String areaName;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}
