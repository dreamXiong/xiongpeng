/*
MySQL Data Transfer
Source Host: localhost
Source Database: hgl
Target Host: localhost
Target Database: hgl
Date: 2016/5/3 11:31:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
CREATE TABLE `tb_user_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_type` int(10) NOT NULL COMMENT '用户类型',
  `exp_level` int(10) DEFAULT NULL COMMENT '等级',
  `exp` int(10) DEFAULT NULL COMMENT '产生经验值',
  `reg_province` int(10) DEFAULT NULL COMMENT '注册省',
  `reg_city` int(10) DEFAULT NULL COMMENT '注册市',
  `reg_country` int(10) DEFAULT NULL COMMENT '注册区县',
  `reg_street` int(10) DEFAULT NULL COMMENT '注册街道',
  `reg_address` varchar(200) DEFAULT NULL COMMENT '具体地址',
  `sales` int(10) DEFAULT NULL COMMENT '销售量',
  `image` varchar(50) DEFAULT NULL COMMENT '营业执照图片',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注,扩展字段',
  `auth_status` int(10) DEFAULT NULL COMMENT '审核状态',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_dt` bigint(16) NOT NULL COMMENT '创建时间',
  `birth_year` int(10) DEFAULT '0' COMMENT '出生年',
  `birth_month` int(10) DEFAULT '0' COMMENT '生出月',
  `birth_day` int(10) DEFAULT '0' COMMENT '生出日',
  `gender` int(10) DEFAULT '236' COMMENT 'Male:232,Female:234,unknow:236',
  `version` int(10) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户详细表';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_user_info` VALUES ('1', '1', null, null, '430000', '430100', '430111', '430111400', null, null, null, null, null, '1', '1', '1982', '4', '19', '234', '44');
