/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.security.services.fido.bean.FidoDoRevokeRequestBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoDoRevokeResponseBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoLoginRequestBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoLoginResponseBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoLoginResponseContentBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoPublicKeyRequestBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoPublicKeyResponseBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoQueryLogRequestBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoQueryLogResponseBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoQueryLogResponseContentBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoQueryVerifyResultRequestBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoQueryVerifyResultResponseBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoQueryVerifyResultResponseContentBean;
import com.tfb.aibank.biz.security.services.fido.model.DoRevokeRequest;
import com.tfb.aibank.biz.security.services.fido.model.DoRevokeResponse;
import com.tfb.aibank.biz.security.services.fido.model.LoginRequest;
import com.tfb.aibank.biz.security.services.fido.model.LoginResponse;
import com.tfb.aibank.biz.security.services.fido.model.QueryLogResponse;
import com.tfb.aibank.biz.security.services.fido.model.QueryLogResponseRepeat;
import com.tfb.aibank.biz.security.services.fido.model.QueryVerifyResultRequest;
import com.tfb.aibank.biz.security.services.fido.model.QueryVerifyResultResponse;
import com.tfb.aibank.biz.security.services.fido.proxy.FidoServiceClient;
import com.tfb.aibank.common.code.AIBankConstants;

/**
 * @author john
 *
 */
@Component
public class FidoServiceHelper {

    private final IBLog logger = IBLog.getLog(FidoServiceHelper.class);

    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;

    @Autowired
    FidoServiceClient fidoServiceClient;

    @Autowired
    FidoSequenceProvider fidoSequenceProvider;

    private String clientId = "FIDO_CLIENT_ID";

    private String businessNo = "FIDO_BUSINESS_NO";

    private String publicKey;

