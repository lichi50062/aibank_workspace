package com.tfb.aibank.chl.creditcard.qu001.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001014Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001014Rs;

//@formatter:off
/**
* @(#)NCCQU001014Task.java
* 
* <p>Description:信用卡總覽 功能首頁 查看完整卡號 前置動作</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU001014Task extends AbstractAIBankBaseTask<NCCQU001014Rq, NCCQU001014Rs> {

    @Override
    public void validate(NCCQU001014Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCQU001014Rq rqData, NCCQU001014Rs rsData) throws ActionException {
        // 重置交易安控
        super.resetTxSecurity();
        // 啟動交易安控驗證
        super.initTxSecurity(I18NUtils.getMessage("nccqu001.otp.title"));
    }

}