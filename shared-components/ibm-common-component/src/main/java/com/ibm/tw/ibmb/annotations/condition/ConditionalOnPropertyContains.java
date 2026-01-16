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
package com.ibm.tw.ibmb.annotations.condition;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.ibm.tw.ibmb.component.condition.OnPropertyContainsCondition;

import org.springframework.context.annotation.Conditional;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@Conditional(OnPropertyContainsCondition.class)
public @interface ConditionalOnPropertyContains {
    /**
     * Alias for {@link #name()}.
     * 
     * @return the names
     */
    String[] value() default {};

    /**
     * The name of the properties to test. If a prefix has been defined, it is applied to compute the full key of each property. For instance if the prefix is {@code app.config} and one value is {@code my-value}, the fully key would be
     * {@code app.config.my-value}
     * <p>
     * Use the dashed notation to specify each property, that is all lower case with a "-" to separate words (e.g. {@code my-long-property}).
     * 
     * @return the names
     */
    String[] name() default {};

    /**
     * The string representation of the expected value for the properties. If not specified, the property must <strong>not</strong> be equals to {@code false}.
     * 
     * @return the expected value
     */
    String containsValue() default "";

    boolean matchIfMissing() default false;

}
