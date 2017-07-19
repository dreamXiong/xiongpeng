package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbSaveInfoMapper;
import com.liguo.hgl.proxydao.dto.UserLetterDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbSaveInfo;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbSaveInfoMapperImpl extends BaseMapperImpl<TbSaveInfo> implements TbSaveInfoMapper {

    public TbSaveInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbSaveInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbSaveInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbSaveInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.insert", record);
    }

    public int insertSelective(TbSaveInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbSaveInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.selectByObject", parameter);
    }

    public TbSaveInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbSaveInfo)obj : null;
    }

    /*分页*/
	@Override
	public List<TbSaveInfo> selectByObjectPage(Criteria criteria,PageDto pgo) {
		Page<TbSaveInfo> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.selectByObjectPage", criteria, new RowBounds(pgo.pageIndex,pgo.pageSize));
		pgo.reset((int) selectList.getTotal());
		
		return selectList;
	}


	@Override
	public TbSaveInfo selectSaveInfo(Criteria example) {
		
		return this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.selectSaveInfo", example);
	}

	@Override
	public List<UserLetterDto> getUserLetter(Criteria parameter) {
		// TODO Auto-generated method stub
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.getUserLetter", parameter);
	}

	@Override
	public List<UserLetterDto> getShopLetter(Criteria parameter) {
		// TODO Auto-generated method stub
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbSaveInfoMapper.getShopLetter", parameter);
	}
}