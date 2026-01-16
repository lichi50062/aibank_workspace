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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5557891Response.java
 * 
 * <p>Description:高中職以上學歷註記(EB032282)下行欄位 Body</p>
 * <p>Modify History:</p>
 * v1.0, 2024/06/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "高中職以上學歷註記(EB032282)下行欄位 Body")
public class EB032282Res extends TxHeadRs {

    private static final long serialVersionUID = 1L;

    /** 戶名 */
    @Schema(description = "戶名")
    private String custName;

    /** 客戶戶況 */
    @Schema(description = "客戶戶況")
    private String custSts;

    /** 學歷待確認註記(判斷是否有高中職以上學歷) */
    // Y：代表已確認過有高中職以上學歷
    // N：代表未確認過高中職以上學歷
    @Schema(description = "學歷待確認註記(判斷是否有高中職以上學歷)")
    private String custEduFlag;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSts() {
        return custSts;
    }

    public void setCustSts(String custSts) {
        this.custSts = custSts;
    }

    public String getCustEduFlag() {
        return custEduFlag;
    }

    public void setCustEduFlag(String custEduFlag) {
        this.custEduFlag = custEduFlag;
    }
}
