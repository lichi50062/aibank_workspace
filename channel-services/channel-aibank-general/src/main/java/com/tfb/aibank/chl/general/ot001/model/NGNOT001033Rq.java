package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001033Rq.java 
* 
* <p>Description:確認圖形頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230605, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001033Rq implements RqData {

    /**
     * 加密後的圖形密碼
     */
    private String pinBlock;

    /**
     * @return the pinBlock
     */
    public String getPinBlock() {
        return pinBlock;
    }

    /**
     * @param pinBlock
     *            the pinBlock to set
     */
    public void setPinBlock(String pinBlock) {
        this.pinBlock = pinBlock;
    }

}
