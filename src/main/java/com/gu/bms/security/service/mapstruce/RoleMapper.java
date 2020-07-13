package com.gu.bms.security.service.mapstruce;

import com.gu.bms.security.domain.Role;
import com.gu.bms.security.service.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author FastG
 * @create 2020-07-13
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {

}
