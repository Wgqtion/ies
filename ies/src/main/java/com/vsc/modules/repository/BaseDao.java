package com.vsc.modules.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vsc.modules.entity.IdEntity;

public interface BaseDao<T extends IdEntity> extends JpaRepository<T, Long>,JpaSpecificationExecutor<T>{ 

}
