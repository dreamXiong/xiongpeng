package com.linkon.hgl.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbWapOrderService;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbWapOrderServiceService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.Des3;
@Controller
@RequestMapping("serviceOrder")
public class ServiceOrderController extends IBaseController {

    @Autowired
    private TbWapOrderServiceService tbWapOrderServiceService;
    
    @Autowired
    private TbShopInfoService tbShopInfoService;
    @Autowired
    private TbWebUserService  tbWebUserService;
    
    private static final String FIND_MY_ORDER_SERVICE = "serviceOrder/findMyOrderService";
    
    private static final String FIND_MY_MASTER = "serviceOrder/findMymaster";
    
    private static final String FIND_MY_MASTER_LIST = "serviceOrder/findMymasterList";
    
    private static final String SERVICE_ORDER = "serviceOrder/serviceOrder";
    
    private static final String SERVICE_ORDER_LIST = "serviceOrder/serviceOrderList";
    
    private static final String MY_SERVICE = "serviceOrder/myservice";
    
    private static final String MY_SERVICE_LIST = "serviceOrder/myServiceList";
    
    @Override
    protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
        
        Integer userId =getLoginUserId();
        WebUserDto webUserDto =getLoginUser();
        Integer shopId =webUserDto.getShopId();
        //当前用户不存，没有店铺 
        if(shopId==null){
            return;
        }
        TbShopInfo tbShopInfo =tbShopInfoService.selectByPrimaryKey(shopId);
        
        String lat=String.valueOf(tbShopInfo.getLat());//纬度
        String lon =String.valueOf(tbShopInfo.getLon());//经度
        //服务订单列表
        List<WapOrderServiceDto> wapOrderServiceDtoList =tbWapOrderServiceService.getService(userId, shopId, lon, lat,page);
        
        model.addAttribute(HglContants.PAGE_DTO_ID, page);
        model.addAttribute("wapOrderServiceDtoList", wapOrderServiceDtoList);
    }
    
    @RequestMapping(value="/pageindex")
    public String pageInit(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
        Integer userId =getLoginUserId();
        WebUserDto webUserDto =getLoginUser();
        Integer shopId =webUserDto.getShopId();
        //当前用户不存，没有店铺 
        if(shopId==null){
            return null;
        }
        TbShopInfo tbShopInfo =tbShopInfoService.selectByPrimaryKey(shopId);
        String lat=String.valueOf(tbShopInfo.getLat());//纬度
        String lon =String.valueOf(tbShopInfo.getLon());//经度
        //服务订单列表
        List<WapOrderServiceDto> wapOrderServiceDtoList =tbWapOrderServiceService.getService(userId, shopId, lon, lat,page);
        
        model.addAttribute(HglContants.PAGE_DTO_ID, page);
        model.addAttribute("wapOrderServiceDtoList", wapOrderServiceDtoList);
        return "serviceOrder/serviceOrderList";
    }

    @Override
    public String doSearchResult() {
        
        // TODO Auto-generated method stub
        return null;
    }
    
    
    /**
     * 师傅接单
     * **/
    @RequestMapping(value="/doMasterOrder")
    public String doMasterOrder(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model,Integer id){
        Map<String,Object> map =null;
        if(id>0 && getLoginUserId()!=null){
            map = tbWapOrderServiceService.doMasterOrder(id,getLoginUserId());
        }
        init(page, request, response, model);
        return SERVICE_ORDER_LIST;
    }
    
    
    /**
     * 查询自己的待服务的订单
     * **/
    @RequestMapping(value="/findMyOrderService")
    public String findMyOrderService(Model model){
        Integer userId =getLoginUserId();
        List<TbWapOrderService>  tbWapOrderServiceList =tbWapOrderServiceService.findMyOrderService(userId);
        model.addAttribute("tbWapOrderServiceList", tbWapOrderServiceList);
        return FIND_MY_ORDER_SERVICE;
    }
    
    
    
    /**
     * 查询店铺自己的师傅
     * **/
    @RequestMapping(value="/findMymaster")
    public String findMymaster(Model model,Integer id,PageDto pageDto){
        Integer userId =getLoginUserId();
        List<Map<String,Object>>  tbWapOrderServiceList =tbWapOrderServiceService.findMymaster(id,userId,pageDto);
        model.addAttribute("tbWapOrderServiceList", tbWapOrderServiceList);
        model.addAttribute("id", id);
        model.addAttribute("page", pageDto);
        return FIND_MY_MASTER;
    }
    
    /**
     * 更新师傅
     * **/
    @RequestMapping(value="/updateMaster")
    public String updateMaster(Integer id,Integer pId,PageDto page, HttpServletRequest request, HttpServletResponse response, Model model){
        tbWapOrderServiceService.updateMaster(id,pId);
        return getMyService(model, null,page);
    }
    
    /**
     * 师傅接单
     * **/
