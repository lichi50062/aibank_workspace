package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001040Rs.java 
* 
* <p>Description:登入問題引導頁</p>
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
public class NGNOT001040Rs implements RsData {
    // 開辦數位帳戶
    private String digitalDepositUrl;

    // 已有帳戶
    private String applyUseridPasswordUrl;

    // 開卡
    private String enableCreditcardUrl;

    // 以卡片資訊申請會員
    private String applyCreditcardUserUrl;

    /**
     * @return the digitalDepositUrl
     */
    public String getDigitalDepositUrl() {
        return digitalDepositUrl;
    }

    /**
     * @param digitalDepositUrl
     *            the digitalDepositUrl to set
     */
    public void setDigitalDepositUrl(String digitalDepositUrl) {
        this.digitalDepositUrl = digitalDepositUrl;
    }

    /**
     * @return the applyUseridPasswordUrl
     */
    public String getApplyUseridPasswordUrl() {
        return applyUseridPasswordUrl;
    }

    /**
     * @param applyUseridPasswordUrl
     *            the applyUseridPasswordUrl to set
     */
    public void setApplyUseridPasswordUrl(String applyUseridPasswordUrl) {
        this.applyUseridPasswordUrl = applyUseridPasswordUrl;
    }

    /**
     * @return the enableCreditcardUrl
     */
    public String getEnableCreditcardUrl() {
        return enableCreditcardUrl;
    }

    /**
     * @param enableCreditcardUrl
     *            the enableCreditcardUrl to set
     */
    public void setEnableCreditcardUrl(String enableCreditcardUrl) {
        this.enableCreditcardUrl = enableCreditcardUrl;
    }

    /**
     * @return the applyCreditcardUserUrl
     */
    public String getApplyCreditcardUserUrl() {
        return applyCreditcardUserUrl;
    }

    /**
     * @param applyCreditcardUserUrl
     *            the applyCreditcardUserUrl to set
     */
    public void setApplyCreditcardUserUrl(String applyCreditcardUserUrl) {
        this.applyCreditcardUserUrl = applyCreditcardUserUrl;
    }

}
