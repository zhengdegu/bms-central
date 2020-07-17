package com.gu.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.SysDeptDao;
import com.gu.business.user.entity.SysDeptEntity;
import com.gu.business.user.service.SysDeptService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDeptEntity> page = this.page(
                new Query<SysDeptEntity>().getPage(params),
                new QueryWrapper<SysDeptEntity>()
        );

        return new PageUtils(page);
    }

}