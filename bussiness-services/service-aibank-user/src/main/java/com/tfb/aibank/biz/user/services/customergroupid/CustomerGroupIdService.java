package com.tfb.aibank.biz.user.services.customergroupid;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.biz.user.repository.CustomerGroupIdRepository;
import com.tfb.aibank.biz.user.repository.entities.CustomerGroupIdEntity;
import com.tfb.aibank.biz.user.services.customergroupid.model.CustomerGroupIdModel;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * CUSTOMER_GROUP_ID 客群名單
 */
public class CustomerGroupIdService {

    private static final IBLog logger = IBLog.getLog(CustomerGroupIdService.class);
    /** 取得使用者服務 */

    Function<? super CustomerGroupIdEntity, CustomerGroupIdModel> entityToModel = ent -> {
        CustomerGroupIdModel model = new CustomerGroupIdModel();
        model.setCompanyKey(ent.getCompanyKey());
        model.setGroupId(ent.getGroupId());
        model.setCreateTime(ent.getCreateTime());
        return model;
    };

    private CustomerGroupIdRepository customerGroupIdRepository;

    public CustomerGroupIdService(CustomerGroupIdRepository customerGroupIdRepository) {
        this.customerGroupIdRepository = customerGroupIdRepository;
    }

    public List<CustomerGroupIdModel> getAllDatas() throws ActionException {
        List<CustomerGroupIdEntity> entities = customerGroupIdRepository.findAll();
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream().map(entityToModel).collect(Collectors.toList());
    }

}
