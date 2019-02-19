package com.lcworld.library_base.extension;

import com.blankj.utilcode.util.ObjectUtils;

import java.text.DecimalFormat;

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
}
