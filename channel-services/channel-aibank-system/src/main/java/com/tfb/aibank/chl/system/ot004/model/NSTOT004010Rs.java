package com.tfb.aibank.chl.system.ot004.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.system.resource.dto.Announcement;

// @formatter:off
/**
 * @(#)NSTOT004010Rs.java
 * 
 * <p>Description:handshake之後向後端取值 010 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT004010Rs implements RsData {
    /** systemId 為 IMP 與 APP 的 error 資訊 */
    private List<ErrorCodeForHandShake> errorCodes;

    /** 所有交易資訊 */
    private List<TaskForHandShake> tasks;

    /** 數位客服網址 SYSTEM_PARAM.PARAM_VALUE(CATEGORY=AI_BANK AND PARAM_KEY=CHATBOT_URL */
    private String chatbotURL;

    /** 官網 url */
    private String officialWebURL;

    /** 網銀 url */
    private String internetBankingURL;

    /** 客戶是否開啟快登：查詢MB_DEVICE_INFO.LOGIN_FLAG(Local Storage之DEVICE_UUID) */
    private Boolean loginFirst;

    /** 伺服器入口網址 */
    private String serverHost;

    /** APP商店網址 */
    private String appStoreURL;

    /** 系統公告資料 */
    private Announcement anno;

    /** 常駐功能是否開啟優惠項目 Y/N */
    private String ngnqu003Open;

    /** 台網TWID-Portal 的 URL */
    private String twidPortalURL;

    /** 台網TWID-憑證主旨過濾條件 */
    private String twidCertFilter;

    /** 個股商品分時走勢圖URL */
    private String etfDataURL;

    /** googleMapKey */
    private String googleMapKey;

    /** 開關：是否開啟儲存個人化頁面 */
    private boolean enableSavePersonalResultpage = true;

    /** 免登速查flag=> Y/N */
    private String qsearchFlag;

    /** 當前 pushToken */
    private String currentPushToken;

    /** 貸款功能相關InApp功能參數對照mapping */
    private String loanFromNoMapping;

    /**
     * @return the errorCodes
     */
    public List<ErrorCodeForHandShake> getErrorCodes() {
        return errorCodes;
    }

    /**
     * @param errorCodes
     *            the errorCodes to set
     */
    public void setErrorCodes(List<ErrorCodeForHandShake> errorCodes) {
        this.errorCodes = errorCodes;
    }

    /**
     * @return the tasks
     */
    public List<TaskForHandShake> getTasks() {
        return tasks;
    }

    /**
     * @param tasks
     *            the tasks to set
     */
    public void setTasks(List<TaskForHandShake> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the chatbotURL
     */
    public String getChatbotURL() {
        return chatbotURL;
    }

    /**
     * @param chatbotURL
     *            the chatbotURL to set
     */
    public void setChatbotURL(String chatbotURL) {
        this.chatbotURL = chatbotURL;
    }

    /**
     * @return the officialWebURL
     */
    public String getOfficialWebURL() {
        return officialWebURL;
    }

    /**
     * @param officialWebURL
     *            the officialWebURL to set
     */
    public void setOfficialWebURL(String officialWebURL) {
        this.officialWebURL = officialWebURL;
    }

    /**
     * @return the internetBankingURL
     */
    public String getInternetBankingURL() {
        return internetBankingURL;
    }

    /**
     * @param internetBankingURL
     *            the internetBankingURL to set
     */
    public void setInternetBankingURL(String internetBankingURL) {
        this.internetBankingURL = internetBankingURL;
    }

    /**
     * @return the loginFirst
     */
    public Boolean getLoginFirst() {
        return loginFirst;
    }

    /**
     * @param loginFirst
     *            the loginFirst to set
     */
    public void setLoginFirst(Boolean loginFirst) {
        this.loginFirst = loginFirst;
    }

    /**
     * @return the serverHost
     */
    public String getServerHost() {
        return serverHost;
    }

    /**
     * @param serverHost
     *            the serverHost to set
     */
    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getAppStoreURL() {
        return appStoreURL;
    }

    public void setAppStoreURL(String appStoreURL) {
        this.appStoreURL = appStoreURL;
    }

    public Announcement getAnno() {
        return anno;
    }

    public void setAnno(Announcement anno) {
        this.anno = anno;
    }

    public String getNgnqu003Open() {
        return ngnqu003Open;
    }

    public void setNgnqu003Open(String ngnqu003Open) {
        this.ngnqu003Open = ngnqu003Open;
    }

    /**
     * @return {@link #twidPortalURL}
     */
    public String getTwidPortalURL() {
        return twidPortalURL;
    }

    /**
     * @param twidPortalURL
     *            {@link #twidPortalURL}
     */
    public void setTwidPortalURL(String twidPortalURL) {
        this.twidPortalURL = twidPortalURL;
    }

    /**
     * @return {@link #twidCertFilter}
     */
    public String getTwidCertFilter() {
        return twidCertFilter;
    }

    /**
     * @param twidCertFilter
     *            {@link #twidCertFilter}
     */
    public void setTwidCertFilter(String twidCertFilter) {
        this.twidCertFilter = twidCertFilter;
    }

    public String getEtfDataURL() {
        return etfDataURL;
    }

    public void setEtfDataURL(String etfDataURL) {
        this.etfDataURL = etfDataURL;
    }

    public String getGoogleMapKey() {
        return googleMapKey;
    }

    public void setGoogleMapKey(String googleMapKey) {
        this.googleMapKey = googleMapKey;
    }

    public String getQsearchFlag() {
        return qsearchFlag;
    }

    public void setQsearchFlag(String qsearchFlag) {
        this.qsearchFlag = qsearchFlag;
    }

    public boolean isEnableSavePersonalResultpage() {
        return enableSavePersonalResultpage;
    }

    public void setEnableSavePersonalResultpage(boolean enableSavePersonalResultpage) {
        this.enableSavePersonalResultpage = enableSavePersonalResultpage;
    }

    /**
     * @return the currentPushToken
     */
    public String getCurrentPushToken() {
        return currentPushToken;
    }

    /**
     * @param currentPushToken
     *            the currentPushToken to set
     */
    public void setCurrentPushToken(String currentPushToken) {
        this.currentPushToken = currentPushToken;
    }

    public String getLoanFromNoMapping() {
        return loanFromNoMapping;
    }

    public void setLoanFromNoMapping(String loanFromNoMapping) {
        this.loanFromNoMapping = loanFromNoMapping;
    }
}
