package com.gu.bms.security.service.mapstruce;

import com.gu.bms.security.domain.User;
import com.gu.bms.security.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author FastG
 * @create 2020-07-13
 **/
@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
}
