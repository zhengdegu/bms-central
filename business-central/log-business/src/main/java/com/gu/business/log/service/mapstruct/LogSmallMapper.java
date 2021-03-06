
package com.gu.business.log.service.mapstruct;

import com.gu.business.log.domain.Log;
import com.gu.business.log.service.dto.LogSmallDTO;
import com.gu.common.mapstruct.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogSmallMapper extends BaseMapper<LogSmallDTO, Log> {

}