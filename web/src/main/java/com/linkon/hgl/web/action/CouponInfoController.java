package com.linkon.hgl.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.CouponInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbCouponInfoService;

@Controller
@RequestMapping("couponInfo")
public class CouponInfoController extends IBaseController{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String COUPONINFO ="coupon/couponinfo";
	
	private static final String COUPONINFOLIST="coupon/couponinfolist";
	
	@Autowired
	private TbCouponInfoService couponInfoService;
	
	
	@RequestMapping("/doInitCouponInfo")
	public String doInitCouponInfo(PageDto page,ModelMap model){
		Integer shopId = this.getLoginUser().getShopId();
		if(shopId!=null){
			Criteria criteria = new Criteria();
			criteria.put("shopId",shopId);
			criteria.setOrderByClause("operateDateTime desc");
			List<CouponInfoDto> list = couponInfoService.selectByObjectPage(criteria, page);
			//获取优惠券的使用情况
			double gainAmt = 0;
			double employAmt = 0;
			double remainingAmt = 0;
			if(list.size()>0){
				CouponInfoDto couponInfoDto = list.get(0);
				if(couponInfoDto!=null){
					gainAmt = couponInfoDto.getGainAmt()!=null?couponInfoDto.getGainAmt():0;
					employAmt = couponInfoDto.getEmployAmt()!=null?couponInfoDto.getEmployAmt():0;
					remainingAmt = couponInfoDto.getRemainingAmt()!=null?couponInfoDto.getRemainingAmt():0;
				}				
			}
			model.addAttribute("couponInfos", list);
			model.addAttribute(HglContants.PAGE_DTO_ID, page);
						
			model.addAttribute("gainAmt", gainAmt);
			model.addAttribute("employAmt", employAmt);
			model.addAttribute("remainingAmt", remainingAmt);
		}		
		return COUPONINFO;
	}
	
	@RequestMapping("/doSearchResult")
	public String doSearchResult(CouponInfoDto couponInfo,PageDto page,ModelMap model){
		Integer shopId = this.getLoginUser().getShopId();
		if(shopId !=null){
			Criteria criteria = new Criteria();
			Long beginDateTime = null;
			Long endDateTime = null;
			SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			try {
				if(StringUtil.isNotBlank(couponInfo.getFrom())){				
					Date dtFrom = sm.parse(couponInfo.getFrom()+" 00:00:00");
					beginDateTime = dtFrom.getTime();	
				}				
				if(StringUtil.isNotBlank(couponInfo.getTo())){
					Date dtTo = sm.parse(couponInfo.getTo()+" 23:59:59");
					endDateTime = dtTo.getTime();
				}
				
				criteria.put("shopId",shopId);
				criteria.put("status",couponInfo.getStatus());
				criteria.put("from",beginDateTime);
				criteria.put("to", endDateTime);
				criteria.setOrderByClause("operateDateTime desc");
				List<CouponInfoDto> list = couponInfoService.selectByObjectPage(criteria, page);
				//获取优惠券的使用情况
				double gainAmt = 0L;
				double employAmt = 0L;
				double remainingAmt = 0L;
				if(list.size()>0){
					CouponInfoDto couponInfoDto = list.get(0);
					if(couponInfoDto!=null){
						gainAmt = couponInfoDto.getGainAmt()!=null?couponInfoDto.getGainAmt():0;
						employAmt = couponInfoDto.getEmployAmt()!=null?couponInfoDto.getEmployAmt():0;
						remainingAmt = couponInfoDto.getRemainingAmt()!=null?couponInfoDto.getRemainingAmt():0;
					}				
				}
				model.addAttribute("couponInfos", list);
				model.addAttribute(HglContants.PAGE_DTO_ID, page);		
		
				model.addAttribute("gainAmt", gainAmt);
				model.addAttribute("employAmt", employAmt);
				model.addAttribute("remainingAmt", remainingAmt);	
			} catch (ParseException e) {
	
				e.printStackTrace();
			}
		}					
		return COUPONINFOLIST;
	}
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
