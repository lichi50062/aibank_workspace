package com.tfb.aibank.chl.creditcard.ag002.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG002010Rs.java
 * 
 * <p>Description:信用卡電子帳單設定 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG002010Rs implements RsData {

    /** 申請實體帳單 */
    private boolean applyPhysicalBill;

    /** 申請電子帳單 */
    private boolean applyElectronicBill;

    /** 電子信箱 */
    private String email;

    /** 查詢失敗 */
    private boolean queryfail;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApplyPhysicalBill() {
        return applyPhysicalBill;
    }

    public void setApplyPhysicalBill(boolean applyPhysicalBill) {
        this.applyPhysicalBill = applyPhysicalBill;
    }

    public boolean isApplyElectronicBill() {
        return applyElectronicBill;
    }

    public void setApplyElectronicBill(boolean applyElectronicBill) {
        this.applyElectronicBill = applyElectronicBill;
    }

    public boolean isQueryfail() {
        return queryfail;
    }

    public void setQueryfail(boolean queryfail) {
        this.queryfail = queryfail;
    }


}
