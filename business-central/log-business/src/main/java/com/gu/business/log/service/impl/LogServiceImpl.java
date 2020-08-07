package com.gu.business.log.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.gu.business.log.domain.Log;
import com.gu.business.log.repository.LogRepository;
import com.gu.business.log.service.LogService;
import com.gu.business.log.service.dto.LogQueryCriteria;
import com.gu.business.log.service.mapstruct.LogErrorMapper;
import com.gu.business.log.service.mapstruct.LogSmallMapper;
import com.gu.common.utils.FileUtil;
import com.gu.common.utils.PageUtil;
import com.gu.common.utils.QueryHelp;
import com.gu.common.utils.StringUtils;
import com.gu.log.domain.LogVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final LogErrorMapper logErrorMapper;
    private final LogSmallMapper logSmallMapper;

    @Override
    public Object queryAll(LogQueryCriteria criteria, Pageable pageable) {
        Page<Log> page = logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
        String status = "ERROR";
        if (status.equals(criteria.getLogType())) {
            return PageUtil.toPage(page.map(logErrorMapper::toDto));
        }
        return page;
    }

    @Override
    public List<Log> queryAll(LogQueryCriteria criteria) {
        return logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)));
    }

    @Override
    public Object queryAllByUser(LogQueryCriteria criteria, Pageable pageable) {
        Page<Log> page = logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
        return PageUtil.toPage(page.map(logSmallMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, Log log) {

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
        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }
        assert log != null;
        log.setRequestIp(ip);

        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                username = new JSONObject(argValues.get(0)).get("username").toString();
            } catch (Exception e) {
                LogServiceImpl.log.error(e.getMessage(), e);
            }
        }
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        log.setBrowser(browser);
        logRepository.save(log);
    }

    @Override
    public void save(Log logVo) {
//        MethodSignature signature = (MethodSignature) logVo.getPoint().getSignature();
//        Method method = signature.getMethod();
//        com.gu.log.annotation.Log aopLog = method.getAnnotation(com.gu.log.annotation.Log.class);
//
//        // 方法路径
//        String methodName = logVo.getPoint().getTarget().getClass().getName() + "." + signature.getName() + "()";
//
//        StringBuilder params = new StringBuilder("{");
//        //参数值
//        List<Object> argValues = new ArrayList<>(Arrays.asList(logVo.getPoint().getArgs()));
//        //参数名称
//        for (Object argValue : argValues) {
//            params.append(argValue).append(" ");
//        }
//
//        Log log = new Log();
//
//        // 描述
//        if (StringUtils.isNotBlank(logVo.getLogType())) {
//            log.setDescription(aopLog.value());
//        }
//        assert log != null;
//        log.setRequestIp(logVo.getRequestIp());
//
//        String loginPath = "login";
//        String username = log.getUsername();
//        if (loginPath.equals(signature.getName())) {
//            try {
//                username = new JSONObject(argValues.get(0)).get("username").toString();
//            } catch (Exception e) {
//                LogServiceImpl.log.error(e.getMessage(), e);
//            }
//        }
//        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
//        log.setMethod(methodName);
//        log.setUsername(username);
//        log.setParams(params.toString() + " }");
//        log.setBrowser(logVo.getBrowser());
//        if (logVo.getExceptionDetail() != null && logVo.getExceptionDetail().length != 0) {
//            log.setExceptionDetail(log.getExceptionDetail());
//        }
        logRepository.save(logVo);
    }

    @Override
    public Object findByErrDetail(Long id) {
        Log log = logRepository.findById(id).orElseGet(Log::new);
        byte[] details = log.getExceptionDetail();
        return Dict.create().set("exception", new String(ObjectUtil.isNotNull(details) ? details : "".getBytes()));
    }

    @Override
    public void download(List<Log> logs, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Log log : logs) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", log.getUsername());
            map.put("IP", log.getRequestIp());
            map.put("IP来源", log.getAddress());
            map.put("描述", log.getDescription());
            map.put("浏览器", log.getBrowser());
            map.put("请求耗时/毫秒", log.getTime());
            map.put("异常详情", new String(ObjectUtil.isNotNull(log.getExceptionDetail()) ? log.getExceptionDetail() : "".getBytes()));
            map.put("创建日期", log.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByError() {
        logRepository.deleteByLogType("ERROR");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByInfo() {
        logRepository.deleteByLogType("INFO");
    }
}
