/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB512167SvcRqType;
import tw.com.ibm.mf.eb.EB512167SvcRsType;

//@formatter:off
/**
* @(#)EB512167RQ.java
* 
* <p>Description:EB512167 無卡提款功能維護 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/


@Converter(customConverters = { @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*"), 
                              @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
})
public class EB512167RS extends EAIResponse<EB512167SvcRqType, EB512167SvcRsType> {

  /**
   * 建構子
   */
  public EB512167RS() {
      super("EB512167");
  }

  @Override
  protected boolean isNoData(String errId) {
      if (super.isNoData(errId)) {
          return true;
      }
      return false;
  }
}
//@formatter:on