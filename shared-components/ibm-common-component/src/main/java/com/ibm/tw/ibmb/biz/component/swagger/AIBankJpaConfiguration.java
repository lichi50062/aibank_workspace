package com.ibm.tw.ibmb.biz.component.swagger;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.tfb.aibank.biz.*.repository.aib" }, entityManagerFactoryRef = "aibEntityManagerFactory", transactionManagerRef = "aibTransactionManager")
@EntityScan("com.tfb.aibank.biz.*.repository.aib.entities")
public class AIBankJpaConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.aibank.hikari")
    DataSourceProperties aibDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.aibank.hikari")
    DataSource aibDataSource() {
        DataSourceProperties aibDataSourceProperties = aibDataSourceProperties();
        return aibDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean aibEntityManagerFactory(@Qualifier("aibDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).packages("com.tfb.aibank.biz.*.repository.aib.entities").persistenceUnit("AIB").build();
    }

    @Bean
    PlatformTransactionManager aibTransactionManager(@Qualifier("aibEntityManagerFactory") LocalContainerEntityManagerFactoryBean aibEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(aibEntityManagerFactory.getObject()));
    }
    
    @Bean
    JdbcTemplate aibJdbcTemplate(@Qualifier("aibDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Bean(name = "aibNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate aibNamedParameterJdbcTemplate(@Qualifier("aibDataSource") DataSource aibDataSource) {
        return new NamedParameterJdbcTemplate(aibDataSource);
    }

}