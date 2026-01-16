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
package com.ibm.tw.ibmb.util;

import java.lang.reflect.Method;

import com.ibm.tw.commons.log.IBLog;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.util.SimpleMethodInvocation;

public class MethodSecuiryCheckUtils {

    /**
     * 有 token 就可以過，不限 scope
     */
    public static final String IS_AUTHENTICATED = "isAuthenticated()";

    /**
     * 沒有 scope 也可以用，供 system browser 下載，或 check session alive 使用
     */
    public static final String PERMIT_ALL = "permitAll()";

    private static IBLog logger = IBLog.getLog(MethodSecuiryCheckUtils.class);

    private static class SecurityObject {
        @SuppressWarnings("unused")
        public void triggerCheck() {
            /* NOP */ }
    }

    private static Method triggerCheckMethod;
    private static SpelExpressionParser parser;

    static {
        try {
            triggerCheckMethod = SecurityObject.class.getMethod("triggerCheck");
        }
        catch (NoSuchMethodException e) {
            logger.error("should not happed", e);
        }
        parser = new SpelExpressionParser();
    }

    public static boolean check(String securityExpression) {
        if (logger.isDebugEnabled()) {
            logger.debug("Checking security expression [" + securityExpression + "]...");
        }

        SecurityObject securityObject = new SecurityObject();
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        EvaluationContext evaluationContext = expressionHandler.createEvaluationContext(SecurityContextHolder.getContext().getAuthentication(), new SimpleMethodInvocation(securityObject, triggerCheckMethod));
        boolean checkResult = ExpressionUtils.evaluateAsBoolean(parser.parseExpression(securityExpression), evaluationContext);

        if (logger.isDebugEnabled()) {
            logger.debug("Check result: " + checkResult);
        }

        return checkResult;
    }

}