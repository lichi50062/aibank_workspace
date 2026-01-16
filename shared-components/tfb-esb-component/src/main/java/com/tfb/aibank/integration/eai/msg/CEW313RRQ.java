package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW313RSvcRqType;

//@formatter:off
/**
 * @(#)CEW313RRQ.java
 *
 * <p>Description:本期帳單剩餘應繳金額</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/07/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW313RRQ extends EAIRequest<CEW313RSvcRqType> {

    private static final long serialVersionUID = 6978668307459440180L;

    /**
     * 建構子
     */
    public CEW313RRQ() {
        super("CEW313R");
    }
}
