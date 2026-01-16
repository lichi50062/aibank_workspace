package com.tfb.aibank.chl.creditcard.ag009.task;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag009.cache.NCCAG009CacheData;
import com.tfb.aibank.chl.creditcard.ag009.model.NCCAG009011Rq;
import com.tfb.aibank.chl.creditcard.ag009.model.NCCAG009011Rs;
import com.tfb.aibank.chl.creditcard.ag009.service.NCCAG009Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CardLossModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CardLossTxnModel;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.common.type.CreditCardHoldType;

//@formatter:off
/**
 * @(#)NCCAG009011Task.java
 * 
 * <p>Description:信用卡掛失 開卡action</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 *  </ol>
 */
//@formatter:on
@Component
@Scope("prototype")
public class NCCAG009011Task extends AbstractAIBankBaseTask<NCCAG009011Rq, NCCAG009011Rs> {

    @Autowired
    private UserDataCacheService creditcardService;

    @Autowired
    private NCCAG009Service service;

    @Override
    public void validate(NCCAG009011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG009011Rq rqData, NCCAG009011Rs rsData) throws ActionException {
        NCCAG009CacheData cache = getCache(NCCAG009Service.NCCAG009_CACHE_KEY, NCCAG009CacheData.class);
        CreditCard creditCard = cache.getCreditCard();
        String clientIp = this.getClientIp();

        CardLossModel lossRecordResponse = new CardLossModel();
        service.createCardLossLog(clientIp, getLoginUser(), creditCard, lossRecordResponse);

        // 是否正卡
        Boolean isPrimaryCard = CreditCardHoldType.PRIMARY_CARD.getCode().equals(creditCard.getCardHoldType().getCode());

        // 執行掛失
        CardLossTxnModel lossApplyModel = new CardLossTxnModel();
        service.applyLossCreditCard(getLoginUser(), creditCard, lossApplyModel, isPrimaryCard, getLocale(), getAccessLog());

        // 更新開卡資料
        service.updateLossCreditCard(lossRecordResponse, getLoginUser(), lossApplyModel);
        if (StringUtils.isNotBlank(getLoginUser().getEmail())) {
            sendMail(lossApplyModel, creditCard);
        }
        else {
            // 若為一般會員登入，發查 CEW013R 取得信用卡會員登記的行動電話
            String mobileNo = StringUtils.EMPTY;
            if (getLoginUser().getCustomerType().isGeneral()) {
                mobileNo = creditcardService.getOtpMobileFromCEW013R(getLoginUser());
            }
            else {
                mobileNo = getLoginUser().getMobileNo();
            }
            if (StringUtils.isNotBlank(mobileNo)) {
                String msg = I18NUtils.getMessage("nccag009.sms.desc", DateUtils.getDateTimeStr(new Date()), ConvertUtils.str2Int(lossApplyModel.getTxStatus()));
                this.sendResultMsg(msg, null, null, mobileNo);
            }
        }

        // 清空系統暫存信用卡資料
        creditcardService.cleanCache(getLoginUser());

        cache.setErrorMsg(I18NUtils.getMessage("common.txn_result.tx_error.desc", lossApplyModel.getErrorDesc(), StringUtils.isEmpty(lossApplyModel.getErrorSystemId()) ? "" : lossApplyModel.getErrorSystemId(), lossApplyModel.getErrorCode()));
        cache.setTxnStatus(lossApplyModel.getTxStatus());
        setCache(NCCAG009Service.NCCAG009_CACHE_KEY, cache);
        // 交易結果
        rsData.setTxnResult(createTxnResult(lossApplyModel.getTxStatus(), lossApplyModel.getSendToHostTime(), lossApplyModel.getErrorSystemId(), lossApplyModel.getErrorCode(), lossApplyModel.getErrorDesc()));
    }

    /**
     * 發送email通知。
     * 
     * @param isApplyCardSuccess
     * @param lossApplyResponse
     * @param creditCard
     * @throws ActionException
     */
    private void sendMail(CardLossTxnModel lossApplyModel, CreditCard creditCard) throws ActionException {

        String txStatus = lossApplyModel.getTxStatus();
        String txStatusCht = switch (txStatus) {
        case "0" -> I18NUtils.getMessage("nccag009.txnresult.success", getLocale());
        case "1" -> I18NUtils.getMessage("nccag009.txnresult.fail", getLocale());
        default -> I18NUtils.getMessage("nccag009.txnresult.process", getLocale());
        };
        Object[] txStatusParam = { txStatusCht };

        String subject = I18NUtils.getMessage("nccag009.mail.subject", getLocale(), txStatusParam);
        HashMap<String, String> params = new HashMap<>();
        params.put("txStatus", txStatus);

        params.put("txnTime", DateUtils.getDateTimeStr(new Date()));
        params.put("txnName", this.getTaskName());
        params.put("hostTxTime", DateUtils.getDateTimeStr(lossApplyModel.getSendToHostTime()));
        params.put("cardTypeName", creditCard.getCardTypeInfo().getCardTypeName());
        params.put("cardKind", creditCard.getCardLevelDesc());
        params.put("cardHoldType", "0".equals(creditCard.getCardHoldType().getCode()) ? I18NUtils.getMessage("nccag009.mail.primarycard.title", getLocale()) : I18NUtils.getMessage("nccag009.mail.supplementarycard.title", getLocale()));
        params.put("cardIdentifierMask", DataMaskUtil.maskCreditCard(creditCard.getCardNo()));

        this.sendTxnResultMail(subject, getLoginUser().getUserData().getEmails(), params);
    }

}