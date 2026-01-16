package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)InvestmentNoticeSettingService.java
 *
 * <p>Description:投資類交易通知設定 service</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/06/04,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class InvestmentNoticeSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 建立人員USER_KEY
     */
    private Integer createUserKey;

    /**
     * 是否需要交易結果通知
     */
    private String noticeFlag;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 更新人員USER_KEY
     */
    private Integer updateUserKey;

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserKey() {
        return createUserKey;
    }

    public void setCreateUserKey(Integer createUserKey) {
        this.createUserKey = createUserKey;
    }

    public String getNoticeFlag() {
        return noticeFlag;
    }

    public void setNoticeFlag(String noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserKey() {
        return updateUserKey;
    }

    public void setUpdateUserKey(Integer updateUserKey) {
        this.updateUserKey = updateUserKey;
    }
}
