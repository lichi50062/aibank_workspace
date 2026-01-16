package com.tfb.aibank.biz.pushlistener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.pushlistener.model.UpdateStatusModel;
import com.tfb.aibank.biz.pushlistener.repository.CompanyRepository;
import com.tfb.aibank.biz.pushlistener.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.pushlistener.repository.OfferContentDetailRepository;
import com.tfb.aibank.biz.pushlistener.repository.OfferMasterRepository;
import com.tfb.aibank.biz.pushlistener.repository.OfferNotificationInfoRepository;
import com.tfb.aibank.biz.pushlistener.repository.UserRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.CustomizedNotificationRecordRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.FirstDownloadRecordRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.PersonNotificationRecordRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.PushSubscriptionRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.SystemNotificationRecordRepository;
import com.tfb.aibank.biz.pushlistener.services.customizednotification.CustomizedNotificationService;
import com.tfb.aibank.biz.pushlistener.services.loginnotification.LoginNotificationService;
import com.tfb.aibank.biz.pushlistener.services.personnotification.PersonNotificationService;
import com.tfb.aibank.biz.pushlistener.services.personsubscription.PersonSubscriptionService;
import com.tfb.aibank.biz.pushlistener.services.systemnotification.SystemNotificationService;
import com.tfb.aibank.component.dic.DicCacheManager;

@Service
public class ServiceFactory {

    @Autowired
    private CustomizedNotificationRecordRepository customizedNotificationRecordRepository;

    @Autowired
    private PersonNotificationRecordRepository personNotificationRecordRepository;

    @Autowired
    private SystemNotificationRecordRepository systemNotificationRecordRepository;

    @Autowired
    private FirstDownloadRecordRepository firstDownloadRecordRepository;

    @Autowired
    private OfferMasterRepository offerMasterRepository;
    @Autowired
    private OfferContentDetailRepository offerContentDetailRepository;
    @Autowired
    private OfferNotificationInfoRepository offerNotificationInfoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SystemParamCacheManager systemParamCacheManager;
    @Autowired
    private DicCacheManager dicCacheManager;
    @Autowired
    private MbDeviceInfoRepository mbDeviceInfoRepository;
    @Autowired
    private MbDevicePushInfoRepository mbDevicePushInfoRepository;
    @Autowired
    private PushSubscriptionRepository pushSubscriptionRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public PersonNotificationService createPersonNotificationService(UpdateStatusModel model) {
        return new PersonNotificationService(model, personNotificationRecordRepository);
    }

    public CustomizedNotificationService createCustomizedNotificationService(UpdateStatusModel model) {
        return new CustomizedNotificationService(model, customizedNotificationRecordRepository);
    }

    public SystemNotificationService createSystemNotificationService(UpdateStatusModel model) {
        return new SystemNotificationService(model, systemNotificationRecordRepository);
    }

    public LoginNotificationService createLoginNotificationService(UpdateStatusModel model) {
        return new LoginNotificationService(model, firstDownloadRecordRepository);
    }

    public PersonSubscriptionService createPersonSubscriptionService() {
        return new PersonSubscriptionService(offerMasterRepository, customizedNotificationRecordRepository, offerContentDetailRepository, offerNotificationInfoRepository, userRepository, systemParamCacheManager, dicCacheManager, mbDeviceInfoRepository, mbDevicePushInfoRepository, pushSubscriptionRepository, companyRepository);
    }
}
