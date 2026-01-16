package com.tfb.aibank.chl.general.utils;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.general.resource.InformationResource;
import com.tfb.aibank.common.code.AIBankConstants;

//@formatter:off
/**
* @(#)NGNUtils.java
* 
* <p>Description:CHL 通用(NGN)工具類別</p>
* <p>處理與「邏輯」無關的程式行為</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NGNUtils {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private InformationResource informationResource;

    /**
     * 從 SYSTEM_PARAM 取值
     * 
     * @param paramKey
     * @return
     */
    public String getValue(String paramKey) {
        return getValue(AIBankConstants.CHANNEL_NAME, paramKey);
    }

    /**
     * 從 SYSTEM_PARAM 取值
     * 
     * @param category
     * @param paramKey
     * @return
     */
    public String getValue(String category, String paramKey) {
        return systemParamCacheManager.getValue(category, paramKey);
    }

    //@formatter:off
    /**
     * <p>依身份證字號判斷是否啟用「滿意度問卷調查」</p>
     * <ul>
     *  <li>客戶ID末碼為1、2，顯示月份 1月、7月</li>
     *  <li>客戶ID末碼為3、4，顯示月份 2月、8月</li>
     *  <li>客戶ID末碼為5、6，顯示月份 3月、9月</li>
     *  <li>客戶ID末碼為7、8，顯示月份 4月、10月</li>
     *  <li>客戶ID末碼為9，顯示月份 5月、11月</li>
     *  <li>客戶ID末碼為0，顯示月份 6月、12月</li>
     * </ul> 
     * @return
     */
    //@formatter:on
    public boolean checkEnableSatisfaction(String custId) {
        boolean result = false;
        if (StringUtils.isNotBlank(custId)) {
            String lastCode = StringUtils.right(custId, 1);
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            switch (month) {
            case 1:
            case 7:
                result = StringUtils.equalsAny(lastCode, "1", "2");
                break;
            case 2:
            case 8:
                result = StringUtils.equalsAny(lastCode, "3", "4");
                break;
            case 3:
            case 9:
                result = StringUtils.equalsAny(lastCode, "5", "6");
                break;
            case 4:
            case 10:
                result = StringUtils.equalsAny(lastCode, "7", "8");
                break;
            case 5:
            case 11:
                result = StringUtils.equalsAny(lastCode, "9");
                break;
            case 6:
            case 12:
                result = StringUtils.equalsAny(lastCode, "0");
                break;
            }
        }
        return result;
    }

    /**
     * 檢核Email正確性及是否為黑名單
     * 
     * @param email
     * @return
     */
    public boolean checkEmail(String email) {
        return ValidatorUtility.checkEMail(email);
    }

}
