package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * @(#)EconomicIndicators.java
 *
 * <p>Description:市場燈號 - Rq</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/24, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class EconomicIndicator extends EconomicIndicatorJsonData {
    /** 市場燈號大icon */
    @Schema(description = "市場燈號大icon")
    private String indicatorsIcon;

    /** 分類 */
    @Schema(description = "分類")
    private String category;

    /** REMARK_CONTENT.REMARK_KEY */
    @Schema(description = "REMARK_CONTENT.REMARK_KEY")
    private String remarkKey;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private String sort;

    public String getIndicatorsIcon() {
        return indicatorsIcon;
    }

    public void setIndicatorsIcon(String indicatorsIcon) {
        this.indicatorsIcon = indicatorsIcon;
    }

    public String getRemarkKey() {
        return remarkKey;
    }

    public void setRemarkKey(String remarkKey) {
        this.remarkKey = remarkKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
