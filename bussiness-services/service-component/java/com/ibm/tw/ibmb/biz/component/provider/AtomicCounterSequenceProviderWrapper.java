package com.ibm.tw.ibmb.biz.component.provider;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;

public class AtomicCounterSequenceProviderWrapper implements SequenceProvider {

    @Value("${aibank.eai.seq.cache-size:100}")
    private long cacheSize;

    private SequenceProvider delegate;

    private AtomicLong currentValue = null;

    private long nextMax = -1;

    public AtomicCounterSequenceProviderWrapper(SequenceProvider delegate) {
        this.delegate = delegate;
    }

    @Override
    public synchronized String getNextSeq() {
        if (currentValue == null || currentValue.longValue() == nextMax) {
            Long nextSeqFromDB = this.doSelectNextSeq();
            currentValue = new AtomicLong(nextSeqFromDB.longValue());
            nextMax = nextSeqFromDB + getCacheSize();
        }
        long nextVal = currentValue.getAndIncrement();
        return formatSequence(nextVal);
    }

    private Long getCacheSize() {
        return cacheSize;
    }

    @Override
    public Long doSelectNextSeq() {
        return this.delegate.doSelectNextSeq();
    }

    @Override
    public String formatSequence(Long seq) {
        return this.delegate.formatSequence(seq);
    }

}
