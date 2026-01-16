package com.tfb.aibank.chl.general.qu001.model;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.tfb.aibank.chl.general.resource.dto.HomeCardLoanResponse;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)DataInput.java
 *
 * <p>Description: DataInput</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[NGNQU001 中介物件]</li>
 * </ol>
 */
//@formatter:on
public class DataInput {
    private String custId;

    /** 公司統編或身份證字號誤別碼 */
    private String uidDup;

    private String userId;

    private Integer companyKind;

    /** 使用者所屬戶名代碼 */
    private String nameCode;

    private Integer companyKey;
    private Integer userKey;

    private boolean dbu;

    private boolean haveCreditCard;

    private Locale locale;

    private List<UsualTaskVo> usualTasks;

    private ProductType productType = ProductType.UNKNOWN;

    /** 裝置UUID */
    private String deviceUuid;

    private List<String> custCurList;

    /** 是否在 AccountCreditcardCheck 黑名單中(由免登速查中回傳的資料取得) */
    private boolean isInAccountCreditcardCheck;

    /** Banc 與 400 的生日是否相同(由免登速查中回傳的資料取得) */
    private boolean isSameBirthday;

    /** 使用者生日 */
    private Date birthday;
    /** 使用者登入IP */
    private String loginIp;
    /** session */
    private String session;

    /**
     * 客群(貸款相關)
     * 1/2/3/4
     */
    private String taGroup;

    private AIBankUser aiBankUser;

    private AIBankUser aiBankUserNoneLoggedIn;

    private HomeCardLoanResponse homeCardLoanResponse;

    private boolean loggedIn;

    /** 重號戶不可使用此功能 */
    private boolean cardDisableForUidDup = false;

    /** 有閱讀訊息權限 */
    private boolean msgPermission = false;

    public List<String> getCustCurList() {
        return custCurList;
    }

    public void setCustCurList(List<String> custCurList) {
        this.custCurList = custCurList;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public boolean isDbu() {
        return dbu;
    }

    public void setDbu(boolean dbu) {
        this.dbu = dbu;
    }

    public boolean isHaveCreditCard() {
        return haveCreditCard;
    }

    public void setHaveCreditCard(boolean haveCreditCard) {
        this.haveCreditCard = haveCreditCard;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<UsualTaskVo> getUsualTasks() {
        return usualTasks;
    }

    public void setUsualTasks(List<UsualTaskVo> usualTasks) {
        this.usualTasks = usualTasks;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public boolean isInAccountCreditcardCheck() {
        return isInAccountCreditcardCheck;
    }

    public void setInAccountCreditcardCheck(boolean inAccountCreditcardCheck) {
        isInAccountCreditcardCheck = inAccountCreditcardCheck;
    }

    public boolean isSameBirthday() {
        return isSameBirthday;
    }

    public void setSameBirthday(boolean sameBirthday) {
        isSameBirthday = sameBirthday;
    }

    public String getTaGroup() {
        return taGroup;
    }

    public void setTaGroup(String taGroup) {
        this.taGroup = taGroup;
    }

    public AIBankUser getAiBankUser() {
        return aiBankUser;
    }

    public void setAiBankUser(AIBankUser aiBankUser) {
        this.aiBankUser = aiBankUser;
    }

    public HomeCardLoanResponse getHomeCardLoanResponse() {
        return homeCardLoanResponse;
    }

    public void setHomeCardLoanResponse(HomeCardLoanResponse homeCardLoanResponse) {
        this.homeCardLoanResponse = homeCardLoanResponse;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public boolean isCardDisableForUidDup() {
        return cardDisableForUidDup;
    }

    public void setCardDisableForUidDup(boolean cardDisableForUidDup) {
        this.cardDisableForUidDup = cardDisableForUidDup;
    }

    public boolean isMsgPermission() {
        return msgPermission;
    }

    public void setMsgPermission(boolean msgPermission) {
        this.msgPermission = msgPermission;
    }

    public AIBankUser getAiBankUserNoneLoggedIn() {
        return aiBankUserNoneLoggedIn;
    }

    public void setAiBankUserNoneLoggedIn(AIBankUser aiBankUserNoneLoggedIn) {
        this.aiBankUserNoneLoggedIn = aiBankUserNoneLoggedIn;
    }
}
