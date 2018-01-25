<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign fmtfuncname = funcname?replace("/",".")>
package ${basepackage}.entity.${fmtfuncname};

import javax.persistence.*; 
import com.vsc.constants.Constants;
import org.apache.commons.lang3.builder.ToStringBuilder;
<#include "/java_imports.include">

/**
 * 
 * @author ${codeauthor}
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX +"${classNameLower}")
public class ${className} extends IdEntity {

	<@generateFields/>
	<@generateCompositeIdConstructorIfis/> 
	<@generateNotPkProperties/>
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>  
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

<#macro generateFields> 
<#if table.compositeId> 
	<#list table.columns as column>
		<#if !column.pk>
	 
		</#if>
	</#list>
<#else>
	 
<#list table.columns as column>
  <#if !column.pk>	 
     private ${column.javaType} ${column.columnNameLower};
  </#if>
 </#list>
	 
</#if>

</#macro>

<#macro generateCompositeIdConstructorIfis> 
	<#if table.compositeId> 
	<#else> 
	</#if> 
</#macro>


<#macro generateNotPkProperties>
	<#list table.columns as column>
		<#if !column.pk>
	
	/**
	 * @return
	 */
	@Column(name = "${column.sqlName}" <#if column.unique>,unique = ${column.unique?string}</#if>)
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
		</#if>
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "${classNameLower}")
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	<#list foreignKey.parentColumns?values as fkColumn>
	@JoinColumn(name = "${fkColumn}",nullable = false, insertable = false, updatable = false)
    </#list>
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
