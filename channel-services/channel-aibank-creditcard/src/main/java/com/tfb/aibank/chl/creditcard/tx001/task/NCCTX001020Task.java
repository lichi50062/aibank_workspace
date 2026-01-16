package com.tfb.aibank.chl.creditcard.tx001.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.bank.BankCacheManager;
import com.tfb.aibank.chl.component.bank.BankData;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001020Rq;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001020Rs;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001AccountInfo;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001BankInfo;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001CardInfo;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001CacheVo;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001Service;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.common.type.TransOutAcctType;

//@formatter:off
/**
* @(#)NCCTX001020Task.java
*
* <p>Description:預借現金 </p>
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
public class NCCTX001020Task extends AbstractAIBankBaseTask<NCCTX001020Rq, NCCTX001020Rs> {
    @Autowired
    protected NCCTX001Service service;

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private BankCacheManager bankCacheManager;

    @Override
    public void validate(NCCTX001020Rq rqData) throws ActionException {
        int amount = rqData.getAmount();
        if (amount == 0) {
            // 請輸入申請金額。
            this.addErrorFieldMap("amount", I18NUtils.getMessage("validate.amount.empty", getLocale()));
        }
        else if ((Math.round(amount / 1000) * 1000 != amount)) {
            // 很抱歉，預借現金金額以1,000元為單位！
            this.addErrorFieldMap("amount", I18NUtils.getMessage("validate.amount.thousand", getLocale()));
        }
        else {
            NCCTX001CacheVo cache = getCache(NCCTX001Service.NCCTX001_CACHE_KEY, NCCTX001CacheVo.class);
            if (amount > cache.getMaxBalanceAvailable()) {
                // 很抱歉，預借現金金額超過網銀預借現金可用餘額！
                this.addErrorFieldMap("amount", I18NUtils.getMessage("validate.amount.exceed", getLocale()));
            }
        }

    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public void handle(NCCTX001020Rq rqData, NCCTX001020Rs rsData) throws ActionException {
        AIBankUser loginUser = getLoginUser();

        NCCTX001CacheVo cache = getCache(NCCTX001Service.NCCTX001_CACHE_KEY, NCCTX001CacheVo.class);

        boolean isShowSpecial = false;
        // 篩選信用卡
        List<NCCTX001CardInfo> cardInfo = new ArrayList<NCCTX001CardInfo>();
        for (NCCTX001CardInfo c : cache.getCardInfo()) {
            if (c.getAvailableBalance() >= rqData.getAmount()) {
                c.setAvalable(true);
                cardInfo.add(c);
            }
        }
        cardInfo.sort(Comparator.comparing(NCCTX001CardInfo::getCardNo, (s1, s2) -> {
            long d1 = 0;
            long d2 = 0;
            try {
                d1 = Long.parseLong(s1);
                d2 = Long.parseLong(s2);
            }
            catch (NumberFormatException ex) {
                logger.warn("產生例外，不影響程序。");
                logger.error(ex.getMessage(), ex);
            }

            return Long.compare(d1, d2);
        }));
        for (NCCTX001CardInfo c : cache.getCardInfo()) {
            if (c.getAvailableBalance() < rqData.getAmount()) {
                c.setAvalable(false);
                cardInfo.add(c);
                isShowSpecial = true;
            }
        }

        for (NCCTX001CardInfo card : cardInfo) {
            card.setCardNo(null);
        }

        rsData.setCardInfo(cardInfo);
        rsData.setShowSpecial(isShowSpecial);

        // 查詢歸戶下臺幣活存轉出帳號
        List<TransOutAccount> accounts = null;
        if (loginUser.getCustomerType() == CustomerType.GENERAL) {
            if (loginUser.getCustomerType() == CustomerType.GENERAL) {
                accounts = userDataCacheService.getTransOutAccounts(loginUser, getLocale(), TransOutAcctType.TW_SAVING_DOC_NO_TRANS_OUT_ACCT, false);
            }
        }
        rsData.setAccountInfo(getAcclountInfo(accounts));

        // 銀行清單
        rsData.setBankInfo(getBankInfo(bankCacheManager.getAllBanks(getLocale())));

        // 所有自行帳號
        cache.setAccountList(accounts);

        cache.setAmount(rqData.getAmount());

        setCache(NCCTX001Service.NCCTX001_CACHE_KEY, cache);

    }

    private List<NCCTX001AccountInfo> getAcclountInfo(List<TransOutAccount> accounts) {
        List<NCCTX001AccountInfo> accountInfo = new ArrayList<NCCTX001AccountInfo>();

        if (accounts != null && accounts.size() > 0) {
            for (TransOutAccount acct : accounts) {
                NCCTX001AccountInfo accInfo = new NCCTX001AccountInfo();
                accInfo.setAccountKey(acct.getAccountKey());
                accInfo.setAcno(getAcctName(acct) + " " + acct.getDisplayAccountId());
                accountInfo.add(accInfo);
            }
        }
        NCCTX001AccountInfo accInfo = new NCCTX001AccountInfo();
        accInfo.setAccountKey("0");
        accInfo.setAcno(I18NUtils.getMessage("ncctx001.020.otherbank"));
        accountInfo.add(accInfo);

        return accountInfo;
    }

    protected String getAcctName(TransOutAccount act) {
        if (StringUtils.isNotBlank(act.getAcctNickName())) {
            return act.getAcctNickName();
        }
        return act.getDisplayAccountName();
    }

    private List<NCCTX001BankInfo> getBankInfo(List<BankData> bankDatas) {
        List<NCCTX001BankInfo> bankInfo = new ArrayList<NCCTX001BankInfo>();

        NCCTX001BankInfo placeholder = new NCCTX001BankInfo();
        placeholder.setBankId("0");
        placeholder.setBankName(I18NUtils.getMessage("ncc.tx001.choosebank"));
        bankInfo.add(placeholder);

        for (BankData bankData : bankDatas) {
            if ("012".equals(bankData.getBankId()))
                continue;
            if ("000".equals(bankData.getBankId()))
                continue;
            NCCTX001BankInfo bank = new NCCTX001BankInfo();
            bank.setBankId(bankData.getBankId());
            bank.setBankName(bankData.getBankId() + " " + bankData.getBankName());
            bankInfo.add(bank);
        }

        bankInfo.sort(Comparator.comparing(NCCTX001BankInfo::getBankId, (s1, s2) -> {
            long d1 = 0;
            long d2 = 0;
            try {
                d1 = Long.parseLong(s1);
                d2 = Long.parseLong(s2);
            }
            catch (NumberFormatException ex) {
                logger.warn("產生例外，不影響程序。");
                logger.error(ex.getMessage(), ex);
            }

            return Long.compare(d1, d2);
        }));

        return bankInfo;
    }
}