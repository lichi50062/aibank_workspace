package com.tfb.aibank.biz.user.services.bankingunit;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.services.depositassets.model.EB555692Request;
import com.tfb.aibank.common.type.CompanyBUType;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB555692RS;
import com.tfb.aibank.integration.eai.msg.NF032333RS;

import tw.com.ibm.mf.eai.TxRepeatType;
import tw.com.ibm.mf.eb.EB555692RepeatType;
import tw.com.ibm.mf.eb.EB555692SvcRsType;
import tw.com.ibm.mf.eb.NF032333SvcRsType;

/**
 // @formatter:off
 * @(#)BankingUnitService.java
 * 
 * <p>Description: 判斷使用者為 DBU/OBU 的Service</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/1, Marty Pan
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BankingUnitService {

    private static final IBLog logger = IBLog.getLog(BankingUnitService.class);
    private EsbChannelGateway esbGateway;

    public BankingUnitService(EsbChannelGateway esbGateway) {
        this.esbGateway = esbGateway;
    }

    public String getBuType(String custId, String nameCode, String acctNo) throws XmlException, EAIException, EAIResponseException, ActionException {
        logger.debug("custId: {}, nameCode: {}, acctNo: {}", custId, nameCode, acctNo);
        if (StringUtils.isNoneEmpty(custId, nameCode)) {
            return getUserBUTypeByCustIdAndNamecode(custId, nameCode);
        }
        else if (StringUtils.isNotEmpty(acctNo)) {
            return getUserBUTypeByAccount(acctNo);
        }
        return "";
    }

    /**
     * 傳入帳號，判斷BU類型
     * 
     * @throws ActionException
     */
    public String getUserBUTypeByAccount(String accountNo) throws ActionException {
        return checkBUTypeByBranchId(getFullBranchId(accountNo));
    }

    /**
     * 傳入統編(身分證號), nameCode，判斷BU類型
     * 
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     * @throws ActionException
     */
    public String getUserBUTypeByCustIdAndNamecode(String custId, String nameCode) throws XmlException, EAIException, EAIResponseException, ActionException {
        EB555692Request request = new EB555692Request();
        request.setHTLID("2004115");
        request.setFunction("06");
        request.setType("7");
        request.setCustId(custId);
        request.setNameCode(nameCode);
        EB555692RS esbRs = this.esbGateway.sendEB555692(request);
        EB555692SvcRsType rsBody = esbRs.getBodyAsType(EB555692SvcRsType.class);

        List<TxRepeatType> txRepeatTypeList = rsBody.getTxRepeatList();

        String buType = "";

        if (CollectionUtils.isNotEmpty(txRepeatTypeList)) {
            // TXRepet有值，取任一筆帳號
            EB555692RepeatType repeatType = (EB555692RepeatType) txRepeatTypeList.get(0).changeType(EB555692RepeatType.type);
            buType = checkBUTypeByBranchId(getFullBranchId(repeatType.getACNO()));
        }
        return buType;
    }

    /**
     * 取得存款帳號完整分行別(5碼)，適用場景應為後台批次或未發EB5557891取得存款帳號列表的功能
     * 
     * @return
     */
    private String getFullBranchId(String acctNo) {
        String branchId = StringUtils.EMPTY;
        try {
            NF032333RS response = esbGateway.sendNF032333(acctNo);
            // 取得下行電文內容
            NF032333SvcRsType rsType = response.getBody();
            branchId = StringUtils.trimToEmptyEx(rsType.getBrno());
        }
        catch (XmlException | EAIException | EAIResponseException e) {
            logger.warn("sendNF032333 err: {}", e.getMessage());
        }
        return branchId;
    }

    /**
     * 依分行代碼判斷DBU/OBU
     * 
     * @throws ActionException
     */
    private String checkBUTypeByBranchId(String branchId) throws ActionException {
        // 五碼分行別的第二碼是5，則為OBU，否則是DBU
        if (StringUtils.isNotEmpty(branchId) && branchId.length() == 5) {
            String keyForOBU = branchId.substring(1, 2);
            if (keyForOBU.equals("5")) {
                return CompanyBUType.OBU.name();
            }
            else {
                return CompanyBUType.DBU.name();
            }
        }
        else {
            throw new ActionException("Branch ID length not equals 5", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
    }
}