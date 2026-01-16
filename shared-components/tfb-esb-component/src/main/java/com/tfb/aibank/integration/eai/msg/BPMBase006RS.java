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

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIOverviewResponse;
import com.tfb.aibank.integration.eai.EAIOverviewTxBodyRsType;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.type.OverViewSubErrorType;

import tw.com.ibm.mf.eai.TxBodyRqType;
import tw.com.ibm.mf.eai.TxBodyRsType;
import tw.com.ibm.mf.eai.TxRsType;
import tw.com.ibm.mf.eb.BKDCD001SvcRsType;
import tw.com.ibm.mf.eb.BKDCD002SvcRsType;
import tw.com.ibm.mf.eb.CEW304RSvcRsType;
import tw.com.ibm.mf.eb.CEW313RSvcRsType;
import tw.com.ibm.mf.eb.EB120140RepeatType;
import tw.com.ibm.mf.eb.EB120140SvcRsType;
import tw.com.ibm.mf.eb.EB555692SvcRsType;
import tw.com.ibm.mf.eb.EB555789SvcRsType;
import tw.com.ibm.mf.eb.GD320140SvcRsType;
import tw.com.ibm.mf.eb.NMP8YBSvcRsType;
import tw.com.ibm.mf.eb.SPWEBINQSvcRsType;
import tw.com.ibm.mf.eb.SPWEBQ1SvcRsType;
import tw.com.ibm.mf.eb.SPWEBQ2SvcRsType;

//@formatter:off
/**
* @(#)BPMBase006RS.java
* 
* <p>Description: BPM總覽電文 擴充RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/09/09, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class BPMBase006RS<T extends TxBodyRqType, V extends TxBodyRsType> extends EAIOverviewResponse<T, V> {

    private static IBLog logger = IBLog.getLog(BPMBase006RS.class);

    public BPMBase006RS(String txnId) {
        super(txnId);
    }

    private String errorTxId = "";

    @Override
    public boolean validateHERRID(EAIRequest<T> request) throws EAIException {
        boolean result = true;
        EAIOverviewTxBodyRsType[] txBodyRsTypes = getBodyArray();
        for (int i = 0; i < txBodyRsTypes.length; i++) {
            String txnId = txBodyRsTypes[i].getTxnId();
            if ("EB555692".equals(txnId)) {
                EB555692SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(EB555692SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // E005, EBB2 表示無資料, 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("SPWEBQ1".equals(txnId)) {
                SPWEBQ1SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(SPWEBQ1SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // SP01, SP02 表示無資料, 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"SP01".equals(errMsgId) && !"SP02".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("BKDCD002".equals(txnId)) {
                BKDCD002SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(BKDCD002SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // E005, EBB2 表示無資料, 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("EB120140".equals(txnId)) {
                EB120140SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(EB120140SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                if (StringUtils.isNotBlank(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
                else {
                    // EB120140 直接看第一筆
                    EB120140RepeatType repeatType = (EB120140RepeatType) svcRsType.getTxRepeatArray(0).changeType(EB120140RepeatType.type);
                    errMsgId = repeatType.getMSGCOD();
                    // 0000 & E005 & EBB2 視為正常回應, 其餘則視為錯誤
                    if (StringUtils.isNotBlank(errMsgId) && !"0000".equals(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId)) {
                        this.errorCode = errMsgId;
                        this.errorMessage = repeatType.getMSGDESC();
                        this.errorTxId = txnId;
                        result = false;
                    }
                }
            }
            else if ("EB555789".equals(txnId)) {
                EB555789SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(EB555789SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // E005, EBB2 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("CEW313R".equals(txnId)) {
                CEW313RSvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(CEW313RSvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // E005, EBB2, V592, V803, V829 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"X105".equals(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId) && !"V592".equals(errMsgId) && !"V803".equals(errMsgId) && !"V829".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("CEW304R".equals(txnId)) {
                CEW304RSvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(CEW304RSvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // E005, EBB2, V592, V803, V829 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"X105".equals(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId) && !"V592".equals(errMsgId) && !"V803".equals(errMsgId) && !"V829".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("NMP8YB".equals(txnId)) {
                // FOR dev PassBy use 2023/06/27
                // PASSBY
                NMP8YBSvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(NMP8YBSvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // X105 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"X105".equals(errMsgId) && !"B001".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("BKDCD001".equals(txnId)) {
                BKDCD001SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(BKDCD001SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                if (StringUtils.isNotBlank(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("SPWEBQ2".equals(txnId)) {
                SPWEBQ2SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(SPWEBQ2SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                if (StringUtils.isNotBlank(errMsgId) && !"SP01".equals(errMsgId) && !"SP02".equals(errMsgId)) {
                    this.errorCode = errorTxId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("GD320140".equals(txnId)) {
                GD320140SvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(GD320140SvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                if (StringUtils.isNotBlank(errMsgId) && !"E001".equals(errMsgId)) {
                    this.errorCode = errorTxId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else if ("SPWEBINQ".equals(txnId)) {
                SPWEBINQSvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(SPWEBINQSvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                if (StringUtils.isNotBlank(errMsgId) && !"SP01".equals(errMsgId) && !"SP02".equals(errMsgId)) {
                    this.errorCode = errorTxId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
            else {
                // 其它
                TxBodyRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(TxBodyRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // 不為空白則視為錯誤
                if (StringUtils.isNotBlank(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }
        }
        if (!result)
            return result;

        if (!super.validateHERRID(request)) {
            this.errorTxId = OverViewSubErrorType.BPM.name();
            this.errorCode = getHeader().getHERRID();
            try {
                TxBodyRsType bodyRsType = ((TxRsType) getXmlDoc().getTx().changeType(TxRsType.type)).getTxBodyArray(0);
                if (StringUtils.isNotBlank(bodyRsType.getEMSGID())) {
                    this.errorCode = StringUtils.trimToEmptyEx(bodyRsType.getEMSGID());
                }
                this.errorMessage = StringUtils.defaultIfEmpty(bodyRsType.getEMSGTXT(), this.errorCode);
            }
            catch (Exception e) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
                // 無法取得 body 訊息
                logger.error("無法取得 body 訊息", e);
            }
            result = false;
        }

        return result;
    }

    public String getErrorTxId() {
        return errorTxId;
    }

    public void setErrorTxId(String errorTxId) {
        this.errorTxId = errorTxId;
    }
}
