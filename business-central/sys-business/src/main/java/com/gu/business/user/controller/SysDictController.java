package com.gu.business.user.controller;

import com.gu.business.user.entity.SysDictEntity;
import com.gu.business.user.service.SysDictService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 数据字典
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@RestController
@RequestMapping("user/sysdict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysdict:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDictService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dictId}")
    //@RequiresPermissions("user:sysdict:info")
    public R info(@PathVariable("dictId") Long dictId) {
        SysDictEntity sysDict = sysDictService.getById(dictId);

        return R.ok().put("sysDict", sysDict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysdict:save")
    public R save(@RequestBody SysDictEntity sysDict) {
        sysDictService.save(sysDict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysdict:update")
    public R update(@RequestBody SysDictEntity sysDict) {
        sysDictService.updateById(sysDict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysdict:delete")
    public R delete(@RequestBody Long[] dictIds) {
        sysDictService.removeByIds(Arrays.asList(dictIds));

        return R.ok();
    }

}
