package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW304RSvcRqType;

//@formatter:off
/**
 * @(#)CEW304RRQ.java
 *
 * <p>Description:本期帳單剩餘應繳金額</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/05/13, David Huang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW304RRQ extends EAIRequest<CEW304RSvcRqType> {

    private static final long serialVersionUID = 6978668307459440180L;

    /**
     * 建構子
     */
    public CEW304RRQ() {
        super("CEW304R");
    }
}
