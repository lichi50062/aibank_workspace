package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB553911ASvcRqType;
//@formatter:off
/**
* @(#)EB553911ARQ.java
* 
* <p>Description:開零存整付帳號</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/11/01, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
 */
@Converter(
        customConverters = { @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*"), @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 14, fieldXPath = "eai:Tx/TxBody/ACNO_OUT")
        },
        decimalConverters = { @DecimalConverter(converter = EAIDecimalConverter.class, scale = -3, pattern = "00000000000000000", isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx/TxBody/TX_AMT")
        }
)
//@formatter:on
public class EB553911ARQ extends EAIRequest<EB553911ASvcRqType> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 建構子
     */
    public EB553911ARQ() {
        super("EB553911A");
    }
}
