package com.liguo.hgl.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.druid.util.StringUtils;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.QRCodeUtil;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbBrandMapper;
import com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper;
import com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper;
import com.liguo.hgl.proxydao.dao.TbRecommendedMapper;
import com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper;
import com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbUserGroupMapper;
import com.liguo.hgl.proxydao.dao.TbUserInfoMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.dto.TbwebUserShopDto;
import com.liguo.hgl.proxydao.dto.UserRecommendedDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.dto.WebUserGroupDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbGroupDistribution;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralRules;
import com.liguo.hgl.proxydao.model.TbRecommended;
import com.liguo.hgl.proxydao.model.TbRecommendedRules;
import com.liguo.hgl.proxydao.model.TbRecommendedType;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbUserGroup;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.proxydao.util.IAddress;
import com.liguo.hgl.proxydao.util.IdCreator;
import com.liguo.hgl.service.TbSaveInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.MD5Utils;
import com.liguo.hgl.util.NullU;
import com.liguo.hgl.util.SmsSend;
import com.liguo.hgl.util.StringUtil;


@Service
@Scope(value = "prototype")
public class TbWebUserServiceImpl implements TbWebUserService {
	@Autowired
	private TbWebUserMapper tbWebUserMapper;
	
	@Autowired
	private TbShopInfoMapper tbShopInfoMapper;
	
	@Autowired
	private TbUserInfoMapper tbUserInfoMapper;
	
	@Autowired
	private TbUserInfoService userInfoService;
	
	@Autowired
	private TbBrandMapper brandMapper;
	
	@Autowired
	private IAddress iAddress;
	
	@Autowired
	private TbProvinceInfoMapper tbProvinceInfoMapper;
	
	@Autowired
	TbIntegralMapper tbIntegralMapper;

	
	 /** 招商推荐码生成器 */
	@Autowired
    private IdCreator recommendCode;
	
	@Autowired
	private TbAccountMapper tbAccountMapper;
	
	@Autowired
	private TbRecommendedMapper tbRecommendedMapper;
	
	@Autowired
	private TbRecommendedTypeMapper tbRecommendedTypeMapper;
	
	@Autowired
	private TbRecommendedRulesMapper tbRecommendedRulesMapper;
	
	@Autowired
	private TbSaveInfoService saveInfoService;
	
	@Autowired
	private TbShopInfoService shopInfoService;
	
	@Autowired
	private TbUserGroupMapper tbUserGroupMapper;
	
	@Autowired
	private TbGroupDistributionMapper tbGroupDistributionMapper;

	@Autowired
	private TbIntegralRulesMapper tbIntegralRulesMapper;
	
	private static final Logger logger = LoggerFactory
			.getLogger(TbWebUserServiceImpl.class);

