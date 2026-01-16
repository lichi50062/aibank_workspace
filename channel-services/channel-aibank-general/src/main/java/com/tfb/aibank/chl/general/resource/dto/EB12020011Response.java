package com.tfb.aibank.chl.general.resource.dto;

import java.util.ArrayList;
import java.util.List;

//@formatter:off
/**
* @(#)EB12020011Response.java
* 
* <p>Description: 數位存款開戶下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB12020011Response {
    // 回傳代碼
    private String msgCod;
    // 錯誤代碼中文
    private String msgTxt;
    // 是否有多戶名代碼
    private String multiName;
    // 是否只有0001戶名代碼
    private String name0001;
    // 是否有存款實體帳戶
    private String depSts;
    // 統編是否重號(Y/N)
    private String idDup;
    // 主機11位統編(若未重號)
    private String actId;
    // 生日
    private String bday;
    // 統編戶況(第1BYTES=Y(死亡戶))
    private String idnoSts;
    // 手機
    private String cell;
    // EMAIL=
    private String email;
    // 申請狀態=
    private String aplySts;
    // 申請戶數
    private String aplyCnt;
    // 客戶帳號
    private String accntNumber1;
    // 臨櫃異動手機號碼註記
    private String fxind1;
    /** 細項 */
    private List<EB12020011Repeat> txRepeats = new ArrayList<>();

    /**
     * @return the msgCod
     */
    public String getMsgCod() {
        return msgCod;
    }

    /**
     * @param msgCod
     *            the msgCod to set
     */
    public void setMsgCod(String msgCod) {
        this.msgCod = msgCod;
    }

    /**
     * @return the msgTxt
     */
    public String getMsgTxt() {
        return msgTxt;
    }

    /**
     * @param msgTxt
     *            the msgTxt to set
     */
    public void setMsgTxt(String msgTxt) {
        this.msgTxt = msgTxt;
    }

    /**
     * @return the multiName
     */
    public String getMultiName() {
        return multiName;
    }

    /**
     * @param multiName
     *            the multiName to set
     */
    public void setMultiName(String multiName) {
        this.multiName = multiName;
    }

    /**
     * @return the name0001
     */
    public String getName0001() {
        return name0001;
    }

    /**
     * @param name0001
     *            the name0001 to set
     */
    public void setName0001(String name0001) {
        this.name0001 = name0001;
    }

    /**
     * @return the depSts
     */
    public String getDepSts() {
        return depSts;
    }

    /**
     * @param depSts
     *            the depSts to set
     */
    public void setDepSts(String depSts) {
        this.depSts = depSts;
    }

    /**
     * @return the idDup
     */
    public String getIdDup() {
        return idDup;
    }

    /**
     * @param idDup
     *            the idDup to set
     */
    public void setIdDup(String idDup) {
        this.idDup = idDup;
    }

    /**
     * @return the actId
     */
    public String getActId() {
        return actId;
    }

    /**
     * @param actId
     *            the actId to set
     */
    public void setActId(String actId) {
        this.actId = actId;
    }

    /**
     * @return the bday
     */
    public String getBday() {
        return bday;
    }

    /**
     * @param bday
     *            the bday to set
     */
    public void setBday(String bday) {
        this.bday = bday;
    }

    /**
     * @return the idnoSts
     */
    public String getIdnoSts() {
        return idnoSts;
    }

    /**
     * @param idnoSts
     *            the idnoSts to set
     */
    public void setIdnoSts(String idnoSts) {
        this.idnoSts = idnoSts;
    }

    /**
     * @return the cell
     */
    public String getCell() {
        return cell;
    }

    /**
     * @param cell
     *            the cell to set
     */
    public void setCell(String cell) {
        this.cell = cell;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the aplySts
     */
    public String getAplySts() {
        return aplySts;
    }

    /**
     * @param aplySts
     *            the aplySts to set
     */
    public void setAplySts(String aplySts) {
        this.aplySts = aplySts;
    }

    /**
     * @return the aplyCnt
     */
    public String getAplyCnt() {
        return aplyCnt;
    }

    /**
     * @param aplyCnt
     *            the aplyCnt to set
     */
    public void setAplyCnt(String aplyCnt) {
        this.aplyCnt = aplyCnt;
    }

    /**
     * @return the accntNumber1
     */
    public String getAccntNumber1() {
        return accntNumber1;
    }

    /**
     * @param accntNumber1
     *            the accntNumber1 to set
     */
    public void setAccntNumber1(String accntNumber1) {
        this.accntNumber1 = accntNumber1;
    }

    /**
     * @return the fxind1
     */
    public String getFxind1() {
        return fxind1;
    }

    /**
     * @param fxind1
     *            the fxind1 to set
     */
    public void setFxind1(String fxind1) {
        this.fxind1 = fxind1;
    }

    /**
     * @return the txRepeats
     */
    public List<EB12020011Repeat> getTxRepeats() {
        return txRepeats;
    }

    /**
     * @param txRepeats
     *            the txRepeats to set
     */
    public void setTxRepeats(List<EB12020011Repeat> txRepeats) {
        this.txRepeats = txRepeats;
    }

}
