package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.Template;

/**
 * 模板Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-19 上午10:40:16
 */
public interface ITemplateDao {

	/**
	 * 获取全部模板信息
	 * 
	 * @return 模板信息列表
	 */
	List<Template> getAllTemplate();

	/**
	 * 根据模板编号获取模板信息
	 * 
	 * @param templateId
	 *            模板编号
	 * @return 模板信息
	 */
	Template getTemplateById(int templateId);

	/**
	 * 添加模板信息
	 * 
	 * @param template
	 *            模板信息
	 * @return 影响行数
	 */
	int addTemplate(Template template);

	/**
	 * 修改模板信息
	 * 
	 * @param template
	 *            模板信息
	 * @return 影响行数
	 */
	int updateTemplate(Template template);

	/**
	 * 删除模板信息
	 * 
	 * @param template
	 *            模板信息
	 * @return 影响行数
	 */
	int delTemplate(Template template);
	
	/**
	 * 根据条件获取模板列表
	 * @param term 条件
	 * @return 模板列表
	 */
	List<Template> getTemplate(String term);
}
