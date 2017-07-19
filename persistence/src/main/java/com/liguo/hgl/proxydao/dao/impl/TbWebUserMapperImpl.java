package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.TbwebUserShopDto;
import com.liguo.hgl.proxydao.dto.UserRecommendedDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbWebUserMapperImpl extends BaseMapperImpl<TbWebUser> implements TbWebUserMapper {

    public TbWebUserMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWebUserMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWebUser record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebUserMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWebUser record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebUserMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWebUserMapper.deleteByObject", parameter);
    }

    public int insert(TbWebUser record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWebUserMapper.insert", record);
    }

    public int insertSelective(TbWebUser record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWebUserMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebUserMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWebUser> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectByObject", parameter);
    }

    public TbWebUser selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWebUser)obj : null;
    }

	@Override
	public WebUserDto selectByUsernameAndPwd(Criteria parameter) {
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectByUsernameAndPwd", parameter);
	}

	@Override
	public List<TbwebUserShopDto> selectByCriteria(Criteria criteria, PageDto page) {
		Page<TbwebUserShopDto> selectList =(Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectByCriteria", criteria,new RowBounds(page.pageIndex, page.pageSize));
		int total=(int) selectList.getTotal();
		page.reset(total);
		return selectList;
	}

	@Override
	public TbwebUserShopDto selectUserShopById(Integer id) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectUserShopById", id);
	     return obj != null ? (TbwebUserShopDto)obj : null;
	}

	@Override
	public List<WebUserDto> selectByObjectPage(Criteria criteria, PageDto page) {
		
		Page<WebUserDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectByObjectPage", criteria,new RowBounds(page.pageIndex,page.pageSize));
		int total = (int)selectList.getTotal();
		page.reset(total);
		return selectList;
	}

	@Override
	public List<WebUserDto> selectUserGroupPage(Criteria criteria, PageDto page) {
		
		Page<WebUserDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectUserGroupPage", criteria,new RowBounds(page.pageIndex,page.pageSize));
		int total = (int)selectList.getTotal();
		page.reset(total);
		return selectList;
	}
	
	@Override
	public TbWebUser selectWebUser(int shopId) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectWebUser", shopId);
	     return obj != null ? (TbWebUser)obj : null;
	}

	@Override
	public List<WebUserDto> selectByOpenIdList(Criteria example) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectByOpenIdList", example);
	}

	@Override
	public List<UserRecommendedDto> selectRecommended(Criteria parameter) {
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectRecommended", parameter);
    
	}

	@Override
	public int updateUserOpenId(Criteria parameter) throws RuntimeException {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebUserMapper.updateUserOpenId", parameter);
	}
	
	@Override
	public int updateUserOpenIdNull(Criteria parameter) throws RuntimeException {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebUserMapper.updateUserOpenIdNull", parameter);
	}

	@Override
	public TbWebUser selectUserByRecommendCode(Criteria parameter)
			throws RuntimeException {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectUserByRecommendCode", parameter);
	}

	@Override
	public TbWebUser selectUserByLogoCode(Criteria parameter)
			throws RuntimeException {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectUserByLogoCode", parameter);
	}
	
	@Override
	public List<TbWebUser> selectWebUserByGroupId(Criteria parameter){
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectWebUserByGroupId", parameter);
	}

	@Override
	public List<TbWebUser> selectUserByGroup(Criteria parameter) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectUserByGroup", parameter);
		
	}
	

	@Override
	public List<TbWebUser> selectBySaveShop(Criteria parameter) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectBySaveShop", parameter);
	}
	
	@Override
	public TbWebUser selectByUserName(Criteria parameter) {
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectByUserName", parameter);
	}
	
	@Override
	public int updateUserPassword(Criteria parameter) throws RuntimeException {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebUserMapper.updateUserPassword", parameter);
	}
	
	@Override
	public int updateUserAutoLoginFlag(Criteria parameter) throws RuntimeException {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebUserMapper.updateUserAutoLoginFlag", parameter);
	}

	@Override
	public WebUserDto selectByMobileAndPwd(Criteria example) {
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWebUserMapper.selectByMobileAndPwd", example);
	}
	
	@Override
	public int updateUserMobile(Criteria parameter) throws RuntimeException {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebUserMapper.updateUserMobile", parameter);
	}

	@Override
	public int updateUserAutoLoginFlagList(Criteria parameter) {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWebUserMapper.updateUserAutoLoginFlagList", parameter);
	}
}