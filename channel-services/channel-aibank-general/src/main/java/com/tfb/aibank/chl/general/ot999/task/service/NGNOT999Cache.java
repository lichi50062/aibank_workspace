/**
 * 
 */
package com.tfb.aibank.chl.general.ot999.task.service;

import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.component.security.sso.SsoLoginAuthStep;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999010Rq;
import com.tfb.aibank.chl.general.resource.dto.GetFastValidateUserResponse;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.type.TxSecurityStepType;

//@formatter:off
/**
* @(#)NGNOT999Cache.java 
* 
* <p>Description:快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
public class NGNOT999Cache {

    private RetriveDeviceStatusResponse retriveDeviceStatusResponse;

    private NGNOT999010Rq rqData;

    private GetFastValidateUserResponse getFastValidateUserResponse;

    private String mobileNo;
    
    private String userId;
    
    private String custId;
        
    private String userIdDup;
    
    private Integer companyKind;
    
    private OtpAuthKeepData keepData;

    /** 平台名稱 */
    private String channelName;

    /** TOKEN */
    private String token;

    private TxSecurityStepType txSecurityStepType = TxSecurityStepType.UNKNOWN;
    
    /** 一般用戶 m1 / 信用卡 m2*/
    private String loginType;
    
    private SsoLoginAuthStep ssoAuthStep ;
    
    /**
     * 時間因子 有效時間(秒)
     */
    private int timeFactorValidSeconds;

    /**
     * E2EE給前端PWD加密時，是否帶入之時間因子
     */
    private boolean isPwdNeedWithTime;
    
    
    private String uidUuidWithTime;
    
    /**
     * E2EE給前端uid/uuid加密時，是否帶入之時間因子
     */
    private boolean isUidUuidNeedWithTime;

    /**失敗的次數*/
    private int failedCount;
    
    /**
     * @return the retriveDeviceStatusResponse
     */
    public RetriveDeviceStatusResponse getRetriveDeviceStatusResponse() {
        return retriveDeviceStatusResponse;
    }

    /**
     * @param retriveDeviceStatusResponse
     *            the retriveDeviceStatusResponse to set
     */
    public void setRetriveDeviceStatusResponse(RetriveDeviceStatusResponse retriveDeviceStatusResponse) {
        this.retriveDeviceStatusResponse = retriveDeviceStatusResponse;
    }

    /**
     * @return the rqData
     */
    public NGNOT999010Rq getRqData() {
        return rqData;
    }

    /**
     * @param rqData
     *            the rqData to set
     */
    public void setRqData(NGNOT999010Rq rqData) {
        this.rqData = rqData;
    }

    /**
     * @return the getFastValidateUserResponse
     */
    public GetFastValidateUserResponse getGetFastValidateUserResponse() {
        return getFastValidateUserResponse;
    }

    /**
     * @param getFastValidateUserResponse
     *            the getFastValidateUserResponse to set
     */
    public void setGetFastValidateUserResponse(GetFastValidateUserResponse getFastValidateUserResponse) {
        this.getFastValidateUserResponse = getFastValidateUserResponse;
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
     * @return the channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * @param channelName
     *            the channelName to set
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * @return the keepData
     */
    public OtpAuthKeepData getKeepData() {
        return keepData;
    }

    /**
     * @param keepData
     *            the keepData to set
     */
    public void setKeepData(OtpAuthKeepData keepData) {
        this.keepData = keepData;
    }

    /**
     * @return the txSecurityStepType
     */
    public TxSecurityStepType getTxSecurityStepType() {
        return txSecurityStepType;
    }

    /**
     * @param txSecurityStepType
     *            the txSecurityStepType to set
     */
    public void setTxSecurityStepType(TxSecurityStepType txSecurityStepType) {
        this.txSecurityStepType = txSecurityStepType;
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
     * 
     * @return
     */
    public SsoLoginAuthStep getSsoAuthStep() {
        return ssoAuthStep;
    }
    /**
     * 
     * @param ssoAuthStep
     */
    public void setSsoAuthStep(SsoLoginAuthStep ssoAuthStep) {
        this.ssoAuthStep = ssoAuthStep;
    }

    public int getTimeFactorValidSeconds() {
        return timeFactorValidSeconds;
    }

    public void setTimeFactorValidSeconds(int timeFactorValidSeconds) {
        this.timeFactorValidSeconds = timeFactorValidSeconds;
    }

    public boolean isPwdNeedWithTime() {
        return isPwdNeedWithTime;
    }

    public void setPwdNeedWithTime(boolean isPwdNeedWithTime) {
        this.isPwdNeedWithTime = isPwdNeedWithTime;
    }

    public String getUidUuidWithTime() {
        return uidUuidWithTime;
    }

    public void setUidUuidWithTime(String uidUuidWithTime) {
        this.uidUuidWithTime = uidUuidWithTime;
    }

    public boolean isUidUuidNeedWithTime() {
        return isUidUuidNeedWithTime;
    }

    public void setUidUuidNeedWithTime(boolean isUidUuidNeedWithTime) {
        this.isUidUuidNeedWithTime = isUidUuidNeedWithTime;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUserIdDup() {
        return userIdDup;
    }

    public void setUserIdDup(String userIdDup) {
        this.userIdDup = userIdDup;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }
     

}
