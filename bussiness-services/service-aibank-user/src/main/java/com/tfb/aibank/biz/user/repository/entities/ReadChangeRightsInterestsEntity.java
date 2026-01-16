package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)ReadChangeRightsInterestsEntity.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Table(name = "READ_CHANGE_RIGHTS_INTERESTS")
@Entity
public class ReadChangeRightsInterestsEntity implements Serializable {

    private static final long serialVersionUID = 7689245780548673570L;

    /**
     * JSB權益異動說明 Reference to JSB_APPLY_DATA_SEQ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "READ_CHANGE_RIGHTS_SEQ")
    @SequenceGenerator(name = "READ_CHANGE_RIGHTS_SEQ", sequenceName = "READ_CHANGE_RIGHTS_SEQ", allocationSize = 1)
    @Column(name = "READ_KEY")
    private Integer readKey;

    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    @Column(name = "USER_KEY")
    private Integer userKey;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPLOAD_FLAG")
    private String uploadFlag;

    @Column(name = "UPLOAD_TIME")
    private Date uploadTime;

    public Integer getReadKey() {
        return readKey;
    }

    public void setReadKey(Integer readKey) {
        this.readKey = readKey;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUploadFlag() {
        return uploadFlag;
    }

    public void setUploadFlag(String uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}