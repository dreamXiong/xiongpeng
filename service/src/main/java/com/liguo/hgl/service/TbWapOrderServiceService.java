package com.liguo.hgl.service;


import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.dto.SubmitServiceOrderForm;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderService;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWapOrderServiceService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWapOrderService selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWapOrderService> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapOrderService record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapOrderService record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapOrderService record) throws RuntimeException;


    /**
     * 待接单服务  师傅登录
     * @param loginId  当前登录的webuserId
     * @return
     * @author zss
     * @param userInfoId 当前登录的userInfoId
     * @param lon 
     * @param lat 
     */
	List<WapOrderServiceDto> getService(Integer loginId, Integer userInfoId, String lon, String lat);
	/**
	 * 待接单服务  师傅登录(带分页)
	 * @param loginId
	 * @return
	 * @author zss
	 * @param userInfoId 
	 * @param lon 
	 * @param lat 
	 */
	List<WapOrderServiceDto> getService(Integer loginId, Integer userInfoId, String lon, String lat,PageDto page);

    /**
     * 提交服务订单
     * @param submitServiceOrderForm  用户选择的参数对象
     * @param userId  用户ID
     * @return
     */
    boolean submitOrder(SubmitServiceOrderForm submitServiceOrderForm,Integer userId);

    /**
     * 我的服务订单
     * @param loginUserId 当前登录用户id
     * @return
     * @author zss
     * @param typeId 用户类型
     * @param sfService 
     */
	List<WapOrderServiceDto> getMyService(Integer loginUserId, Integer typeId, Integer sfService);
	/**
	 * 我的服务订单(带分页)
	 * @param loginUserId
	 * @return
	 * @author zss
	 * @param typeId 
	 * @param sfService 
	 */
	List<WapOrderServiceDto> getMyService(Integer loginUserId, Integer typeId, Integer sfService,PageDto page);
	/**
	 * 师傅接单
	 * @param id
	 * @return
	 * @author zss
	 * @param userId 
	 */
	Map<String, Object> doMasterOrder(Integer id, Integer loginUserId);
	/**
	 * 取消订单
	 * @param id
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String, Object> doCancelOrder(Integer id, Integer loginUserId);
	/**
	 * 根据Id查询服务订单
	 * @param id
	 * @return
	 * @author zss
	 */
	WapOrderServiceDto selectById(Integer id);
	/**
	 * 修改服务订单
	 * @param submitServiceOrderForm
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	boolean updateOrderService(SubmitServiceOrderForm submitServiceOrderForm,
			Integer loginUserId);

	/**
	 * 师傅进行服务
	 * @param id
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String, Object> doOrderService(Integer id, Integer loginUserId);
	/**
	 * 师傅取消接单，服务挂起，恢复服务
	 * @param id
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String, Object> doAltService(Integer id, Integer loginUserId);
	/**
	 * 用户提交评论
	 * @param orderServiceId
	 * @param start
	 * @param evaluate
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	int saveEvaluate(Integer orderServiceId, String start, String evaluate,
			Integer loginUserId);
	
	/**
	 * 师傅完成服务，改价
	 * @param id
	 * @param loginUserId
	 * @param price
	 * @return
	 * @author zss
	 */
	Map<String, Object> doServicePrice(Integer id, Integer loginUserId,
			String price);
	
	/**
	 * 用户支付
	 * @param id
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String, Object> doPayService(Integer id, Integer loginUserId);
	/**
	 * 用户线上支付
	 * @param id
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String, Object> doOnlinePayService(Integer id, Integer loginUserId);
	/**
	 * 支付回调
	 */
	Map<String, Object> onlinPayResult(String orderNumber);
	/**
	 * 删除服务订单
	 * @param id
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String, Object> doDeleteService(Integer id, Integer loginUserId);
	/**
	 * 验证输入的价格是否低于城市最低服务价格
	 * @param id
	 * @param price
	 * @return
	 * @author zss
	 */
	Map<String, Object> checkCityLowerPrice(Integer id, String price);
	/**
	 * 
	 * <查询自己的待服务的订单>
	 * @param id
	 * @param price
	 * @return
	 * @author wzt
	 * @since   2016年7月1日
	 */
	List<TbWapOrderService> findMyOrderService(Integer userId);
	/**
	 * 
	 * <查询店铺自己的师傅>
	 * @param id
	 * @param price
	 * @return
	 * @author wzt
	 * @since   2016年7月1日
	 */
	List<Map<String, Object>> findMymaster(Integer id,Integer userId,PageDto pageDto);
	/**
	 * 
	 * <店铺更新服务的师傅>
	 * @param id
	 * @param price
	 * @return
	 * @author wzt
	 * @since   2016年7月1日
	 */
	int updateMaster(Integer id,Integer userId);
	
	
	/*查询未完成的订单*/
	int selectUnCompleteOrderCount(Criteria criteria) throws RuntimeException;

}