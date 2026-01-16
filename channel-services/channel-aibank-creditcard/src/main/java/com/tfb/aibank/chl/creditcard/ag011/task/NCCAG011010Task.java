package com.tfb.aibank.chl.creditcard.ag011.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011010Rq;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011010Rs;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011Cache;
import com.tfb.aibank.chl.creditcard.ag011.service.NCCAG011Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

// @formatter:off
/**
 * @(#)NCCAG011010Task.java
 * 
 * <p>Description:好市多會費代扣繳申請 010 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG011010Task extends AbstractAIBankBaseTask<NCCAG011010Rq, NCCAG011010Rs> {

    @Autowired
    private NCCAG011Service service;

    @Autowired
    private UserDataCacheService userDataCacheService;

    /** 交易暫存 */
    private NCCAG011Cache cacheData;

    @Override
    public void validate(NCCAG011010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG011010Rq rqData, NCCAG011010Rs rsData) throws ActionException {

        cacheData = new NCCAG011Cache();
        cacheData.setTraceId(getTraceId());
        cacheData.setClientIp(getClientIp());

        // 發查電文 CE6220R 取得信用卡資訊
        List<CreditCard> allCreditCards = userDataCacheService.getAllCreditCards(getLoginUser(), getLocale());
        allCreditCards = allCreditCards.stream().filter(c -> StringUtils.isNotBlank(c.getCardNo())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(allCreditCards)) {
            throw ExceptionUtils.getActionException(ErrorCode.NO_COSTCO_CARD);
        }
        // 電文成功，判斷是否持有Costco聯名卡
        List<String> cardInfo = new ArrayList<>();
        for (CreditCard cc : allCreditCards) {
            if (cc.getCardTypeInfo() != null) {
                cardInfo.add(cc.getCardTypeInfo().getCardCode());
            }
        }
        // 未持有Costco聯名卡，取錯誤代碼SVC02043，引導至「共通引導頁」，顯示錯誤訊息
        if (!cardInfo.stream().anyMatch(t -> t.equals("C001") || t.equals("C002") || t.equals("C003") || t.equals("C004"))) {
            throw ExceptionUtils.getActionException(ErrorCode.NO_COSTCO_CARD);
        }

        // 正卡判斷條件:CE6220R_Rs.CARD_NO=M_CARD_NO，則視為正卡，取錯誤代碼SVC02044，引導至「共通引導頁」，顯示錯誤訊息
        CreditCard creditCard = allCreditCards.stream().filter(c -> StringUtils.equals(c.getCardNo(), c.getmCardNo())).findFirst().orElse(null);
        if (creditCard == null) {
            throw ExceptionUtils.getActionException(ErrorCode.COSTCO_IS_NOT_PRIMARY);
        }

        service.getCostcoMemberInfo(getLoginUser().getCustId(), cacheData);

        // (6) 判斷是否已申請會費代扣繳
        if (StringUtils.equals(cacheData.getRes().getAutoRenew(), "F")) {
            service.saveCardCostcoDues(getLoginUser(), cacheData, null, null);
            cacheData.setApply(true);
        }

        rsData.setApply(cacheData.isApply());

        // otp/motp安控
        super.initTxSecurity();

        this.setCache(NCCAG011Service.CACHE_KEY, cacheData);
    }
}
