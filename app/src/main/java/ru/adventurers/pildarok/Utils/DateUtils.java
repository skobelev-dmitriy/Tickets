package ru.adventurers.pildarok.Utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Дмитрий on 24.05.2016.
 */
public class DateUtils {
    public static String getDate(String string){
        String DATE_FORMAT_IN = "EEE, dd MMM yyyy";
       //String DATE_FORMAT = "dd.MM.yyyy";
        String DATE_FORMAT_OUT = "dd MMMM yyyy";
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_IN, Locale.ENGLISH);
        SimpleDateFormat format_date = new SimpleDateFormat(DATE_FORMAT_OUT, Locale.getDefault());

        Date result;
        try {

            result = format.parse(string);

        } catch (ParseException e) {
             Log.e("DateUtils",  e.getMessage() + "\n" + string);

            return null;
        }
        return format_date.format(result);
    }
    public static String getDate(String string, String format){

        String DATE_FORMAT_OUT = "dd MMMM yyyy";
        SimpleDateFormat format_date = new SimpleDateFormat(format, Locale.getDefault());
        long time;
        try {
            time= Long.parseLong(string);
        }catch (NumberFormatException ex){
            return null;
        }

        Date result=new Date(time);

        return format_date.format(result);
    }
    public static String getDate(long time, String format){
        SimpleDateFormat format_date = new SimpleDateFormat(format, Locale.getDefault());
        if(time==0){
            return  null;
        }

        Date result=new Date(time);

        return format_date.format(result);
    }
    public static String getDate(long time){

        String DATE_FORMAT_OUT = "dd MMMM yyyy";
        SimpleDateFormat format_date = new SimpleDateFormat(DATE_FORMAT_OUT, Locale.getDefault());

        Date result=new Date(time);

        return format_date.format(result);
    }
    public static String getDateHeader(long time){
        String DATE_FORMAT_OUT = "dd MMMM";
        String DATE_FORMAT_OUT2 = "yy";
        SimpleDateFormat format_date = new SimpleDateFormat(DATE_FORMAT_OUT, Locale.getDefault());
        SimpleDateFormat format_year = new SimpleDateFormat(DATE_FORMAT_OUT2, Locale.getDefault());

        Date result=new Date(time);

        return format_date.format(result)+"'"+format_year.format(result);
    }
    public static String getTime(long time){

        String DATE_FORMAT_OUT = "HH:mm";
        SimpleDateFormat format_date = new SimpleDateFormat(DATE_FORMAT_OUT, Locale.getDefault());
        Date result=new Date(time);

        return format_date.format(result);
    }
    /**
     *
     *
     * @return Строка в формате 15.03.2016 16:19:58
     */
    public static String getDateUpdate(){

        //String DATE_FORMAT_OUT = "dd.MM.yyyy hh:mm:ss";
        String DATE_FORMAT_OUT = "dd.MM.yyyy";

        SimpleDateFormat format_date = new SimpleDateFormat(DATE_FORMAT_OUT);

        Date result = new Date();
        TimeZone timeZone= TimeZone.getTimeZone("GMT+00");
        format_date.setTimeZone(timeZone);

        return format_date.format(result)+" 00:00:00";
    }
    public static String getDateFormatted(){

        String DATE_FORMAT_OUT = "dd MMM yyyy в hh:mm";

        SimpleDateFormat format_date = new SimpleDateFormat(DATE_FORMAT_OUT);

        Date result = new Date();


        return format_date.format(result);
    }
}
