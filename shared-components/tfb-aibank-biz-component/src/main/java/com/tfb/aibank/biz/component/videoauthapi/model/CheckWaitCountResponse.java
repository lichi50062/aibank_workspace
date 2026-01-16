package com.tfb.aibank.biz.component.videoauthapi.model;

// @formatter:off
/**
 * @(#)CheckWaitCountResponse.java
 * 
 * <p>Description:[視訊 api : checkWaitCount 等待人數資訊]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CheckWaitCountResponse extends VideoAuthApiRsBase {

    /**
     * 線上等待人數 (單位：人數順位)
     */
    private int QueueLength;

    /**
     * @return the queueLength
     */
    public int getQueueLength() {
        return QueueLength;
    }

    /**
     * @param queueLength
     *            the queueLength to set
     */
    public void setQueueLength(int queueLength) {
        QueueLength = queueLength;
    }

}
