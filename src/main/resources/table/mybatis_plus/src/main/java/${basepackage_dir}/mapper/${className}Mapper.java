<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameFirstLower = table.classNameFirstLower>
package ${basepackage}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * ${className}Mapper
 <#include "/java_description.include">
 */
public interface ${className}Mapper extends BaseMapper<${className}> {

        ${className} selectByPrimaryKey(Integer id);

        List<${className}> selectAll();

        int deleteByPrimaryKey(Integer id);

        int insertEntiy(${className} ${classNameFirstLower});

        int insertSelective(${className} ${classNameFirstLower});

        int insertBatch(@Param("${classNameFirstLower}s")List<${className}> ${classNameFirstLower}s);

        int updateByPrimaryKeySelective(${className} ${classNameFirstLower});

        int updateByPrimaryKey(${className} ${classNameFirstLower});
}