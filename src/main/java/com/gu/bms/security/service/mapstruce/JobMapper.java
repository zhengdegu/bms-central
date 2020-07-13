package com.gu.bms.security.service.mapstruce;

import com.gu.bms.security.domain.Job;
import com.gu.bms.security.service.dto.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends BaseMapper<JobDto, Job> {
}