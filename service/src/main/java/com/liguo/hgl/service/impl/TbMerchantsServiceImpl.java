package com.liguo.hgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbAgencyMapper;
import com.liguo.hgl.proxydao.dao.TbCountryInfoMapper;
import com.liguo.hgl.proxydao.dao.TbMerchantsMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbStatisticalMapper;
import com.liguo.hgl.proxydao.dao.TbStreetInfoMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.AgencyDto;
import com.liguo.hgl.proxydao.dto.TbMerchantsAssociatedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAgency;
import com.liguo.hgl.proxydao.model.TbMerchNotice;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbStatistical;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.IAddress;
import com.liguo.hgl.proxydao.util.ToolsUtil;
import com.liguo.hgl.service.TbMerchNoticeService;
import com.liguo.hgl.service.TbMerchantsService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.IpConvertUtil;
import com.liguo.hgl.util.NullU;
import com.liguo.hgl.util.StringUtil;

@Service
@Scope(value="prototype")
public class TbMerchantsServiceImpl implements TbMerchantsService {
    @Autowired
    private TbMerchantsMapper tbMerchantsMapper;
    
    @Autowired
    private TbStatisticalMapper tbStatisticalMapper;
    @Autowired
    private TbCountryInfoMapper tbCountryInfoMapper;
    
    @Autowired
    private TbStreetInfoMapper tbStreetInfoMapper;
   
    @Autowired
    private TbShopInfoMapper tbShopInfoMapper;

    @Autowired
	private IAddress iAddress;
    
    @Autowired
	private TbWebUserMapper tbWebUserMapper;
    
    @Autowired
    private TbMerchNoticeService merchNoticeService;
    
