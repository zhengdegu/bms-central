package com.gu.elasticsearch.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gu.common.utils.R;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.ElasticsearchException;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * es查询简单封装
 *
 * @author FastG
 * @date 2020/5/11 16:27
 */
public class SearchBuilder {

    /**
     * 高亮前缀
     */
    private static final String HIGHLIGHTER_PRE_TAGS = "<mark>";
    /**
     * 高亮后缀
     */
    private static final String HIGHLIGHTER_POST_TAGS = "</mark>";
    private final SearchRequest searchRequest;
    private final SearchSourceBuilder searchBuilder;
    private final RestHighLevelClient client;


    private SearchBuilder(SearchRequest searchRequest, SearchSourceBuilder searchSourceBuilder, RestHighLevelClient restHighLevelClient) {
        this.searchRequest = searchRequest;
        this.searchBuilder = searchSourceBuilder;
        this.client = restHighLevelClient;
    }

    /**
     * 生成SearchBuilder实例
     *
     * @param elasticsearchRestTemplate spring es template
     * @param index                     索引
     * @return SearchBuilder 实例
     */
    public static SearchBuilder builder(ElasticsearchRestTemplate elasticsearchRestTemplate, String index) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        RestHighLevelClient restHighLevelClient = elasticsearchRestTemplate.getClient();
        return new SearchBuilder(searchRequest, searchSourceBuilder, restHighLevelClient);
    }


    /**
     * 返回结果 SearchResponse
     */
    public SearchResponse get() throws IOException {
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }

