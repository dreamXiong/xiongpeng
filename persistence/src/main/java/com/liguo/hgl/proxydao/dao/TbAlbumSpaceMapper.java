package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAlbumSpace;

import java.util.List;

public interface TbAlbumSpaceMapper extends BaseMapper {
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
    int insert(TbAlbumSpace record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbAlbumSpace record);

    /**
     * 根据条件查询记录集
     */
    List<TbAlbumSpace> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbAlbumSpace selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbAlbumSpace record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbAlbumSpace record);
    
    
    List<TbAlbumSpace> findImageNowNameBatch(Criteria example) throws RuntimeException;
    
    /**
     * 检索图片名称
     */
    List<TbAlbumSpace> searchImage(Criteria example) throws RuntimeException;
    
    
    List<String> findImageNameOldAll(Criteria example)throws RuntimeException;
}