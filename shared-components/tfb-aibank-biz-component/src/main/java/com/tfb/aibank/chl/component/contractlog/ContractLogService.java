/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.contractlog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)ContractLogService.java
 * 
 * <p>Description:提供紀錄「審閱條款」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class ContractLogService {

    @Autowired
    private ContractLogResource contractLogResource;

    /**
     * 單筆新增「審閱條款」
     * 
     * @param contractLog
     * @return
     */
    public ContractLog saveContractLog(ContractLog contractLog, AIBankUser aibankUser) {
        String custId = aibankUser.getCustId();
        String uidDup = aibankUser.getUidDup();
        String userId = aibankUser.getUserId();
        Integer companyKind = aibankUser.getCompanyKindType().getCode();
        return contractLogResource.saveContractLog(contractLog, custId, uidDup, userId, companyKind);
    }

    /**
     * 多筆新增「審閱條款」
     * 
     * @param contractLogs
     * @return
     */
    public List<ContractLog> saveContractLog(List<ContractLog> contractLogs, AIBankUser aibankUser) {
        String custId = aibankUser.getCustId();
        String uidDup = aibankUser.getUidDup();
        String userId = aibankUser.getUserId();
        Integer companyKind = aibankUser.getCompanyKindType().getCode();
        return contractLogResource.saveContractLog(contractLogs, custId, uidDup, userId, companyKind);
    }

}
