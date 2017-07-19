CREATE TABLE `tb_shop_level` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '等级id',
  `level_name` varchar(60) NOT NULL COMMENT '等级名称',
  `min_exp` int(10) DEFAULT 0 COMMENT '最小经验值',
  `exp_proportion` double DEFAULT 1 COMMENT '经验折算规则',
  `user_sale` double DEFAULT 1  COMMENT '用户优惠折扣',
  `cash_num` int(10) DEFAULT 0  COMMENT '体现次数',
  `level_remark` varchar(60) DEFAULT NULL COMMENT '等级说明',  
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改人',
  `update_dt` bigint(16) DEFAULT NULL COMMENT '修改时间',
  `version` int(10) DEFAULT '0' COMMENT '版本',
   PRIMARY KEY (`id`)
)
COMMENT='等级表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18;

insert into tb_shop_level values(1,'VIP1',0,1,0.98,2,'',null,null,1);
insert into tb_shop_level values(2,'VIP2',3000,1.2,0.95,2,'',null,null,1);
insert into tb_shop_level values(3,'VIP3',8000,1.5,0.92,3,'',null,null,1);
insert into tb_shop_level values(4,'VIP4',15000,1.8,0.9,3,'可参与某些特惠秒杀',null,null,1);
insert into tb_shop_level values(5,'VIP5',30000,2.2,0.88,3,'',null,null,1);
insert into tb_shop_level values(6,'VIP6',50000,2.8,0.85,4,'',null,null,1);
insert into tb_shop_level values(7,'VIP7',80000,3.2,0.84,4,'',null,null,1);
insert into tb_shop_level values(8,'VIP8',100000,3.8,0.83,5,'',null,null,1);
insert into tb_shop_level values(9,'VIP9',150000,4.5,0.8,5,'',null,null,1);
insert into tb_shop_level values(10,'VIP10',180000,5,0.79,6,'',null,null,1);


