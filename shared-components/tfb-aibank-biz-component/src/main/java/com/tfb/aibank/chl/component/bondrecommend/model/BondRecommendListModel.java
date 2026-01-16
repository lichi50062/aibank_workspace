package com.tfb.aibank.chl.component.bondrecommend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// @formatter:off
/**
 * @(#)BondRecommendListModel.java
 * 
 * <p>Description:專屬推介海外債券清單 Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/14, xwr	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */ 
// @formatter:on
public class BondRecommendListModel {

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 下架時間
     */
    private Date endTime;

    /**
     * 說明文字
     */
    private String listIntroduction;

    /**
     * 清單名稱
     */
    private String listTitle;

    /**
     * 語系
     */
    private String locale;

    /**
     * 專屬推介海外債券清單鍵值
     */
    private Integer bondRecommendListKey;

    /**
     * 推介KYC名單(C1、C2、C3、C4，對應客戶的KYC屬性)
     */
    private String recommendKyc;

    /**
     * 推介名單類型(0：客製化名單、1：KYC名單)
     */
    private Integer recommendType;

    /**
     * 上架時間
     */
    private Date startTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    /** 推薦海外債券 */
    private List<BondRecommendItemModel> list = new ArrayList<>();
   
    /** 推薦海外債券 bondNo */
    private BondRecommendItemModel bondRecommendItem;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getListIntroduction() {
        return listIntroduction;
    }

    public void setListIntroduction(String listIntroduction) {
        this.listIntroduction = listIntroduction;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Integer getBondRecommendListKey() {
        return bondRecommendListKey;
    }

    public void setBondRecommendListKey(Integer bondRecommendListKey) {
        this.bondRecommendListKey = bondRecommendListKey;
    }

    public String getRecommendKyc() {
        return recommendKyc;
    }

    public void setRecommendKyc(String recommendKyc) {
        this.recommendKyc = recommendKyc;
    }

    public Integer getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<BondRecommendItemModel> getList() {
        return list;
    }

    public void setList(List<BondRecommendItemModel> list) {
        this.list = list;
    }

	public BondRecommendItemModel getBondRecommendItem() {
		return bondRecommendItem;
	}

	public void setBondRecommendItem(BondRecommendItemModel bondRecommendItem) {
		this.bondRecommendItem = bondRecommendItem;
	}

}
