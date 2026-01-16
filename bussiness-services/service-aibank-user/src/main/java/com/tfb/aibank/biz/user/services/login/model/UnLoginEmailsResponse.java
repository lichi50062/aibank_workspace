/**
 * 
 */
package com.tfb.aibank.biz.user.services.login.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)UnLoginEmailsResponse.java
* 
* <p>Description:未登入時查詢 Email</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "UnLoginEmailsResponse")
public class UnLoginEmailsResponse {

    /** email */
    @Schema(description = "email")
    private List<String> emails;

    /**
     * @return the emails
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     * @param emails
     *            the emails to set
     */
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
