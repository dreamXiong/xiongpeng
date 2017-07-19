package com.liguo.hgl.proxydao.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 公用条件查询类
 */
public class Criteria {
    /**
     * 存放条件查询值
     */
    private Map<String, Object> parameter;

    /**
     * 是否相异
     */
    protected boolean distinct;

    /**
     * 排序字段
     */
    protected String orderByClause;
    
    /**
     * 倒序排序
     */
    protected String orderByClauseDesc;

    private Integer mysqlOffset;

    private Integer mysqlLength;

    protected Criteria(Criteria example) {
        this.orderByClause = example.orderByClause;
        this.orderByClauseDesc = example.orderByClauseDesc;
        this.parameter = example.parameter;
        this.distinct = example.distinct;
        this.mysqlLength = example.mysqlLength;
        this.mysqlOffset = example.mysqlOffset;
    }

    public Criteria() {
        parameter = new HashMap<String, Object>();
    }

    public void clear() {
        parameter.clear();
        orderByClause = null;
        orderByClauseDesc = null;
        distinct = false;
        this.mysqlOffset = null;
        this.mysqlLength = null;
    }

    /**
     * @param parameter 
	 *            查询的条件名称
	 * @param value
	 *            查询的值
     */
    public Criteria put(String parameter, Object value) {
        this.parameter.put(parameter, value);
        return (Criteria) this;
    }

    /**
     * @param orderByClause 
	 *            排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
    /**
     * @param orderByClauseDesc
     * 				倒序排序
     */
    public void setOrderByClauseDesc(String orderByClauseDesc) {
 		this.orderByClauseDesc = orderByClauseDesc;
 	}

    /**
     * @param distinct 
	 *            是否相异
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }


	@Deprecated
    public void setCondition(Map<String, Object> parameter) {
        this.parameter = parameter;
    }

    @Deprecated
    public Map<String, Object> getCondition() {
        return parameter;
    }

    /**
     * @param mysqlOffset 
	 *            指定返回记录行的偏移量<br>
	 *            mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15
     */
    public void setMysqlOffset(Integer mysqlOffset) {
        this.mysqlOffset = mysqlOffset;
    }

    /**
     * @param mysqlLength 
	 *            指定返回记录行的最大数目<br>
	 *            mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15
     */
    public void setMysqlLength(Integer mysqlLength) {
        this.mysqlLength = mysqlLength;
    }

	public String getOrderByClause() {
		return orderByClause;
	}

	public String getOrderByClauseDesc() {
		return orderByClauseDesc;
	}
    
    
}