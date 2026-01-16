package com.tfb.aibank.chl.system.ot014.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NSTOT014020Rs.java
 * 
 * <p>Description:投資風險偏好輔助說明 020 投資風險偏好輔助說明 020</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT014020Rs implements RsData {
    /**
     * 測驗日
     */
    private String testDate;

    /**
     * 留存於本行Email
     */
    private String email;

    /**
     * 第一部分填答結果
     */
    private List<QAItem> qaList;

    /**
     * 第二部分填答結果
     */
    private List<QAItem> qaList2;

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<QAItem> getQaList() {
        return qaList;
    }

    public void setQaList(List<QAItem> qaList) {
        this.qaList = qaList;
    }

    public List<QAItem> getQaList2() {
        return qaList2;
    }

    public void setQaList2(List<QAItem> qaList2) {
        this.qaList2 = qaList2;
    }
}
