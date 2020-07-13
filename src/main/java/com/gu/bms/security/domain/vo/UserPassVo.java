package com.gu.bms.security.domain.vo;

import lombok.Data;

/**
 * 修改密码的 Vo 类
 * @author FastG
 * @date 2020/7/13 14:39
 */
@Data
public class UserPassVo {

    private String oldPass;

    private String newPass;
}
