package com.qsy.public_account.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * @desc 时间格式转换工具
 * @author qsy
 * @version 1.0
 * @date 2021/1/12 15:11
 * */
public class TimeUtils {

    /**
     * @desc 将日期转换为字符串
     * @author qsy
     * @date 2021/1/12 15:11
     * @param date 日期
     * @return 日期
     * */
    public static String dateToStr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);
        System.out.println("格式化后的当前时间: " + formattedDate);
        return formattedDate;
    }

    /**
     * @desc 将字符串转换为日期
     * @author qsy
     * @date 2021/1/12 15:11
     * @param str 字符串
     * @return 日期
     * */
    public static Date strToDate(String  str) {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date formattedDate = formatter.parse(str);
            System.out.println("格式化后的当前时间: " + formattedDate);

            return formattedDate;
        }catch(Exception e){
            // 处理异常情况
            e.printStackTrace();
            return  null;
        }
    }
}
