package com.liguo.hgl.service.impl;

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

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbAgencyMapper;
import com.liguo.hgl.proxydao.dao.TbMerchantsMapper;
import com.liguo.hgl.proxydao.dao.TbShopInfoMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.AgencyDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAgency;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.IAddress;
import com.liguo.hgl.service.TbAgencyService;

@Service
@Scope(value="prototype")
public class TbAgencyServiceImpl implements TbAgencyService {
    @Autowired
    private TbAgencyMapper tbAgencyMapper;
    
    @Autowired
    private TbMerchantsMapper tbMerchantsMapper;
    
    @Autowired
    private TbShopInfoMapper tbShopInfoMapper;
    
    @Autowired
	private TbWebUserMapper tbWebUserMapper;
    
    @Autowired
   	private IAddress iAddress;
    
    private static Object lockObj = "lockerOrder";  

    private static final Logger logger = LoggerFactory.getLogger(TbAgencyServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbAgencyMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbAgency selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAgencyMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbAgency> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbAgencyMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAgencyMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbAgency record) throws RuntimeException {
        try {
            return this.tbAgencyMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbAgency record) throws RuntimeException {
        try {
            return this.tbAgencyMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbAgency record) throws RuntimeException {
        try {
            return this.tbAgencyMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public  Map<String,Object> addAgency(Integer mid, Integer shopId,String recommendCode) {
		Map<String, Object> map = new HashMap<String,Object>();
		int count= 0;
		TbAgency tbagency = this.getAgency(mid,shopId);
		//long currentTime = System.currentTimeMillis();
		String orderNumber = makeOrderNum();
		
		TbMerchants merchants = tbMerchantsMapper.selectByPrimaryKey(mid);
		if(tbagency ==null){
			TbAgency agency = new TbAgency();
			agency.setBrandId(merchants.getBrandId());
			agency.setShopId(shopId);
			agency.setMerchantId(mid);
			agency.setType(HglContants.SERVICE_EXPERIENCE);
			agency.setExpLevel(HglContants.STREET_LEVEL);//街道
			agency.setNumber(merchants.getNumber());
			agency.setCoupons(merchants.getCoupons());
			agency.setState(HglContants.MERCHANTS_AGENCY_STATE);
			agency.setCouponsState(HglContants.MERCHANTS_AGENCY_COUPONSSTATE);
			agency.setOrderState(HglContants.ORDER_STATE_200);
			agency.setCouponsOrderNumber(orderNumber);
			//查询shop用户所在地址
			TbShopInfo shopInfo = tbShopInfoMapper.selectByPrimaryKey(shopId);
			agency.setProvince(shopInfo.getRegProvince());
			agency.setCity(shopInfo.getRegCity());
			agency.setCountry(shopInfo.getRegCountry());
			agency.setStreet(shopInfo.getRegStreet());
			agency.setCreateBy(shopId);
			agency.setCreateDt(System.currentTimeMillis());
			 count = tbAgencyMapper.insertSelective(agency);
			 map.put("result", count);
			 map.put("methods", "add");//为增加招商参与量
		}else{
			tbagency.setNumber(merchants.getNumber());
			tbagency.setCoupons(merchants.getCoupons());
			tbagency.setRecommendCode(recommendCode);
			tbagency.setCouponsOrderNumber(orderNumber);
			count = tbAgencyMapper.updateByPrimaryKeySelective(tbagency);
			map.put("result", count);
			 map.put("methods", "update");
		}
		return map;
	}

	private String makeOrderNum() {
	  String finOrderNum = "";
	  try {
			// 最终生成的订单号
			synchronized (lockObj) {
				long nowLong = Long.parseLong(new SimpleDateFormat(
						"yyMMddHHmmssSSS").format(new Date()));
				for (int i = 0; i < 3; i++) {
					finOrderNum = finOrderNum + (int) (Math.random() * 10) + "";
				}
				return "F" + nowLong + finOrderNum;
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finOrderNum;
	}

	
	private TbAgency getAgency(Integer mid, Integer shopId) {
		Criteria criteria = new Criteria();
		criteria.put("merchantId", mid);
		criteria.put("shopId", shopId);
	//	criteria.put("couponsState", HglContants.MERCHANTS_AGENCY_COUPONSSTATE);//未付款
		List<AgencyDto> agencies = tbAgencyMapper.selectByCriteria(criteria);
		if(agencies.size() >0){
			return agencies.get(0);
		}
		return null;
	}

//	private int getReferrId(String referrName) {
//		if(!referrName.equals("")&& referrName !=null){
//			Criteria example = new Criteria();
//			example.put("userName", referrName);
//			List<TbWebUser> webUsers= tbWebUserMapper.selectByObject(example);
//			if(webUsers!=null && webUsers.size()>0){
//				return webUsers.get(0).getId();
//			}
//		}
//		return 0;
//	}

	@Override
	public List<AgencyDto> selectAgencyByMid(Criteria criteria, PageDto page
			) {
		if(criteria.getOrderByClause()==null){
			criteria.setOrderByClause("id");
			criteria.setOrderByClauseDesc("desc");
		}
		List<AgencyDto> agencies = tbAgencyMapper.selectByCriteria(criteria,page);
		if(agencies !=null && agencies.size()>0){
			System.out.println(agencies.toString());
			  for (AgencyDto agencyDto : agencies) {
					String addresss= iAddress.getAddressName(agencyDto.getProvince(), agencyDto.getCity(), 
							agencyDto.getCountry(), agencyDto.getStreet());
					agencyDto.setPalceName(addresss);
					logger.debug("-----------"+addresss+"----"+agencyDto.getPalceName());
				}
				return agencies;
		}
        return null;
       
	}

	@Override
	public Map<String, Object> updateAgencyState(Integer id, Integer state) {
		Map<String,Object> map = new HashMap<String,Object>();
		int count=0;
		TbAgency agency = tbAgencyMapper.selectByPrimaryKey(id);
		if(agency!=null && state>=0 ){
			agency.setState(state);
			count= tbAgencyMapper.updateByPrimaryKeySelective(agency);
		}
		if(count >0){
			map.put("state", agency.getState());
			map.put("errcode", "0");
			map.put("msg", "招商状态变更成功！");
		}else{
			map.put("errcode", "1");
			map.put("msg", "招商状态变更失败！");
		}
		return map;
	}

	@Override
	public boolean isUserByService(Integer shopId, Integer mid) {
		Criteria criteria = new Criteria();
		criteria.put("merchantId", mid);
		criteria.put("shopId", shopId);
		criteria.put("state1",HglContants.MERCHANTS_AGENCY_STATE1);//暂时生效
		criteria.put("state2",HglContants.MERCHANTS_AGENCY_STATE2);//生效
		criteria.put("couponsState", HglContants.MERCHANTS_AGENCY_COUPONSSTATE1);
		List<AgencyDto> agencies = tbAgencyMapper.selectByCriteria(criteria);
		if(agencies.size() >0){
			return true;
		}
		return false;
	}
}