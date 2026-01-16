package com.tfb.aibank.chl.creditcard.resource.dto;

/**
 * 信用卡掛失 rq
 * 
 * @author Evan Wang
 *
 */
public class CEW1410RRequest {

    /** 功能別 */
    private String func;

    /** 持卡ID */
    private String hdid;

    /** 卡號 */
    private String crdno;

    /** 是否為正卡 */
    private Boolean isPrimaryCard;

    /**
     * @return the func
     */
    public String getFunc() {
        return func;
    }

    /**
     * @param func
     *            the func to set
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * @return the crdno
     */
    public String getCrdno() {
        return crdno;
    }

    /**
     * @param crdno
     *            the crdno to set
     */
    public void setCrdno(String crdno) {
        this.crdno = crdno;
    }

    /**
     * @return the hdid
     */
    public String getHdid() {
        return hdid;
    }

    /**
     * @param hdid
     *            the hdid to set
     */
    public void setHdid(String hdid) {
        this.hdid = hdid;
    }

    /**
     * @return the isPrimaryCard
     */
    public Boolean getIsPrimaryCard() {
        return isPrimaryCard;
    }

    /**
     * @param isPrimaryCard
     *            the isPrimaryCard to set
     */
    public void setIsPrimaryCard(Boolean isPrimaryCard) {
        this.isPrimaryCard = isPrimaryCard;
    }

}
