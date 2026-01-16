package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013010Rs.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG013010Rs implements RsData {

    /**
     * FIDO 綁定狀態
     */
    private boolean isFido;

    /**
     * 廠牌名稱
     */
    private String marketingName;

    /**
     * 檢核裝置系統版本
     */
    private boolean isCheckDeviceVersion;

    public boolean isFido() {
        return isFido;
    }

    public void setFido(boolean fido) {
        isFido = fido;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public boolean isCheckDeviceVersion() {
        return isCheckDeviceVersion;
    }

    public void setCheckDeviceVersion(boolean checkDeviceVersion) {
        isCheckDeviceVersion = checkDeviceVersion;
    }
}
