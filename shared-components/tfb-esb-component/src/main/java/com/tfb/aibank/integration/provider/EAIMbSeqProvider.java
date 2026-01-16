package com.tfb.aibank.integration.provider;

import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.biz.component.provider.OracleSequenceProvider;

@Component
public class EAIMbSeqProvider extends OracleSequenceProvider {

    @Override
    protected String getSeqName() {
        return "EAI_AI_SEQ";
    }

    @Override
    public String formatSequence(Long seq) {
        return StringUtils.right(String.format("%07d", seq), 7);
    }

}
