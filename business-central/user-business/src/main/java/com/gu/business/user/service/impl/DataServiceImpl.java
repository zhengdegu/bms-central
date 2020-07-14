package com.gu.business.user.service.impl;


import com.gu.business.user.service.DataService;
import com.gu.common.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Service
public class DataServiceImpl implements DataService {

    @Override
    public List<Long> getDeptIds(UserDto user) {
        return null;
    }
}
