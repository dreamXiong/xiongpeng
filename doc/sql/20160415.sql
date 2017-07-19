CREATE TABLE `tb_web_user` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`user_name` VARCHAR(32) NOT NULL COMMENT '登录用户名',
	`password` VARCHAR(128) NOT NULL COMMENT '密码',
	`address` VARCHAR(256)  COMMENT '常用地址',
	`email` VARCHAR(128) COMMENT '邮箱地址',
	`mobile` VARCHAR(20) NOT NULL COMMENT '手机',
	`name` VARCHAR(32) NOT NULL COMMENT '姓名',
	`role_id` INT(10) NOT NULL COMMENT '角色id',
	`type_id` INT(10) NOT NULL COMMENT '类型id,厂家 经销商 终端客户 仓库管理员 地推人员',
	`shop_id` INT(10) NOT NULL COMMENT '店铺id',
	`role_id` INT(10) NOT NULL COMMENT '角色id',
	`id_card` VARCHAR(32) COMMENT '扩展字段_身份证',
	`remark`   VARCHAR(500) COMMENT '备注,扩展字段',
	`version` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='前台用户表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;

CREATE TABLE `tb_web_role` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '角色id',
	`name` VARCHAR(32) NOT NULL COMMENT '角色名称',
	`permission_ids` VARCHAR(256) COMMENT '权限ids',
	`remark`   VARCHAR(500) COMMENT '备注,扩展字段',
	`version` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='前台用户角色表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;

CREATE TABLE `tb_web_permission` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '权限id',
	`name` VARCHAR(32) NOT NULL COMMENT '权限名称',
	`action_url` VARCHAR(256) COMMENT '权限url',
	`remark`   VARCHAR(500) COMMENT '备注,扩展字段',
	`version` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='前台权限表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;


CREATE TABLE `tb_admin_user` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`user_name` VARCHAR(32) NOT NULL COMMENT '登录用户名',
	`password` VARCHAR(128) NOT NULL COMMENT '密码',
	`address` VARCHAR(256)  COMMENT '常用地址',
	`email` VARCHAR(128) COMMENT '邮箱地址',
	`mobile` VARCHAR(20)  COMMENT '手机',
	`name` VARCHAR(32)  COMMENT '姓名',
	`role_id` INT(10) NOT NULL COMMENT '角色id',
	`id_card` VARCHAR(32) COMMENT '扩展字段_身份证',
	`remark`   VARCHAR(500) COMMENT '备注,扩展字段',
	`version` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='后台用户表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;

CREATE TABLE `tb_admin_role` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '角色id',
	`name` VARCHAR(32) NOT NULL COMMENT '角色名称',
	`permission_ids` VARCHAR(256) COMMENT '权限ids',
	`remark`   VARCHAR(500) COMMENT '备注,扩展字段',
	`version` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='后台用户角色表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;

CREATE TABLE `tb_admin_permission` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '权限id',
	`name` VARCHAR(32) NOT NULL COMMENT '权限名称',
	`action_url` VARCHAR(256) COMMENT '权限url',
	`remark`   VARCHAR(500) COMMENT '备注,扩展字段',
	`version` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='后台权限表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;