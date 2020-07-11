package TrackPack;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class StringTime {

    public static String getDate(){
        StringBuilder dateShow = new StringBuilder();
        Calendar calendar = new GregorianCalendar();
        dateShow.append(calendar.get(Calendar.MONTH) + 1); //Months are index from 0
        dateShow.append("/");
        dateShow.append(calendar.get(Calendar.DAY_OF_MONTH));
        dateShow.append("/");
        dateShow.append(calendar.get(Calendar.YEAR));
        return dateShow.toString();
    }
    public static String getClockTime(){
        StringBuilder time = new StringBuilder();
        Calendar calendar = new GregorianCalendar();
        time.append(calendar.get(Calendar.HOUR));
        time.append(":");
        time.append(calendar.get(Calendar.MINUTE));
        time.append(".");
        time.append(calendar.get(Calendar.SECOND));
        return time.toString();
    }
}
