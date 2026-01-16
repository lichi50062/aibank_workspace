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
package com.tfb.aibank.chl.component.datacenter.model;

import java.util.List;

//@formatter:off
/**
* @(#)UserTagResponse.java
* 
* <p>Description:取得情境版位資料 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
 public class UserTagResponse {

     /** 個性標籤 */
     private List<String> pdrsonalityTag;

     /** 風險標籤 */
     private List<String> riskTag;

     /**
      * @return the pdrsonalityTag
      */
     public List<String> getPdrsonalityTag() {
         return pdrsonalityTag;
     }

     /**
      * @param pdrsonalityTag
      *         the pdrsonalityTag to set
      */
     public void setPdrsonalityTag(List<String> pdrsonalityTag) {
         this.pdrsonalityTag = pdrsonalityTag;
     }

     /**
      * @return the riskTag
      */
     public List<String> getRiskTag() {
         return riskTag;
     }

     /**
      * @param riskTag
      *         the riskTag to set
      */
     public void setRiskTag(List<String> riskTag) {
         this.riskTag = riskTag;
     }

 }
