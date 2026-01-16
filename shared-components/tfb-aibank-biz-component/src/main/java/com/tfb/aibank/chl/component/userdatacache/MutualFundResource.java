package com.tfb.aibank.chl.component.userdatacache;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.userdatacache.model.CM061435CRRes;
import com.tfb.aibank.chl.component.userdatacache.model.EB062171ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE071Req;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE072Req;
import com.tfb.aibank.common.model.FundOverview;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)MutualFundResource.java
 * 
 * <p>Description:溝通 service-aibank-mutual-fund 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-user-data-cache-to-mutual-fund-service", url = "${aibank.common.feign.service-aibank-mutual-fund-url:http://svc-biz-mutual-fund:8080}")
public interface MutualFundResource {

    /** 查詢「簽訂基金電子契約」 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/e-contract-status/query", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean checkFundElectronicContractStatus(@RequestHeader(value = "custId") String custId, @RequestParam("nameCode") String nameCode);

    /** 查詢基金OBU帳戶總覽 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/overview/OBU/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FundOverview> queryNFEE071(@RequestBody NFEE071Req request);

    /** 查詢基金帳戶DBU總覽 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/overview/DBU/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FundOverview> queryNFEE072(@RequestBody NFEE072Req request);

    /** 查詢高資產客戶資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutualfund/high-asset-status/query", produces = { MediaType.APPLICATION_JSON_VALUE })
    CM061435CRRes queryHighNetWorthClientData(@RequestHeader(value = "custId") @Schema(description = "身份證字號") String custId);

    /** 查詢使用者撥貸狀態 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-fund/tx/loan-allocate/query", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EB062171ResRep> queryLoanAllocate(@RequestHeader("custId") String custId);

    /** 查詢高資產法人授權人ID驗證 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutualfund/high-asset-status-auth/query", produces = { MediaType.APPLICATION_JSON_VALUE })
    CM061435CRRes queryHighNetWorthAuthClientData(@RequestHeader(value = "custId") String custId, @RequestHeader(value = "authId") String authId);
}
