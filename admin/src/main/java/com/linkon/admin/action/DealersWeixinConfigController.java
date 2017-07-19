package com.linkon.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.DealersWeixinConfigDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDealersWeixinConfig;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbDealersWeixinConfigService;
import com.liguo.hgl.service.TbShopInfoService;

@Controller
@RequestMapping("dealersWeixinConfig")
public class DealersWeixinConfigController extends IBaseController {

	@Autowired
	protected TbDealersWeixinConfigService tbDealersWeixinConfigService;
	
	@Autowired
	protected TbShopInfoService tbShopInfoService;
	
	@Override
	protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
		Criteria criteria = new Criteria();
		List<DealersWeixinConfigDto> dealersWeixinConfig = tbDealersWeixinConfigService.selectByObject(criteria,page);
		model.addAttribute("dealersWeixinConfig",dealersWeixinConfig);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}

	/**
	 * 分页查询
	 * @param request
	 * @param shopName
	 * @param page
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchResult")
    public String searchResult(HttpServletRequest request,String shopName,PageDto page,Model model) throws IOException {
		Criteria criteria = new Criteria();
		criteria.put("shopName",shopName);
		List<DealersWeixinConfigDto> dealersWeixinConfig = tbDealersWeixinConfigService.selectByObject(criteria,page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("dealersWeixinConfig",dealersWeixinConfig);
    	model.addAttribute("shopName", shopName);
        return "/dealersWeixinConfig/dealersWeixinConfigList";
    }
	 
    /**
     * 点击修改调用,查询出该id的信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getUpdateDealersWeixinConfigPage")
    public String getUpdateDealersWeixinConfigPage(Integer id,Model model) throws Exception{
    	 TbDealersWeixinConfig dealersWeixinConfig = tbDealersWeixinConfigService.selectByPrimaryKey(id);
    	 model.addAttribute("dealersWeixinConfig", dealersWeixinConfig);
    	 Criteria criteria = new Criteria();
    	 List<TbShopInfo> shopInfoList = tbShopInfoService.selectByObject(criteria);
    	 model.addAttribute("shopInfoList", shopInfoList);
    	return "dealersWeixinConfig/updateDealersWeixinConfigPage";
    }
    
    /**
     * 更新经销商配置表信息调用
     * @param tbSystemConfig
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateDealersWeixinConfig")
    public String updateDealersWeixinConfig(TbDealersWeixinConfig dealersWeixinConfig,HttpServletRequest request) throws Exception{
    	TbDealersWeixinConfig dealersWeixinConfigPersistent = tbDealersWeixinConfigService.selectByPrimaryKey(dealersWeixinConfig.getId());
    	BeanUtils.copyProperties(dealersWeixinConfig, dealersWeixinConfigPersistent,"id","version","createDt");
    	int count = tbDealersWeixinConfigService.updateByPrimaryKeySelective(dealersWeixinConfigPersistent);
    	this.logger.info("更新经销商配置表信息受影响行数: " + count);
    	return "redirect:/dealersWeixinConfig/index";
    }
   
    /**
     * 插入经销商配置表信息调用
     * @param tbSystemConfig
     * @param request
     * @return
     * @throws Exception
     */
	@RequestMapping("/insertDealersWeixinConfig")
	public String insertDealersWeixinConfig(TbDealersWeixinConfig dealersWeixinConfig,HttpServletRequest request) throws Exception{
		dealersWeixinConfig.setStatus(0);
		dealersWeixinConfig.setCreateBy(this.getLoginUserId());
		dealersWeixinConfig.setCreateDt(System.currentTimeMillis());
		int count = tbDealersWeixinConfigService.insert(dealersWeixinConfig);
		this.logger.info("插入经销商配置表信息受影响行数: " + count);
	 	return "redirect:/dealersWeixinConfig/index";
	}
	
     /**
      * 删除经销商配置表信息调用
      * @param id
      * @return
      */
     @RequestMapping("/deleteDealersWeixinConfigById")
     public String deleteDealersWeixinConfigById(Integer id) throws Exception{
    	 int count = tbDealersWeixinConfigService.deleteByPrimaryKey(id);
    	 this.logger.info("删除经销商配置表信息受影响行数: " + count);
    	 return "redirect:/dealersWeixinConfig/index";
     }
     
     /**
      * 修改状态
      * @param id
      * @return
      */
     @RequestMapping("/modifyStatusDealersWeixinConfigById")
     public String modifyStatusDealersWeixinConfigById(Integer id,Integer status) throws Exception{
    	 TbDealersWeixinConfig dealersWeixinConfigPersistent = tbDealersWeixinConfigService.selectByPrimaryKey(id);
    	 TbDealersWeixinConfig dealersWeixinConfig = new TbDealersWeixinConfig();
    	 dealersWeixinConfig.setId(id);
    	 dealersWeixinConfig.setStatus(status);
    	 dealersWeixinConfig.setVersion(dealersWeixinConfigPersistent.getVersion());
     	 int count = tbDealersWeixinConfigService.updateByPrimaryKeySelective(dealersWeixinConfig);
 		 this.logger.info("修改状态受影响行数: " + count);
    	 return "redirect:/dealersWeixinConfig/index";
     }
     
     /**
      * 点击修改调用,查询出该id的信息
      * @param id
      * @param model
      * @return
      */
     @RequestMapping("/getInsertDealersWeixinConfigPage")
     public String getInsertDealersWeixinConfigPage(Model model) throws Exception{
    	 Criteria criteria = new Criteria();
    	 List<TbShopInfo> shopInfoList = tbShopInfoService.selectByObject(criteria);
    	 model.addAttribute("shopInfoList", shopInfoList);
     	 return "dealersWeixinConfig/addDealersWeixinConfigPage";
     }
     
     /**
      * 校验店铺是否已经添加了
      * @param shopId
      * @return
      * @throws Exception
      */
     @RequestMapping("/validateShopIdIsExist")
     public @ResponseBody Map<String,Object> validateShopIdIsExist(Integer shopId) throws Exception{
    	 Map<String, Object> map = new HashMap<String, Object>();
    	 Criteria criteria = new Criteria();
    	 criteria.put("shopId", shopId);
    	 TbDealersWeixinConfig dealersWeixinConfig = tbDealersWeixinConfigService.selectByShopId(criteria);
    	 map.put("isExit", dealersWeixinConfig != null ? true : false);
   	 	 return map;
     }

 	@Override
 	public String doSearchResult() {
 		return null;
 	}
}