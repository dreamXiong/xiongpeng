package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.dto.TbwebUserShopDto;
import com.liguo.hgl.proxydao.dto.UserRecommendedDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWebUserService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWebUser selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWebUser> selectByObject(Criteria example) throws RuntimeException;


    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWebUser record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWebUser record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWebUser record) throws RuntimeException;
    
    /*wap端用户注册*/
    int insert(TbWebUser record) throws RuntimeException;
    
    /**
     * 通过用户名与密码获取用户
     */
    WebUserDto selectByUsernameAndPwd(String userName,String pwd) throws RuntimeException;

    /**
     * 校验用户名是否存在
     * @param username
     * @return
     * @author zss
     */
	Map<String, Object> checkName(String username);
	/**
	 * 校验手机号码是否存在
	 * @param mobile
	 * @return
	 * @author zss
	 */
	Map<String, Object> checkMobile(String mobile);
	
	/**
	 * 注册方法
	 * @param param
	 * @return
	 * @author zss
	 */
	int register(Map<String, Object> param);
	/**
	 * 厂家注册
	 * @param param
	 * @return
	 * @author zss
	 */
	int cjregister(Map<String, Object> param);
	/**
	 * 经销商注册
	 * @param param
	 * @return
	 * @author zss
	 */
	int jxregister(Map<String, Object> param);
	/**
	 * 后台查询所有用户
	 * @param criteria
	 * @param page
	 * @return
	 * @author zss
	 */
	List<TbwebUserShopDto> getWebUserList(Criteria criteria, PageDto page);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @author zss
	 */
	TbwebUserShopDto getUserShop(Integer id);
	/**
	 * 修改
	 * @param param
	 * @return
	 * @author zss
	 */
	int updateUserShop(Map<String, Object> param,int id);
	/**
	 * 供应商（厂家）后台列表
	 * @param criteria
	 * @param page
	 * @return
	 * @author zss
	 */
	List<TbwebUserShopDto> getSupplierWebUserList(Criteria criteria,
			PageDto page);

	ShopWebUserDto getShopWebUser(Integer id);
	/**
	 * 改变用户状态
	 * @param id
	 * @return
	 * @author zss
	 * @param state 
	 * @param userId 
	 * @param checkmesg 
	 */
	Map<String, Object> updateState(Integer id, Integer userId, Integer state,String checkmesg);
	/**
	 * 经销商后台列表
	 * @param criteria
	 * @param page
	 * @return
	 * @author zss
	 */
	List<TbwebUserShopDto> getDealersUserList(Criteria criteria, PageDto page);
	
	/*分页*/
	List<WebUserDto> selectByObjectPage(Criteria criteria,PageDto page);
	/**
	 * 用户是否审核通过
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	Map<String,Object> isApproved(Integer loginUserId);
	/**
	 * 经销商，厂家重新提交用户资料
	 * @param param
	 * @param shopId
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	int updateShopByUser(Map<String, Object> param, Integer shopId,
			Integer loginUserId);
	/**
	 * 验证此分类下此品牌名称是否存在
	 * @param productTypeId
	 * @param brandName
	 * @return
	 * @author zss
	 */
	Map<String, Object> checkBranName(Integer productTypeId, String brandName); 
	
    /**
     * 通过openId登录
     */
	WebUserDto selectByOpenId(String openId) throws RuntimeException;
    
    /*Wap端个人注册*/
    int registerCustomer(TbWebUser webUser,String headimgurl);  	
    
    
    /*Wap端师傅*/
    int registerWorker(WebUserDto webUser,HttpServletRequest request);
    
    /**
     * 我的推荐列表
     * @param recommendCode
     * @return
     * @author zss
     */
    List<UserRecommendedDto> selectRecommended(String recommendCode);
 
    boolean updateUserOpenId(WebUserDto webUser,Integer id) throws RuntimeException;
    
    /**
     * Wap端修改密码
     * @param webUser  用户信息
     * @param newPassword  新密码
     * @return
     * @throws RuntimeException
     * @author zst
     */
    int updatePassword(TbWebUser webUser,String newPassword) throws RuntimeException;
    
    /**
     * 根据推荐码查找用户
     * @param criteria
     * @return
     * @throws RuntimeException
     * @author zst
     */
    TbWebUser selectUserByRecommendCode(Criteria criteria) throws RuntimeException;
    
    /**
     * 判定推荐码是否存在
     * @param criteria
     * @return
     * @throws RuntimeException
     * @author zst
     */
    TbWebUser selectUserByLogoCode(Criteria criteria) throws RuntimeException;
    /**
     * 验证推荐码是否存在
     * @param recommendCode
     * @return
     * @author zss
     */
	Map<String, Object> checkRecommedCode(String recommendCode);
    
    
	List<WebUserDto> selectUserGroupPage(Criteria criteria,PageDto page);
	
    List<TbWebUser> selectWebUserByGroupId(Criteria example);
    
    List<WebUserDto> selectUserGroup(Criteria example,PageDto page);
    
    int updateUserOpenId(Integer id,String openId) throws RuntimeException;
    
    int updateAutoLoginFlag(Integer id,Integer autoLoginFlagOld,Integer autoLoginFlag,String openId) throws RuntimeException;
    
    WebUserDto selectByOpenId(String openId,Integer autoLoginFlag) throws RuntimeException;

    /**
     * 查询店铺下面所有用户，收藏的
     * @param shopId
     * @return
     * @author zss
     * @param userId 
     */
	List<TbWebUser> selectBySaveShop(Integer shopId, Integer userId);

	TbWebUser selectByUserName(Criteria parameter) ;
	
	int updateUserPassword(Criteria parameter);
	
	 int updateUserOpenIdNull(String openId) ;
	 /**
	  * 查询关注此店铺的所有用户
	  * @param shopId
	  * @return
	  * @author zss
	  */
	List<TbWebUser> selectBySaveShopUser(Integer shopId);
	
	int updateUserAutoLoginFlag(Integer id,Integer autoLoginFlag);
	
	TbWebUser selectWebUserByShopId(Integer shopId);
	
	WebUserDto selectByMobileAndPwd(String mobile,String password);
	
	int updateUserMobile(Criteria parameter);
	
	int doUpdateWorker(WebUserDto webUser,HttpServletRequest request);
	
	/**
	 * 用户扫收藏店铺二维码时，如果是绑定惠绘利店铺的用户，生成推荐信息
	 * @param id 推荐人的用户ID
	 * @param userType  推荐人的用户角色
	 * @param code 被推荐的推荐码--对方推荐码
	 * @author zk
	 */
	public int insertRecommendedInfo(String code,String shopId,TbWebUser loginUser);
}