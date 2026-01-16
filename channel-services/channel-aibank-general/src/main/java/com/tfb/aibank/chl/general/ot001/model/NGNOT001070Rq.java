package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001070Rq.java 
* 
* <p>Description:移轉設定動畫頁</p>
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
public class NGNOT001070Rq implements RqData {

    /**
     * 是否設定所有訊息
     * 
     */
    private boolean notificationAll;

    /**
     * @return the notificationAll
     */
    public boolean isNotificationAll() {
        return notificationAll;
    }

    /**
     * @param notificationAll
     *            the notificationAll to set
     */
    public void setNotificationAll(boolean notificationAll) {
        this.notificationAll = notificationAll;
    }

}
