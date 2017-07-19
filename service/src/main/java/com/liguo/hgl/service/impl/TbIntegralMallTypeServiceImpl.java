package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbIntegralMallTypeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralMallType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbIntegralMallTypeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbIntegralMallTypeServiceImpl implements TbIntegralMallTypeService {
    @Autowired
    private TbIntegralMallTypeMapper tbIntegralMallTypeMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbIntegralMallTypeServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbIntegralMallTypeMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbIntegralMallType selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralMallTypeMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbIntegralMallType> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbIntegralMallTypeMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralMallTypeMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbIntegralMallType record) throws RuntimeException {
        try {
            return this.tbIntegralMallTypeMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbIntegralMallType record) throws RuntimeException {
        try {
            return this.tbIntegralMallTypeMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbIntegralMallType record) throws RuntimeException {
        try {
            return this.tbIntegralMallTypeMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbIntegralMallType> selectByObject(Criteria example, PageDto pgo) throws RuntimeException {
		  try {
            return this.tbIntegralMallTypeMapper.selectByObject(example,pgo);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
	}

	@Override
	public int insert(TbIntegralMallType record) throws RuntimeException {
		 try {
            return this.tbIntegralMallTypeMapper.insert(record);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
	}
}