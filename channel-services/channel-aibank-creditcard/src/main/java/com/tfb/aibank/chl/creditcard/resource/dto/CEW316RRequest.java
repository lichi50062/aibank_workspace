/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW316RRequest.java
* 
* <p>Description:CEW316R 信用卡道路救援車號 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW316RRequest {

    /** 資料別
     * 上送1：查詢
     * 上送2：登錄/刪除
     * */
    private String dataType;

    /** 持卡人ID */
    private String custId11;

    /** 卡號 */
    private String custCrdno;


    /** 交易別
     * (1) 	上送A：新增車號
     * (2) 	上送U：變更車號
     * (3) 	上送D：刪除車號*/
    private String txtType;


    /** 車號
     * (1) 	若TxtType=A或U，上送「編輯頁」輸入之[車號]
     * (2) 	若TxtType=D，不上送此欄位
     * */
    private String carnum;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getCustId11() {
        return custId11;
    }

    public void setCustId11(String custId11) {
        this.custId11 = custId11;
    }

    public String getCustCrdno() {
        return custCrdno;
    }

    public void setCustCrdno(String custCrdno) {
        this.custCrdno = custCrdno;
    }

    public String getTxtType() {
        return txtType;
    }

    public void setTxtType(String txtType) {
        this.txtType = txtType;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
}
