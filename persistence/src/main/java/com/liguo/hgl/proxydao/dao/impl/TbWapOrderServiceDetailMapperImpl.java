package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDetailDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderServiceDetail;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbWapOrderServiceDetailMapperImpl extends BaseMapperImpl<TbWapOrderServiceDetail> implements TbWapOrderServiceDetailMapper {

    public TbWapOrderServiceDetailMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbWapOrderServiceDetail record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbWapOrderServiceDetail record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.deleteByObject", parameter);
    }

    public int insert(TbWapOrderServiceDetail record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.insert", record);
    }

    public int insertSelective(TbWapOrderServiceDetail record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWapOrderServiceDetail> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.selectByObject", parameter);
    }

    public TbWapOrderServiceDetail selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWapOrderServiceDetail)obj : null;
    }

	@Override
	public int batchInsertSelective(List<TbWapOrderServiceDetail> list) {
		 return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.batchInsertSelective", list);
	}

	@Override
	public List<WapOrderServiceDetailDto> selectInventByObject(Criteria example) {
		
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.selectInventByObject", example);
	}

	@Override
	public int updateByServiceKey(Criteria example) {
		return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWapOrderServiceDetailMapper.updateByServiceKey", example);
	}
}