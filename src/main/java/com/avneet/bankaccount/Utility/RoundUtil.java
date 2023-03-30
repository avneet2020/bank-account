package com.avneet.bankaccount.Utility;

import java.text.DecimalFormat;

public class RoundUtil {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static double round(double num){
        return Double.parseDouble(df.format(num));
    }
}
