package com.tfb.aibank.chl.creditcard.qu008.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu008.cache.NCCQU008CacheData;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008060Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008060Rs;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008BilledDetailVo;
import com.tfb.aibank.chl.creditcard.qu008.service.NCCQU008Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4150RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4151RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4152RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4153RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW221RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW317RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CardBillPeriodApplyModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CardStagesApplyModel;
import com.tfb.aibank.common.model.TxnResult;
import com.tfb.aibank.common.type.MailParamType;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)NCCQU008060Task.java
 * 
 * <p>Description:信用卡分期管理 060 結果頁</p>
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
public class NCCQU008060Task extends AbstractAIBankBaseTask<NCCQU008060Rq, NCCQU008060Rs> {

    @Autowired
    private NCCQU008Service service;

    @Override
    public void validate(NCCQU008060Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU008060Rq rqData, NCCQU008060Rs rsData) throws ActionException {
        NCCQU008CacheData cache = getCache(NCCQU008Service.CACHE_KEY, NCCQU008CacheData.class);

        Map<String, String> emailParamsMap = new HashMap<>();

        if (Boolean.TRUE.equals(cache.getIsBillProcess())) {
            CEW317RResponse cew317RRs = new CEW317RResponse();
            CardBillPeriodApplyModel cardBillPeriodApplyModel = new CardBillPeriodApplyModel();
            service.createCardBillPeriodApplyModel(getLoginUser(), ConvertUtils.str2Int(cache.getPeriod()), cache.getInstallmentBillRs().getBillYyyymm(), getClientIp(), cardBillPeriodApplyModel);
            service.billInstallmentApply(getLoginUser(), cache.getPeriod(), cache.getInstallmentBillRs().getBillYyyymm(), getLocale(), cardBillPeriodApplyModel, cew317RRs, getAccessLog());
            service.updateCardBillPeriodApplyModel(cardBillPeriodApplyModel);
            TxnResult txnResult = createTxnResult(cardBillPeriodApplyModel.getTxStatus(), new Date(), cardBillPeriodApplyModel.getTxErrorSystemId(), cardBillPeriodApplyModel.getTxErrorCode(), cardBillPeriodApplyModel.getTxErrorDesc());
            genBilledProcessEmailMap(emailParamsMap, cache, txnResult, cew317RRs, cardBillPeriodApplyModel);
            String txnStatusDesc = StringUtils.equals(txnResult.getTxStatus(), "0") ? I18NUtils.getMessage("nccqu008.txnresult.success") : I18NUtils.getMessage("nccqu008.txnresult.fail");

            // 是否進行「任務牆任務更新」
            rsData.setExecUpdateMissionWall(StringUtils.equals(txnResult.getTxStatus(), TxStatusType.SUCCESS.getCode()));

            rsData.setInstallmentRate(cache.getInstallmentRate());

            if (Objects.nonNull(cardBillPeriodApplyModel.getAex())) {
                throw cardBillPeriodApplyModel.getAex();
            }
            else {
                // 2023/12/06 Ice與客戶確認 失敗不發email
                sendTxnResultMail(I18NUtils.getMessage("nccqu008.email.bill.subject.desc", txnStatusDesc), getLoginUser().getEmail(), emailParamsMap);
            }

            // 分期總額
            if (CollectionUtils.isNotEmpty(cache.getInterestCalResults())) {

                String captial = cache.getInterestCalResults().get(1).getTotal();
                rsData.setInstallmentSumAmt(captial);
            }
        }
        else {

            CardStagesApplyModel cardStagesApplyModel = new CardStagesApplyModel();
            service.createCardStagesApply(getLoginUser(), cache, cache.getBilledDetailSelect(), ConvertUtils.str2Int(cache.getPeriod()), getClientIp(), cardStagesApplyModel);
            if (Boolean.TRUE.equals(cache.getBilledDetailSelect().getIsPayBill())) {
                CEW221RResponse cew221RRs = new CEW221RResponse();
                service.singleInstallmentApplyHasBill(getLoginUser(), getAccessLog(), cache.getPeriod(), getLocale(), cache.getBilledDetailSelect(), cew221RRs, cardStagesApplyModel);
            }
            else {
                if (StringUtils.equals("CE4152R", cache.getBilledDetailSelect().getHtxtId())) {
                    CE4152RResponse ce4152RRs = new CE4152RResponse();
                    service.checkAndDetermineTaxApplyNoBill(getLoginUser(), cache, cache.getPeriod(), getLocale(), cache.getBilledDetailSelect(), ce4152RRs, cardStagesApplyModel, getAccessLog());
                }
                else if (StringUtils.equals("CE4151R", cache.getBilledDetailSelect().getHtxtId())) {
                    CE4151RResponse ce4151RRs = new CE4151RResponse();
                    service.tuitionFeeApplyNoBill(getLoginUser(), cache, cache.getPeriod(), getLocale(), cache.getBilledDetailSelect(), ce4151RRs, cardStagesApplyModel, getAccessLog());
                }
                else if (StringUtils.equals("CE4150R", cache.getBilledDetailSelect().getHtxtId())) {
                    CE4150RResponse ce4150RRs = new CE4150RResponse();
                    service.comprehensiveTaxApplyNoBill(getLoginUser(), cache, cache.getPeriod(), getLocale(), cache.getBilledDetailSelect(), ce4150RRs, cardStagesApplyModel, getAccessLog());
                }
                else {
                    CE4153RResponse ce4153RRs = new CE4153RResponse();
                    service.singleInstallmentApplyNoBill(getLoginUser(), cache, cache.getPeriod(), getLocale(), cache.getBilledDetailSelect(), ce4153RRs, cardStagesApplyModel, getAccessLog());
                }
            }
            service.updateCardStagesApplyModel(cardStagesApplyModel);
            TxnResult txnResult = createTxnResult(cardStagesApplyModel.getTxStatus(), new Date(), cardStagesApplyModel.getTxErrorSystemId(), cardStagesApplyModel.getTxErrorCode(), cardStagesApplyModel.getTxErrorDesc());
            genSingleProcessEmailMap(emailParamsMap, cache.getPeriod(), cache, txnResult, cache.getBilledDetailSelect(), cardStagesApplyModel);
            String txnStatusDesc = StringUtils.equals(txnResult.getTxStatus(), TxStatusType.SUCCESS.getCode()) ? I18NUtils.getMessage("nccqu008.txnresult.success") : I18NUtils.getMessage("nccqu008.txnresult.fail");

            // 是否進行「任務牆任務更新」
            rsData.setExecUpdateMissionWall(StringUtils.equals(txnResult.getTxStatus(), TxStatusType.SUCCESS.getCode()));

            if (Objects.nonNull(cardStagesApplyModel.getAex())) {
                throw cardStagesApplyModel.getAex();
            }
            else {
                // 2023/12/06 Ice與客戶確認 失敗不發email
                sendTxnResultMail(I18NUtils.getMessage("nccqu008.email.single.subject.desc", txnStatusDesc), getLoginUser().getEmail(), emailParamsMap);
            }
        }

        rsData.setIsBillProcess(cache.getIsBillProcess());
        rsData.setInstallmentDataPeriod(cache.getInstallmentDataPeriod());
        // #8083 NCCQU008_信用卡帳單分期確認頁英文版移除部分標題
        if (StringUtils.equals(getLocale().toString(), Locale.TAIWAN.toString()) || !Boolean.TRUE.equals(cache.getIsBillProcess())) {
            rsData.setInstallmentData(cache.getInstallmentData());
        }
        rsData.setInstallmentDesc(cache.getInstallmentDesc());
        rsData.setInstallmentAmt(cache.getInstallmentAmt());
        rsData.setCardDesc(cache.getCardDesc());
        rsData.setInstallmentRate(cache.getInstallmentRate());
        rsData.setPurchaseDay(cache.getPurchaseDay());
        rsData.setNccDay(cache.getNccDay());
        service.getCardEmail(getLoginUser().getCustId(), rsData);
    }

