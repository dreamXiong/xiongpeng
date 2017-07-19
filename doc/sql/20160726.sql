INSERT INTO `tb_system_config` VALUES ('6', 'signIntegral', '20', '一次签到获得的积分', '0', null, '0', 'admin', '0', null, '0');
INSERT INTO `tb_system_config` VALUES ('7', 'orderInregral', '1', '订单积分规则', '0', null, '0', 'admin', '0', null, '0');

CREATE TABLE `tb_company_type` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '分类id',
	`name` VARCHAR(100) NULL DEFAULT '0' COMMENT '分类名称',
	`describes` VARCHAR(200) NULL DEFAULT NULL COMMENT '分类描述',
	`img_name` VARCHAR(30) NULL DEFAULT NULL COMMENT '页面图片',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='服务公司分类型表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
CREATE TABLE `tb_company_service` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '公司id',
	`type_id` INT(10) NULL DEFAULT NULL COMMENT '公司类型id',
	`company_name` VARCHAR(60) NULL DEFAULT NULL COMMENT '公司名称',
	`contract` VARCHAR(20) NULL DEFAULT NULL COMMENT '联系人',
	`contract_phone` VARCHAR(30) NULL DEFAULT NULL COMMENT '联系人联系方式',
	`company_tel` VARCHAR(30) NULL DEFAULT NULL COMMENT '公司座机',
	`reg_province` INT(10) NULL DEFAULT NULL COMMENT '注册省',
	`reg_city` INT(10) NULL DEFAULT NULL COMMENT '注册市',
	`reg_country` INT(10) NULL DEFAULT NULL COMMENT '注册区县',
	`reg_street` INT(10) NULL DEFAULT NULL COMMENT '注册街道',
	`reg_address` VARCHAR(200) NULL DEFAULT NULL COMMENT '具体地址',
	`scope` VARCHAR(200) NULL DEFAULT NULL COMMENT '经营范围',
	`company_image1` VARCHAR(50) NULL DEFAULT NULL COMMENT '实景图片',
	`company_image2` VARCHAR(50) NULL DEFAULT NULL COMMENT '实景图片',
	`company_image3` VARCHAR(50) NULL DEFAULT NULL COMMENT '实景图片',
	`remark` VARCHAR(500) NULL DEFAULT NULL COMMENT '备注,扩展字段',
	`describes` VARCHAR(500) NULL DEFAULT NULL COMMENT '公司描述',
	`lon` DOUBLE UNSIGNED NULL DEFAULT NULL COMMENT '公司的纵坐标',
	`lat` DOUBLE UNSIGNED NULL DEFAULT NULL COMMENT '公司的横坐标',
	`version` INT(10) NOT NULL DEFAULT '0' COMMENT '版本号',
	PRIMARY KEY (`id`)
)
COMMENT='服务公司表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
CREATE TABLE `tb_company_consult` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '分类id',
	`user_id` INT(10) NULL DEFAULT NULL COMMENT '用户id',
	`contract` VARCHAR(20) NULL DEFAULT NULL COMMENT '联系人',
	`contract_phone` VARCHAR(30) NULL DEFAULT NULL COMMENT '用户电话号码',
	`company_id` INT(10) NULL DEFAULT NULL COMMENT '公司id',
	`create_dt` BIGINT(16) UNSIGNED NULL DEFAULT NULL COMMENT '时间',
	`describes` VARCHAR(200) NULL DEFAULT NULL COMMENT '描述',
	`version` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COMMENT='客户咨询信息表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;

INSERT INTO `hgl`.`tb_admin_permission` (`name`, `action_url`, `parent_node`, `child_node`, `remark`, `version`) VALUES ('类型管理', '', 63, 0, '63', 0);
INSERT INTO `hgl`.`tb_admin_permission` (`name`, `action_url`, `parent_node`, `child_node`, `remark`, `version`) VALUES ('公司类型列表', '/companyType/index', 63, 1, '1', 0);
INSERT INTO `hgl`.`tb_admin_permission` (`name`, `action_url`, `parent_node`, `child_node`, `remark`, `version`) VALUES ('服务公司列表', '/companyService/index', 63, 2, '2', 0);
INSERT INTO `hgl`.`tb_admin_permission` (`name`, `action_url`, `parent_node`, `child_node`, `remark`, `version`) VALUES ('公司信息咨询', '/companyConsult/index', 63, 3, '3', 0);