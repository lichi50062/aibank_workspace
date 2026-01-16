/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.component.filter;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.MDC;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.context.ExecutionContext;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.log.AsyncLogProcessor;
import com.ibm.tw.ibmb.util.IBUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Log Filter for API Logger
 * 
 * @author horance
 *
 */
public class LoggingFilter implements Filter {
    // private static final String LOG_DT_FORMAT = "yyyyMMddHHmmss.SSS";
    protected static final IBLog performanceLogger = IBLog.getLog("PERFORMANCE_LOGGER");
    protected static final IBLog logger = IBLog.getLog(LoggingFilter.class);
    protected static final IBLog APILogger = IBLog.getLog("API_LOGGER");

    private static final String AIBANK_HEADER_PREFIX = "x-aibank-";
    private String applicationId = "UNKNOWN";
    private String apiBaseUri;

    private AsyncLogProcessor asyncLogProcessor;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        if (webApplicationContext != null) {
            // TODO: instance Id
            Environment environment = webApplicationContext.getEnvironment();
            this.applicationId = IBUtils.getInstanceId(environment);
            this.apiBaseUri = environment.getProperty("aibank.common.api-base-uri", "");
            this.asyncLogProcessor = webApplicationContext.getBean(AsyncLogProcessor.class);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ServletRequest execRequest = request;
        ServletResponse execResponse = response;

        HttpServletRequest req = (HttpServletRequest) execRequest;

        boolean isApiRequest = StringUtils.isNotBlank(apiBaseUri) && StringUtils.startsWith(req.getRequestURI(), apiBaseUri);
        boolean contextLogging = isApiRequest && APILogger.isDebugEnabled();

        if (contextLogging) {
            execRequest = new ContentCachingRequestWrapper(req);
            execResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
        }
        long start = System.currentTimeMillis();

        // loop through all headers, if match "X-AIBANK-" prefix, add header value to MDC
        ExecutionContext context = prepareExecContext(req);
        /**
         * 讓 request 繼續
         */
        chain.doFilter(execRequest, execResponse);

        long end = System.currentTimeMillis();

        long cost = end - start;
        // remove health check logging
        if (isApiRequest) {
            MDC.put("cost", String.valueOf(cost));
            performanceLogger.info(applicationId + ":" + req.getRequestURI() + ":" + cost);
            MDC.remove("cost");
        }
        if (contextLogging) {
            asyncLogProcessor.doApiLogging(context, execRequest, execResponse, start, end);
        }
        cleanupExecContext();
    }

    /**
     * 清除 context
     */
    private void cleanupExecContext() {
        MDC.clear();
        IBUtils.clearExecContext();
    }

    /**
     * 準備 context
     * 
     * @param req
     * @return
     */
    private ExecutionContext prepareExecContext(HttpServletRequest req) {
        ExecutionContext context = new ExecutionContext();
        Collections.list(req.getHeaderNames()).forEach(headerName -> {
            if (StringUtils.startsWithIgnoreCase(headerName, AIBANK_HEADER_PREFIX)) {
                String strippedHeaderName = StringUtils.substring(headerName, AIBANK_HEADER_PREFIX.length());
                String headerValue = req.getHeader(headerName);
                MDC.put(strippedHeaderName, headerValue);
                context.put(strippedHeaderName, headerValue);
            }
        });

        MDC.put(MDCKey.springinstanceid.name(), applicationId);
        context.put(MDCKey.springinstanceid.name(), applicationId);

        IBUtils.setExecContext(context);
        // 在這邊設定 I18NUtils
        // I18NUtils.setLocale(ConvertUtils.str2Locale(MDC.get(MDCKey.locale.name())));

        return context;

    }

    @Override
    public void destroy() {

    }

}
