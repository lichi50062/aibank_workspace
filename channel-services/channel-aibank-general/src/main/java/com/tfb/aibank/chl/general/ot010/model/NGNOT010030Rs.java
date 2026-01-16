package com.tfb.aibank.chl.general.ot010.model;

import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

//@formatter:off

/**
 * @(#)NGNOT010030Rs.java
 *
 * <p>Description: 註冊分流頁</p>
 *
 */
//@formatter:on
@Component
public class NGNOT010030Rs implements RsData {

    /**
     * 智能客服
     */
    private String chatBotUrl;

    public String getChatBotUrl() {
        return chatBotUrl;
    }

    public void setChatBotUrl(String chatBotUrl) {
        this.chatBotUrl = chatBotUrl;
    }

}
