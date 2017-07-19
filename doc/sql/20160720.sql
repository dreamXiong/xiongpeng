 
ALTER TABLE `tb_wap_order_tracking_service`
	CHANGE COLUMN `operateBy` `operate_by` INT(10) NULL DEFAULT NULL COMMENT '操作人' AFTER `operate_name`;
 
 
ALTER TABLE `tb_wap_address`
	CHANGE COLUMN `checkFlag` `check_flag` INT(10) NULL DEFAULT NULL COMMENT '是否选中' AFTER `telephone`;
 
  
ALTER TABLE `tb_address`
	CHANGE COLUMN `checkFlag` `check_flag` INT(10) NULL DEFAULT NULL COMMENT '是否选中' AFTER `telephone`;
 
 
ALTER TABLE `tb_wap_product`
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
 
 