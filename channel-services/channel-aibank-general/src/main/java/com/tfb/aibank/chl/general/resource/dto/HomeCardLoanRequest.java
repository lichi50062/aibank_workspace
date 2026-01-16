package com.tfb.aibank.chl.general.resource.dto;

// @formatter:off
/**
 * @(#)HomeCardLoanRequest.java
 * 
 * <p>Description:首頁貸款牌卡資料查詢HomeCardLoanRequest.</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/02,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class HomeCardLoanRequest {

    private static final long serialVersionUID = -1465757927985011209L;

    private String custId;

    /** 身分證號或統編(10碼) */
    private String idno;

    /** 使用者代號 */
    private String userId;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    private String uidDup;

    /** Company Kind */
    private Integer companyKind;

    /** 證件類型 */
    private String idType;

    /** NAME_COD */
    private String nameCod;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

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

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNameCod() {
        return nameCod;
    }

    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }
}
