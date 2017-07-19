CREATE TABLE `tb_system_config` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `config_key` varchar(100) NOT NULL COMMENT '系统配置key,唯一约束,且不能输入中文',
  `config_value` varchar(100) NOT NULL COMMENT '系统配置value',
  `remark` varchar(500) DEFAULT NULL COMMENT '系统配置描述',
  `delete_flag` int(10) DEFAULT '0' COMMENT '删除标识',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_dt` bigint(16) NOT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改人',
  `update_dt` bigint(16) NOT NULL COMMENT '修改时间',
  `extension_field` varchar(100) DEFAULT NULL COMMENT '扩展属性',
  `version` int(10) DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `config_key` (`config_key`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='系统配置表';