package com.tfb.aibank.chl.creditcard.resource.dto;


import java.util.List;


/**
 * 縣市與鄉鎮區
 *
 * @author Aaron
 */
public class CityAndAreaModel {

    /**
     * 縣市資料.
     */
    private List<CityModel> cityModelList ;

    /**
     * 鄉鎮區.
     */
    private List<CityAreaModel> cityAreaModelList ;


    /**
     * Gets city model list.
     *
     * @return the city model list
     */
    public List<CityModel> getCityModelList() {
        return cityModelList;
    }

    /**
     * Sets city model list.
     *
     * @param cityModelList
     *            the city model list
     */
    public void setCityModelList(List<CityModel> cityModelList) {
        this.cityModelList = cityModelList;
    }

    /**
     * Gets city area model list.
     *
     * @return the city area model list
     */
    public List<CityAreaModel> getCityAreaModelList() {
        return cityAreaModelList;
    }

    /**
     * Sets city area model list.
     *
     * @param cityAreaModelList
     *            the city area model list
     */
    public void setCityAreaModelList(List<CityAreaModel> cityAreaModelList) {
        this.cityAreaModelList = cityAreaModelList;
    }
}
