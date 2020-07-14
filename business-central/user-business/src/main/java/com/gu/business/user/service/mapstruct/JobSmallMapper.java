package com.gu.business.user.service.mapstruct;

import com.gu.business.user.domain.Job;
import com.gu.common.domain.dto.JobSmallDto;
import com.gu.common.mapstruct.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSmallMapper extends BaseMapper<JobSmallDto, Job> {

}