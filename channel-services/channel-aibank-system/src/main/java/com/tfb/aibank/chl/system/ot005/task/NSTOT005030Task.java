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
package com.tfb.aibank.chl.system.ot005.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.accountselect.FxAccountSelectorService;
import com.tfb.aibank.chl.component.accountselect.TwAccountSelectorService;
import com.tfb.aibank.chl.component.accountselect.model.FxAccountSelector;
import com.tfb.aibank.chl.component.accountselect.model.FxPayeeRecord;
import com.tfb.aibank.chl.component.accountselect.model.PayeeRecord;
import com.tfb.aibank.chl.component.accountselect.model.TwAccountSelector;
import com.tfb.aibank.chl.component.accountselect.model.TwPayeeRecord;
import com.tfb.aibank.chl.system.ot005.model.NSTOT005030Rq;
import com.tfb.aibank.chl.system.ot005.model.NSTOT005030Rs;
import com.tfb.aibank.common.type.TransOutAcctType;

//@formatter:off
/**
* @(#)NSTOT005020Task.java
* 
* <p>Description:『最近轉帳/常用/約定』帳號選擇元件 (轉出帳號對應期臺外幣轉入帳號) - 用轉出取得對應轉入 台外幣 常用專用</p>
* <p>取得臺外幣轉入帳號</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/30, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NSTOT005030Task extends AbstractAIBankBaseTask<NSTOT005030Rq, NSTOT005030Rs> {
    @Autowired
    private TwAccountSelectorService twAccountSelectorService;
    @Autowired
    private FxAccountSelectorService fxAccountSelectorService;

    @Override
    public void validate(NSTOT005030Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT005030Rq rqData, NSTOT005030Rs rsData) throws ActionException {

        TwAccountSelector twAccountSelector = getCache(TwAccountSelectorService.FAVORITE_CACHE_KEY, TwAccountSelector.class);
        if (twAccountSelector == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        FxAccountSelector fxAccountSelector = getCache(FxAccountSelectorService.FAVORITE_CACHE_KEY, FxAccountSelector.class);
        if (fxAccountSelector == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        List<PayeeRecord> records = new ArrayList<>();

        if (rqData.getType() == 1) {
            // 選擇常用Tab內的一筆轉出帳號
            List<TwPayeeRecord> twPayees = twAccountSelectorService.getFavoritePayee(TransOutAcctType.TWD_COMMONLY_USED_AND_AGREED_ACCT, twAccountSelector, getLoginUser(), getLocale(), rqData.getKey());
            List<FxPayeeRecord> fxPayees = fxAccountSelectorService.getFavoritePayee(TransOutAcctType.FOREIGN_COMMONLY_USED_AND_AGREED_ACCT, fxAccountSelector, getLoginUser(), getLocale(), rqData.getFxKey());
            this.merge(twPayees, fxPayees, records);
        }
        else if (rqData.getType() == 2) {
            // 選擇約定Tab內的一筆轉出帳號
            List<TwPayeeRecord> twPayees = twAccountSelectorService.getAgreedPayee(rqData.isSyncAgreeIn(), TransOutAcctType.TWD_COMMONLY_USED_AND_AGREED_ACCT, twAccountSelector, getLoginUser(), getLocale(), rqData.getKey());
            List<FxPayeeRecord> fxPayees = fxAccountSelectorService.getAgreedPayee(rqData.isSyncAgreeIn(), TransOutAcctType.FOREIGN_COMMONLY_USED_AND_AGREED_ACCT, fxAccountSelector, getLoginUser(), getLocale(), rqData.getFxKey());
            this.merge(twPayees, fxPayees, records);
        }

        rsData.setPayees(records);

        setCache(TwAccountSelectorService.FAVORITE_CACHE_KEY, twAccountSelector);
        setCache(FxAccountSelectorService.FAVORITE_CACHE_KEY, fxAccountSelector);

    }

    /**
     * merge 台外幣轉入帳號
     * 
     * @param twPayees
     * @param fxPayees
     * @param records
     */
    private void merge(List<TwPayeeRecord> twPayees, List<FxPayeeRecord> fxPayees, List<PayeeRecord> records) {
        List<PayeeRecord> temps = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(twPayees))
            for (TwPayeeRecord payee : twPayees) {
                PayeeRecord temp = new PayeeRecord();
                temp.setKey(payee.getKey());
                temp.setPayeeBankId(payee.getPayeeBankId());
                temp.setPayeeBankName(payee.getPayeeBankName());
                temp.setPayeeAccount(payee.getPayeeAccount());
                temp.setDesignate(payee.isDesignate());
                temp.setFavorite(payee.isFavorite());
                temp.setPayeeNickname(payee.getPayeeNickname());
                temp.setPayeeAvatarCharacter(payee.getPayeeAvatarCharacter());
                temp.setPayeeAccountName(payee.getPayeeAccountName());
                temp.setDisplayAccountId(payee.getDisplayAccountId());
                temp.setSort(payee.getSort());
                temps.add(temp);
            }

        if (CollectionUtils.isNotEmpty(fxPayees))
            for (FxPayeeRecord payee : fxPayees) {
                PayeeRecord temp = new PayeeRecord();
                temp.setKey(payee.getKey());
                temp.setPayeeBankId(payee.getPayeeBankId());
                temp.setPayeeBankName(payee.getPayeeBankName());
                temp.setPayeeAccount(payee.getPayeeAccount());
                temp.setDesignate(payee.isDesignate());
                temp.setFavorite(payee.isFavorite());
                temp.setPayeeNickname(payee.getPayeeNickname());
                temp.setPayeeAvatarCharacter(payee.getPayeeAvatarCharacter());
                temp.setPayeeAccountName(payee.getPayeeAccountName());
                temp.setDisplayAccountId(payee.getDisplayAccountId());
                temp.setSort(payee.getSort());
                temp.setIsXXX(true);
                temps.add(temp);
            }

        temps = this.filter(temps);
        temps = IBUtils.sort(temps, "sort", false);

        records.addAll(temps);

    }

    // 過濾帳號
    private List<PayeeRecord> filter(List<PayeeRecord> accts) {
        return accts.stream().filter(Objects::nonNull) // 排除空物件
                .filter(this.distinctByKey(acc -> acc.getPayeeBankId() + acc.getPayeeAccount())) // 過濾重複帳號
                .collect(Collectors.toList());
    }

    /**
     * 過濾重複鍵值
     * 
     * @param keyExtractor
     * @return
     */
    public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }
}