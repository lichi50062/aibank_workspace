package com.tfb.aibank.chl.general.qu002.task;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002011Rq;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002011Rs;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002Cache;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002Output;
import com.tfb.aibank.chl.general.qu002.service.NGNQU002Service;
import com.tfb.aibank.common.model.ServiceBase;

// @formatter:off
/**
 * @(#)NGNQU002011Task.java
 * 
 * <p>Description:服務據點 011 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU002011Task extends AbstractAIBankBaseTask<NGNQU002011Rq, NGNQU002011Rs> {
    @Autowired
    private NGNQU002Service service;

    private NGNQU002Output dataOutput = new NGNQU002Output();

    @Override
    public void validate(NGNQU002011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU002011Rq rqData, NGNQU002011Rs rsData) throws ActionException {
        NGNQU002Cache cache = getCache(NGNQU002Service.NCCQU001_CACHE_KEY, NGNQU002Cache.class);
        BigDecimal latitude = rqData.getLatitude();
        BigDecimal longitude = rqData.getLongitude();
        // 取得BRANCHMAP
        service.getAllBranchmap(dataOutput);

        BigDecimal distance = new BigDecimal(2.5);
        // 分行 並取得最近分行
        service.getNearestBaseInformations(latitude, longitude, dataOutput.getBranchmapList(), 1, getLocale(), cache.getLatitude(), cache.getLongitude(), dataOutput);
        List<ServiceBase> branchs = dataOutput.getServiceBases();
        if (CollectionUtils.isNotEmpty(branchs)) {
            List<ServiceBase> nearbyBranchs = branchs.stream().filter(branch -> branch.getRadiusDistance().compareTo(distance) <= 0).collect(Collectors.toList());
            rsData.setBranchs(nearbyBranchs);
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
