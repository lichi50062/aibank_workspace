package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

//@formatter:off
/**
* @(#)EB202674Response.java
* 
* <p>Description: 活存帳號即時餘額下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/07, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB202674D003Res extends TxHeadRs {

    private static final long serialVersionUID = 2731080374838123746L;

    /** 回傳代碼(0000 表示成功) */
    private String msgCod;

    /** 訊息中文代碼 */
    private String msgTxt;

    /** 帳號 */
    private String accountNo;

    /** 明細 */
    private List<EB202674D003ResRep> txRepeats;

    public String getMsgCod() {
        return msgCod;
    }

    public void setMsgCod(String msgCod) {
        this.msgCod = msgCod;
    }

    public String getMsgTxt() {
        return msgTxt;
    }

    public void setMsgTxt(String msgTxt) {
        this.msgTxt = msgTxt;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public List<EB202674D003ResRep> getTxRepeats() {
        return txRepeats;
    }

    public void setTxRepeats(List<EB202674D003ResRep> txRepeats) {
        this.txRepeats = txRepeats;
    }

}
