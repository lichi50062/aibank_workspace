/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.accountselect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.accountselect.model.FuturesAccount;
import com.tfb.aibank.chl.component.accountselect.model.FuturesAcctRecord;
import com.tfb.aibank.chl.component.accountselect.model.RecentlyTwTransferRecord;
import com.tfb.aibank.chl.component.accountselect.model.TwAccountSelection;
import com.tfb.aibank.chl.component.accountselect.model.TwAccountSelector;
import com.tfb.aibank.chl.component.accountselect.model.TwPayeeRecord;
import com.tfb.aibank.chl.component.accountselect.model.TwPayerRecord;
import com.tfb.aibank.chl.component.accountselect.model.TwTransRecord;
import com.tfb.aibank.chl.component.bank.BankCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.model.account.AgreedInAccount;
import com.tfb.aibank.chl.model.account.FavoriteAccount;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.type.TransOutAcctType;
import com.tfb.aibank.common.util.AccountUtils;

// @formatter:off
/**
 * @(#)AccountSelectorService.java
 * 
 * <p>Description:處理『最近轉帳/常用/約定』帳號選擇元件(臺幣)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class TwAccountSelectorService {

    public static final String CACHE_KEY = "TW_ACCOUNT_SELECTOR";

    public static final String FAVORITE_CACHE_KEY = "FAVORITE_TW_ACCOUNT_SELECTOR";

    private static final String RECENT_TRAN_KEY_PREFIX = "TRAN_";

    private static final String FAVORITE_PAYEE_KEY_PREFIX = "FAV_";

    private static final String AGREED_PAYEE_KEY_PREFIX = "AGR_";

    private static final String FUTURES_PAYEE_KEY_PREFIX = "FUTURES_";

    private static final String SELF_PAYEE_KEY_PREFIX = "TW_SELF_";

    private static final String SELF_PAYEE_KEY_FORMAT = "TW_SELF_%s_%s";

    @Autowired
    private UserDataCacheService userAccountService;

    @Autowired
    private BankCacheManager bankCacheManager;

    @Autowired
    private AccountSelectorTransferResource transferResource;

    /**
     * 設置初始資料
     * 
     * @param syncAgreeIn
     * @param transOutAcctType
     * @param accountSelector
     * @param loginUser
     * @param locale
     */
    public void setInitData(boolean syncAgreeIn, TransOutAcctType transOutAcctType, TwAccountSelector accountSelector, AIBankUser loginUser, Locale locale) {
        setInitData(syncAgreeIn, transOutAcctType, accountSelector, loginUser, locale, new ArrayList<>());
    }

    /**
     * 設置初始資料
     * 
     * @param syncAgreeIn
     * @param transOutAcctType
     * @param accountSelector
     * @param loginUser
     * @param locale
     * @param futuresAcctList
     */
    public void setInitData(boolean syncAgreeIn, TransOutAcctType transOutAcctType, TwAccountSelector accountSelector, AIBankUser loginUser, Locale locale, List<FuturesAccount> futuresAcctList) {

        // 設置轉出帳號
        setPayers(transOutAcctType, accountSelector, loginUser, locale);

        // 設置轉帳紀錄資料
        setTrans(syncAgreeIn, transOutAcctType, accountSelector, loginUser, locale);

        // 設置期貨帳號
        if (CollectionUtils.isNotEmpty(futuresAcctList)) {
            setFuturesAccts(accountSelector, futuresAcctList, locale);
        }

    }

    /**
     * 設置轉出帳號
     *
     * @param transOutAcctType
     * @param accountSelector
     * @param loginUser
     * @param locale
     */
    private void setPayers(TransOutAcctType transOutAcctType, TwAccountSelector accountSelector, AIBankUser loginUser, Locale locale) {

        // 取得臺幣約定轉出帳號
        List<TransOutAccount> agreedOutAccounts = getTwAgreedOutAccount(transOutAcctType, loginUser, locale);
        agreedOutAccounts = IBUtils.sort(agreedOutAccounts, "acno", false);

        List<TwPayerRecord> payers = new ArrayList<>();
        List<TwPayerRecord> selfs = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(agreedOutAccounts)) {

            int index = 0;
            for (TransOutAccount agreedOutAccount : agreedOutAccounts) {
                if (agreedOutAccount.isOpenTransFlag()) {
                    payers.add(buildPayer(agreedOutAccount, index));
                }
                selfs.add(buildSelf(agreedOutAccount, index));
                index++;
            }
        }
        accountSelector.setPayers(payers);
        accountSelector.setSelfs(selfs);
    }

    /**
     * 設置轉帳紀錄
     * 
     * @param accountSelector
     * @param loginUser
     * @param locale
     */
    private void setTrans(boolean syncAgreeIn, TransOutAcctType transOutAcctType, TwAccountSelector accountSelector, AIBankUser loginUser, Locale locale) {

        // 取得最近轉帳紀錄
        List<RecentlyTwTransferRecord> transRecords = transferResource.getRecentlyTwTransferRecord(loginUser.getCustId(), loginUser.getUserId(), loginUser.getCompanyKind(), loginUser.getUidDup()).getRecords();

        List<TwTransRecord> trans = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(transRecords)) {

            int index = 0;
            for (RecentlyTwTransferRecord transRecord : transRecords) {

                // 之前的轉出帳號現在已不是約定轉出帳號
                TransOutAccount agreedOutAccount = findAgreedOutAccount(transOutAcctType, loginUser, locale, transRecord.getPayerAccount());
                // #1908 [共用]最近轉帳頁籤 資料撈取增加判斷轉出帳號轉帳註記
                // 交易資料之[轉出帳號]openTransFlag=Y，才顯示於最近轉帳頁籤
                if (agreedOutAccount == null || !agreedOutAccount.isOpenTransFlag()) {
                    continue;
                }

                trans.add(buildTran(syncAgreeIn, loginUser, agreedOutAccount, transRecord, index++, locale));
            }
        }

        accountSelector.setTrans(trans);
    }

    /**
     * 設置期貨帳號
     * 
     * @param accountSelector
     * @param futuresAcctList
     * @param locale
     */
    private void setFuturesAccts(TwAccountSelector accountSelector, List<FuturesAccount> futuresAcctList, Locale locale) {

        List<FuturesAcctRecord> futuresAccts = new ArrayList<>();

        int index = 0;
        for (FuturesAccount futuresAccount : futuresAcctList) {
            futuresAccts.add(buildFutures(futuresAccount, index++, locale));
        }

        accountSelector.setFuturesAccts(futuresAccts);
    }

    /**
     * 以約定轉出帳號取得對應常用轉入帳號
     * 
     * @param accountSelector
     * @param loginUser
     * @param locale
     * @param key
     * @return
     * @throws ActionException
     */
    public List<TwPayeeRecord> getFavoritePayee(TransOutAcctType transOutAcctType, TwAccountSelector accountSelector, AIBankUser loginUser, Locale locale, String key) throws ActionException {

        List<TwPayeeRecord> payees = new ArrayList<>();

        Map<String, List<TwPayeeRecord>> favoritePayeeMap = accountSelector.getFavoritePayeeMap();
        if (favoritePayeeMap == null) {
            favoritePayeeMap = new HashMap<>();
        }

        if (favoritePayeeMap.containsKey(key)) {
            // 有暫存資料
            payees = favoritePayeeMap.get(key);
        }
        else {
            // 無暫存資料
            TwPayerRecord payerRecord = findPayer(accountSelector, key);
            TransOutAccount agreedOutAccount = findAgreedOutAccount(transOutAcctType, loginUser, locale, payerRecord.getPayerAccount());
            if (agreedOutAccount == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }

            List<FavoriteAccount> favoritePayeeAccounts = userAccountService.getFavoritePayeeAccounts(agreedOutAccount, loginUser);
            if (CollectionUtils.isNotEmpty(favoritePayeeAccounts)) {
                int index = 0;
                for (FavoriteAccount favoritePayeeAccount : favoritePayeeAccounts) {
                    payees.add(buildFavoritePayee(favoritePayeeAccount, payerRecord.getKey(), index++, locale));
                }
            }
        }

        favoritePayeeMap.put(key, payees);
        accountSelector.setFavoritePayeeMap(favoritePayeeMap);

        return payees;
    }

    /**
     * 以約定轉出帳號取得對應約定轉入帳號
     *
     * @param syncAgreeIn
     * @param transOutAcctType
     * @param accountSelector
     * @param loginUser
     * @param locale
     * @param key
     * @return
     * @throws ActionException
     */
    public List<TwPayeeRecord> getAgreedPayee(boolean syncAgreeIn, TransOutAcctType transOutAcctType, TwAccountSelector accountSelector, AIBankUser loginUser, Locale locale, String key) throws ActionException {

        List<TwPayeeRecord> payees = new ArrayList<>();

        Map<String, List<TwPayeeRecord>> agreedPayeeMap = accountSelector.getAgreedPayeeMap();
        if (agreedPayeeMap == null) {
            agreedPayeeMap = new HashMap<>();
        }

        if (agreedPayeeMap.containsKey(key)) {
            // 有暫存資料
            payees = agreedPayeeMap.get(key);
        }
        else {
            // 無暫存資料
            TwPayerRecord payerRecord = findPayer(accountSelector, key);
            TransOutAccount agreedOutAccount = findAgreedOutAccount(transOutAcctType, loginUser, locale, payerRecord.getPayerAccount());
            if (agreedOutAccount == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }

            List<AgreedInAccount> agreeInAccounts = userAccountService.getAgreedInAccounts(syncAgreeIn, agreedOutAccount, loginUser);
            if (CollectionUtils.isNotEmpty(agreeInAccounts)) {
                int index = 0;
                for (AgreedInAccount agreedInAccount : agreeInAccounts) {
                    payees.add(buildAgreedPayee(agreedInAccount, payerRecord.getKey(), index++, locale));
                }
            }
        }

        agreedPayeeMap.put(key, payees);
        accountSelector.setAgreedPayeeMap(agreedPayeeMap);

        return payees;
    }

    /**
     * 比對是否為約定轉入
     *
     * @param syncAgreeIn
     * @param loginUser
     * @param agreedOutAccount
     * @param payeeBankId
     * @param payeeAccount
     * @return
     */
    private AgreedInAccount findAgreedInAccount(boolean syncAgreeIn, AIBankUser loginUser, TransOutAccount agreedOutAccount, String payeeBankId, String payeeAccount) {
        List<AgreedInAccount> agreedInAccounts = userAccountService.getAgreedInAccounts(syncAgreeIn, agreedOutAccount, loginUser);
        return agreedInAccounts.stream().filter(a -> isSamePayeeAccount(a.getBankNo(), payeeBankId, a.getAcno(), payeeAccount)).findAny().orElse(null);
    }

    /**
     * 比對是否為常用轉入
     * 
     * @param loginUser
     * @param agreedOutAccount
     * @param payeeBankId
     * @param payeeAccount
     * @return
     */
    private FavoriteAccount findFavoritePayeeAccount(AIBankUser loginUser, TransOutAccount agreedOutAccount, String payeeBankId, String payeeAccount) {
        List<FavoriteAccount> favoritePayeeAccounts = userAccountService.getFavoritePayeeAccounts(agreedOutAccount, loginUser);
        return favoritePayeeAccounts.stream().filter(a -> isSamePayeeAccount(a.getPayeeBank(), payeeBankId, a.getPayeeAccount(), payeeAccount)).findAny().orElse(null);
    }

    /**
     * 是否為相同轉入帳號
     * 
     * @param payeeBank1
     * @param payeeBank2
     * @param payeeAcct1
     * @param payeeAcct2
     * @return
     */
    private boolean isSamePayeeAccount(String payeeBank1, String payeeBank2, String payeeAcct1, String payeeAcct2) {
        if (StringUtils.isAnyBlank(payeeBank1, payeeBank2, payeeAcct1, payeeAcct2)) {
            return false;
        }
        payeeBank1 = StringUtils.leftPad(payeeBank1, 3, "0");
        payeeBank2 = StringUtils.leftPad(payeeBank2, 3, "0");
        payeeAcct1 = StringUtils.leftPad(payeeAcct1, 16, "0");
        payeeAcct2 = StringUtils.leftPad(payeeAcct2, 16, "0");
        return StringUtils.equals(payeeBank1, payeeBank2) && StringUtils.equals(payeeAcct1, payeeAcct2);
    }

    /**
     * 轉出帳號
     * 
     * @param agreedOutAccount
     * @param index
     * @return
     */
    private TwPayerRecord buildPayer(TransOutAccount agreedOutAccount, int index) {
        return new TwPayerRecord(agreedOutAccount, index);
    }

    /**
     * 本人帳號
     * 
     * @param agreedOutAccount
     * @param index
     * @return
     */
    private TwPayerRecord buildSelf(TransOutAccount agreedOutAccount, int index) {
        TwPayerRecord self = buildPayer(agreedOutAccount, index);
        self.setKey(String.format(SELF_PAYEE_KEY_FORMAT, agreedOutAccount.getAccountId(), index));
        return self;
    }

    /**
     * 轉帳紀錄
     *
     * @param syncAgreeIn
     * @param loginUser
     * @param agreedOutAccount
     * @param transRecord
     * @param index
     * @param locale
     * @return
     */
    private TwTransRecord buildTran(boolean syncAgreeIn, AIBankUser loginUser, TransOutAccount agreedOutAccount, RecentlyTwTransferRecord transRecord, int index, Locale locale) {
        TwTransRecord tran = new TwTransRecord();
        tran.setKey(RECENT_TRAN_KEY_PREFIX + index);
        AgreedInAccount agreedInAccount = findAgreedInAccount(syncAgreeIn, loginUser, agreedOutAccount, transRecord.getPayeeBank(), transRecord.getPayeeAccount());
        boolean isDesignate = agreedInAccount != null;
        FavoriteAccount favoritePayeeAccount = findFavoritePayeeAccount(loginUser, agreedOutAccount, transRecord.getPayeeBank(), transRecord.getPayeeAccount());
        boolean isFavorite = favoritePayeeAccount != null;
        tran.setDesignate(isDesignate);
        tran.setFavorite(isFavorite);
        tran.setPayeeBankId(transRecord.getPayeeBank());
        tran.setPayeeBankName(bankCacheManager.getBankName(transRecord.getPayeeBank(), locale));
        tran.setPayeeAccount(transRecord.getPayeeAccount());
        if (isDesignate) {
            tran.setPayeeNickname(agreedInAccount.getAccountAlias());
            tran.setPayeeAvatarCharacter(agreedInAccount.getAvatarType());
            tran.setPayeeAccountName(DataMaskUtil.maskCustName(agreedInAccount.getAccountName()));
        }
        else if (isFavorite) {
            tran.setPayeeNickname(favoritePayeeAccount.getAccountAlias());
            tran.setPayeeAvatarCharacter(favoritePayeeAccount.getDisplayAvatarCharacter());
        }
        else {
            tran.setPayeeAvatarCharacter(AIBankConstants.AVATAR_CHARACTER_DEFAULT);
        }
        tran.setPayerAccount(transRecord.getPayerAccount());
        tran.setDisplayPayerAccountId(AccountUtils.getDisplayAccountId(AIBankConstants.TFB_BANK_CODE, tran.getPayerAccount()));
        tran.setDisplayPayeeAccountId(AccountUtils.getDisplayAccountId(tran.getPayeeBankId(), tran.getPayeeAccount()));
        return tran;
    }

    /**
     * 期貨帳號
     * 
     * @param futuresAccount
     * @param index
     * @param locale
     * @return
     */
    private FuturesAcctRecord buildFutures(FuturesAccount futuresAccount, int index, Locale locale) {
        FuturesAcctRecord record = new FuturesAcctRecord();
        record.setKey(FUTURES_PAYEE_KEY_PREFIX + index);
        record.setPayeeBankId(AIBankConstants.TFB_BANK_CODE);
        record.setPayeeBankName(bankCacheManager.getBankName(AIBankConstants.TFB_BANK_CODE, locale));
        record.setPayeeAccount(futuresAccount.getUserAcc());
        if (StringUtils.equals("T", futuresAccount.getFuturesType())) {
            record.setPayeeNickname(I18NUtils.getMessage("common.futures-type.T"));
        }
        else if (StringUtils.equals("F", futuresAccount.getFuturesType())) {
            record.setPayeeNickname(I18NUtils.getMessage("common.futures-type.F"));
        }
        record.setPayeeAvatarCharacter(AIBankConstants.AVATAR_CHARACTER_DEFAULT);
        record.setDisplayAccountId(AccountUtils.getDisplayAccountId(AIBankConstants.TFB_BANK_CODE, record.getPayeeAccount()));
        return record;
    }

    /**
     * 常用轉入
     *
     * @param favoritePayeeAccount
     * @param payerKey
     * @param index
     * @param locale
     * @return
     */
    private TwPayeeRecord buildFavoritePayee(FavoriteAccount favoritePayeeAccount, String payerKey, int index, Locale locale) {
        TwPayeeRecord payee = new TwPayeeRecord();
        payee.setKey(payerKey + ";" + FAVORITE_PAYEE_KEY_PREFIX + index);
        payee.setDesignate(false);
        payee.setFavorite(true);
        payee.setPayeeBankId(favoritePayeeAccount.getPayeeBank());
        payee.setPayeeBankName(bankCacheManager.getBankName(favoritePayeeAccount.getPayeeBank(), locale));
        payee.setPayeeAccount(favoritePayeeAccount.getPayeeAccount());
        payee.setPayeeNickname(favoritePayeeAccount.getAccountAlias());
        payee.setPayeeAvatarCharacter(StringUtils.defaultString(favoritePayeeAccount.getDisplayAvatarCharacter(), AIBankConstants.AVATAR_CHARACTER_DEFAULT));
        payee.setDisplayAccountId(AccountUtils.getDisplayAccountId(payee.getPayeeBankId(), payee.getPayeeAccount()));
        payee.setSort(favoritePayeeAccount.getAccountSort());
        return payee;
    }

    /**
     * 約定轉入
     * 
     * @param agreedInAccount
     * @param index
     * @param locale
     * @return
     */
    private TwPayeeRecord buildAgreedPayee(AgreedInAccount agreedInAccount, String payerKey, int index, Locale locale) {
        TwPayeeRecord payee = new TwPayeeRecord();
        payee.setKey(payerKey + ";" + AGREED_PAYEE_KEY_PREFIX + index);
        payee.setDesignate(true);
        payee.setFavorite(false);
        payee.setPayeeBankId(agreedInAccount.getBankNo());
        payee.setPayeeBankName(bankCacheManager.getBankName(agreedInAccount.getBankNo(), locale));
        payee.setPayeeAccount(agreedInAccount.getAcno());
        payee.setPayeeNickname(agreedInAccount.getAccountAlias());
        payee.setPayeeAvatarCharacter(StringUtils.defaultString(agreedInAccount.getAvatarType(), AIBankConstants.AVATAR_CHARACTER_DEFAULT));
        payee.setPayeeAccountName(DataMaskUtil.maskCustName(agreedInAccount.getAccountName()));
        payee.setDisplayAccountId(AccountUtils.getDisplayAccountId(payee.getPayeeBankId(), payee.getPayeeAccount()));
        payee.setSort(agreedInAccount.getAccountSort());
        return payee;
    }

    /**
     * 取得臺幣約定轉出帳號
     * 
     * @param loginUser
     * @param locale
     * @return
     */
    private List<TransOutAccount> getTwAgreedOutAccount(TransOutAcctType transOutAcctType, AIBankUser loginUser, Locale locale) {
        return userAccountService.getTransOutAccounts(loginUser, locale, transOutAcctType);
    }

    /**
     * 帳號資料找約定轉出暫存
     *
     * @param transOutAcctType
     * @param loginUser
     * @param locale
     * @param payerAccount
     * @return
     */
    private TransOutAccount findAgreedOutAccount(TransOutAcctType transOutAcctType, AIBankUser loginUser, Locale locale, String payerAccount) {
        List<TransOutAccount> agreedOutAccounts = getTwAgreedOutAccount(transOutAcctType, loginUser, locale);
        return agreedOutAccounts.stream().filter(a -> StringUtils.equals(a.getAccountId(), payerAccount)).findAny().orElse(null);
    }

    /**
     * 選擇一筆資料後由鍵值找到相關資訊
     * 
     * @param accountSelector
     * @param key
     * @return
     * @throws ActionException
     */
    public TwAccountSelection findAccountSelection(TwAccountSelector accountSelector, String key) throws ActionException {

        TwAccountSelection result = new TwAccountSelection();
        if (StringUtils.startsWith(key, RECENT_TRAN_KEY_PREFIX)) {
            // 選擇最近轉帳紀錄
            TwTransRecord tran = accountSelector.getTrans().stream().filter(t -> StringUtils.equals(t.getKey(), key)).findAny().orElse(null);
            if (tran == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            result.setPayerAccount(tran.getPayerAccount());
            result.setPayeeBank(tran.getPayeeBankId());
            result.setPayeeAccount(tran.getPayeeAccount());
        }
        else if (StringUtils.startsWith(key, FUTURES_PAYEE_KEY_PREFIX)) {
            // 選擇期貨帳號
            FuturesAcctRecord futuresAcct = accountSelector.getFuturesAccts().stream().filter(t -> StringUtils.equals(t.getKey(), key)).findAny().orElse(null);
            if (futuresAcct == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            result.setPayeeBank(AIBankConstants.TFB_BANK_CODE);
            result.setPayeeAccount(futuresAcct.getPayeeAccount());
        }
        else if (StringUtils.startsWith(key, SELF_PAYEE_KEY_PREFIX)) {
            // 選擇本人帳號
            TwPayerRecord selfAcct = accountSelector.getSelfs().stream().filter(t -> StringUtils.equals(t.getKey(), key)).findAny().orElse(null);
            if (selfAcct == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            result.setPayeeBank(AIBankConstants.TFB_BANK_CODE);
            result.setPayeeAccount(selfAcct.getPayerAccount());
        }
        else {
            // 選擇常用或約定
            String[] keys = StringUtils.split(key, ";");
            if (keys == null || keys.length != 2) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            TwPayerRecord payer = findPayer(accountSelector, keys[0]);
            TwPayeeRecord payee = findPayee(accountSelector, payer, keys[1]);
            result.setPayerAccount(payer.getPayerAccount());
            result.setPayerAccountName(payer.getPayerAccountName());
            result.setPayeeBank(payee.getPayeeBankId());
            result.setPayeeAccount(payee.getPayeeAccount());
            result.setDesignate(payee.isDesignate());
            result.setPayeeAccountName(DataMaskUtil.maskCustName(payee.getPayeeAccountName()));
        }
        return result;
    }

    /**
     * 找暫存轉出資料
     * 
     * @param accountSelector
     * @param key
     * @return
     * @throws ActionException
     */
    public TwPayerRecord findPayer(TwAccountSelector accountSelector, String key) throws ActionException {
        TwPayerRecord payerRecord = accountSelector.getPayers().stream().filter(payer -> StringUtils.equals(key, payer.getKey())).findAny().orElse(null);
        if (payerRecord == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }
        return payerRecord;
    }

    /**
     * 找暫存轉入資料
     * 
     * @param accountSelector
     * @param payer
     * @param payeeKey
     * @return
     * @throws ActionException
     */
    private TwPayeeRecord findPayee(TwAccountSelector accountSelector, TwPayerRecord payer, String payeeKey) throws ActionException {
        if (StringUtils.startsWith(payeeKey, FAVORITE_PAYEE_KEY_PREFIX)) {
            List<TwPayeeRecord> payees = accountSelector.getFavoritePayeeMap().getOrDefault(payer.getKey(), new ArrayList<>());
            TwPayeeRecord payee = payees.stream().filter(t -> StringUtils.equals(t.getKey(), payer.getKey() + ";" + payeeKey)).findAny().orElse(null);
            if (payee == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            return payee;
        }
        else if (StringUtils.startsWith(payeeKey, AGREED_PAYEE_KEY_PREFIX)) {
            List<TwPayeeRecord> payees = accountSelector.getAgreedPayeeMap().getOrDefault(payer.getKey(), new ArrayList<>());
            TwPayeeRecord payee = payees.stream().filter(t -> StringUtils.equals(t.getKey(), payer.getKey() + ";" + payeeKey)).findAny().orElse(null);
            if (payee == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            return payee;
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

}
