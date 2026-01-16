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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)CEW013RRepeat.java
 * 
 * <p>Description:申辦信用卡客戶資料查詢下行電文 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW013RRep implements Serializable {

    private static final long serialVersionUID = -220289403287466472L;

    public CEW013RRep() {
        // default constructor
    }

    /** 卡號 */
    private String cardNo;

    /** 卡片代號 */
    private String cardType;

    /** 卡種別名稱 */
    private String cardCtnm;

    /** 卡片名稱 */
    private String cardSpnm;

    /** 正附卡 */
    private String cardCty3;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardCtnm() {
        return cardCtnm;
    }

    public void setCardCtnm(String cardCtnm) {
        this.cardCtnm = cardCtnm;
    }

    public String getCardSpnm() {
        return cardSpnm;
    }

    public void setCardSpnm(String cardSpnm) {
        this.cardSpnm = cardSpnm;
    }

    public String getCardCty3() {
        return cardCty3;
    }

    public void setCardCty3(String cardCty3) {
        this.cardCty3 = cardCty3;
    }

}
