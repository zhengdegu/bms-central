package com.gu.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

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

    private Timestamp createTime;

    private Timestamp updateTime;

}
