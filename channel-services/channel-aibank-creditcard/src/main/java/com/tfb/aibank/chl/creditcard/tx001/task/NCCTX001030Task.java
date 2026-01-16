package com.tfb.aibank.chl.creditcard.tx001.task;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW311RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW311RResponse;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001030Rq;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001030Rs;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001CacheVo;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001Service;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

//@formatter:off
/**
* @(#)NCCTX001030Task.java
*
* <p>Description:預借現金 確認頁</p>
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
public class NCCTX001030Task extends AbstractAIBankBaseTask<NCCTX001030Rq, NCCTX001030Rs> {
    @Autowired
    protected NCCTX001Service service;

    @Autowired
    private CreditCardResource creditCardResource;

    @Override
    public void validate(NCCTX001030Rq rqData) throws ActionException {

        if (StringUtils.isBlank(rqData.getCvv())) {
            this.addErrorFieldMap("cvv", I18NUtils.getMessage("validate.cvv.empty.desc", getLocale()));
        }
        else {
            if (rqData.getCvv().length() != 3) {
                this.addErrorFieldMap("cvv", I18NUtils.getMessage("validate.cvv.length.desc", getLocale()));
            }
            if (!ValidatorUtility.checkNumeric(rqData.getCvv())) {
                this.addErrorFieldMap("cvv", I18NUtils.getMessage("validate.cvv.number.desc", getLocale()));
            }
        }

        boolean isOtherBank = service.isOtherBank(rqData);
        if (isOtherBank) {
            if (StringUtils.isBlank(rqData.getAccountNo())) {
                // 請輸入銀行帳號。
                this.addErrorFieldMap("accountNo", I18NUtils.getMessage("validate.accountNo.empty.desc", getLocale()));
            }
            else if (!ValidatorUtility.checkNumeric(rqData.getAccountNo())) {
                // 請輸入數字。
                this.addErrorFieldMap("accountNo", I18NUtils.getMessage("validate.accountNo.number.desc", getLocale()));
            }
        }
    }

    @Override
    public void handle(NCCTX001030Rq rqData, NCCTX001030Rs rsData) throws ActionException {
        // AIBankUser loginUser = getLoginUser();

        boolean isOtherBank = service.isOtherBank(rqData);
        if (!service.isBusinessTime() && isOtherBank) {
            throw new ActionException(ErrorCode.TRANSFER_NOT_IN_BUSINESS_TIME);
        }

        NCCTX001CacheVo cache = getCache(NCCTX001Service.NCCTX001_CACHE_KEY, NCCTX001CacheVo.class);

        CreditCard card = null;
        for (CreditCard c : cache.getCards()) {
            if (c.getCardKey().equals(rqData.getCardKey())) {
                card = c;
                break;
            }
        }
        if (card == null) {
            throw new ActionException(ErrorCode.CASH_ANVANCED_COMMON_ERROR);
        }

        CEW311RRequest request = new CEW311RRequest();
        request.setVncdno(card.getCardNo());
        request.setVenedym("");
        request.setVicvv2(rqData.getCvv());
        request.setVnbird("");
        request.setVntxam(StringUtils.leftPad(Integer.toString(cache.getAmount()), 7, '0'));

        CEW311RResponse response = null;
        try {
            response = creditCardResource.sendCEW311R(request);
        }
        catch (ServiceException ex) {
            logger.error("CEW311R", ex);
            throw ex;
        }

        if (response == null) {
            throw new ActionException(ErrorCode.CASH_ANVANCED_COMMON_ERROR);
        }

        logger.debug("CEW311R email is {}", response.getEmailAddress());
        cache.setEmail(response.getEmailAddress());

        BigDecimal amount = new BigDecimal(cache.getAmount());
        BigDecimal calcFee = service.getFee(cache.getAmount());
        BigDecimal realFee = response.getVnfee();

        if (!(realFee.compareTo(new BigDecimal(0)) == 1)) {
            throw new ActionException(ErrorCode.CASH_ANVANCED_COMMON_ERROR);
        }

        cache.setRealFee(realFee);
        cache.setDispFee(calcFee);

        rsData.setAmount(IBUtils.formatMoney(amount, 0));
        rsData.setFee(IBUtils.formatMoney(cache.getDispFee(), 0));
        rsData.setTotalAmount(IBUtils.formatMoney(amount.add(cache.getDispFee()), 0));

        rsData.setOtherBank(isOtherBank);
        rsData.setCardName(card.getCardName() + " ···· " + card.getCardNo().substring(12));

        if (!isOtherBank) {
            rsData.setAccountName(service.getAccountName(rqData.getAccountKey(), cache));
            cache.setBankId("0122009");
            rsData.setInterbankFee("0");
        }
        else {
            rsData.setAccountName(service.getOtherBankAccountName(rqData.getBankId(), rqData.getBranchId(), rqData.getAccountNo(), getLocale()));
            cache.setBankId(rqData.getBankId() + rqData.getBranchId());
            cache.setAccountNo(rqData.getAccountNo());
            rsData.setInterbankFee("30");
        }

        cache.setCreditCard(card);
        cache.setAccountName(rsData.getAccountName());
        cache.setCardName(rsData.getCardName());
        cache.setOtherBank(isOtherBank);
        cache.setAccountKey(rqData.getAccountKey());
        cache.setCvv(rqData.getCvv());
        setCache(NCCTX001Service.NCCTX001_CACHE_KEY, cache);

        super.initTxSecurity();

    }

}