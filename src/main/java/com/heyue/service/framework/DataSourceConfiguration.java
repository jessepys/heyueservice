package com.heyue.service.framework;

import com.heyue.service.Application;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by jessepi on 4/15/16.
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "oltpTransactionManager")
@EnableConfigurationProperties
public class DataSourceConfiguration {
    private static final String OLTP_PREFIX = "spring.oltp";
    private static final String ODS_PREFIX = "spring.ods";

    @Primary
    @Bean(name="oltpDataSource")
    @ConfigurationProperties(prefix = OLTP_PREFIX)
    public DataSource oltpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="odsDataSource")
    @ConfigurationProperties(prefix = ODS_PREFIX)
    public DataSource odsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oltpTransaction")
    PlatformTransactionManager oltpTransactionManager() {
        return new JpaTransactionManager(oltpEntityManagerFactory().getObject());
    }

    @Bean(name = "entityManagerFactory")
    LocalContainerEntityManagerFactoryBean oltpEntityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(oltpDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(Application.class.getPackage().getName());

        return factoryBean;
    }

}
