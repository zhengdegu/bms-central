package com.gu.log.feign;

import com.gu.common.constat.CommonConstants;
import com.gu.common.utils.R;
import com.gu.log.domain.LogVo;
import com.gu.log.feign.fallback.LogServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

;

/**
 * 日志feign远程调用服务
 *
 * @author FastG
 * @date 2020/8/7 10:43
 */
@FeignClient(name = CommonConstants.LOG_BUSINESS,fallbackFactory = LogServiceFallbackFactory.class, decode404 = true)
public interface LogService {

    /**
     * 保存日志接口
     *
     * @return
     */
    @PostMapping("/api/logs/save")
    ResponseEntity<Object> saveLog(@RequestBody LogVo log);
}
