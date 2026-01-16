package com.tfb.aibank.chl.creditcard.tx005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCTX005040Rq.java
 * 
 * <p>Description:額度調整 040 確認頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCTX005040Rq implements RqData {

    /** 調整項目，1:調高、2:調降、3:附卡額度 */
    private String adjustItem;

    /** 是否可略過財力證明 */
    private boolean skipProofResources;

    public boolean isSkipProofResources() {
        return skipProofResources;
    }

    public void setSkipProofResources(boolean skipProofResources) {
        this.skipProofResources = skipProofResources;
    }

    public String getAdjustItem() {
        return adjustItem;
    }

    public void setAdjustItem(String adjustItem) {
        this.adjustItem = adjustItem;
    }

}
