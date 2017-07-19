package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbUserInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbUserInfoMapperImpl extends BaseMapperImpl<TbUserInfo> implements TbUserInfoMapper {

    public TbUserInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbUserInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbUserInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbUserInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.insert", record);
    }

    public int insertSelective(TbUserInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbUserInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.selectByObject", parameter);
    }

    public TbUserInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbUserInfo)obj : null;
    }

    @Override
    public int updateEvaluateNumAvg(Criteria example) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.updateEvaluateNumAvg", example);
    }
    
    @Override
    public List<Map<String,Object>> findMasterByShopId(Criteria criteria, PageDto page) {
        
        Page<Map<String,Object>> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbUserInfoMapper.findMasterByShopId", criteria,new RowBounds(page.pageIndex,page.pageSize));
        int total = (int)selectList.getTotal();
        page.reset(total);
        return selectList;
    }
}