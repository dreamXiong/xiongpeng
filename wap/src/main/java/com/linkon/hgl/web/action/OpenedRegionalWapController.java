package com.linkon.hgl.web.action;

import static com.liguo.hgl.util.JsonUtil.setJson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;
import com.liguo.hgl.proxydao.model.TbProvinceInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbCityInfoService;
import com.liguo.hgl.service.TbCountryInfoService;
import com.liguo.hgl.service.TbOpenedRegionalService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbWebPermissionService;
import com.liguo.hgl.service.TbWebRoleService;

/***
 * *
 * 
 * <已开通区域><br>
 * 
 * @FileName OpenedRegionalController.java。<br>
 * @Language java。
 * @date 2016年6月12日
 * @author wzt
 */
@Controller
@RequestMapping("/openedRegional")
public class OpenedRegionalWapController extends IBaseController {

    @Autowired
    private TbWebPermissionService tbWebPermissionService;

    @Autowired
    private TbWebRoleService tbWebRoleService;
    
    @Autowired
    private TbProvinceInfoService tbProvinceInfoService;
    
    @Autowired
    private TbCityInfoService tbCityInfoService;
    
    @Autowired
    private TbCountryInfoService tbCountryInfoServicel;
    
    @Autowired
    private TbOpenedRegionalService tbOpenedRegionalService;
    


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String OPENED_REGIONAL = "/openedRegional/openedRegional";
    
    private static final String OPENED_REGIONAL_LIST = "/openedRegional/openedRegionalList";
    
    /**zTree默认选中,展开**/
    private static final String CHECKED = "true";

    @Override
    protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
        // TODO Auto-generated method stub

    }

    @Override
    public String doSearchResult() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @RequestMapping(value = "/cityView", method = RequestMethod.GET)
    public String cityView(Model model,String cityName,String actionName) throws UnsupportedEncodingException {
        logger.debug("start init...");
        Criteria c = new Criteria();
        List<TbOpenedRegional>  tbOpenedRegionalList =tbOpenedRegionalService.selectByObject(c);
        if(CollectionUtils.isEmpty(tbOpenedRegionalList)){
            
        }
        String city ="";
        for(int i=0;i<tbOpenedRegionalList.size();i++){
            TbOpenedRegional tbOpenedRegional=tbOpenedRegionalList.get(i);
            city+= tbOpenedRegional.getOpenCity();
            if(i!=tbOpenedRegionalList.size()-1){
//                city=city+",";
            }
        }
        
        if(!StringUtil.isBlank(city)){
            String[]  openCityArr =city.split(",");
            List<String> openCityList = Arrays.asList(openCityArr);
            List<TbCityInfo> tbCityInfoList =tbCityInfoService.findBatchCityById(openCityList);
            model.addAttribute("tbCityInfoList", tbCityInfoList);
        }
        model.addAttribute("cityName", cityName);
        model.addAttribute("actionName", actionName);
        
        return OPENED_REGIONAL;
    }
    @RequestMapping(value = "/countryView", method = RequestMethod.POST)
    public void countryView(Integer id,Integer cityId,Model model, HttpServletResponse response) {
        logger.debug("start init...");
        Criteria c = new Criteria();
        c.put("proviceId",id);
        //查询所有省份
        List<TbOpenedRegional>  tbOpenedRegionalList =tbOpenedRegionalService.findOpenedRegByproviceId(c);
        if(!CollectionUtils.isEmpty(tbOpenedRegionalList)){
            String openCity =tbOpenedRegionalList.get(0).getOpenCountry();
            if(!StringUtil.isBlank(openCity)){
                String[]  openCityArr =openCity.split(",");
                List<String> openCityList = Arrays.asList(openCityArr);
                c = new Criteria();
                c.put("openCityList", openCityList);
                c.put("cityId", cityId);
                List<TbCityInfo> tbCityInfoList =tbCountryInfoServicel.findBatchCountryById(c);
                setJson(tbCityInfoList, response);
            }
        }
    }
    
    
    
    
    @RequestMapping(value = "/cityTreeShow_ajax", method = RequestMethod.GET)
    public void cityTreeShow(Integer id, HttpServletResponse response,Model model) {
        logger.debug("roleTreeShow init...");
        TbProvinceInfo tbProvinceInfo =tbProvinceInfoService.selectByPrimaryKey(id);
        //查询省份中第一个的市区
        Integer provinceId =tbProvinceInfo.getId();
        
        //查询该省下的所有的市
        Criteria criteria = new Criteria();
        criteria.put("parentid", provinceId);
        List<TbCityInfo>  cityList =tbCityInfoService.selectByObject(criteria);
        
        //查询省下所有的市区
        List<Map<String, Object>>  tbCityInfoList =tbCityInfoService.findCityByProvice(provinceId);
        
        criteria = new Criteria();
        criteria.put("proviceId", provinceId);
        //查询市下面所有已经开通的区
        List<TbOpenedRegional>  tbOpenedRegionalList =tbOpenedRegionalService.findOpenedRegByproviceId(criteria);
        
        List<String>  openCodeList = new ArrayList<String>();
        Integer version =0;
       if(!CollectionUtils.isEmpty(tbOpenedRegionalList)){
           //版本号
           version = tbOpenedRegionalList.get(0).getVersion();
           
           //拼装出所有区域数组
           for(TbOpenedRegional tor :tbOpenedRegionalList){
               String openCity =tor.getOpenCity();
               String openCountry =tor.getOpenCountry();
               
               if(StringUtil.isBlank(openCity)&&StringUtil.isBlank(openCountry))
                   continue;
               String[] openCityArr =openCity.split(",");
               String[] openCountryArr =openCountry.split(",");
               openCodeList.addAll(Arrays.asList(openCityArr));
               openCodeList.addAll(Arrays.asList(openCountryArr));
           }
       }
       
       //子节点数组
       for(Map<String, Object> tcil:tbCityInfoList ){
           String perId =String.valueOf(tcil.get("id"));
           if(openCodeList.contains(perId)){
               tcil.put("checked", CHECKED);
           }
           tcil.put("open", CHECKED);
       }
       List<Object> list =new ArrayList<Object>();
         list.add(version);
         list.add(tbCityInfoList);
         setJson(list, response);
    }
    
    
    @RequestMapping(value = "/addProvice_ajax", method = RequestMethod.GET)
    public void addProvice(TbOpenedRegional tbOpenedRegional,HttpServletResponse response) {
        logger.debug("addProvice init...");
        if(null!=tbOpenedRegional&&null!=tbOpenedRegional.getProviceId()){
            tbOpenedRegionalService.updateByPrimaryKeySelective(tbOpenedRegional);
            //查询版本号，更新前端版本
            tbOpenedRegional=tbOpenedRegionalService.selectByPrimaryKey(tbOpenedRegional.getProviceId());
            Integer version =tbOpenedRegional.getVersion();
            List<Integer> list =new ArrayList<Integer>();
            list.add(version);
            setJson(list, response);
        }
    }
}
