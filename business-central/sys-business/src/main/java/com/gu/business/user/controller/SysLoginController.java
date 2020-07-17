package com.gu.business.user.controller;

import com.gu.business.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FastG
 * @date 2020/7/17 15:47
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysUserService sysUserService;

}
