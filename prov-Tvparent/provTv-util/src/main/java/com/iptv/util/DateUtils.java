package com.iptv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/**
	 * //获取当前月第一天：
	 * @return
	 */
	public static String   getMonthFirstDay(){
		
		//获取当前月第一天：
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
        Calendar c = Calendar.getInstance();   
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;
	}
	
	/**
	 * //获取前月的最后一天
	 * @return
	 */
	public static String getLastMonthLastDay(){
		
		 //获取前月的最后一天
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
	    Calendar cale = Calendar.getInstance();  
	    cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
	    String lastDay = format.format(cale.getTime());
	    return lastDay;
	}
	
	/**
	 * //获取当前月最后一天：
	 * @return
	 */
	public static String   getMonthLastDay(){
		
		//获取当前月最后一天：
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
		Calendar ca = Calendar.getInstance();   
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        String last = format.format(ca.getTime());
        return last;
	}
	
	/**
	 * //获取当前时间(yyyyMMdd)：
	 * @return Date
	 * @throws ParseException 
	 */
	public static Date   getNowDateyMDdHms() throws ParseException{
		
		//获取当前时间：
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
        String now = format.format(new Date());
        return format.parse(now);
	}

}
