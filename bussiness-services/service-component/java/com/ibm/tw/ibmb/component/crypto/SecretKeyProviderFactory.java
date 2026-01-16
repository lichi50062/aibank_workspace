/*
 * =========================================================================== IBM Confidential AIS Source Materials (C) Copyright IBM Corp. 2013. ===========================================================================
 */
package com.ibm.tw.ibmb.component.crypto;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.log.IBLog;

/**
 * <p>
 * Title: com.ibm.tw.commons.util.crypto.SecretKeyProviderFactory
 * </p>
 * <p>
 * Description: 由設定檔中取得指定的 provider
 * </p>
 * <p>
 * Copyright: Copyright (c) IBM Corp. 2013. All Rights Reserved.
 * </p>
 * <p>
 * Company: IBM GBS Team
 * </p>
 * 
 * @author horance
 * @version 1.0
 */
@Service
public class SecretKeyProviderFactory implements ApplicationContextAware, InitializingBean {
    private static final String FACTORY_CLASS_NAME = SecretKeyProviderFactory.class.getName();
    /**
     * logger
     */
    private static IBLog logger = IBLog.getLog(SecretKeyProviderFactory.class);
    private Map<String, SecretKeyProvider<?>> providerCacheMap = new Hashtable<>();
    private static SecretKeyProviderFactory instance;

    /**
     * default constructor
     */
    private SecretKeyProviderFactory() {
        // do nothing
    }

    public synchronized void refreshProvidorCache() {
        for (SecretKeyProvider<?> provider : providerCacheMap.values()) {
            provider.resetSecretKey();
        }
        providerCacheMap.clear();
    }

    /**
     * 設定檔內容 (改由 spring context 取得）
     */
    private ApplicationContext applicationContext;

    /**
     * 取得預設 SecretKeyProvider (取設定檔中 com.ibm.tw.commons.util.crypto.SecretKeyProviderFactory 指定的 provider )
     * 
     * @return
     */
    public SecretKeyProvider<?> getProvider() {
        return getProvider(SecretKeyProviderFactory.class);
    }

    /**
     * 依傳入 Class name 取得 SecretKeyProvider
     * 
     * @param clazz
     * @return
     */
    public SecretKeyProvider<?> getProvider(Class<?> clazz) {
        return getProvider(clazz.getName());
    }

    public static SecretKeyProviderFactory getInstance() {
        return instance;
    }

    /**
     * 依傳入 Key 值 取得 SecretKeyProvider
     * 
     * @param className
     * @return
     */
    public SecretKeyProvider<?> getProvider(String settingKey) {
        SecretKeyProvider<?> result = null;
        synchronized (providerCacheMap) {
            result = providerCacheMap.get(settingKey);
            if (result != null) {
                return result;
            }
            SecretKeyProviderConfig props = applicationContext.getBean(SecretKeyProviderConfig.class);
            // create new one
            String providerClassName = props.getProperty(settingKey, props.getProperty(FACTORY_CLASS_NAME, DummySecretKeyProvider.class.getName()));
            try {
                Class<?> clazz = Class.forName(providerClassName);
                result = (SecretKeyProvider<?>) clazz.getDeclaredConstructors()[0].newInstance(new Object[0]);
                providerCacheMap.put(settingKey, result);
            }
            catch (NoSuchBeanDefinitionException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException e) {
                // fix for Fortify log forging
                logger.warn("error initial provider: ", e);
            }
        }
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SecretKeyProviderFactory.instance = this;
    }
}
