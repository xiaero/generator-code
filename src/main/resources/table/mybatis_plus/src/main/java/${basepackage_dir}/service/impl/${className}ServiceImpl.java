<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.service.impl;

import org.springframework.stereotype.Service;

/**
 *
 * ${classNameFirstLower}Service 接口实现类
 *
 */
@Service("${classNameFirstLower}Service")
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements ${className}Service{

}