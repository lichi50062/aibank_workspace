package com.tfb.aibank.chl.creditcard.ag013.task;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013010Rq;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013010Rs;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013Fido2APIEnum;
import com.tfb.aibank.chl.creditcard.ag013.service.NCCAG013Service;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.SecurityResource;
import com.tfb.aibank.chl.creditcard.resource.UserResource;
import com.tfb.aibank.chl.creditcard.resource.dto.EB602652ARepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.QueryUserInfoFido2Request;
import com.tfb.aibank.chl.creditcard.resource.dto.QueryUserInfoFido2Response;
import com.tfb.aibank.chl.creditcard.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.session.AIBankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

// @formatter:off
/**
 * @(#)NCCAG013010Task.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG013010Task extends AbstractAIBankBaseTask<NCCAG013010Rq, NCCAG013010Rs> {

    @Autowired
    private NCCAG013Service service;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private UserResource userResource;

    @Override
    public void validate(NCCAG013010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG013010Rq rqData, NCCAG013010Rs rsData) throws ActionException {
        super.initTxSecurity();

        AIBankUser loginUser = getLoginUser();
        // 查詢是否持有信用卡
        boolean hasCreditCard = userDataCacheService.haveCreditCard(loginUser);
        // 查詢簽帳卡資料
        List<EB602652ARepeat> eb602652ARepeats = creditCardResource.sendEB602652A(loginUser.getCustId());
        logger.info("查詢簽帳卡資料: {}", eb602652ARepeats);

        // true:「未持有信用卡」或 未持有簽帳卡
        if(!hasCreditCard && eb602652ARepeats.isEmpty()){
            // 檢核是否為特殊戶
            userDataCacheService.validateCreditCardStatus(loginUser);
        }

        String device = rqData.getDevice();
        Integer deviceVersion = rqData.getDeviceVersion();
        if (device == null || deviceVersion == null) {
            logger.error("裝置類型或版本為 null");
            rsData.setCheckDeviceVersion(false);
            return;
        }

        switch (device.toLowerCase()) {
            case "android":
                if (deviceVersion < 9) {
                    logger.error("android 裝置版本不大於等於 9");
                    rsData.setCheckDeviceVersion(false);
                    return;
                }
                break;
            case "ios":
                if (deviceVersion < 16) {
                    logger.error("ios 裝置版本不大於等於 16");
                    rsData.setCheckDeviceVersion(false);
                    return;
                }
                break;
            default:
                logger.error("不支援的裝置類型: {}", device);
                rsData.setCheckDeviceVersion(false);
                return;
        }

        // [FIDO2] 打API查詢 FIDO2 會員狀態
        QueryUserInfoFido2Request request = new QueryUserInfoFido2Request();

        String rpId = systemParamCacheManager.getValue("AIBANK", "FIDO2_HEADER_RPID");
        request.setRpId(rpId);
        request.setUsername(loginUser.getCustId());
        QueryUserInfoFido2Response response = securityResource.queryUserInfoFido2(request);
        String code = response.getReturnCode();
        if (NCCAG013Fido2APIEnum.STATUS_FAIL.getCode().equalsIgnoreCase(response.getStatus()) &&
                !NCCAG013Fido2APIEnum.RPMEMBER_NOT_RECOGNIZED.getCode().equals(code)) {
            String desc = service.getEPayErrorMessage(service.normalizeErrorCode(code), getLocale());
            logger.error("[FIDO2] FIDO2 查詢會員狀態失敗回傳錯誤代碼: {}, 錯誤訊息: {}", code, desc);
            ErrorStatus errorStatus = new ErrorStatus(IBSystemId.FIDO2.getSystemId(), "API " + code,
                    SeverityType.ERROR, desc);
            throw ExceptionUtils.getActionException(errorStatus);
        }

        if (NCCAG013Fido2APIEnum.ATTESTATION_STATUS_SUCCESS.getCode().equals(response.getAttestationStatus())) {
            // 裝置綁定狀態
            RetriveDeviceStatusResponse retriveDeviceStatusResponse = userResource.retriveDeviceStatus(getRequest().getDeviceIxd(), getRequest().getModel(), getRequest().getVersion());
            rsData.setMarketingName(retriveDeviceStatusResponse.getMarketingName());
            rsData.setFido(true);
            rsData.setCheckDeviceVersion(true);
            return;
        }
        rsData.setFido(false);
        rsData.setCheckDeviceVersion(true);
    }
}
