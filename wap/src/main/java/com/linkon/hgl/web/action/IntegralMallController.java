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
import org.springframework.web.bind.annotation.RequestMethod;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralMall;
import com.liguo.hgl.proxydao.model.TbWapAddress;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbIntegralMallService;
import com.liguo.hgl.service.TbIntegralService;
import com.liguo.hgl.service.TbWapAddressService;

@Controller
@RequestMapping("integralMall")
public class IntegralMallController extends IBaseController {

	@Autowired
	protected TbIntegralMallService tbIntegralMallService;
	
	@Autowired
	private TbIntegralService tbIntegralService;

	@Autowired
	protected TbWapAddressService tbWapAddressService;
	
	@Autowired
	protected MyAddressController myAddressController;
	
	/**
	 * 查询列表
	 */
	@Override
	protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model){
		Criteria criteria = new Criteria();
		criteria.put("status", 1);
		criteria.put("integralMallTypeId", request.getParameter("integralMallTypeId"));
		List<TbIntegralMall> integralMallList = tbIntegralMallService.selectByObject(criteria);
		model.addAttribute("integralMallList", integralMallList);
		//调用查询出当前用户的积分
		selectByUserIdObject(model);
	}
	
	 /**
     * 积分兑换(我要兑换)页面
     * @param id
     * @param addressId
     * @param model
     * @return
     */
    @RequestMapping("/integralExchange")
    public String integralExchange(Integer integralMallId,Integer addressId,Model model) throws Exception{
    	//根据积分商城ID查询
    	selectByPrimaryKey(integralMallId,model);
    	//查询出该用户的默认地址(如果addressId不等于就根据addressId查询)
    	getAddress(addressId,model);
    	return "integralMall/integralExchange";
    }
    
	 /**
     * 查询出该用户的默认地址(如果addressId不等于就根据addressId查询)
     * @param addressId
     * @param model
     * @return
     */
    @RequestMapping("/getAddress")
    public String getAddress(Integer addressId,Model model) throws Exception{
    	//查询出该用户的默认地址(如果addressId不等于就根据addressId查询)
		Criteria criteria = new Criteria();
		if(addressId != null){
			criteria.put("id", addressId);
		}else{
			criteria.put("checkFlag", 0);
		}
		criteria.put("userId", this.getLoginUserId());
		TbWapAddress wapAddress = tbWapAddressService.selectByDefaultObject(criteria);
		model.addAttribute("wapAddress", wapAddress);
		return "integralMall/getAddress";
    }
	
	 /**
     * 根据Id查询出该信息和积分信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getIntegralMallInfo")
    public String getIntegralMallInfo(Integer id,Model model) throws Exception{
    	//根据积分商城ID查询
    	selectByPrimaryKey(id,model);
    	//调用查询出当前用户的积分
    	selectByUserIdObject(model);
    	return "integralMall/integralMallDetail";
    }
	 
    /**
     * 根据Id查询出该信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/selectByPrimaryKey")
    public String selectByPrimaryKey(Integer id,Model model) throws Exception{
    	//根据积分商城ID查询
    	TbIntegralMall integralMall = tbIntegralMallService.selectByPrimaryKey(id);
    	model.addAttribute("integralMall", integralMall);
    	return "integralMall/integralMallDetail";
    }
    
    /**
     * 查询出当前用户的积分
     * @param model
     * @return
     */
    @RequestMapping("/selectByUserIdObject")
    public String selectByUserIdObject(Model model){
    	//查询出该用户的积分
    	Criteria criteria = new Criteria();
 		criteria.put("userId", this.getLoginUserId());
 		TbIntegral integral = tbIntegralService.selectByUserIdObject(criteria);
    	model.addAttribute("integral", integral);
    	return "integralMall/selectByUserIdObject";
    }
    
    /**
     * 显示出我的收货地址
     * @param addressId  收货地址ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showMyAddress", method = RequestMethod.POST)
    public String showMyAddress(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
    	//根据地址Id查找出我的地址
		Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
		List<TbWapAddress> addressList = tbWapAddressService.selectByObject(criteria);
		if(addressList != null && addressList.size()>0){
			model.addAttribute("addressList", addressList);
			return "integralMall/addressList";
		}
		return "myAddress/AddLocationMyAddress";
    }
    
	 /**
     * 添加收货地址
     * @param address 地址对象
     * @param integralMallId 积分商城ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(TbWapAddress address,Integer integralMallId,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
    	myAddressController.addAddress(address,model);
    	integralExchange(integralMallId,address.getId(),model);
    	return "integralMall/integralExchange";
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