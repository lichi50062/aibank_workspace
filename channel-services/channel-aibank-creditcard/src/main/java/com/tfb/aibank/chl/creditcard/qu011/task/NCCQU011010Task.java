package com.tfb.aibank.chl.creditcard.qu011.task;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011010Rq;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011010Rs;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011CacheData;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011Output;
import com.tfb.aibank.chl.creditcard.qu011.service.NCCQU011Service;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCQU011010Task.java
 * 
 * <p>Description:好多金總覧 010 好多金總覧-功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU011010Task extends AbstractAIBankBaseTask<NCCQU011010Rq, NCCQU011010Rs> {

    @Autowired
    private UserDataCacheService userDataCacheService;
    @Autowired
    private NCCQU011Service service;

    private NCCQU011Output dataOutput = new NCCQU011Output();

    @Override
    public void validate(NCCQU011010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU011010Rq rqData, NCCQU011010Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        Locale userLocale = getLocale();

        // 信用卡戶況及註記
        userDataCacheService.validateCreditCardStatus(loginUser);
        // 檢核是否為信用卡正卡持有人
        boolean isPrimaryCard = userDataCacheService.isPrimaryCard(loginUser, userLocale);
        if (!isPrimaryCard) {
            throw ExceptionUtils.getActionException(ErrorCode.PRIMARY_CARDHOLDER_ONLY);
        }

        // 是否「資料查詢失敗」
        boolean isQueryFailed = false;

        NCCQU011CacheData cache = new NCCQU011CacheData();

        try {
            // 發查電文(VB0051)取得好多金餘額
            String montyp = "0";
            service.getCostcoBalance(loginUser, montyp, dataOutput);
            cache.getCostcoBalanceMap().put(montyp, dataOutput.getCostcoBalance());
            rsData.setCostcoBalance(cache.getCostcoBalanceMap().get(montyp));
        }
        catch (ServiceException se) {
            logger.error("發查電文(VB0051)失敗。不中止程序。", se);
            isQueryFailed = true;
        }

        if (!isQueryFailed) {
            try {
                // 發查電文(VB0052)取得好多金明細
                service.getCostcoDetails(loginUser, dataOutput, cache);
                service.setCardName(loginUser, userLocale, dataOutput.getCostcoDetails());
                cache.setCostcoDetails(dataOutput.getCostcoDetails());
                rsData.setCostcoDetails(dataOutput.getCostcoDetails());
                rsData.setMontyp(cache.getMontyp());
            }
            catch (ServiceException se) {
                logger.error("發查電文(VB0052)失敗。不中止程序。", se);
                isQueryFailed = true;
            }
        }
        rsData.setQueryFailed(isQueryFailed);

        // 讀取信用卡清單
        service.getCreditCardList(loginUser, userLocale, dataOutput);
        rsData.setCardInfos(dataOutput.getCardInfos());

        this.setCache(NCCQU011Service.CACHE_KEY, cache);
    }

}
