package com.tfb.aibank.chl.system.ot010.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NSTOT010020Rs.java
* 
* <p>Description:數位客服</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT010020Rs implements RsData {

    /**
     * 數位客服網址
     */
    private String digitalChatUrl;

    /**
     * 網域白名單
     */
    private List<String> whiteListWhenOpenUrl;

    /**
     * @return the digitalChatUrl
     */
    public String getDigitalChatUrl() {
        return digitalChatUrl;
    }

    /**
     * @param digitalChatUrl
     *            the digitalChatUrl to set
     */
    public void setDigitalChatUrl(String digitalChatUrl) {
        this.digitalChatUrl = digitalChatUrl;
    }

    /**
     * @return the whiteListWhenOpenUrl
     */
    public List<String> getWhiteListWhenOpenUrl() {
        return whiteListWhenOpenUrl;
    }

    /**
     * @param whiteListWhenOpenUrl
     *            the whiteListWhenOpenUrl to set
     */
    public void setWhiteListWhenOpenUrl(List<String> whiteListWhenOpenUrl) {
        this.whiteListWhenOpenUrl = whiteListWhenOpenUrl;
    }

}
