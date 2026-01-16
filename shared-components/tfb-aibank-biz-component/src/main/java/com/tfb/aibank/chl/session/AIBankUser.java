/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.session;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.model.B2CWebUser;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.tfb.aibank.chl.component.task.Task;
import com.tfb.aibank.chl.component.userdatacache.model.CEW013RRes;
import com.tfb.aibank.chl.component.userdatacache.model.CM061435CRRes;
import com.tfb.aibank.chl.component.userdatacache.model.EB032280Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB032282Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB032675Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB062171ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.EB202674D003Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB67115Res;
import com.tfb.aibank.chl.component.userdatacache.model.EBHN002Response;
import com.tfb.aibank.chl.component.userdatacache.model.EBLN010Response;
import com.tfb.aibank.chl.component.userdatacache.model.FinancialMgmMemberLevel;
import com.tfb.aibank.chl.component.userdatacache.model.InvestmentNoticeSetting;
import com.tfb.aibank.chl.component.userdatacache.model.KycAnswerResponse;
import com.tfb.aibank.chl.component.userdatacache.model.LoanAccount;
import com.tfb.aibank.chl.component.userdatacache.model.NJWEEN02ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.NKNE01Res;
import com.tfb.aibank.chl.component.userdatacache.model.OdsLoancustData;
import com.tfb.aibank.chl.component.userdatacache.model.PeopleSoftRes;
import com.tfb.aibank.chl.component.userdatacache.model.SDACTQ12ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.TrustContract;
import com.tfb.aibank.chl.component.usualtask.UsualTask;
import com.tfb.aibank.chl.model.account.AgreedInAccount;
import com.tfb.aibank.chl.model.account.FavoriteAccount;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.model.account.TrustAccount;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.EB5556981Response;
import com.tfb.aibank.chl.session.vo.FxRemittanceDetailResponse;
import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;
import com.tfb.aibank.chl.session.vo.UserLoginVo;
import com.tfb.aibank.chl.session.vo.UserVo;
import com.tfb.aibank.common.model.FundOverview;
import com.tfb.aibank.common.type.CompanyBUType;
import com.tfb.aibank.common.type.CompanyKindType;
import com.tfb.aibank.common.type.CreditCardIdType;
import com.tfb.aibank.common.type.LoanCustomerGroupType;
import com.tfb.aibank.common.type.MotpAuthVerifyType;
import com.tfb.aibank.common.type.RoleTemplateType;
import com.tfb.aibank.common.type.TaskPermissionType;
import com.tfb.aibank.common.type.TransOutAcctType;
import com.tfb.aibank.common.util.BaNCSUtil;

