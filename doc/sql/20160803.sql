CREATE TABLE `tb_integral_rules` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '推荐规则id',
	`shop_id` INT(10) NULL DEFAULT NULL COMMENT '经销商id',
	`type` INT(10) NULL DEFAULT '0' COMMENT '类型：0 百分比，1 满送',
	`money` INT(10) NULL DEFAULT '0' COMMENT '满送金额',
	`pay_money` INT(10) NULL DEFAULT '0' COMMENT '购买金额',
	`use_situation` INT(10) NULL DEFAULT '0' COMMENT '使用情况：0 使用中，1 禁用',
	`version` INT(10) NULL DEFAULT '0' COMMENT '版本',
	PRIMARY KEY (`id`)
)
COMMENT='经销商返利规则表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=19
;


insert into tb_system_config (config_key, config_value, remark, create_by,create_dt, update_by, update_dt,extension_field,version)
VALUES 
('medalAgent','0.2','金牌代理商权限购买金额','admin',1470192110,'admin',1470192110,NULL,0);
('medalAgentToMedalAgent','4','20000推荐20000','admin',1470279680,'admin',1470279680,NULL,0),
('medalAgentToSettlement','3','20000推荐6800','admin',1470279687,'admin',1470279687,NULL,0),
('settlementToMedalAgent','2','6800推荐20000','admin',1470279693,'admin',1470279693,NULL,0),
('settlementToSettlement','1','6800推荐6800','admin',1470279699,'admin',1470279699,NULL,0);

ALTER TABLE tb_shop_info ADD medal_agent_flag INT(10) DEFAULT 0 COMMENT '金牌代理商权限标示0:未开通,1:未审核,2:已开通';
ALTER TABLE tb_integral_mall ADD integral_mall_type_id INT(10) COMMENT '积分商城类型ID';

INSERT INTO `hgl`.`tb_system_config` (`config_key`,`create_dt` ,`update_dt`,`config_value`, `remark`) VALUES ('settlementAddIntegral','0','0', '6000', '开通结算功能赠送积分');

CREATE TABLE `tb_integral_mall_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_type_name` varchar(100) DEFAULT NULL COMMENT '商品类型名称',
  `goods_type_image` varchar(100) DEFAULT NULL COMMENT '商品类型图片',
  `goods_type_describe` varchar(500) DEFAULT NULL COMMENT '商品类型描述',
  `status` int(10) DEFAULT NULL COMMENT '商品类型状态: 0下架,1上架',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_dt` bigint(16) NOT NULL COMMENT '创建时间',
  `version` int(10) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分商城类型表';

#
# Data for table "tb_integral_mall_type"
#

INSERT INTO `tb_integral_mall_type` VALUES (1,'电影','integral_mall_type_1470297317934.png','电影',1,3,12345445,9),(2,'美食','integral_mall_type_1470297292257.jpg','美食',1,1,1470296919662,2),(3,'酒店','integral_mall_type_1470297333920.png','酒店',1,1,1470297336039,3);

INSERT INTO `hgl`.`tb_admin_permission` (`name`, `action_url`, `parent_node`, `child_node`, `remark`, `version`) VALUES 
('积分商城类型管理','/integralMallType/index',6,12,'90',0);

