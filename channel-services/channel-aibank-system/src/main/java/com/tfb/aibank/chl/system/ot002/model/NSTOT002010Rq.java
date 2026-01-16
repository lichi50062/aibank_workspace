package com.tfb.aibank.chl.system.ot002.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT002010Rq.java
 * 
 * <p>Description:元件功能 010 讀取「輔助說明」、「備註」</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT002010Rq implements RqData {

    /** 類型，1:輔助說明；2:備註；3:條款 */
    private String type;

    /** 文案鍵值 */
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
