package com.tfb.aibank.chl.creditcard.tx005.model;

import java.math.BigDecimal;
import java.util.List;

import com.tfb.aibank.chl.component.cityandarea.City;
import com.tfb.aibank.chl.creditcard.resource.dto.CreditLimitsApplyData;
import com.tfb.aibank.chl.creditcard.resource.dto.CustomerCardApply;
import com.tfb.aibank.chl.creditcard.resource.dto.EBCC002Request;

// @formatter:off
/**
 * @(#)NCCTX005Output.java
 * 
 * <p>Description:額度調整 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCTX005Output {

    /** 附卡清單 */
    private List<NCCTX005CreditCard> creditCards;

    /** 調整項目下拉選單，調高、調降、附卡額度 */
    private List<NCCTX005AdjustItem> adjustItems;

    /** 額度用途，7:人壽保費扣繳(分期)、2:人壽保費扣繳、3:一般消費需求、4:出國需求、6:其他 */
    private List<QuotaUsageType> quotaUsageList;

    /** 不可啟案的錯誤代碼 */
    private String code;

    /** 不可啟案的錯誤說明 */
    private String desc;

    /** 信用額度 */
    private BigDecimal creditLimit;

    /** 用戶辦卡申請記錄表 */
    private CustomerCardApply customerCardApply;

    /** 縣市、鄉鎮市區選項 */
    private List<City> cities;

    /** 工作資訊 */
    private NCCTX005WorkInfo workInfo;

    /** 電文(EBCC002)上行 */
    private EBCC002Request request;

    /** 信用卡固定額度調整交易紀錄檔 */
    private CreditLimitsApplyData creditLimitsApplyData;

    public List<NCCTX005AdjustItem> getAdjustItems() {
        return adjustItems;
    }

    public void setAdjustItems(List<NCCTX005AdjustItem> adjustItems) {
        this.adjustItems = adjustItems;
    }

    public List<QuotaUsageType> getQuotaUsageList() {
        return quotaUsageList;
    }

    public void setQuotaUsageList(List<QuotaUsageType> quotaUsageList) {
        this.quotaUsageList = quotaUsageList;
    }

    public List<NCCTX005CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<NCCTX005CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public CustomerCardApply getCustomerCardApply() {
        return customerCardApply;
    }

    public void setCustomerCardApply(CustomerCardApply customerCardApply) {
        this.customerCardApply = customerCardApply;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public NCCTX005WorkInfo getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(NCCTX005WorkInfo workInfo) {
        this.workInfo = workInfo;
    }

    public EBCC002Request getRequest() {
        return request;
    }

    public void setRequest(EBCC002Request request) {
        this.request = request;
    }

    public CreditLimitsApplyData getCreditLimitsApplyData() {
        return creditLimitsApplyData;
    }

    public void setCreditLimitsApplyData(CreditLimitsApplyData creditLimitsApplyData) {
        this.creditLimitsApplyData = creditLimitsApplyData;
    }

}
