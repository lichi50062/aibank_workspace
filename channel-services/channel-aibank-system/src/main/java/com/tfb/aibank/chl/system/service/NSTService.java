package com.tfb.aibank.chl.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tfb.aibank.chl.service.AIBankChannelService;
import com.tfb.aibank.chl.system.resource.InformationResource;
import com.tfb.aibank.chl.system.resource.InvestResource;
import com.tfb.aibank.chl.system.resource.PreferencesResource;
import com.tfb.aibank.chl.system.resource.UserResource;

// @formatter:off
/**
 * @(#)NSTService.java
 * 
 * <p>Description:CHL 系統(NST）服務類別，撰寫此大類共同的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NSTService extends AIBankChannelService {

    @Autowired
    protected InformationResource informationResource;

    @Autowired
    protected InvestResource investResource;

    @Autowired
    protected UserResource userResource;

    @Autowired
    protected PreferencesResource preferencesResource;

    /**
     * 這是一個範例 method
     * 
     * @param input
     *            傳入的資料
     * @param output
     *            回傳的資料
     */
    public void example(Object input, Object output) {
        // TODO: 處理與邏輯相關的程式行為
    }

}
