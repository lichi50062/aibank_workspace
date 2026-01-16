package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;
// @formatter:off
/**
 * @(#)NGNQU001020Rq.java
 *
 * <p>Description: NGNQU001020Rq</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[NGNQU001020Rq]</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001020Rq implements RqData {


    /**
     * 產品別：
     * 1. deposit
     * 2. creditcard
     * 3. investment
     * 4. loan
     * 5. insurance
     * 6. stock
     * */
    private String product;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
