package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema; /**
 * @(#)ImageJsonData.java
 *
 * <p>Description:數位理財圖片</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class ImageJsonData {

    /** 連結 */
    @Schema(description = "連結")
    private String url;

    /** 連結名稱 */
    @Schema(description = "連結名稱")
    private String urlName;

    /** 首頁圖片路徑 */
    @Schema(description = "首頁圖片路徑")
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
