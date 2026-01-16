/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

/**
 * @author john
 *
 */
public class BonusExchangeResponse {

    /**
     * 兌換資料
     */
    private List<BonusExchangeResponseRepeat> items;

    /**
     * 查詢狀態 0-正常 1-沒有資料
     */
    private String status;

    /**
     * @return the items
     */
    public List<BonusExchangeResponseRepeat> getItems() {
        return items;
    }

    /**
     * @param items
     *            the items to set
     */
    public void setItems(List<BonusExchangeResponseRepeat> items) {
        this.items = items;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
