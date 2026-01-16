package com.ibm.tw.ibmb.biz.component.swagger;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.tfb.aibank.biz.*.repository" }, 
    excludeFilters = { 
        @ComponentScan.Filter(pattern = "com.tfb.aibank.biz.*.repository.*.*",
                type = FilterType.ASPECTJ)},
    entityManagerFactoryRef = "pibEntityManagerFactory", transactionManagerRef = "pibTransactionManager")
@EntityScan("com.tfb.aibank.biz.*.repository.entities")
public class PIBJpaConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.pib.hikari")
    DataSourceProperties pibDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.pib.hikari")
    DataSource pibDataSource() {
        DataSourceProperties pibDataSourceProperties = pibDataSourceProperties();
        return pibDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean pibEntityManagerFactory(@Qualifier("pibDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).packages("com.tfb.aibank.biz.*.repository.entities").persistenceUnit("PIB").build();
    }

    @Primary
    @Bean
    PlatformTransactionManager pibTransactionManager(@Qualifier("pibEntityManagerFactory") LocalContainerEntityManagerFactoryBean pibEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(pibEntityManagerFactory.getObject()));
    }
    
    @Primary
    @Bean
    JdbcTemplate pibJdbcTemplate(@Qualifier("pibDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Primary
    @Bean(name = "pibNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate pibNamedParameterJdbcTemplate(@Qualifier("pibDataSource") DataSource pibDataSource) {
        return new NamedParameterJdbcTemplate(pibDataSource);
    }
}