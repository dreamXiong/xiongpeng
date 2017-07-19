/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common.message;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName RespGroupsCreateMessage.java<br>
 * @Language Java<br>
 * @date 2016-01-11<br>
 */
public class RespGroupsCreateMessage extends RespBaseMessage {

    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static class Group{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
