package com.tfb.aibank.biz.pushsender.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.pushsender.repository.aib.CustomizedNotificationRecordRepository;
import com.tfb.aibank.biz.pushsender.repository.aib.PersonNotificationRecordRepository;
import com.tfb.aibank.biz.pushsender.repository.aib.SystemNotificationRecordRepository;
import com.tfb.aibank.biz.pushsender.resource.PushResource;
import com.tfb.aibank.biz.pushsender.services.customizednotification.CustomizedNotificationService;
import com.tfb.aibank.biz.pushsender.services.personnotification.PersonNotificationService;
import com.tfb.aibank.biz.pushsender.services.systemnotification.SystemNotificationService;

@Service
public class ServiceFactory {

    @Autowired
    private CustomizedNotificationRecordRepository customizedNotificationRecordRepository;

    @Autowired
    private PersonNotificationRecordRepository personNotificationRecordRepository;

    @Autowired
    private SystemNotificationRecordRepository systemNotificationRecordRepository;

    @Autowired
    private PushResource pushResource;

    @Autowired
    private SystemParamCacheManager sysParamCacheManager;

    public CustomizedNotificationService createCustomizedNotificationService() {
        return new CustomizedNotificationService(customizedNotificationRecordRepository, pushResource, sysParamCacheManager);
    }

    public PersonNotificationService createPersonNotificationService() {
        return new PersonNotificationService(personNotificationRecordRepository, pushResource, sysParamCacheManager);
    }

    public SystemNotificationService createSystemNotificationService() {
        return new SystemNotificationService(systemNotificationRecordRepository, pushResource, sysParamCacheManager);
    }

}
