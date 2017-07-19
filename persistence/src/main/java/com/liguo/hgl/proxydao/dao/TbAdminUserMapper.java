package com.liguo.hgl.proxydao.dao;

import java.util.List;
import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbAdminUserMapper extends BaseMapper {
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
    int insert(TbAdminUser record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbAdminUser record);

    /**
     * 根据条件查询记录集
     */
    List<TbAdminUser> selectByObject(Criteria example);
    
    /**
     * 
    * @Description:根据仓库ID查询出该仓库下所有的管理员
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:List<TbAdminUser>
     */
    List<TbAdminUser> selectByTbWarehouseId(Integer tbWIntegerId);
    

    /**
     * 根据主键查询记录
     */
    TbAdminUser selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbAdminUser record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbAdminUser record);
    
    /**
     * 根据用户名密码查询用户
     */
    TbAdminUserDto selectByUsernameAndPwd(Criteria example);
     
    
    /*分页*/
    List<TbAdminUserDto> selectByObject(Criteria criteria,PageDto pgo);
   
}