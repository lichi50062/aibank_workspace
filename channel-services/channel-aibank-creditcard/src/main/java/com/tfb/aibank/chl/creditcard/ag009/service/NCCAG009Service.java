package com.tfb.aibank.chl.creditcard.ag009.service;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW1410RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW1410RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CardLossModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CardLossTxnModel;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)NCCAG009Service.java
 * 
 * <p>Description:信用卡掛失 Service</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

@Service
public class NCCAG009Service extends NCCService {
    private static final IBLog logger = IBLog.getLog(NCCAG009Service.class);

    public static final String NCCAG009_CACHE_KEY = "NCCAG009CacheKey";

    /**
     * 新增掛失紀錄
     * 
     * @param user
     * @param rqData
     * @param response
     * @throws ActionException
     */
    public void createCardLossLog(String clientIp, AIBankUser user, CreditCard creditCard, CardLossModel model) {

        // 新增一筆開卡紀錄
        model.setCompanyKind(user.getCompanyKind());
        model.setNameCode(user.getUserData().getNameCode());
        model.setCustId(user.getCustId());
        model.setUserId(user.getUserId());
        model.setTraceId(MDC.get(MDCKey.traceId.name()));
        model.setCardNo(creditCard.getCardNo());
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        model.setClientIp(clientIp);
        model.setTxDate(new Date());
        model.setTxSource("3");
        CardLossModel serviceResponse = creditCardResource.addCardLoss(model, user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind());
        model.setLossKey(serviceResponse.getLossKey());

    }

    /**
     * 申請掛失
     * 
     * @param rqData
     * @param response
     * @throws ActionException
     */
    public void applyLossCreditCard(AIBankUser user, CreditCard creditCard, CardLossTxnModel model, Boolean isPrimaryCard, Locale locale, MBAccessLog accessLog) {
        CEW1410RRequest request = new CEW1410RRequest();
        request.setCrdno(creditCard.getCardNo());
        request.setFunc("2");
        request.setHdid(user.getCustId());
        request.setIsPrimaryCard(isPrimaryCard);

        TxStatusType txnStatusType = TxStatusType.SUCCESS;
        ActionException aex = null;
        BaseServiceResponse<CEW1410RResponse> serviceResponse = null;
        CEW1410RResponse res = null;
        try {
            serviceResponse = creditCardResource.applyLossCreditCard(request);
            res = serviceResponse.getData();
            model.setCardHolderId(res.getCardHolderId());
            model.setPrimaryCardNo(res.getPrimaryCardNo());
            model.setSupplementaryCardNos(res.getSupplementaryCardNos());
            model.setFunc(res.getFunc());
            model.setErrorCode(res.getResult());
            if (!Constants.STATUS_CODE_SUCCESS.equals(res.getResult())) {
                txnStatusType = TxStatusType.FAIL;
                ErrorDescription errDes = IBUtils.getErrorDescMessage(errorCodeCacheManager, new ErrorStatus(IBSystemId.AI.getSystemId(), res.getResult(), SeverityType.ERROR, ""), locale, MDC.get(MDCKey.frompage.name()));
                model.setErrorSystemId(errDes.getSys());
                model.setErrorDesc(errDes.getDesc());
            }
        }
        catch (ServiceException sex) {
            aex = handleException(accessLog, sex);
            ErrorDescription errDes = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex.getStatus(), locale, MDC.get(MDCKey.frompage.name()));
            txnStatusType = getTxStatusType(sex);
            model.setErrorSystemId(errDes.getSys());
            model.setErrorCode(aex.getErrorCode());
            model.setErrorDesc(aex.getErrorDesc());
            logger.warn("NCCAG009 applyLossCreditCard error :" + JsonUtils.getJson(aex));
        }
        model.setSendToHostTime(new Date());
        model.setTxStatus(txnStatusType.getCode());

    }

    /**
     * 更新掛失紀錄
     * 
     * @param cardApplyResponse
     * @param updateDataId
     * @param response
     * @throws ActionException
     */
    public void updateLossCreditCard(CardLossModel model, AIBankUser user, CardLossTxnModel txnModel) {

        model.setTxStatus(txnModel.getTxStatus());
        model.setCustId(user.getCustId());
        model.setCompanyKind(user.getCompanyKind());
        model.setUserId(user.getUserId());
        model.setUpdateTime(new Date());
        Optional.ofNullable(txnModel).ifPresent(cardLossRs -> {
            model.setHostTxTime(cardLossRs.getSendToHostTime());
            model.setTxErrorCode(cardLossRs.getErrorCode());
            model.setTxErrorDesc(cardLossRs.getErrorDesc());
            model.setTxErrorSystemId(cardLossRs.getErrorSystemId());
        });
        try {
            creditCardResource.updateCardLoss(model, user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind());
        }
        catch (ServiceException ex) {
            logger.warn("NCCAG009: " + ex.getLocalizedMessage());
        }
    }

}
