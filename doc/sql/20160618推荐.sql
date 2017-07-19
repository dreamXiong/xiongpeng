 CREATE TABLE `tb_recommended_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '推荐类型id',
  `recommended_type` int(10) DEFAULT '0' COMMENT '推荐人类型',
  `is_recommended_type` int(10) DEFAULT '0' COMMENT '被推荐人类型',
  `way` int(10) DEFAULT '0' COMMENT '方式：0 直接给予，1，经予',
  `reward_type` int(10) DEFAULT '0' COMMENT '奖励类型：0 积分，1金额',
   `reward_way` int(10) DEFAULT '0' COMMENT '奖励方式：0 百分比，1固定',
  `reward` int(10) DEFAULT '0' COMMENT '奖励',
  `describes` varchar(200) DEFAULT NULL COMMENT '描述',
  `update_by` int(10) DEFAULT NULL COMMENT '修改用户人id',
  `update_dt` bigint(16) DEFAULT NULL COMMENT '修改时间',
  `version` int(10) DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='推荐类型表';



INSERT INTO `tb_recommended_type` VALUES (1,106,106,0,0,1,10,'终端推荐终端直接奖励10积分',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (2,106,114,0,0,1,20,'终端推荐师傅直接奖励20积分',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (3,106,104,0,1,1,50,'终端推荐经销商直接奖励50元',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (4,114,106,0,0,1,10,'师傅推荐终端直接奖励10积分',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (5,114,114,0,0,0,5,'师傅推荐师傅直接奖励首单5%',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (6,114,104,0,0,1,10,'师傅推荐经销商直接奖励10积分',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (7,104,106,0,0,1,10,'经销商推荐终端直接奖励10积分',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (8,104,114,0,0,1,10,'经销商推荐师傅直接奖励10积分',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (9,104,104,0,1,1,100,'经销商推荐经销商直接奖励100元',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (10,108,106,0,0,1,10,'递推人员推荐终端直接奖励10积分',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (11,108,114,0,0,1,10,'递推人员推荐师傅直接奖励10积分',NULL,NULL,0);

INSERT INTO `tb_recommended_type` VALUES (12,108,104,0,1,1,100,'递推人员推荐经销商直接奖励100元',NULL,NULL,0);


 CREATE TABLE `tb_recommended` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '推荐id',
  `recommended` int(10) DEFAULT '0' COMMENT '推荐人Id',
  `is_recommended` int(10) DEFAULT '0' COMMENT '被推荐人Id',
   `recommended_type` int(10) DEFAULT '0' COMMENT '推荐人类型',
  `is_recommended_type` int(10) DEFAULT '0' COMMENT '被推荐人类型',
   `recommended_contact_type` int(10) DEFAULT '0' COMMENT '推荐之间的相互联系类型tb_recommended_type',
    `type` int(10) DEFAULT '0' COMMENT '收益类型：0 单位为积分 1单位为元',
  `earnings` int(10) DEFAULT '0' COMMENT '收益',
  `order_type` int(10) DEFAULT '0' COMMENT '订单类型',
   `first_order_Id` int(10) DEFAULT '0' COMMENT '首单Id',
   `money` double DEFAULT '0' COMMENT '首单金额',
    `reserved` int(10) DEFAULT '0' COMMENT '预留',
  `describes` varchar(200) DEFAULT NULL COMMENT '描述',
  `update_by` int(10) DEFAULT NULL COMMENT '修改用户人id',
  `update_dt` bigint(16) DEFAULT NULL COMMENT '修改时间',
  `version` int(10) DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='推荐表';