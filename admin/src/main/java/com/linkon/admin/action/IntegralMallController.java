package com.linkon.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.IntegralMallDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralMall;
import com.liguo.hgl.proxydao.model.TbIntegralMallType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbIntegralMallService;
import com.liguo.hgl.service.TbIntegralMallTypeService;
import com.liguo.hgl.util.StringUtil;

@Controller
@RequestMapping("integralMall")
public class IntegralMallController extends IBaseController {

	@Autowired
	protected TbIntegralMallService tbIntegralMallService;
	
	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;

	@Autowired
	protected TbIntegralMallTypeService tbIntegralMallTypeService;
	
	/**
	 * 查询列表
	 */
	@Override
	protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
		Criteria criteria = new Criteria();
		List<IntegralMallDto> integralMallList = tbIntegralMallService.selectByObject(criteria,page);
		model.addAttribute("integralMallList", integralMallList);
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
    public String searchResult(HttpServletRequest request,String goodsName,PageDto page,Model model) throws IOException {
		//根据商品名称模糊查询
		Criteria criteria = new Criteria();
		criteria.put("goodsName",goodsName);
		List<IntegralMallDto> integralMallList = tbIntegralMallService.selectByObject(criteria,page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("integralMallList",integralMallList);
    	model.addAttribute("goodsName", goodsName);
        return "/integralMall/integralMallList";
    }
	 
    /**
     * 点击修改调用,查询出该id的信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getUpdateIntegralMallPage")
    public String getUpdateIntegralMallPage(Integer id,Model model) throws Exception{
    	 getInsertIntegralMallPage(model);
    	 IntegralMallDto integralMall = tbIntegralMallService.selectByPrimaryKey(id);
    	 model.addAttribute("integralMall", integralMall);
    	 return "integralMall/updateIntegralMallPage";
    }
    
    /**
     * 点击详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getIntegralMallDetailPage")
    public String getIntegralMallDetailPage(Integer id,Model model) throws Exception{
    	getUpdateIntegralMallPage(id,model);
    	return "integralMall/integralMallDetailPage";
    }
    
    /**
     * 更新积分商城表信息调用
     * @param tbSystemConfig
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateIntegralMall")
    public String updateIntegralMall(TbIntegralMall integralMall,HttpServletRequest request) throws Exception{
    	TbIntegralMall integralMallPersistent = tbIntegralMallService.selectByPrimaryKey(integralMall.getId());
    	BeanUtils.copyProperties(integralMall, integralMallPersistent,"id","version","createDt");
    	int count = tbIntegralMallService.updateByPrimaryKeySelective(integralMallPersistent);
    	this.logger.info("更新积分商城表信息受影响行数: " + count);
    	return "redirect:/integralMall/index";
    }
   
    /**
     * 插入积分商城表信息调用
     * @param tbSystemConfig
     * @param request
     * @return
     * @throws Exception
     */
	@RequestMapping("/insertIntegralMall")
	public String insertIntegralMall(TbIntegralMall integralMall,HttpServletRequest request) throws Exception{
		integralMall.setStatus(0);
		integralMall.setCreateBy(this.getLoginUserId());
		integralMall.setCreateDt(System.currentTimeMillis());
		int count = tbIntegralMallService.insert(integralMall);
		this.logger.info("插入经积分商城表信息受影响行数: " + count);
	 	return "redirect:/integralMall/index";
	}
	
     /**
      * 删除经积分商城表信息调用
      * @param id
      * @return
      */
     @RequestMapping("/deleteIntegralMallById")
     public String deleteIntegralMallById(Integer id) throws Exception{
    	 int count = tbIntegralMallService.deleteByPrimaryKey(id);
    	 this.logger.info("删除经积分商城表信息受影响行数: " + count);
    	 return "redirect:/integralMall/index";
     }
     
     /**
      * 修改状态
      * @param id
      * @return
      */
     @RequestMapping("/modifyStatusIntegralMallById")
     public String modifyStatusIntegralMallById(Integer id,Integer status) throws Exception{
    	 TbIntegralMall integralMallPersistent = tbIntegralMallService.selectByPrimaryKey(id);
    	 TbIntegralMall integralMall = new TbIntegralMall();
    	 integralMall.setId(id);
    	 integralMall.setStatus(status);
    	 integralMall.setVersion(integralMallPersistent.getVersion());
     	 int count = tbIntegralMallService.updateByPrimaryKeySelective(integralMall);
 		 this.logger.info("修改状态受影响行数: " + count);
    	 return "redirect:/integralMall/index";
     }
     
     /**
      * 点击修改调用,查询出该id的信息
      * @param id
      * @param model
      * @return
      */
     @RequestMapping("/getInsertIntegralMallPage")
     public String getInsertIntegralMallPage(Model model) throws Exception{
    	Criteria criteria = new Criteria();
 		List<TbIntegralMallType> integralMallTypeList = tbIntegralMallTypeService.selectByObject(criteria);
 		model.addAttribute("integralMallTypeList", integralMallTypeList);
     	return "integralMall/addIntegralMallPage";
     }
     
     
	/**
	 * 验证图片
	 * @param imgFile
	 * @param request
	 * @return
	 */
      @RequestMapping("/validateImg")
      public @ResponseBody Map<String,Object> validate(MultipartFile imgFile,HttpServletRequest request){
    	  Map<String, Object> map = new HashMap<String, Object>();
    	  String newName = "";
    	  try{
	    	  String imgName = imgFile.getOriginalFilename();
	    	  map.put("code", HglContants.SUCCESS);
	    	  long imgSize = imgFile.getSize();
	    	  long name = System.currentTimeMillis();
	    	  newName = StringUtil.changeFileName("integral_mall_"+name,imgName);
	    	  //图片过大 不超过5MB
	    	  if(imgSize > 5242880){
	    		 map.put("code", HglContants.FAIL);
	    		 return map;
		      }
	    	  //上传的路径
    	      String pathDir = System.getProperty("user.home") + File.separator + "integralMall" + File.separator;
    		  productTypeService.uploadForm(imgFile,newName,pathDir);
    	  }catch(Exception e){
    		  logger.debug("文件上传失败.....");
    		  e.printStackTrace();
    	  }
    	  map.put("imgName",newName);
    	  return map;
      }
      
      /**
       * 生成显示图片
       * @param model
       * @param imgName
       * @param response
       * @return
       */
      @RequestMapping("/generateImage")
      public String generateImage(ModelMap model,String imgName,HttpServletResponse response) {
    	  try {
    		  String pathDir = System.getProperty("user.home") + File.separator + "integralMall" + File.separator + imgName;
              File imageFile = new File(pathDir);
              InputStream is = new FileInputStream(imageFile);
              OutputStream os = response.getOutputStream();
              while (true) {
                  byte[] buffer = new byte[10 * 1024];
                  int read = is.read(buffer);
                  if (read < 0) {
                      break;
                  }
                  os.write(buffer);
              }
              os.close();
              is.close();
          } catch (Exception e) {
        	  logger.error(e.getMessage());
        	  e.printStackTrace();
          }
    	  return null;
      }
     
 	 @Override
 	 public String doSearchResult() {
 	 	return null;
 	 }
}