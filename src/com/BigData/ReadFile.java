package com.BigData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Rex on 15/6/27.
 */

public class ReadFile {

    private HashMap<String, ArrayList<DailyPriceItem>> stockPrice = new HashMap<String, ArrayList<DailyPriceItem>>();
    private HashMap<String, DailyPriceItem> marketPrice = new HashMap<String, DailyPriceItem>();

    public void printFiles(){
        for (String day: marketPrice.keySet()){
            System.out.println(day + ": " + marketPrice.get(day).max + ", " + marketPrice.get(day).min);
        }
    }

    public void loadFiles(){
        for (char c = 'A'; c <= 'Z'; c++){
            String dir = ConstantValues.base + c + ConstantValues.tail;
            File file = new File(String.valueOf(dir));
            if (file.exists()) try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                System.out.println("[ processing: " + c + " ]");
                String dataLine = reader.readLine();
                while ((dataLine = reader.readLine()) != null){
                    String[] items = dataLine.split(",");
//                    System.out.println(items[4]);
//                    System.out.println(items[5]);
//                    System.out.println("============");
                    double max = Double.parseDouble(items[4]);
                    double min = Double.parseDouble(items[5]);
                    if (stockPrice.get(items[1]) == null){
                        stockPrice.put(items[1], new ArrayList<DailyPriceItem>());
                    }
                    stockPrice.get(items[1]).add(new DailyPriceItem(max, min, items[2]));
                    if (marketPrice.get(items[2]) == null){
                        marketPrice.put(items[2], new DailyPriceItem(max, min, items[2]));
                    } else {
                        DailyPriceItem today = marketPrice.get(items[2]);
                        double maxPrice = today.max > max ? today.max : max;
                        double minPrice = today.min < min ? today.min : min;
                        marketPrice.put(items[2], new DailyPriceItem(maxPrice, minPrice, items[2]));
                    }
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
