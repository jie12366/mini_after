package com.after.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/28 20:06
 */
public class CalendarUtil {

    /**
     * 获取过去7天内的日期数组
     * @return  日期数组
     */
    public static ArrayList<String> pastDay(String time){
        ArrayList<String> pastDaysList = new ArrayList<>();
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(time);
            for (int i = 6; i >= 0; i--) {
                pastDaysList.add(getPastDate(i,date));
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return pastDaysList;
    }

    /**
     * 获取未来7天内的日期数组
     *  @return  日期数组
     */
    public static ArrayList<String> futureDay() {
        ArrayList<String> fetureDaysList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            fetureDaysList.add(getFetureDate(i));
        }
        return fetureDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(today);
        return result;
    }

    /**
     * 获取未来 第 future 天的日期
     * @param future
     * @return
     */
    public static String getFetureDate(int future) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + future);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(today);
        return result;
    }
}