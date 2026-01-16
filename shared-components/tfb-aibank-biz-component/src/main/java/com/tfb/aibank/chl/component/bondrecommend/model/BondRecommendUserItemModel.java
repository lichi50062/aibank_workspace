package com.tfb.aibank.chl.component.bondrecommend.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;


// @formatter:off
/**
 * @(#)BondRecommendUserItemModel.java
 * 
 * <p>Description:專屬推介海外債券推薦名單 Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/14, xwr	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondRecommendUserItemModel {
	
	/** 使用者ID */
    @Schema(description = "使用者ID")
    private String custId;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private Integer companyKind;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;
    
    /**
     * 專屬推介基金清單鍵值
     */
    private String bondNo;

    private Integer bondRecommendUserItemKey;
    
    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    public BondRecommendUserItemModel() {}

    public BondRecommendUserItemModel(String custId, String userId, Integer companyKind, String uidDup, String bondNo,
            Integer bondRecommendUserItemKey, Integer companyKey, Date createTime, Date updateTime, Integer userKey) {
        this.custId = custId;
        this.userId = userId;
        this.companyKind = companyKind;
        this.uidDup = uidDup;
        this.bondNo = bondNo;
        this.bondRecommendUserItemKey = bondRecommendUserItemKey;
        this.companyKey = companyKey;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userKey = userKey;
    }

    public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCompanyKind() {
		return companyKind;
	}

	public void setCompanyKind(Integer companyKind) {
		this.companyKind = companyKind;
	}

	public String getUidDup() {
		return uidDup;
	}

	public void setUidDup(String uidDup) {
		this.uidDup = uidDup;
	}

	public Integer getBondRecommendUserItemKey() {
		return bondRecommendUserItemKey;
	}

	public void setBondRecommendUserItemKey(Integer bondRecommendUserItemKey) {
		this.bondRecommendUserItemKey = bondRecommendUserItemKey;
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

	public String getBondNo() {
		return bondNo;
	}

	public void setBondNo(String bondNo) {
		this.bondNo = bondNo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserKey() {
		return userKey;
	}

	public void setUserKey(Integer userKey) {
		this.userKey = userKey;
	}

}
