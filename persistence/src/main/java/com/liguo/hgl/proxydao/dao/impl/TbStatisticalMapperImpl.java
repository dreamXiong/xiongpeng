package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbStatisticalMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbStatistical;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbStatisticalMapperImpl extends BaseMapperImpl<TbStatistical> implements TbStatisticalMapper {

    public TbStatisticalMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbStatisticalMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbStatistical record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbStatisticalMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbStatistical record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbStatisticalMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbStatisticalMapper.deleteByObject", parameter);
    }

    public int insert(TbStatistical record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbStatisticalMapper.insert", record);
    }

    public int insertSelective(TbStatistical record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbStatisticalMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbStatisticalMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbStatistical> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbStatisticalMapper.selectByObject", parameter);
    }

    public TbStatistical selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbStatisticalMapper.selectByPrimaryKey", id);
        return obj != null ? (TbStatistical)obj : null;
    }

	@Override
	public void addStatistical() {
		String ip=null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
}