package com.tfb.aibank.biz.systemconfig.services;

import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.systemconfig.services.creditcardactivities.CreditCardActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tfb.aibank.biz.systemconfig.repository.CcAvalibleTaskRepository;
import com.tfb.aibank.biz.systemconfig.repository.DicRepository;
import com.tfb.aibank.biz.systemconfig.repository.ErrorCodeRepository;
import com.tfb.aibank.biz.systemconfig.repository.ErrorInfoRepository;
import com.tfb.aibank.biz.systemconfig.repository.MenuKeywordMappingRepository;
import com.tfb.aibank.biz.systemconfig.repository.MenuNameRepository;
import com.tfb.aibank.biz.systemconfig.repository.MenuRepository;
import com.tfb.aibank.biz.systemconfig.repository.RoleTaskRepository;
import com.tfb.aibank.biz.systemconfig.repository.SystemParamRepository;
import com.tfb.aibank.biz.systemconfig.repository.TaskPageRepository;
import com.tfb.aibank.biz.systemconfig.repository.TaskRepository;
import com.tfb.aibank.biz.systemconfig.repository.TaskTimeRepository;
import com.tfb.aibank.biz.systemconfig.repository.TxSystemMapRepository;
import com.tfb.aibank.biz.systemconfig.repository.UidDupAvailableTaskRepository;
import com.tfb.aibank.biz.systemconfig.services.availtask.AvailableTaskService;
import com.tfb.aibank.biz.systemconfig.services.cache.CacheService;
import com.tfb.aibank.biz.systemconfig.services.dic.DicService;
import com.tfb.aibank.biz.systemconfig.services.errorcode.ErrorCodeService;
import com.tfb.aibank.biz.systemconfig.services.menu.MenuService;
import com.tfb.aibank.biz.systemconfig.services.roletask.RoleTaskService;
import com.tfb.aibank.biz.systemconfig.services.session.SessionService;
import com.tfb.aibank.biz.systemconfig.services.systemparam.SystemParamService;
import com.tfb.aibank.biz.systemconfig.services.tasktime.TaskTimeService;
import com.tfb.aibank.component.session.LocalSessionManager;

@Service
public class ServiceFactory {

    @Autowired
    private ErrorCodeRepository errorCodeRepository;

    @Autowired
    private ErrorInfoRepository errorInfoRepository;

    @Autowired
    private TxSystemMapRepository txSystemMapRepository;

    @Autowired
    private LocalSessionManager localSessionManager;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RoleTaskRepository roleTaskRepository;

    @Autowired
    private SystemParamRepository systemParamRepository;

    @Autowired
    CcAvalibleTaskRepository ccAvalibleTaskRepository;

    @Autowired
    UidDupAvailableTaskRepository uidDupAvailableTaskRepository;

    @Autowired
    private TaskPageRepository taskPageRepository;

    @Autowired
    private IdentityService identityService;

    @Autowired
    SystemParamCacheManager systemParamCacheManager;

    /**
     * 以下為 cache manager 處理
     *
     */
    @Autowired
    @Qualifier("cacheManagerRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    @Qualifier("stringRedisTemplate")
    private RedisTemplate<String, String> sessionRedisTemplate;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuNameRepository menuNameRepository;

    @Autowired
    private TaskTimeRepository taskTimeRepository;

    @Autowired
    private DicRepository dicRepository;


    @Autowired
    private CacheManager springCacheManager;

    @Autowired
    private MenuKeywordMappingRepository menuKeywordMappingRepository;

    public ErrorCodeService createErrorCodeCollection() {
        return new ErrorCodeService(errorCodeRepository, errorInfoRepository, txSystemMapRepository);
    }

    public CacheService createCacheCodeCollection() {
        return new CacheService(redisTemplate, springCacheManager);
    }

    public MenuService createMenuCollection() {
        return new MenuService(this.menuRepository, this.menuNameRepository, menuKeywordMappingRepository);
    }

    public SessionService createSessionCollection() {
        return new SessionService(this.sessionRedisTemplate, this.localSessionManager);
    }

    public RoleTaskService createRoleTaskCollection() {
        return new RoleTaskService(taskRepository, roleTaskRepository,taskPageRepository);
    }

    public SystemParamService createSystemParamCollection() {
        return new SystemParamService(systemParamRepository);
    }

    public TaskTimeService createTaskTimeService() {
        return new TaskTimeService(taskTimeRepository);
    }

    public AvailableTaskService createAvailableTaskService() {
        return new AvailableTaskService(ccAvalibleTaskRepository, uidDupAvailableTaskRepository);
    }

    public DicService createDicService() {
        return new DicService(dicRepository);
    }

    public CreditCardActivitiesService createNCAOT006Service() {
        return new CreditCardActivitiesService(sessionRedisTemplate, systemParamCacheManager);
    }

}
