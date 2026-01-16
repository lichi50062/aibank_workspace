/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.biz.component.etrans.message;

// @formatter:off
/**
 * @(#)BOND8030_ITERATION.java
 * 
 * <p>Description:e化繳費網 晶片卡前置系統 電文格式 xml 設定檔 mapping</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/16, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BOND8030_ITERATION {

    private String DUMMY = "";
    private String TRX_NO = "";
    private String INSURE_NO = "";
    private String AMOUNT = "";
    private String CAR_NO = "";
    private String NAME = "";

    public String getDUMMY() {
        return DUMMY;
    }

    public void setDUMMY(String dUMMY) {
        DUMMY = dUMMY;
    }

    public String getTRX_NO() {
        return TRX_NO;
    }

    public void setTRX_NO(String tRX_NO) {
        TRX_NO = tRX_NO;
    }

    public String getINSURE_NO() {
        return INSURE_NO;
    }

    public void setINSURE_NO(String iNSURE_NO) {
        INSURE_NO = iNSURE_NO;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String aMOUNT) {
        AMOUNT = aMOUNT;
    }

    public String getCAR_NO() {
        return CAR_NO;
    }

    public void setCAR_NO(String cAR_NO) {
        CAR_NO = cAR_NO;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String nAME) {
        NAME = nAME;
    }

}
