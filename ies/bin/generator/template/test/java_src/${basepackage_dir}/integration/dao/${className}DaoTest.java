<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.integration.dao;

<#include "/java_imports.include">

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.test.spring.SpringTxTestCase;

import com.vsc.auth.dao.${className}Dao;
import com.vsc.auth.entity.${className};

public class ${className}DaoTest extends SpringTxTestCase{
    @Autowired
	private ${className}Dao entityDao;

	@Test
	public void crudEntity() {
		//new entity and save it. 
		${className} entity = new ${className}();

        //请填写默认数据代码

		entityDao.save(entity);
		flush();

		//find entity.
		${className} entityFromDB = entityDao.findUniqueBy("id", entity.getId());
		assertReflectionEquals(entity, entityFromDB);

		//delete entity.
		entityDao.delete(entity.getId());
		flush();
		entity = entityDao.findUniqueBy("id", entity.getId());
		assertNull(entity);
	}
}
