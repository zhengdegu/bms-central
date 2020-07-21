package com.gu.business.user;

import com.gu.business.user.service.SysUserService;
import com.gu.common.domain.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author FastG
 * @date 2020/7/21 14:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserServiceTest {

    @Autowired
    private SysUserService  userService;

    @Test
    public  void  testsFindByName() {
        UserDto admin = userService.findByName("admin");
        Assert.assertNotNull(admin);
    }
}
