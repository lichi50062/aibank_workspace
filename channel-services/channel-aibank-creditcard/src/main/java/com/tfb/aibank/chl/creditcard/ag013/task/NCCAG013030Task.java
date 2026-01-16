package com.tfb.aibank.chl.creditcard.ag013.task;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013030Rq;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013030Rs;
import com.tfb.aibank.chl.creditcard.ag013.service.NCCAG013Service;
import com.tfb.aibank.chl.session.AIBankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


// @formatter:off
/**
 * @(#)NCCAG013030Task.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 030 生物辨識設定</p>
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
public class NCCAG013030Task extends AbstractAIBankBaseTask<NCCAG013030Rq, NCCAG013030Rs> {

    @Autowired
    private NCCAG013Service service;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NCCAG013030Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG013030Rq rqData, NCCAG013030Rs rsData) throws ActionException {

        AIBankUser user = getLoginUser();

        // 從條款頁進入，寫入一筆同意條款記錄
        if (rqData.isSaveContractLog()) {
            saveContractLog(NCCAG013Service.TERMS_REMARK_KEY, "020");
            logger.info("從條款頁進入，寫入一筆同意條款記錄");
        } else {
            // 沒同意條款
            throw ExceptionUtils.getActionException(CommonErrorCode.UNAUTHORIZED_OPERATIONAL);
        }

        // [FIDO2] 獲取FIDO2 SDK需要的參數
        String serverUrl = systemParamCacheManager.getValue("AIBANK", "FIDO2_SDK_SERVERURL");
        String systemId = systemParamCacheManager.getValue("AIBANK", "FIDO2_REQUEST_EXTENSIONS_SYSTEMID");

        rsData.setUsername(user.getCustId());
        rsData.setDisplayName(DataMaskUtil.maskIdNo(user.getCustId()));
        NCCAG013030Rs.Extensions extensions = new NCCAG013030Rs.Extensions();
        extensions.setDeviceType(rqData.getDeviceType());
        extensions.setSystemId(systemId);

        rsData.setExtensions(extensions);
        rsData.setServerUrl(serverUrl);
        rsData.setSystemId(systemId);
    }
}
