/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common.message;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName ReqMenuAddconditionalMessage.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
public class ReqMenuAddconditionalMessage extends ReqBaseMessage {

    private List<Button> button;

    private Matchrule matchrule;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    public Matchrule getMatchrule() {
        return matchrule;
    }

    public void setMatchrule(Matchrule matchrule) {
        this.matchrule = matchrule;
    }

    public static class Button{
        private String name;
        private String type;
        private String key;
        private String url;
        private List<SubButton> subButton;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<SubButton> getSubButton() {
            return subButton;
        }

        public void setSubButton(List<SubButton> subButton) {
            this.subButton = subButton;
        }

        public static class SubButton{
            private String name;
            private String type;
            private String key;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class Matchrule{
        private String groupId;
        private String sex;
        private String country;
        private String province;
        private String city;
        private String clientPlatformType;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getClientPlatformType() {
            return clientPlatformType;
        }

        public void setClientPlatformType(String clientPlatformType) {
            this.clientPlatformType = clientPlatformType;
        }
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        ReqMenuAddconditionalMessage menuMessage = new ReqMenuAddconditionalMessage();
        List<Button> buttons = new ArrayList<Button>();
        Button button1 = new Button();
        button1.setName("今日歌曲");
        button1.setType("click");
        button1.setKey("V10001_TODAY_MUSIC");
        Button button2 = new Button();
        button2.setName("菜单");
        List<Button.SubButton> subButtons2 = new ArrayList<Button.SubButton>();
        Button.SubButton subButton2 = new Button.SubButton();
        subButton2.setName("搜索");
        subButton2.setKey("view");
        subButton2.setUrl("http://www.soso.com");
        subButtons2.add(subButton2);
        button2.setSubButton(subButtons2);
        buttons.add(button1);
        buttons.add(button2);
        menuMessage.setButton(buttons);
        System.out.println(menuMessage.toString());
    }
}