    /**
     * 帳單流程Email參數建立
     * 
     * @param emailParamsMap
     * @param period
     * @param cache
     * @param txnResult
     * @param billedDetail
     * @param cardBillPeriodApplyModel
     */
    private void genBilledProcessEmailMap(Map<String, String> emailParamsMap, NCCQU008CacheData cache, TxnResult txnResult, CEW317RResponse cew317RRs, CardBillPeriodApplyModel cardBillPeriodApplyModel) {
        emailParamsMap.put(MailParamType.TEMPLATE_NAME.getCode(), "NCCQU008_BILLEDPROCESS");
        emailParamsMap.put(MailParamType.TXN_NAME.getCode(), I18NUtils.getMessage("nccqu008.email.billed_installment.desc"));
        emailParamsMap.put("hostTime", DateUtils.getDateTimeStr(new Date()));
        emailParamsMap.put("status", txnResult.getTxStatus());
        String txnResultTmp = "";
        if (StringUtils.equals(txnResult.getTxStatus(), "0")) {
            txnResultTmp = I18NUtils.getMessage("nccqu008.txn_result.email.success.desc");
        }
        else {
            txnResultTmp = I18NUtils.getMessage("nccqu008.txn_result.email.fail.desc", cardBillPeriodApplyModel.getTxErrorCode(), cardBillPeriodApplyModel.getTxErrorDesc());
        }

        emailParamsMap.put("txnResult", txnResultTmp);

        emailParamsMap.put("billedTime", StringUtils.left(DateUtils.getCEDateStr(cew317RRs.getBillYyyymm()), 7));
        emailParamsMap.put("billPayDate", DateUtils.getCEDateStr(cew317RRs.getBillCycleDay()));
        emailParamsMap.put("purchaseLastDay", DateUtils.getCEDateStr(cew317RRs.getPaymentDeadline()));
        emailParamsMap.put("statusApply", I18NUtils.getMessage("nccqu008.email.apply.desc", ConvertUtils.str2Int(txnResult.getTxStatus())));
        emailParamsMap.put("insRate", ConvertUtils.bigDecimal2Str(cew317RRs.getInterestRate(), 2));
        emailParamsMap.put("insPeriod", cache.getPeriodDesc());
    }

