package com.tfb.aibank.chl.component.bondrecommend.model;

import java.util.Date;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Basic;

// @formatter:off
/**
 * @(#)MfdRecommendItemModel.java
 * 
 * <p>Description:專屬推介海外債券 Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/14, xwr	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "專屬推介海外債券 Model")
public class BondRecommendItemModel {

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 海外債券代號
     */
    @Schema(description = "海外債券代號")
    private String bondNo;

    /**
     * 海外債券排序
     */
    @Schema(description = "海外債券排序")
    private Integer itemSort;

    /**
     * 專屬推介海外債券項目鍵值
     */
    @Schema(description = "專屬推介海外債券項目鍵值")
    private Integer mfdRecommendItemKey;

    /**
     * 專屬推介海外債券清單鍵值
     */
    @Schema(description = "專屬推介海外債券清單鍵值")
    private Integer mfdRecommendListKey;
    
    /**
	 * 公司名稱
	 */
	@Schema(description = "公司名稱")
	private String companyName;

	
	 /**
     * 文案內容
     */
    @Basic
    @Schema(description = "文案內容")
    private String content;

	
    @Schema(description = "更新時間")
	private Date updateTime;

    public BondRecommendItemModel() {}
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

    /**
     * 取得海外債券代號
     * 
     * @return String 海外債券代號
     */
    public String getBondNo() {
        return this.bondNo;
    }

    /**
     * 設定海外債券代號
     * 
     * @param fundCode
     *            要設定的海外債券代號
     */
    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    /**
     * 取得海外債券排序
     * 
     * @return Integer 海外債券排序
     */
    public Integer getItemSort() {
        return this.itemSort;
    }

    /**
     * 設定海外債券排序
     * 
     * @param itemSort
     *            要設定的海外債券排序
     */
    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
    }

    /**
     * 取得專屬推介海外債券項目鍵值
     * 
     * @return Integer 專屬推介海外債券項目鍵值
     */
    public Integer getMfdRecommendItemKey() {
        return this.mfdRecommendItemKey;
    }

    /**
     * 設定專屬推介海外債券項目鍵值
     * 
     * @param mfdRecommendItemKey
     *            要設定的專屬推介海外債券項目鍵值
     */
    public void setMfdRecommendItemKey(Integer mfdRecommendItemKey) {
        this.mfdRecommendItemKey = mfdRecommendItemKey;
    }

    /**
     * 取得專屬推介海外債券清單鍵值
     * 
     * @return String 專屬推介海外債券清單鍵值
     */
    public Integer getMfdRecommendListKey() {
        return this.mfdRecommendListKey;
    }

    /**
     * 設定專屬推介海外債券清單鍵值
     * 
     * @param mfdRecommendListKey
     *            要設定的專屬推介海外債券清單鍵值
     */
    public void setMfdRecommendListKey(Integer mfdRecommendListKey) {
        this.mfdRecommendListKey = mfdRecommendListKey;
    }

    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
