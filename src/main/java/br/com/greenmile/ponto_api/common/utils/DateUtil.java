package br.com.greenmile.ponto_api.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String HOUR_FORMAT = "HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_HOUR_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private DateUtil() {
    }

    public static Date convertStringToHour(String hour) {
        Date result = null;
        SimpleDateFormat hourFormat = new SimpleDateFormat(HOUR_FORMAT);

        try {
            result = hourFormat.parse(hour);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date convertStringToDate(String date) {
        Date result = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try {
            result = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date convertStringToDateAndHour(String dateAndHour) {
        Date result = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_HOUR_FORMAT);

        try {
            result = dateFormat.parse(dateAndHour);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean isWithinRange(Date minDate, Date maxDate, Date date) {
        if (minDate == null || maxDate == null || date == null) {
            return false;
        }
        return date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0;
    }
}
