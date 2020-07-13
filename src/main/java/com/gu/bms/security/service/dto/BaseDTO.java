package com.gu.bms.security.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author FastG
 * @date 2020/7/13 17:24
 */
@Getter
@Setter
@ToString
public class BaseDTO implements Serializable {

    private String createBy;

    private String updatedBy;

    private Timestamp createTime;

    private Timestamp updateTime;


}
