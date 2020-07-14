package com.gu.business.user.controller;

import com.gu.business.user.service.UserService;
import com.gu.common.constat.CommonConstants;
import com.gu.common.domain.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;


    @ApiOperation(value = "根据用户名查询用户实体")
    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> selectByUsername(@PathVariable String username) {
        UserDto name = userService.findByName(username);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @ApiOperation("新增用户")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(CommonConstants.DEF_USER_PASSWORD));
        userService.create(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
