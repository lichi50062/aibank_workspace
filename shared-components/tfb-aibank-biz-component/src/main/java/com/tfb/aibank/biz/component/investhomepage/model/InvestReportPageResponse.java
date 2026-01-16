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
import java.math.BigDecimal;
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
public class InvestReportPageResponse {
    // 投資週報
    private List<FinancialCard> tab1;
    // 事件評析
    private List<FinancialCard> tab2;
    // 股票市場
    private List<FinancialCard> tab3;
    // 債券市場
    private List<FinancialCard> tab4;
    // 外匯市場
    private List<FinancialCard> tab5;
    // 預設一
    private List<FinancialCard> tab6;
    // 預設二
    private List<FinancialCard> tab7;
    //// 市場燈號
    private List<EconomicIndicator> economicIndicators;

    public List<FinancialCard> getTab1() {
        return tab1;
    }

    public void setTab1(List<FinancialCard> tab1) {
        this.tab1 = tab1;
    }

    public List<FinancialCard> getTab2() {
        return tab2;
    }

    public void setTab2(List<FinancialCard> tab2) {
        this.tab2 = tab2;
    }

    public List<FinancialCard> getTab3() {
        return tab3;
    }

    public void setTab3(List<FinancialCard> tab3) {
        this.tab3 = tab3;
    }

    public List<FinancialCard> getTab4() {
        return tab4;
    }

    public void setTab4(List<FinancialCard> tab4) {
        this.tab4 = tab4;
    }

    public List<FinancialCard> getTab5() {
        return tab5;
    }

    public void setTab5(List<FinancialCard> tab5) {
        this.tab5 = tab5;
    }

    public List<FinancialCard> getTab6() {
        return tab6;
    }

    public void setTab6(List<FinancialCard> tab6) {
        this.tab6 = tab6;
    }

    public List<FinancialCard> getTab7() {
        return tab7;
    }

    public void setTab7(List<FinancialCard> tab7) {
        this.tab7 = tab7;
    }

    public List<EconomicIndicator> getEconomicIndicators() {
        return economicIndicators;
    }

    public void setEconomicIndicators(List<EconomicIndicator> economicIndicators) {
        this.economicIndicators = economicIndicators;
    }
}
