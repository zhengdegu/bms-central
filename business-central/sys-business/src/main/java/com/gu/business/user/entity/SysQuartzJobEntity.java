package com.gu.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@Data
@TableName("sys_quartz_job")
public class SysQuartzJobEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long jobId;
    /**
     * Spring Bean名称
     */
    private String beanName;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 状态：1暂停、0启用
     */
    private Boolean isPause;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * 备注
     */
    private String description;
    /**
     * 负责人
     */
    private String personInCharge;
    /**
     * 报警邮箱
     */
    private String email;
    /**
     * 子任务ID
     */
    private String subTask;
    /**
     * 任务失败后是否暂停
     */
    private Boolean pauseAfterFailure;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
