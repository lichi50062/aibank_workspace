package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIOverviewResponse;
import com.tfb.aibank.integration.eai.EAIOverviewTxBodyRsType;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.BKDCD001SvcRsType;
import tw.com.ibm.mf.eb.DCDBPBNKSvcRqType;
import tw.com.ibm.mf.eb.DCDBPBNKSvcRsType;
import tw.com.ibm.mf.eb.SPWEBQ2SvcRsType;

/**
 * <p>
 * Title: com.fubon.tw.pib.tr.message.DCDBPBNKRS
 * </p>
 * <p>
 * Description: DCDBPBNK 下行電文
 * </p>
 * <p>
 * Copyright: Copyright (c) IBM Corp. 2013. All Rights Reserved.
 * </p>
 * <p>
 * Company: IBM GBS Team
 * </p>
 *
 * @author Edward Tien
 * @version 1.0
 */
//@formatter:off
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                        " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur | eai:Tx/TxBody/OCCUR"),
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                        " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur | eai:Tx/TxBody/OCCUR")
        },
                decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class,fieldXPath = "eai:Tx/TxBody/TxRepeat/RefAmt | eai:Tx/TxBody/TxRepeat/DCDAMOUNTNTD" +
                        		" | eai:Tx/TxBody/TxRepeat/SPOTRATE | eai:Tx/TxBody/TxRepeat/STRIKE | eai:Tx/TxBody/TxRepeat/YIELD" +
                        		" | eai:Tx/TxBody/TxRepeat/DCDAMOUNT | eai:Tx/TxBody/TxRepeat/EXPIRYDATESPOTRATE | eai:Tx/TxBody/TxRepeat/DCDAMOUNTORI"),
                        @DecimalConverter(converter = EAIDecimalConverter.class,scale=2, fieldXPath = "eai:Tx/TxBody/TxRepeat/InvestAmt" +
                        		" | eai:Tx/TxBody/TxRepeat/TOTALFEE | eai:Tx/TxBody/TxRepeat/INTERESTAMOUNT" +
                        		" | eai:Tx/TxBody/TxRepeat/DELIVERYDATEAMOUNT | eai:Tx/TxBody/TxRepeat/RefAmtOri"),
                        @DecimalConverter(converter = EAIDecimalConverter.class,scale=6, fieldXPath = "eai:Tx/TxBody/TxRepeat/TradeAmt")
        }
)
//@formatter:on
public class DCDBPBNKRS extends EAIOverviewResponse<DCDBPBNKSvcRqType, DCDBPBNKSvcRsType> {
    /**
     * 建構子
     */
    public DCDBPBNKRS() {
        super("DCDBPBNK");
    }

    /**
     * 依欄位內容判斷是否為錯誤
     *
     * @return
     */
    @Override
    public boolean validateHERRID(EAIRequest<DCDBPBNKSvcRqType> request) {
        // get TXBody Arrays
        EAIOverviewTxBodyRsType[] txBodyRsTypes = getBodyArray();
        for (int i = 0; i < txBodyRsTypes.length; i++) {
            String txnId = txBodyRsTypes[i].getTxnId();
            if ("SPWEBQ2".equals(txnId)) {
                SPWEBQ2SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(SPWEBQ2SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // SP01, SP02 表示無資料, 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"SP01".equals(errMsgId) && !"SP02".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    return false;
                }
            }
            else if ("BKDCD001".equals(txnId)) {
                BKDCD001SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(BKDCD001SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // E005 表示無資料, 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"E005".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    return false;
                }
            }
        }
        return true;
    }

}
