CREATE TABLE `tb_shop_info` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `shop_type` INT(10) NOT NULL  COMMENT '店铺类型，厂家或者经销商',
  `exp_level` int(10) DEFAULT NULLCOMMENT '等级',
  `exp` int(10) DEFAULT NULLCOMMENT '产生经验值',
  `shop_name` varchar(60) NOT NULL COMMENT '店铺名称',
  `company_name` varchar(60) DEFAULT NULL COMMENT '公司名称',
  `contract` varchar(20) DEFAULT NULLCOMMENT '联系人',
  `contract_phone` varchar(30) DEFAULT NULLCOMMENT '联系人联系方式',
  `company_tel`  varchar(30) DEFAULT NULLCOMMENT '公司座机',
  `legal_representative` varchar(30) DEFAULT NULLCOMMENT '法人代表',
  `reg_province` int(10) DEFAULT NULLCOMMENT '注册省',
  `reg_city` int(10) DEFAULT NULLCOMMENT '注册市',
  `reg_country` int(10) DEFAULT NULLCOMMENT '注册区县',
  `reg_street` int(10) DEFAULT NULLCOMMENT '注册街道',
  `reg_address` varchar(200) DEFAULT NULLCOMMENT '具体地址',  
  `productType_id` int(10) NOT NULLCOMMENT '经营的分类',
  `brand_name` varchar(30) DEFAULT NULLCOMMENT '品牌名称',
  `scope` varchar(200) DEFAULT NULLCOMMENT '经营范围',
  `sales` int(10)  NOT NULLCOMMENT '销售量',
  `taxpayer_image` varchar(50) DEFAULT NULLCOMMENT '一般纳税人图片',
  `license_image` varchar(50) DEFAULT NULLCOMMENT '营业执照图片',
  `organization_image` varchar(50) DEFAULT NULLCOMMENT '组织机构代码证图片',
  `shop_image1` varchar(50) DEFAULT NULLCOMMENT '实景图片',
  `shop_image2` varchar(50) DEFAULT NULLCOMMENT '实景图片',
  `shop_image3` varchar(50) DEFAULT NULLCOMMENT '实景图片',
  `ads_image` varchar(50) DEFAULT NULLCOMMENT '公司名称',
  `remark`   VARCHAR(500) COMMENT '备注,扩展字段',
  `auth_status` int(10) DEFAULT NULLCOMMENT '审核状态',
  `create_by` int(10) DEFAULT NULLCOMMENT '创建人',
  `create_dt` bigint(16) NOT NULLCOMMENT '创建时间',
  `version` INT(10) NULL DEFAULT NULLCOMMENT '版本号',
	PRIMARY KEY (`id`)
)
COMMENT=店铺表
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;

CREATE TABLE `tb_warehouse` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '仓库id',
  `warehouse_province` int(10) DEFAULT NULL COMMENT '注册省',
  `warehouse_city` int(10) DEFAULT NULL COMMENT '注册市',
  `warehouse_country` int(10) DEFAULT NULL COMMENT '注册区县',
  `warehouse_street` int(10) DEFAULT NULL COMMENT '注册街道',
  `warehouse_address` varchar(200) DEFAULT NULL COMMENT '具体地址',  
  `shop_type` varchar(60) COMMENT '仓库类型',
  `warehouse_name` varchar(60) NOT NULL COMMENT '仓库名称',
  `contract` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contract_phone` varchar(30) DEFAULT NULL COMMENT '联系人联系方式',
  `warehouse_tel`  varchar(30) DEFAULT NULL COMMENT '仓库座机',
  `manage_user` int(10) DEFAULT NULL COMMENT '仓库管理员帐号',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_dt` bigint(16) NOT NULL COMMENT '创建时间',
  `version` INT(10) NULL DEFAULT NULL COMMENT '版本号',
	PRIMARY KEY (`id`)
)
COMMENT='仓库表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;

ALTER TABLE `tb_warehouse`
	ADD COLUMN `states` INT(5) NULL DEFAULT NULL COMMENT '状态：1 启用，0停用' AFTER `create_by`;

CREATE TABLE `tb_warehouse_user` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `warehouse_id` INT(10) NOT NULL COMMENT '仓库id',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `shop_type` varchar(60) COMMENT '仓库类型',
  `warehouse_name` varchar(60) NOT NULL COMMENT '仓库名称',
  `warehouse_tel`  varchar(30) DEFAULT NULL COMMENT '仓库座机',
  `contract` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contract_phone` varchar(30) DEFAULT NULL COMMENT '联系人联系方式',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_dt` bigint(16) NOT NULL COMMENT '创建时间',
  `version` INT(10) NULL DEFAULT NULL COMMENT '版本号',
	PRIMARY KEY (`id`)
)
COMMENT='仓库用户表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;

