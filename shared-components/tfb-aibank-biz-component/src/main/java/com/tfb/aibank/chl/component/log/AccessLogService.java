package com.tfb.aibank.chl.component.log;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.util.IBUtils;

// @formatter:off
/**
 * @(#)AccessLogService.java
 * 
 * <p>Description:提供紀錄ACCESS LOG之相關服務</p>
 * 
 * <p>Modify History:</p>
 *    v1.0, 20253/03/07, David Huang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class AccessLogService {

    private static final IBLog logger = IBLog.getLog(AccessLogService.class);

    @Autowired
    private Environment environment;
    @Autowired
    private AccessLogResource resource;

    /**
     * 新增AI ACCESS LOG記錄
     * 
     * @param WBPlusAccessLogRequest
     * @param notify
     */
    @Async
    public void addWBPlusAccessLog(MBAccessLog log) {

        WBPlusAccessLogRequest request = new WBPlusAccessLogRequest();

        request.setAccessTime(log.getAccessTime());
        request.setAppVersion(MDC.get(MDCKey.appVersion.name()));
        request.setDataAppVersion(log.getAppVersion());
        request.setChannelId(log.getChannelId());
        request.setClientIp(log.getClientIp());
        request.setDeviceId(log.getDeviceId());
        request.setFromPage(MDC.get(MDCKey.frompage.name()));
        request.setPageId(MDC.get(MDCKey.pageid.name()));
        request.setLanguageTag(MDC.get(MDCKey.languagetag.name()));
        request.setLocale(MDC.get(MDCKey.locale.name()));
        request.setPodName(IBUtils.getInstanceId(environment));
        request.setProjectName("aibank");
        request.setProjectApplicationName(environment.getProperty("spring.application.name", "unknown"));
        request.setSeed(MDC.get(MDCKey.seed.name()));
        request.setSessionId(MDC.get(MDCKey.sessionid.name()));
        request.setSpanId(MDC.get("spanId"));
        request.setTraceId(MDC.get(MDCKey.traceId.name()));
        request.setTrackingId(MDC.get(MDCKey.trackingid.name()));

        request.setClientPort(log.getClientPort());
        request.setCompanyKey(log.getCompanyKey());
        request.setCompanyKind(log.getCompanyKind());
        request.setModel(log.getModel());
        // request.setFlbKey("");
        request.setBusType(log.getBusType());
        request.setFuncType(log.getFuncType());
        request.setDevicePlatform(log.getDevicePlatform());
        request.setDevicePlatformVersion(log.getDevicePlatformVersion());
        request.setEncCustId(log.getEncCustId());
        request.setMaskUserId(log.getMaskUserId());
        request.setUserKey(log.getUserKey());
        request.setNameCode(log.getNameCode());

        request.setMassChk(log.getMassChk());
        request.setErrorCode(log.getErrorCode());
        request.setErrorDesc(log.getErrorDesc());
        request.setErrorSystemId(log.getErrorSystemId());

        request.setNetwork(log.getNetwork());
        request.setResultCode(log.getResultCode());
        request.setTaskId(log.getTaskId());
        request.setResVersion(log.getResVersion());
        request.setServerName(log.getServerName());

        try {
            resource.addWBPlusAccessLog(request);
        }
        catch (ServiceException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
