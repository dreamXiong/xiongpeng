package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.dto.UserLetterDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.CustomerServiceDto;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbSaveInfoService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbSaveInfo selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbSaveInfo> selectByObject(Criteria example) throws RuntimeException;


    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbSaveInfo record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbSaveInfo record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbSaveInfo record) throws RuntimeException;
    
    /*分页*/
    public List<TbSaveInfo> selectByObjectPage(Criteria example,PageDto page) throws RuntimeException;
    
    /*产品收藏*/
    int insertProductSelective(TbProductDto productDto,Integer createdBy) throws RuntimeException;
    
    /*wap端产品的收藏*/
    int insertWapProduct(TbWapProductDto wapProductDto,Integer createdBy) throws RuntimeException;
    
    /*店铺收藏*/
    int insertShopInfoSelective(TbShopInfo shopInfo,Integer createdBy) throws RuntimeException;
    
    /*wap端店铺的收藏*/
    int insertWapShopInfo(TbShopInfo shopInfo,Integer createdBy,Integer bind) throws RuntimeException;
    
    int insertMerchantSelective(Integer brandId,Integer createdBy) throws RuntimeException;
    
    /*师傅的收藏*/
    int insertWorkSelective(TbWebUser webUser);
    
    /*服务的收藏*/
    int insertServiceSelective(CustomerServiceDto serviceDto,Integer createdBy) throws RuntimeException;
    
    /*判断店铺、产品是否已经被收藏*/
    public TbSaveInfo selectSaveInfo(Integer saveId,Integer saveType,Integer createdBy) throws RuntimeException;

    /**
     * 用户消息列表：当前登录用户的
     * @param loginUserId
     * @return
     * @author zss
     */
	List<UserLetterDto> getUserLetter(Integer loginUserId);
	
	
	TbSaveInfo selectDefaultBindShop(Integer createdBy) throws RuntimeException;
    
	
	/**
	 * 店铺的收藏和取消收藏
	 * @param shopId  店铺id
	 * @param typeId  收藏或取消(1表示收藏 ,0表示取消收藏)
	 * @param createdBy 操作人id
	 * @return
	 * @throws RuntimeException
	 */
	String doOperateShopSaveInfo(Integer shopId,Integer typeId,Integer createdBy) throws RuntimeException;
	
	/**
	 * WAP端店铺绑定
	 * @param shopId  店铺id
	 * @param webUserDto  绑定用户
	 * @return
	 */
	Integer doBindShopInfo(String recommendCode,Integer shopId,WebUserDto webUserDto);
	
	/**
	 * WAP端店铺绑定
	 * @param shopId  店铺id
	 * @param createdBy  绑定用户id
	 */
	void doBindShopInfo(Integer shopId, Integer createdBy);
	
	
	/**
	 * 
	 * @param serviceId 服务id
	 * @param typeId 操作类型(1:收藏,0:取消收藏)
	 * @param createdBy 收藏人id
	 * @return
	 */
	String doWapOperateServiceSaveInfo(Integer serviceId,Integer typeId,Integer createdBy);
    
}