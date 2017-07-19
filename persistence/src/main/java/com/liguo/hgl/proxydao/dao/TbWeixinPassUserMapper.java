package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.exception.DataAlreadyModifyException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWeixinPassUserMapper extends BaseMapper {
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
    int insert(TbWeixinPassUser record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWeixinPassUser record);

    /**
     * 根据条件查询记录集
     */
    List<TbWeixinPassUser> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbWeixinPassUser selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWeixinPassUser record) throws DataAlreadyModifyException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWeixinPassUser record) throws DataAlreadyModifyException;

    /**
     * 获得指定数目的随机签到用户 排除掉没有头像没有昵称的用户
     * @param parameter
     * @return
     */
    List<TbWeixinPassUser> selectRandUser(Criteria parameter,PageDto pgo);

    /**
     * 批量修改多个用户的中奖状态
     * @param parameter
     * @return
     */
    int updateUserByPrimaryKeyArray(Criteria parameter);

    /**
     * 修改指定用户的中奖状态
     * @param winGrade 修改后的中奖状态
     * @return
     */
    int updateWinUserByWinGrade(Criteria parameter);

    List<TbWeixinPassUser> selectSignInUser(Integer dateCount);

    Integer selectCanErnieCount(Criteria parameter);
}