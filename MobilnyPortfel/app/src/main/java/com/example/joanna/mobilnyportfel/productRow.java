package com.example.joanna.mobilnyportfel;

import java.util.Date;

/**
 * Created by Łukasz on 27.11.2017.
 */

public class productRow {

    public String prName;
    public int priceZL;
    public int priceGR;
    private int ID;
    private String date;

    public productRow(String name, int price_zl, int price_gr, int ID){
        prName = name;
        this.priceZL = price_zl;
        this.priceGR = price_gr;
        this.ID = ID;
    }

    public productRow(String name, int price_zl, int price_gr, String date){
        prName = name;
        this.priceZL = price_zl;
        this.priceGR = price_gr;
        this.date = date;
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
