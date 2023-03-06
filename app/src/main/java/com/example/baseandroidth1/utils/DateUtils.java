package com.example.baseandroidth1.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    public static final String DD_MM_YYYY = "dd/mm/yyyy";

    public static boolean isValidate(String dateStr) {
        try {
            DateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
            sdf.setLenient(false);
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidate(String dateStr, String format) {
        try {
            DateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
