package com.example.infovk.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class City {

    private int id;

    @JsonProperty("title")
    private String cityName;

    public City(){}
    public City(int id, String cityName){
        this.id = id;
        this.cityName = cityName;
    }
    public int getId(){
        return id;
    }
    public String getCityName(){
        return cityName;
    }
}
