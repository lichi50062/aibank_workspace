package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)ChangePasswordTipsEntity.java
* 
* <p>Description:逾半年以上未變更網銀密碼記錄</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/04, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
public class ChangePasswordTips {
    
  /** 交易存取記錄鍵值 */
  private Integer masterKey;
  
  /** 使用者鍵值 */
  private Integer userKey;
  
  /** 公司鍵值 */
  private Integer companyKey;

  /** 建立時間 */
  private Date createTime;

  /** 記錄日期 */
  private Date txDate;

public Integer getMasterKey() {
    return masterKey;
}

public void setMasterKey(Integer masterKey) {
    this.masterKey = masterKey;
}

public Integer getUserKey() {
    return userKey;
}

public void setUserKey(Integer userKey) {
    this.userKey = userKey;
}

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

public Date getTxDate() {
    return txDate;
}

public void setTxDate(Date txDate) {
    this.txDate = txDate;
}

  
  
}