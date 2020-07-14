package com.gu.business.user.service.impl;


import com.gu.business.user.domain.Dept;
import com.gu.business.user.service.DeptService;
import com.gu.common.domain.dto.DeptDto;
import com.gu.common.domain.dto.DeptQueryCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Override
    public List<DeptDto> queryAll(DeptQueryCriteria criteria, Boolean isQuery) throws Exception {
        return null;
    }

    @Override
    public DeptDto findById(Long id) {
        return null;
    }

    @Override
    public void create(Dept resources) {

    }

    @Override
    public void update(Dept resources) {

    }

    @Override
    public void delete(Set<DeptDto> deptDtos) {

    }

    @Override
    public List<Dept> findByPid(long pid) {
        return null;
    }

    @Override
    public Set<Dept> findByRoleId(Long id) {
        return null;
    }

    @Override
    public void download(List<DeptDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public Set<DeptDto> getDeleteDepts(List<Dept> deptList, Set<DeptDto> deptDtos) {
        return null;
    }

    @Override
    public List<DeptDto> getSuperior(DeptDto deptDto, List<Dept> depts) {
        return null;
    }

    @Override
    public Object buildTree(List<DeptDto> deptDtos) {
        return null;
    }

    @Override
    public List<Long> getDeptChildren(Long deptId, List<Dept> deptList) {
        return null;
    }

    @Override
    public void verification(Set<DeptDto> deptDtos) {

    }
}