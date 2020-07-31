package com.gu.elasticsearch.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询
 *
 * @author FastG
 * @date 2020/5/13 12:49
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class SearchDto implements Serializable {
    private static final long serialVersionUID = -2084416068307485742L;
    /**
     * 搜索关键字
     */
    private String queryStr;

    /**
     * 过滤原字段
     */
    private String[] fetchSource;
    /**
     * 当前页数
     */
    private Integer page;
    /**
     * 每页显示数
     */
    private Integer limit;
    /**
     * 排序字段
     */
    private String sortCol;
    /**
     * 是否显示高亮
     */
    private Boolean isHighlighter;
    /**
     * es的路由
     */
    private String routing;
}
