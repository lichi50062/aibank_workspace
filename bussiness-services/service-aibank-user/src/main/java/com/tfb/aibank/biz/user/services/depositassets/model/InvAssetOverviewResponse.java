
package com.tfb.aibank.biz.user.services.depositassets.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.ibm.tw.commons.util.NumberUtils;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 資產總覽InvAssetOverviewResponse Usage: for all ESB query to query assets
 */
@Schema(description = "InvAssetOverviewRequest")
public class InvAssetOverviewResponse {

    public InvAssetOverviewResponse() {
        this.totalAmount = NumberUtils.createBigDecimal("0");
        this.esbResultMap = new HashMap<>();
        this.esbValueMap = new HashMap<>();
    }

    @Schema(description = "資產總覽總值")
    private BigDecimal totalAmount;

    @Schema(description = "發送的電文及結果")
    private Map<String, String> esbResultMap;

    @Schema(description = "由電文取得的值")
    private Map<String, BigDecimal> esbValueMap;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Map<String, String> getEsbResultMap() {
        return esbResultMap;
    }

    public void setEsbResultMap(Map<String, String> esbResultMap) {
        this.esbResultMap = esbResultMap;
    }

    public Map<String, BigDecimal> getEsbValueMap() {
        return esbValueMap;
    }

    public void setEsbValueMap(Map<String, BigDecimal> esbValueMap) {
        this.esbValueMap = esbValueMap;
    }
}