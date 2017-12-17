package com.example.joanna.mobilnyportfel;

import java.util.Date;

/**
 * Created by Łukasz on 27.11.2017.
 */

public class productRow {

    public String prName;
    public String prName1;
    public int priceZL;
    public int priceGR;
    private int ID;
    private String date;
    private String category;


    public productRow(String name, String name1, int price_zl, int price_gr, String date){
        prName = name;
        prName1 = name1;
        this.priceZL = price_zl;
        this.priceGR = price_gr;
        this.date = date;
    }

    public productRow(String name, String category, int price_zl, int price_gr, int ID){
        prName = name;
        this.priceZL = price_zl;
        this.priceGR = price_gr;
        this.ID = ID;
        this.category = category;
    }

    public void add(int zl, int gr) {
        this.priceZL += zl;
        this.priceGR += gr;
        if (priceGR > 100){
            priceZL += 1;
            priceGR -= 100;
        }
    }

    public int getID(){
        return ID;
    }
    public String getDate() { return date; }
}
