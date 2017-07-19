package com.liguo.hgl.proxydao.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.liguo.hgl.proxydao.util.StringUtil;
/**
 * 页面日期选择日期bean 开始日期 、结束日期、 今日、 近一个月 等。<br>
 * 
 * @filename SelectDateBean.java<br>
 * @author 张勇<br>
 * @date 2015-5-29<br>
 * @Language java<br>
 */
public class SelectDateBean {
	/** 选中类型 1-今天 */
	public static final String DATETYPE_TODAY="1";
	/** 选中类型 2-近一个月*/
	public static final String DATETYPE_ONE_MONTH="2";
	/** 选中类型  3-近三个月 */
	public static final String DATETYPE_THREE_MONTH="3";
	/** 选中类型 4-近六个月  */
	public static final String DATETYPE_SIX_MONTH="4";
	/** 选中类型 5-近一年 */
	public static final String DATETYPE_ONE_YEAR="5";
	/** 开始日期 */
	private String startDate;
	/** 结束日期 */
	private String endDate;
	/** 选中类型 1-今天 2-近一个月 3-近三个月 4-近六个月 5-近一年 */
	private String dateType;
	/** 是否选中事件触发请求 */
	private boolean dateTypeClik;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public boolean getDateTypeClik() {
		return dateTypeClik;
	}

	public void setDateTypeClik(boolean dateTypeClik) {
		this.dateTypeClik = dateTypeClik;
	}

	public void init(){
		try {
			Calendar calendar = Calendar.getInstance();
			String format = "yyyy-MM-dd";
			String date = format(calendar.getTime(), format);
			// 当前日期
			if (StringUtil.isBlank(endDate)) {
				endDate=date;
			}
			if (StringUtil.isBlank(startDate)) {
				dateType=SelectDateBean.DATETYPE_ONE_MONTH;
				dateTypeClik=true;
			}
			if (dateTypeClik&& StringUtil.isNotBlank(dateType)) {// 用户点击日期类型查询时
				if (SelectDateBean.DATETYPE_TODAY.equals(dateType)) {// 今天
					startDate=endDate;
				} else if (SelectDateBean.DATETYPE_ONE_MONTH.equals(dateType)) {// 近一个月
					calendar.add(Calendar.MONTH, -1);
				} else if (SelectDateBean.DATETYPE_THREE_MONTH.equals(dateType)) {// 近三个月
					calendar.add(Calendar.MONTH, -3);
				} else if (SelectDateBean.DATETYPE_SIX_MONTH.equals(dateType)) {// 近6个月
					calendar.add(Calendar.MONTH, -6);
				} else if (SelectDateBean.DATETYPE_ONE_YEAR.equals(dateType)) {// 近一年
					calendar.add(Calendar.YEAR, -1);
				}
				startDate = format(calendar.getTime(),format);
				endDate=date;
			} else {// 用户非点击日期类型查询时
				if (!date.equals(endDate)) {// 结束日期不是当前日期
					dateType="";
					return;
				} else if (endDate.equals(startDate)) {// 结束日期和开始日期都为当前日期
					dateType=SelectDateBean.DATETYPE_TODAY;
					return;
				}
				calendar.add(Calendar.MONTH, -1);
				if (format(calendar.getTime(), format).equals(startDate)) {// 符合近一个月条件
					dateType=SelectDateBean.DATETYPE_ONE_MONTH;
					return;
				}
				calendar.add(Calendar.MONTH, -2);
				if (format(calendar.getTime(), format).equals(startDate)) {// 符合近三个月条件
					dateType=SelectDateBean.DATETYPE_THREE_MONTH;
					return;
				}
				calendar.add(Calendar.MONTH, -3);
				if (format(calendar.getTime(), format).equals(startDate)) {// 符合近六个月条件
					dateType=SelectDateBean.DATETYPE_SIX_MONTH;
					return;
				}
				calendar.add(Calendar.MONTH, 6);
				calendar.add(Calendar.YEAR, -1);
				if (format(calendar.getTime(), format).equals(startDate)) {// 符合近一年条件
					dateType=SelectDateBean.DATETYPE_ONE_YEAR;
					return;
				}
				dateType="";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 按指定格式进行格式化
     *
     * @param date 当前日期
     * @param formatText 日期格式
     * @return String
     * @throws Exception
     */
    public static String format(Date date, String formatText) {

        SimpleDateFormat format = new SimpleDateFormat(formatText);
        return format.format(date);
    }
}
