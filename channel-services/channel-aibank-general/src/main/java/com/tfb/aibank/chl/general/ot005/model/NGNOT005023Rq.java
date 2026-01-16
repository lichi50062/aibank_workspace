package com.tfb.aibank.chl.general.ot005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT005010Rq.java 
* 
* <p>Description:登入頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250305, Benson
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
public class NGNOT005023Rq implements RqData {

    /** 原始密碼 */
    private String pinblock;

    /** 使用者代號 */
    private String userId;

    /** 新密碼 */
    private String pinblock1;

    /**
     * 變更方式 CHANG_USERID_PINBLOCK("1", "變更使用者代號及密碼"),
     * 
     * CHANG_USERID("2", "變更使用者代號"),
     * 
     * CHANG_PINBLOCK("3", "變更密碼"),
     */
    private String changeType;

    /**
     * @return the pinblock
     */
    public String getPinblock() {
        return pinblock;
    }

    /**
     * @param pinblock
     *            the pinblock to set
     */
    public void setPinblock(String pinblock) {
        this.pinblock = pinblock;
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
     * @return the pinblock1
     */
    public String getPinblock1() {
        return pinblock1;
    }

    /**
     * @param pinblock1
     *            the pinblock1 to set
     */
    public void setPinblock1(String pinblock1) {
        this.pinblock1 = pinblock1;
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
