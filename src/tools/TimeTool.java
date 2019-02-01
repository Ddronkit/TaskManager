package tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeTool {
    private static Date d;
    private static GregorianCalendar Gcal;
    public TimeTool(){
        Gcal = new GregorianCalendar();
    }


    public static void getTime(){
        d=Gcal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("'The current time is'  hh':'mm a  d 'of' MMMMM y");
        System.out.println(sdf.format(d));
    }

    public static void getTime(GregorianCalendar Gcal){
        d=Gcal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("'The current time is'  hh':'mm a  d 'of' MMMMM y");
        System.out.println(sdf.format(d));
    }

}
