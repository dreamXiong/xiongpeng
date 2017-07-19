package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAlbumSpace;

public interface TbAlbumSpaceService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbAlbumSpace selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbAlbumSpace> selectByObject(Criteria example) throws RuntimeException;


    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbAlbumSpace record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbAlbumSpace record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbAlbumSpace record) throws RuntimeException;
    /**
     * 根据图片名称批量查询
     */
    List<TbAlbumSpace> findImageNowNameBatch(Criteria example) throws RuntimeException;
    
    /**
     * 模糊查询图片
     */
    List<TbAlbumSpace> searchImage(Criteria example) throws RuntimeException;
    
    /**
     * 根据指定条件查询图片名称返回一个List<String>
     */
    List<String> findImageNameOldAll(Criteria example) throws RuntimeException;
    
    /**
     * 根据指定条件查询图片名称返回一个List<String>
     */
    void albumSpaceUpload(WebUserDto webUserDto,HttpServletRequest request,Map<String,String> map) throws RuntimeException;
    
    
    void alterImageName(TbAlbumSpace tbAlbumSpace,WebUserDto webUserDto) ;
    
    
    
}