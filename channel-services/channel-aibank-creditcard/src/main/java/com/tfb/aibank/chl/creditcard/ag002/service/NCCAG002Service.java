package com.tfb.aibank.chl.creditcard.ag002.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.creditcard.resource.dto.RecommendInfo;
import com.tfb.aibank.chl.creditcard.resource.dto.RecommendInfoRes;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002DataOutput;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW213RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW302RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CardEmailBillModel;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.TxSourceType;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)NCCAG002Service.java
 * 
 * <p>Description:信用卡電子帳單設定 service</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/01, Aaron	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCAG002Service extends NCCService {

    public static final String CACHE_KEY = "NCCAG002_CACHE_KEY";

    /**
     * 發查信用卡卡片總覽電文
     * 
     * @param custid
     * @param dataOutput
     * @throws ActionException
     */
    public void sendCEW302R(String custid, NCCAG002DataOutput dataOutput) throws ActionException {

        BaseServiceResponse<CEW302RRes> res = creditCardResource.sendCEW302R(custid);
        if (res.getStatus().isError()) {
            throw ExceptionUtils.getActionException(res.getStatus());
        }

        if (res.getData() != null) {
            dataOutput.setCew302RResponse(res.getData());
        }
    }

    public void saveCancelCardEmailBill(AIBankUser user, NCCAG002DataOutput dataOutput) {
        Date now = new Date();
        CardEmailBillModel model = new CardEmailBillModel();
        model.setCompanyKey(user.getCompanyKey());
        model.setNameCode(user.getNameCode());
        model.setUserkey(user.getUserKey());
        model.setUserId(user.getUserId());
        model.setTxKind("1"); // 信用卡電子帳單-終止
        model.setEmailBillFlag("1"); // 電子帳單取消
        model.setBillFlag("0"); // 實體帳單申請
        model.setTxDate(now);
        model.setCreateTime(now);
        model.setTxScource(TxSourceType.AI_BANK.getCode()); // AI Bank
        model.setTxStatus(TxStatusType.PROCESS.getCode());
        model.setUpdateTime(now);
        // 設定推薦碼相關
        if( dataOutput.getRecommendInfo() != null){
            RecommendInfo recommend = dataOutput.getRecommendInfo();
            model.setRecommodType(recommend.getType());
            model.setRecommodCompany(recommend.getCompany());
            model.setRecommodPathNo(recommend.getPathNo());
            model.setRecommodIdNo(recommend.getIdNo());
            model.setRecommodStatus("2"); // 無效推薦
        }
        BaseServiceResponse<CardEmailBillModel> result = creditCardResource.saveCardEmailBill(model);

        dataOutput.setCancelResultModel(result.getData());

    }

    public void saveApplyCardEmailBill(AIBankUser user, NCCAG002DataOutput dataOutput) {
        Date now = new Date();
        CardEmailBillModel model = new CardEmailBillModel();
        model.setCompanyKey(user.getCompanyKey());
        model.setNameCode(user.getNameCode());
        model.setUserkey(user.getUserKey());
        model.setUserId(user.getUserId());
        model.setTxKind("0"); // 信用卡電子帳單申請
        model.setEmailBillFlag("0"); // 電子帳單申請
        model.setBillFlag("1"); // 實體帳單取消
        model.setTxDate(now);
        model.setCreateTime(now);
        model.setTxScource(TxSourceType.AI_BANK.getCode()); // AI Bank
        model.setTxStatus(TxStatusType.PROCESS.getCode());
        model.setUpdateTime(now);
        // 設定推薦碼相關
        if( dataOutput.getRecommendInfo() != null){
            RecommendInfo recommend = dataOutput.getRecommendInfo();
            model.setRecommodType(recommend.getType());
            model.setRecommodCompany(recommend.getCompany());
            model.setRecommodPathNo(recommend.getPathNo());
            model.setRecommodIdNo(recommend.getIdNo());
            model.setRecommodStatus(recommend.getStatus()); // 新增才設定  推薦碼成功/失敗
        }
        BaseServiceResponse<CardEmailBillModel> result = creditCardResource.saveCardEmailBill(model);

        dataOutput.setCancelResultModel(result.getData());

    }

    public void saveCardEmailBillResult(AIBankUser user, CardEmailBillModel model, boolean isSucess, ActionException error, NCCAG002DataOutput dataOutput) {
        Date now = new Date();
        model.setUpdateTime(now);
        model.setHostTxTime(now);
        if (error != null) {
            model.setTxStatus(TxStatusType.FAIL.getCode());
            model.setTxErrorCode(error.getErrorCode());
            model.setTxErrorSystemId(error.getSystemId());
            model.setTxErrorDesc(error.getErrorDesc());
            // 主體設定 fail 且為新增設定 才壓推薦碼失敗
            if(dataOutput.getRecommendInfo() != null && StringUtils.equals(model.getTxKind(),"0"))
                model.setRecommodStatus("0");
        }
        else if (isSucess) {
            // 成功
            model.setTxStatus(TxStatusType.SUCCESS.getCode());
        }
        else {
            model.setTxStatus(TxStatusType.FAIL.getCode());
            // 主體設定 fail 且為新增設定 才壓推薦碼失敗
            if(dataOutput.getRecommendInfo() != null && StringUtils.equals(model.getTxKind(),"0"))
                model.setRecommodStatus("0");
        }

        creditCardResource.saveCardEmailBill(model);
    }

    public void sendCEW213R(AIBankUser user, String custEMLF, String custBILL, NCCAG002DataOutput output) throws ActionException {

        BaseServiceResponse<CEW213RResponse> response = creditCardResource.sendCEW213R(user.getCustId(), custEMLF, custBILL);

        if (response.getStatus().isError()) {
            throw ExceptionUtils.getActionException(response.getStatus());
        }

        if (response.getData() != null) {
            // 成功
            output.setSend213RSuccess(Constants.STATUS_CODE_SUCCESS.equals(response.getData().getAbndCode()));
        }
    }

    // 取得推薦短網址實際推薦內容
    public RecommendInfo getShortenerParams(String urlCode){
        RecommendInfoRes res = creditCardResource.getShortenerParams(urlCode);
        RecommendInfo recommendInfo = new RecommendInfo();
        if (res.isSuccess() && res.getData() != null) {
            RecommendInfoRes.DataBody body = res.getData();
            recommendInfo.setType(body.getRecommender().getType());
            recommendInfo.setCompany(body.getRecommender().getBu());
            recommendInfo.setPathNo(body.getRecommender().getScCode());
            recommendInfo.setIdNo(body.getRecommender().getIdNo());
            recommendInfo.setStatus("1");
        } else {
            // 依 errorData 做處理
            recommendInfo.setStatus("0");  // 失敗
        }
        return recommendInfo;
    }

    public void buildFailEmailParams(ActionException err, NCCAG002DataOutput output) {
        // 產生Email通知的內容
        Map<String, String> params = new HashMap<>();
        // 失敗
        params.put("status", TxStatusType.FAIL.getCode());
        params.put("content", I18NUtils.getMessage("nccag002.notification.email.fail.content"));
        params.put("tableTitle", I18NUtils.getMessage("nccag002.notification.email.tableTitle"));
        // 交易結果
        Object[] errParam = { err.getErrorCode() + " " + err.getErrorDesc() };
        params.put("result", I18NUtils.getMessage("nccag002.notification.email.fail.result", errParam));
        // 交易時間
        params.put("time", DateUtils.getDateTimeStr(new Date()));

        // 信用卡電子帳單申請結果通知
        String title = I18NUtils.getMessage("nccag002.notification.email.subject");
        params.put("title", title);
        params.put("remark1", I18NUtils.getMessage("nccag002.notification.email.remark1"));
        params.put("remark2", I18NUtils.getMessage("nccag002.notification.email.remark2"));
        output.setParams(params);
    }

    public void buildEmailParams(NCCAG002DataOutput output) {

        // 產生Email通知的內容
        Map<String, String> params = new HashMap<>();
        // 成功
        params.put("status", TxStatusType.SUCCESS.getCode());
        params.put("content", I18NUtils.getMessage("nccag002.notification.email.success.content"));
        params.put("tableTitle", I18NUtils.getMessage("nccag002.notification.email.tableTitle"));
        // 交易結果
        params.put("result", I18NUtils.getMessage("nccag002.notification.email.success.result"));
        // 交易時間
        params.put("time", DateUtils.getDateTimeStr(new Date()));

        // 信用卡電子帳單申請結果通知
        String title = I18NUtils.getMessage("nccag002.notification.email.subject");
        params.put("title", title);
        params.put("remark1", I18NUtils.getMessage("nccag002.notification.email.remark1"));
        params.put("remark2", I18NUtils.getMessage("nccag002.notification.email.remark2"));
        output.setParams(params);

    }

    public void buildUnknownEmailParams(NCCAG002DataOutput output) {

        // 產生Email通知的內容
        Map<String, String> params = new HashMap<>();

        params.put("status", TxStatusType.SUCCESS.getCode());
        params.put("content", I18NUtils.getMessage("nccag002.notification.email.unknown.content"));
        params.put("tableTitle", I18NUtils.getMessage("nccag002.notification.email.tableTitle"));
        // 交易結果
        params.put("result", I18NUtils.getMessage("nccag002.notification.email.unknown.result"));
        // 交易時間
        params.put("time", DateUtils.getDateTimeStr(new Date()));

        // 信用卡電子帳單申請結果通知
        String title = I18NUtils.getMessage("nccag002.notification.email.subject");
        params.put("title", title);
        params.put("remark1", I18NUtils.getMessage("nccag002.notification.email.remark1"));
        params.put("remark2", I18NUtils.getMessage("nccag002.notification.email.remark2"));
        output.setParams(params);

    }
}
