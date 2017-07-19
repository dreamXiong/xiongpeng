package com.linkon.hgl.web.action;

import static com.liguo.hgl.util.JsonUtil.setJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbMerchantsAssociatedDto;
import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.model.TbAgency;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.PayService;
import com.liguo.hgl.service.TbAdminUserService;
import com.liguo.hgl.service.TbAgencyService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbMerchantsService;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.service.TbSaveInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbShoppingCartService;
import com.liguo.hgl.service.TbStatisticalService;
import com.liguo.hgl.util.ImageUtil;
import com.linkon.hgl.web.common.IpUtil;

/**
 * 招商
 * @fiMerchantsController.java
 * @2016-4-5    
 * @author 周双双
 */
@Controller
@RequestMapping("merchants")
public class MerchantsController extends IBaseController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final String SAVESUCCESS ="1"; //收藏成功
    private static final String SAVEERROR ="-1";  //收藏失败
    private static final String DLTSUCCESS ="2"; //取消成功
    private static final String DLTERROR = "-2"; //取消失败
    private static final String RECORDISEXIST="-1";
    
    @Autowired
    private TbMerchantsService iMerchantsService;
    
    @Autowired
    private TbStatisticalService statisticalService;
    
    @Autowired
    private TbProductService productService;
    
    @Autowired
    private TbBrandService brandService;
    @Autowired
    private IProductTypeService productTypeService;
    
    @Autowired
    private TbSaveInfoService saveInfoService;
    
    @Autowired
    private TbShopInfoService shopInfoService;
    
   @Autowired
    private TbAgencyService agencyService;
   @Autowired
   private TbShoppingCartService cartService;
   
   @Autowired
   private TbAdminUserService tbAdminUserService;
    
    /**
     * 前台招商列表
     * @return
     */
    @RequestMapping(value="/merchants")
    @ResponseBody
    public ModelAndView  getMerchants(){
        ModelAndView modelAndView = new ModelAndView();
        List<TbMerchantsAssociatedDto> list =null;
        if(getLoginUserId() !=null && getLoginUserId() >0){
             list = iMerchantsService.getMerchantsList(getLoginUserId());
        }else{
            list = iMerchantsService.getMerchantsList(0);
        }
        if(list !=null && list.size() >0){
            modelAndView.addObject("merchants", list);
            }else{
            	 modelAndView.addObject("msg", "没有满足条件的招商!");
            }
        modelAndView.setViewName("merchants/merchants");
        return modelAndView;
    }
    
    /**
     * 前台招商详情
     * @param id
     * @return
     */
     @RequestMapping(value="/merchantsDetails")
     public ModelAndView getMerchantsDetails(HttpServletRequest request,PageDto page,@RequestParam(required=false)Integer id,@RequestParam Integer bid){
        // page.pageSize = 10;
         ModelAndView modelAndView = new ModelAndView();
         if(getShopId()!=null && id >0 && iMerchantsService.isHasMerchants(id,getShopId())){
        	 modelAndView.addObject("errcode", "true");
        //围观量
         String browser = request.getHeader("user-agent");
         String ip = IpUtil.getRemoteHost(request);
         logger.debug("request -----ip："+ip+"client ："+browser);
        
        	statisticalService.addStatisticalView(id,browser,ip);
         
       
        //品牌logo，描述
        if(bid!=null&& bid>0){
            TbBrand brand =brandService.selectByPrimaryKey(bid);
            modelAndView.addObject("remark",brand.getRemark());
            List<TbProductType> productTypes = productTypeService.selectProductTypeByMinId(brand.getProductTypeId());
            if(productTypes.size() >0){
                modelAndView.addObject("productTypes", productTypes);
            } 
            
            /*查看该店铺是否被收藏*/
            TbSaveInfo saveInfo = saveInfoService.selectSaveInfo(bid, HglContants.SHOP_SAVE, this.getLoginUser().getId());
             modelAndView.addObject("saveInfo", saveInfo);
            
        }
               
        Criteria example = new Criteria();
        example.put("status", 1);
        example.put("brandIds", new String[]{bid+""});
        List<TbProductDto> tbProductList = productService.selectByCriteria(
                example, page);
        modelAndView.addObject("tbProductList", tbProductList);
        modelAndView.addObject("merchant", iMerchantsService.selectById(id));
        modelAndView.addObject("isBuyService", iMerchantsService.isMerchantService(id,getShopId()));
        if(getLoginUserId() !=null){
            modelAndView.addObject("shopCartMoney", cartService.getShopCartMoneyByBid(getLoginUserId(),bid));
        }
        modelAndView.addObject("bid", bid);
        modelAndView.addObject("cursor", 0);
        modelAndView.addObject("st", HglContants.MERCHANT_ORDER); //商品类型,招商
        modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
         }else{
        	 modelAndView.addObject("errcode", "false");
        	 modelAndView.addObject("msg", "对不起，你不满足此招商规则！");
         }
        modelAndView.setViewName("merchants/merchantsDetails");
        return modelAndView;
     }
     
     @RequestMapping("/merchantsImage")
     public String merchantsImage(ModelMap model,Integer id,HttpServletResponse response) {
        ImageUtil.showImageMerchants(id,response);
        return null;
     }
     
     /*添加到收藏*/
    @RequestMapping("/doAddSaveInfo")
    @ResponseBody
    public String doAddSaveInfo(Integer brandId,Integer typeId){
        Integer createdBy = this.getLoginUser().getId();      
        TbSaveInfo saveInfo = saveInfoService.selectSaveInfo(brandId, HglContants.SHOP_SAVE, createdBy);
        if(typeId == 0){  //取消收藏
        	if(saveInfo!=null){
        		if(saveInfoService.deleteByPrimaryKey(saveInfo.getId())==1){
        			return DLTSUCCESS;
        		}else{
        			return DLTERROR;
        		}
        	}
        }else if(typeId == 1){  //收藏
        	if(saveInfo==null){
        		if(saveInfoService.insertMerchantSelective(brandId,createdBy)==1){
            		return SAVESUCCESS;
            	}else{
            		return SAVEERROR;
            	}
        	}      	       
        }
        return String.valueOf(false);
    }
    /**
     * 得到购买招商服务页面
     * @param request
     * @param id
     * @return
     * @author zss
     */
    @RequestMapping(value="/merchantService")
    public ModelAndView merchantService(HttpServletRequest request,Integer id){
        ModelAndView modelAndView = new ModelAndView();
        if(getShopId() !=null && id!=null && id >0){
        	boolean isMerchantService= isMerchantService(id);
           if(isMerchantService){
            TbMerchants merchants = iMerchantsService.getMerchantService(id,getShopId());
            modelAndView.addObject("merchants", merchants);
           }
           modelAndView.addObject("isMerchantService", isMerchantService);
        }
        modelAndView.setViewName("merchants/merchantService");
        return modelAndView;
    }
    
   /**
    * 点击购买招商服务 
    * @param request
    * @param mid
    * @param referrName
    * @return
    * @author zss
    */
  @RequestMapping(value="/agency")
  public ModelAndView agency(HttpServletRequest request,Integer mid,Integer bid,String referrName){
        ModelAndView modelAndView = new ModelAndView();
        if(isMerchantService(mid)){
        	Map<String,Object> map = agencyService.addAgency(mid,getShopId(),referrName);
            if(Integer.parseInt(map.get("result").toString())>0){
                //modelAndView.setViewName("merchants/merchantsDetails?id="+mid+"&bid="+bid);
                if(map.get("methods").toString().equals("add")){
                	 statisticalService.addStatisticalParticipate(mid,request.getHeader("user-agent"),IpUtil.getRemoteHost(request));
                }
            }
        }
        modelAndView.setViewName("redirect:merchantsDetails?id="+mid+"&bid="+bid);
        return modelAndView;
    }
     /**
      * 判断是否能购买招商服务
      * @param mid
      * @return
      * @author zss
      */
     @RequestMapping("/isMerchantService")
     @ResponseBody
     public boolean isMerchantService(Integer mid){
	   if(getShopId()!=null && mid!=null && mid >0){
  		 return  iMerchantsService.isMerchantService(mid,getShopId());
	   }
  	  return false;
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

@RequestMapping(value="/checkRecommCode_ajax")
    public void checkRecommendCode(String recommendCode,HttpServletResponse response ){
        logger.debug("checkRecommendCode start...");
        //查询地推人员是否存在，如果存在，则允许插入
        Criteria c = new Criteria();
        c.put("recommendCode", recommendCode);
        c.put("type", HglContants.USER_TYPE_SALE);
        List<String> list = new ArrayList<String>();
        List<TbAdminUser> adminRole =tbAdminUserService.selectByObject(c);
        if(CollectionUtils.isEmpty(adminRole)){
            list.add("error");
        }else{
            list.add("success");
        }
        setJson(list, response);
    }
/**
 * 假接口
 */
@Autowired
private PayService payService;
@RequestMapping(value="/agencyResult")
public ModelAndView agency(@RequestParam Integer id){
      ModelAndView modelAndView = new ModelAndView();
      TbAgency agency = agencyService.selectByPrimaryKey(id);
      if(agency!=null){
    	 // TbAgency agency = agencyService.selectByPrimaryKey(id);
      	Map<String,Object> map = payService.payCallResults(agency.getCouponsOrderNumber(), agency.getCoupons());
          if(Integer.parseInt(map.get("errcode").toString())>0){
              //modelAndView.setViewName("merchants/merchantsDetails?id="+mid+"&bid="+bid);
              
          }else{
        	  
          }
          modelAndView.setViewName("redirect:merchantService?id="+agency.getMerchantId());
      }
    
      return modelAndView;
  }

@RequestMapping(value="/orderResult")
public ModelAndView orderResult(@RequestParam String orderNumber,@RequestParam double money){
	 ModelAndView modelAndView = new ModelAndView();
      Map<String,Object> map= payService.payCallSuccessResults(orderNumber, money);
      modelAndView.addObject("errcode", map.get("errcode"));
      modelAndView.addObject("msg", map.get("msg"));
    
      modelAndView.addObject("errmsg", map.get("errmsg"));
      return modelAndView;
  }



     
}


