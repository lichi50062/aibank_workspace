package com.tfb.aibank.biz.component.identity;

import java.lang.reflect.Method;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component
public class IdentityCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(String.valueOf(param));
        }
        return DigestUtils.sha256Hex(sb.toString());
    }

}
