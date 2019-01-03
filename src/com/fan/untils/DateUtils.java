package com.fan.untils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getDate(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }
}
