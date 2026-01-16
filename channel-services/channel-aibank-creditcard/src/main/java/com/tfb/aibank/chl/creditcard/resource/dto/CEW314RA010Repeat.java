package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW314RA010Repeat.java
* 
* <p>Description:CEW314RA010Repeat API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW314RA010Repeat {
    /** 帳務年月 X(5) */
    private String LsnYYYMM;
    /** 客戶身分 X(1) */
    private String a1cstyp;
    /** 郵區 X(5) */
    private String a1zipc;
    /** Address-1 X(30) */
    private String a1adr1;
    /** Address-2 X(30) */
    private String a1adr2;
    /** Address-3 X(30) */
    private String a1adr3;
    /** 性別 X(1) */
    private String a1sex;
    /** 中文姓名 X(12) */
    private String a1cnam;
    /** 生日 X(7) */
    private String a1brdy;
    /** 列印控制 X(1) */
    private String a1prtf;
    /** 帳單版本 X(2) */
    private String a1type;
    /** 繳款帳號 X(90) */
    private String a1acfd;
    /** 歸戶ID X(11) */
    private String a1acid;
    /** 遲繳狀況 X(1) */
    private String aidlqs;
    /** 自扣戶註記 X(1) */
    private String a1auac;
    /** 轉帳帳號 ID 英文轉換數字 X(2) */
    private String a1anum;

    /**
     * @return the lsnYYYMM
     */
    public String getLsnYYYMM() {
        return LsnYYYMM;
    }

    /**
     * @param lsnYYYMM
     *            the lsnYYYMM to set
     */
    public void setLsnYYYMM(String lsnYYYMM) {
        LsnYYYMM = lsnYYYMM;
    }

    /**
     * @return the a1cstyp
     */
    public String getA1cstyp() {
        return a1cstyp;
    }

    /**
     * @param a1cstyp
     *            the a1cstyp to set
     */
    public void setA1cstyp(String a1cstyp) {
        this.a1cstyp = a1cstyp;
    }

    /**
     * @return the a1zipc
     */
    public String getA1zipc() {
        return a1zipc;
    }

    /**
     * @param a1zipc
     *            the a1zipc to set
     */
    public void setA1zipc(String a1zipc) {
        this.a1zipc = a1zipc;
    }

    /**
     * @return the a1adr1
     */
    public String getA1adr1() {
        return a1adr1;
    }

    /**
     * @param a1adr1
     *            the a1adr1 to set
     */
    public void setA1adr1(String a1adr1) {
        this.a1adr1 = a1adr1;
    }

    /**
     * @return the a1adr2
     */
    public String getA1adr2() {
        return a1adr2;
    }

    /**
     * @param a1adr2
     *            the a1adr2 to set
     */
    public void setA1adr2(String a1adr2) {
        this.a1adr2 = a1adr2;
    }

    /**
     * @return the a1adr3
     */
    public String getA1adr3() {
        return a1adr3;
    }

    /**
     * @param a1adr3
     *            the a1adr3 to set
     */
    public void setA1adr3(String a1adr3) {
        this.a1adr3 = a1adr3;
    }

    /**
     * @return the a1sex
     */
    public String getA1sex() {
        return a1sex;
    }

    /**
     * @param a1sex
     *            the a1sex to set
     */
    public void setA1sex(String a1sex) {
        this.a1sex = a1sex;
    }

    /**
     * @return the a1cnam
     */
    public String getA1cnam() {
        return a1cnam;
    }

    /**
     * @param a1cnam
     *            the a1cnam to set
     */
    public void setA1cnam(String a1cnam) {
        this.a1cnam = a1cnam;
    }

    /**
     * @return the a1brdy
     */
    public String getA1brdy() {
        return a1brdy;
    }

    /**
     * @param a1brdy
     *            the a1brdy to set
     */
    public void setA1brdy(String a1brdy) {
        this.a1brdy = a1brdy;
    }

    /**
     * @return the a1prtf
     */
    public String getA1prtf() {
        return a1prtf;
    }

    /**
     * @param a1prtf
     *            the a1prtf to set
     */
    public void setA1prtf(String a1prtf) {
        this.a1prtf = a1prtf;
    }

    /**
     * @return the a1type
     */
    public String getA1type() {
        return a1type;
    }

    /**
     * @param a1type
     *            the a1type to set
     */
    public void setA1type(String a1type) {
        this.a1type = a1type;
    }

    /**
     * @return the a1acfd
     */
    public String getA1acfd() {
        return a1acfd;
    }

    /**
     * @param a1acfd
     *            the a1acfd to set
     */
    public void setA1acfd(String a1acfd) {
        this.a1acfd = a1acfd;
    }

    /**
     * @return the a1acid
     */
    public String getA1acid() {
        return a1acid;
    }

    /**
     * @param a1acid
     *            the a1acid to set
     */
    public void setA1acid(String a1acid) {
        this.a1acid = a1acid;
    }

    /**
     * @return the aidlqs
     */
    public String getAidlqs() {
        return aidlqs;
    }

    /**
     * @param aidlqs
     *            the aidlqs to set
     */
    public void setAidlqs(String aidlqs) {
        this.aidlqs = aidlqs;
    }

    /**
     * @return the a1auac
     */
    public String getA1auac() {
        return a1auac;
    }

    /**
     * @param a1auac
     *            the a1auac to set
     */
    public void setA1auac(String a1auac) {
        this.a1auac = a1auac;
    }

    /**
     * @return the a1anum
     */
    public String getA1anum() {
        return a1anum;
    }

    /**
     * @param a1anum
     *            the a1anum to set
     */
    public void setA1anum(String a1anum) {
        this.a1anum = a1anum;
    }

}
