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
package com.tfb.aibank.chl.session.type;

import com.ibm.tw.commons.type.IEnum;
import com.tfb.aibank.common.type.CompanyKindType;

// @formatter:off
/**
 * @(#)ChannelIdType.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, John
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum ChannelIdType implements IEnum {

    eBank("0", "網銀"),

    WebATM("1", "網路ATM"),

    CST("2", "媒體薪轉"),

    ePay("3", "e化繳費網"),

    MobileBank("4", "行銀"),

    CardMember("5", "信用卡會員"),

    CES("6", "員工持股儲蓄信託"),

    FINI("7", "員工分紅認股信託FINI保管專區"),

    WebService("8", "Web Service"),

    AIBank("M", "AIBANK"),

    UNKNOWN("Z", "未知");

    private String channelId;
    private String memo;

    private ChannelIdType(String channelId, String memo) {
        this.channelId = channelId;
        this.memo = memo;
    }

    /**
     * 依 channelId 找出 Type
     * 
     * @param channelId
     * @return
     */
    public static ChannelIdType find(String channelId) {
        for (ChannelIdType channel : ChannelIdType.values()) {
            if (channel.getChannelId().equals(channelId)) {
                return channel;
            }
        }
        return ChannelIdType.UNKNOWN;
    }

    /**
     * 依 channelId 找出 Type
     * 
     * @param channelId
     * @return
     */
    public static ChannelIdType findByCompanyKind(int companyKind) {
        CompanyKindType companyKindType = CompanyKindType.find(companyKind);
        ChannelIdType channelIdType = ChannelIdType.UNKNOWN;
        switch (companyKindType) {
        case COMPANY:
        case PERSONAL:
            channelIdType = ChannelIdType.eBank;
            break;
        case PRIMARY:
        case SECONDARY:
            channelIdType = ChannelIdType.CardMember;
            break;
        case WEBATM:
            channelIdType = ChannelIdType.WebATM;
            break;
        case CES_COMPANY:
            channelIdType = ChannelIdType.CES;
            break;
        case FINI:
            channelIdType = ChannelIdType.FINI;
            break;
        }
        return channelIdType;
    }

    /**
     * 取得 channelId
     * 
     * @return 傳回 channelId。
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 取得 memo
     * 
     * @return 傳回 memo。
     */
    @Override
    public String getMemo() {
        return memo;
    }
}
