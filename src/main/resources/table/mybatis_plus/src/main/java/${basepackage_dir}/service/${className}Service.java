<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * ${className} 表数据服务层接口
 *
 */
public interface ${className}Service extends IService<${className}>{

}