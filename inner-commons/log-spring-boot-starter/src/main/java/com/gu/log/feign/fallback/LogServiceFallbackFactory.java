package com.gu.log.feign.fallback;

import com.gu.log.domain.LogVo;
import com.gu.log.feign.LogService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

/**
 * @author FastG
 * @date 2020/8/7 14:24
 */
@Slf4j
public class LogServiceFallbackFactory implements FallbackFactory<LogService> {
    @Override
    public LogService create(Throwable throwable) {
        return new LogService() {
            @Override
            public ResponseEntity<Object> saveLog(LogVo logVo) {
                log.error("远程调用日志服务保存日志发生异常",throwable);
                return null;
            }
        };
    }
}
