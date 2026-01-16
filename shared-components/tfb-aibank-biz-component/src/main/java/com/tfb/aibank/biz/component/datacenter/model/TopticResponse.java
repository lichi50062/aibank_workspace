package com.tfb.aibank.biz.component.datacenter.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
 /**
 * @(#)OfferActionRequest.java
 *
 * <p>Description:B2E 個人化主題 toptics - Response</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/04/30, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
 @Schema(description = "B2E 個人化主題 toptics")
 public class TopticResponse implements Serializable {

     private static final long serialVersionUID = 1L;
     @Schema(description = "訊息")
     private String message;
     @Schema(description = "結果")
     private String result;

     public String getMessage() {
         return message;
     }

     public void setMessage(String message) {
         this.message = message;
     }

     public String getResult() {
         return result;
     }

     public void setResult(String result) {
         this.result = result;
     }
 }
