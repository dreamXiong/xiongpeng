package com.linkon.hgl.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDeliveryTerms;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbDeliveryTermsService;

@Controller
@RequestMapping("deliveryTerm")
public class DeliveryTermController extends IBaseController {

	private Logger logger = LoggerFactory.getLogger(DeliveryTermController.class);
	
	private static final String DELIVERYTERMS="deliveryTerms/deliveryTerms";
	private static final Integer VERSION = 0; //新增版本默认值
	
	@Autowired
	private TbDeliveryTermsService deliveryTermsService;
	
	/**
	 * 查询出所有的送货条款
	 * @param model
	 * @return
	 */
	@RequestMapping("/doSearchResult")
	public String doSearchResult(ModelMap model){		
		
		if(this.getLoginUser().getShopId()!=null && this.getLoginUser().getShopId()!=0){
			Criteria criteria = new Criteria();
			criteria.put("shopId", this.getLoginUser().getRecommendShopId());
			criteria.setOrderByClause("freight");
			
			List<TbDeliveryTerms> list =  deliveryTermsService.selectByObject(criteria);
			model.addAttribute("deliveryTerms", list);
		}
		
		return DELIVERYTERMS;
		
	}
	
	/**
	 * 查看某一条送货条款
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSearchDeliveryTermsById",produces="text/html;charset=UTF-8")	
	public String doSearchDeliveryTermsById(Integer id){
		String jsonString = "";
		TbDeliveryTerms deliveryTerms= deliveryTermsService.selectByPrimaryKey(id);
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonString = mapper.writeValueAsString(deliveryTerms);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
	
	/**
	 * 修改送货条款
	 * @param deliveryTerms
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doUpdateDeliveryTerms")	
	public String doUpdateDeliveryTerms(TbDeliveryTerms deliveryTerms){
		TbDeliveryTerms deliveryTermUpd = deliveryTermsService.selectByPrimaryKey(deliveryTerms.getId());
		if(deliveryTermUpd!=null){
			if(deliveryTermsService.updateByPrimaryKeySelective(deliveryTerms)==1){
				return String.valueOf(true);
			}
		}
		return String.valueOf(false);
		
	}
	
	/**
	 * 删除送货条款
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doDeleteDeliveryTerms")	
	public String doDeleteDeliveryTerms(Integer id){
		if(deliveryTermsService.deleteByPrimaryKey(id)==1){
			return String.valueOf(true);
		}else{
			return String.valueOf(false);
		}
	}
	
	
	/**
	 * 新增送货条款
	 * @param deliveryTerms
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addDeliveryTerms")
	public String addDeliveryTerms(TbDeliveryTerms deliveryTerms){
		deliveryTerms.setVersion(VERSION);
		deliveryTerms.setShopId(this.getLoginUser().getRecommendShopId());
		if(deliveryTermsService.insert(deliveryTerms)==1){
			return String.valueOf(deliveryTerms.getId());
		}else{
			return String.valueOf(false);
		}
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
