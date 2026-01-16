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
package com.tfb.aibank.chl.general.ag004.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.general.ag004.model.vo.FxCurrencyVo;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.resource.InformationResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.RateCardUser;
import com.tfb.aibank.chl.general.resource.dto.RateCurrency;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNAG004Service.java
 * 
 * <p>Description:匯率設定 Service</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NGNAG004Service extends NGNService {

    public static final String NGNAG004_CACHE_KEY = "NGNAG004CacheKey";

    private static final IBLog logger = IBLog.getLog(NGNAG004Service.class);

    @Autowired
    private InformationResource informationResource;

    @Autowired
    private UserResource userResource;

    /**
     * 查詢外幣清單
     * 
     * @param rateType
     * @return
     */
    public void queryFxCurrency(String rateType, AIBankUser user, Locale locale, List<FxCurrencyVo> output) {
        List<RateCardUser> userCards = userResource.retrieveUserRateCards(user.getCustId(), user.getUserId(), user.getCompanyKind(), StringUtils.defaultString(user.getUidDup(), "0"));

        List<RateCurrency> rateCurrencys = informationResource.getRateCurrencyInTypes(rateType);
        Map<String, Integer> userCardsMap = userCards.stream().collect(Collectors.toMap(RateCardUser::getCurrencyEname1, RateCardUser::getCurrencySort));
        List<FxCurrencyVo> currencyVos = rateCurrencys.stream().map(rateCurrency -> {
            FxCurrencyVo fxCurrencyVo = new FxCurrencyVo();
            fxCurrencyVo.setCurName(currencyCodeCacheManager.getCurrencyName(rateCurrency.getCurrencyEname1(), locale));
            fxCurrencyVo.setCurCode(StringUtils.lowerCase(rateCurrency.getCurrencyEname1()));
            // 0是首頁顯示 1是其他幣別
            fxCurrencyVo.setDisplayArea(userCardsMap.containsKey(rateCurrency.getCurrencyEname1()) ? "0" : "1");
            fxCurrencyVo.setCurIndex(rateCurrency.getCurrencySort().toString());
            if (Objects.nonNull(userCardsMap.get(rateCurrency.getCurrencyEname1()))) {
                fxCurrencyVo.setHomeDisplayIndex(String.valueOf(userCardsMap.get(rateCurrency.getCurrencyEname1())));
            }
            else {
                fxCurrencyVo.setHomeDisplayIndex("-1");
            }
            return fxCurrencyVo;
        }).toList();
        output.addAll(currencyVos);
    }

    /**
     * RATE_CARD_USER 資料庫處理
     * 
     * @param user
     * @param currencyInHomeArea
     * @param locale
     * @throws ActionException
     */
    public void doRateCardUser(AIBankUser user, List<FxCurrencyVo> currencyInHomeArea, Locale locale) throws ActionException {
        Date currentDate = new Date();
        List<RateCardUser> userRateCardsInHome = currencyInHomeArea.stream().map(currencyVo -> {
            RateCardUser rateCardUser = new RateCardUser();
            rateCardUser.setCurrencyEname1(StringUtils.upperCase(currencyVo.getCurCode(), locale));
            rateCardUser.setCurrencySort(ConvertUtils.str2Int(currencyVo.getHomeDisplayIndex()));
            rateCardUser.setUpdateTime(currentDate);
            rateCardUser.setCreateTime(currentDate);
            return rateCardUser;
        }).toList();
        try {
            userResource.deleteUserRateCards(user.getCustId(), user.getUserId(), user.getCompanyKind(), user.getUidDup());
            userResource.addUserRateCard(user.getCustId(), user.getUserId(), user.getCompanyKind(), StringUtils.defaultString(user.getUidDup(), "0"), userRateCardsInHome);
        }
        catch (ServiceException ex) {
            logger.error(ex.getMessage());
            throw ExceptionUtils.getActionException(ErrorCode.UPDATE_USER_CARD_CURRENCY_ERROR);
        }

    }
}
