package com.tfb.aibank.biz.user.services.bpm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.services.bpm.model.InvestmentDetailOverviewModel;
import com.tfb.aibank.biz.user.services.bpm.model.InvestmentOverviewModel;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.common.type.InvestItemType;
import com.tfb.aibank.common.type.InvestProductType;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIOverviewResponse;
import com.tfb.aibank.integration.eai.EAIOverviewTxBodyRsType;
import com.tfb.aibank.integration.eai.EAIResponseException;

import tw.com.ibm.mf.eai.TxRepeatType;
import tw.com.ibm.mf.eb.AJW0840001RepeatType;
import tw.com.ibm.mf.eb.AJW084SvcRsType;
import tw.com.ibm.mf.eb.BKDCD001RepeatType;
import tw.com.ibm.mf.eb.BKDCD001SvcRsType;
import tw.com.ibm.mf.eb.BKDCD002SvcRsType;
import tw.com.ibm.mf.eb.GD320140D001RepeatType;
import tw.com.ibm.mf.eb.GD320140SvcRsType;
import tw.com.ibm.mf.eb.N8084NRepeatType;
import tw.com.ibm.mf.eb.N8084NSvcRsType;
import tw.com.ibm.mf.eb.NJW084RepeatType;
import tw.com.ibm.mf.eb.NJW084SvcRsType;
import tw.com.ibm.mf.eb.NMP8YB0001RepeatType;
import tw.com.ibm.mf.eb.NMP8YBRepeatType;
import tw.com.ibm.mf.eb.NMP8YBSvcRsType;
import tw.com.ibm.mf.eb.SPWEBINQ0002RepeatType;
import tw.com.ibm.mf.eb.SPWEBINQSvcRsType;
import tw.com.ibm.mf.eb.SPWEBQ10001RepeatType;
import tw.com.ibm.mf.eb.SPWEBQ1SvcRsType;
import tw.com.ibm.mf.eb.SPWEBQ20001RepeatType;
import tw.com.ibm.mf.eb.SPWEBQ2SvcRsType;
import tw.com.ibm.mf.eb.UK084NRepeatType;
import tw.com.ibm.mf.eb.UK084NSvcRsType;
import tw.com.ibm.mf.eb.VN084N1RepeatType;
import tw.com.ibm.mf.eb.VN084N1SvcRsType;
import tw.com.ibm.mf.eb.VN084NRepeatType;
import tw.com.ibm.mf.eb.VN084NSvcRsType;

