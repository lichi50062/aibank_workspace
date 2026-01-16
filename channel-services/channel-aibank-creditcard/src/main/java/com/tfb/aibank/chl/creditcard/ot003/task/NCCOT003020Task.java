package com.tfb.aibank.chl.creditcard.ot003.task;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.ot003.model.NCCOT003020Rq;
import com.tfb.aibank.chl.creditcard.ot003.model.NCCOT003020Rs;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW325RResponse;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCOT003020Task.java
 * 
 * <p>Description:超商繳信用卡款 020 繳款條碼頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCOT003020Task extends AbstractAIBankBaseTask<NCCOT003020Rq, NCCOT003020Rs> {

    @Autowired
    private CreditCardResource creditCardResource;

    @Override
    public void validate(NCCOT003020Rq rqData) throws ActionException {
        String txType = rqData.getTxType();
        if (StringUtils.isBlank(rqData.getTxType()) || !StringUtils.equalsAny(txType, "0", "1", "2")) {
            // 請選擇繳款金額
            this.addErrorFieldMap("txType", I18NUtils.getMessage("nccot003.txType.error.msg"));
        }
        BigDecimal txAmount = rqData.getTxAmount();
        if (StringUtils.equals("2", txType))
            if (txAmount == null || txAmount.compareTo(new BigDecimal(20000)) > 0) {
                // 請輸入2萬內的金額
                this.addErrorFieldMap("txAmount", I18NUtils.getMessage("nccot003.txAmount.error.msg"));
            }
    }

    @Override
    public void handle(NCCOT003020Rq rqData, NCCOT003020Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        CEW325RResponse rs = null;
        try {
            rs = creditCardResource.sendCEW325R(aibankUser.getCustId(), rqData.getTxType(), rqData.getTxAmount());
        }
        catch (ServiceException e) {
            if (StringUtils.equals("V860", e.getErrorCode())) {
                throwActionException(ErrorCode.EXCEEDED_BARCODES_COUNT);
            }
            throw e;
        }
        rsData.setCollectPeriod(rs.getCollectPeriod());
        rsData.setBillAcctCD(rs.getBillAcctCD());
        rsData.setBillAmtCD(rs.getBillAmtCD());
        rsData.setPeriod(rs.getPeriod());
        rsData.setBillAcct(rs.getBillAcct());
        rsData.setBillAmt(rs.getBillAmt());
        rsData.setPaymentDeadline(rs.getPaymentDeadline());
    }
}
