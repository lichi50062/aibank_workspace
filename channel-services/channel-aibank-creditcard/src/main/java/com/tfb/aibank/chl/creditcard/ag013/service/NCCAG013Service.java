package com.tfb.aibank.chl.creditcard.ag013.service;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

// @formatter:off
/**
 * @(#)NCCAG013Service.java
 * 
 * <p>Description:CHL NCCAG013 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCAG013Service extends NCCService {

    /** FIDO2綁定條款 RemarkKey */
    public static final String TERMS_REMARK_KEY = "NCCAG013_020";

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    private static final String PUSH_START_TIME = "0800";
    private static final String PUSH_END_TIME = "2200";

    public Date getEndDate() {
        String daysStr = systemParamCacheManager.getValue("AIBANK", "NOTIFICATION_DURATION","90");
        int day = Integer.parseInt(daysStr);
        return DateUtils.addDays(new Date(), day);
    }

    public boolean checkIsPush(Integer notifyFlag, Integer notifyPass) {
        if (notifyFlag == null || notifyPass == null) {
            return false;
        }
        if (notifyFlag == 0) {
            return false;
        }
        else if (notifyFlag == 1) {
            return checkIsPush(notifyPass);
        }
        return false;
    }

    public boolean checkIsPush(Integer notifyPass) {
        if (notifyPass == null) {
            return false;
        }
        if (notifyPass == 1) {
            return true;
        }
        else if (notifyPass == 2) {
            String sysTime = DateFormatUtils.SIMPLE_TIME_FORMAT_HOUR_MINUTE.format(new Date());
            if (sysTime.compareTo(PUSH_START_TIME) > 0 && sysTime.compareTo(PUSH_END_TIME) < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 取得資料庫錯誤訊息
     *
     * @param errorCode
     * @return
     */
    public String getEPayErrorMessage(String errorCode, Locale locale) {
        String message = IBUtils.getErrorDesc(errorCodeCacheManager, IBSystemId.FIDO2.getSystemId(), errorCode, locale, "default");
        if (StringUtils.isEmpty(message)) {
            return I18NUtils.getMessage("nccag013.error.desc");
        } else {
            return message;
        }
    }

    /**
     * FIDO2 API 錯誤碼去頭共用方法, 資料庫存不了8位以上的錯誤碼
     * @param code
     * @return
     */
    public String normalizeErrorCode(String code) {
        if (code == null) return null;
        return code.startsWith("VeriFIDO-") ? code.substring("VeriFIDO-".length()) : code;
    }
}
