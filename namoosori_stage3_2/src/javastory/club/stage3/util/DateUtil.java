package javastory.club.stage3.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    public static String today(String format){
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd");
      return dateFormat.format((Calendar.getInstance()).getTime());
    }

    public static String today(){
        return today("yyyy.mm.dd");
    }
}
