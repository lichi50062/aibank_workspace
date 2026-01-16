package com.tfb.aibank.integration.eai.msg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIOverviewResponse;
import com.tfb.aibank.integration.eai.EAIOverviewTxBodyRsType;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.type.OverViewSubErrorType;

import tw.com.ibm.mf.eai.TxBodyRqType;
import tw.com.ibm.mf.eai.TxBodyRsType;
import tw.com.ibm.mf.eai.TxRsType;
import tw.com.ibm.mf.eb.CEW304RSvcRsType;
import tw.com.ibm.mf.eb.CEW313RSvcRsType;

public class BPMBaseCCRS<T extends TxBodyRqType, V extends TxBodyRsType> extends EAIOverviewResponse<T, V> {

    private static final Log _logger = LogFactory.getLog(BPMBaseCCRS.class);

    public BPMBaseCCRS(String txnId) {
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
            if ("CEW313R".equals(txnId)) {
                CEW313RSvcRsType svcRsType = txBodyRsTypes[i].getTxBodyRs(CEW313RSvcRsType.class);
                String errMsgId = svcRsType.getEMSGID();
                // E005, EBB2, V592, V803, V829 視為正常回應, 其餘則視為錯誤
                if (StringUtils.isNotBlank(errMsgId) && !"E005".equals(errMsgId) && !"EBB2".equals(errMsgId) && !"V592".equals(errMsgId) && !"V803".equals(errMsgId) && !"V829".equals(errMsgId)) {
                    this.errorCode = errMsgId;
                    this.errorMessage = svcRsType.getEMSGTXT();
                    this.errorTxId = txnId;
                    result = false;
                }
            }else if ("CEW304R".equals(txnId)) {
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
            catch (IllegalArgumentException ex) {
                _logger.error(ex.getMessage(), ex);
            }
            catch (Exception e) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外，取得詳細資訊
                // 無法取得 body 訊息
                _logger.warn("無法取得 Body 訊息");
                _logger.error(e.getMessage(), e);
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