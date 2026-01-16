package com.tfb.aibank.chl.creditcard.tx001.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCTX001021Rs.java
*
* <p>Description:預借現金 申請資料輸入頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX001021Rs implements RsData {

    /** 卡片清單 */
    private List<NCCTX001BranchInfo> branchInfo;

    /**
     * @return the branchInfo
     */
    public List<NCCTX001BranchInfo> getBranchInfo() {
        return branchInfo;
    }

    /**
     * @param branchInfo
     *            the branchInfo to set
     */
    public void setBranchInfo(List<NCCTX001BranchInfo> branchInfo) {
        this.branchInfo = branchInfo;
    }

}
