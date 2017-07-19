package com.liguo.hgl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 * 
 * @author wzt
 * 
 */
public final class DateUtil {

    /**
     * 日期格式常量类：yyyy-MM-dd
     */
    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式常量类：yyyy-MM-dd HH:mm:ss
     */
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    
    private static final String YYYYMMDD = "yyyyMMdd";
    /**
     * 把日期加天数
     * 
     * @param date 需要处理日期
     * @param day 增加天数
     * @return Date
     */
    public static Date addDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 获取当前日期月的最后一天
     * @param date 日期
     * @return 最后一天
     */
    public static Integer getLastDay(Date date) {
        Integer year = getYear(date);
        Integer month = getMonth(date);
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String dayLast = df.format(cal.getTime());
        StringBuffer endStr = new StringBuffer().append(dayLast).append(" 23:59:59");
        Date date2 = strToDate(endStr.toString());
        return getDayDate(date2);
    }

    /**
     * 把日期加天数，返回结果
     * 
     * @param date 日期
     * @param day 增加天数
     * @return 新日期
     */
    public static Long addDay(Long date, int day) {
        if (date == null) {
            return null;
        }
        Date realDate = addDay(toRealDate(date), day);
        return toLongDate(realDate);
    }

    /**
     * 把日期加上指定分钟数，返回结果
     * 
     * @param date 日期
     * @param minutes 增加分钟数
     * @return 新日期
     */
    public static Date addMin(Date date, int minutes) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 把日期加上指定分钟数，返回结果
     * 
     * @param date 日期
     * @param hours 增加小时数
     * @return 新日期
     */
    public static Date addHour(Date date, int hours) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

    /**
     * 请参看Calender.get(field)函数
     * 返回给定日历字段的值
     * @param date  日期
     * @param field 给定日历字段
     * @return 对应字段值
     */
    public static int get(Date date, int field) {
        if (date == null) {
            return -1;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(field);
    }

    /**
     * 请参看Calender.get(field)函数
     * 
     * @param date 日期
     * @param field 给定日历字段
     * @return 对应字段值
     */
    public static int get(Long date, int field) {
        return get(toRealDate(date), field);
    }

    /**
     * 把长整形日期转换成真实日期 20150102030405 2015年1月2日3时4分5秒
     * 
     * @param date  数字型日期
     * @return 日期类型
     */
    public static Date toRealDate(Long date) {
        if (date == null){
            return null;
        }

        Calendar calendar = Calendar.getInstance();

        int year = (int) (date / 10000000000l);
        calendar.set(Calendar.YEAR, year);

        int month = (int) ((date % 10000000000l) / 100000000);
        calendar.set(Calendar.MONTH, month - 1);

        int day = (int) ((date % 100000000) / 1000000);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        int hour = (int) ((date % 1000000) / 10000);
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        int minute = (int) ((date % 10000) / 100);
        calendar.set(Calendar.MINUTE, minute);

        int second = (int) (date % 100);
        calendar.set(Calendar.SECOND, second);

        return calendar.getTime();
    }

    /**
     * 把日期转换成yyyyMMddHH格式
     * 
     * @param date 日期
     * @return 转换后的格式
     */
    public static Integer toYmdh(Date date) {
        if (date != null) {
            return Integer.parseInt(new SimpleDateFormat("yyyyMMddHH").format(date));
        }
        else {
            return null;
        }
    }

    /**
     * 把日期转换成yyyyMMdd格式
     * 
     * @param date 日期
     * @return 转换后的格式
     */
    public static Integer toYmd(Date date) {
        if (date != null) {
            return Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(date));
        }
        else {
            return null;
        }
    }

