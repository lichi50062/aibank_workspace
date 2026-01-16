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
package com.tfb.aibank.chl.component.customergroupid;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)CustomerGroupIdCacheManager.java
 * 
 * <p>Description:客群代碼 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/25
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class CustomerGroupIdCacheManager extends AbstractCacheManager {

    @Autowired
    private CustomerGroupIdResource resource;

    private Map<Integer, List<CustomerGroupId>> customerGroupIdsMap = null;

    /**
     * 取得所有 CustomerGroupId
     *
     * @return
     */
    public List<CustomerGroupId> getCustomerGroupIdByKey(Integer cKey) {
        return this.customerGroupIdsMap.getOrDefault(cKey, Collections.emptyList());
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.NEW_FUNC_INTROS_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<CustomerGroupId> customerGroupIds = resource.getAllCustomerGroupIds();
        this.customerGroupIdsMap = customerGroupIds.stream().filter(cgi -> Objects.nonNull(cgi.getCompanyKey())).collect(Collectors.groupingBy(CustomerGroupId::getCompanyKey));
    }

    @Override
    protected boolean isFirstLoad() {
        return this.customerGroupIdsMap == null;
    }

}
