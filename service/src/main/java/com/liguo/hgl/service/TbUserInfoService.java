package com.liguo.hgl.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserInfo;

public interface TbUserInfoService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbUserInfo selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbUserInfo> selectByObject(Criteria example) throws RuntimeException;


    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbUserInfo record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbUserInfo record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbUserInfo record) throws RuntimeException;
    
    /*
     * 插入记录
     * */
    int insert(TbUserInfo record) throws RuntimeException;
    
	/*上传图片*/
	boolean uploadImage(MultipartFile file,String fileName,String floder) throws IOException;
}