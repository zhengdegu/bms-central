package com.gu.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author FastG
 * @date 2020/7/14 16:47
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BaseDto implements Serializable {

    private String createBy;

    private String updatedBy;

    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
