package com.ibm.tw.ibmb.biz.component.i18n;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class I18nResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    @Override
    protected MessageFormat createMessageFormat(String msg, Locale locale) {
        msg = msg.replaceAll("'", "''");
        return new MessageFormat(msg, locale);
    }
}
