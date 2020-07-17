package com.gu.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.SysRolesMenusDao;
import com.gu.business.user.entity.SysRolesMenusEntity;
import com.gu.business.user.service.SysRolesMenusService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysRolesMenusService")
public class SysRolesMenusServiceImpl extends ServiceImpl<SysRolesMenusDao, SysRolesMenusEntity> implements SysRolesMenusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysRolesMenusEntity> page = this.page(
                new Query<SysRolesMenusEntity>().getPage(params),
                new QueryWrapper<SysRolesMenusEntity>()
        );

        return new PageUtils(page);
    }

}