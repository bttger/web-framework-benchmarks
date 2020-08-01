package bttger.webframeworkbenchmarks.spring.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class IsoTimestamp {

    public static String getIsoTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        return sdf.format(new Date());
    }
}
