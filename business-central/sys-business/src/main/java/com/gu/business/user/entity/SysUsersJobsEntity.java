package com.gu.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@Data
@TableName("sys_users_jobs")
public class SysUsersJobsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    private Long userId;
    /**
     * 岗位ID
     */
    private Long jobId;

}
