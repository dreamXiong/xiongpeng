/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.springframework.web.multipart.MultipartFile;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbProductType;


public interface IProductTypeService {
	/**
	 * 添加分类
	 * @param name
	 * @param describe
	 * @param parentId
	 * @param mainId
	 * @param userId
	 * @param isAdmin
	 * @return
	 * @author zss
	 */
	public int insert(String name,String describe,Integer parentId,Integer mainId, Integer userId, boolean isAdmin);
    List<TbProductType> selectByTbProductType(TbProductType tbProductType);
    
    public TbProductType selectByPrimaryKey(Integer id);
    
    public int updateByPrimaryKey(TbProductType tbProductType);
    
    public int deleteByPrimaryKey(Integer id);
    
    /**
    * @Description:二级分类更新时，级联更新三级分类
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return: int
     */
    public int updateProductTyppeByPrimaryKeyArray(Integer mainId,List<TbProductType> listP);

    
    /**
     * @author zhoushangtao
     * 获取商品类别 
     * @return
     */
    public List<TbProductType> getProductType();
    

    /**
     * @Description:品牌图片更新
     * @author:ZK 
     * @date:2016-7-18
     * @parameter:
     * @return: int
      */
    public boolean uploadForm(MultipartFile file, String filename) throws Exception ;
    
    /***
     * 
     * <自定义上传目录>
     * @param file
     * @param filename
     * @param path
     * @return
     * @throws Exception
     * @author wzt
     * @since   2016年7月9日
     */
    public boolean uploadForm(MultipartFile file, String filename,String path) throws Exception ;
    
    /***
     * 
     * <生成小图片的上传>
     * @param file
     * @param filename
     * @param path
     * @param suffix
     * @param minImageSavePath
     * @return
     * @throws Exception
     * @author wzt
     * @since   2016年7月9日
     */
    public boolean uploadForm(MultipartFile file, String filename,String path,String suffix,String minImageSavePath) throws Exception ;
    
    /**
     * 根据品牌的类别Id查询对应的品名
     * @param producttypeId
     * @author zss
     */
	public List<TbProductType> selectProductTypeByMinId(Integer producttypeId);
	
	/**上传ZIP内的文件  
     * @param stream   zip流
     * @param path     保存在服务器上的路径
     * @param filename 保存的文件名称 
	 * @author wzt
	 * @since   2016年6月2日
	 */
	public void SaveFileFromZipInputStream(ZipInputStream stream,String path,String filename)throws IOException;
	
	/***
	 * 
	 * <解压RAR文件>
	 * @param srcRarPath           RAR文件所在路径，包含文件名称
	 * @param dstDirectoryPath     解压路径
	 * @throws IOException
	 * @author wzt
	 * @since   2016年6月2日
	 */
	public void SaveFileFromRarInputStream(Archive a,FileHeader fh, String dstDirectoryPath,String fileName) throws IOException ;
	
	/***
	 * 
	 * <查询所有非父节点的节点>
	 * @param criteria
	 * @return
	 * @author wzt
	 * @since   2016年7月18日
	 */
	public List<ProductImport> findNotParentId(Criteria criteria) ;
	
	/***
	 * 
	 * <查询所有非父节点的节点数量>
	 * @return
	 * @author wzt
	 * @since   2016年7月18日
	 */
	public int findNotParentIdCount() ;
	
	/**
	 * 
	 * 附近的店铺类型
	 * */
	public List<TbProductType> selectNearShopType();
}