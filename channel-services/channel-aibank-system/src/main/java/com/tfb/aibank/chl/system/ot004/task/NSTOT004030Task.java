package com.tfb.aibank.chl.system.ot004.task;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004030Rq;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004030Rs;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004Input;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004Output;
import com.tfb.aibank.chl.system.ot004.service.NSTOT004Service;

// @formatter:off
/**
 * @(#)NSTOT004030Task.java
 * 
 * <p>Description: 030 結果頁截圖</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/17, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT004030Task extends AbstractAIBankBaseTask<NSTOT004030Rq, NSTOT004030Rs> {

    @Autowired
    private NSTOT004Service service;

    /** 結果頁 */
    private static final String RESULT_PAGE = "resultPage";
    /** 個人化版位 */
    private static final String PERSONALIZATION = "personalization";

    private NSTOT004Input dataInput = new NSTOT004Input();

    private NSTOT004Output dataOutput = new NSTOT004Output();

    @Override
    public void validate(NSTOT004030Rq rqData) throws ActionException {
        if (StringUtils.isBlank(rqData.getType())) {
            logger.error("傳入參數(type)為必要條件。");
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
        else if (StringUtils.notAllEquals(rqData.getType(), RESULT_PAGE, PERSONALIZATION)) {
            logger.error("傳入參數(type)必須是 {} 和 {}。", RESULT_PAGE, PERSONALIZATION);
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NSTOT004030Rq rqData, NSTOT004030Rs rsData) throws ActionException {

        // #7615 U-19923，新增開關控制是否要儲存個人化頁面
        String enableSavePersonalResultpage = service.getValue("ENABLE_SAVE_PERSONAL_RESULTPAGE");
        logger.debug("是否開啟儲存個人化頁面：{}", enableSavePersonalResultpage);
        if (StringUtils.isNotBlank(enableSavePersonalResultpage) && StringUtils.equalsIgnoreCase(enableSavePersonalResultpage, "false")) {
            return;
        }

        dataInput.setType(rqData.getType());
        dataInput.setTraceId(rqData.getTraceId());
        dataInput.setActionPointId(rqData.getActionPointId());
        dataInput.setTemplateId(StringUtils.isBlank(rqData.getTemplateId()) ? StringUtils.EMPTY : StringUtils.upperCase(rqData.getTemplateId()));
        dataInput.setOfferId(rqData.getOfferId());
        dataInput.setPageId(getFromPage());

        logger.debug("Base64.getDecoder() 之前：" + rqData.getResData());
        String resData = new String(Base64.getDecoder().decode(rqData.getResData()), StandardCharsets.UTF_8);
        dataInput.setResData(resData);
        logger.debug("Base64.getDecoder() 之後：" + resData);

        if (StringUtils.equals(rqData.getType(), RESULT_PAGE)) {
            service.addTxnResultPageRecord(getLoginUser(), dataInput, dataOutput);
            logger.debug("儲存交易結果頁截圖，執行結果：{}", dataOutput.getExecResult());
        }
        else if (StringUtils.equals(rqData.getType(), PERSONALIZATION)) {
            dataInput.setTrackingId(getTrackingIxd());
            service.addPersonalResultPageRecord(getLoginUser(), dataInput, dataOutput);
            logger.debug("儲存個人化版位截圖，執行結果：{}", dataOutput.getExecResult());
        }
    }
}
