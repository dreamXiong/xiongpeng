package com.liguo.hgl.service.impl;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbCityInfoMapper;
import com.liguo.hgl.proxydao.dao.TbCityLowestPriceMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbUserInfoMapper;
import com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper;
import com.liguo.hgl.proxydao.dao.TbWapAddressMapper;
import com.liguo.hgl.proxydao.dao.TbWapOrderServiceMapper;
import com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.BaiduMapDto;
import com.liguo.hgl.proxydao.dto.SubmitServiceOrderForm;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityLowestPrice;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWapOrderService;
import com.liguo.hgl.proxydao.model.TbWapOrderTrackingService;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.service.TbWapOrderServiceService;

@Service
@Scope(value="prototype")
public class TbWapOrderServiceServiceImpl implements TbWapOrderServiceService {
    @Autowired
    private TbWapOrderServiceMapper tbWapOrderServiceMapper;
    
    @Autowired
    protected TbUserInfoMapper tbUserInfoMapper;
    @Autowired
    private TbWapAddressMapper tbWapAddressMapper;
    @Autowired
    private TbWapAddressHistoryMapper tbWapAddressHistoryMapper;
	@Autowired
	protected TbWapOrderGroupService tbWapOrderGroupService;
	@Autowired
	protected TbWapOrderTrackingServiceMapper tbWapOrderTrackingServiceMapper;
	@Autowired
	protected TbCityLowestPriceMapper tbCityLowestPriceMapper;
	@Autowired
	private TbCityInfoMapper tbCityInfoMapper;
	@Autowired
	private TbWebUserMapper tbWebUserMapper;
	@Autowired
	private TbShopInfoMapper tbShopInfoMapper;
	
	
	

