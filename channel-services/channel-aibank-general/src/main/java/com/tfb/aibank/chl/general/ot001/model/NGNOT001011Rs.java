package com.tfb.aibank.chl.general.ot001.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.general.resource.vo.MenuForHandShakeVo;

//@formatter:off
/**
* @(#)NGNOT001011Rs.java 
* 
* <p>Description:登入執行頁</p>
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
public class NGNOT001011Rs implements RsData {

    /** 登入狀態 */
    private String status;

    /** 信用卡會員 */
    private String isCCUser;

    /** Token */
    private String token;

    /**
     * 使用者代號 / 密碼 變更
     */
    private int nextJob;

    /** 進入快速登入，0-不能設定 1-可以設定 2-已綁其他，可以設定 */
    private int fastLoginStatus;

    /** 顯示變更密碼提示 */
    private boolean isShowChangeTip;

    /** Celebrus Encrypted ID */
    private String celebrusId;

    /** 所有選單資訊 */
    private Map<String, List<MenuForHandShakeVo>> menuInfo;

    /** 預設字體大小 */
    private int fontSize;

    /** 「是否需提供滿意度問卷」註記 */
    private boolean isShowSatisfactionFlag;

    /** 啟用「滿意度調查」功能清單 ex: key:NPYTX001040 value:繳信用卡款 */
    private Map<String, String> satisfactionTasksMap = new HashMap<>();
    
    /** 需要雙重驗證 */
    private boolean isTwoFactorAuth;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the isCCUser
     */
    public String getIsCCUser() {
        return isCCUser;
    }

    /**
     * @param isCCUser
     *            the isCCUser to set
     */
    public void setIsCCUser(String isCCUser) {
        this.isCCUser = isCCUser;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the nextJob
     */
    public int getNextJob() {
        return nextJob;
    }

    /**
     * @param nextJob
     *            the nextJob to set
     */
    public void setNextJob(int nextJob) {
        this.nextJob = nextJob;
    }

    /**
     * @return the fastLoginStatus
     */
    public int getFastLoginStatus() {
        return fastLoginStatus;
    }

    /**
     * @param fastLoginStatus
     *            the fastLoginStatus to set
     */
    public void setFastLoginStatus(int fastLoginStatus) {
        this.fastLoginStatus = fastLoginStatus;
    }

    /**
     * @return the isShowChangeTip
     */
    public boolean isShowChangeTip() {
        return isShowChangeTip;
    }

    /**
     * @param isShowChangeTip
     *            the isShowChangeTip to set
     */
    public void setShowChangeTip(boolean isShowChangeTip) {
        this.isShowChangeTip = isShowChangeTip;
    }

    /**
     * @return the celebrusId
     */
    public String getCelebrusId() {
        return celebrusId;
    }

    /**
     * @param celebrusId
     *            the celebrusId to set
     */
    public void setCelebrusId(String celebrusId) {
        this.celebrusId = celebrusId;
    }

    public Map<String, List<MenuForHandShakeVo>> getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(Map<String, List<MenuForHandShakeVo>> menuInfo) {
        this.menuInfo = menuInfo;
    }

    /**
     * @return the fontSize
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * @param fontSize
     *            the fontSize to set
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isShowSatisfactionFlag() {
        return isShowSatisfactionFlag;
    }

    public void setShowSatisfactionFlag(boolean isShowSatisfactionFlag) {
        this.isShowSatisfactionFlag = isShowSatisfactionFlag;
    }

    public Map<String, String> getSatisfactionTasksMap() {
        return satisfactionTasksMap;
    }

    public void setSatisfactionTasksMap(Map<String, String> satisfactionTasksMap) {
        this.satisfactionTasksMap = satisfactionTasksMap;
    }

    public boolean isTwoFactorAuth() {
        return isTwoFactorAuth;
    }

    public void setTwoFactorAuth(boolean isTwoFactorAuth) {
        this.isTwoFactorAuth = isTwoFactorAuth;
    }
 
}
