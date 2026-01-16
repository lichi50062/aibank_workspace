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
package com.tfb.aibank.biz.security.services.motp;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.security.repository.entities.MotpLogDataEntity;
import com.tfb.aibank.biz.security.services.motp.bean.CreateOtpUserRq;
import com.tfb.aibank.biz.security.services.motp.bean.CreateOtpUserRs;
import com.tfb.aibank.biz.security.services.motp.bean.DeleteOtpUserRq;
import com.tfb.aibank.biz.security.services.motp.bean.DeleteOtpUserRs;
import com.tfb.aibank.biz.security.services.motp.bean.GetUserValidTokensRq;
import com.tfb.aibank.biz.security.services.motp.bean.GetUserValidTokensRs;
import com.tfb.aibank.biz.security.services.motp.bean.InitPushRq;
import com.tfb.aibank.biz.security.services.motp.bean.InitPushRs;
import com.tfb.aibank.biz.security.services.motp.bean.MotpRq;
import com.tfb.aibank.biz.security.services.motp.bean.MotpRs;
import com.tfb.aibank.biz.security.services.motp.bean.PushMessageRq;
import com.tfb.aibank.biz.security.services.motp.bean.PushMessageRs;
import com.tfb.aibank.biz.security.services.motp.bean.RegisterTokenRq;
import com.tfb.aibank.biz.security.services.motp.bean.RegisterTokenRs;
import com.tfb.aibank.biz.security.services.motp.bean.VerifyOtpRq;
import com.tfb.aibank.biz.security.services.motp.bean.VerifyOtpRs;
import com.tfb.aibank.biz.security.services.motp.bean.ViewUsersTokensRq;
import com.tfb.aibank.biz.security.services.motp.bean.ViewUsersTokensRs;
import com.tfb.aibank.biz.security.services.motp.helper.MotpLogDataHelper;
import com.tfb.aibank.biz.security.services.motp.helper.MotpLogDataModel;
import com.tfb.aibank.biz.security.services.motp.type.MotpParam;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)MotpService.java
 * 
 * <p>Description:全景MOTP - API介接服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/06, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class MotpProxyService {

    private static final IBLog logger = IBLog.getLog(MotpProxyService.class);

    /** Timeout */
    private static final int CONNECTION_TIMEOUT = 60;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private MotpLogDataHelper motpLogDataHelper;

    /**
     * 全景MOTP API - 查詢使用者綁定狀態
     * 
     * @param account
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public GetUserValidTokensRs callAPIGetUserValidTokens(String account, MotpLogDataModel motpLogData) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException {
        GetUserValidTokensRq rq = new GetUserValidTokensRq();
        rq.setAccount(account);
        rq.setFlag("0");
        String connectURL = MessageFormat.format(systemParamCacheManager.getValue(MotpParam.MOTP_API_GET_USER_VALID_TOKENS), systemParamCacheManager.getValue(MotpParam.MOTP_API_URL_IP));
        return sendAndReceive(rq, GetUserValidTokensRs.class, connectURL, HttpMethod.GET, motpLogData);
    }

    /**
     * 全景MOTP API - 新增OTP使用者
     * 
     * @param account
     * @param desc
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public CreateOtpUserRs callAPICreateOtpUser(String account, String desc, MotpLogDataModel motpLogData) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException {
        CreateOtpUserRq rq = new CreateOtpUserRq();
        rq.setOpAcct(systemParamCacheManager.getValue(MotpParam.MOTP_API_ACCT));
        rq.setOpPwd(systemParamCacheManager.getValue(MotpParam.MOTP_API_PWD));
        rq.setAccount(account);
        List<String> group = new ArrayList<String>();
        group.add(AIBankConstants.CHANNEL_NAME);
        rq.setGroup(group);
        rq.setDeviceType("16384");
        rq.setDesc(StringUtils.left(desc, 80));
        rq.setLogMsg("此筆交易由AIBANK建立");
        String connectURL = MessageFormat.format(systemParamCacheManager.getValue(MotpParam.MOTP_API_CREATE_OTP_USER), systemParamCacheManager.getValue(MotpParam.MOTP_API_URL_IP));
        return sendAndReceive(rq, CreateOtpUserRs.class, connectURL, HttpMethod.POST, motpLogData);
    }

    /**
     * 全景MOTP API - 初始化推播資訊
     * 
     * @param account
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public InitPushRs callAPIInitPush(String account, MotpLogDataModel motpLogData) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException {
        InitPushRq rq = new InitPushRq();
        rq.setOpAcct(systemParamCacheManager.getValue(MotpParam.MOTP_API_ACCT));
        rq.setOpPwd(systemParamCacheManager.getValue(MotpParam.MOTP_API_PWD));
        rq.setAccount(account);
        rq.setDevice("16384");
        rq.setFlag("1");
        String connectURL = MessageFormat.format(systemParamCacheManager.getValue(MotpParam.MOTP_API_INIT_PUSH), systemParamCacheManager.getValue(MotpParam.MOTP_API_URL_IP));
        return sendAndReceive(rq, InitPushRs.class, connectURL, HttpMethod.POST, motpLogData);
    }

    /**
     * 全景MOTP API - 註冊載具
     * 
     * @param account
     * @param deviceId
     * @param sn
     * @param pushId
     * @param mobileType
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public RegisterTokenRs callAPIRegisterToken(String account, String deviceId, String sn, String pushId, String mobileType, MotpLogDataModel motpLogData) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException {
        RegisterTokenRq rq = new RegisterTokenRq();
        rq.setOpAcct(systemParamCacheManager.getValue(MotpParam.MOTP_API_ACCT));
        rq.setOpPwd(systemParamCacheManager.getValue(MotpParam.MOTP_API_PWD));
        rq.setAccount(account);
        rq.setToken_num(sn);
        rq.setMachineCode(deviceId);
        rq.setPush_id(pushId);
        rq.setMobile_type(mobileType);
        rq.setFlag("0");
        String connectURL = MessageFormat.format(systemParamCacheManager.getValue(MotpParam.MOTP_API_REGISTER_TOKEN), systemParamCacheManager.getValue(MotpParam.MOTP_API_URL_IP));
        return sendAndReceive(rq, RegisterTokenRs.class, connectURL, HttpMethod.POST, motpLogData);
    }

    /**
     * 全景MOTP API - 取得使用者載具資訊
     * 
     * @param account
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public ViewUsersTokensRs callAPIViewUsersTokens(String account, MotpLogDataModel motpLogData) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException {
        ViewUsersTokensRq rq = new ViewUsersTokensRq();
        rq.setOpAcct(systemParamCacheManager.getValue(MotpParam.MOTP_API_ACCT));
        rq.setOpPwd(systemParamCacheManager.getValue(MotpParam.MOTP_API_PWD));
        rq.setAccount(account);
        rq.setLogMsg("16384");
        String connectURL = MessageFormat.format(systemParamCacheManager.getValue(MotpParam.MOTP_API_VIEW_USERS_TOKENS), systemParamCacheManager.getValue(MotpParam.MOTP_API_URL_IP));
        return sendAndReceive(rq, ViewUsersTokensRs.class, connectURL, HttpMethod.POST, motpLogData);
    }

    /**
     * 全景MOTP API - 刪除OTP使用者（解除綁定）
     * 
     * @param account
     * @param logMsg
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public DeleteOtpUserRs callAPIDeleteOTPUser(String account, String logMsg, MotpLogDataModel motpLogData) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException {
        DeleteOtpUserRq rq = new DeleteOtpUserRq();
        rq.setOpAcct(systemParamCacheManager.getValue(MotpParam.MOTP_API_ACCT));
        rq.setOpPwd(systemParamCacheManager.getValue(MotpParam.MOTP_API_PWD));
        rq.setAccount(account);
        rq.setDelAccount("false");
        rq.setDelOTPUser("true");
        rq.setLogMsg(logMsg);
        String connectURL = MessageFormat.format(systemParamCacheManager.getValue(MotpParam.MOTP_API_DELETE_OTP_USER), systemParamCacheManager.getValue(MotpParam.MOTP_API_URL_IP));
        return sendAndReceive(rq, DeleteOtpUserRs.class, connectURL, HttpMethod.POST, motpLogData);
    }

    /**
     * 全景MOTP API - 推播訊息
     * 
     * @param account
     * @param title
     * @param challenge
     * @param group
     * @param custInfo
     * @param appInfo
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public PushMessageRs callAPIPushMessage(String account, String title, String message, String challenge, String group, String custInfo, Map<String, Object> appInfo, MotpLogDataModel motpLogData) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException {
        PushMessageRq rq = new PushMessageRq();
        rq.setOpAcct(systemParamCacheManager.getValue(MotpParam.MOTP_API_ACCT));
        rq.setOpPwd(systemParamCacheManager.getValue(MotpParam.MOTP_API_PWD));
        rq.setAccount(account);
        rq.setTitle(title);
        rq.setInboxMessage(message);
        rq.setMode("ODSI");
        rq.setChallenge(challenge);
        rq.setFlag("1");
        rq.setGroup(group);
        rq.setCustInfo(custInfo);
        rq.setAppInfo(appInfo);
        String connectURL = MessageFormat.format(systemParamCacheManager.getValue(MotpParam.MOTP_API_PUSH_MESSAGE), systemParamCacheManager.getValue(MotpParam.MOTP_API_URL_IP));
        return sendAndReceive(rq, PushMessageRs.class, connectURL, HttpMethod.POST, motpLogData);
    }

    /**
     * 全景MOTP API - 驗證OTP
     * 
     * @param account
     * @param group
     * @param challenge
     * @param otp
     * @param logMsg
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public VerifyOtpRs callAPIVerifyOtp(String account, String group, String challenge, String otp, String logMsg, MotpLogDataModel motpLogData) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException {
        VerifyOtpRq rq = new VerifyOtpRq();
        rq.setAccount(account);
        rq.setGroup(group);
        rq.setChallenge(challenge);
        rq.setCRmode("1");
        rq.setOtp(otp);
        rq.setLogMsg(logMsg);
        rq.setFlag("0");
        String connectURL = MessageFormat.format(systemParamCacheManager.getValue(MotpParam.MOTP_API_VERIFY_OTP), systemParamCacheManager.getValue(MotpParam.MOTP_API_URL_IP));
        return sendAndReceive(rq, VerifyOtpRs.class, connectURL, HttpMethod.POST, motpLogData);
    }

    /**
     * 執行API Post請求
     * 
     * @param rq
     * @param responseType
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private <S extends MotpRs> S sendAndReceive(MotpRq rq, Class<S> responseType, String connectUrl, HttpMethod method, MotpLogDataModel motpLogData) throws ActionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        MotpLogDataEntity log = motpLogDataHelper.getMotpLogDataEntity(motpLogData);
        try {

            S rs = null;
            CustomHttpResponse response = null;

            if (method.equals(HttpMethod.GET)) {
                Map<String, Object> params = PropertyUtils.describe(rq);
                List<NameValuePair> parameters = new ArrayList<>();
                StringBuilder rqSb = new StringBuilder();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                    logger.info("MOTP Service: Get Param Key={}, Value={}", entry.getKey(), entry.getValue());
                    rqSb.append(entry.getKey());
                    rqSb.append("=");
                    rqSb.append(entry.getValue().toString());
                    rqSb.append("&");
                }
                log.setRqData(rqSb.toString());
                try {
                    motpLogDataHelper.save(log);
                }
                catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
                    logger.error("儲存失敗，不中止程序。", ex);
                }

                logger.info("MOTP Service: Call={}", connectUrl);
                response = HttpClient5.doGet(connectUrl, new ArrayList<>(), parameters, CONNECTION_TIMEOUT);

            }
            else {
                String payload = JsonUtils.getJson(rq);
                log.setRqData(payload);
                try {
                    motpLogDataHelper.save(log);
                }
                catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
                    logger.error("儲存失敗，不中止程序。", ex);
                }
                logger.info("MOTP Service: Call={}, Payload={}", connectUrl, payload);
                List<Header> headers = Arrays.asList(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
                response = HttpClient5.doPost(connectUrl, headers, payload, CONNECTION_TIMEOUT);
            }

            int statusCode = response.getCode();
            String rsPayload = StringUtils.defaultString(response.getResponse());
            log.setRsData(rsPayload);

            logger.info("MOTP Service: StatusCode={}, Payload={}", statusCode, rsPayload);

            if (StringUtils.isNotBlank(rsPayload)) {
                rs = JsonUtils.getObject(rsPayload, responseType);
            }
            else {
                throw ExceptionUtils.getActionException(AIBankErrorCode.MOTP_API_SERVICE_ERROR);
            }
            log.setStatus("SUCCESS");
            return rs;
        }
        catch (ActionException ex) {
            log.setStatus("FAIL");
            log.setStatusDesc(ex.getLocalizedMessage());
            throw ex;
        }
        finally {
            log.setUpdateTime(new Date());
            try {
                motpLogDataHelper.save(log);
            }
            catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
                logger.error("儲存失敗，不中止程序。", ex);
            }
        }
    }

}