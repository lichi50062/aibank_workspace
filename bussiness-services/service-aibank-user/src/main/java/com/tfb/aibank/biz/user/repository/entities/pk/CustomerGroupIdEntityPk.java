package com.tfb.aibank.biz.user.repository.entities.pk;
import java.io.Serializable;


// @formatter:off
/**
 * @(#)CustomerGroupIdEntityPK.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, //, 
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CustomerGroupIdEntityPk implements Serializable {

    private Integer companyKey;

    private String groupId;

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
}
