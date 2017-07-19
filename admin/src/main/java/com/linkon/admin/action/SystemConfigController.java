package com.linkon.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbSystemConfig;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbSystemConfigService;

/**
 * 类的功能描述:系统配置表<br>
 *
 * @author xiongpeng<br>
 * @FileName SystemConfigController.java<br>
 * @Language Java<br>
 * @date 2016-04-21<br>
 */
@Controller
@RequestMapping("systemConfig")
public class SystemConfigController{
  
	@Autowired
	protected TbSystemConfigService tbSystemConfigService;
	
	/**
	 * 声明日志
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 显示系统配置信息调用
	 * @param page
	 * @param model
	 * @return
	 */
    @RequestMapping("/index")
    public String index(PageDto page,ModelMap model){
    	Criteria criteria = new Criteria();
    	List<TbSystemConfig> systemConfigList = tbSystemConfigService.selectByObject(criteria,page);
    	model.addAttribute("systemConfigList",systemConfigList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
        return "systemConfig/systemConfig";
    }
    
    /**
	 * 显示系统配置信息分页调用
	 * @param page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchResult")
    public String searchResult(HttpServletRequest request,String name,PageDto page,Model model) throws IOException {
		Criteria criteria = new Criteria();
		criteria.put("name",name);
		List<TbSystemConfig> systemConfigList = tbSystemConfigService.selectByObject(criteria,page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
    	model.addAttribute("systemConfigList", systemConfigList);
    	model.addAttribute("name", name);
        return "/systemConfig/systemConfigList";
    }
	 
    /**
     * 点击修改调用,查询出该id的信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getUpdateSystemConfigPage")
    public String getUpdateSystemConfigPage(Integer id,ModelMap model){
    	TbSystemConfig tbSystemConfig = tbSystemConfigService.selectByPrimaryKey(id);
    	model.addAttribute("systemConfig", tbSystemConfig);
    	return "systemConfig/updateSystemConfigPage";
    }
    
    /**
     * 更新系统配置表信息调用
     * @param tbSystemConfig
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateSystemConfig")
    public String updateSystemConfig(TbSystemConfig tbSystemConfig,HttpServletRequest request) throws Exception{
    	TbSystemConfig systemConfig = tbSystemConfigService.selectByPrimaryKey(tbSystemConfig.getId());
    	systemConfig.setConfigValue(tbSystemConfig.getConfigValue());
    	systemConfig.setRemark(tbSystemConfig.getRemark());
        systemConfig.setUpdateBy(getUserName(request)); //获取session中保持的userName
    	int count = tbSystemConfigService.updateByPrimaryKey(systemConfig);
    	logger.info("updateSystemConfig: " + count);
    	return "redirect:/systemConfig/index";
    }
   
    /**
     * 插入系统配置表信息调用
     * @param tbSystemConfig
     * @param request
     * @return
     * @throws Exception
     */
	@RequestMapping("/insertSystemConfig")
	public String insertSystemConfig(TbSystemConfig tbSystemConfig,HttpServletRequest request) throws Exception{
		String userName = getUserName(request); //获取session中保持的userName
		tbSystemConfig.setCreateBy(userName); 
		tbSystemConfig.setUpdateBy(userName); 
		int count = tbSystemConfigService.insert(tbSystemConfig);
		logger.info("insertSystemConfig: " + count);
	 	return "redirect:/systemConfig/index";
	}
	
	 /**
      * 校验配置表名称是否存在重复
      * @param configKey
      * @return
      */
     @RequestMapping("/validateSystemConfigIsExist")
     public @ResponseBody Map<String,Object> validateSystemConfigIsExist(String configKey) throws Exception{
    	 Map<String, Object> map = new HashMap<String, Object>();
    	 boolean isExist = tbSystemConfigService.validateSystemConfigIsExist(configKey);
    	 Integer isExistValue = HglContants.IS_NOT_EXIST; //默认不存在重复
    	 if(isExist){ //如果存在重复,重新赋值为存在标识
    		 isExistValue = HglContants.IS_EXIST;
    	 }
    	 map.put("isExit", isExistValue) ;
   	 	 return map;
     }
     
     /**
      * 删除系统配置表信息调用
      * @param id
      * @return
      */
     @RequestMapping("/deleteSystemConfigById")
     public String deleteSystemConfigById(Integer id) throws Exception{
    	 TbSystemConfig tbSystemConfig = new TbSystemConfig();
    	 TbSystemConfig systemConfig = tbSystemConfigService.selectByPrimaryKey(id);
    	 tbSystemConfig.setVersion(systemConfig.getVersion());
    	 tbSystemConfig.setDeleteFlag(1);  //设置为删除标识
    	 tbSystemConfig.setId(id);
     	 int count = tbSystemConfigService.updateByPrimaryKeySelective(tbSystemConfig);
    	 logger.info("deleteSystemConfigById: " + count);
    	 return "redirect:/systemConfig/index";
     }
     
      /**
       * 获取用户名,从session中获取不到返回空
       * @param request
       * @return
       */
      public String getUserName(HttpServletRequest request){
    	  if(request != null){
    		  HttpSession session = request.getSession(); //获取session
    		  TbAdminUserDto tbAdminUserDto = (TbAdminUserDto)session.getAttribute(HglContants.SESSION_KEY); //获取sesion中保存的userName
    		  if(tbAdminUserDto != null){
    			  return tbAdminUserDto.getUserName();
    		  }
    	  }
    	  return "";
      }
      
}