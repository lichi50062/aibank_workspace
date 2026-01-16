/**
 *
 */
package com.tfb.aibank.chl.model.homepagecard;

//@formatter:off
/**
* @(#)CardDescModel.java
* 
* <p>Description:HomePageCard CardDesc</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/22, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CardDesc {

    /** TAG **/
    private String tag;

    /** TITLE **/
    private String title;

    /** CONTENT */
    private String content;

    /** CONTENT2 */
    private String content2;

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

}
