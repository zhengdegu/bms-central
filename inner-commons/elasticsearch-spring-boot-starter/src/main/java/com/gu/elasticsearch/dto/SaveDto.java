package com.gu.elasticsearch.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Map;

/**
 * es保存数据
 *
 * @author FastG
 * @date 2020/5/13 13:50
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class SaveDto implements Serializable {
    private static final long serialVersionUID = -2084416068307485742L;

    /**
     * id
     */
    private long id;


    /**
     * 数据  以json形式
     */
    private Map data;
}
