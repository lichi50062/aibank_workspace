package com.tfb.aibank.chl.creditcard.tx004.task;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.tx004.model.CreditCardData;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004020Rq;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004020Rs;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004Cache;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004Input;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004Output;
import com.tfb.aibank.chl.creditcard.tx004.service.NCCTX004Service;

//@formatter:off
/**
* @(#)NCCTX004010Task.java
*
* <p>Description:NCCTX004_道路救援登錄 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/24
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCTX004020Task extends AbstractAIBankBaseTask<NCCTX004020Rq, NCCTX004020Rs> {
    @Autowired
    protected NCCTX004Service service;

    private NCCTX004Input input = new NCCTX004Input();

    private NCCTX004Output output = new NCCTX004Output();

    @Override
    public void validate(NCCTX004020Rq rqData) throws ActionException {
        NCCTX004Cache cache = getCache();
        List<CreditCardData> ccDatas = cache.getCreditCardDatas();
        if (CollectionUtils.isEmpty(ccDatas))
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);

        if (!ccDatas.stream().map(CreditCardData::getCardKey).anyMatch(key -> key.equals(rqData.getCreditCardKey()))) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        List<String> allTxTypes = Stream.of("A", "U", "D").collect(Collectors.toList());
        if (StringUtils.isBlank(rqData.getTxtType()) || !allTxTypes.contains(rqData.getTxtType())) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        if (StringUtils.isBlank(rqData.getCarNo())) {
            addErrorFieldMap("input1", I18NUtils.getMessage("ncctx004.form.blank.value"));
            addErrorFieldMap("input2", I18NUtils.getMessage("ncctx004.form.blank.value"));
        }
        else {
            String carNo = RegExUtils.replaceAll(rqData.getCarNo(), "-", "");
            if (!ValidatorUtility.checkEngAndNumber(carNo)) {
                addErrorFieldMap("input1", I18NUtils.getMessage("ncctx004.form.endig.only"));
                addErrorFieldMap("input2", I18NUtils.getMessage("ncctx004.form.endig.only"));
            }
        }
    }

    @Override
    public void handle(NCCTX004020Rq rqData, NCCTX004020Rs rsData) throws ActionException {
        input.setLocale(getLocale());
        NCCTX004Cache cache = getCache();
        List<CreditCardData> ccDatas = cache.getCreditCardDatas();
        CreditCardData creditCardData = ccDatas.stream().filter(data -> data.getCardKey().equals(rqData.getCreditCardKey())).findFirst().orElse(null);
        if (logger.isDebugEnabled()) {
            logger.debug("==NCCTX004020 creditCardData ==: {}", IBUtils.attribute2Str(creditCardData));
        }

        input.setCarNo(rqData.getCarNo());
        input.setTxtType(rqData.getTxtType());
        input.setCreditCardData(creditCardData);

        service.doCardCarNoConfig(getLoginUser(), input, output, getAccessLog());

        logger.debug("==NCCTX004020 after config ==: {}", IBUtils.attribute2Str(output.getApplyData()));

    }

    private NCCTX004Cache getCache() {
        NCCTX004Cache cache = getCache(NCCTX004Cache.class.getCanonicalName(), NCCTX004Cache.class);
        if (cache == null) {
            cache = new NCCTX004Cache();
        }
        return cache;
    }
}