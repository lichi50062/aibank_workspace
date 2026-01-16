package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off

import io.swagger.v3.oas.annotations.media.Schema; /**
 * @(#)ImageJsonData.java
 *
 * <p>Description:其他精選理財</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class ContentJsonData {

    /** URL種類 1外開瀏覽器；2指定交易 */
    @Schema(description = "URL種類")
    private String urlType;

    /** 連結 */
    @Schema(description = "連結")
    private String url;

    /** 連結名稱 */
    @Schema(description = "連結名稱")
    private String urlName;

    /** 圖表標題 */
    @Schema(description = "圖片路徑")
    private String imagePath;

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
