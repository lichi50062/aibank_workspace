package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB172650SvcRqType;
import tw.com.ibm.mf.eb.EB172650SvcRsType;

// @formatter:off
/**
 * @(#)EB172650RS.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
            @CustomConverter(
                    converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/ACNO_FS | eai:Tx/TxBody/FS_NO | eai:Tx/TxBody/NAME_COD | eai:Tx/TxBody/NAME1"
                            + " | eai:Tx/TxBody/ADDR_COD1 | eai:Tx/TxBody/ADDR_1 | eai:Tx/TxBody/ADDR_2 | eai:Tx/TxBody/ADDR_COD2 | eai:Tx/TxBody/ADDR_3"
                            + " | eai:Tx/TxBody/ADDR_4 | eai:Tx/TxBody/TELD_COD | eai:Tx/TxBody/TELD_NO | eai:Tx/TxBody/TELD_EXT | eai:Tx/TxBody/TELN_COD"
                            + " | eai:Tx/TxBody/TELN_NO | eai:Tx/TxBody/TELN_EXT | eai:Tx/TxBody/E_MAIL | eai:Tx/TxBody/MOBIL_PHONE | eai:Tx/TxBody/IDNO"
                            + " | eai:Tx/TxBody/RESP_ID | eai:Tx/TxBody/FAX_COD | eai:Tx/TxBody/FAXNO | eai:Tx/TxBody/IDU_COD | eai:Tx/TxBody/CTRY_COD"
                            + " | eai:Tx/TxBody/ORG_TYPE | eai:Tx/TxBody/CUST_TYPE | eai:Tx/TxBody/TAX_COD | eai:Tx/TxBody/TAX_TYPE | eai:Tx/TxBody/TX_BRH"
                            + " | eai:Tx/TxBody/TX_BRH1 | eai:Tx/TxBody/OPN_DATE | eai:Tx/TxBody/VISA_ISUDATE | eai:Tx/TxBody/SEAL_BRH | eai:Tx/TxBody/FC_IDNO"
                            + " | eai:Tx/TxBody/CLOSE_DATE | eai:Tx/TxBody/YEAR_H_FEE | eai:Tx/TxBody/VISA_ENDDATE | eai:Tx/TxBody/NAME_STS1 | eai:Tx/TxBody/NAME_STS2"
                            + " | eai:Tx/TxBody/YEAR_TAX_L | eai:Tx/TxBody/YEAR_TAX_C | eai:Tx/TxBody/YEAR_INT_L | eai:Tx/TxBody/FS_LT_BAL | eai:Tx/TxBody/FD_ACNO"
                            + " | eai:Tx/TxBody/WARN_MSG | eai:Tx/TxBody/BLOCK | eai:Tx/TxBody/TBR_BRH | eai:Tx/TxBody/TBR_ID | eai:Tx/TxBody/ACCUM_PRD1 | eai:Tx/TxBody/ACCUM_PRD2"
                            + " | eai:Tx/TxBody/ACCUM_PRD3 | eai:Tx/TxBody/ACCUM_PRD4 | eai:Tx/TxBody/ACCUM_PRD5 | eai:Tx/TxBody/ACCUM_PRD6 | eai:Tx/TxBody/ACCUM_PRD7"
                            + " | eai:Tx/TxBody/TxRepeat/* ")
        }
)
// @formatter:on
public class EB172650RS extends EAIResponse<EB172650SvcRqType, EB172650SvcRsType> {

    public EB172650RS() {
        super("EB172650");
    }
}
