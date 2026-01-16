package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

import java.util.List;
// @formatter:off
/**
 * @(#)NGNQU001050Rq.java
 *
 * <p>Description: 首頁-匯率資料區塊Rq</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>匯率資料區塊Rq</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001050Rq implements RqData {

    private List<String> custCurList;

    public List<String> getCustCurList() {
        return custCurList;
    }

    public void setCustCurList(List<String> custCurList) {
        this.custCurList = custCurList;
    }
}
