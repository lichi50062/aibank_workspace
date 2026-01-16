package com.tfb.aibank.chl.component.customergroupid;

import java.io.Serializable;
import java.util.Date;

/**
 * CUSTOMER_GROUP_ID 客群名單
 */
public class CustomerGroupId implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 公司鍵值
     */
    private Integer companyKey;
    /**
     * 客群代碼
     */
    private String groupId;

    /**
     * 建立時間
     */
    private Date createTime;

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CustomerGroupId{" +
                "companyKey=" + companyKey +
                ", groupId='" + groupId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
