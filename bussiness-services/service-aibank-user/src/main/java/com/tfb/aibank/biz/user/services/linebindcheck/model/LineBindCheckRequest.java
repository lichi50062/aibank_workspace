/**
 *
 */
package com.tfb.aibank.biz.user.services.linebindcheck.model;

/**
 // @formatter:off
 * @(#)LineBindCheckRequest.java
 *
 * <p>Description:[LineBindCheckModel]</p>
 *
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/1, Marty Pan
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LineBindCheckRequest {
    private String idno;
    private long time;

    public LineBindCheckRequest(String idno, long time) {
        this.idno = idno;
        this.time = time;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}