package com.linkon.hgl.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbCountryInfo;
import com.liguo.hgl.proxydao.model.TbStreetInfo;
import com.liguo.hgl.service.TbCityInfoService;
import com.liguo.hgl.service.TbCountryInfoService;
import com.liguo.hgl.service.TbStreetInfoService;
/**
 * 地区联动效果
 * @fiAddressController.java
 * @2016-4-19	
 * @author 周双双
 */
@Controller
@RequestMapping("address")
public class AddressController {

	@Autowired
	protected TbCityInfoService tbCityInfoService;
	
	@Autowired
	protected TbCountryInfoService tbCountryInfoService;
	
	@Autowired
	protected TbStreetInfoService tbStreetInfoService;
	
		
		 /**
		  * 根据省Id查询所有的城市
		  * @param pid
		  * @return
		  */
		 @ResponseBody
		 @RequestMapping(value="/cityInfos")
		 public Map<String,Object> getCityInfos(@RequestParam Integer pid){
		 	List<TbCityInfo> cityInfos = tbCityInfoService.getCityInfos(pid);
		 	Map<String,Object> map = new HashMap<String,Object>();
		 	map.put("cityInfos", cityInfos);
		 	return map;
		 }
		 
		 /**
		  * 根据市Id查询其下所有的区域
		  * @param cid
		  * @return
		  */
		 @ResponseBody
		 @RequestMapping(value="/countryInfos")
		 public Map<String,Object> getCountryInfos(@RequestParam Integer cid){
		 	List<TbCountryInfo> countryInfos = tbCountryInfoService.getCountrys(cid);
		 	Map<String,Object> map = new HashMap<String,Object>();
		 	map.put("countryInfos", countryInfos);
		 	return map;
		 }
		 
		 /**
		  * 根据区域Id查询其下所有的街道
		  * @param cid
		  * @return
		  */
		 @ResponseBody
		 @RequestMapping(value="/streetInfos")
		 public Map<String,Object> getStreetInfos(@RequestParam Integer cid){
		 	List<TbStreetInfo> streetInfos = tbStreetInfoService.getStreetInfos(cid);
		 	Map<String,Object> map = new HashMap<String,Object>();
		 	map.put("streetInfos", streetInfos);
		 	return map;
		 }
}
