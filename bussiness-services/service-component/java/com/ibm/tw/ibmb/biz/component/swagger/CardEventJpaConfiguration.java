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
@EnableJpaRepositories(basePackages = { "com.tfb.aibank.biz.*.repository.cardevent" }, entityManagerFactoryRef = "cardeventEntityManagerFactory", transactionManagerRef = "cardeventTransactionManager")
@EntityScan("com.tfb.aibank.biz.*.repository.cardevent.entities")
@ConditionalOnProperty(name = "spring.datasource.cardevent.enabled", havingValue = "true", matchIfMissing = false)
public class CardEventJpaConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.cardevent.hikari")
    DataSourceProperties cardeventDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.cardevent.hikari")
    DataSource cardeventDataSource() {
        DataSourceProperties cardeventDataSourceProperties = cardeventDataSourceProperties();
        return cardeventDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean cardeventEntityManagerFactory(@Qualifier("cardeventDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).packages("com.tfb.aibank.biz.*.repository.cardevent.entities").persistenceUnit("CARDEVENT").build();
    }

    @Bean
    PlatformTransactionManager cardeventTransactionManager(@Qualifier("cardeventEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardeventEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(cardeventEntityManagerFactory.getObject()));
    }
    
    @Bean
    JdbcTemplate cardeventJdbcTemplate(@Qualifier("cardeventDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}