/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import com.ibm.tw.commons.util.StringUtils;

import tw.com.ibm.mf.eai.TxBodyRqType;
import tw.com.ibm.mf.eai.TxBodyRsType;
import tw.com.ibm.mf.eai.TxRsType;

// @formatter:off
/**
 * @(#)EAIOverviewResponse.java
 * 
 * <p>Description:總覧電文回應類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAIOverviewResponse<T extends TxBodyRqType, V extends TxBodyRsType> extends EAIResponse<T, V> {

    private static final Log _logger = LogFactory.getLog(EAIOverviewResponse.class);

    /** xsi namespace */
    private static QName xsiQname = new QName("http://www.w3.org/2001/XMLSchema-instance", "type", "xsi");

    /** eb namespace */
    private static QName ebQname = new QName("http://www.ibm.com.tw/mf/eb", "eb", "xmlns");

    /**
     * body array
     */
    private EAIOverviewTxBodyRsType[] bodyArray = null;

    /**
     * body Map
     */
    private Map<String, List<EAIOverviewTxBodyRsType>> bodyMap = null;

    /**
     * validate 時的 error code (多筆 body 中, 其中一筆 error 即視為 error)
     */
    protected String errorCode;
    /**
     * validate 時的 error message (多筆 body 中, 其中一筆 error 即視為 error)
     */
    protected String errorMessage;

    /**
     * 建構子
     * 
     * @param txnId
     */
    protected EAIOverviewResponse(String txnId) {
        super(txnId);
    }

    /**
     * 設定並解析xml
     * 
     * @param xml
     * @throws XmlException
     */
    @Override
    void setRsXml(String xml) throws XmlException {
        // RS須加上xsi:type，讓xmlbeans看得懂
        XmlObject xmlObj = XmlObject.Factory.parse(xml);

        XmlCursor cursor = xmlObj.newCursor();
        cursor.selectPath("Tx/TxBody/HTXTID");
        while (cursor.toNextSelection()) {

            String txnId = cursor.getTextValue();
            if (cursor.toParent()) { // TxBody加上namespace
                cursor.setAttributeText(ebQname, "http://www.ibm.com.tw/mf/eb");
                cursor.setAttributeText(xsiQname, String.format("eb:%sSvcRsType", txnId));
            }

            cursor.toChild(0);
            while (cursor.toNextSibling()) {// TxRepeat加上namespace
                if (cursor.getName().toString().equals("TxRepeat")) {
                    cursor.setAttributeText(xsiQname, String.format("eb:%sRepeatType", txnId));
                }
            }
        }
        super.setRsXml(xmlObj.xmlText());
    }

    /**
     * 總覽電文有多個Body，此method不使用
     */
    @Override
    @Deprecated
    public V getBody() {
        throw new IllegalStateException("總覽電文請使用getBodyArry()取得資料");
    }

    /**
     * 取得電文Body
     * 
     * @return
     */
    public EAIOverviewTxBodyRsType[] getBodyArray() {
        if (bodyArray != null) {
            return bodyArray;
        }
        TxRsType rsType = (TxRsType) xmlDoc.getTx().changeType(TxRsType.type);
        TxBodyRsType[] allRs = rsType.getTxBodyArray();
        bodyArray = new EAIOverviewTxBodyRsType[allRs.length];
        for (int i = 0; i < allRs.length; i++) {

            // 取得實際Rs Type
            SchemaType schemaType = null;
            String bodyTxnId = allRs[i].getHTXTID();
            try {
                schemaType = (SchemaType) allRs[i].getClass().getField("type").get(null);
            }
            catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                _logger.warn("RS type for txnId: " + bodyTxnId + " not found");
                if (_logger.isDebugEnabled()) {
                    _logger.warn("stack trace: ", e);
                }
            }

            bodyArray[i] = new EAIOverviewTxBodyRsType(bodyTxnId, allRs[i], schemaType);
        }
        return bodyArray;
    }

    /**
     * 取得 bodyMap
     * 
     * @return 傳回 bodyMap。
     */
    public Map<String, List<EAIOverviewTxBodyRsType>> getBodyMap() {
        if (bodyMap != null) {
            return bodyMap;
        }
        EAIOverviewTxBodyRsType[] bodyArray = getBodyArray();
        bodyMap = new HashMap<>();
        for (int i = 0; i < bodyArray.length; i++) {
            EAIOverviewTxBodyRsType rsType = bodyArray[i];
            List<EAIOverviewTxBodyRsType> rsTypeList = bodyMap.get(rsType.getTxnId());
            if (rsTypeList == null) {
                rsTypeList = new ArrayList<>();
                bodyMap.put(rsType.getTxnId(), rsTypeList);
            }
            rsTypeList.add(rsType);
        }
        return bodyMap;
    }

    /**
     * 取得 errorCode
     * 
     * @return
     */
    @Override
    public String getErrorCode() {
        return StringUtils.isNotBlank(errorCode) ? errorCode : StringUtils.trimToEmptyEx(getHeader().getHERRID());
    }

    /**
     * 取得 errorMessage
     * 
     * @return 傳回 errorMessage。
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getErrorMsg() {
        if (StringUtils.isNotBlank(getErrorMessage())) {
            return getErrorMessage();
        }
        try {
            TxBodyRsType bodyRsType = ((TxRsType) getXmlDoc().getTx().changeType(TxRsType.type)).getTxBodyArray(0);
            return bodyRsType.getEMSGTXT();
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            _logger.warn("產生例外，不中止程序。");
            return null;
        }
    }
}
