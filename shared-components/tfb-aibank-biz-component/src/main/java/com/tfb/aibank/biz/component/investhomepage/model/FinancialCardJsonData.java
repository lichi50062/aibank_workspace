package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.util.HashMap;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema; /**
 * @(#)FinancialCardJsonData.java
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
public class FinancialCardJsonData {

    //// 關鍵話題

    /** 標題 */
    @Schema(description = "標題")
    private String title;

    /** 副標題 */
    @Schema(description = "副標題")
    private String subTitle;

    /** 連結 */
    @Schema(description = "連結")
    private String url;

    /** 圖表標題 */
    @Schema(description = "圖片路徑")
    private String imagePath;

    //// 時事風向

    /** 樣板 */
    @Schema(description = "樣板")
    private String template;

    /** 題目 */
    @Schema(description = "題目")
    private String question;

    /** 答案 */
    @Schema(description = "答案")
    private HashMap<String, String> ans;

    /** 觀點 */
    @Schema(description = "觀點")
    private String point;

    /** 連結名稱 */
    @Schema(description = "連結名稱")
    private String urlName;

    //// 研究報告
    /** PDF路徑 */
    @Schema(description = "PDF路徑")
    private String pdf;

    /** 首頁圖片路徑 */
    @Schema(description = "首頁圖片路徑")
    private String image;

    /** 短評 */
    @Schema(description = "短評")
    private String comment;

    /** 描述 */
    @Schema(description = "描述")
    private List<ReportDesc> reportDesc;

    /** 項目 */
    @Schema(description = "項目")
    private List<String> list;

    /** 內容 */
    @Schema(description = "內容")
    private List<String> content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashMap<String, String> getAns() {
        return ans;
    }

    public void setAns(HashMap<String, String> ans) {
        this.ans = ans;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public List<ReportDesc> getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(List<ReportDesc> reportDesc) {
        this.reportDesc = reportDesc;
    }
}
