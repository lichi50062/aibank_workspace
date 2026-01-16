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
package com.tfb.aibank.biz.user.services.employee.model;

import io.swagger.v3.oas.annotations.media.Schema;
import tw.com.ibm.mf.eb.RTWEBP020001RepeatType;

//@formatter:off
/**
* @(#)RTWEBP02ResRep.java
* 
* <p>Description: 員工持股信託公司查詢 repeat model</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "員工持股信託公司查詢 repeat model")
public class RTWEBP02ResRep {

    /**
     * 公司代號
     */
    @Schema(description = "公司代號")
    private String cmpId;

    /**
     * 公司名稱
     */
    @Schema(description = "公司名稱")
    private String cmpNam;

    public RTWEBP02ResRep() {
    }

    public RTWEBP02ResRep(RTWEBP020001RepeatType repeatType) {
        this.cmpId = repeatType.getCmpID();
        this.cmpNam = repeatType.getCmpNam();
    }

    public String getCmpId() {
        return cmpId;
    }

    public void setCmpId(String cmpId) {
        this.cmpId = cmpId;
    }

    public String getCmpNam() {
        return cmpNam;
    }

    public void setCmpNam(String cmpNam) {
        this.cmpNam = cmpNam;
    }
}
