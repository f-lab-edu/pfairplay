package com.pfairplay.api.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateStringConverter {

    private static final DateTimeFormatter ISO_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final DateTimeFormatter SIMPLE_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter SIMPLE_DATE_FORMATTER_KR = DateTimeFormatter.ofPattern("yyyy'년' MM'월' dd'일'");
    private static final DateTimeFormatter SIMPLE_DATE_WITHOUT_YEAR_FORMATTER_KR = DateTimeFormatter.ofPattern("MM'월' dd'일'");

    public static LocalDate fromIsoDateTimeFormatString(String datetime) {
        return LocalDate.parse(datetime, ISO_DATE_TIME_FORMATTER);
    }

    public static LocalDate fromSimpleDateFormatString(String datetime) {
        return LocalDate.parse(datetime, SIMPLE_DATE_FORMATTER);
    }

    public static String toLocalDateFormatString(LocalDate localDate) {
        return localDate.format(SIMPLE_DATE_FORMATTER);
    }

    public static String toIsoDateTimeFormatString(LocalDate localDate) {
        return localDate.atStartOfDay().format(ISO_DATE_TIME_FORMATTER);
    }

    public static String toSimpleDateFormatStringKR(LocalDate localDate) {
        return localDate.format(SIMPLE_DATE_FORMATTER_KR);
    }

    public static String toSimpleDateFormatStringWithoutYearKR(LocalDate localDate) {
        return localDate.format(SIMPLE_DATE_WITHOUT_YEAR_FORMATTER_KR);
    }

}


