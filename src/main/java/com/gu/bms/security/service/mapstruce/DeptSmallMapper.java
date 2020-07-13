package com.gu.bms.security.service.mapstruce;

import com.gu.bms.security.domain.Dept;
import com.gu.bms.security.service.dto.DeptSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author FastG
 * @create 2020-07-13
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptSmallMapper extends BaseMapper<DeptSmallDto, Dept> {

}