create table table tb_web_user_tm  as select * from tb_web_user;
delete from tb_web_user;
alter table tb_web_user change logoCode logo_code  varchar(100) NOT NULL COMMENT '我的标识码/推荐码';
alter table tb_web_user change recommendCode recommend_code   varchar(100) DEFAULT NULL COMMENT '被推荐码/我被推荐';
alter table tb_web_user change recommend_shopId recommend_shop_id   int(10) DEFAULT NULL COMMENT '定绑shopId';

insert into tb_web_user   select * from tb_web_user_tm;
drop table tb_web_user_tm;


create table tb_recommended_rules_tm  as select * from tb_recommended_rules;
delete from tb_recommended_rules;
alter table tb_recommended_rules change shopId shop_id  int(10) DEFAULT NULL COMMENT '经销商id';
insert into tb_recommended_rules   select * from tb_recommended_rules_tm;
drop table tb_recommended_rules_tm;


create table tb_merchants_tm  as select * from tb_merchants;
delete from tb_merchants;
alter table tb_merchants change brandId brand_id  int(10) NOT NULL DEFAULT '0' COMMENT '品牌id';
alter table tb_merchants change userId user_id  int(10) NOT NULL DEFAULT '0' COMMENT '用户id';
insert into tb_merchants   select * from tb_merchants_tm;
drop table tb_merchants_tm;


create table tb_agency_tm  as select * from tb_agency;
delete from tb_agency;
alter table tb_agency change brandId brand_id  int(10) NOT NULL DEFAULT '0' COMMENT '品牌id';
alter table tb_agency change merchantId merchant_id   int(10) NOT NULL COMMENT '招商id';
alter table tb_agency change shopId shop_id  int(10) NOT NULL COMMENT '经销商id';
alter table tb_agency change orderId order_id   varchar(100) DEFAULT NULL COMMENT '商品购买订单号';
alter table tb_agency change couponsOrderNumber coupons_order_number  varchar(100) DEFAULT NULL COMMENT '服务费订单号';

insert into tb_agency   select * from tb_agency_tm;

drop table tb_agency_tm;


create table tb_wap_order_service_tm  as select * from tb_wap_order_service;
delete from tb_wap_order_service;
alter table tb_wap_order_service change cityCode city_code  int(10) DEFAULT NULL COMMENT '城市code';

insert into tb_wap_order_service   select * from tb_wap_order_service_tm;

drop table tb_wap_order_service_tm;

create table tb_wap_address_history_tm  as select * from tb_wap_address_history;
delete from tb_wap_address_history;
alter table tb_wap_address_history change addressId address_id int(10) DEFAULT '0' COMMENT '收货地址表Id';

insert into tb_wap_address_history   select * from tb_wap_address_history_tm;

drop table tb_wap_address_history_tm;

 ALTER TABLE `tb_brand`
	CHANGE COLUMN `logoName` `logo_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '品牌名称' AFTER `name`,
	CHANGE COLUMN `productType_id` `product_type_id` INT(10) NULL DEFAULT '0' COMMENT '分类id' AFTER `remark`,
	CHANGE COLUMN `productType_name` `product_type_name` VARCHAR(200) NULL DEFAULT NULL AFTER `product_type_id`;

ALTER TABLE `tb_customer_service`
	CHANGE COLUMN `serviceType_id` `service_type_id` INT(10) NULL DEFAULT '0' COMMENT '服务分类id' AFTER `operation_id`,
	CHANGE COLUMN `cImgOne` `cimg_one` VARCHAR(100) NULL DEFAULT NULL COMMENT '服务图片1' AFTER `introduce`,
	CHANGE COLUMN `cImgTwo` `cimg_two` VARCHAR(100) NULL DEFAULT NULL COMMENT '服务图片2' AFTER `cimg_one`,
	CHANGE COLUMN `cImgThree` `cimg_three` VARCHAR(100) NULL DEFAULT NULL COMMENT '服务图片3' AFTER `cimg_two`;

ALTER TABLE `tb_inventory_lock`
	CHANGE COLUMN `Inventory_id` `inventory_id` INT(10) NULL DEFAULT NULL COMMENT '库存ID' AFTER `order_id`;

ALTER TABLE `tb_order_tracking`
	CHANGE COLUMN `operateBy` `operate_by` INT(10) NULL DEFAULT NULL COMMENT '经销商操作' AFTER `operate_name`,
	CHANGE COLUMN `operateByAdmin` `operate_by_admin` INT(10) NULL DEFAULT NULL COMMENT '后台操作ID' AFTER `operate_by`;

ALTER TABLE `tb_product`
	CHANGE COLUMN `productType_id` `product_type_id` INT(10) NULL DEFAULT '0' COMMENT '分类id' AFTER `price`,
	CHANGE COLUMN `saleNum` `sale_num` INT(10) NULL DEFAULT '0' COMMENT '销量' AFTER `product_type_id`,
	CHANGE COLUMN `pImgOne` `pimg_one` VARCHAR(100) NULL DEFAULT NULL COMMENT '产品图片1' AFTER `describes`,
	CHANGE COLUMN `pImgTwo` `pimg_two` VARCHAR(100) NULL DEFAULT NULL COMMENT '产品图片2' AFTER `pimg_one`,
	CHANGE COLUMN `pImgThree` `pimg_three` VARCHAR(100) NULL DEFAULT NULL COMMENT '产品图片3' AFTER `pimg_two`,
	CHANGE COLUMN `dImgOne` `dimg_one` VARCHAR(100) NULL DEFAULT NULL COMMENT '描述图片1' AFTER `pimg_three`,
	CHANGE COLUMN `dImgTwo` `dimg_two` VARCHAR(100) NULL DEFAULT NULL COMMENT '描述图片2' AFTER `dimg_one`,
	CHANGE COLUMN `dImgThree` `dimg_three` VARCHAR(100) NULL DEFAULT NULL COMMENT '描述图片3' AFTER `dimg_two`,
	CHANGE COLUMN `meterageUnit` `meterage_unit` VARCHAR(100) NULL DEFAULT NULL COMMENT '计量单位' AFTER `dimg_three`,
	CHANGE COLUMN `pickNo` `pick_no` INT(10) NULL DEFAULT NULL COMMENT '装箱数' AFTER `meterage_unit`;

ALTER TABLE `tb_wap_order_tracking`
	CHANGE COLUMN `operateBy` `operate_by` INT(10) NULL DEFAULT NULL COMMENT '操作人' AFTER `operate_name`;
	
create table tb_opened_regional_history  as select * from tb_opened_regional;
delete from tb_opened_regional;
alter table tb_opened_regional change proviceId provice_id int(11) DEFAULT NULL COMMENT '省ID' ;
insert into tb_opened_regional   select * from tb_opened_regional_history;
drop table tb_opened_regional_history;

ALTER TABLE `tb_wap_product`
	CHANGE COLUMN `batchNo` `batch_no` VARCHAR(50) NULL DEFAULT NULL COMMENT '批次号';
	CHANGE COLUMN `fileName` `file_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '导入文件名称';

ALTER TABLE `tb_order_group`
	CHANGE COLUMN `updateTime` `update_time` BIGINT(16) NULL DEFAULT NULL COMMENT '订单最新更新时间' AFTER `reissue_image`;
	
ALTER TABLE `tb_wap_order_group`
	CHANGE COLUMN `updateTime` `update_time` BIGINT(16) NULL DEFAULT NULL COMMENT '订单最新更新时间' AFTER `repairman_id`;
