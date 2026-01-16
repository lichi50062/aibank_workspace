package com.tfb.aibank.chl.general.qu003.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.branch.BranchCacheManager;
import com.tfb.aibank.chl.component.branch.BranchData;
import com.tfb.aibank.chl.component.userdatacache.model.FinancialMgmMemberLevel;
import com.tfb.aibank.chl.component.userdatacache.model.MissionDetail;
import com.tfb.aibank.chl.general.qu003.model.AccountFavor;
import com.tfb.aibank.chl.general.qu003.model.FinMgmMemberData;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003050RsData;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003Output;
import com.tfb.aibank.chl.general.resource.ForeignExchangeResource;
import com.tfb.aibank.chl.general.resource.PreferencesResource;
import com.tfb.aibank.chl.general.resource.dto.BankOperationOffer;
import com.tfb.aibank.chl.general.resource.dto.CEW306RRepeat;
import com.tfb.aibank.chl.general.resource.dto.CEW306RResponse;
import com.tfb.aibank.chl.general.resource.dto.CampaignMappingDetailModel;
import com.tfb.aibank.chl.general.resource.dto.FEP512161Res;
import com.tfb.aibank.chl.general.resource.dto.FEP512166Res;
import com.tfb.aibank.chl.general.resource.dto.MissionDetailMappingModel;
import com.tfb.aibank.chl.general.resource.dto.MissionListModel;
import com.tfb.aibank.chl.general.resource.dto.MissionMasterModel;
import com.tfb.aibank.chl.general.resource.dto.Promotion;
import com.tfb.aibank.chl.general.resource.dto.PromotionClickRecord;
import com.tfb.aibank.chl.general.resource.dto.PromotionListRequest;
import com.tfb.aibank.chl.general.resource.dto.PromotionListResponse;
import com.tfb.aibank.chl.general.resource.dto.PromotionTimeTag;
import com.tfb.aibank.chl.general.resource.dto.RewardsProductResponse;
import com.tfb.aibank.chl.general.resource.dto.ServiceAdvisor;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.TransOutAcctType;

