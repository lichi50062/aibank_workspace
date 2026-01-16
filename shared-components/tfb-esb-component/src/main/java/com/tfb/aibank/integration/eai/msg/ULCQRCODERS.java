/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.ULCQRCODESvcRqType;
import tw.com.ibm.mf.eb.ULCQRCODESvcRsType;

// @formatter:off
/**
 * @(#)ULCQRCODERS.java
 * 
 * <p>Description:金融卡解鎖序號下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*"
                        + " | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/MasterAct | eai:Tx/TxBody/CardNo | eai:Tx/TxBody/CwdTxnSeq | eai:Tx/TxBody/BankNameChin | eai:Tx/TxBody/BankName")
        }
)
// @formatter:on
public class ULCQRCODERS extends EAIResponse<ULCQRCODESvcRqType, ULCQRCODESvcRsType> {

    /**
     * 建構子
     */
    public ULCQRCODERS() {
        super("ULCQRCODE");
    }

}
