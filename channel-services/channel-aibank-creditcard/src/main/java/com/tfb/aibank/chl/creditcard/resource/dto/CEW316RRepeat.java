/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
import java.util.Date; /**
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
public class CEW316RRepeat {

    /** 卡號 */
    private String crdNo;

    /** 車號
     * 回傳分隔線”-“*/
    private String carNo;

    /** 生效日
     * 回傳格式為CCCMMDD*/
    private String opDay;

    /** 生效日 */
    private Date opDate;

    /** 生效日 */
    private String opDateSimple;

    /** 登錄狀態 */
    private String carSts;

    /** 卡別 */
    private String crdsw;

    public String getCrdNo() {
        return crdNo;
    }

    public void setCrdNo(String crdNo) {
        this.crdNo = crdNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getOpDay() {
        return opDay;
    }

    public void setOpDay(String opDay) {
        this.opDay = opDay;
    }

    public String getCarSts() {
        return carSts;
    }

    public void setCarSts(String carSts) {
        this.carSts = carSts;
    }

    public String getCrdsw() {
        return crdsw;
    }

    public void setCrdsw(String crdsw) {
        this.crdsw = crdsw;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public String getOpDateSimple() {
        return opDateSimple;
    }

    public void setOpDateSimple(String opDateSimple) {
        this.opDateSimple = opDateSimple;
    }
}
