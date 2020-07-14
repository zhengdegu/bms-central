package com.gu.elasticsearch.service;

import com.gu.elasticsearch.dto.IndexDto;

import java.io.IOException;
import java.util.Map;

/**
 * 索引增删操作
 *
 * @author FastG
 * @date 2020/5/11 17:42
 */
public interface IndexService {
    /**
     * 创建索引
     */
    boolean create(IndexDto indexDto) throws IOException;

    /**
     * 删除索引
     *
     * @param indexName 索引名
     */
    boolean delete(String indexName) throws IOException;


    /**
     * 显示索引明细
     *
     * @param indexName 索引名
     */
    Map<String, Object> show(String indexName) throws IOException;
}
