package com.tfb.aibank.chl.system.ot014.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot014.model.NSTOT014020Rq;
import com.tfb.aibank.chl.system.ot014.model.NSTOT014020Rs;
import com.tfb.aibank.chl.system.ot014.model.NSTOT014Output;
import com.tfb.aibank.chl.system.ot014.service.NSTOT014Service;
import com.tfb.aibank.common.type.IDType;
import com.tfb.aibank.common.util.BaNCSUtil;

// @formatter:off
/**
 * @(#)NSTOT014020Task.java
 * 
 * <p>Description:投資風險偏好輔助說明 020 投資風險偏好輔助說明 020</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT014020Task extends AbstractAIBankBaseTask<NSTOT014020Rq, NSTOT014020Rs> {

    @Autowired
    private NSTOT014Service service;

    private final NSTOT014Output output = new NSTOT014Output();

    @Override
    public void validate(NSTOT014020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT014020Rq rqData, NSTOT014020Rs rsData) throws ActionException {
        // 個人戶 取得第一部分
        String idType = BaNCSUtil.getIDTYPE(getLoginUser().getCustId());
        boolean isPersonal = !StringUtils.equalsAny(idType, IDType.LEGAL_ENTITY_CARD_NUMBER.getCode(), IDType.OVERSEAS_VIRTUAL_NUMBER.getCode());
        if (isPersonal) {
            service.getUserInformation(getLoginUser(), getLocale(), output);
            rsData.setQaList(output.getQaList());
        }

        // 取得第二部分
        service.getPeopleSoft(getLoginUser(), getLocale(), isPersonal ? "1" : "2", output);
        rsData.setQaList2(output.getQaList2());
        rsData.setTestDate(DateUtils.getDateStr(getLoginUser().getLoginMsgRs().getEbillStrDate().getTime(), "/"));
        rsData.setEmail(DataMaskUtil.maskEmail(output.getEmail()));
    }

}
