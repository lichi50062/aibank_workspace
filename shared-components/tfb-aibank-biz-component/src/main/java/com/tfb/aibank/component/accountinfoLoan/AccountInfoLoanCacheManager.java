package com.tfb.aibank.component.accountinfoLoan;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

//@formatter:off
/**
 * @(#)AccountInfoLoanCacheManager.java  
 * 
 * <p>Description:貸款帳號資料檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/14, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Component
public class AccountInfoLoanCacheManager extends AbstractCacheManager {

    @Autowired
    private AccountInfoLoanResource resource;

    /** Map<locale, Map<prodCode + prodSubCode, AccountInfo>> */
    private Map<String, AccountInfoLoan> accountInfo = new HashMap<>();

    private Map<String, Map<String, AccountInfoLoanNameVo>> accountInfoName = new HashMap<>();

    public AccountInfoLoan getAccountInfoLoan(String aType, String lnTyp, String stuSpNo) {

        aType = StringUtils.left(aType, 4);
        return getAccountInfoLoan(aType, lnTyp, stuSpNo, Locale.TAIWAN.toString());
    }

    public AccountInfoLoan getAccountInfoLoan(String aType, String lnTyp, String stuSpNo, String locale) {

        Map<String, AccountInfoLoan> accounts = accountInfo.entrySet().stream().filter(a -> StringUtils.equals(a.getValue().getAtype(), aType)).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        // Match aType & lnTyp & stuSpNo
        for (var mapItem : accounts.entrySet()) {
            AccountInfoLoan acct = mapItem.getValue();
            if (StringUtils.equals(acct.getAtype(), aType) && StringUtils.equals(acct.getLnTyp(), lnTyp) && StringUtils.equals(acct.getStuSpNo(), stuSpNo)) {
                return acct;
            }
        }

        // Match aType & lnTyp
        for (var mapItem : accounts.entrySet()) {
            AccountInfoLoan acct = mapItem.getValue();
            if (StringUtils.equals(acct.getAtype(), aType) && StringUtils.equals(acct.getLnTyp(), lnTyp)) {
                return acct;
            }
        }

        // Match aType & stuSpNo
        for (var mapItem : accounts.entrySet()) {
            AccountInfoLoan acct = mapItem.getValue();
            if (StringUtils.equals(acct.getAtype(), aType) && StringUtils.equals(acct.getStuSpNo(), stuSpNo)) {
                return acct;
            }
        }

        // Match aType
        for (var mapItem : accounts.entrySet()) {
            AccountInfoLoan acct = mapItem.getValue();
            if (StringUtils.isNotBlank(acct.getLnTyp()) || StringUtils.isNotBlank(acct.getStuSpNo()))
                continue;

            if (StringUtils.equals(acct.getAtype(), aType)) {
                return acct;
            }
        }

        return null;
    }

    /**
     * 取得 ProductType Name
     * 
     * @param accountInfoLoan
     * @param locale
     * @return
     */
    public String getProdTypeName(AccountInfoLoan accountInfoLoan) {
        return getProdTypeName(accountInfoLoan, Locale.TAIWAN.toString());
    }

    public String getProdTypeName(AccountInfoLoan accountInfoLoan, String locale) {
        return Optional.ofNullable(accountInfoName.get(locale).get(accountInfoLoan.getProductType())).map(AccountInfoLoanNameVo::getDisplayText).orElse("");
    }

    /**
     * 取得 LoanType Name
     * 
     * @param accountInfoLoan
     * @param locale
     * @return
     */
    public String getLoanTypeName(AccountInfoLoan accountInfoLoan) {
        return getLoanTypeName(accountInfoLoan, Locale.TAIWAN.toString());
    }

    public String getLoanTypeName(AccountInfoLoan accountInfoLoan, String locale) {
        return Optional.ofNullable(accountInfoName.get(locale).get(accountInfoLoan.getLoanType())).map(AccountInfoLoanNameVo::getDisplayText).orElse("");
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.ACCOUNT_INFO_LOAN_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        GetAccountInfoLoanResponse response = resource.getAccountInfoLoan();

        Map<String, AccountInfoLoan> tmpAccountInfo = new HashMap<>();

        Map<String, Map<String, AccountInfoLoanNameVo>> tmpAccountInfoName = new HashMap<>();

        if (response.getAccountInfoLoan() != null) {
            response.getAccountInfoLoan().forEach(data -> {

                String key = getKey(data.getAtype(), data.getLnTyp(), data.getStuSpNo());
                tmpAccountInfo.put(key, getAccountInfoLoan(data));
            });
        }
        if (response.getAccountInfoLoanName() != null) {
            response.getAccountInfoLoanName().forEach(data -> {

                String locale = data.getLocale();
                String key = data.getItemValue();

                if (tmpAccountInfoName.get(locale) == null) {
                    tmpAccountInfoName.put(locale, new HashMap<>());
                }
                tmpAccountInfoName.get(locale).put(key, data);
            });
        }

        this.accountInfo = tmpAccountInfo;
        this.accountInfoName = tmpAccountInfoName;

    }

    private AccountInfoLoan getAccountInfoLoan(AccountInfoLoanVo entity) {
        AccountInfoLoan model = new AccountInfoLoan();
        model.setAccountInfoLoanKey(entity.getAccountInfoLoanKey());
        model.setAtype(entity.getAtype());
        model.setLnTyp(entity.getLnTyp());
        model.setStuSpNo(entity.getStuSpNo());
        model.setDescription(entity.getDescription());
        model.setDescriptionSub(entity.getDescriptionSub());
        model.setProductType(entity.getProductType());
        model.setLoanType(entity.getLoanType());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());

        return model;
    }

    private String getKey(String aType, String lnTyp, String stuSpNo) {
        String lnType = StringUtils.isNoneBlank(lnTyp) ? lnTyp : "X";
        String stuSpNos = StringUtils.isNoneBlank(stuSpNo) ? stuSpNo : "X";

        return new StringBuilder(0).append(aType).append("_").append(lnType).append("_").append(stuSpNos).toString();
    }

    @Override
    protected boolean isFirstLoad() {
        return this.accountInfo.isEmpty();
    }

}