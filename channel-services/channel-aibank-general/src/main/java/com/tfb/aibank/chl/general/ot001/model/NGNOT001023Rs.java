package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001023Rs.java 
* 
* <p>Description:啟用完成頁</p>
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
public class NGNOT001023Rs implements RsData {
    /** 執行結果 0-正常， 1-失敗 */
    private int status;

    /**
     * 變更方式 CHANG_USERID_PINBLOCK("1", "變更使用者代號及密碼"),
     * 
     * CHANG_USERID("2", "變更使用者代號"),
     * 
     * CHANG_PINBLOCK("3", "變更密碼"),
     */
    private String changeType;

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the changeType
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * @param changeType
     *            the changeType to set
     */
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

}
