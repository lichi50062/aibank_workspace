/*
 * ===========================================================================
 * IBM Confidential AIS Source Materials (C) Copyright IBM Corp. 2013.
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)CardUserProfileRepository.java
* 
* <p>Description:ISO3366國名對照表  Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/29, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "COUNTRY_NAME")
public class CountryNameEntity implements Serializable {
    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 7757647444476674103L;
    /**
     * ISO代碼(2位)
     */
    @Id
    @Column(name = "ISO_CODE")
    private String isoCode;
    /**
     * ISO代碼(3位)
     */
    @Basic
    @Column(name = "ISO_CODE2")
    private String isoCode2;
    /**
     * 國名(英文)
     */
    @Basic
    @Column(name = "NAME_EN_US")
    private String nameEnUs;
    /**
     * 國名(簡中)
     */
    @Basic
    @Column(name = "NAME_ZH_CN")
    private String nameZhCn;
    /**
     * 國名(繁中)
     */
    @Basic
    @Column(name = "NAME_ZH_TW")
    private String nameZhTw;

    /**
     * 取得ISO代碼(2位)
     * 
     * @return String ISO代碼(2位)
     */
    public String getIsoCode() {
        return this.isoCode;
    }

    /**
     * 設定ISO代碼(2位)
     * 
     * @param isoCode 要設定的ISO代碼(2位)
     */
    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    /**
     * 取得ISO代碼(3位)
     * 
     * @return String ISO代碼(3位)
     */
    public String getIsoCode2() {
        return this.isoCode2;
    }

    /**
     * 設定ISO代碼(3位)
     * 
     * @param isoCode2 要設定的ISO代碼(3位)
     */
    public void setIsoCode2(String isoCode2) {
        this.isoCode2 = isoCode2;
    }

    /**
     * 取得國名(英文)
     * 
     * @return String 國名(英文)
     */
    public String getNameEnUs() {
        return this.nameEnUs;
    }

    /**
     * 設定國名(英文)
     * 
     * @param nameEnUs 要設定的國名(英文)
     */
    public void setNameEnUs(String nameEnUs) {
        this.nameEnUs = nameEnUs;
    }

    /**
     * 取得國名(簡中)
     * 
     * @return String 國名(簡中)
     */
    public String getNameZhCn() {
        return this.nameZhCn;
    }

    /**
     * 設定國名(簡中)
     * 
     * @param nameZhCn 要設定的國名(簡中)
     */
    public void setNameZhCn(String nameZhCn) {
        this.nameZhCn = nameZhCn;
    }

    /**
     * 取得國名(繁中)
     * 
     * @return String 國名(繁中)
     */
    public String getNameZhTw() {
        return this.nameZhTw;
    }

    /**
     * 設定國名(繁中)
     * 
     * @param nameZhTw 要設定的國名(繁中)
     */
    public void setNameZhTw(String nameZhTw) {
        this.nameZhTw = nameZhTw;
    }
}
