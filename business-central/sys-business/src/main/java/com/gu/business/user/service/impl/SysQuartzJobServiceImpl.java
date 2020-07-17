package com.gu.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.SysQuartzJobDao;
import com.gu.business.user.entity.SysQuartzJobEntity;
import com.gu.business.user.service.SysQuartzJobService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysQuartzJobService")
public class SysQuartzJobServiceImpl extends ServiceImpl<SysQuartzJobDao, SysQuartzJobEntity> implements SysQuartzJobService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysQuartzJobEntity> page = this.page(
                new Query<SysQuartzJobEntity>().getPage(params),
                new QueryWrapper<SysQuartzJobEntity>()
        );

        return new PageUtils(page);
    }

}