package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001100Rq.java 
* 
* <p>Description:登入 FIDO 綁定初始化 Login</p>
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
public class NGNOT001100Rq implements RqData {

    /** 製造商 */
    private String deviceManufacturer;

    /** 行為 */
    private String action;

    /**
     * @return the deviceManufacturer
     */
    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    /**
     * @param deviceManufacturer
     *            the deviceManufacturer to set
     */
    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

}
