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
package com.tfb.aibank.chl.component.favoriteaccount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.component.favoriteaccount.model.CreateFavoriteAccountRequest;
import com.tfb.aibank.chl.component.favoriteaccount.model.CreateFavoriteAccountResponse;
import com.tfb.aibank.chl.component.favoriteaccount.model.UpdateFavoriteAccountRequest;
import com.tfb.aibank.chl.component.favoriteaccount.model.UpdateFavoriteAccountResponse;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)FavoriteAccountService.java
 * 
 * <p>Description:常用帳號服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/14, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class FavoriteAccountService {

    @Autowired
    private FavoriteAccountResource favoriteAccountResource;

    /** 新增常用帳號 */
    public CreateFavoriteAccountResponse createFavoriteAccount(AIBankUser loginUser, CreateFavoriteAccountRequest request) {
        CreateFavoriteAccountResponse response = favoriteAccountResource.createFavoriteAccount(request);
        loginUser.clearFavoriteAccountsMap();
        return response;
    }

    /** 更新常用帳號 */
    public UpdateFavoriteAccountResponse updateFavoriteAccount(AIBankUser loginUser, UpdateFavoriteAccountRequest request) {
        UpdateFavoriteAccountResponse response = favoriteAccountResource.updateFavoriteAccount(request);
        loginUser.clearFavoriteAccountsMap();
        return response;
    }

    /** 刪除常用帳號 */
    public Boolean deleteFavoriteAccount(AIBankUser loginUser, String payerAccount, String designateFlag, String payeeBank, String payeeAccount) {
        Boolean response = favoriteAccountResource.deleteFavoriteAccount(loginUser.getCustId(), loginUser.getUserId(), loginUser.getCompanyKind(), loginUser.getUidDup(), payerAccount, designateFlag, payeeBank, payeeAccount);
        loginUser.clearFavoriteAccountsMap();
        return response;
    }

    /** 排序常用帳號 */
    public Boolean sortFavoriteAccount(AIBankUser loginUser, String payerAccount, String designateFlag, List<String> bankAccounts) {
        Boolean response = favoriteAccountResource.sortFavoriteAccount(loginUser.getCustId(), loginUser.getUserId(), loginUser.getCompanyKind(), loginUser.getUidDup(), payerAccount, designateFlag, bankAccounts);
        loginUser.clearFavoriteAccountsMap();
        return response;
    }

}
