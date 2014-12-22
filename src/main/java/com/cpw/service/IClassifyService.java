package com.cpw.service;

import java.util.List;

import com.cpw.dto.TemplateDto;
import com.cpw.pojo.Template;

/**
 * 分类设置服务类
 * 
 * @author pengwei_chen
 * @date 2014-4-14 下午6:18:37
 */
public interface IClassifyService {

	/**
	 * 添加模板信息
	 * 
	 * @param template
	 *            模板信息
	 */
	void addTemplate(Template template);

	/**
	 * 获取全部模板信息
	 * 
	 * @return
	 */
	List<TemplateDto> getAllTemplateDto();

	/**
	 * 根据模板编号删除模板
	 * 
	 * @param templateId
	 *            模板编号
	 */
	void delTemplateById(int templateId);

	/**
	 * 根据商家编号删除商家
	 * 
	 * @param busId
	 *            商家编号
	 * @return
	 */
	boolean delBussiness(int busId);

	/**
	 * 删除项目
	 * 
	 * @param proId
	 *            项目编号
	 * @return
	 */
	boolean delProject(int proId);

	/**
	 * 删除成员
	 * 
	 * @param memberId
	 *            成员编号
	 * @return
	 */
	boolean delMember(int memberId);

	/**
	 * 删除一级支出分类
	 * 
	 * @param outId
	 *            支出编号
	 * @return
	 */
	boolean delFirstOut(int outId);

	/**
	 * 删除一级收入分类
	 * 
	 * @param inId
	 *            收入编号
	 * @return
	 */
	boolean delFirstIn(int inId);

	/**
	 * 删除二级支出分类
	 * 
	 * @param outId
	 *            支出编号
	 * @return
	 */
	boolean delSecondOut(int outId);

	/**
	 * 删除二级收入分类
	 * 
	 * @param inId
	 *            收入编号
	 * @return
	 */
	boolean delSecondIn(int inId);

	/**
	 * 获取分类信息
	 * 
	 * @param obj
	 * @param id
	 * @return
	 */
	Object getClassifyById(Object obj, int id);

	/**
	 * 更新类型
	 * 
	 * @param obj
	 *            类型信息
	 */
	void updateClassify(Object obj);

	/**
	 * 更新模板信息
	 * 
	 * @param template
	 *            模板信息
	 */
	void updateTemplate(Template template);

	/**
	 * 获取支出类型的模板列表
	 * 
	 * @return 模板列表
	 */
	List<Template> getSpendingTemplate();

	/**
	 * 获取收入类型模板列表
	 * 
	 * @return 模板列表
	 */
	List<Template> getIncomeTemplate();
}
