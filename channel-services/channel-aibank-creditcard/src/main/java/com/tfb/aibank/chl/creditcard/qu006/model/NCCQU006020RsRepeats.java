/**
 * 
 */
package com.tfb.aibank.chl.creditcard.qu006.model;

/**
 * @author john
 *
 */
public class NCCQU006020RsRepeats {

    /**
     * 兌換日期
     */
    private String btdTrdt;

    /**
     * 商品中文
     */
    private String btdChnm;

    /**
     * 自付額
     */
    private String btdAmt;

    /**
     * 兌換點數
     */
    private String btdPdns;

    /**
     * 兌換數量
     */
    private String btdCnt;

    /**
     * 兌換狀態
     */
    private String btdTxt1;

    /** 顯示Header */
    private boolean isDisplayHeader;

    /** 顯示Header Date */
    private Long headerDate;

    /** 顯示Header Month */
    private Integer headerMonth;

    /** 顯示用Header Month */
    private String headerMonthDisplay;

    /** 顯示Header Year */
    private Integer headerYear;

    /**
     * @return the btdTrdt
     */
    public String getBtdTrdt() {
        return btdTrdt;
    }

    /**
     * @param btdTrdt
     *            the btdTrdt to set
     */
    public void setBtdTrdt(String btdTrdt) {
        this.btdTrdt = btdTrdt;
    }

    /**
     * @return the btdChnm
     */
    public String getBtdChnm() {
        return btdChnm;
    }

    /**
     * @param btdChnm
     *            the btdChnm to set
     */
    public void setBtdChnm(String btdChnm) {
        this.btdChnm = btdChnm;
    }

    /**
     * @return the btdAmt
     */
    public String getBtdAmt() {
        return btdAmt;
    }

    /**
     * @param btdAmt
     *            the btdAmt to set
     */
    public void setBtdAmt(String btdAmt) {
        this.btdAmt = btdAmt;
    }

    /**
     * @return the btdPdns
     */
    public String getBtdPdns() {
        return btdPdns;
    }

    /**
     * @param btdPdns
     *            the btdPdns to set
     */
    public void setBtdPdns(String btdPdns) {
        this.btdPdns = btdPdns;
    }

    /**
     * @return the btdCnt
     */
    public String getBtdCnt() {
        return btdCnt;
    }

    /**
     * @param btdCnt
     *            the btdCnt to set
     */
    public void setBtdCnt(String btdCnt) {
        this.btdCnt = btdCnt;
    }

    /**
     * @return the btdTxt1
     */
    public String getBtdTxt1() {
        return btdTxt1;
    }

    /**
     * @param btdTxt1
     *            the btdTxt1 to set
     */
    public void setBtdTxt1(String btdTxt1) {
        this.btdTxt1 = btdTxt1;
    }

    /**
     * @return the isDisplayHeader
     */
    public boolean isDisplayHeader() {
        return isDisplayHeader;
    }

    /**
     * @param isDisplayHeader
     *            the isDisplayHeader to set
     */
    public void setDisplayHeader(boolean isDisplayHeader) {
        this.isDisplayHeader = isDisplayHeader;
    }

    /**
     * @return the headerDate
     */
    public Long getHeaderDate() {
        return headerDate;
    }

    /**
     * @param headerDate
     *            the headerDate to set
     */
    public void setHeaderDate(Long headerDate) {
        this.headerDate = headerDate;
    }

    /**
     * @return the headerMonth
     */
    public Integer getHeaderMonth() {
        return headerMonth;
    }

    /**
     * @param headerMonth
     *            the headerMonth to set
     */
    public void setHeaderMonth(Integer headerMonth) {
        this.headerMonth = headerMonth;
    }

    /**
     * @return the headerMonthDisplay
     */
    public String getHeaderMonthDisplay() {
        return headerMonthDisplay;
    }

    /**
     * @param headerMonthDisplay
     *            the headerMonthDisplay to set
     */
    public void setHeaderMonthDisplay(String headerMonthDisplay) {
        this.headerMonthDisplay = headerMonthDisplay;
    }

    /**
     * @return the headerYear
     */
    public Integer getHeaderYear() {
        return headerYear;
    }

    /**
     * @param headerYear
     *            the headerYear to set
     */
    public void setHeaderYear(Integer headerYear) {
        this.headerYear = headerYear;
    }

}
