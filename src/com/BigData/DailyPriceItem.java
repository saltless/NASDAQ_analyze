package com.BigData;

/**
 * Created by Rex on 15/6/27.
 */
public class DailyPriceItem {
    public double max;
    public double min;
    public String timeStamp;
    public DailyPriceItem(double mmax, double mmin, String time){
        max = mmax;
        min = mmin;
        timeStamp = time;
    }
}
