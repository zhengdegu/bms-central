package com.gu.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@Data
@TableName("sys_quartz_log")
public class SysQuartzLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long logId;
    /**
     *
     */
    private String beanName;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private String cronExpression;
    /**
     *
     */
    private String exceptionDetail;
    /**
     *
     */
    private Boolean isSuccess;
    /**
     *
     */
    private String jobName;
    /**
     *
     */
    private String methodName;
    /**
     *
     */
    private String params;
    /**
     *
     */
    private Long time;

}
