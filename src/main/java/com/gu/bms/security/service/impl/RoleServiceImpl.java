package com.gu.bms.security.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.PageUtil;
import com.gu.bms.common.exception.BadRequestException;
import com.gu.bms.security.domain.Role;
import com.gu.bms.security.repository.RoleRepository;
import com.gu.bms.security.repository.UserRepository;
import com.gu.bms.security.service.RoleService;
import com.gu.bms.security.service.dto.RoleDto;
import com.gu.bms.security.service.dto.RoleQueryCriteria;
import com.gu.bms.security.service.dto.RoleSmallDto;
import com.gu.bms.security.service.dto.UserDto;
import com.gu.bms.security.service.mapstruce.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zheng Jie
 * @date 2018-12-03
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Override
    public List<RoleDto> queryAll() {
        return null;
    }

    @Override
    public RoleDto findById(long id) {
        return null;
    }

    @Override
    public void create(Role resources) {

    }

    @Override
    public void update(Role resources) {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    @Override
    public List<RoleSmallDto> findByUsersId(Long id) {
        return null;
    }

    @Override
    public Integer findByRoles(Set<Role> roles) {
        return null;
    }

    @Override
    public void updateMenu(Role resources, RoleDto roleDTO) {

    }

    @Override
    public void untiedMenu(Long id) {

    }

    @Override
    public Object queryAll(RoleQueryCriteria criteria, Pageable pageable) {
        return null;
    }

    @Override
    public List<RoleDto> queryAll(RoleQueryCriteria criteria) {
        return null;
    }

    @Override
    public void download(List<RoleDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        return null;
    }

    @Override
    public void verification(Set<Long> ids) {

    }

    @Override
    public List<Role> findInMenuId(List<Long> menuIds) {
        return null;
    }
}
