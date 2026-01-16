package com.tfb.aibank.chl.creditcard.ag008.service;

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
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.creditcard.ag008.model.NCCAG008011Rq;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW208RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW208RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CardActivateModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CreditCardApplyModel;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)NCCAG008Service.java
 * 
 * <p>Description:信用卡開卡 Service</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

@Service
public class NCCAG008Service extends NCCService {
    private static final IBLog logger = IBLog.getLog(NCCAG008Service.class);

    /**
     * 新增開卡紀錄
     * 
     * @param user
     * @param rqData
     * @param response
     * @throws ActionException
     */
    public void createCardActivateLog(AIBankUser user, String clientIp, NCCAG008011Rq rqData, CardActivateModel model) {

        // 新增一筆開卡紀錄
        model.setUserId(user.getUserId());
        model.setCompanyKind(user.getCompanyKind());
        model.setNameCode(user.getUserData().getNameCode());
        model.setCustId(user.getCustId());
        model.setTraceId(MDC.get(MDCKey.traceId.name()));

        model.setCardNo(rqData.getCardNo());
        model.setCardCvv2(rqData.getCardCvv2());
        model.setBirthday(DateUtils.getSimpleDate(rqData.getBirthday()));

        model.setCardNedy(DateUtils.changeFormat(rqData.getCardNedy(), "MMyy", "MM/yyyy"));
        model.setTxStatus(TxStatusType.PROCESS.getCode());
        Date currentTime = new Date();

        Date currentDate = new Date();
        DateUtils.clearTime(currentDate);

        model.setCreateTime(currentTime);
        model.setTxDate(currentDate);
        model.setTxSource("3");
        model.setClientIp(clientIp);

        CardActivateModel serviceResponse = creditCardResource.createCardActivate(model, user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind());
        model.setActivateKey(serviceResponse.getActivateKey());
    }

    /**
     * 申請開卡
     * 
     * @param rqData
     * @param response
     * @throws ActionException
     */
    public void applyCreditCard(NCCAG008011Rq rqData, CreditCardApplyModel model, Locale locale, MBAccessLog accessLogEntity) {
        CEW208RRequest request = new CEW208RRequest();
        request.setCustcdno(rqData.getCardNo());
        request.setCustcvv2(rqData.getCardCvv2());
        request.setCustbrdy(rqData.getBirthday());
        request.setCustnedy(rqData.getCardNedy());

        TxStatusType txnStatusType = TxStatusType.SUCCESS;
        ActionException aex = null;
        BaseServiceResponse<CEW208RResponse> serviceResponse = null;
        CEW208RResponse res = null;
        try {
            serviceResponse = creditCardResource.applyCreditCard(request);
            res = serviceResponse.getData();
            model.setAbndCode(res.getAbndCode());
            model.setErrorCode(res.getAbndCode());
            if (!Constants.STATUS_CODE_SUCCESS.equals(res.getAbndCode())) {
                txnStatusType = TxStatusType.FAIL;
                ErrorDescription errDes = IBUtils.getErrorDescMessage(errorCodeCacheManager, new ErrorStatus(IBSystemId.AI.getSystemId(), res.getAbndCode(), SeverityType.ERROR, ""), locale, MDC.get(MDCKey.frompage.name()));
                model.setErrorSystemId(errDes.getSys());
                model.setErrorDesc(errDes.getDesc());
            }
        }
        catch (ServiceException sex) {
            aex = handleException(accessLogEntity, sex);

            ErrorDescription errDes = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex.getStatus(), locale, MDC.get(MDCKey.frompage.name()));

            txnStatusType = getTxStatusType(sex);
            model.setErrorSystemId(errDes.getSys());
            model.setErrorCode(errDes.getCode());
            model.setErrorDesc(errDes.getDesc());
            logger.warn("NCCAG009 applyLossCreditCard error :" + aex.getMessage());
        }

        model.setTxnStatus(txnStatusType.getCode());
        model.setSendToHostTime(new Date());

    }

    /**
     * 更新開卡紀錄
     * 
     * @param cardApplyResponse
     * @param updateDataId
     * @param response
     * @throws ActionException
     */
    public void updateCreditCard(CardActivateModel request, AIBankUser user, CreditCardApplyModel model) {

        request.setTxStatus(model.getTxnStatus());
        request.setUpdateTime(new Date());
        request.setUserId(user.getUserId());
        request.setCustId(user.getCustId());
        request.setCompanyKind(user.getCompanyKind());
        Optional.ofNullable(model).ifPresent(cardApplyRs -> {
            request.setHostTxTime(cardApplyRs.getSendToHostTime());
            request.setTxErrorCode(cardApplyRs.getErrorCode());
            request.setTxErrorDesc(cardApplyRs.getErrorDesc());
            request.setTxErrorSystemId(cardApplyRs.getErrorSystemId());
        });

        try {
            creditCardResource.updateCardActivate(request, user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind());
        }
        catch (ServiceException ex) {
            logger.error("NCCAG008 updateCreditCard error" + ex.getLocalizedMessage());
        }
    }

}