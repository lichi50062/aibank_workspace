package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CE5552RRequest.java
* 
* <p>Description:客戶主動控卡設定(CE5552R)電文 上行</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/26, Evan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CE5552RRequest {

    /** 功能碼 X(1) */
    private String piFunc;

    /** 卡號 X(16) */
    private String piCdNo;

    /** 國內實體卡交易控管碼 X(?) */
    private String piLPhy;

    /** 國外實體卡交易控管碼 X(?) */
    private String piFPhy;

    /** 國內非實體卡交易控管碼 X(?) */
    private String piLCnp;

    /** 國外非實體卡交易控管碼 X(?) */
    private String piFCnp;

    /** 國內行動支付卡交易控管碼 X(?) */
    private String piLTkn;

    /** 國外行動支付卡交易控管碼 X(?) */
    private String piFTkn;

    /** 國內實體卡交易刷卡金額上限 X(?) */
    private String piLPam;

    /** 國外實體卡交易易刷卡金額上限 X(?) */
    private String piFPam;

    /** 國內非實體卡交易易刷卡金額上限 X(?) */
    private String piLCam;

    /** 國外非實體卡交易易刷卡金額上限 X(?) */
    private String piFCam;

    /** 國內行動支付卡交易易刷卡金額上限 X(?) */
    private String piLTam;

    /** 國外行動支付卡交易易刷卡金額上限 X(?) */
    private String piFTam;

    /**
     * @return the piFunc
     */
    public String getPiFunc() {
        return piFunc;
    }

    /**
     * @param piFunc
     *            the piFunc to set
     */
    public void setPiFunc(String piFunc) {
        this.piFunc = piFunc;
    }

    /**
     * @return the piCdNo
     */
    public String getPiCdNo() {
        return piCdNo;
    }

    /**
     * @param piCdNo
     *            the piCdNo to set
     */
    public void setPiCdNo(String piCdNo) {
        this.piCdNo = piCdNo;
    }

    /**
     * @return the piLPhy
     */
    public String getPiLPhy() {
        return piLPhy;
    }

    /**
     * @param piLPhy
     *            the piLPhy to set
     */
    public void setPiLPhy(String piLPhy) {
        this.piLPhy = piLPhy;
    }

    /**
     * @return the piFPhy
     */
    public String getPiFPhy() {
        return piFPhy;
    }

    /**
     * @param piFPhy
     *            the piFPhy to set
     */
    public void setPiFPhy(String piFPhy) {
        this.piFPhy = piFPhy;
    }

    /**
     * @return the piLCnp
     */
    public String getPiLCnp() {
        return piLCnp;
    }

    /**
     * @param piLCnp
     *            the piLCnp to set
     */
    public void setPiLCnp(String piLCnp) {
        this.piLCnp = piLCnp;
    }

    /**
     * @return the piFCnp
     */
    public String getPiFCnp() {
        return piFCnp;
    }

    /**
     * @param piFCnp
     *            the piFCnp to set
     */
    public void setPiFCnp(String piFCnp) {
        this.piFCnp = piFCnp;
    }

    /**
     * @return the piLTkn
     */
    public String getPiLTkn() {
        return piLTkn;
    }

    /**
     * @param piLTkn
     *            the piLTkn to set
     */
    public void setPiLTkn(String piLTkn) {
        this.piLTkn = piLTkn;
    }

    /**
     * @return the piFTkn
     */
    public String getPiFTkn() {
        return piFTkn;
    }

    /**
     * @param piFTkn
     *            the piFTkn to set
     */
    public void setPiFTkn(String piFTkn) {
        this.piFTkn = piFTkn;
    }

    /**
     * @return the piLPam
     */
    public String getPiLPam() {
        return piLPam;
    }

    /**
     * @param piLPam
     *            the piLPam to set
     */
    public void setPiLPam(String piLPam) {
        this.piLPam = piLPam;
    }

    /**
     * @return the piFPam
     */
    public String getPiFPam() {
        return piFPam;
    }

    /**
     * @param piFPam
     *            the piFPam to set
     */
    public void setPiFPam(String piFPam) {
        this.piFPam = piFPam;
    }

    /**
     * @return the piLCam
     */
    public String getPiLCam() {
        return piLCam;
    }

    /**
     * @param piLCam
     *            the piLCam to set
     */
    public void setPiLCam(String piLCam) {
        this.piLCam = piLCam;
    }

    /**
     * @return the piFCam
     */
    public String getPiFCam() {
        return piFCam;
    }

    /**
     * @param piFCam
     *            the piFCam to set
     */
    public void setPiFCam(String piFCam) {
        this.piFCam = piFCam;
    }

    /**
     * @return the piLTam
     */
    public String getPiLTam() {
        return piLTam;
    }

    /**
     * @param piLTam
     *            the piLTam to set
     */
    public void setPiLTam(String piLTam) {
        this.piLTam = piLTam;
    }

    /**
     * @return the piFTam
     */
    public String getPiFTam() {
        return piFTam;
    }

    /**
     * @param piFTam
     *            the piFTam to set
     */
    public void setPiFTam(String piFTam) {
        this.piFTam = piFTam;
    }

}
