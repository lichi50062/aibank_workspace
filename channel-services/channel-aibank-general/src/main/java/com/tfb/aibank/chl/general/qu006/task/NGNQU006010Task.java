package com.tfb.aibank.chl.general.qu006.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu006.model.NGNQU006010Rq;
import com.tfb.aibank.chl.general.qu006.model.NGNQU006010Rs;
import com.tfb.aibank.chl.general.qu006.model.NGNQU006Output;
import com.tfb.aibank.chl.general.qu006.service.NGNQU006Service;

// @formatter:off
/**
 * @(#)NGNQU006010Task.java
 * 
 * <p>Description:設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NGNQU006010Task extends AbstractAIBankBaseTask<NGNQU006010Rq, NGNQU006010Rs> {

    @Autowired
    private NGNQU006Service service;

    private NGNQU006Output dataOutput = new NGNQU006Output();

    @Override
    public void validate(NGNQU006010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU006010Rq rqData, NGNQU006010Rs rsData) throws ActionException {
        // 組成選單
        service.buildOptionList(getLoginUser(), getLocale(), getAppVer(), dataOutput);
        // 若為登入狀態，功能若為「快速登入」、「通知訊息」、「免登速查」、「動態密碼(MOTP)」、「螢幕截圖設定」需顯示選項狀態
        if (isLoggedIn()) {
            logger.info("本裝置識別碼：{}", this.getRequest().getDeviceIxd());
            service.setOptionStatus(getLoginUser(), getLocale(), dataOutput.getMenuDataMap(), rqData.isEnableScreenshot());
        }

        rsData.setRootDataList(dataOutput.getRootDataList());
        rsData.setMenuDataMap(dataOutput.getMenuDataMap());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
