package com.emat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

public class DateUtils
{
  private static final Logger logger = Logger.getLogger(DateUtils.class);
  private static int startDays;
  private static int endDays;
  public final static String date_Pattern = "yyyy-MM-dd";
  public final static String datePattern = "yyyyMMdd";
  public final static String timePattern = "yyyy-MM-dd HH:mm:ss";
  public final static String solrDatePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  
  public static int getIntervalDays(Date startDate, Date endDate)
  {
    if ((startDate == null) || (endDate == null)) {
      return -1;
    }
    long intervalMilli = endDate.getTime() - startDate.getTime();
    if (intervalMilli < 0L) {
      return -1;
    }
    return (int)(intervalMilli / 86400000L);
  }
  
  public static int getStartDays(Date startDate)
  {
    Date now = new Date();
    startDays = getIntervalDays(startDate, now);
    if (startDays == -1) {
      startDays = 0;
    }
    return startDays;
  }
  
  public static int getEndDays(Date endDate)
  {
    Date now = new Date();
    endDays = getIntervalDays(endDate, now);
    if (endDays == -1) {
      endDays = 0;
    }
    return endDays;
  }
  
  public static String parseLongToDate(long longdate, String pattern)
  {
    Date d = new Date(longdate);
    String format = DateFormatUtils.format(d, pattern);
    return format;
  }
  
  public static String parseLongToDate(long longdate)
  {
    Date d = new Date(longdate);
    String format = DateFormatUtils.format(d, "yyyy-MM-dd HH:mm:ss");
    return format;
  }
  
  public static String DateFormat(Date date, String returntype)
  {
    SimpleDateFormat df1 = new SimpleDateFormat(returntype);
    
    return df1.format(date);
  }
  
  public static Date DateFormat(String date, String type)
  {
    SimpleDateFormat df1 = new SimpleDateFormat(type);
    date = StringUtils.trimToEmpty(date);
    Date mydate = null;
    try
    {
      mydate = df1.parse(date);
    }
    catch (ParseException e1)
    {
      logger.info(e1.getMessage());
    }
    return mydate;
  }
  
  public static Date DateAddResult(Date date, int type, int num)
  {     
	    Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, num);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
  }
  
  /**
	 * 将一个字符串转换成日期格式
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String date, String pattern) {
		if (("" + date).equals("")) {
			return null;
		}
		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDate;
	}
	
	/**
     *将字符串格式yyyyMMdd的字符串转为日期，格式"yyyy-MM-dd"
	 *
	 * @param date 日期字符串
	 * @return 返回格式化的日期
	 * @throws ParseException 分析时意外地出现了错误异常
	 */	    
	public static String strToDateFormat(String date){
	       SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	       formatter.setLenient(false);
	       Date newDate = new Date();
	       try {
	    	   newDate = formatter.parse(date);
	       } catch (ParseException e) {
	    	   e.printStackTrace();
	       }
	       formatter = new SimpleDateFormat("yyyy-MM-dd");
	       return formatter.format(newDate);
	}
	
	/**
     * 获取以秒为单位的时间年份
	 *
	 * @param str 日期字符串
	 * @return 返回年份
	 */	    
	public static String getStrYear(String str){
		Long initTime = Long.valueOf(com.emat.util.StringUtils.toString(str, "0"));
		String date = DateFormat(new Date(initTime * 1000), "yyyy-MM-dd");
		String year = date.substring(0,date.indexOf("-"));
		return year;
	}
	
	/**
     *将EEE MMM dd HH:mm:ss Z yyyy格式的日期转化为"yyyy-MM-dd"
	 *
	 * @param date 日期
	 * @return 返回格式化的日期
	 * @throws ParseException 分析时意外地出现了错误异常
	 */	    
	public static String solrDateFormat(String str){
		   String sDate = "";
		   if(StringUtils.isEmpty(str))return sDate;
		   SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
	       try{
	       	   Date date=sdf1.parse(str);
	           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	           sDate=sdf.format(date);
	       }catch (ParseException e){
	       }
	       return sDate;
	}
}
