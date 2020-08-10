package com.gu.business.search.controller;

import com.gu.business.search.service.SearchService;
import com.gu.business.search.service.dto.ServerLogQueryCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FastG
 * @date 2020/8/10 16:00
 */
@RequiredArgsConstructor
@RestController("api/sysLog")
public class ServerLogController {


    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<Object> query(ServerLogQueryCriteria serverLogQueryCriteria, Pageable pageable) {
        return new ResponseEntity<>(searchService.queryAll(serverLogQueryCriteria, pageable), HttpStatus.OK);
    }
}
