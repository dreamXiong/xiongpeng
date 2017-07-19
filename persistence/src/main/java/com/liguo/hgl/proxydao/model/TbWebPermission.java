package com.liguo.hgl.proxydao.model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class TbWebPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限url
     */
    private String actionUrl;

    /**
     * 备注,扩展字段
     */
    private String remark;

    private Integer version;
    
    /**
     * 父节点ID,如果是父节点ID是自己
     */
    private Integer parentNode;

    /**
     * 子节点序号
     */
    private Integer childNode;
    
    /**
     * 子菜单
     */
    private List<TbWebPermission> childs = new CopyOnWriteArrayList<TbWebPermission>();

    /**
     * 锁标识
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * @return 权限id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            权限id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            权限名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 权限url
     */
    public String getActionUrl() {
        return actionUrl;
    }

    /**
     * @param actionUrl 
	 *            权限url
     */
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl == null ? null : actionUrl.trim();
    }

    /**
     * @return 备注,扩展字段
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注,扩展字段
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * @return 父节点ID,如果是父节点ID是自己
     */
    public Integer getParentNode() {
        return parentNode;
    }

    /**
     * @param parentNode 
     *            父节点ID,如果是父节点ID是自己
     */
    public void setParentNode(Integer parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * @return 子节点序号
     */
    public Integer getChildNode() {
        return childNode;
    }

    /**
     * @param childNode 
     *            子节点序号
     */
    public void setChildNode(Integer childNode) {
        this.childNode = childNode;
    }
    
    public List<TbWebPermission> getChilds() {
        return childs;
    }
    /**
     * 增加子菜单
     * @param child 子菜单
     */
    public void addChild(TbWebPermission child) {
        lock.lock();
        try {
            int index = childs.indexOf(child);
            if (index > -1) {
                childs.set(index, child);
            }
            else {
                childs.add(child);
            }
        }
        finally {
            lock.unlock();
        }

    }
}