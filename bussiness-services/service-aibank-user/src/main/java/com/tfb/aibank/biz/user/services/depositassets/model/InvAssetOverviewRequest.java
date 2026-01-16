
package com.tfb.aibank.biz.user.services.depositassets.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 資產總覽Request
 * Usage: for all ESB query to query assets
 */
@Schema(description = "InvAssetOverviewRequest")
public class InvAssetOverviewRequest {
    @Schema(description = "Header.HTLID")
    private String htlid;
    @Schema(description = "帳號")
    private String acctId16;

    @Schema(description = "密碼")
    private String custPswd32;

    @Schema(description = "客戶ID")
    private String custIxd;
    @Schema(description = "使用者代號")
    private String curAcctId;
    @Schema(description = "戶名代號")
    private String curAcctName;

    @Schema(description = "Nano歸戶代碼")
    private String curAcctNameNano;

    @Schema(description = "DBU or OBU")
    private String dbuObu;

    @Schema(description = "是否已登入")
    private boolean loggedIn;

    @Schema(description = "契約查詢")
    private String funcNano;

    @Schema(description = "TradeStatus for BKDCD002")
    private String tradeStatus;

    @Schema(description = "gold source 黃金來源別")
    private String goldSource;

    @Schema(description = "gold category 黃金交易別")
    private String goldCategory;

    @Schema(description = "gold acno 黃金帳號")
    private String goldAcno;

    @Schema(description = "gold convey end 黃金是否傳送至結束")
    private String goldConveyEnd;

    @Schema(description = "goldIdNo 客戶統編")
    private String goldIdNo;

    @Schema(description = "goldFuncCod 功能")
    private String goldFuncCod;

    @Schema(description = "goldNameCod 歸戶代碼")
    private String goldNameCod;

    public static final String DBU = "DBU";
    public static final String OBU = "OBU";


    public String getHtlid() {
        return htlid;
    }

    public void setHtlid(String htlid) {
        this.htlid = htlid;
    }

    public String getAcctId16() {
        return acctId16;
    }

    public void setAcctId16(String acctId16) {
        this.acctId16 = acctId16;
    }

    public String getCustPswd32() {
        return custPswd32;
    }

    public void setCustPswd32(String custPswd32) {
        this.custPswd32 = custPswd32;
    }

    public String getDbuObu() {
        return dbuObu;
    }

    public void setDbuObu(String dbuObu) {
        this.dbuObu = dbuObu;
    }

    public String getCustIxd() {
        return custIxd;
    }

    public void setCustIxd(String custIxd) {
        this.custIxd = custIxd;
    }

    public String getCurAcctId() {
        return curAcctId;
    }

    public void setCurAcctId(String curAcctId) {
        this.curAcctId = curAcctId;
    }

    public String getCurAcctName() {
        return curAcctName;
    }

    public void setCurAcctName(String curAcctName) {
        this.curAcctName = curAcctName;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getCurAcctNameNano() {
        return curAcctNameNano;
    }

    public void setCurAcctNameNano(String curAcctNameNano) {
        this.curAcctNameNano = curAcctNameNano;
    }

    public String getFuncNano() {
        return funcNano;
    }

    public void setFuncNano(String funcNano) {
        this.funcNano = funcNano;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getGoldIdNo() {
        return goldIdNo;
    }

    public void setGoldIdNo(String goldIdNo) {
        this.goldIdNo = goldIdNo;
    }

    public String getGoldFuncCod() {
        return goldFuncCod;
    }

    public void setGoldFuncCod(String goldFuncCod) {
        this.goldFuncCod = goldFuncCod;
    }

    public String getGoldNameCod() {
        return goldNameCod;
    }

    public void setGoldNameCod(String goldNameCod) {
        this.goldNameCod = goldNameCod;
    }

    public String getGoldSource() {
        return goldSource;
    }

    public void setGoldSource(String goldSource) {
        this.goldSource = goldSource;
    }

    public String getGoldCategory() {
        return goldCategory;
    }

    public void setGoldCategory(String goldCategory) {
        this.goldCategory = goldCategory;
    }

    public String getGoldConveyEnd() {
        return goldConveyEnd;
    }

    public void setGoldConveyEnd(String goldConveyEnd) {
        this.goldConveyEnd = goldConveyEnd;
    }

    public String getGoldAcno() {
        return goldAcno;
    }

    public void setGoldAcno(String goldAcno) {
        this.goldAcno = goldAcno;
    }
}
