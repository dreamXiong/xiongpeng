CREATE TABLE `tb_product_type` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '分类id',
	`parent_id` INT(10) NULL DEFAULT '0' COMMENT '父节点',
	`main_id` INT(10) NULL DEFAULT '0' COMMENT '大分类id',
	`name` VARCHAR(100) NULL DEFAULT NULL COMMENT '分类名称',
	`describes` VARCHAR(500) NULL DEFAULT NULL COMMENT '分类描述',
	`version` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='产品分类表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18
;

CREATE TABLE `tb_brand` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
	`name` VARCHAR(100) NOT NULL COMMENT '品牌名称',
	`logoName` VARCHAR(100) NOT NULL COMMENT '品牌名称',
	`url` VARCHAR(100) NULL DEFAULT NULL COMMENT '品牌网址',
	`state` INT(10) NOT NULL COMMENT '状态：0是显示 1是隐藏',
	`manufacturer_id` INT(10) NOT NULL COMMENT '厂商id',
	`manufacturer_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '厂家名称',
	`remark` VARCHAR(500) NULL DEFAULT NULL COMMENT '品牌描述',
	`productType_id` INT(10) NULL DEFAULT '0' COMMENT '分类id',
	`productType_name` VARCHAR(100) NOT NULL COMMENT '大类名称',
	`sort` INT(10) NULL DEFAULT '0' COMMENT '排序',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='品牌表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3
;
CREATE TABLE `tb_product` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '品名id',
	`name` VARCHAR(100) NOT NULL COMMENT '品名',
	`brand_id` INT(10) NULL DEFAULT NULL COMMENT '品牌id',
	`price` FLOAT NULL DEFAULT NULL COMMENT '产品价格',
	`productType_id` INT(10) NULL DEFAULT '0' COMMENT '分类id',
	`saleNum` INT(10) NULL DEFAULT '0' COMMENT '销量',
	`attributes` VARCHAR(300) NULL DEFAULT NULL COMMENT '附加属性',
	`describes` VARCHAR(300) NULL DEFAULT NULL COMMENT '产品描述',
	`pImgOne` VARCHAR(100) NULL DEFAULT NULL COMMENT '产品图片1',
	`pImgTwo` VARCHAR(100) NULL DEFAULT NULL COMMENT '产品图片2',
	`pImgThree` VARCHAR(100) NULL DEFAULT NULL COMMENT '产品图片3',
	`dImgOne` VARCHAR(100) NULL DEFAULT NULL COMMENT '描述图片1',
	`dImgTwo` VARCHAR(100) NULL DEFAULT NULL COMMENT '描述图片2',
	`dImgThree` VARCHAR(100) NULL DEFAULT NULL COMMENT '描述图片3',
	`meterageUnit` VARCHAR(100) NULL DEFAULT NULL COMMENT '计量单位',
	`producyNo` VARCHAR(100) NULL DEFAULT NULL COMMENT '货号',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='产品表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `tb_dict` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`dictkey` VARCHAR(20) NULL DEFAULT NULL COMMENT '键',
	`value` VARCHAR(20) NULL DEFAULT NULL COMMENT '值',
	`type` VARCHAR(20) NULL COMMENT '类型',
	PRIMARY KEY (`id`)
)
COMMENT='字典表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=191
;

ALTER TABLE `tb_product`
	CHANGE COLUMN `producyNo` `pickNo` INT(10) NULL DEFAULT NULL COMMENT '装箱数' AFTER `meterageUnit`;
	
ALTER TABLE `tb_web_user`
	CHANGE COLUMN `type_id` `type_id` INT(10) NOT NULL COMMENT '102 厂家 104 经销商 106 终端客户 108 地推人员 110 仓库管理员' AFTER `name`;
	
INSERT INTO `hgl`.`tb_dict` (`key`, `value`) VALUES ('123', '自营仓库');
INSERT INTO `hgl`.`tb_dict` (`key`, `value`) VALUES ('123', '代理仓库');

ALTER TABLE `tb_warehouse_user`
	ADD COLUMN `user_name` VARCHAR(60) NULL DEFAULT NULL COMMENT '用户姓名' AFTER `shop_type`,
	ADD COLUMN `user_account` VARCHAR(60) NULL DEFAULT NULL COMMENT '登陆帐户' AFTER `user_name`;

CREATE TABLE `tb_order_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_group_id` int(10) DEFAULT NULL,
  `warehouse_id` int(10) DEFAULT NULL,
  `goods_detail_id` int(10) DEFAULT NULL,
  `warehouse_batch_no` varchar(45) DEFAULT NULL,
  `inventory_id` int(10) DEFAULT NULL,
  `price` double DEFAULT NULL COMMENT '提交订单时候的价格',
  `buy_price` double DEFAULT NULL COMMENT '实际付款的价格，有可能会',
  `buy_num` int(10) DEFAULT NULL COMMENT '购买数量',
  `per_weight` double DEFAULT NULL COMMENT '均重',
  `pay_money` double DEFAULT NULL COMMENT '该detail实际支付金额',
  `total_weight` double DEFAULT NULL COMMENT '该detail的总重量',
  `money` double DEFAULT NULL COMMENT '提交订单时该detail的金额',
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
COMMENT='订单明细表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=108
;

CREATE TABLE `tb_order_group` (
  `id` int(10) NOT NULL AUTO_INCREMEN,
  `order_serial_no` varchar(30) NOT NULL,
  `total_money` double DEFAULT NULL COMMENT '提交订单时group的总金额',
  `total_num` double DEFAULT NULL COMMENT '购买这个group下所有产品的总数量',
  `actual_money` double DEFAULT NULL COMMENT '实付的总金额，不含佣金',
  `pay_money` double DEFAULT NULL COMMENT '应付的总金额，不含佣金',
  `total_quantity` double DEFAULT NULL COMMENT '购买这个group下所有产品的总数量',
  `shop_id` int(10) DEFAULT NULL COMMENT '卖家ID',
  `buyer_id` int(10) DEFAULT NULL COMMENT  '买家ID',
  `brand_id` int(10) DEFAULT NULL COMMENT '品牌ID',
  `order_status` int(10) DEFAULT NULL COMMENT '订单状态',
  `pay_dt` bigint(16) unsigned zerofill DEFAULT NULL  COMMENT '支付时间',
  `commission` double DEFAULT NULL COMMENT '佣金',
  `settle_type` int(10) NOT NULL COMMENT '结算方式',
  `version` int(10) NOT NULL,
  `plat_flag` int(10) DEFAULT NULL,
  `audit_dt` bigint(16) unsigned DEFAULT NULL COMMENT '卖家确认订单时间',
  `cancel_dt` bigint(16) DEFAULT NULL COMMENT '取消时间',
  `create_dt` bigint(16) DEFAULT NULL COMMENT '创建时间',
  `send_dt` bigint(16) DEFAULT NULL COMMENT '发货时间',
  `cancel_flag` int(10) DEFAULT NULL COMMENT '取消订单原因，数据存放在字典中',
  `delete_dt` bigint(16) DEFAULT NULL COMMENT '逻辑删除时间',
  `delete_flag` int(10) DEFAULT NULL COMMENT '逻辑删除flag',
  `order_type` int(10) DEFAULT NULL COMMENT '逻辑删除flag',
  `stop_status` int(10) DEFAULT NULL COMMENT '终止之前的状态',
  PRIMARY KEY (`id`)
) 
COMMENT='订单表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=108
;

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('200', '待确认','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('202', '待付款','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('204', '待补发货','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('206', '已终止','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('208', '待发货','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('210', '待确认收货','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('212', '待登记发票','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('214', '待确认收票','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('216', '待买确认终止','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('218', '待卖确认终止','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('220', '交易完成','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('222', '交易关闭','orderState');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('240', '线上支付','settleType');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('242', '线下支付','settleType');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('262', '招商订单','orderType');
INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('264', '普通订单','orderType');

CREATE TABLE `Tb_Order_Tracking` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_group_id` int(10) DEFAULT NULL,
  `recordType` int(10) DEFAULT NULL,
  `goods_detail_id` int(10) DEFAULT NULL COMMENT '订单ID',
  `operate_name` varchar(45) DEFAULT NULL,
  `operateBy` int(10) DEFAULT NULL,
  `operateDt` bigint(16) DEFAULT NULL COMMENT '操作时间',
  `status` int(10) DEFAULT NULL,
  `createBy` int(10) DEFAULT NULL,
  `createDt` bigint(16) DEFAULT NULL COMMENT '操作时间',
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
COMMENT='订单跟踪记录表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=108
;

