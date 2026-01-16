/**
 * 
 */
package com.tfb.aibank.component.accountinfoLoan;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)AccountInfoLoan.java
* 
* <p>Description:貸款帳號資訊檔</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/11/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AccountInfoLoanVo {

    private static final long serialVersionUID = 1L;

    /**
     * 鍵值
     */
    @Schema(description = "ACCOUNT_INFO_LOAN_KEY")
    private Integer accountInfoLoanKey;

    /**
     * 產品大類
     */
    @Schema(description = "ATYPE")
    private String atype;

    /**
     * 建立時間
     */
    @Schema(description = "CREATE_TIME")
    private Date createTime;

    /**
     * 備註1
     */
    @Schema(description = "DESCRIPTION")
    private String description;

    /**
     * 備註2
     */
    @Schema(description = "DESCRIPTION_SUB")
    private String descriptionSub;

    /**
     * 性質別
     */
    @Schema(description = "LN_TYP")
    private String lnTyp;

    /**
     * 貸款類型
     */
    @Schema(description = "LOAN_TYPE")
    private String loanType;

    /**
     * 產品類型
     */
    @Schema(description = "PRODUCT_TYPE")
    private String productType;

    /**
     * 專案別
     */
    @Schema(description = "STU_SP_NO")
    private String stuSpNo;

    /**
     * 更新時間
     */
    @Schema(description = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 取得鍵值
     * 
     * @return Integer 鍵值
     */
    public Integer getAccountInfoLoanKey() {
        return this.accountInfoLoanKey;
    }

    /**
     * 設定鍵值
     * 
     * @param accountInfoLoanKey
     *            要設定的鍵值
     */
    public void setAccountInfoLoanKey(Integer accountInfoLoanKey) {
        this.accountInfoLoanKey = accountInfoLoanKey;
    }

    /**
     * 取得產品大類
     * 
     * @return String 產品大類
     */
    public String getAtype() {
        return this.atype;
    }

    /**
     * 設定產品大類
     * 
     * @param atype
     *            要設定的產品大類
     */
    public void setAtype(String atype) {
        this.atype = atype;
    }

    /**
     * 取得建立時間
     * 
     * @return Date 建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定建立時間
     * 
     * @param createTime
     *            要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得備註1
     * 
     * @return String 備註1
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 設定備註1
     * 
     * @param description
     *            要設定的備註1
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 取得備註2
     * 
     * @return String 備註2
     */
    public String getDescriptionSub() {
        return this.descriptionSub;
    }

    /**
     * 設定備註2
     * 
     * @param descriptionSub
     *            要設定的備註2
     */
    public void setDescriptionSub(String descriptionSub) {
        this.descriptionSub = descriptionSub;
    }

    /**
     * 取得性質別
     * 
     * @return String 性質別
     */
    public String getLnTyp() {
        return this.lnTyp;
    }

    /**
     * 設定性質別
     * 
     * @param lnTyp
     *            要設定的性質別
     */
    public void setLnTyp(String lnTyp) {
        this.lnTyp = lnTyp;
    }

    /**
     * 取得貸款類型
     * 
     * @return String 貸款類型
     */
    public String getLoanType() {
        return this.loanType;
    }

    /**
     * 設定貸款類型
     * 
     * @param loanType
     *            要設定的貸款類型
     */
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    /**
     * 取得產品類型
     * 
     * @return String 產品類型
     */
    public String getProductType() {
        return this.productType;
    }

    /**
     * 設定產品類型
     * 
     * @param productType
     *            要設定的產品類型
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * 取得專案別
     * 
     * @return String 專案別
     */
    public String getStuSpNo() {
        return this.stuSpNo;
    }

    /**
     * 設定專案別
     * 
     * @param stuSpNo
     *            要設定的專案別
     */
    public void setStuSpNo(String stuSpNo) {
        this.stuSpNo = stuSpNo;
    }

    /**
     * 取得更新時間
     * 
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     * 
     * @param updateTime
     *            要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
