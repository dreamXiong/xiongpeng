package com.linkon.hgl.web.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.BaiduMapDto;
import com.liguo.hgl.proxydao.dto.ShopIndexForWapDto;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.dto.WapProductType;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.GaoDeMapUtil;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbShopInfoService;

@RequestMapping("nearbyShop")
@Controller
public class NearbyShopController extends IBaseController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbShopInfoService tbShopInfoService ;
    
    @Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;
    
    private static final String SHOP_DETAIL = "nearbyShop/shopDetail";
    
    private static final Integer PAGE_SIZE = 6;
    

    @Override
    protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
    		//初始化筛选条件
    	 List<TbProductType> shopTypeList = productTypeService.selectNearShopType();
    	 model.addAttribute("shopTypeList", shopTypeList);
    	 model.addAttribute("shopInfoList", null);
    }
    
    @RequestMapping("findShopDetail")
    public String findShopDetail(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model){
        Integer shopId = Integer.parseInt(request.getParameter("id")); 
        Double distance = Double.parseDouble(request.getParameter("distance")); 
        String grade = request.getParameter("grade"); 
        Criteria c = new Criteria();
        c.put("id",shopId);
        List<WapProductType> productTypeList = tbShopInfoService.selectProductBrand(c);
        //查询店铺信息
        ShopIndexForWapDto t = tbShopInfoService.selectShopIndexInfo(c);
        //查询该店铺下所有产品 List<TbWapProductDto>  wapProductDtoList
        List<TbWapProductDto> wapProductDtoList = tbShopInfoService.selectTbWapProductDtoList(c);
        model.addAttribute("t", t);
        model.addAttribute("distance", distance);
        model.addAttribute("id", shopId);
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("wapProductDtoList", wapProductDtoList);
        model.addAttribute("grade", grade);
        return SHOP_DETAIL;
    }
    
    /***
     * <根据坐标获得距离用户最近的店铺>
     * @param request
     * @param model
     * @return
     * @author wzt
     * @since   2016年6月23日
     */
    @RequestMapping("getNearShop")
    public String getNearShop(HttpServletRequest request, Model model) {
        String lon = (String)(session.getAttribute(HglContants.NEAR_LON));
        String lat = (String)(session.getAttribute(HglContants.NEAR_LAT));
        Double userLat = 22.6392031;
        Double userLon = 114.038424;
        if(lat == null || lon == null){
        	logger.error(">>>>>>>>>>>>>>>>>>>request.getParameter is :"+ request.getParameter("lat"));
        	userLat = Double.parseDouble(request.getParameter("lat"));
        	userLon = Double.parseDouble(request.getParameter("lon"));
	    	session.setAttribute(HglContants.NEAR_LON, userLon+"");
	        session.setAttribute(HglContants.NEAR_LAT, userLat+"");
        }else{
        	userLon = Double.parseDouble(lon);
        	userLat = Double.parseDouble(lat);
        }
        BaiduMapDto d = null;
        try {
            d = GaoDeMapUtil.selectAddBycoordinate(userLon,userLat);
        }
        catch (IOException e) {
            logger.error("get lon lat is error ,error message is :"+ e.getMessage());
        }
        String address = "";
        if(StringUtils.isBlank(d.getRegeocode().getAddressComponent().getCity())){
            address = "定位失败";
        }else{
            address = d.getRegeocode().getAddressComponent().getCity();
            logger.debug("定位客户的地址："+address);
            session.setAttribute(HglContants.ADDRESS_CITY, address);
        }
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>lat="+lat+","+lon);
        Criteria c = new Criteria();
        c.put("lon", userLon);
        c.put("lat", userLat);
        selectListInfo(model,c,0);
        model.addAttribute("address", address);
        return "nearbyShop/nearbyShopList";
    }
    
    /**
     * 
     * */
    @RequestMapping("selectNearShopType")
    @ResponseBody
    public Map<String,Object> selectNearShopType(HttpServletRequest request) {
    	 Map<String,Object> map = new HashMap<String,Object>();
    	 map.put("address" , (String)session.getAttribute(HglContants.ADDRESS_CITY));
    	 map.put("userLat" , (String)session.getAttribute(HglContants.NEAR_LAT));
    	 map.put("userLon" , (String)session.getAttribute(HglContants.NEAR_LON));
    	 return map;
    }
    
    /***
     * <根据坐标获得距离用户最近的店铺>
     * @param request
     * @param model
     * @return
     * @author zk
     * @since 2016年8月10日
     */
    @RequestMapping("selectNearShopList")
    public String selectNearShopList(HttpServletRequest request, Model model){
    	
    	String lon = (String)(session.getAttribute(HglContants.NEAR_LON));
        String lat = (String)(session.getAttribute(HglContants.NEAR_LAT));
        
    	String typeList =(String)request.getParameter("typeList"); 
    	String seachText =(String)request.getParameter("searchText"); 
    	Criteria c = new Criteria();
    	if(!StringUtils.isBlank(typeList)){
			c.put("typeList",Arrays.asList(typeList.split(",")));
		}
    	if(!StringUtils.isBlank(seachText)){
    		c.put("searchText",seachText.trim());
		}
    	c.put("lon", lon);
	    c.put("lat", lat);
	    selectListInfo(model,c,0);
    	return "nearbyShop/nearbyShopList";
    }

    /***
     * <根据坐标获得距离用户最近的店铺 分页>
     * @param request
     * @param model
     * @return
     * @author zk
     * @since 2016年8月10日
     */
    @RequestMapping("selectNearShopPage")
    public String selectNearShopPage(HttpServletRequest request, Model model){
    	
    	String lon = (String)(session.getAttribute(HglContants.NEAR_LON));
        String lat = (String)(session.getAttribute(HglContants.NEAR_LAT));
        
    	String typeList =(String)request.getParameter("typeList"); 
    	String seachText =(String)request.getParameter("searchText"); 
    	//下一页
    	Integer indexPage = Integer.parseInt(request.getParameter("indexPage"));
    	
    	Criteria c = new Criteria();
    	if(!StringUtils.isBlank(typeList)){
			c.put("typeList",Arrays.asList(typeList.split(",")));
		}
    	if(!StringUtils.isBlank(seachText)){
    		c.put("searchText",seachText.trim());
		}
		c.put("lon", lon);
	    c.put("lat", lat);
	    selectListInfo(model,c,indexPage);
    	return "nearbyShop/nearbyShopList";
    }
    
    private void selectListInfo(Model model,Criteria c,Integer indexPage){
	    //总页数
	    int pageCount = (tbShopInfoService.selectNearTbShopPageCount(c)-1)/PAGE_SIZE+1;
	    c.put("pageFrom", PAGE_SIZE*indexPage);
		c.put("pageSize", PAGE_SIZE);
    	List<TbShopInfo> shopInfoList= tbShopInfoService.selectNearTbShopInfo(c);
    	model.addAttribute("pageCount", pageCount);
    	model.addAttribute("shopInfoList", shopInfoList);
    }
    @Override
    public String doSearchResult() {
    	
        return null;
    }
}

