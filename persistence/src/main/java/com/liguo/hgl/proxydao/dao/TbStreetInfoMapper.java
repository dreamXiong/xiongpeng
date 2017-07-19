package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.AddressDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbStreetInfo;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbStreetInfoMapper extends BaseMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByObject(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByObject(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbStreetInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbStreetInfo record);

    /**
     * 根据条件查询记录集
     */
    List<TbStreetInfo> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbStreetInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbStreetInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbStreetInfo record);
    
    /**
     * 根据cityId查询市下面所有的街道
     * @param example
     * @return
     */
	int countByCityParentid(Criteria example);
	
	/*查询省、市、区、街道 分页显示*/
	List<AddressDto> selectObjectByPage(Criteria example,PageDto page);
	
	List<AddressDto> selectAddressInfo(Criteria example);
}