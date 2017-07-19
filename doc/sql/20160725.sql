#积分商城表
CREATE TABLE `tb_integral_mall` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `integral` int(10) DEFAULT NULL COMMENT '积分',
  `pay_amount` double DEFAULT NULL COMMENT '兑换后支付金额',
  `market_amount` double DEFAULT NULL COMMENT '商品的市场价格',
  `goods_image` varchar(100) DEFAULT NULL COMMENT '商品图片',
  `goods_describe` varchar(500) DEFAULT NULL COMMENT '商品描述',
  `attention_matters` varchar(500) DEFAULT NULL COMMENT '注意事项',
  `exchange_process` varchar(500) DEFAULT NULL COMMENT '兑换流程',
  `status` int(10) DEFAULT NULL COMMENT '商品状态: 0下架,1上架',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_dt` bigint(16) NOT NULL COMMENT '创建时间',
  `version` int(10) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='积分商城表';

#积分商城记录表
CREATE TABLE `tb_integral_mall_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `integral_mall_id` int(10) DEFAULT NULL COMMENT '积分商城表ID',
  `integral_serial_no` varchar(30) DEFAULT NULL COMMENT '积分序列号(系统生成)',
  `user_id` int(10) DEFAULT NULL COMMENT '用户ID',
  `pay_money` double DEFAULT NULL COMMENT '应付的总金额',
  `remaining_integral` int(10) DEFAULT NULL COMMENT '使用了多少积分',
  `pay_status` int(10) DEFAULT NULL COMMENT '支付状态(待支付,已支付)',
  `plat_status` int(10) DEFAULT NULL COMMENT '发货状态(待发货,已发货)',
  `exchange_num` int(10) DEFAULT NULL COMMENT '兑换数量',
  `address_id` int(10) DEFAULT NULL COMMENT '收货地址历史表(tb_wap_address_history)ID',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_dt` bigint(16) NOT NULL COMMENT '创建时间',
  `version` int(10) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='积分商城记录表';

INSERT INTO `tb_dict` VALUES 
(140,'1104','待支付','integrall_mall_pay'),
(141,'1106','已支付','integrall_mall_pay'),
(142,'1108','待发货','integrall_mall_plat'),
(143,'1110','已发货','integrall_mall_plat');

INSERT INTO `tb_admin_permission` (name, action_url, remark, version,parent_node, child_node)
VALUES 
('积分商城管理','/integralMall/index',6,10,'89',0),
('积分商城记录管理','/integralMallRecord/index',6,11,'90',0);