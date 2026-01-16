package com.tfb.aibank.chl.system.ot002.service;

import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.system.ot002.model.NSTOT002Output;
import com.tfb.aibank.chl.system.service.NSTService;

// @formatter:off
/**
 * @(#)NSTOT002Service.java
 * 
 * <p>Description:CHL NSTOT002 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NSTOT002Service extends NSTService {

    /**
     * 讀取「輔助說明」、「備註」
     * 
     * @param remarkType
     * @param remarkKey
     * @param userLocale
     * @param dataOutput
     */
    public void getRemarkContent(String remarkType, String remarkKey, String userLocale, NSTOT002Output dataOutput) {
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(remarkType, remarkKey, userLocale);
        dataOutput.setRemarkContent(remarkContent);
    }
}
