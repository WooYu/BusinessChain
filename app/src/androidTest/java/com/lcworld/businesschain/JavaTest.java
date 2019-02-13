package com.lcworld.businesschain;

import java.text.DecimalFormat;

public class JavaTest {
    public static void main(String[] args) {
        System.out.println("hello https://tool.lu/");

        System.out.println(formatMoney("1.1,1"));
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
}
