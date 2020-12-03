${gg.setIgnoreOutput(table.pkCount <= 1)}

<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

<#include "/java_imports_e.include">

/**
<#include "/java_description.include">
 */
public class ${className}Id implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
<#list table.columns as column>
	<#if column.pk>
	@TableId(value = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};
	</#if>
</#list>

<@generateConstructor className+"Id"/>
	
<#list table.columns as column>
	<#if column.pk>
	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}

	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#if>
</#list>

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}
}