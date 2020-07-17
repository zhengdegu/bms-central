package com.gu.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.SysJobDao;
import com.gu.business.user.entity.SysJobEntity;
import com.gu.business.user.service.SysJobService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysJobService")
public class SysJobServiceImpl extends ServiceImpl<SysJobDao, SysJobEntity> implements SysJobService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysJobEntity> page = this.page(
                new Query<SysJobEntity>().getPage(params),
                new QueryWrapper<SysJobEntity>()
        );

        return new PageUtils(page);
    }

}