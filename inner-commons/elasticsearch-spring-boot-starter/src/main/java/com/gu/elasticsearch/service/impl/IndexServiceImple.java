package com.gu.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.carrotsearch.hppc.cursors.ObjectCursor;
import com.gu.elasticsearch.dto.IndexDto;
import com.gu.elasticsearch.service.IndexService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FastG
 * @date 2020/5/11 17:44
 */
@Service
public class IndexServiceImple implements IndexService {

    @Autowired(required = false)
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Deprecated
    @Override
    public boolean create(IndexDto indexDto) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexDto.getIndexName());
        request.settings(Settings.builder()
                .put("index.number_of_shards", indexDto.getNumberOfShards())
                .put("index.number_of_replicas", indexDto.getNumberOfReplicas())
        );
        if (StringUtils.isNotEmpty(indexDto.getType()) && StringUtils.isNotEmpty(indexDto.getMappingsSource())) {
            //mappings
            request.mapping(indexDto.getType(), indexDto.getMappingsSource(), XContentType.JSON);
        }
        CreateIndexResponse response = elasticsearchRestTemplate.getClient()
                .indices()
                .create(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }


    @Override
    public boolean delete(String indexName) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        AcknowledgedResponse response = elasticsearchRestTemplate.getClient().indices().delete(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }

    @Override
    public Map<String, Object> show(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        GetIndexResponse getIndexResponse = elasticsearchRestTemplate.getClient()
                .indices().get(request, RequestOptions.DEFAULT);
        ImmutableOpenMap<String, MappingMetaData> mappOpenMap = getIndexResponse.getMappings().get(indexName);
        List<AliasMetaData> indexAliases = getIndexResponse.getAliases().get(indexName);

        String settingsStr = getIndexResponse.getSettings().get(indexName).toString();
        Object settingsObj = null;
        if (StringUtils.isNotEmpty(settingsStr)) {
            settingsObj = JSONObject.parse(settingsStr);
        }
        Map<String, Object> result = new HashMap<>(1);
        Map<String, Object> indexMap = new HashMap<>(3);
        Map<String, Object> mappMap = new HashMap<>(mappOpenMap.size());
        List<String> aliasesList = new ArrayList<>(indexAliases.size());
        indexMap.put("aliases", aliasesList);
        indexMap.put("settings", settingsObj);
        indexMap.put("mappings", mappMap);
        result.put(indexName, indexMap);
        //获取mappings数据
        for (ObjectCursor<String> key : mappOpenMap.keys()) {
            MappingMetaData data = mappOpenMap.get(key.value);
            Map<String, Object> dataMap = data.getSourceAsMap();
            mappMap.put(key.value, dataMap);
        }
        //获取aliases数据
        for (AliasMetaData aliases : indexAliases) {
            aliasesList.add(aliases.getAlias());
        }
        return result;
    }
}
