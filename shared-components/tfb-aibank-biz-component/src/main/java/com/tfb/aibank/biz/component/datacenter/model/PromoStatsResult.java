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
package com.tfb.aibank.biz.component.datacenter.model;

import java.util.List;

//@formatter:off
/**
* @(#)PromoStatsResult.java
* 
* <p>Description:數據中台 API- 取得客戶所屬標籤 - Rs</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/07, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*
*/
//@formatter:on
public class PromoStatsResult {

    // 海外消費潛力戶
    private String caOveraSpndFlg;
    // 消費力
    private String caSpndRank;
    // 自動扣繳
    private String caAutoPayFlg;
    // 地區
    private String caBillArea;
    // 標籤清單
    private List<DTagGroup> dTagGroup;
    // 客戶卡片清單
    private List<CardTypGroup> cardTypGroup;

    public String getCaOveraSpndFlg() {
        return caOveraSpndFlg;
    }

    public void setCaOveraSpndFlg(String caOveraSpndFlg) {
        this.caOveraSpndFlg = caOveraSpndFlg;
    }

    public String getCaSpndRank() {
        return caSpndRank;
    }

    public void setCaSpndRank(String caSpndRank) {
        this.caSpndRank = caSpndRank;
    }

    public String getCaAutoPayFlg() {
        return caAutoPayFlg;
    }

    public void setCaAutoPayFlg(String caAutoPayFlg) {
        this.caAutoPayFlg = caAutoPayFlg;
    }

    public String getCaBillArea() {
        return caBillArea;
    }

    public void setCaBillArea(String caBillArea) {
        this.caBillArea = caBillArea;
    }

    public List<DTagGroup> getdTagGroup() {
        return dTagGroup;
    }

    public void setdTagGroup(List<DTagGroup> dTagGroup) {
        this.dTagGroup = dTagGroup;
    }

    public List<CardTypGroup> getCardTypGroup() {
        return cardTypGroup;
    }

    public void setCardTypGroup(List<CardTypGroup> cardTypGroup) {
        this.cardTypGroup = cardTypGroup;
    }
}
