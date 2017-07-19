package com.liguo.hgl.proxydao.dao.impl;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAlbumSpace;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TbAlbumSpaceMapperImpl extends BaseMapperImpl<TbAlbumSpace> implements TbAlbumSpaceMapper {

    public TbAlbumSpaceMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbAlbumSpace record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbAlbumSpace record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.deleteByObject", parameter);
    }

    public int insert(TbAlbumSpace record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.insert", record);
    }

    public int insertSelective(TbAlbumSpace record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbAlbumSpace> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.selectByObject", parameter);
    }

    public TbAlbumSpace selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.selectByPrimaryKey", id);
        return obj != null ? (TbAlbumSpace)obj : null;
    }

    @Override
    public List<TbAlbumSpace> findImageNowNameBatch(Criteria example) throws RuntimeException {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.findImageNowNameBatch", example);
    }

    @Override
    public List<TbAlbumSpace> searchImage(Criteria example) throws RuntimeException {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.searchImage", example);
    }

    @Override
    public List<String> findImageNameOldAll(Criteria example) throws RuntimeException {
        
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper.findImageNameOldAll", example);
    }
}