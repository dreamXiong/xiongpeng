package com.liguo.hgl.proxydao.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

import com.liguo.hgl.proxydao.dao.TbCityInfoMapper;
import com.liguo.hgl.proxydao.dao.TbCountryInfoMapper;
import com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper;
import com.liguo.hgl.proxydao.dao.TbStreetInfoMapper;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbCountryInfo;
import com.liguo.hgl.proxydao.model.TbProvinceInfo;
import com.liguo.hgl.proxydao.model.TbStreetInfo;

@Component
public class AddressUtil implements IAddress{

	@Autowired
	   TbProvinceInfoMapper provinceInfoMapper;
		
		@Autowired
	    TbCityInfoMapper cityInfoMapper;
		
		@Autowired
	   TbCountryInfoMapper countryInfoMapper;
		
		@Autowired
	     TbStreetInfoMapper streetInfoMapper;
		
		public  String getProvinceName(int id){
			if(id >0){
				TbProvinceInfo provinceInfo =provinceInfoMapper.selectByPrimaryKey(id);
				if(provinceInfo!=null){
					return provinceInfo.getName();
				}
			}
			return "";
			
		}
		
		public String getCityName(int id){
			if(id >0){
			TbCityInfo cityInfo = cityInfoMapper.selectByPrimaryKey(id);
				if(cityInfo!=null){
					return cityInfo.getName();
				}
			}
			return "";
		}
		
		public String getCountryName(int id){
			if(id >0){
			TbCountryInfo tbCountryInfo = countryInfoMapper.selectByPrimaryKey(id);
			if(tbCountryInfo!=null){
				return tbCountryInfo.getName();
			}
			}
			return "";
		}
		
		public String getStreetName(int id){
			if(id >0){
			TbStreetInfo streetInfo=streetInfoMapper.selectByPrimaryKey(id);
			if(streetInfo!=null){
				return streetInfo.getName();
			}
			}
			return "";
		}

	@Override
	public String getAddressName(int pid, int cid, int nid, int sid) {
		String address="";
		
		
		address = address+getProvinceName(pid)+getCityName(cid)
				+getCountryName(nid)+getStreetName(sid);
	
		return address;
	}
	
	
	
}