    private static final Logger logger = LoggerFactory.getLogger(TbWapOrderServiceServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapOrderServiceMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWapOrderService selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapOrderServiceMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWapOrderService> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapOrderServiceMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapOrderServiceMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWapOrderService record) throws RuntimeException {
        try {
            return this.tbWapOrderServiceMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWapOrderService record) throws RuntimeException {
        try {
            return this.tbWapOrderServiceMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWapOrderService record) throws RuntimeException {
        try {
            return this.tbWapOrderServiceMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


   
    /**
     * 提交服务订单
     */
    public boolean submitOrder(SubmitServiceOrderForm submitServiceOrderForm,Integer userId){
    	if(submitServiceOrderForm != null){
    		//根据选择的收货地址ID查询
			Integer addressHistoryId = tbWapOrderGroupService.modifyAddressHistory(submitServiceOrderForm.getAddressId(),submitServiceOrderForm.getPhone(),submitServiceOrderForm.getRecipient(),userId);
    		TbWapOrderService wapOrderService = new TbWapOrderService();
    		wapOrderService.setOrderSerialNo(tbWapOrderGroupService.makeOrderNum("F"));
    		wapOrderService.setMasterId(userId);
    		wapOrderService.setServiceTypeId(submitServiceOrderForm.getServiceTypeId());
    		wapOrderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_800);
    		wapOrderService.setCreateDt(System.currentTimeMillis());
    		wapOrderService.setAppointmentDt(submitServiceOrderForm.getAppointment());
    		wapOrderService.setAppointmentPeriodDt(submitServiceOrderForm.getAppointmentPeriod());
    		wapOrderService.setAddressId(addressHistoryId);
    		wapOrderService.setOrderType(HglContants.ORDSERVICE_ORDER);
    		wapOrderService.setMessage(submitServiceOrderForm.getMessage());
    		wapOrderService.setCityCode(submitServiceOrderForm.getCityCode());
    		int insCount = this.tbWapOrderServiceMapper.insert(wapOrderService);
    		logger.info("服务订单插入记录: " + insCount);
    		
    		TbWapOrderTrackingService wapOrderTrackingService = new TbWapOrderTrackingService();
    		wapOrderTrackingService.setOrderServiceId(wapOrderService.getId());
    		wapOrderTrackingService.setOperateName("客户已下单");
    		wapOrderTrackingService.setOperateBy(userId);
    		wapOrderTrackingService.setOrderState(HglContants.WAP_ORDER_SERVICE_STATE_800);
    		wapOrderTrackingService.setOperateDt(System.currentTimeMillis());
    		int trackingCount = tbWapOrderTrackingServiceMapper.insert(wapOrderTrackingService);
    		logger.info("服务订单跟踪记录表插入记录: " + trackingCount);
    		if(trackingCount>0){
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * 待接单服务  师傅登录
     */
    @Override
    public List<WapOrderServiceDto> getService(Integer loginId,Integer userInfoId,String lon, String lat) {
        TbUserInfo tbUserInfo =tbUserInfoMapper.selectByPrimaryKey(userInfoId);
         Criteria parameter = new Criteria();
        if(StringUtils.isNotBlank(tbUserInfo.getServiceType())){
            List<String> serviceTypeIds = new ArrayList<String>();
            String[] sourceStrArray = tbUserInfo.getServiceType().split(",");
            for (int i = 0; i < sourceStrArray.length; i++) {
                serviceTypeIds.add(sourceStrArray[i]);
            }
           
            parameter.put("serviceTypeIds", serviceTypeIds);
          
        } 
        //如果经度或纬度一个为空，单独一个不为空没有任何意义
        if(StringUtil.isNotBlank(lon)&&StringUtil.isNotBlank(lat)){
            double  lon_dou =Double.parseDouble(lon);
            double  lat_dou =Double.parseDouble(lat);
            parameter.put("lon",  lon_dou);
            parameter.put("lat",  lat_dou);
            
            //定位
            try {
                BaiduMapDto mapDto =GaoDeMapUtil.selectAddBycoordinate(lon_dou,lat_dou);
                String city =mapDto.getRegeocode().getAddressComponent().getCity();
                Criteria criteria = new Criteria();
                criteria.put("cityName", city);
                criteria.setMysqlLength(1);
                criteria.setMysqlOffset(0);
                String cityId =tbCityInfoMapper.searchCity(criteria);
                if(StringUtil.isNotBlank(cityId)){
                    parameter.put("cityCode", cityId);
                }
            }
            catch (IOException e) {
                logger.error("GaoDeMapUtil is Location fail error message: " + e.getMessage());
                
            }
        }
        parameter.put("orderStatus", HglContants.WAP_ORDER_SERVICE_STATE_800);//待接单
        parameter.put("notIsmaster", loginId);//排除师傅自己发的服务订单
        List<WapOrderServiceDto> serviceDtos = tbWapOrderServiceMapper.selectByCriteria(parameter);
        return serviceDtos;
    }
	/**
	 * 待接单服务  师傅登录 (带分页)
	 */
	@Override
	public List<WapOrderServiceDto> getService(Integer loginId,Integer userInfoId,String lon, String lat,PageDto page) {
	    TbShopInfo tbShopInfo =tbShopInfoMapper.selectByPrimaryKey(userInfoId);
	    Criteria parameter = new Criteria();
	    if(null!=tbShopInfo&&StringUtils.isNotBlank(tbShopInfo.getServiceType())){
	        List<String> serviceTypeIds = new ArrayList<String>();
	        String[] sourceStrArray = tbShopInfo.getServiceType().split(",");
	        for (int i = 0; i < sourceStrArray.length; i++) {
	            serviceTypeIds.add(sourceStrArray[i]);
	        }
	        
	        parameter.put("serviceTypeIds", serviceTypeIds);
	        
	    }
	    //如果经度或纬度一个为空，单独一个不为空没有任何意义
	    if(StringUtil.isNotBlank(lon)&&StringUtil.isNotBlank(lat)){
	        double  lon_dou =Double.parseDouble(lon);
	        double  lat_dou =Double.parseDouble(lat);
	        parameter.put("lon",  lon_dou);
	        parameter.put("lat",  lat_dou);
	        
	        //定位
	        try {
	            BaiduMapDto mapDto =GaoDeMapUtil.selectAddBycoordinate(lon_dou,lat_dou);
	            String city =mapDto.getRegeocode().getAddressComponent().getCity();
	            Criteria criteria = new Criteria();
	            criteria.put("cityName", city);
	            criteria.setMysqlLength(1);
	            criteria.setMysqlOffset(0);
	            String cityId =tbCityInfoMapper.searchCity(criteria);
	            if(StringUtil.isNotBlank(cityId)){
	                parameter.put("cityCode", cityId);
	            }
	        }
	        catch (IOException e) {
	            logger.error("GaoDeMapUtil is Location fail error message: " + e.getMessage());
	            
	        }
	    }
	    parameter.put("orderStatus", HglContants.WAP_ORDER_SERVICE_STATE_800);//待接单
	    parameter.put("notIsmaster", loginId);//排除师傅自己发的服务订单
	    parameter.setMysqlLength(page.getPageSize());
	    parameter.setMysqlOffset(page.getPageIndex());
	    List<WapOrderServiceDto> serviceDtos = tbWapOrderServiceMapper.selectByCriteria(parameter,page);
	    return serviceDtos;
	}

    /**
     * 我的服务
     */
	@Override
	public List<WapOrderServiceDto> getMyService(Integer loginUserId,Integer typeId, Integer sfService) {
		 Criteria parameter = new Criteria();
		 if(typeId.equals(HglContants.USER_TYPE_MASTER)||typeId.equals(HglContants.USER_TYPE_DISTRIBUTOR)){
			 if(sfService !=null &&sfService>0){
				 parameter.put("masterId", loginUserId);
				 parameter.put("masterShow", HglContants.SERVICE_MASTERSHOW);
			 }else{
				 parameter.put("repairmanId", loginUserId);
				 parameter.put("repairmanShow", HglContants.SERVICE_REPAIRMANSHOW);
			 }
		 }else if(typeId.equals(HglContants.USER_TYPE_CUS)){
			 parameter.put("masterId", loginUserId);
			 parameter.put("masterShow", HglContants.SERVICE_MASTERSHOW);
		 }
		/*if(StringUtils.isNotBlank(lon)){
			parameter.put("lon",  Double.parseDouble(lon));
		}
		 if(StringUtil.isNotBlank(lat)){
			 parameter.put("lat",  Double.parseDouble(lat));
		 }*/
		 List<WapOrderServiceDto> serviceDtos = tbWapOrderServiceMapper.selectByCriteria(parameter);
		 for (WapOrderServiceDto wapOrderServiceDto : serviceDtos) {
			if(wapOrderServiceDto.getMasterId().equals(loginUserId)){
				wapOrderServiceDto.setCustomer(true);
			}
		}
		 
		 
		return serviceDtos;
	}
	/**
	 * 我的服务(带分页)
	 */
	@Override
	public List<WapOrderServiceDto> getMyService(Integer loginUserId,Integer typeId, Integer sfService,PageDto page) {
	    Criteria parameter = new Criteria();
	    if(typeId.equals(HglContants.USER_TYPE_MASTER)||typeId.equals(HglContants.USER_TYPE_DISTRIBUTOR)){
	        if(sfService !=null &&sfService>0){
	            parameter.put("masterId", loginUserId);
	            parameter.put("masterShow", HglContants.SERVICE_MASTERSHOW);
	        }else{
	            parameter.put("repairmanId", loginUserId);
	            parameter.put("repairmanShow", HglContants.SERVICE_REPAIRMANSHOW);
	        }
	    }else if(typeId.equals(HglContants.USER_TYPE_CUS)){
	        parameter.put("masterId", loginUserId);
	        parameter.put("masterShow", HglContants.SERVICE_MASTERSHOW);
	    }
	    /*if(StringUtils.isNotBlank(lon)){
			parameter.put("lon",  Double.parseDouble(lon));
		}
		 if(StringUtil.isNotBlank(lat)){
			 parameter.put("lat",  Double.parseDouble(lat));
		 }*/
	    List<WapOrderServiceDto> serviceDtos = tbWapOrderServiceMapper.selectByCriteria(parameter,page);
	    for (WapOrderServiceDto wapOrderServiceDto : serviceDtos) {
	        if(wapOrderServiceDto.getMasterId().equals(loginUserId)){
	            wapOrderServiceDto.setCustomer(true);
	        }
	    }
	    
	    
	    return serviceDtos;
	}
	/**
	 * 师傅接单 TODO 
	 */
	@Override
	public Map<String, Object> doMasterOrder(Integer id, Integer loginUserId) {
		TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(id);
		int count=0;
		if(orderService !=null && orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_800)){
			orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_802);
			orderService.setRepairmanId(loginUserId);
			
			boolean isInsertTracking = insertTracking(loginUserId, orderService.getId(),"师傅接单",HglContants.WAP_ORDER_SERVICE_STATE_802);
			if(isInsertTracking){
				count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
			}
		}
		return getReslutMap(orderService, count);
	}

	/**
	 * 客户取消订单
	 */
	@Override
	public Map<String, Object> doCancelOrder(Integer id, Integer loginUserId) {
		TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(id);
		int count=0;
		if(orderService !=null && orderService.getMasterId().equals(loginUserId)){
			orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_814);
			orderService.setCancelDt(System.currentTimeMillis());
			boolean isInsertTracking = insertTracking(loginUserId, orderService.getId(),"取消订单",HglContants.WAP_ORDER_SERVICE_STATE_814);
			if(isInsertTracking){
				count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
			}
		}
		return getReslutMap(orderService, count);
	}
	/**
	 * 改变服务状态的map值
	 * @param orderService
	 * @param count
	 * @return
	 * @author zss
	 */
	private Map<String, Object> getReslutMap(TbWapOrderService orderService,
			int count) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(count >0 ){
			
			map.put("status", orderService.getOrderStatus());
			map.put("errcode", "0");
			map.put("msg", "成功！");
		}else{
			map.put("errcode", "1");
			map.put("msg", "失败！");
		}
		return map;
	}

	@Override
	public WapOrderServiceDto selectById(Integer id) {
		 Criteria parameter = new Criteria();
	     parameter.put("id", id);
		 List<WapOrderServiceDto> serviceDtos = tbWapOrderServiceMapper.selectByCriteria(parameter);
		 if(serviceDtos.size() > 0){
			 return serviceDtos.get(0);
		 }
		 return null;
	}

	@Override
	public boolean updateOrderService(
			SubmitServiceOrderForm submitServiceOrderForm, Integer loginUserId) {
		if(submitServiceOrderForm != null && submitServiceOrderForm.getOderServiceId()>0){
    		//根据选择的收货地址ID查询
			Integer addressHistoryId = tbWapOrderGroupService.modifyAddressHistory(submitServiceOrderForm.getAddressId(),submitServiceOrderForm.getPhone(),submitServiceOrderForm.getRecipient(),loginUserId);
    		TbWapOrderService wapOrderService = tbWapOrderServiceMapper.selectByPrimaryKey(submitServiceOrderForm.getOderServiceId());
    		if(!StringUtils.isEmpty(submitServiceOrderForm.getAppointmentPeriod())){
	    		wapOrderService.setAppointmentDt(submitServiceOrderForm.getAppointment());
	    		wapOrderService.setAppointmentPeriodDt(submitServiceOrderForm.getAppointmentPeriod());
    		}
    		wapOrderService.setAddressId(addressHistoryId);
    		wapOrderService.setMessage(submitServiceOrderForm.getMessage());
    		wapOrderService.setCityCode(submitServiceOrderForm.getCityCode());
    		int insCount = this.tbWapOrderServiceMapper.updateByPrimaryKeySelective(wapOrderService);
    		logger.info("更新服务订单记录: " + insCount);
    		 return insertTracking(loginUserId, wapOrderService.getId(),"客户修改订单消息",wapOrderService.getOrderStatus());
    		
    	}
    	return false;
	}
	/**
	 * 插入订单详细流程
	 * @param loginUserId 当前登录用户id
	 * @param wapOrderServiceId 服务id
	 * @param msg 操作描述
	 * @param status 状态
	 * @return
	 * @author zss
	 */
	private boolean insertTracking(Integer loginUserId,
			Integer wapOrderServiceId,String msg,Integer status) {
		TbWapOrderTrackingService wapOrderTrackingService = new TbWapOrderTrackingService();
		wapOrderTrackingService.setOrderServiceId(wapOrderServiceId);
		wapOrderTrackingService.setOperateName(msg);
		wapOrderTrackingService.setOperateBy(loginUserId);
		wapOrderTrackingService.setOrderState(status);
		wapOrderTrackingService.setOperateDt(System.currentTimeMillis());
		int trackingCount = tbWapOrderTrackingServiceMapper.insert(wapOrderTrackingService);
		logger.info("服务订单跟踪记录表插入记录: " + trackingCount);
		if(trackingCount>0){
			return true;
		}
		return false;
	}

	/**
	 * 师傅服务操作，开始服务，确认收款
	 */
	@Override
	public Map<String, Object> doOrderService(Integer id, Integer loginUserId) {
		TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(id);
		int count=0;
		if(orderService !=null && orderService.getRepairmanId().equals(loginUserId)){
			if(orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_802)){
				orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_804);//开始服务
				boolean isInsertTracking = insertTracking(loginUserId, orderService.getId(),"师傅开始服务",HglContants.WAP_ORDER_SERVICE_STATE_804);
				if(isInsertTracking){
					count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
				}
			}else if(orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_816)){//待确认
				orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_810);//待评价
				boolean isInsertTracking = insertTracking(loginUserId, orderService.getId(),"师傅确认收款",HglContants.WAP_ORDER_SERVICE_STATE_810);
				if(isInsertTracking){
					count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
				}
			}
		}
		return getReslutMap(orderService, count);
	}

	/**
	 * 师傅取消接单，服务挂起，恢复服务
	 */
	@Override
	public Map<String, Object> doAltService(Integer id, Integer loginUserId) {
		TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(id);
		int count=0;
		if(orderService !=null && orderService.getRepairmanId().equals(loginUserId)){
			if(orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_802)){//待服务
				orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_800);//待接单
				boolean isInsertTracking = insertTracking(loginUserId, orderService.getId(),"师傅取消服务",HglContants.WAP_ORDER_SERVICE_STATE_800);
				if(isInsertTracking){
					count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
				}
			}else if(orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_804)){//
				orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_806);//服务挂起
				boolean isInsertTracking = insertTracking(loginUserId, orderService.getId(),"服务挂起，等待师傅继续服务",HglContants.WAP_ORDER_SERVICE_STATE_808);
				if(isInsertTracking){
					count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
				}
			}else if(orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_806)){
				orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_804);//服务挂起
				boolean isInsertTracking = insertTracking(loginUserId, orderService.getId(),"恢复服务，师傅正在服务",HglContants.WAP_ORDER_SERVICE_STATE_804);
				if(isInsertTracking){
					count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
				}
			}
		}
		return getReslutMap(orderService, count);
	}

	@Override
	public int saveEvaluate(Integer orderServiceId, String start,
			String evaluate, Integer loginUserId) {
		int count =0;
		TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(orderServiceId);
		if(orderService!=null){}
		orderService.setEvaluate(evaluate);
		orderService.setEvaluateStart(start);
		orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_812);
    	boolean isInsertTracking = insertTracking(loginUserId, orderService.getId(),"用户评价",HglContants.WAP_ORDER_SERVICE_STATE_812);
    	if(isInsertTracking){
			count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
		}
    	//评价的时候,更新师傅的平均星级 
    	Integer repairmanId= orderService.getRepairmanId();
    	TbWebUser tbWebUser =tbWebUserMapper.selectByPrimaryKey(repairmanId);
    	Integer userInfoId= tbWebUser.getUserinfoId();
    	Criteria criteria = new Criteria();
    	criteria.put("repId", repairmanId);
    	criteria.put("id", userInfoId);
    	try {
    	    tbUserInfoMapper.updateEvaluateNumAvg(criteria);
        }
        catch (Exception e) {
           logger.error(">>>>>>>>>>>>>>update evaluate avg is errir .error message:"+e.getMessage());
        }
    	return count;
    	
    	
	}

	/**
	 * 师傅完成服务，改价
	 */
	@Override
	public Map<String, Object> doServicePrice(Integer id, Integer loginUserId,
			String price) {
		TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(id);
		int count=0;
		boolean isInsertTracking = false;
		if(orderService !=null && orderService.getRepairmanId().equals(loginUserId)){
			if(orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_804)){//服务中
				//orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_808);//待支付
				orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_810);//待评价
				orderService.setTotalMoney(Double.valueOf(price));
				isInsertTracking = insertTracking(loginUserId, orderService.getId(),"服务完成，待评价",HglContants.WAP_ORDER_SERVICE_STATE_810);
			}else if(orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_810)){
				orderService.setTotalMoney(Double.valueOf(price));
				isInsertTracking = insertTracking(loginUserId, orderService.getId(),"师傅修改服务价格",HglContants.WAP_ORDER_SERVICE_STATE_810);
			}
			
			if(isInsertTracking){
				count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
			}
		}
		return getReslutMap(orderService, count);
	}

	@Override
	public Map<String, Object> doPayService(Integer id, Integer loginUserId) {
		int count =0;
		TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(id);
		if(orderService!=null && orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_808)){
			orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_816);
	    	boolean isInsertTracking = insertTracking(loginUserId, orderService.getId(),"用户线下支付完成",HglContants.WAP_ORDER_SERVICE_STATE_816);
	    	if(isInsertTracking){
				count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
			}
		}
    	return getReslutMap(orderService, count);
	}

	@Override
	public Map<String, Object> doOnlinePayService(Integer id,
			Integer loginUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> onlinPayResult(String orderNumber) {
		int count=0;
		Criteria criteria = new Criteria();
		criteria.put("orderSerialNo", orderNumber);
		List<TbWapOrderService> list=tbWapOrderServiceMapper.selectByObject(criteria);
		if(list.size()>0){
			TbWapOrderService orderService = list.get(0);
			orderService.setOrderStatus(HglContants.WAP_ORDER_SERVICE_STATE_810);//待评价
			boolean isInsertTracking = insertTracking(orderService.getMasterId(), orderService.getId(),"用户线下支付完成",HglContants.WAP_ORDER_SERVICE_STATE_816);
	    	if(isInsertTracking){
				count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
			}
		}
		
		return getReslutMap(null, count);
	}

	@Override
	public Map<String, Object> doDeleteService(Integer id, Integer loginUserId) {
		TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(id);
		int count=0;
		if(orderService !=null && (orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_812) || orderService.getOrderStatus().equals(HglContants.WAP_ORDER_SERVICE_STATE_814))){
			if(orderService.getMasterId().equals(loginUserId)){//
				orderService.setMasterShow(HglContants.SERVICE_MASTERSHOW_DELETE);
				count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
				
			}else if(orderService.getRepairmanId()!= null && orderService.getRepairmanId().equals(loginUserId)){//师傅
				orderService.setRepairmanShow(HglContants.SERVICE_REPAIRMANSHOW_DELETE);
				count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
				
			}
		}
		return getReslutMap(orderService, count);
	}

	@Override
	public Map<String, Object> checkCityLowerPrice(Integer id, String price) {
		Map<String, Object> map =new HashMap<String,Object>();;
		TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(id);
		BigDecimal bd=new BigDecimal(price); 
		if (orderService!=null&&orderService.getCityCode()!=null) {
			Criteria criteria = new Criteria();
			criteria.put("cityid", orderService.getCityCode());
			List<TbCityLowestPrice> cityLowestPrice = tbCityLowestPriceMapper.selectByObject(criteria);
			if (cityLowestPrice.size()>0) {
				BigDecimal lowerPrice = cityLowestPrice.get(0).getLowestPrice();
				if(lowerPrice.compareTo(bd)==1 ){
					map.put("errcode", 1);
					map.put("lowerPrice", lowerPrice);
					map.put("msg", "你设置的金额小于城市限定最低金额");
					return map;
				}
			}
		}
		map.put("errcode", 0);
		map.put("msg", "你设置的金额符合规定");
		return map;
	}

    @Override
    public List<TbWapOrderService> findMyOrderService(Integer userId) {
        
        Criteria criteria = new Criteria();
        criteria.put("orderStatus", HglContants.WAP_ORDER_SERVICE_STATE_802);
        criteria.put("repairmanId", userId);
        return tbWapOrderServiceMapper.selectByObject(criteria);
    }

    @Override
    public List<Map<String,Object>> findMymaster(Integer id,Integer userId,PageDto pageDto) {
        
        //查询该服务的类型
        Criteria criteria = new Criteria();
        criteria.put("id", id);
        Integer typeId =tbWapOrderServiceMapper.findServiceTypeIdByOrderId(criteria);
        
        //查询自己的店铺id
        TbWebUser tbWebUser =tbWebUserMapper.selectByPrimaryKey(userId);
        Integer shopId =tbWebUser.getRecommendShopId();
        //如果没有店铺id,返回null
        if(shopId==null){
            return null;
        }
        
        //查询店铺下的指定技能的师傅
        criteria.clear();
        criteria.put("typeId", typeId);
        criteria.put("shopId", shopId);
        List<Map<String,Object>> tbWebUserList =tbUserInfoMapper.findMasterByShopId(criteria,pageDto);
        
        return tbWebUserList;
    }

    @Override
    public int updateMaster(Integer id, Integer userId) {
        TbWapOrderService orderService = tbWapOrderServiceMapper.selectByPrimaryKey(id);
        orderService.setRepairmanId(userId);
        boolean isInsertTracking = insertTracking(userId, orderService.getId(),"经销商给师傅添加服务订单",HglContants.WAP_ORDER_SERVICE_STATE_802);
        int count = 0;
        if(isInsertTracking){
            count = tbWapOrderServiceMapper.updateByPrimaryKeySelective(orderService);
        }
        return count;
    }

	@Override
	public int selectUnCompleteOrderCount(Criteria criteria)
			throws RuntimeException {
		try{
			return tbWapOrderServiceMapper.selectUnCompleteOrderCount(criteria);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
}