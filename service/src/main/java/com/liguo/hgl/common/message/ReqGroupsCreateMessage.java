/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common.message;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName ReqGroupsCreateMessage.java<br>
 * @Language Java<br>
 * @date 2016-01-11<br>
 */
public class ReqGroupsCreateMessage extends ReqBaseMessage {

    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static class Group{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
