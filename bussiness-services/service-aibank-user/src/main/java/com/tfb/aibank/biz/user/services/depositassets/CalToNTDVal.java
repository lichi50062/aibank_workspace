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
package com.tfb.aibank.biz.user.services.depositassets;

//@formatter:off
/**
* @(#)CalToNTDVal.java
* 
* <p>Description: 換算外幣成臺幣</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/12, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FunctionalInterface
public interface CalToNTDVal<A, B, C, O> {

    O apply(A a, B b, C c);
}
