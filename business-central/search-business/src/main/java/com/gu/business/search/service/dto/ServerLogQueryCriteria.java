package com.gu.business.search.service.dto;

import com.gu.common.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author FastG
 * @date 2020/8/10 16:06
 */
public class ServerLogQueryCriteria {

    @Query(blurry = "message,logLevel,appName,classname")
    private String blurry;

    @Query
    private String logType;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
