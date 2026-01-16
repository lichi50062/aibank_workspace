package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;

//@formatter:off
/**
 * @(#)EB12020011ResRep.java
 * 
 * <p>Description: 數位存款開戶下行電文 repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/15, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class EB12020011ResRep implements Serializable {

    private static final long serialVersionUID = -2972494574881081429L;

    /** 歸戶註記 */
    private String subid;

    /** 分行 */
    private String branchID2;

    /** 異動日期 */
    private String systDt;

    /** 異動時間 */
    private String systTime;

    /**
     * @return the subid
     */
    public String getSubid() {
        return subid;
    }

    /**
     * @param subid
     *            the subid to set
     */
    public void setSubid(String subid) {
        this.subid = subid;
    }

    /**
     * @return the branchID2
     */
    public String getBranchID2() {
        return branchID2;
    }

    /**
     * @param branchID2
     *            the branchID2 to set
     */
    public void setBranchID2(String branchID2) {
        this.branchID2 = branchID2;
    }

    /**
     * @return the systDt
     */
    public String getSystDt() {
        return systDt;
    }

    /**
     * @param systDt
     *            the systDt to set
     */
    public void setSystDt(String systDt) {
        this.systDt = systDt;
    }

    /**
     * @return the systTime
     */
    public String getSystTime() {
        return systTime;
    }

    /**
     * @param systTime
     *            the systTime to set
     */
    public void setSystTime(String systTime) {
        this.systTime = systTime;
    }

}
