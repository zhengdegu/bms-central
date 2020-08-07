package com.gu.log.aspect;

import cn.hutool.json.JSONObject;
import com.gu.common.utils.HeaderUtil;
import com.gu.common.utils.StringUtils;
import com.gu.log.domain.LogVo;
import com.gu.log.feign.LogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author FastG
 * @date 2020/8/6 13:50
 */
@Component
@Aspect
@RequiredArgsConstructor
public class LogAspect {

    private final LogService logService;
    /**
     * 存储执行时间
     */
    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.gu.log.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        HttpServletRequest request = this.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.gu.log.annotation.Log aopLog = method.getAnnotation(com.gu.log.annotation.Log.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        List<Object> argValues = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        //参数名称
        for (Object argValue : argValues) {
            params.append(argValue).append(" ");
        }

        LogVo log = new LogVo();

        // 描述
        log.setDescription(aopLog.value());
        log.setRequestIp(StringUtils.getIp(request));

        String loginPath = "login";
        String username = HeaderUtil.extractUser();
        if (loginPath.equals(signature.getName())) {
            username = new JSONObject(argValues.get(0)).get("username").toString();
        }
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        log.setBrowser(StringUtils.getBrowser(request));
        log.setTime(System.currentTimeMillis() - currentTime.get());
        log.setLogType("INFO");
        currentTime.remove();
        logService.saveLog(log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        currentTime.set(System.currentTimeMillis());
        HttpServletRequest request = this.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.gu.log.annotation.Log aopLog = method.getAnnotation(com.gu.log.annotation.Log.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        List<Object> argValues = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        //参数名称
        for (Object argValue : argValues) {
            params.append(argValue).append(" ");
        }

        LogVo log = new LogVo();

        // 描述
        log.setDescription(aopLog.value());
        log.setRequestIp(StringUtils.getIp(request));

        String loginPath = "login";
        String username = HeaderUtil.extractUser();
        if (loginPath.equals(signature.getName())) {
            username = new JSONObject(argValues.get(0)).get("username").toString();
        }
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        log.setBrowser(StringUtils.getBrowser(request));
        log.setTime(System.currentTimeMillis() - currentTime.get());
        log.setExceptionDetail(getStackTrace(e).getBytes());
        log.setLogType("ERROR");
        currentTime.remove();
        logService.saveLog(log);
    }

    public HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }


}
