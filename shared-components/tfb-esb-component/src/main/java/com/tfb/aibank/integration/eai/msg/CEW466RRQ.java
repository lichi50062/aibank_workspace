/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.CEW466RSvcRqType;

// @formatter:off
/**
 * @(#)CEW466RRQ.java
 * 
 * <p>Description:[會員自動續約]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */


@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/requestDate")
        }
)

// @formatter:on
public class CEW466RRQ extends EAIRequest<CEW466RSvcRqType> {

    private static final long serialVersionUID = -5197220306611047180L;

    /**
     * 建構子
     */
    public CEW466RRQ() {
        super("CEW466R");
    }
}
