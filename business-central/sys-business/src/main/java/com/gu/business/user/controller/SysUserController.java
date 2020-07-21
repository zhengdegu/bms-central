package com.gu.business.user.controller;

import com.gu.business.user.entity.SysUserEntity;
import com.gu.business.user.service.SysUserService;
import com.gu.common.domain.dto.UserDto;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@RestController
//@RequestMapping("user/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysuser:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysUserService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("user:sysuser:info")
    public R info(@PathVariable("userId") Long userId) {
        SysUserEntity sysUser = sysUserService.getById(userId);

        return R.ok().put("sysUser", sysUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysuser:save")
    public R save(@RequestBody SysUserEntity sysUser) {
        sysUserService.save(sysUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysuser:update")
    public R update(@RequestBody SysUserEntity sysUser) {
        sysUserService.updateById(sysUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysuser:delete")
    public R delete(@RequestBody Long[] userIds) {
        sysUserService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

    /**
     * 根据用户名查找用户
     */
    @GetMapping("/user/{username}")
    public UserDto user(@PathVariable("username") String username){
       return sysUserService.findByName(username);
    }


    @PostMapping(value = "/user/auth")
    List<GrantedAuthority> auth(@RequestBody UserDto user){
        return  sysUserService.mapToGrantedAuthorities(user);

        }
}
