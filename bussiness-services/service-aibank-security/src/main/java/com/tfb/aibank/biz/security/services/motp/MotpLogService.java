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
package com.tfb.aibank.biz.security.services.motp;

import com.tfb.aibank.biz.security.services.motp.helper.MotpLogDataHelper;
import com.tfb.aibank.biz.security.services.motp.model.SaveMotpLogRequest;

// @formatter:off
/**
 * @(#)MotpLogService.java
 * 
 * <p>Description:MOTP_LOG_DATA</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/03, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MotpLogService {

    private MotpLogDataHelper motpLogDataHelper;

    public MotpLogService(MotpLogDataHelper motpLogDataHelper) {
        this.motpLogDataHelper = motpLogDataHelper;
    }

    public Boolean saveMotpLog(SaveMotpLogRequest rq) {
        motpLogDataHelper.save(rq.getType(),

                rq.getCustId(), rq.getUserId(), rq.getCompanyKind(), rq.getUidDup(),

                rq.getServiceName(), rq.getServiceMethod(), rq.getStatus(), rq.getRqData(), rq.getRsData());

        return true;
    }
}