    /**
     * Login
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public LoginResponse login(LoginRequest request) throws ActionException {

        initParam();

        LoginResponse response = new LoginResponse();
        response.setStatus("1");

        FidoLoginRequestBean rq = new FidoLoginRequestBean();
        FidoLoginResponseBean rs = null;
        Map<String, String> params = new HashMap<>();

        String memberNo = encrypt(fixCustId(request.getCustId()));
        String verifyNo = getVerifyNo();

        params.put("clientId", clientId);
        params.put("businessNo", businessNo);
        params.put("memberNo", memberNo);
        params.put("verifyNo", verifyNo);
        params.put("action", request.getAction().toUpperCase());
        rq.setParams(params);

        rs = fidoServiceClient.post(rq, FidoLoginResponseBean.class);

        if (rs == null) {
            logger.error("login Response is null");
            return response;
        }
        logger.info("====================> login RS={}, {}, {}", rs.getTxid(), rs.getStatus(), rs.getContent());

        FidoLoginResponseContentBean fidoLoginResponseContentBean = JsonUtils.getObject(rs.getContent(), FidoLoginResponseContentBean.class);
        if (fidoLoginResponseContentBean != null && fidoLoginResponseContentBean.getFidoOutputParams() != null) {
            response.setData(fidoLoginResponseContentBean.getFidoOutputParams().getData());
            response.setToken(fidoLoginResponseContentBean.getToken());
        }

        if (rs.getStatus() == null) {
            logger.error("Login Response is null");
            return response;
        }

        response.setVerifyNo(verifyNo);
        response.setTxid(rs.getTxid());
        response.setContent(rs.getContent());
        response.setStatus(rs.getStatus());
        response.setError(rs.getError());
        response.setError_description(rs.getError_description());

        return response;
    }

    /**
     * QueryVerifyResult
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public QueryVerifyResultResponse queryVerifyResult(QueryVerifyResultRequest request) throws ActionException {

        initParam();

        QueryVerifyResultResponse response = new QueryVerifyResultResponse();
        response.setStatus("1");

        FidoQueryVerifyResultRequestBean rq = new FidoQueryVerifyResultRequestBean();
        FidoQueryVerifyResultResponseBean rs = null;
        Map<String, String> params = new HashMap<>();

        String memberNo = encrypt(fixCustId(request.getCustId()));

        params.put("clientId", clientId);
        params.put("businessNo", businessNo);
        params.put("memberNo", memberNo);
        params.put("verifyNo", request.getVerifyNo());
        params.put("token", request.getToken());
        rq.setParams(params);

        rs = fidoServiceClient.post(rq, FidoQueryVerifyResultResponseBean.class);

        if (rs == null) {
            logger.error("queryVerifyResult Response is null");
            return response;
        }

        if (rs.getStatus() == null) {
            logger.error("QueryVerifyResult Response status is null");
            return response;
        }

        FidoQueryVerifyResultResponseContentBean queryVerifyResultResponse = JsonUtils.getObject(rs.getContent(), FidoQueryVerifyResultResponseContentBean.class);
        if (queryVerifyResultResponse == null) {
            logger.error("QueryVerifyResult Response Content is null");
            return response;
        }

        response.setTxid(rs.getTxid());
        response.setContent(rs.getContent());
        response.setStatus(rs.getStatus());
        response.setError(rs.getError());
        response.setError_description(rs.getError_description());
        response.setAction(queryVerifyResultResponse.getAction());
        response.setSelectType(queryVerifyResultResponse.getSelectType());
        response.setVerifyTime(queryVerifyResultResponse.getVerifyTime());
        response.setPlaintext(queryVerifyResultResponse.getPlaintext());
        response.setVerifyCode(queryVerifyResultResponse.getFidoParams().getVerifyCode());
        response.setVerifyMsg(queryVerifyResultResponse.getFidoParams().getVerifyMsg());

        return response;
    }

    /**
     * DO-REVOKE API
     * 
     * https://[domain]/tpfb-idac-sso/fido/do-revoke
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public DoRevokeResponse doRevoke(DoRevokeRequest request) throws ActionException {

        initParam();

        DoRevokeResponse response = new DoRevokeResponse();

        FidoDoRevokeRequestBean rq = new FidoDoRevokeRequestBean();
        FidoDoRevokeResponseBean rs = null;
        Map<String, String> params = new HashMap<>();

        params.put("clientId", clientId);
        params.put("businessNo", businessNo);
        params.put("verifyNo", getVerifyNo());
        params.put("keyId", request.getKeyId());
        rq.setParams(params);

        rs = fidoServiceClient.post(rq, FidoDoRevokeResponseBean.class);

        if (rs == null) {
            logger.error("doRevoke Response is null");
            return response;
        }

        if (rs.getStatus() == null) {
            logger.error("Do-Revoke Response is null");
            return response;
        }

        response.setTxid(rs.getTxid());
        response.setContent(rs.getContent());
        response.setStatus(rs.getStatus());
        response.setError(rs.getError());
        response.setError_description(rs.getError_description());
        return response;
    }

    /**
     * QUERY-LOG API
     * 
     * https://[domain]/tpfb-idac-sso/fido/query-log
     * 
     * @param userId
     * @return
     * @throws ActionException
     */
    public QueryLogResponse queryLog(String userId) throws ActionException {

        initParam();

        QueryLogResponse response = new QueryLogResponse();
        response.setStatus("1");

        FidoQueryLogRequestBean rq = new FidoQueryLogRequestBean();
        FidoQueryLogResponseBean rs = null;
        Map<String, String> params = new HashMap<>();

        String memberNo = encrypt(fixCustId(userId));

        params.put("clientId", clientId);
        params.put("businessNo", businessNo);
        params.put("memberNo", memberNo);
        params.put("dataStatus", "valid");

        rq.setParams(params);

        rs = fidoServiceClient.post(rq, FidoQueryLogResponseBean.class);

        if (rs == null) {
            logger.error("Query Log Response is null");
            return response;
        }
        logger.info("====================> queryLog RS={}, {}, {}", rs.getTxid(), rs.getStatus(), rs.getContent());

        if (rs.getStatus() == null) {
            logger.error("Query Log Response Status is null");
            return response;
        }

        if (StringUtils.isNoneBlank(rs.getError_description())) {
            logger.error("FIDO API ERROR", rs.getError_description());
        }

        String data = rs.getContent();
        List<String> datas = split(data);
        List<QueryLogResponseRepeat> responseData = new ArrayList<>();
        for (String m : datas) {
            FidoQueryLogResponseContentBean fidoQueryLogResponseContentBean = JsonUtils.getObject(m, FidoQueryLogResponseContentBean.class);
            if (fidoQueryLogResponseContentBean == null)
                continue;

            if (fidoQueryLogResponseContentBean.getFidoKeyId() == null) {
                continue;
            }
            QueryLogResponseRepeat one = new QueryLogResponseRepeat();
            one.setBusiNo(fidoQueryLogResponseContentBean.getBusiNo());
            one.setCreatedTime(fidoQueryLogResponseContentBean.getCreatedTime());
            one.setDeviceId(fidoQueryLogResponseContentBean.getDeviceId());
            one.setDeviceName(fidoQueryLogResponseContentBean.getDeviceName());
            one.setDeviceOs(fidoQueryLogResponseContentBean.getDeviceOs());
            one.setFidoKeyId(fidoQueryLogResponseContentBean.getFidoKeyId());
            one.setInvalid(fidoQueryLogResponseContentBean.getInvalid());
            one.setMobileOid(fidoQueryLogResponseContentBean.getMobileOid());
            one.setUserId(fidoQueryLogResponseContentBean.getUserId());
            responseData.add(one);
        }
        response.setData(responseData);
        response.setTxid(rs.getTxid());
        response.setStatus("0");
        response.setError(rs.getError());
        response.setError_description(rs.getError_description());

        return response;
    }

