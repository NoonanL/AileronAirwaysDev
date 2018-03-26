package util;

import java.util.Date;

public class DateHelper {

    public String parseDate(String creationTimeStamp){

        long epoch = 621355968000000000L;
        long ticsPerMillisecond = 10000;

        long ticks = Long.parseLong(creationTimeStamp);

        Date date = new Date((ticks-epoch) / ticsPerMillisecond);
        return date.toString();
    }
}
