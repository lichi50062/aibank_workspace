package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

 // @formatter:off
/**
 * @(#)RespectInfo.java
 *
 * <p>Description:[招呼語資訊]</p>
 *
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/1, Marty Pan
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class RespectInfo {

    private static final long serialVersionUID = 1L;
    /**
     * 資料鍵值
     */
    private Long respectKey;

    /**
     * 招呼語內容
     */
    private String respectDesc;
    /**
     * 圖示
     */
    private String respectIcon;
    /**
     * 招呼語標語
     */
    private String respectDesc2;
    /**
     *招呼語標語2
     */
    private String respectDesc3;
    /**
     * 對應功能/導頁連結
     */
    private String respectTarget;
    /**
     * 顯示起始日
     */
    private Date startDate;
    /**
     * 顯示結束日
     */
    private Date endDate;
    /**
     * 順序
     */
    private Integer orderNo;
    /**
     * 語系
     */
    private String locale;

    public Long getRespectKey() {
        return respectKey;
    }

    public void setRespectKey(Long respectKey) {
        this.respectKey = respectKey;
    }

    public String getRespectDesc() {
        return respectDesc;
    }

    public void setRespectDesc(String respectDesc) {
        this.respectDesc = respectDesc;
    }

    public String getRespectIcon() {
        return respectIcon;
    }

    public void setRespectIcon(String respectIcon) {
        this.respectIcon = respectIcon;
    }

    public String getRespectDesc2() {
        return respectDesc2;
    }

    public void setRespectDesc2(String respectDesc2) {
        this.respectDesc2 = respectDesc2;
    }

    public String getRespectDesc3() {
        return respectDesc3;
    }

    public void setRespectDesc3(String respectDesc3) {
        this.respectDesc3 = respectDesc3;
    }

    public String getRespectTarget() {
        return respectTarget;
    }

    public void setRespectTarget(String respectTarget) {
        this.respectTarget = respectTarget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