// @formatter:off
/**
 * @(#)NGNQU003Service.java
 * 
 * <p>Description:CHL NGNQU003 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NGNQU003Service extends NGNService {

    @Autowired
    protected PreferencesResource preferencesResource;

    @Autowired
    protected ForeignExchangeResource foreignExchangeResource;

    @Autowired
    private BranchCacheManager branchCacheManager;

    // 優惠產品
    public void findPromotionProduct(AIBankUser loginUser, Locale locale, NGNQU003Output output) {
        PromotionListRequest request = new PromotionListRequest();
        request.setLocale(locale.toString());
        if (loginUser != null) {
            request.setLogin(true);
            request.setCustId(loginUser.getCustId());
            request.setCompanyKind(loginUser.getCompanyKind());
            request.setUidDup(loginUser.getUidDup());
            request.setUserId(loginUser.getUserId());
        }
        PromotionListResponse response = informationResource.findPromotionProduct(request);

        Predicate<Promotion> promotionBetweenTime = pro -> {
            boolean afterUptime = Objects.nonNull(pro.getUpTime()) && new Date().after(pro.getUpTime());
            boolean beforeDowntime = Objects.nonNull(pro.getDownTime()) && new Date().before(pro.getDownTime());
            return afterUptime && beforeDowntime;
        };

        List<Promotion> promotions = response.getPromotions();
        promotions = promotions.stream().filter(promotionBetweenTime).collect(Collectors.toList());
        handTimeTagAndOtherInfo(promotions);

        List<Promotion> bannerPromotions = response.getBanners();
        bannerPromotions = bannerPromotions.stream().filter(promotionBetweenTime).collect(Collectors.toList());
        handTimeTagAndOtherInfo(bannerPromotions);

        output.setPromotions(promotions);
        output.setPromotionCategories(response.getPromotionCategories());
        output.setBanners(bannerPromotions);
    }

    private void handTimeTagAndOtherInfo(List<Promotion> promotions) {
        if (CollectionUtils.isNotEmpty(promotions)) {

            String fubonUrl = systemParamCacheManager.getValue("AIBANK", "AIBANK_SHARE_URL");

            Date now = new Date();
            DateUtils.clearTime(now);

            for (var po : promotions) {

                String shareUrl = fubonUrl + "?" + "taskId=NGNQU003&promotion=" + po.getPromotionKey() + "&openexternalbrowser=1";

                po.setShareUrl(shareUrl);

                if (null != po.getUpTime()) {
                    Date upTime = new Date(po.getUpTime().getTime());

                    int upDays = DateUtils.getDaysBetween(upTime, now);

                    if (upDays <= 5) {
                        PromotionTimeTag tag = new PromotionTimeTag(I18NUtils.getMessage("ngn.qu003.new_in_n_day", String.valueOf(upDays)), "tag-accent");

                        if (upDays == 0) {
                            tag.setTagLabel(I18NUtils.getMessage("ngn.qu003.new_on"));
                        }
                        po.setPromotionTimeTag(tag);
                    }
                }

                if (null == po.getPromotionTimeTag() && null != po.getFinishTime() && po.getFinishTime().after(now)) {
                    // finishTime(EndTime)在系統日後才進行此區判斷

                    Date endTime = new Date(po.getFinishTime().getTime());

                    int daysToFinish = DateUtils.getDaysBetween(endTime, now);

                    if (daysToFinish > 0 && daysToFinish <= 5) {
                        PromotionTimeTag tag = new PromotionTimeTag(I18NUtils.getMessage("ngn.qu003.countdown_n_day", String.valueOf(daysToFinish)), "");
                        po.setPromotionTimeTag(tag);
                    }
                }

                if (StringUtils.isNotBlank(po.getStartTime())) {
                    try {
                        Date startTime = DateFormatUtils.CE_DATE_FORMAT.parse(po.getStartTime());
                        po.setStartTimeMs(startTime.getTime());
                    }
                    catch (ParseException e) {
                        logger.warn("Parse StartTime error, startTime: {}", po.getStartTime());
                    }
                }

                if (StringUtils.isNotBlank(po.getEndTime())) {
                    try {
                        Date endTimeForCal = DateFormatUtils.CE_DATE_FORMAT.parse(po.getEndTime());
                        endTimeForCal = DateUtils.addDay(endTimeForCal, 1);
                        DateUtils.clearTime(endTimeForCal);
                        po.setEndTimeMs(endTimeForCal.getTime());

                        Date endTime = DateFormatUtils.CE_DATE_FORMAT.parse(po.getEndTime());

                        // 活動是否已結束
                        Date currentTime = new Date();
                        po.setPromotionEnded(currentTime.after(endTime));
                        if (po.isPromotionEnded()) {
                            // 預設 0 ，結束的牌後面
                            po.setSortSeqPromoAlive(1);

                            int daysFromEndTime = DateUtils.getDaysBetween(endTime, currentTime);
                            po.setDaysFromEndTime(daysFromEndTime);
                        }
                    }
                    catch (ParseException e) {
                        logger.warn("Parse EndTime error, endTime: {}", po.getEndTime());
                    }
                }
            }
        }
    }

    // 查詢點數餘額 若電文失敗或逾時，則不顯示個人點數資料
    public void getBonusPoint(String custId, NGNQU003Output output) {
        List<CEW306RRepeat> txRepeats = new ArrayList<>();
        try {
            CEW306RResponse cew306r = creditCardResource.sendCEW306R(custId);
            if (CollectionUtils.isNotEmpty(cew306r.getTxRepeats())) {
                boolean goodData = false;
                for (CEW306RRepeat repeat : cew306r.getTxRepeats()) {
                    if (StringUtils.isNotBlank(repeat.getBonGrop()) && StringUtils.isNotBlank(repeat.getBonName())) {
                        goodData = true;
                    }
                }
                if (goodData) {
                    txRepeats = cew306r.getTxRepeats();
                }
            }
        }
        catch (ServiceException se) {
            if (StringUtils.equals("V003", se.getErrorCode())) {
                logger.warn("CEW306R 查無資料");
            }
        }
        output.setCew306RRepeats(txRepeats);
    }

    // 是否顯示個人點數資料
    public void checkShowPersonalPointData(AIBankUser loginUser, NGNQU003Output output) {
        boolean showPersonalPointData = true;
        if (userDataCacheService.isSpecialCreditCard(loginUser)) {
            // 特殊戶，則不顯示個人點數資料
            showPersonalPointData = false;
        }
        else if (!userDataCacheService.haveCreditCard(loginUser)) {
            // 若未持有信用卡，則不顯示個人點數資料
            showPersonalPointData = false;
        }
        output.setShowPersonalPointData(showPersonalPointData);
    }

    // 取得點數商品資料
    public void getRewardProducts(NGNQU003Output output) {
        RewardsProductResponse response = informationResource.getRewardsProducts();
        output.setBonusPoints(response.getBonusPoints());
        output.setMileagePoints(response.getMileagePoints());
        output.setFuhuaPoints(response.getFuhuaPoints());
    }

    // 分類整理個人點數資料
    public void getPoint(NGNQU003050RsData bonusData, List<String> list, NGNQU003Output output) {
        // 剩餘點數
        BigDecimal bonBsav = BigDecimal.ZERO;
        // 到期點數
        BigDecimal bonEamt = BigDecimal.ZERO;
        // 到期日
        String eday = StringUtils.EMPTY;
        for (String category : list) {
            List<CEW306RRepeat> records = output.getCew306RRepeats().stream().filter(cew306r -> StringUtils.equalsAny(cew306r.getBonGrop(), category)).toList();
            for (CEW306RRepeat repeat : records) {
                if (StringUtils.equals("-", repeat.getBonsing())) {
                    bonBsav = bonBsav.subtract(repeat.getBonBsav());
                }
                else {
                    bonBsav = bonBsav.add(repeat.getBonBsav());
                }
                bonEamt = bonEamt.add(repeat.getBonEamt());
                if (repeat.getBonEday() != null) {
                    eday = DateUtils.getCEDateStr(DateUtils.getSimpleROCDate(repeat.getBonEday()));
                }
            }
        }
        bonusData.setBonBsav(bonBsav);
        bonusData.setBonEamt(bonEamt);
        bonusData.setEday(eday);
    }

    /**
     * 自AI Bank User取得取得轉出帳號清單 <br>
     * => A. 若取得的清單為空，則顯示「無帳戶引導頁」
     **/
    public void getTransOutAccounts(AIBankUser aiBankUser, Locale locale, NGNQU003Output output) {
        List<TransOutAccount> allTransOutTW = userDataCacheService.getTransOutAccounts(aiBankUser, locale, TransOutAcctType.TW_TRANS_OUT_ACCT_OVERVIEW);
        List<TransOutAccount> allTransOutFR = userDataCacheService.getTransOutAccounts(aiBankUser, locale, TransOutAcctType.FX_TRANS_OUT_ACCT_OVERVIEW);

        output.setTransOutAcctsTW(allTransOutTW);
        output.setTransOutAcctsFR(allTransOutFR);

    }

    /**
     * 發送電文查詢客戶{所屬會員等級}
     **/
    public void getFinancialMgmMemberLevel(AIBankUser aiBankUser, FinMgmMemberData mainData) {
        try {
            FinancialMgmMemberLevel fl = userDataCacheService.getFinancialMgmMemberLevel(aiBankUser);
            if (null != fl) {
                List<String> validRanks = Arrays.asList("K", "T", "H");
                if (StringUtils.isNotBlank(fl.getPersonalFlag()) && validRanks.contains(fl.getPersonalFlag())) {
                    mainData.setPersonalFlag(fl.getPersonalFlag());
                }
                if (StringUtils.isNotBlank(fl.getFamilyFlag()) && validRanks.contains(fl.getFamilyFlag())) {
                    mainData.setFamilyFlag(fl.getFamilyFlag());
                }
            }
        }
        catch (ServiceException e) {
            logger.warn("== 發送電文查詢客戶{所屬會員等級} error，視為一般會員 ==", e);
        }
    }

    /**
     * 判斷登入者是否為[薪轉會員] 查詢DB[FX_TRANS_DISCOUNT_ID.DISCOUNT_CODE], 查有資料時表示為「薪轉會員」
     *
     * @param aiBankUser
     * @return
     */
    public boolean userHaveSalaryAccount(AIBankUser aiBankUser, FinMgmMemberData mainData) {
        boolean salaryAcctMember = false;

        // 是薪轉會員
        if (CollectionUtils.isNotEmpty(mainData.getTransOutAcctsTW())) {
            // PayrollCode 為「薪轉委託單位代號」有值時表示該帳號為新轉帳號
            salaryAcctMember = mainData.getTransOutAcctsTW().stream().anyMatch(acct -> StringUtils.isNotBlank(acct.getPayrollCode()));
        }

        if (!salaryAcctMember && CollectionUtils.isNotEmpty(mainData.getTransOutAcctsFR())) {
            // PayrollCode 為「薪轉委託單位代號」有值時表示該帳號為新轉帳號
            salaryAcctMember = mainData.getTransOutAcctsFR().stream().anyMatch(acct -> StringUtils.isNotBlank(acct.getPayrollCode()));
        }

        // 到這邊還沒確認是否是薪轉戶，檢查是否是員工
        if (!salaryAcctMember) {
            // 如果是在職員工，就是薪轉戶
            salaryAcctMember = userDataCacheService.isCurrentEmployee(aiBankUser);
        }
        return salaryAcctMember;
    }

    public void checkAndProcessSalaryAccountFavor(AIBankUser loginUser, FinMgmMemberData mainData) {
        boolean salaryAcctMember = userHaveSalaryAccount(loginUser, mainData);

        mainData.setSalaryAcctMember(salaryAcctMember);

        if (mainData.isSalaryAcctMember()) {
            // 是薪轉會員
            if (CollectionUtils.isNotEmpty(mainData.getTransOutAcctsTW())) {
                for (var acct : mainData.getTransOutAcctsTW()) {
                    // PayrollCode 為「薪轉委託單位代號」有值時表示該帳號為新轉帳號
                    if (StringUtils.isNotBlank(acct.getPayrollCode())) {
                        mainData.markSalaryAccountNo(acct.getAcno());
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(mainData.getTransOutAcctsFR())) {
                for (var acct : mainData.getTransOutAcctsFR()) {
                    // PayrollCode 為「薪轉委託單位代號」有值時表示該帳號為新轉帳號
                    if (StringUtils.isNotBlank(acct.getPayrollCode())) {
                        mainData.markSalaryAccountNo(acct.getAcno());
                    }
                }
            }
        }

    }

    /**
     * 依客戶轉出帳號逐筆發送電文判斷是否為{身障優惠帳號}
     *
     * @param mainData
     */
    public void checkDisabilityAccts(FinMgmMemberData mainData) {

        if (CollectionUtils.isNotEmpty(mainData.getAccountFavors())) {
            for (var favor : mainData.getAccountFavors()) {
                try {
                    FEP512161Res res = userResource.getAutomationFeeDiscounts(favor.getAccountNo());

                    if (logger.isDebugEnabled()) {
                        logger.debug("== getAutomationFeeDiscounts, accountNo: [{}] ==, res: {}", favor.getAccountNo(), res);
                    }

                    if (null != res) {
                        favor.setCsCntR(NumberUtils.defaultValue(res.getCsCntR(), 0));
                        favor.setCsCnt(NumberUtils.defaultValue(res.getCsCnt(), 0));
                        if (null == res.getTrCnt() || null == res.getTrCntR()) {
                            favor.setNoTrCntData(true);
                        }
                        favor.setTrCnt(NumberUtils.defaultValue(res.getTrCnt(), 0));
                        favor.setTrCntR(NumberUtils.defaultValue(res.getTrCntR(), 0));

                        if (StringUtils.isNotBlank(res.getSrcMemo3()) && StringUtils.startsWith(res.getSrcMemo3(), "03")) {
                            favor.setDisabilityDiscount(true);
                        }
                    }

                }
                catch (ServiceException e) {
                    favor.setSendFEP512161Error(true);
                    logger.warn("==發送電文 FEP512161判斷是否為身障優惠帳號 ，失敗不影響流程 ==", e);
                }
            }
        }
    }

    /**
     * 發送電文FEP512166取得每月跨行優惠資訊
     *
     * @param aiBankUser
     * @param mainData
     */
    public void checkCrossBankTxFavor(AIBankUser aiBankUser, FinMgmMemberData mainData) {
        FEP512166Res res = null;
        try {
            res = userResource.getVipFeeDiscounts(aiBankUser.getCustId());
        }
        catch (ServiceException e) {
            logger.warn("==發送電文 FEP512166取得每月跨行優惠資訊，不論成功失敗均接續下一步驟  ==", e);
        }
        if (null == res) {
            res = new FEP512166Res();
        }

        Integer remainCsCnt = res.getRemainCsCnt();
        Integer specCsCnt = res.getSpecCsCnt();

        if (null == remainCsCnt) {
            List<AccountFavor> accountFavors = mainData.getAccountFavors();
            if (CollectionUtils.isNotEmpty(accountFavors) && accountFavors.stream().noneMatch(AccountFavor::isSendFEP512161Error)) {
                int subCsCntR = 0;
                for (var acctFavor : accountFavors) {
                    subCsCntR += acctFavor.getCsCntR();
                }
                remainCsCnt = subCsCntR;
            }
        }

        if (null == specCsCnt) {
            List<AccountFavor> accountFavors = mainData.getAccountFavors();
            if (CollectionUtils.isNotEmpty(accountFavors) && accountFavors.stream().noneMatch(AccountFavor::isSendFEP512161Error)) {
                int subCsCnt = 0;
                for (var acctFavor : accountFavors) {
                    subCsCnt += acctFavor.getCsCnt();
                }
                specCsCnt = subCsCnt;
            }
        }

        if (null != remainCsCnt && null != specCsCnt) {
            res.setRemainCsCnt(remainCsCnt);
            res.setSpecCsCnt(specCsCnt);
            mainData.setCrossBankTxFavor(res);
        }
        else {
            mainData.setCrossBankTxFavor(null);
        }

        // 每月跨行轉帳優惠資訊

        Integer remainTrCnt = res.getRemainTrCnt();
        Integer specTrCnt = res.getSpecTrCnt();

        if (null == remainTrCnt) {
            List<AccountFavor> accountFavors = mainData.getAccountFavors();
            if (CollectionUtils.isNotEmpty(accountFavors) && accountFavors.stream().noneMatch(AccountFavor::isSendFEP512161Error)) {
                int subTrCntR = 0;
                for (var acctFavor : accountFavors) {
                    subTrCntR += acctFavor.getTrCntR();
                }
                remainTrCnt = subTrCntR;
            }
        }

        if (null == specTrCnt) {
            List<AccountFavor> accountFavors = mainData.getAccountFavors();
            if (CollectionUtils.isNotEmpty(accountFavors) && accountFavors.stream().noneMatch(AccountFavor::isSendFEP512161Error)) {
                int subTrCnt = 0;
                for (var acctFavor : accountFavors) {
                    subTrCnt += acctFavor.getTrCnt();
                }
                specTrCnt = subTrCnt;
            }
        }

        if (null != remainTrCnt && null != specTrCnt) {
            FEP512166Res crossBankTxTrFavor = new FEP512166Res();
            crossBankTxTrFavor.setRemainTrCnt(remainTrCnt);
            crossBankTxTrFavor.setSpecTrCnt(specTrCnt);
            mainData.setCrossBankTxTrFavor(crossBankTxTrFavor);
        }

    }

    /**
     * (7) 發送電文取得每月銀行作業優惠資訊
     *
     * @param aiBankUser
     * @param mainData
     */
    public void checkBankOperationFavor(AIBankUser aiBankUser, FinMgmMemberData mainData) {
        try {
            BankOperationOffer offer = informationResource.getBankOperationOffers(aiBankUser.getCustId(), 321, 11);
            if (null != offer) {
                offer.organizeData();
                mainData.setBankOperationOffer(offer);
            }
        }
        catch (ServiceException e) {
            logger.warn("==發送電文 EB069024 取得每月銀行作業[ 國內 ] 優惠資訊，不論成功失敗均接續下一步驟  ==", e);
        }

        try {
            BankOperationOffer offerOversea = informationResource.getBankOperationOffers(aiBankUser.getCustId(), 470, 12);
            if (null != offerOversea) {
                offerOversea.organizeData();
                mainData.setBankOperationOfferOversea(offerOversea);
            }
        }
        catch (ServiceException e) {
            logger.warn("==發送電文 EB069024 取得每月銀行作業[ 國外 ]優惠資訊，不論成功失敗均接續下一步驟  ==", e);
        }

    }

    public void getServiceAdvisor(AIBankUser aiBankUser, FinMgmMemberData mainData, Locale locale) {
        ServiceAdvisor serviceAdvisor = informationResource.getServiceAdvisor(aiBankUser.getCustId());

        if (null != serviceAdvisor && StringUtils.isNotBlank(serviceAdvisor.getBranchId())) {
            BranchData branchData = branchCacheManager.getBranch(StringUtils.leftPadZero(serviceAdvisor.getBranchId(), 5), locale);
            if (null != branchData) {
                serviceAdvisor.setBranchName(branchData.getBranchName());
            }
        }
        mainData.setServiceAdvisor(serviceAdvisor);
    }

    public boolean addFavoriePromotion(AIBankUser aiBankUser, String promotionKey) {
        boolean saveResult = informationResource.saveMyFavoritePromotion(aiBankUser.getCustId(), aiBankUser.getUserId(), aiBankUser.getUidDup(), aiBankUser.getCompanyKind(), promotionKey);
        logger.debug("== addFavoriePromotion, result: {} ==", saveResult);
        return saveResult;
    }

    public boolean deleteMyFavoritePromotion(AIBankUser aiBankUser, String promotionKey) {
        boolean opResult = informationResource.deleteMyFavoritePromotion(aiBankUser.getCustId(), aiBankUser.getUserId(), aiBankUser.getUidDup(), aiBankUser.getCompanyKind(), promotionKey);
        logger.debug("== deleteMyFavoritePromotion, result: {} ==", opResult);
        return opResult;
    }

    public void getFavoritePromotions(AIBankUser aiBankUser, NGNQU003Output output) {
        List<Promotion> promotions = informationResource.getFavoritePromotions(aiBankUser.getCustId(), aiBankUser.getUserId(), aiBankUser.getUidDup(), aiBankUser.getCompanyKind());

        handTimeTagAndOtherInfo(promotions);

        promotions = promotions.stream().filter(promotion -> promotion.getDaysFromEndTime() < 10).collect(Collectors.toList());

        IBUtils.sort(promotions, new String[] { "sortSeqPromoAlive", "addfavoriteTime" }, new boolean[] { false, true });

        output.setPromotions(promotions);
    }

    /**
     * 檢查任務牆狀態
     *
     * @param loginUser
     * @param output
     */
    public void checkMissonStatus(AIBankUser loginUser, Locale locale, NGNQU003Output output) {

        MissionMasterModel missionMasterModel = null;

        try {
            missionMasterModel = preferencesResource.queryMissionMasterByUser(loginUser.getCustId(), loginUser.getUserId(), loginUser.getUidDup(), loginUser.getCompanyKind());
        }
        catch (ServiceException e) {
            logger.error("查詢任務強主黨  queryMissionMasterByUser 錯誤：", e);
        }

        String missionActivityQualification = "";
        String missionBannerLevel = "";

        if (null != missionMasterModel) {
            logger.debug("== checkMissonStatus ==, missionMasterModel: {}", IBUtils.attribute2Str(missionMasterModel));

            if (null != missionMasterModel.getEndTime() && missionMasterModel.getEndTime().before(new Date())) {
                // 任務已結束
                return;
            }

            String missionLevel = missionMasterModel.getMissionLevel();

            if (StringUtils.isNotBlank(missionLevel)) {

                if ("0".equals(missionLevel)) {
                    // 使用者尚未完成開戶任務，{活動資格}設為Y，進入「功能首頁」，顯示任務牆BannerB。
                    missionActivityQualification = StringUtils.Y;
                    missionBannerLevel = "A";
                }
                else {
                    // 使用者已完成開戶任務，{活動資格}設為Y，進入「功能首頁」，顯示任務牆BannerC
                    missionActivityQualification = StringUtils.Y;
                    missionBannerLevel = "C";

                    int missionCompleteCount = 0;

                    if (Arrays.asList("1", "2").contains(missionLevel)) {

                        List<MissionDetailMappingModel> detailMappingModels = preferencesResource.queryMissionDetailByUserAndLevel(loginUser.getCustId(), loginUser.getUserId(), loginUser.getUidDup(), loginUser.getCompanyKind(), missionLevel, locale.toString());

                        List<MissionDetail> missionDetails = preferencesResource.queryMissionDetailOnlyByUser(loginUser.getCustId(), loginUser.getUserId(), loginUser.getUidDup(), loginUser.getCompanyKind());
                        logger.debug("== checkMissonStatus ==, missionDetails: {}", IBUtils.attribute2Str(missionDetails));

                        if (CollectionUtils.isNotEmpty(missionDetails)) {

                            logger.debug("== checkMissonStatus ==, detailMappingModels: {}", IBUtils.attribute2Str(detailMappingModels));

                            List<MissionDetail> completedMissions = new ArrayList<>();

                            for (var detail : missionDetails) {
                                if ("1".equals(missionLevel)) {
                                    if ("2".equals(detail.getMissionLevel()) && null != detail.getCompleteTime()) {
                                        completedMissions.add(detail);
                                    }
                                }
                                else if ("2".equals(missionLevel)) {
                                    if ("3".equals(detail.getMissionLevel()) && null != detail.getCompleteTime()) {
                                        completedMissions.add(detail);
                                    }
                                }
                            }
                            missionCompleteCount = completedMissions.size();
                        }
                    }
                    else if ("3".equals(missionLevel)) {
                        missionCompleteCount = 3;
                    }
                    output.setMissionCompleteCount(missionCompleteCount);

                    List<CampaignMappingDetailModel> campaignMappingDetailModels = preferencesResource.getCampaignMappingDetailByCampaignId(missionMasterModel.getCampaignId());

                    if (CollectionUtils.isNotEmpty(campaignMappingDetailModels)) {
                        logger.debug("== checkMissonStatus ==, campaignMappingDetailModels: {}", IBUtils.attribute2Str(campaignMappingDetailModels));

                        Predicate<CampaignMappingDetailModel> matchLv = detail -> {
                            boolean isEqual = false;
                            if ("0".equals(missionLevel)) {
                                isEqual = "1".equals(detail.getMissionLevel());
                            }
                            else if ("1".equals(missionLevel)) {
                                isEqual = "2".equals(detail.getMissionLevel());
                            }
                            else if (Arrays.asList("2", "3").contains(missionLevel)) {
                                isEqual = "3".equals(detail.getMissionLevel());
                            }
                            return isEqual;
                        };

                        campaignMappingDetailModels.stream().filter(matchLv).findFirst().ifPresent(cam -> {
                            logger.debug("== campaignMappingDetailModel ==, : {}", IBUtils.attribute2Str(cam));
                            output.setMissionRate(ConvertUtils.str2BigDecimal(cam.getRate()));
                        });
                    }
                }
            }

        }
        else {
            logger.debug("== checkMissonStatus ==, no mission data");
            // 查無資料，視為使用者未開始任務牆活動

            try {
                MissionListModel missionListModel = preferencesResource.queryMissionListByUser(loginUser.getCompanyVo().getCompanyUid());
                // 查無資料時「queryMissionListByUser」會拋錯
                missionActivityQualification = StringUtils.Y;
                missionBannerLevel = "A";
            }
            catch (ServiceException e) {
                logger.warn("查無任務牆名單檔，不符合資格");
                missionActivityQualification = StringUtils.N;
            }
        }

        output.setMissionActivityQualification(missionActivityQualification);
        output.setMissionBannerLevel(missionBannerLevel);
    }

    /**
     * 增加Promotiono點擊量
     */
    public void addPromotionClick(String promotionKey) {
        if (StringUtils.isNotBlank(promotionKey)) {
            logger.debug("== addPromotionClick, promotionKey: {}", promotionKey);
            informationResource.addPromotionClick(promotionKey);
        }
    }

    public void getPromotionClickMap(NGNQU003Output output) {

        List<PromotionClickRecord> clickRecords = informationResource.getPromotionClicks();

        if (CollectionUtils.isNotEmpty(clickRecords)) {
            Map<String, Integer> clickM = clickRecords.stream().collect(Collectors.toMap(PromotionClickRecord::getPromotionKey, PromotionClickRecord::getClick, (a, b) -> a));

            output.setPromotionClickCountMap(clickM);
        }

    }
    
    // 是否為高貢獻客戶
    public void checkHighContributeCust(AIBankUser loginUser, NGNQU003Output output) {
        boolean check = informationResource.checkHighContributeCust(loginUser.getCustId());
        output.setHighContributeCust(check);
    }
}
