package com.tfb.aibank.chl.component.cityandarea;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CityModel.java
 * 
 * <p>Description:縣市資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "縣市")
public class City implements Serializable {

    private static final long serialVersionUID = 4947791025484005719L;

    @Schema(description = "縣市代碼1")
    private String cityCode1;

    @Schema(description = "縣市代號2")
    private String cityCode2;

    @Schema(description = "LOCALE")
    private String locale;

    @Schema(description = "縣市名稱")
    private String cityName;

    @Schema(description = "排序")
    private Integer citySort;

    @Schema(description = "建立時間")
    private Date createTime;

    @Schema(description = "更新時間")
    private Date updateTime;

    @Schema(description = "縣市地區")
    private List<CityArea> areas;

    @Schema(description = "郵遞區號")
    private List<ZipCode> zipcodes;

    public String getCityCode1() {
        return cityCode1;
    }

    public void setCityCode1(String cityCode1) {
        this.cityCode1 = cityCode1;
    }

    public String getCityCode2() {
        return cityCode2;
    }

    public void setCityCode2(String cityCode2) {
        this.cityCode2 = cityCode2;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCitySort() {
        return citySort;
    }

    public void setCitySort(Integer citySort) {
        this.citySort = citySort;
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

    public List<CityArea> getAreas() {
        return areas;
    }

    public void setAreas(List<CityArea> areas) {
        this.areas = areas;
    }

    public List<ZipCode> getZipcodes() {
        return zipcodes;
    }

    public void setZipcodes(List<ZipCode> zipcodes) {
        this.zipcodes = zipcodes;
    }
}
