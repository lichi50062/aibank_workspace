package com.tfb.aibank.component;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tfb.aibank.component.log.MDCTaskDecorator;

import io.micrometer.context.ContextExecutorService;
import io.micrometer.context.ContextRegistry;
import io.micrometer.context.ContextSnapshot;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.contextpropagation.ObservationAwareSpanThreadLocalAccessor;
import jakarta.annotation.PostConstruct;

@Configuration(proxyBeanMethods = false)
class SpanThreadLocalAccessorConfig {

    @Configuration(proxyBeanMethods = false)
    static class AsyncConfig implements AsyncConfigurer, WebMvcConfigurer {
        @Autowired
        ThreadPoolTaskExecutor threadExecutor;

        @Override
        public Executor getAsyncExecutor() {
            return ContextExecutorService.wrap(new ExecutorServiceAdapter(threadExecutor), ContextSnapshot::captureAll);
        }

        @Override
        public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
            threadExecutor.setTaskDecorator(new MDCTaskDecorator(r -> {
                return new Thread(ContextSnapshot.captureAll().wrap(r));
            }));
            configurer.setTaskExecutor(threadExecutor);
        }
    }

    @Autowired
    Tracer tracer;

    @PostConstruct
    void setupThreadLocalAccessor() {
        ContextRegistry.getInstance().registerThreadLocalAccessor(new ObservationAwareSpanThreadLocalAccessor(tracer));
    }

    @Bean
    TaskDecorator mdcTaskDecorator() {
        return new MDCTaskDecorator();
    }
}