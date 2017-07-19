package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbAdminRoleMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminRole;
import com.liguo.hgl.service.TbAdminRoleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbAdminRoleServiceImpl implements TbAdminRoleService {
    @Autowired
    private TbAdminRoleMapper tbAdminRoleMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbAdminRoleServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbAdminRoleMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbAdminRole selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAdminRoleMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbAdminRole> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbAdminRoleMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAdminRoleMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbAdminRole record) throws RuntimeException {
        try {
            return this.tbAdminRoleMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbAdminRole record) throws RuntimeException {
        try {
            return this.tbAdminRoleMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbAdminRole record) throws RuntimeException {
        try {
            return this.tbAdminRoleMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}