/**
 // @formatter:off
 * @(#)BPMService.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/1, Marty Pan
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BPMService {

    private static final IBLog logger = IBLog.getLog(BPMService.class);
    private EsbChannelGateway esbGateway;

    public BPMService(EsbChannelGateway esbGateway) {
        this.esbGateway = esbGateway;
    }

    public InvestmentOverviewModel getUserInvestOverviewValue(String cusid, String nameCode, boolean isDBU, boolean haveCreditCard) throws XmlException, EAIException, EAIResponseException {
        logger.debug("== getUserInvestOverviewValue == cusid: {}, nameCode: {}, isDBU: {}, haveCreditCard: {}", cusid, nameCode, isDBU, haveCreditCard);

        InvestmentOverviewModel model = new InvestmentOverviewModel();
        EAIOverviewResponse<?, ?> response;
        // 20250605 投資不查信用卡電文
        if (isDBU) {
//            if (haveCreditCard) {
//                response = esbGateway.sendBPM001Overview(cusid, nameCode);
//            }
//            else {
//                response = esbGateway.sendBPM002Overview(cusid, nameCode);
//            }
            response = esbGateway.sendBPM002Overview(cusid, nameCode);
        }
        else {// [OBU]
//            if (haveCreditCard) {
//                response = esbGateway.sendBPM003Overview(cusid, nameCode);
//            }
//            else {
//                response = esbGateway.sendBPM004Overview(cusid, nameCode);
//            }
            response = esbGateway.sendBPM004Overview(cusid, nameCode);
        }

        BigDecimal investTtlAmt = BigDecimal.ZERO;
        if (null == response)
            return model;

        Map<String, BigDecimal> productValMap = new HashMap<>();

        List<EAIOverviewTxBodyRsType> rsTypeList;
        // DBU => BPM001, BPM002
        if (isDBU) {
            // 基金 + 黃金存摺 + 海外ETF/海外股票 + 組合式商品 + 海外債 + 境外結構式商品 + 代銷基金
            /*
             * 基金：VN084N.Amount(DBU) 海外債券：NJW084.BondAmt1(DBU) 境外結構式商品： NJW084.BondAmt2(DBU)
             */
            rsTypeList = response.getBodyMap().get("VN084N");
            VN084NSvcRsType vn084NSvcRsType = rsTypeList.get(0).getTxBodyRs(VN084NSvcRsType.class);

            logger.debug("get [VN084N] EMSGID : {}", vn084NSvcRsType.getEMSGID());
            if (StringUtils.isEmpty(StringUtils.trimToEmptyEx(vn084NSvcRsType.getEMSGID()))) {
                if (vn084NSvcRsType.getOccur() > 0) {
                    VN084NRepeatType vn084nRepeatType = (VN084NRepeatType) vn084NSvcRsType.getTxRepeatArray(0).changeType(VN084NRepeatType.type);
                    productValMap.put("fundTtlAmt", NumberUtils.defaultValue(vn084nRepeatType.getAmount(), BigDecimal.ZERO));
                }
            }

            // DBU 抓 NJW084
            rsTypeList = response.getBodyMap().get("NJW084");
            NJW084SvcRsType njw084SvcRsType = rsTypeList.get(0).getTxBodyRs(NJW084SvcRsType.class);
            if (njw084SvcRsType.getOccur() > 0) {
                NJW084RepeatType njw084RepeatType = (NJW084RepeatType) njw084SvcRsType.getTxRepeatArray(0).changeType(NJW084RepeatType.type);
                productValMap.put("offBondsTtlAmt", NumberUtils.defaultValue(njw084RepeatType.getBondAmt1(), BigDecimal.ZERO));
                productValMap.put("offStructuredTtlAmt", NumberUtils.defaultValue(njw084RepeatType.getBondAmt2(), BigDecimal.ZERO));
            }
        }
        else {
            // OBU => BPM003, BPM004
            /*
             * 基金：VN084N1.Amount(OBU) 海外債券： AJW084. BondAmt1(OBU) 境外結構式商品： AJW084. BondAmt2(OBU)
             */
            rsTypeList = response.getBodyMap().get("VN084N1");
            VN084N1SvcRsType vn084N1SvcRsType = rsTypeList.get(0).getTxBodyRs(VN084N1SvcRsType.class);
            if (vn084N1SvcRsType.getOccur() > 0) {
                VN084N1RepeatType vn084n1RepeatType = (VN084N1RepeatType) vn084N1SvcRsType.getTxRepeatArray(0).changeType(VN084N1RepeatType.type);
                productValMap.put("fundTtlAmt", NumberUtils.defaultValue(vn084n1RepeatType.getAmount(), BigDecimal.ZERO));
            }
            // OBU 抓 AJW084
            rsTypeList = response.getBodyMap().get("AJW084");
            AJW084SvcRsType ajw084SvcRsType = rsTypeList.get(0).getTxBodyRs(AJW084SvcRsType.class);
            if (ajw084SvcRsType.getOccur() > 0) {
                AJW0840001RepeatType ajw084RepeatType = (AJW0840001RepeatType) ajw084SvcRsType.getTxRepeatArray(0).changeType(AJW0840001RepeatType.type);
                productValMap.put("offBondsTtlAmt", NumberUtils.defaultValue(ajw084RepeatType.getBondAmt1(), BigDecimal.ZERO));
                productValMap.put("offStructuredTtlAmt", NumberUtils.defaultValue(ajw084RepeatType.getBondAmt2(), BigDecimal.ZERO));
            }
        }

        /*
         * 黃金：EB120140.P_VALUE
         */
        // 黄金
        rsTypeList = response.getBodyMap().get("GD320140");
        GD320140SvcRsType gd320140SvcRsType = rsTypeList.get(0).getTxBodyRs(GD320140SvcRsType.class);
        // 應該只有一筆
        TxRepeatType[] txRepeatArray = gd320140SvcRsType.getTxRepeatArray();
        if (txRepeatArray.length > 0) {
            GD320140D001RepeatType gd320140D001RepeatType = (GD320140D001RepeatType) txRepeatArray[0].changeType(GD320140D001RepeatType.type);
            productValMap.put("goldTtlAmt", NumberUtils.defaultValue(gd320140D001RepeatType.getPVALUE(), BigDecimal.ZERO));
        }

        // 海外ETF/海外股票
        /*
         * 海外ETF/海外股票：UK084N.Amount + N8084N.AR101
         */
        rsTypeList = response.getBodyMap().get("UK084N");
        UK084NSvcRsType uk084nSvcRsType = rsTypeList.get(0).getTxBodyRs(UK084NSvcRsType.class);
        if (uk084nSvcRsType.getOccur() > 0) {
            UK084NRepeatType uk084nRepeatType = (UK084NRepeatType) uk084nSvcRsType.getTxRepeatArray(0).changeType(UK084NRepeatType.type);
            productValMap.put("offStocksTtlAmt", NumberUtils.defaultValue(uk084nRepeatType.getAmount(), BigDecimal.ZERO));
        }

        rsTypeList = response.getBodyMap().get("N8084N");
        N8084NSvcRsType n8084nSvcRsType = rsTypeList.get(0).getTxBodyRs(N8084NSvcRsType.class);
        if (n8084nSvcRsType.getOccur() > 0) {
            N8084NRepeatType n8084nRepeatType = (N8084NRepeatType) n8084nSvcRsType.getTxRepeatArray(0).changeType(N8084NRepeatType.type);
            if (productValMap.get("offStocksTtlAmt") == null) {
                productValMap.put("offStocksTtlAmt", NumberUtils.defaultValue(n8084nRepeatType.getAR101(), BigDecimal.ZERO));
            }
            else {
                productValMap.put("offStocksTtlAmt", productValMap.get("offStocksTtlAmt").add(NumberUtils.defaultValue(n8084nRepeatType.getAR101(), BigDecimal.ZERO)));
            }
        }

        // 組合式商品
        /*
         * 組合式商品：SPWEBQ1.Amount + BKDCD002.Amount
         */
        rsTypeList = response.getBodyMap().get("SPWEBQ1");
        SPWEBQ1SvcRsType spwebq1SvcRsType = rsTypeList.get(0).getTxBodyRs(SPWEBQ1SvcRsType.class);
        // EMSGID 有值時此電文無回傳內容，不取值
        logger.debug("get [SPWEBQ1] EMSGID : {}", spwebq1SvcRsType.getEMSGID());
        if (StringUtils.isEmpty(spwebq1SvcRsType.getEMSGID())) {
            txRepeatArray = spwebq1SvcRsType.getTxRepeatArray();
            if (txRepeatArray.length > 0) {
                SPWEBQ10001RepeatType spwebq1RepeatType = (SPWEBQ10001RepeatType) txRepeatArray[0].changeType(SPWEBQ10001RepeatType.type);
                productValMap.put("comboTtlAmt", NumberUtils.defaultValue(spwebq1RepeatType.getAmount(), BigDecimal.ZERO));
            }
        }
        // BKDCD002 有兩次
        rsTypeList = response.getBodyMap().get("BKDCD002");
        for (EAIOverviewTxBodyRsType rsType : rsTypeList) {
            BKDCD002SvcRsType bkdcd002SvcRsType = rsType.getTxBodyRs(BKDCD002SvcRsType.class);
            // EMSGID 有值時此電文無回傳內容，不取值
            if (StringUtils.isEmpty(bkdcd002SvcRsType.getEMSGID())) {
                if (null == productValMap.get("comboTtlAmt")) {
                    productValMap.put("comboTtlAmt", NumberUtils.defaultValue(bkdcd002SvcRsType.getAmount(), BigDecimal.ZERO));
                }
                else {
                    productValMap.put("comboTtlAmt", productValMap.get("comboTtlAmt").add(NumberUtils.defaultValue(bkdcd002SvcRsType.getAmount(), BigDecimal.ZERO)));
                }
            }
        }

        // 奈米投
        if (response.getBodyMap().containsKey("NMP8YB")) {
            rsTypeList = response.getBodyMap().get("NMP8YB");
            if (rsTypeList != null && rsTypeList.size() > 0) {
                NMP8YBSvcRsType nMP8YBSvcRsType = rsTypeList.get(0).getTxBodyRs(NMP8YBSvcRsType.class);
                if (nMP8YBSvcRsType.getOCCUR() > 0) {
                    NMP8YBRepeatType repeat = (NMP8YBRepeatType) nMP8YBSvcRsType.getTxRepeatArray(0).changeType(NMP8YBRepeatType.type);
                    productValMap.put("nanoTtlAmt", NumberUtils.defaultValue(repeat.getCurBalNT(), BigDecimal.ZERO));
                }
            }
        }

        // 投資 = 基金 + 黃金存摺 + 海外ETF/海外股票 + 組合式商品 + 海外債 + 境外結構式商品 + 代銷基金 + 奈米投
        logger.debug("productValMap: {}", IBUtils.attribute2Str(productValMap));
        investTtlAmt = productValMap.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        model.setInvestTtlAmt(investTtlAmt);
        model.setProductValMap(productValMap);
        return model;
    }

    /** NBOQU004 投資總覽 */
    public InvestmentOverviewModel getMarketValueGreater0(String cusid, String nameCode, boolean isDBU, boolean haveCreditCard) throws XmlException, EAIException, EAIResponseException {
        logger.debug("getMarketValueGreater0 cusid: {}, nameCode: {}, isDBU: {}, haveCreditCard: {}", cusid, nameCode, isDBU, haveCreditCard);

        InvestmentOverviewModel model = new InvestmentOverviewModel();

        EAIOverviewResponse<?, ?> response;
        if (isDBU) {
            if (haveCreditCard) {
                response = esbGateway.sendBPM001Overview(cusid, nameCode);
            }
            else {
                response = esbGateway.sendBPM002Overview(cusid, nameCode);
            }
        }
        else {// [OBU]
            if (haveCreditCard) {
                response = esbGateway.sendBPM003Overview(cusid, nameCode);
            }
            else {
                response = esbGateway.sendBPM004Overview(cusid, nameCode);
            }
        }

        BigDecimal investTtlAmt = BigDecimal.ZERO;
        if (null == response)
            return model;

        Map<InvestItemType, BigDecimal> productMap = new HashMap<>();

        // DBU => BPM001, BPM002
        if (isDBU) {
            // 基金 + 黃金存摺 + 海外ETF/海外股票 + 組合式商品 + 海外債 + 境外結構式商品 + 代銷基金
            // 基金：VN084N.Amount(DBU) 海外債券：NJW084.BondAmt1(DBU) 境外結構式商品： NJW084.BondAmt2(DBU)
            processVN084N(productMap, response.getBodyMap().get("VN084N"));

            // DBU 抓 NJW084
            processNJW084(productMap, response.getBodyMap().get("NJW084"));

        }
        else {
            // OBU => BPM003, BPM004
            // 基金：VN084N1.Amount(OBU) 海外債券： AJW084. BondAmt1(OBU) 境外結構式商品： AJW084. BondAmt2(OBU)
            processVN084N1(productMap, response.getBodyMap().get("VN084N1"));

            // OBU 抓 AJW084
            processAJW084(productMap, response.getBodyMap().get("AJW084"));
        }

        // 黃金：EB120140.P_VALUE
        processGD320140(productMap, response.getBodyMap().get("GD320140"));

        // 海外ETF/海外股票-單筆：UK084N.Amount
        processUK084N(productMap, response.getBodyMap().get("UK084N"));
        // 海外ETF/海外股票-定期定額：N8084N.AR101
        processN8084N(productMap, response.getBodyMap().get("N8084N"));

        // 組合式商品：SPWEBQ1.Amount + BKDCD002.Amount
        processSPWEBQ(productMap, response.getBodyMap().get("SPWEBQ1"));
        processBKDCD002(productMap, response.getBodyMap().get("BKDCD002"));

        // 奈米投
        processNMP8YB(productMap, response.getBodyMap().get("NMP8YB"));

        // 投資 = 基金 + 黃金存摺 + 海外ETF/海外股票 + 組合式商品 + 海外債 + 境外結構式商品 + 代銷基金 + 奈米投
        logger.debug("getMarketValueGreater0: {}", IBUtils.attribute2Str(productMap));
        investTtlAmt = productMap.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        model.setInvestTtlAmt(investTtlAmt);
        model.setProductTypeMap(productMap);
        return model;
    }

    public List<InvestmentDetailOverviewModel> getInvestmentDetailOverview(String custId, String namecode) throws XmlException, EAIException, EAIResponseException {
        EAIOverviewResponse<?, ?> response = esbGateway.sendBPM006Overview(custId, namecode);
        if (response == null)
            return Collections.emptyList();

        logger.info("====== BPM006 response ======");
        logger.info("{}", response);
        logger.info("bodyMap: {}", response.getBodyMap());
        List<InvestmentDetailOverviewModel> investmentDetailOverviewModels = new ArrayList<>();
        Map<String, List<EAIOverviewTxBodyRsType>> bodyMap = response.getBodyMap();
        List<EAIOverviewTxBodyRsType> rsTypeList;
        TxRepeatType[] txRepeatArray;

        // GOLD GD320140
        rsTypeList = bodyMap.get("GD320140");
        if (CollectionUtils.isNotEmpty(rsTypeList)) {
            GD320140SvcRsType gd320140SvcRsType = rsTypeList.get(0).getTxBodyRs(GD320140SvcRsType.class);
            txRepeatArray = gd320140SvcRsType.getTxRepeatArray();
            if (txRepeatArray.length > 0) {
                for (int i = 0; i < txRepeatArray.length; i++) {
                    GD320140D001RepeatType repeatType = (GD320140D001RepeatType) txRepeatArray[i].changeType(GD320140D001RepeatType.type);
                    InvestmentDetailOverviewModel goldModel = new InvestmentDetailOverviewModel(repeatType.getCUR(), repeatType.getPVALUE(), repeatType.getACNO(), InvestProductType.GOLD.name());
                    investmentDetailOverviewModels.add(goldModel);
                }
            }
        }

        // NANO NMP8YB
        rsTypeList = bodyMap.get("NMP8YB");
        if (CollectionUtils.isNotEmpty(rsTypeList)) {
            for (int j = 0; j < rsTypeList.size(); j++) {
                NMP8YBSvcRsType nmp8ybSvcRsType = rsTypeList.get(j).getTxBodyRs(NMP8YBSvcRsType.class);
                txRepeatArray = nmp8ybSvcRsType.getTxRepeatArray();
                if (txRepeatArray.length > 0) {
                    for (int i = 0; i < txRepeatArray.length; i++) {
                        NMP8YB0001RepeatType repeatType = (NMP8YB0001RepeatType) txRepeatArray[i].changeType(NMP8YB0001RepeatType.type);
                        String potId = repeatType.getPotId();
                        String currency = potId.startsWith("250900") ? CurrencyCode.USD : potId.startsWith("250901") ? CurrencyCode.TWD : StringUtils.EMPTY;
                        InvestmentDetailOverviewModel nanoModel = new InvestmentDetailOverviewModel(currency, new BigDecimal(repeatType.getMarketValTwd()), potId, InvestProductType.NANO.name());
                        investmentDetailOverviewModels.add(nanoModel);
                    }
                }
            }
        }

        // COMBO SPWEBINQ
        rsTypeList = bodyMap.get("SPWEBINQ");
        if (CollectionUtils.isNotEmpty(rsTypeList)) {
            SPWEBINQSvcRsType spwebinqSvcRsType = rsTypeList.get(0).getTxBodyRs(SPWEBINQSvcRsType.class);
            txRepeatArray = spwebinqSvcRsType.getTxRepeatArray();
            if (txRepeatArray.length > 0) {
                for (int i = 0; i < txRepeatArray.length; i++) {
                    SPWEBINQ0002RepeatType repeatType = (SPWEBINQ0002RepeatType) txRepeatArray[i].changeType(SPWEBINQ0002RepeatType.type);
                    InvestmentDetailOverviewModel spwebinqModel = new InvestmentDetailOverviewModel(repeatType.getCCY(), repeatType.getREFAMT(), repeatType.getPRDNAM(), InvestProductType.COMBO.name());
                    investmentDetailOverviewModels.add(spwebinqModel);
                }
            }
        }

        rsTypeList = bodyMap.get("SPWEBQ2");
        if (CollectionUtils.isNotEmpty(rsTypeList)) {
            SPWEBQ2SvcRsType spwebq2SvcRsType = rsTypeList.get(0).getTxBodyRs(SPWEBQ2SvcRsType.class);
            txRepeatArray = spwebq2SvcRsType.getTxRepeatArray();
            if (txRepeatArray.length > 0) {
                for (int i = 0; i < txRepeatArray.length; i++) {
                    SPWEBQ20001RepeatType repeatType = (SPWEBQ20001RepeatType) txRepeatArray[i].changeType(SPWEBQ20001RepeatType.type);
                    InvestmentDetailOverviewModel spwebq2Model = new InvestmentDetailOverviewModel(repeatType.getCurCode(), repeatType.getRefAmt(), "SPWEBQ2", InvestProductType.COMBO.name());
                    investmentDetailOverviewModels.add(spwebq2Model);
                }
            }
        }

        rsTypeList = bodyMap.get("BKDCD001");
        if (CollectionUtils.isNotEmpty(rsTypeList)) {
            BKDCD001SvcRsType bkdcd001SvcRsType = rsTypeList.get(0).getTxBodyRs(BKDCD001SvcRsType.class);
            txRepeatArray = bkdcd001SvcRsType.getTxRepeatArray();
            if (txRepeatArray.length > 0) {
                for (int i = 0; i < txRepeatArray.length; i++) {
                    BKDCD001RepeatType repeatType = (BKDCD001RepeatType) txRepeatArray[i].changeType(BKDCD001RepeatType.type);
                    InvestmentDetailOverviewModel bkdcd001Model = new InvestmentDetailOverviewModel(repeatType.getCURRENCY(), repeatType.getDCDCURAMOUNTNTD(), "BKDCD001", InvestProductType.COMBO.name());
                    investmentDetailOverviewModels.add(bkdcd001Model);
                }
            }
        }

        return investmentDetailOverviewModels;
    }

    private void processSPWEBQ(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = BigDecimal.ZERO;
        SPWEBQ1SvcRsType spwebq1SvcRsType = rsTypeList.get(0).getTxBodyRs(SPWEBQ1SvcRsType.class);
        // EMSGID 有值時此電文無回傳內容，不取值
        logger.debug("get [SPWEBQ1] EMSGID : {}", spwebq1SvcRsType.getEMSGID());
        if (StringUtils.isEmpty(spwebq1SvcRsType.getEMSGID())) {
            TxRepeatType[] txRepeatArray = spwebq1SvcRsType.getTxRepeatArray();
            if (txRepeatArray.length > 0) {
                SPWEBQ10001RepeatType spwebq1RepeatType = (SPWEBQ10001RepeatType) txRepeatArray[0].changeType(SPWEBQ10001RepeatType.type);
                totalAmt = NumberUtils.defaultValue(spwebq1RepeatType.getAmount(), BigDecimal.ZERO);
            }
        }

        if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
            productMap.put(InvestItemType.SIDCI, totalAmt);
        }
    }

    // BKDCD002 有兩次
    private void processBKDCD002(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = productMap.containsKey(InvestItemType.SIDCI) ? productMap.get(InvestItemType.SIDCI) : BigDecimal.ZERO;
        for (EAIOverviewTxBodyRsType rsType : rsTypeList) {
            BKDCD002SvcRsType bkdcd002SvcRsType = rsType.getTxBodyRs(BKDCD002SvcRsType.class);
            // EMSGID 有值時此電文無回傳內容，不取值
            logger.debug("get [BKDCD002] EMSGID : {}", bkdcd002SvcRsType.getEMSGID());
            if (StringUtils.isEmpty(bkdcd002SvcRsType.getEMSGID())) {
                totalAmt = totalAmt.add(NumberUtils.defaultValue(bkdcd002SvcRsType.getAmount(), BigDecimal.ZERO));
            }
        }

        if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
            productMap.put(InvestItemType.SIDCI, totalAmt);
        }
    }

    private void processNMP8YB(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = BigDecimal.ZERO;
        if (rsTypeList != null && rsTypeList.size() > 0) {
            NMP8YBSvcRsType nMP8YBSvcRsType = rsTypeList.get(0).getTxBodyRs(NMP8YBSvcRsType.class);
            if (nMP8YBSvcRsType.getOCCUR() > 0) {
                NMP8YBRepeatType repeat = (NMP8YBRepeatType) nMP8YBSvcRsType.getTxRepeatArray(0).changeType(NMP8YBRepeatType.type);

                totalAmt = NumberUtils.defaultValue(repeat.getCurBalNT(), BigDecimal.ZERO);
                if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                    productMap.put(InvestItemType.NMI, totalAmt);
                }
            }
        }
    }

    // 海外ETF 單筆
    private void processUK084N(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = BigDecimal.ZERO;
        UK084NSvcRsType uk084nSvcRsType = rsTypeList.get(0).getTxBodyRs(UK084NSvcRsType.class);
        if (uk084nSvcRsType.getOccur() > 0) {
            UK084NRepeatType uk084nRepeatType = (UK084NRepeatType) uk084nSvcRsType.getTxRepeatArray(0).changeType(UK084NRepeatType.type);

            totalAmt = NumberUtils.defaultValue(uk084nRepeatType.getAmount(), BigDecimal.ZERO);
            if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                productMap.put(InvestItemType.ETF, totalAmt);
            }
        }
    }

    // 海外ETF 定期定額
    private void processN8084N(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = productMap.containsKey(InvestItemType.ETF) ? productMap.get(InvestItemType.ETF) : BigDecimal.ZERO;

        N8084NSvcRsType n8084nSvcRsType = rsTypeList.get(0).getTxBodyRs(N8084NSvcRsType.class);
        if (n8084nSvcRsType.getOccur() > 0) {
            N8084NRepeatType n8084nRepeatType = (N8084NRepeatType) n8084nSvcRsType.getTxRepeatArray(0).changeType(N8084NRepeatType.type);

            totalAmt = totalAmt.add(NumberUtils.defaultValue(n8084nRepeatType.getAR101(), BigDecimal.ZERO));

            if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                productMap.put(InvestItemType.ETF, totalAmt);
            }
        }
    }

    private void processGD320140(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = BigDecimal.ZERO;
        GD320140SvcRsType gd320140SvcRsType = rsTypeList.get(0).getTxBodyRs(GD320140SvcRsType.class);
        // 應該只有一筆
        TxRepeatType[] txRepeatArray = gd320140SvcRsType.getTxRepeatArray();
        if (txRepeatArray.length > 0) {
            GD320140D001RepeatType gd320140D001RepeatType = (GD320140D001RepeatType) txRepeatArray[0].changeType(GD320140D001RepeatType.type);

            totalAmt = NumberUtils.defaultValue(gd320140D001RepeatType.getPVALUE(), BigDecimal.ZERO);
            if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                productMap.put(InvestItemType.GOLD, totalAmt);
            }
        }
    }

    private void processAJW084(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = BigDecimal.ZERO;
        AJW084SvcRsType ajw084SvcRsType = rsTypeList.get(0).getTxBodyRs(AJW084SvcRsType.class);
        if (ajw084SvcRsType.getOccur() > 0) {
            AJW0840001RepeatType ajw084RepeatType = (AJW0840001RepeatType) ajw084SvcRsType.getTxRepeatArray(0).changeType(AJW0840001RepeatType.type);

            totalAmt = NumberUtils.defaultValue(ajw084RepeatType.getBondAmt1(), BigDecimal.ZERO);
            if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                productMap.put(InvestItemType.BOND, totalAmt);
            }

            totalAmt = NumberUtils.defaultValue(ajw084RepeatType.getBondAmt2(), BigDecimal.ZERO);
            if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                productMap.put(InvestItemType.SN, totalAmt);
            }
        }
    }

    private void processVN084N1(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = BigDecimal.ZERO;
        VN084N1SvcRsType vn084N1SvcRsType = rsTypeList.get(0).getTxBodyRs(VN084N1SvcRsType.class);
        if (vn084N1SvcRsType.getOccur() > 0) {
            VN084N1RepeatType vn084n1RepeatType = (VN084N1RepeatType) vn084N1SvcRsType.getTxRepeatArray(0).changeType(VN084N1RepeatType.type);

            totalAmt = NumberUtils.defaultValue(vn084n1RepeatType.getAmount(), BigDecimal.ZERO);
            if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                productMap.put(InvestItemType.FUND, totalAmt);
            }
        }
    }

    private void processNJW084(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = BigDecimal.ZERO;
        NJW084SvcRsType njw084SvcRsType = rsTypeList.get(0).getTxBodyRs(NJW084SvcRsType.class);
        if (njw084SvcRsType.getOccur() > 0) {
            NJW084RepeatType njw084RepeatType = (NJW084RepeatType) njw084SvcRsType.getTxRepeatArray(0).changeType(NJW084RepeatType.type);

            totalAmt = NumberUtils.defaultValue(njw084RepeatType.getBondAmt1(), BigDecimal.ZERO);
            if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                productMap.put(InvestItemType.BOND, totalAmt);
            }

            totalAmt = NumberUtils.defaultValue(njw084RepeatType.getBondAmt2(), BigDecimal.ZERO);
            if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                productMap.put(InvestItemType.SN, totalAmt);
            }

        }
    }

    private void processVN084N(Map<InvestItemType, BigDecimal> productMap, List<EAIOverviewTxBodyRsType> rsTypeList) {
        BigDecimal totalAmt = BigDecimal.ZERO;
        VN084NSvcRsType vn084NSvcRsType = rsTypeList.get(0).getTxBodyRs(VN084NSvcRsType.class);
        logger.debug("get [VN084N] EMSGID : {}", vn084NSvcRsType.getEMSGID());
        if (StringUtils.isEmpty(StringUtils.trimToEmptyEx(vn084NSvcRsType.getEMSGID()))) {
            if (vn084NSvcRsType.getOccur() > 0) {
                VN084NRepeatType vn084nRepeatType = (VN084NRepeatType) vn084NSvcRsType.getTxRepeatArray(0).changeType(VN084NRepeatType.type);
                totalAmt = NumberUtils.defaultValue(vn084nRepeatType.getAmount(), BigDecimal.ZERO);
                if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                    productMap.put(InvestItemType.FUND, totalAmt);
                }
            }
        }
    }
}
