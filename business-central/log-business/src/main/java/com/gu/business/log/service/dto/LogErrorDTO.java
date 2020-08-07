
package com.gu.business.log.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class LogErrorDTO implements Serializable {

    private Long id;

    private String username;

    private String description;

    private String method;

    private String params;

    private String browser;

    private String requestIp;

    private String address;

    private Timestamp createTime;
}