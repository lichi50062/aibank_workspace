package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LINE_PNP_RECORD")
public class LinePNPRecordEntity {

    /** 客戶ID */
    @Id
    @Column(name = "COMPANY_UID")
    private String companyUid;

    /** 生日 */
    @Basic
    @Column(name = "BIRTHDAY")
    private Date birthday;

    /** 手機號碼 */
    @Basic
    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;

    /**
     * 平台(行銀：MOBBANK、網銀：eBANK)
     */
    @Basic
    @Column(name = "PLATFORM")
    private String platform;

    /**
     * 交易別(繳信用卡款：MPYTX009、首次啟用行動銀行：LOGIN)
     */
    @Basic
    @Column(name = "TX_KIND")
    private String txKind;

    /**
     * 是否同意開啟(Y：同意、N：不同意)
     */
    @Basic
    @Column(name = "AGREE_FLAG")
    private String agreeFlag;

    /**
     * 創建時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 修改時間
     */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getCompanyUid() {
        return companyUid;
    }

    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTxKind() {
        return txKind;
    }

    public void setTxKind(String txKind) {
        this.txKind = txKind;
    }

    public String getAgreeFlag() {
        return agreeFlag;
    }

    public void setAgreeFlag(String agreeFlag) {
        this.agreeFlag = agreeFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
