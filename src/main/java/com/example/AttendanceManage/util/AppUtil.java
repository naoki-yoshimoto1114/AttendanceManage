package com.example.AttendanceManage.util;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * アプリケーション共通クラス
 */
public class AppUtil
{
    public static String getMessage(MessageSource messageSource, String key, Object... params)
    {
        return messageSource.getMessage(key, params, Locale.JAPAN);
    }
}
