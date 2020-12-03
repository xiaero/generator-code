<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign pkJavaType = table.idColumn.javaType>
package ${basepackage}.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONArray;
import com.jns.datacenter.common.util.JnsRuntimeException;
import com.jns.datacenter.common.util.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;;
import com.jns.datacenter.common.util.JsonRequest;
import com.jns.datacenter.dao.dto.ServerResultDTO;

/**
 * @apiDefine ${classNameFirstLower}
 */
@RestController
@RequestMapping("/${classNameFirstLower}")
public class ${className}Controller extends BaseControler{
	private static final Logger logger = LoggerFactory.getLogger(${className}Controller.class);

	@Autowired
	private ${className}Service ${classNameFirstLower}Service;

	/**
	 * @api {post} /${classNameFirstLower}/query 分页查询
	 * @apiName query
	 * @apiGroup ${classNameFirstLower}
	 * @apiVersion 1.0.0
	 * @apiHeader {String} sessionToken
	 * @apiParam {Integer} currentPage 开始页
	 * @apiParam {Integer} pageSize 每页多少条
	 *
	 * @apiSuccess {Integer} total 总条数
	 * @apiSuccess {Integer} pages 总页数
	 * @apiSuccess {Integer} currentPage 开始页
	 * @apiSuccess {Integer} pageSize 每页多少条
     * @apiSuccess {list} ${classNameFirstLower}List
	<#list table.columns as column>
	<#switch column.columnNameLower>
	<#case "creatorId">
	<#break>
	<#case "createdTime">
	<#break>
	<#case "updatedId">
	<#break>
	<#case "updatedTime">
	<#break>
	<#case "isDelete">
	<#break>
	<#default>
	 * @apiSuccess {${column.javaType}}  ${classNameFirstLower}List.${column.columnNameLower}  ${column.columnAlias!}
	</#switch>
	</#list>
	 */
	@Power
	@RequestMapping(value="/query",method= RequestMethod.POST)
	public ServerResultDTO query(@RequestBody JsonRequest request) {
		IPage page = new Page(request.getInteger("currentPage", 1), request.getInteger("pageSize", 10));
		${className} ${classNameFirstLower} = request.toJavaObject(${className}.class);
		page = this.${classNameFirstLower}Service.page(page, new QueryWrapper(${classNameFirstLower}));
		JSONObject json = new JSONObject();
		json.put("total", page.getTotal());
		json.put("pages", page.getPages());
		json.put("${classNameFirstLower}List", page.getRecords());
		json.put("currentPage", page.getCurrent());
		json.put("pageSize", page.getSize());
		return ServerResultDTO.newSuccess("查询成功", json);
	}

	/**
	* @api {post} /${classNameFirstLower}/findById 根据Id查询详情
	* @apiName findById
	* @apiGroup ${classNameFirstLower}
	* @apiVersion 1.0.0
	* @apiHeader {String} sessionToken
	* @apiParam {Integer} id id
	*
    *
	<#list table.columns as column>
	<#switch column.columnNameLower>
	<#case "creatorId">
	<#break>
	<#case "createdTime">
	<#break>
	<#case "updatedId">
	<#break>
	<#case "updatedTime">
	<#break>
	<#case "isDelete">
	<#break>
	<#case "version">
	<#break>
	<#default>
	* @apiSuccess {${column.javaType}}  ${column.columnNameLower}  ${column.columnAlias!}
	</#switch>
	</#list>
	*/
	@Power
	@RequestMapping(value="/findById",method=RequestMethod.POST)
	public ServerResultDTO findById(@RequestBody ${className} ${classNameFirstLower}){
		if(${classNameFirstLower}.getId()==null){
			throw new JnsRuntimeException(-1,"id不能为空");
		}
		${classNameFirstLower} = this.${classNameFirstLower}Service.getById(${classNameFirstLower}.getId());
		return ServerResultDTO.newSuccess("查询成功",${classNameFirstLower});
	}




	/**
	* @api {post} /${classNameFirstLower}/saveOrUpdate 新增&更新
	* @apiName saveOrUpdate
	* @apiGroup ${classNameFirstLower}
	* @apiVersion 1.0.0
	* @apiHeader {String} sessionToken
	* @apiParam {Integer} id id
	*
    <#list table.columns as column>
    <#switch column.columnNameLower>
    <#case "creatorId">
     <#break>
     <#case "createdTime">
     <#break>
     <#case "updatedId">
     <#break>
     <#case "updatedTime">
     <#break>
     <#case "isDelete">
     <#break>
	<#case "version">
	<#break>
     <#default>
    * @apiSuccess {${column.javaType}} ${column.columnNameLower}  ${column.columnAlias!}
     </#switch>
    </#list>
	*/
	@Power
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public ServerResultDTO saveOrUpdate(@RequestBody ${className} ${classNameFirstLower}){
		try {
			this.${classNameFirstLower}Service.saveOrUpdate(${classNameFirstLower});
		}catch(Exception e) {
			logger.error(e.getMessage());
			return ServerResultDTO.newError(e.getMessage());
		}
		return ServerResultDTO.newSuccess("保存成功",${classNameFirstLower});
	}

	/**
	 * @api {post} /${classNameFirstLower}/delete 删除
	 * @apiName delete
	 * @apiGroup ${classNameFirstLower}
	 * @apiVersion 1.0.0
	 * @apiHeader {String} sessionToken
	 * @apiParam {Integer} id
	 */
	@Power
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ServerResultDTO delete(@RequestBody ${className} ${classNameFirstLower}) {
		if(${classNameFirstLower}.getId()==null){
			throw new JnsRuntimeException(-1,"id不能为空");
		}
		this.${classNameFirstLower}Service.removeById(${classNameFirstLower}.getId());
		return ServerResultDTO.newSuccess("删除成功");
	}

	/**
	 * @api {post} /${classNameFirstLower}/batchDelete 批量删除
	 * @apiName batchDelete
	 * @apiGroup ${classNameFirstLower}
	 * @apiVersion 1.0.0
	 * @apiHeader {String} sessionToken
	 * @apiParam {List} ids
	 */
	@Power
	@RequestMapping(value="/batchDelete",method=RequestMethod.POST)
	public ServerResultDTO batchDelete(@RequestBody JsonRequest request) {
		JSONArray items = request.getJSONArray("ids");
		for(int i = 0; i < items.size(); i++) {
			this.${classNameFirstLower}Service.removeById(items.getInteger(i));
		}
		return ServerResultDTO.newSuccess("删除成功");
	}
}

