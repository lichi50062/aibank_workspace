/**
 * 
 */
package com.tfb.aibank.biz.user.services.log;

import java.util.Date;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.EbLoginLogBRepository;
import com.tfb.aibank.biz.user.repository.UserLoginRepository;
import com.tfb.aibank.biz.user.repository.aib.WBPlusAccessLogRepository;
import com.tfb.aibank.biz.user.repository.aib.entities.WBPlusAccessLogEntity;
import com.tfb.aibank.biz.user.repository.entities.EbLoginLogBEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;
import com.tfb.aibank.biz.user.services.log.model.WBPlusAccessLogRequest;
import com.tfb.aibank.biz.user.services.login.model.EbLoginLogBRequest;
import com.tfb.aibank.chl.session.type.LoginStatusType;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)WBPlusAccessLogService.java
* 
* <p>Description: WBPlusAccessLog 相關處理</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/03/17, David Huang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class WBPlusAccessLogService {

    private static final IBLog logger = IBLog.getLog(WBPlusAccessLogService.class);

    private WBPlusAccessLogRepository accessLogRepository;

    public WBPlusAccessLogService(WBPlusAccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }


    /**
     * 新增 WBPlusAccessLog 記錄
     * 
     * @param request
     * @return
     */
    public Boolean addWBPlusAccessLog(WBPlusAccessLogRequest request) {
        WBPlusAccessLogEntity log = new WBPlusAccessLogEntity();

        try {
            //log.setAccessTime(request.getAccessTime());
        	log.setAccessTime(request.getAccessTime());
        	log.setAccessDay(request.getAccessTime());
            log.setClientIp(request.getClientIp());
            log.setClientPort(request.getClientPort());
            log.setAppVersion(request.getAppVersion());
            log.setDataAppVersion(request.getDataAppVersion());
            log.setChannelId(request.getChannelId());
            log.setDeviceId(request.getDeviceId());
            log.setFlbKey(request.getFlbKey());
            log.setFromPage(request.getFromPage());
            log.setPageId(request.getPageId());
            log.setLanguageTag(request.getLanguageTag());
            log.setLocale(request.getLocale());
            log.setPodName(request.getPodName());
            log.setProjectApplicationName(request.getProjectApplicationName());
            log.setProjectName(request.getProjectName());
            log.setSeed(request.getSeed());
            log.setSessionId(request.getSessionId());
            log.setSpanId(request.getSpanId());
            log.setTraceId(request.getTraceId());
            log.setTrackingId(request.getTrackingId());
            
            log.setCompanyKey(request.getCompanyKey());
            log.setCompanyKind(request.getCompanyKind());
            log.setModel(request.getModel());
            log.setBusType(request.getBusType());
            log.setFuncType(request.getFuncType());
            log.setDevicePlatform(request.getDevicePlatform());
            log.setDevicePlatformVersion(request.getDevicePlatformVersion());
            log.setEncCustid(request.getEncCustId());
            //log.setCustId(request.getCustId());
            log.setMaskUserId(request.getMaskUserId());
            log.setUserKey(request.getUserKey());
            log.setNameCode(request.getNameCode());
            
            log.setMassChk(request.getMassChk());
            log.setErrorCode("");
            log.setErrorDesc(request.getErrorDesc());
            log.setErrorSystemId(request.getErrorSystemId());
            
            log.setNetwork(request.getNetwork());
            log.setResultCode(request.getResultCode());
            log.setTaskId(request.getTaskId());
            log.setResVersion(request.getResVersion());
            log.setServerName(request.getServerName());            
            
            
            accessLogRepository.save(log);
            return true;
        }
        catch (Exception ex) {
            logger.error("WB_PLUS_ACCESS_LOG 保存時出錯 {}", ex.getMessage());
            return false;
        }
    }
}
