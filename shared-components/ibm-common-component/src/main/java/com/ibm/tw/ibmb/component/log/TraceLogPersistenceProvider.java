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
package com.ibm.tw.ibmb.component.log;

/**
 * 
 * TraceLog persistence provider intarface
 * 
 * @author horance
 *
 * @param <ID>
 *            trace log id type
 * @param <LID>
 *            trace log data id type
 */
public interface TraceLogPersistenceProvider<ID, LID> {

    /**
     * determine whether the provider support "update" operation
     * 
     * @return
     */
    public boolean isMutableAfterSave();

    /**
     * save trace log
     * 
     * @param traceLog
     */
    public void saveTraceLog(ITraceLogEntity<ID> traceLog, ITraceLogDataEntity<LID, LID> traceLogData);

    /**
     * generate trace log key
     * 
     * @return
     */
    public ID generateTraceLogKey();

    /**
     * update metadata
     * 
     * @param rsTraceLog
     */
    public void updateMetadata(ITraceLogEntity<ID> rsTraceLog);
}
