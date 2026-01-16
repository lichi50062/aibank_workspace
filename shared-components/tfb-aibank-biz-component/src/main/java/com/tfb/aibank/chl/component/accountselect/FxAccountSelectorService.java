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
import com.tfb.aibank.chl.component.accountselect.model.FuturesAccount;
import com.tfb.aibank.chl.component.accountselect.model.FuturesAcctRecord;
import com.tfb.aibank.chl.component.accountselect.model.FxAccountSelection;
import com.tfb.aibank.chl.component.accountselect.model.FxAccountSelector;
import com.tfb.aibank.chl.component.accountselect.model.FxPayeeRecord;
import com.tfb.aibank.chl.component.accountselect.model.FxPayerRecord;
import com.tfb.aibank.chl.component.accountselect.model.FxTransRecord;
import com.tfb.aibank.chl.component.accountselect.model.RecentlyFxTransferRecord;
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
 * @(#)FxAccountSelectorService.java
 * 
 * <p>Description:處理『最近轉帳/常用/約定』帳號選擇元件(外幣)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/23, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class FxAccountSelectorService {

    public static final String CACHE_KEY = "FX_ACCOUNT_SELECTOR";

    public static final String FAVORITE_CACHE_KEY = "FAVORITE_FX_ACCOUNT_SELECTOR";

    public static final String PAYER_KEY_PREFIX = "FX_PAYER_";

    private static final String RECENT_TRAN_KEY_PREFIX = "TRAN_";

    private static final String FAVORITE_PAYEE_KEY_PREFIX = "FAV_";

    private static final String AGREED_PAYEE_KEY_PREFIX = "AGR_";

    private static final String FUTURES_PAYEE_KEY_PREFIX = "FUTURES_";

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
     * @param o
     */
    public void setInitData(boolean syncAgreeIn, TransOutAcctType transOutAcctType, FxAccountSelector accountSelector, AIBankUser loginUser, Locale locale, Object o) {
        setInitData(syncAgreeIn, transOutAcctType, accountSelector, loginUser, locale, null);
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
    public void setInitData(boolean syncAgreeIn, TransOutAcctType transOutAcctType, FxAccountSelector accountSelector, AIBankUser loginUser, Locale locale, List<FuturesAccount> futuresAcctList) {

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
    private void setPayers(TransOutAcctType transOutAcctType, FxAccountSelector accountSelector, AIBankUser loginUser, Locale locale) {

        // 取得約定轉出帳號
        List<TransOutAccount> agreedOutAccounts = getAgreedOutAccount(transOutAcctType, loginUser, locale);

        List<FxPayerRecord> payers = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(agreedOutAccounts)) {

            int index = 0;
            List<String> accounts = new ArrayList<>();
            for (TransOutAccount agreedOutAccount : agreedOutAccounts) {
                if (!agreedOutAccount.isOpenTransFlag()) {
                    continue;
                }
                String account = StringUtils.leftPadZero(agreedOutAccount.getAccountId(), 16);
                if (!accounts.contains(account)) {
                    payers.add(buildPayer(agreedOutAccount, locale, index++));
                    accounts.add(account);
                }
            }
        }

        accountSelector.setPayers(payers);
    }

    /**
     * 設置轉帳紀錄
     * 
     * @param accountSelector
     * @param loginUser
     * @param locale
     */
    private void setTrans(boolean syncAgreeIn, TransOutAcctType transOutAcctType, FxAccountSelector accountSelector, AIBankUser loginUser, Locale locale) {

        // 取得最近轉帳紀錄
        List<RecentlyFxTransferRecord> transRecords = transferResource.getRecentlyFxTransferRecord(loginUser.getCustId(), loginUser.getUserId(), loginUser.getCompanyKind(), loginUser.getUidDup()).getRecords();

        List<FxTransRecord> trans = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(transRecords)) {

            int index = 0;
            for (RecentlyFxTransferRecord transRecord : transRecords) {

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
    private void setFuturesAccts(FxAccountSelector accountSelector, List<FuturesAccount> futuresAcctList, Locale locale) {

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
    public List<FxPayeeRecord> getFavoritePayee(TransOutAcctType transOutAcctType, FxAccountSelector accountSelector, AIBankUser loginUser, Locale locale, String key) throws ActionException {

        List<FxPayeeRecord> payees = new ArrayList<>();

        Map<String, List<FxPayeeRecord>> favoritePayeeMap = accountSelector.getFavoritePayeeMap();
        if (favoritePayeeMap == null) {
            favoritePayeeMap = new HashMap<>();
        }

        if (favoritePayeeMap.containsKey(key)) {
            // 有暫存資料
            payees = favoritePayeeMap.get(key);
        }
        else {
            // 無暫存資料
            FxPayerRecord payerRecord = findPayer(accountSelector, key);
            TransOutAccount agreedOutAccount = findAgreedOutAccount(transOutAcctType, loginUser, locale, payerRecord.getPayerAccount());
            if (agreedOutAccount == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }

            List<FavoriteAccount> favoritePayeeAccounts = userAccountService.getFavoritePayeeAccounts(agreedOutAccount, loginUser);
            if (CollectionUtils.isNotEmpty(favoritePayeeAccounts)) {
                // 篩選銀行代號為012之轉入帳號
                favoritePayeeAccounts = favoritePayeeAccounts.stream().filter(a -> StringUtils.equals(a.getPayeeBank(), AIBankConstants.TFB_BANK_CODE)).toList();
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
     * @param accountSelector
     * @param loginUser
     * @param locale
     * @param key
     * @return
     * @throws ActionException
     */
    public List<FxPayeeRecord> getAgreedPayee(boolean syncAgreeIn, TransOutAcctType transOutAcctType, FxAccountSelector accountSelector, AIBankUser loginUser, Locale locale, String key) throws ActionException {

        List<FxPayeeRecord> payees = new ArrayList<>();

        Map<String, List<FxPayeeRecord>> agreedPayeeMap = accountSelector.getAgreedPayeeMap();
        if (agreedPayeeMap == null) {
            agreedPayeeMap = new HashMap<>();
        }

        if (agreedPayeeMap.containsKey(key)) {
            // 有暫存資料
            payees = agreedPayeeMap.get(key);
        }
        else {
            // 無暫存資料
            FxPayerRecord payerRecord = findPayer(accountSelector, key);
            TransOutAccount agreedOutAccount = findAgreedOutAccount(transOutAcctType, loginUser, locale, payerRecord.getPayerAccount());
            if (agreedOutAccount == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }

            List<AgreedInAccount> agreeInAccounts = userAccountService.getFxAgreedInAccounts(syncAgreeIn, agreedOutAccount, loginUser);
            if (CollectionUtils.isNotEmpty(agreeInAccounts)) {
                // 篩選銀行代號為012之轉入帳號
                agreeInAccounts = agreeInAccounts.stream().filter(a -> StringUtils.equals(a.getBankNo(), AIBankConstants.TFB_BANK_CODE)).toList();
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
     * @param loginUser
     * @param agreedOutAccount
     * @param payeeBankId
     * @param payeeAccount
     * @return
     */
    private AgreedInAccount findAgreedInAccount(boolean syncAgreeIn, AIBankUser loginUser, TransOutAccount agreedOutAccount, String payeeBankId, String payeeAccount) {
        List<AgreedInAccount> agreedInAccounts = userAccountService.getFxAgreedInAccounts(syncAgreeIn, agreedOutAccount, loginUser);
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
     * @param locale
     * @param index
     * @return
     */
    private FxPayerRecord buildPayer(TransOutAccount agreedOutAccount, Locale locale, int index) {
        FxPayerRecord payer = new FxPayerRecord();
        payer.setKey(PAYER_KEY_PREFIX + index);
        payer.setPayerAccount(agreedOutAccount.getAccountId());
        payer.setPayerAccountName(agreedOutAccount.getDisplayAccountName());
        payer.setDigitalFlg(agreedOutAccount.getDigitalFlg());
        payer.setDisplayAccountId(agreedOutAccount.getDisplayAccountId());
        payer.setDigital3Flg(agreedOutAccount.isDigital3());
        return payer;
    }

    /**
     * 轉帳紀錄
     * 
     * @param loginUser
     * @param agreedOutAccount
     * @param transRecord
     * @param index
     * @param locale
     * @return
     */
    private FxTransRecord buildTran(boolean syncAgreeIn, AIBankUser loginUser, TransOutAccount agreedOutAccount, RecentlyFxTransferRecord transRecord, int index, Locale locale) {
        FxTransRecord tran = new FxTransRecord();
        tran.setKey(RECENT_TRAN_KEY_PREFIX + index);
        AgreedInAccount agreedInAccount = findAgreedInAccount(syncAgreeIn, loginUser, agreedOutAccount, AIBankConstants.TFB_BANK_CODE, transRecord.getPayeeAccount());
        boolean isDesignate = agreedInAccount != null;
        FavoriteAccount favoritePayeeAccount = findFavoritePayeeAccount(loginUser, agreedOutAccount, AIBankConstants.TFB_BANK_CODE, transRecord.getPayeeAccount());
        boolean isFavorite = favoritePayeeAccount != null;
        tran.setDesignate(isDesignate);
        tran.setFavorite(isFavorite);
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
        tran.setPayerCur(transRecord.getPayerCur());
        tran.setPayeeBankId(AIBankConstants.TFB_BANK_CODE);
        tran.setPayeeBankName(bankCacheManager.getBankName(AIBankConstants.TFB_BANK_CODE, locale));
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
     * @param agreedInAccount
     * @param index
     * @param locale
     * @return
     */
    private FxPayeeRecord buildFavoritePayee(FavoriteAccount favoritePayeeAccount, String payerKey, int index, Locale locale) {
        FxPayeeRecord payee = new FxPayeeRecord();
        payee.setKey(payerKey + ";" + FAVORITE_PAYEE_KEY_PREFIX + index);
        payee.setDesignate(false);
        payee.setFavorite(true);
        payee.setPayeeAccount(favoritePayeeAccount.getPayeeAccount());
        payee.setPayeeNickname(favoritePayeeAccount.getAccountAlias());
        payee.setPayeeAvatarCharacter(favoritePayeeAccount.getDisplayAvatarCharacter());
        payee.setPayeeBankId(AIBankConstants.TFB_BANK_CODE);
        payee.setPayeeBankName(bankCacheManager.getBankName(AIBankConstants.TFB_BANK_CODE, locale));
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
    private FxPayeeRecord buildAgreedPayee(AgreedInAccount agreedInAccount, String payerKey, int index, Locale locale) {
        FxPayeeRecord payee = new FxPayeeRecord();
        payee.setKey(payerKey + ";" + AGREED_PAYEE_KEY_PREFIX + index);
        payee.setDesignate(true);
        payee.setFavorite(false);
        payee.setXXX(true);
        payee.setPayeeAccount(agreedInAccount.getAcno());
        payee.setPayeeNickname(agreedInAccount.getAccountAlias());
        payee.setPayeeAvatarCharacter(agreedInAccount.getAvatarType());
        payee.setPayeeAccountName(DataMaskUtil.maskCustName(agreedInAccount.getAccountName()));
        payee.setPayeeBankId(AIBankConstants.TFB_BANK_CODE);
        payee.setPayeeBankName(bankCacheManager.getBankName(AIBankConstants.TFB_BANK_CODE, locale));
        payee.setDisplayAccountId(AccountUtils.getDisplayAccountId(payee.getPayeeBankId(), payee.getPayeeAccount()));
        payee.setSort(agreedInAccount.getAccountSort());
        return payee;
    }

    /**
     * 取得約定轉出帳號
     * 
     * @param type
     * @param loginUser
     * @param locale
     * @return
     */
    private List<TransOutAccount> getAgreedOutAccount(TransOutAcctType transOutAcctType, AIBankUser loginUser, Locale locale) {
        return userAccountService.getTransOutAccounts(loginUser, locale, transOutAcctType).stream().filter(TransOutAccount::isForeignFlag).toList();
    }

    /**
     * 帳號資料找約定轉出暫存
     * 
     * @param loginUser
     * @param locale
     * @param payerAccount
     * @return
     */
    private TransOutAccount findAgreedOutAccount(TransOutAcctType transOutAcctType, AIBankUser loginUser, Locale locale, String payerAccount) {
        List<TransOutAccount> agreedOutAccounts = getAgreedOutAccount(transOutAcctType, loginUser, locale);
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
    public FxAccountSelection findAccountSelection(FxAccountSelector accountSelector, String key) throws ActionException {

        FxAccountSelection result = new FxAccountSelection();
        if (StringUtils.startsWith(key, RECENT_TRAN_KEY_PREFIX)) {
            // 選擇最近轉帳紀錄
            FxTransRecord tran = accountSelector.getTrans().stream().filter(t -> StringUtils.equals(t.getKey(), key)).findAny().orElse(null);
            if (tran == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            result.setPayerAccount(tran.getPayerAccount());
            result.setPayerCur(tran.getPayerCur());
            result.setPayeeAccount(tran.getPayeeAccount());
        }
        else if (StringUtils.startsWith(key, FUTURES_PAYEE_KEY_PREFIX)) {
            // 選擇期貨帳號
            FuturesAcctRecord futuresAcct = accountSelector.getFuturesAccts().stream().filter(t -> StringUtils.equals(t.getKey(), key)).findAny().orElse(null);
            if (futuresAcct == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            result.setPayeeAccount(futuresAcct.getPayeeAccount());
        }
        else {
            // 選擇常用或約定
            String[] keys = StringUtils.split(key, ";");
            if (keys == null || keys.length != 2) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            FxPayerRecord payer = findPayer(accountSelector, keys[0]);
            FxPayeeRecord payee = findPayee(accountSelector, payer, keys[1]);
            result.setPayerAccount(payer.getPayerAccount());
            result.setPayeeAccount(payee.getPayeeAccount());
            result.setPayerAccountName(payer.getPayerAccountName());
            result.setPayeeAccount(payee.getPayeeAccount());
            result.setDesignate(payee.isDesignate());
            result.setPayeeBank(payee.getPayeeBankId());
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
    public FxPayerRecord findPayer(FxAccountSelector accountSelector, String key) throws ActionException {
        FxPayerRecord payerRecord = accountSelector.getPayers().stream().filter(payer -> StringUtils.equals(key, payer.getKey())).findAny().orElse(null);
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
    private FxPayeeRecord findPayee(FxAccountSelector accountSelector, FxPayerRecord payer, String payeeKey) throws ActionException {
        if (StringUtils.startsWith(payeeKey, FAVORITE_PAYEE_KEY_PREFIX)) {
            List<FxPayeeRecord> payees = accountSelector.getFavoritePayeeMap().getOrDefault(payer.getKey(), new ArrayList<>());
            FxPayeeRecord payee = payees.stream().filter(t -> StringUtils.equals(t.getKey(), payer.getKey() + ";" + payeeKey)).findAny().orElse(null);
            if (payee == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            return payee;
        }
        else if (StringUtils.startsWith(payeeKey, AGREED_PAYEE_KEY_PREFIX)) {
            List<FxPayeeRecord> payees = accountSelector.getAgreedPayeeMap().getOrDefault(payer.getKey(), new ArrayList<>());
            FxPayeeRecord payee = payees.stream().filter(t -> StringUtils.equals(t.getKey(), payer.getKey() + ";" + payeeKey)).findAny().orElse(null);
            if (payee == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            return payee;
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

}
