package com.gu.business.user.service.mapstruct;

import com.gu.business.user.domain.Role;
import com.gu.common.domain.dto.RoleDto;
import com.gu.common.mapstruct.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {

}