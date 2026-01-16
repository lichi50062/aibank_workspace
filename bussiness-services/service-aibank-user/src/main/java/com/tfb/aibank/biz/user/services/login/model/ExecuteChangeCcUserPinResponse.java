package com.tfb.aibank.biz.user.services.login.model;

import java.util.Date;

import com.ibm.tw.commons.error.ErrorStatus;
import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;
import com.tfb.aibank.chl.session.vo.UserLoginVo;
import com.tfb.aibank.chl.session.vo.UserVo;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ExecuteUserLoginResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "ExecuteChangeCcUserPinResponse")
public class ExecuteChangeCcUserPinResponse {

    /** 登入異常代碼 */
    @Schema(description = "錯誤碼")
    private String errorCode;

    /** 錯誤描述 */
    @Schema(description = "錯誤描述")
    private String systemId;

    /** 錯誤系統別 */
    @Schema(description = "錯誤系統別")
    private String errorDesc;

    @Schema(description = "User_Profile_Entity")
    private UserVo userVo;

    /** Company_Entity */
    @Schema(description = "Company_Entity")
    private CompanyVo companyVo;

    @Schema(description = "Card_User_Profile_Entity")
    private CardUserProfileVo cardUserProfileVo;

    @Schema(description = "MB_Device_Info_Entity")
    private MbDeviceInfoVo mbDeviceInfoVo;

    @Schema(description = "User_Log_Entity")
    private UserLoginVo userLoginVo;

    @Schema(description = "Name Code")
    private String nameCode;

    private String eUid;

    /** 登入狀態 */
    @Schema(description = "登入狀態")
    private int loginStatus;


    /** 重複登入 平台 */
    @Schema(description = "重複登入平台")
    private String channel;

    /** 前次登入時間 */
    @Schema(description = "前次登入時間")
    private Date preLoginDate;

    // 登入成功狀態
    @Schema(description = "登入狀態")
    private boolean isSuccess;

    // 存戶 / 卡戶
    @Schema(description = "存戶/卡戶")
    private boolean isBankUser;

    // 多使用者代碼客戶判斷
    @Schema(description = "多使用者代碼客戶判斷")
    private boolean isMultiUser;


    @Schema(description = "USER_KEY")
    private Integer userKey;

    @Schema(description = "COMPANY_KEY")
    private Integer companyKey;


    @Schema(description = "錯誤")
    private ErrorStatus errorStatus;



    /**
     * @return the preLoginDate
     */
    public Date getPreLoginDate() {
        return preLoginDate;
    }

    /**
     * @param preLoginDate
     *            the preLoginDate to set
     */
    public void setPreLoginDate(Date preLoginDate) {
        this.preLoginDate = preLoginDate;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public ExecuteChangeCcUserPinResponse() {
        this.companyVo = new CompanyVo();
        this.userVo = new UserVo();

    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId
     *            the systemId to set
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * @return the errorDesc
     */
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     * @param errorDesc
     *            the errorDesc to set
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    /**
     * @return the userEntityVo
     */
    public UserVo getUserVo() {
        if (userVo == null) {
            userVo = new UserVo();
        }
        return userVo;
    }

    /**
     * @param userEntityVo
     *            the userEntityVo to set
     */
    public void setUserVo(UserVo userEntityVo) {
        this.userVo = userEntityVo;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * @return the eUid
     */
    public String geteUid() {
        return eUid;
    }

    /**
     * @param eUid
     *            the eUid to set
     */
    public void seteUid(String eUid) {
        this.eUid = eUid;
    }

    /**
     * @return the loginStatus
     */
    public int getLoginStatus() {
        return loginStatus;
    }

    /**
     * @param loginStatus
     *            the loginStatus to set
     */
    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    /**
     * @return the companyEntityVo
     */
    public CompanyVo getCompanyVo() {
        if (companyVo == null) {
            companyVo = new CompanyVo();
        }
        return companyVo;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel
     *            the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * @return the isBankUser
     */
    public boolean isBankUser() {
        return isBankUser;
    }

    /**
     * @param isBankUser
     *            the isBankUser to set
     */
    public void setBankUser(boolean isBankUser) {
        this.isBankUser = isBankUser;
    }

    /**
     * @return the isMultiUser
     */
    public boolean isMultiUser() {
        return isMultiUser;
    }

    /**
     * @param isMultiUser
     *            the isMultiUser to set
     */
    public void setMultiUser(boolean isMultiUser) {
        this.isMultiUser = isMultiUser;
    }

    /**
     * @return the cardUserProfileEntity
     */
    public CardUserProfileVo getCardUserProfileVo() {
        if (cardUserProfileVo == null) {
            cardUserProfileVo = new CardUserProfileVo();
        }
        return cardUserProfileVo;
    }

    /**
     * @param cardUserProfileEntity
     *            the cardUserProfileEntity to set
     */
    public void setCardUserProfileVo(CardUserProfileVo cardUserProfileVo) {
        this.cardUserProfileVo = cardUserProfileVo;
    }

    /**
     * @return the mbDeviceInfoEntity
     */
    public MbDeviceInfoVo getMbDeviceInfoVo() {
        if (mbDeviceInfoVo == null) {
            mbDeviceInfoVo = new MbDeviceInfoVo();
        }
        return mbDeviceInfoVo;
    }

    /**
     * @param mbDeviceInfoEntity
     *            the mbDeviceInfoEntity to set
     */
    public void setMbDeviceInfoVo(MbDeviceInfoVo mbDeviceInfoVo) {
        this.mbDeviceInfoVo = mbDeviceInfoVo;
    }

    /**
     * @return the userLoginEntity
     */
    public UserLoginVo getUserLoginVo() {
        if (userLoginVo == null) {
            userLoginVo = new UserLoginVo();
        }
        return userLoginVo;
    }

    /**
     * @param userLoginEntity
     *            the userLoginEntity to set
     */
    public void setUserLoginVo(UserLoginVo userLoginVo) {
        this.userLoginVo = userLoginVo;
    }

	/**
	 * @return the errorStatus
	 */
	public ErrorStatus getErrorStatus() {
		return errorStatus;
	}

	/**
	 * @param errorStatus the errorStatus to set
	 */
	public void setErrorStatus(ErrorStatus errorStatus) {
		this.errorStatus = errorStatus;
	}

}
