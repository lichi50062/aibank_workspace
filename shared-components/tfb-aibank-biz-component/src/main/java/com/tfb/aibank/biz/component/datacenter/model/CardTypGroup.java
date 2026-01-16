package com.tfb.aibank.biz.component.datacenter.model;

public class CardTypGroup {
    // 卡別分類
    private String cardType;
    // 卡等分類
    private String cardLvl;
    // 單持卡註記
    private String caPureFlg;
    // 卡別國際組織代碼
    private String cardOrg;
    // 海外消費潛力註記
    private String overaSpndFlg;
    // 高回饋通路消費註記
    private String highFeebackFlg;
    // PCS註記
    private String pcsFlg;
    // 呆卡分群
    private String dorGrp;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardLvl() {
        return cardLvl;
    }

    public void setCardLvl(String cardLvl) {
        this.cardLvl = cardLvl;
    }

    public String getCaPureFlg() {
        return caPureFlg;
    }

    public void setCaPureFlg(String caPureFlg) {
        this.caPureFlg = caPureFlg;
    }

    public String getCardOrg() {
        return cardOrg;
    }

    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg;
    }

    public String getOveraSpndFlg() {
        return overaSpndFlg;
    }

    public void setOveraSpndFlg(String overaSpndFlg) {
        this.overaSpndFlg = overaSpndFlg;
    }

    public String getHighFeebackFlg() {
        return highFeebackFlg;
    }

    public void setHighFeebackFlg(String highFeebackFlg) {
        this.highFeebackFlg = highFeebackFlg;
    }

    public String getPcsFlg() {
        return pcsFlg;
    }

    public void setPcsFlg(String pcsFlg) {
        this.pcsFlg = pcsFlg;
    }

    public String getDorGrp() {
        return dorGrp;
    }

    public void setDorGrp(String dorGrp) {
        this.dorGrp = dorGrp;
    }
}