ALTER TABLE `tb_dict`
	CHANGE COLUMN `key` `dictkey` VARCHAR(20) NULL DEFAULT NULL COMMENT '键' AFTER `id`;
ALTER TABLE `tb_order_group`
	CHANGE COLUMN `send_dt` `coupon_money` INT NULL DEFAULT NULL COMMENT '使用优惠卷的金额' AFTER `create_dt`;
ALTER TABLE `tb_order_group`
	CHANGE COLUMN `seller_id` `shop_id` INT(10) NULL DEFAULT NULL COMMENT '卖家ID' AFTER `total_quantity`;
ALTER TABLE `tb_order_group`
	DROP COLUMN `audit_dt`;
ALTER TABLE `tb_order_group`
	ADD COLUMN `stop_reason` VARCHAR(100) NULL DEFAULT NULL COMMENT '终止原因' AFTER `stop_status`;
	
CREATE TABLE `Tb_Inventory_Lock` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_id` int(10) DEFAULT NULL COMMENT '订单ID',
  `Inventory_id` int(10) DEFAULT NULL COMMENT '库存ID',
  `order_detail_id` int(10) DEFAULT NULL COMMENT '订单ID',
  `order_type` int(10) DEFAULT NULL COMMENT '订单类型',
  `lock_quantity` int(10) DEFAULT NULL COMMENT '锁定量',
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
COMMENT='锁库存表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=108
;

CREATE TABLE `tb_cash_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cash_out` double DEFAULT NULL COMMENT '支出',
  `cash_in` double DEFAULT NULL COMMENT '转入',
  `balance` double DEFAULT NULL COMMENT '余额',
  `operation_dt` bigint(16) unsigned DEFAULT NULL COMMENT '操作时间',
  `order_serial_no` VARCHAR(30) DEFAULT NULL COMMENT '订单号',
  PRIMARY KEY (`id`)
) 
COMMENT='金额表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=108
;

ALTER TABLE `tb_order_group`
	ADD COLUMN ` remainder` DOUBLE NULL DEFAULT NULL COMMENT '账户剩余金额' AFTER `total_quantity`,
	ADD COLUMN `warehouse_id` INT(10) NULL DEFAULT NULL COMMENT '仓库ID' AFTER `order_status`,
	ADD COLUMN `address_id` INT(11) NULL DEFAULT NULL COMMENT '地址ID' AFTER `coupon_money`;


CREATE TABLE `tb_inventory_lock` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`order_id` INT(10) NULL DEFAULT NULL COMMENT '订单ID',
	`inventory_id` INT(10) NULL DEFAULT NULL COMMENT '库存ID',
	`goods_detail_id` INT(10) NULL DEFAULT NULL COMMENT '订单ID',
	`order_status` INT(10) NULL DEFAULT NULL COMMENT '订单状态',
	`order_type` INT(10) NULL DEFAULT NULL COMMENT '订单类型',
	`lock_quantity` INT(10) NULL DEFAULT NULL COMMENT '锁定量',
	`version` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='锁库存表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=224
;
ALTER TABLE `tb_order_detail`
	DROP COLUMN `warehouse_id`,
	DROP COLUMN `goods_detail_id`,
	DROP COLUMN `warehouse_batch_no`;
ALTER TABLE `tb_order_group`
	DROP COLUMN `actual_money`,
	DROP COLUMN `total_quantity`;
ALTER TABLE `tb_order_group`
	CHANGE COLUMN `total_num` `total_num` INT NULL DEFAULT NULL COMMENT '购买这个group下所有产品的总数量' AFTER `total_money`;	
ALTER TABLE `tb_shop_info`
	ADD COLUMN `balance` DOUBLE UNSIGNED NULL DEFAULT NULL COMMENT '剩余金额' AFTER `create_dt`,
	ADD COLUMN `lon` DOUBLE UNSIGNED NULL DEFAULT NULL COMMENT '店铺的纵坐标' AFTER `balance`,
	ADD COLUMN `lat` DOUBLE UNSIGNED NULL DEFAULT NULL COMMENT '店铺的横坐标' AFTER `lon`;
	
ALTER TABLE `tb_wap_product_inventory` DROP COLUMN `shop_id`;

ALTER TABLE `tb_wap_product` ADD COLUMN `shop_id` INT(10) NULL DEFAULT '0' COMMENT '店铺ID' AFTER `shop_flag`;



CREATE TABLE `tb_wap_order_tracking` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`order_group_id` INT(10) NULL DEFAULT NULL COMMENT '订单ID',
	`order_state` INT(10) NULL DEFAULT NULL COMMENT '订单状态',
	`operate_name` VARCHAR(45) NULL DEFAULT NULL,
	`operateBy` INT(10) NULL DEFAULT NULL COMMENT '操作人',
	`operate_dt` BIGINT(16) NULL DEFAULT NULL COMMENT '操作时间',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='wap订单跟踪表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=975
;
CREATE TABLE `tb_wap_inventory_lock` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`order_id` INT(10) NULL DEFAULT NULL COMMENT '订单ID',
	`Inventory_id` INT(10) NULL DEFAULT NULL COMMENT '库存ID',
	`order_status` INT(10) NULL DEFAULT NULL COMMENT '订单状态',
	`order_type` INT(10) NULL DEFAULT NULL COMMENT '订单类型',
	`lock_quantity` INT(10) NULL DEFAULT NULL COMMENT '锁定量',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='wap锁库存表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=345
;
CREATE TABLE `tb_wap_order_detail` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`order_group_id` INT(10) NULL DEFAULT NULL,
	`inventory_id` INT(10) NULL DEFAULT NULL,
	`price` DOUBLE NULL DEFAULT NULL COMMENT '提交订单时候的价格',
	`buy_price` DOUBLE NULL DEFAULT NULL COMMENT '实际付款的价格，有可能会',
	`buy_num` INT(10) NULL DEFAULT NULL COMMENT '购买数量',
	`pay_money` DOUBLE NULL DEFAULT NULL COMMENT '实际支付金额，改价后',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='wap订单明细表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=322
;
CREATE TABLE `tb_wap_order_group` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`order_serial_no` VARCHAR(30) NOT NULL,
	`total_money` DOUBLE NULL DEFAULT NULL COMMENT '订单总额',
	`total_num` INT(11) NULL DEFAULT NULL COMMENT '购买这个group下所有产品的总数量',
	`pay_money` DOUBLE NULL DEFAULT NULL COMMENT '应付的总金额，不含佣金',
	`remainder` DOUBLE NULL DEFAULT NULL COMMENT '账户剩余金额',
	`shop_id` INT(10) NULL DEFAULT NULL COMMENT '卖家ID',
	`buyer_id` INT(10) NULL DEFAULT NULL COMMENT '买家ID',
	`brand_id` INT(10) NULL DEFAULT NULL COMMENT '品牌ID',
	`order_status` INT(10) NULL DEFAULT NULL COMMENT '订单状态',
	`pay_dt` BIGINT(16) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '支付时间',
	`settle_type` INT(10) NOT NULL COMMENT '结算方式',
	`version` INT(10) NOT NULL DEFAULT '0',
	`plat_flag` INT(10) NULL DEFAULT NULL,
	`cancel_dt` BIGINT(16) NULL DEFAULT NULL COMMENT '取消时间',
	`create_dt` BIGINT(16) NULL DEFAULT NULL COMMENT '创建时间',
	`coupon_money` INT(11) NULL DEFAULT NULL COMMENT '使用优惠卷的金额',
	`address_id` INT(11) NULL DEFAULT NULL COMMENT '地址ID',
	`cancel_flag` INT(10) NULL DEFAULT NULL COMMENT '取消订单原因',
	`delete_dt` BIGINT(16) NULL DEFAULT NULL COMMENT '逻辑删除时间',
	`delete_flag` INT(10) NULL DEFAULT NULL COMMENT '逻辑删除flag',
	`order_type` INT(10) NULL DEFAULT NULL COMMENT '订单类型',
	`stop_status` INT(10) NULL DEFAULT NULL COMMENT '终止之前的状态',
	`stop_reason` VARCHAR(100) NULL DEFAULT NULL COMMENT '终止原因',
	PRIMARY KEY (`id`)
)
COMMENT='wap订单表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=191
;
ALTER TABLE `tb_order_group`
	DROP COLUMN ` remainder`;
	
INSERT INTO `hgl`.`tb_dict` (`id`, `dictkey`, `value`, `type`) VALUES (103, '510', '普通订单', 'wapOrderType');

INSERT INTO `hgl`.`tb_dict` (`id`, `dictkey`, `value`, `type`) VALUES (104, '212', '服务订单', 'wapOrderType');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('600', '待确认','wapOrderState');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('602', '待付款','wapOrderState');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('606', '已终止','wapOrderState');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('608', '待发货','wapOrderState');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('610', '待确认收货','wapOrderState');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('612', '待评价','wapOrderState');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('616', '待买确认终止','wapOrderState');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('618', '待卖确认终止','wapOrderState');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('620', '交易完成','wapOrderState');

INSERT INTO `hgl`.`tb_dict` (`dictkey`, `value`,`type`) VALUES ('622', '交易关闭','wapOrderState');

ALTER TABLE `tb_order_tracking`
	CHANGE COLUMN `operateBy` `operateBy` INT(10) NULL DEFAULT NULL COMMENT '经销商操作' AFTER `operate_name`,
	ADD COLUMN `operateByAdmin` INT(10) NULL DEFAULT NULL COMMENT '后台操作ID' AFTER `operateBy`;
CREATE TABLE `tb_customer_service` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '品名id',
	`name` VARCHAR(100) NOT NULL COMMENT '服务名称',
	`operation_id` INT(10) NULL DEFAULT NULL COMMENT '操作人id',
	`serviceType_id` INT(10) NULL DEFAULT '0' COMMENT '服务分类id',
	`describes` VARCHAR(300) NULL DEFAULT NULL COMMENT '服务说明',
	`introduce` VARCHAR(300) NULL DEFAULT NULL COMMENT '服务介绍',
	`cImgOne` VARCHAR(100) NULL DEFAULT NULL COMMENT '服务图片1',
	`cImgTwo` VARCHAR(100) NULL DEFAULT NULL COMMENT '服务图片2',
	`cImgThree` VARCHAR(100) NULL DEFAULT NULL COMMENT '服务图片3',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='客户服务表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=124
;
ALTER TABLE `tb_customer_service` ADD COLUMN `price` DOUBLE NULL DEFAULT '0' COMMENT '价格' AFTER `serviceType_id`;
ALTER TABLE `tb_shop_info` ADD COLUMN `freeze_cash` DOUBLE UNSIGNED NULL DEFAULT '0' COMMENT '冻结金额' AFTER `lat`;

CREATE TABLE `tb_account` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`balance` DOUBLE NULL DEFAULT '0' COMMENT '余额',
	`freeze` DOUBLE NULL DEFAULT '0' COMMENT '冻结金额',
	`bank_account` VARCHAR(100) NULL DEFAULT NULL COMMENT '银行账户',
	`bank` VARCHAR(50) NULL DEFAULT NULL COMMENT '银行',
	`version` INT(10) NULL DEFAULT '0' COMMENT '版本',
	PRIMARY KEY (`id`)
)
COMMENT='账户金额表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;


ALTER TABLE `tb_wap_order_group`
	ADD COLUMN `delete_dt_seller` BIGINT(16) NULL DEFAULT NULL COMMENT '卖家删除订单时间' AFTER `delete_dt`,
	ADD COLUMN `delete_flag_seller` INT(10) NULL DEFAULT '0' COMMENT '卖家删除订单flag' AFTER `delete_flag`;
	
ALTER TABLE `tb_cash_account`
	ADD COLUMN `platform` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT '平台发起：1wap,0其它' AFTER `shop_id`;
ALTER TABLE `tb_shop_info` DROP COLUMN `freeze_cash`;
ALTER TABLE `tb_user_info` ADD COLUMN `account_id` INT(10) NULL DEFAULT '236' COMMENT '账户ID' AFTER `gender`;
ALTER TABLE `tb_cash_account` ADD COLUMN `account_id` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '账户ID' AFTER `shop_id`;

CREATE TABLE `tb_freezing` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`order_id` INT(10) NOT NULL COMMENT '订单ID',
	`account_id` INT(10) NOT NULL COMMENT '账户ID',
	`order_serial_no` VARCHAR(20) NOT NULL COMMENT '订单号',
	`freeze_money` DOUBLE NOT NULL COMMENT '冻结金额',
	`unfreeze_money` DOUBLE NOT NULL COMMENT '解冻金额',
	`balance` DOUBLE NOT NULL COMMENT '账户总额',
	`freeze` DOUBLE NOT NULL COMMENT '冻结总额',
	`opear_dt` BIGINT(16) NULL DEFAULT NULL COMMENT '操作时间',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='冻结明细表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;

ALTER TABLE `tb_shop_info`
	ADD COLUMN `account_id` INT(10) NOT NULL DEFAULT '0' COMMENT '账户ID' AFTER `version`;
	
ALTER TABLE `tb_wap_order_group`
	ADD COLUMN `account_id` INT(11) NULL DEFAULT NULL COMMENT '账户ID' AFTER `address_id`;
ALTER TABLE `tb_freezing`
	ADD COLUMN `finish` INT NULL DEFAULT '0' COMMENT '是否完成：0否，1是' AFTER `opear_dt`;
	
	
CREATE TABLE `tb_integral` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`user_id` INT(10) NOT NULL COMMENT '用户ID',
	`integral` INT(10) NOT NULL DEFAULT '0' COMMENT '积分总额',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='积分表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `tb_integral_detailed` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`integral_id` INT(10) NOT NULL COMMENT '用户ID',
	`opear_dt` BIGINT(16) NULL DEFAULT NULL COMMENT '操作时间',
	`order_id` INT(10) NOT NULL DEFAULT '0' COMMENT '订单ID',
	`order_serial_no` VARCHAR(20) NOT NULL DEFAULT '0' COMMENT '订单号',
	`integral` INT(10) NOT NULL COMMENT '所得积分',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='积分明细表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
ALTER TABLE `tb_wap_order_group` ADD COLUMN `rebate` INT(10) NULL DEFAULT NULL COMMENT '是否已经执行返利任务：0否，1是' AFTER `stop_status`;

CREATE TABLE `tb_withdrawals` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`user_id` INT(10) NOT NULL COMMENT '用户ID',
	`account_id` INT(10) NOT NULL COMMENT '账户ID',
	`state` INT(10) NOT NULL COMMENT '0：待审核，1：审核通过,2审核拒绝',
	`freezing` INT(10) NOT NULL COMMENT '提现时冻结记录ID',
	`money` DOUBLE NOT NULL COMMENT '提现金额',
	`operation_dt` BIGINT(16) UNSIGNED NULL DEFAULT NULL COMMENT '操作时间',
	`version` INT(10) NULL DEFAULT '0' COMMENT '版本',
	`audit_id` INT(10) NULL DEFAULT NULL COMMENT '审核人Id',
	`accountbank_id` INT(10) NULL DEFAULT NULL COMMENT '银行账户ID',
	`audit_dt` BIGINT(16) NULL DEFAULT NULL COMMENT '审核时间',
	`shop_id` INT(10) NULL DEFAULT NULL COMMENT '店铺ID',
	`audit_person` INT(10) NULL DEFAULT NULL COMMENT '审核人。1平台，2店铺',
	`determine_dt` BIGINT(16) NULL DEFAULT NULL COMMENT '预留确定时间',
	`bank_account` VARCHAR(20) NULL DEFAULT NULL COMMENT '银行账号',
	`bank` VARCHAR(20) NULL DEFAULT NULL COMMENT '银行',
	PRIMARY KEY (`id`)
)
COMMENT='账户提现表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;

ALTER TABLE `tb_account`
	ADD COLUMN `branch` VARCHAR(50) NULL DEFAULT NULL COMMENT '支行' AFTER `bank`;
	
ALTER TABLE `tb_integral_detailed`
	ADD COLUMN `type` INT(10) NOT NULL COMMENT '1:支付积分，2：返利积分' AFTER `integral`;
	
ALTER TABLE `tb_withdrawals`
	ADD COLUMN `freezing` INT(10) NOT NULL COMMENT '提现时冻结记录ID' AFTER `state`;
	
CREATE TABLE `tb_account_bank` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`bank_account` VARCHAR(100) NULL DEFAULT NULL COMMENT '银行账户',
	`account_id` INT(10) not NULL  COMMENT '账户ID',
	`bank` VARCHAR(50) NULL DEFAULT NULL COMMENT '银行',
	`branch` VARCHAR(50) NULL DEFAULT NULL COMMENT '支行',
	`name` VARCHAR(50) NULL DEFAULT NULL COMMENT '持卡人姓名',
	`version` INT(10) NULL DEFAULT '0' COMMENT '版本',
	PRIMARY KEY (`id`)
)
COMMENT='银行账户表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;


CREATE TABLE `tb_user_group` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`shop_id` INT(10) NULL DEFAULT '0' COMMENT '店铺ID',
	`user_id` INT(10) NULL DEFAULT '0' COMMENT '用户ID',
	`remark` VARCHAR(100) NULL DEFAULT NULL COMMENT '说明',
	`name` VARCHAR(50) NULL DEFAULT NULL COMMENT '组名',
	`discount` INT(50) NULL DEFAULT NULL COMMENT '折扣',
	`version` INT(10) NULL DEFAULT '0' COMMENT '版本',
	PRIMARY KEY (`id`)
)
COMMENT='店铺分组设置表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;

CREATE TABLE `tb_group_distribution` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`shop_id` INT(10) NULL DEFAULT '0' COMMENT '店铺ID',
	`user_id` INT(10) NULL DEFAULT '0' COMMENT '用户ID',
	`group_id` VARCHAR(100) NULL DEFAULT NULL COMMENT '用户所属组ID',
	`version` INT(10) NULL DEFAULT '0' COMMENT '版本',
	PRIMARY KEY (`id`)
)
COMMENT='用户组分配表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;

ALTER TABLE `tb_shop_info`
	ADD COLUMN `settlement` INT(10) NULL DEFAULT NULL COMMENT '平台结算：0，自结算：1' AFTER `isAutomatic_order`;
	
CREATE TABLE `tb_pay_context_param` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`order_groub_no` VARCHAR(30) NOT NULL COMMENT '订单号',
	`trade_status` INT(10) NULL DEFAULT NULL COMMENT '当前订单的状态',
	`version` INT(10) NULL DEFAULT '0' COMMENT '版本',
	`payment` VARCHAR(255) NULL DEFAULT NULL COMMENT '0:支付，1:充值',
	`account_id` VARCHAR(255) NULL DEFAULT NULL COMMENT '充值时的账户ID',
	PRIMARY KEY (`id`)
)
COMMENT='维护支付订单最新的状态\r\n'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3
;
ALTER TABLE `tb_withdrawals`
	ADD COLUMN `audit_person` INT(10) NULL DEFAULT NULL COMMENT '审核人。1平台，2店铺' AFTER `determine_id`;
	
ALTER TABLE `tb_withdrawals`
	CHANGE COLUMN `determine_id` `shop_id` INT(10) NULL DEFAULT NULL COMMENT '店铺ID' AFTER `audit_dt`;
	
ALTER TABLE `tb_withdrawals`
	ADD COLUMN `card_holder` VARCHAR(20) NULL DEFAULT NULL COMMENT '持卡人姓名' AFTER `bank_account`;
	
UPDATE `hgl`.`tb_web_permission` SET `action_url`='/userGroup/index' WHERE  `id`=401;


INSERT INTO `hgl`.`tb_system_config` (`id`, `config_key`, `config_value`, `remark`, `create_dt`, `update_dt`) VALUES (4, 'settlement', '0.1', '结算服务权限购买金额', 0, 0);