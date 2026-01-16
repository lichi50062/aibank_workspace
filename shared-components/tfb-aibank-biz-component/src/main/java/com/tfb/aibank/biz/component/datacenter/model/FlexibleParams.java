package com.tfb.aibank.biz.component.datacenter.model;

public class FlexibleParams {
    private String loginTime;
    private String prePageID;
    private String curPageID;
    private String pageName;

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getPrePageID() {
        return prePageID;
    }

    public void setPrePageID(String prePageID) {
        this.prePageID = prePageID;
    }

    public String getCurPageID() {
        return curPageID;
    }

    public void setCurPageID(String curPageID) {
        this.curPageID = curPageID;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
