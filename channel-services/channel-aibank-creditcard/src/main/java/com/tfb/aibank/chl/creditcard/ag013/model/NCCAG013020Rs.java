package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013020Rs.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 020 同意條款</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG013020Rs implements RsData {

    /**
     * 約定條款標題
     */
    private String title;

    /**
     * 約定條款內文
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
