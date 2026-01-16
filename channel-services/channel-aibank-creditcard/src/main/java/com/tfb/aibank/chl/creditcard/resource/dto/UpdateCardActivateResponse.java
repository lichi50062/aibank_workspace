package com.tfb.aibank.chl.creditcard.resource.dto;

/**
 * 更新開卡紀錄 rs
 * 
 * @author Evan Wang
 *
 */
public class UpdateCardActivateResponse {
    /** 結果代碼 0:失敗 1:成功 */
    private Integer code;

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(Integer code) {
        this.code = code;
    }

}
