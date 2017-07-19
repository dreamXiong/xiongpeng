package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.dto.ActivityInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbActivityInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface TbActivityInfoService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbActivityInfo selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbActivityInfo> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbActivityInfo record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbActivityInfo record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbActivityInfo record) throws RuntimeException;
    
    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbActivityInfo record) throws RuntimeException;
    
    /*分页查询*/
    List<TbActivityInfo> selectObjectByPage(Criteria example,PageDto page) throws RuntimeException;
    
    /*插入记录*/
    int insertActivityInfo(ActivityInfoDto activityInfoDto,Integer createdBy) throws RuntimeException;
    
    /*查询ActivityInfoDto*/
    ActivityInfoDto selectActivityInfoDto(Integer id);
    
    /*修改ActivityInfoDto*/
    int updateActivityInfoDto(ActivityInfoDto activityInfoDto) throws RuntimeException;
    
    
    boolean uploadForm(MultipartFile file,String filename,String path) throws Exception;
    
}