package com.liguo.hgl.service;

import java.io.IOException;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapProduct;
import com.liguo.hgl.proxydao.model.WapProductInfoDto;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWapProductService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWapProduct selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWapProduct> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapProduct record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapProduct record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapProduct record) throws RuntimeException;
    
    /**
     * 根据id查询
     * @param id
     * @return
     * @throws RuntimeException
     */
    TbWapProductDto selectWapProductDtoByPrimaryKey(Integer id) throws RuntimeException;
    
    /**
     * 根据条件查询返回集合,分页
     * @param criteria
     * @param pgo
     * @return
     * @throws RuntimeException
     */
    List<TbWapProductDto> selectByCriteria(Criteria criteria, PageDto pgo) throws RuntimeException; 
    
    /**
     * 根据条件查询返回集合
     * @param criteria
     * @return
     * @throws RuntimeException
     */
    List<TbWapProductDto> selectByCriteria(Criteria criteria)throws RuntimeException;
    
    /**
     * 根据条件查询
     * @param criteria
     * @param page
     * @return
     */
    List<WapProductInfoDto> selectInfoListByName(Criteria criteria,PageDto page);
    
    /**
     * 查询更新信息
     * @param criteria
     * @return
     */
    WapProductInfoDto selectUpdateInfo(Criteria criteria);
    
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(TbWapProduct record);
    
    /**
     * 选择插入
     * @param tbWapProduct
     * @return
     */
    public int insertTbWapProduct(TbWapProduct tbWapProduct);
    
    /**
     * 查询产品列表
     * @param c
     * @return
     */
    public List<TbWapProductDto> selectTbWapPickList(Criteria c);
    
    /***
     * 
     * <批量添加产品>
     * @param objList
     * @author wzt
     * @since   2016年7月18日
     */
    public void insertBatchProduct(List<TbWapProduct> objList);
    
    public void importWapProductBatch(Integer userId,MultipartFile file ,Model model)throws IOException ;
    
}