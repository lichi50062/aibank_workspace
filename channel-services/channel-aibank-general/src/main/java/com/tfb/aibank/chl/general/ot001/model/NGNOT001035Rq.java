package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001035Rq.java 
* 
* <p>Description:開啟推播設定頁</p>
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
public class NGNOT001035Rq implements RqData {

    /**
     * 開啟推播設定
     */
    private int isNotificationOn;

    /** 開啟 Line 通知 */
    private int isLineOn;

    /**
     * @return the isNotificationOn
     */
    public int getIsNotificationOn() {
        return isNotificationOn;
    }

    /**
     * @param isNotificationOn
     *            the isNotificationOn to set
     */
    public void setIsNotificationOn(int isNotificationOn) {
        this.isNotificationOn = isNotificationOn;
    }

    /**
     * @return the isLineOn
     */
    public int getIsLineOn() {
        return isLineOn;
    }

    /**
     * @param isLineOn
     *            the isLineOn to set
     */
    public void setIsLineOn(int isLineOn) {
        this.isLineOn = isLineOn;
    }

}
