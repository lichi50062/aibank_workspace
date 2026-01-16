package com.tfb.aibank.chl.creditcard.qu016.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

@Component
public class NCCQU016010Rq implements RqData {

    String key;
    String custId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }
}
