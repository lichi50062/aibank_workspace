package com.tfb.aibank.chl.creditcard.qu008.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu008.cache.NCCQU008CacheData;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008010Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008010Rs;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008BillNotIntallmentsDisplay;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008BilledDetailVo;
import com.tfb.aibank.chl.creditcard.qu008.service.NCCQU008Service;
import com.tfb.aibank.chl.creditcard.qu008.type.NCCQU008StatusType;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW343RRepeat;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

// @formatter:off
/**
 * @(#)NCCQU008010Task.java
 * 
 * <p>Description:信用卡分期管理 010 功能首頁</p>
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
public class NCCQU008010Task extends AbstractAIBankBaseTask<NCCQU008010Rq, NCCQU008010Rs> {
    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private NCCQU008Service service;

    @Override
    public void validate(NCCQU008010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU008010Rq rqData, NCCQU008010Rs rsData) throws ActionException {
        NCCQU008CacheData cache = new NCCQU008CacheData();

        userDataCacheService.validateCreditCardStatus(getLoginUser());
        // 附卡人引導至「共通錯誤頁」
        if (!userDataCacheService.isPrimaryCard(getLoginUser(), getLocale())) {
            throw new ActionException(ErrorCode.PRIMARY_CARDHOLDER_ONLY);
        }

        cache.setInstallmentType(rqData.getType());
        // {分期類型}有值
        if (StringUtils.isNotEmpty(rqData.getType())) {
            rsData.setType(rqData.getType());
            // i. 若{分期類型}為帳單分期，導至「分期注意事項頁」。
            if (NCCQU008Service.BILL_TYPE.equals(rqData.getType())) {
                rsData.setTargetPagePath("020");
            }
            else {
                service.getAgreementFlag(getLoginUser(), cache, "5", "");
                if (Boolean.TRUE.equals(cache.getAgreementFlag())) {
                    // 都有值
                    if (!service.isRqTxnDataAnyEmpty(rqData.getTxnData())) {
                        // 若已簽訂約定書，導至「方案選擇頁」，並執行單筆分期流程
                        rsData.setTargetPagePath("040");
                        // 直接到方案選擇頁要做資料處理
                        service.getBilledDetails(rsData, getLoginUser(), rqData.getTxnData(), getLocale());
                    }
                    else {
                        // 若未簽訂約定書，導至「條款頁」，並執行單筆分期流程
                        rsData.setTargetPagePath("030");
                    }
                }
                else {
                    // 若未簽訂約定書，導至「條款頁」，並執行單筆分期流程
                    rsData.setTargetPagePath("021");
                }

            }
        }
        else {
            getTxnData(cache, rsData, rqData.getReSearchFlag());
            service.getAgreementFlag(getLoginUser(), cache, "5", "");
            rsData.setIsAgreement(cache.getAgreementFlag());
            rsData.setTxnTabDefault(rqData.getTxnTabDefault());
        }

        setCache(NCCQU008Service.CACHE_KEY, cache);
    }

    /**
     * 取得交易資料
     * 
     * @param user
     * @param cache
     * @param rsData
     * @throws ActionException
     */
    private void getTxnData(NCCQU008CacheData cache, NCCQU008010Rs rsData, String reSearchFlag) throws ActionException {
        List<CreditCard> effectiveCreditCards = userDataCacheService.getEffectiveCreditCards(getLoginUser(), getLocale());
        if (StringUtils.isBlank(reSearchFlag) || StringUtils.equals(reSearchFlag, "1")) {
            try {
                service.queryInstallmentTxn(getLoginUser(), cache);
            }
            catch (ServiceException ex) {
                rsData.setQueryStatus(NCCQU008StatusType.QUERY_ERROR.getMemo());
            }
            if (CollectionUtils.isEmpty(cache.getInstallmentTxnRs().getRepeats())) {

                rsData.setQueryStatus(NCCQU008StatusType.QUERY_NODATA.getMemo());
            }
            else {
                rsData.setQueryStatus(NCCQU008StatusType.QUERY_SUCCESS.getMemo());
            }

            // 查 CEW343R單筆分期查詢交易
            service.getSingleIntallments(effectiveCreditCards, cache, rsData);

            // 取CEW343R 帳單分期查詢交易
            service.getBillIntallments(effectiveCreditCards, cache, rsData);

            // 取CEW343R其他分期查詢交易
            service.getOtherIntallments(effectiveCreditCards, cache, rsData);

            BigDecimal remainInsTotal = cache.getInstallmentTxnRs().getRepeats().stream().map(CEW343RRepeat::getTxuamt).reduce(BigDecimal.ZERO, BigDecimal::add);
            rsData.setRemainInsTotal(IBUtils.formatMoney(remainInsTotal, 0, "$"));

        }
        // ----------------------- 未分期資訊 -----------------------
        if (StringUtils.isBlank(reSearchFlag) || StringUtils.equals(reSearchFlag, "2")) {
            // 單筆分期未請款
            List<NCCQU008BilledDetailVo> singleIntallmentsNoGetMoney = new ArrayList<>();
            Boolean isCew222Error = false;
            try {
                List<NCCQU008BilledDetailVo> singleIntallmentsInApplyNoGetMoney = new ArrayList<>();
                // #9223 FP-25737 急上版:未分期資訊 CEW222R固定回無資料
                service.getSingleIntallmentsInApplyNoGetMoney(this.getLoginUser().getCustId(), effectiveCreditCards, cache, singleIntallmentsInApplyNoGetMoney);
                // singleIntallmentsNoGetMoney.addAll(singleIntallmentsInApplyNoGetMoney);

            }
            catch (ServiceException ex) {
                logger.error("cew222 單筆未分期查詢失敗", ex);
                isCew222Error = true;

            }
            service.getUnbilledDetails(getLoginUser(), cache, getLocale());
            singleIntallmentsNoGetMoney.addAll(cache.getUnBilledDetails());
            singleIntallmentsNoGetMoney.sort(Comparator.comparing(NCCQU008BilledDetailVo::getPchDay).reversed());
            rsData.setSingleIntallmentsNoGetMoney(singleIntallmentsNoGetMoney);
            if (Boolean.TRUE.equals(isCew222Error) && StringUtils.equals(cache.getUnBilledDetailQueryStatus(), NCCQU008StatusType.QUERY_ERROR.getMemo())) {
                rsData.setSingleIntallmentsQueryNotPayStatus(NCCQU008StatusType.QUERY_ERROR.getMemo());
            }
            else if (CollectionUtils.isEmpty(singleIntallmentsNoGetMoney)) {
                rsData.setSingleIntallmentsQueryNotPayStatus(NCCQU008StatusType.QUERY_NODATA.getMemo());
            }
            else {
                rsData.setSingleIntallmentsQueryNotPayStatus(NCCQU008StatusType.QUERY_SUCCESS.getMemo());
            }

        }
        if (StringUtils.isBlank(reSearchFlag) || StringUtils.equals(reSearchFlag, "3")) {
            try {
                // 單筆分期未分期已請款
                List<NCCQU008BilledDetailVo> singleBillIntallmentsGetMoney = new ArrayList<>();
                service.getSingleBillIntallmentsGetMoney(getLoginUser(), cache, singleBillIntallmentsGetMoney);
                rsData.setSingleIntallmentsGetMoney(singleBillIntallmentsGetMoney);
                if (CollectionUtils.isEmpty(singleBillIntallmentsGetMoney)) {
                    rsData.setSingleIntallmentsQueryHasPayStatus(NCCQU008StatusType.QUERY_NODATA.getMemo());
                }
                else {
                    rsData.setSingleIntallmentsQueryHasPayStatus(NCCQU008StatusType.QUERY_SUCCESS.getMemo());
                }
            }
            catch (ServiceException ex) {
                logger.error("單筆未分期查詢失敗", ex);
                rsData.setSingleIntallmentsQueryHasPayStatus(NCCQU008StatusType.QUERY_ERROR.getMemo());
            }
        }
        if (StringUtils.isBlank(reSearchFlag) || StringUtils.equals(reSearchFlag, "4")) {
            try {
                // * 帳單分期未請款
                List<NCCQU008BillNotIntallmentsDisplay> billNotIntallments = new ArrayList<>();
                service.getBillIntallmentsInApplyNoGetMoney(getLoginUser(), cache, billNotIntallments);
                rsData.setBillNotIntallments(billNotIntallments);

                if (CollectionUtils.isEmpty(billNotIntallments)) {
                    rsData.setBillIntallmentsQueryStatus(NCCQU008StatusType.QUERY_NODATA.getMemo());
                }
                else {
                    rsData.setBillIntallmentsQueryStatus(NCCQU008StatusType.QUERY_SUCCESS.getMemo());
                }
            }
            catch (ServiceException ex) {
                logger.error("帳單未分期查詢失敗", ex);
                rsData.setBillIntallmentsQueryStatus(NCCQU008StatusType.QUERY_ERROR.getMemo());
            }
        }

    }

}