    private List<String> split(String data) {
        List<String> datas = new ArrayList<String>();

        if (data == null)
            return datas;

        String one = "";
        boolean isAdd = false;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '[')
                continue;

            if (data.charAt(i) == '{') {
                isAdd = true;
            }
            if (isAdd) {
                one += data.charAt(i);
            }

            if (data.charAt(i) == '}') {
                isAdd = false;
                datas.add(one);
                one = "";
            }
        }

        return datas;
    }

    private void getPublicKey() {
        FidoPublicKeyRequestBean rq = new FidoPublicKeyRequestBean();
        FidoPublicKeyResponseBean rs = null;

        Map<String, String> params = new HashMap<>();

        params.put("clientId", clientId);

        rq.setApi(rq.getApi() + "?clientId=" + clientId);
        rq.setParams(params);

        try {
            rs = fidoServiceClient.post(rq, FidoPublicKeyResponseBean.class);
        }
        catch (ActionException ex) {
            logger.error(ex.getMessage(), ex);
        }

        if (rs == null) {
            return;
        }

        if (rs.getStatus() == null) {
            logger.error("Query Log Response is null");
            return;
        }
        publicKey = rs.getPublicKey();

        logger.info("publicKey={}", publicKey);
    }

    /**
     * 加密 ID
     * 
     * @param userId
     * @return
     */
    private String encrypt(String userId) {
        if (publicKey == null) {
            getPublicKey();
        }
        return RSAUtils.encrypy(userId, publicKey);
    }

    private void initParam() {
        clientId = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_CLIENT_ID");
        businessNo = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_BUSINESS_NO");
        fidoServiceClient.init(systemParamCacheManager);
    }

    /**
     * 系統代碼(3) + 交易代碼(4) + 日期 (YYYYMMDDHH24MISS) + 流水號(8)
     * 
     * @return
     */
    private String getVerifyNo() {
        // 系統代碼(3) + 交易代碼(4) + 日期 (YYYYMMDDHH24MISS) + 流水號(8)
        // AIBANK + FIDO + 日期 (YYYYMMDDHH24MISS) + 流水號(8):[FIDO_FIDO_SEQ])
        return "AIBANK" + "FIDO" + DateFormatUtils.SIMPLE_DATETIME_FORMAT.format(new Date()) + fidoSequenceProvider.getNextSeq();
    }

    private String fixCustId(String custId) {
        if (custId != null && custId.length() == 11) {
            return custId.substring(0, 10);
        }
        return custId;
    }
}
