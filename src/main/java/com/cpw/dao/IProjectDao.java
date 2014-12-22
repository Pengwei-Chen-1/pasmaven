package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.Project;

/**
 * 项目管理Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-19 上午10:37:37
 */
public interface IProjectDao {

	/**
	 * 获取全部项目信息
	 * 
	 * @return 项目信息列表
	 */
	List<Project> getAllProject();

	/**
	 * 根据项目编号获取项目信息
	 * 
	 * @param projectId
	 *            项目编号
	 * @return 项目信息
	 */
	Project getProjectById(int projectId);

	/**
	 * 添加项目信息
	 * 
	 * @param project
	 *            项目信息
	 * @return 影响行数
	 */
	int addProject(Project project);

	/**
	 * 修改项目信息
	 * 
	 * @param project
	 *            项目信息
	 * @return 影响行数
	 */
	int updateProject(Project project);

	/**
	 * 删除项目信息
	 * 
	 * @param project
	 *            项目信息
	 * @return 影响行数
	 */
	int delProject(Project project);
}
