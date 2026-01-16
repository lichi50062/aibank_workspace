package com.tfb.aibank.biz.user.services.depositassets.model;

import java.math.BigDecimal;
import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

import io.swagger.v3.oas.annotations.media.Schema;
import tw.com.ibm.mf.eb.EB555692SvcRsType;

// @formatter:off
/**
 * @(#)EB555692Response.java
 * 
 * <p>Description:EB555692 網路銀行歸戶資產查詢 API下行欄位.</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "網路銀行歸戶資產查詢 電文下行")
public class EB555692Response extends TxHeadRs {

    private static final long serialVersionUID = -6807124593174891035L;

    public EB555692Response() {
        // default constructor
    }

    @Schema(description = "查無資料Flag -> Y/N")
    private String noDataFlag = "N";

    @Schema(description = "台幣資產總計+外幣資產總計")
    private BigDecimal totalAmt;

    @Schema(description = "多戶名判斷")
    private String nameCodMore;

    @Schema(description = "台幣資產總計")
    private BigDecimal totalAmtNtd;

    @Schema(description = "外幣資產總計")
    private BigDecimal totalAmtFrg;

    @Schema(description = "房貸餘額總計")
    private BigDecimal totalAmt61;

    @Schema(description = "信貸餘額總計")
    private BigDecimal totalAmt62;

    @Schema(description = "學貸餘額總計")
    private BigDecimal totalAmt63;

    @Schema(description = "個人周轉金貸款餘額總計")
    private BigDecimal totalAmt64;

    @Schema(description = "企業貸款餘額總計")
    private BigDecimal totalAmt65;

    @Schema(description = "綜存質借")
    private BigDecimal totalAmt67;

    @Schema(description = "循環型信貸")
    private BigDecimal totalAmt68;

    @Schema(description = "循環型房貸")
    private BigDecimal totalAmt69;

    @Schema(description = "存單質借")
    private BigDecimal totalAmt70;

    @Schema(description = "新型循環型信貸")
    private BigDecimal totalAmt73;

    @Schema(description = "就貸")
    private BigDecimal totalAmt6302;

    @Schema(description = "勞工紓困")
    private BigDecimal totalAmt6303;

    @Schema(description = "細項")
    private List<EB555692Repeat> repeats;

    private String errorCode;

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getNameCodMore() {
        return nameCodMore;
    }

    public void setNameCodMore(String nameCodMore) {
        this.nameCodMore = nameCodMore;
    }

    public BigDecimal getTotalAmtNtd() {
        return totalAmtNtd;
    }

    public void setTotalAmtNtd(BigDecimal totalAmtNtd) {
        this.totalAmtNtd = totalAmtNtd;
    }

    public BigDecimal getTotalAmtFrg() {
        return totalAmtFrg;
    }

    public void setTotalAmtFrg(BigDecimal totalAmtFrg) {
        this.totalAmtFrg = totalAmtFrg;
    }

    public List<EB555692Repeat> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<EB555692Repeat> repeats) {
        this.repeats = repeats;
    }

    public BigDecimal getTotalAmt61() {
        return totalAmt61;
    }

    public void setTotalAmt61(BigDecimal totalAmt61) {
        this.totalAmt61 = totalAmt61;
    }

    public BigDecimal getTotalAmt62() {
        return totalAmt62;
    }

    public void setTotalAmt62(BigDecimal totalAmt62) {
        this.totalAmt62 = totalAmt62;
    }

    public BigDecimal getTotalAmt63() {
        return totalAmt63;
    }

    public void setTotalAmt63(BigDecimal totalAmt63) {
        this.totalAmt63 = totalAmt63;
    }

    public BigDecimal getTotalAmt64() {
        return totalAmt64;
    }

    public void setTotalAmt64(BigDecimal totalAmt64) {
        this.totalAmt64 = totalAmt64;
    }

    public BigDecimal getTotalAmt65() {
        return totalAmt65;
    }

    public void setTotalAmt65(BigDecimal totalAmt65) {
        this.totalAmt65 = totalAmt65;
    }

    public BigDecimal getTotalAmt67() {
        return totalAmt67;
    }

    public void setTotalAmt67(BigDecimal totalAmt67) {
        this.totalAmt67 = totalAmt67;
    }

    public BigDecimal getTotalAmt68() {
        return totalAmt68;
    }

    public void setTotalAmt68(BigDecimal totalAmt68) {
        this.totalAmt68 = totalAmt68;
    }

    public BigDecimal getTotalAmt69() {
        return totalAmt69;
    }

    public void setTotalAmt69(BigDecimal totalAmt69) {
        this.totalAmt69 = totalAmt69;
    }

    public BigDecimal getTotalAmt70() {
        return totalAmt70;
    }

    public void setTotalAmt70(BigDecimal totalAmt70) {
        this.totalAmt70 = totalAmt70;
    }

    public BigDecimal getTotalAmt73() {
        return totalAmt73;
    }

    public void setTotalAmt73(BigDecimal totalAmt73) {
        this.totalAmt73 = totalAmt73;
    }

    public BigDecimal getTotalAmt6302() {
        return totalAmt6302;
    }

    public void setTotalAmt6302(BigDecimal totalAmt6302) {
        this.totalAmt6302 = totalAmt6302;
    }

    public BigDecimal getTotalAmt6303() {
        return totalAmt6303;
    }

    public void setTotalAmt6303(BigDecimal totalAmt6303) {
        this.totalAmt6303 = totalAmt6303;
    }

    public String getNoDataFlag() {
        return noDataFlag;
    }

    public void setNoDataFlag(String noDataFlag) {
        this.noDataFlag = noDataFlag;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
