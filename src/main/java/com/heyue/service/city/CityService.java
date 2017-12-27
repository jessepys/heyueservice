package com.heyue.service.city;

import com.heyue.service.city.dao.CityDao;
import com.heyue.service.city.domain.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jessepi on 4/16/16.
 */
@Service
@CacheConfig(cacheNames = "memcached")
public class CityService {
    private static final Logger logger = LoggerFactory.getLogger(CityService.class);

    @Autowired private CityDao cityDao;

    @Transactional(readOnly = true)
    @Cacheable(key = "#root.targetClass + #root.methodName")
    public List<City> findAll() {
        logger.info(" get all city: test");
        return cityDao.findAllCity();
    }
}
