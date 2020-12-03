<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
<#include "/java_description.include">
 */
@Data
@ToString
@TableName(value = "${table.sqlName}")
@KeySequence(value = "${table.sqlName}_SEQ")
public class ${className} extends Model<${className}> implements java.io.Serializable{

	private static final long serialVersionUID = 5454155825314635342L;

	//alias
	public static final String TABLE_ALIAS = "${table.tableAlias}";
<#list table.columns as column>
	public static final String ALIAS_${column.constantName} = "${column.columnAlias}";
</#list>

	//field
<#list table.columns as column>
	public static final String FIELD_${column.constantName} = "${column.columnNameLower}";
</#list>

	//date formats

<@generateFields/>
<@generateCompositeIdConstructorIfis/>

	@Override
	protected Serializable pkVal() {
<#list table.columns as column>
    <#if column.pk>
		return this.${column.columnNameLower};
    </#if>
</#list>
	}

	public int hashCode() {
		return new HashCodeBuilder()
<#list table.pkColumns as column>
    <#if !table.compositeId>
		.append(get${column.columnName}())
    </#if>
</#list>
		.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof ${className} == false) return false;
		if(this == obj) return true;
		${className} other = (${className})obj;
		return new EqualsBuilder()
<#list table.pkColumns as column>
    <#if !table.compositeId>
		.append(get${column.columnName}(),other.get${column.columnName}())
    </#if>
</#list>
		.isEquals();
	}

<#macro generateFields>
    <#if table.compositeId>
	private ${className}Id id;
        <#list table.columns as column>
            <#if !column.pk>
	@TableField(value = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};
            </#if>
        </#list>
    <#else>
	//columns START
        <#list table.columns as column>
            <#if column.pk>
	/**
	 * ${column.columnAlias!}       db_column: ${column.sqlName}
	 */
	@TableId(value = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};
            <#else>
	/**
	 * ${column.columnAlias!}       db_column: ${column.sqlName}
	 */
                <#if "isDelete"==column.columnNameLower>
	@TableLogic
                </#if>
                <#if "version"==column.columnNameLower>
	@Version
                </#if>
                <#switch column.columnNameLower>
                    <#case "creatorId">
    @TableField(value = "${column.sqlName}",fill = FieldFill.INSERT)
                        <#break>
                    <#case "createdTime">
    @TableField(value = "${column.sqlName}",fill = FieldFill.INSERT)
                        <#break>
                    <#case "updatedId">
    @TableField(value = "${column.sqlName}",fill = FieldFill.UPDATE)
                        <#break>
                    <#case "updatedTime">
    @TableField(value = "${column.sqlName}",fill = FieldFill.UPDATE)
                        <#break>
                    <#default>
    @TableField(value = "${column.sqlName}")
                </#switch>
	private ${column.javaType} ${column.columnNameLower};
            </#if>
        </#list>
	//columns END
    </#if>

</#macro>


<#macro generateCompositeIdConstructorIfis>

    <#if table.compositeId>
	public ${className}(){
			}
	public ${className}(${className}Id id) {
			this.id = id;
			}
    <#else>
        <@generateConstructor className/>
    </#if>

</#macro>
}
