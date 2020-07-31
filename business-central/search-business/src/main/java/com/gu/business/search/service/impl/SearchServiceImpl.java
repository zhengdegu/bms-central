package com.gu.business.search.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gu.business.search.service.SearchService;
import com.gu.elasticsearch.dto.SearchDto;
import com.gu.elasticsearch.utils.SearchBuilder;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author FastG
 * @date 2020/7/30 15:39
 */
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {


    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<JSONObject> strQuery(String indexName, SearchDto searchDto) throws IOException {

        return SearchBuilder.builder(elasticsearchRestTemplate, indexName)
                .queryStringQuery(searchDto.getQueryStr(), searchDto.getFetchSource())
                .addSort(searchDto.getSortCol(), SortOrder.DESC)
                .setIsHighlight(searchDto.getIsHighlighter())
                .getPage(searchDto.getPage(), searchDto.getLimit());

    }
}
