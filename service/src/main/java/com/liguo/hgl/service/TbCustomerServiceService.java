package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.CustomerServiceDto;
import com.liguo.hgl.proxydao.model.ProductInfoDto;
import com.liguo.hgl.proxydao.model.TbCustomerService;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbCustomerServiceService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCustomerService selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCustomerService> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCustomerService record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCustomerService record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCustomerService record) throws RuntimeException;
    
    
    public List<CustomerServiceDto> selectInfoListByName(String name, PageDto page);
    /**
     * 手机端服务列表展示
     * */
    public List<CustomerServiceDto> selectInfoListByName(Criteria example);
    
    /**
     * @Description:根据服客户服务Id查询客户服务和务类型信息
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:String
     */
    public CustomerServiceDto selectInfoById(Integer id);
    
    /**
     * @Description:插入服客户服务信息
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:String
     */
    public int insertCustomerService(TbCustomerService record) throws RuntimeException;
    
    /**
     * @Description:更新服客户服务信息
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:String
     */
    public int UpdateCustomerService(TbCustomerService record);
}