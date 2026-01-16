package com.tfb.aibank.chl.component.cityandarea;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CityAreaModel.java
 * 
 * <p>Description:縣市地區</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "鄉鎮市區")
public class CityArea implements Serializable {

    private static final long serialVersionUID = 6330772366649179680L;

    @Schema(description = "資料鍵值")
    private Integer areaKey;

    @Schema(description = "縣市代號1")
    private String cityCode1;

    @Schema(description = "區域代碼")
    private String areaCode;

    @Schema(name = "語系")
    private String locale;

    @Schema(name = "區域名稱")
    private String areaName;

    @Schema(description = "排序")
    private Integer areaSort;

    @Schema(description = "建立時間")
    private Date createTime;

    @Schema(description = "更新時間")
    private Date updateTime;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaKey() {
        return areaKey;
    }

    public void setAreaKey(Integer areaKey) {
        this.areaKey = areaKey;
    }

    public String getCityCode1() {
        return cityCode1;
    }

    public void setCityCode1(String cityCode1) {
        this.cityCode1 = cityCode1;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getAreaSort() {
        return areaSort;
    }

    public void setAreaSort(Integer areaSort) {
        this.areaSort = areaSort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
