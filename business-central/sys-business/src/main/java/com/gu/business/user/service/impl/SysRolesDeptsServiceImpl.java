package com.gu.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.SysRolesDeptsDao;
import com.gu.business.user.entity.SysRolesDeptsEntity;
import com.gu.business.user.service.SysRolesDeptsService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysRolesDeptsService")
public class SysRolesDeptsServiceImpl extends ServiceImpl<SysRolesDeptsDao, SysRolesDeptsEntity> implements SysRolesDeptsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysRolesDeptsEntity> page = this.page(
                new Query<SysRolesDeptsEntity>().getPage(params),
                new QueryWrapper<SysRolesDeptsEntity>()
        );

        return new PageUtils(page);
    }

}