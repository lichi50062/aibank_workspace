package com.tfb.aibank.biz.component.datacenter.model;

public class DTagGroup {
    // D-Tag標籤
    private String dTag;
    // D-tag標籤分數(1~10)
    private String dtagProbRank;

    public String getdTag() {
        return dTag;
    }

    public void setdTag(String dTag) {
        this.dTag = dTag;
    }

    public String getDtagProbRank() {
        return dtagProbRank;
    }

    public void setDtagProbRank(String dtagProbRank) {
        this.dtagProbRank = dtagProbRank;
    }
}
