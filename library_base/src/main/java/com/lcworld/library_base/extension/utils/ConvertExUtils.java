package com.lcworld.library_base.extension.utils;

import android.content.Context;
import com.blankj.utilcode.util.ObjectUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 扩展{@link com.blankj.utilcode.util.ConvertUtils}
 */
public class ConvertExUtils {
    //转换粉丝人数
    public static String fans2FitNumOfPeople(int fans) {
        if (fans < 10000) {
            return String.valueOf(fans);
        }

        String pattern = ".##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(fans / 10000.0) + "w";
    }

    public static String formatMoney(double money) {
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(money);
    }

    public static String formatMoney(String money) {
        if (null == money || "".equals(money)) {
            return "0";
        }

        try {
            Double moneyD = Double.parseDouble(money);
            DecimalFormat df = new DecimalFormat("0.##");
            return df.format(moneyD);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    //转换收益比
    public static String convertProfitRatio(String ratio) {
        if (null == ratio || "".equals(ratio)) {
            return "0";
        }

        try {
            Double ratioD = Double.parseDouble(ratio) * 100;
            DecimalFormat df = new DecimalFormat("0.##");
            return df.format(ratioD);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    //转换手机号
    public static String convertPhone(String mobile) {
        if (ObjectUtils.isEmpty(mobile)) {
            return "";
        }
        StringBuilder sBuilder = new StringBuilder();
        int length = mobile.length();
        for (int i = 0; i < length; i++) {
            char tempChar = mobile.charAt(i);
            if ((i == length - 11) || (i == length - 10)
                    || (i == length - 9) || (i == length - 4)
                    || (i == length - 3) || (i == length - 2)
                    || (i == length - 1)) {
                sBuilder.append(tempChar);
            } else {
                sBuilder.append("*");
            }
        }
        return sBuilder.toString();
    }

    //转换时间：秒-时间
    public static String convertTime(long second) {
        return convertTime(second, "yyyy-MM-dd hh:mm:ss");
    }

    //转换时间：秒-时间
    public static String convertTime(long second, String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }

    //去除末尾的逗号
    public static String removeLastComma(Context context, String str) {
        if (ObjectUtils.isEmpty(str)) {
            return "";
        }

        String comma = context.getString(com.lcworld.library_res.R.string.symbol_comma);
        char lastChar = str.charAt(str.length() - 1);
        if (!comma.equals(lastChar + "")) {
            return str;
        }

        return str.substring(0, str.length() - 1);
    }

}
