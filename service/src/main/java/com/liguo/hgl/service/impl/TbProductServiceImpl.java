package com.liguo.hgl.service.impl;

import java.util.List;

import org.apache.ibatis.ognl.SetPropertyAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbProductMapper;
import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductInfoDto;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductTestDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

@Service
@Scope(value="prototype")
public class TbProductServiceImpl implements TbProductService {
    @Autowired
    private TbProductMapper tbProductMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbProductServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbProductMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbProduct selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbProductMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbProduct> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbProductMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbProductMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbProduct record) throws RuntimeException {
        try {
            return this.tbProductMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbProduct record) throws RuntimeException {
        try {
            return this.tbProductMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbProduct record) throws RuntimeException {
        try {
            return this.tbProductMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ProductInfoDto selectUpdateInfo(Integer id){
    	 try {
             return this.tbProductMapper.selectUpdateInfo(id);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }
    public List<ProductInfoDto> selectInfoListByName(String name, PageDto page){
    	Criteria criteria = new Criteria();
    	criteria.put("name", name);
    	return tbProductMapper.selectInfoListByName(criteria,page);
    }
    public int insertProduct(TbProduct record) throws RuntimeException {
        try {
        	this.tbProductMapper.insertSelective(record);
        	Integer id = record.getId();
        	if(record.getPimgOne() !=null && !"".equals(record.getPimgOne())){
        		String newName = StringUtil.changeFileName(record.getPimgOne().split("_")[0],record.getPimgOne());
        		ImageUtil.changImageName(record.getPimgOne(), newName, id);
        		record.setPimgOne(newName);
        	}
        	if(record.getPimgTwo() !=null && !"".equals(record.getPimgTwo())){
        		String newName = StringUtil.changeFileName(record.getPimgTwo().split("_")[0],record.getPimgTwo());
        		ImageUtil.changImageName(record.getPimgTwo(), newName, id);
        		record.setPimgTwo(newName);
        	}
        	if(record.getPimgThree() !=null && !"".equals(record.getPimgThree())){
        		String newName = StringUtil.changeFileName(record.getPimgThree().split("_")[0],record.getPimgThree());
        		ImageUtil.changImageName(record.getPimgThree(), newName, id);
        		record.setPimgThree(newName);
        	}
        	if(record.getDimgOne() !=null && !"".equals(record.getDimgOne())){
        		String newName = StringUtil.changeFileName(record.getDimgOne().split("_")[0],record.getDimgOne());
        		ImageUtil.changImageName(record.getDimgOne(), newName, id);
        		record.setDimgOne(newName);
        	}
        	if(record.getDimgTwo() !=null && !"".equals(record.getDimgTwo())){
        		String newName = StringUtil.changeFileName(record.getDimgTwo().split("_")[0],record.getDimgTwo());
        		ImageUtil.changImageName(record.getDimgTwo(), newName, id);
        		record.setDimgTwo(newName);
        	}
        	if(record.getDimgThree() !=null && !"".equals(record.getDimgThree())){
        		String newName = StringUtil.changeFileName(record.getDimgThree().split("_")[0],record.getDimgThree());
        		ImageUtil.changImageName(record.getDimgThree(), newName, id);
        		record.setDimgThree(newName);
        	}
        	record.setVersion(0);
        	updateByPrimaryKey(record);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public int updateProduct(TbProduct record) throws RuntimeException {
    	Integer id = record.getId();
    	TbProduct tbProduct = selectByPrimaryKey(record.getId());
        try {
        
        	if(record.getPimgOne() !=null && !"".equals(record.getPimgOne()) && !tbProduct.getPimgOne().equals(record.getPimgOne())){
        		String newName = StringUtil.changeFileName(record.getPimgOne().split("_")[0],record.getPimgOne());
        		ImageUtil.changImageName(record.getPimgOne(), newName, id);
        		record.setPimgOne(newName);
        	}
        	if(record.getPimgTwo() !=null && !"".equals(record.getPimgTwo())&& !tbProduct.getPimgTwo().equals(record.getPimgTwo())){
        		String newName = StringUtil.changeFileName(record.getPimgTwo().split("_")[0],record.getPimgTwo());
        		ImageUtil.changImageName(record.getPimgTwo(), newName, id);
        		record.setPimgTwo(newName);
        	}
        	if(record.getPimgThree() !=null && !"".equals(record.getPimgThree())&& !tbProduct.getPimgThree().equals(record.getPimgThree())){
        		String newName = StringUtil.changeFileName(record.getPimgThree().split("_")[0],record.getPimgThree());
        		ImageUtil.changImageName(record.getPimgThree(), newName, id);
        		record.setPimgThree(newName);
        	}
        	if(record.getDimgOne() !=null && !"".equals(record.getDimgOne())&& !tbProduct.getDimgOne().equals(record.getDimgOne())){
        		String newName = StringUtil.changeFileName(record.getDimgOne().split("_")[0],record.getDimgOne());
        		ImageUtil.changImageName(record.getDimgOne(), newName, id);
        		record.setDimgOne(newName);
        	}
        	if(record.getDimgTwo() !=null && !"".equals(record.getDimgTwo())&& !tbProduct.getDimgTwo().equals(record.getDimgTwo())){
        		String newName = StringUtil.changeFileName(record.getDimgTwo().split("_")[0],record.getDimgTwo());
        		ImageUtil.changImageName(record.getDimgTwo(), newName, id);
        		record.setDimgTwo(newName);
        	}
        	if(record.getDimgThree() !=null && !"".equals(record.getDimgThree())&& !tbProduct.getDimgThree().equals(record.getDimgThree())){
        		String newName = StringUtil.changeFileName(record.getDimgThree().split("_")[0],record.getDimgThree());
        		ImageUtil.changImageName(record.getDimgThree(), newName, id);
        		record.setDimgThree(newName);
        	}
        	updateByPrimaryKeySelective(record);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	@Override
	public List<TbProductDto> selectByCriteria(Criteria criteria, PageDto pgo)
			throws RuntimeException {
		try {
            return this.tbProductMapper.selectByCriteria(criteria,pgo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	@Override
	public List<TbProductDto> selectByCriteria(Criteria criteria)
			throws RuntimeException {
		try {
			return this.tbProductMapper.selectByCriteria(criteria);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public TbProductDto selectProductDtoByPrimaryKey(Integer id)
			throws RuntimeException {
		try {
            return this.tbProductMapper.selectProductDtoByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	/*@Override
	public List<TbProductTestDto> selectTest(){
		 return this.tbProductMapper.selectTest();
	}*/
	@Override
    public Integer selectShopIdByPId(Integer productId){
    	return this.tbProductMapper.selectShopIdByPId(productId);
    }
}