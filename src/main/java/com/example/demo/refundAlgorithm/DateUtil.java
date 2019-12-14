package com.example.demo.refundAlgorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间工具
 * @since v1.1
 * @history
 * @see
 */
public class DateUtil {
	
	
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return currDate;
	}
	
	//获取当前系统时间戳
	public static Long getTimeInMillis() {
		

		return Calendar.getInstance().getTimeInMillis();
	}
	
	
	/**
	 * @Title: compareDate
	 * @Description: TODO(比较两日期大小)
	 * @param beginTime
	 * @param endTime
	 * @return
	 * int 返回类型
	 * @throws  
	 */
	public static int compareDate(Object beginTime,Object endTime)  {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			Date bt =null;
			 Date et=null;
			if(beginTime instanceof String && endTime instanceof String) {
				 bt = sdf.parse(String.valueOf(beginTime));
				 et=sdf.parse(String.valueOf(endTime)); 
			}else if(beginTime instanceof Date && endTime instanceof Date) {
				bt=sdf.parse(sdf.format((Date)beginTime));
				et=sdf.parse(sdf.format((Date)endTime));
			}
			  if (bt.before(et)){ 
			   //表示bt小于et
				 return -1;
			  }else if(bt.after(et)){ 
				  return 1;
			  }else if(bt.equals(et)){
				  return 0;
			  }
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	//获取当前系统时间
		public static String getSysDate() {
			Calendar cal = Calendar.getInstance();
			Date currDate = cal.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return simpleDateFormat.format(currDate);
		}
	
	/**
	 * 将时间转成"yyyy-MM-dd"格式的字符串
	 * 
	 * @param date
	 *            时间
	 * @return
	 * 
	 */
	public static String formatToYYYYMMDD(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		} else {
			return "null";
		}

	}

	public static String formatToYYYYMMDDMMHHSS(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	

	public static Date formatToDayByYYYYMMDDMMHH(String s)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return simpleDateFormat.parse(s);
	}

	public static String formatToYYYYMMDDMMHHSSSlash(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}

	public static String formatToYYYYMMDDSlash(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return simpleDateFormat.format(date);
	}

	public static String formatToYMZSlash(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	public static String formatToYMZSMMHHSSSlash(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 返回一个二维数组，单位分别是月和日，代表两个Date之差。 <br>
	 * 本方法忽略小时和分钟。 <br>
	 * <br>
	 * 例： <br>
	 * 1，2012年6月1日到2012年6月3日，返回值是[0,2] （2天） <br>
	 * 2，2012年6月15日到2012年8月4日，返回值是[1,20] （1个月零20天） <br>
	 * 3，2011年11月3日到2013年1月14日，返回值是[14,11] （14个月零11天）
	 * 
	 * @param olderDate
	 *            较早的日期
	 * @param newerDate
	 *            较晚的日期
	 * @return
	 *             当olderDate晚于newerDate时
	 */
	public static int[] getDateDifferenceInMonthAndDay(Date olderDate,
			Date newerDate) throws Exception {
		int[] differenceInMonthAndDay = new int[2];
		int years = 0;
		int months = 0;
		int days = 0;

		Calendar older = Calendar.getInstance();
		Calendar newer = Calendar.getInstance();
		older.setTime(olderDate);
		newer.setTime(newerDate);

		if (olderDate.getTime() > newerDate.getTime()) {
			throw new Exception();
		} else {
			years = newer.get(Calendar.YEAR) - older.get(Calendar.YEAR);
			if (years >= 0) {

				months = newer.get(Calendar.MONTH) - older.get(Calendar.MONTH)
						+ 12 * years;
				older.add(Calendar.MONTH, months);
				days = newer.get(Calendar.DATE) - older.get(Calendar.DATE);

				if (days < 0) {
					months = months - 1;
					older.add(Calendar.MONTH, -1);
				}

				days = daysBetween(newer.getTime(), older.getTime());
				differenceInMonthAndDay[0] = months;
				differenceInMonthAndDay[1] = days;
			}

		}
		return differenceInMonthAndDay;
	}

	/**
	 * 取两个Date之间的天数差<br>
	 * <br>
	 * 例：newerDate是6月3日，olderDate是5月31日，则应返回3 <br>
	 * 一个更极端的列子：newerDate是6月3日 00:01，olderDate是6月2日
	 * 23:59，则应返回1，说明相差一天，即便实际上只差2分钟 <br>
	 * 此代码来自网上 <br>
	 * http://blog.csdn.net/rmartin/article/details/1452867
	 * 
	 * @param newerDate
	 * @param olderDate
	 * @return
	 */
	public static int daysBetween(Date newerDate, Date olderDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(newerDate);
		cReturnDate.setTime(olderDate);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}

	private static int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}

	private static void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	/**
	 * 得到距离当前时间前一个月的时间
	 * 
	 * @return
	 * 
	 */
	public static Date getLastMonthDate() {
		Calendar lastMonthDate = Calendar.getInstance();
		lastMonthDate.add(Calendar.MONTH, -1);
		return lastMonthDate.getTime();
	}

	/**
	 * 比较两个时间日期的大小
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int dateCompare(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		return c1.compareTo(c2);
	}

	/**
	 * 解析日期zsj 20150302
	 * 
	 * @param dt
	 *            2015-03-01 23:59:59
	 * @param pattern
	 *            yyyy-MM-dd HH:mm:ss
	 */
	public static Date parseDate(String dt, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		String date1 = addDays(date,1,0);
		String bb="2018-09-27 00:00:00.0";
		String cc="2018-09-27";
		int compareDate = compareDate(bb,cc);
		System.out.println(compareDate);
	}

	
	
	/**
	 * 计算给定的日期加上给定的天数。
	 * 
	 * @param when
	 *            给定的日期
	 * @param amount
	 *            给定的天数
	 * @return 计算后的日期
	 */
	public static String addDays(Date when, int amount, int offset) {
		return add(when, Calendar.DAY_OF_YEAR, amount, offset);
	}

	public static String add(Date when, int field, int amount, int offset) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(when);
		calendar.add(field, amount);
		calendar.add(Calendar.SECOND, offset);
		return  sdf.format(calendar.getTime());
//		return calendar.getTime();
	}
	public synchronized static String revertDateTime(String myDateTime) {
    	if(myDateTime==null) return "";
        String rtnDateTime = "";
        if(myDateTime.length() == 10 || myDateTime.length() == 19) {
            rtnDateTime = myDateTime.substring(0, 4) + myDateTime.substring(5, 7) +
                myDateTime.substring(8, 10);
            if(myDateTime.length() == 19) {
                myDateTime = myDateTime.substring(11);
            }
        }
        if(myDateTime.length() == 8) {
            rtnDateTime = rtnDateTime + myDateTime.substring(0, 2) +
                myDateTime.substring(3, 5) + myDateTime.substring(6, 8);
        }
        return rtnDateTime;
    }
	
	/* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

	/**
	 * 计算还款日期（dueDate）和baseDate之间相差的天数。 <br>
	 * 如果dueDate在baseDate之前（逾期），则返回一个负数，其绝对值是相差的天数 <br>
	 * 如果dueDate在baseDate之后（提前还款），则返回一个正数，其值是相差的天数 <br>
	 * 如果两者是同一天，则返回0
	 *
	 * @param baseDate
	 * @return
	 */
	public static int getDifferenceDays(Date dueDate, Date baseDate) {
		return DateUtil.daysBetween(baseDate, dueDate);
	}

	/**
	 * 按天借款的方式 返款时间的计算
	 *
	 * @param releaseTime 放款时间
	 * @param termCount   借款天数
	 * @return 还款日期
	 */
	public static Date getDayRepayDate(Date releaseTime, int termCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(releaseTime);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, -1);
		calendar.add(Calendar.DAY_OF_MONTH, termCount);
		return calendar.getTime();
	}
}