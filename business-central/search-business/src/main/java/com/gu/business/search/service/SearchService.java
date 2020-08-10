package com.gu.business.search.service;


import com.gu.business.search.service.dto.ServerLogQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * search
 *
 * @author FastG
 * @date 2020/7/30 15:33
 */
public interface SearchService {

    /**
     * 查询服务log
     * @param serverLogQueryCriteria
     * @param pageable
     * @return
     */
    Object queryAll(ServerLogQueryCriteria serverLogQueryCriteria, Pageable pageable);
}
