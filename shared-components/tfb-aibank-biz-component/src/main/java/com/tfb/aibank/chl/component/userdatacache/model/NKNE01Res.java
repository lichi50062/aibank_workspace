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

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)NKNE01Res.java
 * 
 * <p>Description:股票客戶查詢 下行資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "股票客戶查詢 下行資料")
public class NKNE01Res implements Serializable {

    private static final long serialVersionUID = -1394816379040371361L;

    /** 傳送序號 */
    @Schema(description = "傳送序號")
    private String spRefId;

    /** 帳號 */
    @Schema(description = "帳號")
    private String acctId16;

    /** 姓名 */
    @Schema(description = "姓名")
    private String name;

    /** 性別 */
    @Schema(description = "性別")
    private String sex;

    /** 資料筆數 */
    @Schema(description = "資料筆數")
    private int occur;

    /** 資料 */
    @Schema(description = "資料")
    private List<NKNE01ResRep> repeats;

    public String getSpRefId() {
        return spRefId;
    }

    public void setSpRefId(String spRefId) {
        this.spRefId = spRefId;
    }

    public String getAcctId16() {
        return acctId16;
    }

    public void setAcctId16(String acctId16) {
        this.acctId16 = acctId16;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getOccur() {
        return occur;
    }

    public void setOccur(int occur) {
        this.occur = occur;
    }

    public List<NKNE01ResRep> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<NKNE01ResRep> repeats) {
        this.repeats = repeats;
    }

}
