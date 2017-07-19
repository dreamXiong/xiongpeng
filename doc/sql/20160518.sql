CREATE TABLE `tb_wap_address` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `recipient` varchar(100) DEFAULT NULL COMMENT '收货人',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `province_name` varchar(100) DEFAULT '0' COMMENT '省名称',
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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='收货地址表';

CREATE TABLE `tb_wap_address_history` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `recipient` varchar(100) DEFAULT NULL COMMENT '收货人',
  `province_name` varchar(100) DEFAULT '0' COMMENT '省份',
  `phone` varchar(100) DEFAULT NULL COMMENT '电话',
  `code` varchar(100) DEFAULT NULL COMMENT '邮编',
  `telephone` varchar(100) DEFAULT NULL COMMENT '固定电话',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人,是谁的地址',
  `create_dt` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `extension_field` varchar(100) DEFAULT NULL COMMENT '扩展字段',
  `version` int(10) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='收货地址历史表';

CREATE TABLE `tb_wap_shopping_cart` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `inventory_id` int(10) DEFAULT NULL COMMENT '库存ID',
  `buy_num` int(10) DEFAULT NULL COMMENT '购买的数量',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人,谁购买的',
  `create_dt` bigint(16) unsigned DEFAULT NULL COMMENT '购买时间',
  `version` int(10) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='wap购物车表';
