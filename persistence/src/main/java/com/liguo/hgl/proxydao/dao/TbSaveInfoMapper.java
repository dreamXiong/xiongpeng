package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.UserLetterDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbSaveInfoMapper extends BaseMapper {
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
    int insert(TbSaveInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbSaveInfo record);

    /**
     * 根据条件查询记录集
     */
    List<TbSaveInfo> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbSaveInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbSaveInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbSaveInfo record);
    
    TbSaveInfo selectSaveInfo(Criteria example);
    /*分页*/
    public List<TbSaveInfo> selectByObjectPage(Criteria criteria,PageDto pgo);

    /**
     * 个人店铺消息列表
     * @param criteria
     * @return
     * @author zss
     */
	List<UserLetterDto> getUserLetter(Criteria criteria);
	/**
	 * 店铺用户消息列表
	 * @param criteria
	 * @return
	 * @author zss
	 */
	List<UserLetterDto> getShopLetter(Criteria criteria);
    
}