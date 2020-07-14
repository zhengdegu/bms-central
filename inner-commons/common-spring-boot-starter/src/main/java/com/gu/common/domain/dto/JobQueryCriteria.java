package com.gu.common.domain.dto;

import com.gu.common.annotation.Query;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author FastG
 * @date 2020/7/14 16:45
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}