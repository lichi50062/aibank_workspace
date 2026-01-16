package com.tfb.aibank.biz.user.services.employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.BankEmployeeRepository;
import com.tfb.aibank.biz.user.services.employee.model.RTWEBP02ResRep;
import com.tfb.aibank.biz.user.services.employee.model.SavingTrustDetail;
import com.tfb.aibank.common.type.SavingDetailType;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.RTWEBP01RS;
import com.tfb.aibank.integration.eai.msg.RTWEBP02RS;

import tw.com.ibm.mf.eai.TxRepeatType;
import tw.com.ibm.mf.eb.RTWEBP010003RepeatType;
import tw.com.ibm.mf.eb.RTWEBP010004RepeatType;
import tw.com.ibm.mf.eb.RTWEBP010005RepeatType;
import tw.com.ibm.mf.eb.RTWEBP020001RepeatType;
import tw.com.ibm.mf.eb.RTWEBP02SvcRsType;

// @formatter:off
/**
 * @(#)EmpolyeeService.java
 * 
 * <p>Description:提供「行員」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/19, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EmpolyeeService {

    private EsbChannelGateway esbChannelGateway;

    private IdentityService identityService;

    private BankEmployeeRepository bankEmployeeRepository;

    public EmpolyeeService(EsbChannelGateway esbChannelGateway, IdentityService identityService, BankEmployeeRepository bankEmployeeRepository) {
        this.esbChannelGateway = esbChannelGateway;
        this.identityService = identityService;
        this.bankEmployeeRepository = bankEmployeeRepository;
    }

    /**
     * 使用者是否為員工
     */
    public boolean isEmployee(String custId) {
        String custIdEncrypted = identityService.encrypt(custId);
        Long employeeCnt = bankEmployeeRepository.countByEmployeeIdAndHrStatus(custIdEncrypted, "A");
        return employeeCnt > 0;
    }

    /**
     * 使用者是否為「退休」員工
     */
    public boolean isRetiredEmployee(String custId) {
        String custIdEncrypted = identityService.encrypt(custId);
        Long employeeCnt = bankEmployeeRepository.countByEmployeeIdAndHrStatus(custIdEncrypted, "I");
        return employeeCnt > 0;
    }

    public List<SavingTrustDetail> getSavingDetails(String custId, String userId, String nameCode, String beginYm, String endYm, String cmpId) throws XmlException, EAIException, EAIResponseException {
        List<RTWEBP01RS> rsList = esbChannelGateway.sendRTWEBP01(custId, userId, nameCode, beginYm, endYm, cmpId);
        List<SavingTrustDetail> savingTrustDetails = new ArrayList<>();
        for (RTWEBP01RS rs : rsList) {
            String herrid = rs.getHeader().getHERRID();
            if (StringUtils.equals(Constants.STATUS_CODE_SUCCESS, herrid)) {
                String hfmtid = rs.getHeader().getHFMTID();
                SavingDetailType savingDetailType = SavingDetailType.findByCode(hfmtid);
                List<TxRepeatType> repeatList = rs.getBody().getTxRepeatList();
                List<SavingTrustDetail> detailTypes = new ArrayList<>();
                switch (savingDetailType) {
                case DETAILS -> detailTypes = repeatList.stream().map(txRepeatType -> (RTWEBP010003RepeatType) txRepeatType.changeType(RTWEBP010003RepeatType.type)).map(SavingTrustDetail::new).collect(Collectors.toList());
                case ACCUMULATE -> detailTypes = repeatList.stream().map(txRepeatType -> (RTWEBP010004RepeatType) txRepeatType.changeType(RTWEBP010004RepeatType.type)).map(SavingTrustDetail::new).collect(Collectors.toList());
                case NET_VALUE -> detailTypes = repeatList.stream().map(txRepeatType -> (RTWEBP010005RepeatType) txRepeatType.changeType(RTWEBP010005RepeatType.type)).map(SavingTrustDetail::new).collect(Collectors.toList());
                }
                if (CollectionUtils.isNotEmpty(detailTypes))
                    savingTrustDetails.addAll(detailTypes);
            }
        }

        return savingTrustDetails;
    }

    /**
     * 員工持股信託公司查詢 所有公司
     *
     * @param custId
     * @param uidDup
     * @param userId
     * @param nameCode
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public List<RTWEBP02ResRep> getShareHoldCompanies(String custId, String userId, String nameCode) throws XmlException, EAIException, EAIResponseException {
        RTWEBP02RS rs = esbChannelGateway.sendRTWEBP02(custId, userId, nameCode);
        String herrid = rs.getHeader().getHERRID();
        String hfmtid = rs.getHeader().getHFMTID();
        if (StringUtils.notEquals(Constants.STATUS_CODE_SUCCESS, herrid) || StringUtils.notEquals("0001", hfmtid))
            return Collections.emptyList();

        RTWEBP02SvcRsType rsBody = rs.getBody();
        return rsBody.getTxRepeatList().stream().map(txRepeatType -> (RTWEBP020001RepeatType) txRepeatType.changeType(RTWEBP020001RepeatType.type)).map(RTWEBP02ResRep::new).collect(Collectors.toList());
    }

}
