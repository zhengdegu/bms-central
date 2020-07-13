package com.gu.bms.security.service.impl;

import com.gu.bms.security.service.DataService;
import com.gu.bms.security.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zheng Jie
 * @website https://el-admin.vip
 * @description 数据权限服务实现
 * @date 2020-05-07
 **/
@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    @Override
    public List<Long> getDeptIds(UserDto user) {
        return null;
    }
}
