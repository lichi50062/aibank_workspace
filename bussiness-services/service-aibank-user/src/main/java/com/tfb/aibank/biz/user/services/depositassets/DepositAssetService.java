package com.tfb.aibank.biz.user.services.depositassets;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.ExchangeRateHistoryRepository;
import com.tfb.aibank.biz.user.repository.entities.ExchangeRateHistoryEntity;
import com.tfb.aibank.biz.user.services.depositassets.model.EB555692Repeat;
import com.tfb.aibank.biz.user.services.depositassets.model.EB555692Request;
import com.tfb.aibank.biz.user.services.depositassets.model.EB555692Response;
import com.tfb.aibank.biz.user.services.depositassets.model.InvAssetOverviewRequest;
import com.tfb.aibank.biz.user.services.depositassets.model.InvAssetOverviewResponse;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.component.userdatacache.model.HasTrustAcct;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.component.accountinfo.AccountInfo;
import com.tfb.aibank.component.accountinfo.AccountInfoCacheManager;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.AJW084RS;
import com.tfb.aibank.integration.eai.msg.BKDCD002RS;
import com.tfb.aibank.integration.eai.msg.EB555692RS;
import com.tfb.aibank.integration.eai.msg.GD320140RS;
import com.tfb.aibank.integration.eai.msg.NJW084RS;
import com.tfb.aibank.integration.eai.msg.NMP8YBRS;
import com.tfb.aibank.integration.eai.msg.SPWEBQ1RS;
import com.tfb.aibank.integration.eai.msg.UK084NRS;
import com.tfb.aibank.integration.eai.msg.VN084N1RS;
import com.tfb.aibank.integration.eai.msg.VN084NRS;

import tw.com.ibm.mf.eai.TxRepeatType;
import tw.com.ibm.mf.eb.AJW0840001RepeatType;
import tw.com.ibm.mf.eb.BKDCD002SvcRsType;
import tw.com.ibm.mf.eb.EB555692RepeatType;
import tw.com.ibm.mf.eb.EB555692SvcRsType;
import tw.com.ibm.mf.eb.GD320140D001RepeatType;
import tw.com.ibm.mf.eb.NJW084RepeatType;
import tw.com.ibm.mf.eb.NMP8YB0002RepeatType;
import tw.com.ibm.mf.eb.SPWEBQ10001RepeatType;
import tw.com.ibm.mf.eb.UK084NRepeatType;
import tw.com.ibm.mf.eb.VN084N1RepeatType;
import tw.com.ibm.mf.eb.VN084NRepeatType;

