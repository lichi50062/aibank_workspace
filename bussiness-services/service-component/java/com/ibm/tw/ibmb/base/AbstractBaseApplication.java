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
package com.ibm.tw.ibmb.base;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 基礎的Spring Application
 * </p>
 *
 * @author Alex LS Chen
 * @version 1.0, 2016/04/25
 * @see
 * @since
 */
public abstract class AbstractBaseApplication extends SpringBootServletInitializer {

    private String baseApplicationVersion = "1.0";

    @RequestMapping("/base/version")
    public String getBaseApplicationVersion() {
        return baseApplicationVersion;
    }

    public void setBaseApplicationVersion(String baseApplicationVersion) {
        this.baseApplicationVersion = baseApplicationVersion;
    }

    /**
     * for testing
     * 
     * @param message
     * @return
     */
    @RequestMapping("/echo/{message}")
    public String echo(@PathVariable("message") String message) {
        return "echo : " + message;
    }

}
