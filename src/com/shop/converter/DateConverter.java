package com.shop.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * S -> 源头 字符串的日期格式
 * T -> 目标类 Date
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
