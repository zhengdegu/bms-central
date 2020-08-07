package com.gu.business.log.controller;

import com.gu.business.log.domain.Log;
import com.gu.business.log.service.LogService;
import com.gu.business.log.service.dto.LogQueryCriteria;
import com.gu.common.utils.HeaderUtil;
import com.gu.common.utils.R;
import com.gu.log.domain.LogVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author FastG
 * @date 2020/8/6 16:27
 */
@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;


    @com.gu.log.annotation.Log("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
        criteria.setLogType("INFO");
        logService.download(logService.queryAll(criteria), response);
    }

    @com.gu.log.annotation.Log("导出错误数据")
    @GetMapping(value = "/error/download")
    public void downloadErrorLog(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
        criteria.setLogType("ERROR");
        logService.download(logService.queryAll(criteria), response);
    }
    @GetMapping
    public ResponseEntity<Object> query(LogQueryCriteria criteria, Pageable pageable){
        criteria.setLogType("INFO");
        return new ResponseEntity<>(logService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<Object> queryUserLog(LogQueryCriteria criteria, Pageable pageable){
        criteria.setLogType("INFO");
        criteria.setBlurry(HeaderUtil.extractUser());
        return new ResponseEntity<>(logService.queryAllByUser(criteria,pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/error")
    public ResponseEntity<Object> queryErrorLog(LogQueryCriteria criteria, Pageable pageable){
        criteria.setLogType("ERROR");
        return new ResponseEntity<>(logService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/error/{id}")
    public ResponseEntity<Object> queryErrorLogs(@PathVariable Long id){
        return new ResponseEntity<>(logService.findByErrDetail(id), HttpStatus.OK);
    }
    @DeleteMapping(value = "/del/error")
    @com.gu.log.annotation.Log("删除所有ERROR日志")
    public ResponseEntity<Object> delAllErrorLog(){
        logService.delAllByError();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/del/info")
    @com.gu.log.annotation.Log("删除所有INFO日志")
    public ResponseEntity<Object> delAllInfoLog(){
        logService.delAllByInfo();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveLog(@RequestBody LogVo logVo){
        Log log =new Log();
        BeanUtils.copyProperties(logVo,log);
        logService.save(log);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
