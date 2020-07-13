package com.gu.bms.security.service.mapstruce;

import com.gu.bms.security.domain.Job;
import com.gu.bms.security.service.dto.JobSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author FastG
 * @create 2020-07-13
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSmallMapper extends BaseMapper<JobSmallDto, Job> {

}