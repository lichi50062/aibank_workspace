package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema; /**
 * @(#)EconomicPackegeJsonData.java
 *
 * <p>Description:市場燈號</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/24, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class EconomicPackegeJsonData {
    private String locale;
    private EconomicIndicatorJsonData information;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public EconomicIndicatorJsonData getInformation() {
        return information;
    }

    public void setInformation(EconomicIndicatorJsonData information) {
        this.information = information;
    }
}
