package com.example.infovk.model;

import android.annotation.TargetApi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Relatives {

    //
    private String type;
    private int id;
    private String name;

    public Relatives(){}
    public Relatives(String type, int id, String name){
        this.type = type;
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }

}
