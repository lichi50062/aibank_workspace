package com.tfb.aibank.component.log;

import java.util.Map;
import java.util.concurrent.ThreadFactory;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import com.ibm.tw.commons.log.IBLog;

public class MDCTaskDecorator implements TaskDecorator {

    private static IBLog logger = IBLog.getLog(MDCTaskDecorator.class);

    private ThreadFactory threadFactory;

    public MDCTaskDecorator() {
    }

    public MDCTaskDecorator(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public static class MDCDecoratedRunnable implements Runnable {

        private final Runnable runnable;

        private final Map<String, String> contextMap;

        public MDCDecoratedRunnable(Runnable runnable) {
            super();
            this.runnable = runnable;
            this.contextMap = MDC.getCopyOfContextMap();
        }

        @Override
        public void run() {
            try {
                MDC.setContextMap(contextMap);
                runnable.run();
            }
            finally {
                try {
                    MDC.clear();
                }
                catch (Throwable e) {
                    // should not happened
                    logger.warn("", e);
                }
            }
        }

    }

    @Override
    public Runnable decorate(Runnable runnable) {
        if (this.threadFactory != null) {
            return new MDCDecoratedRunnable(this.threadFactory.newThread(runnable));
        }
        else {
            return new MDCDecoratedRunnable(runnable);
        }

    }
}