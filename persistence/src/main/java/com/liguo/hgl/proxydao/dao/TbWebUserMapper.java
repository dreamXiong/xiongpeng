package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.TbwebUserShopDto;
import com.liguo.hgl.proxydao.dto.UserRecommendedDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWebUserMapper extends BaseMapper {
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
    int insert(TbWebUser record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWebUser record);

    /**
     * 根据条件查询记录集
     */
    List<TbWebUser> selectByObject(Criteria example);
    
    /**
     * 根据用户名密码查询用户
     */
    WebUserDto selectByUsernameAndPwd(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbWebUser selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWebUser record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWebUser record);

	List<TbwebUserShopDto> selectByCriteria(Criteria criteria, PageDto page);
	/**
	 * 根据Id查询usershop
	 * @param id
	 * @return
	 * @author zss
	 */
	TbwebUserShopDto selectUserShopById(Integer id);
	
	/*后台前台用户管理分页*/
	List<WebUserDto> selectByObjectPage(Criteria criteria,PageDto page);

	TbWebUser selectWebUser(int shopId);
	
    /**
     * 根据openId查询
     */
	List<WebUserDto> selectByOpenIdList(Criteria example);
	
    /**
     * 查询推荐
     * @param example
     * @return
     * @author zss
     */
	List<UserRecommendedDto> selectRecommended(Criteria example);
	
	int updateUserOpenId(Criteria parameter) throws RuntimeException ;
	
	/*根据推荐码查找用户信息*/
	TbWebUser selectUserByRecommendCode(Criteria parameter) throws RuntimeException;
	
	/*判定推荐码是否存在*/
	TbWebUser selectUserByLogoCode(Criteria parameter) throws RuntimeException;
	
	List<TbWebUser> selectWebUserByGroupId(Criteria example);
	
	List<WebUserDto> selectUserGroupPage(Criteria criteria,PageDto page);
	/**
	 * 根据分组查询出对应的用户
	 * @param criteria
	 * @return
	 * @author zss
	 */
	List<TbWebUser> selectUserByGroup(Criteria criteria);
	
    /**
     * 查询店铺下面所有用户，收藏的
     * @param example
     * @return
     * @author zss
     */
	List<TbWebUser> selectBySaveShop(Criteria example);
	
	 TbWebUser selectByUserName(Criteria parameter) ;
	 
	 int updateUserPassword(Criteria parameter) throws RuntimeException;
	 
	 int updateUserOpenIdNull(Criteria parameter);
	 
	 int updateUserAutoLoginFlag(Criteria parameter);
	 
	 int updateUserAutoLoginFlagList(Criteria parameter);
	 
	 /*通过电话号码和密码来查找用户*/
	 WebUserDto selectByMobileAndPwd(Criteria example);
	 
	 int updateUserMobile(Criteria parameter) ;
}