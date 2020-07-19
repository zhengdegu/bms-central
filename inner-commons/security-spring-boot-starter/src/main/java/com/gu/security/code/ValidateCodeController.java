package com.gu.security.code;


import cn.hutool.core.util.IdUtil;
import com.gu.common.properties.SecurityConstants;
import com.gu.common.properties.SecurityProperties;
import com.gu.common.utils.R;

import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ValidateCodeController {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSevice validateCodeSevice;

    /**
     * 创建验证码
     *
     * @throws Exception
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL)
    public R createCode()
            throws Exception {
        // 获取运算的结果
        Captcha captcha = securityProperties.getValidateCode().getCaptcha();
        String uuid = IdUtil.simpleUUID();
        // 保存
        validateCodeSevice.saveImageCode(uuid, captcha.text());
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return R.ok(imgResult);
    }

}
