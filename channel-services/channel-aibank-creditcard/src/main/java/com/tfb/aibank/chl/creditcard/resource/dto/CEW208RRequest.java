package com.tfb.aibank.chl.creditcard.resource.dto;

/**
 * 信用卡開卡 rq
 * 
 * @author Evan Wang
 *
 */
public class CEW208RRequest {
    /** 信用卡號 */
    private String custcdno;

    /** 檢查碼 */
    private String custcvv2;

    /** 生日 */
    private String custbrdy;

    /** 有效期限 */
    private String custnedy;

    /**
     * @return the custcdno
     */
    public String getCustcdno() {
        return custcdno;
    }

    /**
     * @param custcdno
     *            the custcdno to set
     */
    public void setCustcdno(String custcdno) {
        this.custcdno = custcdno;
    }

    /**
     * @return the custcvv2
     */
    public String getCustcvv2() {
        return custcvv2;
    }

    /**
     * @param custcvv2
     *            the custcvv2 to set
     */
    public void setCustcvv2(String custcvv2) {
        this.custcvv2 = custcvv2;
    }

    /**
     * @return the custbrdy
     */
    public String getCustbrdy() {
        return custbrdy;
    }

    /**
     * @param custbrdy
     *            the custbrdy to set
     */
    public void setCustbrdy(String custbrdy) {
        this.custbrdy = custbrdy;
    }

    /**
     * @return the custnedy
     */
    public String getCustnedy() {
        return custnedy;
    }

    /**
     * @param custnedy
     *            the custnedy to set
     */
    public void setCustnedy(String custnedy) {
        this.custnedy = custnedy;
    }

}
