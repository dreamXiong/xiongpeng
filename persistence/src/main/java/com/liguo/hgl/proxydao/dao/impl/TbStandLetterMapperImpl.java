package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbStandLetterMapper;
import com.liguo.hgl.proxydao.dto.LetterActivityDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbStandLetter;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbStandLetterMapperImpl extends BaseMapperImpl<TbStandLetter> implements TbStandLetterMapper {

    public TbStandLetterMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbStandLetter record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbStandLetter record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.deleteByObject", parameter);
    }

    public int insert(TbStandLetter record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.insert", record);
    }

    public int insertSelective(TbStandLetter record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbStandLetter> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.selectByObject", parameter);
    }

    public TbStandLetter selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.selectByPrimaryKey", id);
        return obj != null ? (TbStandLetter)obj : null;
    }

	@Override
	public List<LetterActivityDto> selectLetters(Criteria parameter) {
		// TODO Auto-generated method stub
		 return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.selectLetters", parameter);
	}

	@Override
	public int insertList(List<TbStandLetter> record) {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.insertList", record);
	}

	@Override
	public int updateReservedState(Criteria parameter) {
		 return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbStandLetterMapper.updateReservedState", parameter);
	}
}