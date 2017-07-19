/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.model.Criteria;

/**
 * 业务逻辑抽象类型，所有service都应继承此抽象类<br>
 * 调用这可通过该方法，获取错误的具体描述。<br />
 * @author 王丹
 * @FileName AbstractService.java<br>
 * @Language Java<br>
 * @date 2014-11-3<br>
 */
public abstract class AbstractService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //    protected SqlSession session = null;

    protected Criteria criteria = new Criteria();

    protected BaseMapper baseMapper = new BaseMapperImpl();

    //    protected List<TransactionVo> transactionVoList = new ArrayList<TransactionVo>();

    protected String errorMessage;

    protected String getErrorMessage() {

        return errorMessage;
    }

    /**
     * 添加事务对象
     * @param methodType
     * @param object
     * @return
     */
    /*protected boolean addTransaction(TransactionVo.Type methodType, Object object) {
        TransactionVo transactionVo = new TransactionVo(methodType, object);
        return addTransaction(transactionVo);
    }

    protected boolean addTransaction(TransactionVo transactionVo) {
        return transactionVoList.add(transactionVo);
    }*/

    /**
     * 删除一个事务对象
     * @param methodType
     * @param object
     * @return
     */
    /*protected boolean removeTransaction(TransactionVo.Type methodType, Object object) {
        TransactionVo transactionVo = new TransactionVo(methodType, object);
        return removeTransaction(transactionVo);
    }*/

    /*protected boolean removeTransaction(TransactionVo transactionVo) {
        return transactionVoList.remove(transactionVo);
    }*/

    /**
     * 提交执行事务
     */
    /*protected boolean commitTransaction() {
        boolean ret = false;
        ret = baseMapper.executeTransaction(transactionVoList);
        if(!transactionVoList.isEmpty() && transactionVoList.size()>0){
            transactionVoList.clear();
        }
        return ret;
    }*/

}
