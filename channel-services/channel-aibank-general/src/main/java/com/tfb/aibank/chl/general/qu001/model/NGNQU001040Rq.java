package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;
// @formatter:off
/**
 * @(#)NGNQU001040Rq.java
 *
 * <p>Description: NGNQU001040Rq</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[NGNQU001040Rq]</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001040Rq implements RqData {

    /**
     * 裝置端有常用功能紀錄
     * */
    private boolean haveDeviceRecord;

    public boolean isHaveDeviceRecord() {
        return haveDeviceRecord;
    }

    public void setHaveDeviceRecord(boolean haveDeviceRecord) {
        this.haveDeviceRecord = haveDeviceRecord;
    }
}
