package com.tfb.aibank.chl.creditcard.ag002.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;


// @formatter:off
/**
 *
 * <p>Description:信用卡電子帳單設定 功能首頁</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:off
@Component
public class NCCAG002010Rq implements RqData {

    public String urlCode;

    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }


}
