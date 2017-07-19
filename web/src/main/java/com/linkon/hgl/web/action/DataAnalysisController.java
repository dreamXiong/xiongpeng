package com.linkon.hgl.web.action;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.proxydao.dto.CustomerInterestsDto;
import com.liguo.hgl.proxydao.dto.GoodsProfitDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCustomerInterests;
import com.liguo.hgl.proxydao.model.TbGoodsProfit;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbCustomerInterestsService;
import com.liguo.hgl.service.TbGoodsProfitService;

@Controller
@RequestMapping("dataAnalysis")
public class DataAnalysisController extends IBaseController {

	@Autowired
	private TbCustomerInterestsService tbCustomerInterestsService;
	
	@Autowired
	private TbGoodsProfitService tbGoodsProfitService;
	
	private static int[] DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		Map<String,Object> map = splitDate(request);
		String selectDate = (String)map.get("selectDate");
		int dayCount = (int)map.get("dayCount");
		
		//查询条件
		Criteria example = new Criteria();
		example.put("selectDate", selectDate);
		example.put("dayCount", dayCount);
		example.put("shopId", this.getShopId());
		
		//计算中行数是否大于10条
		int count = tbGoodsProfitService.countByObject(example);
		if(count > 0){
			//查询出总和
			TbGoodsProfit goodsProfit = tbGoodsProfitService.selectByDataInfoSum(example);
			model.addAttribute("goodsProfit", goodsProfit);
			example.put("profitTotalMoney", goodsProfit.getProfitMoney());
			
			//如果大于10,需要统计出前5条,后4条
			if(count > 10){
				example.put("startCount", count-4);
			}
			
			//库存预警
			example.setOrderByClause("sale_day asc,sale_num_day desc");
			List<GoodsProfitDto> inventoryWarningList = tbGoodsProfitService.selectByDataInfo(example);
			if(inventoryWarningList != null && inventoryWarningList.size()>0){
				model.addAttribute("inventoryWarningList", inventoryWarningList);
			}
			
			//货品盈利
			example.setOrderByClause("profit_money desc");
			List<GoodsProfitDto> goodsProfitList = tbGoodsProfitService.selectByDataInfo(example);
			if(goodsProfitList != null && goodsProfitList.size()>0){
				model.addAttribute("goodsProfitList", goodsProfitList);
			}
			
			//货品销量
			example.setOrderByClause("sale_num desc");
			List<GoodsProfitDto> goodsSaleNumList = tbGoodsProfitService.selectByDataInfo(example);
			if(goodsSaleNumList != null && goodsSaleNumList.size()>0){
				model.addAttribute("goodsSaleNumList", goodsSaleNumList);
			}
			
			//货品销售额
			example.setOrderByClause("sale_money desc");
			List<GoodsProfitDto> goodsSaleMoneyList = tbGoodsProfitService.selectByDataInfo(example);
			if(goodsSaleMoneyList != null && goodsSaleMoneyList.size()>0){
				model.addAttribute("goodsSaleMoneyList", goodsSaleMoneyList);
			}
			
			//利润变化
			List<GoodsProfitDto> profitsChangeList = tbGoodsProfitService.selectByProfitsChange(example);
			if(profitsChangeList != null && profitsChangeList.size()>0){
				model.addAttribute("profitsChangeList", profitsChangeList);
			}
			
			//货品利润率的总和
			TbGoodsProfit goodsProfitsRateSum = tbGoodsProfitService.selectByDataInfoProfitsRate(example);
			if(goodsProfitsRateSum != null){
				model.addAttribute("goodsProfitsRateSum", goodsProfitsRateSum);
			}
			
			//货品利润率
			example.setOrderByClause("profits_rate desc");
			List<GoodsProfitDto> goodsProfitsRateList = tbGoodsProfitService.selectByDataInfo(example);
			if(goodsProfitsRateList != null && goodsProfitsRateList.size()>0){
				model.addAttribute("goodsProfitsRateList", goodsProfitsRateList);
			}
		}
		