    /**
     * Date类型转换成Long型日期
     * 
     * @param date 日期
     * @return 转换后的格式
     */
    public static Long toLongDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return Long.valueOf(sdf.format(date));
    }

    /**
     * 把日期加月份
     * 
     * @param date 日期
     * @param month 增加月份
     * @return 计算后的日期
     */
    public static Date addMonth(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 设置当前月份的日期
     * @param date 日期
     * @param value 天
     * @return 转换日期
     */
    public static Date setDayOfMonth(Date date, int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, value);
        return c.getTime();
    }

    /**
     * 把日期加月份
     * 
     * @param date 日期
     * @param month 需要增加月份数
     * @return 转换后的日期
     */
    public static Long addMonth(Long date, int month) {
        if (date == null){
            return null;
        }
        Date realDate = addMonth(toRealDate(date), month);
        return toLongDate(realDate);
    }

    /**
     * 把日期的时分秒去除只留年月日
     * @param date 日期
     * @return 只留年月日的日期
     */
    public static Date clearTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.clear();
        calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, d);
        return calendar.getTime();
    }

    /**
     * 设置日期格式的时间部分包含：时、分、秒；如果某部分为负数，不修改此部分
     * 
     * @param date 日期
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 设置结果
     */
    public static Date setTime(Date date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (hour >= 0) {
            cal.set(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute >= 0) {
            cal.set(Calendar.MINUTE, minute);
        }
        if (second >= 0) {
            cal.set(Calendar.SECOND, second);
        }
        return cal.getTime();
    }

    /**
     * 把日期的时分秒去除只留年月日 20101230115511 == >20101230000000
     * 
     * @param date 传入日期
     * @return 只留年月日的日期
     */
    public static Long clearTime(Long date) {
        if (date == null) {
            return null;
        }
        return (date / 1000000) * 1000000;
    }

    /**
     * 日期转化为字串
     * 
     * @param date 传入日期
     * @param pattern 格式
     * @return 转化结果
     */
    public static String dateToStr(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 日期时间转化为字串yyyy-MM-dd HH:mm:SS
     * 
     * @param date 传入日期
     * @return 转化结果
     */
    public static String dateTimeToStr(Date date) {
        return dateToStr(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 把日期转化成"yyyy-MM-dd"格式的字串
     * 
     * @param date 传入日期
     * @return 转化结果
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, YYYY_MM_DD);
    }

    /**
     * 把日期转成日期想要的格式
     * 
     * @param date 传入日期
     * @param pattern 日期格式
     * @return 转化结果
     */
    public static Date dateToDatePattern(Date date, String pattern) {
        String dateStr = dateToStr(date, pattern);
        return strToDate(dateStr, pattern);
    }

    /**
     * 字串转化成日期
     * 
     * @param date 传入日期
     * @param pattern 转换格式
     * @return 转化结果
     */
    public static Date strToDate(String date, String pattern) {
        if (date == null || pattern == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字串转化成日期(不抛异常)
     * 
     * @param date 传入日期
     * @param pattern 转换格式
     * @return 转化结果
     */
    public static Date safeStrToDate(String date, String pattern) {
        if (date == null || pattern == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * 返回两个日期之间的天数差
     * 
     * @param d1 第一个日期
     * @param d2 第二个日期
     * @return 两个日期之间相差天数
     */
    public static int dateDiff(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            throw new RuntimeException("dateDiff方法两个参数不能为null!");
        }
        d1 = setTime(d1, 0, 0, 0);
        d2 = setTime(d2, 0, 0, 0);

        Long diff = (d1.getTime() - d2.getTime()) / 1000 / 60 / 60 / 24;
        return diff.intValue();
    }

    /**
     * 返回两个Date的月份
     * 
     * @param start 开始月份
     * @param end 结束月份
     * @return 两个月份相差结果
     */
    public static int dateMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if (startCalendar.get(Calendar.DATE) == 1 && temp.get(Calendar.DATE) == 1) {
            return year * 12 + month + 1;
        }
        else if (startCalendar.get(Calendar.DATE) != 1 && temp.get(Calendar.DATE) == 1) {
            return year * 12 + month;
        }
        else if (startCalendar.get(Calendar.DATE) == 1 && temp.get(Calendar.DATE) != 1) {
            return year * 12 + month;
        }
        else {
            return year * 12 + month - 1 < 0 ? 0 : year * 12 + month;
        }
    }

    /**
     * 把毫秒转化成日期
     * 
     * @param dateFormat 要转换的日期
     * @param millSec 毫秒数
     * @return 转换后的日期
     */
    public static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 返回两个日期之间的天数差 结果大于等于0..
     * 
     * @param d1 第一个日期
     * @param d2 第二个日期
     * @return 两个日期之间相差天数
     */
    public static Long dateDiffLong(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return 0L;
        }
        d1 = setTime(d1, 0, 0, 0);
        d2 = setTime(d2, 0, 0, 0);
        Long diff = (d1.getTime() - d2.getTime()) / 1000 / 60 / 60 / 24;
        if (diff < 0) {
            diff = 0L;
        }
        return diff;
    }

    /**
     * 返回两个日期之间的天数差
     * 
     * @param d1 第一个日期
     * @param d2 第二个日期
     * @return 两个日期之间相差天数
     */
    public static int dateDiff(Long d1, Long d2) {
        return dateDiff(toRealDate(d1), toRealDate(d2));
    }

    /**
     * 把"yyyy-MM-dd"格式的字串转化成日期
     * 
     * @param date 传入日期
     * @return 转换后的日期格式
     */
    public static Date strToDate(String date) {
        return strToDate(date, YYYY_MM_DD);
    }

    /**
     * 获取日期是周几
     * 
     * @param dt 传入日期
     * @return 星期几
     */
    public static int getWeekOfDate(Date dt) {
        int[] weekDays = {0, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 校验日期是否yyyy.MM.dd格式
     * @param date 传入日期
     * @return 校验结果
     */
    public static Boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date validDate = sdf.parse(date);
            String tmp = dateToStr(validDate, "yyyy.MM.dd");

            // 格式化之后相等说明日期合法
            if (date.equals(tmp)) {
                return true;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 取有效日期
     * 
     * @param year 年
     * @param month 月
     * @param day  日
     * @return 有效日期
     */
    public static Date getValidDate(int year, int month, int day) {
        int tempDay = day;
        String tempDate = "";
        Date validDate = null;

        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            try {
                tempDate = year + "-" + month + "-" + tempDay;
                validDate = sdf.parse(tempDate);
                String tmp = dateToStr(validDate, "yyyy-M-d");
                // System.out.println(v_date +"-" + tmp);
                // 格式化之后相等说明日期合法
                if (tempDate.equals(tmp)) {
                    return validDate;
                }

                tempDay = tempDay - 1;
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取年
     * 
     * @param date 传入日期
     * @return 日期年份
     */
    public static int getYear(Date date) {
        // 使用日历类
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 得到年
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取月
     * 
     * @param date 传入日期
     * @return 日期月份
     */
    public static int getMonth(Date date) {
        // 使用日历类
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 得到月
        int month = cal.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 获取日
     * 
     * @param date 传入日期
     * @return 日期天数
     */
    public static int getDayDate(Date date) {
        // 使用日历类
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 得到日
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取还款日期
     * 
     * @param date 传入日期
     * @param payday 还款天数
     * @return 还期日期
     */
    public static Date getEndDate(Date date, int payday) {
        return getValidDate(getYear(date), getMonth(date), payday);
    }
    /**
     * 把日期转化成"yyyyMMdd"格式的字串
     * 
     * @param date 传入日期
     * @return 转化结果
     */
    public static String dateToStryyyyMMdd(Date date) {
        return dateToStr(date, YYYYMMDD);
    }
}