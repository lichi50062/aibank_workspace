package com.tfb.aibank.chl.creditcard.tx001.task;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.bank.BankCacheManager;
import com.tfb.aibank.chl.component.branch.BankBranchCacheManager;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.InformationResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW013RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.EB12020009Request;
import com.tfb.aibank.chl.creditcard.resource.dto.EB12020009Response;
import com.tfb.aibank.chl.creditcard.resource.dto.insertCardBorrowCashRequest;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001040Rq;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001040Rs;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001CacheVo;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001Service;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.model.AccountDay;
import com.tfb.aibank.common.type.TxSourceType;
import com.tfb.aibank.common.type.TxStatusType;
import com.tfb.aibank.common.util.BaNCSUtil;

//@formatter:off
/**
* @(#)NCCTX001040Task.java
*
* <p>Description:預借現金 結果頁</p>
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
public class NCCTX001040Task extends AbstractAIBankBaseTask<NCCTX001040Rq, NCCTX001040Rs> {
    @Autowired
    protected NCCTX001Service service;

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private InformationResource informationResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private BankCacheManager bankCacheManager;

    @Autowired
    private BankBranchCacheManager bankBranchCacheManager;

    @Autowired
    private CurrencyCodeCacheManager currencyCodeCacheManager;

    @Override
    public void validate(NCCTX001040Rq rqData) throws ActionException {

        super.doTxConfirmCheck();
    }

    @Override
    public void handle(NCCTX001040Rq rqData, NCCTX001040Rs rsData) throws ActionException {
        AIBankUser loginUser = getLoginUser();

        NCCTX001CacheVo cache = getCache(NCCTX001Service.NCCTX001_CACHE_KEY, NCCTX001CacheVo.class);

        Date now = new Date();
        EB12020009Request request = new EB12020009Request();
        if (!cache.isOtherBank()) {
            request.setFuncCod("01");
            request.setRtFee("00000000000000000+");
        }
        else {
            request.setFuncCod("02");
            request.setRtFee("00000000000030000+");
        }

        request.setFunc("1");
        request.setSeqNo("0");
        request.setIdno(loginUser.getCustId());
        request.setVncvv2(cache.getCvv());
        AccountDay accountDate = informationResource.getAccountDay();
        String dateStr = DateFormatUtils.SIMPLE_DATE_FORMAT.format(accountDate.getAccountDay());
        dateStr = dateStr.substring(6) + dateStr.substring(4, 6) + dateStr.substring(0, 4);
        request.setEbActDate(dateStr);
        request.setVncdno(cache.getCreditCard().getCardNo());
        String expireDate = cache.getCreditCard().getCardExpired();
        expireDate = "20" + expireDate.substring(2) + expireDate.substring(0, 2);
        request.setVendym(expireDate);
        request.setVntxam(StringUtils.leftPad(Integer.toString(cache.getAmount()), 7, '0'));
        request.setBnkCod(cache.getBankId());
        request.setAcno(cache.getAccountNo());
        request.setAmt(StringUtils.leftPad(Integer.toString(cache.getAmount()), 14, '0') + "000+");
        request.setMemo("CSHADV");
        request.setRcvName(cache.getCreditCard().getCardHoldName());
        request.setVnmrcd("FBU0000221");
        request.setIdType(BaNCSUtil.getIDTYPE(loginUser.getCustId()));

        request.setTxSrc("");
        request.setCtiNo("");
        request.setRmk("");
        request.setStrDate(Calendar.getInstance());
        request.setEndDate(Calendar.getInstance());
        request.setTxSts("");
        request.setFiller(" ");

        insertCardBorrowCashRequest log = new insertCardBorrowCashRequest();

        log.setCustId(loginUser.getCustId());
        log.setUserId(loginUser.getUserId());
        log.setUidDup(loginUser.getUidDup());
        log.setCompanyKind(loginUser.getCompanyKind());
        log.setNameCode(loginUser.getNameCode());
        log.setCardNo(cache.getCreditCard().getCardNo());
        log.setCardCvv2(cache.getCvv());
        log.setBirthday(cache.getBirthday());
        log.setCardNedy(expireDate);
        log.setCardOwnerName(cache.getCreditCard().getCardHoldName());
        log.setTxType(cache.isOtherBank() ? "2" : "1");
        log.setPayeeBank(cache.getBankId());
        log.setPayeeAccount(cache.getAccountNo());
        log.setTxAmount(cache.getAmount());
        log.setTxFee(cache.getRealFee().intValue());
        log.setTraceId(MDC.get(MDCKey.traceId.name()));
        log.setRemitFee(0);
        rsData.setInterbankFee("0");

        log.setEmail(loginUser.getEmail());
        log.setSecurityType(super.getSecurityType());
        log.setAccountDate(accountDate.getAccountDay());
        log.setTxDate(new Date());
        log.setClientIp(getRequest().getClientIp());
        log.setTxSource(TxSourceType.AI_BANK.getCode());

        request.setLog(log);

        rsData.setAmount(IBUtils.formatMoney(new BigDecimal(cache.getAmount()), 0));
        BigDecimal amount = new BigDecimal(cache.getAmount());
        rsData.setFee(IBUtils.formatMoney(cache.getDispFee(), 0));
        rsData.setTotalAmount(IBUtils.formatMoney(amount.add(cache.getDispFee()), 0));

        rsData.setCardName(cache.getCardName());
        rsData.setAccountName(cache.getAccountName());

        rsData.setHostTxTime(DateFormatUtils.CE_DATETIME_FORMAT.format(now));
        rsData.setDigitalDepositUrl(systemParamCacheManager.getValue("AIBANK", "DIGITAL_DEPOSIT_URL"));
        rsData.setHelpPhone(systemParamCacheManager.getValue("AIBANK", "CC_HELP_CENTER_PHONE"));
        rsData.setOtherBank(cache.isOtherBank());

        try {
            /** 發送電文 EB12020009 */
            EB12020009Response rs = creditCardResource.sendEB12020009(request);
            sendMail(loginUser, request, TxStatusType.SUCCESS, now, null, cache.getEmail());
            rsData.setSuccess(true);
            if (StringUtils.isNotBlank(rs.getRtFee())) {
                rsData.setInterbankFee(rs.getRtFee());
            }
        }
        catch (ServiceException ex) {
            ActionException aex = service.handleException(getAccessLog(), ExceptionUtils.getActionException(ex));
            sendMail(loginUser, request, TxStatusType.FAIL, now, aex, cache.getEmail());

            rsData.setSuccess(false);
            rsData.setSystemId(aex.getSystemId());
            rsData.setErrorCode(aex.getErrorCode());
            rsData.setErrorDesc(aex.getErrorDesc());
        }

    }

    private void sendMail(AIBankUser aibankuser, EB12020009Request request, TxStatusType txStatus, Date time, ActionException ex, String email) {

        if (ex != null && ex.getStatus() != null && ex.getStatus().getErrorCode() != null && //
                ex.getStatus().getErrorCode().startsWith("X")) {
            txStatus = TxStatusType.UNKNOWN;
        }

        if (StringUtils.isBlank(aibankuser.getEmail())) {
            String mobileNo = getCEW013RMobileNo(aibankuser.getCustId());
            if (StringUtils.isBlank(mobileNo))
                return;

            String smsTemplate;

            switch (txStatus) {
            case SUCCESS:
                smsTemplate = "ncctx001.sms.succ";
                break;
            case FAIL:
                smsTemplate = "ncctx001.sms.fail";
                break;
            default:
                smsTemplate = "ncctx001.sms.unknown";
                break;
            }

            sendResultMsg(I18NUtils.getMessage(smsTemplate, DateFormatUtils.CE_DATETIME_FORMAT.format(new Date())), null, null, mobileNo);
            return;
        }

        HashMap<String, String> params = new HashMap<>();

        String timeStr = DateFormatUtils.CE_DATETIME_FORMAT.format(time);

        String status = "";
        String extStatus = "";
        switch (txStatus) {
        case SUCCESS:
            status = I18NUtils.getMessage("ncctx001.notification.mail.succ");
            break;
        case FAIL:
            if (ex != null && StringUtils.isNoneBlank(ex.getErrorCode())) {
                status = I18NUtils.getMessage("ncctx001.notification.mail.fail");
                extStatus = "(" + ex.getErrorCode() + ")" + ex.getErrorDesc();
            }
            else {
                status = I18NUtils.getMessage("ncctx001.notification.mail.unknow");
            }
            break;
        default:
            status = I18NUtils.getMessage("ncctx001.notification.mail.unknow");
            break;
        }

        // ncctx001.notification.mail.succ=成功
        // ncctx001.notification.mail.fail=失敗
        // ncctx001.notification.mail.unknow=未明
        // ncctx001.notification.mail.result=交易{0}
        // ncctx001.notification.mail.subject=台北富邦行動銀行預借現金交易「{0}」通知
        // ncctx001.notification.mail.title=預借現金交易{0}

        // ncctx001.notification.mail.subtitle=預借現金
        // ncctx001.notification.mail.content=您於 {0} 透過台北富邦行動銀行「預借現金」交易{1}。

        String subject = I18NUtils.getMessage("ncctx001.notification.mail.subject", status);

        params.put("status", txStatus.getCode());
        params.put("title", I18NUtils.getMessage("ncctx001.notification.mail.title", status));
        params.put("content", I18NUtils.getMessage("ncctx001.notification.mail.content", timeStr, status));
        params.put("subTitle", I18NUtils.getMessage("ncctx001.notification.mail.subtitle"));

        params.put("result", I18NUtils.getMessage("ncctx001.notification.mail.result", status) + extStatus);
        params.put("hostTxTime", timeStr);
        params.put("cardNo", DataMaskUtil.maskCreditCard(request.getVncdno()));

        if (StringUtils.equals(request.getBnkCod(), "0122009")) {
            params.put("payeeBank", "012 " + bankCacheManager.getBankName("012", getLocale()));
        }
        else {
            String bankId = request.getBnkCod().substring(0, 3);
            String branchId = request.getBnkCod().substring(3);
            params.put("payeeBank", bankId + bankCacheManager.getBankName(bankId, getLocale()) + " " + branchId + StringUtils.trimAllBigSpace(bankBranchCacheManager.getBranchName(bankId, branchId, getLocale())));
        }

        String amount = "0";

        if (StringUtils.isNotBlank(request.getAmt())) {
            if (request.getAmt().length() > 4) {
                amount = request.getAmt().substring(0, request.getAmt().length() - 4);
            }
        }

        params.put("payeeAmount", currencyCodeCacheManager.getCurrencyName("TWD", getLocale()) + " " + IBUtils.formatMoney(new BigDecimal(amount), 0));
        params.put("payeeAccount", DataMaskUtil.maskBankAccount(request.getAcno()));

        sendTxnResultMail(aibankuser.getCustId(), aibankuser.getUidDup(), aibankuser.getUserId(), aibankuser.getCompanyKind(), subject, email, params);

    }

    private String getCEW013RMobileNo(String custId) {
        try {
            CEW013RRes res = creditCardResource.sendCEW013R(custId);
            if (res != null) {
                return res.getMobile();
            }
        }
        catch (ServiceException ex) {
            logger.warn("CEW013R Error", ex);
        }
        return "";
    }

}