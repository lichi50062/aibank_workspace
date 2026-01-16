package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema; /**
 * @(#)FinancialCardPackegeJsonData.java
 *
 * <p>Description:關鍵話題</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class FinancialCardPackegeJsonData {
    private String locale;
    private FinancialCardJsonData information;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public FinancialCardJsonData getInformation() {
        return information;
    }

    public void setInformation(FinancialCardJsonData information) {
        this.information = information;
    }
}
