package com.tfb.aibank.chl.creditcard.qu011.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011021Rq;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011021Rs;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011CacheData;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011CostcoBalance;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011Output;
import com.tfb.aibank.chl.creditcard.qu011.service.NCCQU011Service;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCQU011021Task.java
 * 
 * <p>Description:好多金總覧 021 每期結餘資訊頁-展開詳細資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/10, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU011021Task extends AbstractAIBankBaseTask<NCCQU011021Rq, NCCQU011021Rs> {

    @Autowired
    private NCCQU011Service service;

    private NCCQU011Output dataOutput = new NCCQU011Output();

    @Override
    public void validate(NCCQU011021Rq rqData) throws ActionException {
        String period = rqData.getBillingPeriod();
        if (!ValidatorUtility.checkNumeric(period) || ConvertUtils.str2Int(period) < 0 || ConvertUtils.str2Int(period) > 6) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCQU011021Rq rqData, NCCQU011021Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        NCCQU011CacheData cache = this.getCache(NCCQU011Service.CACHE_KEY, NCCQU011CacheData.class);

        // 處理此次進入的請求
        String montyp = rqData.getBillingPeriod();
        NCCQU011CostcoBalance costcoBalance = cache.getCostcoBalanceMap().get(montyp);
        if (StringUtils.isBlank(costcoBalance.getRspCode())) {
            String period = costcoBalance.getPeriod();
            // 發查電文(VB0051)取得好多金餘額
            service.getCostcoBalance(loginUser, montyp, dataOutput);
            costcoBalance = dataOutput.getCostcoBalance();
            costcoBalance.setPeriod(period);
            cache.getCostcoBalanceMap().put(montyp, costcoBalance);
        }

        rsData.setCostcoBalance(costcoBalance);

        this.setCache(NCCQU011Service.CACHE_KEY, cache);

    }

}
