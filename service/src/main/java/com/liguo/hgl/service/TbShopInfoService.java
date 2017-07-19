package com.liguo.hgl.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dto.ShopIndexForWapDto;
import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.dto.WapProductType;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbShopInfoService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbShopInfo selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbShopInfo> selectByObject(Criteria example) throws RuntimeException;

   
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbShopInfo record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbShopInfo record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbShopInfo record) throws RuntimeException;
    
    
    List<TbShopInfo> selectTbShopInfoForWap(double lon,double lat,String city);
    /**
     * 
     * <通过坐标查询店铺，根据等级筛选>
     * @param lon
     * @param lat
     * @param city
     * @return
     * @author wzt
     * @since   2016年6月23日
     */
    List<TbShopInfo> selectTbShopInfoForWapByGrade(double lon,double lat,String city);
    /**
     * 供应商（厂家）后台列表
     * @param criteria
     * @param page
     * @return
     * @author zss
     */
	List<ShopWebUserDto> getSupplierWebUserList(Criteria criteria, PageDto page);
	/**
	 * 经销商后台列表审核
	 * @param criteria
	 * @param page
	 * @return
	 * @author zss
	 */
	List<ShopWebUserDto> getDealersUserList(Criteria criteria, PageDto page);
	
	/**
	 * Wap店铺首页信息显示
	 * */
	ShopIndexForWapDto selectShopIndexInfo(Criteria criteria);
	
	/**
	 * @Description:查询产品下面所有的品牌和分类
	* @author:ZK 
	* @date:2016-7-18
	* @parameter:
	 */
	List<WapProductType> selectProductBrand(Criteria criteria);
	
	/**
	 * @Description:查询产品
	* @author:ZK 
	* @date:2016-7-18
	* @parameter:
	 */
	public List<TbWapProductDto> selectTbWapProductDtoList(Criteria criteria);
	/**
	 * 修改店铺返利设置
	 * @param id
	 * @param rebate
	 * @return
	 * @author zss
	 */
	Map<String, Object> updateEarnings(Integer id, double rebate);
	/**
	 * 自动确认订单
	 * @param id
	 * @return
	 * @author zss
	 */
	Map<String, Object> doAutomaticOder(Integer id);
	
	
	public int openSettlement(Integer shopId,String openType);
	
	/**
	 * 用户选择账户支付开通平台支付
	 * */
	public int getSellement(Integer ShopId,Integer accountId,String openType) throws TransactionException;
	/**
	 * 微信扫码支付得到结算权限
	 * 
	 * */
	public int weixinPaygetSettlement(String orderGroupNo,String accountId);
	
	public boolean uploadShopInfoImage(MultipartFile file,String fileName,String floder) throws IOException;
	
	Double subtractSettlementMoney(TbShopInfo tbShopInfo,Double payMoney) throws TransactionException;
	
	void rebatereCommended(TbShopInfo tbShopInfo);
	
	/**
	 * @Description:购买结算权限时所赠积分
	* @author:ZK 
	* @date:2016-8-4
	* @parameter:
	 */
	public void addShopIntegral(Integer shopId);
	
	/**
	 * @Description:购买结算权限时所赠积分
	* @author:ZK 
	* @date:2016-8-4
	* @parameter:
	 */
	public TbShopInfo selectTbShopInfoByUserId(Integer userId);
	
	/**
	 * @Description:附近的店
	* @author:ZK 
	* @date:2016-8-4
	* @parameter:
	 */
	List<TbShopInfo> selectNearTbShopInfo(Criteria example);
	
	Integer selectNearTbShopPageCount(Criteria example);
}