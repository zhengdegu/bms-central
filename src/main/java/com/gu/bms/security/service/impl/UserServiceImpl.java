package com.gu.bms.security.service.impl;

import cn.hutool.core.util.PageUtil;
import com.gu.bms.security.domain.User;
import com.gu.bms.security.repository.UserRepository;
import com.gu.bms.security.service.UserService;
import com.gu.bms.security.service.dto.JobSmallDto;
import com.gu.bms.security.service.dto.UserDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author FastG
 * @date 2020/7/13 18:00
 */
public class UserServiceImpl implements UserService {

    @Override
    public UserDto findById(long id) {
        return null;
    }

    @Override
    public void create(User resources) {

    }

    @Override
    public void update(User resources) {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    @Override
    public UserDto findByName(String userName) {
        return null;
    }

    @Override
    public void updatePass(String username, String encryptPassword) {

    }

    @Override
    public Map<String, String> updateAvatar(MultipartFile file) {
        return null;
    }

    @Override
    public void updateEmail(String username, String email) {

    }
}

