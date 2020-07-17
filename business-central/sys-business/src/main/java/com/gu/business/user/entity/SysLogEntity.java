package com.gu.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@Data
@TableName("sys_log")
public class SysLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long logId;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private String logType;
    /**
     *
     */
    private String method;
    /**
     *
     */
    private String params;
    /**
     *
     */
    private String requestIp;
    /**
     *
     */
    private Long time;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String browser;
    /**
     *
     */
    private String exceptionDetail;
    /**
     *
     */
    private Date createTime;

}
