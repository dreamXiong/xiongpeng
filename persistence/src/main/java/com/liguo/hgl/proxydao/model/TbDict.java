package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbDict implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 键
     */
    private String dictkey;

    /**
     * 值
     */
    private String value;

    /**
     * 类型
     */
    private String type;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 键
     */
    public String getDictkey() {
        return dictkey;
    }

    /**
     * @param dictkey 
	 *            键
     */
    public void setDictkey(String dictkey) {
        this.dictkey = dictkey == null ? null : dictkey.trim();
    }

    /**
     * @return 值
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value 
	 *            值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}