//    @RequestMapping(value="/doMasterOrder")
//    public String doMasterOrder(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model,Integer id){
//        Map<String,Object> map =null;
//        if(id>0 && getLoginUserId()!=null){
//            map = tbWapOrderServiceService.doMasterOrder(id,getLoginUserId());
//        }
//        init(page, request, response, model);
//        return SERVICE_ORDER_LIST;
//    }
    
    /**
     * 师傅开始服务，确认支付
     * @param model
     * @param id
     * @return
     * @author wzt
     */
    @RequestMapping(value="/doOrderService",method=RequestMethod.POST)
    public String doOrderService(Model model,Integer id,PageDto page){
        Map<String,Object> map =null;
        if(id>0 && getLoginUserId()!=null){
            map = tbWapOrderServiceService.doOrderService(id,getLoginUserId());
        }
        getMyService(model, null,page);
        return MY_SERVICE_LIST;
    }
    
    /**
     * 师傅取消接单，服务挂起，恢复服务
     * @param model
     * @param id
     * @return
     * @author wzt
     */
    @RequestMapping(value="/doAltService")
    public String doAltService(Model model,Integer id,PageDto page){
        Map<String,Object> map =null;
        if(id>0 && getLoginUserId()!=null){
            map = tbWapOrderServiceService.doAltService(id,getLoginUserId());
        }
        getMyService(model, null,page);
        return MY_SERVICE_LIST;
    }
    
     
     /**
      * 师傅完成服务，输入价格，改价
      * @param model
      * @param id
      * @return
      * @author wzt
      */
     @RequestMapping(value="/doServicePrice")
     public String doServicePrice(Model model,Integer id,String price,PageDto page){
        if(id>0 && getLoginUserId()!=null){
            tbWapOrderServiceService.doServicePrice(id,getLoginUserId(),price);
        }
       return  getMyService(model, null,page);
     }
     
     /**
      * 查询我的服务
      * @param model
      * @return
      * @author zss
      */
     @RequestMapping("/myService")
     public String getMyService(Model model,@RequestParam(required=false)Integer sfSercive,PageDto page){
         if(getLoginUserId()!=null){
             List<WapOrderServiceDto> orderServiceDtos= tbWapOrderServiceService.getMyService(getLoginUserId(),getLoginUser().getTypeId(),sfSercive,page);
             model.addAttribute(HglContants.PAGE_DTO_ID,page);//是否是普通用户
             model.addAttribute("isCUS",getLoginUser().getTypeId().equals(HglContants.USER_TYPE_CUS));//是否是普通用户
             model.addAttribute("wapOrderServiceDtoList",orderServiceDtos);
         }
         return MY_SERVICE;
     }
     @RequestMapping("/myServiceList")
     public String myServiceList(Model model,@RequestParam(required=false)Integer sfSercive,PageDto page){
         if(getLoginUserId()!=null){
             List<WapOrderServiceDto> orderServiceDtos= tbWapOrderServiceService.getMyService(getLoginUserId(),getLoginUser().getTypeId(),sfSercive,page);
             model.addAttribute(HglContants.PAGE_DTO_ID,page);//是否是普通用户
             model.addAttribute("isCUS",getLoginUser().getTypeId().equals(HglContants.USER_TYPE_CUS));//是否是普通用户
             model.addAttribute("wapOrderServiceDtoList",orderServiceDtos);
         }
         return MY_SERVICE_LIST;
     }
     
     /**
      * 取消订单
      * @param model
      * @param id
      * @return
      * @author zss
      */
     @RequestMapping(value="/doCancelOrder")
     public String doCancelOrder(Model model,Integer id,PageDto page){
         Map<String,Object> map =null;
         if(id>0 && getLoginUserId()!=null){
             map = tbWapOrderServiceService.doCancelOrder(id,getLoginUserId());
         }
         getMyService(model, null,page);
         return MY_SERVICE_LIST;
     }
     
     @RequestMapping(value="/checkCityLowerPrice")
     @ResponseBody
     public Map<String, Object> checkCityLowerPrice(Integer id,String price){
         Map<String,Object> map= new HashMap<String, Object>();
         if(id >0){
          map = tbWapOrderServiceService.checkCityLowerPrice(id,price);
         }
         return map;
     }
}

