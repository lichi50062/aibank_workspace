package com.tfb.aibank.chl.general.qu001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001040Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001040Rs;
import com.tfb.aibank.chl.general.qu001.service.NGNQU001Service;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNQU001040Task.java
 *
 * <p>Description: 首頁-常用功能設定</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyP
 *  <li>有登入時才可使用</li>
 * </ol>
 */
//@formatter:on
@Component
@Scope("prototype")
public class NGNQU001040Task extends AbstractAIBankBaseTask<NGNQU001040Rq, NGNQU001040Rs> {

    @Autowired
    @Qualifier("NGNQU001Service")
    private NGNQU001Service service;

    @Override
    public void validate(NGNQU001040Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNQU001040Rq rqData, NGNQU001040Rs rsData) throws ActionException {

        DataInput input = new DataInput();
        input.setLocale(getLocale());
        DataOutput output = new DataOutput();

        if (isLoggedIn()) {
            AIBankUser aiBankUser = getLoginUser();
            service.queryUsualTasks(aiBankUser, input, output, getAppVer());
            service.getMenusForUserSetting(aiBankUser, input, output, getMenuCategory());
            service.getMenusForUserSearching(aiBankUser, input, output, getAppVer(), getMenuCategory());
        }
        else if (!isLoggedIn()) {
            if (rqData.isHaveDeviceRecord()) {
                service.getMenusForSearchingNotLogin(input, output, getAppVer());
            }
            else {
                service.getDefaultUsualTasks(input, output, getAppVer());
            }

        }

        setRsData(output, rsData);
    }

    private void setRsData(DataOutput output, NGNQU001040Rs rsData) {
        rsData.setUsualTasks(output.getUsualTasks());
        rsData.setMenusForSetting(output.getMenusForSetting());
        rsData.setMenusForSearching(output.getMenusForSearching());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
