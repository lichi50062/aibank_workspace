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
package com.tfb.aibank.chl.system.ot010.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.system.service.NSTService;

// @formatter:off
/**
 * @(#)NSTOT01Service.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/03, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NSTOT010Service extends NSTService {

    public List<String> getWhitList() {
        String whiteListString = dicCacheManager.getValue("AIBANK", "WHITE_LIST");

        /** 沒有白名單 */
        if (StringUtils.isBlank(whiteListString)) {
            return new ArrayList<String>();
        }
        whiteListString = whiteListString.replace("*.", "");

        String[] whiteListArray = whiteListString.trim().split(",");

        return new ArrayList<String>(Arrays.asList(whiteListArray));
    }

    public String getDomain(String href) {
        if (href == null)
            return "";

        URI uri;
        try {
            uri = new URI(href);
        }
        catch (URISyntaxException e) {
            return "";
        }
        return uri.getHost();
    }

}
