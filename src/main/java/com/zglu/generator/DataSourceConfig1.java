package com.zglu.generator;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

/**
 * @author zglu
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.zglu.generator.target"},
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean1",
        transactionManagerRef = "platformTransactionManager1")
@EnableTransactionManagement
public class DataSourceConfig1 {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource1")
    public DataSourceProperties dataSourceProperties1() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource dataSource1() {
        return dataSourceProperties1().initializeDataSourceBuilder().build();
    }

    private Properties jpaProperties() {
        final Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.setProperty("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        jpaProperties.setProperty("hibernate.show_sql", "true");
        return jpaProperties;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean1() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource1());
        factoryBean.setJpaProperties(jpaProperties());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("com.zglu.generator.target");
        return factoryBean;
    }

    @Bean
    @Primary
    public PlatformTransactionManager platformTransactionManager1() {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean1().getObject()));
    }

}
