
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'机械设备',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'五金工具',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'建筑五金',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'机电五金',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'锁具安防',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'日用五金',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'五金材料',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'通用配件',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'环保设施',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'厨房家电',	 '',	 NULL,	 '',	 NULL,	 NULL);
INSERT INTO `tb_product_type` (	 `parent_id`,	 `main_id`,	 `name`,	 `describes`,	 `version`,	 `icon`,	 `type`,	 `create_by`) VALUES (	 '0',	 '0',	'电子电工',	 '',	 NULL,	 '',	 NULL,	 NULL);

select @a:=id from tb_product_type  where name='机械设备';                                      
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'机床','1');                      
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'包装机械','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'园林机械','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'化工设备','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'食品加工','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'塑料机械','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'纺织设备','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'压缩系统','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'分离工控系统','1');


select @a:=id from tb_product_type  where name='五金工具';                                                                                              
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`) VALUES(@a,@a,'日用工具','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`) VALUES(@a,@a,'电动工具','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`) VALUES(@a,@a,'气动工具','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`) VALUES(@a,@a,'手动工具','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`) VALUES(@a,@a,'测量工具金刚石工具','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`) VALUES(@a,@a,'防爆工具','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`) VALUES(@a,@a,'起重工具','1');                     
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`) VALUES(@a,@a,'液压工具','1'); 
                                                                                        
select @a:=id from tb_product_type  where name='建筑五金';
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'门窗','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'室内照明','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'小五金','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'金属建材','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'卫浴洁具','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'管件','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'水暖五金','1');

select @a:=id from tb_product_type  where name='锁具安防';                                                                          
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'电动机','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'发电机','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'焊接设备','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'变速器','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'电热设备','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'内燃机','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'电机配件','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'泵','1');

select @a:=id from tb_product_type  where name='日用五金';                                                                          
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'工艺用品','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'家居用品','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'金属家具','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'刀剪','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'杯','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'休闲家具','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'个人护理用具','1');

select @a:=id from tb_product_type  where name='五金材料';                                                                          
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'不锈钢材','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'普通型钢','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'有色金属','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'金属丝、绳','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'金属粉末','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'普通管材','1');


select @a:=id from tb_product_type  where name='通用配件';                                                                          
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'汽摩配件','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'轴承','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'密封附件','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'模具','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'阀门','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'弹簧','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'磨具','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'紧固件','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'机械零部件','1');


select @a:=id from tb_product_type  where name='环保设施';                                                                          
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'原水处理','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'空气净化','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'过滤设备','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'污水处理','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'清洗清理','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'环保检测','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'节能设备','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'分析仪器','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'太阳能','1');


select @a:=id from tb_product_type  where name='厨房家电';                                                                          
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'厨房设备','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'餐具','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'整体厨房','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'厨具','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'炊具','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'污物桶：杯、壶','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'灶具','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'家用电器','1');

select @a:=id from tb_product_type  where name='电子电工';                                                                          
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'电子材料','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'电线电缆','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'电源','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'低压电器','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'连接器','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'变压器','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'绝缘材料','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'电工仪表仪器','1');
INSERT  INTO    `tb_product_type` (   `parent_id`,    `main_id`,  `name`, `type`  )   VALUES(@a,@a,'电池','1');