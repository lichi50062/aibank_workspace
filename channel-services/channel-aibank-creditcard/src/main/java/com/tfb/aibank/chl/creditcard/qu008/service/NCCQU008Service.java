package com.tfb.aibank.chl.creditcard.qu008.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu008.cache.NCCQU008CacheData;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008010Rs;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008030Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008040Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008040Rs;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008060Rs;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008BillNotIntallmentsDisplay;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008BilledDetailVo;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008InsInterestSection;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008InstallmentDetailVo;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008InterestCalResult;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008MonthlyEstimate;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008MonthlyEstimateDetail;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008Output;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008TxnDataRq;
import com.tfb.aibank.chl.creditcard.qu008.type.NCCQU008OgType;
import com.tfb.aibank.chl.creditcard.qu008.type.NCCQU008StatusType;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4150RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4150RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4150RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4151RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4151RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4151RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4152RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4152RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4152RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4153RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4153RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4153RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW220RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW220RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW221RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW221RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW222RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW302RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW315RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW317RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW317RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW326RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW326RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW343RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW343RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CardBillPeriodApplyModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CardStagesApplyModel;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.TxStatusType;

//@formatter:off
/**
* @(#)NCCQU008Service.java
* 
* <p>Description:信用卡分期管理 NCCQU008Service</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NCCQU008Service extends NCCService {
    private static final IBLog logger = IBLog.getLog(NCCQU008Service.class);

    public static final String CACHE_KEY = "NCCQU008_CACHE_KEY";

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private CreditCardResource creditCardResource;

    /**
     * 帳單分期
     */
    public static final String BILL_TYPE = "BILL";

    /**
     * 單筆分期
     */
    public static final String UNBILL_TYPE = "UNBILL";

    /**
     * 是否已簽訂約定書
     *
     * @param user
     * @param cache
     */
    public void getAgreementFlag(AIBankUser user, NCCQU008CacheData cache, String txtype, String txbseqn) {

        try {
            boolean agreementFlag = creditCardResource.sendCEW227R(user.getCompanyVo().getCompanyUid(), txtype, txbseqn);
            cache.setAgreementFlag(agreementFlag);
        }
        catch (ServiceException ex) {
            logger.error(ex.getLocalizedMessage());
            cache.setAgreementFlag(false);
        }

    }

    /**
     * 
     * 分期查詢交易
     * 
     * @param user
     * @param cache
     */
    public void queryInstallmentTxn(AIBankUser user, NCCQU008CacheData cache) {
        if (Objects.isNull(cache.getInstallmentTxnRs())) {
            CEW343RResponse response = creditCardResource.sendCEW343R(user.getCustId());
            cache.setInstallmentTxnRs(response);
        }
    }

    /**
     * 
     * 帳單分期清單
     * 
     * @param user
     * @param cache
     * @throws ActionException
     */
    public void queryBillInstallment(AIBankUser user, NCCQU008CacheData cache) throws ActionException {
        if (Objects.isNull(cache.getInstallmentBillRs())) {
            CEW315RResponse response = creditCardResource.sendCEW315R(user.getCustId());
            cache.setInstallmentBillRs(response);
            validateCew315RRS(response);
        }
        else {
            validateCew315RRS(cache.getInstallmentBillRs());
        }
    }

    /**
     * 取得單筆分期資訊
     * 
     * @param user
     * @param cache
     * @param singleIntallments
     */
    public void getSingleIntallments(List<CreditCard> effectiveCreditCards, NCCQU008CacheData cache, NCCQU008010Rs rsData) {

        List<NCCQU008InstallmentDetailVo> singleIntallments = new ArrayList<>();

        if (cache.getInstallmentTxnRs() != null && CollectionUtils.isNotEmpty(cache.getInstallmentTxnRs().getRepeats())) {
            singleIntallments = cache.getInstallmentTxnRs().getRepeats().stream().filter(repeat -> StringUtils.equals(repeat.getOgtype(), NCCQU008OgType.CONS.getMemo())).map(repeat -> convertCEW343R2InstallmentDetail(repeat, effectiveCreditCards)).sorted(Comparator.comparingLong(NCCQU008InstallmentDetailVo::getBillTime).reversed()).toList();
        }

        rsData.setSingleIntallments(singleIntallments);

    }

    /**
     * 分期細項轉換
     * 
     * @param repeat
     * @param effectiveCreditCards
     * @return
     */
    private NCCQU008InstallmentDetailVo convertCEW343R2InstallmentDetail(CEW343RRepeat repeat, List<CreditCard> effectiveCreditCards) {
        NCCQU008InstallmentDetailVo installmentDetail = new NCCQU008InstallmentDetailVo();
        if (StringUtils.equals(repeat.getOgtype(), NCCQU008OgType.BEX1.getMemo())) {
            // 帳單分期
            // 餘額
            String balanceAmt = IBUtils.formatMoney(repeat.getTxuamt(), 0, "$");

            String billDesc = """
                    %s %s
                    """.formatted(I18NUtils.getMessage("nccqu008.balance_amt.desc"), balanceAmt);
            installmentDetail.setBillDesc(genBillDesc(repeat));
            installmentDetail.setBillDetailDesc(billDesc);
        }
        else {
            // 餘額
            String balanceAmt = IBUtils.formatMoney(repeat.getTxuamt(), 0, "$");

            String billDesc = """
                    %s %s
                    """.formatted(I18NUtils.getMessage("nccqu008.balance_amt.desc"), balanceAmt);
            installmentDetail.setBillDesc(repeat.getMercnm());
            installmentDetail.setBillDetailDesc(billDesc);
        }

        // TODO 待確認有無可能回空 不做防呆避免問題未發現
        installmentDetail.setBillTime((repeat.getPchday()).getTime());
        installmentDetail.setInstallmentAmt("$" + IBUtils.formatMoney(repeat.getDesamt(), 0));

        String installmentAlreadyPaid = I18NUtils.getMessage("nccqu008.installment_single.period.desc", repeat.getTxbtic(), repeat.getTxstge());
        installmentDetail.setInstallmentAlreadyPaid(installmentAlreadyPaid);
        // TODO 需確認位數
        installmentDetail.setInstallmentAnnualRate(repeat.getTxrate() + "%");

        installmentDetail.setApplyDate(DateUtils.getCEDateStr(repeat.getTxiday()));
        CreditCard creditCardOrNull = effectiveCreditCards.stream().filter(card -> StringUtils.equals(StringUtils.substring(card.getCardNo(), -4), repeat.getCrdno4())).findAny().orElse(null);

        if (Objects.isNull(creditCardOrNull)) {
            // ···· 複製規格
            installmentDetail.setCardDisplay(I18NUtils.getMessage("ncc.default.card_name") + " ····" + repeat.getCrdno4());
        }
        else {
            // ···· 複製規格
            installmentDetail.setCardDisplay(creditCardOrNull.getCardName() + " ····" + repeat.getCrdno4());
        }
        installmentDetail.setBillDate(DateUtils.getCEDateStr(repeat.getPchday()));
        installmentDetail.setNccDay(DateUtils.getCEDateStr(repeat.getNccday()));

        return installmentDetail;

    }

    /**
     * 帳單資訊 組合
     * 
     * @param repeat
     * @return
     */
    private String genBillDesc(CEW343RRepeat repeat) {
        if (Objects.isNull(repeat.getTxYymm())) {
            return "";
        }
        String billDateStr = DateUtils.getCEDateStr(repeat.getTxYymm());
        String billYyyy = StringUtils.left(billDateStr, 4);
        String billMm = StringUtils.substring(billDateStr, 5, 7);
        Integer billMmInt = ConvertUtils.str2Int(billMm, 0);
        billMm = billMmInt.toString();

        return I18NUtils.getMessage("nccqu008.installment_bill.bill.desc", billYyyy, billMm);
    }

    /**
     * 取得帳單分期
     * 
     * @param user
     * @param effectiveCreditCards
     * @param cache
     * @param rsData
     */
    public void getBillIntallments(List<CreditCard> effectiveCreditCards, NCCQU008CacheData cache, NCCQU008010Rs rsData) {

        List<NCCQU008InstallmentDetailVo> billIntallments = cache.getInstallmentTxnRs().getRepeats().stream().filter(repeat -> StringUtils.equals(repeat.getOgtype(), NCCQU008OgType.BEX1.getMemo())).map(repeat -> {
            return convertCEW343R2InstallmentDetail(repeat, effectiveCreditCards);
        }).sorted(Comparator.comparingLong(NCCQU008InstallmentDetailVo::getBillTime).reversed()).toList();
        rsData.setBillIntallments(billIntallments);

    }

    /**
     * 取得其他分期資訊
     * 
     * @param user
     * @param cache
     * @param singleIntallments
     */
    public void getOtherIntallments(List<CreditCard> effectiveCreditCards, NCCQU008CacheData cache, NCCQU008010Rs rsData) {

        List<NCCQU008InstallmentDetailVo> otherIntallments = cache.getInstallmentTxnRs().getRepeats().stream().filter(repeat -> StringUtils.notAllEquals(repeat.getOgtype(), NCCQU008OgType.CONS.getMemo(), NCCQU008OgType.BEX1.getMemo())).map(repeat -> {
            return convertCEW343R2InstallmentDetail(repeat, effectiveCreditCards);
        }).sorted(Comparator.comparingLong(NCCQU008InstallmentDetailVo::getBillTime)).toList();

        rsData.setOtherIntallments(otherIntallments);

    }

    /**
     * 單筆消費明細資料
     * 
     * @param nccqu008030Rs
     */
    public void getBilledDetail(NCCQU008030Rq rqData, AIBankUser user, NCCQU008CacheData cache, Locale locale) {
        if (StringUtils.isBlank(rqData.getQueryFlag()) || StringUtils.equals(rqData.getQueryFlag(), "1")) {
            getUnbilledDetails(user, cache, locale);
        }
        if (StringUtils.isBlank(rqData.getQueryFlag()) || StringUtils.equals(rqData.getQueryFlag(), "2")) {
            getBilledDetails(user, cache, locale);
        }

    }

    /**
     * 新增檢核
     * 
     * @param user
     * @param cache
     * @param locale
     * @throws ActionException
     */
    public void checkBillTxn(AIBankUser user, NCCQU008CacheData cache, Locale locale) throws ActionException {

        getUnbilledDetails(user, cache, locale);

        getBilledDetails(user, cache, locale);
        if (StringUtils.notEquals(cache.getBilledDetailQueryStatus(), NCCQU008StatusType.QUERY_SUCCESS.getMemo()) && StringUtils.notEquals(cache.getUnBilledDetailQueryStatus(), NCCQU008StatusType.QUERY_SUCCESS.getMemo())) {
            throw ExceptionUtils.getActionException(ErrorCode.NO_INSTALLMANT_BILL_DETAIL_ERROR);
        }

    }

    /**
     * 未請款消費明細資料
     * 
     * @param nccqu008030Rs
     */
    public void getUnbilledDetails(AIBankUser user, NCCQU008CacheData cache, Locale locale) {
        // 如果有數據說明已經打過一次，直接返回
        if (CollectionUtils.isNotEmpty(cache.getUnBilledDetails()))
            return;

        List<NCCQU008BilledDetailVo> unBilledDetails = new ArrayList<>();
        boolean isCE4153RError = false;
        try {
            CE4153RRequest ce4153RRequest = new CE4153RRequest();
            ce4153RRequest.setTxtype("1");
            // 規格有錯 應該帶 CustId 就好
            ce4153RRequest.setPid(user.getCustId());

            CE4153RResponse ce4153response = null;
            ce4153response = creditCardResource.installmentConfigurationTxn(ce4153RRequest);
            List<NCCQU008BilledDetailVo> unBilledCe4153Details = processCE4153Repeat(ce4153response, locale);
            unBilledDetails.addAll(unBilledCe4153Details);
        }
        catch (ServiceException ex) {
            logger.error(ex.getLocalizedMessage());
            isCE4153RError = true;
        }

        boolean isCE4152RError = false;
        try {
            CE4152RRequest ce4152RRequest = new CE4152RRequest();
            ce4152RRequest.setTxtype("1");
            ce4152RRequest.setPid(user.getCustId());

            CE4152RResponse ce4152response = creditCardResource.checkAndDetermineTaxTxn(ce4152RRequest);
            List<NCCQU008BilledDetailVo> unBilledCe4152Details = processCE4152Repeat(ce4152response);
            unBilledDetails.addAll(unBilledCe4152Details);
        }
        catch (ServiceException ex) {
            logger.error(ex.getLocalizedMessage());
            isCE4152RError = true;
        }

        boolean isCE4151RError = false;
        try {
            CE4151RRequest ce4151RRequest = new CE4151RRequest();
            ce4151RRequest.setTxtype("1");
            ce4151RRequest.setPeriod("12");
            ce4151RRequest.setPid(user.getCustId());

            CE4151RResponse ce4151response = creditCardResource.tuitionFeeTxn(ce4151RRequest);
            List<NCCQU008BilledDetailVo> unBilledCe4151Details = processCE4151Repeat(ce4151response);
            unBilledDetails.addAll(unBilledCe4151Details);
        }
        catch (ServiceException ex) {
            logger.error(ex.getLocalizedMessage());
            isCE4151RError = true;
        }

        boolean isCE4150RError = false;
        try {
            CE4150RRequest ce4150RRequest = new CE4150RRequest();
            ce4150RRequest.setTxtype("1");
            ce4150RRequest.setPid(user.getCustId());

            CE4150RResponse ce4150response = creditCardResource.comprehensiveTaxTxn(ce4150RRequest);
            List<NCCQU008BilledDetailVo> unBilledCe4150Details = processCE4150Repeat(ce4150response);
            unBilledDetails.addAll(unBilledCe4150Details);
        }
        catch (ServiceException ex) {
            logger.error(ex.getLocalizedMessage());
            isCE4150RError = true;
        }

        cache.setUnBilledDetails(unBilledDetails);

        if (isCE4153RError && isCE4152RError && isCE4151RError && isCE4150RError) {
            cache.setUnBilledDetailQueryStatus(NCCQU008StatusType.QUERY_ERROR.getMemo());
        }
        else if (CollectionUtils.isEmpty(cache.getUnBilledDetails())) {
            cache.setUnBilledDetailQueryStatus(NCCQU008StatusType.QUERY_NODATA.getMemo());
        }
        else {
            cache.setUnBilledDetailQueryStatus(NCCQU008StatusType.QUERY_SUCCESS.getMemo());
        }
    }

    private List<NCCQU008BilledDetailVo> processCE4153Repeat(CE4153RResponse response, Locale locale) {
        List<CE4153RRepeat> ce4153Repeat = response.getRepeats();
        List<NCCQU008BilledDetailVo> unBilledDetails = ce4153Repeat.stream().filter(repeat -> {
            boolean moreThanThousand = repeat.getTxAmt().compareTo(new BigDecimal(1000)) >= 0;
            boolean isNoBilled = StringUtils.isBlank(repeat.getNcgrop()) && (StringUtils.isBlank(repeat.getNcseqn()));
            return isNoBilled && moreThanThousand;
        }).map(repeat -> {
            NCCQU008BilledDetailVo billedDetail = new NCCQU008BilledDetailVo();
            billedDetail.setIsPayBill(false);
            billedDetail.setPchDay(DateUtils.getCEDateStr(repeat.getPchday()));
            billedDetail.setPchTime(Optional.ofNullable(repeat.getPchday()).map(Date::getTime).orElse(0L));

            billedDetail.setNcsbsq(repeat.getNcsbsq());
            billedDetail.setNcgrop(repeat.getNcgrop());
            billedDetail.setNcseqn(repeat.getNcseqn());
            billedDetail.setCreditDate(repeat.getNccday());
            billedDetail.setAuthCode(repeat.getAuthCode());

            if (Objects.nonNull(repeat.getTxAmt())) {
                billedDetail.setTxAmt(IBUtils.formatMoney(repeat.getTxAmt().divide(new BigDecimal(100), 0, RoundingMode.DOWN), 0, "$"));
                billedDetail.setTxAmtOrigin(repeat.getTxAmt().divide(new BigDecimal(100), 0, RoundingMode.DOWN));
            }
            else {
                billedDetail.setTxAmt("$0");
                billedDetail.setTxAmtOrigin(BigDecimal.ZERO);
            }
            if (Objects.nonNull(repeat.getRate())) {
                billedDetail.setRate(repeat.getRate().divide(new BigDecimal(100), 2, RoundingMode.DOWN));
            }
            else {
                billedDetail.setRate(BigDecimal.ZERO);
            }

            billedDetail.setFxCur(currencyCodeCacheManager.getCurrencyName(repeat.getSrccur(), locale));
            billedDetail.setFxAmt(repeat.getSrcamt());
            billedDetail.setCardNo(repeat.getPin());
            billedDetail.setBillDesc(repeat.getNcbtxt1());

            billedDetail.setAccountDay(repeat.getNccday());
            // TODO 待規格確認 關帳日日期
            billedDetail.setCloseDay(new Date());
            // for 交易結果判斷使用哪隻電文做交易
            billedDetail.setHtxtId("CE4153R");
            billedDetail.setIsApply(false);
            billedDetail.setIsGetMoney(false);
            return billedDetail;
        }).toList();
        return unBilledDetails;
    }

    /**
     * 未請款查核定稅消費明細資料
     * 
     * @param response
     * @return
     */
    private List<NCCQU008BilledDetailVo> processCE4152Repeat(CE4152RResponse response) {
        List<CE4152RRepeat> ce4152Repeat = response.getRepeats();

        List<NCCQU008BilledDetailVo> unBilledDetails = ce4152Repeat.stream().filter(repeat -> {
            // 查核定稅需過濾出單筆消費金額大於等於1,000元的消費明細
            return repeat.getAmt().compareTo(new BigDecimal(1000)) >= 0;
        }).map(repeat -> {
            NCCQU008BilledDetailVo billedDetail = new NCCQU008BilledDetailVo();
            billedDetail.setIsPayBill(false);
            billedDetail.setPchDay(DateUtils.getCEDateStr(repeat.getPchday()));
            billedDetail.setPchTime(Optional.ofNullable(repeat.getPchday()).map(Date::getTime).orElse(0L));

            billedDetail.setAuthCode(repeat.getAuthCode());

            if (Objects.nonNull(repeat.getAmt())) {
                billedDetail.setTxAmt(IBUtils.formatMoney(repeat.getAmt(), 0, "$"));
                billedDetail.setTxAmtOrigin(repeat.getAmt());
            }
            else {
                billedDetail.setTxAmt("$0");
                billedDetail.setTxAmtOrigin(BigDecimal.ZERO);
            }
            if (Objects.nonNull(repeat.getRate())) {
                billedDetail.setRate(repeat.getRate().divide(new BigDecimal(100), 2, RoundingMode.DOWN));
            }
            else {
                billedDetail.setRate(BigDecimal.ZERO);
            }

            billedDetail.setType(repeat.getType());
            billedDetail.setCtype(repeat.getCtype());
            // for 交易結果判斷使用哪隻電文做交易
            billedDetail.setHtxtId("CE4152R");

            billedDetail.setCardNo(repeat.getPin());
            billedDetail.setIsApply(false);
            billedDetail.setIsGetMoney(false);
            return billedDetail;
        }).toList();
        return unBilledDetails;
    }

    /**
     * 未請款學雜費消費明細資料
     * 
     * @param response
     * @return
     */
    private List<NCCQU008BilledDetailVo> processCE4151Repeat(CE4151RResponse response) {
        List<CE4151RRepeat> ce4151Repeat = response.getRepeats();

        List<NCCQU008BilledDetailVo> unBilledDetails = ce4151Repeat.stream().filter(repeat -> {
            // 學費/綜所稅需過濾出單筆消費金額大於0元的消費明細
            return repeat.getTaxAmt().compareTo(BigDecimal.ZERO) > 0;
        }).map(repeat -> {
            NCCQU008BilledDetailVo billedDetail = new NCCQU008BilledDetailVo();
            billedDetail.setIsPayBill(false);
            billedDetail.setPchDay(DateUtils.getCEDateStr(repeat.getPchday()));
            billedDetail.setPchTime(Optional.ofNullable(repeat.getPchday()).map(Date::getTime).orElse(0L));

            billedDetail.setAuthCode(repeat.getAuthCode());

            if (Objects.nonNull(repeat.getTaxAmt())) {
                billedDetail.setTxAmt(IBUtils.formatMoney(repeat.getTaxAmt(), 0, "$"));
                billedDetail.setTxAmtOrigin(repeat.getTaxAmt());
            }
            else {
                billedDetail.setTxAmt("$0");
                billedDetail.setTxAmtOrigin(BigDecimal.ZERO);
            }
            if (Objects.nonNull(repeat.getRate())) {
                billedDetail.setRate(repeat.getRate().divide(new BigDecimal(100), 2, RoundingMode.DOWN));
            }
            else {
                billedDetail.setRate(BigDecimal.ZERO);
            }

            billedDetail.setCtype(repeat.getCtype());
            // for 交易結果判斷使用哪隻電文做交易
            billedDetail.setHtxtId("CE4151R");
            billedDetail.setCardNo(repeat.getPin());
            billedDetail.setIsApply(false);
            billedDetail.setIsGetMoney(false);
            return billedDetail;
        }).toList();
        return unBilledDetails;
    }

    /**
     * 未請款綜所稅消費明細資料
     * 
     * @param response
     * @return
     */
    private List<NCCQU008BilledDetailVo> processCE4150Repeat(CE4150RResponse response) {
        List<CE4150RRepeat> ce4150Repeat = response.getRepeats();

        List<NCCQU008BilledDetailVo> unBilledDetails = ce4150Repeat.stream().filter(repeat -> {
            // 學費/綜所稅需過濾出單筆消費金額大於0元的消費明細
            return repeat.getTaxAmt().compareTo(BigDecimal.ZERO) > 0;
        }).map(repeat -> {
            NCCQU008BilledDetailVo billedDetail = new NCCQU008BilledDetailVo();
            billedDetail.setIsPayBill(false);
            billedDetail.setPchDay(DateUtils.getCEDateStr(repeat.getPchday()));
            billedDetail.setPchTime(Optional.ofNullable(repeat.getPchday()).map(Date::getTime).orElse(0L));

            billedDetail.setAuthCode(repeat.getAuthCode());

            if (Objects.nonNull(repeat.getTaxAmt())) {
                billedDetail.setTxAmt(IBUtils.formatMoney(repeat.getTaxAmt(), 0, "$"));
                billedDetail.setTxAmtOrigin(repeat.getTaxAmt());
            }
            else {
                billedDetail.setTxAmt("$0");
                billedDetail.setTxAmtOrigin(BigDecimal.ZERO);
            }
            if (Objects.nonNull(repeat.getRate())) {
                billedDetail.setRate(repeat.getRate().divide(new BigDecimal(100), 2, RoundingMode.DOWN));
            }
            else {
                billedDetail.setRate(BigDecimal.ZERO);
            }

            billedDetail.setCtype(repeat.getCtype());
            // for 交易結果判斷使用哪隻電文做交易
            billedDetail.setHtxtId("CE4150R");

            billedDetail.setCardNo(repeat.getPin());
            billedDetail.setIsApply(false);
            return billedDetail;
        }).toList();
        return unBilledDetails;
    }

    /**
     * 請款消費明細資料
     * 
     * @param nccqu008030Rs
     * @throws ActionException
     */
    public void getBilledDetails(NCCQU008010Rs nccqu008010Rs, AIBankUser user, NCCQU008TxnDataRq txnData, Locale locale) throws ActionException {
        CreditCard cardObj = getCardNoByCardKey(user, locale, txnData.getCardKey());

        CEW205RResponse cew205RResponse = null;
        // 規格有錯 應該帶 CustId 就好
        if (cardObj != null && cardObj.isSupplementary()) {
            cew205RResponse = creditCardResource.sendCEW205R("", cardObj.getCardNo()).getData();
        }
        else {
            cew205RResponse = creditCardResource.sendCEW205R(user.getCustId(), "").getData();
        }
        if (cardObj == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        Optional<NCCQU008BilledDetailVo> billedDetailOtp = cew205RResponse.getTxRepeats().stream().filter(repeat -> StringUtils.equals(repeat.getOreFlag(), "Y") && StringUtils.equals(repeat.getNcSeqn(), txnData.getSeq()) && StringUtils.equals(repeat.getNcGrop(), txnData.getGroup())).map(repeat -> {
            NCCQU008BilledDetailVo billedDetail = new NCCQU008BilledDetailVo();
            billedDetail.setIsPayBill(StringUtils.equals(txnData.getTxnType(), "2"));
            billedDetail.setPchDay(DateUtils.getCEDateStr(repeat.getNcbPchd()));
            billedDetail.setPchTime(Optional.ofNullable(repeat.getNcbPchd()).map(Date::getTime).orElse(0L));
            billedDetail.setBillDesc(repeat.getNcbTxt1());
            billedDetail.setTxAmt(IBUtils.formatMoney(repeat.getNcbTwd(), 0, "$"));
            billedDetail.setTxAmtOrigin(repeat.getNcbTwd());
            billedDetail.setNcsbsq(repeat.getNcSbsq());
            billedDetail.setNcgrop(repeat.getNcGrop());
            billedDetail.setNcseqn(repeat.getNcSeqn());
            billedDetail.setStagesType(repeat.getTxType());
            billedDetail.setCreditDate(repeat.getNcbNccd());
            billedDetail.setFxCur(repeat.getSrcCur());
            billedDetail.setFxAmt(repeat.getSrcAmt());
            billedDetail.setCardNo(cardObj.getCardNo());
            billedDetail.setAccountDay(DateUtils.getCEDate(txnData.getBillDate()));
            billedDetail.setCloseDay(DateUtils.getCEDate(txnData.getCloseDate()));
            billedDetail.setRate(ConvertUtils.str2BigDecimal(txnData.getRate(), 2));
            return billedDetail;
        }).findAny();

        if (billedDetailOtp.isPresent()) {
            nccqu008010Rs.setBilledDetail(billedDetailOtp.get());
        }

    }

    /**
     * 請款消費明細資料
     * 
     * @param nccqu008030Rs
     */
    public void getBilledDetails(AIBankUser user, NCCQU008CacheData cache, Locale locale) {
        if (Boolean.TRUE.equals(cache.getIsCew205RIn030HasCheck())) {
            return;
        }
        CEW205RResponse cew205RResponse = null;
        try {
            CreditCard cardObj = getCardNoByCardKey(user, locale, cache.getBillDates().getCardKey());
            if (cardObj != null && cardObj.isSupplementary()) {
                // 規格有錯 應該帶 CustId 就好
                cew205RResponse = creditCardResource.sendCEW205R("", cardObj.getCardNo()).getData();
            }
            else {
                // 規格有錯 應該帶 CustId 就好
                cew205RResponse = creditCardResource.sendCEW205R(user.getCustId(), "").getData();
            }

        }
        catch (ServiceException | ActionException ex) {
            logger.error(ex.getLocalizedMessage());
            cache.setBilledDetailQueryStatus(NCCQU008StatusType.QUERY_ERROR.getMemo());
            return; // Exit early in case of an exception
        }
        cache.setIsCew205RIn030HasCheck(Boolean.TRUE);
        List<NCCQU008BilledDetailVo> billedDetails = cew205RResponse.getTxRepeats().stream().filter(repeat -> StringUtils.equals(repeat.getOreFlag(), "Y")).map(repeat -> {
            NCCQU008BilledDetailVo billedDetail = new NCCQU008BilledDetailVo();
            billedDetail.setIsPayBill(true);
            billedDetail.setPchDay(DateUtils.getCEDateStr(repeat.getNcbPchd()));
            billedDetail.setPchTime(Optional.ofNullable(repeat.getNcbPchd()).map(Date::getTime).orElse(0L));
            billedDetail.setBillDesc(repeat.getNcbTxt1());
            billedDetail.setTxAmt(IBUtils.formatMoney(repeat.getNcbTwd(), 0, "$"));
            billedDetail.setTxAmtOrigin(repeat.getNcbTwd());
            billedDetail.setNcsbsq(repeat.getNcSbsq());
            billedDetail.setNcgrop(repeat.getNcGrop());
            billedDetail.setNcseqn(repeat.getNcSeqn());
            billedDetail.setStagesType(repeat.getTxType());
            billedDetail.setCreditDate(repeat.getNcbNccd());
            billedDetail.setFxCur(repeat.getSrcCur());
            billedDetail.setFxAmt(repeat.getSrcAmt());
            billedDetail.setCardNo(repeat.getNcbCard());
            billedDetail.setAccountDay(repeat.getNcbNccd());
            billedDetail.setCloseDay(repeat.getNcCday());
            return billedDetail;
        }).toList();

        cache.setBilledDetails(billedDetails);
        cache.setBilledDetailQueryStatus(CollectionUtils.isEmpty(billedDetails) ? NCCQU008StatusType.QUERY_NODATA.getMemo() : NCCQU008StatusType.QUERY_SUCCESS.getMemo());
    }

    /**
     * 是否可申請分期帳單資訊 檢查
     * 
     * @param user
     * @param cache
     * @throws ActionException
     */
    public void checkIsInstallmentBillApply(AIBankUser user, NCCQU008CacheData cache) throws ActionException {
        queryBillInstallment(user, cache);
        if (Objects.isNull(cache.getInstallmentBillRs())) {
            throw ExceptionUtils.getActionException(ErrorCode.INSTALLMANT_BILL_QUERY_ERROR);
        }

    }

    /**
     * 方案選擇資料-帳單分期
     * 
     * @throws ActionException
     */
    public void getPlanSelection(AIBankUser user, NCCQU008CacheData cache, NCCQU008040Rq qu004Rq, NCCQU008040Rs nccqu008040Rs, boolean isBillProcess) throws ActionException {
        if (isBillProcess) {
            checkIsInstallmentBillApply(user, cache);
        }
        genInstallmentDescInPlanSelection(qu004Rq, cache, isBillProcess, nccqu008040Rs, user);
    }

    /**
     * 帳單分期-分期資訊
     * 
     * @param installmentBillRs
     * @param nccqu008040Rs
     * @throws ActionException
     */
    private void genInstallmentDescInPlanSelection(NCCQU008040Rq qu004Rq, NCCQU008CacheData cache, boolean isBillProcess, NCCQU008040Rs nccqu008040Rs, AIBankUser user) throws ActionException {

        BigDecimal purchaseAmt = isBillProcess ? cache.getInstallmentBillRs().getPaymentBalance() : qu004Rq.getBilledDetail().getTxAmtOrigin();
        cache.setPurchaseAmt(purchaseAmt);
        String purchaseAmtStr = IBUtils.formatMoney(purchaseAmt, 0, "$");
        // 帳單分期流程
        if (isBillProcess) {
            String billMonth = String.valueOf(ConvertUtils.str2Int(StringUtils.right(cache.getInstallmentBillRs().getBillYyyymm(), 2)));
            String billAmt = IBUtils.formatMoney(cache.getInstallmentBillRs().getPaymentBalance(), 0, "$");
            String installmentDesc = I18NUtils.getMessage("nccqu008.plan_selection.installment_bill.desc", billMonth, billAmt);
            nccqu008040Rs.setInstallmentDesc(installmentDesc);
            // #6115 利率顯示不應四捨五入，直接取用電文回傳資料即可
            String installmentDesc02 = I18NUtils.getMessage("nccqu008.plan_selection.installment_bill.desc02", ConvertUtils.bigDecimal2Str(cache.getInstallmentBillRs().getInterestRate(), 2), IBUtils.formatMoney(cache.getInstallmentBillRs().getMinimumPay(), 0, "$"));
            nccqu008040Rs.setInstallmentDesc02(installmentDesc02);

            cache.setInstallmentData(I18NUtils.getMessage("nccqu008.bill-by-month.desc", billMonth));
            cache.setInstallmentAmt(billAmt);
            cache.setInstallmentRate(ConvertUtils.bigDecimal2Str(cache.getInstallmentBillRs().getInterestRate(), 2) + "%");
        }
        else {
            String purchaseDesc = StringUtils.isBlank(cache.getBillDates().getMemo()) ? qu004Rq.getBilledDetail().getBillDesc() : cache.getBillDates().getMemo();
            String installmentDesc = purchaseDesc + " " + purchaseAmtStr;
            if (StringUtils.isBlank(purchaseDesc)) {
                installmentDesc = purchaseAmtStr;
            }
            cache.setInstallmentAmt(IBUtils.formatMoney(purchaseAmt, 0, "$"));
            nccqu008040Rs.setInstallmentDesc(installmentDesc);
            cache.setInstallmentData(purchaseDesc);
        }

        // nccqu008040Rs.setInterestCalResults(interestCalResults);
        // cache.setInterestCalResults(interestCalResults);

        genMainInsInterestSection(nccqu008040Rs, cache, isBillProcess, user);
    }

    /**
     * 取得試算結果
     *
     * @param applyAmt
     * @param period
     * @param rsTypeD002
     * @return
     */
    private List<NCCQU008InterestCalResult> doTrialAmount(BigDecimal twdAmount, int applyPreiod, CEW315RResponse installmentBillRs) {

        // 除於12
        final BigDecimal bd12 = new BigDecimal(12);
        // 除於100
        final BigDecimal bd100 = new BigDecimal(100);

        // 存放第N期 的 [合計]
        Map<Integer, BigDecimal> totalData = new HashMap<>();
        // 存放第N期 的 未還本金
        Map<Integer, BigDecimal> notReturnCapitalData = new HashMap<>();
        // 存放第N期 的 本金
        Map<Integer, BigDecimal> capitalData = new HashMap<>();

        // 回傳清單
        List<NCCQU008InterestCalResult> payResults = new ArrayList<>();

        // m = [分期年利率] ÷ 12
        BigDecimal m = installmentBillRs.getInterestRate().divide(bd100).divide(bd12, 30, RoundingMode.DOWN);

        // n = [分期期數]
        for (int n = 1; n <= applyPreiod; n++) {

            // ------------------------------------
            if (n <= (applyPreiod - 1)) {
                // 第1~(n-1)期的 [合計] = [臺幣金額]
                // × { [ (1+m)n ] × m ｝
                // ÷ { [ (1+m)n ] - 1 }，且四捨五入至整數位
                BigDecimal rate = BigDecimal.valueOf(Math.pow(BigDecimal.ONE.add(m).doubleValue(), applyPreiod)); // (1+m)n
                BigDecimal amountN = null;
                if (rate.subtract(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
                    // 分母為0
                    amountN = twdAmount.multiply(rate).multiply(m).divide(BigDecimal.ONE, 0, RoundingMode.HALF_UP);
                }
                else {
                    amountN = twdAmount.multiply(rate).multiply(m).divide(rate.subtract(BigDecimal.ONE), 0, RoundingMode.HALF_UP);
                }

                totalData.put(n, amountN);
            }
            // ------------------------------------
            BigDecimal currNotReturnCapital = BigDecimal.ZERO; // 未還本金
            if (n == 1) {
                // 第1期的 [未還本金] = [臺幣金額]
                currNotReturnCapital = twdAmount;
            }
            else {

                BigDecimal prevNotReturnCapital = notReturnCapitalData.get(n - 1); // 第(n-1)期的 [未還本金]
                BigDecimal prevCapital = capitalData.get(n - 1); // 第(n-1)期的 [本金]
                currNotReturnCapital = prevNotReturnCapital.subtract(prevCapital);
            }
            notReturnCapitalData.put(n, currNotReturnCapital);
            // ------------------------------------
            // 第1~n期的 [利息] = 該期的 [未還本金] × m，且四捨五入至整數位
            BigDecimal currInterest = currNotReturnCapital.multiply(m).setScale(0, RoundingMode.HALF_UP);

            // ------------------------------------
            // 第1~(n-1)期的 [本金] = 該期的 [合計] - 該期的 [利息]
            if (n <= (applyPreiod - 1)) {
                BigDecimal currCapital = totalData.get(n).subtract(currInterest);
                capitalData.put(n, currCapital);
            }
            else {
                // 最後一期的 本金 就是 最後一期未還本金
                capitalData.put(n, notReturnCapitalData.get(n));
            }
            // ------------------------------------
            // 第n期的 [合計] = 第n期的 [未還本金] + 第n期的 [利息]
            if (n == applyPreiod) {
                totalData.put(n, currNotReturnCapital.add(currInterest));
            }

            // ------------------------------------
            NCCQU008InterestCalResult interestCalResult = new NCCQU008InterestCalResult();

            // 分期期數
            interestCalResult.setPayTimes(String.valueOf(n));
            // 本金
            interestCalResult.setCaptial(IBUtils.formatMoney(capitalData.get(n), 0, "$"));
            interestCalResult.setCaptialOrigin(capitalData.get(n));
            // 利息
            interestCalResult.setInterest(IBUtils.formatMoney(currInterest, 0, "$"));
            interestCalResult.setInterestOrigin(currInterest);
            // 合計
            interestCalResult.setTotal(IBUtils.formatMoney(totalData.get(n), 0, "$"));

            payResults.add(interestCalResult);
        }

        return payResults;
    }

    public List<NCCQU008InterestCalResult> getPayResult(int applyPreiod, BigDecimal freeRate, Date closeDay, Date accountDay, BigDecimal twdAmount) throws ActionException {

        final BigDecimal BD_12 = new BigDecimal(12);
        final BigDecimal BD_100 = new BigDecimal(100);

        List<NCCQU008InterestCalResult> payResult = new ArrayList<>();

        // Date closeDay = billedDetailVo.getCloseDay(); // 關帳日
        // Date accountDay = billedDetailVo.getAccountDay(); // 入帳日

        // 若關帳日小於入帳日則抛錯
        if (closeDay.compareTo(accountDay) < 0) {
            throw new ActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }

        // BigDecimal twdAmount = billedDetailVo.getTxAmtOrigin(); // 台幣金額

        Map<Integer, BigDecimal> totalData = new HashMap<>(); // 存放第N期 的 [合計]
        Map<Integer, BigDecimal> notReturnCapitalData = new HashMap<>(); // 存放第N期 的 未還本金
        Map<Integer, BigDecimal> capitalData = new HashMap<>(); // 存放第N期 的 本金

        if (freeRate.compareTo(BigDecimal.ZERO) == 0) {
            // (a) 若分期年利率=0，
            // n = [分期期數]
            // 第1~n期的 [利息] = 0
            // 第1期的 [本金] = ( [臺幣金額] / n) ，且無條件捨去取整數 + 除不盡的金額
            // 第2~n期的 [本金] = ( [臺幣金額] / n) ，且無條件捨去取整數

            // n = [分期期數]
            for (int n = 1; n <= applyPreiod; n++) {
                // ------------------------------------
                // ------------------------------------
                NCCQU008InterestCalResult interestCalResult = new NCCQU008InterestCalResult();
                // 分期期數
                interestCalResult.setPayTimes(String.valueOf(n));
                interestCalResult.setInterest(IBUtils.formatMoney(BigDecimal.ZERO, 0, "$"));
                interestCalResult.setInterestOrigin(BigDecimal.ZERO);
                if (n == 1) {
                    // 第1期
                    BigDecimal tmpCaptial = twdAmount.divide(new BigDecimal(applyPreiod), 0, RoundingMode.DOWN);
                    BigDecimal remainder = twdAmount.remainder(new BigDecimal(applyPreiod));
                    BigDecimal captial = tmpCaptial.add(remainder);

                    // 本金
                    interestCalResult.setCaptial(IBUtils.formatMoney(captial, 0, "$"));
                    interestCalResult.setCaptialOrigin(captial);
                    // 合計
                    interestCalResult.setTotal(IBUtils.formatMoney(captial, 0, "$"));
                }
                else {
                    // 第2~n期
                    BigDecimal captial = twdAmount.divide(new BigDecimal(applyPreiod), 0, RoundingMode.DOWN);

                    // 本金
                    interestCalResult.setCaptial(IBUtils.formatMoney(captial, 0, "$"));
                    interestCalResult.setCaptialOrigin(captial);

                    // 合計
                    interestCalResult.setTotal(IBUtils.formatMoney(captial, 0, "$"));
                }
                payResult.add(interestCalResult);
            }

        }
        else {
            // (b) 若分期年利率<>0，
            // 無單, 處理 /12 除不盡問題 -- 取到第九位後無條件捨去
            // BigDecimal m = freeRate.divide(BD_100).divide(BD_12, 9, RoundingMode.FLOOR); // m = [分期年利率] ÷ 12
            BigDecimal m = freeRate.divide(BD_100).divide(BD_12, 9, RoundingMode.FLOOR); // m = [分期年利率] ÷ 12
            // 甲、 若關帳日-入帳日<>0，
            if (closeDay.compareTo(accountDay) != 0) {

                // 先使用年金法計算公式，取得 [年金法的第1期本金]
                // 第一期 p1_X
                BigDecimal rate = new BigDecimal(Math.pow(BigDecimal.ONE.add(m).doubleValue(), Double.valueOf(applyPreiod))); // (1+m)n

                BigDecimal p1Before_AmountN = twdAmount.multiply(rate).multiply(m).divide(rate.subtract(BigDecimal.ONE), 0, RoundingMode.HALF_UP); // 合計

                BigDecimal p1Before_NotReturnCapital = twdAmount; // 未還本金
                BigDecimal p1Before_Interest = p1Before_NotReturnCapital.multiply(m).setScale(0, RoundingMode.HALF_UP);
                BigDecimal p1Before_Capital = p1Before_AmountN.subtract(p1Before_Interest);

                // [入帳日] + 1個月(java calendar)
                Calendar accountDayNextMonth = Calendar.getInstance();
                accountDayNextMonth.setTime(accountDay);
                accountDayNextMonth.add(Calendar.MONTH, 1);

                // 範例:
                // 借款本金[臺幣金額] = 26520
                // 年利率: 4.68%
                // 消費入帳日: 102/07/21
                // 關帳日 : 102/08/02
                // 關帳日-消費入帳日 = 12 (天)
                // 消費入帳日+ 1個月 - 消費入帳日 = 31 (天)
                int days1 = DateUtils.getDaysBetween(closeDay, accountDay); // [關帳日] - [入帳日]
                int days2 = DateUtils.getDaysBetween(accountDayNextMonth.getTime(), accountDay); // [關帳日] - [入帳日]

                // 第1期的 [本金] = ( [年金法的第1期本金]
                // x ( [關帳日] - [入帳日] )
                // / ( [入帳日] + 1個月(java calendar) – [入帳日]))，且四捨五入至整數位
                BigDecimal p1_Capital = p1Before_Capital.multiply(new BigDecimal(days1)).divide(new BigDecimal(days2), 0, RoundingMode.HALF_UP);

                // 第1期的 [利息] = ( [臺幣金額]
                // x [分期年利率]
                // x ( [關帳日] - [入帳日] ) / 365 )，且四捨五入至整數位
                BigDecimal p1_Interest = twdAmount.multiply(freeRate.divide(BD_100)).multiply(new BigDecimal(days1).divide(new BigDecimal(365), 10, RoundingMode.HALF_UP)).setScale(0, RoundingMode.HALF_UP);

                // 第1期的 [合計] =第1期的 [本金] + 第1期的 [利息]
                BigDecimal p1_Total = p1_Capital.add(p1_Interest);

                // ------------------------------------
                NCCQU008InterestCalResult interestCalResult = new NCCQU008InterestCalResult();

                // 分期期數
                interestCalResult.setPayTimes(String.valueOf(1));
                // 本金
                interestCalResult.setCaptial(IBUtils.formatMoney(p1_Capital, 0, "$"));
                interestCalResult.setCaptialOrigin(p1_Capital);
                // 利息
                interestCalResult.setInterest(IBUtils.formatMoney(p1_Interest, 0, "$"));
                interestCalResult.setInterestOrigin(p1_Interest);
                // 合計
                interestCalResult.setTotal(IBUtils.formatMoney(p1_Total, 0, "$"));

                payResult.add(interestCalResult);

                twdAmount = twdAmount.subtract(p1_Capital);

                totalData.put(1, p1_Total); // 第一期合計
                notReturnCapitalData.put(1, twdAmount); // 第一期未還本金
                capitalData.put(1, p1_Capital); // 第一期利息

                rate = new BigDecimal(Math.pow(BigDecimal.ONE.add(m).doubleValue(), Double.valueOf(applyPreiod - 1))); // (1+m)n

                // n = [分期期數]
                for (int n = 2; n <= applyPreiod; n++) {

                    // ------------------------------------
                    if (n <= (applyPreiod - 1)) {
                        // 第1~(n-1)期的 [合計] = [臺幣金額]
                        // × { [ (1+m)n ] × m ｝
                        // ÷ { [ (1+m)n ] - 1 }，且四捨五入至整數位
                        BigDecimal amountN = twdAmount.multiply(rate).multiply(m).divide(rate.subtract(BigDecimal.ONE), 0, RoundingMode.HALF_UP);
                        totalData.put(n, amountN);
                    }
                    // ------------------------------------

                    // 第2期的 [未還本金] = 第1期的 [未還本金] – 第1期的 [本金]
                    // 第3期的 [未還本金] = 第2期的 [未還本金] – 第2期的 [本金]
                    // 第n期的 [未還本金] = 第(n-1)期的 [未還本金] – 第(n-1)期的 [本金]

                    BigDecimal prevNotReturnCapital = notReturnCapitalData.get(n - 1); // 第(n-1)期的 [未還本金]
                    BigDecimal prevCapital = capitalData.get(n - 1); // 第(n-1)期的 [本金]
                    BigDecimal currNotReturnCapital = BigDecimal.ZERO;
                    if (n == 2) {
                        currNotReturnCapital = prevNotReturnCapital;// 未還本金
                    }
                    else {
                        currNotReturnCapital = prevNotReturnCapital.subtract(prevCapital);// 未還本金
                    }

                    notReturnCapitalData.put(n, currNotReturnCapital);
                    // ------------------------------------
                    // 第1~n期的 [利息] = 該期的 [未還本金] × m，且四捨五入至整數位
                    BigDecimal currInterest = currNotReturnCapital.multiply(m).setScale(0, RoundingMode.HALF_UP);

                    // ------------------------------------
                    // 第1~(n-1)期的 [本金] = 該期的 [合計] - 該期的 [利息]
                    if (n <= (applyPreiod - 1)) {
                        BigDecimal currCapital = totalData.get(n).subtract(currInterest);
                        capitalData.put(n, currCapital);
                    }
                    else {
                        // 最後一期的 本金 就是 最後一期未還本金
                        capitalData.put(n, notReturnCapitalData.get(n));
                    }
                    // ------------------------------------
                    // 第n期的 [合計] = 第n期的 [未還本金] + 第n期的 [利息]
                    if (n == applyPreiod) {
                        totalData.put(n, currNotReturnCapital.add(currInterest));
                    }

                    // ------------------------------------
                    interestCalResult = new NCCQU008InterestCalResult();

                    // 分期期數
                    interestCalResult.setPayTimes(String.valueOf(n));
                    // 本金
                    interestCalResult.setCaptialOrigin(capitalData.get(n));
                    interestCalResult.setCaptial(IBUtils.formatMoney(capitalData.get(n), 0, "$"));
                    // 利息
                    interestCalResult.setInterest(IBUtils.formatMoney(currInterest, 0, "$"));
                    interestCalResult.setInterestOrigin(currInterest);
                    // 合計
                    interestCalResult.setTotal(IBUtils.formatMoney(totalData.get(n), 0, "$"));
                    payResult.add(interestCalResult);
                }

            }
            else {
                // 乙、 若關帳日-入帳日=0，

                // n = [分期期數]
                for (int n = 1; n <= applyPreiod; n++) {

                    // ------------------------------------
                    if (n <= (applyPreiod - 1)) {
                        // 第1~(n-1)期的 [合計] = [臺幣金額]
                        // × { [ (1+m)n ] × m ｝
                        // ÷ { [ (1+m)n ] - 1 }，且四捨五入至整數位
                        BigDecimal rate = new BigDecimal(Math.pow(BigDecimal.ONE.add(m).doubleValue(), Double.valueOf(applyPreiod))); // (1+m)n

                        BigDecimal amountN = twdAmount.multiply(rate).multiply(m).divide(rate.subtract(BigDecimal.ONE), 0, RoundingMode.HALF_UP);
                        totalData.put(n, amountN);
                    }
                    // ------------------------------------
                    BigDecimal currNotReturnCapital = BigDecimal.ZERO; // 未還本金
                    if (n == 1) {
                        // 第1期的 [未還本金] = [臺幣金額]
                        currNotReturnCapital = twdAmount;
                    }
                    else {

                        // 第2期的 [未還本金] = 第1期的 [未還本金] – 第1期的 [本金]
                        // 第3期的 [未還本金] = 第2期的 [未還本金] – 第2期的 [本金]
                        // 第n期的 [未還本金] = 第(n-1)期的 [未還本金] – 第(n-1)期的 [本金]
                        BigDecimal prevNotReturnCapital = notReturnCapitalData.get(n - 1); // 第(n-1)期的 [未還本金]
                        BigDecimal prevCapital = capitalData.get(n - 1); // 第(n-1)期的 [本金]
                        currNotReturnCapital = prevNotReturnCapital.subtract(prevCapital);
                    }
                    notReturnCapitalData.put(n, currNotReturnCapital);
                    // ------------------------------------
                    // 第1~n期的 [利息] = 該期的 [未還本金] × m，且四捨五入至整數位
                    BigDecimal currInterest = currNotReturnCapital.multiply(m).setScale(0, RoundingMode.HALF_UP);

                    // ------------------------------------
                    // 第1~(n-1)期的 [本金] = 該期的 [合計] - 該期的 [利息]
                    if (n <= (applyPreiod - 1)) {
                        BigDecimal currCapital = totalData.get(n).subtract(currInterest);
                        capitalData.put(n, currCapital);
                    }
                    else {
                        // 最後一期的 本金 就是 最後一期未還本金
                        capitalData.put(n, notReturnCapitalData.get(n));
                    }
                    // ------------------------------------
                    // 第n期的 [合計] = 第n期的 [未還本金] + 第n期的 [利息]
                    if (n == applyPreiod) {
                        totalData.put(n, currNotReturnCapital.add(currInterest));
                    }

                    // ------------------------------------
                    NCCQU008InterestCalResult interestCalResult = new NCCQU008InterestCalResult();

                    // 分期期數
                    interestCalResult.setPayTimes(String.valueOf(n));
                    // 本金
                    interestCalResult.setCaptial(IBUtils.formatMoney(capitalData.get(n), 0, "$"));
                    interestCalResult.setCaptialOrigin(capitalData.get(n));
                    // 利息
                    interestCalResult.setInterest(IBUtils.formatMoney(currInterest, 0, "$"));
                    interestCalResult.setInterestOrigin(currInterest);
                    // 合計
                    interestCalResult.setTotal(IBUtils.formatMoney(totalData.get(n), 0, "$"));
                    payResult.add(interestCalResult);
                }
            }
        }
        return payResult;
    }

    /**
     * 建立 方案選擇資料 分期資料
     * 
     * @param interestCalResults
     * @param nccqu008040Rs
     */
    private void genMainInsInterestSection(NCCQU008040Rs nccqu008040Rs, NCCQU008CacheData cache, boolean isBillProcess, AIBankUser user) throws ActionException {

        // 主方案存期
        List<String> mainInsIntersetPeriod = List.of("12", "30");
        String mostPopularPeriod = "12";
        String mostEasyPeriod = "30";

        // 主方案存期資訊
        List<NCCQU008InsInterestSection> mainInsIntersets = new ArrayList<>();

        for (String period : mainInsIntersetPeriod) {
            // 利息計算結果
            List<NCCQU008InterestCalResult> interestCalResults = new ArrayList<>();

            if (!isBillProcess) {
                CEW220RResponse cew220Response = getCew220RRs(cache, user, period);
                cache.getPeriodCew220rMap().put(period, cew220Response);
                BigDecimal feerate = cew220Response.getFeerate();
                if (Boolean.TRUE.equals(cache.getBilledDetailSelect().getIsPayBill())) {
                    interestCalResults = getPayResult(ConvertUtils.str2Int(period), feerate, cache.getBilledDetailSelect().getCloseDay(), cache.getBilledDetailSelect().getAccountDay(), cache.getBilledDetailSelect().getTxAmtOrigin());
                }
            }
            else {
                BigDecimal billedPurchaseAmt = Optional.ofNullable(cache.getInstallmentBillRs()).map(billRs -> billRs.getPaymentBalance().subtract(billRs.getMinimumPay())).orElse(BigDecimal.ONE);
                interestCalResults = doTrialAmount(billedPurchaseAmt, Integer.valueOf(period), cache.getInstallmentBillRs());
            }

            NCCQU008InsInterestSection insInterestSection = new NCCQU008InsInterestSection();
            insInterestSection.setIsMostEasy(StringUtils.equals(period, mostEasyPeriod));
            insInterestSection.setIsMostPopular(StringUtils.equals(period, mostPopularPeriod));
            insInterestSection.setPeriod(I18NUtils.getMessage("nccqu008.plan_selection.period.desc", period));
            insInterestSection.setPeriodOrigin(ConvertUtils.str2Int(period));
            if (isBillProcess) {
                BigDecimal totalInterest = interestCalResults.stream().map(NCCQU008InterestCalResult::getInterestOrigin).reduce(BigDecimal.ZERO, BigDecimal::add);

                String interestDesc = I18NUtils.getMessage("nccqu008.plan_selection.installment_insterest.desc", interestCalResults.get(1).getTotal(), IBUtils.formatMoney(totalInterest, 0, "$"));
                insInterestSection.setInterestDesc(interestDesc);
                insInterestSection.setInterestCalResults(interestCalResults);
            }
            else if (cache.getBilledDetailSelect().getIsPayBill()) {
                BigDecimal totalInterest = interestCalResults.stream().map(NCCQU008InterestCalResult::getInterestOrigin).reduce(BigDecimal.ZERO, BigDecimal::add);

                String interestDesc = I18NUtils.getMessage("nccqu008.plan_selection.installment_insterest.desc", interestCalResults.get(1).getTotal(), IBUtils.formatMoney(totalInterest, 0, "$"));
                insInterestSection.setInterestDesc(interestDesc);
                insInterestSection.setInterestCalResults(interestCalResults);
            }

            mainInsIntersets.add(insInterestSection);
        }

        nccqu008040Rs.setInsInterestSection(mainInsIntersets);

    }

    public void getOtherInsInterestSection(NCCQU008Output output, NCCQU008CacheData cache, boolean isBillProcess, AIBankUser user) throws ActionException {
        String mostPopularPeriod = "12";
        String mostEasyPeriod = "30";
        // 非主方案存期
        List<String> supInsIntersetPeriod = List.of("3", "6", "18", "24");

        List<NCCQU008InsInterestSection> otherInsIntersets = new ArrayList<>();
        for (String period : supInsIntersetPeriod) {
            // 利息計算結果

            List<NCCQU008InterestCalResult> interestCalResults = new ArrayList<>();

            if (!isBillProcess) {
                CEW220RResponse cew220Response = getCew220RRs(cache, user, period);
                cache.getPeriodCew220rMap().put(period, cew220Response);
                BigDecimal feerate = cew220Response.getFeerate();
                if (Boolean.TRUE.equals(cache.getBilledDetailSelect().getIsPayBill())) {
                    interestCalResults = getPayResult(ConvertUtils.str2Int(period), feerate, cache.getBilledDetailSelect().getCloseDay(), cache.getBilledDetailSelect().getAccountDay(), cache.getBilledDetailSelect().getTxAmtOrigin());
                }
            }
            else {
                BigDecimal billedPurchaseAmt = Optional.ofNullable(cache.getInstallmentBillRs()).map(billRs -> billRs.getPaymentBalance().subtract(billRs.getMinimumPay())).orElse(BigDecimal.ONE);
                interestCalResults = doTrialAmount(billedPurchaseAmt, Integer.valueOf(period), cache.getInstallmentBillRs());
            }

            NCCQU008InsInterestSection insInterestSection = new NCCQU008InsInterestSection();
            insInterestSection.setIsMostEasy(StringUtils.equals(period, mostEasyPeriod));
            insInterestSection.setIsMostPopular(StringUtils.equals(period, mostPopularPeriod));
            insInterestSection.setPeriod(I18NUtils.getMessage("nccqu008.plan_selection.period.desc", period));
            insInterestSection.setPeriodOrigin(ConvertUtils.str2Int(period));
            if (isBillProcess) {
                BigDecimal totalInterest = interestCalResults.stream().map(NCCQU008InterestCalResult::getInterestOrigin).reduce(BigDecimal.ZERO, BigDecimal::add);
                String interestDesc = I18NUtils.getMessage("nccqu008.plan_selection.installment_insterest.desc", interestCalResults.get(1).getTotal(), IBUtils.formatMoney(totalInterest, 0, "$"));
                insInterestSection.setInterestDesc(interestDesc);
                insInterestSection.setInterestCalResults(interestCalResults);

            }
            else if (Boolean.TRUE.equals(cache.getBilledDetailSelect().getIsPayBill())) {
                BigDecimal totalInterest = interestCalResults.stream().map(NCCQU008InterestCalResult::getInterestOrigin).reduce(BigDecimal.ZERO, BigDecimal::add);
                String interestDesc = I18NUtils.getMessage("nccqu008.plan_selection.installment_insterest.desc", interestCalResults.size() >= 2 ? interestCalResults.get(1).getTotal() : "", IBUtils.formatMoney(totalInterest, 0, "$"));
                insInterestSection.setInterestDesc(interestDesc);
                insInterestSection.setInterestCalResults(interestCalResults);
            }
            otherInsIntersets.add(insInterestSection);
        }

        output.setOtherInsInterestSection(otherInsIntersets);
    }

    /**
     * 單筆分期CEW220R 從計算
     * 
     * @param feerate
     * @param cache
     * @param isBillProcess
     * @param period
     */
    public void reCalInterestSection(BigDecimal feerate, NCCQU008CacheData cache, Boolean isBillProcess, String period) {
        List<NCCQU008InterestCalResult> interestCalResults = new ArrayList<>();
        if (Boolean.FALSE.equals(isBillProcess)) {

            try {
                if (Boolean.TRUE.equals(cache.getBilledDetailSelect().getIsPayBill())) {
                    interestCalResults = getPayResult(ConvertUtils.str2Int(period), feerate, cache.getBilledDetailSelect().getCloseDay(), cache.getBilledDetailSelect().getAccountDay(), cache.getBilledDetailSelect().getTxAmtOrigin());
                }

            }
            catch (ActionException e) {
                logger.error(e.getMessage());
            }

        }
        else {
            BigDecimal billedPurchaseAmt = Optional.ofNullable(cache.getInstallmentBillRs()).map(billRs -> billRs.getPaymentBalance().subtract(billRs.getMinimumPay())).orElse(BigDecimal.ONE);
            interestCalResults = doTrialAmount(billedPurchaseAmt, Integer.valueOf(period), cache.getInstallmentBillRs());
        }
        cache.setInterestCalResults(interestCalResults);
    }

    public void genInsInterestSection(String period, NCCQU008CacheData cache, AIBankUser user) throws ActionException {
        CEW220RResponse cew220Response = new CEW220RResponse();

        if (cache.getBilledDetailSelect() != null && Boolean.TRUE.equals(cache.getBilledDetailSelect().getIsPayBill())) {
            try {
                if (cache.getPeriodCew220rMap().get(period) != null) {
                    cew220Response = cache.getPeriodCew220rMap().get(period);
                }
                else {
                    CEW220RRequest request = new CEW220RRequest();
                    request.setTxtype(cache.getBilledDetailSelect().getStagesType());
                    request.setTxamt(ConvertUtils.bigDecimal2Str(cache.getBilledDetailSelect().getTxAmtOrigin()));

                    request.setTxstge(period);
                    request.setNcbpchd(DateUtils.getROCDateStr(DateUtils.getCEDate(cache.getBilledDetailSelect().getPchDay()), ""));
                    request.setTxacid(user.getCustId());

                    request.setCustCdno(cache.getBilledDetailSelect().getCardNo());
                    request.setNcsbsq(cache.getBilledDetailSelect().getNcsbsq());
                    request.setNccday(DateUtils.getROCDateStr(cache.getBilledDetailSelect().getCreditDate(), ""));
                    request.setNcgrop(cache.getBilledDetailSelect().getNcgrop());
                    request.setNcseqn(cache.getBilledDetailSelect().getNcseqn());
                    cew220Response = creditCardResource.calculationTransaction(request);
                }

                cache.setFreerate(cew220Response.getFeerate());
            }
            catch (ServiceException e) {
                throw ExceptionUtils.getActionException(ErrorCode.SINGLE_INS_CAL_ERROR);
            }
        }
        else {
            if (StringUtils.equals("CE4152R", cache.getBilledDetailSelect().getHtxtId())) {
                try {
                    CE4152RRequest request = new CE4152RRequest();
                    request.setTxtype("1");
                    request.setPid(user.getCustId());
                    request.setPeriod(period);

                    CE4152RResponse response = creditCardResource.checkAndDetermineTaxTxn(request);
                    List<NCCQU008BilledDetailVo> unBilledDetails = processCE4152Repeat(response);
                    Optional<NCCQU008BilledDetailVo> billedDetailOtp = unBilledDetails.stream().findAny();
                    if (billedDetailOtp.isPresent()) {
                        NCCQU008BilledDetailVo detailVo = billedDetailOtp.get();
                        cache.getBilledDetailSelect().setRate(detailVo.getRate());
                        cache.setFreerate(detailVo.getRate());
                    }
                }
                catch (ServiceException ex) {
                    logger.error(ex.getLocalizedMessage());
                }
            }
            else if (StringUtils.equals("CE4151R", cache.getBilledDetailSelect().getHtxtId())) {
                try {
                    CE4151RRequest request = new CE4151RRequest();
                    request.setTxtype("1");
                    request.setPeriod(period);
                    request.setPid(user.getCustId());

                    CE4151RResponse response = creditCardResource.tuitionFeeTxn(request);
                    List<NCCQU008BilledDetailVo> unBilledDetails = processCE4151Repeat(response);
                    Optional<NCCQU008BilledDetailVo> billedDetailOtp = unBilledDetails.stream().findAny();
                    if (billedDetailOtp.isPresent()) {
                        NCCQU008BilledDetailVo detailVo = billedDetailOtp.get();
                        cache.getBilledDetailSelect().setRate(detailVo.getRate());
                        cache.setFreerate(detailVo.getRate());
                    }
                }
                catch (ServiceException ex) {
                    logger.error(ex.getLocalizedMessage());
                }
            }
            else if (StringUtils.equals("CE4150R", cache.getBilledDetailSelect().getHtxtId())) {
                try {
                    CE4150RRequest request = new CE4150RRequest();
                    request.setTxtype("1");
                    request.setPid(user.getCustId());
                    request.setPeriod(period);

                    CE4150RResponse response = creditCardResource.comprehensiveTaxTxn(request);
                    List<NCCQU008BilledDetailVo> unBilledDetails = processCE4150Repeat(response);
                    Optional<NCCQU008BilledDetailVo> billedDetailOtp = unBilledDetails.stream().findAny();
                    if (billedDetailOtp.isPresent()) {
                        NCCQU008BilledDetailVo detailVo = billedDetailOtp.get();
                        cache.getBilledDetailSelect().setRate(detailVo.getRate());
                        cache.setFreerate(detailVo.getRate());
                    }
                }
                catch (ServiceException ex) {
                    logger.error(ex.getLocalizedMessage());
                }
            }
            else {
                cache.setFreerate(cache.getBilledDetailSelect().getRate());
            }
        }
    }

    /**
     * 帳單分期申請
     * 
     * @param user
     * @param period
     * @param billYyyymm
     * @param output
     */
    public void billInstallmentApply(AIBankUser user, String period, String billYyyymm, Locale locale, CardBillPeriodApplyModel cardBillPeriodApplyModel, CEW317RResponse cew317RRs, MBAccessLog accessLogEntity) {
        CEW317RRequest request = new CEW317RRequest();
        request.setBillYyyymm(billYyyymm);
        request.setChanel("K");
        request.setPeriod(StringUtils.leftPadByByteLength(period, 2, "0"));

        ActionException aex = null;
        try {
            // 規格有錯 應該帶 CustId 就好
            CEW317RResponse newCew317RRs = creditCardResource.calculationTransaction(request, user.getCustId());
            cardBillPeriodApplyModel.setHostTxTime(new Date());
            cardBillPeriodApplyModel.setBillYyyymm(StringUtils.left(DateUtils.getSimpleDateFormat(newCew317RRs.getBillYyyymm()), 6));
            cardBillPeriodApplyModel.setTxStatus(TxStatusType.SUCCESS.getCode());
            cardBillPeriodApplyModel.setUpdateTime(new Date());
            cardBillPeriodApplyModel.setRefNo(newCew317RRs.getRefNo());
            cardBillPeriodApplyModel.setInterestRate(newCew317RRs.getInterestRate());

            cew317RRs.setCardHolderId(newCew317RRs.getCardHolderId());
            cew317RRs.setRefNo(newCew317RRs.getRefNo());
            cew317RRs.setBillYyyymm(newCew317RRs.getBillYyyymm());
            cew317RRs.setBillCycleDay(newCew317RRs.getBillCycleDay());
            cew317RRs.setPaymentDeadline(newCew317RRs.getPaymentDeadline());
            cew317RRs.setInterestRate(newCew317RRs.getInterestRate());
            cew317RRs.setPeriod(newCew317RRs.getPeriod());
            cew317RRs.setEmail(newCew317RRs.getEmail());

        }
        catch (ServiceException ex) {
            aex = handleException(accessLogEntity, ex);
            cardBillPeriodApplyModel.setAex(aex);
            ErrorDescription errDesOutput = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex.getStatus(), locale, "");
            cardBillPeriodApplyModel.setTxStatus(TxStatusType.FAIL.getCode());
            cardBillPeriodApplyModel.setTxErrorCode(errDesOutput.getCode());
            cardBillPeriodApplyModel.setTxErrorDesc(errDesOutput.getDesc());
            cardBillPeriodApplyModel.setTxErrorSystemId(errDesOutput.getSys());
        }

    }

    /**
     * 創建 帳單分期主檔
     * 
     * @param user
     * @param period
     * @param billYyyymm
     * @param clientIp
     * @param output
     */
    public void createCardBillPeriodApplyModel(AIBankUser user, Integer period, String billYyyymm, String clientIp, CardBillPeriodApplyModel output) {
        output.setCompanyKey(user.getCompanyKey());
        output.setNameCode(user.getNameCode());
        output.setUserKey(user.getUserKey());
        output.setUserId(user.getUserId());
        output.setBillYyyymm(billYyyymm);
        output.setPeriod(period);
        output.setRefNo(" ");
        output.setInterestRate(BigDecimal.ZERO);
        output.setTxStatus("4");
        output.setTxDate(new Date());
        output.setCreateTime(new Date());
        output.setClientIp(clientIp);
        output.setTxSource("3");
        output.setTraceId(MDC.get(MDCKey.traceId.name()));
        CardBillPeriodApplyModel model = creditCardResource.createCardBillPeriodApply(output);
        output.setBillKey(model.getBillKey());
    }

    /**
     * 更新 帳單分期主檔
     * 
     * @param model
     */
    public void updateCardBillPeriodApplyModel(CardBillPeriodApplyModel model) {
        try {
            creditCardResource.createCardBillPeriodApply(model);
        }
        catch (ServiceException ex) {
            logger.error(ex.getLocalizedMessage());
        }
    }

    /**
     * 創建 單筆分期主檔
     * 
     * @param user
     * @param period
     * @param billYyyymm
     * @param clientIp
     * @param output
     */
    public void createCardStagesApply(AIBankUser user, NCCQU008CacheData cache, NCCQU008BilledDetailVo billedDetail, Integer period, String clientIp, CardStagesApplyModel model) {

        model.setCompanyKey(user.getCompanyKey());
        model.setNameCode(user.getNameCode());
        model.setUserKey(user.getUserKey());
        model.setUserId(user.getUserId());
        model.setCardNo(billedDetail.getCardNo());
        model.setNccDate(billedDetail.getCreditDate());
        model.setCardNo(billedDetail.getCardNo());
        model.setTwdAmt(ConvertUtils.str2Long(ConvertUtils.bigDecimal2Str(cache.getPurchaseAmt())));
        model.setNcsbsq(billedDetail.getNcsbsq());
        model.setNcgrop(billedDetail.getNcgrop());
        model.setNcseqn(ConvertUtils.str2Int(billedDetail.getNcseqn()));
        model.setStagesType(billedDetail.getStagesType());
        model.setStagesNumber(period);
        model.setAuthCode(billedDetail.getAuthCode());

        model.setTxStatus("4");
        model.setTxDate(new Date());
        model.setCreateTime(new Date());
        model.setClientIp(clientIp);
        model.setTxSource("3");
        model.setTraceId(MDC.get(MDCKey.traceId.name()));
        CardStagesApplyModel newModel = creditCardResource.createCardStagesApply(model);
        model.setStagesKey(newModel.getStagesKey());
    }

    /**
     * 更新 單筆分期主檔
     * 
     * @param model
     */
    public void updateCardStagesApplyModel(CardStagesApplyModel model) {
        try {
            creditCardResource.updateCardStagesApply(model);
        }
        catch (ServiceException ex) {
            logger.error(ex.getLocalizedMessage());
        }
    }

    /**
     * 帳單分期申請
     * 
     * @param user
     * @param period
     * @param billYyyymm
     * @param output
     */
    public void cardInstallmentApply(AIBankUser user, String period, String billYyyymm, Locale locale, CardBillPeriodApplyModel cardBillPeriodApplyModel, CEW317RResponse cew317RRs) {
        CEW317RRequest request = new CEW317RRequest();
        request.setBillYyyymm(billYyyymm);
        request.setChanel("K");
        request.setPeriod(StringUtils.leftPadByByteLength(period, 2, "0"));

        TxStatusType txnStatusType = TxStatusType.SUCCESS;
        ActionException aex = null;
        try {
            cew317RRs = creditCardResource.calculationTransaction(request, user.getCustIdWithDup());

            cardBillPeriodApplyModel.setBillYyyymm(StringUtils.left(DateUtils.getSimpleDateFormat(cew317RRs.getBillYyyymm()), 6));

            cardBillPeriodApplyModel.setRefNo(cew317RRs.getRefNo());
            cardBillPeriodApplyModel.setInterestRate(cew317RRs.getInterestRate());

        }
        catch (ServiceException sex) {
            aex = ExceptionUtils.getActionException(sex);
            ErrorDescription errDesOutput = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex.getStatus(), locale, "");
            txnStatusType = getTxStatusType(sex);
            cardBillPeriodApplyModel.setTxErrorCode(errDesOutput.getCode());
            cardBillPeriodApplyModel.setTxErrorDesc(errDesOutput.getDesc());
            cardBillPeriodApplyModel.setTxErrorSystemId(errDesOutput.getSys());
        }
        cardBillPeriodApplyModel.setUpdateTime(new Date());
        cardBillPeriodApplyModel.setHostTxTime(new Date());
        cardBillPeriodApplyModel.setTxStatus(txnStatusType.getCode());

    }

    /**
     * 單筆分期申請 - 未請款
     * 
     * @param user
     * @param cache
     * @param period
     * @param locale
     * @param billedDetail
     * @param ce4153RRs
     * @param cardStagesApplyModel
     */
    public void singleInstallmentApplyNoBill(AIBankUser user, NCCQU008CacheData cache, String period, Locale locale, NCCQU008BilledDetailVo billedDetail, CE4153RResponse ce4153RRs, CardStagesApplyModel cardStagesApplyModel, MBAccessLog accessLogEntity) {
        CE4153RRequest request = new CE4153RRequest();
        request.setTxtype("2");
        request.setPid(user.getCustId());
        request.setTxAmt(ConvertUtils.bigDecimal2Str(cache.getPurchaseAmt(), 0));
        request.setCdno(billedDetail.getCardNo());
        request.setPchday(DateUtils.getROCDateStr(DateUtils.getCEDate(billedDetail.getPchDay()), ""));
        request.setAuthCode(billedDetail.getAuthCode());
        request.setPeriod(period);
        request.setChel("K");
        request.setNcsbsq(billedDetail.getNcsbsq());
        request.setNccday(DateUtils.getROCDateStr(billedDetail.getCreditDate(), ""));
        request.setNcgrop(billedDetail.getNcgrop());
        request.setNcseqn(billedDetail.getNcseqn());
        TxStatusType txnStatusType = TxStatusType.SUCCESS;
        ActionException aex = null;
        try {
            // TODO 需確認 RS格式
            CE4153RResponse newCe4153RRs = creditCardResource.installmentConfigurationTxn(request);
            ce4153RRs.setSprefid(newCe4153RRs.getSprefid());
            ce4153RRs.setId(newCe4153RRs.getId());
            ce4153RRs.setRepeats(newCe4153RRs.getRepeats());
            cardStagesApplyModel.setFeeRate(billedDetail.getRate());
            cardStagesApplyModel.setStagesType("53");
        }
        catch (ServiceException sex) {
            logger.error(sex.getMessage(), sex);
            aex = handleException(accessLogEntity, sex);
            cardStagesApplyModel.setAex(aex);
            ErrorDescription errDesOutput = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex.getStatus(), locale, "");
            txnStatusType = getTxStatusType(sex);
            cardStagesApplyModel.setTxErrorCode(errDesOutput.getCode());
            cardStagesApplyModel.setTxErrorDesc(errDesOutput.getDesc());
            cardStagesApplyModel.setTxErrorSystemId(errDesOutput.getSys());
        }
        cardStagesApplyModel.setUpdateTime(new Date());
        cardStagesApplyModel.setHostTxTime(new Date());
        cardStagesApplyModel.setTxStatus(txnStatusType.getCode());

    }

    /**
     * 單筆分期查核定稅申請 - 未請款
     * 
     * @param user
     * @param cache
     * @param period
     * @param locale
     * @param billedDetail
     * @param ce4152RRs
     * @param cardStagesApplyModel
     */
    public void checkAndDetermineTaxApplyNoBill(AIBankUser user, NCCQU008CacheData cache, String period, Locale locale, NCCQU008BilledDetailVo billedDetail, CE4152RResponse ce4152RRs, CardStagesApplyModel cardStagesApplyModel, MBAccessLog accessLogEntity) {
        CE4152RRequest request = new CE4152RRequest();
        request.setTxtype("2");
        request.setPid(user.getCustId());
        request.setPin(billedDetail.getCardNo());
        request.setPchday(DateUtils.getROCDateStr(DateUtils.getCEDate(billedDetail.getPchDay()), ""));
        request.setAuthCode(billedDetail.getAuthCode());
        request.setAmt(ConvertUtils.bigDecimal2Str(cache.getPurchaseAmt(), 0));
        request.setPeriod(period);
        request.setType(billedDetail.getType());
        request.setChel("K");
        TxStatusType txnStatusType = TxStatusType.SUCCESS;
        ActionException aex = null;
        try {
            CE4152RResponse newCe4152RRs = creditCardResource.checkAndDetermineTaxTxn(request);
            ce4152RRs.setSprefid(newCe4152RRs.getSprefid());
            ce4152RRs.setId(newCe4152RRs.getId());
            ce4152RRs.setRepeats(newCe4152RRs.getRepeats());
            cardStagesApplyModel.setFeeRate(billedDetail.getRate());
            cardStagesApplyModel.setStagesType("52" + billedDetail.getType());
        }
        catch (ServiceException sex) {
            logger.error(sex.getMessage(), sex);
            aex = handleException(accessLogEntity, sex);
            cardStagesApplyModel.setAex(aex);
            ErrorDescription errDesOutput = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex.getStatus(), locale, "");
            txnStatusType = getTxStatusType(sex);
            cardStagesApplyModel.setTxErrorCode(errDesOutput.getCode());
            cardStagesApplyModel.setTxErrorDesc(errDesOutput.getDesc());
            cardStagesApplyModel.setTxErrorSystemId(errDesOutput.getSys());
        }
        cardStagesApplyModel.setUpdateTime(new Date());
        cardStagesApplyModel.setHostTxTime(new Date());
        cardStagesApplyModel.setTxStatus(txnStatusType.getCode());

    }

    /**
     * 單筆分期學雜費申請 - 未請款
     * 
     * @param user
     * @param cache
     * @param period
     * @param locale
     * @param billedDetail
     * @param ce4151RRs
     * @param cardStagesApplyModel
     */
    public void tuitionFeeApplyNoBill(AIBankUser user, NCCQU008CacheData cache, String period, Locale locale, NCCQU008BilledDetailVo billedDetail, CE4151RResponse ce4151RRs, CardStagesApplyModel cardStagesApplyModel, MBAccessLog accessLogEntity) {
        CE4151RRequest request = new CE4151RRequest();
        request.setTxtype("2");
        request.setPid(user.getCustId());
        request.setTaxAmt(ConvertUtils.bigDecimal2Str(cache.getPurchaseAmt(), 0));
        request.setCdno(billedDetail.getCardNo());
        request.setPchday(DateUtils.getROCDateStr(DateUtils.getCEDate(billedDetail.getPchDay()), ""));
        request.setAuthCode(billedDetail.getAuthCode());
        request.setPeriod(period);
        request.setChel("K");
        TxStatusType txnStatusType = TxStatusType.SUCCESS;
        ActionException aex = null;
        try {
            CE4151RResponse newCe4151RRs = creditCardResource.tuitionFeeTxn(request);
            ce4151RRs.setSprefid(newCe4151RRs.getSprefid());
            ce4151RRs.setId(newCe4151RRs.getId());
            ce4151RRs.setRepeats(newCe4151RRs.getRepeats());
            cardStagesApplyModel.setFeeRate(billedDetail.getRate());
            cardStagesApplyModel.setStagesType("51");
        }
        catch (ServiceException sex) {
            logger.error(sex.getMessage(), sex);
            aex = handleException(accessLogEntity, sex);
            cardStagesApplyModel.setAex(aex);
            ErrorDescription errDesOutput = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex.getStatus(), locale, "");
            txnStatusType = getTxStatusType(sex);
            cardStagesApplyModel.setTxErrorCode(errDesOutput.getCode());
            cardStagesApplyModel.setTxErrorDesc(errDesOutput.getDesc());
            cardStagesApplyModel.setTxErrorSystemId(errDesOutput.getSys());
        }
        cardStagesApplyModel.setUpdateTime(new Date());
        cardStagesApplyModel.setHostTxTime(new Date());
        cardStagesApplyModel.setTxStatus(txnStatusType.getCode());

    }

    /**
     * 單筆分期綜所稅申請 - 未請款
     * 
     * @param user
     * @param cache
     * @param period
     * @param locale
     * @param billedDetail
     * @param ce4150RRs
     * @param cardStagesApplyModel
     */
    public void comprehensiveTaxApplyNoBill(AIBankUser user, NCCQU008CacheData cache, String period, Locale locale, NCCQU008BilledDetailVo billedDetail, CE4150RResponse ce4150RRs, CardStagesApplyModel cardStagesApplyModel, MBAccessLog accessLogEntity) {
        CE4150RRequest request = new CE4150RRequest();
        request.setTxtype("2");
        request.setPid(user.getCustId());
        request.setTaxAmt(ConvertUtils.bigDecimal2Str(cache.getPurchaseAmt(), 0));
        request.setPin(billedDetail.getCardNo());
        request.setPchday(DateUtils.getROCDateStr(DateUtils.getCEDate(billedDetail.getPchDay()), ""));
        request.setAuthCode(billedDetail.getAuthCode());
        request.setPeriod(period);
        request.setChel("K");
        TxStatusType txnStatusType = TxStatusType.SUCCESS;
        ActionException aex = null;
        try {
            CE4150RResponse newCe4150RRs = creditCardResource.comprehensiveTaxTxn(request);
            ce4150RRs.setSprefid(newCe4150RRs.getSprefid());
            ce4150RRs.setId(newCe4150RRs.getId());
            ce4150RRs.setRepeats(newCe4150RRs.getRepeats());
            cardStagesApplyModel.setFeeRate(billedDetail.getRate());
            cardStagesApplyModel.setStagesType("50");
        }
        catch (ServiceException sex) {
            logger.error(sex.getMessage(), sex);
            aex = handleException(accessLogEntity, sex);
            cardStagesApplyModel.setAex(aex);
            ErrorDescription errDesOutput = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex.getStatus(), locale, "");
            txnStatusType = getTxStatusType(sex);
            cardStagesApplyModel.setTxErrorCode(errDesOutput.getCode());
            cardStagesApplyModel.setTxErrorDesc(errDesOutput.getDesc());
            cardStagesApplyModel.setTxErrorSystemId(errDesOutput.getSys());
        }
        cardStagesApplyModel.setUpdateTime(new Date());
        cardStagesApplyModel.setHostTxTime(new Date());
        cardStagesApplyModel.setTxStatus(txnStatusType.getCode());

    }

    public void singleInstallmentApplyHasBill(AIBankUser user, MBAccessLog accessLogEntity, String period, Locale locale, NCCQU008BilledDetailVo billedDetail, CEW221RResponse cew221ERs, CardStagesApplyModel cardStagesApplyModel) {
        CEW221RRequest request = new CEW221RRequest();
        request.setCustcdno(billedDetail.getCardNo());
        request.setAccid(user.getCustId());
        request.setNcsbsq(billedDetail.getNcsbsq());
        request.setNccday(DateUtils.getROCDateStr(billedDetail.getCreditDate(), ""));
        request.setNcgrop(billedDetail.getNcgrop());
        request.setNcseqn(billedDetail.getNcseqn());
        request.setTxamt(ConvertUtils.bigDecimal2Str(billedDetail.getTxAmtOrigin()));
        request.setTxtype(billedDetail.getStagesType());
        request.setTxstge(period);
        request.setTxchnl("K");

        TxStatusType txnStatusType = TxStatusType.SUCCESS;
        ActionException aex = null;

        try {
            CEW221RResponse newCew221ERs = creditCardResource.calculationTransaction(request, user.getCustId());
            cew221ERs.setOrfamt(newCew221ERs.getOrfamt());
            cew221ERs.setOreamt(newCew221ERs.getOreamt());
            cew221ERs.setFeeamt(newCew221ERs.getFeeamt());
            cew221ERs.setFeerate(newCew221ERs.getFeerate());

            cardStagesApplyModel.setFeeRate(billedDetail.getRate());
        }
        catch (ServiceException sex) {
            logger.error(sex.getMessage(), sex);
            aex = handleException(accessLogEntity, sex);
            cardStagesApplyModel.setAex(aex);
            ErrorDescription errDesOutput = IBUtils.getErrorDescMessage(errorCodeCacheManager, aex.getStatus(), locale, "");
            txnStatusType = getTxStatusType(sex);
            cardStagesApplyModel.setTxErrorCode(errDesOutput.getCode());
            cardStagesApplyModel.setTxErrorDesc(errDesOutput.getDesc());
            cardStagesApplyModel.setTxErrorSystemId(errDesOutput.getSys());
        }
        cardStagesApplyModel.setUpdateTime(new Date());
        cardStagesApplyModel.setHostTxTime(new Date());
        cardStagesApplyModel.setTxStatus(txnStatusType.getCode());
    }

    /**
     * 消費資訊有任一屬性是null 回true
     * 
     * @param txnData
     * @return
     */
    public boolean isRqTxnDataAnyEmpty(NCCQU008TxnDataRq txnData) {
        if (Objects.isNull(txnData)) {
            return true;
        }

        return Objects.isNull(txnData.getAmt()) || StringUtils.isAnyEmpty(txnData.getCardKey(), txnData.getTxnDate(), txnData.getBillDate(), txnData.getMemo(), txnData.getSeq(), txnData.getTxnType(), txnData.getCloseDate());
    }

    /**
     * 用CardKey查卡資料
     * 
     * @param user
     * @param locale
     * @param cardKey
     * @return
     * @throws ActionException
     */
    public CreditCard getCardNoByCardKey(AIBankUser user, Locale locale, String cardKey) throws ActionException {
        if (StringUtils.isBlank(cardKey)) {
            return null;
        }
        return userDataCacheService.getCreditCardByCardKey(user, cardKey, locale);
    }

    /**
     * 取信用卡會員email
     * 
     * @param custId
     * @param rsData
     * @throws ActionException
     */
    public void getCardEmail(String custId, NCCQU008060Rs rsData) {
        BaseServiceResponse<CEW302RRes> res = creditCardResource.sendCEW302R(custId);
        if (res.getStatus().isError()) {
            logger.error("== 取信用卡會員email 失敗 == errcode: " + res.getStatus().getErrorCode());
        }
        else {
            CEW302RRes cew302RRes = res.getData();
            rsData.setEmail(cew302RRes.getEmailAddress());
        }
    }

    /**
     * 發送CEW326R獲取每月預估金額
     *
     * @param custId
     * @param output
     */
    public void getMonthlyEstimateAmt(String custId, NCCQU008Output output) {
        CEW326RResponse response = creditCardResource.sendCEW326R(custId);
        List<CEW326RRepeat> repeats = response.getRepeats();
        List<NCCQU008MonthlyEstimateDetail> details = repeats.stream().map(r -> {
            BigDecimal unbillAmt = Optional.ofNullable(r.getUnbillAmount()).orElse(BigDecimal.ZERO);
            BigDecimal billAmt = Optional.ofNullable(r.getBillAmount()).orElse(BigDecimal.ZERO);
            BigDecimal monthTotal = unbillAmt.add(billAmt);

            NCCQU008MonthlyEstimateDetail detail = new NCCQU008MonthlyEstimateDetail();
            detail.setBillYearMonth(DateUtils.changeFormat(r.getBillYyyymm(), DateFormatUtils.SIMPLE_DATE_FORMAT_YEAR_MONTH.getPattern(), DateFormatUtils.CE_YEAR_MONTH_FORMAT.getPattern()));
            detail.setMonthTotal(monthTotal);
            detail.setMonthTotalDisplay(IBUtils.formatMoney(monthTotal));
            detail.setUnbillAmt(unbillAmt);
            detail.setUnbillAmtDisplay(IBUtils.formatMoney(unbillAmt));
            detail.setBillAmt(billAmt);
            detail.setBillAmtDisplay(IBUtils.formatMoney(billAmt));
            return detail;
        }).collect(Collectors.toList());

        NCCQU008MonthlyEstimate monthlyEstimate = new NCCQU008MonthlyEstimate();
        monthlyEstimate.setDetails(details);

        BigDecimal totalAmt = details.stream().map(NCCQU008MonthlyEstimateDetail::getMonthTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        monthlyEstimate.setTotalAmt(totalAmt);
        monthlyEstimate.setTotalAmtDisplay(IBUtils.formatMoney(totalAmt));
        output.setMonthlyEstimate(monthlyEstimate);
    }

    /**
     * 單筆分期尚未入帳消費區塊
     * 
     * @param user
     * @param cache
     * @param singleIntallments
     */
    public void getSingleIntallmentsInApplyNoGetMoney(String custId, List<CreditCard> effectiveCreditCards, NCCQU008CacheData cache, List<NCCQU008BilledDetailVo> output) {

        CEW222RResponse response = creditCardResource.sendCEW222R(custId);
        cache.setSingleIntallmentsInApplyNoGetMoney(response);
        List<NCCQU008BilledDetailVo> inApplyNoGetMoneyIntallments = response.getRepeats().stream().filter(repeat -> {
            return repeat.getNccday() == null;
        }).map(repeat -> {
            NCCQU008BilledDetailVo billedDetail = new NCCQU008BilledDetailVo();
            billedDetail.setIsPayBill(false);
            billedDetail.setPchDay(DateUtils.getCEDateStr(repeat.getPchday()));
            billedDetail.setPchTime(Optional.ofNullable(repeat.getPchday()).map(Date::getTime).orElse(0L));

            if (Objects.nonNull(repeat.getDesamt())) {
                billedDetail.setTxAmt(IBUtils.formatMoney(repeat.getDesamt(), 0, "$"));
                billedDetail.setTxAmtOrigin(repeat.getDesamt());
            }
            else {
                billedDetail.setTxAmt("$0");
                billedDetail.setTxAmtOrigin(BigDecimal.ZERO);
            }
            if (Objects.nonNull(repeat.getTxrate())) {
                billedDetail.setRate(repeat.getTxrate().divide(new BigDecimal(100), 2, RoundingMode.DOWN));
            }
            else {
                billedDetail.setRate(BigDecimal.ZERO);
            }

            // for 交易結果判斷使用哪隻電文做交易
            billedDetail.setHtxtId("CEW222R");
            CreditCard creditCardOrNull = effectiveCreditCards.stream().filter(card -> StringUtils.equals(StringUtils.substring(card.getCardNo(), -4), repeat.getCrdno4())).findAny().orElse(null);

            if (Objects.isNull(creditCardOrNull)) {
                billedDetail.setCardNo(" ····" + repeat.getCrdno4());
            }
            else {
                billedDetail.setCardNo(creditCardOrNull.getCardNo());
            }
            billedDetail.setIsApply(true);
            billedDetail.setIsGetMoney(repeat.getNccday() == null);
            return billedDetail;
        }).toList();
        output.addAll(inApplyNoGetMoneyIntallments);
    }

    /**
     * 判斷日期是否在系統日（包含）與系統日-60天之間
     * 
     * @param checkDate
     *            要檢查的日期
     * @return true如果在範圍內，否則false
     */
    private boolean isDateWithin60Days(Date checkDate) {
        if (checkDate == null) {
            return false;
        }

        // 取得系統日
        Date systemDate = new Date();

        // 計算系統日-60天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(systemDate);
        calendar.add(Calendar.DAY_OF_YEAR, -60);
        Date systemDateMinus60Days = calendar.getTime();

        // 因為between方法不包含邊界值，所以我們需要調整
        // 這裡我們需要檢查date是否在[systemDateMinus60Days, systemDate]範圍內
        // 所以我們需要檢查：
        // 1. checkDate等於systemDateMinus60Days或systemDate
        // 2. 或者checkDate在systemDateMinus60Days和systemDate之間

        // 檢查是否等於邊界值
        if (StringUtils.equals(DateUtils.getCEDateStr(systemDateMinus60Days), DateUtils.getCEDateStr(checkDate)) || StringUtils.equals(DateUtils.getCEDateStr(checkDate), DateUtils.getCEDateStr(systemDate))) {
            return true;
        }
        return DateUtils.between(checkDate, systemDateMinus60Days, systemDate);
    }

    /**
     * 帳單分期未
     * 
     * @param user
     * @param cache
     * @param singleIntallments
     * @throws ActionException
     */
    public void getBillIntallmentsInApplyNoGetMoney(AIBankUser user, NCCQU008CacheData cache, List<NCCQU008BillNotIntallmentsDisplay> output) throws ActionException {
        try {
            this.queryBillInstallment(user, cache);
        }
        catch (ActionException ex) {
            // #9018 此情境如果電文CEW315R HERRID=0000 不管wording 如何都不報錯
            CEW315RResponse response = cache.getInstallmentBillRs();
            if (StringUtils.notEquals(StringUtils.trimToEmptyEx(response.getHERRID()), "0000")) {
                throw ex;
            }
            logger.debug("Suppressed ActionException for HERRID=0000", ex);
        }
        CEW315RResponse cew315RResponse = cache.getInstallmentBillRs();
        List<NCCQU008BillNotIntallmentsDisplay> billNotIntallments = new ArrayList<>();
        // 由大到小排序
        if (StringUtils.isBlank(cew315RResponse.getWording())) {

            NCCQU008BillNotIntallmentsDisplay billNotIntallment = new NCCQU008BillNotIntallmentsDisplay();
            billNotIntallment.setBillYyyy(StringUtils.substring(cew315RResponse.getBillYyyymm(), 0, 4));
            billNotIntallment.setBillMm(StringUtils.substring(cew315RResponse.getBillYyyymm(), 4));
            // (1: 申請中, 2: 未申請)
            if (StringUtils.isBlank(cew315RResponse.getWording()) && StringUtils.equals(cew315RResponse.getStatus(), "A")) {
                billNotIntallment.setStatus("1");
            }
            else if (StringUtils.isBlank(cew315RResponse.getWording()) && StringUtils.equals(cew315RResponse.getStatus(), "N")) {
                billNotIntallment.setStatus("2");
            }
            billNotIntallment.setTxAmt(IBUtils.formatMoneyStr(cew315RResponse.getPaymentBalance(), 0));
            billNotIntallment.setTxAmtOrigin(cew315RResponse.getPaymentBalance());
            billNotIntallments.add(billNotIntallment);

        }

        output.addAll(billNotIntallments);
    }

    /**
     * 請款消費明細資料
     * 
     * @param nccqu008030Rs
     * @throws ActionException
     */
    public void getSingleBillIntallmentsGetMoney(AIBankUser user, NCCQU008CacheData cache, List<NCCQU008BilledDetailVo> output) throws ActionException {

        CEW205RResponse cew205RResponse = creditCardResource.sendCEW205R(user.getCustId(), "").getData();

        List<NCCQU008BilledDetailVo> billedDetails = cew205RResponse.getTxRepeats().stream().filter(res -> StringUtils.equalsAny(res.getOreFlag(), "S", "Y")).map(repeat -> {
            NCCQU008BilledDetailVo billedDetail = new NCCQU008BilledDetailVo();
            billedDetail.setIsPayBill(true);
            billedDetail.setPchDay(DateUtils.getCEDateStr(repeat.getNcbPchd()));
            billedDetail.setPchTime(Optional.ofNullable(repeat.getNcbPchd()).map(Date::getTime).orElse(0L));
            billedDetail.setBillDesc(repeat.getNcbTxt1());
            billedDetail.setTxAmt("$" + IBUtils.formatMoneyStr(repeat.getNcbTwd(), 0));
            billedDetail.setTxAmtOrigin(repeat.getNcbTwd());
            billedDetail.setNcsbsq(repeat.getNcSbsq());
            billedDetail.setNcgrop(repeat.getNcGrop());
            billedDetail.setNcseqn(repeat.getNcSeqn());
            billedDetail.setStagesType(repeat.getTxType());
            billedDetail.setCreditDate(repeat.getNcbNccd());
            billedDetail.setFxCur(repeat.getSrcCur());
            billedDetail.setFxAmt(repeat.getSrcAmt());
            billedDetail.setAccountDay(repeat.getNcbNccd());
            billedDetail.setCloseDay(repeat.getNcCday());
            billedDetail.setIsApply(StringUtils.equals(repeat.getOreFlag(), "S"));
            billedDetail.setIsGetMoney(true);
            billedDetail.setCardNo(repeat.getNcbCard());
            return billedDetail;
        }).collect(Collectors.toList());
        billedDetails.sort(Comparator.comparingLong(NCCQU008BilledDetailVo::getPchTime).reversed());
        output.addAll(billedDetails);
        cache.setCew205RResponse(cew205RResponse);
    }

    /**
     * 指定消費分期試算
     * 
     * @param cache
     * @param user
     * @param period
     * @return
     * @throws ActionException
     */
    private CEW220RResponse getCew220RRs(NCCQU008CacheData cache, AIBankUser user, String period) throws ActionException {
        CEW220RResponse cew220Response = new CEW220RResponse();
        if (cache.getBilledDetailSelect() != null && cache.getBilledDetailSelect().getIsPayBill()) {
            try {
                CEW220RRequest request = new CEW220RRequest();
                request.setTxtype(cache.getBilledDetailSelect().getStagesType());
                request.setTxamt(ConvertUtils.bigDecimal2Str(cache.getBilledDetailSelect().getTxAmtOrigin()));
                request.setTxstge(period);
                request.setNcbpchd(DateUtils.getROCDateStr(DateUtils.getCEDate(cache.getBilledDetailSelect().getPchDay()), ""));
                request.setTxacid(user.getCustId());

                request.setCustCdno(cache.getBilledDetailSelect().getCardNo());
                request.setNcsbsq(cache.getBilledDetailSelect().getNcsbsq());
                request.setNccday(DateUtils.getROCDateStr(cache.getBilledDetailSelect().getCreditDate(), ""));
                request.setNcgrop(cache.getBilledDetailSelect().getNcgrop());
                request.setNcseqn(cache.getBilledDetailSelect().getNcseqn());
                cew220Response = creditCardResource.calculationTransaction(request);
                if (StringUtils.equals(period, "12")) {
                    cache.getBilledDetailSelect().setRate(cew220Response.getFeerate());
                }
            }
            catch (ServiceException e) {
                throw ExceptionUtils.getActionException(ErrorCode.SINGLE_INS_CAL_ERROR);
            }
        }
        return cew220Response;
    }
}
