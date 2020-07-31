package com.gu.business.search.service;

import com.alibaba.fastjson.JSONObject;
import com.gu.elasticsearch.dto.SearchDto;

import java.io.IOException;
import java.util.List;

/**
 * search
 *
 * @author FastG
 * @date 2020/7/30 15:33
 */
public interface SearchService {


    /**
     * 通用搜索 适合模糊查询
     *
     * @param indexName
     * @param searchDto
     * @return
     * @throws IOException
     */
    List<JSONObject> strQuery(String indexName, SearchDto searchDto) throws IOException;

}
