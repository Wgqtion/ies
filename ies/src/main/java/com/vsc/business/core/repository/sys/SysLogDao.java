package com.vsc.business.core.repository.sys;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vsc.business.core.entity.sys.SysLog;

public interface SysLogDao extends PagingAndSortingRepository<SysLog, Long>,JpaSpecificationExecutor<SysLog> {
}
