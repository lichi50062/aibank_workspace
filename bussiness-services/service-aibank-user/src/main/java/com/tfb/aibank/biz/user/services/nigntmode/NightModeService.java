package com.tfb.aibank.biz.user.services.nigntmode;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.biz.user.repository.NightModeRepository;
import com.tfb.aibank.biz.user.repository.entities.NightModeEntity;

/**
 // @formatter:off
 * @(#)NightModeService.java
 * 
 * <p>Description:[共通業務辦法 2.6.23.  夜間模式判斷]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/8/22, Leiley
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NightModeService {

    private static final IBLog logger = IBLog.getLog(NightModeService.class);
    private NightModeRepository nightModeRepo;

    public NightModeService(NightModeRepository nightModeRepo) {
        this.nightModeRepo = nightModeRepo;
    }

    public Boolean checkNightMode(boolean checkBancs) {
        logger.debug("checkBancs : {}", checkBancs);

        boolean isNightMode = false;
        try {
            // 查詢HOST_NIGHTMODE,來取得夜間模式或日間模式
            List<NightModeEntity> nightModeList = nightModeRepo.findAll();
            Calendar c = Calendar.getInstance();
            String nowTime = StringUtils.leftPadZero(String.valueOf(c.get(Calendar.HOUR_OF_DAY)), 2).concat(":").concat(StringUtils.leftPadZero(String.valueOf(c.get(Calendar.MINUTE)), 2));
            LocalTime start = LocalTime.parse("09:00");
            LocalTime end = LocalTime.parse("21:00");
            LocalTime now = LocalTime.parse(nowTime);
            // A. 若checkBancs=false，若系統時間介於晚上9點至早上9點(夜間模式)，無法執行該交易
            if (!checkBancs) {
                isNightMode = now.isAfter(end) || now.isBefore(start);

            }
            // B. 若checkBancs=true，查詢HOST_NIGHTMODE.RegionMode。若
            // (A) 若RegionMode=D，表示目前BaNCs為日間模式。
            // (B) 若RegionMode=N，表示目前BaNCs為夜間模式，無法執行該交易
            else {
                String nightMode = nightModeList.get(0).getRegionMode();
                logger.debug("nightMode.RegionMode:{}", nightMode);
                isNightMode = "N".equals(nightMode) ? true : false;
            }

            logger.debug("isNightMode:{}", isNightMode);
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            // 若查詢途中發生錯誤,一律為日間模式
            logger.error("HOST_NIGHTMODE QueryException :{} ", ex);
            isNightMode = false;
        }

        return isNightMode;
    }
}