		//计算中行数是否大于10条
		int customCount = tbCustomerInterestsService.countByObject(example);
		if(customCount > 0){
			//如果大于10,需要统计出前5条,后4条
			if(customCount > 10){
				example.put("startCount", customCount-4);
			}else{
				example.put("startCount", null);
			}
			
			//查询出总和
			TbCustomerInterests customerInterests = tbCustomerInterestsService.selectByDataInfoSum(example);
			model.addAttribute("customerInterests", customerInterests);
			
			//客户盈利
			example.setOrderByClause("profit_money desc");
			List<CustomerInterestsDto> customerInterestsList = tbCustomerInterestsService.selectByDataInfo(example);
			if(customerInterestsList != null && customerInterestsList.size()>0){
				model.addAttribute("customerInterestsList", customerInterestsList);
			}
			
			//推荐有效客户
			example.setOrderByClause("total_profit desc");
			List<CustomerInterestsDto> effectivecustomerList = tbCustomerInterestsService.selectByDataInfo(example);
			if(effectivecustomerList != null && effectivecustomerList.size()>0){
				model.addAttribute("effectivecustomerList", effectivecustomerList);
			}
		}
	}

	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 查询方法
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/searchList")
	public String searchList(HttpServletRequest request,HttpServletResponse response,PageDto page,Model model) {
		init(page, request, response, model);
		return "/dataAnalysis/dataAnalysisList";
	}
	
	/**
	 * 库存预警
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goodsDetail")
	public String goodsDetail(HttpServletRequest request,HttpServletResponse response,PageDto page,Model model) {
		Map<String,Object> map = splitDate(request);
		String selectDate = (String)map.get("selectDate");
		int dayCount = (int)map.get("dayCount");
		
		//查询条件
		Criteria example = new Criteria();
		example.put("selectDate", selectDate);
		example.put("dayCount", dayCount);
		example.put("shopId", this.getShopId());
		
		//查询出总和
		TbGoodsProfit goodsProfit = tbGoodsProfitService.selectByDataInfoSum(example);
		if(goodsProfit != null){
			example.put("profitTotalMoney", goodsProfit.getProfitMoney());
			example.setOrderByClause(request.getParameter("orderByClause"));
			
			//查询
			List<GoodsProfitDto> inventoryWarningList = tbGoodsProfitService.selectByDataInfo(example);
			if(inventoryWarningList != null && inventoryWarningList.size()>0){
				model.addAttribute("inventoryWarningList", inventoryWarningList);
			}
		}
		return request.getParameter("location");
	}
	
	/**
	 * 利润变化 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/profitsChangeDetail")
	public String profitsChangeDetail(HttpServletRequest request,HttpServletResponse response,Model model) {
		Map<String,Object> map = splitDate(request);
		
		//查询条件
		Criteria example = new Criteria();
		example.put("selectDate", (String)map.get("selectDate"));
		example.put("shopId", this.getShopId());
		List<GoodsProfitDto> profitsChangeList = tbGoodsProfitService.selectByProfitsChange(example);
		if(profitsChangeList != null && profitsChangeList.size()>0){
			model.addAttribute("profitsChangeList", profitsChangeList);
		}
		return "/dataAnalysis/profitsChangeDetail";
	}
	
	/**
	 * 客户盈利明细
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/customerInterestsDetail")
	public String customerInterestsDetail(HttpServletRequest request,HttpServletResponse response,Model model) {
		Map<String,Object> map = splitDate(request);
		
		//查询条件
		Criteria example = new Criteria();
		example.put("selectDate", (String)map.get("selectDate"));
		example.put("shopId", this.getShopId());
		
		//推荐有效客户
		example.setOrderByClause(request.getParameter("orderByClause"));
		List<CustomerInterestsDto> customerInterestsList = tbCustomerInterestsService.selectByDataInfo(example);
		if(customerInterestsList != null && customerInterestsList.size()>0){
			model.addAttribute("customerInterestsList", customerInterestsList);
		}
		
		return request.getParameter("location");
	}
	
	public Map<String,Object> splitDate(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		//获取选择的年月,如果为空获取当前年月
		String selectDate = request.getParameter("selectDate");
		int year = 0;
		int month = 0;
		int dayCount = 0;
		if(selectDate != null){
			selectDate = selectDate.replace("年", "").replace("月", "");
	    	String[] selectDates = selectDate.split("-");
	    	year = Integer.parseInt(selectDates[0]);
	    	month = Integer.parseInt(selectDates[1]);
		}else{
			 Calendar calendar = Calendar.getInstance();
		     year = calendar.get(Calendar.YEAR);
		     month = calendar.get(Calendar.MONTH) + 1;
		}
		selectDate = year + "-" + (month<10 ? "0" + month : month);
		dayCount = getMonthDays(year, month);
		map.put("selectDate", selectDate);
		map.put("dayCount", dayCount);
		return map;
	}

	/**
	 * 获取该年和该月中多少人
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDays(int year, int month) {
        month--;
        if (month != 1) {
            return DAYS[month];
        }
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return 29;
        }
        return DAYS[month];
    }
}
