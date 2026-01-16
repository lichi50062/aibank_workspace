package com.tfb.aibank.chl.general;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.base.AbstractBaseChannelApplication;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.component.session.LocalSessionManager;

import jakarta.annotation.PreDestroy;

public class AIBankGeneralChannelApplication extends AbstractBaseChannelApplication {

    private static IBLog logger = IBLog.getLog(AIBankGeneralChannelApplication.class);

    private static Class<AIBankGeneralChannelApplication> applicationClass = AIBankGeneralChannelApplication.class;

    /** 當前spring ApplicationContext */
    private static ConfigurableApplicationContext currentContext;

    /** 當前spring LocalSessionManager */
    private static LocalSessionManager sessionManager;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(String[] args) {
        System.setProperty("aibank.channel.session-cleaner.enabled", "true");

        currentContext = SpringApplication.run(AIBankGeneralChannelApplication.class, args);
        sessionManager = currentContext.getBean(LocalSessionManager.class);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("AIBankGeneralChannelApplication 關閉中");
            AIBankGeneralChannelApplication app = currentContext.getBean(AIBankGeneralChannelApplication.class);

            app.moveDestroySessionListToCommon();
            logger.info("AIBankGeneralChannelApplication 關閉完成");
        }));

    }

    @PreDestroy
    public void moveDestroySessionListToCommon() {
        logger.debug("moveSessionList podName Session Key 清除");
        // 當前pod中所有的登入sessionId
        Set<String> sessionKeyInPods = ValidateParamUtils.validParamSet(sessionManager.getAppSessionIds());
        if (CollectionUtils.isEmpty(sessionKeyInPods)) {
            return;
        }
        // 新增sessionId 到共用SessionSet
        if (sessionManager.addCommonSessionIds(sessionKeyInPods.toArray(String[]::new))) {
            // 刪除SessionId
            if (!sessionManager.removeAllAppSessionIds(sessionKeyInPods.toArray())) {
                logger.error("pod 刪除時 moveSession 於刪除session資料時失敗");
            }
        }
        else {
            logger.error("pod 刪除時 moveSession 於新增到共用session資料時失敗");
        }

    }
}
