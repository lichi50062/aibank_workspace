package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NGNQU001011Rq.java
 *
 * <p>Description: NGNQU001011Rq</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/11/15, MartyPan
 *  <li>[NGNQU001011Rq]</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001011Rq implements RqData {

    /** 暱稱 */
    private String nickname;

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     *            the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
