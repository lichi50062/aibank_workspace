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
package com.tfb.aibank.chl.creditcard.ag005.model;

import java.util.List;

// @formatter:off
/**
 * @(#)NCCAG005CacheData.java
 * 
 * <p>Description:保費權益設定 Cache 物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG005CacheData {

    /** 可進行保費權益設定的卡片清單 */
    private List<NCCAG005CardInfo> cardInfoList;

    /** 選擇的卡片 */
    private NCCAG005CardInfo cardInfo;

    public List<NCCAG005CardInfo> getCardInfoList() {
        return cardInfoList;
    }

    public void setCardInfoList(List<NCCAG005CardInfo> cardInfoList) {
        this.cardInfoList = cardInfoList;
    }

    public NCCAG005CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(NCCAG005CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

}
