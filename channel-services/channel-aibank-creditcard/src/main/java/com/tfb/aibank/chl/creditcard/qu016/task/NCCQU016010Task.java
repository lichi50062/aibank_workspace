package com.tfb.aibank.chl.creditcard.qu016.task;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu016.model.NCCQU016010Rq;
import com.tfb.aibank.chl.creditcard.qu016.model.NCCQU016010Rs;
import com.tfb.aibank.chl.creditcard.qu016.model.NCCQU016Output;
import com.tfb.aibank.chl.creditcard.qu016.service.NCCQU016Service;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.CreditCardIdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;

@Component
@Scope("prototype")
public class NCCQU016010Task extends AbstractAIBankBaseTask<NCCQU016010Rq, NCCQU016010Rs> {

    @Autowired
    NCCQU016Service nccqu016Service;

    NCCQU016Output output = new NCCQU016Output();

    @Override
    public void validate(NCCQU016010Rq rqData) throws ActionException {
        AIBankUser loginUser = getLoginUser();
        Locale userLocale = getLocale();

        // 檢核信用卡戶況
        userDataCacheService.validateCreditCardStatus(loginUser);

        // 檢核信用卡身分別
        CreditCardIdType creditCardIdType = userDataCacheService.getCreditCardIdType(loginUser, userLocale);
        if (!creditCardIdType.isPrimaryCard()) {
            throw ExceptionUtils.getActionException(ErrorCode.PRIMARY_CARDHOLDER_ONLY);
        }
    }

    @Override
    public void handle(NCCQU016010Rq rqData, NCCQU016010Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        nccqu016Service.getAdditionalBenefit(loginUser.getCustId() , output);
        rsData.setQueryDateTime(DateUtils.getDateTimeStr(new Date()));
        rsData.setShowPrivateBanking(output.isShowPrivateBanking());
        rsData.setEffectStartDate(output.getEffectStartDate());
        rsData.setEffectEndDate(output.getEffectEndDate());
        rsData.setDataList(output.getDataList());
    }


}