    /**
     * 單筆流程Email參數建立
     * 
     * @param emailParamsMap
     * @param cew317RRs
     * @param cardBillPeriodApplyModel
     */
    private void genSingleProcessEmailMap(Map<String, String> emailParamsMap, String period, NCCQU008CacheData cache, TxnResult txnResult, NCCQU008BilledDetailVo billedDetail, CardStagesApplyModel cardStagesApplyModel) {
        emailParamsMap.put(MailParamType.TEMPLATE_NAME.getCode(), "NCCQU008_SINGLEPROCESS");
        emailParamsMap.put(MailParamType.TXN_NAME.getCode(), I18NUtils.getMessage("nccqu008.email.single_installment.desc"));
        emailParamsMap.put("hostTime", DateUtils.getDateTimeStr(new Date()));
        emailParamsMap.put("status", txnResult.getTxStatus());
        String txnResultTmp = "";
        if (StringUtils.equals(txnResult.getTxStatus(), "0")) {
            txnResultTmp = I18NUtils.getMessage("nccqu008.txn_result.email.success.desc");
        }
        else {
            txnResultTmp = I18NUtils.getMessage("nccqu008.txn_result.email.fail.desc", cardStagesApplyModel.getTxErrorCode(), cardStagesApplyModel.getTxErrorDesc());
        }

        emailParamsMap.put("txnResult", txnResultTmp);
        emailParamsMap.put("purchaseDate", billedDetail.getPchDay());

        emailParamsMap.put("creditDate", DateUtils.getCEDateStr(billedDetail.getCreditDate()));
        emailParamsMap.put("purchaseDesc", billedDetail.getBillDesc());
        emailParamsMap.put("creditIdentifierLastFour", billedDetail.getCardNxLastFour());
        emailParamsMap.put("fxCur", billedDetail.getFxCur());
        emailParamsMap.put("fxAmt", IBUtils.formatMoney(billedDetail.getFxAmt(), IBUtils.getMoneyScale(billedDetail.getFxCur()), "$"));

        emailParamsMap.put("twdAmt", IBUtils.formatMoney(cache.getPurchaseAmt(), 0, "$"));
        emailParamsMap.put("insPeriod", I18NUtils.getMessage("nccqu008.email.period.desc", period));
        emailParamsMap.put("insYearRate", ConvertUtils.bigDecimal2Str(cardStagesApplyModel.getFeeRate(), 2) + "%");

    }
}
