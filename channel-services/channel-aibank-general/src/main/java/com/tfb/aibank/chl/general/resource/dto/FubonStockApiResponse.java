/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

// @formatter:off
/**
 * @(#)FubonStockApiResponse.java
 * 
 * <p>Description:富邦證券整註記異動API 下行資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FubonStockApiResponse {

    /**
     * 序號
     */
    private String seqNo;

    /**
     * 股票資料
     */
    private List<AssetData> datas;

    /**
     * 查詢日期 e.g. YYYYMMDD hh:mm:ss
     */
    private Date qryDate;

    /**
     * 是否以開戶(證券) e.g. Y or N
     */
    private String isValid;

    /**
     * 庫存市值(帳面價值)總和, 18碼, 後5碼為小數
     */
    private BigDecimal sumAssetAmtTwd;

    public FubonStockApiResponse() {
    }

    public FubonStockApiResponse(String seqNo, List<AssetData> datas, Date qryDate, String isValid, BigDecimal sumAssetAmtTwd) {
        this.seqNo = seqNo;
        this.datas = datas;
        this.qryDate = qryDate;
        this.isValid = isValid;
        this.sumAssetAmtTwd = sumAssetAmtTwd;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public List<AssetData> getDatas() {
        return datas;
    }

    public void setDatas(List<AssetData> datas) {
        this.datas = datas;
    }

    public Date getQryDate() {
        return qryDate;
    }

    public void setQryDate(Date qryDate) {
        this.qryDate = qryDate;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public BigDecimal getSumAssetAmtTwd() {
        return sumAssetAmtTwd;
    }

    public void setSumAssetAmtTwd(BigDecimal sumAssetAmtTwd) {
        this.sumAssetAmtTwd = sumAssetAmtTwd;
    }

    @Override
    public String toString() {
        return "FubonStockApiResponse [seqNo=" + seqNo + ", datas=" + datas + ", qryDate=" + qryDate + ", isValid=" + isValid + ", sumAssetAmtTwd=" + sumAssetAmtTwd + "]";
    }
}