// @formatter:off
/**
 * @(#)AIBankUser.java
 * 
 * <p>Description:AI Bank 登入使用者</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@JsonSerialize
public class AIBankUser extends B2CWebUser<UserVo> {

    private static final long serialVersionUID = 1L;

    public static final String AUTH_DEVICE_LOCAL = "Local";
    public static final String AUTH_DEVICE_REMOTE = "Remote";
    public static final String DEVICE_BINDED_STATUS = "02";

    /**
     * 僅用於未正常登出時呼叫 logout API 使用
     */
    private String loginIp;

    /**
     * 是否在生物辨識黑名單 (Y/N)
     */
    private String bioBlackList;

    /**
     * 本次登入方式 0 代號密碼、1指紋/臉部
     */
    private String loginMethod = "0";

    /** 是否在 AccountCreditcardCheck 黑名單中 */
    private boolean isInAccountCreditcardCheck;

    /** Banc 與 400 的生日是否相同 */
    private boolean isSameBirthday;

    /** 公司型態 */
    private CompanyKindType companyKindType = CompanyKindType.PERSONAL;

    /**
     * 判斷是否有綁定裝置, 有-> 02，沒有 -> 其他
     */
    private String deviceBindingStatus;

    /**
     * 是否為綁定裝置
     */
    private String authDevice = "Remote";

    /**
     * 依裝置設定顯示預設交易機制 Y為開啟：預設帶入指紋/臉部辨識。 N為關閉：預設帶入登入密碼。
     */
    private String bioLogin;

    /**
     * encrypted(corpIdNum)
     */
    private String insightId2;

    /**
     * encrypted(authcode)
     */
    private String insightId3;

    /**
     * 用戶角色，GU: 一般用戶，CU: 讀卡機用戶
     */
    private String roleType;

    // =============================== 以下作為信用卡資料的暫存 ===============================
    /** 信用卡註記，是否持有信用卡 */
    private Boolean haveCreditCard;
    /** 信用卡戶況，是否為信用卡特殊戶 */
    private Boolean specialCreditCard;
    /** 信用卡身分別，分為正卡人、附卡人兩種身分 */
    private CreditCardIdType creditCardIdType;
    /** 全部歸戶信用卡清單 */
    private List<CreditCard> allCreditCards;
    /** 有效的歸戶信用卡清單 */
    private List<CreditCard> effectiveCreditCards;
    /** 申辦信用卡客戶資料 */
    private CEW013RRes cew013RRes;
    // =============================== 以上作為信用卡資料的暫存 ===============================

    /** 約定轉出帳號 Map<帳號類型, 帳號清單> */
    private Map<TransOutAcctType, List<TransOutAccount>> transOutAccountsMap = new HashMap<>();

    /** 約定轉出帳號對應約定轉入帳號清單 */
    private Map<String, List<AgreedInAccount>> agreedInAccountsMap = new HashMap<>();
    /** 外幣約定轉出帳號對應約定轉入帳號清單 */
    private Map<String, List<AgreedInAccount>> fxAgreedInAccountsMap = new HashMap<>();

    /** 約定轉出帳號對應常用帳號清單 */
    private Map<String, List<FavoriteAccount>> favoriteAccountsMap = new HashMap<>();

    /** 信託轉入帳號對應幣別清單 */
    private Map<String, Map<String, List<TrustAccount>>> trustAcctsMap = new HashMap<>();

    /** 換匯轉出帳號對應約定轉入帳號清單 */
    private Map<String, List<AgreedInAccount>> fxexInAccountsMap = new HashMap<>();
    // =============================== 以下為貸款資料的暫存 ===============================

    /** 房貸可增貸額度 */
    private EBHN002Response ebhn002Response;

    /** 速貸通資格 */
    private EBLN010Response ebln010Response;
    // =============================== 以上為貸款資料的暫存 ===============================

    // =============================== 以下為使用者OTP資料的暫存 ===============================
    /** (EB552170)簡訊OTP申請註記 */
    private String otpFlagFromEB552170;
    /** (EB552170)接收OTP用行動電話 */
    private String otpMobileFromEB552170;
    /** (EB552170)ＯＴＰ 臨櫃申請/網路申請/ＡＴＭ申請 */
    private String otpMobileEmpFromEB552170;
    /** (CEW013R) */
    private String otpMobileFromCEW013R;
    /** MOTP 是否已申請 (Y/N) */
    private String motpFlag;
    /** MOTP 檢查狀態 */
    private MotpAuthVerifyType motpAuthVerifyType;
    /** 是否已執行過判斷OTP可更新註記 */
    private boolean canUpdateCheckFlag;
    /** 是否可以更新OTP */
    private boolean canUpdateOtp;
    /** 是否可以更新MOTP */
    private boolean canUpdateMotp;
    /** 不能更新OTP的錯誤訊息 */
    private ErrorDescription errorOtp;
    /** 不能更新MOTP的錯誤訊息 */
    private ErrorDescription errorMotp;
    // =============================== 以上為使用者OTP資料的暫存 ===============================

    // ========================== 以下為基金帳戶暫存 =================================
    /** 基金帳戶總覽資訊 */
    private List<FundOverview> fundOverviews;
    /** 高中職以上學歷註記(EB032282) */
    private EB032282Res eb032282Res;
    /** 客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)(EB032675) */
    private EB032675Res eb032675Res;
    /** 取得客戶FATCA排外註記(EB67115) */
    private EB67115Res eb67115Res;
    /** KYC 前次填答結果頁 第一部分 */
    private KycAnswerResponse kycAnswerResponse;
    /** KYC 前次填答結果頁 第二部分 */
    private Map<Locale, List<PeopleSoftRes>> peopleSoftResMap = new HashMap<>();

    // ========================== 以上為基金帳戶暫存 =================================

    // =============================== 使用者相關設定資料 ===============================
    /** 常用功能 */
    private List<UsualTask> usualTasks;

    /** 客戶「登入方式」 : 「一般會員登入」or「信用卡會員登入」 */
    private CustomerType customerType = CustomerType.UNKNOWN;

    /** 客戶BU類型 */
    private CompanyBUType buType = CompanyBUType.UNKNOWN;

    private CompanyVo companyVo;

    private CardUserProfileVo cardUserProfileVo;

    private MbDeviceInfoVo mbDeviceInfoVo;

    private UserLoginVo userLoginVo;

    private EB5556981Response loginMsgRs;

    /** 多使用者代碼判斷，使用 UserDataCacheService.getOpnCnFlag(AIBankUser) */
    private Boolean opnCnFlag;

    /** 通知信E-mail */
    private String email;

    /** 是否填過不喜歡的問卷 */
    private boolean hasSubmitDislikeOption;

    /** 是否已發查過數據中台userTags Y */
    private String userTagQuery;

    /** 個性標籤 (數據中台) */
    private List<String> pdrsonalityTag;

    /** 風險標籤 (數據中台) */
    private List<String> riskTag;

    /** 行動電話 */
    private String mobileNo;

    /** 生日 */
    private Date birthDay;

    /** 是否為行員 */
    private Boolean employee;

    /** 是否為「退休」行員 */
    private Boolean retiredEmployee;

    /** 「是否需提供滿意度問卷」註記 */
    private boolean isShowSatisfactionFlag;

    /** 裝置廠牌名稱 */
    private String marketingName;

    /** 顯示變更密碼提示 */
    private boolean isShowChangeTip;

    /** 貸款客群別 */
    private LoanCustomerGroupType loanGroupType = null;

    /** 外幣匯入款明細暫存 */
    private List<FxRemittanceDetailResponse> fxRemittanceDetailResponseList;

    /** 是否死亡戶 */
    private Boolean isDeceasedAccount;

    /** 是否單一戶 */
    private Boolean isSingleAccount;

    /** 貸款帳號 */
    private List<LoanAccount> loanAccount;
        
    /** 貸款牌卡貸款帳號 */
    private List<LoanAccount> loanAccountHomeCard;

	/** 貸款課群資料 */
    private OdsLoancustData odsLoancustData;

    /** 用戶簽訂基金電子契約狀態 */
    private Boolean fundEContractSigned;

    /** 客戶股票查詢(NKNE01)，W-8Ben及簽署起迄日期、風險預告書、首購狀態 */
    private NKNE01Res stockStatus;

    /** 客戶辦理投資有價證券資訊提供聲明書維護(EB032280) */
    private EB032280Res eb032280Res;

    /** 高資產客戶資訊查詢(CM061435CR) */
    private CM061435CRRes cm061435CRRes;

    /** (EB062171) 撥貸紀錄 */
    private List<EB062171ResRep> eb062171ResReps;

    /** 是否持有信託戶 */
    private Boolean hasTrustAcct;

    /**
     * 專業投資人註記 來源：由登入電文EB5556982取得INVEST_COD，EB5556982_Rs.INVEST_COD = Y
     */
    private Boolean professionalInvestor;

    /** 「是否看過約定報酬率自動贖回說明」註記 */
    private boolean isReadAutoRedemptionFlag;

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

    /**
     * EB_LOGIN_LOG_B PK
     */
    private Integer loginLogPk;

    /**
     * 目前裝置綁定資訊(原行銀)
     */
    private List<String> deviceBindingInfo;

    /** 目前裝置訂閱推播資訊(原行銀) */
    private List<String> deviceSubPushCode;

    /** 財管會員等級 */
    private FinancialMgmMemberLevel financialMgmMemberLevel;

    // ========================== 以下為金錢信託契約暫存 =================================

    /** 金錢信託契約清單 */
    private List<TrustContract> moneyTrustContractList;

    /** 金錢信託活存帳號即時餘額清單 */
    private Map<String, List<EB202674D003Res>> moneyTrustDeopsitListMap = new HashMap<>();

    /** 金錢信託定存帳號即時餘額 */
    private Map<String, BigDecimal> moneyTrustTermDeopsitBalanceMap = new HashMap<>();

    /** 金錢信託基金總值 */
    private Map<String, BigDecimal> moneyTrustFundBalanceMap = new HashMap<>();

    /** 金錢信託海外ETF總值 */
    private Map<String, BigDecimal> moneyTrustOverseaETFBalanceMap = new HashMap<>();

    /** 金錢信託海外債+結構型商品 */
    private Map<String, List<NJWEEN02ResRep>> njween02RepListMap = new HashMap<>();

    /** 金錢信託SI商品 SDACTQ12ResRep */
    private Map<String, List<SDACTQ12ResRep>> sdactq12ResRepsListMap = new HashMap<>();

    // ========================== 以上為金錢信託契約暫存 =================================
    /** 資產負債分析cache data (json string) */
    private String nboqu001CacheDataStr;

    /** 使用者投資類交易通知設定(TABLE:investmentNoticeSetting) */
    private InvestmentNoticeSetting investmentNoticeSetting;
    
    /**登入是否為無障礙(多元版) */
    private boolean accessibility;
    
    /**海外債專屬推薦債券*/
    private String recommendBondNo;

    /** 國家*/
    private String countryName;
    
    /**
     * @return the hasTrustAcct
     */
    public Boolean getHasTrustAcct() {
        return hasTrustAcct;
    }

    /**
     * @param hasTrustAcct
     *            the hasTrustAcct to set
     */
    public void setHasTrustAcct(Boolean hasTrustAcct) {
        this.hasTrustAcct = hasTrustAcct;
    }

    /**
     * the fundOverviews
     * 
     * @return
     */
    public List<FundOverview> getFundOverviews() {
        return fundOverviews;
    }

    /**
     * @param fundOverviews
     *            the fundOverviews to set
     */
    public void setFundOverviews(List<FundOverview> fundOverviews) {
        this.fundOverviews = fundOverviews;
    }

    /**
     * @return the odsLoancustData
     */
    public OdsLoancustData getOdsLoancustData() {
        return odsLoancustData;
    }

    /**
     * @param odsLoancustData
     *            the odsLoancustData to set
     */
    public void setOdsLoancustData(OdsLoancustData odsLoancustData) {
        this.odsLoancustData = odsLoancustData;
    }

    /**
     * @return the loanAccount
     */
    public List<LoanAccount> getLoanAccount() {
        return loanAccount;
    }

    /**
     * @param loanAccountHomeCard
     *            the loanAccountHomeCard to set
     */
    public void setLoanAccountHomeCard(List<LoanAccount> loanAccountHomeCard) {
        this.loanAccountHomeCard = loanAccountHomeCard;
    }
    

    /**
     * @return the loanAccountHomeCard
     */
    public List<LoanAccount> getLoanAccountHomeCard() {
        return loanAccountHomeCard;
    }

    /**
     * @param loanAccount
     *            the loanAccount to set
     */
    public void setLoanAccount(List<LoanAccount> loanAccount) {
        this.loanAccount = loanAccount;
    }

    /**
     * @return EB5556981 OTP_MOBILE
     */
    public String getOtpMobilePhone() {
        return loginMsgRs.getOtpMobile();
    }

    public AIBankUser(UserVo userData) {
        super(userData);
    }

    // public AIBankUser(UserInfo userData, String loginIp) {
    // super(userData);
    // this.loginIp = loginIp;
    // }

    /**
     * 是否有綁定裝置
     * 
     * @return
     */
    public boolean isDeviceBinded() {
        return StringUtils.equals(DEVICE_BINDED_STATUS, deviceBindingStatus);
    }

    /**
     * 當前設備是否為綁定裝置
     * 
     * @return
     */
    public boolean isLocalDevice() {
        return StringUtils.equals(AUTH_DEVICE_LOCAL, authDevice);
    }

    /**
     * 預設使用生物辨識
     * 
     * @return
     */
    public boolean isBioLogin() {
        return StringUtils.isY(bioLogin);
    }

    /**
     * 取得公司鍵值
     * 
     * @return
     */
    public Integer getCompanyKey() {
        return getCompanyVo().getCompanyKey();
    }

    /**
     * 取得使用者鍵值
     * 
     * @return
     */
    public Integer getUserKey() {
        return getUserData().getUserKey();
    }

    /**
     * 取得使用者代碼
     */
    @Override
    public String getUserId() {
        return getUserData().getUserUuid();
    }

    /**
     * 取得「使用者所屬用戶代碼」
     * 
     * @return
     */
    public String getNameCode() {
        return getUserData().getNameCode();
    }

    /**
     * 取得公司類型
     * 
     * @return
     */
    public int getCompanyKind() {
        return getCompanyVo().getCompanyKind();
    }

    public String getMassCheck() {
        if (null == loginMsgRs || StringUtils.isEmpty(StringUtils.trimToEmptyEx(StringUtils.defaultString(loginMsgRs.getMassChk())))) {
            return "MASS";
        }
        return loginMsgRs.getMassChk();
    }

    public String getMassCheckTrimmed() {
        return StringUtils.trimToEmptyEx(getMassCheck());
    }

    /**
     * 取得登入者身份別(如果有EB5556981Response)
     */
    public String getIdCheck() {
        return Optional.ofNullable(getLoginMsgRs()).map(EB5556981Response::getIdCheck).orElse("");
    }

    /**
     * 取得誤別碼
     * 
     * @return
     */
    public String getUidDup() {
        return StringUtils.defaultIfEmpty(getCompanyVo().getUidDup(), "0");
    }

    /**
     * 登入者是重號戶 UidDup != 0
     *
     * @return
     */
    public boolean uidDuplicatedUser() {
        return !"0".equals(getUidDup());
    }

    /**
     * 取用 身分證字號(10碼)＋誤別碼(1碼)
     * 
     * @return
     */
    public String getCustIdWithDup() {
        return StringUtils.rightPad(this.getCustId(), 10) + StringUtils.defaultIfEmpty(getUidDup(), "0");
    }

    // @formatter:off
    /**
     * 取用 身分證字號(10碼)＋ 檢查 誤別碼(1碼)，誤別碼有值且不為0時才帶入誤別碼
     * 
     * 若重覆碼為0, 則取前10位,否則回覆11位:
     * 'A1234567890' --> 'A123456789'
     * '87178818 0' --> '87178818'
     * 'A1234567891' --> 'A1234567891'
     * '87178818  1' --> '87178818  1'
     * 
     * @return
     */
    // @formatter:on
    public String getCustIdAndCheckDup() {
        return BaNCSUtil.getCustIdAndCheckDup(getCustId(), getUidDup());
    }

    /** 取得登入者的KYC 狀態Flag */
    public String getKycFlag() {
        return loginMsgRs.getEbillCheck();
    }

    /** 取得登入者的KYC有效日期 */
    public Date getKycEndDate() {
        if (null != getLoginMsgRs() && null != getLoginMsgRs().getEbillEndDate()) {
            return ConvertUtils.calendar2Date(getLoginMsgRs().getEbillEndDate());
        }
        return null;
    }

    /**
     * 2.6.13. 個人戶判斷 1. 判斷規則：判斷AI Bank User之COMPANY_UID長度 <= 8，表示非個人戶
     *
     * @return
     */
    public boolean isIndividualAccount() {
        return !(StringUtils.length(getCustId()) <= 8);
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * @return the motpFlag
     */
    public String getMotpFlag() {
        return motpFlag;
    }

    /**
     * @param motpFlag
     *            the motpFlag to set
     */
    public void setMotpFlag(String motpFlag) {
        this.motpFlag = motpFlag;
    }

    /**
     * @return the motpAuthVerifyType
     */
    public MotpAuthVerifyType getMotpAuthVerifyType() {
        return motpAuthVerifyType;
    }

    /**
     * @param motpAuthVerifyType
     *            the motpAuthVerifyType to set
     */
    public void setMotpAuthVerifyType(MotpAuthVerifyType motpAuthVerifyType) {
        this.motpAuthVerifyType = motpAuthVerifyType;
    }

    /**
     * @return the canUpdateCheckFlag
     */
    public boolean isCanUpdateCheckFlag() {
        return canUpdateCheckFlag;
    }

    /**
     * @param canUpdateCheckFlag
     *            the canUpdateCheckFlag to set
     */
    public void setCanUpdateCheckFlag(boolean canUpdateCheckFlag) {
        this.canUpdateCheckFlag = canUpdateCheckFlag;
    }

    /**
     * @return the canUpdateOtp
     */
    public boolean isCanUpdateOtp() {
        return canUpdateOtp;
    }

    /**
     * @param canUpdateOtp
     *            the canUpdateOtp to set
     */
    public void setCanUpdateOtp(boolean canUpdateOtp) {
        this.canUpdateOtp = canUpdateOtp;
    }

    /**
     * @return the canUpdateMotp
     */
    public boolean isCanUpdateMotp() {
        return canUpdateMotp;
    }

    /**
     * @param canUpdateMotp
     *            the canUpdateMotp to set
     */
    public void setCanUpdateMotp(boolean canUpdateMotp) {
        this.canUpdateMotp = canUpdateMotp;
    }

    /**
     * @return the errorOtp
     */
    public ErrorDescription getErrorOtp() {
        return errorOtp;
    }

    /**
     * @param errorOtp
     *            the errorOtp to set
     */
    public void setErrorOtp(ErrorDescription errorOtp) {
        this.errorOtp = errorOtp;
    }

    /**
     * @return the errorMotp
     */
    public ErrorDescription getErrorMotp() {
        return errorMotp;
    }

    /**
     * @param errorMotp
     *            the errorMotp to set
     */
    public void setErrorMotp(ErrorDescription errorMotp) {
        this.errorMotp = errorMotp;
    }

    /**
     * @return the loginMethod
     */
    public String getLoginMethod() {
        return loginMethod;
    }

    /**
     * @param loginMethod
     *            the loginMethod to set
     */
    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    /**
     * @return the bioBlackList
     */
    public String getBioBlackList() {
        return bioBlackList;
    }

    /**
     * @param bioBlackList
     *            the bioBlackList to set
     */
    public void setBioBlackList(String bioBlackList) {
        this.bioBlackList = bioBlackList;
    }

    /**
     * @return the authDevice
     */
    public String getAuthDevice() {
        return authDevice;
    }

    /**
     * @param authDevice
     *            the authDevice to set
     */
    public void setAuthDevice(String authDevice) {
        this.authDevice = authDevice;
    }

    public String getDeviceBindingStatus() {
        return deviceBindingStatus;
    }

    public void setDeviceBindingStatus(String deviceBindingStatus) {
        this.deviceBindingStatus = deviceBindingStatus;
    }

    public String getBioLogin() {
        return bioLogin;
    }

    public void setBioLogin(String bioLogin) {
        this.bioLogin = bioLogin;
    }

    // /**
    // * D. 獨資戶身分判斷：登入暫存login_Rs.UserInfo.custType=12 & login_Rs.UserInfo.custID<>NULL。
    // *
    // * @return
    // */
    // public boolean hasPersonalAccount() {
    // UserInfo userData = getUserData();
    // return StringUtils.equals(userData.getCustType(), "12") && StringUtils.isNotBlank(userData.getCustID());
    // }
    //
    // /**
    // * MSB名單客戶判斷：登入暫存msbFlag=Y
    // *
    // * @return
    // */
    // public boolean isInMSBList() {
    // return StringUtils.isY(getUserData().getMsbFlag());
    // }

    /**
     * @return the insightId2
     */
    public String getInsightId2() {
        return insightId2;
    }

    /**
     * @param insightId2
     *            the insightId2 to set
     */
    public void setInsightId2(String insightId2) {
        this.insightId2 = insightId2;
    }

    /**
     * @return the insightId3
     */
    public String getInsightId3() {
        return insightId3;
    }

    /**
     * @param insightId3
     *            the insightId3 to set
     */
    public void setInsightId3(String insightId3) {
        this.insightId3 = insightId3;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<UsualTask> getUsualTasks() {
        return this.usualTasks;
    }

    public void setUsualTasks(List<UsualTask> usualTasks) {
        this.usualTasks = usualTasks;
    }

    /**
     * @return the otpFlagFromEB552170
     */
    public String getOtpFlagFromEB552170() {
        return otpFlagFromEB552170;
    }

    /**
     * @param otpFlagFromEB552170
     *            the otpFlagFromEB552170 to set
     */
    public void setOtpFlagFromEB552170(String otpFlagFromEB552170) {
        this.otpFlagFromEB552170 = otpFlagFromEB552170;
    }

    /**
     * @return the otpMobileFromEB552170
     */
    public String getOtpMobileFromEB552170() {
        return otpMobileFromEB552170;
    }

    /**
     * @param otpMobileFromEB552170
     *            the otpMobileFromEB552170 to set
     */
    public void setOtpMobileFromEB552170(String otpMobileFromEB552170) {
        this.otpMobileFromEB552170 = otpMobileFromEB552170;
    }

    /**
     * @return the otpMobileEmpFromEB552170
     */
    public String getOtpMobileEmpFromEB552170() {
        return otpMobileEmpFromEB552170;
    }

    /**
     * @param otpMobileEmpFromEB552170
     *            the otpMobileEmpFromEB552170 to set
     */
    public void setOtpMobileEmpFromEB552170(String otpMobileEmpFromEB552170) {
        this.otpMobileEmpFromEB552170 = otpMobileEmpFromEB552170;
    }

    /**
     * @return the otpMobileFromCEW013R
     */
    public String getOtpMobileFromCEW013R() {
        return otpMobileFromCEW013R;
    }

    /**
     * @param otpMobileFromCEW013R
     *            the otpMobileFromCEW013R to set
     */
    public void setOtpMobileFromCEW013R(String otpMobileFromCEW013R) {
        this.otpMobileFromCEW013R = otpMobileFromCEW013R;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    /**
     * @return the companyKindType
     */
    public CompanyKindType getCompanyKindType() {
        return companyKindType;
    }

    /**
     * @param companyKindType
     *            the companyKindType to set
     */
    public void setCompanyKindType(CompanyKindType companyKindType) {
        this.companyKindType = companyKindType;
    }

    public Boolean isHaveCreditCard() {
        return haveCreditCard;
    }

    public void setHaveCreditCard(Boolean haveCreditCard) {
        this.haveCreditCard = haveCreditCard;
    }

    public Boolean isSpecialCreditCard() {
        return specialCreditCard;
    }

    public void setSpecialCreditCard(Boolean specialCreditCard) {
        this.specialCreditCard = specialCreditCard;
    }

    public CreditCardIdType getCreditCardIdType() {
        return creditCardIdType;
    }

    public void setCreditCardIdType(CreditCardIdType creditCardIdType) {
        this.creditCardIdType = creditCardIdType;
    }

    public List<CreditCard> getAllCreditCards() {
        return allCreditCards;
    }

    public void setAllCreditCards(List<CreditCard> allCreditCards) {
        this.allCreditCards = allCreditCards;
    }

    public List<CreditCard> getEffectiveCreditCards() {
        return effectiveCreditCards;
    }

    public void setEffectiveCreditCards(List<CreditCard> effectiveCreditCards) {
        this.effectiveCreditCards = effectiveCreditCards;
    }
    
    public CEW013RRes getCew013RRes() {
        return cew013RRes;
    }
    
    public void setCEW013RRes(CEW013RRes cew013RRes) {
        this.cew013RRes = cew013RRes;
    }

    /**
     * @return the companyEntity
     */
    public CompanyVo getCompanyVo() {
        return companyVo;
    }

    /**
     * @param companyEntity
     *            the companyEntity to set
     */
    public void setCompanyVo(CompanyVo companyEntity) {
        this.companyVo = companyEntity;
    }

    /**
     * @return the cardUserProfileEntity
     */
    public CardUserProfileVo getCardUserProfileVo() {
        return cardUserProfileVo;
    }

    /**
     * @param cardUserProfileVo
     *            the cardUserProfileEntity to set
     */
    public void setCardUserProfileVo(CardUserProfileVo cardUserProfileVo) {
        this.cardUserProfileVo = cardUserProfileVo;
    }

    /**
     * @return the mbDeviceInfoEntity
     */
    public MbDeviceInfoVo getMbDeviceInfoVo() {
        return mbDeviceInfoVo;
    }

    /**
     * @param mbDeviceInfoVo
     *            the mbDeviceInfoEntity to set
     */
    public void setMbDeviceInfoVo(MbDeviceInfoVo mbDeviceInfoVo) {
        this.mbDeviceInfoVo = mbDeviceInfoVo;
    }

    /**
     * @return the userLoginEntity
     */
    public UserLoginVo getUserLoginVo() {
        return userLoginVo;
    }

    /**
     * @param userLoginVo
     *            the userLoginEntity to set
     */
    public void setUserLoginVo(UserLoginVo userLoginVo) {
        this.userLoginVo = userLoginVo;
    }

    public void setLoginMsgRs(EB5556981Response loginMsgRs) {
        this.loginMsgRs = loginMsgRs;
    }

    public EB5556981Response getLoginMsgRs() {
        return loginMsgRs;
    }

    public String getCustName() {
        return this.getLoginMsgRs().getCustName();
    }

    public Map<TransOutAcctType, List<TransOutAccount>> getTransOutAccountsMap() {
        return transOutAccountsMap;
    }

    public void clearTransOutAccountsMap() {
        this.transOutAccountsMap.clear();
    }

    /**
     * @return the agreedInAccountsMap
     */
    public Map<String, List<AgreedInAccount>> getAgreedInAccountsMap() {
        return agreedInAccountsMap;
    }

    /**
     * @return the fxAgreedInAccountsMap
     */
    public Map<String, List<AgreedInAccount>> getFxAgreedInAccountsMap() {
        return fxAgreedInAccountsMap;
    }

    /**
     * @return the favoriteAccountsMap
     */
    public Map<String, List<FavoriteAccount>> getFavoriteAccountsMap() {
        return favoriteAccountsMap;
    }

    public Map<String, List<AgreedInAccount>> getFxexInAccountsMap() {
        return fxexInAccountsMap;
    }

    /**
     * 清空約定轉入帳號暫存
     */
    public void clearAgreedInAccountsMap() {
        this.agreedInAccountsMap.clear();
    }

    /**
     * 清空外幣約定轉入帳號暫存
     */
    public void clearFxAgreedInAccountsMap() {
        this.fxAgreedInAccountsMap.clear();
    }

    /**
     * 清空換匯約定轉入帳號暫存
     */
    public void clearFxExAgreedInAccountsMap() {
        this.fxexInAccountsMap.clear();
    }

    /**
     * 清空常用轉入帳號暫存
     */
    public void clearFavoriteAccountsMap() {
        this.favoriteAccountsMap.clear();
    }

    /**
     * @return the opnCnFlag
     */
    public Boolean getOpnCnFlag() {
        return opnCnFlag;
    }

    /**
     * @param opnCnFlag
     *            the opnCnFlag to set
     */
    public void setOpnCnFlag(Boolean opnCnFlag) {
        this.opnCnFlag = opnCnFlag;
    }

    /**
     * @return the email
     */

    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the hasSubmitDislikeOption
     */
    public boolean isHasSubmitDislikeOption() {
        return hasSubmitDislikeOption;
    }

    /**
     * @param hasSubmitDislikeOption
     *            the hasSubmitDislikeOption to set
     */
    public void setHasSubmitDislikeOption(boolean hasSubmitDislikeOption) {
        this.hasSubmitDislikeOption = hasSubmitDislikeOption;
    }

    /**
     * @return the userTagQuery
     */
    public String getUserTagQuery() {
        return userTagQuery;
    }

    /**
     * @param userTagQuery
     *            the userTagQuery to set
     */
    public void setUserTagQuery(String userTagQuery) {
        this.userTagQuery = userTagQuery;
    }

    /**
     * @return the pdrsonalityTag
     */
    public List<String> getPdrsonalityTag() {
        return pdrsonalityTag;
    }

    /**
     * @param pdrsonalityTag
     *            the pdrsonalityTag to set
     */
    public void setPdrsonalityTag(List<String> pdrsonalityTag) {
        this.pdrsonalityTag = pdrsonalityTag;
    }

    /**
     * @return the riskTag
     */
    public List<String> getRiskTag() {
        return riskTag;
    }

    /**
     * @param riskTag
     *            the riskTag to set
     */
    public void setRiskTag(List<String> riskTag) {
        this.riskTag = riskTag;
    }

    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo
     *            the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the birthDay
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * 登入者年紀超過65歲？
     */
    public boolean ageOver65() {
        if (Objects.nonNull(getBirthDay())) {
            return DateUtils.calculateAge(getBirthDay()) > 65;
        }
        return false;
    }

    /**
     * 登入者年紀未滿18歲？
     */
    public boolean ageUnder18() {
        if (Objects.nonNull(getBirthDay())) {
            return DateUtils.calculateAge(getBirthDay()) < 18;
        }
        return false;
    }

    /**
     * @param birthDay
     *            the birthDay to set
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Boolean isEmployee() {
        return employee;
    }

    public void setEmployee(Boolean employee) {
        this.employee = employee;
    }

    public Boolean isRetiredEmployee() {
        return retiredEmployee;
    }

    public void setRetiredEmployee(Boolean retiredEmployee) {
        this.retiredEmployee = retiredEmployee;
    }

    /**
     * 在此 override 權限檢查 function, 以配合 TFB Business Model
     * 
     * @param taskId
     * @return
     */
    public TaskPermissionType getTaskPermission(String taskId, Task task) {

        // 查無交易代號
        if (null == task) {
            return TaskPermissionType.NONE;
        }

        // 檢查不控管的交易權限
        if (task.getAccessControlFlag() <= 0) {
            return TaskPermissionType.NOT_CONTROL;
        }

        // 使用者擁有此新交易代號的執行權限
        if (getAllTaskIds().contains(taskId.toLowerCase())) {
            return TaskPermissionType.ROLE;
        }

        // 無交易權限
        return TaskPermissionType.NONE;
    }

    /**
     * 取得角色模版型別
     * 
     * @return
     */
    public RoleTemplateType getRoleTemplateType() {
        RoleTemplateType roleTemplateType = null;
        CompanyKindType companyKindType = getCompanyKindType();
        // 公司用戶使用個人用戶角色
        if (companyKindType.isCompany() || companyKindType.isPersonal()) {
            roleTemplateType = RoleTemplateType.AI_PERSON_ROLE_TEMPLATE;
        }
        else if (companyKindType.isPrimary() || companyKindType.isSecondary()) {
            roleTemplateType = RoleTemplateType.AI_CARD_MEMBER_ROLE_TEMPLATE;
        }
        return roleTemplateType;
    }

    /**
     * 一般會員，取得變更密碼日期
     * 
     * @return
     */
    public Date getPwChgDate() {
        return ConvertUtils.calendar2Date(loginMsgRs.getPwChgDate());
    }

    /**
     * 取得國籍代碼 由「EB5556981Response」.NATIONAL取得代碼值 如果是台灣會是「TW」
     * 
     * @return
     */
    public String getNational() {
        return Optional.ofNullable(getLoginMsgRs()).map(EB5556981Response::getNational).orElse("");
    }

    /**
     * @return the isInAccountCreditcardCheck
     */
    public boolean isInAccountCreditcardCheck() {
        return isInAccountCreditcardCheck;
    }

    /**
     * @param isInAccountCreditcardCheck
     *            the isInAccountCreditcardCheck to set
     */
    public void setInAccountCreditcardCheck(boolean isInAccountCreditcardCheck) {
        this.isInAccountCreditcardCheck = isInAccountCreditcardCheck;
    }

    /**
     * @return the isSameBirthday
     */
    public boolean isSameBirthday() {
        return isSameBirthday;
    }

    /**
     * @param isSameBirthday
     *            the isSameBirthday to set
     */
    public void setSameBirthday(boolean isSameBirthday) {
        this.isSameBirthday = isSameBirthday;
    }

    public boolean isShowSatisfactionFlag() {
        return isShowSatisfactionFlag;
    }

    public void setShowSatisfactionFlag(boolean showSatisfactionFlag) {
        this.isShowSatisfactionFlag = showSatisfactionFlag;
    }

    /**
     * @return the marketingName
     */
    public String getMarketingName() {
        return marketingName;
    }

    /**
     * @param marketingName
     *            the marketingName to set
     */
    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    /**
     * @return the loanGroupType
     */
    public LoanCustomerGroupType getLoanGroupType() {
        return loanGroupType;
    }

    /**
     * @param loanGroupType
     *            the loanGroupType to set
     */
    public void setLoanGroupType(LoanCustomerGroupType loanGroupType) {
        this.loanGroupType = loanGroupType;
    }

    /**
     * @return the fxRemittanceDetailResponseList
     */
    public List<FxRemittanceDetailResponse> getFxRemittanceDetailResponseList() {
        return fxRemittanceDetailResponseList;
    }

    /**
     * @param fxRemittanceDetailResponseList
     *            the fxRemittanceDetailResponseList to set
     */
    public void setFxRemittanceDetailResponseList(List<FxRemittanceDetailResponse> fxRemittanceDetailResponseList) {
        this.fxRemittanceDetailResponseList = fxRemittanceDetailResponseList;
    }

    /**
     * @return the isDeceasedAccount
     */
    public Boolean getIsDeceasedAccount() {
        return isDeceasedAccount;
    }

    /**
     * @param isDeceasedAccount
     *            the isDeceasedAccount to set
     */
    public void setIsDeceasedAccount(Boolean isDeceasedAccount) {
        this.isDeceasedAccount = isDeceasedAccount;
    }

    /**
     * @return the isSingleAccount
     */
    public Boolean getIsSingleAccount() {
        return isSingleAccount;
    }

    /**
     * @param isSingleAccount
     *            the isSingleAccount to set
     */
    public void setIsSingleAccount(Boolean isSingleAccount) {
        this.isSingleAccount = isSingleAccount;
    }

    /**
     * @return the ebhn002Response
     */
    public EBHN002Response getEbhn002Response() {
        return ebhn002Response;
    }

    /**
     * @param ebhn002Response
     *            the ebhn002Response to set
     */
    public void setEbhn002Response(EBHN002Response ebhn002Response) {
        this.ebhn002Response = ebhn002Response;
    }

    /**
     * @return the ebln010Response
     */
    public EBLN010Response getEbln010Response() {
        return ebln010Response;
    }

    /**
     * @param ebln010Response
     *            the ebln010Response to set
     */
    public void setEbln010Response(EBLN010Response ebln010Response) {
        this.ebln010Response = ebln010Response;
    }

    public CompanyBUType getBuType() {
        return buType;
    }

    public void setBuType(CompanyBUType buType) {
        this.buType = buType;
    }

    public NKNE01Res getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(NKNE01Res stockStatus) {
        this.stockStatus = stockStatus;
    }

    public EB032280Res getEb032280Res() {
        return eb032280Res;
    }

    public void setEb032280Res(EB032280Res eb032280Res) {
        this.eb032280Res = eb032280Res;
    }

    public EB032675Res getEb032675Res() {
        return eb032675Res;
    }

    public void setEb032675Res(EB032675Res eb032675Res) {
        this.eb032675Res = eb032675Res;
    }

    public CM061435CRRes getCm061435CRRes() {
        return cm061435CRRes;
    }

    public void setCm061435CRRes(CM061435CRRes cm061435CRRes) {
        this.cm061435CRRes = cm061435CRRes;
    }

    public Boolean getFundEContractSigned() {
        return fundEContractSigned;
    }

    public void setFundEContractSigned(Boolean fundEContractSigned) {
        this.fundEContractSigned = fundEContractSigned;
    }

    public List<EB062171ResRep> getEb062171ResReps() {
        return eb062171ResReps;
    }

    /**
     * @param eb062171ResReps
     */
    public void setEb062171ResReps(List<EB062171ResRep> eb062171ResReps) {
        this.eb062171ResReps = eb062171ResReps;
    }

    /**
     * @return the trustAcctsMap
     */
    public Map<String, Map<String, List<TrustAccount>>> getTrustAcctsMap() {
        return trustAcctsMap;
    }

    /**
     * @param trustAcctsMap
     *            the trustAcctsMap to set
     */
    public void setTrustAcctsMap(Map<String, Map<String, List<TrustAccount>>> trustAcctsMap) {
        this.trustAcctsMap = trustAcctsMap;
    }

    public Boolean getProfessionalInvestor() {
        return professionalInvestor;
    }

    public void setProfessionalInvestor(Boolean professionalInvestor) {
        this.professionalInvestor = professionalInvestor;
    }

    public KycAnswerResponse getKycAnswerResponse() {
        return kycAnswerResponse;
    }

    public void setKycAnswerResponse(KycAnswerResponse kycAnswerResponse) {
        this.kycAnswerResponse = kycAnswerResponse;
    }

    public List<PeopleSoftRes> getPeopleSoftResList(Locale userLocale) {
        return this.peopleSoftResMap.get(userLocale);
    }

    public void setPeopleSoftResList(Locale userLocale, List<PeopleSoftRes> peopleSoftResList) {
        this.peopleSoftResMap.put(userLocale, peopleSoftResList);
    }

    public void clearPeopleSoftResMap() {
        this.peopleSoftResMap.clear();
    }

    public EB032282Res getEb032282Res() {
        return eb032282Res;
    }

    public void setEb032282Res(EB032282Res eb032282Res) {
        this.eb032282Res = eb032282Res;
    }

    /**
     * @return the isReadAutoRedemptionFlag
     */
    public boolean isReadAutoRedemptionFlag() {
        return isReadAutoRedemptionFlag;
    }

    /**
     * @param isReadAutoRedemptionFlag
     *            the isReadAutoRedemptionFlag to set
     */
    public void setReadAutoRedemptionFlag(boolean isReadAutoRedemptionFlag) {
        this.isReadAutoRedemptionFlag = isReadAutoRedemptionFlag;
    }

    /**
     * @return the onboardingType
     */
    public int getOnboardingType() {
        return onboardingType;
    }

    /**
     * @param onboardingType
     *            the onboardingType to set
     */
    public void setOnboardingType(int onboardingType) {
        this.onboardingType = onboardingType;
    }

    /**
     * @return the deviceBindingInfo
     */
    public List<String> getDeviceBindingInfo() {
        return deviceBindingInfo;
    }

    /**
     * @param deviceBindingInfo
     *            the deviceBindingInfo to set
     */
    public void setDeviceBindingInfo(List<String> deviceBindingInfo) {
        this.deviceBindingInfo = deviceBindingInfo;
    }

    /**
     * @return the deviceSubPushCode
     */
    public List<String> getDeviceSubPushCode() {
        return deviceSubPushCode;
    }

    /**
     * @param deviceSubPushCode
     *            the deviceSubPushCode to set
     */
    public void setDeviceSubPushCode(List<String> deviceSubPushCode) {
        this.deviceSubPushCode = deviceSubPushCode;
    }

    public FinancialMgmMemberLevel getFinancialMgmMemberLevel() {
        return financialMgmMemberLevel;
    }

    public void setFinancialMgmMemberLevel(FinancialMgmMemberLevel financialMgmMemberLevel) {
        this.financialMgmMemberLevel = financialMgmMemberLevel;
    }

    /**
     * @return the moneyTrustContract
     */
    public List<TrustContract> getMoneyTrustContractList() {
        return moneyTrustContractList;
    }

    /**
     * @param moneyTrustContract
     *            the moneyTrustContract to set
     */
    public void setMoneyTrustContractList(List<TrustContract> moneyTrustContract) {
        this.moneyTrustContractList = moneyTrustContract;
    }

    /**
     * @return the nboqu001CacheDataStr
     */
    public String getNboqu001CacheDataStr() {
        return nboqu001CacheDataStr;
    }

    /**
     * @param nboqu001CacheDataStr
     *            the nboqu001CacheDataStr to set
     */
    public void setNboqu001CacheDataStr(String nboqu001CacheDataStr) {
        this.nboqu001CacheDataStr = nboqu001CacheDataStr;
    }

    /**
     * @return the loginLogPk
     */
    public Integer getLoginLogPk() {
        return loginLogPk;
    }

    /**
     * @param loginLogPk
     *            the loginLogPk to set
     */
    public void setLoginLogPk(Integer loginLogPk) {
        this.loginLogPk = loginLogPk;
    }

    public Map<String, List<EB202674D003Res>> getMoneyTrustDeopsitListMap() {
        return moneyTrustDeopsitListMap;
    }

    public Map<String, BigDecimal> getMoneyTrustTermDeopsitBalanceMap() {
        return moneyTrustTermDeopsitBalanceMap;
    }

    public Map<String, BigDecimal> getMoneyTrustFundBalanceMap() {
        return moneyTrustFundBalanceMap;
    }

    public Map<String, BigDecimal> getMoneyTrustOverseaETFBalanceMap() {
        return moneyTrustOverseaETFBalanceMap;
    }

    public Map<String, List<NJWEEN02ResRep>> getNjween02RepListMap() {
        return njween02RepListMap;
    }

    public Map<String, List<SDACTQ12ResRep>> getSdactq12ResRepsListMap() {
        return sdactq12ResRepsListMap;
    }

    public InvestmentNoticeSetting getInvestmentNoticeSetting() {
        return investmentNoticeSetting;
    }

    public void setInvestmentNoticeSetting(InvestmentNoticeSetting investmentNoticeSetting) {
        this.investmentNoticeSetting = investmentNoticeSetting;
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
     * 
     * @return
     */
    public boolean isAccessibility() {
        return accessibility;
    }
    /**
     * 
     * @param accessibility
     */
    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    public EB67115Res getEb67115Res() {
		return eb67115Res;
	}

	public void setEb67115Res(EB67115Res eb67115Res) {
		this.eb67115Res = eb67115Res;
	}

    public String getRecommendBondNo() {
        return recommendBondNo;
    }

    public void setRecommendBondNo(String recommendBondNo) {
        this.recommendBondNo = recommendBondNo;
    }
	
    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
	
}
