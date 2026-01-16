/*
 * ===========================================================================
 * 
 * WIS Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright WIS Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.bondrecommend;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.i18n.I18NCacheManager;
import com.ibm.tw.ibmb.component.i18n.I18nModel;
import com.tfb.aibank.chl.component.bondrecommend.model.BondGroupInfo;
import com.tfb.aibank.chl.component.bondrecommend.model.BondHistoryNetValue;
import com.tfb.aibank.chl.component.bondrecommend.model.BondQueryCondition;
import com.tfb.aibank.chl.component.bondrecommend.model.BondRecommendData;
import com.tfb.aibank.chl.component.bondrecommend.model.BondRecommendItemModel;
import com.tfb.aibank.chl.component.bondrecommend.model.BondRecommendLineGraph;
import com.tfb.aibank.chl.component.bondrecommend.model.BondRecommendListModel;
import com.tfb.aibank.chl.component.bondrecommend.model.OverseasBondReferPriceModel;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.CompanyBUType;
import com.tfb.aibank.component.dic.DicCacheManager;

// @formatter:off
/**
 * @(#)BondRecommendService.java
 * 
 * <p>Description:海外債券專屬推薦</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/26, xwr	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class BondRecommendService {
	
	private static final IBLog logger = IBLog.getLog(BondRecommendService.class);
	
	@Autowired
	protected BondRecommendResource investResource;
	@Autowired
    protected I18NCacheManager i18nCacheManager;
    @Autowired
    protected DicCacheManager dicCacheManager;
    @Autowired
    protected CurrencyCodeCacheManager currencyCodeCacheManager;
    @Autowired
    protected UserDataCacheService userDataCacheService;
    
	/**
	 * 根據風險等級查詢推薦海外債券清單
	 * @param loginUser
	 * @param locale
	 * @return
	 */
	public BondRecommendListModel getKYCRecommendBondsList(String kyc, Locale locale) {
		BondRecommendListModel bondRecommendListModel = investResource.getKYCRecommendBondsList(kyc, locale);
		if (!Optional.ofNullable(bondRecommendListModel).isPresent()) {
			return null;
		}
		return bondRecommendListModel;
	}
	
	/**
	 * 查詢是否有推薦海外債券清單
	 * @param kyc
	 * @param locale
	 * @return
	 */
	public Boolean isHaveRecommendBonds(String kyc, Locale locale) {
	   return investResource.getCountByRecommendKycAndLocaleAndTime(kyc, locale) > 0;
	}
	
	/**
	 * 輪播推薦的海外債券商品
	 * @param kyc
	 * @param locale
	 * @param intervalDay 
	 * @return
	 */
	public BondRecommendListModel getRecommendBond(String kyc, Locale locale, String intervalDay) {
		BondRecommendListModel bondRecommendListModel = investResource.getKYCRecommendBondsList(kyc, locale);
		if (Optional.ofNullable(bondRecommendListModel).
				map(BondRecommendListModel::getList)
				.filter(list -> CollectionUtils.isNotEmpty(list)).isEmpty()) {
			return null;
		}

		// 按照維護天數進行輪播
		Date startTime = DateUtils.getFirstDayOfMonth(new Date());
		DateUtils.clearTime(startTime);
		int num = DateUtils.getIntervalNumber(startTime, new Date(), Integer.valueOf(intervalDay));
		//取餘輪播
		num=num%bondRecommendListModel.getList().size();
		bondRecommendListModel.setBondRecommendItem(bondRecommendListModel.getList().get(num));
		// 獲取推薦的海外債券商品
		return bondRecommendListModel;
	}
	
	/**
	 * 取得當前專屬推薦債券
	 * 
	 * @param loginUser
	 * @param bondNo
	 * @param locale
	 * @throws ActionException
	 */
	public BondRecommendData getExclusiveRecBonds(AIBankUser loginUser, List<String> observeList, List<BondGroupInfo> bondInfos, 
	        BondRecommendListModel bondRecommendListModel, String groupId, Locale locale) throws ActionException {
		// 按照維護天數進行輪播
		BondRecommendItemModel bondRecommendItemModel = bondRecommendListModel.getBondRecommendItem();
		
		// 組裝頁面資料
		Map<String, String> payFreqData = this.getPayFreqData(locale);
		Map<String, String> industrialData = this.getIndustrialData(locale);
		Map<String, OverseasBondReferPriceModel> refPriceData = this.getRefPriceData(Arrays.asList(bondInfos.get(0).getBondNo()));
		
		BondRecommendData data = this.toUiData(loginUser, bondInfos, locale, payFreqData, industrialData,
				observeList, refPriceData);
		
		data.setCompanyName(bondRecommendItemModel.getCompanyName());
		data.setContent(bondRecommendItemModel.getContent());
		data.setGroupId(groupId);
		data.setListTitle(bondRecommendListModel.getListTitle());
		data.setListIntroduction(bondRecommendListModel.getListIntroduction());
	    
		return data;
	}
	
	/**
	 * 組裝頁面所需要的字段
	 * @param infos
	 * @param locale
	 * @param payFreqData
	 * @param industrialData
	 * @param getObserveList
	 * @param refPriceData
	 * @return
	 * @throws Exception 
	 */
	public BondRecommendData toUiData(AIBankUser aibankUser, List<BondGroupInfo> infos, Locale locale, Map<String, String> payFreqData, 
			Map<String, String> industrialData, List<String> observeList,
			Map<String, OverseasBondReferPriceModel> refPriceData) {
		BondRecommendData bond = new BondRecommendData();
		try {
			BondGroupInfo info = infos.get(0);		// 一個bondNo可能存在多個群組，但bond訊息一樣，取第一個即可
			
			bond.setBondNo(info.getBondNo());
			bond.setBondSName(info.getBondSName()); // 海外債券名稱
			bond.setFaceIntRate(info.getFaceIntRate()); // 票面利率
			bond.setYtmytcSign(info.getYtmytcSign());
			bond.setYtmytc(StringUtils.equals("-", info.getYtmytcSign()) ? info.getYtmytc().negate() : info.getYtmytc()); // 參考殖利率
			bond.setObserve(observeList.contains(info.getBondNo())); // 是否是觀察債券
			bond.setCurName(currencyCodeCacheManager.getCurrencyName(info.getBondCcy(), locale));		// 計價幣別
			bond.setCanBuy(this.checkCanBuyIncludeObu(info, aibankUser));     // 是否可申購,需投資等級與kyc相匹配並且為非OBU客戶
			
			List<String> groupName = new ArrayList<>();
			groupName.addAll(infos.stream().map(BondGroupInfo::getGroupName).toList());		// 所屬群組名稱
			if (StringUtils.isNotBlank(info.getPayFreq()) && StringUtils.notEquals("5", info.getPayFreq())) {
				groupName.add(payFreqData.get(info.getPayFreq()));
			}
			if (StringUtils.isNotBlank(industrialData.get(info.getIndustrialType()))) {
				groupName.add(industrialData.get(info.getIndustrialType()));
			}
			bond.setGroupName(groupName); // 主題TAG ，配息頻率TAG

			
			OverseasBondReferPriceModel refPrice = refPriceData.get(info.getBondNo());
			if (refPrice != null) {
				bond.setRefPrice(refPriceData.get(info.getBondNo()).getReferPrice());// 參考申購報價
				bond.setRefPriceDate(refPriceData.get(info.getBondNo()).getReferPriceDate());// 報價日期
			}
		} catch (Exception e) {
			logger.error("toUiData error");
		}
		return bond;
	}
	
	/**
	 * 取得產業類別
	 * @return
	 */
	public Map<String, String> getIndustrialData(Locale locale) {
		Map<String, I18nModel> industrialDataMap = i18nCacheManager.getI18nByCategory(locale, "BOND_INDUSTRIAL_TYPE");
		Map<String, String> industrialData = dicCacheManager.getDicListByCategory("BOND_INDUSTRIAL_TYPE").stream()
				.collect(Collectors.toMap(c -> c.getDicKey(), c -> industrialDataMap.get(c.getDicKey()).getValue()));	
		return industrialData;
	}
	
	/**
	 * 取得配息頻率
	 * @return
	 */
	public Map<String, String> getPayFreqData(Locale locale) {
		Map<String, I18nModel> payFreqDataMap = i18nCacheManager.getI18nByCategory(locale, "BOND_PAY_FREQ");
		Map<String, String> payFreqData = dicCacheManager.getDicListByCategory("BOND_PAY_FREQ").stream()
				.collect(Collectors.toMap(c -> c.getDicKey(), c -> payFreqDataMap.get(c.getDicKey()).getValue()));
		return payFreqData;
	}
	
	/**
	 * 取得申購報價相關資料
	 * @param bondNos
	 * @return
	 */
	public Map<String, OverseasBondReferPriceModel> getRefPriceData(List<String> bondNos) {
		List<OverseasBondReferPriceModel> refPriceData = investResource
				.getOverseasBondReferPriceByBondNos(bondNos);
		Map<String, OverseasBondReferPriceModel> refPriceDataMap = refPriceData.stream()
				.collect(Collectors.toMap(c -> c.getBondNo(), c -> c));
		return refPriceDataMap;
	}
	   /**
     * 取得債券明細以及所屬群組訊息
     * @param userLocale
     * @param bondNos
     * @param groupIds
     * @return
     */
    public List<BondGroupInfo> queryBondInfoAndGroupInfo(BondQueryCondition model) {
        return investResource.queryBondInfoAndGroupInfo(model);
    }
	
	/**
	 * 專屬推薦歷史淨值資料表取得近一年的價格走勢資料
	 */
	public List<BondRecommendLineGraph> getLineGraphList(String bondNo) {
		List<BondRecommendLineGraph> lineGraphList = null;
		try {
			if (StringUtils.isNotBlank(bondNo)) {
				Date eDate = DateUtils.getToday();
				Date sDate = DateUtils.getEndDate(DateUtils.addMonth(eDate, -12));
				long txnTime = System.currentTimeMillis();
				List<BondHistoryNetValue> bondHistoryNetValueList = investResource.getBondHistory(bondNo, sDate, eDate);
				
				if (CollectionUtils.isNotEmpty(bondHistoryNetValueList)) {
					lineGraphList = bondHistoryNetValueList.stream().map(this::toLineGraph)
							.collect(Collectors.toList());
				}

				logger.info("spend time = {}", System.currentTimeMillis() - txnTime);
			}
		} catch (ServiceException e) {
			logger.error("取得海外債券歷史淨值資料表失敗", e);
		}
		return lineGraphList;
	}
	
	private BondRecommendLineGraph toLineGraph(BondHistoryNetValue bondHistoryNetValue) {
		BondRecommendLineGraph lineGraph = new BondRecommendLineGraph();
		lineGraph.setBondNo(bondHistoryNetValue.getBondNo());
		lineGraph.setBuyPrice(bondHistoryNetValue.getBuyPrice());
		lineGraph.setSellPrice(bondHistoryNetValue.getSellPrice());
		long txDateTime = bondHistoryNetValue.getTxDate() != null ? bondHistoryNetValue.getTxDate().getTime() : 0;
		lineGraph.setTxDate(txDateTime);
		return lineGraph;
	}
	
	 /**
     * 顯示申購按鈕
     */
    public boolean checkCanBuy(BondGroupInfo info, AIBankUser aibankUser) {
        if (aibankUser == null) {
            return StringUtils.isY(info.getIsSubscription());
        }

        // #7328 [暫停銷售的商品則不出現申購按鈕]，資料來源（暫停銷售）：【1】BOND_INFO.STOP_SALE_FLAG=Y
        // 或 【2】PAY_FREQ為空值 或【3】RAM_RISK_TYPE為空值 或【4】NEXT_INTEREST_DATE為空值
        if (StringUtils.isY(info.getStopSaleFlag()) || StringUtils.isBlank(info.getPayFreq()) || StringUtils.isBlank(info.getRamRiskType()) || StringUtils.isBlank(info.getNextInterestDate())) {
            return false;
        }

        boolean canBuy = false;
        boolean canBuyPlus = false;
        boolean isIndividualAccoun = aibankUser.isIndividualAccount();
        boolean w8Ben = userDataCacheService.getSignStatus4W8Ben(aibankUser);;

        boolean isHeighClient = userDataCacheService.isHighWealthClient(aibankUser);
        boolean isHeighClientH = userDataCacheService.isHighWealthClientH(aibankUser);
        boolean isHeighClientI = userDataCacheService.isHighWealthClientI(aibankUser);
        if (isHeighClient) {
            // 非特定客戶之高資產客戶
            canBuyPlus = isIndividualAccoun || !StringUtils.isY(info.getW8ben()) || w8Ben;
        }
        else if (isHeighClientH) {
            // 高資產客戶(註記H)
            canBuy = isIndividualAccoun || !StringUtils.isY(info.getW8ben()) || w8Ben;
        }
        else if (isHeighClientI) {
            // 高資產客戶(註記I)
            canBuy = !StringUtils.isY(info.getHighAssetsSale()) && (isIndividualAccoun || !StringUtils.isY(info.getW8ben()) || w8Ben);
        }
        else if (isIndividualAccoun) {
            // 個人戶
            boolean is80YearsWhenBondEnd = false;
            if (isOldAge(aibankUser)) {
                // 非特定客戶之高資產客戶
                boolean isAllNotHeighClient = !isHeighClient && !isHeighClientH && !isHeighClientI;
                boolean isProfessionalInvestorBy5556982 = userDataCacheService.isMarkedProfessionalInvestor(aibankUser);
                if (Boolean.FALSE.equals(isProfessionalInvestorBy5556982) && isAllNotHeighClient && is80YearsWhenBondEnd(aibankUser.getBirthDay(), info.getEndDate())) {
                    is80YearsWhenBondEnd = true;
                }
            }
            boolean isMarkedProfessionalInvestor = isIndividualAccoun && userDataCacheService.isMarkedProfessionalInvestor(aibankUser);
            canBuy = !StringUtils.isY(info.getHighAssetsSale()) && (isMarkedProfessionalInvestor || (!StringUtils.isY(info.getIsProfessionSale()) && !is80YearsWhenBondEnd));
        }
        else {
            // 非個人戶
            boolean isProfessionalInvestor = !isIndividualAccoun && userDataCacheService.isProfessionalInvestor(aibankUser);
            canBuy = !StringUtils.isY(info.getHighAssetsSale()) && (!StringUtils.isY(info.getW8ben()) || w8Ben) && (!StringUtils.isY(info.getIsProfessionSale()) || isProfessionalInvestor);
        }

        String kycFlag = aibankUser.getKycFlag();
        if (!StringUtils.equalsAny(kycFlag, "C1", "C2", "C3", "C4")) {
            return false;
        }
        else if (canBuyPlus) {
            return StringUtils.isY(info.getIsSubscription()) && risk.valueOf(kycFlag).getCanBuyPlus().contains(info.getRamRiskType());
        }
        else if (canBuy) {
            return StringUtils.isY(info.getIsSubscription()) && risk.valueOf(kycFlag).getCanBuy().contains(info.getRamRiskType());
        }
        else {
            return false;
        }
    }
    
    /**
     * 是否可申購
     * 需投資等級與kyc相匹配並且為非OBU客戶
     * @param info
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public boolean checkCanBuyIncludeObu(BondGroupInfo info, AIBankUser aibankUser) throws ActionException {
    	 CompanyBUType companyBUType = userDataCacheService.getBuType(aibankUser);
    	 return this.checkCanBuy(info, aibankUser) && !companyBUType.isOBU();
    }
    
    /**
     * 是否滿足高齡認知檢核條件(年齡大於65歲)
     * 
     * @return true:表示年齡大於65歲
     */
    private boolean isOldAge(AIBankUser aibankUser) {
        Date birthday = aibankUser.getBirthDay();
        if (birthday == null) {
            return false;
        }
        Date yearsLater65 = DateUtils.addYears(birthday, 65);
        Date sysDate = DateUtils.getStartDate(new Date());

        return yearsLater65.compareTo(sysDate) < 0;
    }
    
    /**
     * 是否相差80年
     * @param birthDay
     * @param endDate
     * @return
     */
    private boolean is80YearsWhenBondEnd(Date birthDay, Date endDate) {
        LocalDate localDate1 = birthDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(localDate1, localDate2);
        return period.getYears() >= 80;
    }
    
    public enum risk {
        /** C1 */
        C1(Set.of("P1"), Set.of("P1", "P2")),
        /** C2 */
        C2(Set.of("P1", "P2"), Set.of("P1", "P2", "P3")),
        /** C3 */
        C3(Set.of("P1", "P2", "P3"), Set.of("P1", "P2", "P3", "P4")),
        /** C4 */
        C4(Set.of("P1", "P2", "P3", "P4"), Set.of("P1", "P2", "P3", "P4"));

        /** 可申購 */
        Set<String> canBuy;

        /** 可越級申購 */
        Set<String> canBuyPlus;

        risk(Set<String> canBuy, Set<String> canBuyPlus) {
            this.canBuy = canBuy;
            this.canBuyPlus = canBuyPlus;
        }

        /**
         * @return {@link #canBuy}
         */
        public Set<String> getCanBuy() {
            return canBuy;
        }

        /**
         * @return {@link #canBuyPlus}
         */
        public Set<String> getCanBuyPlus() {
            return canBuyPlus;
        }
    }
}
