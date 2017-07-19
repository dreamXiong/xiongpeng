alter table tb_coupon_info change obtainDateTime obtain_dt bigint
alter table tb_coupon_info change useDateTime use_dt bigint
alter table tb_coupon_info change operateDateTime operate_dt bigint


alter table tb_notice_info change createdDateTime create_dt bigint
alter table tb_notice_info change createdBy create_by varchar(100)


alter table tb_save_info change brandName brand_name varchar(200)
alter table tb_save_info change createdDateTime create_dt bigint
alter table tb_save_info change createdBy create_by bigint

alter table tb_shop_info change brandId brand_id int(10)
alter table tb_shop_info change coupon_gainAmt coupon_gain_amt double
alter table tb_shop_info change coupon_employAmt coupon_employ_amt double
alter table tb_shop_info change coupon_remainingAmt coupon_remaining_amt double
alter table tb_shop_info change isAutomatic_order is_automatic_order double