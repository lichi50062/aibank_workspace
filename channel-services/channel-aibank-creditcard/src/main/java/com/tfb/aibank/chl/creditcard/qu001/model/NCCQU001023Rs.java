package com.tfb.aibank.chl.creditcard.qu001.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001021Rs.java
* 
* <p>Description:信用卡總覽 卡片管理頁 重新取得信用卡綁定行動支付類型資料 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Lis
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001023Rs implements RsData {
    /** 發送電文CEW329R取得已綁定支付資訊失敗 */
    private boolean cew329rError;
    /** 信用卡綁定行動支付類型 */
    private List<NCCQQU001PayType> payTypes;
    
    /**
     * @return the cew329rError
     */
    public boolean isCew329rError() {
        return cew329rError;
    }
    
    /**
     * @param cew329rError
     *            the cew329rError to set
     */
    public void setCew329rError(boolean cew329rError) {
        this.cew329rError = cew329rError;
    }
    /**
     * @return the payTypes
     */
    public List<NCCQQU001PayType> getPayTypes() {
        return payTypes;
    }
    
    /**
     * @param payTypes
     *            the payTypes to set
     */
    public void setPayTypes(List<NCCQQU001PayType> payTypes) {
        this.payTypes = payTypes;
    }


}
