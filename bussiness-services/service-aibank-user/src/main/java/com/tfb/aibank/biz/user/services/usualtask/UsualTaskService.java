package com.tfb.aibank.biz.user.services.usualtask;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.UsualTaskRepository;
import com.tfb.aibank.biz.user.repository.entities.UsualTaskEntity;
import com.tfb.aibank.biz.user.services.usualtask.model.UsualTaskModel;

/**
 * USUAL_TASK 常用功能設定
 */
public class UsualTaskService {

    private static final IBLog logger = IBLog.getLog(UsualTaskService.class);
    /** 取得使用者服務 */

    Function<? super UsualTaskEntity, UsualTaskModel> entityToModel = ent -> {
        UsualTaskModel model = new UsualTaskModel();
        model.setTaskSort(ent.getTaskSort());
        model.setTaskId(ent.getTaskId());
        model.setMenuId(ent.getMenuId());
        model.setCreateTime(ent.getCreateTime());
        return model;
    };
    Function<? super UsualTaskModel, UsualTaskEntity> modelToEntities = model -> {
        UsualTaskEntity ent = new UsualTaskEntity();
        ent.setCompanyKey(model.getCompanyKey());
        ent.setUserKey(model.getUserKey());
        ent.setTaskSort(model.getTaskSort());
        //USUAL_TASK.TASK_ID 非NULL，因此無值時要塞一格空白
        ent.setTaskId(StringUtils.defaultString(model.getTaskId(), model.getMenuId()));
        ent.setMenuId(model.getMenuId());
        ent.setCreateTime(model.getCreateTime());
        return ent;
    };
    private UsualTaskRepository usualTaskRepository;

    private IdentityService identityService;

    public UsualTaskService(IdentityService identityService, UsualTaskRepository usualTaskRepository) {
        this.identityService = identityService;
        this.usualTaskRepository = usualTaskRepository;
    }

    public List<UsualTaskModel> getUserUsualTasks(String custId, String userId, String uidDup, Integer companyKind) throws ActionException {
        logger.debug(" ＊＊＊=== getUserUsualTasks ===＊＊＊＊ custId: {}, userId: {}, companyKind: {}", custId, userId, companyKind);
        IdentityData user = getUser(custId, userId, uidDup, companyKind);
        logger.debug(" ＊=== getUserUsualTasks ===＊ IdentityData user: {}", IBUtils.attribute2Str(user));

        Integer companyKey = user.getCompanyKey();
        Integer userKey = user.getUserKey();

        List<UsualTaskEntity> entities = usualTaskRepository.findByCompanyKeyAndUserKeyOrderByTaskSortAsc(companyKey, userKey);
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream().map(entityToModel).collect(Collectors.toList());
    }

    public List<UsualTaskModel> updateUserUsualTasks(String custId, String userId, String uidDup, Integer companyKind, List<UsualTaskModel> usualTasks) throws ActionException {
        IdentityData user = getUser(custId, userId, uidDup, companyKind);
        Integer companyKey = user.getCompanyKey();
        Integer userKey = user.getUserKey();

        List<UsualTaskModel> usualTasksUpdated = null;
        List<UsualTaskEntity> entitiesToInsert = null;
        List<UsualTaskEntity> entities = usualTaskRepository.findByCompanyKeyAndUserKeyOrderByTaskSortAsc(companyKey, userKey);
        if (CollectionUtils.isNotEmpty(entities)) {
            usualTaskRepository.deleteAll(entities);
        }
        if (CollectionUtils.isNotEmpty(usualTasks)) {
            entitiesToInsert = usualTasks.stream().map(modelToEntities).collect(Collectors.toList());
            entitiesToInsert.forEach( ent -> {
                ent.setCompanyKey(companyKey);
                ent.setUserKey(userKey);
                ent.setCreateTime(new Date());
            });
            entitiesToInsert = usualTaskRepository.saveAll(entitiesToInsert);
        }
        if( null != entitiesToInsert){
            usualTasksUpdated = entitiesToInsert.stream().map(entityToModel).collect(Collectors.toList());
        }else {
            usualTasksUpdated = Collections.emptyList();
        }
        return usualTasksUpdated;
    }


    /**
     * 取得使用者資料
     *
     * @param userId
     * @param userUuid
     * @param companyKind
     * @return
     * @throws ActionException
     */
    private IdentityData getUser(String userId, String userUuid, String uidDup, Integer companyKind) throws ActionException {
        try {
            IdentityData identityData = identityService.query(userId, uidDup, userUuid,  companyKind);
            logger.debug("identityData: {}", IBUtils.attribute2Str(identityData));
            if (identityData != null && identityData.isAliveUser()) {
                return identityData;
            }
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }
}
