package com.gu.bms.security.service.mapstruce;

import com.gu.bms.security.domain.Dept;
import com.gu.bms.security.service.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {
}