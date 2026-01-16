package com.tfb.aibank.chl.general.resource;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.general.resource.dto.AppVersion;
import com.tfb.aibank.chl.general.resource.dto.BankOperationOffer;
import com.tfb.aibank.chl.general.resource.dto.Promotion;
import com.tfb.aibank.chl.general.resource.dto.PromotionClickRecord;
import com.tfb.aibank.chl.general.resource.dto.PromotionListRequest;
import com.tfb.aibank.chl.general.resource.dto.PromotionListResponse;
import com.tfb.aibank.chl.general.resource.dto.RateCurrency;
import com.tfb.aibank.chl.general.resource.dto.RespectInfo;
import com.tfb.aibank.chl.general.resource.dto.RewardsProductResponse;
import com.tfb.aibank.chl.general.resource.dto.ServiceAdvisor;

//@formatter:off
/**
 * @(#)InformationResource.java
 *
 * <p>Description:溝通 service-aibank-user 的介面</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, MartyPan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-chl-general-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
public interface InformationResource {

    /** 取得 System招呼語資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/respect-info/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RespectInfo> getRespectInfos();

    /** 查詢資料表(RATE_CURRENCY)，以rateType取得資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/rate-currency-in-types/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RateCurrency> getRateCurrencyInTypes(@RequestParam(name = "rateType") String rateType);

    /** 查詢資料表(APP_VERSION)，依作業系統類別，最得最新版本紀錄 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/app-version/latest-version/get", produces = MediaType.APPLICATION_JSON_VALUE)
    AppVersion getAppVersion(@RequestParam("category") String category);

    /** 查詢客戶在30天內是否已顯示「滿意度問卷」 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/satisfactions/status-shown/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean checkHasBeenShownSatisfaction(@RequestHeader("custId") String custId, @RequestParam("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind);

    /** 檢核Email正確性及是否為黑名單 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/email-check/query", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean checkEmail(@RequestParam("email") String email);

    /** 取得優惠功能首頁商品與客戶資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/promotion-product/list", produces = MediaType.APPLICATION_JSON_VALUE)
    PromotionListResponse findPromotionProduct(@RequestBody PromotionListRequest request);

    /** 取得點數商品資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/reward-product/list", produces = MediaType.APPLICATION_JSON_VALUE)
    RewardsProductResponse getRewardsProducts();

    /** 發查 EB069024 取得銀行作業優惠資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bank-op-offer/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BankOperationOffer getBankOperationOffers(@RequestHeader("custIxd") String custIxd, @RequestParam("feeType") Integer feeType, @RequestParam("accumNo") Integer accumNo);

    /** 查找「專屬服務專員」 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/financial-advisor/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceAdvisor getServiceAdvisor(@RequestHeader("custIxd") String custIxd);

    /** 查找「查找我的收藏」 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/promotion/{companyKind},{uidDup}/favorite/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Promotion> getFavoritePromotions(@RequestHeader("custIxd") String custIxd, @RequestHeader("userIxd") String userIxd, @PathVariable("uidDup") String uidDup, @PathVariable("companyKind") Integer companyKind);

    /** 新增FavoritePromotionEntity */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/promotion/favorite/{companyKind},{uidDup}/save", produces = { MediaType.APPLICATION_JSON_VALUE })
    Boolean saveMyFavoritePromotion(@RequestHeader("custIxd") String custIxd, @RequestHeader("userIxd") String userIxd, @PathVariable("uidDup") String uidDup, @PathVariable("companyKind") Integer companyKind, @RequestParam("promotionKey") String promotionKey);

    /** 刪除FavoritePromotionEntity */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/promotion/favorite/{companyKind},{uidDup}/remove", produces = { MediaType.APPLICATION_JSON_VALUE })
    Boolean deleteMyFavoritePromotion(@RequestHeader("custIxd") String custIxd, @RequestHeader("userIxd") String userIxd, @PathVariable("uidDup") String uidDup, @PathVariable("companyKind") Integer companyKind, @RequestParam("promotionKey") String promotionKey);

    /** 熱門優惠活動點選次數(PROMOTION_CLICK_RECORD)，增加點擊數 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/promotion-click/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean addPromotionClick(@RequestParam("promotionKey") String promotionKey);

    /** 熱門優惠活動點選次數(PROMOTION_CLICK_RECORD)，全部活動點擊數 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/promotion-click/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<PromotionClickRecord> getPromotionClicks();
    
    /** 檢核是否為高貢獻客戶 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/highcontributecust-check/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean checkHighContributeCust(@RequestHeader("custId") String custId);
}
