package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;

//@formatter:off
/**
* @(#)FC032155Response.java
* 
* <p>Description:本行客戶註記下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on

public class FC032155RsBody {
    // 戶名
    private String custName;
    // 通報統一編號
    private String idnoLoan;
    // 通報類別
    private String telCod1;
    // 通報類別中文說明
    private String reason1;
    // 通報日期
    private String aprovDate1;
    // 通報機構
    private String busAddr1;
    // 通報機構代
    private String frCmpid1;
    // 狀態註記A-新增 U-修改 C-解除
    private String advSts1;
    // 案發日期
    private String busDate1;
    // 案情描述
    private String cttAddr1;
    // 案情描述
    private String addr0111;
    // 案情描述
    private String addr0121;
    // 案情描述
    private String addr0211;
    // 案情描述
    private String addr0221;
    // 案情描述
    private String addr0311;
    // 註記：J(有效)
    private String st1;
    // 風險等級
    private String memo2901;
    // 揭露期限 ( 單位 : 月 )
    private String resetDate1;
    // 解除代碼(原因)
    private BigDecimal canRes1;
    // 解除日期
    private BigDecimal memo24531;
    // 案件流水號
    private String srnNo1;
    // 權責單位
    private String memo24691;
    // 維護部門
    private String memo24741;
    // 經辦
    private String txEmpId1;
    // 覆核主管
    private String autEmpId1;
    // 最初建檔經辦
    private String orgEmpId1;
    // 最初建檔日期
    private String createDate1;
    // 最後異動日期
    private String lstMtnDate1;
    // 最後異動時間
    private String lstMtnTime1;
    // 通報類別
    private String telCod2;
    // 通報類別中文說明
    private String reason2;
    // 通報日期
    private String aprovDate2;
    // 通報機構
    private String busAddr2;
    // 通報機構代碼
    private String frCmpid2;
    // 狀態註記A-新增 U-修改 C-解除
    private String advSts2;
    // 案發日期
    private String busDate2;
    // 案情描述
    private String cttAddr2;
    // 案情描述
    private String addr0112;
    // 案情描述
    private String addr0122;
    // 案情描述
    private String addr0212;
    // 案情描述
    private String addr0222;
    // 案情描述
    private String addr0312;
    // 註記：J(有效)
    private String st2;
    // 風險等級
    private String memo2902;
    // 揭露期限 ( 單位 : 月 )
    private String resetDate2;
    // 解除代碼(原因)
    private String canRes2;
    // 解除日期
    private String memo24532;
    // 案件流水號
    private String srnNo2;
    // 權責單位
    private String memo24692;
    // 維護部門
    private String memo24742;
    // 經辦
    private String txEmpId2;
    // 覆核主管
    private String autEmpId2;
    // 最初建檔經辦
    private String orgEmpId2;
    // 最初建檔日期
    private String createDate2;
    // 最後異動日期
    private String lstMtnDate2;
    // 最後異動時間
    private String lstMtnTime2;
    // 備忘錄1
    private String memo1;
    // 備忘錄2
    private String memo2;
    // 原因代碼1
    private String reasonCode1;
    // 原因代碼2
    private String reasonCode2;
    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName
     *            the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return the idnoLoan
     */
    public String getIdnoLoan() {
        return idnoLoan;
    }

    /**
     * @param idnoLoan
     *            the idnoLoan to set
     */
    public void setIdnoLoan(String idnoLoan) {
        this.idnoLoan = idnoLoan;
    }

    /**
     * @return the telCod1
     */
    public String getTelCod1() {
        return telCod1;
    }

    /**
     * @param telCod1
     *            the telCod1 to set
     */
    public void setTelCod1(String telCod1) {
        this.telCod1 = telCod1;
    }

    /**
     * @return the reason1
     */
    public String getReason1() {
        return reason1;
    }

    /**
     * @param reason1
     *            the reason1 to set
     */
    public void setReason1(String reason1) {
        this.reason1 = reason1;
    }

    /**
     * @return the aprovDate1
     */
    public String getAprovDate1() {
        return aprovDate1;
    }

    /**
     * @param aprovDate1
     *            the aprovDate1 to set
     */
    public void setAprovDate1(String aprovDate1) {
        this.aprovDate1 = aprovDate1;
    }

    /**
     * @return the busAddr1
     */
    public String getBusAddr1() {
        return busAddr1;
    }

    /**
     * @param busAddr1
     *            the busAddr1 to set
     */
    public void setBusAddr1(String busAddr1) {
        this.busAddr1 = busAddr1;
    }

    /**
     * @return the frCmpid1
     */
    public String getFrCmpid1() {
        return frCmpid1;
    }

    /**
     * @param frCmpid1
     *            the frCmpid1 to set
     */
    public void setFrCmpid1(String frCmpid1) {
        this.frCmpid1 = frCmpid1;
    }

    /**
     * @return the advSts1
     */
    public String getAdvSts1() {
        return advSts1;
    }

    /**
     * @param advSts1
     *            the advSts1 to set
     */
    public void setAdvSts1(String advSts1) {
        this.advSts1 = advSts1;
    }

    /**
     * @return the busDate1
     */
    public String getBusDate1() {
        return busDate1;
    }

    /**
     * @param busDate1
     *            the busDate1 to set
     */
    public void setBusDate1(String busDate1) {
        this.busDate1 = busDate1;
    }

    /**
     * @return the cttAddr1
     */
    public String getCttAddr1() {
        return cttAddr1;
    }

    /**
     * @param cttAddr1
     *            the cttAddr1 to set
     */
    public void setCttAddr1(String cttAddr1) {
        this.cttAddr1 = cttAddr1;
    }

    /**
     * @return the addr0111
     */
    public String getAddr0111() {
        return addr0111;
    }

    /**
     * @param addr0111
     *            the addr0111 to set
     */
    public void setAddr0111(String addr0111) {
        this.addr0111 = addr0111;
    }

    /**
     * @return the addr0121
     */
    public String getAddr0121() {
        return addr0121;
    }

    /**
     * @param addr0121
     *            the addr0121 to set
     */
    public void setAddr0121(String addr0121) {
        this.addr0121 = addr0121;
    }

    /**
     * @return the addr0211
     */
    public String getAddr0211() {
        return addr0211;
    }

    /**
     * @param addr0211
     *            the addr0211 to set
     */
    public void setAddr0211(String addr0211) {
        this.addr0211 = addr0211;
    }

    /**
     * @return the addr0221
     */
    public String getAddr0221() {
        return addr0221;
    }

    /**
     * @param addr0221
     *            the addr0221 to set
     */
    public void setAddr0221(String addr0221) {
        this.addr0221 = addr0221;
    }

    /**
     * @return the addr0311
     */
    public String getAddr0311() {
        return addr0311;
    }

    /**
     * @param addr0311
     *            the addr0311 to set
     */
    public void setAddr0311(String addr0311) {
        this.addr0311 = addr0311;
    }

    /**
     * @return the st1
     */
    public String getSt1() {
        return st1;
    }

    /**
     * @param st1
     *            the st1 to set
     */
    public void setSt1(String st1) {
        this.st1 = st1;
    }

    /**
     * @return the memo2901
     */
    public String getMemo2901() {
        return memo2901;
    }

    /**
     * @param memo2901
     *            the memo2901 to set
     */
    public void setMemo2901(String memo2901) {
        this.memo2901 = memo2901;
    }

    /**
     * @return the resetDate1
     */
    public String getResetDate1() {
        return resetDate1;
    }

    /**
     * @param resetDate1
     *            the resetDate1 to set
     */
    public void setResetDate1(String resetDate1) {
        this.resetDate1 = resetDate1;
    }

    /**
     * @return the canRes1
     */
    public BigDecimal getCanRes1() {
        return canRes1;
    }

    /**
     * @param canRes1
     *            the canRes1 to set
     */
    public void setCanRes1(BigDecimal canRes1) {
        this.canRes1 = canRes1;
    }

    /**
     * @return the memo24531
     */
    public BigDecimal getMemo24531() {
        return memo24531;
    }

    /**
     * @param memo24531
     *            the memo24531 to set
     */
    public void setMemo24531(BigDecimal memo24531) {
        this.memo24531 = memo24531;
    }

    /**
     * @return the srnNo1
     */
    public String getSrnNo1() {
        return srnNo1;
    }

    /**
     * @param srnNo1
     *            the srnNo1 to set
     */
    public void setSrnNo1(String srnNo1) {
        this.srnNo1 = srnNo1;
    }

    /**
     * @return the memo24691
     */
    public String getMemo24691() {
        return memo24691;
    }

    /**
     * @param memo24691
     *            the memo24691 to set
     */
    public void setMemo24691(String memo24691) {
        this.memo24691 = memo24691;
    }

    /**
     * @return the memo24741
     */
    public String getMemo24741() {
        return memo24741;
    }

    /**
     * @param memo24741
     *            the memo24741 to set
     */
    public void setMemo24741(String memo24741) {
        this.memo24741 = memo24741;
    }

    /**
     * @return the txEmpId1
     */
    public String getTxEmpId1() {
        return txEmpId1;
    }

    /**
     * @param txEmpId1
     *            the txEmpId1 to set
     */
    public void setTxEmpId1(String txEmpId1) {
        this.txEmpId1 = txEmpId1;
    }

    /**
     * @return the autEmpId1
     */
    public String getAutEmpId1() {
        return autEmpId1;
    }

    /**
     * @param autEmpId1
     *            the autEmpId1 to set
     */
    public void setAutEmpId1(String autEmpId1) {
        this.autEmpId1 = autEmpId1;
    }

    /**
     * @return the orgEmpId1
     */
    public String getOrgEmpId1() {
        return orgEmpId1;
    }

    /**
     * @param orgEmpId1
     *            the orgEmpId1 to set
     */
    public void setOrgEmpId1(String orgEmpId1) {
        this.orgEmpId1 = orgEmpId1;
    }

    /**
     * @return the createDate1
     */
    public String getCreateDate1() {
        return createDate1;
    }

    /**
     * @param createDate1
     *            the createDate1 to set
     */
    public void setCreateDate1(String createDate1) {
        this.createDate1 = createDate1;
    }

    /**
     * @return the lstMtnDate1
     */
    public String getLstMtnDate1() {
        return lstMtnDate1;
    }

    /**
     * @param lstMtnDate1
     *            the lstMtnDate1 to set
     */
    public void setLstMtnDate1(String lstMtnDate1) {
        this.lstMtnDate1 = lstMtnDate1;
    }

    /**
     * @return the lstMtnTime1
     */
    public String getLstMtnTime1() {
        return lstMtnTime1;
    }

    /**
     * @param lstMtnTime1
     *            the lstMtnTime1 to set
     */
    public void setLstMtnTime1(String lstMtnTime1) {
        this.lstMtnTime1 = lstMtnTime1;
    }

    /**
     * @return the telCod2
     */
    public String getTelCod2() {
        return telCod2;
    }

    /**
     * @param telCod2
     *            the telCod2 to set
     */
    public void setTelCod2(String telCod2) {
        this.telCod2 = telCod2;
    }

    /**
     * @return the reason2
     */
    public String getReason2() {
        return reason2;
    }

    /**
     * @param reason2
     *            the reason2 to set
     */
    public void setReason2(String reason2) {
        this.reason2 = reason2;
    }

    /**
     * @return the aprovDate2
     */
    public String getAprovDate2() {
        return aprovDate2;
    }

    /**
     * @param aprovDate2
     *            the aprovDate2 to set
     */
    public void setAprovDate2(String aprovDate2) {
        this.aprovDate2 = aprovDate2;
    }

    /**
     * @return the busAddr2
     */
    public String getBusAddr2() {
        return busAddr2;
    }

    /**
     * @param busAddr2
     *            the busAddr2 to set
     */
    public void setBusAddr2(String busAddr2) {
        this.busAddr2 = busAddr2;
    }

    /**
     * @return the frCmpid2
     */
    public String getFrCmpid2() {
        return frCmpid2;
    }

    /**
     * @param frCmpid2
     *            the frCmpid2 to set
     */
    public void setFrCmpid2(String frCmpid2) {
        this.frCmpid2 = frCmpid2;
    }

    /**
     * @return the advSts2
     */
    public String getAdvSts2() {
        return advSts2;
    }

    /**
     * @param advSts2
     *            the advSts2 to set
     */
    public void setAdvSts2(String advSts2) {
        this.advSts2 = advSts2;
    }

    /**
     * @return the busDate2
     */
    public String getBusDate2() {
        return busDate2;
    }

    /**
     * @param busDate2
     *            the busDate2 to set
     */
    public void setBusDate2(String busDate2) {
        this.busDate2 = busDate2;
    }

    /**
     * @return the cttAddr2
     */
    public String getCttAddr2() {
        return cttAddr2;
    }

    /**
     * @param cttAddr2
     *            the cttAddr2 to set
     */
    public void setCttAddr2(String cttAddr2) {
        this.cttAddr2 = cttAddr2;
    }

    /**
     * @return the addr0112
     */
    public String getAddr0112() {
        return addr0112;
    }

    /**
     * @param addr0112
     *            the addr0112 to set
     */
    public void setAddr0112(String addr0112) {
        this.addr0112 = addr0112;
    }

    /**
     * @return the addr0122
     */
    public String getAddr0122() {
        return addr0122;
    }

    /**
     * @param addr0122
     *            the addr0122 to set
     */
    public void setAddr0122(String addr0122) {
        this.addr0122 = addr0122;
    }

    /**
     * @return the addr0212
     */
    public String getAddr0212() {
        return addr0212;
    }

    /**
     * @param addr0212
     *            the addr0212 to set
     */
    public void setAddr0212(String addr0212) {
        this.addr0212 = addr0212;
    }

    /**
     * @return the addr0222
     */
    public String getAddr0222() {
        return addr0222;
    }

    /**
     * @param addr0222
     *            the addr0222 to set
     */
    public void setAddr0222(String addr0222) {
        this.addr0222 = addr0222;
    }

    /**
     * @return the addr0312
     */
    public String getAddr0312() {
        return addr0312;
    }

    /**
     * @param addr0312
     *            the addr0312 to set
     */
    public void setAddr0312(String addr0312) {
        this.addr0312 = addr0312;
    }

    /**
     * @return the st2
     */
    public String getSt2() {
        return st2;
    }

    /**
     * @param st2
     *            the st2 to set
     */
    public void setSt2(String st2) {
        this.st2 = st2;
    }

    /**
     * @return the memo2902
     */
    public String getMemo2902() {
        return memo2902;
    }

    /**
     * @param memo2902
     *            the memo2902 to set
     */
    public void setMemo2902(String memo2902) {
        this.memo2902 = memo2902;
    }

    /**
     * @return the resetDate2
     */
    public String getResetDate2() {
        return resetDate2;
    }

    /**
     * @param resetDate2
     *            the resetDate2 to set
     */
    public void setResetDate2(String resetDate2) {
        this.resetDate2 = resetDate2;
    }

    /**
     * @return the canRes2
     */
    public String getCanRes2() {
        return canRes2;
    }

    /**
     * @param canRes2
     *            the canRes2 to set
     */
    public void setCanRes2(String canRes2) {
        this.canRes2 = canRes2;
    }

    /**
     * @return the memo24532
     */
    public String getMemo24532() {
        return memo24532;
    }

    /**
     * @param memo24532
     *            the memo24532 to set
     */
    public void setMemo24532(String memo24532) {
        this.memo24532 = memo24532;
    }

    /**
     * @return the srnNo2
     */
    public String getSrnNo2() {
        return srnNo2;
    }

    /**
     * @param srnNo2
     *            the srnNo2 to set
     */
    public void setSrnNo2(String srnNo2) {
        this.srnNo2 = srnNo2;
    }

    /**
     * @return the memo24692
     */
    public String getMemo24692() {
        return memo24692;
    }

    /**
     * @param memo24692
     *            the memo24692 to set
     */
    public void setMemo24692(String memo24692) {
        this.memo24692 = memo24692;
    }

    /**
     * @return the memo24742
     */
    public String getMemo24742() {
        return memo24742;
    }

    /**
     * @param memo24742
     *            the memo24742 to set
     */
    public void setMemo24742(String memo24742) {
        this.memo24742 = memo24742;
    }

    /**
     * @return the txEmpId2
     */
    public String getTxEmpId2() {
        return txEmpId2;
    }

    /**
     * @param txEmpId2
     *            the txEmpId2 to set
     */
    public void setTxEmpId2(String txEmpId2) {
        this.txEmpId2 = txEmpId2;
    }

    /**
     * @return the autEmpId2
     */
    public String getAutEmpId2() {
        return autEmpId2;
    }

    /**
     * @param autEmpId2
     *            the autEmpId2 to set
     */
    public void setAutEmpId2(String autEmpId2) {
        this.autEmpId2 = autEmpId2;
    }

    /**
     * @return the orgEmpId2
     */
    public String getOrgEmpId2() {
        return orgEmpId2;
    }

    /**
     * @param orgEmpId2
     *            the orgEmpId2 to set
     */
    public void setOrgEmpId2(String orgEmpId2) {
        this.orgEmpId2 = orgEmpId2;
    }

    /**
     * @return the createDate2
     */
    public String getCreateDate2() {
        return createDate2;
    }

    /**
     * @param createDate2
     *            the createDate2 to set
     */
    public void setCreateDate2(String createDate2) {
        this.createDate2 = createDate2;
    }

    /**
     * @return the lstMtnDate2
     */
    public String getLstMtnDate2() {
        return lstMtnDate2;
    }

    /**
     * @param lstMtnDate2
     *            the lstMtnDate2 to set
     */
    public void setLstMtnDate2(String lstMtnDate2) {
        this.lstMtnDate2 = lstMtnDate2;
    }

    /**
     * @return the lstMtnTime2
     */
    public String getLstMtnTime2() {
        return lstMtnTime2;
    }

    /**
     * @param lstMtnTime2
     *            the lstMtnTime2 to set
     */
    public void setLstMtnTime2(String lstMtnTime2) {
        this.lstMtnTime2 = lstMtnTime2;
    }

    /**
     * @return the memo1
     */
    public String getMemo1() {
        return memo1;
    }

    /**
     * @param memo1
     *            the memo1 to set
     */
    public void setMemo1(String memo1) {
        this.memo1 = memo1;
    }

    /**
     * @return the memo2
     */
    public String getMemo2() {
        return memo2;
    }

    /**
     * @param memo2
     *            the memo2 to set
     */
    public void setMemo2(String memo2) {
        this.memo2 = memo2;
    }

    /**
     * @return the reasonCode1
     */
    public String getReasonCode1() {
        return reasonCode1;
    }

    /**
     * @param reasonCode1
     *            the reasonCode1 to set
     */
    public void setReasonCode1(String reasonCode1) {
        this.reasonCode1 = reasonCode1;
    }

    /**
     * @return the reasonCode2
     */
    public String getReasonCode2() {
        return reasonCode2;
    }

    /**
     * @param reasonCode2
     *            the reasonCode2 to set
     */
    public void setReasonCode2(String reasonCode2) {
        this.reasonCode2 = reasonCode2;
    }

}
