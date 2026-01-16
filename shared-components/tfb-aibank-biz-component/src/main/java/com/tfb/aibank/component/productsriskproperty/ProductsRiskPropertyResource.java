package com.tfb.aibank.component.productsriskproperty;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

//@formatter:off
/**
* @(#)ProductsRiskPropertyResource.java
* 
* <p>Description:溝通 service-aibank-invest 的介面</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/18 Evan Wang,
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-products-risk-property-service", url = "${aibank.common.feign.service-aibank-mutual-fund-url:http://svc-biz-mutual-fund:8080}")
public interface ProductsRiskPropertyResource {

    /** 取得全部商品風險屬性資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutualfund/prod-risk-props/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<ProductsRiskProperty> getAllProductsRiskProperties();
}
