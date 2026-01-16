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
package com.ibm.tw.ibmb.type;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.model.WrapperBean;

// @formatter:off
/**
 * @(#)NotBeanType.java
 * 
 * <p>Description:非 JavaBean 的物件型別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum NotBeanType {

    STRING(String.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<String> target = new WrapperBean<String>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<String>((String) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("String.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    CHARACTER(Character.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Character> target = new WrapperBean<Character>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Character>((Character) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Character.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    SHORT(Short.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Short> target = new WrapperBean<Short>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Short>((Short) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Short.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    INTEGER(Integer.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Integer> target = new WrapperBean<Integer>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Integer>((Integer) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Integer.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    LONG(Long.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Long> target = new WrapperBean<Long>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Long>((Long) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Long.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    DOUBLE(Double.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Double> target = new WrapperBean<Double>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Double>((Double) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Double.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    BYTE(Byte.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Byte> target = new WrapperBean<Byte>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Byte>((Byte) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Byte.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    FLOAT(Float.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Float> target = new WrapperBean<Float>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Float>((Float) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Float.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    BOOLEAN(Boolean.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Boolean> target = new WrapperBean<Boolean>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Boolean>((Boolean) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Boolean.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    BIG_DECIMAL(BigDecimal.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<BigDecimal> target = new WrapperBean<BigDecimal>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<BigDecimal>((BigDecimal) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("BigDecimal.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    DATE(Date.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Date> target = new WrapperBean<Date>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Date>((Date) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Date.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    },

    LOCLAE(Locale.class) {
        @Override
        public Object wrapper(Object source) throws ActionException {
            WrapperBean<Locale> target = new WrapperBean<Locale>();
            try {
                BeanUtils.copyProperties(target, new WrapperBean<Locale>((Locale) source));
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new ActionException("Locale.class arguments wrapper error.", CommonErrorCode.BEAN_WRAPPER_ERROR);
            }
            return target.getValue();
        }
    };

    private Class<?> clazz;

    NotBeanType(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public static NotBeanType findByClazz(Class<?> clazz) {
        for (NotBeanType type : NotBeanType.values()) {
            if (type.getClazz().equals(clazz)) {
                return type;
            }
        }
        return null;
    }

    public abstract Object wrapper(Object source) throws ActionException;
}
