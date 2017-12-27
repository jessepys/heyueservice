package com.heyue.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by jessepi on 4/9/16.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, TransactionAutoConfiguration.class})
@ConfigurationProperties(locations = "classpath:application.properties")
@EnableScheduling
@EnableJpaRepositories
@ComponentScan(value = {"com.heyue.service"})
@EnableCaching(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
