package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)CEW327RRepeat.java
* 
* <p>Description:CEW327R 保費權益設定查詢 API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW327RRepeat {
    /** 卡別 X(4) */
    private String crdtyp;
    /** 卡號 X(16) */
    private String cdno;
    /** 最近一次變更日期 X(8) */
    private Date changedate;
    /** 保費權益 X(1) */
    private String insutype;
    /** 可設定保費權益1 X(1) */
    private String allinsutype1;
    /** 可設定保費權益2 X(1) */
    private String allinsutype2;
    /** 可設定保費權益3 X(1) */
    private String allinsutype3;
    /** 可設定保費權益4 X(1) */
    private String allinsutype4;
    /** 可設定保費權益5 X(1) */
    private String allinsutype5;
    /** 可設定保費權益6 X(1) */
    private String allinsutype6;
    /** 可設定保費權益7 X(1) */
    private String allinsutype7;
    /** 可設定保費權益8 X(1) */
    private String allinsutype8;
    /** 可設定保費權益9 X(1) */
    private String allinsutype9;
    /** 可設定保費權益10 X(1) */
    private String allinsutype10;
    /** FILLER X(11) */
    private String filler;

    /**
     * @return the crdtyp
     */
    public String getCrdtyp() {
        return crdtyp;
    }

    /**
     * @param crdtyp
     *            the crdtyp to set
     */
    public void setCrdtyp(String crdtyp) {
        this.crdtyp = crdtyp;
    }

    /**
     * @return the cdno
     */
    public String getCdno() {
        return cdno;
    }

    /**
     * @param cdno
     *            the cdno to set
     */
    public void setCdno(String cdno) {
        this.cdno = cdno;
    }

    /**
     * @return the changedate
     */
    public Date getChangedate() {
        return changedate;
    }

    /**
     * @param changedate
     *            the changedate to set
     */
    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    /**
     * @return the insutype
     */
    public String getInsutype() {
        return insutype;
    }

    /**
     * @param insutype
     *            the insutype to set
     */
    public void setInsutype(String insutype) {
        this.insutype = insutype;
    }

    /**
     * @return the allinsutype1
     */
    public String getAllinsutype1() {
        return allinsutype1;
    }

    /**
     * @param allinsutype1
     *            the allinsutype1 to set
     */
    public void setAllinsutype1(String allinsutype1) {
        this.allinsutype1 = allinsutype1;
    }

    /**
     * @return the allinsutype2
     */
    public String getAllinsutype2() {
        return allinsutype2;
    }

    /**
     * @param allinsutype2
     *            the allinsutype2 to set
     */
    public void setAllinsutype2(String allinsutype2) {
        this.allinsutype2 = allinsutype2;
    }

    /**
     * @return the allinsutype3
     */
    public String getAllinsutype3() {
        return allinsutype3;
    }

    /**
     * @param allinsutype3
     *            the allinsutype3 to set
     */
    public void setAllinsutype3(String allinsutype3) {
        this.allinsutype3 = allinsutype3;
    }

    /**
     * @return the allinsutype4
     */
    public String getAllinsutype4() {
        return allinsutype4;
    }

    /**
     * @param allinsutype4
     *            the allinsutype4 to set
     */
    public void setAllinsutype4(String allinsutype4) {
        this.allinsutype4 = allinsutype4;
    }

    /**
     * @return the allinsutype5
     */
    public String getAllinsutype5() {
        return allinsutype5;
    }

    /**
     * @param allinsutype5
     *            the allinsutype5 to set
     */
    public void setAllinsutype5(String allinsutype5) {
        this.allinsutype5 = allinsutype5;
    }

    /**
     * @return the allinsutype6
     */
    public String getAllinsutype6() {
        return allinsutype6;
    }

    /**
     * @param allinsutype6
     *            the allinsutype6 to set
     */
    public void setAllinsutype6(String allinsutype6) {
        this.allinsutype6 = allinsutype6;
    }

    /**
     * @return the allinsutype7
     */
    public String getAllinsutype7() {
        return allinsutype7;
    }

    /**
     * @param allinsutype7
     *            the allinsutype7 to set
     */
    public void setAllinsutype7(String allinsutype7) {
        this.allinsutype7 = allinsutype7;
    }

    /**
     * @return the allinsutype8
     */
    public String getAllinsutype8() {
        return allinsutype8;
    }

    /**
     * @param allinsutype8
     *            the allinsutype8 to set
     */
    public void setAllinsutype8(String allinsutype8) {
        this.allinsutype8 = allinsutype8;
    }

    /**
     * @return the allinsutype9
     */
    public String getAllinsutype9() {
        return allinsutype9;
    }

    /**
     * @param allinsutype9
     *            the allinsutype9 to set
     */
    public void setAllinsutype9(String allinsutype9) {
        this.allinsutype9 = allinsutype9;
    }

    /**
     * @return the allinsutype10
     */
    public String getAllinsutype10() {
        return allinsutype10;
    }

    /**
     * @param allinsutype10
     *            the allinsutype10 to set
     */
    public void setAllinsutype10(String allinsutype10) {
        this.allinsutype10 = allinsutype10;
    }

    /**
     * @return the filler
     */
    public String getFiller() {
        return filler;
    }

    /**
     * @param filler
     *            the filler to set
     */
    public void setFiller(String filler) {
        this.filler = filler;
    }

}
