package com.tfb.aibank.chl.creditcard.ag005.task;

import java.util.Arrays;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag005.model.InsurFeeBenefitsType;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005020Rq;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005020Rs;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005CacheData;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005CardInfo;
import com.tfb.aibank.chl.creditcard.ag005.service.NCCAG005Service;


// @formatter:off
/**
 * @(#)NCCAG005020Task.java
 * 
 * <p>Description:保費權益設定 020 設定編輯頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG005020Task extends AbstractAIBankBaseTask<NCCAG005020Rq, NCCAG005020Rs> {
    @Override
    public void validate(NCCAG005020Rq rqData) throws ActionException {
        NCCAG005CacheData cache = this.getCache(NCCAG005Service.CACHE_KEY, NCCAG005CacheData.class);
        if (rqData.getCardIdx() >= cache.getCardInfoList().size()) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCAG005020Rq rqData, NCCAG005020Rs rsData) throws ActionException {
        rsData.setInsurFeeBenefitsList(Arrays.asList(InsurFeeBenefitsType.values()));
        rsData.setInsurFeeBenefitsListUnderFive(InsurFeeBenefitsType.getSomeInsurFeeBenefits());
        NCCAG005CacheData cache = this.getCache(NCCAG005Service.CACHE_KEY, NCCAG005CacheData.class);
        NCCAG005CardInfo cardInfo = cache.getCardInfoList().get(rqData.getCardIdx());
        rsData.setCardName(cardInfo.getCardName());
        rsData.setInsurFeeBenefits(cardInfo.getInsurFeeBenefits().getCode());
        if(cardInfo.getInsurFeeBenefitsUnderFive() != null) {
			// 單筆未達5萬元,若客戶原設定為「9期0利率」或「12期0利率」則預設勾選0.5%回饋
			if (cardInfo.getInsurFeeBenefitsUnderFive() == InsurFeeBenefitsType.NINE_PERIODS_ZERO_INTEREST_RATE
					|| cardInfo.getInsurFeeBenefitsUnderFive() == InsurFeeBenefitsType.TWELVE_PERIODS_ZERO_INTEREST_RATE) {
				rsData.setInsurFeeBenefitsUnderFive(InsurFeeBenefitsType.FEEDBACK.getCode());
			} else {
				rsData.setInsurFeeBenefitsUnderFive(cardInfo.getInsurFeeBenefitsUnderFive().getCode());
			}
        	rsData.setUnderFiveFlag(true);
        } else {
        	rsData.setUnderFiveFlag(false);
        }
        rsData.setCardKey(cardInfo.getCardKey());
        cache.setCardInfo(cardInfo);
        this.setCache(NCCAG005Service.CACHE_KEY, cache);
    }

}
