package com.linkon.admin.custom.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 时间操作工具类
 * @version 1.0
 * @version 1.1 添加了 parseDateTimeFromCertDate方法，用于处理证书中得到的特殊格式的日期。
 * 
 */
public class DateUtil {

    private static String[] MONTH_IN_YEAR_EN = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

    private static String CERT_DATE_PATTERN = "EEE MMM d HH:mm:ss 'UTC'Z yyyy";

    /**
     * 对于DateFormatSymbols.setWeekdays()方法，所需的String[] DAY_IN_WEEK数组，
     *         必须提供8个String元素，其中第一个元素是一个无意义的值。
     */
    private static String[] DAY_IN_WEEK = new String[] { "NAN", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

    /**
     * 格式化为 yyyy-MM-dd 的字符串
     * 
     * @param date
     *            Date类的实例
     * @return
     */
    static public String getDateStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 格式化为 yyyy年MM月dd日 的字符串
     * 
     * @param date
     *            Date类的实例
     * @return
     */
    static public String getDateStrC(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    /**
     * 格式化为 yyyyMMdd 的字符串
     * 
     * @param date
     *            Date类的实例
     * @return
     */
    static public String getDateStrCompact(Date date) {

        if (date == null)
            return "";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        return str;
    }

    /**
     * 格式化为 yyyy-MM-dd HH:mm:ss 的字符串
     * 
     * @param date
     *            Date类的实例
     * @return
     */
    static public String getDateTimeStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 格式化为 yyyy年MM月dd日 HH时mm分ss秒 的字符串
     * 
     * @param date
     *            Date类的实例
     * @return
     */
    static public String getDateTimeStrC(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return format.format(date);
    }

    /**
     * 格式化为指定格式的字符串
     * 
     * @param pattern
     *            格式字符串
     * @return
     */
    public static String getCurDateStr(String pattern) {

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    /**
     * 把yyyy-MM格式的日期字符串转换为Date类型
     * 
     * @param s
     *            yyyy-MM格式的日期字符串
     * @return
     */
    public static Date parseDateM(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.parse(s);
    }

    /**
     * 把yyyy-MM-dd格式的日期字符串转换为Date类型
     * 
     * @param s
     *            yyyy-MM-dd格式的日期字符串
     * @return Date
     * @throws java.text.ParseException
     */
    public static Date parseDate(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(s);
    }

    public static Date parseDate(String dateStr, String formatStr) {

        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    /**
     * 把 yyyy年MM月dd日 格式的日期字符串转换为Date类型
     *
     * @param s
     *            yyyy年MM月dd日 格式的日期字符串
     * @return
     * @throws java.text.ParseException
     */
    public static Date parseDateC(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.parse(s);
    }

    /**
     * 把 yyyy-MM-dd HH:mm:ss 格式的日期字符串转换为Date类型
     *
     * @param s
     *            yyyy-MM-dd HH:mm:ss
     * @return
     * @throws java.text.ParseException
     */
    public static Date parseDateTime(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(s);
    }

    /**
     * 解析从证书中读到的日期时间字符串。
     *
     * @param s 字符串格式为
     *            ："Thu Mar 29 15:43:55 UTC+0800 2012"
     *
     *
     * @return 返回Date对象。
     *
     * @throws java.text.ParseException
     *             对中英文月份名称和周日期名称都尝试失败后抛出异常。
     *
     * @modify 2011-06-30 龙轶星： 测试时发现要支持形如"Thu Mar 29 15:43:55 UTC 0800 2012"的格式。
     *         因为这里的0800不是SimpleDateFormat默认支持的格式， 所以在本方法中进行特殊处理。（临时方案）
     *
     * @modify 2011-07-05 JDK Bug： "Dec 7 09:38:46 UTC+0800 2014"解析不通过。
     *         最新解决方案：将最前面的Day In Week字符去掉，然后再提取Date对象。
     *
     * @modify 2011-07-06龙轶星： 重新选用新的解决方案：
     *         对于DateFormatSymbols.setWeekdays()方法，所需的String[] DAY_IN_WEEK数组，
     *         必须提供8个String元素，其中第一个元素是一个无意义的值。
     */
    public static Date parseDateTimeFromCertDate(String s) throws ParseException {

        Date result = null;
        SimpleDateFormat formatForCertDate;

        formatForCertDate = new SimpleDateFormat(CERT_DATE_PATTERN);
        try {
            if (s.contains("UTC ")) {
                s = s.replaceAll("UTC ", "UTC+");
            }

            result = formatForCertDate.parse(s);

            return result;
        } catch (ParseException e) {
            // ignore.
        }

        // 默认的Symbols尝试不成功，改尝试英文月份，周日期名称。
        DateFormatSymbols symbols = formatForCertDate.getDateFormatSymbols();

        symbols.setMonths(MONTH_IN_YEAR_EN);
        symbols.setWeekdays(DAY_IN_WEEK);
        symbols.setShortMonths(MONTH_IN_YEAR_EN);
        symbols.setShortWeekdays(DAY_IN_WEEK);

        formatForCertDate.setDateFormatSymbols(symbols);

        return formatForCertDate.parse(s);
    }

    public static String formatDateTimeFromCertDate(Date date) throws ParseException {

        SimpleDateFormat formatForCertDate;

        formatForCertDate = new SimpleDateFormat(CERT_DATE_PATTERN);

        // 默认的Symbols尝试不成功，改尝试英文月份，周日期名称。
        DateFormatSymbols symbols = formatForCertDate.getDateFormatSymbols();

        symbols.setMonths(MONTH_IN_YEAR_EN);
        symbols.setWeekdays(DAY_IN_WEEK);
        symbols.setShortMonths(MONTH_IN_YEAR_EN);
        symbols.setShortWeekdays(DAY_IN_WEEK);

        formatForCertDate.setDateFormatSymbols(symbols);

        return formatForCertDate.format(date);
    }

    /**
     * 把Date转换为 yyyy-MM-dd 格式的字符串类型
     *
     * @param s
     *            Date
     * @return
     * @throws java.text.ParseException
     */
    public static String parseString(Date s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(s);
    }

    /**
     * 把 yyyy-MM-dd 格式的日期字符串转换为Date类型
     *
     * @param s
     *            yyyy-MM-dd 格式的日期字符串
     * @return
     * @throws java.text.ParseException
     */
    public static Date parseStr(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(s);
    }

    /**
     * 把 yyyy-MM-dd HH:mm:ss 格式的日期字符串转换为Date类型
     *
     * @param s
     *            yyyy-MM-dd HH:mm:ss
     * @return
     * @throws java.text.ParseException
     */
    public static Date parseStrDate(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(s);
    }

    /**
     * 把 yyyy年MM月dd日 HH时mm分ss秒 格式的日期字符串转换为Date类型
     *
     * @param s
     *            yyyy年MM月dd日 HH时mm分ss秒
     * @return
     * @throws java.text.ParseException
     */
    static public Date parseDateTimeC(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return format.parse(s);
    }

    /**
     * 把 HH:mm:ss 格式的日期字符串转换为Date类型
     *
     * @param s
     *            HH:mm:ss
     * @return
     * @throws java.text.ParseException
     */
    static public Date parseTime(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.parse(s);
    }
    
    /**
     * 把 日期字符串转换为Date类型
     *
     * @param s 要转化的字符串
     * @param pattern 要转化的格式
     * @return
     * @throws java.text.ParseException
     */
    static public Date parseTimeByPattern(String s,String pattern) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(s);
        
    }

    /**
     * 把 HH:mm 格式的日期字符串转换为Date类型
     *
     * @param s
     *            HH:mm
     * @return
     * @throws java.text.ParseException
     */
    static public Date parseTimeToHHMM(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.parse(s);
    }

    /**
     * 把 HH时mm分ss秒 格式的日期字符串转换为Date类型
     *
     * @param s
     *            HH时mm分ss秒
     * @return
     * @throws java.text.ParseException
     */
    static public Date parseTimeC(String s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒");
        return format.parse(s);
    }

    /**
     * 获取当前时间的年份
     *
     * @param s
     *            Date
     * @return yyyy
     * @throws java.text.ParseException
     */
    static public int yearOfDate(Date s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(s);
        return Integer.parseInt(d.substring(0, 4));
    }

    /**
     * 获取当前时间的月份
     *
     * @param s
     *            Date
     * @return mm
     * @throws java.text.ParseException
     */
    static public int monthOfDate(Date s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(s);
        return Integer.parseInt(d.substring(5, 7));
    }

    /**
     * 获取当前时间的日期
     *
     * @param s
     *            Date
     * @return dd
     * @throws java.text.ParseException
     */
    static public int dayOfDate(Date s) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(s);
        return Integer.parseInt(d.substring(8, 10));
    }

    /**
     * 获取2个日期的间隔月数。
     *
     * @param sd
     *            开始日期
     * @param ed
     *            结束日期
     * @return 相隔月数
     * @throws java.text.ParseException
     */
    static public int diffDateM(Date sd, Date ed) throws ParseException {

        return (ed.getYear() - sd.getYear()) * 12 + ed.getMonth() - sd.getMonth() + 1;
    }

    /**
     * 获取2个日期的间隔天数。
     *
     * @param sd
     *            开始日期
     * @param ed
     *            结束日期
     * @return 相隔天数
     * @throws java.text.ParseException
     */
    static public int diffDateD(Date sd, Date ed) throws ParseException {

        return Math.round((ed.getTime() - sd.getTime()) / 86400000) + 1;
    }

    /**
     * @param sym
     * @param eym
     * @return
     * @throws java.text.ParseException
     */
    static public int diffDateM(int sym, int eym) throws ParseException {

        return (Math.round(eym / 100) - Math.round(sym / 100)) * 12 + (eym % 100 - sym % 100) + 1;
    }

    /**
     * 获取下个月的第一天的日期
     *
     * @param date
     *            当前日期
     * @return java.sql.Date
     * @throws java.text.ParseException
     */
    static public Date getNextMonthFirstDate(Date date) throws ParseException {

        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.MONTH, 1);
        scalendar.set(Calendar.DATE, 1);
        return new Date(scalendar.getTime().getTime());
    }

    /**
     * 获取当前日期的前几天。
     *
     * @param date
     *            当前日期
     * @param dayCount
     *            指定是前几天
     * @return
     * @throws java.text.ParseException
     */
    static public Date getFrontDateByDayCount(Date date, int dayCount) throws ParseException {

        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.DATE, -dayCount);
        return new Date(scalendar.getTime().getTime());
    }

