package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.WapOrderServiceDetailDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderServiceDetail;

public interface TbWapOrderServiceDetailMapper extends BaseMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByObject(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByObject(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbWapOrderServiceDetail record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapOrderServiceDetail record);

    /**
     * 根据条件查询记录集
     */
    List<TbWapOrderServiceDetail> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbWapOrderServiceDetail selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapOrderServiceDetail record);
    
    /**
     * 更新详情的状态
     * @param example
     * @return
     */
    int updateByServiceKey(Criteria example);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapOrderServiceDetail record);
    
    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsertSelective(List<TbWapOrderServiceDetail> list);
    
    /**
     * 根据条件查询库存
     * @param example
     * @return
     */
    List<WapOrderServiceDetailDto> selectInventByObject(Criteria example);
}