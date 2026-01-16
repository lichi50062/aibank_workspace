package com.tfb.aibank.chl.general.ot003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.accountselect.TwAccountSelectorService;
import com.tfb.aibank.chl.component.accountselect.model.TwAccountSelector;
import com.tfb.aibank.chl.component.accountselect.model.TwTransRecord;
import com.tfb.aibank.chl.component.favoriteaccount.FavoriteAccountService;
import com.tfb.aibank.chl.component.favoriteaccount.model.CreateFavoriteAccountRequest;
import com.tfb.aibank.chl.component.favoriteaccount.model.CreateFavoriteAccountResponse;
import com.tfb.aibank.chl.general.ot003.model.NGNOT003012Rq;
import com.tfb.aibank.chl.general.ot003.model.NGNOT003012Rs;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNOT003012Task.java
 *
 * <p>Description: 收付 新增常用帳號</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2024/09/05, Yoyo Lin
 *  <li>[新增說明]</li>
 * </ol>
 */
//@formatter:on
@Component
@Scope("prototype")
public class NGNOT003012Task extends AbstractAIBankBaseTask<NGNOT003012Rq, NGNOT003012Rs> {

    @Autowired
    private FavoriteAccountService favoriteAccountService;

    @Override
    public void validate(NGNOT003012Rq rqData) throws ActionException {
        // 檢核帳號暱稱(非必填)
        String favoriteName = rqData.getFavoriteName();
        if (StringUtils.isNotBlank(favoriteName)) {
            // 格式錯誤
            if (!ValidatorUtility.isValidChar(favoriteName)) {
                addErrorFieldMap("favoriteName", I18NUtils.getMessage("common.error.format"));
            }
            else if (favoriteName.length() > 20) {
                addErrorFieldMap("favoriteName", I18NUtils.getMessage("common.error.format"));
            }
        }
    }

    @Override
    public void handle(NGNOT003012Rq rqData, NGNOT003012Rs rsData) throws ActionException {

        TwAccountSelector accountSelector = getCache(TwAccountSelectorService.CACHE_KEY, TwAccountSelector.class);
        if (accountSelector == null) {
            // fortify: Redundant Null Check
            throw ExceptionUtils.getActionException(CommonErrorCode.UNKNOWN_EXCEPTION);
        }

        CreateFavoriteAccountRequest request = new CreateFavoriteAccountRequest();

        AIBankUser loginUser = getLoginUser();

        request.setCustId(loginUser.getCustId());
        request.setUserId(loginUser.getUserId());
        request.setCompanyKind(loginUser.getCompanyKind());
        request.setUidDup(loginUser.getUidDup());
        request.setNameCode(loginUser.getNameCode());
        request.setAccountAlias(rqData.getFavoriteName());

        TwTransRecord trans = accountSelector.getTrans().stream().filter(c -> StringUtils.equals(rqData.getFavoriteAcctKey(), c.getKey())).findFirst().orElse(null);
        request.setPayerAccount(trans.getPayerAccount());
        request.setPayeeBank(trans.getPayeeBankId());
        request.setPayeeAccount(trans.getPayeeAccount());

        CreateFavoriteAccountResponse response = favoriteAccountService.createFavoriteAccount(loginUser, request);
        if (response.isSuccess()) {
            accountSelector.getFavoritePayeeMap().clear();
            setCache(TwAccountSelectorService.CACHE_KEY, accountSelector);
        }
        rsData.setSuccess(response.isSuccess());

    }
}
