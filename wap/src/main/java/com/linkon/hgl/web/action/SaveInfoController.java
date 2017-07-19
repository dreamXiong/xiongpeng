package com.linkon.hgl.web.action;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbSaveInfoService;
import com.liguo.hgl.util.ImageUtil;

@Controller
@RequestMapping("saveInfo")
public class SaveInfoController extends IBaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SaveInfoController.class);
	
	private static final String MYSAVEINFO = "saveInfo/mySaveInfo";
	
	@Autowired
	private TbSaveInfoService saveInfoService;
	
	
	/*显示所有的收藏*/
	@RequestMapping("/doSearchResult")
	public String doSearchResult(ModelMap model){
		
		logger.debug("显示用户:"+this.getLoginUser().getUserName()+" 所有的收藏");
		
		//判断登录用户是师傅还是终端客户
		TbWebUser webUser = this.getLoginUser();
		model.addAttribute("webUser", webUser);
		
		Criteria criteria = new Criteria();
				
		//店铺收藏
		criteria = new Criteria();
		criteria.put("saveType", HglContants.SHOP_SAVE);
		criteria.put("createBy", this.getLoginUser().getId());
		criteria.setOrderByClause("id desc");
		List<TbSaveInfo> shopList = saveInfoService.selectByObject(criteria);
		model.addAttribute("shopList", shopList);
		
		//产品收藏
		criteria.put("saveType", HglContants.GOOD_SAVE);
		criteria.put("createBy", this.getLoginUser().getId());
		criteria.setOrderByClause("id desc");
		List<TbSaveInfo> productList = saveInfoService.selectByObject(criteria);
		model.addAttribute("productList", productList);
				
		//服务收藏
		criteria = new Criteria();
		criteria.put("saveType", HglContants.SERVICE_SAVE);
		criteria.put("createBy", this.getLoginUser().getId());
		criteria.setOrderByClause("id desc");
		List<TbSaveInfo> serviceList = saveInfoService.selectByObject(criteria);
		model.addAttribute("serviceList", serviceList);
		model.addAttribute("code", getLoginUser().getLogoCode());
		return MYSAVEINFO;
	}
	
	
	/*删除收藏*/
	@RequestMapping("/doDeleteSaveInfo")
	@ResponseBody
	public String doDeleteSaveInfo(Integer id){
		if(saveInfoService.deleteByPrimaryKey(id)==1){
			return String.valueOf(true);
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
