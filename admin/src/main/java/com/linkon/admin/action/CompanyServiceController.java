package com.linkon.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbCompanyServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyService;
import com.liguo.hgl.proxydao.model.TbCompanyType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbCompanyServiceService;
import com.liguo.hgl.service.TbCompanyTypeService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.util.BeanCopyUtil;
import com.liguo.hgl.util.ImageUtil;
/**
 * * 
*Company:  hgl
* Description:服务类型公司
* @author:zk
* @date 2016-7-25 下午6:40:05
 */
@Controller
@RequestMapping("/companyService")
public class CompanyServiceController extends IBaseController{

	
	@Autowired
	protected TbCompanyServiceService tbCompanyServiceService;
	
	@Autowired
	protected TbCompanyTypeService tbCompanyTypeService;
	
	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
	/**
	 * @Description:服务公司列表
	* @author:ZK 
	* @date:2016-7-25
	* @parameter:
	 */
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		List<TbCompanyType> tbCompanyTypeList = tbCompanyTypeService.selectByObject(null);
		List<TbCompanyServiceDto> cList = tbCompanyServiceService.selectTbCompanyServicePage(new Criteria(), page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("cList", cList);
		model.addAttribute("tbCompanyTypeList", tbCompanyTypeList);
	}
	
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@RequestMapping("serachCompanyService")
	public String serachCompanyService(PageDto page,Model model, HttpServletRequest request){
		page.pageSize = 2;
		List<TbCompanyServiceDto> cList = tbCompanyServiceService.selectTbCompanyServicePage(new Criteria(), page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("cList", cList);
		return "companyService/companyServiceList";
	}*/
	
	/**
	 *添加页面 
	 */
	@RequestMapping("/showAddpage")
	public String showAddpage(Model model){
		List<TbCompanyType> cList = tbCompanyTypeService.selectByObject(null);
		model.addAttribute("provinceInfos", tbProvinceInfoService.selectByObject(null));
		model.addAttribute("cList",cList);
		return "companyService/companyServiceAdd";
	}
	
	/**
	 *保存添加信息 
	 */
	@RequestMapping("/saveAddInfo")
	public String saveAddInfo(Model model,TbCompanyServiceDto dto){
		TbCompanyService t = new TbCompanyService();
		try{
			BeanCopyUtil.CopyBeanToBean(dto,t);
			t.setVersion(0);
			tbCompanyServiceService.saveAddInfo(t);
		}catch (Exception e){
			e.printStackTrace();
		}
		return "redirect:/companyService/index";
	}
	
	/**
	 *删除信息 
	 */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public Map<String,Object> deleteInfo(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", tbCompanyServiceService.deleteByPrimaryKey(id));
		return map;
	}
	
	/**
	 *展示详情 
	 */
	@RequestMapping("/showdetailsInfo")
	public String showdetailsInfo(Integer id,Model model,PageDto page){
		Criteria criteria = new Criteria();
		criteria.put("id",id);
		TbCompanyServiceDto pifd = tbCompanyServiceService.selectTbCompanyServicePage(criteria, page).get(0);
		model.addAttribute("pifd",pifd);
		return "companyService/companyServiceDetail";
	}
	@RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageWapProduct(id,imgName,response,HglContants.COMPANY_SERVICE);
        return null;
    }
	
	/**
	 *信息查询
	 */
	@RequestMapping("/serachInfo")
    public String serachInfo(ModelMap model,Integer typeId,String companyName,HttpServletResponse response,PageDto page) {
		Criteria criteria  = new Criteria();
		criteria.put("typeId", typeId);
		if(!StringUtils.isBlank(companyName)){
			criteria.put("companyName", companyName.trim());
		}
		List<TbCompanyServiceDto> cList = tbCompanyServiceService.selectTbCompanyServicePage(criteria, page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("cList", cList);
        return "companyService/companyServiceList";
    }
    
	/**
	 *信息修改页面
	 */
    @RequestMapping("/updateInfoShow")
	public String updateInfo(Integer id,Model model,PageDto page){
		Criteria criteria = new Criteria();
		criteria.put("id",id);
		TbCompanyServiceDto pifd = tbCompanyServiceService.selectTbCompanyServicePage(criteria, page).get(0);
		model.addAttribute("provinceInfos", tbProvinceInfoService.selectByObject(null));
		model.addAttribute("pifd",pifd);
		return "companyService/companyServiceUpdate";
	}
    
    /**
	 *保存修改信息
	 */
    @RequestMapping("/saveUpdateInfo")
   	public String updateInfo(TbCompanyServiceDto dto,PageDto page){
		TbCompanyService t = new TbCompanyService();
		try{
			BeanCopyUtil.CopyBeanToBean(dto,t);
			tbCompanyServiceService.saveUpdateInfo(t);
		}catch (Exception e){
			e.printStackTrace();
		}
   		return "redirect:/companyService/index";
   	}
}
