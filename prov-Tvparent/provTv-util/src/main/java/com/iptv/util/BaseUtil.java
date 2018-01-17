package com.iptv.util;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class BaseUtil {

	private static final Logger log = LoggerFactory.getLogger(BaseUtil.class);

	/**
	 * 根据输入字符串获取字符串对应uuid
	 * 
	 * @param target
	 *            目标字符串
	 * @return 字符串对应的uuid
	 */
	public static String convertStringToUUid(String target) {
		if (target != null && !target.isEmpty()) {
			return target;
		}
		return null;
	}
	
	public static int[] StringToIntArray(String target, String symbol){
		String arr[] = target.split(symbol);
		int[] retArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++){
			retArr[i] = Integer.parseInt(arr[i]);
		}
		return retArr;
	}
	
	/**
	 * 命名规则name_version.apk/rom
	 * @param fileName
	 * @return
	 */
	public static String getSysFileVersionByName(String fileName){
		return fileName.substring(0, fileName.lastIndexOf(".")).split("_")[1];
	}
	
	/**
	 * 根据盒子上报日志文件名称获取上报日志的版本号
	 * 
	 * @param fileName
	 *            日志文件名称，格式:酒店id_盒子id_日期_版本号.csv
	 * @return 版本号
	 */
	public static String getLogVersionByName(String fileName) {
		if (fileName != null && !fileName.isEmpty()) {
			String arr[] = fileName.split("_");
			if (arr.length == 4) {
				String logVersion = arr[3].substring(0, arr[3].length() - 4);
				log.info("BaseUtil.getLogVersionByName: fileName="+ fileName +", log version="+ logVersion);
				return logVersion;
			}
		}
		log.info("BaseUtil.getLogVersionByName: fileName="+ fileName +", log version=");
		return "";
	}
	
	
	/**
	 * 返回指定格式偏移量的日期
	 * @param format yyyyMMDD、yyyy-MM-dd HH:mm:ss etc.
	 * @param threadhold 偏移量：-1指昨天，0：当天
	 * @return format格式的日期字符串
	 */
	public static String getFormatTime(String format, int threadhold){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, threadhold);
		
		return sdf.format(cal.getTime());
	}
	
	public static int getDate(int field){
		Calendar cal = Calendar.getInstance();
		return cal.get(field);
	}
	
	/**
	 * 将毫秒数转换成format格式日期字符串
	 * @param millsecond
	 * @param format
	 * @return
	 */
	public static String convertMillsToDate(String millsecond, String format){
		long second = Long.parseLong(millsecond);
		Date date = new Date(second);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}


	
	public static boolean authenLoginExpire(HttpServletRequest req){
		
		if (WebUtils.getSessionAttribute(req, "isLogin") == null){
			return false;
		} else {
			Long loginTime = (Long) WebUtils.getSessionAttribute(req, "isLogin");
			long now = System.currentTimeMillis();
			
			if ((now - loginTime) > 3600 * 1000){
				WebUtils.clearErrorRequestAttributes(req);
				return false;
			} else {
				return true;
			}
		}
	}
	
	public static Long convertTimeToSeconds(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		return sdf.parse(time).getTime();
	}
	
	public static boolean compareTime(String start, String end, long target){
		SimpleDateFormat formart = new SimpleDateFormat("HH:mm");
		Date t = new Date(target);
		String dest = formart.format(t);
		try
        {
            Date s = formart.parse(start);
            Date e = formart.parse(end);
            
            Date result = formart.parse(dest);
            return result.compareTo(s)>=0 && result.compareTo(e) <= 0;
        }
        catch (ParseException e)
        {
            System.out.println("date init fail!");
            e.printStackTrace();
            return false;
        }
	}
	
	public static boolean contains(Set<String> filter, String target) {
		for (String words : filter) {
			if (target.contains(words)) {
				return true;
			}
		}
		return false;
	}
	
	public static String perCalculate( double  p1,  double  p2)  {
        String str;
        double  p3  =  p1  /  p2;
        NumberFormat nf  =  NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits( 2 );
        str  =  nf.format(p3);
         return  str;
    }
	
	public static void main(String args[]) throws UnsupportedEncodingException, JSONException, ParseException {
		String start = "11:30";
		String end = "14:30";
		long play = 1417925138821l;
		System.out.println(convertMillsToDate("1417925138821", "yyyy-MM-dd HH:mm:ss"));
		System.out.println(compareTime(start, end, play));
	}
}
