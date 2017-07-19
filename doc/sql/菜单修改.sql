##修改前的对照
INSERT INTO `tb_web_permission` VALUES ('1', '订单中心', '', '1', '1', '订单中心', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('11', '我的订单', '/myOrderGroup/index', '1', '11', '我的订单', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('13', '收货地址', '', '1', '13', '收货地址', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('15', '我的优惠券', '/couponInfo/doInitCouponInfo', '1', '15', '我的优惠券', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('17', '我的等级', '/level/level', '1', '17', '我的等级', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('19', '积分管理', '', '1', '19', '积分管理', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('111', '工作台', '', '1', '111', '工作台', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('113', '系统配置', '', '1', '113', '系统配置', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('115', '我的收藏', '/saveinfo/doSearchResult', '1', '115', '我的收藏', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('117', '我的经验值', '/level/experience', '1', '117', '我的经验值', '0', '0');

INSERT INTO `tb_web_permission` VALUES ('2', '销售订单', '', '2', '2', '销售订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('21', '销售订单', '/saleOrderGroup/index', '2', '21', '销售订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('23', '关闭订单', '/saleOrderGroup/stopOrder', '2', '23', '关闭订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('25', '送货条款', '/deliveryTerm/doSearchResult', '2', '25', '送货条款', '0', '1');

INSERT INTO `tb_web_permission` VALUES ('3', '货品管理', '', '3', '3', '货品管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('31', '添加分类', '/brand/productType', '3', '31', '添加分类', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('33', '品牌管理', '/brand/brand', '3', '33', '品牌管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('35', '个人相册', '/albumspace/getMyAlbumspaceList', '3', '35', '个人相册', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('37', '产品管理', '/product/index', '3', '37', '产品管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('39', '产品导入', '/import/productMuneView', '3', '39', '产品导入', '0', '1');

INSERT INTO `tb_web_permission` VALUES ('4', '客户营销', '', '4', '4', '客户营销', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('41', '分组管理', '/group/index', '4', '41', '分组管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('43', '站内信息', '/letter/letter', '4', '43', '站内信息', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('45', '活动管理', '/activity/doInitActivity', '4', '45', '活动管理', '0', '1');

INSERT INTO `tb_web_permission` VALUES ('5', '服务订单', '', '5', '5', '服务订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('51', '服务订单', '/serviceOrder/index', '5', '51', '服务订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('53', '我的服务', '/serviceOrder/myService', '5', '53', '我的服务', '0', '1');

INSERT INTO `tb_web_permission` VALUES ('6', '系统功能', '', '6', '6', '系统功能', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('61', '账户管理', '/webuser/doInitEditWebUser', '6', '61', '账户管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('63', '我的推荐', '/level/myRecommended', '6', '63', '我的推荐', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('65', '资金明细', '/cashAccount/index', '6', '65', '资金明细', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('67', '店铺管理', '/shop/shopManagement', '6', '67', '店铺管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('69', '数据分析', '/dataAnalysis/index', '6', '69', '数据分析', '0', '1');

##修改后
INSERT INTO `tb_web_permission` VALUES ('1', '订单中心', '', '1', '0', '订单中心', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('101', '我的订单', '/myOrderGroup/index', '1', '101', '我的订单', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('103', '收货地址', '', '1', '103', '收货地址', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('105', '我的优惠券', '/couponInfo/doInitCouponInfo', '1', '105', '我的优惠券', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('107', '我的等级', '/level/level', '1', '107', '我的等级', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('109', '积分管理', '', '1', '109', '积分管理', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('111', '工作台', '', '1', '111', '工作台', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('113', '系统配置', '', '1', '113', '系统配置', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('115', '我的收藏', '/saveinfo/doSearchResult', '1', '115', '我的收藏', '0', '0');
INSERT INTO `tb_web_permission` VALUES ('117', '我的经验值', '/level/experience', '1', '117', '我的经验值', '0', '0');

INSERT INTO `tb_web_permission` VALUES ('2', '销售订单', '', '2', '0', '销售订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('201', '销售订单', '/saleOrderGroup/index', '2', '201', '销售订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('203', '关闭订单', '/saleOrderGroup/stopOrder', '2', '203', '关闭订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('205', '送货规则', '/deliveryTerm/doSearchResult', '2', '205', '送货规则', '0', '1');

INSERT INTO `tb_web_permission` VALUES ('3', '货品管理', '', '3', '0', '货品管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('301', '分类管理', '/brand/productType', '3', '301', '添加分类', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('303', '品牌管理', '/brand/brand', '3', '303', '品牌管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('305', '产品图片', '/albumspace/getMyAlbumspaceList', '3', '305', '个人相册', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('307', '产品管理', '/product/index', '3', '307', '产品管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('309', '产品导入', '/import/productMuneView', '3', '309', '产品导入', '0', '1');

INSERT INTO `tb_web_permission` VALUES ('4', '客户营销', '', '4', '0', '客户营销', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('401', '客户管理', '/userGroup/index', '4', '401', '分组管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('403', '站内信息', '/letter/letter', '4', '403', '站内信息', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('405', '活动管理', '/activity/doInitActivity', '4', '405', '活动管理', '0', '1');

INSERT INTO `tb_web_permission` VALUES ('5', '接单', '', '5', '0', '服务订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('501', '服务订单', '/serviceOrder/index', '5', '501', '服务订单', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('503', '服务订单', '/serviceOrder/myService', '5', '503', '接单', '0', '1');

INSERT INTO `tb_web_permission` VALUES ('6', '系统功能', '', '6', '0', '系统功能', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('601', '账户管理', '/webuser/doInitEditWebUser', '6', '601', '账户管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('603', '我的推荐', '/level/myRecommended', '6', '603', '我的推荐', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('605', '资金明细', '/cashAccount/index', '6', '605', '资金明细', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('607', '店铺管理', '/shop/shopManagement', '6', '607', '店铺管理', '0', '1');
INSERT INTO `tb_web_permission` VALUES ('609', '数据分析', '/dataAnalysis/index', '6', '609', '数据分析', '0', '1');

INSERT INTO `tb_web_role` VALUES ('1', '已审核经销商', '1,101,103,105,107,109,111,113,115,117,2,201,203,205,3,301,303,305,307,309,4,401,403,405,5,501,503,6,601,603,605,607,609', '', '0');
INSERT INTO `tb_web_role` VALUES ('2', '未购买经销商', '6,601,607', '', '1');
INSERT INTO `tb_web_role` VALUES ('3', '未审核经销商', '', '', '2');