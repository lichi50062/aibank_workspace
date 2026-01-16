/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import tw.com.ibm.mf.eb.EB622676SvcRqType;

//@formatter:off
/**
* @(#).java
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/07/30, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB622676RQ extends	EAIRequest<EB622676SvcRqType> {
	
	/**
	 * 建構子
	 */
	public EB622676RQ() {
		super("EB622676");
	}
}
