package com.davada.domain.common.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil extends DateUtils {

  /**
   * 기본 날짜 포맷 (yyyy-MM-dd'T'HH:mm:ss) RFC333 (http://www.ietf.org/rfc/rfc3339.txt)
   */
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
  /**
   * 1일을 millisecond로 환산한 값
   */
  public static final long ONE_DAY_MS = 24 * 60 * 60 * 1000;

  /**
   * 연도 포맷 {@value}
   */
  public static final String YEAR_FORMAT = "yyyy";
  /**
   * 날짜 포맷 {@value}
   */
  public static final String DATE_FORMAT = "yyyy-MM-dd";
  /**
   * {@value}
   */
  public static final String DATE_KOR_FORMAT = "yyyy년MM월dd일";
  /**
   * {@value}
   */
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
  /**
   * {@value}
   */
  public static final String DATE_TIME_FORMAT2 = "yyyy-MM-dd'T'HH:mm";
  /**
   * {@value}
   */
  public static final String ITSM_DATE_TIME_FORMAT = "yyyyMMdd HHmmss";
  /**
   * {@value}
   */
  public static final String TIME_FORMAT = "HH:mm";
  /**
   * 연월일 문자열 포맷 {@value}
   */
  public static final String YYYYMMDD_FORMAT = "yyyyMMdd";
  /**
   * 데이터 초기일자(업무적으로 Default/null 값을 의미)
   */
  public static final Date DEFAULT_BASE_DATE = new Date(2019, 9, 1);
  public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
  /**
   * 기본 날짜 포맷 길이
   */
  private static final int DEFAUT_DATE_FORMAT_LEN = DEFAULT_DATE_FORMAT.length();
  /**
   * 날짜 포맷 길이
   */
  private static final int DATE_FORMAT_LEN = DATE_FORMAT.length();

  /**
   * 현재 날짜를 문자열로 반환한다.
   *
   * @return
   */
  public static String getCurrentDateAsString() {
    return dateToString(new Date(), null);
  }

  /**
   * 현재 날짜를 문자열로 반환한다.
   *
   * @return
   */
  public static String getCurrentDateAsString(String format) {
    return dateToString(new Date(), format);
  }

  /**
   * 기본 포맷으로 날짜를 문자열로 변환한다.
   *
   * @param date 날짜
   * @return 문자열 날짜
   */
  public static String dateToString(Date date) {
    return dateToString(date, null);
  }

  /**
   * 기본 포맷으로 날짜를 문자열로 변환한다.
   *
   * @param dateTime 날짜
   * @return 문자열 날짜
   */
  public static String dateToString(Long dateTime) {
    return dateToString(new Date(dateTime), null);
  }

  /**
   * 날짜를 문자열로 변환한다.
   *
   * @param date   날짜
   * @param format 포맷
   * @return 문자열 날짜
   */
  public static String dateToString(Date date, String format) {
    if (date == null) {
      return null;
    }

    SimpleDateFormat sdf = makeFormat(format);

    return sdf.format(date);
  }

  /**
   * 문자열을 데이터로 변환한다.
   *
   * @param dateString 날짜 문자열
   * @return 변환된 날짜
   */
  public static Date stringToDate(String dateString) {
    return stringToDate(dateString, null);
  }

  /**
   * 문자열을 데이터로 변환한다.
   *
   * @param dateString 날짜 문자열
   * @param format     변환할 포맷
   * @return 변환된 날짜
   */
  public static Date stringToDate(String dateString, String format) {
    if (dateString == null) {
      return null;
    }

    SimpleDateFormat sdf = makeFormat(dateString, format);

    try {
      return sdf.parse(dateString);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * 문자열을 데이터로 변환한다.
   *
   * @param dateString 날짜 문자열
   * @param format     변환할 포맷
   * @return 변환된 날짜
   */
  public static Date stringToDate(String dateString, String format, Locale locale) {
    if (dateString == null) {
      return null;
    }

    SimpleDateFormat sdf = makeFormat(dateString, format, locale);

    try {
      return sdf.parse(dateString);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * 날짜를 입력된 포맷으로 변환한다.
   *
   * @param date   날짜
   * @param format 포맷
   * @return 변환된 날짜
   */
  public static Date convert(Date date, String format) {
    return stringToDate(dateToString(date, format), format);
  }

  private static SimpleDateFormat makeFormat(String format) {
    String usingFormat;
    if (format != null) {
      usingFormat = format;
    } else {
      usingFormat = DEFAULT_DATE_FORMAT;
    }

    return new SimpleDateFormat(usingFormat);
  }

  private static SimpleDateFormat makeFormat(String dateString, String format) {
    String usingFormat;
    if (format != null) {
      usingFormat = format;
    } else {
      int dateStringLen = dateString.length();
      if (dateStringLen == DEFAUT_DATE_FORMAT_LEN) {
        usingFormat = DEFAULT_DATE_FORMAT;
      } else if (dateStringLen == DATE_FORMAT_LEN) {
        usingFormat = DATE_FORMAT;
      } else {
        usingFormat = DEFAULT_DATE_FORMAT;
      }
    }

    SimpleDateFormat sdf = new SimpleDateFormat(usingFormat);

    return sdf;
  }

  private static SimpleDateFormat makeFormat(String dateString, String format, Locale locale) {
    String usingFormat;
    if (format != null) {
      usingFormat = format;
    } else {
      int dateStringLen = dateString.length();
      if (dateStringLen == DEFAUT_DATE_FORMAT_LEN) {
        usingFormat = DEFAULT_DATE_FORMAT;
      } else if (dateStringLen == DATE_FORMAT_LEN) {
        usingFormat = DATE_FORMAT;
      } else {
        usingFormat = DEFAULT_DATE_FORMAT;
      }
    }

    SimpleDateFormat sdf = new SimpleDateFormat(usingFormat, locale);

    return sdf;
  }

  /**
   * 입력받은 날짜에서 +/- N개월의 날짜를 구한다.
   *
   * @param date
   * @param month
   * @return
   */
  public static Date changeDateWithMonthLevel(Date date, int month) {

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
    return cal.getTime();
  }

  /**
   * 입력받은 날짜에서 +/- N일의 날짜를 구한다.
   *
   * @param date
   * @param day
   * @return
   */
  public static Date changeDateWithDayLevel(Date date, int day) {

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + day);
    return cal.getTime();
  }

  /**
   * 두 날짜의 차이(일수)를 구한다. <br> lastDate - firstDate의 일수
   *
   * @param firstDate
   * @param lastDate
   * @return 두날짜중 하나라도 null일 경우 null반환
   */
  public static Integer diffDate(Date firstDate, Date lastDate) {
    if (firstDate == null || lastDate == null) {
      return null;
    }

    String firstDateString = dateToString(firstDate, DATE_FORMAT);
    String lastDateString = dateToString(lastDate, DATE_FORMAT);

    Date defaultFirstDate = stringToDate(firstDateString, DATE_FORMAT);
    Date defaultLastDate = stringToDate(lastDateString, DATE_FORMAT);

    long diffTime = defaultLastDate.getTime() - defaultFirstDate.getTime();
    int days = (int) Math.ceil((double) diffTime / ONE_DAY_MS);

    return days;
  }

  /**
   * 두 날짜를 포함한 개월 수를 반환한다. <br> 2009-01-01, 2009-08-30로 값이 주어지면 8이<br> 2009-01-30, 2010-01-01로 값이 주어지면 13이 반환된다. 값을 거꾸로 넣어도 개월간의 차이수만 반환한다. (기간을 -로 반환하지 않는다)
   *
   * @param startDate 시작일
   * @param endDate   종료일
   * @return 두 날짜를 포함한 기간을 개월수로 반환한다.
   */
  public static int diffMonth(Date startDate, Date endDate) {

    Calendar startCal = Calendar.getInstance();
    Calendar endCal = Calendar.getInstance();
    if (DateUtil.isNextDate(startDate, endDate)) {
      startCal.setTime(startDate);
      endCal.setTime(endDate);
    } else {
      endCal.setTime(startDate);
      startCal.setTime(endDate);
    }
    int year = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
    int month = endCal.get(Calendar.MONTH) - (startCal.get(Calendar.MONTH));
    month += year != 0 ? year * 12 : 0;
    return month;
  }

  /**
   * 이전 날짜인지 비교 <br/> base를 기준으로 target이 이전 날짜인지 확인
   *
   * @param baseDate 기준일(base)
   * @param prevDate 이전일(확인할 대상 날짜:target)
   * @return 이전 날짜인지 여부
   */
  public static boolean isPrevDate(Date baseDate, Date prevDate) {
    if (isSameDay(baseDate, prevDate)) {
      return false;
    }
    if (baseDate.compareTo(prevDate) < 0) {
      return false;
    }
    return true;
  }

  /**
   * 이후 날짜인지 비교 <br/> base를 기준으로 target이 이후 날짜인지 확인
   *
   * @param baseDate 기준일(base)
   * @param nextDate 이후일(확인할 대상 날짜:target)
   * @return 이후 날짜인지 여부
   */
  public static boolean isNextDate(Date baseDate, Date nextDate) {
    if (isSameDay(baseDate, nextDate)) {
      return false;
    }
    if (baseDate.compareTo(nextDate) > 0) {
      return false;
    }
    return true;
  }

  /**
   * 입력된 시간 초를 최대치(59)로 변경하여 반환한다.
   *
   * @param date 시간
   * @return 59초로 변경된 시간
   */
  public static Date fillLimitSecond(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.SECOND, 59);
    return calendar.getTime();
  }

  /**
   * 두 날짜 사이의 기간을 날짜수로 반환한다. 2009-01-01, 2009-01-05로 값이 주어지면 4가 반환된다.
   *
   * @param startDate 시작일
   * @param endDate   종료일
   * @return 두 날짜 사이의 기간을 일수로 반환한다.
   */
  public static Long getDuration(Date startDate, Date endDate) {
    Calendar startCal = Calendar.getInstance();
    startCal.setTime(startDate);
    truncTime(startCal);

    Calendar endCal = Calendar.getInstance();
    endCal.setTime(endDate);
    truncTime(endCal);
    return (endCal.getTimeInMillis() - startCal.getTimeInMillis()) / 1000 / 60 / 60 / 24;
  }

  /**
   * 경과 시간
   *
   * @param startDate
   * @param endDate
   * @return
   */
  public static Long getElapseTime(Date startDate, Date endDate) {
    Calendar startCal = Calendar.getInstance();
    startCal.setTime(startDate);

    Calendar endCal = Calendar.getInstance();
    endCal.setTime(endDate);
    return (endCal.getTimeInMillis() - startCal.getTimeInMillis()) / 1000 / 60 / 60;
  }

  private static void truncTime(Calendar cal) {
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
  }

  public static boolean containInPeriod(Date startDate, Date endDate, Date aDay) {
    // FIXME 중복으로 만드네
    Calendar startCal = Calendar.getInstance();
    startCal.setTime(startDate);
    Calendar endCal = Calendar.getInstance();
    endCal.setTime(endDate);

    startCal.set(Calendar.HOUR, 0);
    startCal.set(Calendar.MINUTE, 0);
    startCal.set(Calendar.SECOND, 0);
    startCal.set(Calendar.MILLISECOND, 0);

    endCal.set(Calendar.HOUR_OF_DAY, 23);
    endCal.set(Calendar.MINUTE, 59);
    endCal.set(Calendar.SECOND, 59);
    endCal.set(Calendar.MILLISECOND, 999);

    long h = aDay.getTime();

    return startCal.getTimeInMillis() <= h && h <= endCal.getTimeInMillis();
  }

  /**
   * 주어진 년월일을 마지막 날값을 반환한다. 2009-02-22가 주어지면 2009-02-28이 반환된다.
   *
   * @param date 날짜
   * @return 해당 년월의 마지막 날짜 값
   */
  public static Date getLastDayOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
    return cal.getTime();
  }

  /**
   * 주어진 년월일을 1일값을 반환한다. 2009-02-22가 주어지면 2009-02-01이 반환된다.
   *
   * @param date 날짜
   * @return 해당 년월의 1일 값
   */
  public static Date getFirstDayOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
    return cal.getTime();
  }

  /**
   * 주어진 주간의 토요일 반환하나. 2011-12-XX 가 수요일일 경우 토요일에 해당하는 날짜를 반환 단 주의 시작을 토요일로 설정
   *
   * @param date 날짜
   * @return 해당 주간의 마지막 값
   */
  public static Date getLastDayOfWeek(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    truncTime(cal);
    while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
      cal.add(Calendar.DATE, 1);
    }
    return cal.getTime();
  }

  /**
   * 주어진 주간의 일요일 반환하나. 2011-12-XX 가 수요일일 경우 일요일에 해당하는 날짜를 반환 단 주의 시작을 일요일로 설정
   *
   * @param date 날짜
   * @return 해당 주간의 마지막 값
   */
  public static Date getFirstDayOfWeek(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    truncTime(cal);
    while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
      cal.add(Calendar.DATE, -1);
    }
    return cal.getTime();
  }

  /**
   * 주어진 날짜에 시간,분,초 를 모두 0으로 셋팅한다.
   *
   * @param date 날짜
   * @return
   */
  public static Date trunkTime(Date date) {
    if (date == null) {
      return null;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  /**
   * 연도를 추출한다.
   *
   * @param date 일시
   * @return 년도
   */
  public static int getYear(Date date) {
    if (date == null) {
      throw new IllegalArgumentException("파라미터값이 없습니다.");
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.YEAR);
  }

  /**
   * 월을 추출한다.<br> 카렌더의 월은 1월이 0부터 시작하기때문에 1을 더해 1부터 추출하도록 함.
   *
   * @param date 일시
   * @return 날짜에서 월을 뽑아서 반환한다.
   */
  public static int getMonth(Date date) {
    if (date == null) {
      throw new IllegalArgumentException("파라미터값이 없습니다.");
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.MONTH) + 1;
  }

  /**
   * 일을 추출
   *
   * @param date 일시
   * @return 날짜에서 일을 뽑아서 반환한다.
   */
  public static int getDate(Date date) {
    if (date == null) {
      throw new IllegalArgumentException("파라미터값이 없습니다.");
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.DATE);
  }

  public static Date fillTime(Date date) {
    if (date == null) {
      return null;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    return calendar.getTime();
  }

  /**
   * 두 날짜 중 더 빠른 날짜를 반환한다.
   *
   * @param date1 날짜1
   * @param date2 날짜2
   * @return 두 날짜 중 더 빠른 날짜
   */
  public static Date getMin(Date date1, Date date2) {
    return date1.getTime() < date2.getTime() ? date1 : date2;
  }

  /**
   * 해당 달의 날짜수를 구한다. 2009-02-02가 주어지면 28일이 반환된다.
   *
   * @param date 날짜
   * @return 해당 달의 날짜수
   */
  public static int getDayCountOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
  }


  /**
   * 해당날짜의 마지막 시간(23시 59분 59.999초)로 변경하여 반환한다.<br/> 입력값이 null 이면 null 리턴
   *
   * @param date 시간
   * @return 23시 59분 59.999초로 변경된 시간
   */
  public static Date getStartTimeOfDay(Date date) {
    if (date == null) {
      return null;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /**
   * 해당날짜의 마지막 시간(23시 59분 59.999초)로 변경하여 반환한다.<br/> 입력값이 null 이면 null 리턴
   *
   * @param date 시간
   * @return 23시 59분 59.999초로 변경된 시간
   */
  public static Date getLastTimeOfDay(Date date) {
    if (date == null) {
      return null;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    return calendar.getTime();
  }

  /**
   * 입력받은 날짜의 다음 수요일 정보를 제공
   *
   * @param date
   * @return
   * @author minha.kim
   */
  public static Date getNextWeekByDayOfWeek(Date date, int dayOfWeek) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, 7);
    cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
    return cal.getTime();
  }

  /**
   * 해당날짜의 년도가 윤년인지 확인
   *
   * @param date
   * @return
   */
  public static boolean isLeapYear(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    if ((0 == (cal.get(Calendar.YEAR) % 4) && 0 != (cal.get(Calendar.YEAR) % 100)) || 0 == cal.get(Calendar.YEAR) % 400) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 날짜 문자열 형식에서 하이픈(-) 제거
   *
   * @param dateString yyyy-MM-dd
   * @return yyyyMMdd
   */
  public static String removeHyphenFromDateString(String dateString) {
    return dateString.replaceAll("-", "");
  }

  /**
   * 날짜 문자열 형식에서 하이픈(-) 추가
   *
   * @param dateString
   * @return
   */
  public static String toHyphenFormatDateString(String dateString) {
    if (dateString == null || dateString.length() != 8) {
      throw new RuntimeException("yyyyMMdd 형식의 문자열이 아닙니다. " + dateString);
    }

    StringBuilder sb = new StringBuilder();
    sb.append(dateString.substring(0, 4)).append("-");
    sb.append(dateString.substring(4, 6)).append("-");
    sb.append(dateString.substring(6, 8));
    return sb.toString();
  }

  /**
   * 그달의 주간 수를 반환
   *
   * @param date
   * @return
   */
  public static int getWeekCountOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));

    return cal.get(Calendar.WEEK_OF_MONTH);
  }

  /**
   * 해당일의 요일 반환
   *
   * @param date
   * @return
   */
  public static int getDayOfWeek(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.DAY_OF_WEEK);
  }

}
