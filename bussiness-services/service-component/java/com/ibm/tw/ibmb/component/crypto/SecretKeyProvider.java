/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.ibm.tw.ibmb.component.crypto;

/**
 * <p>
 * Title: com.ibm.tw.commons.util.crypto.SexretKeyProvider
 * </p>
 * <p>
 * Description: 定義密鑰提供者介面
 * </p>
 * <p>
 * Copyright: Copyright (c) IBM Corp. 2024. All Rights Reserved.
 * </p>
 * <p>
 * Company: IBM GBS Team
 * </p>
 * 
 * @author horance
 * @version 1.0
 */
public interface SecretKeyProvider<T> {
    /**
     * 回傳 SexretKey 的類型
     * 
     * @return
     */
    public SecretKeyType getSecretKeyType();

    /**
     * 回傳SexretKey
     * 
     * @return
     */
    public T getSecretKey();

    /**
     * 重新產生 sexret key
     */
    public void resetSecretKey();
}
