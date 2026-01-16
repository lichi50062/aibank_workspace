package com.tfb.aibank.chl.creditcard.ag011.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011021Rs;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011Cache;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.resource.UserResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW466RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CardCostcoDues;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)NCCAG011Service.java
 * 
 * <p>Description:CHL NCCAG011 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCAG011Service extends NCCService {
    public static final String CACHE_KEY = "NCCAG011_CACHE";
    public static final String REMARK_KEY = "NCCAG011_020_Agreement";

    private static final IBLog logger = IBLog.getLog(NCCAG011Service.class);

    private static final List<String> NON_VALID_MEMBER = List.of("04", "07", "09");
    private static final List<String> SUCCESS_STATUS_CODES = List.of(Constants.STATUS_CODE_SUCCESS, "V848");
    private static final List<String> FAILED_STATUS_CODES = List.of("9996", "9997", "9998", "9999");

    @Autowired
    private UserResource userResource;

    /**
     * 發查電文 CEW466R
     * 
     * @param type
     * @param input
     * @param dataOutput
     * @throws ActionException
     */
    public void getCostcoMemberInfo(String custIxd, NCCAG011Cache cacheData) throws ActionException {

        CEW466RRes res = userResource.getCostcoMemberInfo(custIxd, "09", "");
        String abn = res.getAbn();

        boolean resCode_00 = StringUtils.equals(res.getResponseCode(), "00");
        boolean isP = StringUtils.equals(res.getMembercardType(), "P");

        if (StringUtils.equals(abn, Constants.STATUS_CODE_SUCCESS)) {
            // membercardType=P視為主卡&responseCode=00 視為有效會員, 若非Costco 有效主卡會員，取錯誤代碼SVC02045
            // 若為主卡但非有效會員或是ResponseCode=04,07，或09，取錯誤代碼SVC02045，引導至「共通引導頁」，顯示錯誤訊息。
            if (!(resCode_00 && isP) || NON_VALID_MEMBER.contains(res.getResponseCode())) {
                throw ExceptionUtils.getActionException(ErrorCode.COSTCO_NOT_MEMBER);
            }
        }
        else if (FAILED_STATUS_CODES.contains(abn)) {
            throw ExceptionUtils.getActionException(ErrorCode.COSTCO_API_FAIL);
        }
        else {
            throw ExceptionUtils.getActionException(ErrorCode.COSTCO_SEND_FAIL);
        }

        cacheData.setRes(res);
    }

    /**
     * 發查電文 CEW466R
     * 
     * @param type
     * @param input
     * @param dataOutput
     * @throws ActionException
     */
    public CEW466RRes applyCostco(String custIxd, NCCAG011Cache cacheData, NCCAG011021Rs rsData) throws ActionException {

        CEW466RRes res = userResource.getCostcoMemberInfo(custIxd, "10", "F");
        String abn = res.getAbn();

        boolean resCode_20 = StringUtils.equals(res.getResponseCode(), "20");

        // A. CEW466R_Rs. responseCode = 20且CEW466R_Rs.VOABND=0000，代表電文發送成功。
        // B. 若電CEW466R_Rs.VOABND=9996, 9997, 9998, 9999，代表好市多API回覆失敗。
        // C. 若非以上回覆，代表卡主機電文發查失敗
        if (StringUtils.equals(abn, Constants.STATUS_CODE_SUCCESS) && resCode_20) {
            // 電文發送成功
            rsData.setSuccess(true);
        }
        // "9996", "9997", "9998", "9999" 好市多API回覆失敗
        else if (FAILED_STATUS_CODES.contains(abn)) {
            throw ExceptionUtils.getActionException(ErrorCode.COSTCO_API_FAIL);
        }
        else {
            throw ExceptionUtils.getActionException(ErrorCode.COSTCO_SEND_FAIL);
        }
        cacheData.setRes(res);

        return res;
    }

    /**
     * 執行交易
     * 
     * @param user
     * @param cacheData
     * @param traceId
     * @param txKind
     * @param accessLogEntity
     * @throws ActionException
     */

    public void doTransaction(AIBankUser user, NCCAG011Cache cacheData, NCCAG011021Rs rsData, String traceId) throws ActionException {
        ActionException aex = null;
        ActionException bex = null;
        CEW466RRes res = null;
        try {
            // 自動續約
            res = applyCostco(user.getCustId(), cacheData, rsData);

        }
        catch (ActionException ex) {
            logger.error(ex.getMessage(), ex);
            aex = ExceptionUtils.getActionException(ex);
            rsData.setTxStatus(TxStatusType.FAIL.getCode());
            throw aex;
        }
        catch (ServiceException ex) {
            logger.error(ex.getMessage(), ex);
            aex = ExceptionUtils.getActionException(ex);
            rsData.setTxStatus(getTxStatusType(ex).getCode());
            throw aex;
        }
        finally {

            if (aex != null) {
                rsData.setSuccess(false);
                rsData.setSystemId(aex.getSystemId());
                rsData.setErrorCode(aex.getErrorCode());
                rsData.setErrorDesc(aex.getErrorDesc());

                // 更新CardCostcoDues
                saveCardCostcoDues(user, cacheData, aex, bex);
            }
        }

        try {
            // 續約通知
            // 若發送CEW4661R失敗，會繼續發送，直到成功，最多再發送3次
            notifyCostcoMember(user.getCustId(), res.getResponseCode(), res.getAbn());
        }
        catch (ServiceException ex) {
            logger.error(ex.getMessage(), ex);
            bex = ExceptionUtils.getActionException(ex);
            rsData.setTxStatus(getTxStatusType(ex).getCode());
            throw bex;
        }
        finally {
            if (bex != null) {
                rsData.setSuccess(false);
                rsData.setSystemId(bex.getSystemId());
                rsData.setErrorCode(bex.getErrorCode());
                rsData.setErrorDesc(bex.getErrorDesc());
            }
            else {
                rsData.setTxStatus(TxStatusType.SUCCESS.getCode());
            }

            // 更新CardCostcoDues
            saveCardCostcoDues(user, cacheData, aex, bex);
        }
    }

    private void notifyCostcoMember(String custId, String responseCode, String abn) throws ServiceException {
        boolean success = false;
        int fail = 0;
        CEW466RRes res1r;
        ServiceException serEx = null;
        while (!success && fail < 3) {
            try {
                res1r = userResource.notifyCostcoMember(custId, responseCode, abn);
                if (SUCCESS_STATUS_CODES.contains(res1r.getResponseCode())) {
                    success = true;
                }
                else {
                    logger.error("api failed, responseCode: {}", res1r.getResponseCode());
                    throw new ServiceException(IBSystemId.SVC.getSystemId(), ErrorCode.COSTCO_SEND_FAIL.getMemo(), SeverityType.ERROR, ErrorCode.COSTCO_SEND_FAIL.getErrorCode());
                }
            }
            catch (ServiceException e) {
                logger.error("notifyCostcoMember failed, fail times: {}, retrying...", fail);
                fail++;
                serEx = e;
            }
        }

        if (fail >= 3 && serEx != null)
            throw serEx;
    }

    public void saveCardCostcoDues(AIBankUser user, NCCAG011Cache cache, ActionException ex, ActionException ex2) {

        CardCostcoDues ccd = prepareCardCostcoDues(user, cache.getClientIp(), cache.getTraceId(), cache.getRes());

        if (ex != null) {
            ccd.setTxErrorSystemId(ex.getSystemId());
            ccd.setErrorCode466r(ex.getErrorCode());
            ccd.setErrorDesc466r(ex.getErrorDesc());
            ccd.setTxStatus("1"); // 交易狀態(0：成功、1：失敗)
        }
        else if (ex2 != null) {
            ccd.setTxErrorSystemId(ex2.getSystemId());
            ccd.setErrorCode4661r(ex2.getErrorCode());
            ccd.setErrorDesc4661r(ex2.getErrorDesc());
            ccd.setTxStatus("1"); // 交易狀態(0：成功、1：失敗)
        }
        else {
            ccd.setTxStatus("0"); // 交易狀態(0：成功、1：失敗)
        }

        userResource.saveCardCostcoDues(ccd);
    }

    private CardCostcoDues prepareCardCostcoDues(AIBankUser user, String clientIp, String traceId, CEW466RRes res) {
        CardCostcoDues log = new CardCostcoDues();
        log.setCustId(user.getCustId());
        log.setUidDup(user.getUidDup());
        log.setCompanyKind(user.getCompanyKind());
        log.setNameCode(user.getNameCode());
        log.setUserId(user.getUserId());
        log.setClientIp(clientIp);
        log.setTraceId(traceId);
        log.setTxDate(new Date());
        if (res != null) {
            log.setMemberNo(res.getMemberNumber());
            log.setMemberCardType(res.getMembercardType());
            if (!StringUtils.equals("0000", res.getAbn())) {
                log.setTxErrorCode(res.getAbn());
            }
        }

        return log;
    }

    public void getCardMemberMobile(AIBankUser user) {
        userDataCacheService.getOtpMobileFromCEW013R(user);
    }
}
