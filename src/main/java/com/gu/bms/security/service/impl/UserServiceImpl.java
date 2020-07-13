package com.gu.bms.security.service.impl;

import cn.hutool.core.util.PageUtil;
import com.gu.bms.common.exception.EntityNotFoundException;
import com.gu.bms.security.domain.User;
import com.gu.bms.security.repository.UserRepository;
import com.gu.bms.security.service.UserService;
import com.gu.bms.security.service.dto.JobSmallDto;
import com.gu.bms.security.service.dto.UserDto;
import com.gu.bms.security.service.mapstruce.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author FastG
 * @date 2020/7/13 18:00
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userMapper.toDto(user);
        }
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

