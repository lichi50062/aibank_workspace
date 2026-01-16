package com.tfb.aibank.chl.creditcard.qu008.task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu008.cache.NCCQU008CacheData;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008050Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008050Rs;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008InterestCalResult;
import com.tfb.aibank.chl.creditcard.qu008.service.NCCQU008Service;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

// @formatter:off
/**
 * @(#)NCCQU008050Task.java
 * 
 * <p>Description:信用卡分期管理 050 確認頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU008050Task extends AbstractAIBankBaseTask<NCCQU008050Rq, NCCQU008050Rs> {

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private NCCQU008Service service;

    @Override
    public void validate(NCCQU008050Rq rqData) throws ActionException {
        if (StringUtils.isBlank(rqData.getPeriod()) || ConvertUtils.str2Int(rqData.getPeriod()) < 0 || ConvertUtils.str2Int(rqData.getPeriod()) > 30) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCQU008050Rq rqData, NCCQU008050Rs rsData) throws ActionException {

        NCCQU008CacheData cache = getCache(NCCQU008Service.CACHE_KEY, NCCQU008CacheData.class);
        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.SESSION_EXPIRED);
        }
        service.genInsInterestSection(rqData.getPeriod(), cache, getLoginUser());
        service.reCalInterestSection(cache.getFreerate(), cache, rqData.getIsBillProcess(), rqData.getPeriod());
        cache.setPeriod(rqData.getPeriod());
        cache.setPeriodDesc(rqData.getPeriodDesc());
        // cache.setInterestCalResults(rqData.getInterestCalResults());

        if (Boolean.TRUE.equals(rqData.getIsBillProcess())) {
            rsData.setInstallmentRate(cache.getInstallmentRate());
        }
        else {
            List<CreditCard> effectiveCreditCards = userDataCacheService.getEffectiveCreditCards(getLoginUser(), getLocale());
            CreditCard creditCardOrNull = effectiveCreditCards.stream().filter(card -> StringUtils.equals(StringUtils.substring(card.getCardNo(), -4), StringUtils.substring(cache.getBilledDetailSelect().getCardNo(), -4))).findAny().orElse(null);

            if (Objects.isNull(creditCardOrNull)) {
                // ···· 複製規格
                rsData.setCardDesc(I18NUtils.getMessage("ncc.default.card_name") + "····" + StringUtils.substring(cache.getBilledDetailSelect().getCardNo(), -4));
                cache.setCardDesc(I18NUtils.getMessage("ncc.default.card_name") + "····" + StringUtils.substring(cache.getBilledDetailSelect().getCardNo(), -4));
            }
            else {
                // ···· 複製規格
                rsData.setCardDesc(creditCardOrNull.getCardName() + "····" + StringUtils.substring(cache.getBilledDetailSelect().getCardNo(), -4));
                // #2052 單筆分期完成頁明細內容卡片顯示格式不同
                cache.setCardDesc(creditCardOrNull.getCardName() + "····" + StringUtils.substring(cache.getBilledDetailSelect().getCardNo(), -4));
            }

            rsData.setInstallmentRate(ConvertUtils.bigDecimal2Str(cache.getFreerate(), 2) + "%");
            cache.setInstallmentRate(ConvertUtils.bigDecimal2Str(cache.getFreerate(), 2) + "%");

            rsData.setPurchaseDay(cache.getBilledDetailSelect().getPchDay());
            cache.setPurchaseDay(cache.getBilledDetailSelect().getPchDay());
            rsData.setNccDay(DateUtils.getCEDateStr(cache.getBilledDetailSelect().getCreditDate()));
            cache.setNccDay(DateUtils.getCEDateStr(cache.getBilledDetailSelect().getCreditDate()));
        }

        rsData.setIsBillProcess(rqData.getIsBillProcess());
        cache.setIsBillProcess(rqData.getIsBillProcess());
        cache.setInstallmentDataPeriod(rqData.getPeriodDesc());
        rsData.setInstallmentDataPeriod(rqData.getPeriodDesc());
        // #8083 NCCQU008_信用卡帳單分期確認頁英文版移除部分標題
        if (StringUtils.equals(getLocale().toString(), Locale.TAIWAN.toString()) || !Boolean.TRUE.equals(rqData.getIsBillProcess())) {
            rsData.setInstallmentData(cache.getInstallmentData());
        }
        if (CollectionUtils.isNotEmpty(cache.getInterestCalResults())) {
            // NCCQU008InterestCalResult interestCalResult = cache.getInterestCalResults().stream().filter(interestCal -> {
            // return StringUtils.equals(interestCal.getPayTimes(), rqData.getPeriod());
            // }).findAny().orElse(new NCCQU008InterestCalResult());

            String captial = cache.getInterestCalResults().get(1).getTotal();
            // 將所有的 Interest 字串轉換為 BigDecimal，並加總
            BigDecimal totalInterest = cache.getInterestCalResults().stream().map(NCCQU008InterestCalResult::getInterestOrigin) // 將每個字串轉換為 BigDecimal
                    .reduce(BigDecimal.ZERO, BigDecimal::add); // 加總

            cache.setInstallmentDesc(I18NUtils.getMessage("nccqu008.form.installmentDesc.desc", captial, IBUtils.formatMoney(totalInterest, 0, "$")));
            rsData.setInstallmentDesc(I18NUtils.getMessage("nccqu008.form.installmentDesc.desc", captial, IBUtils.formatMoney(totalInterest, 0, "$")));

        }

        rsData.setInstallmentAmt(cache.getInstallmentAmt());

        setCache(NCCQU008Service.CACHE_KEY, cache);
    }

}
