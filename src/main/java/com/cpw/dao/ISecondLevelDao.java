package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.SecondLevel;

/**
 * 二级分类Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-19 上午10:38:55
 */
public interface ISecondLevelDao {

	/**
	 * 获取全部二级分类信息
	 * @return 二级分类信息列表
	 */
	List<SecondLevel> getAllSecondLevel();

	/**
	 * 根据二级分类编号获取二级分类信息
	 * @param secondLevelId 二级分类编号
	 * @return 二级分类信息
	 */
	SecondLevel getSecondLevelById(int secondLevelId);

	/**
	 * 添加二级分类
	 * @param secondLevel 二级分类信息
	 * @return 影响行数
	 */
	int addSecondLevel(SecondLevel secondLevel);

	/**
	 * 修改二级分类信息
	 * @param secondLevel 二级分类信息
	 * @return 影响行数
	 */
	int updateSecondLevel(SecondLevel secondLevel);

	/**
	 * 删除二级分类
	 * @param secondLevel 二级分类信息
	 * @return 影响行数
	 */
	int delSecondLevel(SecondLevel secondLevel);
}
