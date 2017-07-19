alter table tb_product_inventory add deleted int,
add createBy varchar(100),
add create_time bigint,
add lastUpdateBy varchar(100),
add last_update_time bigint;

alter table tb_product_inventory add spec  varchar(100),
add material varchar(100)