//#####################    封装查询条件    ###############


    /**
     * 生成queryString查询  多想匹配
     *
     * @param stringQuery 查询
     * @param fetchSource 过滤原字段
     */
    public SearchBuilder queryStringQuery(String stringQuery, String[] fetchSource) {
        QueryBuilder queryBuilder;
        if (StringUtils.isNotEmpty(stringQuery)) {
            queryBuilder = QueryBuilders.queryStringQuery(stringQuery);
        } else {
            queryBuilder = QueryBuilders.matchAllQuery();
        }
        //过滤原字段
        if (ArrayUtils.isNotEmpty(fetchSource)) {
            searchBuilder.fetchSource(fetchSource, new String[]{});
        }
        searchBuilder.query(queryBuilder);
        return this;
    }

    /**
     * @param source      源字段
     * @param value       参数
     * @param fetchSource 过滤原字段
     */
    public SearchBuilder termQuery(String source, Object value, String[] fetchSource) {
        QueryBuilder queryBuilder;
        if (StringUtils.isNotEmpty(source) && value != null) {
            queryBuilder = QueryBuilders.termQuery(source, value);
        } else {
            queryBuilder = QueryBuilders.matchAllQuery();
        }
        //过滤原字段
        if (ArrayUtils.isNotEmpty(fetchSource)) {
            searchBuilder.fetchSource(fetchSource, new String[]{});
        }
        searchBuilder.query(queryBuilder);
        return this;
    }


    public SearchBuilder matchQuery(String source, Object value, String[] fetchSource) {
        QueryBuilder queryBuilder;
        if (StringUtils.isNotEmpty(source) && value != null) {
            queryBuilder = QueryBuilders.matchQuery(source, value);
        } else {
            queryBuilder = QueryBuilders.matchAllQuery();
        }
        //过滤原字段
        if (ArrayUtils.isNotEmpty(fetchSource)) {
            searchBuilder.fetchSource(fetchSource, new String[]{});
        }
        searchBuilder.query(queryBuilder);
        return this;
    }

    /**
     * 多字段匹配查询
     *
     * @param source      源字段
     * @param value       数据
     * @param fetchSource 过滤原字段
     */
    public SearchBuilder multiQuery(String[] source, Object value, String[] fetchSource) {
        QueryBuilder queryBuilder;
        if (ArrayUtils.isNotEmpty(source) && value != null) {
            queryBuilder = QueryBuilders.multiMatchQuery(value, source);
        } else {
            queryBuilder = QueryBuilders.matchAllQuery();
        }
        //过滤原字段
        if (ArrayUtils.isNotEmpty(fetchSource)) {
            searchBuilder.fetchSource(fetchSource, new String[]{});
        }
        searchBuilder.query(queryBuilder);
        return this;
    }


    /**
     * 增加排序   持在keyword、date、ﬂoat等类型上添加，text类型的字段上不允许添加排序
     *
     * @param field 排序字段
     * @param order 顺序方向
     */
    public SearchBuilder addSort(String field, SortOrder order) {
        if (StringUtils.isNotEmpty(field) && order != null) {
            searchBuilder.sort(field, order);
        }
        return this;
    }

    /**
     * 设置分页
     *
     * @param page  当前页数
     * @param limit 每页显示数
     */
    public SearchBuilder setPage(Integer page, Integer limit) {
        if (page != null && limit != null) {
            searchBuilder.from((page - 1) * limit)
                    .size(limit);
        }
        return this;
    }

    /**
     * 设置高亮 高亮显示可以将搜索结果一个或多个字突出显示，以便向用户展示匹配关键字的位置
     *
     * @param preTags  高亮处理前缀
     * @param postTags 高亮处理后缀
     */
    public SearchBuilder setHighlight(String field, String preTags, String postTags) {
        if (StringUtils.isNotEmpty(field) && StringUtils.isNotEmpty(preTags) && StringUtils.isNotEmpty(postTags)) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field(field)
                    .preTags(preTags)
                    .postTags(postTags);
            searchBuilder.highlighter(highlightBuilder);
        }
        return this;
    }

    /**
     * 设置是否需要高亮处理
     *
     * @param isHighlighter 是否需要高亮处理
     */
    public SearchBuilder setIsHighlight(Boolean isHighlighter) {
        if (BooleanUtils.isTrue(isHighlighter)) {
            this.setHighlight("*"
                    , HIGHLIGHTER_PRE_TAGS, HIGHLIGHTER_POST_TAGS);
        }
        return this;
    }


    /**
     * 设置查询路由
     *
     * @param routing 路由数组
     */
    public SearchBuilder setRouting(String... routing) {
        if (ArrayUtils.isNotEmpty(routing)) {
            searchRequest.routing(routing);
        }
        return this;
    }

    /**
     * 返回列表结果 List<JSONObject>
     */
    public List<JSONObject> getList() throws IOException {
        return this.getList(this.get().getHits());
    }

    /**
     * 返回分页结果 PageResult<JSONObject>
     */
    public R getPage() throws IOException {
        return this.getPage(null, null);
    }

    /**
     * 返回分页结果 PageResult<JSONObject>
     *
     * @param page  当前页数
     * @param limit 每页显示
     */
    public R getPage(Integer page, Integer limit) throws IOException {
        this.setPage(page, limit);
        SearchResponse response = this.get();
        SearchHits searchHits = response.getHits();
        long totalCount = searchHits.getTotalHits().value;
        List<JSONObject> list = getList(searchHits);
        Map<String, Object> map = new HashMap<>(2);
        map.put("count", totalCount);
        map.put("data", list);
        return R.ok(map);
    }

    /**
     * 返回JSON列表数据
     */
    private List<JSONObject> getList(SearchHits searchHits) {
        List<JSONObject> list = new ArrayList<>();
        if (searchHits != null) {
            searchHits.forEach(item -> {
                JSONObject jsonObject = JSON.parseObject(item.getSourceAsString());
                jsonObject.put("id", item.getId());

                Map<String, HighlightField> highlightFields = item.getHighlightFields();
                if (highlightFields != null) {
                    populateHighLightedFields(jsonObject, highlightFields);
                }
                list.add(jsonObject);
            });
        }
        return list;
    }

    /**
     * 组装高亮字符
     *
     * @param result          目标对象
     * @param highlightFields 高亮配置
     */
    private <T> void populateHighLightedFields(T result, Map<String, HighlightField> highlightFields) {
        for (HighlightField field : highlightFields.values()) {
            try {
                String name = field.getName();
                if (!name.endsWith(".keyword")) {
                    PropertyUtils.setProperty(result, field.getName(), concat(field.fragments()));
                }
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new ElasticsearchException("failed to set highlighted value for field: " + field.getName()
                        + " with value: " + Arrays.toString(field.getFragments()), e);
            }
        }
    }

    /**
     * 拼凑数组为字符串
     */
    private String concat(Text[] texts) {
        StringBuffer sb = new StringBuffer();
        for (Text text : texts) {
            sb.append(text.toString());
        }
        return sb.toString();
    }

}