    @Autowired
    private TbAgencyMapper tbAgencyMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbMerchantsServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbMerchantsMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbMerchants selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbMerchantsMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbMerchants> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbMerchantsMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbMerchantsMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 修改招商
     */
    public int updateByPrimaryKeySelective(TbMerchants record) throws RuntimeException {
    	if(record.getState() ==0){
    		record = this.updateAdress(record);
    	}
    	TbMerchantsAssociatedDto merchants = tbMerchantsMapper.selectById(record.getId());
        if(!StringUtils.isEmpty(record.getReserved1()) && !record.getReserved1().equals(merchants.getReserved1())){
        	String newName = StringUtil.changeFileName(String.valueOf(record.getId()),record.getReserved1());
        	try {
				ImageUtil.changMerchantsPathAndName(record.getReserved1().toString(), newName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	record.setReserved1(newName);
        }
         
        return tbMerchantsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TbMerchants record) throws RuntimeException {
        try {
            return this.tbMerchantsMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbMerchants record) {
    	int count= 0;
    	record = this.updateAdress(record);
    	count= this.tbMerchantsMapper.insertSelective(record);
    	 int merchantsId = record.getId();
    	 if(NullU.isNotNull(record.getReserved1().toString())){
 			String newName = StringUtil.changeFileName(String.valueOf(merchantsId),record.getReserved1());
 			try {
				ImageUtil.changMerchantsPathAndName(record.getReserved1().toString(), newName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			record.setReserved1(newName);
 			count=tbMerchantsMapper.updateByPrimaryKeySelective(record);
 			
 		}
    
        return count;
      
    }
    /**
     * 填充招商位数
     * @param record
     * @return
     */
    public TbMerchants updateAdress(TbMerchants record){
	  Criteria example = new Criteria();
    	if(record.getStreet()!=null){
    	int street=record.getStreet();
    	
    	if(street >0){
    		record.setPlaces(1);
    	}else if(record.getCountry() >0){
    		example.put("parentid", record.getCountry());
    		record.setPlaces(this.tbStreetInfoMapper.countByObject(example));
    	}else if(record.getCity() >0){
    		example.put("id", record.getCity());
    		record.setPlaces(this.tbStreetInfoMapper.countByCityParentid(example));
    	}
    	}
    	return record;
    }
    
	@Override
	public List<TbMerchants> getMerchantsList(String brandName) {
		/**
		 * need to do
		 * 根据品牌名，查询出对应的品牌id，然后根据品牌ID查询出list并且具有分页
		 */
		Criteria example = new Criteria();
		if(brandName !=null ){
			example.put("brandid", brandName);
		}
		
		 List<TbMerchants> merchants = tbMerchantsMapper.selectByObject(example);
		
		
		return merchants;
		
	}

	@Override
	public TbMerchants updateState(Integer id) {
		TbMerchants merchants = tbMerchantsMapper.selectByPrimaryKey(id);
		
		if(merchants.getState() == HglContants.MERCHANTS_NOT_RELEASE){
			merchants.setReleaseTime(System.currentTimeMillis());
			merchants.setState(HglContants.MERCHANTS_RELEASE);
		}else if(merchants.getState() == HglContants.MERCHANTS_RELEASE){
			merchants.setState(HglContants.MERCHANTS_FAILURE);
		}else if(merchants.getState() == HglContants.MERCHANTS_FAILURE){
			merchants.setState(HglContants.MERCHANTS_RELEASE);
			merchants.setReleaseTime(System.currentTimeMillis());
		}	
	
		tbMerchantsMapper.updateByPrimaryKeySelective(merchants);  
		return merchants;
	}

	@Override
	public List<TbMerchantsAssociatedDto> getMerchantsList(int userId) {
		Criteria example = new Criteria();
		example.put("state", HglContants.MERCHANTS_RELEASE);
		example.setOrderByClause("release_time");
		example.setOrderByClauseDesc("desc");
		List<TbMerchantsAssociatedDto> merchants =null;
		if(userId>0){
			TbWebUser user = tbWebUserMapper.selectByPrimaryKey(userId);
			if(user!=null && user.getTypeId() == HglContants.USER_TYPE_DISTRIBUTOR){
				merchants = getUserMerchants(user.getShopId(), example);
			}
		}else{
			merchants = tbMerchantsMapper.selectByShopCriteria(example);
		}
		if(merchants !=null){
			  for (TbMerchantsAssociatedDto merchantsDto : merchants) {
					String addresss= iAddress.getAddressName(merchantsDto.getProvince(), merchantsDto.getCity(), 
							merchantsDto.getCountry(), merchantsDto.getStreet());
					merchantsDto.setPlacesName(addresss);
					logger.debug("-----------"+addresss+"----"+merchantsDto.getPlacesName());
					merchantsDto.setRemainingPlaceNumber(merchantsDto.getPlaces() - getMerchantsNum(merchantsDto.getId()));
				}
				return merchants;
		}
		return null;
	}

	private List<TbMerchantsAssociatedDto> getUserMerchants(int usershopId,
			Criteria example) {
		List<TbMerchantsAssociatedDto> merchants;
		//查询shop用户所在地址
		TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(usershopId);
		
		//查询出代理表中生效的招商，除去当前用户生效的
		Criteria criteria= new Criteria();
		criteria.put("shopId", shopInfo.getId());
		criteria.put("state1",HglContants.MERCHANTS_AGENCY_STATE1);
		criteria.put("state2", HglContants.MERCHANTS_AGENCY_STATE2);
		criteria.put("city", shopInfo.getRegCity());
		criteria.put("country", shopInfo.getRegCountry());
		criteria.put("street", shopInfo.getRegStreet());
		List<TbAgency> agencies = tbAgencyMapper.selecEffectMerchants(criteria);
		List<String> agencyIdList = new ArrayList<String>();
		if(agencies.size()>0){
			for (int i = 0; i < agencies.size(); i++) {
				agencyIdList.add(agencies.get(i).getMerchantId().toString());
			}
			example.put("agencyIdList", agencyIdList);
		}
		example.put("userId", usershopId);///只用于标识
		example.put("city", shopInfo.getRegCity());
		example.put("country", shopInfo.getRegCountry());
		example.put("street", shopInfo.getRegStreet());
		merchants = tbMerchantsMapper.selectByShopCriteria(example);
		return merchants;
	}
	
	private int getMerchantsNum(int id){
		Criteria criteria= new Criteria();
		criteria.put("state1",HglContants.MERCHANTS_AGENCY_STATE1);
		criteria.put("state2", HglContants.MERCHANTS_AGENCY_STATE2);
		criteria.put("merchantId", id);
		List<AgencyDto> agencies = tbAgencyMapper.selectByCriteria(criteria);
		if(agencies.size()>0){
			return agencies.size();
		}
		return 0;
	}

	@Override
	public TbMerchants getMerchantsDetails(Integer id,String browser, String ip) {
		TbStatistical statistical = new TbStatistical();
		statistical.setIp(ip);
		statistical.setBrowser(IpConvertUtil.getBrowserByAgent(browser));
		statistical.setMerchansid(id);
		statistical.setType(HglContants.STATISTICAL_VIEW);
		statistical.setCreateTime(ToolsUtil.getDateYMDHMS_14bit());
		tbStatisticalMapper.insert(statistical);
		
		TbMerchants merchants = tbMerchantsMapper.selectByPrimaryKey(id);
		merchants.setViews(merchants.getViews()+1); 
		tbMerchantsMapper.updateByPrimaryKeySelective(merchants);
		
		return tbMerchantsMapper.selectByPrimaryKey(id);
	}

	@Override
	public TbMerchants selectById(Integer id) {
		 try {
	            return this.tbMerchantsMapper.selectById(id);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}

	@Override
	public List<TbMerchantsAssociatedDto> getMerchantsList(Criteria criteria, PageDto page) {
		if(criteria.getOrderByClause()==null){
			criteria.setOrderByClause("id");
			criteria.setOrderByClauseDesc("desc");
		}
		try {
            return this.tbMerchantsMapper.selectByCriteria(criteria,page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<TbMerchantsAssociatedDto> getNewestMerchants() {
		Criteria criteria = new Criteria();
		criteria.put("state", 1);
		criteria.setMysqlOffset(0);
		criteria.setMysqlLength(4);
		criteria.setOrderByClause("release_time desc");	//按照发布时间降序排列	
		List<TbMerchantsAssociatedDto> list = tbMerchantsMapper.selectByCriteria(criteria);
		if(list !=null){
			  for (TbMerchantsAssociatedDto merchantsDto : list) {
					merchantsDto.setRemainingPlaceNumber(merchantsDto.getPlaces() - getMerchantsNum(merchantsDto.getId()));
				}
			  return list;
		}
		return null;
	}

	@Override
	public int updateViews(TbMerchants merchant) {
		merchant.setViews(merchant.getViews()+1);
		
		return tbMerchantsMapper.updateByPrimaryKey(merchant);		
	}
	
	/*根据招商信息操作招商公告
	 * typeId: 1表示发布招商公告，2表示下线招商公告
	 * */
	public int operateMerchNotice(Integer merchantId,String name,String detail,String createdBy,Integer typeId){
		
		TbMerchants merchant = tbMerchantsMapper.selectByPrimaryKey(merchantId);
		if(merchant!=null){
			Criteria criteria = new Criteria();
			criteria.put("merchantid", merchant.getId());
			List<TbMerchNotice> list = merchNoticeService.selectByObject(criteria);
			
			TbMerchNotice merchNotice= new TbMerchNotice();
			if(list.size()>0){  //该招商对应的招商公告已经存在,只需要修改公告状态即可
				merchNotice = list.get(0);
				if(merchant.getState().equals(HglContants.MERCHANTS_RELEASE)){ //激活
					merchNotice.setStatus(HglContants.MERCH_NOTICE_VALIDATE);
				}else if(merchant.getState().equals(HglContants.MERCHANTS_FAILURE)){ //失效
					merchNotice.setStatus(HglContants.MERCH_NOTICE_INVALIDATE);
				}
				if(typeId==1){
					merchNotice.setName(name);
					merchNotice.setDetail(detail);
				}
				merchNotice.setCreatedby(createdBy);
				merchNotice.setCreateddatetime(System.currentTimeMillis());
				
				return merchNoticeService.updateByPrimaryKeySelective(merchNotice);			
			}else{  //该招商对应的招商公告不存在并且为发布招商公告时,需要添加一条招商公告 
				if(typeId == 1){
					StringBuffer sb = new StringBuffer();				
					merchNotice.setMerchantid(merchant.getId());
					merchNotice.setBrandid(merchant.getBrandId());
					merchNotice.setTypeId(HglContants.MERCH_NOTICE_ORDER);
					if(merchant.getState().equals(HglContants.MERCHANTS_RELEASE)){ //激活
						merchNotice.setStatus(HglContants.MERCH_NOTICE_VALIDATE);
					}else if(merchant.getState().equals(HglContants.MERCHANTS_FAILURE)){ //失效
						merchNotice.setStatus(HglContants.MERCH_NOTICE_VALIDATE);
					}
					merchNotice.setNumber(merchant.getNumber());
					merchNotice.setTypeId(HglContants.MERCH_NOTICE_ORDER);
					merchNotice.setName(name);
					merchNotice.setCoupons(merchant.getCoupons());
					merchNotice.setLevel(merchant.getLevel());
					merchNotice.setImgfile(merchant.getReserved1());
					merchNotice.setDetail(detail);
					merchNotice.setCreatedby(createdBy);
					merchNotice.setCreateddatetime(System.currentTimeMillis());
					merchNotice.setVersion(1);
					
					return merchNoticeService.insertSelective(merchNotice);
				}
			}			
		}
		return 0;
	}

	@Override
	public TbMerchants getMerchantService(Integer id, Integer shopId) {
		TbMerchantsAssociatedDto merchants = tbMerchantsMapper.selectById(id);
		//查询shop用户所在地址
		TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(shopId);
		String addresss= iAddress.getAddressName(shopInfo.getRegProvince(), shopInfo.getRegCity(), 
				shopInfo.getRegCountry(), shopInfo.getRegStreet());
		merchants.setPlacesName(addresss);//用户的地址
		return merchants;
	}

	@Override
	public boolean isMerchantService(Integer mid, Integer shopId) {
		
		boolean isMerchants = isHasMerchants(mid, shopId);
	
		if(isMerchants){
			Criteria criteria = new Criteria();
			criteria.put("merchantId", mid);
			criteria.put("shopId", shopId);
			criteria.put("state1",HglContants.MERCHANTS_AGENCY_STATE1);//暂时生效
			criteria.put("state2",HglContants.MERCHANTS_AGENCY_STATE2);//生效
			criteria.put("couponsState", HglContants.MERCHANTS_AGENCY_COUPONSSTATE1);
			List<AgencyDto> agencies = tbAgencyMapper.selectByCriteria(criteria);
			if(agencies.size()<=0){
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public int checkMerchants(int brandId, int city, int country, int street,int merchantsId) {
		int count= 0;
    	logger.debug("--------tbmerchants:"+country+"---"+street);
    	Criteria criteria = new Criteria();
    	criteria.put("brandId",brandId);
    	if(city>0){
    		criteria.put("city", city);
    	}
    	if(country>0){
    		criteria.put("country", country);
    	}
    	if(street >0){
    		criteria.put("street", street);
    	}
    	if(merchantsId >0){
    		criteria.put("merchantsId", merchantsId);
    	}
    	
    	List<TbMerchants> list= tbMerchantsMapper.selectByAdress(criteria);
    	if(list!=null){
    		return list.size();
    	}
		return count;
	}

	@Override
	public boolean isHasMerchants(Integer mid, Integer shopId) {
		Criteria example = new Criteria();
		example.put("state", HglContants.MERCHANTS_RELEASE);
		List<TbMerchantsAssociatedDto> merchants = getUserMerchants(shopId, example);;
		boolean isMerchants = false;
		if(merchants.size() >0){
			for (TbMerchantsAssociatedDto associatedDto : merchants) {
				if(associatedDto.getId().equals(mid)){
					isMerchants=true;
				}
			}
		}
		return isMerchants;
	}
		
}