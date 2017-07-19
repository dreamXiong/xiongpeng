package com.linkon.hgl.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbSaveInfoService;
import com.liguo.hgl.util.ImageUtil;

@Controller
@RequestMapping("saveinfo")
public class SaveInfoController extends IBaseController{
	
	@Autowired
	private TbSaveInfoService saveInfoService;
	
	@Autowired
	private TbBrandService brandService;
	
	private static final String SAVEINFO="saveinfo/saveinfo";
	
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
	
	/**
	 * 查询所有该用户收藏收藏
	 * @param page
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/doSearchResult")
	public String doSearchResult(ModelMap model){
		
		//查询出当前用户所有的产品收藏
		String createdBy = this.getLoginUser().getUserName();
		Criteria criteria = new Criteria();
		criteria.put("saveType", HglContants.GOOD_SAVE);	
		criteria.put("createBy", createdBy);
		List<TbSaveInfo> list=saveInfoService.selectByObject(criteria);
		model.addAttribute("saveGoodInfos", list);
		
		
		//查询出当前用户所有的店铺收藏
		criteria = new Criteria();
		criteria.put("saveType", HglContants.SHOP_SAVE);
		criteria.put("createBy", createdBy);
		list = saveInfoService.selectByObject(criteria);
		model.addAttribute("saveShopInfos", list);
		
		return SAVEINFO;			
	}
	
	
	/*添加到收藏*/
	@RequestMapping("/doAddSaveInfo")
	@ResponseBody
	public String doAddSaveInfo(TbSaveInfo saveInfo){
		if(saveInfoService.insertSelective(saveInfo)==1){
			return String.valueOf(true);
		}else{
			return String.valueOf(false);
		}				
	}
	
	/*显示五金商品图片*/
	@RequestMapping("/generateGoodImage")
	public String generateGoodImage(ModelMap model,String imgName,HttpServletResponse response) {
			ImageUtil.showImage("shopLogo",imgName,response);
		return null;
	}
	
	/*删除收藏*/
	@RequestMapping("/doDeleteSaveInfo")
	@ResponseBody
	public String doDeleteSaveInfo(Integer id){
		
		int count = saveInfoService.deleteByPrimaryKey(id);
		
		return String.valueOf(count);
	}
	
	/*显示店铺品牌图片*/
	@RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer brandId,HttpServletResponse response) {
    	TbBrand tbBrand = brandService.selectByPrimaryKey(brandId);
    	model.addAttribute("tbBrand", tbBrand);
    	if(tbBrand!=null){
    		ImageUtil.showImage("shopLogo",tbBrand.getLogoName(),response);
    	}
        return null;
    }
	
}
