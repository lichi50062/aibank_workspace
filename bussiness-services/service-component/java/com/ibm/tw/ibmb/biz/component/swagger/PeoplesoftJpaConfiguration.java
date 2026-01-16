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
@EnableJpaRepositories(basePackages = { "com.tfb.aibank.biz.*.repository.peoplesoft" }, entityManagerFactoryRef = "peoplesoftEntityManagerFactory", transactionManagerRef = "peoplesoftTransactionManager")
@EntityScan("com.tfb.aibank.biz.*.repository.peoplesoft.entities")
@ConditionalOnProperty(name = "spring.datasource.peoplesoft.enabled", havingValue = "true", matchIfMissing = false)
public class PeoplesoftJpaConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.peoplesoft.hikari")
    DataSourceProperties peoplesoftDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.peoplesoft.hikari")
    DataSource peoplesoftDataSource() {
        DataSourceProperties peoplesoftDataSourceProperties = peoplesoftDataSourceProperties();
        return peoplesoftDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean peoplesoftEntityManagerFactory(@Qualifier("peoplesoftDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).packages("com.tfb.aibank.biz.*.repository.peoplesoft.entities").persistenceUnit("PEOPLESOFT").build();
    }

    @Bean
    PlatformTransactionManager peoplesoftTransactionManager(@Qualifier("peoplesoftEntityManagerFactory") LocalContainerEntityManagerFactoryBean peoplesoftEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(peoplesoftEntityManagerFactory.getObject()));
    }

    @Bean
    JdbcTemplate peoplesoftJdbcTemplate(@Qualifier("peoplesoftDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}