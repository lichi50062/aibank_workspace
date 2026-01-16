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
package com.tfb.aibank.biz.user.services.datasync;

import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.biz.user.repository.AiDataSyncLogRepository;
import com.tfb.aibank.biz.user.repository.entities.AiDataSyncLogEntity;

//@formatter:off
/**
* @(#).java
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/06, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AiDataSyncLogService {

    private static final IBLog logger = IBLog.getLog(AiDataSyncLogService.class);

    private AiDataSyncLogRepository aiDataSyncLogRepository;

    public AiDataSyncLogService(AiDataSyncLogRepository aiDataSyncLogRepository) {
        this.aiDataSyncLogRepository = aiDataSyncLogRepository;
    }

    public AiDataSyncLogEntity save(AiDataSyncLogEntity entity) {
        return aiDataSyncLogRepository.save(entity);
    }
}
