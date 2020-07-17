package com.gu.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.SysDictDetailDao;
import com.gu.business.user.entity.SysDictDetailEntity;
import com.gu.business.user.service.SysDictDetailService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysDictDetailService")
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailDao, SysDictDetailEntity> implements SysDictDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDictDetailEntity> page = this.page(
                new Query<SysDictDetailEntity>().getPage(params),
                new QueryWrapper<SysDictDetailEntity>()
        );

        return new PageUtils(page);
    }

}