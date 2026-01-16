package com.tfb.aibank.chl.creditcard.tx001.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.branch.BankBranchCacheManager;
import com.tfb.aibank.chl.component.branch.BankBranchData;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001021Rq;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001021Rs;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001BranchInfo;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001Service;

//@formatter:off
/**
* @(#)NCCTX001021Task.java
*
* <p>Description:預借現金 分行查詢 </p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCTX001021Task extends AbstractAIBankBaseTask<NCCTX001021Rq, NCCTX001021Rs> {
    @Autowired
    protected NCCTX001Service service;

    @Autowired
    private BankBranchCacheManager bankBranchCacheManager;

    @Override
    public void validate(NCCTX001021Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NCCTX001021Rq rqData, NCCTX001021Rs rsData) throws ActionException {

        Map<String, BankBranchData> branchs = bankBranchCacheManager.getBranchbyBank(rqData.getBankId(), getLocale().toString());

        List<NCCTX001BranchInfo> branchInfo = new ArrayList<NCCTX001BranchInfo>();

        NCCTX001BranchInfo placeholder = new NCCTX001BranchInfo();
        placeholder.setBranchId("0");
        placeholder.setBranchName(I18NUtils.getMessage("ncc.tx001.choosebranch"));
        branchInfo.add(placeholder);

        for (Map.Entry<String, BankBranchData> entry : branchs.entrySet()) {

            NCCTX001BranchInfo branch = new NCCTX001BranchInfo();
            branch.setBranchId(entry.getValue().getBranchId());
            branch.setBranchName(entry.getValue().getBranchId() + " " + entry.getValue().getBranchName());
            branchInfo.add(branch);
        }

        branchInfo.sort(Comparator.comparing(NCCTX001BranchInfo::getBranchId, (s1, s2) -> {
            long d1 = 0;
            long d2 = 0;
            try {
                d1 = Long.parseLong(s1);
                d2 = Long.parseLong(s2);
            }
            catch (NumberFormatException ex) {
                logger.warn("產生例外，不影響程序。");
                logger.error(ex.getMessage(), ex);
            }

            return Long.compare(d1, d2);
        }));

        rsData.setBranchInfo(branchInfo);
    }

}