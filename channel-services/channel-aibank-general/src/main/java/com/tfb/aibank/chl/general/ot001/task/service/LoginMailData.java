/**
 * 
 */
package com.tfb.aibank.chl.general.ot001.task.service;

import java.util.Map;

/**
 * @author john
 *
 */
public class LoginMailData {

    String subject;

    Map<String, String> params;

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the params
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * @param params
     *            the params to set
     */
    public void setParams(Map<String, String> params) {
        this.params = params;
    }

}
