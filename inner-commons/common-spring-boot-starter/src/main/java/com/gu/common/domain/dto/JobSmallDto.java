package com.gu.common.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author FastG
 * @date 2020/7/14 16:45
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class JobSmallDto implements Serializable {

    private Long id;

    private String name;
}