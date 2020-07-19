package com.gu.business.user.controller;

//import cn.hutool.core.collection.CollectionUtil;
//import com.gu.business.user.domain.vo.UserPassVo;
//import com.gu.business.user.service.*;
//import com.gu.common.constat.CommonConstants;
//import com.gu.common.domain.dto.UserDto;
//import com.gu.common.domain.dto.UserQueryCriteria;
//import com.gu.common.exception.BadRequestException;
//import com.gu.common.model.CodeEnum;
//import com.gu.common.utils.PageUtil;
//import com.gu.common.utils.RsaUtils;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.ObjectUtils;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.Set;
//
///**
// * 用户管理
// *
// * @author FastG
// * @date 2020/7/14 14:33
// */
//@Api(tags = "系统：用户管理")
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private DeptService deptService;
//    @Autowired
//    private DataService dataService;
//    @Autowired
//    private SecurityProperties securityProperties;
//    @Autowired
//    private VerifyService verifyService;
//    @ApiOperation(value = "根据用户名查询用户")
//    @GetMapping(value = "/{username}")
//    public ResponseEntity<Object> selectByUsername(@PathVariable String username) {
//        UserDto name = userService.findByName(username);
//        return new ResponseEntity<>(name, HttpStatus.OK);
//    }
//
//    @ApiOperation("新增用户")
//    @PostMapping
//    public ResponseEntity<Object> create(@Validated @RequestBody UserDto userDto) {
//        userDto.setPassword(passwordEncoder.encode(CommonConstants.DEF_USER_PASSWORD));
//        userService.create(userDto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @ApiOperation("修改用户")
//    @PutMapping
//    public ResponseEntity<Object> update(@Validated @RequestBody UserDto userDto) {
//        userService.update(userDto);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @ApiOperation("删除用户")
//    @DeleteMapping
//    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
//        //TODO
////        for (Long id : ids) {
////            Integer currentLevel =  Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
////            Integer optLevel =  Collections.min(roleService.findByUsersId(id).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
////            if (currentLevel > optLevel) {
////                throw new BadRequestException("角色权限不足，不能删除：" + userService.findById(id).getUsername());
////            }
////        }
//        userService.delete(ids);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @ApiOperation("导出用户数据")
//    @GetMapping(value = "/download")
//    public void download(HttpServletResponse response, UserQueryCriteria criteria) throws IOException {
//        userService.download(userService.queryAll(criteria), response);
//    }
//
//    @ApiOperation("查询用户")
//    @GetMapping
//    public ResponseEntity<Object> query(UserQueryCriteria criteria, Pageable pageable) {
//        if (!ObjectUtils.isEmpty(criteria.getDeptId())) {
//            criteria.getDeptIds().add(criteria.getDeptId());
//            criteria.getDeptIds().addAll(deptService.getDeptChildren(criteria.getDeptId(),
//                    deptService.findByPid(criteria.getDeptId())));
//        }
//        //TODO
//        // 进一步过滤数据权限
//        List<Long> dataScopes = null;
//        // criteria.getDeptIds() 不为空并且数据权限不为空则取交集
//        if (!CollectionUtils.isEmpty(criteria.getDeptIds()) && !CollectionUtils.isEmpty(dataScopes)) {
//            // 取交集
//            criteria.getDeptIds().retainAll(dataScopes);
//            if (!CollectionUtil.isEmpty(criteria.getDeptIds())) {
//                return new ResponseEntity<>(userService.queryAll(criteria, pageable), HttpStatus.OK);
//            }
//        } else {
//            // 否则取并集
//            criteria.getDeptIds().addAll(dataScopes);
//            return new ResponseEntity<>(userService.queryAll(criteria, pageable), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(PageUtil.toPage(null, 0), HttpStatus.OK);
//    }
//
//    @ApiOperation("修改密码")
//    @PostMapping(value = "/updatePass")
//    public ResponseEntity<Object> updatePass(@RequestBody UserPassVo passVo) throws Exception {
//        String oldPass = RsaUtils.decryptByPrivateKey(securityProperties.getRsa().getPrivateKey(), passVo.getOldPass());
//        String newPass = RsaUtils.decryptByPrivateKey(securityProperties.getRsa().getPrivateKey(), passVo.getNewPass());
//        //TODO
//        // UserDto user = userService.findByName(SecurityUtils.getCurrentUsername());
//        UserDto user = null;
//        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
//            throw new BadRequestException("修改失败，旧密码错误");
//        }
//        if (passwordEncoder.matches(newPass, user.getPassword())) {
//            throw new BadRequestException("新密码不能与旧密码相同");
//        }
//        userService.updatePass(user.getUsername(), passwordEncoder.encode(newPass));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @ApiOperation("修改头像")
//    @PostMapping(value = "/updateAvatar")
//    public ResponseEntity<Object> updateAvatar(@RequestParam MultipartFile avatar) {
//        //TODO
//        return new ResponseEntity<>(userService.updateAvatar(avatar), HttpStatus.OK);
//    }
//
//    @ApiOperation("修改用户：个人中心")
//    @PutMapping(value = "center")
//    public ResponseEntity<Object> center(@RequestBody UserDto resources) {
//        userService.updateCenter(resources);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @ApiOperation("修改邮箱")
//    @PostMapping(value = "/updateEmail/{code}")
//    public ResponseEntity<Object> updateEmail(@PathVariable String code, @RequestBody UserDto user) throws Exception {
//        String password = RsaUtils.decryptByPrivateKey(securityProperties.getRsa().getPrivateKey(), user.getPassword());
//        //TODO
//        UserDto userDto = userService.findByName(null);
//        if (!passwordEncoder.matches(password, userDto.getPassword())) {
//            throw new BadRequestException("密码错误");
//        }
//        verifyService.validated(CodeEnum.EMAIL_RESET_EMAIL_CODE.getKey() + user.getEmail(), code);
//        userService.updateEmail(userDto.getUsername(), user.getEmail());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//    /**
//     * 解析head中的token
//     */
//    private String extractHeaderToken() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Enumeration<String> headers = request.getHeaders(CommonConstants.TOKEN_HEADER);
//        while (headers.hasMoreElements()) {
//            String value = headers.nextElement();
//            if ((value.toLowerCase().startsWith(CommonConstants.BEARER_TYPE))) {
//                String authHeaderValue = value.substring(CommonConstants.BEARER_TYPE.length()).trim();
//                int commaIndex = authHeaderValue.indexOf(',');
//                if (commaIndex > 0) {
//                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
//                }
//                return authHeaderValue;
//            }
//        }
//        return null;
//    }
//}
