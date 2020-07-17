package com.gu.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.SysUsersJobsDao;
import com.gu.business.user.entity.SysUsersJobsEntity;
import com.gu.business.user.service.SysUsersJobsService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysUsersJobsService")
public class SysUsersJobsServiceImpl extends ServiceImpl<SysUsersJobsDao, SysUsersJobsEntity> implements SysUsersJobsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysUsersJobsEntity> page = this.page(
                new Query<SysUsersJobsEntity>().getPage(params),
                new QueryWrapper<SysUsersJobsEntity>()
        );

        return new PageUtils(page);
    }

}