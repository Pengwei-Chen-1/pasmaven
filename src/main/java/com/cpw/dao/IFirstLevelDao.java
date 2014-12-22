package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.FirstLevel;

/**
 * 一级分类管理Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-19 上午9:53:18
 */
public interface IFirstLevelDao {

	/**
	 * 获取全部一级分类信息
	 * 
	 * @return 一级分类信息列表
	 */
	List<FirstLevel> getAllFirstLevel();

	/**
	 * 根据一级分类编号获取一级分类信息
	 * 
	 * @param firstLevelId
	 *            一级分类编号
	 * @return 一级分类信息
	 */
	FirstLevel getFirstLevelById(int firstLevelId);

	/**
	 * 添加一级分类
	 * 
	 * @param firstLevel
	 *            一级分类信息
	 * @return 影响行数
	 */
	int addFirstLevel(FirstLevel firstLevel);

	/**
	 * 修改一级分类信息
	 * 
	 * @param firstLevel
	 *            一级分类信息
	 * @return 影响行数
	 */
	int updateFirstLevel(FirstLevel firstLevel);

	/**
	 * 删除一级分类
	 * 
	 * @param firstLevel
	 *            一级分类信息
	 * @return 影响行数
	 */
	int delFirstLevel(FirstLevel firstLevel);
}
