package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013030Rq.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 030 生物辨識設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG013030Rq implements RqData {

    /**
     * 新增同意條款記錄
     */
    private boolean saveContractLog;

    /**
     * 裝置種類
     */
    private String deviceType;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public boolean isSaveContractLog() {
        return saveContractLog;
    }

    public void setSaveContractLog(boolean saveContractLog) {
        this.saveContractLog = saveContractLog;
    }
}
