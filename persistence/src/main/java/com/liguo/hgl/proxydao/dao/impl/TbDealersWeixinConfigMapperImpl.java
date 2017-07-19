package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper;
import com.liguo.hgl.proxydao.dto.DealersWeixinConfigDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDealersWeixinConfig;
import com.liguo.hgl.proxydao.page.PageDto;

@Repository
public class TbDealersWeixinConfigMapperImpl extends BaseMapperImpl<TbDealersWeixinConfig> implements TbDealersWeixinConfigMapper {

    public TbDealersWeixinConfigMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbDealersWeixinConfig record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbDealersWeixinConfig record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.deleteByObject", parameter);
    }

    public int insert(TbDealersWeixinConfig record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.insert", record);
    }

    public int insertSelective(TbDealersWeixinConfig record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<DealersWeixinConfigDto> selectByObject(Criteria parameter,PageDto pgo) {
        Page<DealersWeixinConfigDto> selectList = (Page)this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.selectByObject", parameter,new RowBounds(pgo.pageIndex, pgo.pageSize));
    	pgo.reset((int) selectList.getTotal());
        return selectList;
    }

    public TbDealersWeixinConfig selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.selectByPrimaryKey", id);
        return obj != null ? (TbDealersWeixinConfig)obj : null;
    }

	@Override
	public TbDealersWeixinConfig selectByShopId(Criteria example) {
		Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper.selectByShopId", example);
        return obj != null ? (TbDealersWeixinConfig)obj : null;
	}
}