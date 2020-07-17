package com.gu.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.SysQuartzLogDao;
import com.gu.business.user.entity.SysQuartzLogEntity;
import com.gu.business.user.service.SysQuartzLogService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysQuartzLogService")
public class SysQuartzLogServiceImpl extends ServiceImpl<SysQuartzLogDao, SysQuartzLogEntity> implements SysQuartzLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysQuartzLogEntity> page = this.page(
                new Query<SysQuartzLogEntity>().getPage(params),
                new QueryWrapper<SysQuartzLogEntity>()
        );

        return new PageUtils(page);
    }

}