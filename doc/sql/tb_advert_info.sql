/*
MySQL Data Transfer
Source Host: localhost
Source Database: hgl
Target Host: localhost
Target Database: hgl
Date: 2016/4/5 9:20:18
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_advert_info
-- ----------------------------
CREATE TABLE `tb_advert_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自动增长的id',
  `advert_name` varchar(200) NOT NULL COMMENT '告广名称',
  `advert_position` int(20) DEFAULT NULL COMMENT '广告版位(首页轮播,系统公告,热门商家)',
  `advert_type` int(6) DEFAULT NULL COMMENT '广告类型(图片,文字)',
  `advert_status` int(6) DEFAULT NULL COMMENT '告广状态(显示,隐藏)',
  `createdDateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '告广添加时间，系统在添加数据时自动产生',
  `advert_sort` int(10) DEFAULT NULL COMMENT '排序',
  `advert_address` varchar(200) DEFAULT NULL COMMENT '链接地址',
  `clicked_times` int(10) DEFAULT NULL COMMENT '击点次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_advert_info` VALUES ('12', '客户端新人95元红包', '3', '1', '1', '2016-04-01 10:00:44', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('14', 'Apple新品火爆预约', '1', '1', '1', '2016-03-31 15:18:22', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('16', '雾霾净化器', '3', '2', '1', '2016-04-01 10:03:42', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('17', '', '1', '1', '1', '2016-04-01 14:38:41', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('18', 'adffggg', '1', '1', '1', '2016-04-01 14:46:52', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('19', '', '1', '1', '1', '2016-04-01 14:49:26', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('20', '', '1', '1', '1', '2016-04-01 14:50:36', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('21', '', '1', '1', '1', '2016-04-01 14:51:35', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('22', '', '1', '1', '1', '2016-04-01 14:52:35', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('23', '', '1', '1', '1', '2016-04-01 14:54:36', null, null, null);
INSERT INTO `tb_advert_info` VALUES ('24', 'aaaaa', '1', '1', '1', '2016-04-01 15:48:39', null, 'ffff', null);
INSERT INTO `tb_advert_info` VALUES ('25', 'test', '1', '1', '1', '2016-04-01 15:53:11', null, 'gggggg', null);
INSERT INTO `tb_advert_info` VALUES ('26', 'hhh', null, null, null, '2016-04-01 16:14:15', '5', 'hh', null);
INSERT INTO `tb_advert_info` VALUES ('27', 'hhh', '1', '1', '1', '2016-04-01 18:30:48', '5', 'hh', '15');
INSERT INTO `tb_advert_info` VALUES ('28', 'hhhhhhhh', '1', '1', '1', '2016-04-01 18:00:11', '1', 'hhhhh', '4');
INSERT INTO `tb_advert_info` VALUES ('29', 'hhhhhhhhh', null, null, null, '2016-04-01 16:19:50', '1', '1111', null);
INSERT INTO `tb_advert_info` VALUES ('30', 'hhhhhhhh', null, null, null, '2016-04-01 16:21:40', '1', 'jjjjj', null);
INSERT INTO `tb_advert_info` VALUES ('31', 'bbbbbbbbbbbb', null, null, null, '2016-04-01 16:23:28', '1', 'bbbbb', null);
INSERT INTO `tb_advert_info` VALUES ('32', 'bbbbbbb', '1', '1', '1', '2016-04-01 16:24:34', '22', 'hhh', null);
INSERT INTO `tb_advert_info` VALUES ('33', '', '1', '1', '1', '2016-04-01 16:35:43', null, '', null);
INSERT INTO `tb_advert_info` VALUES ('34', '', '1', '1', '1', '2016-04-01 16:35:51', null, '', null);
INSERT INTO `tb_advert_info` VALUES ('35', '', '1', '1', '1', '2016-04-01 16:42:10', null, '', null);
INSERT INTO `tb_advert_info` VALUES ('36', '', '1', '1', '1', '2016-04-01 16:42:37', null, '', null);
INSERT INTO `tb_advert_info` VALUES ('37', '', '1', '1', '1', '2016-04-01 16:43:17', null, '', null);
INSERT INTO `tb_advert_info` VALUES ('38', 'ffffff', '1', '1', '1', '2016-04-01 16:51:15', '1', 'hhhhh', null);
INSERT INTO `tb_advert_info` VALUES ('39', 'ffffff', '1', '1', '1', '2016-04-01 16:51:45', '22', 'hhhhh', null);
INSERT INTO `tb_advert_info` VALUES ('40', 'hhhhhhhh', '1', '1', '1', '2016-04-01 16:53:59', '4', 'ggggggggg', null);
