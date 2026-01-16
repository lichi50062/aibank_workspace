package com.tfb.aibank.chl.general.ot005.task;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.e2ee.E2EEUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.satisfaction.AibankServiceSettingCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005010Rq;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005010Rs;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.resource.SessionResource;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.component.session.LocalSessionManager;

import jakarta.servlet.http.HttpSession;

//@formatter:off
/**
* @(#)NGNOT005010Task.java 
* 
* <p>Description:登入首頁, 不需多國語系</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250305, Benson
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT005010Task extends AbstractAIBankBaseTask<NGNOT005010Rq, NGNOT005010Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT005010Task.class);

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private UserResource userResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private LoginService loginService;

    @Override
    public void validate(NGNOT005010Rq rqData) throws ActionException {        
    }

    @Override
    public void handle(NGNOT005010Rq rqData, NGNOT005010Rs rsData) throws ActionException {
        // 已登入, 或由外部傳入後續動作
        if (handleLoginOrDesignatedActionFlow(rqData, rsData)) {
            return;
        } 
 
        // Time Factor 
        populateTimeFactor(rsData);

        // Device Status 
        populateDeviceStatus(rsData);
        
        // General Info 
        populateGeneralInfo(rsData);


    }
    
    /**
     * 
     * @param rqData
     * @param rsData
     * @return
     * @throws ActionException
     */
    private boolean handleLoginOrDesignatedActionFlow(NGNOT005010Rq rqData, NGNOT005010Rs rsData) throws ActionException {
        if (this.isLoggedIn() || StringUtils.isNotBlank(rqData.getAction())) {
            logger.debug("acc_action: "+ rqData.getAction());
            // 可能為登入或沒登入的情境
            if (this.getLoginUser() != null && this.getLoginUser().getCustomerType() != null) {
                // Login User 為空值
                CustomerType customerType  = this.getLoginUser().getCustomerType();
                logger.debug("CustomerType: {}", customerType == null ? "" : customerType);
                if (CustomerType.GENERAL != customerType) {
                    throw ExceptionUtils.getActionException(AIBankErrorCode.CC_MEMBER_NOT_ALLOWED_ACC_ERROR);        
                }
            }
            // 登入後固定導向無障礙首頁
            rsData.setAction(2);

            if (StringUtils.isNotBlank(rqData.getAction())) {
                try {
                    logger.debug("accaction is: "+ rqData.getAction());
                    rsData.setAction(Integer.parseInt(rqData.getAction()));
                    
                    if (rsData.getAction() != null && rsData.getAction().intValue() == 1) {
                        logger.debug("acc logout invalid session");
                        logoutUser();
                        invalidateSession();
                    }
                }catch(Throwable e) {
                    logger.error("param from acc is invalid:  "+ rqData.getAction(), e);
                }
            }
            return true;
        }
        
        return false;
    }

    /**
     * DeviceStatus 
     * @param rsData
     * @return
     */
    private RetriveDeviceStatusResponse populateDeviceStatus(NGNOT005010Rs rsData) {
        RetriveDeviceStatusResponse retriveDeviceStatusResponse = null;
        try {

            String disableFifo = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DISABLE_FIDO");

            // ** 裝置綁定狀態*/
            retriveDeviceStatusResponse = userResource.retriveDeviceStatus(getRequest().getDeviceIxd(), getRequest().getModel(), getRequest().getVersion());


            rsData.setFastLogin(retriveDeviceStatusResponse.isFastLogin());
            if (StringUtils.equals("Y", disableFifo)) {
                rsData.setFastLogin(false);
            }

            // pwType 圖形不算快登
            if (rsData.isFastLogin()) {
                if ( 0 ==  retriveDeviceStatusResponse.getPwType() || 1 ==  retriveDeviceStatusResponse.getPwType()) {
                    rsData.setFastLogin(false);
                }
            }
            
            rsData.setLoginMethod(retriveDeviceStatusResponse.getPwType());
            rsData.setLoginType(retriveDeviceStatusResponse.getLoginType());
            rsData.setCustId(retriveDeviceStatusResponse.getCustId());
            rsData.setDirectEzLoginFlag(retriveDeviceStatusResponse.getDirectEzLoginFlag());
            rsData.setIsInBlackList(retriveDeviceStatusResponse.getIsInBlackList());
            rsData.setStatus(retriveDeviceStatusResponse.getStatus());

            retriveDeviceStatusResponse.setUidUuidNeedWithTime(rsData.isUidUuidNeedWithTime());
            retriveDeviceStatusResponse.setPwdNeedWithTime(rsData.isPwdNeedWithTime());
            // Cache 需區別無障礙
            setCache(LoginService.DEVICE_BINDING_CACHE_KEY, retriveDeviceStatusResponse); 
            
        }
        catch (ServiceException ex) {
            logger.warn("取得裝置綁定狀態失敗，只能以帳密登入", ex);
            rsData.setFastLogin(false);
        }
        
        return retriveDeviceStatusResponse;
    }   

    /**
     * Timefactor 
     * @param rsData
     */
    private void populateTimeFactor(NGNOT005010Rs rsData) {
        rsData.setPwdNeedWithTime(loginService.isPwdNeedWithTime());
        if (rsData.isPwdNeedWithTime()) {
            String tmpTimeFactorValidSeconds = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "TIME_FACTOR_VALID_SECONDS");
            int timeFactorValidSeconds = 280;
            if (StringUtils.isNotBlank(tmpTimeFactorValidSeconds)) {
                /** 資料庫為時間因子容許時間差，此處保留 20 秒緩衝時間給通訊或程式運作 */
                timeFactorValidSeconds = Integer.parseInt(tmpTimeFactorValidSeconds) - 20;
            }

            rsData.setTimeFactorValidSeconds(timeFactorValidSeconds);
            rsData.setPwdWithTime(E2EEUtils.getPassWithTime());
        }

        /** E2EE給前端uid/uuid加密時，是否帶入之時間因子 */
        rsData.setUidUuidNeedWithTime(loginService.isUidUuidNeedWithTime());
        
        if (rsData.isUidUuidNeedWithTime()) {
            rsData.setUidUuidWithTime(E2EEUtils.getPassWithTime());
        }
        try {
            String kxy = securityResource.getRSAPublicKxy();
            rsData.setPublicKeyforUid(kxy);
            rsData.setPublicKeyforSecret(kxy);
        }
        catch (ServiceException ex) {
            logger.error("取得公鑰失敗", ex);
        }
        

    }

    private void populateGeneralInfo(NGNOT005010Rs rsData) {
        String fidoPortalUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_SDK_URL");
        rsData.setFidoPortalUrl(fidoPortalUrl);

        String businessNo = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_BUSINESS_NO");
        rsData.setBusinessNo(businessNo);
    }
    
    
    protected final void invalidateSession() {
        // 取得目前 session
        HttpSession session = getHttpServletRequest().getSession(false);
        session.invalidate();
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
    /*
     * 只處理中文
     * @see com.ibm.tw.ibmb.task.AbstractBaseTask#getLocale()
     */
    @Override
    protected Locale getLocale() {
       return Locale.TRADITIONAL_CHINESE;
    }

}
