package com.tfb.aibank.chl.general.resource;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@formatter:off
/**
 * @(#)SystemConfigResource.java
 *
 * <p>Description:溝通 service-aibank-system-config 的介面</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Horance
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-chl-general-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
public interface SystemConfigResource {
    /** 查詢MenuId 相關的關鍵字 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/menus/menu-keyword/query", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, List<String>> getMenuKeywordsForSearch(@RequestBody List<String> menuIds);

    /** 達上限暫停登入Session是否開啟狀態 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/session/max-login-forbid/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean isMaxLoginForbid();
}
