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
package com.ibm.tw.ibmb.biz.component.vendor;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.BatchWriting;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class EclipseLinkJpaConfiguration extends JpaBaseConfiguration {

    protected EclipseLinkJpaConfiguration(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
        super(dataSource, properties, jtaTransactionManager);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PersistenceUnitProperties.WEAVING, detectWeavingMode());
        // boolean available = InstrumentationLoadTimeWeaver.isInstrumentationAvailable();
        // map.put(PersistenceUnitProperties.WEAVING, "true");
        // map.put(PersistenceUnitProperties.WEAVING_LAZY, "true");
        // map.put(PersistenceUnitProperties.DDL_GENERATION, "drop-and-create-tables");
        map.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
        map.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINE_LABEL);
        map.put(PersistenceUnitProperties.SHARED_CACHE_MODE, "NONE");
        // map.put(PersistenceUnitProperties.TARGET_DATABASE_PROPERTIES, "");

        // EclipseLink Shared Cache Mode
        // https://wiki.eclipse.org/EclipseLink/Examples/JPA/Caching
        // https://wiki.eclipse.org/EclipseLink/FAQ/How_to_disable_the_shared_cache%3F
        return map;
    }

    /**
     * detect Weaving Instruction
     *
     * @return
     */
    private String detectWeavingMode() {
        return InstrumentationLoadTimeWeaver.isInstrumentationAvailable() ? "true" : "static";
    }
}
