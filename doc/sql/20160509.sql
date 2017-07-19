/*
MySQL Data Transfer
Source Host: 192.168.0.133
Source Database: hgl
Target Host: 192.168.0.133
Target Database: hgl
Date: 2016/5/9 17:30:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
CREATE TABLE `tb_address` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `recipient` varchar(100) DEFAULT NULL COMMENT '收货人',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `province_name` varchar(100) DEFAULT '0' COMMENT 'ç‰ˆæœ¬',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `area` varchar(100) DEFAULT NULL COMMENT '区',
  `street` varchar(100) DEFAULT NULL COMMENT '街道',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机',
  `code` varchar(100) DEFAULT NULL COMMENT '邮编',
  `telephone` varchar(100) DEFAULT NULL COMMENT '固定电话',
  `checkFlag` int(10) DEFAULT NULL COMMENT '是否选中',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_dt` bigint(16) NOT NULL COMMENT '创建时间',
  `extension_field` varchar(100) DEFAULT NULL COMMENT '扩展属性',
  `version` int(10) DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='收货地址表';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_address` VALUES ('42', '周琨', '210000', '辽宁省', '210400', '210403', 'fasdfasdfasdf', '15200861763', '', '', '0', '6', '1462544191', '辽宁省抚顺市东洲区fasdfasdfasdf', '0');
INSERT INTO `tb_address` VALUES ('43', '吴大侠', '130000', '河北省', '130100', '130104', '撒干撒的', '13525241254', '', '', '1', '3', '1462606708', '河北省石家庄市桥西区撒干撒的', '0');
INSERT INTO `tb_address` VALUES ('45', '333', '210000', '辽宁省', '210300', '210304', '33', '13414193635', '', '', '0', '38', '1462610013', '辽宁省鞍山市立山区33', '0');
INSERT INTO `tb_address` VALUES ('46', 'test', '120000', '天津', '120100', '120101', 'testtttt', '15820914042', '', '', '0', '7', '1462610586', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address` VALUES ('48', '205255', '220000', '吉林省', '220200', '220202', '精灵省街道德里克ikkkkkdkkdk公道归公道各反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复方法和公告各国国歌各国国', '13825263652', '', '', '1', '3', '1462613763', '吉林省吉林市昌邑区精灵省街道德里克ikkkkkdkkdk公道归公道各反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复方法和公告各国国歌各国国', '0');
INSERT INTO `tb_address` VALUES ('49', '张某某', '650000', '新疆维吾尔自治区', '652700', '652702', '噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦', '13852632541', '', '0755-96585454', '0', '3', '1462615036', '新疆维吾尔自治区博尔塔拉蒙古自治州阿拉山口市噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦', '0');
INSERT INTO `tb_address` VALUES ('50', 'gfd', '210000', '辽宁省', '210200', '210203', 'dfhdfhd', '15820914042', '', '', '1', '7', '1462617388', '辽宁省大连市西岗区dfhdfhd', '0');
INSERT INTO `tb_address` VALUES ('51', '李生', '440000', '广东省', '440200', '440203', '请选择  新华街道  惠民街道  西联镇  西河镇  龙归镇  江湾镇  重阳镇  ', '18764125525', '', '', '0', '48', '1462775332', '广东省韶关市武江区请选择  新华街道  惠民街道  西联镇  西河镇  龙归镇  江湾镇  重阳镇  ', '0');
INSERT INTO `tb_address` VALUES ('52', '张三', '130000', '河北省', '130100', '130104', '请选择  东里街道  中山路街道  南长街道  维明街道  裕西街道  友谊街道  红旗街道  新石街道  苑东街道  西里街道  振头街道  留营乡  ', '13252521425', '253632', '0731-25255555', '1', '48', '1462775674', '河北省石家庄市桥西区请选择  东里街道  中山路街道  南长街道  维明街道  裕西街道  友谊街道  红旗街道  新石街道  苑东街道  西里街道  振头街道  留营乡  ', '0');
INSERT INTO `tb_address` VALUES ('53', '饿了哦哦', '150000', '内蒙古自治区', '150200', '150203', '哦', '13566662222', '', '', '0', '2', '1462779153', '内蒙古自治区包头市昆都仑区哦', '0');
INSERT INTO `tb_address` VALUES ('54', '蒋为', '440000', '广东省', '440300', '440312', '民治街道展滔科技大厦A座1201', '13617139624', '518131', '0755-12452563', '0', '51', '1462783633', '广东省深圳市龙华新区民治街道展滔科技大厦A座1201', '0');


/*
MySQL Data Transfer
Source Host: 192.168.0.133
Source Database: hgl
Target Host: 192.168.0.133
Target Database: hgl
Date: 2016/5/9 17:30:17
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_address_history
-- ----------------------------
CREATE TABLE `tb_address_history` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `recipient` varchar(100) DEFAULT NULL COMMENT 'æ”¶è´§äºº',
  `province_name` varchar(100) DEFAULT '0' COMMENT 'çœä»½',
  `phone` varchar(100) DEFAULT NULL COMMENT 'æ‰‹æœº',
  `code` varchar(100) DEFAULT NULL COMMENT 'é‚®ç¼–',
  `telephone` varchar(100) DEFAULT NULL COMMENT 'å›ºå®šç”µè¯',
  `create_by` int(10) DEFAULT NULL COMMENT 'åˆ›å»ºäºº',
  `create_dt` bigint(16) NOT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `extension_field` varchar(100) DEFAULT NULL COMMENT 'åœ°å€',
  `version` int(10) DEFAULT '0' COMMENT 'ç‰ˆæœ¬',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='æ”¶è´§åœ°å€è¡¨åŽ†å²è¡¨';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_address_history` VALUES ('37', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462518930', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('38', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462520036', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('39', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462520669', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('40', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462520851', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('41', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462521939', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('42', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462523215', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('45', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462523838', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('46', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462527236', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('47', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462527445', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('48', 'test', '内蒙古自治区', '15820914041', '', '0736-1234560', '7', '1462530696', '内蒙古自治区包头市昆都仑区testwwe', '0');
INSERT INTO `tb_address_history` VALUES ('49', 'dk', '北京', '13585253625', '', '', '2', '1462532085', '北京北京市东城区sadfds', '0');
INSERT INTO `tb_address_history` VALUES ('50', '将为', '北京', '15200861763', '33334445', '', '6', '1462533400', '北京北京市西城区发上帝发誓地方', '0');
INSERT INTO `tb_address_history` VALUES ('51', 'test', '河北省', '15820914042', '', '', '7', '1462533674', '河北省秦皇岛市海港区test', '0');
INSERT INTO `tb_address_history` VALUES ('52', '周琨', '辽宁省', '15200861763', '', '', '6', '1462544201', '辽宁省抚顺市东洲区fasdfasdfasdf', '0');
INSERT INTO `tb_address_history` VALUES ('53', '周琨', '辽宁省', '15200861763', '', '', '6', '1462544307', '辽宁省抚顺市东洲区fasdfasdfasdf', '0');
INSERT INTO `tb_address_history` VALUES ('54', '周琨', '辽宁省', '15200861763', '', '', '6', '1462544604', '辽宁省抚顺市东洲区fasdfasdfasdf', '0');
INSERT INTO `tb_address_history` VALUES ('55', '吴大侠', '河北省', '13525241254', '', '', '3', '1462609271', '河北省石家庄市桥西区撒干撒的', '0');
INSERT INTO `tb_address_history` VALUES ('56', '333', '辽宁省', '13414193635', '', '', '38', '1462610241', '辽宁省鞍山市立山区33', '0');
INSERT INTO `tb_address_history` VALUES ('57', '吴大侠', '河北省', '13525241254', '', '', '3', '1462611431', '河北省石家庄市桥西区撒干撒的', '0');
INSERT INTO `tb_address_history` VALUES ('58', 'test', '天津', '15820914042', '', '', '7', '1462612448', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('60', 'test', '天津', '15820914042', '', '', '7', '1462612980', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('62', 'test', '天津', '15820914042', '', '', '7', '1462613256', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('63', '￥￥@#@￥%', '吉林省', '13825263652', '', '', '3', '1462614807', '吉林省吉林市昌邑区精灵省街道德里克ikkkkkdkkdk公道归公道各反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复方法和公告各国国歌各国国', '0');
INSERT INTO `tb_address_history` VALUES ('65', '张某某', '新疆维吾尔自治区', '13852632541', '', '0755-96585454', '3', '1462615183', '新疆维吾尔自治区博尔塔拉蒙古自治州阿拉山口市噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦啦噜啦啦啦啦啦啦啦噜啦', '0');
INSERT INTO `tb_address_history` VALUES ('66', '吴大侠', '河北省', '13525241254', '', '', '3', '1462615695', '河北省石家庄市桥西区撒干撒的', '0');
INSERT INTO `tb_address_history` VALUES ('67', 'test', '天津', '15820914042', '', '', '7', '1462617399', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('68', 'test', '天津', '15820914042', '', '', '7', '1462755545', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('70', 'test', '天津', '15820914042', '', '', '7', '1462756508', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('71', 'test', '天津', '15820914042', '', '', '7', '1462757652', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('72', 'test', '天津', '15820914042', '', '', '7', '1462758250', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('73', 'test', '天津', '15820914042', '', '', '7', '1462758604', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('74', 'test', '天津', '15820914042', '', '', '7', '1462760940', '天津天津市和平区testtttt', '0');
INSERT INTO `tb_address_history` VALUES ('75', '李生', '广东省', '18764125525', '', '', '48', '1462775350', '广东省韶关市武江区大新社区8999号大新社区8999号大新社区8999号大新社区8999号大新社区8999号大新社区8999号.', '0');
INSERT INTO `tb_address_history` VALUES ('76', '张三', '河北省', '13252521425', '253632', '0731-25255555', '48', '1462775679', '河北省石家庄市桥西区点点滴滴', '0');
INSERT INTO `tb_address_history` VALUES ('77', '张三', '河北省', '13252521425', '253632', '0731-25255555', '48', '1462776748', '河北省石家庄市桥西区点点滴滴', '0');
INSERT INTO `tb_address_history` VALUES ('78', '李生', '广东省', '18764125525', '', '', '48', '1462778827', '广东省韶关市武江区请选择  新华街道  惠民街道  西联镇  西河镇  龙归镇  江湾镇  重阳镇  ', '0');
INSERT INTO `tb_address_history` VALUES ('79', '饿了哦哦', '内蒙古自治区', '13566662222', '', '', '2', '1462779164', '内蒙古自治区包头市昆都仑区哦', '0');
INSERT INTO `tb_address_history` VALUES ('80', '蒋为', '广东省', '13617139624', '518131', '0755-12452563', '51', '1462783674', '广东省深圳市龙华新区民治街道展滔科技大厦A座1201', '0');
INSERT INTO `tb_address_history` VALUES ('81', '蒋为', '广东省', '13617139624', '518131', '0755-12452563', '51', '1462783819', '广东省深圳市龙华新区民治街道展滔科技大厦A座1201', '0');
INSERT INTO `tb_address_history` VALUES ('82', '蒋为', '广东省', '13617139624', '518131', '0755-12452563', '51', '1462783843', '广东省深圳市龙华新区民治街道展滔科技大厦A座1201', '0');
INSERT INTO `tb_address_history` VALUES ('83', '蒋为', '广东省', '13617139624', '518131', '0755-12452563', '51', '1462785309', '广东省深圳市龙华新区民治街道展滔科技大厦A座1201', '0');


/*
MySQL Data Transfer
Source Host: 192.168.0.133
Source Database: hgl
Target Host: 192.168.0.133
Target Database: hgl
Date: 2016/5/9 17:30:24
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_wap_product
-- ----------------------------
CREATE TABLE `tb_wap_product` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '品名id',
  `name` varchar(100) NOT NULL COMMENT '品名',
  `brand_id` int(10) DEFAULT NULL COMMENT '品牌id',
  `price` float DEFAULT NULL COMMENT '产品价格',
  `productType_id` int(10) DEFAULT '0' COMMENT '分类id',
  `saleNum` int(10) DEFAULT '0' COMMENT '销量',
  `attributes` varchar(300) DEFAULT NULL COMMENT '附加属性',
  `describes` varchar(300) DEFAULT NULL COMMENT '产品描述',
  `pImgOne` varchar(100) DEFAULT NULL COMMENT '产品图片1',
  `pImgTwo` varchar(100) DEFAULT NULL COMMENT '产品图片2',
  `pImgThree` varchar(100) DEFAULT NULL COMMENT '产品图片3',
  `dImgOne` varchar(100) DEFAULT NULL COMMENT '描述图片1',
  `dImgTwo` varchar(100) DEFAULT NULL COMMENT '描述图片2',
  `dImgThree` varchar(100) DEFAULT NULL COMMENT '描述图片3',
  `meterageUnit` varchar(100) DEFAULT NULL COMMENT '计量单位',
  `pickNo` int(10) DEFAULT NULL COMMENT '装箱数',
  `version` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COMMENT='wap产品表';

-- ----------------------------
-- Records 
-- ----------------------------


/*
MySQL Data Transfer
Source Host: 192.168.0.133
Source Database: hgl
Target Host: 192.168.0.133
Target Database: hgl
Date: 2016/5/9 17:30:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_wap_product_inventory
-- ----------------------------
CREATE TABLE `tb_wap_product_inventory` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '产品库存id',
  `product_id` int(10) NOT NULL COMMENT '产品id',
  `name` varchar(100) DEFAULT NULL COMMENT '产品库存名称',
  `code` varchar(30) NOT NULL COMMENT '货号',
  `sale_inventory` int(10) DEFAULT NULL COMMENT '剩余库存',
  `total_inventory` int(10) DEFAULT NULL COMMENT '入库库存',
  `unsale_inventory` int(10) DEFAULT NULL COMMENT '占用库存',
  `sales_count` int(10) DEFAULT NULL COMMENT '销售数量',
  `attributes_values` varchar(500) DEFAULT NULL COMMENT '属性值',
  `instock_price` decimal(18,1) DEFAULT NULL COMMENT '入库价格',
  `outstock_price` decimal(18,1) DEFAULT NULL COMMENT '出库价格',
  `sales_price` decimal(18,1) DEFAULT NULL COMMENT '零售价格',
  `status` int(10) DEFAULT NULL COMMENT '状态',
  `onebox_count` int(10) DEFAULT NULL COMMENT '装箱数',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注,扩展字段',
  `version` int(10) DEFAULT '0',
  `deleted` int(11) DEFAULT NULL,
  `createBy` varchar(100) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `lastUpdateBy` varchar(100) DEFAULT NULL,
  `last_update_time` bigint(20) DEFAULT NULL,
  `spec` varchar(100) DEFAULT NULL,
  `material` varchar(100) DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wap库存表';

-- ----------------------------
-- Records 
-- ----------------------------
