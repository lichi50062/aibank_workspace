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

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.collections.MapUtils;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.DatabaseException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;

import feign.FeignException;
import feign.RetryableException;
import io.lettuce.core.RedisException;
import jakarta.persistence.PersistenceException;

// @formatter:off
/**
 * @(#)ExceptionUtils.java
 * 
 * <p>Description:Exception 處理共用程式</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ExceptionUtils extends org.apache.commons.lang3.exception.ExceptionUtils {

    private static IBLog logger = IBLog.getLog(ExceptionUtils.class);

    /**
     * 將 ServiceException 轉換為 ActionException
     * 
     * @param serviceException
     *            從 business-services 抛出的例外
     * @return
     */
    public static ActionException getActionException(ServiceException serviceException) {
        ActionException ae = new ActionException(serviceException.getSystemId(), serviceException.getErrorCode(), serviceException.getSeverity(), serviceException.getErrorDesc());
        if (MapUtils.isNotEmpty(serviceException.getExtraInfo())) {
            ae.getStatus().setExtras(serviceException.getExtraInfo());
        }
        return getActionException(ae);
    }

    /**
     * 將 b2cUaaRuntimeException 轉換為 ActionException ( 有 Cause 則取 Cause, 否則丟UnknownException)
     * 
     * @param responseException
     * @return
     */
    public static ActionException getActionException(RuntimeException runtimeException) {
        if (runtimeException.getCause() != null && runtimeException != runtimeException.getCause()) {
            return getActionException(runtimeException.getCause());
        }
        return getActionException(new ActionException(CommonErrorCode.UNKNOWN_EXCEPTION, runtimeException));
    }

    /**
     * 將 DatabaseException 轉換為 ActionException
     * 
     * @param responseException
     * @return
     */
    public static ActionException getActionException(DatabaseException databaseException) {
        return getActionException(new ActionException(CommonErrorCode.DATABASE_EXCEPTION, databaseException));
    }

    /**
     * 將 FeignActionException 轉換為 ActionException
     * 
     * @param responseException
     * @return
     */
    public static ActionException getActionException(FeignException feignException) {
        Throwable cause = feignException.getCause();
        if (cause != null && cause != feignException) {
            return getActionException(cause);
        }
        return getActionException(new ActionException(CommonErrorCode.SERVICE_UNAVALIBLE, feignException));
    }

    /**
     * 處理 SQL Exception
     * 
     * @param t
     * @return
     */
    public static ActionException getActionException(SQLException sqlException) {
        return getActionException(new ActionException(CommonErrorCode.DATABASE_EXCEPTION, sqlException));
    }

    /**
     * 處理 Hystrix Timeout Exception
     * 
     * @param t
     * @return
     */
    public static ActionException getActionException(TimeoutException timeoutException) {
        return getActionException(new ActionException(CommonErrorCode.HYSTRIX_TIMEOUT, timeoutException));
    }

    /**
     * 處理 Feign client Timeout Exception
     * 
     * @param t
     * @return
     */
    public static ActionException getActionException(InterruptedIOException interruptedIOException) {
        return getActionException(new ActionException(CommonErrorCode.FEIGN_CLIENT_TIMEOUT, interruptedIOException));
    }

    /**
     * 處理 Feign ConnectException
     * 
     * @param t
     * @return
     */
    public static ActionException getActionException(ConnectException connectException) {
        return getActionException(new ActionException(CommonErrorCode.BIZ_UNAVALIBLE, connectException));
    }

    /**
     * 處理 RetryableException
     * 
     * @param t
     * @return
     */
    public static ActionException getActionException(RetryableException retryableException) {
        return getActionException(new ActionException(CommonErrorCode.FEIGN_CLIENT_TIMEOUT, retryableException));
    }

    /**
     * 處理 IllegalAccess Exception
     * 
     * @param t
     * @return
     */
    public static ActionException getActionException(IllegalAccessException illegalAccessException) {
        return getActionException(new ActionException(CommonErrorCode.ILLEGAL_ACCESS_EXCEPTION, illegalAccessException));
    }

    /**
     * 處理 Jedis Exception
     * 
     * @param t
     * @return
     */
    public static ActionException getActionException(RedisException redisException) {
        return getActionException(new ActionException(CommonErrorCode.REDIS_UNAVAIBLE, redisException));
    }

    /**
     * 處理 CryptException Exception
     * 
     * @param t
     * @return
     */
    public static ActionException getActionException(CryptException cryptException) {
        ActionException ae = new ActionException(CommonErrorCode.E2E_ERROR);
        ActionException trow = new ActionException(ae.getSystemId(), ae.getErrorCode(), ae.getSeverity(), cryptException.getMessage());
        return getActionException(trow);
    }

    /**
     * 處理 IO Exception
     * 
     * @param t
     * @return
     */
    public static ActionException getActionException(IOException ioException) {
        return getActionException(new ActionException(CommonErrorCode.INPUT_OR_OUTPUT_EXCEPTION, ioException));
    }

    /**
     * 用於需指定 locale 之情況 (如 Servlet)
     * 
     * @param t
     * @param userLocale
     * @return
     */
    public static ActionException getActionException(Throwable t) {
        if (t instanceof ActionException) {
            // 轉換過才印
            logger.error("processing action exception: ", t);
        }
        if (t instanceof InvocationTargetException) {
            return getActionException(((InvocationTargetException) t).getTargetException());
        }
        else if (t instanceof RedisException) {
            return getActionException((RedisException) t);
        }
        else if (t instanceof ServiceException) {
            return getActionException((ServiceException) t);
        }
        else if (t instanceof ActionException) {
            return (ActionException) t;
        }
        else if (t instanceof SQLException) {
            return getActionException((SQLException) t);
        }
        else if (t instanceof PersistenceException) {
            return getActionException(new DatabaseException(t));
        }
        else if (t instanceof DatabaseException) {
            return getActionException((DatabaseException) t);
        }
        else if (t instanceof CryptException) {
            // HSM
            return getActionException((CryptException) t);
        }
        else if (t instanceof RetryableException) {
            // Throwable cause = getRootCause(t);
            return getActionException((RetryableException) t);
        }
        else if (t instanceof FeignException) {
            return getActionException((FeignException) t);
        }
        else if (t instanceof TimeoutException) {
            return getActionException((TimeoutException) t);
        }
        else if (t instanceof InterruptedIOException) {
            return getActionException((InterruptedIOException) t);
        }
        else if (t instanceof IllegalAccessException) {
            return getActionException((IllegalAccessException) t);
        }
        else if (t instanceof RuntimeException) {
            return getActionException((RuntimeException) t);
        }
        else if (t instanceof ConnectException) {
            return getActionException((ConnectException) t);
        }
        else if (t instanceof IOException) {
            return getActionException((IOException) t);
        }
        logger.error("will be converted to unknown exception: {}", t.getClass().getCanonicalName());
        return getActionException(new ActionException(CommonErrorCode.UNKNOWN_EXCEPTION, t));
    }

    public static ActionException getActionException(ErrorStatus errorStatus) {
        return getActionException(new ActionException(errorStatus));
    }

    public static ActionException getActionException(IErrorCode errorCode) {
        return getActionException(errorCode.getError());
    }

    public static ActionException getActionException(String msg, IErrorCode errorCode) {
        return getActionException(new ActionException(msg, errorCode));
    }

    public static String getFullStackTrace(Exception ex) {
        return org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(ex);
    }

}
