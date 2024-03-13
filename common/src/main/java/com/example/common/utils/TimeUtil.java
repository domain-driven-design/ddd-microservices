package com.example.common.utils;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeUtil {

    private TimeUtil() {}

    public static boolean expired(OffsetDateTime time) {
        return Objects.nonNull(time) && OffsetDateTime.now().compareTo(time) >= 0;
    }

    public static OffsetDateTime addDays(OffsetDateTime time, Integer days) {
        return time.plusDays(days);
    }

    public static OffsetDateTime addDaysFromNow(Integer days) {
        return addDays(OffsetDateTime.now(), days);
    }

    public static OffsetDateTime now() {
        return OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public static long daysBetween(OffsetDateTime from, OffsetDateTime to) {
        return ChronoUnit.DAYS.between(from, to);
    }

    public static OffsetDateTime getStartOfDate(OffsetDateTime dateTime) {
        return dateTime.truncatedTo(ChronoUnit.DAYS);
    }

    public static OffsetDateTime getEndOfDate(OffsetDateTime dateTime) {
        return dateTime.with(LocalTime.MAX);
    }

    public static OffsetDateTime updateDate(OffsetDateTime dateTime, ChronoUnit unit, long amountToAdd) {
        return dateTime.plus(amountToAdd, unit);
    }

}
