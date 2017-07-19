package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbAdminUserMapper;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbAdminUserMapperImpl extends BaseMapperImpl<TbAdminUser> implements TbAdminUserMapper {

    public TbAdminUserMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbAdminUser record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbAdminUser record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.deleteByObject", parameter);
    }

    public int insert(TbAdminUser record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.insert", record);
    }

    public int insertSelective(TbAdminUser record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbAdminUser> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.selectByObject", parameter);
    }
    
    @Override
    public List<TbAdminUser> selectByTbWarehouseId(Integer tbWIntegerId){
    	return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.selectByTbWarehouseId", tbWIntegerId);
    }

    public TbAdminUser selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.selectByPrimaryKey", id);
        return obj != null ? (TbAdminUser)obj : null;
    }

	@Override
	public TbAdminUserDto selectByUsernameAndPwd(Criteria parameter) {
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.selectByUsernameAndPwd", parameter);
	}

	@Override
	
	public List<TbAdminUserDto> selectByObject(Criteria criteria, PageDto pgo) {
		Page<TbAdminUserDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAdminUserMapper.selectByObjectPage", criteria, new RowBounds(pgo.pageIndex,pgo.pageSize));
		pgo.reset((int) selectList.getTotal());
		
		return selectList;
	}
}