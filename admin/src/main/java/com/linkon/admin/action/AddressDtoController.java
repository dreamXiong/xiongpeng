package com.linkon.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.AddressDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbCountryInfo;
import com.liguo.hgl.proxydao.model.TbProvinceInfo;
import com.liguo.hgl.proxydao.model.TbStreetInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbCityInfoService;
import com.liguo.hgl.service.TbCountryInfoService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbStreetInfoService;


@Controller
@RequestMapping("address")
public class AddressDtoController extends IBaseController {

	private static final String ADDRESS = "address/address";
	
	private static final String ADDRESSLIST = "address/addressList";
	
	private static final Integer STREETADD = 1; //添加街道
	
	private static final Integer COUNTRYADD=2;   //添加区县
	
	@Autowired
	private TbStreetInfoService streetInfoService;
	
	@Autowired
	private TbProvinceInfoService provinceInfoService;
	
	@Autowired
	private TbCityInfoService cityInfoService;
	
	@Autowired
	private TbCountryInfoService countryInfoService;
		
	
	@RequestMapping("/doInitAddress")
	public String doInitAddress(PageDto page,ModelMap model){
		Criteria criteria = new Criteria();
		criteria.setOrderByClause("s.id");
		List<AddressDto> list = streetInfoService.selectObjectByPage(criteria, page);
		model.addAttribute("list", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return ADDRESS;
	}
	
	@RequestMapping("/doSearchResult")
	public String doSearchResult(AddressDto addressDto,PageDto page,ModelMap model){
		Criteria criteria = new Criteria();
		criteria.put("streetName", addressDto.getStreetName());
		criteria.put("countryName", addressDto.getCountryName());
		criteria.put("cityName", addressDto.getCityName());
		criteria.put("provinceName", addressDto.getProvinceName());
		criteria.setOrderByClause("s.id");
		
		List<AddressDto> list = streetInfoService.selectObjectByPage(criteria, page);
		model.addAttribute("list", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return ADDRESSLIST; 
	}
	
	/*查找省*/
	@RequestMapping(value="/doSearchProvinceResult",produces="text/html;charset=utf-8")
	@ResponseBody
	public String doSearchProvinceResult(){
		String strJson = "";
		List<TbProvinceInfo> list = provinceInfoService.selectByObject(new Criteria());
		ObjectMapper mapper = new ObjectMapper();
		try {
			strJson = mapper.writeValueAsString(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return strJson;
	}
	
	/*查找市*/
	@RequestMapping(value="/doSearchCityResult",produces="text/html;charset=utf-8")
	@ResponseBody
	public String doSearchCityResult(Integer parentId){
		String strJson = "";
		Criteria criteria = new Criteria();
		criteria.put("parentid", parentId);
		List<TbCityInfo> list = cityInfoService.selectByObject(criteria);
		ObjectMapper mapper = new ObjectMapper();
		try {
			strJson = mapper.writeValueAsString(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return strJson;
	}
	
	/*查找区*/
	@RequestMapping(value="/doSearchCountryResult",produces="text/html;charset=utf-8")
	@ResponseBody
	public String doSearchCountryResult(Integer parentId){
		String strJson = "";
		Criteria criteria = new Criteria();
		criteria.put("parentid", parentId);
		List<TbCountryInfo> list = countryInfoService.selectByObject(criteria);
		ObjectMapper mapper = new ObjectMapper();
		try {
			strJson = mapper.writeValueAsString(list);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return strJson;
	}
	
	/*查找街道*/
	@RequestMapping(value="/doSearchStreetResult",produces="text/html;charset=utf-8")
	@ResponseBody
	public String doSearchStreetResult(Integer parentId){
		String strJson = "";
		Criteria criteria = new Criteria();
		criteria.put("parentid", parentId);
		List<TbStreetInfo> list = streetInfoService.selectByObject(criteria);
		ObjectMapper mapper = new ObjectMapper();
		try {
			strJson = mapper.writeValueAsString(list);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return strJson;
	}
	
	/*新增区县、街道*/
	@RequestMapping("/doAddStreetInfo")
	@ResponseBody
	public String doAddStreetInfo(Integer addType,Integer cityId,Integer countryId,String countryName,Integer streetId,String streetName){
		if(addType == STREETADD){ //如果是添加街道
			TbStreetInfo streetInfo = new TbStreetInfo();
			streetInfo.setId(streetId);
			streetInfo.setName(streetName);
			streetInfo.setParentid(countryId);
			if(streetInfoService.insert(streetInfo)==1){
				return String.valueOf(true);
			}else{
				return String.valueOf(false);
			}
		}else if(addType == COUNTRYADD){ //如果是添加区县
			TbCountryInfo countryInfo = new TbCountryInfo();
			countryInfo.setId(countryId);
			countryInfo.setName(countryName);
			countryInfo.setParentid(cityId);
			if(countryInfoService.insert(countryInfo)==1){
				return String.valueOf(true);
			}else{
				return String.valueOf(false);
			}
		}
		
		return String.valueOf(false);
		
	}
	
	/*初始化修改信息*/
	@RequestMapping("/doInitUpdateAddress")
	@ResponseBody
	public String doInitUpdateAddress(Integer provinceId,Integer cityId,Integer countryId,Integer streetId){
		String strJson = "";
		Criteria criteria = new Criteria();
		criteria.put("streetId", streetId);
		criteria.put("countryId", countryId);
		criteria.put("cityId", cityId);
		criteria.put("provinceId", provinceId);
		
		List<AddressDto> list= streetInfoService.selectAddressInfo(criteria);
		
		AddressDto addressDto = null;
		ObjectMapper mapper = new ObjectMapper();
		if(list.size()>0){
			addressDto = list.get(0);
			try {
				strJson = mapper.writeValueAsString(addressDto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return strJson;		
	}
	
	//修改街道、区县、市名称
	@RequestMapping("/doUpdateAddress")
	@ResponseBody
	public String doUpdateAddress(Integer cityId,String cityName,Integer countryId,String countryName,Integer streetId,String streetName){
		if(cityId != null && StringUtil.isNotBlank(cityName)){
			TbCityInfo cityInfo = new TbCityInfo();
			cityInfo.setName(cityName);
			cityInfo.setId(cityId);
			cityInfoService.updateByPrimaryKeySelective(cityInfo);
		}
		
		if(countryId !=null && StringUtil.isNotBlank(countryName)){
			TbCountryInfo countryInfo = new TbCountryInfo();
			countryInfo.setName(countryName);
			countryInfo.setId(countryId);
			countryInfoService.updateByPrimaryKeySelective(countryInfo);
		}
		
		if(streetId != null && StringUtil.isNotBlank(streetName)){
			TbStreetInfo streetInfo = new TbStreetInfo();
			streetInfo.setName(streetName);
			streetInfo.setId(streetId);
			streetInfoService.updateByPrimaryKeySelective(streetInfo);
		}
		
		return "";	
	}
	
	//删除街道信息
	@RequestMapping("/doDeleteStreet")
	@ResponseBody
	public String doDeleteStreet(Integer streetId){
		
		return String.valueOf(streetInfoService.deleteByPrimaryKey(streetId));
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
