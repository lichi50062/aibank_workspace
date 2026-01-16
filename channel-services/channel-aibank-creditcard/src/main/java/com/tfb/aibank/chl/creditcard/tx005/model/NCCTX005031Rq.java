package com.tfb.aibank.chl.creditcard.tx005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCTX005031Rq.java
 * 
 * <p>Description:額度調整 031 處理財力證明上傳檔案</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/13, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCTX005031Rq implements RqData {

    /** 動作 ADD、DEL */
    private String action;

    /** 檔案 */
    private NCCTX005ProofFile proofFile;

    /** 欲移除的檔案索引值 */
    private int delIdx;

    public NCCTX005ProofFile getProofFile() {
        return proofFile;
    }

    public void setProofFile(NCCTX005ProofFile proofFile) {
        this.proofFile = proofFile;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getDelIdx() {
        return delIdx;
    }

    public void setDelIdx(int delIdx) {
        this.delIdx = delIdx;
    }

}
