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
package com.ibm.tw.ibmb.component.condition;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.annotations.condition.ConditionalOnPropertyContains;

public class OnPropertyContainsCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> map = metadata.getAllAnnotationAttributes(ConditionalOnPropertyContains.class.getName());
        String[] names = (String[]) map.getFirst("name");
        String expectValue = (String) map.getFirst("containsValue");
        Boolean matchIfMissing = (Boolean) map.getFirst("matchIfMissing");
        Assert.notEmpty(names, "property name must not be null");
        Assert.hasText(expectValue, "containsValue must not be blank");
        return determineCondition(ArrayUtils.isEmpty(names) ? null : names[0], expectValue, context.getEnvironment(), matchIfMissing);
    }

    private ConditionOutcome determineCondition(String name, String expectValue, PropertyResolver resolver, Boolean matchIfMissing) {
        if (!resolver.containsProperty(name)) {
            if (matchIfMissing != null && matchIfMissing.booleanValue()) {
                return ConditionOutcome.match();
            }
            return ConditionOutcome.noMatch("property " + name + " not found");
        }
        String value = resolver.getProperty(name);
        if (StringUtils.contains(value, expectValue)) {
            return ConditionOutcome.match("property " + name + ", value = '" + value + "',  contains '" + expectValue + "'");
        }

        return ConditionOutcome.noMatch("property " + name + ", value = '" + value + "', not contains '" + expectValue + "'");
    }

}
