


CREATE TABLE `tb_wap_order_service` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_serial_no` varchar(30) NOT NULL,
  `total_money` double DEFAULT NULL COMMENT '订单总额',
  `repairman_id` int(10) DEFAULT NULL COMMENT '师傅ID',
  `master_id` int(10) DEFAULT NULL COMMENT '主人ID',
  `service_type_id` int(10) DEFAULT NULL COMMENT '服务类型ID',
  `order_status` int(10) DEFAULT NULL COMMENT '订单状态',
  `settle_type` int(10) NOT NULL COMMENT '结算方式',
  `create_dt` bigint(16) DEFAULT NULL COMMENT '创建时间',
  `pay_dt` bigint(16) unsigned zerofill DEFAULT NULL COMMENT '支付时间',
  `appointment_dt` bigint(16) DEFAULT NULL COMMENT '预约时间',
  `appointment_period_dt` varchar(200) DEFAULT NULL COMMENT '预约时间段',
  `address_id` int(11) DEFAULT NULL COMMENT '地址ID',
  `cancel_dt` bigint(16) DEFAULT NULL COMMENT '取消时间',
  `cancel_flag` int(10) DEFAULT NULL COMMENT '取消订单原因',
  `order_type` int(10) DEFAULT NULL COMMENT '订单类型',
  `message` varchar(1000) DEFAULT NULL COMMENT '描述',
  `evaluate` varchar(200) DEFAULT NULL COMMENT '评价',
  `evaluate_start` varchar(10) DEFAULT NULL COMMENT '评价星级',
  `evaluate_img` varchar(10) DEFAULT NULL COMMENT '评价图片',
  `extension_field` varchar(200) DEFAULT NULL COMMENT '扩展属性',
  `version` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='wap订单服务表';

CREATE TABLE `tb_wap_order_tracking_service` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_service_id` int(10) DEFAULT NULL COMMENT '服务订单ID',
  `order_state` int(10) DEFAULT NULL COMMENT '订单状态',
  `operate_name` varchar(45) DEFAULT NULL COMMENT '订单描述',
  `operateBy` int(10) DEFAULT NULL COMMENT '操作人',
  `operate_dt` bigint(16) DEFAULT NULL COMMENT '操作时间',
  `version` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='wap服务订单跟踪表';





