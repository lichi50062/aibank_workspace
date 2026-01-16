/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * @(#)KYCQuestionResponse.java
 * 
 * <p>Description:KYC填答結果查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/20, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KycQuestionResponse implements Serializable {

    private static final long serialVersionUID = 8644727655075299935L;

    private List<KycQuestion> questions = new ArrayList<>();

    public List<KycQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<KycQuestion> questions) {
        this.questions = questions;
    }
}
