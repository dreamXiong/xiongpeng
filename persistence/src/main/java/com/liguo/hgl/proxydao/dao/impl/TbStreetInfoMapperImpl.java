package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbStreetInfoMapper;
import com.liguo.hgl.proxydao.dto.AddressDto;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbStreetInfo;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbStreetInfoMapperImpl extends BaseMapperImpl<TbStreetInfo> implements TbStreetInfoMapper {

    public TbStreetInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbStreetInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbStreetInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbStreetInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.insert", record);
    }

    public int insertSelective(TbStreetInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbStreetInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.selectByObject", parameter);
    }

    public TbStreetInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbStreetInfo)obj : null;
    }

	@Override
	public int countByCityParentid(Criteria parameter) {
		 Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.countByCityParentid", parameter);
	      return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
	}

	@Override
	public List<AddressDto> selectObjectByPage(Criteria example, PageDto page) {
		
		Page<AddressDto> selectList = (Page) this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.selectObjectByPage", example, new RowBounds(page.pageIndex,page.pageSize));
		page.reset((int)selectList.getTotal());
		
		return selectList;
	}

	@Override
	public List<AddressDto> selectAddressInfo(Criteria example) {
		
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbStreetInfoMapper.selectAddressInfo", example);
	}
}