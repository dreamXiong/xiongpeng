/*
MySQL Data Transfer
Source Host: localhost
Source Database: hgl
Target Host: localhost
Target Database: hgl
Date: 2016/3/25 16:02:06
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_province_info
-- ----------------------------
CREATE TABLE `tb_province_info` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_province_info` VALUES ('110000', '北京');
INSERT INTO `tb_province_info` VALUES ('120000', '天津');
INSERT INTO `tb_province_info` VALUES ('130000', '河北省');
INSERT INTO `tb_province_info` VALUES ('140000', '山西省');
INSERT INTO `tb_province_info` VALUES ('150000', '内蒙古自治区');
INSERT INTO `tb_province_info` VALUES ('210000', '辽宁省');
INSERT INTO `tb_province_info` VALUES ('220000', '吉林省');
INSERT INTO `tb_province_info` VALUES ('230000', '黑龙江省');
INSERT INTO `tb_province_info` VALUES ('310000', '上海');
INSERT INTO `tb_province_info` VALUES ('320000', '江苏省');
INSERT INTO `tb_province_info` VALUES ('330000', '浙江省');
INSERT INTO `tb_province_info` VALUES ('340000', '安徽省');
INSERT INTO `tb_province_info` VALUES ('350000', '福建省');
INSERT INTO `tb_province_info` VALUES ('360000', '江西省');
INSERT INTO `tb_province_info` VALUES ('370000', '山东省');
INSERT INTO `tb_province_info` VALUES ('410000', '河南省');
INSERT INTO `tb_province_info` VALUES ('420000', '湖北省');
INSERT INTO `tb_province_info` VALUES ('430000', '湖南省');
INSERT INTO `tb_province_info` VALUES ('440000', '广东省');
INSERT INTO `tb_province_info` VALUES ('450000', '广西壮族自治区');
INSERT INTO `tb_province_info` VALUES ('460000', '海南省');
INSERT INTO `tb_province_info` VALUES ('500000', '重庆');
INSERT INTO `tb_province_info` VALUES ('510000', '四川省');
INSERT INTO `tb_province_info` VALUES ('520000', '贵州省');
INSERT INTO `tb_province_info` VALUES ('530000', '云南省');
INSERT INTO `tb_province_info` VALUES ('540000', '西藏自治区');
INSERT INTO `tb_province_info` VALUES ('610000', '陕西省');
INSERT INTO `tb_province_info` VALUES ('620000', '甘肃省');
INSERT INTO `tb_province_info` VALUES ('630000', '青海省');
INSERT INTO `tb_province_info` VALUES ('640000', '宁夏回族自治区');
INSERT INTO `tb_province_info` VALUES ('650000', '新疆维吾尔自治区');
INSERT INTO `tb_province_info` VALUES ('710000', '台湾');
INSERT INTO `tb_province_info` VALUES ('810000', '香港特别行政区');
INSERT INTO `tb_province_info` VALUES ('820000', '澳门特别行政区');
INSERT INTO `tb_province_info` VALUES ('900000', '钓鱼岛');
