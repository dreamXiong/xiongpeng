package com.liguo.hgl.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbBrandMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

@Service
@Scope(value="prototype")
public class TbBrandServiceImpl implements TbBrandService {
    @Autowired
    private TbBrandMapper tbBrandMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbBrandServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbBrandMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbBrand selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbBrandMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<TbBrand> selectByTbBrand(TbBrand tbBrand) throws RuntimeException {
    	Criteria example = new Criteria();
    	example.put("name", tbBrand.getName());
    	example.put("producttypeId", tbBrand.getProductTypeId());
        try {
            return this.tbBrandMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<TbBrand> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbBrandMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<TbBrand> selectByObject(Criteria example,PageDto pgo) throws RuntimeException {
        try {
            return this.tbBrandMapper.selectByObject(example,pgo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
  /*  public List<TbBrand> selectByObjectPage(Criteria example,PageDto pgo) throws RuntimeException {
        try {
            return this.tbBrandMapper.selectByObject(example,pgo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
    
   /* public DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException {
        try { 
			int totalRecords = countByObject(example); 
			Page pg = PageUtil.createPage(page, lines, totalRecords); 
			example.setMysqlOffset(pg.getBeginIndex()); 
			example.setMysqlLength(pg.getEveryPage()); 
			DataPackage result = new DataPackage(); 
			List<TbBrand> ls = selectByObject(example); 
			result.setTotalCount(ls.size()); 
			result.setPageindex(page); 
			result.setDatas(ls); 
			Result rs = new Result(); 
			rs.setPage(pg); 
			rs.setContent(ls); 
			result.setResult(rs); 
			return result; 
		} catch (Exception e) { 
			throw new RuntimeException(e); 
		}
    }
*/@Override
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbBrandMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int updateByPrimaryKeySelective(TbBrand record) throws RuntimeException {
        try {
            return this.tbBrandMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int updateByPrimaryKey(TbBrand record) throws RuntimeException {
        try {
            return this.tbBrandMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int insertSelective(TbBrand record) throws RuntimeException {
        try {
            return this.tbBrandMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public int insert(TbBrand record) throws RuntimeException {
		 try {
	            return this.tbBrandMapper.insert(record);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}

	@Override
	public int saveBrandByUser(Map<String, Object> param, Integer userId) {
		int count =0;
		TbBrand brand = new TbBrand();
		brand.setCreateBy(userId);
		brand.setType(HglContants.BRAND_DEFAULT_TYPE);
		brand.setProductTypeId(Integer.parseInt(param.get("producttypeId").toString()));
		brand.setProductTypeName(param.get("producttypeName").toString());
		brand.setName(param.get("name").toString());
		brand.setUrl(param.get("url").toString());
		brand.setManufacturerId(HglContants.BRAND_DEFAULT_MANUFACTURERID);
		brand.setManufacturerName(param.get("manufacturerName").toString());
		brand.setRemark(param.get("remark").toString());
		brand.setState(HglContants.BRAND_STATE_ISSHOW);
		String logoName = param.get("logoName").toString().trim();
		brand.setLogoName(param.get("logoName").toString());
		tbBrandMapper.insert(brand);
		int bid=brand.getId();
		if(!StringUtils.isEmpty(logoName)){
			TbBrand tbBrand = new TbBrand();
			String newName = StringUtil.changeFileName(String.valueOf(bid),logoName);
			try {
				ImageUtil.changRegisterPathAndBrandLogoName(logoName, newName, bid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tbBrand.setLogoName(newName);
			tbBrand.setId(bid);
			count =tbBrandMapper.updateByPrimaryKeySelective(tbBrand);
		}
		return count;
	}
	

    @Override
    public List<ProductImport> findBrandIdAll(Criteria example) {
        
        return this.tbBrandMapper.findBrandIdAll(example);
    }
}