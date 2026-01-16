/**
 *
 */
package com.tfb.aibank.chl.component.loan;

//@formatter:off
import io.swagger.v3.oas.annotations.media.Schema; /**
* @(#)EB555789Req.java
* 
* <p>Description:EB555789 取得貸款帳號 Req</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/05, Jackie Chien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB555789Req {

    /** 身分證號或統編(10碼) */
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
    @Schema(description = "證件類型")
    private String idType;

    /** 子功能 */
    @Schema(description = "子功能")
    private String func;

    /** NAME_COD */
    @Schema(description = "NAME_COD")
    private String nameCod;

    /** TX_SER */
    @Schema(description = "TX_SER")
    private String txSer;

    /** 貸款帳號 */
    @Schema(description = "貸款帳號")
    private String acno;

    /** 學期別 */
    @Schema(description = "學期別")
    private String yearTerm;

    /** 幣別 */
    @Schema(description = "幣別")
    private String curr;

    /** SA_NO */
    @Schema(description = "SA_NO")
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

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getNameCod() {
        return nameCod;
    }

    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }

    public String getTxSer() {
        return txSer;
    }

    public void setTxSer(String txSer) {
        this.txSer = txSer;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
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
