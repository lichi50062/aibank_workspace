/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.mfdcompany;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)MfdCompanyModel.java
 * 
 * <p>Description:基金公司 Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/10, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MfdCompany implements Serializable {

    private static final long serialVersionUID = 4354881859245174485L;

    /** 代理人 */
    private String agentName;

    /** 基金公司代碼 */
    private String companyCode;

    /** 公司名稱 */
    private String companyName;

    /** 公司簡稱 */
    private String companySortName;

    /** 語系 */
    private String locale;

    /** 地區 */
    private String region;

    /** 停止交易註記Y/N */
    private String stopflag;

    /** 建立時間 */
    private Date createTime;

    /** 更新時間 */
    private Date updateTime;

    /**
     * 取得代理人
     *
     * @return String 代理人
     */
    public String getAgentName() {
        return this.agentName;
    }

    /**
     * 設定代理人
     *
     * @param agentName
     *            要設定的代理人
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * 取得基金公司代碼
     *
     * @return String 基金公司代碼
     */
    public String getCompanyCode() {
        return this.companyCode;
    }

    /**
     * 設定基金公司代碼
     *
     * @param companyCode
     *            要設定的基金公司代碼
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * 取得公司名稱
     *
     * @return String 公司名稱
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     * 設定公司名稱
     *
     * @param companyName
     *            要設定的公司名稱
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 取得公司簡稱
     *
     * @return String 公司簡稱
     */
    public String getCompanySortName() {
        return this.companySortName;
    }

    /**
     * 設定公司簡稱
     *
     * @param companySortName
     *            要設定的公司簡稱
     */
    public void setCompanySortName(String companySortName) {
        this.companySortName = companySortName;
    }

    /**
     * 取得建立時間
     *
     * @return Date 建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定建立時間
     *
     * @param createTime
     *            要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得語系
     *
     * @return String 語系
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * 設定語系
     *
     * @param locale
     *            要設定的語系
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 取得地區
     *
     * @return String 地區
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * 設定地區
     *
     * @param region
     *            要設定的地區
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 取得更新時間
     *
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     *
     * @param updateTime
     *            要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStopflag() {
        return stopflag;
    }

    public void setStopflag(String stopflag) {
        this.stopflag = stopflag;
    }
}
