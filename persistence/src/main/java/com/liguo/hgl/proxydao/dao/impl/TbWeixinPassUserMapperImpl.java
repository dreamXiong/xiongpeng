package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper;
import com.liguo.hgl.proxydao.exception.DataAlreadyModifyException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbWeixinPassUserMapperImpl extends BaseMapperImpl<TbWeixinPassUser> implements TbWeixinPassUserMapper {

    public TbWeixinPassUserMapperImpl() {

        super();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {

        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.deleteByPrimaryKey", id);
    }

    @Override
    public int updateByPrimaryKeySelective(TbWeixinPassUser record) throws DataAlreadyModifyException {

        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.updateByPrimaryKeySelective", record);
        if (ret > 0) {
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    @Override
    public int updateByPrimaryKey(TbWeixinPassUser record) throws DataAlreadyModifyException {

        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.updateByPrimaryKey", record);
        if (ret > 0) {
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    @Override
    public int deleteByObject(Criteria parameter) {

        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.deleteByObject", parameter);
    }

    @Override
    public int insert(TbWeixinPassUser record) {

        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.insert", record);
    }

    @Override
    public int insertSelective(TbWeixinPassUser record) {

        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.insertSelective", record);
    }

    @Override
    public int countByObject(Criteria parameter) {

        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbWeixinPassUser> selectByObject(Criteria parameter) {

        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.selectByObject", parameter);
    }

    @Override
    public int updateUserByPrimaryKeyArray(Criteria parameter) {

        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.updateByPrimaryKeyArray", parameter);
        if (ret > 0) {
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    @Override
    public int updateWinUserByWinGrade(Criteria parameter) {

        int ret = this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.updateWinUserByWinGrade", parameter);
        if (ret > 0) {
            return ret;
        }
        throw new DataAlreadyModifyException("数据实体已经被修改");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List<TbWeixinPassUser> selectRandUser(Criteria parameter,PageDto pgo) {

        Page<TbWeixinPassUser> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.selectRandUser", parameter,new RowBounds(pgo.pageIndex, pgo.pageSize));
        pgo.reset((int) selectList.getTotal());
        System.out.println("<<<<>>>>"+selectList.toString());
        //selectList.get
        return selectList;
    }

    @Override
    public Integer selectCanErnieCount(Criteria parameter) {

        return (Integer) this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.selectCanErnieCount", parameter);
    }

    @Override
    public List<TbWeixinPassUser> selectSignInUser(Integer dateCount) {

        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.selectSignInUser", dateCount);
    }

    @Override
    public TbWeixinPassUser selectByPrimaryKey(Integer id) {

        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbWeixinPassUserMapper.selectByPrimaryKey", id);
        return obj != null ? (TbWeixinPassUser) obj : null;
    }
}