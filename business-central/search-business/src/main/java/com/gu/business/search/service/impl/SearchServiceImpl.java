package com.gu.business.search.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gu.business.search.service.SearchService;
import com.gu.business.search.service.dto.ServerLogQueryCriteria;
import com.gu.elasticsearch.dto.SearchDto;
import com.gu.elasticsearch.utils.SearchBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author FastG
 * @date 2020/7/30 15:39
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {


    private final ElasticsearchRestTemplate elasticsearchRestTemplate;



    @Override
    public Object queryAll(ServerLogQueryCriteria serverLogQueryCriteria, Pageable pageable) {

        try {
            List<Field> fields = getAllFields(serverLogQueryCriteria.getClass(), new ArrayList<>());
            fields.forEach(field -> {

            });
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return null;
    }


    private List<Field> getAllFields(Class clazz, List<Field> fields) {
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }
}
