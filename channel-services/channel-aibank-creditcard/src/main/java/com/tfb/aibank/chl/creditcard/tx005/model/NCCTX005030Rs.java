package com.tfb.aibank.chl.creditcard.tx005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCTX005030Rs.java
 * 
 * <p>Description:額度調整 030 財力證明上傳頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCTX005030Rs implements RsData {

    /** 調整項目 */
    private AdjustItemType adjustItem;
    /** 是否可略過財力證明 */
    private boolean skipProofResources;
    /** 檔案壓縮後的固定像素 */
    private long imageResize;

    public boolean isSkipProofResources() {
        return skipProofResources;
    }

    public void setSkipProofResources(boolean skipProofResources) {
        this.skipProofResources = skipProofResources;
    }

    public AdjustItemType getAdjustItem() {
        return adjustItem;
    }

    public void setAdjustItem(AdjustItemType adjustItem) {
        this.adjustItem = adjustItem;
    }

    public long getImageResize() {
        return imageResize;
    }

    public void setImageResize(long imageResize) {
        this.imageResize = imageResize;
    }

}
