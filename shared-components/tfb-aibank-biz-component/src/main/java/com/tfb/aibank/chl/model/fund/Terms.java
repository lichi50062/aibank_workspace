/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.model.fund;

import java.io.Serializable;

import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;

// @formatter:off
/**
 * @(#)Terms.java
 * 
 * <p>Description:條款</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/03, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class Terms implements Serializable {

    private static final long serialVersionUID = -4440307758350928947L;

    public Terms() {
        // default constructor
    }

    public Terms(String label, String title, String content) {
        this.label = label;
        this.title = title;
        this.content = content;
    }

    public Terms(String label, String title, String content, String link) {
        this.label = label;
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public Terms(String label, RemarkContent remarkContent) {
        this.label = label;
        this.title = remarkContent.getTitle();
        this.content = remarkContent.getContent();
        this.version = remarkContent.getVersion();
        this.remarkKey = remarkContent.getRemarkKey();
    }

    public Terms(String label, RemarkContent remarkContent, String warning) {
        this.label = label;
        this.title = remarkContent.getTitle();
        this.content = remarkContent.getContent();
        this.version = remarkContent.getVersion();
        this.remarkKey = remarkContent.getRemarkKey();
        this.warning = warning;
    }

    /** 頁面顯示使用-收合時顯示文字 */
    private String label;

    /** 展開狀態下-條款標題 */
    private String title;
    /** 展開狀態下-條款內容 */
    private String content;
    /** 展開狀態下-條款連結網址 */
    private String link;

    /** 展開狀態下-警語 */
    private String warning;

    /** 展開狀態下-第二組條款標題 */
    private String title2;
    /** 展開狀態下-第二組條款內容 */
    private String content2;
    /** 展開狀態下-第二組條款連結網址 */
    private String link2;

    /** 頁面控制使用-是否已閱讀 */
    private boolean isRead;
    /** 頁面控制使用-是否已展開 */
    private boolean isExpand;

    /** 版本 */
    private String version;
    /** 文案鍵值 */
    private String remarkKey;
    /** 文案鍵值 */
    private String remarkKey2;

    /** 項次編號 */
    private int itemNo;

    /** PDF 檔案鍵值 */
    private String pdfKey;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }

    public String getRemarkKey() {
        return remarkKey;
    }

    public void setRemarkKey(String remarkKey) {
        this.remarkKey = remarkKey;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getRemarkKey2() {
        return remarkKey2;
    }

    public void setRemarkKey2(String remarkKey2) {
        this.remarkKey2 = remarkKey2;
    }

    public String getPdfKey() {
        return pdfKey;
    }

    public void setPdfKey(String pdfKey) {
        this.pdfKey = pdfKey;
    }

}
