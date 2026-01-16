package com.tfb.aibank.chl.creditcard.resource;

import java.math.BigDecimal;
import java.util.List;

import com.tfb.aibank.chl.creditcard.resource.dto.RecommendInfoRes;
import com.tfb.aibank.chl.creditcard.resource.dto.EB602652ARepeat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.BonusExchangeResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4150RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4150RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4151RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4151RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4152RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4152RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4153RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE4153RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE5552RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE5552RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CE6210RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW013RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW1410RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW1410RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW208RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW208RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW213RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW218RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW220RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW220RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW221RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW221RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW222RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW230RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW231RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW302RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW306RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW309RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW309RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW311RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW311RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW315RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW316RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW316RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW317RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW317RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW320RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW320RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW321RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW325RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW326RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW327RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW328RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW329RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW332RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW336RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW343RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW344RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW345RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW346RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW424RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW424RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW431RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW431RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CardActivateModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CardBillPeriodApplyModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CardCarnoApply;
import com.tfb.aibank.chl.creditcard.resource.dto.CardControlSettingModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CardEmailBillModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CardLossModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CardStagesApplyModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CreditLimitsApplyData;
import com.tfb.aibank.chl.creditcard.resource.dto.CustomerCardApply;
import com.tfb.aibank.chl.creditcard.resource.dto.DebitCardTxnDetailRs;
import com.tfb.aibank.chl.creditcard.resource.dto.EB12020009Request;
import com.tfb.aibank.chl.creditcard.resource.dto.EB12020009Response;
import com.tfb.aibank.chl.creditcard.resource.dto.EB602655ARepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.EBCC001Response;
import com.tfb.aibank.chl.creditcard.resource.dto.EBCC002Request;
import com.tfb.aibank.chl.creditcard.resource.dto.EBCC002Response;
import com.tfb.aibank.chl.creditcard.resource.dto.FEP852835Repeat;
import com.tfb.aibank.chl.creditcard.resource.dto.InsertCardSettingCashPwdRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.UpdateCardNotificationRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.UpdateCardSettingCashPwdRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0051Response;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0052Rep;

