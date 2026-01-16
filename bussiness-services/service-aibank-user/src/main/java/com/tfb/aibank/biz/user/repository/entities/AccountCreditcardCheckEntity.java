/**
 * 
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.support.AccountCreditcardCheckListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)AccountCreditcardCheckEntity.java
* 
* <p>Description:信用卡網路會員資料 Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/05, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "ACCOUNT_CREDITCARD_CHECK")
@EntityListeners(AccountCreditcardCheckListener.class)
public class AccountCreditcardCheckEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * companyUid
     */
    @Id
    @Column(name = "COMPANY_UID")
    private String companyUid;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 取得companyUid
     * 
     * @return String companyUid
     */
    public String getCompanyUid() {
        return this.companyUid;
    }

    /**
     * 設定companyUid
     * 
     * @param String
     *            companyUid
     */
    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
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
}