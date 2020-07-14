package com.gu.business.user.service.mapstruct;

import com.gu.business.user.domain.User;
import com.gu.common.domain.dto.UserDto;
import com.gu.common.mapstruct.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Mapper(componentModel = "spring", uses = {RoleMapper.class, DeptMapper.class, JobMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
}

