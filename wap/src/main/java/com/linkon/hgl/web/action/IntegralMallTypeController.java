package com.linkon.hgl.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralMallType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbIntegralMallTypeService;

@Controller
@RequestMapping("integralMallType")
public class IntegralMallTypeController extends IBaseController {

	@Autowired
	protected TbIntegralMallTypeService tbIntegralMallTypeService;
	@Autowired
	protected IntegralMallController integralMallController;

	/**
	 * 查询列表
	 */
	@Override
	protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
		Criteria criteria = new Criteria();
		criteria.put("status", 1);
		List<TbIntegralMallType> integralMallTypeList = tbIntegralMallTypeService.selectByObject(criteria,page);
		model.addAttribute("integralMallTypeList", integralMallTypeList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
    	//调用查询出当前用户的积分
    	integralMallController.selectByUserIdObject(model);
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