<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign fmtfuncname = funcname?replace("/",".")>
package ${basepackage}.service.${fmtfuncname};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${basepackage}.entity.${fmtfuncname}.${className};
import ${basepackage}.repository.${fmtfuncname}.${className}Dao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author ${codeauthor}
 *
 */
@Service
@Transactional
public class ${className}Service extends BaseService<${className}>{
	private static Logger logger = LoggerFactory.getLogger(${className}Service.class);
  
	@Autowired
	private ${className}Dao ${classNameLower}Dao;

    
  	
	@Override
	public PagingAndSortingRepository<${className}, Long> getPagingAndSortingRepositoryDao() {
		return this.${classNameLower}Dao;
	}

	@Override
	public JpaSpecificationExecutor<${className}> getJpaSpecificationExecutorDao() {
		return this.${classNameLower}Dao;
	}
	
}
