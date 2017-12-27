package com.heyue.service.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DataSourceManager {
    private Logger logger = LoggerFactory.getLogger(DataSourceManager.class);

    @Autowired
    private DataSource oltpDataSource;
    @Autowired
    private DataSource odsDataSource;
    private static boolean isOltpDataSourceAvailable = true;
    private static boolean isOdsDataSourceAvailable = true;
    private JdbcTemplate oltpJdbcTemplate;
    private JdbcTemplate odsJdbcTemplate;

    @PostConstruct
    private void afterConstruct(){
        oltpJdbcTemplate = new JdbcTemplate(oltpDataSource);
        odsJdbcTemplate = new JdbcTemplate(odsDataSource);
    }
    
    public DataSource getOltpDataSource() {
        if (isOdsDataSourceAvailable) {
            return oltpDataSource;
        }
        
        return null;
    }

    public DataSource getOdsDataSource() {
        if (isOdsDataSourceAvailable) {
            return odsDataSource;
        } else if (isOltpDataSourceAvailable) {
            return oltpDataSource;
        } else {
            
            logger.error("there is no available datasource");
            return null;
        }
    }
    
    @Scheduled(cron="0 0/2 * * * *")
    public void checkDataSource() {
        isOdsDataSourceAvailable = isDataSourceAvailable(odsDataSource, "ods datasource");
        isOltpDataSourceAvailable = isDataSourceAvailable(oltpDataSource, "oltp datasource");
    }

    public boolean isDataSourceAvailable(DataSource datasource, String sourceName) {
        logger.debug("check the db is available");
        try {
            odsDataSource.getConnection();
        } catch (SQLException e) {
            logger.error(sourceName + " get connection error:", e.getMessage());
            return false;
        }
        
        return true;
    }

    public static boolean isOltpDataSourceAvailable() {
        return isOltpDataSourceAvailable;
    }

    public static boolean isOdsDataSourceAvailable() {
        return isOdsDataSourceAvailable;
    }

    public JdbcTemplate getOltpJdbcTemplate() {
        return oltpJdbcTemplate;
    }

    public JdbcTemplate getOdsJdbcTemplate() {
        if (isOdsDataSourceAvailable) {
            return odsJdbcTemplate;
        } else {
            return oltpJdbcTemplate;
        }
    }
    
    
}
