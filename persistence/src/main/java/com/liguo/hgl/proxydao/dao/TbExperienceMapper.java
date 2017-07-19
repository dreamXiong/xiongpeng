package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbExperience;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbExperienceMapper extends BaseMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByObject(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByObject(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbExperience record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbExperience record);

    /**
     * 根据条件查询记录集
     */
    List<TbExperience> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbExperience selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbExperience record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbExperience record);

    /**
     * 根据条件查询经验值
     * @param criteria
     * @param page
     * @return
     * @author zss
     */
	List<TbExperience> selectByCriteria(Criteria criteria, PageDto page);

	/**
	 * 拿到当前用户最新一条经验记录
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	TbExperience getMyExperience(Integer loginUserId);
}