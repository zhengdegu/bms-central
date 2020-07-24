package com.gu.business.user.service.impl;

/**
 * @author FastG
 * @date 2020/7/21 9:21
 */

//public class UserDetailsServiceImpl implements UserDetailsService {
//
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        UserDto userDto = sysUserService.findByName(username);
//        if (userDto == null) {
//            throw new UsernameNotFoundException("用户名或密码错误");
//        }
//        List<GrantedAuthority> grantedAuthorities = sysUserService.mapToGrantedAuthorities(userDto);
//        return new UserDetailsDto(userDto,
//                grantedAuthorities);
//    }
//}
