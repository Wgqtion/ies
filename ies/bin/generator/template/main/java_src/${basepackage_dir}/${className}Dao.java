<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
<#assign fmtfuncname = funcname?replace("/",".")>
package ${basepackage}.repository.${fmtfuncname};

import com.vsc.modules.repository.BaseDao;
import ${basepackage}.entity.${fmtfuncname}.${className};

/**
 * 
 * @author ${codeauthor}
 *
 */
public interface ${className}Dao extends BaseDao<${className}> {
 
}
