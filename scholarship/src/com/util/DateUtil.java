package com.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 对时间进行操作
 * 
 * @author xinxin
 * 
 */

public class DateUtil {
	private static SimpleDateFormat yyyyMmDd = new SimpleDateFormat("yyyy-MM-dd");
	public DateUtil() {
	}

	private static SimpleDateFormat getDateParser(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	public static Date curDate() {
		return new Date();
	}

	public static String curDateStr(String strFormat) {
		Date date = new Date();
		return getDateParser(strFormat).format(date);
	}
	
	public static String formatDate(Date date, String strFormat) {
		return getDateParser(strFormat).format(date);
	}

	public static String curDateStr() {
		Date date = new Date();
		return getDateParser("yyyy-MM-dd").format(date);
	}

	public static String curDateStr6() {
		Date date = new Date();
		return getDateParser("yyyyMM").format(date);
	}

	public static String curDateStr7() {
		Date date = new Date();
		return getDateParser("yyyy-MM").format(date);
	}

	public static String curDateStr8() {
		Date date = new Date();
		return getDateParser("yyyyMMdd").format(date);
	}

	public static String curDateStr10() {
		Date date = new Date();
		return getDateParser("yyyy-MM-dd").format(date);
	}

	public static String curDateTimeStr14() {
		Date date = new Date();
		return getDateParser("yyyyMMddHHmmss").format(date);
	}

	public static String curDateTimeStr19() {
		Date date = new Date();
		return getDateParser("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String curTimeStr6() {
		Date date = new Date();
		return getDateParser("HHmmss").format(date);
	}

	public static String curDateMselStr18() {
		Date date = new Date();
		return getDateParser("yyyyMMddHHmmssSSSS").format(date);
	}

	public static String dateYyyyMmDd(Date dt) {
		if (dt != null)
			return String.valueOf(yyyyMmDd.format(dt));
		else
			return "";
	}
	public static String putDateToTimeStr19(Date date){
		return getDateParser("yyyy-MM-dd HH:mm:ss").format(date);
	}
	public static String putDateToTimeStr10(Date date){
		return getDateParser("yyyy-MM-dd").format(date);
	}
	/**
	 * 得到前一天的时间
	 * 
	 * @return
	 */
	public static String yesterdayDateStr() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mDateTime = formatter.format(c.getTime());

		return mDateTime;
	}

	/**
	 * 得到前三天的时间
	 * 
	 * @return
	 */
	public static String beforeThreeDayStr() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -3);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(c.getTime());

		return mDateTime;
	}
	/**
	 * 得到前天的时间
	 * 
	 * @return
	 */
	public static String beforeDayStr() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(c.getTime());

		return mDateTime;
	}
	/**
	 * 得到明天的时间
	 * 
	 * @return
	 */
    public static String tomorrowDayStr(){
    	Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, +1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(c.getTime());
		return mDateTime;
	
    }
	/**
	 * 得到月初第一天的时间
	 * 
	 * @return
	 */
	public static String montchFirstDayStr() {

		String temp = DateUtil.curDateTimeStr19();
		System.out.println("temp====" + temp); // 2009-09-23 08:05:47
		String t = temp.substring(8, 10);
		int s = Integer.valueOf(t);

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -(s - 1));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(c.getTime());

		return mDateTime;

	}

	/**
	 * 得到本年的第一天
	 * 
	 * @return
	 */
	public static String yearFirstDayStr() {
		String temp = DateUtil.curDateTimeStr19();

		String year = temp.substring(0, 4);
		String yearFirstDayStr = year + "-01-01";

		return yearFirstDayStr;
	}

	public static String toDateTime(long times) {
		times /= 1000L;
		long hours = times / 3600L;
		times -= hours * 3600L;
		long minutes = times / 60L;
		times -= minutes * 60L;
		long seconds = times;
		String result = hours + "(h) " + minutes + "(m) " + seconds + "(s)";
		return result;
	}

	/**
	 * 删除文件或目录中的内容
	 * 
	 * @param filepath
	 * @throws IOException
	 */
	public static void deleteFile(String filepath) throws IOException {
		File f = new File(filepath);// 定义文件路径
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			if (f.listFiles().length < 50) {// 若目录下没有文件则直接删除
				//f.delete();
			} else {// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						deleteFile(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
					}
					delFile[j].delete();// 删除文件
				}
			}
		}
	}
	
	/**
	 * 得到今天是周几
	 * 
	 * @return
	 */
	public static String curDateWeekday() {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
		Calendar c = Calendar.getInstance();
		//Date date = new Date();
		//calendar.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
		//if(dayOfWeek <0)dayOfWeek=0;
		
		return dayNames[dayOfWeek];
	}
	/**
	 * 获得当前的时间点
	 * @return
	 */
	public static String curDateHour(){
		String now=curDateTimeStr19();
		int index=now.indexOf(" ");
		String str=now.substring(index+1, index+3);
		return str;
	} 
	public static void main(String[] args) {
		String ip="92.168.1.6";
		System.out.println("substring:"+ip.substring(ip.lastIndexOf(".")+1));
		System.out.println("getTime:"+new Date().getTime());
	}

	
}
