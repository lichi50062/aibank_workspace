package com.tfb.aibank.integration.eai.msg;



import com.tfb.aibank.integration.eai.EAIRequest;import tw.com.ibm.mf.eb.EB062171SvcRqType;
// @formatter:off
/**
 * @(#)EB062171RQ.java
 *
 * <p>Description:EB062171RQ(撥貸紀錄查詢) 上行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/01/28,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB062171RQ extends EAIRequest<EB062171SvcRqType> {
    /**
     * 建構子
     */
    public EB062171RQ() {
        super("EB062171");
    }
}
