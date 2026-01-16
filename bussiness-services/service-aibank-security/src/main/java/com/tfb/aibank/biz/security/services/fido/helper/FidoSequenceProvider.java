package com.tfb.aibank.biz.security.services.fido.helper;

import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.biz.component.provider.OracleSequenceProvider;

// @formatter:off
/**
 * @(#)FidoSequenceProvider.java
 * 
 * <p>Description:FIDO Sequence</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/01, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class FidoSequenceProvider extends OracleSequenceProvider {

    @Override
    protected String getSeqName() {
        return "TWID_FIDO_SEQ";
    }

    @Override
    public String formatSequence(Long seq) {
        return StringUtils.right(String.format("%08d", seq), 8);
    }

}