	public int countByObject(Criteria example) throws RuntimeException {
		try {
			int count = this.tbWebUserMapper.countByObject(example);
			logger.debug("count: {}", count);
			return count;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public TbWebUser selectByPrimaryKey(Integer id) throws RuntimeException {
		try {
			return this.tbWebUserMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<TbWebUser> selectByObject(Criteria example)
			throws RuntimeException {
		try {
			return this.tbWebUserMapper.selectByObject(example);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int deleteByPrimaryKey(Integer id) throws RuntimeException {
		try {
			return this.tbWebUserMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int updateByPrimaryKeySelective(TbWebUser record)
			throws RuntimeException {
		try {
			return this.tbWebUserMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int updateByPrimaryKey(TbWebUser record) throws RuntimeException {
		try {
			return this.tbWebUserMapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int insertSelective(TbWebUser record) throws RuntimeException {
		try {
			return this.tbWebUserMapper.insertSelective(record);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public WebUserDto selectByUsernameAndPwd(String userName, String pwd)
			throws RuntimeException {
		try {
			Criteria example = new Criteria();
			example.put("userName", userName);
			example.put("password", pwd);
			WebUserDto tbWebUser = this.tbWebUserMapper
					.selectByUsernameAndPwd(example);
			return tbWebUser;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Map<String, Object> checkName(String userName) {
		Criteria example = new Criteria();
		example.put("userName", userName);
		int count = this.tbWebUserMapper.countByObject(example);
		Map<String, Object> map = new HashMap<String,Object>();
		if(count >0){
			map.put("errcode", "1");
			map.put("msg", "此用户名已经存在");
		}else{
			map.put("errcode", "0");
			map.put("msg", "此用户名不存在");
		}
		return map;
	}

	@Override
	public Map<String, Object> checkMobile(String mobile) {
		Criteria example = new Criteria();
		example.put("mobile", mobile);
		int count = this.tbWebUserMapper.countByObject(example);
		Map<String, Object> map = new HashMap<String,Object>();
		if(count >0){
			map.put("errcode", "1");
			map.put("msg", "此手机号码已经存在");
		}else{
			map.put("errcode", "0");
			map.put("msg", "此手机号码不存在");
		}
		return map;
	}

	@Override
	public int register(Map<String, Object> param) {
        if(NullU.isNull(param.get("userName"))||NullU.isNull(param.get("password"))||NullU.isNull(param.get("mobile")) || NullU.isNull(param.get("name"))){
			return 0;
		}
		TbUserInfo tbUserInfo = new TbUserInfo();
		tbUserInfo.setShopType(HglContants.USER_TYPE_CUS);
		if(NullU.isNotNull(param.get("regStreet"))){
			tbUserInfo.setRegProvince(Integer.parseInt(param.get("regProvince").toString()));
			tbUserInfo.setRegCity(Integer.parseInt(param.get("regCity").toString()) );
			tbUserInfo.setRegCountry(Integer.parseInt(param.get("regCountry").toString()));
			tbUserInfo.setRegStreet(Integer.parseInt(param.get("regStreet").toString()));
			tbUserInfo.setRegAddress(param.get("regAddress").toString());
		}
		tbUserInfo.setCreateDt(System.currentTimeMillis());
		tbUserInfoMapper.insert(tbUserInfo);
		int userInfo = tbUserInfo.getId();
		TbWebUser tbWebUser = new TbWebUser();
		tbWebUser.setTypeId(HglContants.USER_TYPE_CUS);
		tbWebUser.setState(HglContants.WEB_AUTH_STATUS1);//用户默认状态
		tbWebUser.setUserName(param.get("userName").toString());
		String passWord= MD5Utils.md5(param.get("password").toString(), "UTF-8");
		tbWebUser.setPassword(passWord);
		tbWebUser.setMobile(param.get("mobile").toString());
		tbWebUser.setName(param.get("name").toString());
		tbWebUser.setUserinfoId(userInfo);
		tbWebUser.setEmail(param.get("email").toString());
		tbWebUser.setLogoCode(recommendCode.create());
		if(!StringUtils.isEmpty(param.get("recommendCode").toString())){
			tbWebUser.setRecommendCode(param.get("recommendCode").toString());
		}
		int dd=tbWebUserMapper.insert(tbWebUser);
		if(!StringUtils.isEmpty(param.get("recommendCode").toString())){
			insertRecommended(tbWebUser.getId(),HglContants.USER_TYPE_CUS,param.get("recommendCode").toString());
		}
		return dd;
	}

	/**
	 * 推荐注册生成推荐记录
	 * @param id 当前注册人的用户ID
	 * @param userType  当前注册人的角色
	 * @param code 被推荐的推荐码--对方推荐码
	 * @author zss
	 */
	private void insertRecommended(Integer id, Integer userType,
			String code) {
		Criteria criteria = new Criteria();
		criteria.put("logoCode", code);
		List<TbWebUser> list =tbWebUserMapper.selectByObject(criteria);
		if(list.size()>0){
			TbWebUser tbWebUser = list.get(0);//推荐人实体
			if(userType.equals(HglContants.USER_TYPE_DISTRIBUTOR)){  //判断是推荐经销商进来的，拿平台推荐规则
				Criteria example = new Criteria();
				example.put("recommendedType", userType);
				example.put("isRecommendedType", tbWebUser.getTypeId());
				List<TbRecommendedType> recommendedTypes = tbRecommendedTypeMapper.selectByObject(example);
				if (recommendedTypes.size()>0) {
					TbRecommendedType recommendedType = recommendedTypes.get(0);
					TbRecommended recommended = new TbRecommended();
					recommended.setRecommended(tbWebUser.getId());
					recommended.setIsRecommended(id);
					recommended.setRecommendedType(tbWebUser.getTypeId());
					recommended.setIsRecommendedType(userType);
					recommended.setTypeRecommend(HglContants.TYPE_RECOMMEND_PT);
					recommended.setRecommendedContactTypeId(recommendedType.getId());
					recommended.setType(recommendedType.getRewardType());
					recommended.setUpdateDt(System.currentTimeMillis());
					recommended.setVersion(0);
					recommended.setMoney(0.0);
					recommended.setFirstOrderId(0);
					tbRecommendedMapper.insert(recommended);
				}
			}else if(!tbWebUser.getTypeId().equals(HglContants.USER_TYPE_DISTRIBUTOR) && !tbWebUser.getRecommendShopId().equals(HglContants.HGL_DEFAULT_SHOP)){
				/**
				 * 判断是普通客户推荐进来的，拿到他对应的经销商店铺规则，排除0000
				 */
				Criteria example = new Criteria();
				example.put("recommendedType",tbWebUser.getTypeId() );
				example.put("isRecommendedType", userType);
				example.put("shopid", tbWebUser.getRecommendShopId());
				List<TbRecommendedRules> recommendedRules = tbRecommendedRulesMapper.selectByObject(example);
				if (recommendedRules.size()>0) {
					TbRecommendedRules rules = recommendedRules.get(0);
					TbRecommended recommended = new TbRecommended();
					recommended.setRecommended(tbWebUser.getId());
					recommended.setIsRecommended(id);
					recommended.setRecommendedType(tbWebUser.getTypeId());
					recommended.setIsRecommendedType(userType);
					recommended.setTypeRecommend(HglContants.TYPE_RECOMMEND_JX);
					recommended.setRecommendedContactTypeId(rules.getId());
					recommended.setType(rules.getRewardType());
					recommended.setUpdateDt(System.currentTimeMillis());
					recommended.setVersion(0);
					recommended.setMoney(0.0);
					recommended.setFirstOrderId(0);
					tbRecommendedMapper.insert(recommended);
				}
			}
		}
	}

	
	@Override
	public int insertRecommendedInfo(String code,String shopId,TbWebUser loginUser){
		Criteria criteria = new Criteria();
		criteria.put("logoCode", code);
		List<TbWebUser> list =tbWebUserMapper.selectByObject(criteria);
		if(list.size()>0){
			TbWebUser tbWebUser = list.get(0);//推荐人实体
			Criteria example = new Criteria();
			example.put("recommendedType", tbWebUser.getTypeId());
			example.put("isRecommendedType", loginUser.getTypeId());
			example.put("shopid", shopId);
			List<TbRecommendedRules> recommendedRules = tbRecommendedRulesMapper.selectByObject(example);
			if (recommendedRules.size()>0) {
				TbRecommendedRules rules = recommendedRules.get(0);
				TbRecommended recommended = new TbRecommended();
				recommended.setRecommended(tbWebUser.getId());
				recommended.setIsRecommended(loginUser.getId());
				recommended.setRecommendedType(tbWebUser.getTypeId());
				recommended.setIsRecommendedType(loginUser.getTypeId());
				recommended.setTypeRecommend(HglContants.TYPE_RECOMMEND_JX);
				recommended.setRecommendedContactTypeId(rules.getId());
				recommended.setType(rules.getRewardType());
				recommended.setUpdateDt(System.currentTimeMillis());
				recommended.setVersion(0);
				recommended.setMoney(0.0);
				recommended.setFirstOrderId(0);
				return tbRecommendedMapper.insert(recommended);
			}
		}
		return 0;
	}
	

	@Override
	public int cjregister(Map<String, Object> param){
		if(NullU.isNull(param.get("userName"))||NullU.isNull(param.get("password"))||NullU.isNull(param.get("mobile")) || NullU.isNull(param.get("name"))){
			return 0;
		}
		if(NullU.isNull(param.get("companyName"))||NullU.isNull(param.get("companyTel"))||NullU.isNull(param.get("brandName")) 
				|| NullU.isNull(param.get("legalRepresentative")) || NullU.isNull(param.get("regStreet")) || NullU.isNull(param.get("scope"))){
			return 0;
		}
	
		int count=this.insertShopInfo(param,HglContants.USER_TYPE_FACTOR);
		return count;
	}
	
	/**
	 * 厂家注册时添加品牌,并且返回添加品牌的主键
	 * @param param
	 * @return
	 * @author zss
	 * @param shopInfoId 
	 */
	private int insertBrand(Map<String, Object> param, int shopInfoId){
		TbBrand tbBrand = new TbBrand();
		tbBrand.setName(param.get("brandName").toString());
		tbBrand.setProductTypeId(Integer.parseInt(param.get("productTypeId").toString()));
		tbBrand.setState(HglContants.BRAND_STATE_ISHIDDE);
		String logoName = param.get("logoName").toString().trim();
		tbBrand.setLogoName(param.get("logoName").toString());
		tbBrand.setManufacturerId(shopInfoId);
		tbBrand.setManufacturerName(param.get("companyName").toString());
		brandMapper.insert(tbBrand);
		int bid=tbBrand.getId();
		if(!StringUtils.isEmpty(logoName)){
			TbBrand brand = new TbBrand();
			String newName = StringUtil.changeFileName(String.valueOf(bid),logoName);
			try {
				ImageUtil.changRegisterPathAndBrandLogoName(logoName, newName, bid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			brand.setLogoName(newName);
			brand.setId(bid);
			brandMapper.updateByPrimaryKeySelective(brand);
		}
		logger.debug("--------bid:"+bid);
		return bid;
	}

	@Override
	public int jxregister(Map<String, Object> param) {
		if(NullU.isNull(param.get("userName"))||NullU.isNull(param.get("password"))||NullU.isNull(param.get("mobile")) || NullU.isNull(param.get("name"))){
			return 0;
		}
		if(NullU.isNull(param.get("shopName"))
				 || NullU.isNull(param.get("regStreet")) || NullU.isNull(param.get("scope"))){
			return 0;
		}
		int count=this.insertShopInfo(param,HglContants.USER_TYPE_DISTRIBUTOR);
		return count;
	}
	/**
	 * 经销商与厂家添加集合
	 * @param param
	 * @param shoptype
	 * @return
	 * @author zss
	 */
	private int insertShopInfo(Map<String, Object> param, Integer shoptype) {
		TbShopInfo tbShopInfo =new TbShopInfo();
		
		Integer provinceCode = Integer.parseInt(param.get("regProvince").toString());
		Integer cityCode = Integer.parseInt(param.get("regCity").toString());
		Integer countryCode = Integer.parseInt(param.get("regCountry").toString());
		Integer streetCode = Integer.parseInt(param.get("regStreet").toString());
		
		tbShopInfo.setShopType(shoptype);
		
		tbShopInfo.setContract(param.get("name").toString());
		tbShopInfo.setContractPhone(param.get("mobile").toString());
		
		tbShopInfo.setRegProvince(provinceCode);
		tbShopInfo.setRegCity(cityCode);
		tbShopInfo.setRegCountry(countryCode);
		tbShopInfo.setRegStreet(streetCode);
		tbShopInfo.setScope(param.get("scope").toString());
		tbShopInfo.setAuthStatus(HglContants.WEB_AUTH_STATUS);
		tbShopInfo.setCreateDt(System.currentTimeMillis());
		tbShopInfo.setVersion(0);
		if(shoptype == HglContants.USER_TYPE_FACTOR){
			tbShopInfo.setCompanyName(param.get("companyName").toString());
			tbShopInfo.setShopName(param.get("companyName").toString());
			tbShopInfo.setCompanyTel(param.get("companyTel").toString());
			tbShopInfo.setProductTypeId(Integer.parseInt(param.get("productTypeId").toString()));
			tbShopInfo.setLegalRepresentative(param.get("legalRepresentative").toString());
		}
		if(shoptype == HglContants.USER_TYPE_DISTRIBUTOR){
			tbShopInfo.setShopName(param.get("shopName").toString());
			tbShopInfo.setProductTypeId(Integer.parseInt(param.get("productTypeId").toString()));
			String address = param.get("regAddress").toString();
			tbShopInfo.setRegAddress(address);
			
			Criteria parameter = new Criteria();
			parameter.put("provinceCode", provinceCode);
			parameter.put("cityCode", cityCode);
			parameter.put("countryCode", countryCode);
			parameter.put("streetCode", streetCode);
			
			String addressInfo = tbProvinceInfoMapper.selectAddressByCode(parameter);
			String baiduAddress = addressInfo+address;
			Map<String, String> map = GaoDeMapUtil.getLatitude(baiduAddress);
			tbShopInfo.setLat(Double.parseDouble(map.get("lat")));
			tbShopInfo.setLon(Double.parseDouble(map.get("lon")));
			tbShopInfo.setIsAutomaticOrder(0);
		}
		tbShopInfo.setSales(HglContants.SHOP_SALES);
		tbShopInfo.setExpLevel(HglContants.SHOP_EXP_LEVEL);
		tbShopInfo.setExp(HglContants.SHOP_EXP);
		updateIamgeName(param, tbShopInfo);
		
		//经销商注册时，插入账户表信息
		TbAccount record = new TbAccount();
		tbAccountMapper.insert(record);
		
		tbShopInfo.setAccountId(record.getId());
		tbShopInfoMapper.insertSelective(tbShopInfo);
		int shopInfoId = tbShopInfo.getId();
		changPath(param,tbShopInfo,shopInfoId);
		TbShopInfo info = tbShopInfoMapper.selectByPrimaryKey(shopInfoId);
		//取场景图片地址，生成二维码
		String shopImageUrl = HglContants.SHOP_INFO_PATH+shopInfoId+File.separator+info.getShopImage1();
		QRCodeUtil.createShopQrCode(shopInfoId,shopImageUrl);
		if(shoptype == HglContants.USER_TYPE_FACTOR){
			
		//	info.setId(shopInfoId);
			int bid=insertBrand(param,shopInfoId);
			info.setVersion(info.getVersion());
			info.setBrandId(bid);
			logger.debug(bid+"==拿到品牌id==========="+info.getBrandId());
			tbShopInfoMapper.updateByPrimaryKeySelective(info);
		}else if(shoptype == HglContants.USER_TYPE_DISTRIBUTOR){
			insertRecommendRules(shopInfoId);
			//
			TbUserGroup tbUserGroup = new TbUserGroup();
			tbUserGroup.setShopId(shopInfoId);
			//注册时生成的用户ID默认为0.
			tbUserGroup.setUserId(0);
			tbUserGroup.setRemark("店铺默认师傅分组");
			tbUserGroup.setName("师傅组");
			//默认为无折扣
			tbUserGroup.setDiscount(0);
			tbUserGroup.setVersion(0);
			tbUserGroupMapper.insertSelective(tbUserGroup);
			}
		return insertWebUser(param, shoptype, shopInfoId,shopImageUrl);
		
	}
	/**
	 * 经销商注册，插入经销商推荐规则
	 * @param shopInfoId
	 * @author zss
	 */
	private void insertRecommendRules(int shopInfoId) {
		List<TbRecommendedRules> rules = new ArrayList<TbRecommendedRules>();
	//	for (int i = 0; i < HglContants.RECOMMEND_TYPESIZE; i++) {
			TbRecommendedRules recommendedRules1 = new TbRecommendedRules();
			recommendedRules1.setShopId(shopInfoId);
			recommendedRules1.setRecommendedType(HglContants.USER_TYPE_CUS);
			recommendedRules1.setIsRecommendedType(HglContants.USER_TYPE_CUS);
			recommendedRules1.setRewardType(HglContants.RECOMMENDED_RULES_REWARD_MONEY);
			rules.add(recommendedRules1);
			TbRecommendedRules recommendedRules2 = new TbRecommendedRules();
			recommendedRules2.setShopId(shopInfoId);
			recommendedRules2.setRecommendedType(HglContants.USER_TYPE_CUS);
			recommendedRules2.setIsRecommendedType(HglContants.USER_TYPE_MASTER);
			recommendedRules2.setRewardType(HglContants.RECOMMENDED_RULES_REWARD_MONEY);
			rules.add(recommendedRules2);
			
			TbRecommendedRules recommendedRules3 = new TbRecommendedRules();
			recommendedRules3.setShopId(shopInfoId);
			recommendedRules3.setRecommendedType(HglContants.USER_TYPE_MASTER);
			recommendedRules3.setIsRecommendedType(HglContants.USER_TYPE_CUS);
			recommendedRules3.setRewardType(HglContants.RECOMMENDED_RULES_REWARD_MONEY);
			rules.add(recommendedRules3);
			
			TbRecommendedRules recommendedRules4 = new TbRecommendedRules();
			recommendedRules4.setShopId(shopInfoId);
			recommendedRules4.setRecommendedType(HglContants.USER_TYPE_MASTER);
			recommendedRules4.setIsRecommendedType(HglContants.USER_TYPE_MASTER);
			recommendedRules4.setRewardType(HglContants.RECOMMENDED_RULES_REWARD_MONEY);
			rules.add(recommendedRules4);
			
			int rulesCount = tbRecommendedRulesMapper.batchInsert(rules);
			logger.info("经销商推荐规则插入记录: " + rulesCount);
			
			List<TbIntegralRules> tList = new ArrayList<TbIntegralRules>();
			TbIntegralRules tbIntegralRules1 = new TbIntegralRules();
			tbIntegralRules1.setShopId(shopInfoId);
			tbIntegralRules1.setType(0);
			tbIntegralRules1.setMoney(0);
			tbIntegralRules1.setUseSituation(0);
			tbIntegralRules1.setPayMoney(0);
			tList.add(tbIntegralRules1);
			
			TbIntegralRules tbIntegralRules2 = new TbIntegralRules();
			tbIntegralRules2.setShopId(shopInfoId);
			tbIntegralRules2.setType(1);
			tbIntegralRules2.setMoney(0);
			tbIntegralRules2.setUseSituation(1);
			tbIntegralRules2.setPayMoney(0);
			for(int i=0 ; i<5 ; i++){
				tList.add(tbIntegralRules2);
			}
			tbIntegralRulesMapper.batchInsert(tList);
			logger.info("经销商积分规则插入记录: " + rulesCount);
	}

	/**
	 * 经销商和厂家用户注册
	 * @param param
	 * @param shoptype
	 * @param shopInfoId
	 * @return
	 * @author zss
	 */
	private int insertWebUser(Map<String, Object> param, Integer shoptype,
			int shopInfoId,String shopImageUrl) {
		TbWebUser tbWebUser = new TbWebUser();
		tbWebUser.setTypeId(shoptype);
		tbWebUser.setState(HglContants.WEB_AUTH_STATUS);  //待审核
		tbWebUser.setUserName(param.get("userName").toString());
		String passWord= MD5Utils.md5(param.get("password").toString(), "UTF-8");
		tbWebUser.setPassword(passWord);
		tbWebUser.setMobile(param.get("mobile").toString());
		tbWebUser.setName(param.get("name").toString());
		tbWebUser.setShopId(shopInfoId);
		tbWebUser.setCreateDt(System.currentTimeMillis());
		tbWebUser.setRecommendShopId(shopInfoId); //经销商注册绑定的ShopId
		String logoCode = recommendCode.create();
		tbWebUser.setLogoCode(logoCode);
		QRCodeUtil.createLogoCodeImage(shopInfoId,logoCode,shopImageUrl);
		tbWebUser.setRoleId(HglContants.REGISTER_ROLE_ID);
		if(!StringUtils.isEmpty(param.get("recommendCode").toString())){
			tbWebUser.setRecommendCode(param.get("recommendCode").toString());
		}
		int dd=tbWebUserMapper.insert(tbWebUser);
		
		//生成积分表
		TbIntegral tbIntegral = new TbIntegral();
		tbIntegral.setUserId(tbWebUser.getId());
		tbIntegralMapper.insertSelective(tbIntegral);
		
		if(!StringUtils.isEmpty(param.get("recommendCode").toString())){
			insertRecommended(tbWebUser.getId(),shoptype,param.get("recommendCode").toString());
		}
		return dd;
	}
	/**
	 * 修改图片路径
	 * @param tbShopInfo
	 * @author zss
	 * @param shopInfoId 
	 * @param param 
	 */
	private void changPath(Map<String, Object> param, TbShopInfo tbShopInfo, int shopInfoId){
		TbShopInfo newtbShopInfo =new TbShopInfo();
		newtbShopInfo.setId(tbShopInfo.getId());
		try {
			if(!StringUtils.isEmpty(tbShopInfo.getLicenseImage()) && !param.get("licenseImage").toString().equals(tbShopInfo.getLicenseImage())){
				ImageUtil.commonUploadNewImage(HglContants.SHOP_INFO_TESTPATH + param.get("licenseImage").toString(), tbShopInfo.getLicenseImage(), shopInfoId, HglContants.SHOP_INFO_TESTPATH, HglContants.SHOP_INFO_PATH);
			}
			if(!StringUtils.isEmpty(tbShopInfo.getOrganizationImage()) && !param.get("organizationImage").toString().equals(tbShopInfo.getOrganizationImage())){
				ImageUtil.commonUploadNewImage(HglContants.SHOP_INFO_TESTPATH + param.get("organizationImage").toString(), tbShopInfo.getOrganizationImage(), shopInfoId, HglContants.SHOP_INFO_TESTPATH, HglContants.SHOP_INFO_PATH);
			}
			if(!StringUtils.isEmpty(tbShopInfo.getTaxpayerImage())&& !param.get("taxpayerImage").toString().equals(tbShopInfo.getTaxpayerImage())){
				ImageUtil.commonUploadNewImage(HglContants.SHOP_INFO_TESTPATH + param.get("taxpayerImage").toString(), tbShopInfo.getTaxpayerImage(), shopInfoId, HglContants.SHOP_INFO_TESTPATH, HglContants.SHOP_INFO_PATH);
			}
			if(!StringUtils.isEmpty(tbShopInfo.getShopImage1()) && !param.get("shopImage1").toString().equals(tbShopInfo.getShopImage1())){
				ImageUtil.commonUploadNewImage(HglContants.SHOP_INFO_TESTPATH + param.get("shopImage1").toString(), tbShopInfo.getShopImage1(), shopInfoId, HglContants.SHOP_INFO_TESTPATH, HglContants.SHOP_INFO_PATH);
			}
			if(!StringUtils.isEmpty(tbShopInfo.getShopImage2()) && !param.get("shopImage2").toString().equals(tbShopInfo.getShopImage2())){
				ImageUtil.commonUploadNewImage(HglContants.SHOP_INFO_TESTPATH + param.get("shopImage2").toString(), tbShopInfo.getShopImage2(), shopInfoId, HglContants.SHOP_INFO_TESTPATH, HglContants.SHOP_INFO_PATH);
			}
			if(!StringUtils.isEmpty(tbShopInfo.getShopImage3()) && !param.get("shopImage3").toString().equals(tbShopInfo.getShopImage3())){
				ImageUtil.commonUploadNewImage(HglContants.SHOP_INFO_TESTPATH + param.get("shopImage3").toString(), tbShopInfo.getShopImage3(), shopInfoId, HglContants.SHOP_INFO_TESTPATH, HglContants.SHOP_INFO_PATH);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//tbShopInfoMapper.updateByPrimaryKeySelective(newtbShopInfo);
	}

	@Override
	public List<TbwebUserShopDto> getWebUserList(Criteria criteria, PageDto page) {
		if(criteria.getOrderByClause()==null){
			criteria.setOrderByClause("id");
			criteria.setOrderByClauseDesc("desc");
		}
		List<TbwebUserShopDto> shopDtos = tbWebUserMapper.selectByCriteria(criteria,page);
        for (TbwebUserShopDto shopDto : shopDtos) {
			String addresss= iAddress.getAddressName(shopDto.getProvince(), shopDto.getCity(), 
					shopDto.getCountry(), shopDto.getStreet());
			shopDto.setAddress(addresss);
		}
            return shopDtos;
	}

	@Override
	public TbwebUserShopDto getUserShop(Integer id) {
		try {
		//	return this.tbWebUserMapper.selectUserShopById(id);
			return this.tbWebUserMapper.selectUserShopById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateUserShop(Map<String, Object> param,int id) {
		return updateShopInfo(param, id,false);
	}

	private int updateShopInfo(Map<String, Object> param, int id,boolean isUserUpdate) {
		int count=0;
		TbShopInfo tbShopInfo =tbShopInfoMapper.selectByPrimaryKey(id);
		if(tbShopInfo!=null){
		
		if(isUserUpdate){
			tbShopInfo.setAuthStatus(HglContants.WEB_AUTH_STATUS);
		}
			
		Integer provinceCode = Integer.parseInt(param.get("regProvince").toString());
		Integer cityCode = Integer.parseInt(param.get("regCity").toString());
		Integer countryCode = Integer.parseInt(param.get("regCountry").toString());
		Integer streetCode = Integer.parseInt(param.get("regStreet").toString());
		if(tbShopInfo.getShopType() == HglContants.USER_TYPE_DISTRIBUTOR){
			tbShopInfo.setShopName(param.get("shopName").toString());
		}
		tbShopInfo.setRegProvince(provinceCode);
		tbShopInfo.setRegCity(cityCode);
		tbShopInfo.setRegCountry(countryCode);
		tbShopInfo.setRegStreet(streetCode);
		tbShopInfo.setScope(param.get("scope").toString());
		if(tbShopInfo.getShopType() == HglContants.USER_TYPE_FACTOR){
			tbShopInfo.setCompanyName(param.get("shopName").toString());
			tbShopInfo.setShopName(param.get("shopName").toString());
			tbShopInfo.setCompanyTel(param.get("companyTel").toString());
			tbShopInfo.setProductTypeId(Integer.parseInt(param.get("productTypeId").toString()));
			tbShopInfo.setLegalRepresentative(param.get("legalRepresentative").toString());
			//修改品牌的信息
			TbBrand brand = brandMapper.selectByPrimaryKey(tbShopInfo.getBrandId());
			if(brand!=null){
				brand.setName(param.get("brandName").toString());
				brand.setProductTypeId(Integer.parseInt(param.get("productTypeId").toString()));
				brand.setManufacturerName(param.get("companyName").toString());
				brandMapper.updateByPrimaryKeySelective(brand);
			}
		}
		String address = param.get("regAddress").toString();
		tbShopInfo.setRegAddress(address);
		tbShopInfo.setVersion(Integer.parseInt(param.get("version").toString()));
		updateIamgeName(param, tbShopInfo);
		changPath(param, tbShopInfo, id);
		Criteria parameter = new Criteria();
		parameter.put("provinceCode", provinceCode);
		parameter.put("cityCode", cityCode);
		parameter.put("countryCode", countryCode);
		parameter.put("streetCode", streetCode);
		String addressInfo = tbProvinceInfoMapper.selectAddressByCode(parameter);
		String baiduAddress = addressInfo+address;
		Map<String, String> map = GaoDeMapUtil.getLatitude(baiduAddress);
		tbShopInfo.setLat(Double.parseDouble(map.get("lat")));
		tbShopInfo.setLon(Double.parseDouble(map.get("lon")));
		//更改店铺二维码
		String shopImageUrl = HglContants.SHOP_INFO_PATH+tbShopInfo.getId()+File.separator+tbShopInfo.getShopImage1();
		QRCodeUtil.createShopQrCode(tbShopInfo.getId(),shopImageUrl);
		//更改个人推荐二维码
		TbWebUser tbWebUser = tbWebUserMapper.selectWebUser(tbShopInfo.getId());
		 QRCodeUtil.createLogoCodeImage(tbWebUser.getRecommendShopId(),tbWebUser.getLogoCode(),shopImageUrl);
		
		count =tbShopInfoMapper.updateByPrimaryKeySelective(tbShopInfo);
		
		}
		return count;
	}
	/**
	 * 修改上传图片的名称
	 * @param param
	 * @param tbShopInfo
	 * @author zss
	 */
	private void updateIamgeName(Map<String, Object> param,
			TbShopInfo tbShopInfo) {
		if(NullU.isNotNull(param.get("licenseImage").toString())){
			String newName = StringUtil.changeFileName("licenseImage",param.get("licenseImage").toString());
			
			tbShopInfo.setLicenseImage(newName);
		}
		if(NullU.isNotNull(param.get("organizationImage").toString())){
			String newName = StringUtil.changeFileName("organizationImage",param.get("organizationImage").toString());
			
			tbShopInfo.setOrganizationImage(newName);
		}
		if(NullU.isNotNull(param.get("taxpayerImage").toString())){
			String newName = StringUtil.changeFileName("taxpayerImage",param.get("taxpayerImage").toString());
			
			tbShopInfo.setTaxpayerImage(newName);
		}
		if(NullU.isNotNull(param.get("shopImage1").toString())){
			String newName = StringUtil.changeFileName("shopImage1",param.get("shopImage1").toString());
			
			tbShopInfo.setShopImage1(newName);
		}
		if(NullU.isNotNull(param.get("shopImage2").toString())){
			String newName = StringUtil.changeFileName("shopImage2",param.get("shopImage2").toString());
			
			tbShopInfo.setShopImage2(newName);
		}
		if(NullU.isNotNull(param.get("shopImage3").toString())){
			String newName = StringUtil.changeFileName("shopImage3",param.get("shopImage3").toString());
			
			tbShopInfo.setShopImage3(newName);
		}
	}

	@Override
	public List<TbwebUserShopDto> getSupplierWebUserList(Criteria criteria,
			PageDto page) {
		criteria.put("typeId", HglContants.USER_TYPE_FACTOR);
		return getShopList(criteria, page);
	}

	private List<TbwebUserShopDto> getShopList(Criteria criteria, PageDto page) {
		if(criteria.getOrderByClause()==null){
			criteria.setOrderByClause("id");
			criteria.setOrderByClauseDesc("desc");
		}
		List<TbwebUserShopDto> shopDtos = tbWebUserMapper.selectByCriteria(criteria,page);
        for (TbwebUserShopDto shopDto : shopDtos) {
			String addresss= iAddress.getAddressName(shopDto.getProvince(), shopDto.getCity(), 
					shopDto.getCountry(), shopDto.getStreet());
			shopDto.setAddress(addresss);
		}
		return shopDtos;
	}

	@Override
	public ShopWebUserDto getShopWebUser(Integer id) {
		ShopWebUserDto shopDto = this.tbShopInfoMapper.getShopWebUserById(id);
		String addresss= iAddress.getAddressName(shopDto.getRegProvince(), shopDto.getRegCity(), 
				shopDto.getRegCountry(), shopDto.getRegStreet());
		shopDto.setAddress(addresss);
		return shopDto;
	}


	@Override
	public Map<String, Object> updateState(Integer id, Integer userId,Integer state,String checkmesg) {
		Map<String,Object> map = new HashMap<String,Object>();
		int count=0;
		int webusercont=0;
		TbShopInfo shopInfo =tbShopInfoMapper.selectByPrimaryKey(id);
		TbWebUser webUser = tbWebUserMapper.selectByPrimaryKey(userId);
		if(shopInfo!=null && state>=0 && webUser!=null){
			shopInfo.setAuthStatus(state);
			webUser.setState(state);
			webUser.setAuditDescription(checkmesg);
			if(HglContants.WEB_AUTH_STATUS1 == state){
				webUser.setRoleId(HglContants.DEFAULT_ROLE_ID);
				if(HglContants.USER_TYPE_FACTOR == shopInfo.getShopType() && !StringUtils.isEmpty(shopInfo.getBrandId().toString())){
					TbBrand brand = brandMapper.selectByPrimaryKey(shopInfo.getBrandId());
					brand.setState(HglContants.BRAND_STATE_ISSHOW);
					brandMapper.updateByPrimaryKeySelective(brand);
				}
				//审核通过时短信通知
				SmsSend.sendStorePass(shopInfo.getContractPhone(),shopInfo.getContract());
			}
			count= tbShopInfoMapper.updateByPrimaryKeySelective(shopInfo);
			webusercont = tbWebUserMapper.updateByPrimaryKeySelective(webUser);
		}
		if(count >0 && webusercont >0){
			
			map.put("state", shopInfo.getAuthStatus());
			map.put("errcode", "0");
			map.put("msg", "审核状态提交成功！");
		}else{
			map.put("errcode", "1");
			map.put("msg", "审核状态提交失败！");
		}
		return map;
	}

	@Override
	public List<TbwebUserShopDto> getDealersUserList(Criteria criteria,
			PageDto page) {
		criteria.put("typeId", HglContants.USER_TYPE_DISTRIBUTOR);
		return getShopList(criteria, page);
	}

	@Override
	public List<WebUserDto> selectByObjectPage(Criteria criteria, PageDto page) {
		
		return tbWebUserMapper.selectByObjectPage(criteria, page);
	}

	@Override
	public Map<String,Object> isApproved(Integer loginUserId) {
		Map<String,Object> map = new HashMap<String,Object>();
		TbWebUser webUser = tbWebUserMapper.selectByPrimaryKey(loginUserId);
		if(webUser.getState()== HglContants.WEB_AUTH_STATUS){
			map.put("errcode", "0");
			map.put("msg", "等待审核中，请稍后...");
		}else if(webUser.getState()== HglContants.WEB_AUTH_STATUS1){
			TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(webUser.getShopId());
			if(shopInfo.getAuthStatus() == HglContants.WEB_AUTH_STATUS1){
				map.put("errcode", "1");
				map.put("msg", "审核通过...");
			}
		}else if(webUser.getState() == HglContants.WEB_AUTH_STATUS2){
			map.put("errcode", "2");
			map.put("msg", webUser.getAuditDescription());
		}else {
			map.put("errcode", "3");
			map.put("msg", webUser.getAuditDescription());
		}
		return map;
	}

	@Override
	public int updateShopByUser(Map<String, Object> param, Integer shopId,
			Integer loginUserId) {
		TbWebUser webUser = tbWebUserMapper.selectByPrimaryKey(loginUserId);
		webUser.setState(HglContants.WEB_AUTH_STATUS);
		tbWebUserMapper.updateByPrimaryKey(webUser);
		//this.updateUserShop(param, shopId);
		return this.updateShopInfo(param, shopId,true);
	}

	@Override
	public Map<String, Object> checkBranName(Integer productTypeId,
			String brandName) {
		Criteria criteria = new Criteria();
		criteria.put("producttypeId", productTypeId);
		criteria.put("name", brandName);
		int count = brandMapper.countByObject(criteria);
		Map<String, Object> map = new HashMap<String,Object>();
		if(count >0){
			map.put("errcode", "1");
			map.put("msg", "此分类下的此品牌名称已存在");
		}else{
			map.put("errcode", "0");
			map.put("msg", "此分类下此品牌不存在");
		}
		return map;
	}

	@Override
	public int insert(TbWebUser record) throws RuntimeException {
		try{
			return tbWebUserMapper.insert(record);
		}catch(Exception e){
			throw new RuntimeException();
		}
	}

	@Override
	public WebUserDto selectByOpenId(String openId) throws RuntimeException {
		try {
			Criteria example = new Criteria();
			example.put("openId", openId);
			List<WebUserDto> tbWebUser = this.tbWebUserMapper.selectByOpenIdList(example);
			if(tbWebUser != null && tbWebUser.size()>0){
				for(WebUserDto user : tbWebUser){
					//判断是否有微信注册账号
					if(user != null && user.getUserName().startsWith("temp_user_")){
						return user;
					}
				}
				//如果没有微信注册账号,返回集合中第一个对象
				return tbWebUser.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	@Override
	public WebUserDto selectByOpenId(String openId,Integer autoLoginFlag) throws RuntimeException {
		try {
			Criteria example = new Criteria();
			example.put("openId", openId);
			example.put("autoLoginFlag", autoLoginFlag);
			List<WebUserDto> tbWebUser = this.tbWebUserMapper.selectByOpenIdList(example);
			if(tbWebUser != null && tbWebUser.size()>0){
				return tbWebUser.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public int registerCustomer(TbWebUser webUser, String headimgurl) {
		if(StringUtils.isEmpty(webUser.getUserName())){
			webUser.setUserName(webUser.getMobile());
			webUser.setName(webUser.getMobile());
		}else{
			if(StringUtils.isEmpty(webUser.getName())){
				webUser.setName(webUser.getUserName());
			}
		}
		if(!StringUtils.isEmpty(webUser.getPassword())){
			webUser.setPassword(MD5Utils.md5(webUser.getPassword(), "UTF-8"));
		}
		String recommondCode="";
		//推荐用户的推荐码,没有推荐码就是默认店铺
		if(!StringUtils.isEmpty(webUser.getRecommendCode())){
			 recommondCode = webUser.getRecommendCode();
		}else{
			recommondCode = HglContants.RECOMMONDCODE_DEFULT;
		}
		webUser.setRecommendCode(recommondCode);
		//生成推荐码
		String code = recommendCode.create();
		logger.debug("logoCode produce..."+code);
		webUser.setLogoCode(code); //生成自己的推荐码，用于推荐别人
		
		webUser.setTypeId(HglContants.USER_TYPE_CUS); //终端客户
		webUser.setState(HglContants.WEB_AUTH_STATUS1); //审核通过
		webUser.setCreateDt(System.currentTimeMillis());
		
		//userInfo信息
		TbUserInfo userInfo = new TbUserInfo();
		userInfo.setShopType(HglContants.USER_TYPE_CUS);
		userInfo.setCreateDt(System.currentTimeMillis());
		if(headimgurl !=null){
			userInfo.setImage(headimgurl);
		}
		//注册时，插入账户表信息
		TbAccount record = new TbAccount();
		tbAccountMapper.insert(record);
		userInfo.setAccountId(record.getId());
		
		userInfo.setVersion(1);
		if(tbUserInfoMapper.insert(userInfo)>0){
			
			
			webUser.setUserinfoId(userInfo.getId());
			//根据推荐码查找推荐人
			Criteria criteria = new Criteria();
			criteria.put("logoCode", recommondCode);
			TbWebUser webUserCode = this.selectUserByLogoCode(criteria);
			if(webUserCode != null){
				//如果是经销商推荐，则直接绑定经销商的shopId；如果不是经销商推荐，则绑定推荐recommendShopId
				if(webUserCode.getTypeId().equals(HglContants.USER_TYPE_DISTRIBUTOR)){
					webUser.setRecommendShopId(webUserCode.getShopId());
				}else{
					webUser.setRecommendShopId(webUserCode.getRecommendShopId());  //绑定店铺id
				}
				
				/**
				 * 拿到用户的头像生成二维码
				 */
				String userImageUrl = HglContants.USER_PATH + userInfo.getId() + File.separator + headimgurl;
				 QRCodeUtil.createLogoCodeImage(webUserCode.getRecommendShopId(),code,userImageUrl);
			}else{
				return 0;
			}
			if(tbWebUserMapper.insert(webUser)==1){
				
				//生成用记积分表
				TbIntegral tbIntegral = new TbIntegral();
				tbIntegral.setUserId(webUser.getId());
				tbIntegralMapper.insertSelective(tbIntegral);
				
				//如果注册成功，需要更新推荐码记录
				if(!StringUtils.isEmpty(recommondCode)){					
					insertRecommended(webUser.getId(),HglContants.USER_TYPE_CUS,recommondCode);
				}	
				
				//注册成功后将绑定店铺添加到收藏
				if(webUserCode!=null){
					TbShopInfo shopInfo = new TbShopInfo();
					if(webUserCode.getTypeId().equals(HglContants.USER_TYPE_DISTRIBUTOR)){
						shopInfo = shopInfoService.selectByPrimaryKey(webUserCode.getShopId());
					}else{
						shopInfo = shopInfoService.selectByPrimaryKey(webUserCode.getRecommendShopId());
					}					
					saveInfoService.insertWapShopInfo(shopInfo, webUser.getId(), 1);
				}				
				return 1;
			}else{
				return 0;
			}
		}
		return 0;
	}

	@Override
	public int registerWorker(WebUserDto webUser,HttpServletRequest request) {
		//判定用户是否已经被注册
		Criteria criteria = new Criteria();
		criteria.put("userName", webUser.getUserName());
		List<TbWebUser> webUsers = tbWebUserMapper.selectByObject(criteria);
		if(webUsers.size()>0){
			return 0;
		}
		
		//判定电话号码是否已经被注册
		criteria = new Criteria();
		criteria.put("mobile", webUser.getMobile());
		webUsers = tbWebUserMapper.selectByObject(criteria);
		if(webUsers.size()>0){
			return 0;
		}
		
		String recommondCode ="";
		//推荐用户的推荐码
		if(!StringUtils.isEmpty(webUser.getRecommendCode())){
			 recommondCode = webUser.getRecommendCode();
		}else{
			recommondCode = HglContants.RECOMMONDCODE_DEFULT;
		}
		webUser.setRecommendCode(recommondCode);
		//转型为MultipartHttpRequest
		MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest)request;
		MultipartFile image  =  multipartRequest.getFile("image");  	
		String fileNameOrg = image.getOriginalFilename();	
		String 	fileName =StringUtil.changeFileName("image",fileNameOrg); //师傅头像
		
		MultipartFile imgFace = multipartRequest.getFile("imageFace"); //师傅正面照
		String fileNameFaceOrg = imgFace.getOriginalFilename();
		String fileNameFace = StringUtil.changeFileName("imageFace",fileNameFaceOrg);
		
		MultipartFile imgBack = multipartRequest.getFile("imageBack"); //师傅反面照
		String fileNameBackOrg = imgBack.getOriginalFilename();
		String fileNameBack = StringUtil.changeFileName("imageBack",fileNameBackOrg);
		TbUserInfo userInfo = new TbUserInfo();
		userInfo.setShopType(HglContants.USER_TYPE_MASTER);
		userInfo.setImage(fileName);	
		userInfo.setImageFace(fileNameFace);
		userInfo.setIamgeBack(fileNameBack);
		userInfo.setCreateDt(System.currentTimeMillis());
		userInfo.setVersion(1); //设置版本为1
		userInfo.setServiceType(webUser.getSkill());
		
		TbAccount record = new TbAccount();
		tbAccountMapper.insert(record);
		userInfo.setAccountId(record.getId());
		
		if(tbUserInfoMapper.insert(userInfo)==1){
			try {				
				userInfoService.uploadImage(image,fileName,String.valueOf(userInfo.getId()));
				userInfoService.uploadImage(imgFace,fileNameFace, String.valueOf(userInfo.getId()));
				userInfoService.uploadImage(imgBack,fileNameBack, String.valueOf(userInfo.getId()));
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("图片上传失败");
			}
			
			if(!StringUtils.isEmpty(webUser.getPassword())){
				webUser.setPassword(MD5Utils.md5(webUser.getPassword(), "UTF-8"));
			}
					
			//生成推荐码
			String code = recommendCode.create();
	        logger.debug("logoCode produce ..."+ code);
	        webUser.setLogoCode(code);
	        
	        String userImageUrl = HglContants.USER_PATH + userInfo.getId() + File.separator + fileName;
	        QRCodeUtil.createLogoCodeImage(webUser.getRecommendShopId(),code,userImageUrl);
	        webUser.setCreateDt(System.currentTimeMillis());
			webUser.setTypeId(HglContants.USER_TYPE_MASTER);
			webUser.setState(HglContants.WEB_AUTH_STATUS); //待审核
			webUser.setUserinfoId(userInfo.getId());
			//根据推荐码查找推荐人
			criteria = new Criteria();
			criteria.put("logoCode", recommondCode);
			TbWebUser webUserCode = this.selectUserByLogoCode(criteria);
			if(webUserCode != null){
				webUser.setRecommendShopId(webUserCode.getRecommendShopId());
			}else{
				return 0;
			}
					
			if(tbWebUserMapper.insert(webUser)==1){
				
				//生成积分表
				TbIntegral tbIntegral = new TbIntegral();
				tbIntegral.setUserId(webUser.getId());
				tbIntegralMapper.insertSelective(tbIntegral);
				
				//添加默认分组
				//得到绑定店铺的默认分组
				
				Criteria parameter = new Criteria();
				parameter.put("shopId", webUser.getRecommendShopId());
				TbUserGroup tbUserGroup = tbUserGroupMapper.selectByObject(parameter).get(0);
				
				TbGroupDistribution tbGroupDistribution = new TbGroupDistribution();
				tbGroupDistribution.setShopId(webUser.getRecommendShopId());
				tbGroupDistribution.setUserId(webUser.getId());
				tbGroupDistribution.setGroupId(tbUserGroup.getId().toString());
				tbGroupDistribution.setVersion(0);
				tbGroupDistributionMapper.insertSelective(tbGroupDistribution);
				
				//如果注册成功，需要更新推荐码记录
				if(!StringUtils.isEmpty(recommondCode)){
					insertRecommended(webUser.getId(),HglContants.USER_TYPE_MASTER,recommondCode);	
				}		
				
				//注册成功后将绑定店铺添加到收藏
				if(webUserCode!=null){
					TbShopInfo shopInfo = new TbShopInfo();
					if(webUserCode.getTypeId().equals(HglContants.USER_TYPE_DISTRIBUTOR)){
						shopInfo = shopInfoService.selectByPrimaryKey(webUserCode.getShopId());
					}else{
						shopInfo = shopInfoService.selectByPrimaryKey(webUserCode.getRecommendShopId());
					}
					saveInfoService.insertWapShopInfo(shopInfo, webUser.getId(), 1);
				}	
				
				return 1;	
			}
		}		
		return 0;
	}
	
	@Override
	public List<UserRecommendedDto> selectRecommended(String recommendCode) {
		Criteria criteria = new Criteria();
		criteria.put("recommendedCode", recommendCode);
		List<UserRecommendedDto> recommendedDtos = tbWebUserMapper.selectRecommended(criteria);
		return recommendedDtos;
	}

	@Override
	public boolean updateUserOpenId(WebUserDto webUser,Integer id) throws RuntimeException {
		Criteria criteria = new Criteria();
    	criteria.put("openId", webUser.getOpenId());
    	criteria.put("id", webUser.getId());
    	int count = tbWebUserMapper.updateUserOpenId(criteria);
    	if(count >0 && id != null){
    		criteria = new Criteria();
        	criteria.put("openId", "");
        	criteria.put("id", id);
        	count = tbWebUserMapper.updateUserOpenId(criteria);
    		return count>0 ? true : false;
    	}
		return false;
	}
	
	@Override
	public int updateUserOpenId(Integer id,String openId) throws RuntimeException {
		Criteria criteria = new Criteria();
    	criteria.put("openId", openId);
    	criteria.put("id", id);
    	return tbWebUserMapper.updateUserOpenId(criteria);
	}

	@Override
	public int updatePassword(TbWebUser webUser, String newPassword)
			throws RuntimeException {
    	String pwd = "";
    	if(webUser!=null){
    		if(!StringUtils.isEmpty(newPassword)){
    			pwd = MD5Utils.md5(newPassword, "UTF-8");
    		}   		
    		webUser.setPassword(pwd);
    		return tbWebUserMapper.updateByPrimaryKeySelective(webUser);
    	}
		return 0;
	}

	@Override
	public TbWebUser selectUserByRecommendCode(Criteria criteria)
			throws RuntimeException {
		try{
			
			return tbWebUserMapper.selectUserByRecommendCode(criteria);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}		
	}

	@Override
	public TbWebUser selectUserByLogoCode(Criteria criteria)
			throws RuntimeException {
		try{
			return tbWebUserMapper.selectUserByLogoCode(criteria);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Map<String, Object> checkRecommedCode(String recommendCode) {
		Map<String, Object> map = new HashMap<String,Object>();
		Criteria criteria = new Criteria();
		criteria.put("logoCode", recommendCode);
		List<TbWebUser> list =tbWebUserMapper.selectByObject(criteria);
		if(list.size()>0){
			map.put("errcode", "0");
			map.put("msg", "此推荐码存在");
		}else{
			map.put("errcode", "1");
			map.put("msg", "不存在此推荐码");
		}
		
		return map;
	}
	
	@Override
	public List<TbWebUser> selectWebUserByGroupId(Criteria example){
		return tbWebUserMapper.selectWebUserByGroupId(example);
	 }
	
	@Override
	public List<WebUserDto> selectUserGroup(Criteria example,PageDto page){
		List<WebUserDto> uList = selectUserGroupPage(example,page);
		if(uList.size() > 0){
			for(WebUserDto u : uList){
				WebUserGroupDto uGroup = new WebUserGroupDto();
				BeanUtils.copyProperties(u, uGroup);
				if(!com.liguo.hgl.proxydao.util.StringUtil.isBlank(u.getUserGroup())){
					List<String> s = Arrays.asList(u.getUserGroup().split(","));
					List<Integer> ids = new ArrayList<Integer>();
					for(String i : s){
						ids.add(Integer.parseInt(i));
					}
					u.setGroupIdList(ids);
				}
				}
			}
		 return uList;
	 }
	
	@Override
	public List<WebUserDto> selectUserGroupPage(Criteria criteria, PageDto page) {
		
		return tbWebUserMapper.selectUserGroupPage(criteria, page);
	}

	@Override
	public List<TbWebUser> selectBySaveShop(Integer shopId, Integer userId) {
		Criteria example = new Criteria();
		example.put("shopId", shopId);
		example.put("saveType", HglContants.SHOP_SAVE);
		example.put("userId", userId);
		return tbWebUserMapper.selectBySaveShop(example);
	}
	
	@Override
	public List<TbWebUser> selectBySaveShopUser(Integer shopId) {
		Criteria example = new Criteria();
		example.put("shopId", shopId);
		example.put("saveType", HglContants.SHOP_SAVE);
		example.put("bind", HglContants.SAVE_SHOP_NOT_BIND);
		return tbWebUserMapper.selectBySaveShop(example);
	}

	@Override
	public TbWebUser selectByUserName(Criteria parameter) {
		return tbWebUserMapper.selectByUserName(parameter);
	}

	@Override
	public int updateUserPassword(Criteria parameter) {
		return tbWebUserMapper.updateUserPassword(parameter);
	}

	@Override
	public int updateUserOpenIdNull(String openId) {
		Criteria criteria = new Criteria();
    	criteria.put("openId", openId);
		return tbWebUserMapper.updateUserOpenIdNull(criteria);
	}

	@Override
	public int updateUserAutoLoginFlag(Integer id,Integer autoLoginFlag) {
		Criteria criteria = new Criteria();
    	criteria.put("id", id);
    	criteria.put("autoLoginFlag", autoLoginFlag);
		return tbWebUserMapper.updateUserAutoLoginFlag(criteria);
	}
	
	@Override
	public int updateAutoLoginFlag(Integer id,Integer autoLoginFlagOld,Integer autoLoginFlag,String openId) {
		Criteria criteria = new Criteria();
    	criteria.put("id", id);
    	criteria.put("autoLoginFlag", autoLoginFlagOld);
    	criteria.put("openId", openId);
		int updataCount = tbWebUserMapper.updateUserAutoLoginFlagList(criteria);
		logger.info("updateUserAutoLoginFlagList: " + updataCount);
		int count = updateUserAutoLoginFlag(id,autoLoginFlag);
		logger.info("updateUserAutoLoginFlag: " + count);
		return count;
	}

	@Override
	public TbWebUser selectWebUserByShopId(Integer shopId) {
		// TODO Auto-generated method stub
		return tbWebUserMapper.selectWebUser(shopId);
	}

	@Override
	public WebUserDto selectByMobileAndPwd(String mobile,String password) {
		Criteria example = new Criteria();
		example.put("mobile", mobile);
		example.put("password", password);
		WebUserDto tbWebUser = this.tbWebUserMapper.selectByMobileAndPwd(example);
		
		return tbWebUser;
	}

	@Override
	public int updateUserMobile(Criteria parameter) {
		return tbWebUserMapper.updateUserMobile(parameter);
	}

	@Override
	public int doUpdateWorker(WebUserDto webUser,HttpServletRequest request){
		//转型为MultipartHttpRequest
		MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest)request;
		MultipartFile image  =  multipartRequest.getFile("image");  	
		String fileNameOrg = image.getOriginalFilename();	
		String 	fileName =StringUtil.changeFileName("image",fileNameOrg); //师傅头像
		
		MultipartFile imgFace = multipartRequest.getFile("imageFace"); //师傅正面照
		String fileNameFaceOrg = imgFace.getOriginalFilename();
		String fileNameFace = StringUtil.changeFileName("imageFace",fileNameFaceOrg);
		
		MultipartFile imgBack = multipartRequest.getFile("imageBack"); //师傅反面照
		String fileNameBackOrg = imgBack.getOriginalFilename();
		String fileNameBack = StringUtil.changeFileName("imageBack",fileNameBackOrg);
		TbWebUser webUserFd = tbWebUserMapper.selectByPrimaryKey(webUser.getId());
		if(webUserFd!=null){
			TbUserInfo userInfo = tbUserInfoMapper.selectByPrimaryKey(webUserFd.getUserinfoId());
			if(userInfo!=null){
				if(fileName!=null){
					userInfo.setImage(fileName);
				}
				if(fileNameFace!=null){
					userInfo.setImageFace(fileNameFace);
				}
				if(fileNameBack!=null){
					userInfo.setIamgeBack(fileNameBack);
				}				
				userInfo.setCreateDt(System.currentTimeMillis());
				userInfo.setServiceType(webUser.getSkill());
				if(tbUserInfoMapper.updateByPrimaryKeySelective(userInfo)==1){
					try {
						if(fileName!=null){
							userInfoService.uploadImage(image,fileName,String.valueOf(userInfo.getId()));
						}
						if(fileNameFace!=null){
							userInfoService.uploadImage(imgFace,fileNameFace, String.valueOf(userInfo.getId()));
						}
						if(fileNameBack!=null){
							userInfoService.uploadImage(imgBack,fileNameBack, String.valueOf(userInfo.getId()));
						}						
					} catch (IOException e) {
						e.printStackTrace();
						logger.debug("图片上传失败");
					}
					return 1;
				}
			}		
		}	
		return 0;
	}
	
}