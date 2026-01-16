/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.overview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.chl.component.overview.model.BondFmInvestItem;
import com.tfb.aibank.chl.component.overview.model.BondInvestItem;
import com.tfb.aibank.chl.component.overview.model.FundInvestItem;
import com.tfb.aibank.chl.component.overview.model.GD320140Req;
import com.tfb.aibank.chl.component.overview.model.GoldInvestItem;
import com.tfb.aibank.chl.component.overview.model.InvestItem;
import com.tfb.aibank.chl.component.overview.model.NmiInvestItem;
import com.tfb.aibank.chl.component.overview.model.SiDciInvestItem;
import com.tfb.aibank.chl.component.overview.model.SnInvestItem;
import com.tfb.aibank.chl.component.overview.model.StockInvestItem;
import com.tfb.aibank.chl.component.overview.type.GD320140FuncCod;
import com.tfb.aibank.chl.component.overview.type.GD320140Source;
import com.tfb.aibank.chl.component.userdatacache.InvestResource;
import com.tfb.aibank.chl.component.userdatacache.MutualFundResource;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.component.userdatacache.model.BondOverviewReq;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE071Req;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE072Req;
import com.tfb.aibank.chl.component.userdatacache.model.NMP8YBReq;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.model.BondOverview;
import com.tfb.aibank.common.model.FundOverview;
import com.tfb.aibank.common.model.GoldOverview;
import com.tfb.aibank.common.model.HybridProdOverview;
import com.tfb.aibank.common.model.NmiOverview;
import com.tfb.aibank.common.model.OdsVpbnd1002;
import com.tfb.aibank.common.model.StockOverview;
import com.tfb.aibank.common.model.StructuredOverview;
import com.tfb.aibank.common.type.CompanyBUType;

