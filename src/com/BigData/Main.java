package com.BigData;

public class Main {

    public static void main(String[] args) {

        ReadFile readFile = new ReadFile();
        long startTime=System.currentTimeMillis();
        readFile.loadFiles();
        readFile.printFiles();
        long endTime=System.currentTimeMillis();
        System.out.println("Processing Time: " + (endTime - startTime) + " ms");
    }
}
