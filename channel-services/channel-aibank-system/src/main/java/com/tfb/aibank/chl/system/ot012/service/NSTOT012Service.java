package com.tfb.aibank.chl.system.ot012.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.system.resource.UserResource;
import com.tfb.aibank.chl.system.resource.dto.KnowledgeCheckRequest;
import com.tfb.aibank.chl.system.service.NSTService;

// @formatter:off
/**
 * @(#)NSTOT012Service.java
 * 
 * <p>Description:CHL NSTOT012 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/05, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NSTOT012Service extends NSTService {

    @Autowired
    private UserResource userResource;

    /**
     * 讀取「輔助說明」、「備註」
     * 
     * @param remarkType
     * @param remarkKey
     * @param userLocale
     * @param dataOutput
     */
    public void addKnowledgeCheck(KnowledgeCheckRequest rq) {

        userResource.addKnowledgeCheck(rq);
    }
}
