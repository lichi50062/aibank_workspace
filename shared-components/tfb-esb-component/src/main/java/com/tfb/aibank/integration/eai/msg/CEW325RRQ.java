package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.CEW325RSvcRqType;
//@formatter:off
/**
* @(#)CEW321RRQ.java
* 
* <p>Description:產生超商繳款條碼</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/04, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Converter(
        decimalConverters = { @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/CustPayAmt", scale = 0, pattern = "00000000000000")
		}
)
public class CEW325RRQ extends EAIRequest<CEW325RSvcRqType> {
    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
	public CEW325RRQ()
	{
		super("CEW325R");
	}
	
}
