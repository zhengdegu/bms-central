package com.gu.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.SysUsersRolesDao;
import com.gu.business.user.entity.SysUsersRolesEntity;
import com.gu.business.user.service.SysUsersRolesService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysUsersRolesService")
public class SysUsersRolesServiceImpl extends ServiceImpl<SysUsersRolesDao, SysUsersRolesEntity> implements SysUsersRolesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysUsersRolesEntity> page = this.page(
                new Query<SysUsersRolesEntity>().getPage(params),
                new QueryWrapper<SysUsersRolesEntity>()
        );

        return new PageUtils(page);
    }

}