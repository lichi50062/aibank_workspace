package com.tfb.aibank.chl.creditcard.ag005.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG005010Rs.java
 * 
 * <p>Description:保費權益設定 010 保費權益信用卡清單</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG005010Rs implements RsData {

    /** 查詢類型 1:歸戶ID，2:卡號 */
    private String type;

    /** 可進行保費權益設定的卡片清單 */
    private List<NCCAG005CardInfo> cardInfoList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NCCAG005CardInfo> getCardInfoList() {
        return cardInfoList;
    }

    public void setCardInfoList(List<NCCAG005CardInfo> cardInfoList) {
        this.cardInfoList = cardInfoList;
    }

}
