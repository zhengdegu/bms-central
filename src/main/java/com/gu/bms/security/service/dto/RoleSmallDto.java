package com.gu.bms.security.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleSmallDto implements Serializable {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
