/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.overview.model;

import com.tfb.aibank.common.model.TxHeadRq;

// @formatter:off
/**
 * @(#)GD320140Req.java
 * 
 * <p>Description:GD320140 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GD320140Req extends TxHeadRq {

    private static final long serialVersionUID = -3347370380653704713L;

    /** b1來源別(必要) */
    private String source;

    /** b2交易別 */
    private String category;

    /** b3交易分行 */
    private String brh;

    /** b4交易櫃員 */
    private String user;

    /** b5覆核主管 */
    private String director;

    /** b6黃金帳號 */
    private String acno;

    /** b7flag4 */
    private String flag4;

    /** b8channel id */
    private String channel;

    /** b9uuid */
    private String uuid;

    /** c1功能 */
    private String funcCod;

    /** c2統編 */
    private String idno;

    /** c3戶名代碼 */
    private String nameCod;

    /** c4是否傳送至結束 */
    private String conveyEnd;

    /** c5每次傳送比數 */
    private String conveyNumber;

    /** c6key */
    private String key;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrh() {
        return brh;
    }

    public void setBrh(String brh) {
        this.brh = brh;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getFlag4() {
        return flag4;
    }

    public void setFlag4(String flag4) {
        this.flag4 = flag4;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFuncCod() {
        return funcCod;
    }

    public void setFuncCod(String funcCod) {
        this.funcCod = funcCod;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getNameCod() {
        return nameCod;
    }

    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }

    public String getConveyEnd() {
        return conveyEnd;
    }

    public void setConveyEnd(String conveyEnd) {
        this.conveyEnd = conveyEnd;
    }

    public String getConveyNumber() {
        return conveyNumber;
    }

    public void setConveyNumber(String conveyNumber) {
        this.conveyNumber = conveyNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
