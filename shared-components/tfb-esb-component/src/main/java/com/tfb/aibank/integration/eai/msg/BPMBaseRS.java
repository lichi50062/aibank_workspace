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
import tw.com.ibm.mf.eb.BKDCD002SvcRsType;
import tw.com.ibm.mf.eb.CEW304RSvcRsType;
import tw.com.ibm.mf.eb.CEW313RSvcRsType;
import tw.com.ibm.mf.eb.EB120140RepeatType;
import tw.com.ibm.mf.eb.EB120140SvcRsType;
import tw.com.ibm.mf.eb.EB555692SvcRsType;
import tw.com.ibm.mf.eb.EB555789SvcRsType;
import tw.com.ibm.mf.eb.NMP8YBSvcRsType;
import tw.com.ibm.mf.eb.SPWEBQ1SvcRsType;

public class BPMBaseRS<T extends TxBodyRqType, V extends TxBodyRsType> extends EAIOverviewResponse<T, V> {
    private static IBLog logger = IBLog.getLog(BPMBaseRS.class);

    public BPMBaseRS(String txnId) {
        super(txnId);
    }

    private String errorTxId = "";

    /**
     * 依欄位內容判斷是否為錯誤
     * 
     * <pre>
     *      <EMSGID> != “”
     *      <HTXTID>= EB555692 及<EMSGID>=E005
     *      <HTXTID>= SPWEBQ1及<EMSGID>=SP01、SP02
     *      <HTXTID>=BKDCD002及<EMSGID>=E005
     *      <HTXTID>=EB120140及<MSG_COD>=0000
     * </pre>
     * 
     * @return
     * @throws EAIException
     */
    @Override
    public boolean validateHERRID(EAIRequest<T> request) throws EAIException {
        boolean result = true;
        // get TXBody Arrays
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
                if (StringUtils.isNotBlank(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId) && !"V592".equals(errMsgId) && !"V803".equals(errMsgId) && !"V829".equals(errMsgId)) {
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
                if (StringUtils.isNotBlank(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId) && !"V592".equals(errMsgId) && !"V803".equals(errMsgId) && !"V829".equals(errMsgId)) {
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
                if (StringUtils.isNotBlank(errMsgId) && !"X105".equals(errMsgId)) {
                    this.errorCode = errMsgId;
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
        if (!result) {
            return result;
        }
        // 最後再檢查 HERRID
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

    /**
     * 取得 errorTxId
     * 
     * @return 傳回 errorTxId。
     */
    public String getErrorTxId() {
        return errorTxId;
    }

    /**
     * 設定 errorTxId
     * 
     * @param errorTxId
     *            要設定的 errorTxId。
     */
    public void setErrorTxId(String errorTxId) {
        this.errorTxId = errorTxId;
    }
}