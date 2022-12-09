package com.davada.domain.common.util;

import com.davada.domain.common.exception.ErpRuntimeException;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public abstract class DateHelper {
    //
    public static final String YEAR_MONTH_FORMAT = "yyyyMM";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyyMMdd'T'HHmmss";
    public static final String HOUR_MINUTES_FORMAT = "HH:mm";
    public static final String HOUR_MINUTES_SECONDS_FORMAT = "HH:mm:ss";
    public static final String TIMESTAMP_FORMAT = "yyyyMMdd'T'HHmmss.SSS";

    public static String currentDateString() {
        return format(new Date(), DATE_FORMAT);
    }
    public static String currentTimeString() {
        return format(new Date(), HOUR_MINUTES_SECONDS_FORMAT);
    }

    public static String currentDateTimeString() {
        return format(new Date(), DATETIME_FORMAT);
    }

    public static String currentHourMinutesString() {
        return format(new Date(), HOUR_MINUTES_FORMAT);
    }

    public static String currentTimestampString() {
        return format(new Date(), TIMESTAMP_FORMAT);
    }

    public static String currentDateWithFormat(String format) {
        return format(new Date(), format);
    }

    public static String format(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    public static String format(Timestamp timestamp, String format) {
        return DateFormatUtils.format(timestamp.getTime(), format);
    }

    public static String format(final long millis, final String format) {
        return DateFormatUtils.format(millis, format);
    }

    public static String format(Calendar calendar, String format) {
        return DateFormatUtils.format(calendar, format);
    }

    public static String convertFormat(String beforeValue, String beforeFormat, String afterFormat) {
        //
        try {
            if (!StringHelper.isEmpty(beforeValue)) {
                DateFormat df = new SimpleDateFormat(beforeFormat);
                Date date = df.parse(beforeValue);
                return DateFormatUtils.format(date, afterFormat);
            } else {
                return null;
            }
        } catch (ParseException e) {
            throw new ErpRuntimeException(e);
        }
    }

    public static List<String> spreadDates(String startDate, String endDate) {
        final List<String> dates = new ArrayList<>();
        if (startDate.compareTo(endDate) > 0) {
            return dates;
        }

        final Calendar calendar = convertToCalendar(startDate);
        while (true) {
            final String date = format(calendar, DATE_FORMAT);
            if (date.compareTo(endDate) > 0) {
                break;
            }
            dates.add(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }

    public static Date convertToDate(String dateStr) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
        } catch (ParseException e) {
            throw new ErpRuntimeException(e);
        }
    }

    public static Date convertToDate(String dateStr, String beforeFormat) {
        try {
            return new SimpleDateFormat(beforeFormat).parse(dateStr);
        } catch (ParseException e) {
            throw new ErpRuntimeException(e);
        }
    }

    public static Calendar convertToCalendar(String dateStr) {
        int year = parseInt(dateStr.substring(0, 4));
        int month = parseInt(dateStr.substring(4, 6)) - 1; // Calendar.MONTH
        int date = parseInt(dateStr.substring(6));
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);

        return calendar;
    }

    public static Timestamp convertToTimestamp(String timestampStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(timestampStr));

            return new Timestamp(calendar.getTimeInMillis());
        } catch (ParseException e) {
            throw new ErpRuntimeException(e);
        }
    }

    public static int getLastDayOfMonth(String yearMonth) {
        int year = parseInt(yearMonth.substring(0, 4), 10);
        int month = parseInt(yearMonth.substring(4), 10) - 1; // Calendar.MONTH
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String getLastDateOfMonth(String yearMonth) {
        return String.format("%s%02d", yearMonth, getLastDayOfMonth(yearMonth));
    }

    public static int calculateGapInMonths(String startYearMonth, String endYearMonth) {
        int startYearValue = Integer.parseInt(startYearMonth.substring(0, 4));
        int endYearValue = Integer.parseInt(endYearMonth.substring(0, 4));
        int startMonthValue = Integer.parseInt(startYearMonth.substring(4));
        int endMonthValue = Integer.parseInt(endYearMonth.substring(4));

        return ((endYearValue * 12) + endMonthValue) - ((startYearValue * 12) + startMonthValue);
    }

    public static int calculateGapInDays(String startDate, String endDate) {
        try {
            long startTimeInMillis = new SimpleDateFormat(DATE_FORMAT).parse(startDate).getTime();
            long endTimeInMillis = new SimpleDateFormat(DATE_FORMAT).parse(endDate).getTime();
            return (int) (((endTimeInMillis / 1000) - (startTimeInMillis / 1000)) / (24 * 60 * 60));
        } catch (ParseException e) {
            throw new ErpRuntimeException(e);
        }
    }

    public static int calculateGapInSeconds(String startDateTime, String endDateTime) {
        try {
            long startTimeInMillis = new SimpleDateFormat(DATETIME_FORMAT).parse(startDateTime).getTime();
            long endTimeInMillis = new SimpleDateFormat(DATETIME_FORMAT).parse(endDateTime).getTime();
            return (int) ((endTimeInMillis / 1000) - (startTimeInMillis / 1000));
        } catch (ParseException e) {
            throw new ErpRuntimeException(e);
        }
    }
}
