package com.tfb.aibank.biz.pushsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.base.AbstractBaseServiceApplication;
import com.tfb.aibank.biz.pushsender.services.ServiceFactory;

@SpringBootApplication
@EnableScheduling
public class ServicePushSenderApplication extends AbstractBaseServiceApplication {

    private static final IBLog logger = IBLog.getLog(ServicePushSenderApplication.class);

    private static Class<ServicePushSenderApplication> applicationClass = ServicePushSenderApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(String[] args) {
        SpringApplication.run(ServicePushSenderApplication.class, args);
    }

    @Autowired
    private ServiceFactory serviceFactory;

    @Scheduled(fixedRate = 10000) // 10秒鐘執行一次
    public void systemNotification() {
        try {
            serviceFactory.createSystemNotificationService().handle();
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Scheduled(fixedRate = 10000) // 10秒鐘執行一次
    public void personNotification() {
        try {
            serviceFactory.createPersonNotificationService().handle();
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Scheduled(fixedRate = 10000) // 10秒鐘執行一次
    public void customizedNotification() {
        try {
            serviceFactory.createCustomizedNotificationService().handle();
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
