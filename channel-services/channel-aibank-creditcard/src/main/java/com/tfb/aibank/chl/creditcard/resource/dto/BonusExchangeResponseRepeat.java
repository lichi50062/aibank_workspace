/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;

/**
 * @author john
 *
 */
public class BonusExchangeResponseRepeat {

    /**
     * 兌換日期
     */
    private String btdTrdt;

    /**
     * 兌換編號
     */
    private String btdOrno;

    /**
     * 商品代號
     */
    private String btdItem;

    /**
     * 商品中文
     */
    private String btdChnm;

    /**
     * 自付額
     */
    private BigDecimal btdAmt;

    /**
     * 兌換點數
     */
    private BigDecimal btdPdns;

    /**
     * 兌換數量
     */
    private BigDecimal btdCnt;

    /**
     * 兌換狀態
     */
    private String btdTxt1;

    /**
     * 兌換方式
     */
    private String btdTxt2;

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
     * @return the btdOrno
     */
    public String getBtdOrno() {
        return btdOrno;
    }

    /**
     * @param btdOrno
     *            the btdOrno to set
     */
    public void setBtdOrno(String btdOrno) {
        this.btdOrno = btdOrno;
    }

    /**
     * @return the btdItem
     */
    public String getBtdItem() {
        return btdItem;
    }

    /**
     * @param btdItem
     *            the btdItem to set
     */
    public void setBtdItem(String btdItem) {
        this.btdItem = btdItem;
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
    public BigDecimal getBtdAmt() {
        return btdAmt;
    }

    /**
     * @param btdAmt
     *            the btdAmt to set
     */
    public void setBtdAmt(BigDecimal btdAmt) {
        this.btdAmt = btdAmt;
    }

    /**
     * @return the btdPdns
     */
    public BigDecimal getBtdPdns() {
        return btdPdns;
    }

    /**
     * @param btdPdns
     *            the btdPdns to set
     */
    public void setBtdPdns(BigDecimal btdPdns) {
        this.btdPdns = btdPdns;
    }

    /**
     * @return the btdCnt
     */
    public BigDecimal getBtdCnt() {
        return btdCnt;
    }

    /**
     * @param btdCnt
     *            the btdCnt to set
     */
    public void setBtdCnt(BigDecimal btdCnt) {
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
     * @return the btdTxt2
     */
    public String getBtdTxt2() {
        return btdTxt2;
    }

    /**
     * @param btdTxt2
     *            the btdTxt2 to set
     */
    public void setBtdTxt2(String btdTxt2) {
        this.btdTxt2 = btdTxt2;
    }

}
