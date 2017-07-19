package com.liguo.hgl.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMapper;
import com.liguo.hgl.proxydao.dao.TbServiceTypeMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbSystemConfigMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.ShopIndexForWapDto;
import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.dto.WapProductType;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;
import com.liguo.hgl.proxydao.model.TbServiceType;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbSystemConfig;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.IAddress;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.util.SmsSend;

@Service
@Scope(value="prototype")
public class TbShopInfoServiceImpl implements TbShopInfoService {
	private static final Object LOCK_OBJ = "lockerOrder";
    @Autowired
    private TbShopInfoMapper tbShopInfoMapper;
    
    @Autowired
	TbIntegralMapper tbIntegralMapper;
    
    @Autowired
    TbWebUserMapper tbWebUserMapper;
    
	@Autowired
	private TbAccountMapper tbAccountMapper;
	
	@Autowired
	private TbSystemConfigMapper tbSystemConfigMapper;
	
	@Autowired
	private TbCashAccountMapper tbCashAccountMapper;
	
	@Autowired
	private TbIntegralDetailedMapper tbIntegralDetailedMapper;
    
    @Autowired
	private IAddress iAddress;
    
    @Autowired
    private TbServiceTypeMapper tbServiceTypeMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbShopInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbShopInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbShopInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbShopInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbShopInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbShopInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbShopInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbShopInfo record) throws RuntimeException {
        try {
            return this.tbShopInfoMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbShopInfo record) throws RuntimeException {
        try {
            return this.tbShopInfoMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbShopInfo record) throws RuntimeException {
        try {
            return this.tbShopInfoMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 用于WAP端首页查询
     * 
     * 传入的手机端用户的坐标
     * */
    public List<TbShopInfo> selectTbShopInfoForWap(double lon,double lat,String city){
    	Criteria example = new Criteria();
    	//查询已经通过审核的店铺,104为经销商
    	example.put("authStatus", 1);
    	example.put("shopType", 104);
    	example.put("address", city);
    	example.put("lon", lon);
    	example.put("lat", lat);
    	List<TbShopInfo> tlist = tbShopInfoMapper.selectTbShopInfoForWap(example);
    	return tlist;
    }
    /**
     * 用于WAP端首页查询
     * 
     * 传入的手机端用户的坐标
     * */
    public List<TbShopInfo> selectTbShopInfoForWapByGrade(double lon,double lat,String city){
        Criteria example = new Criteria();
        //查询已经通过审核的店铺,104为经销商
        example.put("authStatus", 1);
        example.put("shopType", 104);
        example.put("address", city);
        example.put("lon", lon);
        example.put("lat", lat);
        example.put("grade", "1");//带上等级条件查询
        List<TbShopInfo> tlist = tbShopInfoMapper.selectTbShopInfoForWap(example);
        return tlist;
    }
    /**
     * 附近的店
     * */
    @Override
    public List<TbShopInfo> selectNearTbShopInfo(Criteria example){
        //查询已经通过审核的店铺,104为经销商
        example.put("shopType", 104);
        example.put("settlement", 1);
        List<TbShopInfo> tlist = tbShopInfoMapper.selectNearTbShopInfo(example);
        return tlist;
    }
    
    @Override
    public Integer selectNearTbShopPageCount(Criteria example){
        example.put("shopType", 104);
        example.put("settlement", 1);
        return tbShopInfoMapper.selectNearTbShopPageCount(example);
    }
    
	@Override
	public List<ShopWebUserDto> getSupplierWebUserList(Criteria criteria,
			PageDto page) {
		criteria.put("shopType", HglContants.USER_TYPE_FACTOR);
		return getShopList(criteria, page);
	}

	private List<ShopWebUserDto> getShopList(Criteria criteria, PageDto page) {
		if(criteria.getOrderByClause()==null){
			criteria.setOrderByClause("id");
			criteria.setOrderByClauseDesc("desc");
		}
		List<ShopWebUserDto> shopDtos = tbShopInfoMapper.selectByCriteria(criteria,page);
        for (ShopWebUserDto shopDto : shopDtos) {
			String addresss= iAddress.getAddressName(shopDto.getRegProvince(), shopDto.getRegCity(), 
					shopDto.getRegCountry(), shopDto.getRegStreet());
			shopDto.setAddress(addresss);
		}
		return shopDtos;
	}

	@Override
	public List<ShopWebUserDto> getDealersUserList(Criteria criteria,
			PageDto page) {
		criteria.put("shopType", HglContants.USER_TYPE_DISTRIBUTOR);
		return getShopList(criteria, page);
	}
	@Override
	public ShopIndexForWapDto selectShopIndexInfo(Criteria criteria){
		
		return tbShopInfoMapper.selectShopIndexInfo(criteria);
	}
	@Override
	public List<TbWapProductDto> selectTbWapProductDtoList(Criteria criteria){
		return tbShopInfoMapper.selectTbWapProductDtoList(criteria);
	}
	
	public List<WapProductType> selectProductBrand(Criteria criteria){
		return tbShopInfoMapper.selectProductBrand(criteria);
	}

	@Override
	public Map<String, Object> updateEarnings(Integer id, double rebate) {
		Map<String,Object> map = new HashMap<String,Object>();
		int count=0;
		TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(id);
		if(shopInfo !=null){
			shopInfo.setRebate(rebate);
			count = tbShopInfoMapper.updateByPrimaryKeySelective(shopInfo);
		}
		if(count >0){
			map.put("errcode", "0");
			map.put("msg", "成功！");
		}else{
			map.put("errcode", "1");
			map.put("msg", "失败！");
		}
		return map;
	}

	@Override
	public Map<String, Object> doAutomaticOder(Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		int count=0;
		TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(id);
		if(shopInfo !=null){
			if(shopInfo.getIsAutomaticOrder().equals(HglContants.SHOP_ISAUTOMATICORDER_DEFAULT)){
				shopInfo.setIsAutomaticOrder(HglContants.SHOP_ISAUTOMATICORDER);
			}else{
				shopInfo.setIsAutomaticOrder(HglContants.SHOP_ISAUTOMATICORDER_DEFAULT);
			}
			count = tbShopInfoMapper.updateByPrimaryKeySelective(shopInfo);
		}
		if(count >0){
			map.put("errcode", "0");
			map.put("msg", "成功！");
		}else{
			map.put("errcode", "1");
			map.put("msg", "失败！");
		}
		return map;
	}
	/**
	 * admin线下开通店铺结算功能
	 * */
	@Override
	public int openSettlement(Integer shopId,String openType){
		TbShopInfo tbShopInfo = tbShopInfoMapper.selectByPrimaryKey(shopId);
		if(tbShopInfo.getAuthStatus().toString().equals(HglContants.WEB_AUTH_STATUS1+"")){
		    /**给经销商添加所有技能start**/
            Criteria criteria = new Criteria();
            criteria.put("childAll", 0);// 0 任意值
            List<TbServiceType> tbServiceTypeList =tbServiceTypeMapper.selectByObject(criteria);
            StringBuilder sb = new StringBuilder();
            for(int i =0;i<tbServiceTypeList.size();i++){
                Integer id =tbServiceTypeList.get(i).getId();
                sb.append(id);
                if(i!=tbServiceTypeList.size()-1){
                    sb.append(",");
                }
            }
            /**给经销商添加所有技能end**/
		    //添加所有服务
            tbShopInfo.setServiceType(sb.toString());
    	 	tbShopInfo.setSettlement(tbShopInfo.getBalance());
    	 	//设置金牌经销商状态
    	 	if(HglContants.SHOP_MEDAL_AGENT.equals(openType)){
    			tbShopInfo.setMedalAgentFlag(2);
    			tbShopInfo.setBalance(1);
    			tbShopInfo.setSettlement(1);
    		}
    	 	tbShopInfoMapper.updateByPrimaryKey(tbShopInfo);
        	Criteria example = new Criteria();
        	example.put("shopId", tbShopInfo.getId());
        	TbWebUser tbWebUser = tbWebUserMapper.selectByObject(example).get(0);
        	tbWebUser.setRoleId(HglContants.GETED_ROLE_ID);
        	tbWebUserMapper.updateByPrimaryKey(tbWebUser);
        	
        	//调用给推荐人返利方法
        	rebatereCommended(tbShopInfo);
        	
        	//发送短信
			SmsSend.sendPayed(tbShopInfo.getContractPhone(),tbShopInfo.getContract());
        	return 1;
		}
		return 0;
	}
	
	public Double subtractSettlementMoney(TbShopInfo tbShopInfo,Double payMoney) throws TransactionException {
		//判断是否已经开通了上架货品平台结算权限(如果开通需要减去上架的货品的支付钱)
		if("1".equals(String.valueOf(tbShopInfo.getSettlement()))){
			Criteria example = new Criteria();
			example.put("configKey", "settlement");
			String settlementValue = tbSystemConfigMapper.selectByObject(example).get(0).getConfigValue();
	        BigDecimal payBdm = new BigDecimal(payMoney);
	        payMoney = payBdm.subtract(new BigDecimal(settlementValue)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	        if(payMoney <0){
	        	logger.debug("支付金额小于0");        
				throw new TransactionException(MessageEnum.Z2008);
	        }
		}
		return payMoney;
	}
	
	/**
	 * 用户账户支付开通平台结算功能
	 * */
	@Override
	public int getSellement(Integer shopId,Integer accountId,String openType) throws TransactionException {
		logger.debug("用户账户支付开通结算功能》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》" + openType);
		TbShopInfo tbShopInfo = tbShopInfoMapper.selectByPrimaryKey(shopId);
		
		TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(accountId);
		Criteria example = new Criteria();
		example.put("configKey", openType);
		String configValue = tbSystemConfigMapper.selectByObject(example).get(0).getConfigValue();
		Double payMoney = Double.parseDouble(configValue);
		
		//判断是否已经开通了上架货品平台结算权限(如果开通需要减去上架的货品的支付钱)
		payMoney = subtractSettlementMoney(tbShopInfo,payMoney);
		
		logger.debug("用户账户支付开通结算功能》》》》》》》》》》》》》》》payMoney"+payMoney+";accountBalance:"+tbAccount.getBalance());
		if((tbAccount.getBalance() - payMoney) > 0){
			//扣钱操作
			BigDecimal balance = new BigDecimal(tbAccount.getBalance());
			BigDecimal money = new BigDecimal(payMoney);
			Double accountBalance = balance.subtract(money).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			tbAccount.setBalance(accountBalance);
			tbAccountMapper.updateByPrimaryKeySelective(tbAccount);
			
			//添加资金明细记录
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>增加资金明细记录："+shopId);
			TbCashAccount sellerAccount = new TbCashAccount();
			sellerAccount.setCashOut(payMoney);
			sellerAccount.setBalance(tbAccount.getBalance());
			sellerAccount.setOperationDt(System.currentTimeMillis());
			sellerAccount.setPlatform(1);
			sellerAccount.setAccountId(tbAccount.getId());
			sellerAccount.setOrderSerialNo(makeOrderNum("S"));
			int count = tbCashAccountMapper.insertSelective(sellerAccount);
			logger.debug("增加资金明细记录条数: " + count); 
			
			/**给经销商添加所有技能start**/
	        Criteria criteria = new Criteria();
	        criteria.put("childAll", 0);// 0 任意值
	        List<TbServiceType> tbServiceTypeList =tbServiceTypeMapper.selectByObject(criteria);
	        StringBuilder sb = new StringBuilder();
	        for(int i =0;i<tbServiceTypeList.size();i++){
	            Integer id =tbServiceTypeList.get(i).getId();
	            sb.append(id);
	            if(i!=tbServiceTypeList.size()-1){
	                sb.append(",");
	            }
	        }
	        /**给经销商添加所有技能end**/
	        
			tbShopInfo.setBalance(1);
			tbShopInfo.setSettlement(1);
			tbShopInfo.setServiceType(sb.toString());
			//设置金牌代理商开通权限标示
			if(HglContants.SHOP_MEDAL_AGENT.equals(openType)){
				tbShopInfo.setMedalAgentFlag(2);
			}
			tbShopInfoMapper.updateByPrimaryKey(tbShopInfo);
			
	      	//调用给推荐人返利方法
        	rebatereCommended(tbShopInfo);
        	
			//发送短信
			SmsSend.sendPayed(tbShopInfo.getContractPhone(),tbShopInfo.getContract());
			return 1;
		}else{
			logger.debug("金额不足，支付失败");
			throw new TransactionException(MessageEnum.Z2001);
		}
	}
	
	@Override
	public int weixinPaygetSettlement(String orderGroupNo,String accountId){
		//插入出入金记录..
		
		logger.debug("执行微信扫码支付购买平台结算权限》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
		TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(Integer.parseInt(accountId));
		//根据账户得到shopInfo信息
		Criteria c = new Criteria();
		c.put("accountId", tbAccount.getId());
		TbShopInfo tbShopInfo = tbShopInfoMapper.selectByObject(c).get(0);
		//得到服务购买所需金额
		Criteria example = new Criteria();
		example.put("configKey", "settlement");
		String configValue = tbSystemConfigMapper.selectByObject(example).get(0).getConfigValue();
		Double payMoney = Double.parseDouble(configValue);
		
		//插入资金明细记录表
		TbCashAccount inCashAccount = new TbCashAccount();
		inCashAccount.setCashOut(0.0);
		inCashAccount.setCashIn(payMoney);
		inCashAccount.setBalance(tbAccount.getBalance()+payMoney);
		inCashAccount.setOperationDt(System.currentTimeMillis());
		inCashAccount.setShopId(tbShopInfo.getId());
		inCashAccount.setAccountId(Integer.parseInt(accountId));
		inCashAccount.setPlatform(1);
		inCashAccount.setOrderSerialNo(orderGroupNo);
		tbCashAccountMapper.insertSelective(inCashAccount);
		
		//插入出金记录
		TbCashAccount outCashAccount = new TbCashAccount();
		outCashAccount.setCashOut(payMoney);
		outCashAccount.setCashIn(0.0);
		outCashAccount.setBalance(tbAccount.getBalance()-payMoney);
		outCashAccount.setOperationDt(System.currentTimeMillis());
		outCashAccount.setShopId(tbShopInfo.getId());
		outCashAccount.setAccountId(Integer.parseInt(accountId));
		outCashAccount.setPlatform(1);
		outCashAccount.setOrderSerialNo(orderGroupNo);
		tbCashAccountMapper.insertSelective(outCashAccount);
		
		/**给经销商添加所有技能start**/
        Criteria criteria = new Criteria();
        criteria.put("childAll", 0);// 0 任意值
        List<TbServiceType> tbServiceTypeList =tbServiceTypeMapper.selectByObject(criteria);
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<tbServiceTypeList.size();i++){
            Integer id =tbServiceTypeList.get(i).getId();
            sb.append(id);
            if(i!=tbServiceTypeList.size()-1){
                sb.append(",");
            }
        }
        /**给经销商添加所有技能end**/
		
		//更改店铺结算状态
		tbShopInfo.setBalance(1);
		tbShopInfo.setSettlement(1);
		//添加所有技能
		tbShopInfo.setServiceType(sb.toString());
		tbShopInfoMapper.updateByPrimaryKeySelective(tbShopInfo);
		
		//更改用记角色状态
		Criteria webUserExample = new Criteria();
		webUserExample.put("shopId",tbShopInfo.getId());
		TbWebUser tbWebUser = tbWebUserMapper.selectByObject(webUserExample).get(0);
		tbWebUser.setRoleId(HglContants.GETED_ROLE_ID);
		tbWebUserMapper.updateByPrimaryKeySelective(tbWebUser);
		
		//发送短信
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.发送短信");
		SmsSend.sendPayed(tbShopInfo.getContractPhone(),tbShopInfo.getContract());
		return 0;
		}
	
	public boolean uploadShopInfoImage(MultipartFile file,String fileName,String floder) throws IOException{
		long imageSize = file.getSize();
		if(imageSize>1024*1024*5){
			return false;
		}
		
		logger.info("文件长度:"+file.getSize());
		logger.info("文件类型:"+file.getContentType());
		logger.info("文件名称:"+file.getName());
		logger.info("文件原始名称:"+file.getOriginalFilename());
		logger.info("=================================");
		String path = HglContants.SHOP_INFO_PATH+floder;
		File targetFile = new File(path,fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		
		file.transferTo(targetFile);
		
		return true;
	}
	
	/**
	 * 返利到推荐人账号
	 * @param tbShopInfo
	 * @throws Exception
	 */
	public void rebatereCommended(TbShopInfo tbShopInfo){
		//查询出推荐人的店铺信息
		Criteria criteria = new Criteria();
		criteria.put("shopId",tbShopInfo.getId());
		TbShopInfo shopInfo = tbShopInfoMapper.selectRebatereCommended(criteria);
		if(shopInfo != null){
			String configKey = null;
			//20000推荐20000
			if("2".equals(String.valueOf(shopInfo.getMedalAgentFlag())) && "2".equals(String.valueOf(tbShopInfo.getMedalAgentFlag()))){
				configKey = HglContants.MEDALAGENT_TO_MEDALAGENT;
			//20000推荐6800
			}else if("2".equals(String.valueOf(shopInfo.getMedalAgentFlag())) && "1".equals(String.valueOf(tbShopInfo.getSettlement()))){
				configKey = HglContants.MEDALAGENT_TO_SETTLEMENT;
			//6800推荐20000
			}else if("1".equals(String.valueOf(shopInfo.getSettlement())) && "2".equals(String.valueOf(tbShopInfo.getMedalAgentFlag()))){
				configKey = HglContants.SETTLEMENT_TO_MEDALAGENT;
			//6800推荐6800
			}else if("1".equals(String.valueOf(shopInfo.getSettlement())) && "1".equals(String.valueOf(tbShopInfo.getSettlement()))){
				configKey = HglContants.SETTLEMENT_TO_SETTLEMENT;
			} 
			if(configKey != null){
		    	criteria.put("configKey",configKey);
				String rebatereMoney = tbSystemConfigMapper.selectByObject(criteria).get(0).getConfigValue();
				
				logger.debug("recharge >>>>>>>>>>>>>>>>>>>>>>>>>.返利金额:"+rebatereMoney+">>>>>返利类型:"+configKey);
				TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(shopInfo.getAccountId());
				BigDecimal aBalance = new BigDecimal(tbAccount.getBalance());
				tbAccount.setBalance(aBalance.add(new BigDecimal(rebatereMoney)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				tbAccountMapper.updateByPrimaryKey(tbAccount);
				
				//插入资金明细记录表
				TbCashAccount tbCashAccount = new TbCashAccount();
				tbCashAccount.setCashOut(0.0);
				tbCashAccount.setCashIn(Double.parseDouble(rebatereMoney));   
				tbCashAccount.setBalance(tbAccount.getBalance());
				tbCashAccount.setOperationDt(System.currentTimeMillis());
				tbCashAccount.setShopId(shopInfo.getId());
				tbCashAccount.setAccountId(shopInfo.getAccountId());
				tbCashAccount.setPlatform(2);
				tbCashAccount.setOrderSerialNo(makeOrderNum("R"));
				tbCashAccountMapper.insertSelective(tbCashAccount);
			}
		}
	}
	
	/**
	 * 购买结算时添加店铺积分
	 * @param tbShopInfo
	 * @throws Exception
	 */
	public void addShopIntegral(Integer shopId){
		TbIntegral tbIntegerl = tbIntegralMapper.selectByShopId(shopId);
		TbSystemConfig systemCon = tbSystemConfigMapper.selectByConfigKey("settlementAddIntegral");
		if(systemCon != null){
			Integer config = 0;
			try{
				config = Integer.parseInt(systemCon.getConfigValue());
			}catch (NumberFormatException e){
				e.printStackTrace();
			}
			logger.debug("赠送积分额为>>>>>>>>>>>>>>>>>>>>>>>>>:"+systemCon.getConfigValue());
			if(config > 0){
				tbIntegerl.setIntegral(tbIntegerl.getIntegral() + config);
				tbIntegralMapper.updateByPrimaryKey(tbIntegerl);
				//成生积分明细
				TbIntegralDetailed tbIntegralDetailed = new TbIntegralDetailed();
				tbIntegralDetailed.setIntegralId(tbIntegerl.getId());
				tbIntegralDetailed.setOpearDt(System.currentTimeMillis());
				tbIntegralDetailed.setOrderId(null);
				tbIntegralDetailed.setOrderSerialNo(null);
				tbIntegralDetailed.setIntegral(config);
				tbIntegralDetailed.setType(1);
				tbIntegralDetailed.setVersion(0);
				tbIntegralDetailedMapper.insert(tbIntegralDetailed);
			}
		}
	}
	
    /**
     * 生成订单号 
     * @param prefix
     * @return
     */
    public String makeOrderNum(String prefix) {
		String finOrderNum = "";
		try {
			// 最终生成的订单号
			synchronized (LOCK_OBJ) {
				long nowLong = Long.parseLong(new SimpleDateFormat(
						"yyMMddHHmmssSSS").format(new Date()));
				for (int i = 0; i < 3; i++) {
					finOrderNum = finOrderNum + (int) (Math.random() * 10) + "";
				}
					return prefix + nowLong + finOrderNum;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finOrderNum;
	}

	@Override
	public TbShopInfo selectTbShopInfoByUserId(Integer userId) {
		return tbShopInfoMapper.selectByUserId(userId);
	}
}