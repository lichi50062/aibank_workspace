/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2013.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.userdatacache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.bank.BankCacheManager;
import com.tfb.aibank.chl.component.bank.BankData;
import com.tfb.aibank.chl.component.branch.BranchCacheManager;
import com.tfb.aibank.chl.component.branch.BranchData;
import com.tfb.aibank.chl.component.cardpaytype.CardPaytype;
import com.tfb.aibank.chl.component.cardpaytype.CardPaytypeCacheManager;
import com.tfb.aibank.chl.component.cardtype.CardType;
import com.tfb.aibank.chl.component.cardtype.CardTypeCacheManager;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistory;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.chl.component.userdatacache.model.AccountAlias;
import com.tfb.aibank.chl.component.userdatacache.model.CEW013RRes;
import com.tfb.aibank.chl.component.userdatacache.model.CheckMotpStatusRequest;
import com.tfb.aibank.chl.component.userdatacache.model.CheckMotpStatusResponse;
import com.tfb.aibank.chl.component.userdatacache.model.EB032280Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB12020011Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB202674D003Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB552170Response;
import com.tfb.aibank.chl.component.userdatacache.model.EB5556911Req;
import com.tfb.aibank.chl.component.userdatacache.model.EB5556911Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB5556911ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.EB5557891Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB5557891ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.FC032155Response;
import com.tfb.aibank.chl.component.userdatacache.model.FC032155RsBody;
import com.tfb.aibank.chl.component.userdatacache.model.FinancialMgmMemberLevel;
import com.tfb.aibank.chl.component.userdatacache.model.HasTrustAcct;
import com.tfb.aibank.chl.component.userdatacache.model.InvestmentNoticeSetting;
import com.tfb.aibank.chl.component.userdatacache.model.LaonAcountAmount;
import com.tfb.aibank.chl.component.userdatacache.model.LoanAccount;
import com.tfb.aibank.chl.component.userdatacache.model.MissionDetail;
import com.tfb.aibank.chl.component.userdatacache.model.MissionMaster;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE071Req;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE072Req;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE074Req;
import com.tfb.aibank.chl.component.userdatacache.model.NJWEEN02Req;
import com.tfb.aibank.chl.component.userdatacache.model.NJWEEN02Res;
import com.tfb.aibank.chl.component.userdatacache.model.NJWEEN02ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.NKNE01ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.OdsLoancustData;
import com.tfb.aibank.chl.component.userdatacache.model.PeopleSoftRes;
import com.tfb.aibank.chl.component.userdatacache.model.RetrieveUserOTPStatusResponse;
import com.tfb.aibank.chl.component.userdatacache.model.SDACTQ12Req;
import com.tfb.aibank.chl.component.userdatacache.model.SDACTQ12ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.SendEB555789Request;
import com.tfb.aibank.chl.component.userdatacache.model.SendEB555789Response;
import com.tfb.aibank.chl.component.userdatacache.model.StudentLoanData;
import com.tfb.aibank.chl.component.userdatacache.model.TrustAcctInRes;
import com.tfb.aibank.chl.component.userdatacache.model.TrustAcctInResRep;
import com.tfb.aibank.chl.component.userdatacache.model.TrustContract;
import com.tfb.aibank.chl.component.userdatacache.model.UpdateEB12020006;
import com.tfb.aibank.chl.model.account.AgreedInAccount;
import com.tfb.aibank.chl.model.account.FavoriteAccount;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.model.account.TrustAccount;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.model.creditcard.CreditCardStatus;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.error.MbErrorCode;
import com.tfb.aibank.common.model.FundOverview;
import com.tfb.aibank.common.type.CompanyBUType;
import com.tfb.aibank.common.type.CompanyKindType;
import com.tfb.aibank.common.type.CreditCardIdType;
import com.tfb.aibank.common.type.LoanCustomerGroupType;
import com.tfb.aibank.common.type.MotpAuthVerifyType;
import com.tfb.aibank.common.type.TransOutAcctType;
import com.tfb.aibank.common.util.AccountUtils;
import com.tfb.aibank.common.util.BaNCSUtil;
import com.tfb.aibank.component.dic.DicCacheManager;

import feign.Request;
import feign.Request.Options;

