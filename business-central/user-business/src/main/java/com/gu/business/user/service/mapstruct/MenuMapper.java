package com.gu.business.user.service.mapstruct;

import com.gu.business.user.domain.Menu;
import com.gu.common.domain.dto.MenuDto;
import com.gu.common.mapstruct.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDto, Menu> {
}
