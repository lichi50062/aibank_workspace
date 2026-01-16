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
package com.tfb.aibank.biz.component.whitelist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;

import jakarta.servlet.http.HttpServletRequest;

// @formatter:off
/**
 * @(#)WhitListCheckService.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/29, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class WhitListCheckService {

    private static final IBLog logger = IBLog.getLog(WhitListCheckService.class);

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    public WhitListCheckResponse checkIPallowed(String apiName, HttpServletRequest httpRequest) {

        logger.debug("White List Checker");

        String clientIp = httpRequest.getRemoteAddr();
        logger.debug("Client IP:{}", clientIp);
        if (StringUtils.isBlank(clientIp)) {
            clientIp = httpRequest.getHeader("X-Forwarded-For");
            logger.debug("X-Forwarded-For Client IP:{}", clientIp);
            if (StringUtils.isBlank(clientIp)) {
                logger.error("Client IP not exist");
                return new WhitListCheckResponse("9992", "存取被拒-無IP");
            }
        }

        String whiteLists = systemParamCacheManager.getValue("WHITE_LIST", apiName);
        if (StringUtils.isBlank(whiteLists)) {
            return new WhitListCheckResponse("9991", "此服務無法使用，請洽相關人員");
        }

        if (StringUtils.equals("off", whiteLists)) {
            return null;
        }

        String[] whiteList = whiteLists.split(",");

        for (String ip : whiteList) {
            if (StringUtils.equals(ip.trim(), clientIp)) {
                return null;
            }
        }
        return new WhitListCheckResponse("9992", "存取被拒");
    }

}
