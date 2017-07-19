CREATE TABLE `tb_product_inventory` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '产品库存id',
	`product_id` INT(10) NOT NULL COMMENT '产品id',
	`name` VARCHAR(100) NOT NULL COMMENT '产品库存名称',
	`code` VARCHAR(30) NOT NULL COMMENT '货号',
	`sale_inventory` INT(10) COMMENT '剩余库存',
	`total_inventory` INT(10) COMMENT '入库库存',
	`unsale_inventory` INT(10) COMMENT '占用库存',
	`sales_count` INT(10) COMMENT '销售数量',
	`attributes_values` VARCHAR(500) NULL DEFAULT NULL COMMENT '属性值',
	`instock_price`   DECIMAL(18,1) COMMENT '入库价格',
	`outstock_price`   DECIMAL(18,1) COMMENT '出库价格',
	`sales_price`   DECIMAL(18,1) COMMENT '零售价格',
	`status`   INT(10) COMMENT '状态',
	`onebox_count`   INT(10) COMMENT '装箱数',
	`remark`   VARCHAR(500) COMMENT '备注,扩展字段',
	`version` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='库存表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18
;