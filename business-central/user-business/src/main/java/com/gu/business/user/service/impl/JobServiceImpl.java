package com.gu.business.user.service.impl;

import com.gu.business.user.domain.Job;
import com.gu.business.user.service.JobService;
import com.gu.common.domain.dto.JobDto;
import com.gu.common.domain.dto.JobQueryCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Service
public class JobServiceImpl implements JobService {

    @Override
    public JobDto findById(Long id) {
        return null;
    }

    @Override
    public void create(Job resources) {

    }

    @Override
    public void update(Job resources) {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    @Override
    public Map<String, Object> queryAll(JobQueryCriteria criteria, Pageable pageable) {
        return null;
    }

    @Override
    public List<JobDto> queryAll(JobQueryCriteria criteria) {
        return null;
    }

    @Override
    public void download(List<JobDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public void verification(Set<Long> ids) {

    }
}