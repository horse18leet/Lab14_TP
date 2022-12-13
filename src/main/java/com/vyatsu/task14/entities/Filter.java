package com.vyatsu.task14.entities;

public class Filter {
    private int minPrice;
    private int maxPrice;
    private String title;

    public int GetMinPrice(){
        return minPrice;
    }
    public int GetMaxPrice(){
        return maxPrice;
    }
    public String Title(){
        return title;
    }

    public Filter() {

    }
    public Filter(String title, int minPrice, int maxPrice) {
        this.title = title;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}


