package com.liguo.hgl.service.impl;

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
import com.liguo.hgl.proxydao.dao.TbBrandMapper;
import com.liguo.hgl.proxydao.dao.TbCustomerServiceMapper;
import com.liguo.hgl.proxydao.dao.TbRecommendedMapper;
import com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper;
import com.liguo.hgl.proxydao.dao.TbSaveInfoMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.dto.UserLetterDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.CustomerServiceDto;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbRecommended;
import com.liguo.hgl.proxydao.model.TbRecommendedRules;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbSaveInfoService;

@Service
@Scope(value="prototype")
public class TbSaveInfoServiceImpl implements TbSaveInfoService {
    @Autowired
    private TbSaveInfoMapper tbSaveInfoMapper;
    
    @Autowired
    private TbBrandMapper tbBrandMapper;
    
    @Autowired
    private TbWebUserMapper tbWebUserMapper;
    
    @Autowired
    private TbShopInfoMapper tbShopInfoMapper;
    
    @Autowired
    private TbCustomerServiceMapper tbCustomerServiceMapper;
    
    @Autowired
    private TbRecommendedRulesMapper tbRecommendedRulesMapper;
    
    @Autowired
    private TbRecommendedMapper tbRecommendedMapper;
    private static final Logger logger = LoggerFactory.getLogger(TbSaveInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbSaveInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbSaveInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbSaveInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbSaveInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbSaveInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbSaveInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbSaveInfo record) throws RuntimeException {
        try {
            return this.tbSaveInfoMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbSaveInfo record) throws RuntimeException {
        try {
            return this.tbSaveInfoMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbSaveInfo record) throws RuntimeException {
        try {
            return this.tbSaveInfoMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbSaveInfo> selectByObjectPage(Criteria example, PageDto page)
			throws RuntimeException {
		try{
			return this.tbSaveInfoMapper.selectByObjectPage(example, page);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertProductSelective(TbProductDto productDto,Integer createdBy) throws RuntimeException{
		try{
			TbSaveInfo saveInfo = new TbSaveInfo();			
			if(productDto!=null){
				saveInfo.setSaveId(productDto.getId());
				saveInfo.setSaveName(productDto.getName());
				saveInfo.setBrandName(productDto.getBrandName());
				saveInfo.setSaveType(HglContants.GOOD_SAVE);
				saveInfo.setSaveUrl(productDto.getPimgOne());
				saveInfo.setSaveImage(productDto.getPimgOne());
				if(productDto.getPrice()!=null){
					saveInfo.setPrice(Double.valueOf(productDto.getPrice().toString().toString()));
				}
				saveInfo.setCreateDt(System.currentTimeMillis());
				saveInfo.setCreateBy(createdBy);
				saveInfo.setCreateDt(System.currentTimeMillis());			
				saveInfo.setCreateBy(createdBy);			
			}
			return this.tbSaveInfoMapper.insertSelective(saveInfo);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public TbSaveInfo selectSaveInfo(Integer saveId,
			Integer saveType, Integer createdBy) throws RuntimeException{
		TbSaveInfo saveInfo = null;
		if(createdBy==null){
			return saveInfo;
		}
		
		try{			
			Criteria criteria = new Criteria();
			criteria.put("saveId", saveId);
			criteria.put("saveType", saveType);
			criteria.put("createBy", createdBy);
			List<TbSaveInfo> list = this.tbSaveInfoMapper.selectByObject(criteria);
			if(list.size()>0){
				saveInfo = list.get(0);
			}			
			return saveInfo;
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertShopInfoSelective(TbShopInfo shopInfo, Integer createdBy)
			throws RuntimeException {
			try{
				TbSaveInfo saveInfo = new TbSaveInfo();			
				if(shopInfo!=null){
					saveInfo.setSaveId(shopInfo.getId());
					saveInfo.setSaveName(shopInfo.getShopName());
					saveInfo.setBrandId(shopInfo.getBrandId());
					saveInfo.setBrandName(shopInfo.getCompanyName());
					saveInfo.setSaveType(HglContants.SHOP_SAVE);
					saveInfo.setSaveUrl(shopInfo.getShopImage1());
					saveInfo.setSaveImage(shopInfo.getShopImage1());
					saveInfo.setCreateDt(System.currentTimeMillis());			
					saveInfo.setCreateBy(createdBy);
					saveInfo.setBind(0);
					
					return this.tbSaveInfoMapper.insertSelective(saveInfo);
				}				
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		return 0;
	}

	@Override
	public int insertMerchantSelective(Integer brandId, Integer createdBy)
			throws RuntimeException {
		try{
			TbSaveInfo saveInfo = new TbSaveInfo();			
			if(brandId!=null){
				saveInfo.setSaveId(brandId);
				TbBrand brand = tbBrandMapper.selectByPrimaryKey(brandId);
				saveInfo.setSaveName(brand.getName());
				saveInfo.setBrandId(brandId);
				saveInfo.setBrandName(brand.getName());
				saveInfo.setSaveType(HglContants.SHOP_SAVE);
				saveInfo.setSaveUrl("");
				saveInfo.setSaveImage("");
				saveInfo.setCreateDt(System.currentTimeMillis());			
				saveInfo.setCreateBy(createdBy);	
				
				return this.tbSaveInfoMapper.insertSelective(saveInfo);
			}				
		}catch(Exception e){
			throw new RuntimeException(e);
		}		
		return 0;
	}

	@Override
	public int insertWorkSelective(TbWebUser webUser) {
		
		TbSaveInfo saveInfo = new TbSaveInfo();
		saveInfo.setSaveType(HglContants.WORKER_SAVE);
		return this.tbSaveInfoMapper.insert(saveInfo);
	}

	@Override
	public int insertServiceSelective(CustomerServiceDto serviceDto,Integer createdBy) throws RuntimeException{
		try{
			TbSaveInfo saveInfo = new TbSaveInfo();
			saveInfo.setSaveType(HglContants.SERVICE_SAVE);
			saveInfo.setSaveId(serviceDto.getId());
			saveInfo.setSaveName(serviceDto.getName());
			saveInfo.setBrandName(serviceDto.getbName());
			saveInfo.setSaveUrl(serviceDto.getCimgOne());
			saveInfo.setSaveImage(serviceDto.getCimgOne());
			saveInfo.setPrice(serviceDto.getPrice());
			saveInfo.setCreateDt(System.currentTimeMillis());
			saveInfo.setCreateBy(createdBy);
			return this.tbSaveInfoMapper.insertSelective(saveInfo);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int insertWapProduct(TbWapProductDto wapProductDto, Integer createdBy)
			throws RuntimeException {
		try{
			TbSaveInfo saveInfo = new TbSaveInfo();			
			if(wapProductDto!=null){
				saveInfo.setSaveId(wapProductDto.getId());
				saveInfo.setSaveName(wapProductDto.getName());
				saveInfo.setBrandName(wapProductDto.getBrandName());
				saveInfo.setSaveType(HglContants.GOOD_SAVE);
				saveInfo.setSaveUrl(wapProductDto.getPimgOne());
				saveInfo.setSaveImage(wapProductDto.getPimgOne());
				if(wapProductDto.getPrice()!=null){
					saveInfo.setPrice(Double.valueOf(wapProductDto.getPrice().toString()));
				}
				saveInfo.setCreateDt(System.currentTimeMillis());			
				saveInfo.setCreateBy(createdBy);				
			}
			return this.tbSaveInfoMapper.insertSelective(saveInfo);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertWapShopInfo(TbShopInfo shopInfo, Integer createdBy,Integer bind)
			throws RuntimeException {
		try{
			TbSaveInfo saveInfo = new TbSaveInfo();			
			if(shopInfo!=null){
				saveInfo.setSaveId(shopInfo.getId());
				saveInfo.setSaveName(shopInfo.getShopName());
				saveInfo.setBrandName(shopInfo.getShopName());
				saveInfo.setSaveType(HglContants.SHOP_SAVE);
				saveInfo.setSaveUrl(shopInfo.getShopImage1());
				saveInfo.setSaveImage(shopInfo.getShopImage1());
				saveInfo.setCreateDt(System.currentTimeMillis());			
				saveInfo.setCreateBy(createdBy);
				saveInfo.setBind(bind);
			}
			return this.tbSaveInfoMapper.insertSelective(saveInfo);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<UserLetterDto> getUserLetter(Integer loginUserId) {
		TbWebUser tbWebUser = tbWebUserMapper.selectByPrimaryKey(loginUserId);
		Criteria criteria =new Criteria();
		criteria.put("reserved", HglContants.LETTER_DEFAULT);
		criteria.put("userId", loginUserId);
		criteria.put("type", HglContants.SHOP_SAVE);
		if(tbWebUser.getTypeId()==HglContants.USER_TYPE_DISTRIBUTOR){
			criteria.put("shopId", tbWebUser.getShopId());
			return tbSaveInfoMapper.getShopLetter(criteria);
		}else{
			List<UserLetterDto> letterDtos = tbSaveInfoMapper.getUserLetter(criteria);
			for (UserLetterDto userLetterDto : letterDtos) {
				TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(userLetterDto.getShopId());
				userLetterDto.setShopImage(shopInfo.getShopImage1());
			}
			return letterDtos;
		}
	}

	@Override
	public TbSaveInfo selectDefaultBindShop(Integer createdBy)
			throws RuntimeException {
		TbSaveInfo saveInfo = null;
		try{
			Criteria criteria = new Criteria();
			criteria.put("createBy", createdBy);
			criteria.put("saveType", HglContants.SHOP_SAVE);
			criteria.put("saveId", HglContants.HGL_DEFAULT_SHOP); /*默认绑定店铺*/
			
			List<TbSaveInfo> list= tbSaveInfoMapper.selectByObject(criteria);
			if(list.size()>0){
				saveInfo = list.get(0);
			}
			return saveInfo;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * 用户的shopId是否收藏
	 * @param createdBy
	 * @param shopId
	 * @return
	 */
	private Boolean selectSaveShop(Integer createdBy, Integer shopId) {
		Map<Integer, TbSaveInfo> map = new HashMap<Integer, TbSaveInfo>();
		Criteria criteria = new Criteria();
		criteria.put("createBy", createdBy);
		criteria.put("saveType", HglContants.SHOP_SAVE);	
		criteria.put("saveId", shopId); /*默认绑定店铺*/		
		List<TbSaveInfo> list= tbSaveInfoMapper.selectByObject(criteria);
		return (list!=null && !list.isEmpty() && list.get(0)!=null);		
	}

	@Override
	public String doOperateShopSaveInfo(Integer shopId, Integer typeId,
			Integer createdBy) throws RuntimeException {
		if(createdBy==null){
			return String.valueOf(3); //表示用户还没有登录,跳转到登录页面
		}else{
			TbSaveInfo saveInfo = this.selectSaveInfo(shopId,HglContants.SHOP_SAVE,createdBy);
			if(typeId==1){     //typeId:1 表示添加到收藏
				TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(shopId);
				if(saveInfo==null){
					TbSaveInfo saveInfoDefault = this.selectDefaultBindShop(createdBy); //查看是否存在绑定店铺
					if(saveInfoDefault==null){
						if(this.insertWapShopInfo(shopInfo, createdBy, 0)==1){ //不绑定新店铺
							return String.valueOf(1);   //收藏成功
						}else{
							return String.valueOf(-1);  //收藏失败
						}
					}else{
						if(this.insertWapShopInfo(shopInfo, createdBy, 1)==1){  //绑定店铺
							this.deleteByPrimaryKey(saveInfoDefault.getId()); //删除默认绑定店铺
							return String.valueOf(1);    //收藏成功
						}else{
							return String.valueOf(-1);   //收藏失败
						}
					}
				}else{
					return String.valueOf(0);   //表示该店铺已经被收藏
				}
			}else if(typeId==0){   //0表示取消收藏
				if(saveInfo!=null && saveInfo.getBind()==0){  //非绑定店铺才允许被取消收藏
					if(this.deleteByPrimaryKey(saveInfo.getId())==1){
						return String.valueOf(2);
					}else{
						return String.valueOf(-2); //取消收藏失败
					}
				}			
			}
		}
		return String.valueOf(false);
	}

	@Override
	public Integer doBindShopInfo(String recommendCode,Integer shopId, WebUserDto createdBy) {
		//当前访问的店铺为惠给利用户
		if(HglContants.HGL_DEFAULT_SHOP==shopId){
			return null;
		}		
		//当前访问的店铺已经收藏
		if(selectSaveShop(createdBy.getId(),shopId)){
			return null;			
		}
		if(!StringUtils.isBlank(recommendCode)){
			insertRecommendedInfo(recommendCode,shopId.toString(),createdBy);
		}
		TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(shopId);		
		TbSaveInfo  saveInfo= new TbSaveInfo();
		saveInfo.setSaveId(shopId);
		saveInfo.setSaveName(shopInfo.getShopName());
		saveInfo.setBrandName(shopInfo.getShopName());
		saveInfo.setSaveType(HglContants.SHOP_SAVE);
		saveInfo.setSaveUrl(shopInfo.getShopImage1());
		saveInfo.setSaveImage(shopInfo.getShopImage1());
		saveInfo.setCreateDt(System.currentTimeMillis());			
		saveInfo.setCreateBy(createdBy.getId());
		
		//如果登录用户绑定的店铺为惠给利用户
		if(HglContants.HGL_DEFAULT_SHOP==createdBy.getRecommendShopId()){
			TbSaveInfo defaultSaveInfo = this.selectDefaultBindShop(createdBy.getId());			
			//修改用户之前收藏的惠给利经销商为 当前被扫经销商
			saveInfo.setId(defaultSaveInfo.getId());
			saveInfo.setVersion(defaultSaveInfo.getVersion());
			saveInfo.setBind(1);
			tbSaveInfoMapper.updateByPrimaryKey(saveInfo);
			TbWebUser tbWebUser = new TbWebUser();
			tbWebUser.setId(createdBy.getId());
			tbWebUser.setRecommendShopId(shopInfo.getId());
			//修改当前用户的经销商为当前被扫经销商
			tbWebUserMapper.updateByPrimaryKeySelective(tbWebUser);
		}else if(HglContants.HGL_DEFAULT_SHOP!=createdBy.getRecommendShopId()){//如果登录用户绑定的店铺不是为惠给利用户 则添加收藏			
			saveInfo.setBind(0);
			tbSaveInfoMapper.insert(saveInfo);
			return null;
		}
		return 	shopId;	
	}

	@Override
	public void doBindShopInfo(Integer shopId, Integer createdBy) {
		if(shopId==1){
			return;
		}
		TbSaveInfo saveInfo = null;
		Criteria criteria = new Criteria();
		criteria.put("saveId", shopId);
		criteria.put("saveType", HglContants.SHOP_SAVE);
		criteria.put("createBy", createdBy);
		List<TbSaveInfo> list = tbSaveInfoMapper.selectByObject(criteria);
		if(list.size()>0){
			saveInfo = list.get(0);
		}else{
			TbSaveInfo defaultSaveInfo = this.selectDefaultBindShop(createdBy);			
			saveInfo = new TbSaveInfo();
			TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(shopId);
			saveInfo.setSaveId(shopInfo.getId());
			saveInfo.setSaveName(shopInfo.getShopName());
			saveInfo.setBrandName(shopInfo.getShopName());
			saveInfo.setSaveType(HglContants.SHOP_SAVE);
			saveInfo.setSaveUrl(shopInfo.getShopImage1());
			saveInfo.setSaveImage(shopInfo.getShopImage1());
			saveInfo.setCreateDt(System.currentTimeMillis());			
			saveInfo.setCreateBy(createdBy);
			if(defaultSaveInfo!=null){
				saveInfo.setBind(1);
				tbSaveInfoMapper.deleteByPrimaryKey(defaultSaveInfo.getId());
				//将webUser的recommendShopId设置为绑定店铺的id
				TbWebUser webUser = tbWebUserMapper.selectByPrimaryKey(createdBy);
				if(webUser!=null){
					webUser.setRecommendShopId(shopId);
					tbWebUserMapper.updateByPrimaryKey(webUser);
				}
			}else{
				//如果没有任何收藏记录，则绑定
				criteria = new Criteria();
				criteria.put("saveType", HglContants.SHOP_SAVE);
				criteria.put("createBy", createdBy);
				List<TbSaveInfo> saveInfos = tbSaveInfoMapper.selectByObject(criteria);
				if(saveInfos.size()>0){
					saveInfo.setBind(0);
				}else{
					saveInfo.setBind(1);
					//将webUser的recommendShopId设置为绑定店铺的id
					TbWebUser webUser = tbWebUserMapper.selectByPrimaryKey(createdBy);
					if(webUser!=null){
						webUser.setRecommendShopId(shopId);
						tbWebUserMapper.updateByPrimaryKey(webUser);
					}
				}				
			}
			tbSaveInfoMapper.insert(saveInfo);
		}	
	}

	@Override
	public String doWapOperateServiceSaveInfo(Integer serviceId, Integer typeId,Integer createdBy) {
		if(createdBy==null){
			return String.valueOf(3); //createdBy为null表示用户未登陆
		}else{
			TbSaveInfo saveInfo = this.selectSaveInfo(serviceId, HglContants.SERVICE_SAVE, createdBy);
			if(HglContants.SAVE_OPERATE_SAVE==typeId){  //表示收藏
				if(saveInfo==null){
    				CustomerServiceDto c = tbCustomerServiceMapper.selectInfoById(serviceId);
    				if(this.insertServiceSelective(c, createdBy)==1){
    					return String.valueOf(1);   //表示收藏成功
    				}else{
    					return String.valueOf(-1);  //表示收藏失败
    				}
    			}else{
    				return String.valueOf(0); //表示该产品已经被收藏
    			}
			}else if(HglContants.SAVE_OPERATE_CANCEL==typeId){   //取消收藏
				if(saveInfo!=null){
					if(tbSaveInfoMapper.deleteByPrimaryKey(saveInfo.getId())==1){
						return String.valueOf(2);  //取消收藏成功
					}else{
						return String.valueOf(-2);  //取消收藏失败
					}
				}else{
					String.valueOf(-2); //取消收藏失败
				}
			}
		}		
		return String.valueOf(false);
	}
	
	
	private int insertRecommendedInfo(String code,String shopId,TbWebUser loginUser){
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
}