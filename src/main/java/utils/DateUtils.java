package utils;

import java.util.Date;

public class DateUtils {
    public static String formatDay(Date date) {
        if (date == null) {
            return("");
        } else {
            return(String.format("%tA, %tB %te, %tY",
                    date, date, date, date));
        }
    }
    public static Date nextDay(Date day) {
        long millisPerDay = 24 * 60 * 60 * 1000;
        return(new Date(day.getTime() + millisPerDay));
    }
    public static boolean between(Date testDate,
                                  Date startDate,
                                  Date endDate) {
        return(testDate.after(startDate) && testDate.before(endDate));
    }
}
