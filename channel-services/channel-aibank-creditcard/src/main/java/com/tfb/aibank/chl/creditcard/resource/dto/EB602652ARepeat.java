package com.tfb.aibank.chl.creditcard.resource.dto;


// @formatter:off
/**
 * @(#)EB602652ARepeat.java
 * 
 * <p>Description:查詢客戶簽帳卡資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/03, Bill Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB602652ARepeat {

    /**
     * 卡號
     */
    private String type;

    /**
     * 卡片種類
     */
    private String idno;

    /**
     * 刷卡有效校期
     */
    private String bankId;

    /**
     * 刷卡有效校期
     */
    private String actNo;

    /**
     * 卡片種類名稱
     */
    private String idnoName;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

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
     * @return the bankId
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * @param bankId
     *            the bankId to set
     */
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    /**
     * @return the actNo
     */
    public String getActNo() {
        return actNo;
    }

    /**
     * @param actNo
     *            the actNo to set
     */
    public void setActNo(String actNo) {
        this.actNo = actNo;
    }
    /**
     * @return the idnoName
     */
    public String getIdnoName() {
        return idnoName;
    }

    /**
     * @param idnoName
     *            the idnoName to set
     */
    public void setIdnoName(String idnoName) {
        this.idnoName = idnoName;
    }

    @Override
    public String toString() {
        return "EB602652ARepeat{" +
                "type='" + type + '\'' +
                ", idno='" + idno + '\'' +
                ", bankId='" + bankId + '\'' +
                ", actNo='" + actNo + '\'' +
                ", idnoName='" + idnoName + '\'' +
                '}';
    }
}
