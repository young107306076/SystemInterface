package edu.nccu.aps.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static final DateFormat FORMAT_JQUERY_DATE = new SimpleDateFormat("yyyy/MM/dd");
	public static final DateFormat FORMAT_DATE = new SimpleDateFormat("yyyyMMdd");
	public static final DateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");
	public static final DateFormat FORMAT_DATE_TIME = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static final DateFormat FORMAT_REPORT = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	public static final DateFormat FORMAT_MILLISECOND = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	public static final DateFormat FORMAT_DATEHOURMIN = new SimpleDateFormat("MM/dd HH:mm");

	public static final DateFormat FORMAT_DAY = new SimpleDateFormat("HH:mm");
	public static final DateFormat FORMAT_MONTH = new SimpleDateFormat("MM/dd");
	public static final DateFormat FORMAT_YEAR = new SimpleDateFormat("yyyy/MM");
	public static final DateFormat FORMAT_YEARS = new SimpleDateFormat("yyyy");

	public static final DateFormat FORMAT_CURRENT_DAY = new SimpleDateFormat("HH");
	public static final DateFormat FORMAT_CURRENT_MONTH = new SimpleDateFormat("dd");
	public static final DateFormat FORMAT_CURRENT_YEAR = new SimpleDateFormat("MM");

	public static final String[] DAY_OF_WEEK_EN = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday" };
	public static final String[] DAY_OF_WEEK_TW = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	public static final String EMPTY = "".intern();
	public static final int SOLAR_POWER_REPORT_START_DAILY_HOUR = 5;
	public static final int SOLAR_POWER_REPORT_END_DAILY_HOUR = 19;

	/**
	 * 取得目前年
	 * 
	 * @return
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 取得目前年
	 * 
	 * @return
	 */
	public static int getYear(Date pDate) {
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.setTime(pDate);
		return tCalendar.get(Calendar.YEAR);
	}

	/**
	 * 目前月
	 * 
	 * @return
	 */
	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int getMonth(Date pDate) {
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.setTime(pDate);
		return tCalendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 比較兩個日期，如果第一個大於等於第二個 return true
	 * 
	 * @param pCalendar1
	 * @param pCalendar2
	 * @return
	 */
	public static boolean compareGreaterEqual(Calendar pCalendar1, Calendar pCalendar2) {
		if (pCalendar1.compareTo(pCalendar2) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 取得現在的 Date
	 * 
	 * @return
	 */
	public static Date getDate() {
		return new Date();
	}

	public static int getDate(Date pDate) {
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.setTime(pDate);
		return tCalendar.get(Calendar.DATE);
	}

	public static int getHour(Date pDate) {
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.setTime(pDate);
		return tCalendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 取得 Base 新增 多久(pType)時間(pOffset)後的日期
	 * 
	 * @param pBaseDate 比較的Base日期
	 * @param pType     Calendar.YEAR , Calendar.MONTH ...
	 * @param pOffset   幾天 1,2,3 ....
	 * @return
	 */
	public static synchronized Date getDateInterval(final Date pBaseDate, int pType, int pOffset) {
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.setTime(pBaseDate);
		if (pType == Calendar.YEAR) {
			tCalendar.add(Calendar.YEAR, pOffset);
			return getDateInterval(tCalendar.getTime(), Calendar.SECOND, -1);
		} else if (pType == Calendar.WEEK_OF_YEAR) {
			tCalendar.add(Calendar.WEEK_OF_YEAR, pOffset);
			return getDateInterval(tCalendar.getTime(), Calendar.SECOND, -1);
		} else if (pType == Calendar.MONTH) {
			tCalendar.add(Calendar.MONTH, pOffset);
			return getDateInterval(tCalendar.getTime(), Calendar.SECOND, -1);
		} else if (pType == Calendar.DATE) {
			tCalendar.add(Calendar.DATE, pOffset);
			return getDateInterval(tCalendar.getTime(), Calendar.SECOND, -1);
		} else if (pType == Calendar.HOUR) {
			tCalendar.add(Calendar.HOUR, pOffset);
			return getDateInterval(tCalendar.getTime(), Calendar.SECOND, -1);
		} else {
			tCalendar.add(Calendar.SECOND, pOffset);
			return tCalendar.getTime();
		}
	}

	/**
	 * 傳入兩個時間 傳出 xxxx ~ xxxx
	 * 
	 * @param pStart
	 * @param pEnd
	 * @param pType
	 * @return
	 */
//	public static String getStrInterval(Date pStart, Date pEnd, String pType) {
//		String tOut = "";
//		tOut += (pStart != null ? getStrTime(pType, pStart) + " ~ " : "");
//		tOut += (pEnd != null ? DateUtil.getStrTime(pType, pEnd) : "");
//		return tOut;
//	}

	/**
	 * Date to String yyyyMMdd
	 * 
	 * @param pDate
	 * @return
	 */
	public static String getStrDate(Date pDate) {
		if (pDate == null) {
			return EMPTY;
		} else {
			return FORMAT_DATE.format(pDate);
		}
	}
	
	/**
	 * Date to String HH:mm:ss
	 * 
	 * @param pDate
	 * @return
	 */
	public static String getStrTime(Date pDate) {
		if (pDate == null) {
			return EMPTY;
		} else {
			return FORMAT_TIME.format(pDate);
		}
	}

	/**
	 * yyyy/MM/dd HH:mm:ss
	 * 
	 * @param pDate
	 * @return
	 */
	public static String getStrDateTime(Date pDate) {
		if (pDate == null) {
			return EMPTY;
		} else {
			return FORMAT_DATE_TIME.format(pDate);
		}
	}

	/**
	 * 取得現在 yyyyMMdd
	 * 
	 * @return
	 */
	public static String getStrNowDate() {
		return getStrDate(getDate());
	}

	/**
	 * 取得現在 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getStrNowTime() {
		return getStrTime(getDate());
	}

	/**
	 * 取得現在 yyyy/MM/dd HH:mm:ss
	 * 
	 * @param pDate
	 * @return
	 */
	public static String getStrNowDateTime() {
		return getStrDateTime(getDate());
	}

	/**
	 * jQuery 的日期格式 yyyy/MM/dd
	 * 
	 * @param pStrDate
	 * @return
	 */
	public static Date getjQueryDate(String pStrDate) {
		Date tRtnVal = null;
		try {
			tRtnVal = FORMAT_JQUERY_DATE.parse(pStrDate);
		} catch (Exception e) {
		}
		return tRtnVal;
	}

	/**
	 * jQuery的日期格式 yyyy/MM/dd 轉日期
	 * 
	 * @param pDate
	 * @return
	 */
	public static String getjQueryStrDate(Date pDate) {
		if (pDate == null) {
			return EMPTY;
		} else {
			return FORMAT_JQUERY_DATE.format(pDate);
		}
	}

	/**
	 * 報表顯示日期
	 * 
	 * @param pReportType TYPE_DAY,TYPE_MONTH,TYPE_YEAR,TYPE_WEEK
	 * @param pTime       日期
	 * @return TYPE_DAY return HH:mm, FORMAT_MONTH return MM/dd
	 */
//	public static synchronized String getStrTime(String pReportType, Date pTime) {
//		if (pTime == null) {
//			return "NA";
//		}
//		if (StringUtil.isEmptyOrSpace(pReportType)) {
//			return FORMAT_REPORT.format(pTime);
//		} else if (pReportType.equals(TYPE_DAY)) {
//			return FORMAT_DAY.format(pTime);
//		} else if (pReportType.equals(TYPE_MONTH)) {
//			return FORMAT_MONTH.format(pTime);
//		} else if (pReportType.equals(TYPE_YEAR)) {
//			return FORMAT_YEAR.format(pTime);
//		} else if (pReportType.equals(TYPE_WEEK)) {
//			return FORMAT_MONTH.format(pTime);
//		} else if (pReportType.equals(TYPE_YEARS)) {
//			return FORMAT_YEARS.format(pTime);
//		} else if (pReportType.equals(TYPE_MILLISECOND)) {
//			return FORMAT_MILLISECOND.format(pTime);
//		} else if (pReportType.equals("DATEHOURMIN")) {
//			return FORMAT_DATEHOURMIN.format(pTime);
//		} else {
//			return FORMAT_REPORT.format(pTime);
//		}
//	}

//	/**
//	 * Solar報表顯示日期
//	 * 
//	 * @param pReportType
//	 *            TYPE_DAY,TYPE_MONTH,TYPE_YEAR,TYPE_WEEK
//	 * @param pTime
//	 *            日期
//	 * @return TYPE_DAY return HH, FORMAT_MONTH return dd
//	 */
//	public static synchronized String getCurrentStrTime(String pReportType, Date pTime) {
//		if (pTime == null) {
//			return "NA";
//		}
//		if (pReportType == null) {
//			return FORMAT_REPORT.format(pTime);
//		} else if (pReportType.equals(TYPE_DAY)) {
//			return FORMAT_CURRENT_DAY.format(pTime);
//		} else if (pReportType.equals(TYPE_MONTH)) {
//			return FORMAT_CURRENT_MONTH.format(pTime);
//		} else if (pReportType.equals(TYPE_YEAR)) {
//			return FORMAT_CURRENT_YEAR.format(pTime);
//		} else if (pReportType.equals(TYPE_YEARS)) {
//			return FORMAT_YEARS.format(pTime);
//		} else {
//			return FORMAT_REPORT.format(pTime);
//		}
//	}

	public static Date getDateFullOrSimple(String pDate) {
		try {
			return FORMAT_DATE_TIME.parse(pDate);
		} catch (Exception e) {
			try {
				return FORMAT_JQUERY_DATE.parse(pDate);
			} catch (Exception e2) {
			}
		}
		return null;
	}

//	public static Date getDateTime(String pReportType, String pTime) {
//		try {
//			if (pReportType == null) {
//				return FORMAT_REPORT.parse(pTime);
//			} else if (pReportType.equals(TYPE_DAY)) {
//				return FORMAT_DAY.parse(pTime);
//			} else if (pReportType.equals(TYPE_MONTH)) {
//				return FORMAT_MONTH.parse(pTime);
//			} else if (pReportType.equals(TYPE_YEAR)) {
//				return FORMAT_YEAR.parse(pTime);
//			} else if (pReportType.equals(TYPE_WEEK)) {
//				return FORMAT_MONTH.parse(pTime);
//			} else {
//				return FORMAT_REPORT.parse(pTime);
//			}
//		} catch (Exception e) {
//		}
//		return null;
//	}

	/**
	 * jQuery的日期格式 yyyy/MM/dd 轉日期
	 * 
	 * @param date 日期
	 * @param hour 小時
	 * @param min  分鐘
	 * @return
	 */

	public static Date getDateTime(String date, int hour, int min) {
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.setTime(getjQueryDate(date));
		tCalendar.set(Calendar.HOUR_OF_DAY, hour);
		tCalendar.set(Calendar.MINUTE, min);
		return tCalendar.getTime();
	}

	/**
	 * 取得星期幾
	 * 
	 * @param pDayOfWeek
	 * @return
	 */
	public static String getChineseWeek(int pDayOfWeek) {
		return getChineseWeek(pDayOfWeek, null);
	}

	/**
	 * 取得星期幾 取得中英文
	 * 
	 * @param pDayOfWeek
	 * @param pLocale
	 * @return
	 */
	public static String getChineseWeek(int pDayOfWeek, Locale pLocale) {
		if (pLocale == null || (!Locale.US.equals(pLocale)) || (!Locale.ENGLISH.equals(pLocale))) {
			if (0 <= pDayOfWeek && pDayOfWeek <= 6) {
				return DAY_OF_WEEK_TW[pDayOfWeek];
			}
		} else {
			if (0 <= pDayOfWeek && pDayOfWeek <= 6) {
				return DAY_OF_WEEK_EN[pDayOfWeek];
			}
		}
		return "";
	}

	/**
	 * 呼叫SimpleDateFormat格式化字串
	 * 
	 * @param pPattern formatting pattern for SimpleDateFormat
	 * @param pDate    date object
	 * @return
	 */
	public static String getStrSDF(String pPattern, Date pDate) {
		if (null == pDate) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pPattern);
		return sdf.format(pDate);
	}

	/**
	 * 呼叫SimpleDateFormat格式化字串
	 * 
	 * @param pPattern        formatting pattern for SimpleDateFormat
	 * @param pDate           date object
	 * @param pDefaultMessage
	 * @return
	 */
	public static String getStrSDF(String pPattern, Date pDate, String pDefaultMessage) {
		if (null == pDate) {
			return pDefaultMessage;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pPattern);
		return sdf.format(pDate);
	}

//	/**
//	 * 傳入 日期,以及類型
//	 * 
//	 * @param date
//	 *            起始日期，月報會被調整成 x月1日,年報會被調整成1月1日
//	 * @param type
//	 *            Calendar.YEAR,Calendar.MONTH
//	 * @return 傳回計算的區間
//	 */
//	public static Interval getInterval(Date date, int type) {
//		Calendar tCalendar = Calendar.getInstance();
//
//		tCalendar.setTime(date); // 傳入的日期
//		tCalendar.set(Calendar.MINUTE, 0); // 分
//		tCalendar.set(Calendar.SECOND, 0); // 秒
//		Interval tInterval = new Interval();
//		Date tStartTime = null; // 起始日期
//		Date tEndTime = null; // 結束日期
//		if (type == Calendar.YEAR) { // 年報
//			tCalendar.set(Calendar.MONTH, 0); // 1月
//			tCalendar.set(Calendar.DATE, 1); // 1日
//			tCalendar.set(Calendar.HOUR_OF_DAY, 0); // 時
//			tStartTime = tCalendar.getTime();
//			tEndTime = getDateInterval(tStartTime, Calendar.YEAR, 1);
//		} else if (type == Calendar.MONTH) {// 月報
//			tCalendar.set(Calendar.DATE, 1); // 1日
//			tCalendar.set(Calendar.HOUR_OF_DAY, 0); // 時
//			tStartTime = tCalendar.getTime();
//			tEndTime = getDateInterval(tStartTime, Calendar.MONTH, 1);
//		} else if (type == Calendar.DATE) {
//			tCalendar.set(Calendar.HOUR_OF_DAY, 0); // 時
//			tStartTime = tCalendar.getTime();
//			tEndTime = getDateInterval(tStartTime, Calendar.DATE, 1);
//		} else {
//			tStartTime = tCalendar.getTime();
//			tEndTime = getDateInterval(tStartTime, Calendar.HOUR, 1);
//		}
//		tInterval.setStartTime(tStartTime);
//		tInterval.setEndTime(tEndTime);
//		return tInterval;
//	}

//	public static Interval getInterval(Date startDate, Date endDate) {
//		Interval tInterval = new Interval();
//		tInterval.setStartTime(startDate);
//		tInterval.setEndTime(endDate);
//		return tInterval;
//	}

	/**
	 * 傳回當月最大日數
	 * 
	 * @param pBaseDate
	 * @return
	 */
	public static int getMaxDayOfMonth(final Date pBaseDate) {
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.setTime(pBaseDate);
		int tBaseMonth = tCalendar.get(Calendar.MONTH);
		int tTempMonth = -1;
		for (int i = 0; i <= 31; i++) {
			tCalendar.add(Calendar.DATE, 1);
			tTempMonth = tCalendar.get(Calendar.MONTH);
			if (tTempMonth != tBaseMonth) {
				tCalendar.add(Calendar.DATE, -1);
				return tCalendar.get(Calendar.DAY_OF_MONTH);
			}
		}
		return 0;
	}

	/**
	 * 移除秒數以下的資料
	 * 
	 * @param pDate
	 * @return
	 */
//	public static final Date getTruncateTime(Date pDate) {
//		return getTruncateTime(pDate, Calendar.SECOND);
//	}

//	/**
//	 * 移除秒數以下的資料
//	 * 
//	 * @param pDate
//	 * @return
//	 */
//	public static final Date getTruncateTime(Date pDate, int pType) {
//		return DateUtils.truncate(pDate, pType);
//	}

	/**
	 * 大於
	 * 
	 * @param pDate1
	 * @param pDate2
	 * @return
	 */
//	public static final boolean greaterThan(Date pDate1, Date pDate2) {
//		if (getTruncateTime(pDate1).compareTo(getTruncateTime(pDate2)) > 0) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * pDate1 >= pDate2
	 * 
	 * @param pDate1
	 * @param pDate2
	 * @return
	 */
//	public static final boolean greaterEqual(Date pDate1, Date pDate2) {
//		if (getTruncateTime(pDate1).compareTo(getTruncateTime(pDate2)) >= 0) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * 等於
	 * 
	 * @param pDate1
	 * @param pDate2
	 * @return
	 */
//	public static final boolean equal(Date pDate1, Date pDate2) {
//		if (getTruncateTime(pDate1).compareTo(getTruncateTime(pDate2)) == 0) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * 小於
	 * 
	 * @param pDate1
	 * @param pDate2
	 * @return
	 */
//	public static final boolean lessThan(Date pDate1, Date pDate2) {
//		if (getTruncateTime(pDate1).compareTo(getTruncateTime(pDate2)) < 0) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * 小於等於
	 * 
	 * @param pDate1
	 * @param pDate2
	 * @return
	 */
//	public static final boolean lessEqual(Date pDate1, Date pDate2) {
//		if (getTruncateTime(pDate1).compareTo(getTruncateTime(pDate2)) <= 0) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * pDate1 <= pDate <= pDate2
	 * 
	 * @param pDate
	 * @param pDate1
	 * @param pDate2
	 * @return
	 */
//	public static final boolean between(Date pDate, Date pDate1, Date pDate2) {
//		if (greaterEqual(pDate, pDate1) && lessEqual(pDate, pDate2)) {
//			return true;
//		}
//		return false;
//	}

//	public static final boolean between(Date pDate, Interval pInterval) {
//		return between(pDate, pInterval.getStartTime(), pInterval.getEndTime());
//	}

	/**
	 * pBaseInterval.start<=pInterval.start<=pBaseInterval.end
	 * pBaseInterval.start<=pInterval.end<=pBaseInterval.end
	 * 
	 * @param pInterval
	 * @param pInterval1
	 * @return
	 */
//	public static final boolean between(Interval pInterval, Interval pBaseInterval) {
//		return DateUtil.between(pInterval.getStartTime(), pBaseInterval) && DateUtil.between(pInterval.getEndTime(), pBaseInterval);
//	}

	/**
	 * 取得 Base 新增 多久(pType)時間(pOffset)後的日期 不會刪除1秒
	 * 
	 * @param pBaseDate 比較的Base日期
	 * @param pType     Calendar.YEAR , Calendar.MONTH ...
	 * @param pOffset   幾天 1,2,3 ....
	 * @return
	 */
	public static Date getSimpleDateOffset(final Date pBaseDate, int pType, int pOffset) {
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.setTime(pBaseDate);
		tCalendar.add(pType, pOffset);
		return tCalendar.getTime();
	}

	/***
	 * 今日累積,本月累積,本年累積
	 * 
	 * @param pType Calendar.DATE,Calendar.MONTH,Calendar.YEAR
	 * @return
	 */
//	public static Interval getAccumulateInterval(int pType) {
//		if (pType != Calendar.DATE && pType != Calendar.MONTH && pType != Calendar.YEAR) {
//			throw new IllegalArgumentException("type = " + pType);
//		}
//		Interval tOut = new Interval();
//		Date tEnd = DateUtil.getDate();
//		Date tStart = DateUtil.getTruncateTime(tEnd, pType);
//		tOut.setStartTime(tStart);
//		tOut.setEndTime(tEnd);
//		return tOut;
//	}

	/**
	 * 取得 本日 昨日 本週 上一週 本月 上一個月
	 * 
	 * @param pBaseDate
	 * @param pType
	 * @param pOffset
	 * @return
	 */
//	public static Interval getBeforeInterval(final Date pBaseDate, int pType, int pOffset) {
//		if (pType != Calendar.DATE && pType != Calendar.MONTH && pType != Calendar.WEEK_OF_YEAR && pType != Calendar.YEAR) {
//			throw new IllegalArgumentException("type = " + pType);
//		}
//		Date tStartTime = DateUtil.getTruncateTime(DateUtil.getSimpleDateOffset(pBaseDate, Calendar.DATE, pOffset), Calendar.DATE);;
//		Date tEndTime = DateUtil.getDateInterval(tStartTime, Calendar.DATE, 1);
//		Calendar tCalendar = null;
//		switch (pType) {
//		case Calendar.DATE:
//			tStartTime = DateUtil.getTruncateTime(DateUtil.getSimpleDateOffset(pBaseDate, Calendar.DATE, pOffset), Calendar.DATE); // 前一天
//			tEndTime = DateUtil.getDateInterval(tStartTime, Calendar.DATE, 1);
//			break;
//		case Calendar.WEEK_OF_YEAR:
//			tCalendar = Calendar.getInstance();
//			tCalendar.setTime(DateUtil.getTruncateTime(pBaseDate, Calendar.DATE));
//			tCalendar.set(Calendar.DAY_OF_WEEK, tCalendar.getFirstDayOfWeek());
//			tCalendar.add(Calendar.WEEK_OF_YEAR, pOffset);
//			tStartTime = tCalendar.getTime();
//			tEndTime = DateUtil.getDateInterval(tStartTime, Calendar.WEEK_OF_YEAR, 1);
//			break;
//		case Calendar.MONTH:
//			tCalendar = Calendar.getInstance();
//			tCalendar.setTime(DateUtil.getTruncateTime(pBaseDate, Calendar.DATE));
//			tCalendar.add(Calendar.MONTH, pOffset);
//			tCalendar.set(Calendar.DATE, 1);
//			tStartTime = tCalendar.getTime();
//			tEndTime = DateUtil.getDateInterval(tStartTime, Calendar.MONTH, 1);
//			break;
//		case Calendar.YEAR:
//			tCalendar = Calendar.getInstance();
//			tCalendar.setTime(DateUtil.getTruncateTime(pBaseDate, Calendar.DATE));
//			tCalendar.set(Calendar.MONTH, 0);
//			tCalendar.set(Calendar.DATE, 1);
//			tCalendar.add(Calendar.YEAR, pOffset);
//			tStartTime = tCalendar.getTime();
//			tEndTime = DateUtil.getDateInterval(tStartTime, Calendar.YEAR, 1);
//		}
//		Interval tOut = new Interval();
//		tOut.setStartTime(tStartTime);
//		tOut.setEndTime(tEndTime);
//		return tOut;
//	}
}
