package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbWebRoleMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWebRole;
import com.liguo.hgl.service.TbWebRoleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbWebRoleServiceImpl implements TbWebRoleService {
    @Autowired
    private TbWebRoleMapper tbWebRoleMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWebRoleServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWebRoleMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWebRole selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWebRoleMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWebRole> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWebRoleMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWebRoleMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWebRole record) throws RuntimeException {
        try {
            return this.tbWebRoleMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWebRole record) throws RuntimeException {
        try {
            return this.tbWebRoleMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWebRole record) throws RuntimeException {
        try {
            return this.tbWebRoleMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}