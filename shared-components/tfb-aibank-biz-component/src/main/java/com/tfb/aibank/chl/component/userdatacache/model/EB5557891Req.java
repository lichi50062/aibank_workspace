package com.tfb.aibank.chl.component.userdatacache.model;

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5557891Request.java
 * 
 * <p>Description:電文(EB5557891)上送欄位</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "電文(EB5557891)上送欄位")
public class EB5557891Req extends TxHeadRq {

    private static final long serialVersionUID = -486405026574505959L;

    /** 身分證號或統編 */
    @Schema(description = "身分證號或統編")
    private String idno;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 交易類別 */
    @Schema(description = "交易類別")
    private String type;

    /** 交易性質 */
    @Schema(description = "交易性質")
    private String itemNo;

    /** 證件類型 */
    @Schema(description = "idType")
    private String idType;

    /** 帳號 */
    @Schema(description = "帳號")
    private String acno;

    /** 子功能 */
    @Schema(description = "子功能")
    private String func;

    /** 電子通路分戶代碼 */
    @Schema(description = "電子通路分戶代碼")
    private String nameCode;

    /** 授信分行別 */
    @Schema(description = "授信分行別")
    private String txSer;

    /** 學期別 */
    @Schema(description = "學期別")
    private String yearTerm;

    /** 幣別 */
    @Schema(description = "幣別")
    private String curr;

    /** 存款透支帳號 */
    @Schema(description = "存款透支帳號")
    private String saNo;

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getTxSer() {
        return txSer;
    }

    public void setTxSer(String txSer) {
        this.txSer = txSer;
    }

    public String getYearTerm() {
        return yearTerm;
    }

    public void setYearTerm(String yearTerm) {
        this.yearTerm = yearTerm;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public String getSaNo() {
        return saNo;
    }

    public void setSaNo(String saNo) {
        this.saNo = saNo;
    }

}
