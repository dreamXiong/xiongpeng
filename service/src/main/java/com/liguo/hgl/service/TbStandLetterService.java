package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.dto.LetterActivityDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbStandLetter;

public interface TbStandLetterService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbStandLetter selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbStandLetter> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbStandLetter record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbStandLetter record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbStandLetter record) throws RuntimeException;
    /**
     * 发送站内信
     * @param param
     * @return
     * @author zss
     * @param loginUserId 
     */
	int addLetter(Integer loginUserId, Map<String, Object> param);
	/**
	 * 经销商查询所有的站内信
	 * @param shopId
	 * @return
	 * @author zss
	 */
	List<LetterActivityDto> selectLetters(Integer shopId);

	/**
	 * 用户根据用户Id，查询对话信息
	 * @param uId
	 * @param loginUserId
	 * @return
	 * @author zss
	 * @param num 
	 */
	List<LetterActivityDto> getLetterByShopId(Integer uId, Integer loginUserId, Integer num);
	/**
	 * wap端发送消息
	 * @param param
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String, Object> addLetterByUser(Map<String, Object> param,
			Integer loginUserId);

	/**
	 * 最新消息数量
	 * @param uId
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	int selectNewLetter(Integer uId, Integer loginUserId);

	/**
	 * 查询我未读消息数量
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	int selectLetterCount(Integer loginUserId);
}