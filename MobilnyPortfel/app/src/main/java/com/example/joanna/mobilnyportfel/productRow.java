package com.example.joanna.mobilnyportfel;

/**
 * Created by Łukasz on 27.11.2017.
 */

public class productRow {

    public String prName;
    public int priceZL;
    public int priceGR;

    public productRow(String name, int price_zl, int price_gr){
        prName = name;
        this.priceZL = price_zl;
        this.priceGR = price_gr;
    }
}
