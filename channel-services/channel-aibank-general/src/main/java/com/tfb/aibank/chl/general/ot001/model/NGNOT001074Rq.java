package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001074Rq.java 
* 
* <p>Description:記錄移轉項目</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230605, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001074Rq implements RqData {

    /** SDK addProfile response account */
    private String account;

    /** SDK addProfile response deviceID */
    private String deviceId;

    /** SDK addProfile response ClientID */
    private String clientId;

    /** SDK addProfile response SN */
    private String sn;

    /** SDK addProfile response pushID */
    private String pushId;

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn
     *            the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return the pushId
     */
    public String getPushId() {
        return pushId;
    }

    /**
     * @param pushId
     *            the pushId to set
     */
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
