package com.heyue.service.framework;

import com.google.code.ssm.Cache;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.aop.CacheBase;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.providers.xmemcached.XMemcachedConfiguration;
import com.google.code.ssm.spring.SSMCache;
import com.google.code.ssm.spring.SSMCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import org.springframework.context.annotation.Configuration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jessepi on 4/18/16.
 */
@Configuration
public class MemedCacheConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    @Lazy
    public CacheBase cacheBase() {
        return new CacheBase();
    }

    @Bean
    @Lazy
    public Cache defaultCache() {
        try {
            return cacheFactory().getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Lazy
    @DependsOn("cacheBase")
    public CacheFactory cacheFactory() {
        CacheFactory cacheFactory = new CacheFactory();
        cacheFactory.setCacheName(environment.getProperty("spring.memcached.name"));
        cacheFactory.setCacheClientFactory(new MemcacheClientFactoryImpl());


        CacheConfiguration cacheConfiguration = createCacheConfiguration();
        cacheFactory.setAddressProvider(new DefaultAddressProvider(environment.getProperty("spring.memcached.url")));
        cacheFactory.setConfiguration(cacheConfiguration);

        return cacheFactory;
    }


    private CacheConfiguration createCacheConfiguration() {
        CacheConfiguration cacheConfiguration = new XMemcachedConfiguration();
        cacheConfiguration.setConsistentHashing("true".equals(environment.getProperty("spring.memcached.consistenthashing")));
        cacheConfiguration.setUseBinaryProtocol(true);
        cacheConfiguration.setUseNameAsKeyPrefix(true);

        return cacheConfiguration;
    }

    @Bean(name = "cacheManager")
    @Lazy
    public CacheManager cacheManager() {
        Set<SSMCache> ssmCacheSet = new HashSet<>();
        SSMCache ssmCache = new SSMCache(defaultCache(),
                Integer.valueOf(environment.getProperty("spring.memcached.expiration")),
                "true".equals(environment.getProperty("spring.memcached.evit.all.caches")));

        ssmCacheSet.add(ssmCache);

        SSMCacheManager ssmCacheManager = new SSMCacheManager();
        ssmCacheManager.setCaches(ssmCacheSet);
        return ssmCacheManager;
    }
}