// @formatter:off
/**
 * @(#)UserAccountService.java
 * 
 * <p>Description:處理「使用者暫存資訊」相關操作</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class UserDataCacheService {

    private static final IBLog logger = IBLog.getLog(UserDataCacheService.class);

    @Autowired
    private AccountsResource accountResource;

    @Autowired
    private UserResource userResource;

    @Autowired
    private LoanResource loanResource;

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private AccountsResource accountsResource;

    /** 信用卡 API 介面 */
    @Autowired
    private CreditCardResource creditCardResource;
    /** 卡面資料檔 Cache Manager */
    @Autowired
    private CardTypeCacheManager cardTypeCacheManager;
    /** 卡面資料檔 Cache Manager */
    @Autowired
    private CardPaytypeCacheManager cardPayTypeCacheManager;
    /** 幣別檔 Cache Manager */
    @Autowired
    private CurrencyCodeCacheManager currencyCodeCacheManager;
    /** 分行檔 Cache Manager */
    @Autowired
    private BranchCacheManager branchCacheManager;
    @Autowired
    private BankCacheManager bankCacheManager;
    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;
    /** DIC 資料檔 */
    @Autowired
    protected DicCacheManager dicCacheManager;
    @Autowired
    private DepositResource depositResource;
    @Autowired
    private ExchangeRateHistoryCacheManager exchangeRateHistoryCacheManager;
    @Autowired
    private MutualFundResource mutualFundResource;
    @Autowired
    private InvestResource investResource;
    @Autowired
    private InformationResource informationResource;
    @Autowired
    private PreferencesResource preferencesResource;

    /**
     * (未登入)讀取「轉出帳號」清單
     *
     * @param custId
     * @param userId
     * @param nameCode
     * @param userLocale
     * @return
     */
    public List<TransOutAccount> getTransOutAccounts(String custId, String userId, String nameCode, TransOutAcctType agreedOutAcctType, Locale userLocale) {
        EB5557891Res response = accountResource.getTransOutAccounts(custId, userId, nameCode, agreedOutAcctType);
        List<TransOutAccount> accounts = convert2TransOutAccount(null, response, userLocale);
        refillExtraInfo(accounts, userLocale);
        return accounts;
    }

    /**
     * (已登入)讀取「轉出帳號」清單
     *
     * @param aibankUser
     * @param userLocale
     * @param transOutAcctType
     *            轉出帳號類型
     * @return
     */
    public List<TransOutAccount> getTransOutAccounts(AIBankUser aibankUser, Locale userLocale, TransOutAcctType transOutAcctType) {
        return getTransOutAccounts(aibankUser, userLocale, transOutAcctType, false);
    }

    /**
     * (已登入)讀取「約定轉出帳號」清單
     *
     * @param aibankUser
     * @param userLocale
     * @param transOutAcctType
     *            轉出帳號類型
     * @param reload
     *            true:重新發送EB5557891電文
     * @return
     */
    public List<TransOutAccount> getTransOutAccounts(AIBankUser aibankUser, Locale userLocale, TransOutAcctType transOutAcctType, boolean reload) {
        // 取出暫存資料
        List<TransOutAccount> accounts = transOutAcctType.getTransOutAccounts(aibankUser);
        // 判斷是否要重新發送電文
        if (reload || accounts == null) {
            // 發查電文，取得帳號清單
            EB5557891Res response = transOutAcctType.getTransOutAccounts(aibankUser, accountResource);

            if (response != null) {
                // 將帳號清單轉換成 AgreedOutAccount 物件
                accounts = convert2TransOutAccount(aibankUser, response, userLocale);
                // 將取得的帳號放進 AIBankUser 備用
                transOutAcctType.setTransOutAccounts(aibankUser, accounts);
            }
        }

        refillExtraInfo(accounts, userLocale);

        return accounts;
    }

    /**
     * #7898 配合 i18N 調整，幣別名稱、分行名稱等資訊，不再存入 Cache
     * 
     * @param accounts
     * @param userLocale
     */
    private void refillExtraInfo(List<TransOutAccount> accounts, Locale userLocale) {
        if (CollectionUtils.isNotEmpty(accounts)) {
            for (int i = 0; i < accounts.size(); i++) {
                TransOutAccount account = accounts.get(i);
                // 設置幣別名稱
                account.setCurName(currencyCodeCacheManager.getCurrencyName(account.getCurCode(), userLocale));
                // 設置分行名稱
                BranchData branchData = branchCacheManager.getBranch(account.getDisplayBranchId(), userLocale);
                if (branchData != null) {
                    account.setBranchName(branchData.getBranchName());
                }
                else {
                    account.setBranchName(account.getDisplayBranchId());
                    logger.warn("讀取分行資訊失敗，分行代碼：{}", account.getDisplayBranchId());
                }
            }
        }
    }

    /**
     * 設置帳號暱稱
     *
     * @param account
     * @param accountAliasList
     */
    private void buildAcctNickName(TransOutAccount account, List<AccountAlias> accountAliasList) {
        if (CollectionUtils.isNotEmpty(accountAliasList)) {
            // 以帳號和幣別讀取暱稱
            AccountAlias accountAlias = accountAliasList.stream().filter(a -> AccountUtils.isEquals(a.getAccountNo(), account.getAccountId()) && StringUtils.equals(a.getCurrencyEname(), account.getCurCode())).findFirst().orElse(null);
            if (accountAlias != null) {
                account.setAcctNickName(accountAlias.getAccountAlias());
            }
        }
    }

    /**
     * 將帳號資訊轉換成 TransOutAccount 物件
     *
     * @param aibankUser
     * @param response
     * @param userLocale
     * @return
     */
    private List<TransOutAccount> convert2TransOutAccount(AIBankUser aibankUser, EB5557891Res response, Locale userLocale) {

        // 將 EB5557891ResRep 轉換成 AgreedOutAccount
        List<TransOutAccount> accounts = response.getRepeats().stream().map(this::buildTransOutAccount).collect(Collectors.toList());

        // 讀取客戶全部暱稱清單

        List<AccountAlias> accountAliasList = new ArrayList<>();
        if (aibankUser != null) {
            accountAliasList = accountResource.getAccountAliasList(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind());
        }

        for (int i = 0; i < accounts.size(); i++) {
            TransOutAccount account = accounts.get(i);
            // 設置帳號暱稱
            buildAcctNickName(account, accountAliasList);
            // 設置帳號識別鍵值
            account.setAccountKey(account.getAccountId() + "_" + account.getCurCode() + "_" + i);
            // 設置「一本萬利」狀態
            account.set168TWD(AccountUtils.is168TWD(account.getProdCode(), account.getProdSubCode()));
            account.set168Full(AccountUtils.is168Full(account.getProdCode()));
            account.set168Foreign(AccountUtils.is168Foreign(account.getProdCode(), account.getProdSubCode()));
            // 設置分行名稱
            BranchData branchData = branchCacheManager.getBranch(account.getBranchNo(), userLocale);
            if (branchData != null) {
                account.setBranchName(branchData.getBranchName());
            }
            else {
                account.setBranchName(account.getBranchNo());
                logger.warn("讀取分行資訊失敗，分行代碼：{}", account.getBranchNo());
            }
        }

        // 排序：以帳號由小至大排序
        String[] sortProperties = { "acno" };
        boolean[] reverses = { false };
        IBUtils.sort(accounts, sortProperties, reverses);

        return accounts;
    }

    /**
     * 建立轉出帳號物件
     *
     * @param rep
     * @return
     */
    private TransOutAccount buildTransOutAccount(EB5557891ResRep rep) {
        TransOutAccount acct = new TransOutAccount();
        acct.setAcno(rep.getAcno());
        acct.setType(rep.getType());
        acct.setAvalAmt(rep.getAvalAmt());
        acct.setYearTerm(rep.getYearTerm());
        acct.setSlipNo(rep.getSlipNo());
        acct.setCurCod(rep.getCurCod());
        acct.setActSts(rep.getActSts());
        acct.setDocNo(rep.getDocNo());
        acct.setDueDate(rep.getDueDate());
        acct.setRate(rep.getRate());
        acct.setClazz(rep.getClazz());
        acct.setActBal(rep.getActBal());
        acct.setActBalNT(rep.getActBalNT());
        acct.setGlcdLoan(rep.getGlcdLoan());
        acct.setOriLoanBal(rep.getOriLoanBal());
        acct.setLoanTerm(rep.getLoanTerm());
        acct.setLoanTyp(rep.getLoanTyp());
        acct.setAcnoSa(rep.getAcnoSa());
        acct.setAcnoInFlag(rep.getAcnoInFlag());
        acct.setTdNo(rep.getTdNo());
        acct.setIntCycle(rep.getIntCycle());
        acct.setInsAmt(rep.getInsAmt());
        acct.setPrnStrDate(rep.getPrnStrDate());
        acct.setDateOpen(rep.getDateOpen());
        acct.setPartRecv(rep.getPartRecv());
        acct.setAchFlg(rep.getAchFlg());
        acct.setSpecSts(rep.getSpecSts());
        acct.setFlg302611(rep.getFlg302611());
        acct.setFlg302602(rep.getFlg302602());
        acct.setLnTyp(rep.getLnTyp());
        acct.setAtype(rep.getAtype());
        acct.setIcat(rep.getIcat());
        acct.setStuSpNo(rep.getStuSpNo());
        acct.setBranchNo(rep.getBranchNo());
        acct.setDigitalFlg(rep.getDigitalFlg());
        acct.setExFlg(rep.getExFlg());
        acct.setAccName(rep.getAccName());
        acct.setCrossFlg(rep.getCrossFlg());
        acct.setPwdErrCnt(rep.getPwdErrCnt());
        acct.setPassbookFlg(rep.getPassbookFlg());
        acct.setProdInd(rep.getProdInd());
        acct.setOdTotLimit(rep.getOdTotLimit());
        acct.setOdAcno(rep.getOdAcno());
        acct.setWaLnBadDebtInd(rep.getWaLnBadDebtInd());
        acct.setWaLnBcType(rep.getWaLnBcType());
        acct.setWaLnBcStag(rep.getWaLnBcStag());
        acct.setDefaultString18(rep.getDefaultString18());
        acct.setOdavail1(rep.getOdavail1());
        acct.setAlertCount(rep.getAlertCount());
        acct.setDerivative(rep.getDerivative());
        acct.setSwindleCount(rep.getSwindleCount());
        acct.setDeclineCount(rep.getDeclineCount());
        acct.setOthersCount(rep.getOthersCount());
        acct.setNoOfStops(rep.getNoOfStops());
        acct.setNoOfStopWdl(rep.getNoOfStopWdl());
        acct.setFuturesDebit(rep.getFuturesDebit());
        acct.setStockOmniAcc(rep.getStockOmniAcc());
        acct.setComplexAcc(rep.getComplexAcc());
        acct.setOsuBusiness(rep.getOsuBusiness());
        acct.setEnqAgree(rep.getEnqAgree());
        acct.setStockEnq(rep.getStockEnq());
        acct.setTdAct(rep.getTdAct());
        acct.setDisplayBranchId(rep.getDisplayBranchId());
        acct.setLimitupFlag(rep.getLimitupFlag());
        acct.setDigitalLimitupFlag(rep.getDigitalLimitupFlag());
        acct.setIntRate(rep.getIntRate());
        acct.setHappy88(rep.getHappy88());
        acct.setVideoFlg(rep.getVideoFlg());
        acct.setSpecAcctInd1(rep.getSpecAcctInd1());
        acct.setData(rep.getData());
        acct.setDefIntegerT1(rep.getDefIntegerT1());
        acct.setToption(rep.getToption());
        acct.setDefIntegerT2(rep.getDefIntegerT2());
        acct.setAccountTypeName(rep.getAcctTypeName());
        acct.setAccType(rep.getAcctType());
        acct.setAcctSubType(rep.getAcctSubType());
        acct.setAcctTypeName(rep.getAcctTypeName());
        acct.setAccountDesc(rep.getAccountDesc());
        acct.setTransOutFlag(rep.isTransOutFlag());
        acct.setOpenTransFlag(rep.isOpenTransFlag());
        acct.setPayrollCode(rep.getPayrollCode());
        return acct;
    }

    /**
     * 指定轉出帳號，發查電文查詢約定轉入帳號，並且放回 TransOutAccount 備用
     *
     * @param syncAgreeIn
     * @param agreedOutAccount
     * @param aibankUser
     * @return
     */
    public List<AgreedInAccount> getAgreedInAccounts(boolean syncAgreeIn, TransOutAccount agreedOutAccount, AIBankUser aibankUser) {
        String key = agreedOutAccount.getAccountId();
        if (aibankUser.getAgreedInAccountsMap().containsKey(key)) {
            return aibankUser.getAgreedInAccountsMap().get(key);
        }
        // Map中無此帳號Key表示為第一次取值
        List<AgreedInAccount> agreedInAccounts = new ArrayList<>();
        EB5556911Res response = accountResource.getAgreedInAccounts(buildEB5556911Request(syncAgreeIn, aibankUser, agreedOutAccount.getAccountId()), aibankUser.getCompanyKind());
        if (CollectionUtils.isNotEmpty(response.getRepeats())) {
            agreedInAccounts = response.getRepeats().stream().map(this::buildAgreedInAccount).toList();
        }
        aibankUser.getAgreedInAccountsMap().put(key, agreedInAccounts);
        return agreedInAccounts;
    }

    /**
     * 指定約定轉出帳號，發查電文查詢約定轉入帳號
     *
     * @param acnoOut
     * @param aibankUser
     * @return
     */
    public List<AgreedInAccount> getAgreedInAccounts(String acnoOut, AIBankUser aibankUser) {
        String key = StringUtils.leftPadZero(acnoOut, 16);
        if (aibankUser.getAgreedInAccountsMap().containsKey(key)) {
            return aibankUser.getAgreedInAccountsMap().get(key);
        }
        // Map中無此帳號Key表示為第一次取值
        List<AgreedInAccount> agreedInAccounts = new ArrayList<>();
        EB5556911Res response = accountResource.getAgreedInAccounts(buildEB5556911Request(false, aibankUser, acnoOut), aibankUser.getCompanyKind());
        if (CollectionUtils.isNotEmpty(response.getRepeats())) {
            agreedInAccounts = response.getRepeats().stream().map(this::buildAgreedInAccount).toList();
        }
        aibankUser.getAgreedInAccountsMap().put(key, agreedInAccounts);
        return agreedInAccounts;
    }

    /**
     * 指定約定轉出帳號，發查電文查詢約定轉入帳號
     *
     * @param acnoOut
     * @param aibankUser
     * @return
     */
    public List<AgreedInAccount> getFxexAgreedInAccounts(String acnoOut, AIBankUser aibankUser) {
        String key = StringUtils.leftPadZero(acnoOut, 16);
        if (aibankUser.getFxexInAccountsMap().containsKey(key)) {
            return aibankUser.getFxexInAccountsMap().get(key);
        }
        // Map中無此帳號Key表示為第一次取值
        List<AgreedInAccount> agreedInAccounts = new ArrayList<>();
        EB5556911Res response = accountResource.getAgreedInAccounts(buildEB5556911RequestForFx2(false, aibankUser, acnoOut), aibankUser.getCompanyKind());
        if (CollectionUtils.isNotEmpty(response.getRepeats())) {
            agreedInAccounts = response.getRepeats().stream().map(this::buildAgreedInAccount).toList();
        }
        aibankUser.getFxexInAccountsMap().put(key, agreedInAccounts);
        return agreedInAccounts;
    }

    /**
     * 指定約定轉出帳號，發查電文查詢約定轉入帳號(外幣轉帳)
     *
     * @param acnoOut
     * @param syncAgreeIn
     * @param aibankUser
     * @return
     */
    public List<AgreedInAccount> getFxAgreedInAccounts(boolean syncAgreeIn, TransOutAccount transOutAccount, AIBankUser aibankUser) {

        String acnoOut = transOutAccount.getAccountId();
        String key = StringUtils.leftPadZero(acnoOut, 16);
        if (aibankUser.getFxAgreedInAccountsMap().containsKey(key)) {
            return aibankUser.getFxAgreedInAccountsMap().get(key);
        }
        // Map中無此帳號Key表示為第一次取值
        // 外幣轉帳CK_TYPE=09再CK_TYPE=08
        List<AgreedInAccount> agreedInAccounts = new ArrayList<>();
        EB5556911Req req = buildEB5556911RequestForFx1(syncAgreeIn, aibankUser, acnoOut);
        req.setBranchIdOut(StringUtils.right(transOutAccount.getBranchNo(), 3));
        req.setBranchIdMap(getBranchIdMap(aibankUser));

        EB5556911Res response1 = accountResource.getFxAgreedInAccounts(req, aibankUser.getCompanyKind());
        if (CollectionUtils.isNotEmpty(response1.getRepeats())) {
            for (EB5556911ResRep resrep : response1.getRepeats()) {
                if (resrep.isFilter()) {
                    continue;
                }
                agreedInAccounts.add(buildAgreedInAccount(resrep));
            }
        }
        aibankUser.getFxAgreedInAccountsMap().put(key, agreedInAccounts);
        return agreedInAccounts;
    }

    private Map<String, String> getBranchIdMap(AIBankUser aibankUser) {
        Map<String, String> map = new HashMap<>();
        for (List<TransOutAccount> outAccounts : aibankUser.getTransOutAccountsMap().values()) {
            for (TransOutAccount account : outAccounts) {
                map.put(account.getAccountId(), account.getBranchNo());
            }
        }
        return map;
    }

    /**
     * 建置「約定轉入帳號」的電文上行資訊
     *
     * @param syncAgreeIn
     * @param aibankUser
     * @param acnoOut
     * @return
     */
    private EB5556911Req buildEB5556911Request(boolean syncAgreeIn, AIBankUser aibankUser, String acnoOut) {
        return buildEB5556911Request(syncAgreeIn, aibankUser.getCustId(), aibankUser.getUserId(), aibankUser.getNameCode(), acnoOut, aibankUser.getUidDup(), "00");
    }

    /**
     * 建置「約定轉入帳號」的電文上行資訊
     *
     * @param aibankUser
     * @param acnoOut
     * @return
     */
    private EB5556911Req buildEB5556911RequestForFx1(boolean syncAgreeIn, AIBankUser aibankUser, String acnoOut) {
        return buildEB5556911Request(syncAgreeIn, aibankUser.getCustId(), aibankUser.getUserId(), aibankUser.getNameCode(), acnoOut, aibankUser.getUidDup(), "09");
    }

    /**
     * 建置「約定轉入帳號」的電文上行資訊
     *
     * @param aibankUser
     * @param acnoOut
     * @return
     */
    private EB5556911Req buildEB5556911RequestForFx2(boolean syncAgreeIn, AIBankUser aibankUser, String acnoOut) {
        return buildEB5556911Request(syncAgreeIn, aibankUser.getCustId(), aibankUser.getUserId(), aibankUser.getNameCode(), acnoOut, aibankUser.getUidDup(), "08");
    }

    /**
     * 建置「約定轉入帳號」的電文上行資訊
     *
     * @param syncAgreeIn
     * @param custId
     * @param userId
     * @param nameCode
     * @param acnoOut
     * @param uidDup
     * @return
     */
    private EB5556911Req buildEB5556911Request(boolean syncAgreeIn, String custId, String userId, String nameCode, String acnoOut, String uidDup, String ckType) {
        EB5556911Req request = new EB5556911Req();
        request.setSyncAgreeIn(syncAgreeIn);
        request.setIdno(custId);
        request.setNameCod(nameCode);
        request.setUserId(userId);
        request.setAcnoOut(acnoOut);
        request.setCkType(StringUtils.defaultString(ckType, "00"));
        request.setUidDup(uidDup);
        return request;
    }

    /**
     * 建立約定轉入帳號物件
     *
     * @param rep
     * @return
     */
    private AgreedInAccount buildAgreedInAccount(EB5556911ResRep rep) {
        AgreedInAccount acct = new AgreedInAccount();
        acct.setBankNo(rep.getBankNo());
        acct.setAcno(rep.getAcno());
        acct.setCustName(rep.getCustName());
        acct.setAcnoType(rep.getAcnoType());
        acct.setAcnoFlg(rep.getAcnoFlg());
        acct.setType(rep.getType());
        acct.setDigitType(rep.getDigitType());
        acct.setFsFlg(rep.getFsFlg());
        acct.setProdCode(rep.getProdCode());
        acct.setBranchNo(rep.getBranchNo());
        acct.setCurr(rep.getCurr());
        acct.setAccountSort(rep.getAccountSort());
        acct.setAccountAlias(rep.getAccountAlias());
        acct.setAvatarType(rep.getAvatarType());
        acct.setDisplayBranchId(rep.getDisplayBranchId());
        acct.setNotifyEmail(rep.getNotifyEmail());
        return acct;
    }

    /**
     * 指定約定轉出帳號取得對應常用帳號
     *
     * @param agreedOutAccount
     *            約定轉出帳號
     * @param aibankUser
     *            使用者
     * @return
     */
    public List<FavoriteAccount> getFavoritePayeeAccounts(TransOutAccount agreedOutAccount, AIBankUser aibankUser) {
        String key = agreedOutAccount.getAccountId();
        if (aibankUser.getFavoriteAccountsMap().containsKey(key)) {
            return aibankUser.getFavoriteAccountsMap().get(key);
        }
        // Map中無此帳號Key表示為第一次取值
        List<FavoriteAccount> favoritePayeeAccounts = accountResource.getFavoritePayeeAccounts(aibankUser.getCustId(), aibankUser.getUserId(), agreedOutAccount.getAccountId(), aibankUser.getCompanyKind(), aibankUser.getUidDup());
        aibankUser.getFavoriteAccountsMap().put(key, CollectionUtils.isNotEmpty(favoritePayeeAccounts) ? favoritePayeeAccounts : new ArrayList<>());
        return favoritePayeeAccounts;
    }

    /**
     * 此方法非依照共通業務辦法 如需依照請參考 validateCreditCardStatus
     *
     * 檢查信用卡狀態，判斷「是否為特殊戶」或「未持有信用卡」
     *
     * @return true:「是特殊戶」或「未持有信用卡」
     * @throws ActionException
     */
    public boolean checkCreditCardStatus(AIBankUser aibankUser) {
        logger.debug("!!!!此方法為非依照共通業務辦法的特別處理  如需依照請參考 validateCreditCardStatus");
        return isSpecialCreditCard(aibankUser) || !haveCreditCard(aibankUser);
    }

    /**
     * 檢查信用卡狀態，若為信用卡特殊戶或未持有信用卡，顯示錯誤訊息。
     *
     * @param aibankUser
     * @throws ActionException
     * @see 共通業務版法
     */
    public void validateCreditCardStatus(AIBankUser aibankUser) throws ActionException {
        if (isSpecialCreditCard(aibankUser)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.SPECIAL_CARD_USER);
        }
        if (!haveCreditCard(aibankUser)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.CREDIT_CARD_NOT_FOUND);
        }
    }

    /**
     * 是否為信用卡特殊戶況
     *
     * @return
     * @throws ActionException
     */
    public boolean isSpecialCreditCard(AIBankUser aibankUser) {
        if (aibankUser.isSpecialCreditCard() == null) {
            loadCreditCardStatus(aibankUser);
        }
        return aibankUser.isSpecialCreditCard();
    }

    /**
     * 是否持有信用卡
     *
     * @return
     * @throws ActionException
     */
    public boolean haveCreditCard(AIBankUser aibankUser) {
        if (aibankUser.isHaveCreditCard() == null) {
            loadCreditCardStatus(aibankUser);
        }
        return aibankUser.isHaveCreditCard();
    }

    /**
     * 讀取「信用卡戶況、註記」
     *
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    private void loadCreditCardStatus(AIBankUser aibankUser) {
        BaseServiceResponse<CreditCardStatus> creditCardStatusResponse = null;

        try {
            creditCardStatusResponse = creditCardResource.getCreditCardStatus(aibankUser.getCustId());
            if (creditCardStatusResponse.getData() != null) {
                CreditCardStatus creditCardStatus = creditCardStatusResponse.getData();
                aibankUser.setSpecialCreditCard(StringUtils.equalsIgnoreCase(creditCardStatus.getAccountStsCode(), "Y"));
                aibankUser.setHaveCreditCard(StringUtils.equalsIgnoreCase(creditCardStatus.getCardholderCode(), "Y"));
            }
        }
        catch (ServiceException e) {

            logger.error("= getCreditCardStatus error ", e);
            logger.info("= getCreditCardStatus error getExtraInfo: {}", e.getExtraInfo());
            // 如果電文回覆X開頭錯誤，原本的errorCode 會是EAI01，多判斷原始EAI錯誤代碼
            // 當與信用卡牌卡資訊相關的電文(CEW301R/CE6220R/CEW303R/CEW313R)回覆X202/X220時，把錯誤代碼換回原始錯誤errorCode
            // 2025/08/12 配合X開頭錯誤改為不轉換為EAI01，調整相關判斷
            if (null != e.getErrorCode()) {

                List<String> nightmodeEaiErrCodes = Stream.of("X202", "X220").toList();

                if (nightmodeEaiErrCodes.contains(e.getErrorCode())) {
                    // 查CEW301R時，如果是X202/X220，都拋出SVC02051

                    ActionException ae = ExceptionUtils.getActionException(AIBankErrorCode.CREDIT_CARD_DATA_UPDATING);
                    // 避免異動太多既有程式，只拋出原本的ServiceException，不拋ActionException
                    e.setErrorCode(ae.getErrorCode());
                    e.setErrorDesc(ae.getErrorDesc());
                    e.setSeverity(ae.getSeverity());
                    e.setSystemId(ae.getSystemId());
                }
            }
            throw e;
        }

    }

    /**
     * 讀取「全部歸戶信用卡」清單
     *
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public List<CreditCard> getAllCreditCards(AIBankUser aibankUser, Locale locale) throws ActionException {
        List<CreditCard> allCreditCards = Optional.ofNullable(aibankUser.getAllCreditCards()).filter(CollectionUtils::isNotEmpty).orElseGet(() -> {
            List<CreditCard> cards = creditCardResource.getAllCreditCardList(aibankUser.getCustId(), "3");
            return cards.stream().map(creditCard -> {
                CardType cardType = cardTypeCacheManager.getCardType(creditCard.getCardType(), locale);
                String cardName = buildCardName(creditCard, cardType);
                creditCard.setCardTypeInfo(cardType);
                creditCard.setCardName(cardName);
                return creditCard;
            }).toList();
        });

        if (CollectionUtils.isEmpty(allCreditCards)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.CREDIT_CARD_NOT_FOUND);
        }
        aibankUser.setAllCreditCards(allCreditCards);
        return allCreditCards;
    }

    /**
     * 更新AIBankUser 中的 信用卡 List 中特定的卡片資料
     *
     * @param aibankUser
     * @param card
     */
    public void updateCachedCreditCard(AIBankUser aibankUser, CreditCard card) {
        if (aibankUser.getAllCreditCards() == null)
            return;

        List<CreditCard> tmpAllCards = new ArrayList<CreditCard>();
        for (CreditCard one : aibankUser.getAllCreditCards()) {
            if (one.getCardKey().equals(card.getCardKey())) {
                tmpAllCards.add(card);
            }
            else {
                tmpAllCards.add(one);
            }
        }
        aibankUser.setAllCreditCards(tmpAllCards);
    }

    /**
     * 讀取「有效的歸戶信用卡」清單
     *
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public List<CreditCard> getEffectiveCreditCards(AIBankUser aibankUser, Locale locale) throws ActionException {
        List<CreditCard> allCreditCards = getAllCreditCards(aibankUser, locale);
        // 過濾過期卡片
        return allCreditCards.stream().filter(card -> !isCardExpire(card.getCardExpired())).toList();
    }

    /**
     * 依cardKey，取得指定信用卡資訊
     *
     * @return
     * @throws ActionException
     */
    public CreditCard getCreditCardByCardKey(AIBankUser aibankUser, String cardKey, Locale locale) throws ActionException {
        List<CreditCard> allCreditCards = getAllCreditCards(aibankUser, locale);
        return allCreditCards.stream().filter(card -> StringUtils.equals(card.getCardKey(), cardKey)).findAny().orElse(null);
    }

    /**
     * 依卡號，取得指定信用卡資訊
     *
     * @return
     * @throws ActionException
     */
    public CreditCard getCreditCard(AIBankUser aibankUser, String cardNo, Locale locale) throws ActionException {
        List<CreditCard> allCreditCards = getAllCreditCards(aibankUser, locale);
        return allCreditCards.stream().filter(card -> StringUtils.equals(card.getCardNo(), cardNo)).findAny().orElse(null);
    }

    /**
     * 取得所有正卡項下附卡資訊
     *
     * @return
     * @throws ActionException
     */
    public List<CreditCard> getSupplementaryCardInfo(AIBankUser aibankUser, String cardNo, Locale locale) throws ActionException {
        List<CreditCard> allCreditCards = getAllCreditCards(aibankUser, locale);
        return allCreditCards.stream().filter(card -> StringUtils.equals(card.getmCardNo(), cardNo) && StringUtils.notEquals(card.getCardNo(), cardNo)).collect(Collectors.toList());
    }

    /**
     * 信用卡身分別，分為正卡人、附卡人兩種身分
     *
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public CreditCardIdType getCreditCardIdType(AIBankUser aibankUser, Locale locale) throws ActionException {
        CreditCardIdType creditCardIdType = aibankUser.getCreditCardIdType();
        if (creditCardIdType == null) {
            List<CreditCard> allCreditCards = getAllCreditCards(aibankUser, locale);
            boolean isPrimary = allCreditCards.stream().anyMatch((card -> StringUtils.equals(card.getmCardNo(), card.getCardNo())));
            if (isPrimary) {
                creditCardIdType = CreditCardIdType.PRIMARY_CARD;
            }
            else {
                creditCardIdType = CreditCardIdType.SUPPLEMENTARY_CARD;
            }
            // 將值回存 AIBankUser
            aibankUser.setCreditCardIdType(creditCardIdType);
        }
        return creditCardIdType;
    }

    /**
     * 檢核客戶是否為「信用卡正卡持有人」
     *
     * @param aibankUser
     * @param userLocale
     * @throws ActionException
     */
    public void validatePrimaryCardholderOnly(AIBankUser aibankUser, Locale userLocale) throws ActionException {
        CreditCardIdType creditCardIdType = getCreditCardIdType(aibankUser, userLocale);
        if (!creditCardIdType.isPrimaryCard()) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.PRIMARY_CARDHOLDER_ONLY);
        }
    }

    /**
     * 是否正卡客戶(至少有一張正卡)
     *
     * @param aibankUser
     * @throws ActionException
     */
    public boolean isPrimaryCard(AIBankUser aibankUser, Locale locale) throws ActionException {
        CreditCardIdType creditCardIdType = getCreditCardIdType(aibankUser, locale);
        return creditCardIdType.isPrimaryCard();
    }

    /**
     * 清空暫存資料
     *
     * @param aibankUser
     */
    public void cleanCache(AIBankUser aibankUser) {
        aibankUser.setAllCreditCards(null);
        aibankUser.setEffectiveCreditCards(null);
        aibankUser.setSpecialCreditCard(null);
        aibankUser.setHaveCreditCard(null);
    }

    /**
     * 判斷卡片日期是否過期
     *
     * @param expiredDate
     *            MMYY
     * @return
     */
    private Boolean isCardExpire(String expiredDate) {
        if (StringUtils.isBlank(expiredDate)) {
            return true;
        }
        Date currentDate = new Date();
        // 將輸入日期解析為 LocalDate 物件
        int month = Integer.parseInt(expiredDate.substring(0, 2));
        int year = Integer.parseInt(expiredDate.substring(2));
        month = month + 1;
        if (month == 13) {
            month = 1;
            year = year + 1;
        }
        Date cardExpiredDate = DateUtils.getDate(2000 + year, month, 1);
        Date lastDateOfCurrentMonth = DateUtils.getLastDayOfMonth(currentDate);

        Boolean r = cardExpiredDate.before(lastDateOfCurrentMonth);
        return r;
    }

    // @formatter:off
    /**
     * 信用卡名稱
     * <ol> 
     *  <li>若有設定暱稱時，顯示{暱稱}。</li>
     *  <li>若未設定暱稱時，顯示{卡別}；若無{卡別}資料，預設顯示“富邦信用卡”</li>
     * </ol>
     * 
     * @param card
     * @param cardType
     * @return
     */
    // @formatter:on    
    private String buildCardName(CreditCard card, CardType cardType) {
        if (StringUtils.isNotBlank(card.getCardNickname())) {
            return card.getCardNickname();
        }
        return Optional.ofNullable(cardType).map(CardType::getCardTypeName).orElse(I18NUtils.getMessage("common.credit_card.card_name.default")); // 富邦信用卡
    }

    /**
     * 取得 支付方式
     *
     * @param cardPayType
     * @param local
     * @return
     */
    public CardPaytype getCardPaytype(String cardPayType, Locale local) {
        return cardPayTypeCacheManager.getCardPaytype(cardPayType, local.toString());
    }

    /**
     * 取得OTP申請註記：EB552170SvcRs.OTPFLG
     *
     * @param aibankUser
     * @return
     */
    public String getOtpFlagFromEB552170(AIBankUser aibankUser) {
        String otpFlagFromEB552170 = aibankUser.getOtpFlagFromEB552170();
        if (null == otpFlagFromEB552170) {
            this.callEB552170(aibankUser);
            otpFlagFromEB552170 = aibankUser.getOtpFlagFromEB552170();
        }
        return otpFlagFromEB552170;
    }

    /**
     * 取得發送簡訊OTP的行動電話：EB552170SvcRs.OTP_MOBILE
     *
     * @param aibankUser
     * @return
     */
    public String getOtpMobileFromEB552170(AIBankUser aibankUser) {
        String otpMobileFromEB552170 = aibankUser.getOtpMobileFromEB552170();
        if (null == otpMobileFromEB552170) {
            this.callEB552170(aibankUser);
            otpMobileFromEB552170 = aibankUser.getOtpMobileFromEB552170();
        }
        return otpMobileFromEB552170;
    }

    /**
     * 取得發送簡訊OTP的行動電話：EB552170SvcRs.OTP_MOBILE
     *
     * @param aibankUser
     * @return
     */
    public String getOtpMobileEmpFromEB552170(AIBankUser aibankUser) {
        String otpMobileEmpFromEB552170 = aibankUser.getOtpMobileEmpFromEB552170();
        if (null == otpMobileEmpFromEB552170) {
            this.callEB552170(aibankUser);
            otpMobileEmpFromEB552170 = aibankUser.getOtpMobileEmpFromEB552170();
        }
        return otpMobileEmpFromEB552170;
    }

    /**
     * 取得登入暫存之多使用者代碼註記(OPN_CN_FLAG) 若無法取得，則發查EB552170電文
     *
     * 卡戶不需檢查
     * 
     * @param aibankUser
     * @return true: 表示為單一使用者
     */
    public Boolean getOpnCnFlag(AIBankUser aibankUser) {
        if (aibankUser.getCustomerType().isCardMember()) {
            return Boolean.TRUE;
        }
        Boolean opnCnFlag = aibankUser.getOpnCnFlag();
        if (null == opnCnFlag) {
            EB552170Response response = userResource.sendEB552170ForSingleUser(aibankUser.getCustId(), aibankUser.getUidDup());
            if (response.getOpnCnt() != null && StringUtils.equals(response.getOpnCnt().toPlainString(), "1")) {
                opnCnFlag = Boolean.TRUE;
            }
            else {
                opnCnFlag = Boolean.FALSE;
            }
            aibankUser.setOpnCnFlag(opnCnFlag);
        }
        return opnCnFlag;
    }

    /**
     * callEB552170
     *
     * @param aibankUser
     */
    private void callEB552170(AIBankUser aibankUser) {
        RetrieveUserOTPStatusResponse response = userResource.retrieveUserOTPStatus(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getNameCode());
        aibankUser.setOtpFlagFromEB552170(response.getOtpFlag());
        aibankUser.setOtpMobileFromEB552170(response.getOtpMobile());
        aibankUser.setOtpMobileEmpFromEB552170(response.getOtpMobileEmp());
    }

    /**
     * 重設OTP申請註記
     *
     * @param aibankUser
     */
    public void resetOtpFlagFromEB552170(AIBankUser aibankUser) {
        aibankUser.setOtpFlagFromEB552170(null);
        aibankUser.setOtpMobileFromEB552170(null);
        aibankUser.setOtpMobileEmpFromEB552170(null);
    }

    /**
     * 取得發送簡訊OTP的行動電話：CEW013RSvcRs.MOBILE
     *
     * @param aibankUser
     * @return
     */
    public String getOtpMobileFromCEW013R(AIBankUser aibankUser) {

        String otpMobileFromCEW013R = aibankUser.getOtpMobileFromCEW013R();
        if (null == otpMobileFromCEW013R) {
            try {
                CEW013RRes res = creditCardResource.sendCEW013R(aibankUser.getCustId());
                otpMobileFromCEW013R = res.getMobile();
                aibankUser.setOtpMobileFromCEW013R(otpMobileFromCEW013R);
            }
            catch (ServiceException ex) { // Fortify：Poor Error Handling: Overly Broad Catch，必要寫法，需攔截所有錯誤
                logger.warn("取得發送簡訊OTP的行動電話失敗 CEW013R", ex);
            }
        }

        return otpMobileFromCEW013R;
    }

    /**
     * 取得信用卡客戶資料：CEW013RS
     *
     * @param aibankUser
     * @return
     */
    public CEW013RRes getCEW013R(AIBankUser aibankUser) {

        CEW013RRes cew013RRes = aibankUser.getCew013RRes();
        if (null == cew013RRes) {
            try {
                cew013RRes = creditCardResource.sendCEW013R(aibankUser.getCustId());
                aibankUser.setCEW013RRes(cew013RRes);
            }
            catch (ServiceException ex) { // Fortify：Poor Error Handling: Overly Broad Catch，必要寫法，需攔截所有錯誤
                logger.warn("取得CEW013R失敗 ", ex);
            }
        }

        return cew013RRes;
    }

    /**
     * 取得MOTP申請註記
     * 
     * USER可能會在登入期間操作(切換開啟關閉)MOTP狀態，此項目不可用Cache方式取得
     * 
     * @param aibankUser
     * @param deviceIxd
     * @return
     */
    public String getMotpFlag(AIBankUser aibankUser, String deviceIxd) {
        CheckMotpStatusRequest request = new CheckMotpStatusRequest();
        request.setCustId(aibankUser.getCustId());
        request.setUserId(aibankUser.getUserId());
        request.setCompanyKind(aibankUser.getCompanyKind());
        request.setUidDup(aibankUser.getUidDup());
        request.setDeviceIxd(deviceIxd);
        CheckMotpStatusResponse response = securityResource.checkMotpStatus(request);
        MotpAuthVerifyType motpAuthVerifyType = MotpAuthVerifyType.find(response.getMotpAuthVerifyType());
        String motpFlag = motpAuthVerifyType.isValid() ? "Y" : "N";
        aibankUser.setMotpFlag(motpFlag);
        aibankUser.setMotpAuthVerifyType(motpAuthVerifyType);
        return motpFlag;
    }

    /**
     * 重設MOTP申請註記
     *
     * @param aibankUser
     */
    public void resetMotpFlag(AIBankUser aibankUser) {
        aibankUser.setMotpFlag(null);
    }

    /**
     * 檢核是否為「單一使用者」
     *
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public void validateSingleUser(AIBankUser aibankUser) throws ActionException {
        if (!getOpnCnFlag(aibankUser)) {
            logger.info("此客戶不為單一使用者代碼。");
            throw ExceptionUtils.getActionException(MbErrorCode.MB3503);
        }
    }

    /**
     * 取得OBU/DBU
     *
     * @param aibankUser
     * @return
     */
    public CompanyBUType getBuType(AIBankUser aibankUser) throws ActionException {
        CompanyBUType buType = aibankUser.getBuType();
        if (buType.equals(CompanyBUType.UNKNOWN)) {
            // IDType =11 且 長度為10 且 誤別碼為0 為DBU(本國自然人)
            // IDType =21 且 長度為8 且 誤別碼為0 為DBU (本國法人)
            //
            if (("11".equals(BaNCSUtil.getIDTYPE(aibankUser.getCustId())) && aibankUser.getCustId().length() == 10 && "0".equals(aibankUser.getUidDup())) || "21".equals(BaNCSUtil.getIDTYPE(aibankUser.getCustId())) && aibankUser.getCustId().length() == 8 && "0".equals(aibankUser.getUidDup())) {
                buType = CompanyBUType.DBU;
                aibankUser.setBuType(buType);
                aibankUser.getCompanyVo().setCompanyBUType(buType.getCode());
            }
            else {
                List<TransOutAccount> accounts = getTransOutAccounts(aibankUser, Locale.TRADITIONAL_CHINESE, TransOutAcctType.TW_FX_TRANS_OUT_ACCT_OVERVIEW_TW_FX_SAVING_CHECK, true);
                if (CollectionUtils.isEmpty(accounts)) {
                    throw ExceptionUtils.getActionException(AIBankErrorCode.PAYER_ACCOUNTS_IS_EMPTY);
                }
                Integer response = accountResource.getBuTypeByAccount(accounts.get(0).getAcno());
                buType = CompanyBUType.find(response);
                aibankUser.setBuType(buType);
                aibankUser.getCompanyVo().setCompanyBUType(buType.getCode());
            }
        }
        return buType;
    }

    /**
     * 檢核阻擋帳戶的特殊註記
     *
     * @param custId
     * @return
     */
    public boolean isUserRemarkErrorPass(String custId) {
        // 系統參數檔取得阻擋帳戶的特殊註記
        String passCode = systemParamCacheManager.getValue("PROJECT", "FC032155_ERROR_PASS_CODE_LIST");
        if (logger.isDebugEnabled())
            logger.debug("FC032155_ERROR_PASS_CODE_LIST:{}", passCode);

        if (StringUtils.isNotBlank(passCode)) {
            FC032155Response resp = userResource.getUserRemark(custId);

            List<FC032155RsBody> rsList = resp.getRsList();
            for (FC032155RsBody rs : rsList) {
                String[] passCodeArray = StringUtils.split(passCode, ",");
                boolean found1 = Arrays.stream(passCodeArray).anyMatch(str -> str.equals(rs.getTelCod1()));
                boolean found2 = Arrays.stream(passCodeArray).anyMatch(str -> str.equals(rs.getTelCod2()));

                // FC032155_RS.TEL_COD_1 = {註記}，並且FC032155_RS.ST_1不為Y。
                // FC032155_RS.TEL_COD_2 = {註記}，並且FC032155_RS.ST_2不為Y。
                if ((found1 && !StringUtils.equals(StringUtils.Y, rs.getSt1())) || (found2 && !StringUtils.equals(StringUtils.Y, rs.getSt2()))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 是否為行員(在職 或 退休)
     *
     * @param aibankUser
     * @return
     */
    public boolean isEmployee(AIBankUser aibankUser) {
        return isCurrentEmployee(aibankUser) || isRetiredEmployee(aibankUser);
    }

    /**
     * 是否為「在職」行員
     *
     * @param aibankUser
     * @return
     */
    public boolean isCurrentEmployee(AIBankUser aibankUser) {
        if (aibankUser.isEmployee() == null) {
            String uuidDup = aibankUser.getCustIdWithDup();
            if (StringUtils.length(uuidDup) != 11) {
                return false;
            }
            if (StringUtils.equals(StringUtils.right(uuidDup, 1), "0")) {
                uuidDup = StringUtils.left(uuidDup, 10);
            }
            aibankUser.setEmployee(userResource.isEmployee(uuidDup));
        }
        return aibankUser.isEmployee();
    }

    /**
     * 是否為「退休」行員
     *
     * @param aibankUser
     * @return
     */
    public boolean isRetiredEmployee(AIBankUser aibankUser) {
        if (aibankUser.isRetiredEmployee() == null) {
            String uuidDup = aibankUser.getCustIdWithDup();
            if (StringUtils.length(uuidDup) != 11) {
                return false;
            }
            if (StringUtils.equals(StringUtils.right(uuidDup, 1), "0")) {
                uuidDup = StringUtils.left(uuidDup, 10);
            }
            aibankUser.setRetiredEmployee(userResource.isRetiredEmployee(uuidDup));
        }
        return aibankUser.isRetiredEmployee();
    }

    /**
     * 取得客戶貸款客群
     *
     * @param aibankUser
     * @return
     */
    public LoanCustomerGroupType getLoanGroupType(AIBankUser aibankUser) {

        /** 已查過 */
        if (aibankUser.getLoanGroupType() != null)
            return aibankUser.getLoanGroupType();

        /** 企業客群 */
        if (aibankUser.getCompanyKindType() == CompanyKindType.COMPANY) {
            aibankUser.setLoanGroupType(LoanCustomerGroupType.GROUP_ENTERPRISE);
            return aibankUser.getLoanGroupType();
        }

        OdsLoancustData odsLoancustData = loanResource.getLoanCustomerGreup(aibankUser.getCustIdAndCheckDup());

        if (odsLoancustData != null && odsLoancustData.getAibFlag() != null && odsLoancustData.getAibAmt() != null) {
            aibankUser.setLoanGroupType(LoanCustomerGroupType.find(odsLoancustData.getAibFlag()));
            aibankUser.setOdsLoancustData(odsLoancustData);
            return aibankUser.getLoanGroupType();
        }

        OdsLoancustData odsLoancustData4 = loanResource.getLoanCustomerGreup("-");
        if (odsLoancustData4 != null && odsLoancustData4.getAibFlag() != null) {
            aibankUser.setLoanGroupType(LoanCustomerGroupType.find(odsLoancustData4.getAibFlag()));
            aibankUser.setOdsLoancustData(odsLoancustData4);
            return aibankUser.getLoanGroupType();
        }
        return LoanCustomerGroupType.GROUP_4;

    }

    /**
     * 讀取貸款帳號
     * 
     * @param aibankUser
     * @param isReload
     * @return
     */
    public List<LoanAccount> getAccountInfoLoan(AIBankUser aibankUser, boolean isReload) {
        if (aibankUser.getLoanAccount() == null || isReload) {
            SendEB555789Request request = new SendEB555789Request();
            request.setIdno(aibankUser.getCustIdAndCheckDup());
            request.setUidDup(aibankUser.getUidDup());
            request.setUserId(aibankUser.getUserId());
            request.setCompanyKind(aibankUser.getCompanyKind());
            request.setNameCod(aibankUser.getNameCode());
            request.setIdType(BaNCSUtil.getIDTYPE(aibankUser.getCustId()));
            final Options OPTIONS = new Request.Options(120, TimeUnit.SECONDS, 300, TimeUnit.SECONDS, true);
            SendEB555789Response response = loanResource.getAllLoanAccount(request, OPTIONS);
            aibankUser.setLoanAccount(response.getLoanAccount());
            aibankUser.setLoanAccountHomeCard(buildLoanAccountList(response.getLoanAccount()));
        }
        return aibankUser.getLoanAccount();
    }

    /**
     * #8394須排除尚未撥款的分期型貸款帳號 若為分期型貸款(LOAN_ACCOUNT_INFO.PRODUCT_TYPE<>C)，\ 須排除尚未撥款(EB555789_RS_D001.ACT_STS='新戶'
     * 
     * @param aibankUser
     * @param isReload
     * @return
     */
    public List<LoanAccount> getAccountInfoLoanWithoutUnDisbursedInstallmentLoans(AIBankUser aibankUser, boolean isReload) {
        List<LoanAccount> loanAccs = getAccountInfoLoan(aibankUser, isReload);

        if (CollectionUtils.isEmpty(loanAccs)) {
            return new ArrayList<>();
        }
        return loanAccs.stream().filter(acc -> {
            // 排除：分期型貸款 且 尚未撥款（即：PRODUCT_TYPE ≠ C 且 ACT_STS = '新戶'）
            // 新戶判斷用中文
            return !(StringUtils.notEquals(acc.getProductType(), "C") && StringUtils.equals(acc.getActSts(), "新戶"));
        }).toList();
    }

    /** 貸款牌卡取得貸款帳號 */
    public List<LoanAccount> getAccountInfoHomeCardLoan(AIBankUser aibankUser, boolean isReload) {
        if (aibankUser.getLoanAccountHomeCard() == null || isReload) {
            SendEB555789Request request = new SendEB555789Request();
            request.setIdno(aibankUser.getCustIdAndCheckDup());
            request.setUidDup(aibankUser.getUidDup());
            request.setUserId(aibankUser.getUserId());
            request.setCompanyKind(aibankUser.getCompanyKind());
            request.setNameCod(aibankUser.getNameCode());
            request.setIdType(BaNCSUtil.getIDTYPE(aibankUser.getCustId()));
            final Options OPTIONS = new Request.Options(120, TimeUnit.SECONDS, 300, TimeUnit.SECONDS, true);
            SendEB555789Response response = loanResource.getAllHomeCardLoanAccount(request, OPTIONS);
            aibankUser.setLoanAccountHomeCard(response.getLoanAccount());
        }
        return aibankUser.getLoanAccountHomeCard();
    }

    /** 取得貸款帳號 - 沒登入的時候 */
    public List<LoanAccount> getAccountInfoLoan(String custId, String uidDup, String userId, Integer companyKind, String nameCode) {
        SendEB555789Request request = new SendEB555789Request();
        request.setIdno(custId);
        request.setUidDup(uidDup);
        request.setUserId(userId);
        request.setCompanyKind(companyKind);
        request.setNameCod(nameCode);
        request.setIdType(BaNCSUtil.getIDTYPE(custId));
        final Options OPTIONS = new Request.Options(120, TimeUnit.SECONDS, 120, TimeUnit.SECONDS, true);
        SendEB555789Response response = loanResource.getAllLoanAccount(request, OPTIONS);
        return response.getLoanAccount();
    }

    /** 貸款牌卡取得貸款帳號 - 沒登入的時候 */
    public List<LoanAccount> getAccountInfoHomeCardLoan(String custId, String uidDup, String userId, Integer companyKind, String nameCode) {
        SendEB555789Request request = new SendEB555789Request();
        request.setIdno(custId);
        request.setUidDup(uidDup);
        request.setUserId(userId);
        request.setCompanyKind(companyKind);
        request.setNameCod(nameCode);
        request.setIdType(BaNCSUtil.getIDTYPE(custId));
        final Options OPTIONS = new Request.Options(120, TimeUnit.SECONDS, 120, TimeUnit.SECONDS, true);
        SendEB555789Response response = loanResource.getAllHomeCardLoanAccount(request, OPTIONS);
        return response.getLoanAccount();
    }

    private final String PROD_TYPE_SUMMARY = "ABCDEFGHIJ";

    public LaonAcountAmount getTotalLoanAmount(AIBankUser aibankUser) {

        LaonAcountAmount response = new LaonAcountAmount();
        List<LoanAccount> accounts = getAccountInfoLoan(aibankUser, false);
        boolean isEstimate = false;

        BigDecimal totalAmount = new BigDecimal(0);
        for (LoanAccount acct : accounts) {

            if (acct.isSpecialStatusButI02()) {
                // #5176 [貸款總覽] 特殊貸款戶況處理邏輯調整 ，跳過特殊貸款
                continue;
            }

            if (PROD_TYPE_SUMMARY.indexOf(acct.getProductType()) < 0) {
                continue;
            }

            logger.info("換算幣別 : " + acct.getCurCod());
            if (acct.getCurCod().equals("TWD")) {
                acct.setActBalNt(acct.getActBal());
            }
            else {
                isEstimate = true;
                acct.setActBalNt(calNTAmount(acct.getCurCod(), acct.getActBal()));
            }

            totalAmount = totalAmount.add(acct.getActBalNt());
        }

        response.setEstimate(isEstimate);
        response.setTotalAmount(totalAmount);
        return response;
    }

    public BigDecimal calNTAmount(String curCode, BigDecimal amt) {

        List<ExchangeRateHistory> exchangeRateHistory = exchangeRateHistoryCacheManager.getExchangeRateHistoryByPredicate(getExRateHisPredicate(curCode));

        BigDecimal rate = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(exchangeRateHistory)) {
            IBUtils.sort(exchangeRateHistory, new String[] { "txTime" }, new boolean[] { true });
            rate = exchangeRateHistory.get(0).getBuy();
        }
        return amt.multiply(rate);

    }

    private Predicate<ExchangeRateHistory> getExRateHisPredicate(String curCode) {
        return (exRate) -> "0".equals(exRate.getRateFlag()) && "0".equals(exRate.getRateType()) && curCode.equals(exRate.getCurrencyEname1());
    }

    /**
     * 檢核客戶是否留存CIF手機門號
     *
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public void validateMobileNo(AIBankUser aibankUser) throws ActionException {
        if (StringUtils.isBlank(aibankUser.getMobileNo())) {
            logger.info("客戶無留存手機號碼。");
            throw ExceptionUtils.getActionException(MbErrorCode.MB3504);
        }
    }

    /**
     * 檢核客戶是否為個人戶
     *
     * @param aibankUser
     * @throws ActionException
     */
    public void validatePersonal(AIBankUser aibankUser) throws ActionException {
        if (!aibankUser.isIndividualAccount()) {
            logger.info("客戶非個人戶。");
            throw ExceptionUtils.getActionException(MbErrorCode.MB3501);
        }
    }

    /**
     * 檢核客戶是否為 DBU
     *
     * @param aibankUser
     * @throws ActionException
     */
    public void validateDBU(AIBankUser aibankUser) throws ActionException {
        if (!getBuType(aibankUser).isDBU()) {
            logger.info("客戶非DBU");
            throw ExceptionUtils.getActionException(MbErrorCode.MB3500);
        }
    }

    /**
     * 檢查是否死亡戶
     *
     * @param aibankUser
     * @return
     */
    public Boolean checkIsDeceasedAccount(AIBankUser aibankUser) {
        if (aibankUser.getIsDeceasedAccount() == null) {
            EB12020011Res eb12020011Rs = depositResource.sendEB12020011(aibankUser.getCustId(), "11", "1", "1");
            boolean isDeceasedAccount = StringUtils.equals("Y", StringUtils.left(eb12020011Rs.getIdnoSts(), 1));
            aibankUser.setIsDeceasedAccount(isDeceasedAccount);
            return isDeceasedAccount;
        }
        return aibankUser.getIsDeceasedAccount();
    }

    /**
     * 檢核「使用者是否為死亡戶」
     *
     * @param aibankUser
     * @throws ActionException
     */
    public void checkIsDeceased(AIBankUser aibankUser) throws ActionException {
        if (checkIsDeceasedAccount(aibankUser)) {
            throw ExceptionUtils.getActionException(MbErrorCode.MB3511);
        }
    }

    /**
     * 是否「為單一戶名」
     *
     * @param aibankUser
     * @return true:表示為單一戶名
     */
    public Boolean isSingleAccount(AIBankUser aibankUser) {
        if (aibankUser.getIsSingleAccount() == null) {
            Boolean isSingleAccount = accountResource.checkSingleAccount(aibankUser.getCustIdAndCheckDup(), aibankUser.getUserId(), aibankUser.getNameCode());
            aibankUser.setIsSingleAccount(isSingleAccount);
        }
        return aibankUser.getIsSingleAccount();
    }

    /**
     * 是否「不為單一戶名」
     *
     * @param aibankUser
     * @return true:表示不為單一戶名
     */
    public Boolean isNotSingleAccount(AIBankUser aibankUser) {
        return !isSingleAccount(aibankUser);
    }

    /**
     * 檢核是否為單一戶名，若不是單一戶名，顯示錯誤訊息
     * 
     * 卡戶不需檢查
     *
     * @param aibankUser
     * @throws ActionException
     */
    public void checkIsSingleAccount(AIBankUser aibankUser) throws ActionException {
        if (aibankUser.getCustomerType().isCardMember()) {
            return;
        }
        if (isNotSingleAccount(aibankUser)) {
            throw ExceptionUtils.getActionException(MbErrorCode.MB3502);
        }
    }

    /**
     * 使用者基金電子契約註記
     *
     * @param aibankUser
     * @return true:已簽訂，false:未簽訂
     */
    public boolean getSignStatus4FundElectronicContract(AIBankUser aibankUser) {
        if (aibankUser.getFundEContractSigned() == null) {
            aibankUser.setFundEContractSigned(mutualFundResource.checkFundElectronicContractStatus(aibankUser.getCustIdAndCheckDup(), aibankUser.getNameCode()));
        }
        return aibankUser.getFundEContractSigned();
    }

    /**
     * 查詢客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)
     *
     * @param aibankUser
     */
    private void sendEb032675(AIBankUser aibankUser) {
        if (aibankUser.getEb032675Res() == null) {
            aibankUser.setEb032675Res(userResource.sendEB032675(aibankUser.getCustIdAndCheckDup()));
        }
    }

    /**
     * 使用者是否為禁銷戶
     *
     * @param aibankUser
     * @return true:是禁銷戶
     */
    public boolean isBannedFromInvest(AIBankUser aibankUser) {
        sendEb032675(aibankUser);
        return StringUtils.isY(aibankUser.getEb032675Res().getBn01Flg());
    }

    /**
     * 檢核「使用者是否為禁銷戶」
     *
     * @param aibankUser
     * @throws ActionException
     */
    public void checkBannedFromInvest(AIBankUser aibankUser) throws ActionException {
        if (isBannedFromInvest(aibankUser)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.ELDERLY_COGNITION_NOT_PASS);
        }
    }

    /**
     * 使用者是FATCA身分為特定狀態
     *
     * @param aibankUser
     * @return true:是特定狀態
     */
    public boolean isFATCAStatus(AIBankUser aibankUser) throws ActionException {
        sendEb032675(aibankUser);
        String idnFatca = aibankUser.getEb032675Res().getIdnF();
        idnFatca = StringUtils.trimToEmptyEx(StringUtils.defaultString(idnFatca));
        if (StringUtils.isBlank(idnFatca)) {
            return false;
        }
        String fatcaListValue = systemParamCacheManager.getValue("PIB", "FATCAList");
        if (StringUtils.isBlank(fatcaListValue)) {
            logger.error("系統參數檔(AI_SYSTEM_PARAM)未設置參數(FATCAList)");
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }
        return StringUtils.indexOf(fatcaListValue, idnFatca) != -1;
    }

    /**
     * 使用者是FATCA身分為特定狀態
     *
     * @param aibankUser
     * @return true:是特定狀態
     */
    public boolean isUnFatcaFlag(AIBankUser aibankUser) throws ActionException {
        sendEB67115(aibankUser);
        String unfatcaflag = aibankUser.getEb67115Res().getUnfatcaflag();
        return StringUtils.equals(unfatcaflag, "Y");
    }

    /**
     * 檢核「使用者是FATCA身分為特定狀態」&& 客戶不具備FATCA排外身份
     *
     * @param aibankUser
     * @throws ActionException
     */
    public void checkFATCAStatus(AIBankUser aibankUser) throws ActionException {
        if (isFATCAStatus(aibankUser) && !isUnFatcaFlag(aibankUser)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.FATCA_SPECIAL_STS);
        }
    }

    /**
     * 使用者是弱勢身份
     *
     * @param aibankUser
     * @return true:是弱勢身份
     */
    public boolean isUnderprivileged(AIBankUser aibankUser) {
        // #4655 已確認只要是企業戶不需要判斷弱勢身份
        if (aibankUser.getCompanyVo().getCompanyKind() == CompanyKindType.COMPANY.getCode()) {
            return false;
        }
        sendEb032675(aibankUser);
        String under70 = aibankUser.getEb032675Res().getAgeUn70Flag();
        String eduover = aibankUser.getEb032675Res().getEduOvJrFlag();
        String goodHealth = aibankUser.getEb032675Res().getHealthFlag();
        return !StringUtils.isY(under70) || !StringUtils.isY(eduover) || !StringUtils.isY(goodHealth);
    }

    /**
     * 使用者是「高齡」身份 (經Eb032675O判斷)
     *
     * @param aibankUser
     * @return true:是高齡身份
     */
    public boolean isEb032675OldAge(AIBankUser aibankUser) {
        sendEb032675(aibankUser);
        String under70 = aibankUser.getEb032675Res().getAgeUn70Flag();
        return !StringUtils.isY(under70);
    }

    /**
     * 使用者是「國中學歷以下」身份 (經Eb032675O判斷)
     *
     * @param aibankUser
     * @return true:是國中學歷以下身份
     */
    public boolean isEb032675UnderJounior(AIBankUser aibankUser) {
        sendEb032675(aibankUser);
        String eduover = aibankUser.getEb032675Res().getEduOvJrFlag();
        return !StringUtils.isY(eduover);
    }

    /**
     * 使用者是「重大傷病」身份 (經Eb032675O判斷)
     *
     * @param aibankUser
     * @return true:是重大傷病身份
     */
    public boolean isEb032675MajorInjury(AIBankUser aibankUser) {
        sendEb032675(aibankUser);
        String goodHealth = aibankUser.getEb032675Res().getHealthFlag();
        return !StringUtils.isY(goodHealth);
    }

    /**
     * 專業投資人「法人」註記
     *
     * @param aibankUser
     * @return true:是專業投資人法人
     */
    public boolean isProfessionalInvestor(AIBankUser aibankUser) {
        sendEb032675(aibankUser);
        // 20250612 by Tank, 法人專投新增19代碼
        return StringUtils.equalsAny(aibankUser.getEb032675Res().getDesc(), "17", "18", "19", "20", "31", "32");
    }

    // @formatter:off
    /**
     * <pre>
     * 非特定客戶之高資產客戶
     * CM061435CR_Rs.HNWC_FLG = Y &
     * CM061435CR_Rs.HNWC_SERV = Y &
     * CM061435CR_Rs.SP_FLG <> Y
     * 適用：
     * 基金申購
     * 基金贖回
     * 基金轉換
     * 基金變更
     * 買海外ETF/股票
     * 海外債申購
     * </pre>
     * @param aibankUser
     * @return
     */
    // @formatter:on
    public boolean isHighWealthClient(AIBankUser aibankUser) {
        sendCM061435CR(aibankUser);
        return !StringUtils.isY(aibankUser.getCm061435CRRes().getSpFlg()) && StringUtils.isY(aibankUser.getCm061435CRRes().getHnwcFlg()) && StringUtils.isY(aibankUser.getCm061435CRRes().getHnwcServ());
    }

    // @formatter:off
    /**
     * 高資產客戶(註記I)
     * 適用：於海外債申購可申購專投商品，但不可申購高資產商品
     * 
     * @param aibankUser
     * @return
     */
    // @formatter:on
    public boolean isHighWealthClientI(AIBankUser aibankUser) {
        sendCM061435CR(aibankUser);
        return StringUtils.isY(aibankUser.getCm061435CRRes().getHnwcFlg()) && !StringUtils.isY(aibankUser.getCm061435CRRes().getHnwcServ());
    }

    // @formatter:off
    /**
     * 高資產客戶(註記H)
     * 適用：於海外債申購可申購專投商品和高資產商品
     * 
     * @param aibankUser
     * @return
     */
    // @formatter:on
    public boolean isHighWealthClientH(AIBankUser aibankUser) {
        sendCM061435CR(aibankUser);
        return StringUtils.isY(aibankUser.getCm061435CRRes().getHnwcFlg()) && StringUtils.isY(aibankUser.getCm061435CRRes().getHnwcServ());
    }

    /**
     * 客戶辦理投資有價證券資訊提供聲明書維護(EB032280)
     *
     * @param aibankUser
     */
    private void sendEB032280(AIBankUser aibankUser) {
        if (aibankUser.getEb032280Res() == null) {
            aibankUser.setEb032280Res(this.sendEB032280(aibankUser.getCustIdAndCheckDup(), "0", null));
        }
    }

    /**
     * 客戶辦理投資有價證券資訊提供聲明書維護(EB032280)
     *
     * @param custIdAndCheckDup
     * @param func
     * @param func03
     */
    public EB032280Res sendEB032280(String custIdAndCheckDup, String func, String func03) {
        return investResource.sendEB032280(custIdAndCheckDup, func, func03);
    }

    /**
     * 學貸資料 -- 單筆
     *
     * @param studentLoanData
     * @return
     */
    public static StudentLoanData buildStudentLoanData(StudentLoanData studentLoanData) {
        StudentLoanData loanData = new StudentLoanData();
        loanData.setYearTerm(studentLoanData.getYearTerm());
        loanData.setCurCod(studentLoanData.getCurCod());
        loanData.setOriLoanBal(studentLoanData.getOriLoanBal());
        loanData.setActBal(studentLoanData.getActBal());
        loanData.setInsAmt(studentLoanData.getInsAmt());
        return loanData;
    }

    /**
     * 貸款資料 -- 單筆
     *
     * @param loanAccount
     * @return
     */
    public static LoanAccount buildLoanAccount(LoanAccount loanAccount) {
        LoanAccount loanData = new LoanAccount();
        loanData.setAcno(loanAccount.getAcno());
        loanData.setType(loanAccount.getType());
        loanData.setAvalAmt(loanAccount.getAvalAmt());
        loanData.setYearTerm(loanAccount.getYearTerm());
        loanData.setSlipNo(loanAccount.getSlipNo());
        loanData.setCurCod(loanAccount.getCurCod());
        loanData.setActSts(loanAccount.getActSts());
        loanData.setDocNo(loanAccount.getDocNo());
        Calendar dueDate = (null != loanAccount.getDueDate()) ? (java.util.Calendar) loanAccount.getDueDate().clone() : null;
        loanData.setDueDate(dueDate);
        loanData.setRate(loanAccount.getRate());
        loanData.setcLass(loanAccount.getcLass());
        loanData.setActBal(loanAccount.getActBal());
        loanData.setActBalNt(loanAccount.getActBalNt());
        loanData.setGlcdLoan(loanAccount.getGlcdLoan());
        loanData.setOriLoanBal(loanAccount.getOriLoanBal());
        loanData.setLoanTerm(loanAccount.getLoanTerm());
        loanData.setLoanTyp(loanAccount.getLoanTyp());
        loanData.setAcnoSa(loanAccount.getAcnoSa());
        loanData.setAcnoInFlag(loanAccount.getAcnoInFlag());
        loanData.setTdNo(loanAccount.getTdNo());
        loanData.setIntCycle(loanAccount.getIntCycle());
        loanData.setInsAmt(loanAccount.getInsAmt());
        Calendar prnStrDate = (null != loanAccount.getPrnStrDate()) ? (java.util.Calendar) loanAccount.getPrnStrDate().clone() : null;
        loanData.setPrnStrDate(prnStrDate);
        loanData.setPartRecv(loanAccount.getPartRecv());
        loanData.setAchFlag(loanAccount.getAchFlag());
        loanData.setSpecSts(loanAccount.getSpecSts());
        loanData.setFlag302611(loanAccount.getFlag302611());
        loanData.setFlag302602(loanAccount.getFlag302602());
        loanData.setLnTyp(loanAccount.getLnTyp());
        loanData.setaType(loanAccount.getaType());
        loanData.setiCat(loanAccount.getiCat());
        loanData.setStuSpNo(loanAccount.getStuSpNo());
        loanData.setBranchNo(loanAccount.getBranchNo());
        loanData.setDigitalFlg(loanAccount.getDigitalFlg());
        loanData.setExFlg(loanAccount.getExFlg());
        loanData.setAccName(loanAccount.getAccName());
        loanData.setCrossFlg(loanAccount.getCrossFlg());
        loanData.setPwdErrCnt(loanAccount.getPwdErrCnt());
        loanData.setPassbookFlg(loanAccount.getPassbookFlg());
        loanData.setProdInd(loanAccount.getProdInd());
        loanData.setOdTotLimit(loanAccount.getOdTotLimit());
        loanData.setOdAcno(loanAccount.getOdAcno());
        loanData.setWaLnBadDebtInd(loanAccount.getWaLnBadDebtInd());
        loanData.setWaLnBcType(loanAccount.getWaLnBcType());
        loanData.setWaLnBcStag(loanAccount.getWaLnBcStag());
        loanData.setDefaultString18(loanAccount.getDefaultString18());
        loanData.setDefaultString24(loanAccount.getDefaultString24());
        loanData.setOdAvil1(loanAccount.getOdAvil1());
        loanData.setTdAct(loanAccount.getTdAct());
        loanData.setRestLoanTerm(loanAccount.getRestLoanTerm());
        loanData.setArrsCtr(loanAccount.getArrsCtr());
        loanData.setProductType(loanAccount.getProductType());
        loanData.setLoanType(loanAccount.getLoanType());
        loanData.setProductName(loanAccount.getProductName());
        loanData.setLoanName(loanAccount.getLoanName());
        loanData.setIsSpecialStatus(loanAccount.getSpecialStatus());
        loanData.setIsBadDebt(loanAccount.getIsBadDebt());
        loanData.setIsNegotiation(loanAccount.getIsNegotiation());
        loanData.setAccountAlias(loanAccount.getAccountAlias());
        loanData.setIsOverDue(loanAccount.getIsOverDue());
        if (null == loanAccount.getStudentLoan()) {
            loanData.setStudentLoan(null);
        }
        else {
            List<StudentLoanData> copyList = new ArrayList<>();
            for (StudentLoanData studentLoanData : loanAccount.getStudentLoan()) {
                if (null == studentLoanData) {
                    continue;
                }

                StudentLoanData loanItem = buildStudentLoanData(studentLoanData);
                copyList.add(loanItem);
            }

            loanData.setStudentLoan(copyList);

        }
        return loanData;
    }

    /**
     * 貸款資料 -- 多筆
     *
     * @param loanAccountList
     * @return
     */
    public static List<LoanAccount> buildLoanAccountList(List<LoanAccount> loanAccountList) {
        List<LoanAccount> loanDataList = new ArrayList<>();

        if (null == loanAccountList) {
            loanDataList = null;
        }
        else {
            for (LoanAccount loanAccount : loanAccountList) {
                if (null == loanAccount) {
                    continue;
                }

                LoanAccount loanItem = buildLoanAccount(loanAccount);
                loanDataList.add(loanItem);
            }
        }
        return loanDataList;
    }

    /**
     * 高資產客戶資訊查詢(CM061435CR)
     *
     * @param aibankUser
     */
    private void sendCM061435CR(AIBankUser aibankUser) {
        if (aibankUser.getCm061435CRRes() == null) {
            aibankUser.setCm061435CRRes(mutualFundResource.queryHighNetWorthClientData(aibankUser.getCustIdAndCheckDup()));
        }
    }

    /**
     * 取得 特定金錢信託推介同意書 簽署日
     *
     * @param aibankUser
     * @return
     */
    public String getSignDate4SpecifiedMoneyTrustPromotionConsent(AIBankUser aibankUser) {
        sendEB032280(aibankUser);
        return aibankUser.getEb032280Res().getMtnDate();
    }

    /**
     * 取得 海外ETF/股票風險預告書 簽署狀態
     *
     * @param aibankUser
     * @return
     */
    public boolean getSignStatus4OverseasStockRiskNotice(AIBankUser aibankUser) {
        sendEB032280(aibankUser);
        return StringUtils.isY(aibankUser.getEb032280Res().getEtfFlag());
    }

    /**
     * 取得 是否能夠簽署推介同意書 202508_網銀Fubon+信託推介同意書取消年齡限制
     * 
     * @param aibankUser
     */
    public boolean getCanSignStatus4PromotionConsent(AIBankUser aibankUser) {
        sendEB032280(aibankUser);
        EB032280Res eb032280Res = aibankUser.getEb032280Res();
        return StringUtils.isY(eb032280Res.getTypCod05()) && !StringUtils.isY(eb032280Res.getTypCod06());
    }

    // @formatter:off
    /**
     * 特定金錢信託推介同意-檢查是否為弱勢戶
     * 
     * C.  非弱勢客戶判斷：EB032280_RS.TYP_COD_04 = Y and TYP_COD_05 = Y and TYP_COD_06 <> Y
     * D.  弱勢戶判斷：若不符合上述條件，即為弱勢客戶。
     * 
     * @param aibankUser
     * @return
     */
    // @formatter:on
    public boolean checkIsWeakUser(AIBankUser aibankUser) {
        return !getCanSignStatus4PromotionConsent(aibankUser);
    }

    // @formatter:off
    /**
     * 取得 客戶特定金錢信託推介簽署狀態
     * 
     * E.  未簽署判斷：EB032280_RS.TYP_COD_02 <> Y
     * F.  已簽署判斷：EB032280_RS.TYP_COD_02 = Y
     * @param aibankUser
     * @return
     */
    // @formatter:on
    public boolean checkIsSignedTrustPromo(AIBankUser aibankUser) {
        sendEB032280(aibankUser);
        return StringUtils.isY(aibankUser.getEb032280Res().getTypCod02());
    }

    private void sendNKNE01(AIBankUser aibankUser) {
        if (aibankUser.getStockStatus() == null || CollectionUtils.isEmpty(aibankUser.getStockStatus().getRepeats())) {
            aibankUser.setStockStatus(investResource.sendNKNE01(aibankUser.getCustIdAndCheckDup(), aibankUser.getUserId(), aibankUser.getNameCode()));
        }
    }

    /**
     * 客戶是否持有信託戶
     *
     * @param aibankUser
     * @return
     */
    public boolean hasTrustAct(AIBankUser aibankUser) {
        if (aibankUser.getHasTrustAcct() == null) {
            HasTrustAcct act = new HasTrustAcct();
            act.setCustIxd(aibankUser.getCustIdAndCheckDup());
            act.setCurAcctName(aibankUser.getNameCode());
            act.setTrustAcct("B");
            aibankUser.setHasTrustAcct(userResource.hasTrustAcct(act));
        }
        return aibankUser.getHasTrustAcct();
    }

    /**
     * 取得股票客戶 W-8Ben 簽署狀態
     *
     * @param aibankUser
     * @return
     */
    public boolean getSignStatus4W8Ben(AIBankUser aibankUser) {
        sendNKNE01(aibankUser);
        if (CollectionUtils.isEmpty(aibankUser.getStockStatus().getRepeats())) {
            return false;
        }
        NKNE01ResRep nkne01ResRep = aibankUser.getStockStatus().getRepeats().get(0);
        return StringUtils.isY(nkne01ResRep.getW8Ben());
    }

    /**
     * 取得股票客戶 W-8Ben 簽署迄日
     *
     * @param aibankUser
     * @return
     */
    public String getW8BenSignEndDate(AIBankUser aibankUser) {
        sendNKNE01(aibankUser);
        if (CollectionUtils.isEmpty(aibankUser.getStockStatus().getRepeats())) {
            return StringUtils.EMPTY;
        }
        NKNE01ResRep nkne01ResRep = aibankUser.getStockStatus().getRepeats().get(0);
        return nkne01ResRep.getSignEndDate();
    }

    /**
     * 取得股票客戶 風險預告書 簽署狀態
     *
     * @param aibankUser
     * @return
     */
    public boolean getSignStatus4RiskWarningLetter(AIBankUser aibankUser) {
        sendNKNE01(aibankUser);
        if (CollectionUtils.isEmpty(aibankUser.getStockStatus().getRepeats())) {
            return false;
        }
        NKNE01ResRep nkne01ResRep = aibankUser.getStockStatus().getRepeats().get(0);
        return StringUtils.isY(nkne01ResRep.getRam());
    }

    /**
     * KYC檢核
     *
     * @param aibankUser
     * @throws ActionException
     */
    public void validateKYC(AIBankUser aibankUser) throws ActionException {

        String ebillCheck = aibankUser.getLoginMsgRs().getEbillCheck();

        Calendar ebillEndDate = aibankUser.getLoginMsgRs().getEbillEndDate();

        // 未做過KYC
        if (StringUtils.isBlank(ebillCheck) || StringUtils.equals(ebillCheck, "0")) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.KYC_NOT_DONE);
        }
        else {
            if (ebillEndDate == null) {
                // 需要重新測試KYC
                if (!StringUtils.equalsAny(ebillCheck, "C1", "C2", "C3", "C4")) {
                    throw ExceptionUtils.getActionException(AIBankErrorCode.KYC_RETEST);
                }
            }
            else {
                // KYC已經過期
                if (ConvertUtils.calendar2Date(ebillEndDate).compareTo(new Date()) < 0) {
                    throw ExceptionUtils.getActionException(AIBankErrorCode.KYC_EXPIRED);
                }
            }
        }
    }

    /**
     * 讀取基金為庫存之基金總覽資訊
     * 
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public List<FundOverview> getFundOverviewList(AIBankUser aibankUser) throws ActionException {
        return getFundOverviewList(aibankUser, "2");
    }

    /**
     * 讀取基金總覽資訊，TYPE=1，取全部
     * 
     * @param aibankUser
     * @param type
     *            1:ALl 2:庫存 3: 4:
     * @return
     * @throws ActionException
     */
    public List<FundOverview> getFundOverviewList(AIBankUser aibankUser, String type) throws ActionException {
        List<FundOverview> result = new ArrayList<>();

        CompanyBUType buType = getBuType(aibankUser);
        switch (buType) {
        case DBU:
            NFEE072Req nfee072Req = new NFEE072Req();
            nfee072Req.setHFMTID("HFMTID");
            nfee072Req.setHTLID("2004111");
            nfee072Req.setHDRVQ1("NFMOBHQ");
            nfee072Req.setCustId(aibankUser.getCustIdAndCheckDup());
            nfee072Req.setCurAcctName(aibankUser.getNameCode());
            nfee072Req.setType(type);
            result = mutualFundResource.queryNFEE072(nfee072Req);
            break;
        case OBU:
            NFEE071Req nfee071Req = new NFEE071Req();
            nfee071Req.setHFMTID("HFMTID");
            nfee071Req.setHTLID("2004111");
            nfee071Req.setHDRVQ1("NFMOBHQ");
            nfee071Req.setCustId(aibankUser.getCustIdAndCheckDup());
            nfee071Req.setCurAcctName(aibankUser.getNameCode());
            nfee071Req.setType(type);
            result = mutualFundResource.queryNFEE071(nfee071Req);
            break;
        default:
            logger.error("無法辨識客戶身份別 DBU/OBU。");
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        return result;
    }

    /**
     * 查詢海外債券共同營業日
     * 
     * @param calendarDate
     * @return
     * @throws ActionException
     */
    public List<Date> getBondComBusinessDays() throws ActionException {
        return investResource.getValidBusinessComDays();
    }

    /**
     * 是否為海外債券營業日
     * 
     * @param calendarDate
     * @return
     * @throws ActionException
     */
    public Boolean isValidBondBusinessDay() throws ActionException {
        Boolean isBizDay = investResource.isValidBondBusinessDay();

        // 若查無營業日資料，很抱歉，目前系統暫停受理電子交易，如有疑問，請洽客服02-8751-6665。
        if (isBizDay == null) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.BOND_SUSPEND_ELEC);
        }

        return isBizDay;
    }

    /**
     * 查詢海外債券營業日
     * 
     * @param calendarDate
     * @return
     * @throws ActionException
     */
    public Date getBondBusinessDay() throws ActionException {
        Date biz = investResource.getBondBusinessDay();

        // 若查無營業日資料，很抱歉，目前系統暫停受理電子交易，如有疑問，請洽客服02-8751-6665。
        if (biz == null) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.BOND_SUSPEND_ELEC);
        }

        return biz;
    }

    /**
     * 90天內有撥貸紀錄
     *
     * @param aibankUser
     * @return
     */
    public boolean receivedLoanIn90Days(AIBankUser aibankUser) {
        sendEB062171(aibankUser);
        if (CollectionUtils.isNotEmpty(aibankUser.getEb062171ResReps())) {
            for (var rep : aibankUser.getEb062171ResReps()) {
                if ("090".equals(rep.getCycleNumber()) && StringUtils.isY(rep.getFlagNumber())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 撥貸紀錄查詢(EB062171)
     *
     * @param aibankUser
     */
    private void sendEB062171(AIBankUser aibankUser) {
        if (aibankUser.getEb062171ResReps() == null) {
            try {
                aibankUser.setEb062171ResReps(mutualFundResource.queryLoanAllocate(aibankUser.getCustId()));
            }
            catch (ServiceException e) {
                // 有錯時「電文回覆失敗或 Timeout，視為無此註記」
                logger.error("撥貸紀錄查詢(EB062171) error, ", e);
            }
        }
    }

    /**
     * 是否滿足高齡認知檢核條件(年齡大於65歲)
     *
     * @return true:表示年齡大於65歲
     */
    public boolean isOldAge(AIBankUser aibankUser) {
        Date birthday = aibankUser.getBirthDay();
        if (birthday == null) {
            return false;
        }
        Date yearsLater65 = DateUtils.addYears(birthday, 65);
        Date sysDate = DateUtils.getStartDate(new Date());
        return yearsLater65.compareTo(sysDate) < 0;
    }

    /**
     * 是否滿足高齡認知檢核條件年齡大於AI_SYSTEM_PARAM (PARAM_KEY = TRANSFER_SERVICE_AGE_LIMIT))
     *
     * @return true:表示年齡大於AI_SYSTEM_PARAM (PARAM_KEY = TRANSFER_SERVICE_AGE_LIMIT)
     */
    public boolean isOldAgeBySystemParam(AIBankUser aibankUser) {
        Date birthday = aibankUser.getBirthDay();
        if (birthday == null) {
            return false;
        }

        String age = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "TRANSFER_SERVICE_AGE_LIMIT");
        Date yearsLater = DateUtils.addYears(birthday, ConvertUtils.str2Int(age, 0));
        Date sysDate = DateUtils.getStartDate(new Date());
        return yearsLater.compareTo(sysDate) < 0;
    }

    // @formatter:off
    /**
     * 取得分行代碼(3碼)
     * 若 TransOutAccount.displayBranchId 有值，取後3碼
     * 若無值，則發查電文 NF032333 取值
     * 
     * @param account
     * @return
     * @throws ActionException
     */
    // @formatter:on
    public String getBranchCode(TransOutAccount account) throws ActionException {
        if (account != null) {
            if (StringUtils.isNotBlank(account.getDisplayBranchId())) {
                return StringUtils.right(account.getDisplayBranchId(), 3);
            }
            else {
                String branchNo = informationResource.getBranchNo(account.getAccountId());
                if (StringUtils.isNotBlank(branchNo)) {
                    return StringUtils.right(branchNo, 3);
                }
            }
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.BRANCH_NO_ERROR);
    }

    /**
     * (已登入)讀取信託轉入帳號
     *
     * TWD:台幣 XXX:外幣
     *
     * @param aibankUser
     * @param locale
     * @return
     */
    public Map<String, List<TrustAccount>> getTrustAccts(AIBankUser aibankUser, Locale locale) {
        // 取出暫存資料
        Map<String, Map<String, List<TrustAccount>>> trustAcctsMap = aibankUser.getTrustAcctsMap();
        Map<String, List<TrustAccount>> trustAcctsMapByLocale = trustAcctsMap.getOrDefault(locale.toString(), new HashMap<>());
        // 判斷是否要重新發送電文
        if (trustAcctsMapByLocale.isEmpty()) {
            EB5556911Req request = new EB5556911Req();
            request.setIdno(aibankUser.getCustId());
            request.setNameCod(aibankUser.getNameCode());
            request.setUserId(aibankUser.getUserId());
            request.setUidDup(aibankUser.getUidDup());
            request.setAcnoOut("000000000000");
            request.setCkType("01");
            // 發查電文，取得帳號清單
            TrustAcctInRes response = accountsResource.getTrustAcctIn(request);

            // 讀取客戶全部暱稱清單
            List<AccountAlias> accountAliasList = accountResource.getAccountAliasList(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind());

            if (response != null && CollectionUtils.isNotEmpty(response.getRepeats())) {
                trustAcctsMapByLocale = IntStream.range(0, response.getRepeats().size()).mapToObj(index -> {
                    TrustAcctInResRep repeat = response.getRepeats().get(index);
                    return this.convert2TrustAccount(repeat, index, accountAliasList, locale);
                }).collect(Collectors.groupingBy(trustAccount -> StringUtils.equals(CurrencyCode.TWD, trustAccount.getCurr()) ? CurrencyCode.TWD : AIBankConstants.MAIN_ACCOUNT_CUR_CODE));

                this.filterSameAcno(trustAcctsMapByLocale, CurrencyCode.TWD);
                this.filterSameAcno(trustAcctsMapByLocale, AIBankConstants.MAIN_ACCOUNT_CUR_CODE);
                trustAcctsMap.put(locale.toString(), trustAcctsMapByLocale);
                aibankUser.setTrustAcctsMap(trustAcctsMap);
            }
        }
        return trustAcctsMapByLocale;
    }

    private void filterSameAcno(Map<String, List<TrustAccount>> trustAcctsMap, String currency) {
        List<TrustAccount> list = trustAcctsMap.get(currency);
        if (CollectionUtils.isNotEmpty(list)) {
            // 濾掉相同acno
            list = new ArrayList<>(list.stream().collect(Collectors.toMap(TrustAccount::getAcno, Function.identity(), (a, b) -> a)).values());
            trustAcctsMap.put(currency, list);
        }
    }

    /**
     * 信託帳號轉換
     *
     * @return
     */
    private TrustAccount convert2TrustAccount(TrustAcctInResRep repeat, int index, List<AccountAlias> accountAliasList, Locale locale) {
        TrustAccount trustAcct = new TrustAccount();
        trustAcct.setIndex(index);
        trustAcct.setBankNo(repeat.getBankNo());
        trustAcct.setAcno(repeat.getAcno());
        trustAcct.setCustName(repeat.getCustName());
        trustAcct.setAcnoType(repeat.getAcnoType());
        trustAcct.setAcnoFlg(repeat.getAcnoFlg());
        trustAcct.setType(repeat.getType());
        trustAcct.setDigitType(repeat.getDigitType());
        trustAcct.setFsFlg(repeat.getFsFlg());
        trustAcct.setProdCode(repeat.getProdCode());
        trustAcct.setBranchNo(repeat.getBranchNo());
        trustAcct.setCurr(repeat.getCurr());
        BankData bankData = bankCacheManager.getBankData(repeat.getBankNo(), locale);
        // 設置銀行名稱
        if (bankData == null) {
            trustAcct.setBankName(repeat.getBankNo());
        }
        else {
            trustAcct.setBankName(bankData.getBankName());
        }
        // 設置分行名稱
        BranchData branchData = branchCacheManager.getBranch(trustAcct.getBranchNo(), locale);
        if (branchData != null) {
            trustAcct.setBranchName(branchData.getBranchName());
        }
        else {
            trustAcct.setBranchName(trustAcct.getBranchNo());
            logger.warn("讀取分行資訊失敗，分行代碼：{}", trustAcct.getBranchNo());
        }
        trustAcct.setCurName(currencyCodeCacheManager.getCurrencyName(repeat.getCurr(), locale));
        // 設置帳號暱稱
        buildAcctNickName(trustAcct, accountAliasList);
        return trustAcct;
    }

    /**
     * 取得暱稱
     *
     * @param trustAcct
     * @param accountAliasList
     */
    private void buildAcctNickName(TrustAccount trustAcct, List<AccountAlias> accountAliasList) {
        if (CollectionUtils.isNotEmpty(accountAliasList)) {
            // 以帳號和幣別讀取暱稱
            AccountAlias accountAlias = accountAliasList.stream().filter(a -> StringUtils.equals(a.getAccountNo(), StringUtils.leftPadZero(trustAcct.getAcno(), 16)) && StringUtils.equals(a.getCurrencyEname(), trustAcct.getCurr())).findFirst().orElse(null);
            if (accountAlias != null) {
                trustAcct.setAcctNickName(accountAlias.getAccountAlias());
            }
        }
    }

    /**
     * 是否為法人專投 20250613 by Tank, 此為多餘的拿掉，上面有isProfessionalInvestor
     * 
     * public boolean isLegalPerson(AIBankUser aibankUser) { sendEb032675(aibankUser); return StringUtils.equalsAny(aibankUser.getEb032675Res().getDesc(), "17", "18", "31", "32"); }
     */

    /**
     * 專業投資人註記(個人, 非法人)
     *
     * <pre>
     *  是否有專業投資人註記
     *   EB5556982取得INVEST_COD，EB5556982_Rs.INVEST_COD = Y則註記為專業投資人，若否則註記為非專業投資人
     * </pre>
     */
    public boolean isMarkedProfessionalInvestor(AIBankUser aibankUser) {
        sendEB5556982ToCheck(aibankUser);
        return Objects.nonNull(aibankUser.getProfessionalInvestor()) ? aibankUser.getProfessionalInvestor() : false;
    }

    private void sendEB5556982ToCheck(AIBankUser aiBankUser) {
        if (Objects.isNull(aiBankUser.getProfessionalInvestor())) {
            aiBankUser.setProfessionalInvestor(userResource.isProfessionalInvestor(aiBankUser.getCustIdAndCheckDup(), aiBankUser.getUserId(), aiBankUser.getNameCode()));
        }
    }

    public void sendEB032282(AIBankUser aiBankUser) {
        if (Objects.isNull(aiBankUser.getEb032282Res())) {
            aiBankUser.setEb032282Res(investResource.sendEB032282(aiBankUser.getCustId(), "0", StringUtils.EMPTY));
        }
    }

    public void sendEB032675(AIBankUser aiBankUser) {
        if (Objects.isNull(aiBankUser.getEb032675Res())) {
            aiBankUser.setEb032675Res(userResource.sendEB032675(aiBankUser.getCustId()));
        }
    }

    public void sendEB67115(AIBankUser aiBankUser) {
        if (Objects.isNull(aiBankUser.getEb67115Res())) {
            aiBankUser.setEb67115Res(userResource.sendEB67115(aiBankUser.getCustId()));
        }
    }

    public void sendEB032675Modify(AIBankUser aiBankUser, String busAddr1) {
        aiBankUser.setEb032675Res(userResource.sendEB032675Modify(aiBankUser.getCustId(), busAddr1));
    }

    public void getKycAnswerPartyI(AIBankUser aiBankUser) {
        if (Objects.isNull(aiBankUser.getKycAnswerResponse())) {
            aiBankUser.setKycAnswerResponse(preferencesResource.sendEB12020006KYC(aiBankUser.getCompanyVo().getCompanyUid(), aiBankUser.getNameCode(), "06", "01"));
        }
    }

    public void updateKycAnswerPartyI(AIBankUser aiBankUser, UpdateEB12020006 eb12020006) {
        aiBankUser.setKycAnswerResponse(preferencesResource.updateEB12020006KYC(eb12020006));
    }

    /**
     * 取得投資風險偏好(KYC)前次填答問卷答案-第二部分
     * 
     * @param aibankUser
     * @param locale
     * @param idType
     */
    public void getKycAnswerPartyII(AIBankUser aibankUser, Locale locale, String idType) {
        if (Objects.isNull(aibankUser.getPeopleSoftResList(locale))) {
            List<PeopleSoftRes> peopleSoftResList = investResource.getPeopleSoftList(aibankUser.getCompanyVo().getCompanyUid(), locale.toString(), idType);
            aibankUser.setPeopleSoftResList(locale, peopleSoftResList);
        }
    }

    /**
     * 取得金錢信託契約清單
     * 
     * @param aiBankUser
     * @return
     * @throws ActionException
     */
    public List<TrustContract> sendNMWEB01(AIBankUser aiBankUser) throws ActionException {
        if (aiBankUser.getMoneyTrustContractList() == null) {
            List<TrustContract> contractList = depositResource.sendNMWEB01(aiBankUser.getCustId());
            if (CollectionUtils.isEmpty(contractList)) {
                throw new ActionException(AIBankErrorCode.NO_EFFECTIVE_CONTRACT);
            }
            aiBankUser.setMoneyTrustContractList(contractList);
        }
        return aiBankUser.getMoneyTrustContractList();
    }

    /**
     * 取得金錢信託活存餘額資料
     * 
     * @param aiBankUser
     * @param acctIds
     * @param acctType
     *            [10(台幣) | 40 (外幣)]
     * @param curCode
     * @return
     * @throws ActionException
     */
    public List<EB202674D003Res> getMoneyTrustDepositBalances(AIBankUser aiBankUser, String contractNo, List<String> acctIds, String acctType, String curCode) throws ActionException {
        if (null == aiBankUser.getMoneyTrustDeopsitListMap().get(contractNo)) {
            List<EB202674D003Res> eb202674D003ResList = new ArrayList<>();
            for (var accountId : acctIds) {
                EB202674D003Res eb202674D003Res = accountResource.getSavingAcctBalance(accountId, acctType, curCode);
                eb202674D003ResList.add(eb202674D003Res);
            }
            aiBankUser.getMoneyTrustDeopsitListMap().put(contractNo, eb202674D003ResList);
        }
        return aiBankUser.getMoneyTrustDeopsitListMap().get(contractNo);
    }

    /**
     * 金錢信託-金錢信託定存單臺幣現值查詢(NMWEB03)
     * 
     * @param aiBankUser
     * @param contractNo
     * @return
     * @throws ActionException
     */
    public BigDecimal getMoneyTrustTermDepositBalances(AIBankUser aiBankUser, String contractNo) {
        if (null == aiBankUser.getMoneyTrustTermDeopsitBalanceMap().get(contractNo)) {
            BigDecimal bal = depositResource.sendNMWEB03(contractNo);
            aiBankUser.getMoneyTrustTermDeopsitBalanceMap().put(contractNo, NumberUtils.defaultValue(bal, BigDecimal.ZERO));
        }
        return aiBankUser.getMoneyTrustTermDeopsitBalanceMap().get(contractNo);
    }

    /**
     * 金錢信託-金錢信託基金現值查詢(NFEE074)
     * 
     * @param aiBankUser
     * @param acctIds
     * @return
     * @throws ActionException
     */
    public BigDecimal getMoneyTrustFundBalance(AIBankUser aiBankUser, String contractNo, List<String> acctIds) {
        if (null == aiBankUser.getMoneyTrustFundBalanceMap().get(contractNo)) {
            BigDecimal totalAmt1Sum = BigDecimal.ZERO;

            for (var acct : acctIds) {
                NFEE074Req req = new NFEE074Req();
                req.setAcctId16("");
                req.setCurAcctId("");
                req.setCustPswd32("");
                req.setCurAcctId2(acct);
                req.setCurAcctName(aiBankUser.getNameCode());
                req.setCustId(aiBankUser.getCustId());
                req.setCustId2(NFEE074Req.FIXED_CUSTID_AS_TRUST_ACCT);

                BigDecimal totalAmt1 = BigDecimal.ZERO;

                try {
                    totalAmt1 = depositResource.sendNFEE074(req);
                    totalAmt1 = NumberUtils.defaultValue(totalAmt1, new BigDecimal("0"));
                }
                catch (ServiceException e) {
                    logger.error("=depositResource.sendNFEE074 error=", e);
                }
                totalAmt1Sum = totalAmt1Sum.add(totalAmt1);
            }
            aiBankUser.getMoneyTrustFundBalanceMap().put(contractNo, totalAmt1Sum);
        }
        return aiBankUser.getMoneyTrustFundBalanceMap().get(contractNo);
    }

    /**
     * 海外ETF/股票 資料
     * 
     * @param aiBankUser
     * @param contractNo
     * @return
     */
    public BigDecimal getMoneyTrustOverseaETFBalances(AIBankUser aiBankUser, String contractNo) {
        if (null == aiBankUser.getMoneyTrustOverseaETFBalanceMap().get(contractNo)) {
            BigDecimal bal = depositResource.doNMWEB05(contractNo);

            aiBankUser.getMoneyTrustOverseaETFBalanceMap().put(contractNo, NumberUtils.defaultValue(bal, BigDecimal.ZERO));
        }
        return aiBankUser.getMoneyTrustOverseaETFBalanceMap().get(contractNo);
    }

    /**
     * 發送NJWEEN02，取海外債+結構型商品總額
     * 
     * @param aiBankUser
     * @param acctIds
     * @return
     */
    public List<NJWEEN02ResRep> getMoneyTrustOverseaBondAndStructProd(AIBankUser aiBankUser, String contractNo, List<String> acctIds) {
        if (null == aiBankUser.getNjween02RepListMap().get(contractNo)) {
            // 發查 NJWEEN02
            List<NJWEEN02ResRep> njween02RepList = new ArrayList<>();
            acctIds.stream().forEach(acct -> {
                NJWEEN02Req req = new NJWEEN02Req();
                req.setAcctId16(acct);
                req.setCustId(NJWEEN02Req.FIXED_CUSTID_AS_TRUST_ACCT);
                req.setCurAcctId(aiBankUser.getUserId());
                req.setCurAcctName(aiBankUser.getNameCode());
                req.setCustId2(aiBankUser.getCustId());
                req.setCustPswd32("");

                NJWEEN02Res njween02Res = depositResource.sendNJWEEN02(req);
                if (null != njween02Res) {
                    njween02RepList.addAll(njween02Res.getRepeate());
                }
            });
            aiBankUser.getNjween02RepListMap().put(contractNo, njween02RepList);
        }
        return aiBankUser.getNjween02RepListMap().get(contractNo);
    }

    /**
     *
     * @param aiBankUser
     * @param mainAccts
     * @return
     */
    public List<SDACTQ12ResRep> getMoneyTrustSIProdData(AIBankUser aiBankUser, String contractNo, List<String> mainAccts) {
        if (null == aiBankUser.getSdactq12ResRepsListMap().get(contractNo)) {
            // 發查 SDACTQ12
            List<SDACTQ12ResRep> sdactq12ResRepList = new ArrayList<>();
            mainAccts.stream().forEach(acct -> {
                // 建立組合式商品請求
                SDACTQ12Req sdactq12Req = new SDACTQ12Req();
                sdactq12Req.setAcctId14(acct);
                // 取得組合式商品清單
                SDACTQ12ResRep sdactq12ResRep = depositResource.sendSDACTQ12(sdactq12Req);
                if (null != sdactq12ResRep) {
                    sdactq12ResRepList.add(sdactq12ResRep);
                }
            });
            aiBankUser.getSdactq12ResRepsListMap().put(contractNo, sdactq12ResRepList);
        }
        return aiBankUser.getSdactq12ResRepsListMap().get(contractNo);
    }

    public InvestmentNoticeSetting getInvestmentNoticeSetting(AIBankUser aiBankUser) {
        if (Objects.isNull(aiBankUser.getInvestmentNoticeSetting())) {

            String custId = aiBankUser.getCustId();
            String uidDup = aiBankUser.getUidDup();
            String userId = aiBankUser.getUserId();
            Integer companyKind = aiBankUser.getCompanyKindType().getCode();

            aiBankUser.setInvestmentNoticeSetting(informationResource.findInvestmentNoticeSetting(custId, uidDup, userId, companyKind));
        }
        return aiBankUser.getInvestmentNoticeSetting();
    }

    /**
     * 取得登入者的財管會員等級
     *
     * @param aiBankUser
     * @return
     */
    public FinancialMgmMemberLevel getFinancialMgmMemberLevel(AIBankUser aiBankUser) {

        if (null == aiBankUser.getFinancialMgmMemberLevel()) {
            aiBankUser.setFinancialMgmMemberLevel(informationResource.getFinancialMgmMemberLevel(aiBankUser.getCustId()));
        }
        return aiBankUser.getFinancialMgmMemberLevel();
    }

    /**
     * 查詢任務牆，滿足當前任務的記錄
     * 
     * @param aiBankUser
     * @param missionLevel
     * @param missionCode
     * @return
     */
    public List<MissionDetail> queryMissionDetails(AIBankUser aiBankUser, String missionLevel, String missionCode) {
        String custId = aiBankUser.getCustId();
        String userId = aiBankUser.getUserId();
        String uidDup = aiBankUser.getUidDup();
        Integer companyKind = aiBankUser.getCompanyKind();
        return preferencesResource.queryMissionDetailsByCondition(custId, userId, uidDup, companyKind, missionLevel, missionCode);
    }

    /**
     * 更新任務牆活動明細檔(MISSION_DETAIL)
     * 
     * @param missionDetail
     */
    public void saveMissionDetail(MissionDetail missionDetail) {
        preferencesResource.updateMissionDetail(missionDetail);
    }

    /**
     * 查詢任務牆，該次完成任務是否則好為關卡完成任務
     * 
     * @param aiBankUser
     * @param missionLevel
     * @return
     */
    public Integer getCountByCondition(AIBankUser aiBankUser, String missionLevel) {
        String custId = aiBankUser.getCustId();
        String userId = aiBankUser.getUserId();
        String uidDup = aiBankUser.getUidDup();
        Integer companyKind = aiBankUser.getCompanyKind();
        return preferencesResource.getCountByCondition(custId, userId, uidDup, companyKind, missionLevel);
    }

    /**
     * 查詢任務牆活動主檔
     * 
     * @param aiBankUser
     * @return
     */
    public MissionMaster queryMissionMaster(AIBankUser aiBankUser) {
        String custId = aiBankUser.getCustId();
        String userId = aiBankUser.getUserId();
        String uidDup = aiBankUser.getUidDup();
        Integer companyKind = aiBankUser.getCompanyKind();
        return preferencesResource.queryMissionMaster(custId, userId, uidDup, companyKind);
    }

    /**
     * 更新任務牆活動主檔(MISSION_MASTER)
     * 
     * @param missionMaster
     */
    public void saveMissionMaster(MissionMaster missionMaster) {
        preferencesResource.updateMissionMaster(missionMaster);
    }

}
