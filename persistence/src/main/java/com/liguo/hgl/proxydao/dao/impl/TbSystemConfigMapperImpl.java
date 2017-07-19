package com.liguo.hgl.proxydao.dao.impl;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbSystemConfigMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbSystemConfig;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public class TbSystemConfigMapperImpl extends BaseMapperImpl<TbSystemConfig> implements TbSystemConfigMapper {

    public TbSystemConfigMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbSystemConfig record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbSystemConfig record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.deleteByObject", parameter);
    }

    public int insert(TbSystemConfig record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.insert", record);
    }

    public int insertSelective(TbSystemConfig record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbSystemConfig> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.selectByObject", parameter);
    }
    
    @Override
    public List<TbSystemConfig> selectByObject(Criteria parameter,PageDto pgo) {
    	Page<TbSystemConfig> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.selectByObjectPage", parameter,new RowBounds(pgo.pageIndex, pgo.pageSize));
    	pgo.reset((int) selectList.getTotal());
        return selectList;
    }

    public TbSystemConfig selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.selectByPrimaryKey", id);
        return obj != null ? (TbSystemConfig)obj : null;
    }

	@Override
	public TbSystemConfig selectByConfigKey(String configKey) {
		Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbSystemConfigMapper.selectByConfigKey", configKey);
	    return obj != null ? (TbSystemConfig)obj : null;
	}
}