/*
 * ===========================================================================
 * 
 * WIS Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright WIS Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.component.investhomepage.model;

import java.util.List;


// @formatter:off
/**
 * @(#)BondGroupInformation.java
 * 
 * <p>Description:債券項目</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/22, xwr	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondGroupInformation {

	   /** 群組ID */
    private String groupId;

    /** 群組名稱 */
    private String groupName;

    /** 海外債券**/
    private List<OverSeaBondInformation> bondData;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<OverSeaBondInformation> getBondData() {
		return bondData;
	}

	public void setBondData(List<OverSeaBondInformation> bondData) {
		this.bondData = bondData;
	}
}
