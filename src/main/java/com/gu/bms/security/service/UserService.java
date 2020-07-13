package com.gu.bms.security.service;

import com.gu.bms.security.domain.User;
import com.gu.bms.security.service.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户接口
 * @author FastG
 * @date 2020/7/13 17:59
 */
public interface UserService {

    /**
     * 根据ID查询
     * @param id ID
     * @return /
     */
    UserDto findById(long id);

    /**
     * 新增用户
     * @param resources /
     */
    void create(User resources);

    /**
     * 编辑用户
     * @param resources /
     */
    void update(User resources);

    /**
     * 删除用户
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 根据用户名查询
     * @param userName /
     * @return /
     */
    UserDto findByName(String userName);

    /**
     * 修改密码
     * @param username 用户名
     * @param encryptPassword 密码
     */
    void updatePass(String username, String encryptPassword);

    /**
     * 修改头像
     * @param file 文件
     * @return /
     */
    Map<String, String> updateAvatar(MultipartFile file);

    /**
     * 修改邮箱
     * @param username 用户名
     * @param email 邮箱
     */
    void updateEmail(String username, String email);

}