//@formatter:off
/**
 * @(#)CreditCardResource.java
 * 
 * <p>Description:溝通 service-aibank-credit-card 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-chl-creditcard-to-credit-card-service", url = "${aibank.common.feign.service-aibank-credit-card-url:http://svc-biz-credit-card:8080}")
public interface CreditCardResource {

    /** 新增開卡紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/opening-records/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CardActivateModel createCardActivate(@RequestBody CardActivateModel request, @RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind);

    /** 更新開卡紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/opening-records/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CardActivateModel updateCardActivate(@RequestBody CardActivateModel request, @RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind);

    /** 信用卡開卡 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/opening/add", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW208RResponse> applyCreditCard(@RequestBody CEW208RRequest request);

    /** 新增信用卡掛失紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/loss-records/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CardLossModel addCardLoss(@RequestBody CardLossModel request, @RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind);

    /** 更新信用卡掛失紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/loss-records/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CardLossModel updateCardLoss(@RequestBody CardLossModel request, @RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind);

    /** 信用卡掛失 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/loss-card/add", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW1410RResponse> applyLossCreditCard(@RequestBody CEW1410RRequest request);

    /** 查詢結帳日前消費明細 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-details/checkout-date/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW205RResponse> sendCEW205R(@RequestHeader("custId11") String custId11, @RequestParam("custCrdno") String custCrdno);

    /** 額度及繳款查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-information/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW303RResponse> sendCEW303R(@RequestHeader(name = "custAcid", required = false) String custAcid, @RequestParam(name = "custCdno", required = false) String custCdno);

    /** 查詢「信用卡費自動扣繳」狀態 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-automatics/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<Boolean> sendCEW309R(@RequestHeader("custACID") String custACID, @RequestParam("custTYPE") String custType);

    /** 查詢「信用卡費自動扣繳」狀態 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-automatics/billing-automatics/data/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW309RResponse> getAtomaticData(@RequestHeader("custACID") String custACID, @RequestParam("custTYPE") String custType);

    /** 信用卡費自動扣繳設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-automatics/add", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<Boolean> sendCEW309R(@RequestHeader("custACID") String custACID, @RequestBody CEW309RRequest request);

    /** 查詢信用卡卡片總覽電文 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/cards/overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW302RRes> sendCEW302R(@RequestHeader("custACID") String custACID);

    /** 設定電子帳單設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/emailBill/setting/add", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW213RResponse> sendCEW213R(@RequestHeader("custAcid") String custAcid, @RequestParam("CustEMLF") String custEMLF, @RequestParam("CustBILL") String custBILL);

    /** 查詢正卡帳單明細 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-details/master-card/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW314RResponse> sendCEW314R(@RequestHeader("custId11") String custId11, @RequestParam("custIdBlmt") String custIdBlmt);

    /** 查詢附卡帳單明細 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-details/supplementary-card/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW218RResponse> sendCEW218R(@RequestHeader("vqidno") String vqidno, @RequestParam("vqcdno") String vqcdno, @RequestParam("vqqrym") String vqqrym);

    /** 富御金餘額查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/fubon-gold/balance/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<VB0051Response> sendVB0051(@RequestHeader("custId") String accid, @RequestParam("montyp") String montyp);

    /** 富御金歷程查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/fubon-gold/history/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<List<VB0052Rep>> sendVB0052(@RequestHeader("custId") String accid, @RequestParam("montyp") String montyp);

    /** 查詢信用卡紅利積點 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bonuses/bonus-point/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW306RResponse> sendCEW306R(@RequestHeader("custId") String custId);

    /** 查詢紅利積點兌換紀錄 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bonuses/exchange-record/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BonusExchangeResponse getBonusExchangeList(@RequestHeader("custId") String custId);

    /** 查詢虛擬卡資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/virtual-card/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW336RResponse> sendCEW336R(@RequestHeader("custId") String custId);

    /** 查詢年度累積消費 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-details/yearly-accumulated-consumption/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW321RResponse> sendCEW321R(@RequestParam("CRDNO") String cardNo);

    /** 查詢保費權益設定(以歸戶ID查詢) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/insur-fee-benefits/acid/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW327RResponse sendCEW327RByCustId(@RequestHeader("ACID") String custId);

    /** 查詢保費權益設定(以卡號查詢) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/insur-fee-benefits/cdno/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW327RResponse sendCEW327RByCardNo(@RequestHeader("CDNO") String cardNo);

    /** 變更保費權益設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/insur-fee-benefits/{insuType},{channel}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW328RResponse sendCEW328R(@RequestHeader("crdno") String crdno, @PathVariable("insuType") String insuType, @PathVariable("channel") String channel);

    /** 查詢保費權益設定(以歸戶ID查詢) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/insur-fee-benefits-new/acid/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW345RResponse sendCEW345RByCustId(@RequestHeader("ACID") String custId);

    /** 查詢保費權益設定(以卡號查詢) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/insur-fee-benefits-new/cdno/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW345RResponse sendCEW345RByCardNo(@RequestHeader("CDNO") String cardNo);

    /** 變更保費權益設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/insur-fee-benefits-new/{insuTypeA},{insuTypeB},{channel}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW346RResponse sendCEW346R(@RequestHeader("crdno") String crdno, @PathVariable("insuTypeA") String insuTypeA, @PathVariable("insuTypeB") String insuTypeB, @PathVariable("channel") String channel);

    /** 查詢已綁定之行動支付卡 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/mobile-payment/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW329RResponse> sendCEW329R(@RequestHeader("custId") String custId);

    /** 查詢當期帳單已繳明細 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-details/paid-current/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW230RResponse> sendCEW230R(@RequestHeader("custAcid") String custAcid);

    /** 設定信用卡暱稱 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/nickname/add", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW424RResponse> sendCEW424R(@RequestHeader("VIIDNO") String VIIDNO, @RequestBody CEW424RRequest request);

    /** 查詢申辦信用卡客戶資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/apply-customer-info/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW013RRes sendCEW013R(@RequestHeader("custId") String custId);

    /** 查詢信用卡戶會員基本資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/card-user/base-info/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CE6210RResponse getCardUserBaseInfo(@RequestHeader("custId") String custId);

    /** 信用卡Email設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/card-user/email/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean setCardUserEmail(@RequestHeader("custId") String custId, @RequestParam("email") String email);

    /** 信用卡電子帳單設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/emailBill/add", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CardEmailBillModel> saveCardEmailBill(@RequestBody CardEmailBillModel cardEmailBillModel);

    /** 查詢推薦碼內容 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/emailBill/shortener/params", produces = MediaType.APPLICATION_JSON_VALUE)
    RecommendInfoRes getShortenerParams(@RequestParam("urlCode") String urlCode);

    /** 更新信用卡消費訊息通知設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/notification/{companyKind},{uidDup}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateCardNotification(@RequestBody UpdateCardNotificationRequest request, @PathVariable("companyKind") Integer companyKind, @PathVariable("uidDup") String uidDup);

    /** 產生超商繳款條碼 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-bill-barcode/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW325RResponse sendCEW325R(@RequestHeader("custId") String custId, @RequestParam("functionType") String functionType, @RequestParam("custPayAmt") BigDecimal custPayAmt);

    /** 信用卡OTP */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards-otp/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW431RResponse sendCEW431R(@RequestBody CEW431RRequest request);

    /** 預借現金密碼設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/cash-advance-pwde/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW320RResponse sendCEW320R(@RequestBody CEW320RRequest request);

    /** 預借現金資料檢核 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/cash-advance-check/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW311RResponse sendCEW311R(@RequestBody CEW311RRequest request);

    /** 預借現金 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/cash-advance/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB12020009Response sendEB12020009(@RequestBody EB12020009Request request);

    /** 新增預借現金密碼設定紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/card-setting-cash-pwd/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer insertCardSettingCashPwd(@RequestBody InsertCardSettingCashPwdRequest request);

    /** 更新預借現金密碼設定紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/card-setting-cash-pwd/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateCardSettingCashPwd(@RequestBody UpdateCardSettingCashPwdRequest request);

    /** 簽帳卡明細查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/debit-card/txn-detail/get", produces = MediaType.APPLICATION_JSON_VALUE)
    DebitCardTxnDetailRs getDebitCardTxnDetails(@RequestParam("acno") String acno, @RequestParam("searchMonth") String searchMonth);

    /** 歸戶下附卡查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/info/supplementary-card/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW332RResponse sendCEW332R(@RequestHeader("custId") String custid);

    /** 財力證明調額白名單相關資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer-card-apply/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerCardApply getCustomerCardApply(@RequestHeader("custId") String custid);

    /** 查詢簽帳金融卡卡資 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/debit-card/card-data/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EB602655ARepeat> queryDebitCardDatas(@RequestParam("acnos") List<String> acnos);

    /** 查詢簽帳金融卡卡號對應卡片性質 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/debit-card/card-status/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FEP852835Repeat> queryDebitCardStatus(@RequestParam("acnos") List<String> acnos, @RequestParam("cardNo") String cardNo, @RequestHeader("companyUid") String companyUid);

    /** 信用卡道路救援車號-查詢/登錄/刪除 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/roadside-assistance/operation", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW316RResponse roadsideAssistanceOperation(@RequestBody CEW316RRequest request);

    /** 取得簽訂約定書註記 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/installments/agreement/sign/mark/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean sendCEW227R(@RequestParam("custId") String custId, @RequestParam("txtype") String txtype, @RequestParam("txbseqn") String txbseqn);

    /** 取得分期未入帳金額(CEW326R) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/unposted/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW326RResponse sendCEW326R(@RequestParam("custIxd") String custIxd);

    /** 指定消費分期試算交易 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/calculation-txn/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW220RResponse calculationTransaction(@Validated @RequestBody CEW220RRequest request);

    /** 單筆分期申請 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/single-apply/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW221RResponse calculationTransaction(@Validated @RequestBody CEW221RRequest request, @RequestHeader("custId") String custId);

    /** 帳單分期申請 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/bill-apply/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW317RResponse calculationTransaction(@Validated @RequestBody CEW317RRequest request, @RequestHeader("custId") String custId);

    /** 指定消費分期設定交易 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/designated-configuration-txn/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CE4153RResponse installmentConfigurationTxn(@Validated @RequestBody CE4153RRequest request);

    /** 查核定稅分期設定交易 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/check-determine-txn/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CE4152RResponse checkAndDetermineTaxTxn(@Validated @RequestBody CE4152RRequest request);

    /** 學雜費分期設定交易 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/tuition-fee-txn/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CE4151RResponse tuitionFeeTxn(@Validated @RequestBody CE4151RRequest request);

    /** 綜所稅分期設定交易 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/comprehensive-txn/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CE4150RResponse comprehensiveTaxTxn(@Validated @RequestBody CE4150RRequest request);

    /** 分期查詢交易 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/inquiry-txn/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW343RResponse sendCEW343R(@RequestHeader("custId") String custId);

    /** DB CARD_BILL_PERIOD_APPLY 帳單分期主檔 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/card-bill-period-apply/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CardBillPeriodApplyModel createCardBillPeriodApply(@Validated @RequestBody CardBillPeriodApplyModel request);

    /** DB CARD_BILL_PERIOD_APPLY 帳單分期主檔 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/card-bill-period-apply/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CardBillPeriodApplyModel updateCardBillPeriodApply(@Validated @RequestBody CardBillPeriodApplyModel request);

    /** DB CARD_STAGES_APPLY 單筆分期主檔 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/card-stages-apply/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CardStagesApplyModel createCardStagesApply(@Validated @RequestBody CardStagesApplyModel request);

    /** DB CARD_STAGES_APPLY 單筆分期主檔 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/card-stages-apply/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CardStagesApplyModel updateCardStagesApply(@Validated @RequestBody CardStagesApplyModel request);

    /** 信用卡帳單分期查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/installments/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    CEW315RResponse sendCEW315R(@RequestHeader("custId") String custId);

    /** 信用卡道路救援車號-新增CardCarnoApply Table交易資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/roadside-assistance/record/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CardCarnoApply initCardCarnoApplyData(@RequestBody CardCarnoApply applyData);

    /** 信用卡道路救援車號-更新CardCarnoApply Table交易資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/roadside-assistance/record/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CardCarnoApply updateCardCarnoApplyData(@RequestBody CardCarnoApply applyData);

    /** 固定調額啟案檢核 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/quota-adjustment/validte/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EBCC001Response sendEBCC001(@RequestHeader("custId") String cardHolderId, @RequestParam("adjType") String adjType);

    /** 新增額度調整交易資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/quota-adjustment/credit-limits-apply-data/{uidDup},{companyKind}/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CreditLimitsApplyData saveCreditLimitsApplyData(@RequestHeader("custId") String custid, @PathVariable("uidDup") String uidDup, @PathVariable("companyKind") Integer companyKind, @RequestBody CreditLimitsApplyData model);

    /** 更新額度調整交易資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/quota-adjustment/credit-limits-apply-data/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CreditLimitsApplyData updateCreditLimitsApplyData(@RequestBody CreditLimitsApplyData model);

    /** 固定調額啟案 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/quota-adjustment/transaction/add", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<EBCC002Response> sendEBCC002(@RequestBody() EBCC002Request request);

    /** 額度調整-上傳財力證明檔案 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/quota-adjustment/proof-file/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadProofFile(@RequestBody() List<String> fileNameList);

    /** 查詢前六個月帳單已繳明細 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-details/paid-record/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW231RResponse sendCEW231R(@RequestHeader("custAcid") String custAcid);

    /** CE5552R 客戶主動控卡設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/tx-card-control-settings/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CE5552RResponse sendCE5552R(@RequestBody CE5552RRequest request);

    /** DB CARD_CONTROL_SETTING 開發 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/card-control-settings/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CardControlSettingModel sendCardControlSetting(@RequestBody CardControlSettingModel request);

    /** CWE344R 信用卡附加權益查詢 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/card-additional-bonus/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<CEW344RResponse> sendCEW344R(@RequestHeader("custId") String custId);

    /** 單筆分期查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/installment/single/list", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW222RResponse sendCEW222R(@RequestHeader("custId") String custId);


    /** 查詢客戶簽帳卡資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/debit-card/user-card-data/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EB602652ARepeat> sendEB602652A(@RequestHeader("custId") String custId);
}
