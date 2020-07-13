package com.gu.bms.security.service.mapstruce;

import com.gu.bms.security.domain.Menu;
import com.gu.bms.security.service.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDto, Menu> {
}
