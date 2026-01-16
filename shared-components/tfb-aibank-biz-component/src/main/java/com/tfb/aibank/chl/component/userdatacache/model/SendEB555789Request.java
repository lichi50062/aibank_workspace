/**
 * 
 */
package com.tfb.aibank.chl.component.userdatacache.model;

//@formatter:off
/**
* @(#)SendEB555789Request.java
* 
* <p>Description:EB555789 取得貸款帳號 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class SendEB555789Request {

    /** 身分證號或統編(10碼) */
    private String idno;

    /** 使用者代號 */
    private String userId;

    /** 證件類型 */
    private String idType;

    /** NAME_COD */
    private String nameCod;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    private String uidDup;

    /** Company Kind */
    private Integer companyKind;

    /**
     * @return the idno
     */
    public String getIdno() {
        return idno;
    }

    /**
     * @param idno
     *            the idno to set
     */
    public void setIdno(String idno) {
        this.idno = idno;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType
     *            the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the nameCod
     */
    public String getNameCod() {
        return nameCod;
    }

    /**
     * @param nameCod
     *            the nameCod to set
     */
    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }

    /**
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    /**
     * @return the companyKind
     */
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

}
