package com.tfb.aibank.chl.system.ot004.model;

import com.ibm.tw.ibmb.base.model.ClientRequest;
import com.tfb.aibank.chl.session.AIBankUser;

public class AddWidgetRecordInput {
    private String memo;
    private ClientRequest request;
    private AIBankUser aibankUser;

    public ClientRequest getRequest() {
        return request;
    }

    public void setRequest(ClientRequest request) {
        this.request = request;
    }

    public AIBankUser getAibankUser() {
        return aibankUser;
    }

    public void setAibankUser(AIBankUser aibankUser) {
        this.aibankUser = aibankUser;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