// @formatter:off
/**
 * @(#)AssetsService.java
 * 
 * <p>Description:使用者各項資產總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DepositAssetService {

    private static final IBLog logger = IBLog.getLog(DepositAssetService.class);

    private EsbChannelGateway esbGateway;

    private AccountInfoCacheManager acctInfoCache;

    private ExchangeRateHistoryRepository exchangeRateHistoryRepository;

    private static final String NO_DATA_FLAG = "noDataFlag";

    private static final String ERROR_CODE = "errorCode";

    private static final String TOTAL_AMT_TWD = "totalAmtTwd";

    private static final String TOTAL_AMT_FRG = "totalAmtFrg";

    public DepositAssetService(EsbChannelGateway esbGateway, AccountInfoCacheManager accountInfoCacheManager, ExchangeRateHistoryRepository exchangeRateHistoryRepository) {
        this.esbGateway = esbGateway;
        this.acctInfoCache = accountInfoCacheManager;
        this.exchangeRateHistoryRepository = exchangeRateHistoryRepository;
    }

    /**
     * 取得使用者語系
     * 
     * @return
     */
    private String getUserLocale() {
        String userLocale = IBUtils.getExecContext().get(MDCKey.locale.name());
        if (StringUtils.isBlank(userLocale)) {
            userLocale = Locale.TAIWAN.toString();
        }
        return userLocale;
    }

    /**
     * 網路銀行歸戶資產查詢 for 首頁存款總覽牌卡
     *
     * @param request
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     * @throws ActionException
     */
    public EB555692Response sendEB555692ForDepositAmount(EB555692Request request) throws XmlException, EAIException, EAIResponseException, ActionException {
        request.setHTLID("2004115");
        request.setFunction("06");
        request.setType("7");
        EB555692RS esbRs = this.esbGateway.sendEB555692(request);
        logger.debug("esbRs.getErrorCode(): {}", esbRs.getErrorCode());

        Stream<String> noDataRespCodes = Stream.of(EB555692RS.ERRID_AS_NODATA.split(","));
        EB555692Response response = new EB555692Response();

        if (noDataRespCodes.anyMatch(esbRs.getErrorCode()::equals)) {
            response.setNoDataFlag(StringUtils.Y);
            response.setErrorCode(esbRs.getErrorCode());
            return response;
        }

        EB555692SvcRsType rsBody = esbRs.getBodyAsType(EB555692SvcRsType.class);

        List<TxRepeatType> txRepeatTypeList = rsBody.getTxRepeatList();
        List<EB555692Repeat> repeatsForUse = new ArrayList<>();
        Set<String> alertAccounts = filterAlertAccounts(txRepeatTypeList);
        for (var txRepeatType : txRepeatTypeList) {
            EB555692RepeatType repeatType = (EB555692RepeatType) txRepeatType.changeType(EB555692RepeatType.type);
            AccountInfo accountInfo = acctInfoCache.getAccountInfo(repeatType.getPRODCODE(), repeatType.getPRODSUBCODE(), getUserLocale());
            // String type = accountInfo == null ? StringUtils.EMPTY : accountInfo.getAccountType();
            if (isRecordToCollect(request.getNameCode(), accountInfo, alertAccounts, repeatType)) {
                EB555692Repeat repeat = new EB555692Repeat(repeatType, accountInfo, alertAccounts);
                repeatsForUse.add(repeat);
                logger.debug("repeat to add: {}", IBUtils.attribute2Str(repeat));
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("== repeatsForUse ==: {}", IBUtils.attribute2Str(repeatsForUse));
        }

        if (CollectionUtils.isEmpty(repeatsForUse)) {
            response.setNoDataFlag(StringUtils.Y);
            response.setErrorCode("");
            return response;
        }

        Predicate<EB555692Repeat> isTWDVal = rt -> Stream.of("10", "20", "31", "32").anyMatch(rt.getType()::equals) && CurrencyCode.TWD.equals(rt.getCurCod());

        Predicate<EB555692Repeat> isFRGVal = rt -> Stream.of("40", "50", "33", "34").anyMatch(rt.getType()::equals) && !CurrencyCode.TWD.equals(rt.getCurCod());

        BigDecimal totalAmtTwd = BigDecimal.ZERO;
        BigDecimal totalAmtFrg = BigDecimal.ZERO;

        if (CollectionUtils.isNotEmpty(repeatsForUse)) {

            Set<String> cursNoTWD = repeatsForUse.stream().filter(rfu -> !StringUtils.equals("TWD", rfu.getCurCod())).map(EB555692Repeat::getCurCod).collect(Collectors.toSet());

            Map<String, BigDecimal> curRateMap = getExRateHistoryMap(cursNoTWD);

            for (EB555692Repeat repeatType : repeatsForUse) {
                if (isTWDVal.test(repeatType) && !repeatType.isAlertAccount()) {
                    logger.debug("=isTWDVal= CurAmt : {}", repeatType.getCurAmt());
                    BigDecimal curAmt = NumberUtils.defaultValue(repeatType.getCurAmt(), BigDecimal.ZERO);
                    if (curAmt.compareTo(BigDecimal.ZERO) == -1) {
                        curAmt = BigDecimal.ZERO;
                    }
                    totalAmtTwd = totalAmtTwd.add(curAmt);
                }
                else if (isFRGVal.test(repeatType) && !repeatType.isAlertAccount()) {
                    logger.debug("=isFRGVal= CurAmt : {}", repeatType.getCurAmt());
                    BigDecimal curAmt = NumberUtils.defaultValue(repeatType.getCurAmt(), BigDecimal.ZERO);
                    if (curAmt.compareTo(BigDecimal.ZERO) > 0) {
                        totalAmtFrg = totalAmtFrg.add(calToNTDVal(repeatType.getCurCod(), curAmt, curRateMap));
                    }
                }
            }
        }

        response.setTotalAmtNtd(totalAmtTwd);
        response.setTotalAmtFrg(totalAmtFrg);
        response.setTotalAmt(totalAmtTwd.add(totalAmtFrg));
        return response;
    }

    /**
     * 網路銀行歸戶資產查詢 for 資產負債分析 外幣為負使用0計算
     *
     * @param request
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     * @throws ActionException
     */
    public EB555692Response sendEB555692FrgUseZeroIfNegative(EB555692Request request) throws XmlException, EAIException, EAIResponseException, ActionException {
        List<EB555692Repeat> repeatsForUse = new ArrayList<>();
        Map<String, String> errorCodeMap = new HashMap<>();
        extractEB555692RepeatList(request, errorCodeMap, repeatsForUse);

        EB555692Response response = new EB555692Response();
        if (!errorCodeMap.isEmpty()) {
            response.setNoDataFlag(errorCodeMap.get(NO_DATA_FLAG));
            response.setErrorCode(errorCodeMap.get(ERROR_CODE));
            return response;
        }

        Map<String, BigDecimal> valuesContainer = new HashMap<>();
        valuesContainer.put(TOTAL_AMT_TWD, BigDecimal.ZERO);
        valuesContainer.put(TOTAL_AMT_FRG, BigDecimal.ZERO);
        calculateValues(repeatsForUse, valuesContainer, calToNTDValUseZeroIfNegative);

        BigDecimal totalAmtTwd = valuesContainer.getOrDefault(TOTAL_AMT_TWD, BigDecimal.ZERO);
        BigDecimal totalAmtFrg = valuesContainer.getOrDefault(TOTAL_AMT_FRG, BigDecimal.ZERO);
        response.setTotalAmtNtd(totalAmtTwd);
        response.setTotalAmtFrg(totalAmtFrg);
        response.setTotalAmt(totalAmtTwd.add(totalAmtFrg));
        return response;
    }

    private void extractEB555692RepeatList(EB555692Request request, Map<String, String> errorCodeMap, List<EB555692Repeat> repeatsForUse) throws XmlException, EAIException, EAIResponseException {
        request.setHTLID("2004115");
        request.setFunction("06");
        request.setType("7");
        EB555692RS esbRs = this.esbGateway.sendEB555692(request);
        logger.debug("esbRs.getErrorCode(): {}", esbRs.getErrorCode());

        Stream<String> noDataRespCodes = Stream.of(EB555692RS.ERRID_AS_NODATA.split(","));

        if (noDataRespCodes.anyMatch(esbRs.getErrorCode()::equals)) {
            errorCodeMap.put(NO_DATA_FLAG, StringUtils.Y);
            errorCodeMap.put(ERROR_CODE, esbRs.getErrorCode());
            return;
        }

        EB555692SvcRsType rsBody = esbRs.getBodyAsType(EB555692SvcRsType.class);

        List<TxRepeatType> txRepeatTypeList = rsBody.getTxRepeatList();
        Set<String> alertAccounts = filterAlertAccounts(txRepeatTypeList);
        for (var txRepeatType : txRepeatTypeList) {
            EB555692RepeatType repeatType = (EB555692RepeatType) txRepeatType.changeType(EB555692RepeatType.type);
            AccountInfo accountInfo = acctInfoCache.getAccountInfo(repeatType.getPRODCODE(), repeatType.getPRODSUBCODE(), getUserLocale());
            if (isRecordToCollect(request.getNameCode(), accountInfo, alertAccounts, repeatType)) {
                EB555692Repeat repeat = new EB555692Repeat(repeatType, accountInfo, alertAccounts);
                repeatsForUse.add(repeat);
                logger.debug("repeat to add: {}", IBUtils.attribute2Str(repeat));
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("== repeatsForUse == : {}", IBUtils.attribute2Str(repeatsForUse));
        }

        if (CollectionUtils.isEmpty(repeatsForUse)) {
            errorCodeMap.put(NO_DATA_FLAG, StringUtils.Y);
            errorCodeMap.put(ERROR_CODE, StringUtils.EMPTY);
        }
    }

    private void calculateValues(List<EB555692Repeat> repeatsForUse, Map<String, BigDecimal> valuesContainer, CalToNTDVal<String, BigDecimal, Map<String, BigDecimal>, BigDecimal> calculateFunction) {
        if (CollectionUtils.isNotEmpty(repeatsForUse)) {
            Predicate<EB555692Repeat> isTWDVal = rt -> Stream.of("10", "20", "31", "32").anyMatch(rt.getType()::equals) && CurrencyCode.TWD.equals(rt.getCurCod());
            Predicate<EB555692Repeat> isFRGVal = rt -> Stream.of("40", "50", "33", "34").anyMatch(rt.getType()::equals) && !CurrencyCode.TWD.equals(rt.getCurCod());

            BigDecimal totalAmtTwd = valuesContainer.getOrDefault(TOTAL_AMT_TWD, BigDecimal.ZERO);
            BigDecimal totalAmtFrg = valuesContainer.getOrDefault(TOTAL_AMT_FRG, BigDecimal.ZERO);

            Set<String> cursNoTWD = repeatsForUse.stream().filter(rfu -> !StringUtils.equals("TWD", rfu.getCurCod())).map(EB555692Repeat::getCurCod).collect(Collectors.toSet());

            Map<String, BigDecimal> curRateMap = getExRateHistoryMap(cursNoTWD);

            for (EB555692Repeat repeatType : repeatsForUse) {
                if (isTWDVal.test(repeatType) && !repeatType.isAlertAccount()) {
                    logger.debug("=isTWDVal= CurAmt : {}", repeatType.getCurAmt());
                    BigDecimal curAmt = NumberUtils.defaultValue(repeatType.getCurAmt(), BigDecimal.ZERO);
                    if (curAmt.compareTo(BigDecimal.ZERO) < 0) {
                        curAmt = BigDecimal.ZERO;
                    }
                    totalAmtTwd = totalAmtTwd.add(curAmt);
                }
                else if (isFRGVal.test(repeatType) && !repeatType.isAlertAccount()) {
                    totalAmtFrg = totalAmtFrg.add(calculateFunction.apply(repeatType.getCurCod(), repeatType.getCurAmt(), curRateMap));
                }
            }

            valuesContainer.put(TOTAL_AMT_TWD, totalAmtTwd);
            valuesContainer.put(TOTAL_AMT_FRG, totalAmtFrg);
        }
    }

    /**
     * 取得警示戶帳號Set 幣別為XXX => 主帳號 當alertcount > 0 時為警示戶
     */
    private Set<String> filterAlertAccounts(List<TxRepeatType> txRepeatTypeList) {
        Set<String> alertAccounts = new HashSet<>();

        if (CollectionUtils.isEmpty(txRepeatTypeList))
            return alertAccounts;

        for (TxRepeatType txRepeatType : txRepeatTypeList) {
            EB555692RepeatType repeatType = (EB555692RepeatType) txRepeatType.changeType(EB555692RepeatType.type);
            if (AIBankConstants.MAIN_ACCOUNT_CUR_CODE.equals(repeatType.getCURCOD()) && NumberUtils.defaultValue(repeatType.getAlertcount(), BigDecimal.ZERO).compareTo(BigDecimal.ZERO) == 1) {
                alertAccounts.add(repeatType.getACNO());
            }
        }
        return alertAccounts;
    }

    // @formatter:off
    /**
     * (G) 依據取得資料，進行篩選，<br>
     * a. 排除上行的USER_NO與EB555692_Rs.NAME_CODE不同的資料<br>
     * b. 排除EB555692_Rs.WA-LN-BC-TYPE(債清註記)有值的資料<br>
     * c. 排除EB555692_Rs.WA-LN-BC-STAG(協商註記)有值的資料<br>
     * d. 排除CUR_COD為XXX的資料<br>
     * e. 排除EB555692_Rs.PROD CODE不為1,3開頭<br>
     * f. <p>排除ACCOUNT_TYPE和ACCOUNT__SUB_TYPE為以下三種情形者
     * <ol>
     *   <li>ACCOUNT_TYPE = 10, ACCOUNT_SUB_TYPE = 14</li>
     *   <li>ACCOUNT_TYPE = 20, ACCOUNT_SUB_TYPE = 11</li>
     *   <li>ACCOUNT_TYPE = 20, ACCOUNT_SUB_TYPE = 12</li>
     * </ol>
     * </p>
     * (!!) 帳戶TYPE類別判斷， a. 以EB555692_Rs.PROD-CODE、EB555692_Rs.PROD-SUB-CODE至資料庫ACCOUNT_INFO取得TYPE。<br>
     */
    // @formatter:on
    private boolean isRecordToCollect(String nameCode, AccountInfo accountInfo, Set<String> alertAccounts, EB555692RepeatType repeatType) {
        logger.debug("isRecordToCollect -> accountInfo: {}; alertAccounts: {}", IBUtils.attribute2Str(accountInfo), alertAccounts);
        // [a]
        if (!StringUtils.equals(nameCode, repeatType.getNAMECODE())) {
            logger.debug("Namecode not equals -> nameCode: {}; repeatType.getNAMECODE(): {}", nameCode, repeatType.getNAMECODE());
            return false;
        }
        // b. , c.
        if (StringUtils.isNotEmpty(StringUtils.trimToEmptyEx(repeatType.getWALNBCTYPE())) || StringUtils.isNotEmpty(StringUtils.trimToEmptyEx(repeatType.getWALNBCSTAG()))) {
            logger.debug("WALNBCTYPE or WALNBCSTAG not empty -> WALNBCTYPE: {}; WALNBCSTAG: {}", repeatType.getWALNBCTYPE(), repeatType.getWALNBCSTAG());
            return false;
        }

        // d. 排除CUR_COD為XXX的資料
        logger.debug("CURCOD = XXX -> getCURCOD: {}", repeatType.getCURCOD());
        if (AIBankConstants.MAIN_ACCOUNT_CUR_CODE.equals(repeatType.getCURCOD())) {
            return false;
        }

        // e. 排除EB555692_Rs.PROD CODE不為1,3開頭
        logger.debug("PRODCODE = XXX -> getCURCOD: {}", repeatType.getPRODCODE());
        if (!repeatType.getPRODCODE().startsWith("1") && !repeatType.getPRODCODE().startsWith("3")) {
            return false;
        }

        if (Objects.isNull(accountInfo)) {
            logger.warn("ACNO: [{}]'s accountInfo type is empty can't check valid", repeatType.getACNO());
        }

        /*
         * f. <p>排除ACCOUNT_TYPE和ACCOUNT__SUB_TYPE為以下三種情形者 <ol> <li>ACCOUNT_TYPE = 10, ACCOUNT_SUB_TYPE = 14</li> <li>ACCOUNT_TYPE = 20, ACCOUNT_SUB_TYPE = 11</li> <li>ACCOUNT_TYPE = 20, ACCOUNT_SUB_TYPE = 12</li> </ol>
         */
        String accountType = Objects.nonNull(accountInfo) ? accountInfo.getAccountType() : "";
        String accountSubType = Objects.nonNull(accountInfo) ? accountInfo.getAccountSubType() : "";
        logger.debug("ACCOUNT_TYPE : {} 和 ACCOUNT__SUB_TYPE: {}", accountType, accountSubType);

        if (("10".equals(accountType) && "14".equals(accountSubType)) || ("20".equals(accountType) && "11".equals(accountSubType)) || ("20".equals(accountType) && "12".equals(accountSubType))) {
            return false;
        }
        // e. 排除警示戶的資料 [不用了]
        /*
         * if (alertAccounts.contains(repeatType.getACNO()) || alertAccounts.contains(repeatType.getACNOSA())) { logger.debug("alertAccounts -> ACNO: {}; ACNOSA: {}", repeatType.getACNO(), repeatType.getACNOSA()); return false; }
         */

        // [不用了] d. 排除TYPE=66 且EB555692_Rs.LN_TYP=01(呆帳)、TYPE=66 且EB555692_Rs.LN_TYP =02(逾催戶)有值的資料
        /*
         * if ("66".equals(type) && Stream.of("01", "02").anyMatch(repeatType.getLNTYP()::equals)) { logger.debug("type = 66 && LNTYP in (01,02) -> type: {}: lntyp: {}", type, repeatType.getLNTYP()); return false; }
         */
        // [不用了] g. 排除TYPE=63且EB555692_Rs.PROD-CODE='5200'且EB555692_Rs.PROD-SUB-CODE='5000'的資料。<br>
        /*
         * if ("63".equals(type) && "5200".equals(repeatType.getPRODCODE()) && "5000".equals(repeatType.getPRODSUBCODE())) { logger.debug("type = 63 -> PRODCODE: {}: PRODSUBCODE: {}", repeatType.getPRODCODE(), repeatType.getPRODSUBCODE()); return false; }
         */
        return true;
    }

    private List<ExchangeRateHistoryEntity> getExchangeRateHistoryEntities() {
        Date startDate = DateUtils.addDay(new Date(), -10);
        Date endDate = DateUtils.addDay(new Date(), 1);
        DateUtils.clearTime(startDate);
        DateUtils.clearTime(endDate);
        return exchangeRateHistoryRepository.findByTxTimeBetween(startDate, endDate);
    }

    private Predicate<ExchangeRateHistoryEntity> getExRateHisPredicate(String curCode) {
        return (exRate) -> "0".equals(exRate.getRateFlag()) && "0".equals(exRate.getRateType()) && curCode.equals(exRate.getCurrencyEname1());
    }

    private Map<String, BigDecimal> getExRateHistoryMap(Set<String> curCodes) {
        Map<String, BigDecimal> curRateMap = new HashMap<>();

        List<ExchangeRateHistoryEntity> exchangeRateHis = getExchangeRateHistoryEntities();

        if (CollectionUtils.isNotEmpty(exchangeRateHis)) {
            for (String curCode : curCodes) {
                List<ExchangeRateHistoryEntity> curRateHiss = exchangeRateHis.stream().filter(getExRateHisPredicate(curCode)).collect(Collectors.toList());
                if (logger.isDebugEnabled()) {
                    logger.debug(" == getExRateHistoryMap, curCode: {}, curRateHiss ==, {}", curCode, IBUtils.attribute2Str(curRateHiss));
                }
                if (CollectionUtils.isNotEmpty(curRateHiss)) {
                    IBUtils.sort(curRateHiss, new String[] { "txTime" }, new boolean[] { true });
                    curRateMap.put(curCode, curRateHiss.get(0).getBuy());
                }
            }
        }
        logger.debug(" == getExRateHistoryMap curRateMap ==, {}", curRateMap);
        return curRateMap;
    }

    /**
     * 發查NR048N(股票歷史交易明細)/N8048N(股票定期定額歷史交易明細)電文
     *
     * @param req
     * @return
     * @throws ActionException
     */
    public boolean hasTrustAcct(HasTrustAcct req) throws XmlException, EAIException, EAIResponseException {
        boolean hasAct = this.esbGateway.sendNR048NOverview(req);
        if (!hasAct)
            hasAct = this.esbGateway.sendN8048NOverview(req);

        return hasAct;
    }

    public InvAssetOverviewResponse queryAllAssetsAmount(InvAssetOverviewRequest req) {
        InvAssetOverviewResponse response = new InvAssetOverviewResponse();

        // Stock
        queryAssetStockAmount(req, response);

        if (StringUtils.isNotEmpty(req.getDbuObu())) {
            // 基金
            if (InvAssetOverviewRequest.DBU.equals(req.getDbuObu())) {
                queryAssetFundDBUAmount(req, response);
            }
            if (InvAssetOverviewRequest.OBU.equals(req.getDbuObu())) {
                queryAssetFundOBUAmount(req, response);
            }
            // 債券
            if (InvAssetOverviewRequest.DBU.equals(req.getDbuObu())) {
                queryAssetBondDBUAmount(req, response);
            }
            if (InvAssetOverviewRequest.OBU.equals(req.getDbuObu())) {
                queryAssetBondOBUAmount(req, response);
            }
        }

        // NMP8YB 奈米投電文
        queryAssetNanoInvAmount(req, response);

        // SPWEBQ1 組合式商品資產總覽(AS400 DCI+SI)
        queryDCISIAmount(req, response);

        // BKDCD002 組合式商品資產總覽(DCI)
        queryDCIAmount(req, response);

        // GD320140 黃金
        queryGoldAmount(req, response);

        return response;
    }

    /**
     * 發送取得 UK084N 股票資產總覽
     */
    public void queryAssetStockAmount(InvAssetOverviewRequest req, InvAssetOverviewResponse response) {
        logger.info("== start queryAssetStockAmount ==");
        String MSG_FOR_ERR_LOG = "sendUK084N err: {}";
        String resultMsg = "success";
        BigDecimal amount = NumberUtils.createBigDecimal("0");

        try {
            UK084NRS rs = this.esbGateway.sendUK084N(req);
            List<UK084NRepeatType> repeats = rs.getRepeatAsType(UK084NRepeatType.class);
            if (CollectionUtils.isNotEmpty(repeats)) {
                amount = repeats.stream().filter(r -> null != r.getAmount()).map(UK084NRepeatType::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            logger.info("queryAssetStockAmount, value: {}", amount);
            response.getEsbValueMap().put(UK084NRS.class.getName(), amount);
            response.setTotalAmount(response.getTotalAmount().add(amount));
        }
        catch (XmlException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIResponseException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        finally {
            response.getEsbResultMap().put(UK084NRS.class.getName(), resultMsg);
        }
    }

    /**
     * 發送取得 VN084N 基金資產總覽
     */
    public void queryAssetFundDBUAmount(InvAssetOverviewRequest req, InvAssetOverviewResponse response) {
        logger.info("== start queryAssetFundDBUAmount ==");
        String MSG_FOR_ERR_LOG = "sendVN084N err: {}";
        String resultMsg = "success";
        BigDecimal amount = NumberUtils.createBigDecimal("0");

        try {
            VN084NRS rs = this.esbGateway.sendVN084N(req);
            List<VN084NRepeatType> repeats = rs.getRepeatAsType(VN084NRepeatType.class);
            if (CollectionUtils.isNotEmpty(repeats)) {
                amount = repeats.stream().filter(r -> null != r.getAmount()).map(r -> r.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            logger.info("queryAssetFundDBUAmount, value: {}", amount);
            response.getEsbValueMap().put(VN084NRS.class.getName(), amount);
            response.setTotalAmount(response.getTotalAmount().add(amount));
        }
        catch (XmlException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIResponseException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        finally {
            response.getEsbResultMap().put(VN084NRS.class.getName(), resultMsg);
        }
    }

    /**
     * 發送取得 VN084N1 基金OBU資產總覽
     */
    public BigDecimal queryAssetFundOBUAmount(InvAssetOverviewRequest req, InvAssetOverviewResponse response) {
        logger.info("== start queryAssetFundOBUAmount ==");
        String MSG_FOR_ERR_LOG = "sendVN084N1 err: {}";
        String resultMsg = "success";
        BigDecimal amount = NumberUtils.createBigDecimal("0");

        try {
            VN084N1RS rs = this.esbGateway.sendVN084N1(req);
            List<VN084N1RepeatType> repeats = rs.getRepeatAsType(VN084N1RepeatType.class);
            if (CollectionUtils.isNotEmpty(repeats)) {
                amount = repeats.stream().filter(r -> null != r.getAmount()).map(r -> r.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            logger.info("queryAssetFundOBUAmount, value: {}", amount);
            response.getEsbValueMap().put(VN084N1RS.class.getName(), amount);
            response.setTotalAmount(response.getTotalAmount().add(amount));
        }
        catch (XmlException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIResponseException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        finally {
            response.getEsbResultMap().put(VN084N1RS.class.getName(), resultMsg);
        }
        return amount;
    }

    /**
     * 發送取得 NJW084 債券資產總覽
     */
    private void queryAssetBondDBUAmount(InvAssetOverviewRequest req, InvAssetOverviewResponse response) {
        logger.info("== start queryAssetBondDBUAmount ==");
        String MSG_FOR_ERR_LOG = "sendNJW084 err: {}";
        String resultMsg = "success";
        BigDecimal amount = NumberUtils.createBigDecimal("0");

        try {
            NJW084RS rs = this.esbGateway.sendNJW084(req);
            List<NJW084RepeatType> repeats = rs.getRepeatAsType(NJW084RepeatType.class);
            if (CollectionUtils.isNotEmpty(repeats)) {
                // 有登入和未登入會取不一樣的值
                if (req.isLoggedIn()) {
                    amount = repeats.stream().filter(r -> null != r.getBondAmt1()).map(r -> r.getBondAmt1()).reduce(BigDecimal.ZERO, BigDecimal::add);
                }
                else {
                    amount = repeats.stream().filter(r -> null != r.getBondAmt2()).map(r -> r.getBondAmt2()).reduce(BigDecimal.ZERO, BigDecimal::add);
                }
            }
            logger.info("queryAssetBondDBUAmount, value: {}", amount);
            response.getEsbValueMap().put(NJW084RS.class.getName(), amount);
            response.setTotalAmount(response.getTotalAmount().add(amount));
        }
        catch (XmlException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIResponseException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        finally {
            response.getEsbResultMap().put(NJW084RS.class.getName(), resultMsg);
        }
    }

    /**
     * 發送取得 AJW084 債券OBU資產總覽
     */
    private void queryAssetBondOBUAmount(InvAssetOverviewRequest req, InvAssetOverviewResponse response) {
        logger.info("== start queryAssetBondOBUAmount ==");
        String MSG_FOR_ERR_LOG = "sendAJW084 err: {}";
        String resultMsg = "success";
        BigDecimal amount = NumberUtils.createBigDecimal("0");
        try {
            AJW084RS rs = this.esbGateway.sendAJW084(req);
            List<AJW0840001RepeatType> repeats = rs.getRepeatAsType(AJW0840001RepeatType.class);
            if (CollectionUtils.isNotEmpty(repeats)) {
                // 有登入和未登入會取不一樣的值
                if (req.isLoggedIn()) {
                    amount = repeats.stream().filter(r -> null != r.getBondAmt1()).map(r -> r.getBondAmt1()).reduce(BigDecimal.ZERO, BigDecimal::add);
                }
                else {
                    amount = repeats.stream().filter(r -> null != r.getBondAmt2()).map(r -> r.getBondAmt2()).reduce(BigDecimal.ZERO, BigDecimal::add);
                }
            }
            logger.info("queryAssetBondOBUAmount, value: {}", amount);
            response.getEsbValueMap().put(AJW084RS.class.getName(), amount);
            response.setTotalAmount(response.getTotalAmount().add(amount));
        }
        catch (XmlException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIResponseException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        finally {
            response.getEsbResultMap().put(AJW084RS.class.getName(), resultMsg);
        }
    }

    /**
     * NMP8YB 奈米投電文
     */
    private void queryAssetNanoInvAmount(InvAssetOverviewRequest req, InvAssetOverviewResponse response) {
        logger.info("== start queryAssetNanoInvAmount ==");
        String MSG_FOR_ERR_LOG = "sendNMP8YB err: {}";
        String resultMsg = "success";
        BigInteger amount = NumberUtils.createBigInteger("0");

        try {
            NMP8YBRS rs = this.esbGateway.sendNMP8YB(req);
            List<NMP8YB0002RepeatType> repeats = rs.getRepeatAsType(NMP8YB0002RepeatType.class);
            if (CollectionUtils.isNotEmpty(repeats)) {
                amount = repeats.stream().filter(r -> null != r.getCurBalNT()).map(r -> r.getCurBalNT()).reduce(BigInteger.ZERO, BigInteger::add);
            }
            logger.info("queryAssetNanoInvAmount, value: {}", amount);
            BigDecimal amountDec = ConvertUtils.long2BigDecimal(amount.longValue());
            response.getEsbValueMap().put(AJW084RS.class.getName(), amountDec);
            response.setTotalAmount(response.getTotalAmount().add(amountDec));
        }
        catch (XmlException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIResponseException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        finally {
            response.getEsbResultMap().put(NMP8YBRS.class.getName(), resultMsg);
        }
    }

    /**
     * SPWEBQ1 組合式商品資產總覽(AS400 DCI+SI)
     */
    private void queryDCISIAmount(InvAssetOverviewRequest req, InvAssetOverviewResponse response) {
        logger.info("== start queryDCISIAmount ==");
        String MSG_FOR_ERR_LOG = "sendSPWEBQ1 err: {}";
        String resultMsg = "success";
        BigDecimal amount = NumberUtils.createBigDecimal("0");

        try {
            SPWEBQ1RS rs = this.esbGateway.sendSPWEBQ1(req);
            List<SPWEBQ10001RepeatType> repeats = rs.getRepeatAsType(SPWEBQ10001RepeatType.class);
            if (CollectionUtils.isNotEmpty(repeats)) {
                amount = repeats.stream().filter(r -> null != r.getAmount()).map(r -> r.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            logger.info("queryDCISIAmount, value: {}", amount);
            response.getEsbValueMap().put(SPWEBQ1RS.class.getName(), amount);
            response.setTotalAmount(response.getTotalAmount().add(amount));
        }
        catch (XmlException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIResponseException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        finally {
            response.getEsbResultMap().put(SPWEBQ1RS.class.getName(), resultMsg);
        }
    }

    /**
     * BKDCD002 組合式商品資產總覽(DCI)
     */
    private void queryDCIAmount(InvAssetOverviewRequest req, InvAssetOverviewResponse response) {
        logger.info("== start queryDCIAmount ==");
        String MSG_FOR_ERR_LOG = "sendBKDCD002 err: {}";
        String resultMsg = "success";
        BigDecimal amount = NumberUtils.createBigDecimal("0");

        try {
            BKDCD002RS rs = this.esbGateway.sendBKDCD002(req);

            BKDCD002SvcRsType rsBody = rs.getBodyAsType(BKDCD002SvcRsType.class);

            if (null != rsBody) {
                amount = rsBody.getAmount();
            }
            logger.info("queryDCIAmount, value: {}", amount);
            response.getEsbValueMap().put(BKDCD002RS.class.getName(), amount);
            response.setTotalAmount(response.getTotalAmount().add(amount));
        }
        catch (XmlException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIResponseException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        finally {
            response.getEsbResultMap().put(BKDCD002RS.class.getName(), resultMsg);
        }
    }

    private void queryGoldAmount(InvAssetOverviewRequest req, InvAssetOverviewResponse response) {
        logger.info("== start queryGoldAmount ==");
        String MSG_FOR_ERR_LOG = "sendGD320140 err: {}";
        String resultMsg = "success";
        BigDecimal amount = NumberUtils.createBigDecimal("0");

        try {
            GD320140RS rs = this.esbGateway.sendGD320140(req);

            List<GD320140D001RepeatType> repeats = rs.getRepeatAsType(GD320140D001RepeatType.class);
            if (CollectionUtils.isNotEmpty(repeats)) {
                amount = repeats.stream().filter(r -> null != r.getPVALUE()).map(r -> r.getPVALUE()).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            logger.info("queryGoldAmount, value: {}", amount);
            response.getEsbValueMap().put(GD320140RS.class.getName(), amount);
            response.setTotalAmount(response.getTotalAmount().add(amount));
        }
        catch (XmlException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        catch (EAIResponseException e) {
            resultMsg = MSG_FOR_ERR_LOG;
            logger.error(MSG_FOR_ERR_LOG, e.getMessage());
        }
        finally {
            response.getEsbResultMap().put(GD320140RS.class.getName(), resultMsg);
        }
    }

    /**
     * 計算外幣金額乘以匯率，取約當臺幣金額
     */
    private BigDecimal calToNTDVal(String curCode, BigDecimal actBal, Map<String, BigDecimal> curRateMap) {
        BigDecimal toNtdVal = BigDecimal.ZERO;
        if (curRateMap.containsKey(curCode)) {
            BigDecimal valueInNtd = actBal.multiply(curRateMap.get(curCode));
            toNtdVal = NumberUtils.setScale(valueInNtd, 0);
        }
        logger.debug("== calToNTDVal, curCode:{}, toNtdVal:{} ==", curCode, toNtdVal);
        return toNtdVal;
    }

    /**
     * 計算外幣金額乘以匯率，取約當臺幣金額 for 資產負債分析 外幣如果是負值, 以0計算
     */
    private CalToNTDVal<String, BigDecimal, Map<String, BigDecimal>, BigDecimal> calToNTDValUseZeroIfNegative = (curCode, actBal, curRateMap) -> {
        BigDecimal toNtdVal = BigDecimal.ZERO;
        if (curRateMap.containsKey(curCode)) {
            toNtdVal = actBal.multiply(curRateMap.get(curCode));
            if (toNtdVal.compareTo(BigDecimal.ZERO) >= 0) {
                toNtdVal = NumberUtils.setScale(toNtdVal, 0);
            }
        }
        logger.debug("== calToNTDVal, curCode:{}, toNtdVal:{} ==", curCode, toNtdVal);
        return toNtdVal;
    };
}
