package com.tfb.aibank.chl.general.qu001.model;

public class UnreachableCustomerInfo {
    /**
     * 是否為失聯戶
     * true：失聯戶
     * false：非失聯戶
     */
    private boolean isUnreachable;

    /** 失聯戶彈窗主標題 */
    private String title;

    /** 失聯戶彈窗訊息內容 */
    private String message;

    /** 失聯戶彈窗左鍵內容 */
    private String leftButtonText;

    /** 失聯戶彈窗右鍵內容 */
    private String rightButtonText;

    /** 失聯戶修改連結 */
    private String link;

    /**當前系統時間*/
    private String nowDate;

    /**
     * @return the unreachable
     */
    public boolean isUnreachable() {
        return isUnreachable;
    }

    /**
     * @param unreachable
     *            the unreachable to set
     */
    public void setUnreachable(boolean unreachable) {
        isUnreachable = unreachable;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the leftButtonText
     */
    public String getLeftButtonText() {
        return leftButtonText;
    }

    /**
     * @param leftButtonText
     *            the leftButtonText to set
     */
    public void setLeftButtonText(String leftButtonText) {
        this.leftButtonText = leftButtonText;
    }

    /**
     * @return the rightButtonText
     */
    public String getRightButtonText() {
        return rightButtonText;
    }

    /**
     * @param rightButtonText
     *            the rightButtonText to set
     */
    public void setRightButtonText(String rightButtonText) {
        this.rightButtonText = rightButtonText;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }
}
