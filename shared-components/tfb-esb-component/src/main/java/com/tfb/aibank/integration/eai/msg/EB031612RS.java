package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB031612SvcRqType;
import tw.com.ibm.mf.eb.EB031612SvcRsType;

//@formatter:off
/**
 * @(#)EB031612RS.java
 * <pre>
 * Description:EB031612RS 下行電文
 *
 * Modify History:
 * v1.0, 2024/01/06, MP, 初版
 * </pre>
 */
//@formatter:on
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ProjName ")
        },decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class,scale=8, fieldXPath = "eai:Tx/TxBody/PbRate101 | eai:Tx/TxBody/PbRate102 | eai:Tx/TxBody/PbRate103 | eai:Tx/TxBody/PbRate104 | eai:Tx/TxBody/PbRate105 | eai:Tx/TxBody/PbRate106 | eai:Tx/TxBody/PbRate107 "
                        + "| eai:Tx/TxBody/PbRate108 | eai:Tx/TxBody/PbRate109 | eai:Tx/TxBody/PbRate110 | eai:Tx/TxBody/PbRate111 | eai:Tx/TxBody/PbRate112 | eai:Tx/TxBody/PbRate201 "
                        + "| eai:Tx/TxBody/PbRate202 | eai:Tx/TxBody/PbRate203 | eai:Tx/TxBody/PbRate204 | eai:Tx/TxBody/PbRate205 | eai:Tx/TxBody/PbRate206 | eai:Tx/TxBody/PbRate207 "
                        + "| eai:Tx/TxBody/PbRate208 | eai:Tx/TxBody/PbRate209 | eai:Tx/TxBody/PbRate210 | eai:Tx/TxBody/PbRate211 | eai:Tx/TxBody/PbRate212 | eai:Tx/TxBody/PbRate301 "
                        + "| eai:Tx/TxBody/PbRate302 | eai:Tx/TxBody/PbRate303 | eai:Tx/TxBody/PbRate304 | eai:Tx/TxBody/PbRate305 | eai:Tx/TxBody/PbRate306 | eai:Tx/TxBody/PbRate307 "
                        + "| eai:Tx/TxBody/PbRate308 | eai:Tx/TxBody/PbRate309 | eai:Tx/TxBody/PbRate310 | eai:Tx/TxBody/PbRate311 | eai:Tx/TxBody/PbRate312 | eai:Tx/TxBody/PbRate401 "
                        + "| eai:Tx/TxBody/PbRate402 | eai:Tx/TxBody/PbRate403 | eai:Tx/TxBody/PbRate404 | eai:Tx/TxBody/PbRate405 | eai:Tx/TxBody/PbRate406 | eai:Tx/TxBody/PbRate407 "
                        + "| eai:Tx/TxBody/PbRate408 | eai:Tx/TxBody/PbRate409 | eai:Tx/TxBody/PbRate410 | eai:Tx/TxBody/PbRate411 | eai:Tx/TxBody/PbRate412 | eai:Tx/TxBody/PbRate501 "
                        + "| eai:Tx/TxBody/PbRate502 | eai:Tx/TxBody/PbRate503 | eai:Tx/TxBody/PbRate504 | eai:Tx/TxBody/PbRate505 | eai:Tx/TxBody/PbRate506 | eai:Tx/TxBody/PbRate507 "
                        + "| eai:Tx/TxBody/PbRate508 | eai:Tx/TxBody/PbRate509 | eai:Tx/TxBody/PbRate510 | eai:Tx/TxBody/PbRate511 | eai:Tx/TxBody/PbRate512 | eai:Tx/TxBody/PbRate601 "
                        + "| eai:Tx/TxBody/PbRate602 | eai:Tx/TxBody/PbRate603 | eai:Tx/TxBody/PbRate604 | eai:Tx/TxBody/PbRate605 | eai:Tx/TxBody/PbRate606 | eai:Tx/TxBody/PbRate607 "
                        + "| eai:Tx/TxBody/PbRate608 | eai:Tx/TxBody/PbRate609 | eai:Tx/TxBody/PbRate610 | eai:Tx/TxBody/PbRate611 | eai:Tx/TxBody/PbRate612 | eai:Tx/TxBody/PbRate701 "
                        + "| eai:Tx/TxBody/PbRate702 | eai:Tx/TxBody/PbRate703 | eai:Tx/TxBody/PbRate704 | eai:Tx/TxBody/PbRate705 | eai:Tx/TxBody/PbRate706 | eai:Tx/TxBody/PbRate707 "
                        + "| eai:Tx/TxBody/PbRate708 | eai:Tx/TxBody/PbRate709 | eai:Tx/TxBody/PbRate710 | eai:Tx/TxBody/PbRate711 | eai:Tx/TxBody/PbRate712 | eai:Tx/TxBody/PbRate801 "
                        + "| eai:Tx/TxBody/PbRate802 | eai:Tx/TxBody/PbRate803 | eai:Tx/TxBody/PbRate804 | eai:Tx/TxBody/PbRate805 | eai:Tx/TxBody/PbRate806 | eai:Tx/TxBody/PbRate807 "
                        + "| eai:Tx/TxBody/PbRate808 | eai:Tx/TxBody/PbRate809 | eai:Tx/TxBody/PbRate810 | eai:Tx/TxBody/PbRate811 | eai:Tx/TxBody/PbRate812 | eai:Tx/TxBody/PbRate901 "
                        + "| eai:Tx/TxBody/PbRate902 | eai:Tx/TxBody/PbRate903 | eai:Tx/TxBody/PbRate904 | eai:Tx/TxBody/PbRate905 | eai:Tx/TxBody/PbRate906 | eai:Tx/TxBody/PbRate907 "
                        + "| eai:Tx/TxBody/PbRate908 | eai:Tx/TxBody/PbRate909 | eai:Tx/TxBody/PbRate910 | eai:Tx/TxBody/PbRate911 | eai:Tx/TxBody/PbRate912 | eai:Tx/TxBody/PbRate1001 "
                        + "| eai:Tx/TxBody/PbRate1002 | eai:Tx/TxBody/PbRate1003 | eai:Tx/TxBody/PbRate1004 | eai:Tx/TxBody/PbRate1005 | eai:Tx/TxBody/PbRate1006 | eai:Tx/TxBody/PbRate1007 "
                        + "| eai:Tx/TxBody/PbRate1008 | eai:Tx/TxBody/PbRate1009 | eai:Tx/TxBody/PbRate1010 | eai:Tx/TxBody/PbRate1011 | eai:Tx/TxBody/PbRate1012 ")}
)
public class EB031612RS extends EAIResponse<EB031612SvcRqType, EB031612SvcRsType> {
    /**
     * 建構子
     */
    public EB031612RS() {
        super("EB031612");
    }

    
}
