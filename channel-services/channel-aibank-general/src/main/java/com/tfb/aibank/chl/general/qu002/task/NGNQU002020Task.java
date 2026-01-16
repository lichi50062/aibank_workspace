package com.tfb.aibank.chl.general.qu002.task;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.model.LabelValueBean;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.branch.Branchmap;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002020Rq;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002020Rs;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002Output;
import com.tfb.aibank.chl.general.qu002.service.NGNQU002Service;

// @formatter:off
/**
 * @(#)NGNQU002020Task.java
 * 
 * <p>Description:服務據點 020 搜尋頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU002020Task extends AbstractAIBankBaseTask<NGNQU002020Rq, NGNQU002020Rs> {

    @Autowired
    private NGNQU002Service service;

    private NGNQU002Output dataOutput = new NGNQU002Output();

    @Override
    public void validate(NGNQU002020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU002020Rq rqData, NGNQU002020Rs rsData) throws ActionException {

        Locale userLocale = getLocale();

        // 取得BRANCHMAP
        service.getAllBranchmap(dataOutput);
        List<Branchmap> branchmapList = dataOutput.getBranchmapList();

        service.buildAreaOptions(userLocale, dataOutput);
        List<LabelValueBean> branchAreaComboItems = dataOutput.getAreaComboItems();
        rsData.setBranchArea(branchAreaComboItems);
        rsData.setAtmArea(branchAreaComboItems);

        service.buildAreaDetialMapOptions(branchmapList, 1, branchAreaComboItems, userLocale, dataOutput);
        rsData.setBranchAreaDetailMap(dataOutput.getAreaDetailComboItemsMap());

        service.buildAreaDetialMapOptions(branchmapList, 2, branchAreaComboItems, userLocale, dataOutput);
        rsData.setAtmAreaDetailMap(dataOutput.getAreaDetailComboItemsMap());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
