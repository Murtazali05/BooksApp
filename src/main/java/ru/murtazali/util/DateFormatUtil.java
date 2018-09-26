package ru.murtazali.util;

import org.joda.time.LocalDateTime;

import java.sql.Timestamp;

public class DateFormatUtil {

    public String toString(Timestamp timestamp) {
        LocalDateTime localDateTime = new LocalDateTime(timestamp);

        String day;
        if (localDateTime.getDayOfMonth() < 10)
            day = "0" + localDateTime.getDayOfMonth();
        else
            day = String.valueOf(localDateTime.getDayOfMonth());

        String hour;
        int tmp = localDateTime.getHourOfDay() - 1;
        if (tmp < 10)
            hour = "0" + tmp;
        else
            hour = String.valueOf(tmp);

        String minute;
        if (localDateTime.getMinuteOfHour() < 10)
            minute = "0" + localDateTime.getMinuteOfHour();
        else
            minute = String.valueOf(localDateTime.getMinuteOfHour());


        return (day + " ") +
                getMonth(localDateTime.getMonthOfYear() - 1) + " " +
                localDateTime.getYear() + " | " +
                timestamp.getHours() + ":" + minute;
    }

    public String getMonth(int number) {
        String[] month = new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                "июля", "августа", "сентября", "октября", "ноября", "декабря"};

        return month[number];
    }
}
