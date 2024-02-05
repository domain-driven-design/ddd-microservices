package utils;

import java.time.OffsetDateTime;
import java.util.Objects;

public class TimeUtil {

    private TimeUtil() {}

    public static boolean expired(OffsetDateTime time) {
        return Objects.nonNull(time) && OffsetDateTime.now().compareTo(time) >= 0;
    }

    public static OffsetDateTime addDays(OffsetDateTime time, Integer days) {
        return time.plusDays(days);
    }


    public static OffsetDateTime addDays(Integer days) {
        return addDays(OffsetDateTime.now(), days);
    }

}
