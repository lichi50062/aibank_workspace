package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250610, benson
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001016Rs implements RsData {
    // 執行結果 0-> 失敗, 1 -> 成功
    private Integer result;
    /** 0 ->初始, 1 -> 檢查驗證狀態 */
    private Integer action = 0;

    /** 檢查時間 */
    private Integer checkSeconds;

    /** 0 -> UNKNOWN, 1 -> WAIT, 2 -> SUCCESS, 3 -> FAIL, 4 -> TIMEOUT */
    private Integer authStep = 0;
    /** 已綁定裝置 */
    private String bindedDevice;
    /** 發送訊息登入時間 */
    private String loginTime;

    public Integer getCheckSeconds() {
        return checkSeconds;
    }

    public void setCheckSeconds(Integer checkSeconds) {
        this.checkSeconds = checkSeconds;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getAuthStep() {
        return authStep;
    }

    public void setAuthStep(Integer authStep) {
        this.authStep = authStep;
    }

    public String getBindedDevice() {
        return bindedDevice;
    }

    public void setBindedDevice(String bindedDevice) {
        this.bindedDevice = bindedDevice;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

}
