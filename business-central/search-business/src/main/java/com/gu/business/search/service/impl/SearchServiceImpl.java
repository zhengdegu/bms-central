package com.gu.business.search.service.impl;

import com.gu.business.search.service.SearchService;
import com.gu.business.search.service.dto.ServerLogQueryCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author FastG
 * @date 2020/8/24 15:39
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
                //TODO 实现es查询
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
