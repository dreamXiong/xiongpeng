package com.liguo.hgl.proxydao.util;

import java.io.Serializable;

/**
 * XML读写抽象类
 * 第1级节点值为实体类名
 * 只支持2级XML节点
 * XML树<a><data>data</data></a>的data节点
 * 实体类为a，变量名为String data;
 */

public abstract class XmlDocEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String autoEntityName = this.getClass().getSimpleName();
    private final String autoPackageEntityName = this.getClass().getPackage().getName();

    // ----------------------------------------------------------------------------------------

    private static String getTableName(String tableName) {

        String pkname = XmlDocEntity.class.getPackage().getName();
        char chars[] = tableName.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        int posEnd = pkname.lastIndexOf(".");
        pkname = pkname.substring(0, posEnd) + ".bean.";
        pkname = pkname + (new String(chars));
        //pkname=pkname.substring(0,pkname.indexOf("base"))+"bean."+(new String(chars));
        return pkname;
    }

    public static XmlDocEntity getOrderEntityPrototype(String tableName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        return (XmlDocEntity) Class.forName(getTableName(tableName)).newInstance();

        //return (XmlDocEntity)Class.forName(XmlDocEntity.class.getPackage().getName() + "." + tableName).newInstance();
    }

    public boolean validate() {

        return true;
    }

    public String EntityName() {

        return autoEntityName;
    }

    public String PackageEntityName() {

        return autoPackageEntityName + "." + autoEntityName;
    }

}
