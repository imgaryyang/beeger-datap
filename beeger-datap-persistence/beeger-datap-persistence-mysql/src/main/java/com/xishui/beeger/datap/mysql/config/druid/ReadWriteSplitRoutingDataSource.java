package com.xishui.beeger.datap.mysql.config.druid;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 */
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
