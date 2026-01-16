package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema; /**
 * @(#)ReportDesc.java
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
public class ReportDesc {
    /** 副標題 */
    @Schema(description = "副標題")
    private String subTitle;

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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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
}
