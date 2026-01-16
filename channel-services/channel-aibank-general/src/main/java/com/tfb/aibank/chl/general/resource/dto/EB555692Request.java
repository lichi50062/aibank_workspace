package com.tfb.aibank.chl.general.resource.dto;

import com.tfb.aibank.common.model.TxHeadRq;

// @formatter:off
/**
 * @(#)EB555692Request.java
 * 
 * <p>Description:EB555692 網路銀行歸戶資產查詢 API上行欄位.</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB555692Request extends TxHeadRq {

    /** 功能 */
    private String function;

    /** 身份證字號 */
    private String custId;

    /** 查詢類型 */
    private String type;

    /** 證件類型 */
    private String idType;

    /** 使用者所屬戶名代碼 */
    private String nameCode;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }
}
