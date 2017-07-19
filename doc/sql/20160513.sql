
DELIMITER $$
DROP FUNCTION IF EXISTS `hgl`.`GetShortDistance` $$
CREATE FUNCTION `hgl`.`GetShortDistance`(lon1 double, lat1 double, lon2 double, lat2 double) RETURNS double
BEGIN
DECLARE   DEF_PI double DEFAULT 3.14159265359; ## PI
DECLARE   DEF_2PI double DEFAULT  6.28318530712; ##2*PI
DECLARE   DEF_PI180 double DEFAULT  0.01745329252; ## PI/180.0
DECLARE   DEF_R  double DEFAULT 6370693.5;
DECLARE  ew1, ns1, ew2, ns2, dx, dy, dew , distance double;
SET ew1 = lon1 * DEF_PI180;
SET ns1 = lat1 * DEF_PI180;
SET ew2 = lon2 * DEF_PI180;
SET ns2 = lat2 * DEF_PI180;
SET dew = ew1 - ew2;

if (dew > DEF_PI)
THEN SET dew = DEF_2PI - dew;
ELSEIF (dew < -DEF_PI)
THEN SET dew = DEF_2PI + dew;
END IF;
SET dx = DEF_R * cos(ns1) * dew; ## 东西方向长度(在纬度圈上的投影长度)
SET dy = DEF_R * (ns1 - ns2); ##南北方向长度(在经度圈上的投影长度)
##勾股定理求斜边长
SET distance = sqrt(dx * dx + dy * dy);
RETURN convert( distance*1.25/1000,decimal(8,2));
END $$
DELIMITER ;