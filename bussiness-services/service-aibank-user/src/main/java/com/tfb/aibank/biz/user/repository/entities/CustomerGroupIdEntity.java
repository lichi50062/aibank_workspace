package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.tfb.aibank.biz.user.repository.entities.pk.CustomerGroupIdEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)CustomerGroupIdEntity.java
 * 
 * <p>Description:[CUSTOMER_GROUP_ID 客群名單]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, //, 
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "CUSTOMER_GROUP_ID")
@IdClass(CustomerGroupIdEntityPk.class)
public class CustomerGroupIdEntity {

    /**
     * 公司鍵值
     */
    @Id
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;
    /**
     * 客群代碼
     */
    @Id
    @Column(name = "GROUP_ID")
    private String groupId;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerGroupIdEntity that = (CustomerGroupIdEntity) o;
        return companyKey.equals(that.companyKey) && Objects.equals(groupId, that.groupId) && Objects.equals(createTime, that.createTime);
    }
    
    // fortify : Object Model Violation: Just one of equals() and hashCode() Defined
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
