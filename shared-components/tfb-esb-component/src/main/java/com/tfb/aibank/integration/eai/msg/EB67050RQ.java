package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.EB67050SvcRqType;

//@formatter:off
/**
* @(#)EB202674RQ.java
* 
* <p>Description:查詢ID項下Email上行電文處理</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
      customConverters = {
              @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 14, padChar = "0", fieldXPath = "eai:Tx/TxBody/ACNO_SA" )
      },
      datetimeConverters = {
              @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TXDATES | eai:Tx/TxBody/TXDATEE"),
      }
)
//@formatter:on
public class EB67050RQ extends EAIRequest<EB67050SvcRqType> {
    private static final long serialVersionUID = 3002651133177112438L;

    /**
     * 建構子
     */
    public EB67050RQ() {
        super("EB67050");
    }
}