package com.tfb.aibank.chl.general.ot001.task;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.countryname.IpLocationAndCountryNameCacheManager;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001035Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001035Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.UpdateNotficationRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateNotficationResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NGNOT001035Task.java 
* 
* <p>Description:登入 開啟推播設定</p>
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
public class NGNOT001035Task extends AbstractAIBankBaseTask<NGNOT001035Rq, NGNOT001035Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001035Task.class);

    @Autowired
    UserResource userResource;

    @Autowired
    LoginService loginService;

    @Override
    public void validate(NGNOT001035Rq rqData) throws ActionException {

        if (!((rqData.getIsLineOn() == 0) || (rqData.getIsLineOn() == 1))) {
            throw new ActionException(ErrorCode.INBOUND_DATA_INVALID);
        }

        if (!((rqData.getIsLineOn() == 0) || (rqData.getIsLineOn() == 1))) {
            throw new ActionException(ErrorCode.INBOUND_DATA_INVALID);
        }

    }

    @Override
    public void handle(NGNOT001035Rq rqData, NGNOT001035Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001035 start====");

        AIBankUser loginUser = this.getLoginUser();
        UpdateNotficationRequest request = new UpdateNotficationRequest();
        request.setCustId(loginUser.getCustId());
        request.setUidDup(loginUser.getUidDup());
        request.setUserId(loginUser.getUserId());
        request.setCompanyKind(loginUser.getCompanyKind());
        request.setMobileNo(this.getLoginUser().getMobileNo());
        request.setNotificationAgreeFlag(rqData.getIsNotificationOn());
        request.setLineAgreeFlag(rqData.getIsLineOn());
        request.setBirthday(loginUser.getBirthDay());
        request.setNameCode(loginUser.getNameCode());
        // 裝置版本號
        request.setVersion(getAppVer());
        // 用戶類型 (A). 若為一般客戶：TYPE = 0。 (B). 若為信用卡網路會員：TYPE = 1
        request.setType(loginUser.getCustomerType().isGeneral() ? 0 : 1);

        try {
            userResource.updateLinePNP(request);
            updateStatus(LoginService.TRANSFER_SUCC);

            // 發送通知信

//            if (CollectionUtils.isEmpty(response.getFailNotificationTypes())) {
//                updateStatus(LoginService.TRANSFER_SUCC);
//                LoginMailData bindingMail = loginService.getLoginMailData(LoginMailType.NOTIFICATION_SETTING, getRequest().getClientIp(), loginUser.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), loginUser.getCountryName());
//                sendMail(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), bindingMail.getSubject(), loginUser.getEmail(), bindingMail.getParams());
//            }
//            else {
//                logger.error("訊息設定錯誤 {}", response.getFailNotificationTypes());
//                updateStatus(LoginService.TRANSFER_FAIL);
//            }

        }
        catch (ServiceException ex) {
            logger.error("NGNOT001035", ex);
        }

    }

    /**
     * 更新 訊息設定 cache 狀態
     * 
     * @param status
     */
    protected void updateStatus(int status) {
        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
        if (cache != null) {
            cache.setNotification(status);
            setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);
        }
    }

    protected void sendMail(String custId, String uidDup, String userId, int companyKind, String subject, String mailTo, Map<String, String> params) {
        if (StringUtils.isNotBlank(mailTo)) {
            sendTxnResultMail(custId, uidDup, userId, companyKind, subject, mailTo, params);
        }
    }

}
