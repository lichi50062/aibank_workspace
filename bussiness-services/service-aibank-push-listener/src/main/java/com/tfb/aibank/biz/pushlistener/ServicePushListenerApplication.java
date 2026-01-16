package com.tfb.aibank.biz.pushlistener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.gson.Gson;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.base.AbstractBaseServiceApplication;
import com.tfb.aibank.biz.pushlistener.model.UpdateStatusModel;
import com.tfb.aibank.biz.pushlistener.services.ServiceFactory;
import com.tfb.aibank.biz.pushlistener.services.personsubscription.config.PersonalSubscriptionConfig;
import com.tfb.aibank.common.type.RowIdPrefixType;

@SpringBootApplication
@EnableScheduling
public class ServicePushListenerApplication extends AbstractBaseServiceApplication {

    private static final IBLog logger = IBLog.getLog(ServicePushListenerApplication.class);

    private static Class<ServicePushListenerApplication> applicationClass = ServicePushListenerApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(String[] args) {
        SpringApplication.run(ServicePushListenerApplication.class, args);
    }

    @Autowired
    private ServiceFactory serviceFactory;

    @Autowired
    private PersonalSubscriptionConfig personalSubscriptionConfig;

    @RabbitListener(queues = "${aibank.push.resultQueue}", concurrency = "${aibank.push.concurrency}")
    public void onResultMessage(String message) {

        logger.info("input message : {}", message);

        // 將訊息轉換成 UpdateStatusModel
        Gson gson = new Gson();
        UpdateStatusModel model = gson.fromJson(message, UpdateStatusModel.class);
        if (model == null) {
            logger.warn("conversion to object failed. ignored: {} ", message);
            return;
        }

        // 依 RowId 判斷，該交給哪支交易處理
        RowIdPrefixType type = RowIdPrefixType.findByPrefix(model.getRowId());
        if (type == null) {
            logger.warn("Incoming parameters do not match, ignored. RowId : {}", model.getRowId());
            return;
        }
        logger.info("row id prefix type : {}", type.name());

        switch (type) {
        case SYSTEM:
            serviceFactory.createSystemNotificationService(model).handle();
            break;
        case PERSON:
            serviceFactory.createPersonNotificationService(model).handle();
            break;
        case CUSTOMIZED:
            serviceFactory.createCustomizedNotificationService(model).handle();
            break;
        case LOGIN:
            serviceFactory.createLoginNotificationService(model).handle();
            break;
        case MOTP:
            // 不用處理
            logger.debug("got result for MOTP: {}", model.getRowId());
            break;
        }
    }

    /**
     * 訂閱個人化推播
     *
     * @param recordObj
     */
    @KafkaListener(topicPattern = "#{personalSubscriptionConfig.getTopicPattern()}", containerFactory = "personalSubscriptionContainerFactory")
    public void listen(ConsumerRecord<String, String> recordObj) {
        serviceFactory.createPersonSubscriptionService().handle(recordObj.value());
    }

}