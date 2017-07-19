package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbBrandService {
	
		 /**
	     * 根据条件获取总行数
	     */
		public int countByObject(Criteria example) throws RuntimeException ;
		  /**
	     * 根据主键查询记录
	     */
	    public TbBrand selectByPrimaryKey(Integer id) throws RuntimeException ;
	    /**
	     * 根据指定条件查询记录
	     */
	    public List<TbBrand> selectByObject(Criteria example) throws RuntimeException ;
	    /**
	     * 根据主键删除记录
	     */
	    public int deleteByPrimaryKey(Integer id) throws RuntimeException ;

	    /**
	     * 根据主键更新属性不为空的记录
	     */
	    public int updateByPrimaryKeySelective(TbBrand record) throws RuntimeException ;
	    
	    /**
	     * 根据主键更新记录
	     */
	    public int updateByPrimaryKey(TbBrand record) throws RuntimeException ;

	    /**
	     * 保存属性不为空的记录
	     */
	    public int insertSelective(TbBrand record) throws RuntimeException;
	    
	    /**
	     * 保存属性不为空的记录
	     */
	    public int insert(TbBrand record) throws RuntimeException;
	    
	    /**
	    * @Description:品牌分页
	    * @author:ZK 
	    * @date:2016-7-18
	    * @parameter:
	    * @return:String
	     */
	    public List<TbBrand> selectByObject(Criteria example,PageDto pgo) throws RuntimeException ;
	    
	    /**
	     * 根据指定条件查询记录
	     */
	    public List<TbBrand> selectByTbBrand(TbBrand tbBrand) throws RuntimeException ;
	    /**
	     * web端用户添加自有品牌
	     * @param param
	     * @return
	     * @author zss
	     * @param userId 
	     */
		public int saveBrandByUser(Map<String, Object> param, Integer userId);
		
		/***
		 * 
		 * <查询所有品牌id,得到结果仅仅是id>
		 * @param example
		 * @return
		 * @author wzt
		 * @since   2016年6月6日
		 */
		List<ProductImport> findBrandIdAll(Criteria example);
}
