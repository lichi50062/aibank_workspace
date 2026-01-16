package com.tfb.aibank.chl.component.newfunctionintro;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)NewFunctionIntro.java
 *
 * <p>Description:NewFunctionIntro 新功能介紹</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Marty Pan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NewFunctionIntro implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 新功能介紹鍵值
     */
    private Long functionKey;

    /**
     * 語系
     */
    private String locale;

    /**
     * 標題
     */
    private String title;

    /**
     * 牌卡說明文字
     */
    private String content;

    /**
     * 標語
     */
    private String introDesc;

    /**
     * 詳細說明
     */
    private String contentDtl;

    /**
     * 按鈕文字1
     */
    private String buttonNm1;

    /**
     * 按鈕文字2
     */
    private String buttonNm2;

    /**
     * 按鈕連結
     */
    private String buttonUrl;

    /**
     * 功能定錨參數
     */
    private String anchorParam;

    /**
     * 圖示
     */
    private String pictureUrl;

    /**
     * 首頁圖示
     */
    private String pictureUrlHome;

    /**
     * 上架時間
     */
    private Date startTime;

    /**
     * 下架時間
     */
    private Date endTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 客群代碼
     */
    private String customerGroupId;

    /**
     * 新功能介紹鍵值
     */
    public Long getFunctionKey() {
        return functionKey;
    }

    /**
     * 新功能介紹鍵值
     */
    public void setFunctionKey(Long functionKey) {
        this.functionKey = functionKey;
    }

    /**
     * 語系
     */
    public String getLocale() {
        return locale;
    }

    /**
     * 語系
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 標題
     */
    public String getTitle() {
        return title;
    }

    /**
     * 標題
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 牌卡說明文字
     */
    public String getContent() {
        return content;
    }

    /**
     * 牌卡說明文字
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 標語
     */
    public String getIntroDesc() {
        return introDesc;
    }

    /**
     * 標語
     */
    public void setIntroDesc(String introDesc) {
        this.introDesc = introDesc;
    }

    /**
     * 詳細說明
     */
    public String getContentDtl() {
        return contentDtl;
    }

    /**
     * 詳細說明
     */
    public void setContentDtl(String contentDtl) {
        this.contentDtl = contentDtl;
    }

    /**
     * 按鈕文字1
     */
    public String getButtonNm1() {
        return buttonNm1;
    }

    /**
     * 按鈕文字1
     */
    public void setButtonNm1(String buttonNm1) {
        this.buttonNm1 = buttonNm1;
    }

    /**
     * 按鈕文字2
     */
    public String getButtonNm2() {
        return buttonNm2;
    }

    /**
     * 按鈕文字2
     */
    public void setButtonNm2(String buttonNm2) {
        this.buttonNm2 = buttonNm2;
    }

    /**
     * 按鈕連結
     */
    public String getButtonUrl() {
        return buttonUrl;
    }

    /**
     * 按鈕連結
     */
    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }

    /**
     * 功能定錨參數
     */
    public String getAnchorParam() {
        return anchorParam;
    }

    /**
     * 功能定錨參數
     */
    public void setAnchorParam(String anchorParam) {
        this.anchorParam = anchorParam;
    }

    /**
     * 圖示
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * 圖示
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * 首頁圖示
     */
    public String getPictureUrlHome() {
        return pictureUrlHome;
    }

    /**
     * 首頁圖示
     */
    public void setPictureUrlHome(String pictureUrlHome) {
        this.pictureUrlHome = pictureUrlHome;
    }

    /**
     * 上架時間
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 上架時間
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 下架時間
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 下架時間
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 更新時間
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(String customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    @Override
    public String toString() {
        return "NewFunctionIntro{" +
                "functionKey=" + functionKey +
                ", locale='" + locale + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", introDesc='" + introDesc + '\'' +
                ", contentDtl='" + contentDtl + '\'' +
                ", buttonNm1='" + buttonNm1 + '\'' +
                ", buttonNm2='" + buttonNm2 + '\'' +
                ", buttonUrl='" + buttonUrl + '\'' +
                ", anchorParam='" + anchorParam + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", pictureUrlHome='" + pictureUrlHome + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", sort=" + sort +
                ", updateTime=" + updateTime +
                '}';
    }
}
