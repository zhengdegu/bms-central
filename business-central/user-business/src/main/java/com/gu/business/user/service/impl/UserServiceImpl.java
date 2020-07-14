package com.gu.business.user.service.impl;

import com.gu.business.user.domain.User;
import com.gu.business.user.repository.UserRepository;
import com.gu.business.user.service.UserService;
import com.gu.business.user.service.mapstruct.RoleMapper;
import com.gu.business.user.service.mapstruct.UserMapper;
import com.gu.common.domain.dto.JobSmallDto;
import com.gu.common.domain.dto.RoleSmallDto;
import com.gu.common.domain.dto.UserDto;
import com.gu.common.domain.dto.UserQueryCriteria;
import com.gu.common.exception.EntityExistException;
import com.gu.common.exception.EntityNotFoundException;
import com.gu.common.utils.FileUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;


    @Override
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        return null;
    }

    @Override
    public List<UserDto> queryAll(UserQueryCriteria criteria) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto findById(long id) {
        User user = userRepository.findById(id).orElseGet(User::new);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(UserDto resources) {
        if (userRepository.findByUsername(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        if (userRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        userRepository.save(userMapper.toEntity(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserDto resources) {
        User user1 = userRepository.findById(resources.getId()).orElseGet(User::new);
        User user2 = userRepository.findByUsername(resources.getUsername());
        User user3 = userRepository.findByEmail(resources.getEmail());

        if (user2 != null && !user1.getId().equals(user2.getId())) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }

        if (user3 != null && !user1.getId().equals(user3.getId())) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        // 如果用户的角色改变
        if (!resources.getRoles().equals(user1.getRoles())) {

        }
        // 如果用户名称修改
        if (!resources.getUsername().equals(user1.getUsername())) {

        }
        User user = userMapper.toEntity(resources);
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setEnabled(user.getEnabled());
        user.setRoles(user.getRoles());
        user.setDept(user.getDept());
        user.setJobs(user.getJobs());
        user.setPhone(user.getPhone());
        user.setNickName(user.getNickName());
        user.setGender(user.getGender());
        userRepository.save(user);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCenter(User resources) {
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        user.setNickName(resources.getNickName());
        user.setPhone(resources.getPhone());
        user.setGender(resources.getGender());
        userRepository.save(user);
        // 清理缓存

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            // 清理缓存
            UserDto user = findById(id);
        }
        userRepository.deleteAllByIdIn(ids);
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
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String pass) {
        userRepository.updatePass(username, pass, new Date());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateAvatar(MultipartFile multipartFile) {

        return new HashMap<String, String>(1) {{
        }};
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String username, String email) {
        userRepository.updateEmail(username, email);
    }

    @Override
    public void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserDto userDTO : queryAll) {
            List<String> roles = userDTO.getRoles().stream().map(RoleSmallDto::getName).collect(Collectors.toList());
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", userDTO.getUsername());
            map.put("角色", roles);
            map.put("部门", userDTO.getDept().getName());
            map.put("岗位", userDTO.getJobs().stream().map(JobSmallDto::getName).collect(Collectors.toList()));
            map.put("邮箱", userDTO.getEmail());
            map.put("状态", userDTO.getEnabled() ? "启用" : "禁用");
            map.put("手机号码", userDTO.getPhone());
            map.put("修改密码的时间", userDTO.getPwdResetTime());
            map.put("创建日期", userDTO.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }


}
