/**
 * 
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)ChangePasswordTipsEntity.java
* 
* <p>Description:逾半年以上未變更網銀密碼記錄 Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Entity
@Table(name = "CHANGE_PASSWORD_TIPS")
public class ChangePasswordTipsEntity implements Serializable {

  /**
   * <code>serialVersionUID</code> 的註解
   */
  private static final long serialVersionUID = 1L;

  public static final String SEQUENCE_NAME = "CHANGE_PASSWORD_RECORD_SEQ";

  /**
   * 交易存取記錄鍵值
   */
  @Basic
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHANGE_PASSWORD_TIPS_SEQ")
  @SequenceGenerator(name = "CHANGE_PASSWORD_TIPS_SEQ", sequenceName = "CHANGE_PASSWORD_TIPS_SEQ", allocationSize = 1)
  @Column(name = "MASTER_KEY")
  private Integer masterKey;
  
  /**
   * 使用者鍵值
   */
  @Basic
  @Column(name = "USER_KEY")
  private Integer userKey;
  
  /**
   * 公司鍵值
   */
  @Basic
  @Column(name = "COMPANY_KEY")
  private Integer companyKey;

  /**
   * 建立時間
   */
  @Basic
  @Column(name = "CREATE_TIME")
  private Date createTime;

  /**
   * 記錄日期
   */
  @Basic
  @Column(name = "TX_DATE")
  private Date txDate;

/**
 * @return the masterKey
 */
public Integer getMasterKey() {
    return masterKey;
}

/**
 * @param masterKey the masterKey to set
 */
public void setMasterKey(Integer masterKey) {
    this.masterKey = masterKey;
}

/**
 * @return the userKey
 */
public Integer getUserKey() {
    return userKey;
}

/**
 * @param userKey the userKey to set
 */
public void setUserKey(Integer userKey) {
    this.userKey = userKey;
}

/**
 * @return the companyKey
 */
public Integer getCompanyKey() {
    return companyKey;
}

/**
 * @param companyKey the companyKey to set
 */
public void setCompanyKey(Integer companyKey) {
    this.companyKey = companyKey;
}

/**
 * @return the createTime
 */
public Date getCreateTime() {
    return createTime;
}

/**
 * @param createTime the createTime to set
 */
public void setCreateTime(Date createTime) {
    this.createTime = createTime;
}

/**
 * @return the txDate
 */
public Date getTxDate() {
    return txDate;
}

/**
 * @param txDate the txDate to set
 */
public void setTxDate(Date txDate) {
    this.txDate = txDate;
}

/**
 * @return the serialversionuid
 */
public static long getSerialversionuid() {
    return serialVersionUID;
}

/**
 * @return the sequenceName
 */
public static String getSequenceName() {
    return SEQUENCE_NAME;
}
  
  



}