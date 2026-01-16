package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001100Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001100Rs;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.dto.LoginRequest;
import com.tfb.aibank.chl.general.resource.dto.LoginResponse;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001100Task.java 
*  
* <p>Description:登入 FIDO 綁定初始化 Login</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001100Task extends AbstractAIBankBaseTask<NGNOT001100Rq, NGNOT001100Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001100Task.class);

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NGNOT001100Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT001100Rq rqData, NGNOT001100Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001100 start====");

        LoginRequest request = new LoginRequest();

        if ("SIGN".equals(rqData.getAction())) {
            /** 登入or認證 */
            RetriveDeviceStatusResponse cache = getCache(LoginService.DEVICE_BINDING_CACHE_KEY, RetriveDeviceStatusResponse.class);

            if (cache == null) {
                AIBankUser user = getLoginUser();
                if (user != null) {
                    request.setCustId(user.getCustId());
                    request.setUidDup(user.getUidDup());
                    request.setUserId(user.getUserId());
                    request.setCompanyKind(user.getCompanyKind());
                    //#8438 寫入 custId, companyKind
                    populatePreLoginMBAccessLogByCustId(user.getCustId());
                    populatePreLoginMBAccessLogByCompany(user.getCompanyKind());
                    populatePreLoginMBAccessLogByUserId(user.getUserId());
                }
            }
            else {
                request.setCustId(cache.getCustId());
                request.setUidDup(cache.getUidDup());
                request.setUserId(cache.getUserId());
                request.setCompanyKind(cache.getCompanyKind());
                //#8438 寫入 custId, companyKind
                populatePreLoginMBAccessLogByCustId(cache.getCustId());
                populatePreLoginMBAccessLogByCompany(cache.getCompanyKind());
                populatePreLoginMBAccessLogByUserId(cache.getUserId());
            }
        }
        else {
            /** 綁定 */
            AIBankUser user = this.getLoginUser();
            request.setCustId(user.getCustId());
            request.setUidDup(user.getUidDup());
            request.setUserId(user.getUserId());
            request.setCompanyKind(user.getCompanyKind());
            request.setDeviceManufacturer(getRequest().getPlatform());
            request.setDeviceModel(getRequest().getModel());
            //#8438 寫入 custId, companyKind
            populatePreLoginMBAccessLogByCustId(user.getCustId());
            populatePreLoginMBAccessLogByCompany(user.getCompanyKind());
            populatePreLoginMBAccessLogByUserId(user.getUserId());
        }

        request.setDeviceId(getRequest().getDeviceIxd());
        request.setAction(rqData.getAction());

        LoginResponse response = securityResource.fidoLogin(request);
        if ("0".equals(response.getStatus())) {
            setCache(LoginService.FIDO_INFO_KEY, response);
            rsData.setSuccess(true);
            rsData.setData(response.getData());
        }
        else {
            rsData.setSuccess(false);
            rsData.setErrorCode(response.getError());

            if ("F2057101".equals(response.getError())) {
                ActionException err = new ActionException(AIBankErrorCode.FIDO_UNREGISTERED);
                rsData.setErrorDesc(err.getErrorDesc());
            }
            else {
                rsData.setErrorDesc(response.getError_description());
            }
        }

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