// @formatter:off
/**
 * @(#)OverviewService.java
 * 
 * <p>Description:[總覽類共用]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/03, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class OverviewService {
    private static IBLog logger = IBLog.getLog(OverviewService.class);

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private InvestResource investResource;

    @Autowired
    private ExchangeRateHistoryCacheManager exRateHistoryCacheManager;

    @Autowired
    private MutualFundResource mutualFundResource;

    /** 奈米投 */
    public InvestItem getNmiInvestItem(List<NmiOverview> list) {
        InvestItem invObj = new NmiInvestItem(list, exRateHistoryCacheManager);
        return invObj;
    }

    /** 基金 */
    public InvestItem getFundInvestItem(boolean isDbu, List<FundOverview> list) {
        InvestItem invObj = new FundInvestItem(isDbu, list);
        return invObj;
    }

    /** 海外ETF, 股票 */
    public InvestItem getStockInvestItem(List<StockOverview> list, BigDecimal etfMarketAmt) {
        InvestItem invObj = new StockInvestItem(list, etfMarketAmt);
        return invObj;
    }

    /** 海外債券 */
    public InvestItem getBondInvestItem(List<BondOverview> list) {
        InvestItem invObj = new BondInvestItem(list, exRateHistoryCacheManager);
        return invObj;
    }

    /** 外國債券 (自營)] */
    public InvestItem getBondFmInvestItem(List<OdsVpbnd1002> list) {
        InvestItem invObj = new BondFmInvestItem(list, exRateHistoryCacheManager);
        return invObj;
    }

    /** 境內外結構型商品 */
    public InvestItem getSnInvestItem(List<BondOverview> list) {
        InvestItem invObj = new SnInvestItem(list, exRateHistoryCacheManager);
        return invObj;
    }

    /** 組合式商品 */
    public InvestItem getSiDciInvestItem(List<HybridProdOverview> list) {
        InvestItem invObj = new SiDciInvestItem(list, exRateHistoryCacheManager);
        return invObj;
    }

    /** 黃金 */
    public InvestItem getGoldInvestItem(List<GoldOverview> list) {
        InvestItem invObj = new GoldInvestItem(list, exRateHistoryCacheManager);
        return invObj;
    }

    /**
     * 讀取奈米投契約明細查詢-總覽資訊
     *
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public List<NmiOverview> getNmiOverviewList(AIBankUser aibankUser) throws ActionException {
        List<NmiOverview> result = new ArrayList<>();
        NMP8YBReq req = new NMP8YBReq();
        req.setCustId(aibankUser.getCustIdAndCheckDup());
        req.setCurAcctName(aibankUser.getNameCode());
        req.setFunc("1");
        result = investResource.queryNMP8YB(req);
        return result;
    }

    /**
     * 讀取債券總覽資訊
     * 
     * @param aibankUser
     * @param type
     *            1:全部 2:庫存 3:在途
     * @param prodect
     *            1(海外債券) 2(結構型)
     * @return
     * @throws ActionException
     */
    public List<BondOverview> getBondOverviewList(AIBankUser user, String type, String product) throws ActionException {

        CompanyBUType buType = userDataCacheService.getBuType(user);

        BondOverviewReq req = new BondOverviewReq();
        req.setIsObu(buType.isOBU());
        req.setCustId(user.getCustId());
        req.setUserId(user.getUserId());
        req.setNameCode(user.getNameCode());
        req.setType(type);
        req.setProduct(product);

        return investResource.getBondOverviewList(req);
    }

    /**
     * 讀取外國債券 (自營)資訊
     * 
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public List<OdsVpbnd1002> getBondFmOverviewList(AIBankUser user) throws ActionException {

        List<OdsVpbnd1002> list = investResource.queryOdsVpbnd1002(user.getCustId(), "BOND");

        BigDecimal sumMarketAmtNtd = list.stream().map(OdsVpbnd1002::getMarketValueAmtTwd).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (sumMarketAmtNtd.compareTo(BigDecimal.ZERO) <= 0) {
            return Collections.emptyList();
        }

        return list;
    }

    /**
     * 讀取基金總覽資訊，TYPE=1，取全部
     * 
     * @param aibankUser
     * @param type
     *            1:ALl 2:庫存 3: 4:
     * @return
     * @throws ActionException
     */
    public List<FundOverview> getFundOverviewList(AIBankUser aibankUser, String type) throws ActionException {
        List<FundOverview> result = new ArrayList<>();

        CompanyBUType buType = userDataCacheService.getBuType(aibankUser);
        switch (buType) {
        case DBU:
            NFEE072Req nfee072Req = new NFEE072Req();
            nfee072Req.setHFMTID("0001");
            nfee072Req.setCustId(aibankUser.getCustIdAndCheckDup());
            nfee072Req.setCurAcctName(aibankUser.getNameCode());
            nfee072Req.setType(type);
            result = mutualFundResource.queryNFEE072(nfee072Req);
            break;
        case OBU:
            NFEE071Req nfee071Req = new NFEE071Req();
            nfee071Req.setHFMTID("0001");
            nfee071Req.setCustId(aibankUser.getCustIdAndCheckDup());
            nfee071Req.setCurAcctName(aibankUser.getNameCode());
            nfee071Req.setType(type);
            result = mutualFundResource.queryNFEE071(nfee071Req);
            break;
        default:
            logger.error("無法辨識客戶身份別 DBU/OBU。");
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        return result;
    }

    /** 查詢黃金存摺庫存明細 */
    public List<GoldOverview> getGoldOverview(AIBankUser user, String category) {
        GD320140Req request = new GD320140Req();
        request.setSource(GD320140Source.TYPE_D.getCode());
        request.setCategory(category);
        request.setFuncCod(GD320140FuncCod.COD_01.getCode());
        request.setNameCod(user.getNameCode());
        request.setConveyEnd("Y");
        request.setIdno(user.getCompanyVo().getCompanyUid());

        return investResource.sendGD320140(request);
    }

    /**
     * 查詢海外股票、ETF總覽
     * 
     * @param AIBankUser
     * @return
     */
    public List<StockOverview> getStockOverviewList(AIBankUser user) {
        return getStockOverviewList(user.getCustId(), user.getUserId(), user.getNameCode());
    }

    /**
     * 查詢海外股票、ETF總覽
     * 
     * @param custId,userId,nameCode
     * 
     */
    public List<StockOverview> getStockOverviewList(String custId, String userId, String nameCode) {
        return investResource.getStockOverviewList(custId, userId, nameCode);
    }

    /**
     * 查詢組合式商品
     * 
     * @param custId
     * @return
     */
    public List<HybridProdOverview> getHybridProdOverviewList(String custId) {
        // 查詢結構式投資(SI)
        List<StructuredOverview> list = investResource.sendSPWEBINQ(custId);

        // 查詢不保本外匯雙享利(DCI)
        list.addAll(investResource.sendDCDBPBNK(custId));

        List<HybridProdOverview> overview = list.stream().map(o -> new HybridProdOverview(o)).collect(Collectors.toList());

        return overview;
    }

}
