package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProvinceInfo;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TbProvinceInfoMapperImpl extends BaseMapperImpl<TbProvinceInfo> implements TbProvinceInfoMapper {

    public TbProvinceInfoMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbProvinceInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbProvinceInfo record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.deleteByObject", parameter);
    }

    public int insert(TbProvinceInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.insert", record);
    }

    public int insertSelective(TbProvinceInfo record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbProvinceInfo> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.selectByObject", parameter);
    }

    @Override
    public TbProvinceInfo selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.selectByPrimaryKey", id);
        return obj != null ? (TbProvinceInfo)obj : null;
    }

    @Override
    public String selectAddressByCode(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper.selectAddressByCode", parameter);
        return obj != null ? (String)obj : null;
    }
}