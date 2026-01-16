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
package com.tfb.aibank.biz.component.investhomepage.model;

//@formatter:off
import java.util.List; /**
* @(#)InvestHomePageResponse.java
* 
* <p>Description:研究報告 - Rs</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/28, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class InvestQAResponse {
    // QA統計
    private List<FinancialQA> qaList;

    public List<FinancialQA> getQaList() {
        return qaList;
    }

    public void setQaList(List<FinancialQA> qaList) {
        this.qaList = qaList;
    }
}
