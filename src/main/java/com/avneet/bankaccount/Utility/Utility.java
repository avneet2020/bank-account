package com.avneet.bankaccount.Utility;

import java.text.DecimalFormat;

public class Utility {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static String capitalizeFirstLetter(String str) {
        str = str.toLowerCase();
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }


    public static double round(double num){
        return Double.parseDouble(df.format(num));
    }
}
