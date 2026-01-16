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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.tfb.aibank.biz.*.repository.piblog" }, entityManagerFactoryRef = "piblogEntityManagerFactory", transactionManagerRef = "piblogTransactionManager")
@EntityScan("com.tfb.aibank.biz.*.repository.piblog.entities")
@ConditionalOnProperty(name = "spring.datasource.piblog.enabled", havingValue = "true", matchIfMissing = false)
public class PIBLogJpaConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.piblog.hikari")
    DataSourceProperties piblogDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.piblog.hikari")
    DataSource piblogDataSource() {
        DataSourceProperties piblogDataSourceProperties = piblogDataSourceProperties();
        return piblogDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean piblogEntityManagerFactory(@Qualifier("piblogDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).packages("com.tfb.aibank.biz.*.repository.piblog.entities").persistenceUnit("PIBLOG").build();
    }

    @Bean
    PlatformTransactionManager piblogTransactionManager(@Qualifier("piblogEntityManagerFactory") LocalContainerEntityManagerFactoryBean piblogEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(piblogEntityManagerFactory.getObject()));
    }

    @Bean
    JdbcTemplate piblogJdbcTemplate(@Qualifier("piblogDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Bean
    @Qualifier("piblogNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate piblogNamedParameterJdbcTemplate(@Qualifier("piblogDataSource") DataSource piblogDataSource) {
        return new NamedParameterJdbcTemplate(piblogDataSource);
    }
}