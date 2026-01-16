package com.tfb.aibank.chl.creditcard.tx004.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

//@formatter:off
/**
* @(#)NCCQU014010Rq.java
* 
* <p>Description:NCCTX004_道路救援登錄 RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/24
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX004020Rq implements RqData {

    /**
     * CEW316R
     * 交易別
     * (1) 	上送A：新增車號
     * (2) 	上送U：變更車號
     * (3) 	上送D：刪除車號
     * */
    private String txtType;


    private String creditCardKey;


    private String carNo;

    public String getTxtType() {
        return txtType;
    }

    public void setTxtType(String txtType) {
        this.txtType = txtType;
    }

    public String getCreditCardKey() {
        return creditCardKey;
    }

    public void setCreditCardKey(String creditCardKey) {
        this.creditCardKey = creditCardKey;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
}
