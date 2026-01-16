/**
 *
 */
package com.tfb.aibank.chl.model.homepagecard;

import com.tfb.aibank.chl.component.homepagecard.CardEvent;

//@formatter:off
/**
* @(#)CardInfo.java
* 
* <p>Description:HomePageCard</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/22 Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class HomePageCard {
    /** TAG **/
    private String tag;

    /** TITLE **/
    private String title;

    /** CONTENT */
    private String content;

    /** CONTENT2 */
    private String content2;

    /** CARD_TARGET */
    private String cardTarget;

    /** ICON */
    private String icon;

    /** SORT */
    private int sort;

    private CardEvent cardevent;

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     *            the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the content2
     */
    public String getContent2() {
        return content2;
    }

    /**
     * @param content2
     *            the content2 to set
     */
    public void setContent2(String content2) {
        this.content2 = content2;
    }

    /**
     * @return the cardTarget
     */
    public String getCardTarget() {
        return cardTarget;
    }

    /**
     * @param cardTarget
     *            the cardTarget to set
     */
    public void setCardTarget(String cardTarget) {
        this.cardTarget = cardTarget;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the sort
     */
    public int getSort() {
        return sort;
    }

    /**
     * @param sort
     *            the sort to set
     */
    public void setSort(int sort) {
        this.sort = sort;
    }

    /**
     * @return the cardevent
     */
    public CardEvent getCardevent() {
        return cardevent;
    }

    /**
     * @param cardevent
     *            the cardevent to set
     */
    public void setCardevent(CardEvent cardevent) {
        this.cardevent = cardevent;
    }

}