    /**
     * 获取指定日期的前几天。
     *
     * @param s
     *            当前日期<yyyy-MM-dd>
     * @param dayCount
     *            指定是前几天
     * @return yyyy-MM-dd格式返回
     * @throws java.text.ParseException
     */
    static public String getFrontDateByDayCountToStr(String s, int dayCount) throws ParseException {

        Date date = parseStr(s);
        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.DATE, -dayCount);
        return parseString(new Date(scalendar.getTime().getTime()));
    }

    /**
     * 获取指定日期的后几天。
     *
     * @param date
     *            当前日期<yyyy-MM-dd>
     * @param dayCount
     *            指定是后几天
     * @return yyyy-MM-dd格式返回
     * @throws java.text.ParseException
     */
    static public String getAfterDateByDayCountToStr(Date date, int dayCount) throws ParseException {

        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.DATE, +dayCount);
        return parseString(new Date(scalendar.getTime().getTime()));
    }

    /**
     * 获取指定日期的后几天。
     *
     * @param date
     *            当前日期<yyyy-MM-dd>
     * @param dayCount
     *            指定是后几天
     * @return yyyy-MM-dd格式返回
     * @throws java.text.ParseException
     */
    static public Date getAfterDateByDayCount(Date date, int dayCount) throws ParseException {

        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.DATE, +dayCount);
        return scalendar.getTime();
    }

    /**
     * 获取当前月的第一天
     *
     * @param year
     *            当前年份
     * @param month
     *            当前月份
     * @return Date
     * @throws java.text.ParseException
     */
    static public Date getFirstDay(String year, String month) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(year + "-" + month + "-1");
    }

    /**
     * 获取当前月的第一天
     *
     * @param year
     *            当前年份
     * @param month
     *            当前月份
     * @return Date
     * @throws java.text.ParseException
     */
    static public Date getFirstDay(int year, int month) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(year + "-" + month + "-1");
    }

    /**
     * 获取当前月的最后一天
     *
     * @param year
     *            当前年份
     * @param month
     *            当前月份
     * @return Date
     * @throws java.text.ParseException
     */
    static public Date getLastDay(String year, String month) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(year + "-" + month + "-1");

        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.MONTH, 1);
        scalendar.add(Calendar.DATE, -1);
        date = scalendar.getTime();
        return date;
    }

    /**
     * 获取当前月的最后一天
     *
     * @param year
     *            当前年份
     * @param month
     *            当前月份
     * @return Date
     * @throws java.text.ParseException
     */
    static public Date getLastDay(int year, int month) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(year + "-" + month + "-1");

        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.MONTH, 1);
        scalendar.add(Calendar.DATE, -1);
        date = scalendar.getTime();
        return date;
    }

    /**
     * 获取当前日期按 YYYY-MM-DD 格式化
     *
     * @return String
     */

    static public String getTodayStr() throws ParseException {

        Calendar calendar = Calendar.getInstance();
        return getDateStr(calendar.getTime());
    }

    /**
     * 获取当前日期
     *
     * @return Date
     * @throws java.text.ParseException
     */
    static public Date getToday() throws ParseException {

        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前日期(Timestamp)
     *
     * @return String
     */
    static public String getTodayAndTime() {

        return new Timestamp(System.currentTimeMillis()).toString();
    }

    /**
     * 获取当前日期,按 yyyy年MM月dd日 格式化
     *
     * @return
     * @throws java.text.ParseException
     */
    static public String getTodayC() throws ParseException {

        Calendar calendar = Calendar.getInstance();
        return getDateStrC(calendar.getTime());
    }

    /**
     * 获取当前时间的年和月 (YYYYMM)
     *
     * @return int
     * @throws java.text.ParseException
     */
    static public int getThisYearMonth() throws ParseException {

        return getYearMonth(Calendar.getInstance().getTime());
    }

    /**
     * 获取当前时间的年和月 (YYYYMM)
     *
     * @param date
     *            当前时间
     * @return
     * @throws java.text.ParseException
     */
    static public int getYearMonth(Date date) throws ParseException {

        return (date.getYear() + 1900) * 100 + date.getMonth() + 1;
    }

    /**
     * 获取相隔月数 (yyyy-MM-dd)
     *
     * @param beforedate
     *            开始日期
     * @param afterdate
     *            结束日期
     * @return
     * @throws java.text.ParseException
     */
    static public long getDistinceMonth(String beforedate, String afterdate) throws ParseException {

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        long monthCount = 0;
        try {
            Date d1 = d.parse(beforedate);
            Date d2 = d.parse(afterdate);

            monthCount = (d2.getYear() - d1.getYear()) * 12 + d2.getMonth() - d1.getMonth();
            // dayCount = (d2.getTime()-d1.getTime())/(30*24*60*60*1000);

        } catch (ParseException e) {
            System.out.println("Date parse error!");
            // throw e;
        }
        return monthCount;
    }

    /**
     * 获取相隔天数
     *
     * @param beforedate
     *            开始日期
     * @param afterdate
     *            结束日期
     * @return long
     * @throws java.text.ParseException
     */
    static public long getDistinceDay(String beforedate, String afterdate) throws ParseException {

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        long dayCount = 0;
        try {
            Date d1 = d.parse(beforedate);
            Date d2 = d.parse(afterdate);

            dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

        } catch (ParseException e) {
            System.out.println("Date parse error!");
            // throw e;
        }
        return dayCount;
    }

    /**
     * 获取相隔天数
     *
     * @param beforedate
     *            开始日期
     * @param afterdate
     *            结束日期
     * @return long
     * @throws java.text.ParseException
     */
    static public long getDistinceDay(Date beforedate, Date afterdate) throws ParseException {

        long dayCount = 0;

        try {
            dayCount = (afterdate.getTime() - beforedate.getTime()) / (24 * 60 * 60 * 1000);

        } catch (Exception e) {
            System.out.println("Date parse error!");
        }
        return dayCount;
    }

    /**
     * 获取相隔分钟
     *
     * @param beforedate
     *            开始日期
     * @param afterdate
     *            结束日期
     * @return long
     * @throws java.text.ParseException
     */
    static public long getDistinceSeconds(Date beforedate, Date afterdate) throws ParseException {

        long dayCount = 0;

        try {
            dayCount = (afterdate.getTime() - beforedate.getTime()) / (60 * 1000);

        } catch (Exception e) {
            System.out.println("Date parse error!");
        }
        return dayCount;
    }

    /**
     * 获取相隔天数
     *
     * @param beforedate
     *            开始日期(yyyy-MM-dd)
     * @return long
     * @throws java.text.ParseException
     */
    static public long getDistinceDay(String beforedate) throws ParseException {

        return getDistinceDay(beforedate, getTodayStr());
    }

    /**
     * 获取相隔时间数
     *
     * @param beforeDateTime
     *            开始日期(yyyy-MM-dd hh:mm:ss)
     * @param afterDateTime
     *            结束日期
     * @return
     * @throws java.text.ParseException
     */
    static public long getDistinceTime(String beforeDateTime, String afterDateTime) throws ParseException {

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long timeCount = 0;
        try {
            Date d1 = d.parse(beforeDateTime);
            Date d2 = d.parse(afterDateTime);

            timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);

        } catch (ParseException e) {
            System.out.println("Date parse error!");
            throw e;
        }
        return timeCount;
    }

    /**
     * 获取相隔时间数
     *
     * @param beforeDateTime
     *            开始日期(yyyy-MM-dd hh:mm:ss)
     * @return long
     * @throws java.text.ParseException
     */
    static public long getDistinceTime(String beforeDateTime) throws ParseException {

        return getDistinceTime(beforeDateTime, new Timestamp(System.currentTimeMillis()).toLocaleString());
    }

    /**
     * 获取相隔分钟数
     *
     * @param beforeDateTime
     *            开始日期(yyyy-MM-dd hh:mm:ss)
     * @param afterDateTime
     *            结束日期
     * @return
     * @throws java.text.ParseException
     */
    static public long getDistinceMinute(String beforeDateTime, String afterDateTime) throws Exception {

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeCount = 0;
        try {
            Date d1 = d.parse(beforeDateTime);
            Date d2 = d.parse(afterDateTime);

            timeCount = (d2.getTime() - d1.getTime()) / (60 * 1000);

        } catch (Exception e) {
            System.out.println("Date parse error!");
            throw e;
        }
        return timeCount;
    }

    /**
     * 获取相隔分钟数
     *
     * @param afterDateTime
     *            开始日期(yyyy-MM-dd hh:mm:ss)
     * @return
     * @throws java.text.ParseException
     */
    static public long getDistinceMinute(String afterDateTime) throws Exception {

        return getDistinceMinute(getDateTimeStr(new Date()), afterDateTime);
    }

    /**
     * 判断是否超出指定相隔时间范围内
     *
     * @param beforeDateTime
     *            开始日期(yyyy-MM-dd hh:mm:ss)
     * @param timeCount
     * @return
     */
    static public boolean isOvertime(String beforeDateTime, String timeCount) {

        boolean exceed = false;
        try {
            long count1 = Long.parseLong(timeCount);
            long count2 = getDistinceTime(beforeDateTime);
            if (count1 < count2) {
                exceed = true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exceed;
    }

    /**
     * 格式化为 HH:mm:ss 的字符串
     *
     * @param timestamp
     * @return
     */
    static public String getTimestamStr(Timestamp timestamp) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }

    /**
     * 格式化为 yyyy-MM-dd HH:mm:ss 的字符串
     *
     * @param time
     * @return
     */
    static public String getTimeStr(Time time) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(time);
    }

    /**
     * 判断后者时间是否在前者时间前
     *
     * @param checkdate
     *            判断的日期
     * @param auditDate
     *            指定日期
     * @return
     * @throws java.text.ParseException
     */
    static public boolean isBeforeCheckDate(String checkdate, Date auditDate) throws ParseException {

        Date cd;
        try {
            cd = new Date(parseDate(checkdate).getTime());

        } catch (ParseException ex) {
            System.out.println(ex);
            return false;
        }
        return isBeforeCheckDate(cd, auditDate);
    }

    /**
     * 判断待验证的时间是否在指定的时间范围内（与边界值相当时，视为包含在内）。
     *
     * @param checkdateStart 时间范围-起点
     * @param checkdateEnd 时间范围-终点
     * @param auditDate 待验证的时间点
     *
     * @return
     * @throws java.text.ParseException
     */
    static public boolean isBetweenCheckDate(Date checkdateStart, Date checkdateEnd, Date auditDate) throws ParseException {

        boolean result = (checkdateStart.compareTo(auditDate) == 0) || (checkdateEnd.compareTo(auditDate) == 0) || (checkdateStart.before(auditDate) && auditDate.before(checkdateEnd));

        return result;
    }

    /**
     * 此日期是否在指定日期之前
     *
     * @param checkdate
     *            判断的日期
     * @param auditDate
     *            指定日期
     * @return
     * @throws java.text.ParseException
     */
    static public boolean isBeforeCheckDate(Date checkdate, Date auditDate) throws ParseException {

        return auditDate.before(checkdate);
    }

    /**
     * 获取当前日期的下个月日期
     *
     * @param date
     *            当前日期
     * @return Date
     * @throws java.text.ParseException
     */
    static public Date getNextMonthDate(Date date) throws ParseException {

        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.MONTH, 1);
        return new Date(scalendar.getTime().getTime());
    }

    /**
     * 获取当前日期的上个月日期
     *
     * @param s
     *            当前日期 yyyy-MM格式
     * @return
     * @throws java.text.ParseException
     */
    static public String getOnMonthDate(String s) throws ParseException {

        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(parseDateM(s));

        String ym = scalendar.get(Calendar.YEAR) + "-";
        ym += String.valueOf(scalendar.get(Calendar.MONTH) < 10 ? "0" + scalendar.get(Calendar.MONTH) : scalendar.get(Calendar.MONTH));

        if (scalendar.get(Calendar.MONTH) == 0) {
            ym = scalendar.get(Calendar.YEAR) - 1 + "-12";
        }

        return ym;
    }

    /**
     * 按指定格式进行格式化
     * 
     * @param date
     *            当前日期
     * @param formatText
     *            日期格式
     * @return String
     * @throws Exception
     */
    static public String format(Date date, String formatText){
        SimpleDateFormat format = new SimpleDateFormat(formatText);
        return format.format(date);
    }

    /**
     * 2个日期间的指定月份的间隔天数
     * 
     * @param startdate
     *            开始日期
     * @param enddate
     *            结束日期
     * @param month
     *            指定月份
     * @return int
     * @throws Exception
     */
    static public int getDaysOfMonth(Date startdate, Date enddate, String month) throws Exception {

        int startmonth = startdate.getMonth() + 1;
        int endmonth = enddate.getMonth() + 1;
        int m = Integer.parseInt(month);
        int day = getLastDay(String.valueOf(startdate.getYear()), month).getDate();
        if ((startmonth < m) && (m < endmonth)) {
            return day;
        } else if (m == startmonth) {
            return day - startdate.getDate() + 1;
        } else if (m == endmonth) {
            return enddate.getDate();
        }
        return 0;
    }

    /**
     * 获得上个月第一天和最后一天
     * 
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public static Map getDate() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String beginTime = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(beginTime);
        beginTime = str.toString();
        calendar.add(cal.MONTH, 1);
        calendar.set(cal.DATE, 1);
        calendar.add(cal.DATE, -1);
        String endTime = df.format(calendar.getTime());
        Map map = new HashMap();
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return map;
    }

    /**
     * 增加日期格式字符
     * @param noFmtDate
     * @return
     */
    public static String addFormatDate(String noFmtDate) {

        StringBuilder sb = new StringBuilder(20);
        if (null == noFmtDate || noFmtDate.length() != 8 && noFmtDate.length() != 14) {
            return noFmtDate;
        }
        if (noFmtDate.length() == 8) {
            sb.append(noFmtDate.substring(0, 4)).append("-").append(noFmtDate.substring(4, 6)).append("-").append(noFmtDate.substring(6));
        }
        if (noFmtDate.length() == 14) {
            sb.append(noFmtDate.substring(0, 4)).append("-").append(noFmtDate.substring(4, 6)).append("-").append(noFmtDate.substring(6, 8)).append(" ").append(noFmtDate.substring(8, 10)).append(":").append(noFmtDate.substring(10, 12)).append(":").append(noFmtDate.substring(12));
        }
        return sb.toString();
    }

    /**
     * 去除日期格式字符
     * @param fmtDate
     * @return
     */
    public static String removeFormatDate(String fmtDate) {

        if (null == fmtDate) {
            return fmtDate;
        }
        StringTokenizer st = new StringTokenizer(fmtDate, "\\:\\-\\ \\/", false);
        StringBuilder buf = new StringBuilder(fmtDate.length());
        while (st.hasMoreElements()) {
            buf.append(((String) st.nextElement()).trim());
        }
        return buf.toString();
    }

    public static void main(String[] args) {

        try {
            String t = getCurDateStr("yyyyMMddhhmmss");

            System.out.println(t);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
