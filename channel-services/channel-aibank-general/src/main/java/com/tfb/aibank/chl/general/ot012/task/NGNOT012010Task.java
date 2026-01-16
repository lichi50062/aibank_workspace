/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.ot012.task;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot012.model.NGNOT012010Rq;
import com.tfb.aibank.chl.general.ot012.model.NGNOT012010Rs;

//@formatter:off
/**
* @(#)NGNOT012010Rq.java
* 
* <p>Description:共用跳頁功能 010 功能首頁</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/11, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NGNOT012010Task extends AbstractAIBankBaseTask<NGNOT012010Rq, NGNOT012010Rs> {

    @Override
    public void validate(NGNOT012010Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT012010Rq rqData, NGNOT012010Rs rsData) throws ActionException {

        /** 參數為空 */
        if (StringUtils.isBlank(rqData.getParam())) {
            logger.warn("NGNOT012 參數錯誤:{}", rqData.getParam());
            return;
        }

        /** 參數無法解析 */
        JsonElement passinElement = JsonParser.parseString(rqData.getParam());
        if (passinElement == null) {
            logger.warn("NGNOT012 參數錯誤:{}", rqData.getParam());
            return;
        }
        JsonObject passinParam = passinElement.getAsJsonObject();

        /** 所有 CustomParam 集合 */
        JsonObject jsonObject = new JsonObject();

        /** 所有傳入參數 */
        for (String key : passinParam.keySet()) {

            /** Channel ID */
            if ("target".equals(key)) {
                rsData.setTarget(passinParam.get(key).getAsString());
                continue;
            }

            /** customParams 的型態 */
            if ("customParams".equals(key)) {
                String extParam = new String(Base64.getDecoder().decode(passinParam.get(key).getAsString()), StandardCharsets.UTF_8);
                if (StringUtils.isNotBlank(extParam)) {
                    JsonElement customElement = JsonParser.parseString(extParam);
                    if (customElement == null) {
                        continue;
                    }
                    JsonObject customObject = customElement.getAsJsonObject();

                    for (String customKey : customObject.keySet()) {
                        jsonObject.add(customKey, customObject.get(customKey));
                    }

                }

                continue;
            }

            /** 所有其他 */
            jsonObject.add(key, passinParam.get(key));
        }

        if (!jsonObject.isJsonNull())
            rsData.setCustomParams(Base64.getEncoder().encodeToString(jsonObject.toString().getBytes()));

    }
}
