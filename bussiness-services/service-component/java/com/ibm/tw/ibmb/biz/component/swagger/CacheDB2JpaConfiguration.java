package com.ibm.tw.ibmb.biz.component.swagger;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.tfb.aibank.biz.*.repository.cachedb2" }, entityManagerFactoryRef = "cachedb2EntityManagerFactory", transactionManagerRef = "cachedb2TransactionManager")
@EntityScan("com.tfb.aibank.biz.*.repository.cachedb2.entities")
@ConditionalOnProperty(name = "spring.datasource.cachedb2.enabled", havingValue = "true", matchIfMissing = false)
public class CacheDB2JpaConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.cachedb2.hikari")
    DataSourceProperties cachedb2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.cachedb2.hikari")
    DataSource cachedb2DataSource() {
        DataSourceProperties cachedb2DataSourceProperties = cachedb2DataSourceProperties();
        return cachedb2DataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean cachedb2EntityManagerFactory(@Qualifier("cachedb2DataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).packages("com.tfb.aibank.biz.*.repository.cachedb2.entities").persistenceUnit("CacheDB2").build();
    }

    @Bean
    PlatformTransactionManager cachedb2TransactionManager(@Qualifier("cachedb2EntityManagerFactory") LocalContainerEntityManagerFactoryBean cachedb2EntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(cachedb2EntityManagerFactory.getObject()));
    }

    @Bean
    JdbcTemplate cachedb2JdbcTemplate(@Qualifier("cachedb2DataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}