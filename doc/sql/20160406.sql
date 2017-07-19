CREATE TABLE `tb_merchants` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '招商id',
  `brandId` int(10) NOT NULL COMMENT '品牌id',
  `userId` int(10) NOT NULL COMMENT '用户id',
  `type` int(10) DEFAULT NULL COMMENT '类型',
  `level` int(10) DEFAULT NULL COMMENT '招商层次',
  `number` double NOT NULL COMMENT '招商购买量',
  `other` varchar(200) DEFAULT NULL COMMENT '其他',
  `coupons` double NOT NULL COMMENT '优惠卷',
  `address` int(10) DEFAULT '0' COMMENT '招商区域',
  `views` int(10) DEFAULT '0' COMMENT '围观商家',
  `winning` int(10) DEFAULT '0' COMMENT '已中标商家',
  `participate` int(10) DEFAULT '0' COMMENT '参与商家',
  `province` int(10) DEFAULT '0' COMMENT '省',
  `city` int(10) DEFAULT '0' COMMENT '市',
  `country` int(10) DEFAULT '0' COMMENT '区',
  `street` int(10) DEFAULT '0' COMMENT '街镇',
  `places` int(10) DEFAULT '0' COMMENT '招商位数',
  `state` int(10) DEFAULT '0' COMMENT '状态',
  `reserved` int(10) DEFAULT NULL COMMENT '预留字段int',
  `reserved1` varchar(200) DEFAULT NULL COMMENT '预留字段String',
  `version` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='招商表';

CREATE TABLE `tb_statistical` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '统计id',
  `merchansId` int(10) NOT NULL COMMENT '招商id',
  `type` int(10) DEFAULT NULL COMMENT '类型',
  `ip` varchar(200) DEFAULT NULL COMMENT 'ip地址',
  `browser` varchar(200) DEFAULT NULL COMMENT '浏览器',
  `create_time` varchar(300) DEFAULT NULL COMMENT '创建时间',
  `state` int(10) DEFAULT '0' COMMENT '状态',
  `reserved` int(10) DEFAULT NULL COMMENT '预留字段int',
  `reserved1` varchar(200) DEFAULT NULL COMMENT '预留字段String',
  `version` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='统计表';


alter table tb_merchants 
add create_time bigint,
add release_time bigint,
add last_update_time bigint;
