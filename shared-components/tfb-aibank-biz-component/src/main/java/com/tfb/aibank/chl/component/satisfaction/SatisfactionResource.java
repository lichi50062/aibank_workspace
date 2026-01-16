package com.tfb.aibank.chl.component.satisfaction;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

//@formatter:off
/**
 * @(#)SatisfactionResource.java
 *
 * <p>Description:溝通 service-aibank-information 的介面</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/09/20, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-biz-component-satisfaction-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
public interface SatisfactionResource {

    /** 查詢資料表(AIBANK_SERVICE_SETTING)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/aibank-service-setting/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AibankServiceSetting> findAllAibankServiceSetting();

    /** 查詢資料表(AIBANK_SERVICE_KPI_QUESTION、AIBANK_SERVICE_KPI_OPTION)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/aibank-service-question-option/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AibankServiceQuestionOption> findAllAibankServiceQuestionOption();

}
