package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.AddressDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbStreetInfo;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbStreetInfoService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbStreetInfo selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbStreetInfo> selectByObject(Criteria example) throws RuntimeException;
    /**
     * 根据区域Id查询其所有对应的街道
     * @param cid
     * @return
     */
	List<TbStreetInfo> getStreetInfos(Integer cid);

    /*查询出省、市、区 、街道 分页显示*/
	List<AddressDto> selectObjectByPage(Criteria criteria,PageDto page);
	
    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbStreetInfo record);
    
    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbStreetInfo record);
    
    
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);
    
    /*查询地址信息*/
    public List<AddressDto> selectAddressInfo(Criteria example);
}