package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema; /**
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
public class EconomicIndicatorJsonData {
    /** 標題 */
    @Schema(description = "標題")
    private String title;

    /** 內文 */
    @Schema(description = "內文")
    private List<String> content;

    /** 圖表標題 */
    @Schema(description = "圖表標題")
    private String imageTitle;

    /** 圖表路徑 */
    @Schema(description = "圖表路徑")
    private String imagePath;

    /** 圖表資料來源 */
    @Schema(description = "圖表資料來源")
    private String imageSource;

    /** 研究報告標題 */
    @Schema(description = "研究報告標題")
    private String reportTitle;

    /** 幣別 */
    @Schema(description = "幣別")
    private String currency;

    /** 短評 */
    @Schema(description = "短評")
    private String comment;

    /** item */
    @Schema(description = "item")
    private String item;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
