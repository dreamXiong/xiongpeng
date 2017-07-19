package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.LetterActivityDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbStandLetter;
import java.util.List;

public interface TbStandLetterMapper extends BaseMapper {
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
    int insert(TbStandLetter record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbStandLetter record);

    /**
     * 根据条件查询记录集
     */
    List<TbStandLetter> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbStandLetter selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbStandLetter record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbStandLetter record);
    /**
     * 查询出的站内信
     * @param criteria
     * @return
     * @author zss
     */
	List<LetterActivityDto> selectLetters(Criteria criteria);
	/**
	 * 批量插入站内信消息
	 * @param letters
	 * @return
	 * @author zss
	 */
	int insertList(List<TbStandLetter> letters);
	/**
	 * 批量修改站内信状态，修改已读
	 * @param criteria
	 * @return
	 * @author zss
	 */
	int updateReservedState(Criteria criteria);
}