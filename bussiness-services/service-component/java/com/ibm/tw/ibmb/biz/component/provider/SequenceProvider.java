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
package com.ibm.tw.ibmb.biz.component.provider;

// @formatter:off
/**
 * @(#)SequenceProvider.java
 * 
 * <p>Description:Sequence Provider interface</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/14, Horance Chou	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface SequenceProvider {

    public default String getNextSeq() {
        Long seq = doSelectNextSeq();
        return formatSequence(seq);
    }

    public abstract Long doSelectNextSeq();

    public String formatSequence(Long seq);

}
