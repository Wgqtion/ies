package com.vsc.business.core.repository.sys;

import java.util.*;
import org.springframework.data.jpa.repository.Query;

import com.vsc.business.core.entity.sys.Dict;
import com.vsc.modules.repository.BaseDao;

/**
 * 
 * @author 陈婷
 *
 */
public interface DictDao extends BaseDao<Dict> {
	
	@Query("select distinct dict.type from Dict dict order by dict.sort")
	List<String> findAllType();

}
