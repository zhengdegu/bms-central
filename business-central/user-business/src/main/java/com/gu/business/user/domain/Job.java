package com.gu.business.user.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "sys_job")
public class Job extends BaseEntity implements Serializable {

    @Id
    @Column(name = "job_id")
    @NotNull
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "岗位名称")
    private String name;

    @NotNull
    @ApiModelProperty(value = "岗位排序")
    private Long jobSort;

    @NotNull
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

}