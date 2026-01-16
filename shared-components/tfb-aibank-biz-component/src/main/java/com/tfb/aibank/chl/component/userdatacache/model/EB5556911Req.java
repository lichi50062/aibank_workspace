/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.List;
import java.util.Map;

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5556911Request.java
 * 
 * <p>Description:約定轉入帳號 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "約定轉入帳號 上行電文")
public class EB5556911Req extends TxHeadRq {

    private static final long serialVersionUID = 2971037083314685294L;

    /** 需要同步約定 */
    @Schema(description = "需要同步約定")
    private boolean syncAgreeIn;

    /** 身份證字號 */
    @Schema(description = "身份證字號")
    private String idno;

    /** 戶名代碼 */
    @Schema(description = "戶名代碼")
    private String nameCod;

    /** 使用者代碼 */
    @Schema(description = "使用者代碼")
    private String userId;

    /** 轉出帳號 */
    @Schema(description = "轉出帳號")
    private String acnoOut;

    /** 查詢類別 */
    @Schema(description = "查詢類別")
    private String ckType;

    /** 證件類別 */
    @Schema(description = "證件類別")
    private String idtype;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 明細 */
    @Schema(description = "明細")
    private List<EB5556911ReqRep> txRepeats;

    /** 轉出帳號分行別 */
    @Schema(description = "轉出帳號分行別")
    private String branchIdOut;

    /** 分行別清單 */
    @Schema(description = "分行別清單")
    private Map<String, String> branchIdMap;

    public EB5556911Req() {
    }

    /**
     * @return the idno
     */
    public String getIdno() {
        return idno;
    }

    /**
     * @param idno
     *            the idno to set
     */
    public void setIdno(String idno) {
        this.idno = idno;
    }

    /**
     * @return the nameCod
     */
    public String getNameCod() {
        return nameCod;
    }

    /**
     * @param nameCod
     *            the nameCod to set
     */
    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the acnoOut
     */
    public String getAcnoOut() {
        return acnoOut;
    }

    /**
     * @param acnoOut
     *            the acnoOut to set
     */
    public void setAcnoOut(String acnoOut) {
        this.acnoOut = acnoOut;
    }

    /**
     * @return the ckType
     */
    public String getCkType() {
        return ckType;
    }

    /**
     * @param ckType
     *            the ckType to set
     */
    public void setCkType(String ckType) {
        this.ckType = ckType;
    }

    /**
     * @return the idtype
     */
    public String getIdtype() {
        return idtype;
    }

    /**
     * @param idtype
     *            the idtype to set
     */
    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    /**
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public List<EB5556911ReqRep> getTxRepeats() {
        return txRepeats;
    }

    public void setTxRepeats(List<EB5556911ReqRep> txRepeats) {
        this.txRepeats = txRepeats;
    }

    /**
     * @return {@link #branchIdOut}
     */
    public String getBranchIdOut() {
        return branchIdOut;
    }

    /**
     * @param branchIdOut
     *            {@link #branchIdOut}
     */
    public void setBranchIdOut(String branchIdOut) {
        this.branchIdOut = branchIdOut;
    }

    /**
     * @return {@link #branchIdMap}
     */
    public Map<String, String> getBranchIdMap() {
        return branchIdMap;
    }

    /**
     * @param branchIdMap
     *            {@link #branchIdMap}
     */
    public void setBranchIdMap(Map<String, String> branchIdMap) {
        this.branchIdMap = branchIdMap;
    }

    public boolean isSyncAgreeIn() {
        return syncAgreeIn;
    }

    public void setSyncAgreeIn(boolean syncAgreeIn) {
        this.syncAgreeIn = syncAgreeIn;
    }
}
