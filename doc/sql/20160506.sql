ALTER TABLE `tb_shop_level`
	ADD COLUMN `coupon_rule` double  DEFAULT 0 COMMENT '优惠卷使用规则 100快能使用1元优惠卷 则该值是1' AFTER `user_sale`;
	
TRUNCATE TABLE tb_shop_level;
insert into tb_shop_level values(1,'VIP1',0,1,0.98,1,2,'',null,null,1);
insert into tb_shop_level values(2,'VIP2',3000,1.2,0.95,1.1,2,'',null,null,1);
insert into tb_shop_level values(3,'VIP3',8000,1.5,0.92,1.2,3,'',null,null,1);
insert into tb_shop_level values(4,'VIP4',15000,1.8,0.9,1.3,3,'可参与某些特惠秒杀',null,null,1);
insert into tb_shop_level values(5,'VIP5',30000,2.2,0.88,1.4,3,'',null,null,1);
insert into tb_shop_level values(6,'VIP6',50000,2.8,0.85,1.5,4,'',null,null,1);
insert into tb_shop_level values(7,'VIP7',80000,3.2,0.84,1.6,4,'',null,null,1);
insert into tb_shop_level values(8,'VIP8',100000,3.8,0.83,1.7,5,'',null,null,1);
insert into tb_shop_level values(9,'VIP9',150000,4.5,0.8,5,2,'',null,null,1);
insert into tb_shop_level values(10,'VIP10',180000,5,0.79,2.5,6,'',null,null,1);