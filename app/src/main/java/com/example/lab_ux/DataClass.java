package com.example.lab_ux;

public class DataClass {
//test 233
    private String dataTitle;
    private int dataDesc;
    private String dataPrice;
    private int dataImage;
    private String dataDescShort;

    public String getDataTitle() {
        return dataTitle;
    }

    public int getDataDesc() {
        return dataDesc;
    }

    public String getDataPrice() {
        return dataPrice;
    }

    public String getDataDescShort() {
        return dataDescShort;
    }

    public int getDataImage() {
        return dataImage;
    }

    public DataClass(String dataTitle, int dataDesc, String dataPrice, int dataImage, String dataDescShort) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataPrice = dataPrice;
        this.dataImage = dataImage;
        this.dataDescShort = dataDescShort;
    }
}